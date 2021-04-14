package id.co.asyst.bukopin.mobile.payment.config;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import id.co.asyst.bukopin.mobile.common.core.exception.MiddlewareException;
import id.co.asyst.bukopin.mobile.common.model.BkpmConstants;
import id.co.asyst.bukopin.mobile.common.model.ResponseMessage;
import id.co.asyst.bukopin.mobile.common.model.payload.CommonRequest;
import id.co.asyst.bukopin.mobile.common.model.payload.CommonResponse;
import id.co.asyst.bukopin.mobile.common.model.payload.Identity;
import id.co.asyst.bukopin.mobile.master.model.CategoryEnum;
import id.co.asyst.bukopin.mobile.master.model.DestinationTypeEnum;
import id.co.asyst.bukopin.mobile.master.model.TransactionTypeEnum;
import id.co.asyst.bukopin.mobile.master.model.entity.Transaction;
import id.co.asyst.bukopin.mobile.master.model.payload.DestinationCommonRequest;
import id.co.asyst.bukopin.mobile.master.model.payload.TransactionCommonRequest;
import id.co.asyst.bukopin.mobile.service.core.MasterModuleService;
import id.co.asyst.bukopin.mobile.service.model.payload.UpdatePasswordCentagateRequest;
import id.co.asyst.bukopin.mobile.user.model.AccountBalanceByAccNoReq;
import id.co.asyst.bukopin.mobile.user.model.AccountBalanceQRReq;
import id.co.asyst.bukopin.mobile.user.model.AccountBalanceReq;
import id.co.asyst.bukopin.mobile.user.model.InquiryTransactionReq;
import id.co.asyst.bukopin.mobile.user.model.centagate.LoginResultData;
import id.co.asyst.bukopin.mobile.user.model.entity.AccountInfo;
import id.co.asyst.bukopin.mobile.user.model.entity.NonFinancial;
import id.co.asyst.bukopin.mobile.user.model.entity.User;
import id.co.asyst.bukopin.mobile.user.model.payload.InquiryNotificationRequest;
import id.co.asyst.bukopin.mobile.user.model.payload.UpdatePinRequest;
import id.co.asyst.foundation.service.connector.Services;

// import liquibase.integration.spring.SpringLiquibase;

/**
 * Persistence Configuration beans.
 * 
 * @author Ihsan Firman
 * @version $Revision$, (Created on Jul, 2020)
 * @since 1.0.Alpha1
 */
@Aspect
@Component
public class PaymentFinancialTrxLoggingAspect {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private static final String TRANSACTION_TYPE_POST = "POST";
	private static final String NOTE_ID_PLNPRE = "BELI PLN ";
	private static final String NOTE_EN_PLNPRE = "PURC PLN ";
	private static final String NOTE_ID_PLNPOST = "BAYAR PLN ";
	private static final String NOTE_EN_PLNPOST = "PAY PLN ";
	private static final String NOTE_ID_EMONEY = "BELI EMONEY ";
	private static final String NOTE_EN_EMONEY = "PURC EMONEY ";
	private static final String NOTE_ID_TELCOPRE = "BELI PULSA/PAKET DATA ";
	private static final String NOTE_EN_TELCOPRE = "PURC PULSA/DATA ";
	private static final String NOTE_ID_TELCOPOST = "BAYAR TGHN ";
	private static final String NOTE_EN_TELCOPOST = "PAY TELCO ";
	private static final String NOTE_ID_INSURANCEPOST = "BAYAR ASURANSI ";
	private static final String NOTE_EN_INSURANCEPOST = "PAY INSURANCE ";
	private static final String NOTE_ID_CREDITCARDPOST = "BAYAR KARTU KREDIT ";
	private static final String NOTE_EN_CREDITCARDPOST = "PAY CREDIT CARD ";
	private static final String NOTE_ID_SAMOLNAS = "BAYAR SAMOLNAS ";
	private static final String NOTE_EN_SAMOLNAS = "PAY SAMOLNAS ";

	private static final String PLN_BILLER_PRODUCT = "PLN";
	private static final String SAMOLNAS_BILLER_PRODUCT = "Samolnas";
	private static final String INSURANCE_BILLER_PRODUCT = "BPJS Kesehatan";
	private static final String INHEALTH_INSURANCE_BILLER_PRODUCT = "Mandiri Inhealth";

	private static final String PLN_POSTPAID_TRX_MENU = "Electricity Payment";

	private static final String INSURANCE_TRX_MENU = "Insurance Payment";
	private static final String SAMOLNAS_TRX_MENU = "Samolnas Payment";

	private static final String CCBKP = "CCBKP";
	private static final String CC_BKP_TRX_MENU = "Bukopin CC Payment";
	private static final String CC_NONBKP_TRX_MENU = "Non Bukopin CC Payment";

	
	/**
	 * Pointcut that matches all repositories, services and Web REST endpoints.
	 */
	@Pointcut("within(@org.springframework.stereotype.Repository *)"
			+ " || within(@org.springframework.stereotype.Service *)"
			+ " || within(@org.springframework.web.bind.annotation.RestController *)")
	public void springBeanPointcut() {
		// Method is empty as this is just a Pointcut, the implementations are in the
		// advices.
	}

	/**
	 * Pointcut that matches all Spring beans in the application's main packages.
	 */
	@Pointcut("within(id.co.asyst.bukopin.mobile.payment.web.rest..CreditCard*)"
			+ " || within(id.co.asyst.bukopin.mobile.payment.web.rest..Insurance*)"
			+ " || within(id.co.asyst.bukopin.mobile.payment.web.rest..Samolnas*)"
			+ " || within(id.co.asyst.bukopin.mobile.payment.web.rest..PLNPost*)")
	public void applicationPackagePointcut() {
		// Method is empty as this is just a Pointcut, the implementations are in the
		// advices.
	}

	/**
	 * Advice that logs methods throwing exceptions.
	 *
	 * @param joinPoint join point for advice
	 * @param e         exception
	 */
	@AfterThrowing(pointcut = "applicationPackagePointcut() && springBeanPointcut()", throwing = "e")
	public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
		log.error("Exception in {}.{}() with cause = {}", joinPoint.getSignature().getDeclaringTypeName(),
				joinPoint.getSignature().getName(), e.getCause() != null ? e.getCause() : "NULL");
		
		String method = joinPoint.getSignature().getName();
		if ("purchaseInsurance".equals(method) || "paymentPLN".equals(method) 
			|| "paymentSamolnas".equals(method) || "paymentCreditCard".equals(method)) {
        		String code = "";
        		String message = "";
        		String msg = e.getMessage();
        		
        		if(e.getCause() instanceof MiddlewareException) {
        			code = ResponseMessage.ERROR_EXTERNAL.getCode();
        			message = "External error : " + msg;
        		} else {
        			
        			code = ResponseMessage.INTERNAL_SERVER_ERROR.getCode();
        			message = "Internal Service Error : " + msg;
        		}      		
		
			ObjectMapper mapper = new ObjectMapper();
			
			Object[] rq = joinPoint.getArgs();
	
			Map<String, String> reqs = mapper.convertValue(rq[0], Map.class);
			Map<String, String> param = mapper.convertValue(reqs.get("parameter"), Map.class);
			Map<String, BigDecimal> amt = mapper.convertValue(reqs.get("parameter"), Map.class);
	
			TransactionCommonRequest transaction = new TransactionCommonRequest();
			
			transaction.setUsername(param.get("username"));
	
			if ("purchaseInsurance".equals(method)) {
				transaction.setAdminFee(amt.get("adminFee"));
				transaction.setAmount(amt.get("amount"));			
				transaction.setType(TransactionTypeEnum.INSURANCE.name());
				transaction.setTotalAmount(amt.get("totalAmount"));
				transaction.setAccountNumber(param.get("accountNumber"));
				transaction.setNoteId(NOTE_ID_INSURANCEPOST.concat(param.get("subscriberNumber")));
				transaction.setNoteEn(NOTE_EN_INSURANCEPOST.concat(param.get("subscriberNumber")));
				transaction.setBillerProduct(INHEALTH_INSURANCE_BILLER_PRODUCT);
				
				if(param.get("codeIns").equalsIgnoreCase("BPJSKES")){
					transaction.setBillerProduct(INSURANCE_BILLER_PRODUCT);
				}
				
				transaction.setMenu(INSURANCE_TRX_MENU);
	
			} else if ("paymentPLN".equals(method)) {
	
				transaction.setAdminFee(amt.get("adminFee"));
				transaction.setAmount(amt.get("amount"));	
				transaction.setType(TransactionTypeEnum.PLNPOST.name());
				transaction.setTotalAmount(amt.get("totalAmount"));
				transaction.setAccountNumber(param.get("accountNo"));
				transaction.setNoteId(NOTE_ID_PLNPOST.concat(param.get("subscriberNumber")));
				transaction.setNoteEn(NOTE_EN_PLNPOST.concat(param.get("subscriberNumber")));
				transaction.setBillerProduct(PLN_BILLER_PRODUCT);
				transaction.setMenu(PLN_POSTPAID_TRX_MENU);
	
			} else if ("paymentSamolnas".equals(method)) {
	
				BigDecimal total = amt.get("amount")
						.add(amt.get("adminFee"));
	
				transaction.setAdminFee(amt.get("adminFee"));
				transaction.setAmount(amt.get("amount"));	
				transaction.setTotalAmount(total);
				transaction.setType(TransactionTypeEnum.SAMOLNAS.name());
				transaction.setAccountNumber(param.get("accountNumber"));
				transaction.setNoteId(NOTE_ID_SAMOLNAS.concat(param.get("payCode")));
				transaction.setNoteEn(NOTE_EN_SAMOLNAS.concat(param.get("payCode")));
				transaction.setBillerProduct(SAMOLNAS_BILLER_PRODUCT);
				transaction.setMenu(SAMOLNAS_TRX_MENU);
	
			} else if ("paymentCreditCard".equals(method)) {
	
				transaction.setAdminFee(new BigDecimal('0'));
				transaction.setAmount(new BigDecimal(param.get("amount")));	
				transaction.setTotalAmount(new BigDecimal(param.get("amount")));
				transaction.setType(TransactionTypeEnum.CREDITCARD.name());
				transaction.setAccountNumber(param.get("accountNumber"));
				transaction.setNoteId(NOTE_ID_CREDITCARDPOST.concat(param.get("subscriberNumber")));
				transaction.setNoteEn(NOTE_EN_CREDITCARDPOST.concat(param.get("subscriberNumber")));
				transaction.setBillerProduct(param.get("name"));
				transaction.setMenu(CC_NONBKP_TRX_MENU);
	
				if (param.get("codeCc").equalsIgnoreCase(CCBKP)) {
	
					transaction.setMenu(CC_BKP_TRX_MENU);
				}
	
			}
			// dataReq.setSubscriberName();
			// dataReq.setReference(res.getReferenceNumber());
	
			transaction.setReason("code : " + code + " - message: " + message);
			transaction.setStatus(BkpmConstants.STATUS_FAILED);
	
			CommonRequest<TransactionCommonRequest> req = new CommonRequest<>();
			req.setIdentity(generateIdentity());
			req.setData(transaction);
					
			try {
				Services.create(MasterModuleService.class).saveTransaction(req).execute().body();
			} catch (IOException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
		}
	}

	/**
	 * Advice that logs when a method is entered and exited.
	 *
	 * @param joinPoint join point for advice
	 * @return result
	 * @throws Throwable throws IllegalArgumentException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Around("applicationPackagePointcut() && springBeanPointcut()")
	public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
		if (log.isDebugEnabled()) {
			log.debug("Payment Start AOP: {}.{}() with argument[s] = {}",
					joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(),
					Arrays.toString(joinPoint.getArgs()));

			/*
			 * if(!"getAccountInfo".equals(joinPoint.getSignature().getName())) { Object[]
			 * results = joinPoint.getArgs(); for(int i=0; i<results.length; i++){
			 * CommonRequest res = (CommonRequest) results[i]; dataReq = res.getData() ; }
			 * 
			 * }
			 */

		}

		// proceed joincut
		try {
			Object result = joinPoint.proceed();
			if (log.isDebugEnabled()) {
				log.debug("Functione " + joinPoint.getSignature().getName());
				String method = joinPoint.getSignature().getName();
				// get response
				CommonResponse res = (CommonResponse) result;
				ObjectMapper mapper = new ObjectMapper();

				if ("purchaseInsurance".equals(method) || "paymentPLN".equals(method)
						|| "paymentSamolnas".equals(method) || "paymentCreditCard".equals(method)) {

					if (BkpmConstants.CODE_SOAP_SUCCESS.equalsIgnoreCase(res.getCode())) {
						// save report SUCCESS
						Map<String, Object> resp = mapper.convertValue(res.getData(), Map.class);
						
						TransactionCommonRequest trxReq = new TransactionCommonRequest();
						
						//set admin fee and amount for transaction elastic
						if ("purchaseInsurance".equals(method)) {
							trxReq.setAdminFee((BigDecimal) (resp.get("adminFee")));
							trxReq.setAmount((BigDecimal) (resp.get("amount")));	
						} else if ("paymentPLN".equals(method)) {

							trxReq.setAdminFee((BigDecimal) (resp.get("adminFee")));
							trxReq.setAmount((BigDecimal) (resp.get("amount")));	
						} else if ("paymentSamolnas".equals(method)) {

							trxReq.setAdminFee((BigDecimal) (resp.get("adminFee")));
							trxReq.setAmount((BigDecimal) (resp.get("amount")));	
						}  else if ("paymentCreditCard".equals(method)) {
							trxReq.setAdminFee(new BigDecimal('0'));
							trxReq.setAmount((BigDecimal)(resp.get("amount")));	
							trxReq.setType(TransactionTypeEnum.CREDITCARD.name());
							log.debug("type transaksi "+TransactionTypeEnum.CREDITCARD.name());
							trxReq.setMenu(CC_NONBKP_TRX_MENU);
							Object[] rq = joinPoint.getArgs();

							Map<String, String> reqs = mapper.convertValue(rq[0], Map.class);
							Map<String, String> param = mapper.convertValue(reqs.get("parameter"), Map.class);
							trxReq.setBillerProduct(param.get("name"));
							log.debug("test disini "+param.get("codeCc"));
							if (param.get("codeCc").equalsIgnoreCase(CCBKP)) {

								trxReq.setMenu(CC_BKP_TRX_MENU);
							}
							
						}
						
						trxReq.setReason("code : " + res.getCode() + " - message: " + res.getMessage());
						trxReq.setStatus(BkpmConstants.STATUS_SUCCESS);
						
						String reference = (String) resp.get("referenceNumber");
						if(reference == null) {
							reference = (String) resp.get("reference");
							
						}
						trxReq.setReferenceNumber(reference);
						String destId = String.valueOf(resp.get("destinationId"));
						
						if(destId == null || destId == "null" || destId.isEmpty()) {
							destId = String.valueOf(resp.get("idDestination"));
						}
						trxReq.setDestinationId(destId);

						CommonRequest<TransactionCommonRequest> req = new CommonRequest<>();
						req.setIdentity(generateIdentity());
						req.setData(trxReq);

						try {
							Services.create(MasterModuleService.class).updateTransaction(req).execute().body();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					} else {
						// save report Failed to Non_Financial table
						// save report Failed
						Object[] rq = joinPoint.getArgs();

						Map<String, String> reqs = mapper.convertValue(rq[0], Map.class);
						Map<String, String> param = mapper.convertValue(reqs.get("parameter"), Map.class);
						Map<String, BigDecimal> amt = mapper.convertValue(reqs.get("parameter"), Map.class);

						TransactionCommonRequest transaction = new TransactionCommonRequest();

						transaction.setUsername(param.get("username"));

						if ("purchaseInsurance".equals(method)) {

							transaction.setAdminFee(amt.get("adminFee"));
							transaction.setAmount(amt.get("amount"));	
							transaction.setType(TransactionTypeEnum.INSURANCE.name());
							transaction.setTotalAmount(amt.get("totalAmount"));
							transaction.setAccountNumber(param.get("accountNumber"));
							transaction.setNoteId(NOTE_ID_INSURANCEPOST.concat(param.get("subscriberNumber")));
							transaction.setNoteEn(NOTE_EN_INSURANCEPOST.concat(param.get("subscriberNumber")));
							transaction.setBillerProduct(INHEALTH_INSURANCE_BILLER_PRODUCT);
							
							if(param.get("codeIns").equalsIgnoreCase("BPJSKES")){
								transaction.setBillerProduct(INSURANCE_BILLER_PRODUCT);
							}
							
							transaction.setMenu(INSURANCE_TRX_MENU);

						} else if ("paymentPLN".equals(method)) {

							transaction.setAdminFee(amt.get("adminFee"));
							transaction.setAmount(amt.get("amount"));	
							transaction.setType(TransactionTypeEnum.PLNPOST.name());
							transaction.setTotalAmount(amt.get("totalAmount"));
							transaction.setAccountNumber(param.get("accountNo"));
							transaction.setNoteId(NOTE_ID_PLNPOST.concat(param.get("subscriberNumber")));
							transaction.setNoteEn(NOTE_EN_PLNPOST.concat(param.get("subscriberNumber")));
							transaction.setBillerProduct(PLN_BILLER_PRODUCT);
							transaction.setMenu(PLN_POSTPAID_TRX_MENU);

						} else if ("paymentSamolnas".equals(method)) {

							BigDecimal total = amt.get("amount")
									.add(amt.get("adminFee"));

							transaction.setAdminFee(amt.get("adminFee"));
							transaction.setAmount(amt.get("amount"));	
							transaction.setTotalAmount(total);
							transaction.setType(TransactionTypeEnum.SAMOLNAS.name());
							transaction.setAccountNumber(param.get("accountNumber"));
							transaction.setNoteId(NOTE_ID_SAMOLNAS.concat(param.get("payCode")));
							transaction.setNoteEn(NOTE_EN_SAMOLNAS.concat(param.get("payCode")));
							transaction.setBillerProduct(SAMOLNAS_BILLER_PRODUCT);
							transaction.setMenu(SAMOLNAS_TRX_MENU);

						} else if ("paymentCreditCard".equals(method)) {

							transaction.setAdminFee(new BigDecimal('0'));
							transaction.setAmount(new BigDecimal(param.get("amount")));	
							transaction.setTotalAmount(new BigDecimal(param.get("amount")));
							transaction.setType(TransactionTypeEnum.CREDITCARD.name());
							transaction.setAccountNumber(param.get("accountNumber"));
							transaction.setNoteId(NOTE_ID_CREDITCARDPOST.concat(param.get("subscriberNumber")));
							transaction.setNoteEn(NOTE_EN_CREDITCARDPOST.concat(param.get("subscriberNumber")));
							transaction.setBillerProduct(param.get("name"));
							transaction.setMenu(CC_NONBKP_TRX_MENU);

							if (param.get("codeCc").equalsIgnoreCase(CCBKP)) {

								transaction.setMenu(CC_BKP_TRX_MENU);
							}

						}
						// dataReq.setSubscriberName();
						// dataReq.setReference(res.getReferenceNumber());

						transaction.setReason("code : " + res.getCode() + " - message: " + res.getMessage());
						transaction.setStatus(BkpmConstants.STATUS_FAILED);

						CommonRequest<TransactionCommonRequest> req = new CommonRequest<>();
						req.setIdentity(generateIdentity());
						req.setData(transaction);

						try {
							Services.create(MasterModuleService.class).saveTransaction(req).execute().body();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				}

				log.debug("Payment End AOP: {}.{}() with result = {}", joinPoint.getSignature().getDeclaringTypeName(),
						joinPoint.getSignature().getName(), result);

			}
			return result;
		} catch (IllegalArgumentException e) {
			log.error("Illegal argument: {} in {}.{}()", Arrays.toString(joinPoint.getArgs()),
					joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
			throw e;
		}
	}

	public static Identity generateIdentity() {
		Identity identity = new Identity();
		identity.setReqTime(new Date().toString());
		identity.setPlatform("Android");
		identity.setUserAgent("");
		identity.setToken("");
		identity.setSecretCode("");

		return identity;
	}
}
