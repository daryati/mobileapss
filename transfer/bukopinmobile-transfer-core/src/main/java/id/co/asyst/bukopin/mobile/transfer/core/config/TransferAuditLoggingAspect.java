/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.transfer.core.config;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import javax.transaction.UserTransaction;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import id.co.asyst.bukopin.mobile.common.core.exception.MiddlewareException;
import id.co.asyst.bukopin.mobile.common.core.util.CryptoUtil;
import id.co.asyst.bukopin.mobile.common.model.BkpmConstants;
import id.co.asyst.bukopin.mobile.common.model.ResponseMessage;
import id.co.asyst.bukopin.mobile.common.model.payload.CommonRequest;
import id.co.asyst.bukopin.mobile.common.model.payload.CommonResponse;
import id.co.asyst.bukopin.mobile.common.model.payload.Identity;
import id.co.asyst.bukopin.mobile.service.core.NonFinancialService;
import id.co.asyst.bukopin.mobile.service.core.UserModuleService;
import id.co.asyst.bukopin.mobile.transfer.core.service.FundTransferService;
import id.co.asyst.bukopin.mobile.transfer.core.service.elastic.FundTransferElasticService;
import id.co.asyst.bukopin.mobile.transfer.model.entity.FundTransfer;
import id.co.asyst.bukopin.mobile.transfer.model.entity.elastic.FundTransferElastic;
import id.co.asyst.bukopin.mobile.user.model.entity.NonFinancial;
import id.co.asyst.bukopin.mobile.user.model.entity.User;
import id.co.asyst.foundation.service.connector.Services;

// import liquibase.integration.spring.SpringLiquibase;

/**
 * AUdit Logging Aspect
 * 
 * @author Kartika Dwi H
 * @version $Revision$, (Created on Jun 22, 2020)
 * @since 1.3.Alpha1
 */
@Aspect
@Component
public class TransferAuditLoggingAspect {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	private final static String NOTE_ID = "TF ke ";
	private final static String NOTE_EN = "TF to ";

	private final static String DOMESTIC_TRANSFER = "Domestic Transfer";
	private final static String INHOUSE_TRANSFER = "In-house transfer";

	@Autowired
	private FundTransferService fundTransferService;
	
	/**
	 * Elasticsearch Service for FT/ OB
	 */
	@Autowired
	private FundTransferElasticService elasticService;

	@Autowired
	private GetConfiguration config;

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
	@Pointcut("within(id.co.asyst.bukopin.mobile.transfer.web.rest..Transaction*)"
			+ " || within(id.co.asyst.bukopin.mobile.transfer.web.rest..Overbook*)")
	public void applicationPackagePointcut() {
		// Method is empty as this is just a Pointcut, the implementations are in the
		// advices.
	}

	/**
	 * Advice that logs methods throwing exceptions.
	 *
	 * @param joinPoint join point for advice
	 * @param e         exception
	 * @throws IOException 
	 */
	@AfterThrowing(pointcut = "applicationPackagePointcut() && springBeanPointcut()", throwing = "e")
	public void logAfterThrowing(JoinPoint joinPoint, Throwable e) throws IOException {
		log.error("Exception in {}.{}() with cause = {}", joinPoint.getSignature().getDeclaringTypeName(),
				joinPoint.getSignature().getName(), e.getCause() != null ? e.getCause() : "NULL");

		// save report Failed
		String code = "";
		String message = "";
		String msg = e.getMessage();

		if (e.getCause() instanceof MiddlewareException) {
			code = ResponseMessage.ERROR_EXTERNAL.getCode();
			message = "External error : " + msg;
		} else {

			code = ResponseMessage.INTERNAL_SERVER_ERROR.getCode();
			message = "Internal Service Error : " + msg;
		}

		String method = joinPoint.getSignature().getName();

		ObjectMapper mapper = new ObjectMapper();
		Object[] rq = joinPoint.getArgs();

		Map<String, String> reqs = mapper.convertValue(rq[0], Map.class);
		Map<String, String> param = mapper.convertValue(reqs.get("parameter"), Map.class);
		Map<String, BigDecimal> amt = mapper.convertValue(reqs.get("parameter"), Map.class);
		Map<String, String> from = mapper.convertValue(param.get("from"), Map.class);
		Map<String, String> to = mapper.convertValue(param.get("to"), Map.class);

		FundTransfer ft = new FundTransfer();

		String username = from.get("username");

		CommonResponse findUser = new CommonResponse();

		findUser = Services.create(UserModuleService.class).getUserByUsername(username).execute().body();

		ObjectMapper oMapper = new ObjectMapper();

		Map<String, Object> resp = oMapper.convertValue(findUser.getData(), Map.class);
		User userMap = oMapper.convertValue(resp.get("user"), User.class);

		User user = new User();
		user.setId(userMap.getId());

		String adminFee = "";

		adminFee = config.getConfigValue("OVERBOOK_ADMIN_FEE");
		BigDecimal amount = amt.get("amount");

		ft.setAccountNumber(from.get("accountNumber"));

		ft.setAdminFee(new BigDecimal(adminFee));
		ft.setAmount(amount);
		ft.setCreatedOn(LocalDateTime.now());
		ft.setMessage(param.get("message"));
		ft.setMethod(BkpmConstants.OVERBOOK);
		ft.setMenu(INHOUSE_TRANSFER);
		ft.setUsername(user);

		if (!to.get("bankCode").contentEquals(BkpmConstants.BUKOPIN_BANK_CODE)) {
			adminFee = config.getConfigValue("FUND_TRANSFER_ADMIN_FEE");

			ft.setMethod(BkpmConstants.FUND_TRANSFER);
			ft.setMenu(DOMESTIC_TRANSFER);
			ft.setAdminFee(new BigDecimal(adminFee));
		}

		String noteReceiver = to.get("accountName").concat(" " + to.get("accountNumber"));
		ft.setNoteId(NOTE_ID.concat(noteReceiver));
		ft.setNoteEn(NOTE_EN.concat(noteReceiver));

		ft.setStatus(BkpmConstants.STATUS_FAILED);
		ft.setReason("code: " + code + " - message: " + message);
		
		// save to elastic
		elasticService.saveTransaction(new FundTransferElastic(ft));

		fundTransferService.saveFundTransfer(ft);

	}

	/**
	 * Advice that logs when a method is entered and exited.
	 *
	 * @param joinPoint join point for advice
	 * @return result
	 * @throws Throwable throws IllegalArgumentException
	 */
	@SuppressWarnings({ "unchecked" })
	@Around("applicationPackagePointcut() && springBeanPointcut()")
	public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
		Object dataReq = new Object();
		if (log.isDebugEnabled()) {
			log.debug("Transfer Start AOP: {}.{}() with argument[s] = {}",
					joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(),
					Arrays.toString(joinPoint.getArgs()));

			String method = joinPoint.getSignature().getName();
			if ("getAllTransactionHistory".equals(method)) {
				Object[] results = joinPoint.getArgs();
				for (int i = 0; i < results.length; i++) {
					dataReq = (Object) results[i];

				}
			}
		}

		// proceed joincut
		try {
			Object result = joinPoint.proceed();
			if (log.isDebugEnabled()) {
				log.debug("Function " + joinPoint.getSignature().getName());
				String method = joinPoint.getSignature().getName();
				// get response
				CommonResponse res = (CommonResponse) result;

				if ("getAllTransactionHistory".equals(method)) {
					// mapping request
					String username = CryptoUtil.decryptAESHex((String) dataReq);

					CommonResponse user = Services.create(UserModuleService.class).getUserByUsername(username).execute()
							.body();
					ObjectMapper mapper = new ObjectMapper();
					Map<String, Object> resultUserObj = mapper.convertValue(user.getData(), Map.class);
					User userMap = mapper.convertValue(resultUserObj.get("user"), User.class);

					if (BkpmConstants.CODE_SOAP_SUCCESS.equalsIgnoreCase(res.getCode())) {
						// save report SUCCESS to Non_Financial table
						NonFinancial non = new NonFinancial();
						non.setMenu("History Transaction");
						non.setNote("Inquiry history transaction");
						non.setReason("");
						non.setStatus(BkpmConstants.STATUS_SUCCESS);
						non.setCreatedDate(new Date());
						non.setUser(userMap);
						CommonRequest<NonFinancial> nonFinancial = new CommonRequest<>();
						nonFinancial.setIdentity(generateIdentity());
						nonFinancial.setData(non);
						try {
							Services.create(NonFinancialService.class).saveReportNonFinancial(nonFinancial).execute()
									.body();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else {
						// save report Failed to Non_Financial table
						NonFinancial non = new NonFinancial();
						non.setMenu("History Transaction");
						non.setNote("Inquiry history transaction");
						non.setReason("code: " + res.getCode() + " - message: " + res.getMessage());
						non.setStatus(BkpmConstants.STATUS_FAILED);
						non.setCreatedDate(new Date());
						non.setUser(userMap);

						CommonRequest<NonFinancial> nonFinancial = new CommonRequest<>();
						nonFinancial.setIdentity(generateIdentity());
						nonFinancial.setData(non);
						try {
							Services.create(NonFinancialService.class).saveReportNonFinancial(nonFinancial).execute()
									.body();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

				} else if ("postTransaction".equalsIgnoreCase(method)) {
					if (!BkpmConstants.CODE_SOAP_SUCCESS.equalsIgnoreCase(res.getCode())) {
						Object[] rq = joinPoint.getArgs();

						ObjectMapper mapper = new ObjectMapper();

						Map<String, String> reqs = mapper.convertValue(rq[0], Map.class);
						Map<String, String> param = mapper.convertValue(reqs.get("parameter"), Map.class);
						Map<String, BigDecimal> amt = mapper.convertValue(reqs.get("parameter"), Map.class);
						Map<String, String> from = mapper.convertValue(param.get("from"), Map.class);
						Map<String, String> to = mapper.convertValue(param.get("to"), Map.class);

						FundTransfer ft = new FundTransfer();

						String username = from.get("username");

						CommonResponse findUser = new CommonResponse();

						findUser = Services.create(UserModuleService.class).getUserByUsername(username).execute()
								.body();

						ObjectMapper oMapper = new ObjectMapper();

						Map<String, Object> resp = oMapper.convertValue(findUser.getData(), Map.class);
						User userMap = oMapper.convertValue(resp.get("user"), User.class);

						User user = new User();
						user.setId(userMap.getId());

						String adminFee = "";

						adminFee = config.getConfigValue("OVERBOOK_ADMIN_FEE");
						BigDecimal amount = amt.get("amount");

						ft.setAccountNumber(from.get("accountNumber"));

						ft.setAdminFee(new BigDecimal(adminFee));
						ft.setAmount(amount);
						ft.setCreatedOn(LocalDateTime.now());
						ft.setMessage(param.get("message"));
						ft.setMethod(BkpmConstants.OVERBOOK);
						ft.setMenu(INHOUSE_TRANSFER);
						ft.setUsername(user);

						if (!to.get("bankCode").contentEquals(BkpmConstants.BUKOPIN_BANK_CODE)) {
							adminFee = config.getConfigValue("FUND_TRANSFER_ADMIN_FEE");

							ft.setMethod(BkpmConstants.FUND_TRANSFER);
							ft.setMenu(DOMESTIC_TRANSFER);
							ft.setAdminFee(new BigDecimal(adminFee));
						}

						String noteReceiver = to.get("accountName").concat(" " + to.get("accountNumber"));
						ft.setNoteId(NOTE_ID.concat(noteReceiver));
						ft.setNoteEn(NOTE_EN.concat(noteReceiver));

						ft.setStatus(BkpmConstants.STATUS_FAILED);
						ft.setReason("code: " + res.getCode() + " - message: " + res.getMessage());

						// save to elastic
						elasticService.saveTransaction(new FundTransferElastic(ft));
						
						fundTransferService.saveFundTransfer(ft);

					}
				}

				log.debug("Transfer End AOP: {}.{}() with result = {}", joinPoint.getSignature().getDeclaringTypeName(),
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
