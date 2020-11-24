package id.co.asyst.bukopin.mobile.purchase.config;

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
public class PurchaseFinancialTrxLogAspect {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	private static final String NOTE_ID_EMONEY = "BELI EMONEY ";
	private static final String NOTE_EN_EMONEY = "PURC EMONEY ";
	private static final String EMONEY_TRX_MENU = "E-Money Purchase";

	private static final String NOTE_ID_PLNPRE = "BELI PLN ";
	private static final String NOTE_EN_PLNPRE = "PURC PLN ";
	private static final String PLN_PREPAID_TRX_MENU = "Electricity Purchase";
	private static final String PLN_BILLER_PRODUCT = "PLN";

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
	@Pointcut("within(id.co.asyst.bukopin.mobile.purchase.web.rest..PLNPrepaid*)"
			+ " || within(id.co.asyst.bukopin.mobile.purchase.web.rest..LinkAja*)"
			+ " || within(id.co.asyst.bukopin.mobile.purchase.web.rest..EMoney*)")
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
		
		if ("purchaseOVO".equals(method) || "purchaseGoPayResult".equals(method) 
			|| "purchase".equals(method) || "purchaseResult".equals(method)) {
		
        		// save report Failed
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
				transaction.setAccountNumber(param.get("accountNo"));
		
				transaction.setType(TransactionTypeEnum.EMONEY.name());
				transaction.setMenu(EMONEY_TRX_MENU);
				transaction.setBillerProduct(param.get("type"));
		
				if ("purchaseOVO".equals(method)) {
					// total amount
					BigDecimal bill = amt.get("amount");
					String adminfee = param.get("element61").substring(78, 89);
		
					BigDecimal total = bill.add(new BigDecimal(adminfee));
					transaction.setNoteId(NOTE_ID_EMONEY.concat(param.get("custNo")));
					transaction.setNoteEn(NOTE_EN_EMONEY.concat(param.get("custNo")));
		
					transaction.setTotalAmount(total);
					transaction.setAdminFee(new BigDecimal(adminfee));
					transaction.setAmount(bill);	
		
				} else if ("purchaseGoPayResult".equals(method)) {
					// total amount
					BigDecimal bill = amt.get("amount");
					String adminfee = param.get("element61").substring(78, 89);
		
					BigDecimal total = bill.add(new BigDecimal(adminfee));
					transaction.setNoteId(NOTE_ID_EMONEY.concat(param.get("custNo")));
					transaction.setNoteEn(NOTE_EN_EMONEY.concat(param.get("custNo")));
					transaction.setAdminFee(new BigDecimal(adminfee));
					transaction.setAmount(bill);	
		
					transaction.setTotalAmount(total);
				} else if ("purchase".equals(method)) {
					// total amount
					BigDecimal bill = amt.get("amount");
		
					String[] el48 = param.get("element48").split("\\|", -1);
					String adm = el48[6];
					//String adminFee = adm.substring(0, 7).concat("00");
					
					BigDecimal total = bill.add(new BigDecimal(adm));
					transaction.setNoteId(NOTE_ID_EMONEY.concat(param.get("custNo")));
					transaction.setNoteEn(NOTE_EN_EMONEY.concat(param.get("custNo")));
		
					transaction.setTotalAmount(total);
					transaction.setAdminFee(new BigDecimal(adm));
					transaction.setAmount(bill);	
		
				} else if ("purchaseResult".equals(method)) {
					String subNumber = param.get("element48").substring(8, 18);
					if (param.get("flag").equals("1")) {
						subNumber = param.get("element48").substring(19, 30);
					}
		
					BigDecimal bill = new BigDecimal(param.get("nominal"));
					BigDecimal adminfee = new BigDecimal(param.get("adminCharge"));
		
					BigDecimal total = bill.add(adminfee);
		
					transaction.setTotalAmount(total);
					transaction.setAdminFee(adminfee);
					transaction.setAmount(bill);
		
					transaction.setType(TransactionTypeEnum.PLNPRE.name());
					transaction.setNoteId(NOTE_ID_PLNPRE.concat(subNumber));
					transaction.setNoteEn(NOTE_EN_PLNPRE.concat(subNumber));
					transaction.setBillerProduct(PLN_BILLER_PRODUCT);
					transaction.setMenu(PLN_PREPAID_TRX_MENU);
						
		
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
			log.debug("Purchase Start AOP: {}.{}() with argument[s] = {}",
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
				log.debug("Function " + joinPoint.getSignature().getName());
				String method = joinPoint.getSignature().getName();
				// get response
				CommonResponse res = (CommonResponse) result;
				ObjectMapper mapper = new ObjectMapper();

				if ("purchaseOVO".equals(method) || "purchaseGoPayResult".equals(method) || "purchase".equals(method)
						|| "purchaseResult".equals(method)) {
					// mapping request

					if (BkpmConstants.CODE_SOAP_SUCCESS.equalsIgnoreCase(res.getCode())) {
						Object[] rq = joinPoint.getArgs();

						Map<String, String> reqs = mapper.convertValue(rq[0], Map.class);
						Map<String, String> param = mapper.convertValue(reqs.get("parameter"), Map.class);
						Map<String, Object> resp = mapper.convertValue(res.getData(), Map.class);

						
						TransactionCommonRequest trxReq = new TransactionCommonRequest();
						//set admin fee and amount for transaction elastic
						if ("purchaseOVO".equals(method)) {
							trxReq.setAdminFee((BigDecimal) (resp.get("amountFee")));
							trxReq.setAmount((BigDecimal) (resp.get("amount")));	
							
						} else if ("purchaseGoPayResult".equals(method)) {
							trxReq.setAdminFee((BigDecimal) (resp.get("amountFee")));
							trxReq.setAmount((BigDecimal) (resp.get("amount")));	
							
						} else if ("purchase".equals(method)) {
							trxReq.setAdminFee((BigDecimal)(resp.get("amountFee")));
							trxReq.setAmount((BigDecimal) (resp.get("amount")));	
							
						}  else if ("purchaseResult".equals(method)) {
							trxReq.setAdminFee((BigDecimal)(resp.get("adminCharge")));
							trxReq.setAmount((BigDecimal)(resp.get("nominal")));	
						}
						
						
						
						trxReq.setReason("code : " + res.getCode() + " - message: " + res.getMessage());
						trxReq.setStatus(BkpmConstants.STATUS_SUCCESS);
						String reference = (String) resp.get("reference");
						trxReq.setReferenceNumber(reference);
						if(reference == null) {
							trxReq.setReferenceNumber((String)resp.get("referensi"));
						}
						trxReq.setDestinationId(String.valueOf(resp.get("destinationId")));

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
						// save report Failed
						Object[] rq = joinPoint.getArgs();

						Map<String, String> reqs = mapper.convertValue(rq[0], Map.class);
						Map<String, String> param = mapper.convertValue(reqs.get("parameter"), Map.class);
						Map<String, BigDecimal> amt = mapper.convertValue(reqs.get("parameter"), Map.class);

						TransactionCommonRequest transaction = new TransactionCommonRequest();

						transaction.setUsername(param.get("username"));
						transaction.setAccountNumber(param.get("accountNo"));

						transaction.setType(TransactionTypeEnum.EMONEY.name());
						transaction.setMenu(EMONEY_TRX_MENU);
						transaction.setBillerProduct(param.get("type"));

						if ("purchaseOVO".equals(method)) {
							// total amount
							BigDecimal bill = amt.get("amount");
							String adminfee = param.get("element61").substring(78, 89);

							BigDecimal total = bill.add(new BigDecimal(adminfee));
							transaction.setNoteId(NOTE_ID_EMONEY.concat(param.get("custNo")));
							transaction.setNoteEn(NOTE_EN_EMONEY.concat(param.get("custNo")));

							transaction.setTotalAmount(total);
							transaction.setAdminFee(new BigDecimal(adminfee));
							transaction.setAmount(bill);

						} else if ("purchaseGoPayResult".equals(method)) {
							// total amount
							BigDecimal bill = amt.get("amount");
							String adminfee = param.get("element61").substring(78, 89);

							BigDecimal total = bill.add(new BigDecimal(adminfee));
							transaction.setNoteId(NOTE_ID_EMONEY.concat(param.get("custNo")));
							transaction.setNoteEn(NOTE_EN_EMONEY.concat(param.get("custNo")));

							transaction.setTotalAmount(total);
							transaction.setAdminFee(new BigDecimal(adminfee));
							transaction.setAmount(bill);
							
						} else if ("purchase".equals(method)) {
							// total amount
							BigDecimal bill = amt.get("amount");

							String[] el48 = param.get("element48").split("\\|", -1);
							String adm = el48[6];
							//String adminFee = adm.substring(0, 7).concat("00");
							
							BigDecimal total = bill.add(new BigDecimal(adm));
							transaction.setNoteId(NOTE_ID_EMONEY.concat(param.get("custNo")));
							transaction.setNoteEn(NOTE_EN_EMONEY.concat(param.get("custNo")));

							transaction.setTotalAmount(total);
							transaction.setAdminFee(new BigDecimal(adm));
							transaction.setAmount(bill);

						} else if ("purchaseResult".equals(method)) {
							String subNumber = param.get("element48").substring(8, 18);
							if (param.get("flag").equals("1")) {
								subNumber = param.get("element48").substring(19, 30);
							}

							BigDecimal bill = new BigDecimal(param.get("nominal"));
							BigDecimal adminfee = new BigDecimal(param.get("adminCharge"));

							BigDecimal total = bill.add(adminfee);

							transaction.setTotalAmount(total);
							transaction.setAdminFee(adminfee);
							transaction.setAmount(bill);

							transaction.setType(TransactionTypeEnum.PLNPRE.name());
							transaction.setNoteId(NOTE_ID_PLNPRE.concat(subNumber));
							transaction.setNoteEn(NOTE_EN_PLNPRE.concat(subNumber));
							transaction.setBillerProduct(PLN_BILLER_PRODUCT);
							transaction.setMenu(PLN_PREPAID_TRX_MENU);

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

				log.debug("Purchase End AOP: {}.{}() with result = {}", joinPoint.getSignature().getDeclaringTypeName(),
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
