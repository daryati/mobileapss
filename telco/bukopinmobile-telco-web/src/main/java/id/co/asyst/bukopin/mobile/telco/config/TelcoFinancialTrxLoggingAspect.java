/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.telco.config;

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
import id.co.asyst.bukopin.mobile.service.core.NonFinancialService;
import id.co.asyst.bukopin.mobile.service.model.payload.UpdatePasswordCentagateRequest;
import id.co.asyst.bukopin.mobile.telco.model.payload.TelcoPostpaidPaymentResponse;
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
public class TelcoFinancialTrxLoggingAspect {

	private final Logger log = LoggerFactory.getLogger(this.getClass());



	private static final String NOTE_ID_TELCOPRE = "BELI PULSA ";
	private static final String NOTE_EN_TELCOPRE = "PURC PULSA ";
	private static final String NOTE_ID_TELCODATA= "BELI PAKET DATA ";
	private static final String NOTE_EN_TELCODATA = "PURC DATA ";
	private static final String NOTE_ID_TELCOPOST = "BAYAR TGHN ";
	private static final String NOTE_EN_TELCOPOST = "PAY TELCO ";
	private static final String TELCO_PREPAID_TRX_MENU = "Mobile & Data Purchase";
	private static final String TELCO_POSTPAID_TRX_MENU = "Mobile & Data Payment";


	private static final String TELKOM_PSTN_TRX_MENU = "Telephone & Cable TV Payment";


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
	@Pointcut("within(id.co.asyst.bukopin.mobile.telco.web.rest..Prepaid*)"
			+ " || within(id.co.asyst.bukopin.mobile.telco.web.rest..Telco*)")
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
		
		Object[] rq = joinPoint.getArgs();
		
		
		
		String method = joinPoint.getSignature().getName();
		
		ObjectMapper mapper = new ObjectMapper();

		Map<String, String> reqs = mapper.convertValue(rq[0], Map.class);
		Map<String, String> param = mapper.convertValue(reqs.get("parameter"), Map.class);

		DestinationCommonRequest dataReq = new DestinationCommonRequest();
		
		// dataReq.setSubscriberName();

		// dataReq.setReference(res.getReferenceNumber());

		// dataReq.setTotalAmount(res.getAmount());

		TransactionCommonRequest transaction = new TransactionCommonRequest();
		
		transaction.setUsername(param.get("username"));
		transaction.setType(TransactionTypeEnum.TELCOPOST.name());
		transaction.setAccountNumber(param.get("accountNo"));

		if ("purchaseTelkomPostpaid".equals(method)) {
			
			String bill = param.get("element61").substring(78, 89);
			String adminfee = param.get("element61").substring(160, 167);
			
			BigDecimal total = new BigDecimal(bill).add(new BigDecimal(adminfee));
			
			transaction.setTotalAmount(total);
			transaction.setNoteId(NOTE_ID_TELCOPOST.concat(param.get("custNo")));
			transaction.setNoteEn(NOTE_EN_TELCOPOST.concat(param.get("custNo")));
			transaction.setMenu(TELKOM_PSTN_TRX_MENU);
			transaction.setBillerProduct(param.get("type"));
			
			
			transaction.setAdminFee(new BigDecimal(adminfee));
			transaction.setAmount(new BigDecimal(bill));	
			

		} else if ("paymentPostpaidTelco".equals(method)) {
			String bill = param.get("element61").substring(115, 126);
			String adminfee = param.get("element61").substring(127, 134);
			
			BigDecimal total = new BigDecimal(bill).add(new BigDecimal(adminfee));
			
			transaction.setTotalAmount(total);
			
			transaction.setNoteId(NOTE_ID_TELCOPOST.concat(param.get("custNo")));
			transaction.setNoteEn(NOTE_EN_TELCOPOST.concat(param.get("custNo")));
			transaction.setBillerProduct(param.get("productName"));
			transaction.setMenu(TELCO_POSTPAID_TRX_MENU);
			
			transaction.setAdminFee(new BigDecimal(adminfee));
			transaction.setAmount(new BigDecimal(bill));

		} else if ("purchasePrepaidTelcoResult".equals(method)) {
			
			Map<String, BigDecimal> amt = mapper.convertValue(reqs.get("parameter"), Map.class);
			
			BigDecimal  bill = amt.get("amount");
			BigDecimal adminfee = amt.get("adminFee");
			
			BigDecimal total = bill.add(adminfee);
			
			transaction.setTotalAmount(total);

			transaction.setAccountNumber(param.get("accountNumber"));
			transaction.setType(TransactionTypeEnum.TELCOPRE.name());
			
			transaction.setNoteId(NOTE_ID_TELCOPRE.concat(param.get("phoneNumber")));
			transaction.setNoteEn(NOTE_EN_TELCOPRE.concat(param.get("phoneNumber")));
			
			if(param.get("institutionType").equalsIgnoreCase("PAKET DATA")) {
				transaction.setNoteId(NOTE_ID_TELCODATA.concat(param.get("phoneNumber")));
				transaction.setNoteEn(NOTE_EN_TELCODATA.concat(param.get("phoneNumber")));
				transaction.setType(TransactionTypeEnum.TELCODATA.name());
			}
			
			transaction.setBillerProduct(param.get("pGroup"));
			transaction.setMenu(TELCO_PREPAID_TRX_MENU);
			
			transaction.setAdminFee(adminfee);
			transaction.setAmount(bill);
		}

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
			log.debug("Telco Start AOP: {}.{}() with argument[s] = {}", joinPoint.getSignature().getDeclaringTypeName(),
					joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));

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

				if ("purchasePrepaidTelcoResult".equals(method) || "paymentPostpaidTelco".equals(method)
						|| "purchaseTelkomPostpaid".equals(method)) {

					if (BkpmConstants.CODE_SOAP_SUCCESS.equalsIgnoreCase(res.getCode())) {
						// save report SUCCESS
						Map<String, Object> resp = mapper.convertValue(res.getData(), Map.class);

						TransactionCommonRequest trxReq = new TransactionCommonRequest();
						
						//set admin fee and amount for transaction elastic
						if ("purchaseTelkomPostpaid".equals(method)) {
							trxReq.setAdminFee((BigDecimal)(resp.get("amountFee")));
							trxReq.setAmount((BigDecimal) (resp.get("amount")));	
							
						} else if ("paymentPostpaidTelco".equals(method)) {
							trxReq.setAdminFee((BigDecimal) (resp.get("amountFee")));
							trxReq.setAmount((BigDecimal) (resp.get("amount")));	
							
						} else if ("purchasePrepaidTelcoResult".equals(method)) {
							trxReq.setAdminFee((BigDecimal) (resp.get("adminFee")));
							trxReq.setAmount((BigDecimal) (resp.get("amount")));	
						}
						
						
						trxReq.setReason("code : " + res.getCode() + " - message: " + res.getMessage());
						trxReq.setStatus(BkpmConstants.STATUS_SUCCESS);
						String reference = (String) resp.get("referenceNumber");
						if(reference == null) {
							reference = (String) resp.get("referensi");
							
						}
						trxReq.setReferenceNumber(reference);
						String destId = String.valueOf(resp.get("idDestination"));
						
						if(destId == null || destId == "null" || destId.isEmpty()) {
							destId = String.valueOf(resp.get("destinationId"));
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
						Object[] rq = joinPoint.getArgs();

						Map<String, String> reqs = mapper.convertValue(rq[0], Map.class);
						Map<String, String> param = mapper.convertValue(reqs.get("parameter"), Map.class);

						DestinationCommonRequest dataReq = new DestinationCommonRequest();

						
						// dataReq.setSubscriberName();

						// dataReq.setReference(res.getReferenceNumber());

						// dataReq.setTotalAmount(res.getAmount());

						TransactionCommonRequest transaction = new TransactionCommonRequest();
						
						transaction.setUsername(param.get("username"));
						transaction.setType(TransactionTypeEnum.TELCOPOST.name());
						transaction.setAccountNumber(param.get("accountNo"));

						if ("purchaseTelkomPostpaid".equals(method)) {
							
							String bill = param.get("element61").substring(78, 89);
							String adminfee = param.get("element61").substring(160, 167);
							
							BigDecimal total = new BigDecimal(bill).add(new BigDecimal(adminfee));
							
							transaction.setTotalAmount(total);
							transaction.setNoteId(NOTE_ID_TELCOPOST.concat(param.get("custNo")));
							transaction.setNoteEn(NOTE_EN_TELCOPOST.concat(param.get("custNo")));
							transaction.setMenu(TELKOM_PSTN_TRX_MENU);
							transaction.setBillerProduct(param.get("type"));
							
							transaction.setAdminFee(new BigDecimal(adminfee));
							transaction.setAmount(new BigDecimal(bill));	

						} else if ("paymentPostpaidTelco".equals(method)) {
							String bill = param.get("element61").substring(115, 126);
							String adminfee = param.get("element61").substring(127, 134);
							
							BigDecimal total = new BigDecimal(bill).add(new BigDecimal(adminfee));
							
							transaction.setTotalAmount(total);
							
							transaction.setNoteId(NOTE_ID_TELCOPOST.concat(param.get("custNo")));
							transaction.setNoteEn(NOTE_EN_TELCOPOST.concat(param.get("custNo")));
							transaction.setBillerProduct(param.get("productName"));
							transaction.setMenu(TELCO_POSTPAID_TRX_MENU);
							
							transaction.setAdminFee(new BigDecimal(adminfee));
							transaction.setAmount(new BigDecimal(bill));	

						} else if ("purchasePrepaidTelcoResult".equals(method)) {
							
							Map<String, BigDecimal> amt = mapper.convertValue(reqs.get("parameter"), Map.class);
							
							BigDecimal  bill = amt.get("amount");
							BigDecimal adminfee = amt.get("adminFee");
							
							BigDecimal total = bill.add(adminfee);
							
							transaction.setTotalAmount(total);

							transaction.setAccountNumber(param.get("accountNumber"));
							transaction.setType(TransactionTypeEnum.TELCOPRE.name());
							
							transaction.setNoteId(NOTE_ID_TELCOPRE.concat(param.get("phoneNumber")));
							transaction.setNoteEn(NOTE_EN_TELCOPRE.concat(param.get("phoneNumber")));
							
							if(param.get("institutionType").equalsIgnoreCase("PAKET DATA")) {
								transaction.setNoteId(NOTE_ID_TELCODATA.concat(param.get("phoneNumber")));
								transaction.setNoteEn(NOTE_EN_TELCODATA.concat(param.get("phoneNumber")));
								transaction.setType(TransactionTypeEnum.TELCODATA.name());
							}
							
							transaction.setBillerProduct(param.get("pGroup"));
							transaction.setMenu(TELCO_PREPAID_TRX_MENU);
							
							transaction.setAdminFee(adminfee);
							transaction.setAmount(bill);	
						}

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

				log.debug("Telco End AOP: {}.{}() with result = {}", joinPoint.getSignature().getDeclaringTypeName(),
						joinPoint.getSignature().getName(), result);

			}
			return result;
		} catch (

		IllegalArgumentException e) {
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
