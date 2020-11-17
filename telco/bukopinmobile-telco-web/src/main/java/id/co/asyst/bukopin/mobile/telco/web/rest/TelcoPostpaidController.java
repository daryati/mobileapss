/*
 * $Id$
 * 
 * Copyright (c) 2020 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.telco.web.rest;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import id.co.asyst.bukopin.mobile.common.core.exception.MiddlewareException;
import id.co.asyst.bukopin.mobile.common.core.util.BkpmUtil;
import id.co.asyst.bukopin.mobile.common.core.util.MessageUtil;
import id.co.asyst.bukopin.mobile.common.model.BkpmConstants;
import id.co.asyst.bukopin.mobile.common.model.ResponseMessage;
import id.co.asyst.bukopin.mobile.common.model.payload.CommonRequest;
import id.co.asyst.bukopin.mobile.common.model.payload.CommonResponse;
import id.co.asyst.bukopin.mobile.master.model.entity.Configuration;
import id.co.asyst.bukopin.mobile.master.model.entity.Transaction;
import id.co.asyst.bukopin.mobile.master.model.payload.DestinationCommonRequest;
import id.co.asyst.bukopin.mobile.service.core.MasterModuleService;
import id.co.asyst.bukopin.mobile.service.core.TelcoModuleService;
import id.co.asyst.bukopin.mobile.service.core.TransferModuleService;
import id.co.asyst.bukopin.mobile.service.core.UserModuleService;
import id.co.asyst.bukopin.mobile.service.model.payload.pln.GetVerifyPINRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.telco.postpaid.TelcoPostpaidInquiryTibcoRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.telco.postpaid.TelcoPostpaidInquiryTibcoResponse;
import id.co.asyst.bukopin.mobile.service.model.payload.telco.postpaid.TelcoPostpaidPaymentTibcoRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.telco.postpaid.TelcoPostpaidPaymentTibcoResponse;
import id.co.asyst.bukopin.mobile.service.model.payload.telco.telkomPSTN.speedy.TelkomPSTNSpeedyInquiryRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.telco.telkomPSTN.speedy.TelkomPSTNSpeedyInquiryTibcoResponse;
import id.co.asyst.bukopin.mobile.service.model.payload.telco.telkomPSTN.speedy.TelkomPSTNSpeedyPurchaseTibcoRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.telco.telkomPSTN.speedy.TelkomPSTNSpeedyPurchaseTibcoResponse;
import id.co.asyst.bukopin.mobile.telco.core.service.TelcoPostpaidService;
import id.co.asyst.bukopin.mobile.telco.core.service.TelcoService;
import id.co.asyst.bukopin.mobile.telco.core.util.TelcoUtils;
import id.co.asyst.bukopin.mobile.telco.model.entity.TelcoPostpaid;
import id.co.asyst.bukopin.mobile.telco.model.payload.InstitutionMapper;
import id.co.asyst.bukopin.mobile.telco.model.payload.TelcoPostpaidInquiryRequest;
import id.co.asyst.bukopin.mobile.telco.model.payload.TelcoPostpaidInquiryResponse;
import id.co.asyst.bukopin.mobile.telco.model.payload.TelcoPostpaidPaymentRequest;
import id.co.asyst.bukopin.mobile.telco.model.payload.TelcoPostpaidPaymentResponse;
import id.co.asyst.bukopin.mobile.telco.model.payload.TelcoPostpaidPrefixResponse;
import id.co.asyst.bukopin.mobile.telco.model.payload.TelkomPSTNSpeedyInquiryReq;
import id.co.asyst.bukopin.mobile.telco.model.payload.TelkomPSTNSpeedyInquiryRes;
import id.co.asyst.bukopin.mobile.transfer.model.limitUserDailyClass;
import id.co.asyst.bukopin.mobile.user.model.entity.User;
import id.co.asyst.bukopin.mobile.user.model.payload.VerifyAccountOwnerRequest;
import id.co.asyst.bukopin.mobile.user.model.payload.VerifyAccountOwnerResponse;
import id.co.asyst.bukopin.mobile.user.model.payload.VerifyPhoneOwnerRequest;
import id.co.asyst.foundation.service.connector.Services;

/**
 * 
 * 
 * @author Gibran Haq
 * @version $Revision$, Feb 13, 2020
 * @since 1.1.Alpha1-SNAPSHOT
 */
@RestController
@RequestMapping("/Telco/postpaid")
public class TelcoPostpaidController {
	/* Constants: */
	private final Logger log = LoggerFactory
			.getLogger(TelcoPostpaidController.class);

	private static final String SUCCESS_CODE = "000";
	private static final String ERROR_CODE_BILL_NOT_AVAILABLE = "187";
	private static final String ERROR_CODE_BILL_ALREADY_PAID = "188";
	private static final String ERROR_CODE_PHONE_NUMBER_EXPIRED = "181";
	private static final String ERROR_CODE_SYSTEM_FAILURE = "191";
	private static final String ERROR_CODE_PREFIX_UNKNOWN = "192";
	private static final String ERROR_CODE_SYSTEM_FAILURE_2 = "196";
	private static final String ERROR_CODE_CUTT_OFF = "190";
	private static final String ERROR_CODE_PHONE_NUMBER_NOT_FOUND = "214";
	private static final String ERROR_CODE_BLOCKED_ACCOUNT = "183";
	private static final String ERROR_CODE_INVALID_AMOUNT = "113";
	private static final String ERROR_CODE_SPECIAL_NUMBER = "179";
	private static final String ERROR_CODE_INVALID_PHONE_NUMBER = "185";
	private static final String ERROR_CODE_CUSTOMER_NAME = "176";
	private static final String ERROR_CODE_EXCEED_BILL_LIMIT = "177";
	private static final String ERROR_CODE_DIFFERENT_BILL = "178";
	private static final String ERROR_CODE_NUMBER_NOT_REGISTERED = "114";
	private static final String IRREGULAR_AREA_CODE = "IRREGULAR_AREA_CODE";
	private static final String ERROR_NOT_ENOUGH_BALANCE = "851";
	private static final String ERROR_ACCOUNT_INACTIVE = "839";
	private static final String ISFALSE = "FALSE";

	// giro error handle
	private static final String GIRO_AMOUNT_NOT_ENOUGH_BALANCE = "805";
	private static final String GIRO_LIMIT_TRANSFER = "802";
	private static final String GIRO_ACCOUNT_WAS_BLOCKED = "806";
	private static final String GIRO_OVER_LIMIT = "808";
	private static final String GIRO_ACCOUNT_BLOCKED = "814";
	private static final String GIRO_CUT_OFF = "818";
	private static final String GIRO_INACTIVE_ACCOUNT = "822";
	private static final String GIRO_USER_NOT_FOUND = "831";
	private static final String GIRO_DUPLICATE_DATA = "869";
	private static final String GIRO_CLOSED_ACCOUNT = "878";
	private static final String GIRO_ERROR_VALUTA_CODE = "885";
	private static final String GIRO_LIMITED_BALANCE = "897";

	/* Attributes: */
	@Autowired
	private Environment env;

	@Autowired
	private MessageUtil messageUtil;

	@Autowired
	private HttpServletRequest servletRequest;

	@Autowired
	private TelcoPostpaidService telcoPostpaidService;

	@Autowired
	TelcoService telcoService;

	/* Transient Attributes: */

	/* Constructors: */

	/* Getters & setters for attributes: */

	/* Getters & setters for transient attributes: */

	/* Functionalities: */
	/**
	 * Inquiry Postpaid Telco
	 * 
	 * @param req
	 *            Telco Postpaid Inquiry Request
	 * @return CommonResponse with Telco Postpaid Inquiry Response in body, else
	 *         error
	 * @throws IOException
	 */
	@PostMapping("/inquiry")
	public CommonResponse inquiryPostpaidTelco(
			@RequestBody CommonRequest<TelcoPostpaidInquiryRequest> req)
			throws IOException {
		log.debug("REST request to inquiry " + req.getData().getType()
				+ " Postpaid : {}", req.getData());
		CommonResponse response = new CommonResponse();

		String custNo = req.getData().getCustNo();
		String type = req.getData().getType();
		String codeArra = req.getData().getCodeArra();
		String codeCbs = req.getData().getCodeCbs();

		// Check Cut Off
		long cutoffId = TelcoUtils.getCutOffId(req.getData().getProductName());
		CommonResponse cutOffResponse = Services
				.create(MasterModuleService.class)
				.checkCutOffStatus(servletRequest.getLocale().getLanguage(),
						cutoffId).execute().body();
		if (!ResponseMessage.SUCCESS.getCode().equals(cutOffResponse.getCode())) {
			log.error("Error Cutoff");
			return cutOffResponse;
		}

		String forwardInsCode = env
				.getProperty("config.forwarding-institution-code");
		TelcoPostpaidInquiryTibcoRequest inquiryTibcoReq = TelcoUtils
				.generateTelcoPostpaidInquiryRequest(custNo, forwardInsCode,
						codeArra, codeCbs);
		log.debug("Request inquiry postpaid " + type + " to tibco : "
				+ BkpmUtil.convertToJson(inquiryTibcoReq));

		TelcoPostpaidInquiryTibcoResponse inquiryTibcoRes = Services
				.create(TelcoModuleService.class)
				.inquiryTelcoPostpaid(inquiryTibcoReq).execute().body();
		String codeRes = inquiryTibcoRes.getRespayment().getResult()
				.getElement39();
		log.debug("Response inquiry postpaid " + type + " to tibco : "
				+ BkpmUtil.convertToJson(inquiryTibcoRes));

		if (SUCCESS_CODE.equals(codeRes)) {
			log.debug("Inquiry postpaid " + type + " Success");

			// set response
			TelcoPostpaidInquiryResponse resp = new TelcoPostpaidInquiryResponse();
			resp = TelcoUtils.generateTelcoPostpaidInquiryResp(inquiryTibcoRes
					.getRespayment().getResult());
			resp.setType(type);
			resp.setProductName(req.getData().getProductName().toUpperCase());

			response.setCode(ResponseMessage.SUCCESS.getCode());
			response.setMessage(messageUtil.get("success",
					servletRequest.getLocale()));
			response.setData(resp);
		} else if (ERROR_CODE_PHONE_NUMBER_EXPIRED.equals(codeRes)) {
			log.error("phone number expired");
			response.setCode(ResponseMessage.PHONE_NUMBER_EXPIRED.getCode());
			response.setMessage(messageUtil.get("error.phone.number.expired",
					servletRequest.getLocale()));
		} else if (ERROR_CODE_BILL_ALREADY_PAID.equals(codeRes)
				|| ERROR_CODE_BILL_NOT_AVAILABLE.equals(codeRes)) {
			log.error("bill already paid");
			response.setCode(ResponseMessage.ERROR_BILL_ALREADY_PAID.getCode());
			response.setMessage(messageUtil.get("error.bill.already.paid",
					servletRequest.getLocale()));
		} else if (GIRO_LIMIT_TRANSFER.equals(codeRes)
				|| GIRO_OVER_LIMIT.equals(codeRes)) {
			log.error("exceed limit");
			response = new CommonResponse();
			response.setCode(ResponseMessage.LIMIT_TRANSFER_DAY.getCode());
			response.setMessage(messageUtil.get("error.exceed.limit",
					servletRequest.getLocale()));
		} else if (ERROR_CODE_CUTT_OFF.equals(codeRes)
				|| GIRO_CUT_OFF.equals(codeRes)) {
			log.error("cutoff in progress");
			response.setCode(ResponseMessage.ERROR_CUT_OFF_PLN.getCode());
			response.setMessage(messageUtil.get("error.cutoff",
					servletRequest.getLocale()));
		} else if (GIRO_DUPLICATE_DATA.equals(codeRes)) {
			log.error("Giro Duplicate Data");
			response = new CommonResponse();
			response.setCode(ResponseMessage.DUPLICATE_DATA.getCode());
			response.setMessage(messageUtil.get("error.duplicate.data",
					servletRequest.getLocale()));
		} else if (ERROR_CODE_SYSTEM_FAILURE.equals(codeRes)
				|| ERROR_CODE_SYSTEM_FAILURE_2.equals(codeRes)) {
			log.error("system failure");
			response.setCode(ResponseMessage.ERROR_SYSTEM_FAILURE.getCode());
			response.setMessage(messageUtil.get("error.system.failure",
					servletRequest.getLocale()));
		} else if (ERROR_CODE_PHONE_NUMBER_NOT_FOUND.equals(codeRes)
				|| ERROR_CODE_SPECIAL_NUMBER.equals(codeRes)
				|| ERROR_CODE_INVALID_PHONE_NUMBER.equals(codeRes)
				|| ERROR_CODE_NUMBER_NOT_REGISTERED.equals(codeRes)) {
			log.error("data not found with code : " + codeRes);
			response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
			response.setMessage(messageUtil.get("error.number.not.found",
					servletRequest.getLocale()));
		} else if (ERROR_CODE_INVALID_AMOUNT.equals(codeRes)) {
			log.error("invalid amount");
			response.setCode(ResponseMessage.INVALID_AMOUNT.getCode());
			response.setMessage(messageUtil.get("error.invalid.amount",
					servletRequest.getLocale()));
		} else if (ERROR_CODE_BLOCKED_ACCOUNT.equals(codeRes)
				|| GIRO_ACCOUNT_WAS_BLOCKED.equals(codeRes)
				|| GIRO_ACCOUNT_BLOCKED.equals(codeRes)) {
			log.error("blocked account");
			response.setCode(ResponseMessage.CUST_BLOCKED.getCode());
			response.setMessage(messageUtil.get("error.account.was.blocked",
					servletRequest.getLocale()));
		} else if (ERROR_CODE_CUSTOMER_NAME.equals(codeRes)) {
			log.error("customer name are not same in two bill at the same phone number");
			response.setCode(ResponseMessage.ERROR_CUSTOMER_NAME_NOT_SAME
					.getCode());
			response.setMessage(messageUtil.get("error.customer.not.same",
					servletRequest.getLocale()));
		} else if (ERROR_CODE_EXCEED_BILL_LIMIT.equals(codeRes)) {
			log.error("phone number exceed bill limit");
			response.setCode(ResponseMessage.ERROR_EXCEED_BILL_LIMIT.getCode());
			response.setMessage(messageUtil.get("error.exceed.bill.limit",
					servletRequest.getLocale()));
		} else if (ERROR_CODE_DIFFERENT_BILL.equals(codeRes)) {
			log.error("different bill at same month");
			response.setCode(ResponseMessage.ERROR_DIFFERENT_BILL.getCode());
			response.setMessage(messageUtil.get("error.different.bill",
					servletRequest.getLocale()));
		} else {
			log.error("error from aranet with code : " + codeRes);
			throw new MiddlewareException(codeRes);
		}

		return response;
	}

	/**
	 * Payment Postpaid Telco
	 * 
	 * @param req
	 *            Telco Postpaid Payment Request
	 * @return CommonResponse with Telco Postpaid Payment Response in body, else
	 *         Error.
	 * @throws IOException
	 */
	@PostMapping("/payment")
	public CommonResponse paymentPostpaidTelco(
			@RequestBody CommonRequest<TelcoPostpaidPaymentRequest> req)
			throws IOException {
		log.debug("REST request to payment Postpaid : {}", req.getData());
		CommonResponse response = new CommonResponse();

		String type = req.getData().getType();
		String codeArra = req.getData().getCodeArra();
		String codeCbs = req.getData().getCodeCbs();
		String group = req.getData().getProductName();

		// Check Cut Off
		long cutoffId = TelcoUtils.getCutOffId(req.getData().getProductName());
		CommonResponse cutOffResponse = Services
				.create(MasterModuleService.class)
				.checkCutOffStatus(servletRequest.getLocale().getLanguage(),
						cutoffId).execute().body();
		if (!ResponseMessage.SUCCESS.getCode().equals(cutOffResponse.getCode())) {
			log.error("Error Cutoff");
			return cutOffResponse;
		}

		// verify PIN
		GetVerifyPINRequest verifyPINReqData = new GetVerifyPINRequest();
		verifyPINReqData.setUsername(req.getData().getUsername());
		verifyPINReqData.setPin(req.getData().getPin());

		CommonRequest<GetVerifyPINRequest> verifyPINReq = new CommonRequest<>();
		verifyPINReq.setIdentity(req.getIdentity());
		verifyPINReq.setData(verifyPINReqData);

		CommonResponse verifyPINRes = Services
				.create(UserModuleService.class)
				.verifyPIN(
						servletRequest.getHeader(HttpHeaders.ACCEPT_LANGUAGE),
						verifyPINReq).execute().body();
		if (!ResponseMessage.SUCCESS.getCode().equals(verifyPINRes.getCode())) {
			log.error("Error while verify PIN");
			return verifyPINRes;
		}

		// validate account number's owner user
		VerifyAccountOwnerRequest verifyAccountOwnerReqData = new VerifyAccountOwnerRequest();
		verifyAccountOwnerReqData.setAccountNo(req.getData().getAccountNo());
		verifyAccountOwnerReqData.setUsername(req.getData().getUsername());

		CommonRequest<VerifyAccountOwnerRequest> verifyAccOwnerRequest = new CommonRequest<>();
		verifyAccOwnerRequest.setIdentity(req.getIdentity());
		verifyAccOwnerRequest.setData(verifyAccountOwnerReqData);

		CommonResponse verifyAccOwnerResponse = Services
				.create(UserModuleService.class)
				.verifyAccountOwner(verifyAccOwnerRequest).execute().body();
		if (!ResponseMessage.SUCCESS.getCode().equals(
				verifyAccOwnerResponse.getCode())) {
			log.error("Error while verify account owner");
			return verifyAccOwnerResponse;
		}
		ObjectMapper oMapper = new ObjectMapper();
		VerifyAccountOwnerResponse verifyAccOwnRespObj = oMapper.convertValue(
				verifyAccOwnerResponse.getData(),
				VerifyAccountOwnerResponse.class);
		if (!verifyAccOwnRespObj.isValid()) {
			log.error("User and Account Info didn't match: "
					+ req.getData().getAccountNo());
			response.setCode(ResponseMessage.DATA_NOT_MATCH.getCode());
			response.setMessage(messageUtil.get("error.invalid.user.accountno",
					servletRequest.getLocale()));
			return response;
		}
		User user = verifyAccOwnRespObj.getUser();
		String accType = verifyAccOwnRespObj.getAccountInfo().getAccountType()
				.name();

		// cek limit harian
		String amount = req.getData().getElement61().substring(114, 126);
		CommonRequest<limitUserDailyClass> lmtDl = new CommonRequest<>();
		limitUserDailyClass lmtDLClass = new limitUserDailyClass();
		lmtDLClass.setAccNo(req.getData().getAccountNo());
		lmtDLClass.setUsername(req.getData().getUsername());
		lmtDLClass.setAmount(new BigDecimal(amount + ""));
		lmtDLClass.setJenis("payment");
		lmtDl.setData(lmtDLClass);
		lmtDl.setIdentity(req.getIdentity());
		CommonResponse resLmtDL = Services.create(TransferModuleService.class)
				.verifydailylimit(lmtDl).execute().body();
		log.debug("log dari limit harian telco postpaid " + lmtDl
				+ " response " + resLmtDL);
		if (resLmtDL.getCode().equals("000")) {
			String forwardInsCode = env
					.getProperty("config.forwarding-institution-code");
			TelcoPostpaidPaymentTibcoRequest reqPaymentTibco = TelcoUtils
					.generateTelcoPostpaidPaymentReq(req.getData(),
							forwardInsCode, group, codeArra, codeCbs, accType);
			log.debug("Request payment postpaid " + type + " to tibco : "
					+ BkpmUtil.convertToJson(reqPaymentTibco));

			TelcoPostpaidPaymentTibcoResponse resPaymentTibco = Services
					.create(TelcoModuleService.class)
					.paymentTelcoPostpaid(reqPaymentTibco).execute().body();
			String codeRes = resPaymentTibco.getRespayment().getResult()
					.getElement39();
			log.debug("Response payment postpaid " + type + " to tibco : "
					+ BkpmUtil.convertToJson(resPaymentTibco));

			if (SUCCESS_CODE.equals(codeRes)) {
				// set response
				TelcoPostpaidPaymentResponse resp = new TelcoPostpaidPaymentResponse();
				resp = TelcoUtils
						.generateTelcoPostpaidPaymentRes(resPaymentTibco
								.getRespayment().getResult());
				resp.setProductName(req.getData().getProductName()
						.toUpperCase());

				// save to db (Destination and transaction)
				log.debug("saving to database destination and transaction");
				CommonRequest<DestinationCommonRequest> destReq = TelcoUtils
						.generateDestinationReq(req.getIdentity(),
								req.getData(), resp);
				CommonResponse saveFavRes = Services
						.create(MasterModuleService.class)
						.saveToFavouriteCommon(destReq).execute().body();
				if (SUCCESS_CODE.equals(saveFavRes.getCode())) {
					log.debug("saving to database telco postpaid");
					// save to db (Telco Postpaid)
					ObjectMapper objMapper = new ObjectMapper();
					Transaction transaction = objMapper.convertValue(
							saveFavRes.getData(), Transaction.class);
					TelcoPostpaid telco = TelcoUtils.generateDataTelcoPostpaid(
							resp, transaction, destReq.getData());
					telcoPostpaidService.saveTelcoPostpaid(telco);
					log.debug("data has been saved successfully");

					// sendEmail
					log.debug("Send email receipt telco postpaid");
					String category = transaction.getDestination()
							.getCategory().getName();
					telcoService.sendEmailReceiptPostpaidTelco(resp, user,
							category, servletRequest.getLocale());
					// set destination id to response
					resp.setDestinationId(transaction.getDestination().getId());

					response.setCode(ResponseMessage.SUCCESS.getCode());
					response.setMessage(messageUtil.get("success",
							servletRequest.getLocale()));
					response.setData(resp);
					log.debug("Payment postpaid " + type + " Success");
					// save limit harian
					log.debug("param save limit " + resLmtDL.getData());
					CommonResponse prosesLimit = Services
							.create(TransferModuleService.class)
							.prosesdailyLimit(resLmtDL.getData()).execute()
							.body();
					log.debug("log dari proses simpan limit telcopostpaid "
							+ prosesLimit);
				} else {
					log.error("Save to favourite Failed");
					response.setCode(ResponseMessage.INTERNAL_SERVER_ERROR
							.getCode());
					response.setMessage(messageUtil.get(
							"error.internal.server", servletRequest.getLocale()));
				}
			} else if (ERROR_CODE_PHONE_NUMBER_EXPIRED.equals(codeRes)) {
				log.error("phone number expired");
				response.setCode(ResponseMessage.PHONE_NUMBER_EXPIRED.getCode());
				response.setMessage(messageUtil.get(
						"error.phone.number.expired",
						servletRequest.getLocale()));
			} else if (ERROR_NOT_ENOUGH_BALANCE.equals(codeRes)
					|| GIRO_AMOUNT_NOT_ENOUGH_BALANCE.equals(codeRes)
					|| GIRO_ERROR_VALUTA_CODE.equals(codeRes)
					|| GIRO_LIMITED_BALANCE.equals(codeRes)) {
				log.error("Not enough balance");
				response.setCode(ResponseMessage.AMOUNT_NOT_ENOUGH.getCode());
				response.setMessage(messageUtil.get("error.amount.not.enough",
						servletRequest.getLocale()));
				return response;
			} else if (ERROR_ACCOUNT_INACTIVE.equals(codeRes)
					|| GIRO_INACTIVE_ACCOUNT.equalsIgnoreCase(codeRes)
					|| GIRO_CLOSED_ACCOUNT.equalsIgnoreCase(codeRes)) {
				log.error("account inactive");
				response.setCode(ResponseMessage.ERROR_INACTIVE_BANK_ACCOUNT
						.getCode());
				response.setMessage(messageUtil.get(
						"error.inactive.bank.account",
						servletRequest.getLocale()));
			} else if (ERROR_CODE_BILL_ALREADY_PAID.equals(codeRes)
					|| ERROR_CODE_BILL_NOT_AVAILABLE.equals(codeRes)) {
				log.error("bill already paid");
				response.setCode(ResponseMessage.ERROR_BILL_ALREADY_PAID
						.getCode());
				response.setMessage(messageUtil.get("error.bill.already.paid",
						servletRequest.getLocale()));
			} else if (ERROR_CODE_CUTT_OFF.equals(codeRes)) {
				log.error("cutoff in progress");
				response.setCode(ResponseMessage.ERROR_CUT_OFF_PLN.getCode());
				response.setMessage(messageUtil.get("error.cutoff",
						servletRequest.getLocale()));
			} else if (ERROR_CODE_SYSTEM_FAILURE.equals(codeRes)
					|| ERROR_CODE_SYSTEM_FAILURE_2.equals(codeRes)) {
				log.error("system failure");
				response.setCode(ResponseMessage.ERROR_SYSTEM_FAILURE.getCode());
				response.setMessage(messageUtil.get("error.system.failure",
						servletRequest.getLocale()));
			} else if (ERROR_CODE_PHONE_NUMBER_NOT_FOUND.equals(codeRes)
					|| ERROR_CODE_SPECIAL_NUMBER.equals(codeRes)
					|| ERROR_CODE_INVALID_PHONE_NUMBER.equals(codeRes)
					|| ERROR_CODE_NUMBER_NOT_REGISTERED.equals(codeRes)
					|| GIRO_USER_NOT_FOUND.equalsIgnoreCase(codeRes)) {
				log.error("data not found with code : " + codeRes);
				response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
				response.setMessage(messageUtil.get("error.data.not.found",
						servletRequest.getLocale()));
			} else if (ERROR_CODE_INVALID_AMOUNT.equals(codeRes)) {
				log.error("invalid amount");
				response.setCode(ResponseMessage.INVALID_AMOUNT.getCode());
				response.setMessage(messageUtil.get("error.invalid.amount",
						servletRequest.getLocale()));
			} else if (ERROR_CODE_BLOCKED_ACCOUNT.equals(codeRes)) {
				log.error("blocked account");
				response.setCode(ResponseMessage.CUST_BLOCKED.getCode());
				response.setMessage(messageUtil.get(
						"error.account.was.blocked", servletRequest.getLocale()));
			} else if (ERROR_CODE_CUSTOMER_NAME.equals(codeRes)) {
				log.error("customer name are not same in two bill at the same phone number");
				response.setCode(ResponseMessage.ERROR_CUSTOMER_NAME_NOT_SAME
						.getCode());
				response.setMessage(messageUtil.get("error.customer.not.same",
						servletRequest.getLocale()));
			} else if (ERROR_CODE_EXCEED_BILL_LIMIT.equals(codeRes)) {
				log.error("phone number exceed bill limit");
				response.setCode(ResponseMessage.ERROR_EXCEED_BILL_LIMIT
						.getCode());
				response.setMessage(messageUtil.get("error.exceed.bill.limit",
						servletRequest.getLocale()));
			} else if (ERROR_CODE_DIFFERENT_BILL.equals(codeRes)) {
				log.error("different bill at same month");
				response.setCode(ResponseMessage.ERROR_DIFFERENT_BILL.getCode());
				response.setMessage(messageUtil.get("error.different.bill",
						servletRequest.getLocale()));
			} else {
				log.error("error aranet with code : " + codeRes);
				throw new MiddlewareException(codeRes);
			}

		} else {
			if (resLmtDL.getMessage().equals("Limit user not set")) {
				response.setCode(resLmtDL.getCode());
				response.setMessage(messageUtil.get("error.limit.unset",
						servletRequest.getLocale()));

			} else if (resLmtDL.getMessage().equals(
					"amount more than daily limit user")) {
				response.setCode(resLmtDL.getCode());
				response.setMessage(messageUtil.get("error.limit.exceed",
						servletRequest.getLocale()));
			} else if (resLmtDL.getMessage().equals(
					"transactions exceed daily limit")) {
				response.setCode(resLmtDL.getCode());
				response.setMessage(messageUtil.get("error.limit.exceed",
						servletRequest.getLocale()));
			} else {
				response.setCode(resLmtDL.getCode());
				response.setMessage(resLmtDL.getMessage());
			}

		}

		return response;
	}

	/**
	 * Get Prefix Postpaid
	 * 
	 * @param req
	 *            customer number
	 * @return CommonResponse with Telco Postpaid Prefix Response in body, else
	 *         error
	 */
	@SuppressWarnings("unchecked")
	@PostMapping("/prefix")
	public CommonResponse getPrefixPostpaid(
			@RequestBody CommonRequest<Map<String, String>> req) {
		log.debug("REST request to get prefix Postpaid : {}", req.getData());
		CommonResponse response = new CommonResponse();

		try {
			CommonResponse prefixRes = Services
					.create(MasterModuleService.class)
					.findOneByPrefixNo(
							servletRequest.getLocale().getLanguage(),
							req.getData().get("custNo")).execute().body();
			if (!ResponseMessage.SUCCESS.getCode().equals(prefixRes.getCode())) {
				// response not success
				return prefixRes;
			}
			ObjectMapper mapper = new ObjectMapper();
			List<String> prefixResp = mapper.convertValue(prefixRes.getData(),
					ArrayList.class);

			String id = String.valueOf(prefixResp.get(3));

			TelcoPostpaidPrefixResponse resp = new TelcoPostpaidPrefixResponse();
			resp.setProvider(prefixResp.get(0));
			resp.setGroup(prefixResp.get(1));
			resp.setPicture(prefixResp.get(2) != null ? prefixResp.get(2)
					.getBytes() : null);
			resp.setId(Long.valueOf(id));
			resp.setCodeArra(prefixResp.get(4));
			resp.setCodeCbs(prefixResp.get(5));

			response.setCode(ResponseMessage.SUCCESS.getCode());
			response.setMessage(messageUtil.get("success",
					servletRequest.getLocale()));
			response.setData(resp);
			log.debug("get prefix postpaid success");

		} catch (IOException e) {
			log.error(e.getMessage(), e.getLocalizedMessage());
		}

		return response;
	}

	/**
	 * inquiry Postpaid Telco Telkom PSTN and SPEEDY
	 * 
	 * @param req
	 *            Telco Postpaid Payment Request please fill "type" with
	 *            TELKOM_PSTN or SPEEDY
	 * @return CommonResponse with Telco Postpaid Payment Response in body, else
	 *         Error.
	 * @throws IOException
	 */
	@PostMapping("/TelkomPostpaid/inquiry")
	public CommonResponse inquiryTelkomPostpaid(
			@Valid @RequestBody CommonRequest<TelkomPSTNSpeedyInquiryReq> req)
			throws IOException {
		log.debug("REST request to inquiry Telkom PSTN/SPEEDY Postpaid : {}"
				+ BkpmUtil.convertToJson(req.getData()));
		CommonResponse response = new CommonResponse();

		String type = req.getData().getType();

		String custNo = req.getData().getCustNo();

		// Check Cut Off
		long cutoffId = TelcoUtils.getCutOffId(req.getData().getType());
		CommonResponse cutOffResponse = Services
				.create(MasterModuleService.class)
				.checkCutOffStatus(servletRequest.getLocale().getLanguage(),
						cutoffId).execute().body();
		if (!ResponseMessage.SUCCESS.getCode().equals(cutOffResponse.getCode())) {
			log.error("Error Cutoff");
			return cutOffResponse;
		}

		CommonResponse areaCode = Services.create(MasterModuleService.class)
				.findConfigByName(IRREGULAR_AREA_CODE).execute().body();
		if (!ResponseMessage.SUCCESS.getCode().equals(areaCode.getCode())) {
			return areaCode;
		}
		ObjectMapper oMapper = new ObjectMapper();
		Configuration configurationValue = oMapper.convertValue(
				areaCode.getData(), Configuration.class);
		String valueAreaCode = configurationValue.getValue();
		String[] configValues = valueAreaCode.split("\\|", -1);

		String custNoCode = "0" + custNo.substring(0, 3);

		// validate if the area code is irrgular area code (3 number)
		for (String cstNoCode : configValues) {
			if (custNoCode.equals(cstNoCode)) {
				custNo = "0" + custNo;
			}
		}

		String forwardInsCode = env
				.getProperty("config.forwarding-institution-code");
		// Institution findInstitution =
		// institutionService.findCodeByProvider(type);

		CommonResponse institutionRes = Services
				.create(MasterModuleService.class)
				.findCodeByProvider(servletRequest.getLocale().getLanguage(),
						type).execute().body();
		if (!ResponseMessage.SUCCESS.getCode().equals(institutionRes.getCode())) {
			// response not success
			return institutionRes;
		}

		ObjectMapper mapper = new ObjectMapper();
		InstitutionMapper findInstitution = mapper.convertValue(
				institutionRes.getData(), InstitutionMapper.class);
		// PrefixTelcoMapper prefixTelco =
		// findInstitution.getPrefixTelcoMapper();

		String codeArra = findInstitution.getCodeArra();
		String codeCbs = findInstitution.getCodeCbs();
		String product = findInstitution.getPrefixTelcoMapper().getPgroup();

		TelkomPSTNSpeedyInquiryRequest inquiryRequest = TelcoUtils
				.generatePSTNSpeedyTIbcoReq(req.getData(), custNo,
						forwardInsCode, codeArra, codeCbs);
		log.debug("Request inquiry Postpaid from Tibco : "
				+ BkpmUtil.convertToJson(inquiryRequest));

		TelkomPSTNSpeedyInquiryTibcoResponse inquiryResponse = Services
				.create(TelcoModuleService.class)
				.inquiryTelkomPSTNSpeedy(inquiryRequest).execute().body();

		log.debug("Response inquiry Postpaid from Tibco : "
				+ BkpmUtil.convertToJson(inquiryResponse));

		String codeRes = inquiryResponse.getRespayment().getResult()
				.getElement39();

		if (SUCCESS_CODE.equals(codeRes)) {
			log.debug("inquiry response success from tibco...");
			TelkomPSTNSpeedyInquiryRes resp = new TelkomPSTNSpeedyInquiryRes();

			resp = TelcoUtils.generatePSTNSpeedyTibcoResp(inquiryResponse
					.getRespayment().getResult());

			response.setCode(ResponseMessage.SUCCESS.getCode());
			response.setMessage(messageUtil.get("success",
					servletRequest.getLocale()));
			response.setData(resp);
		} else if (ERROR_CODE_PHONE_NUMBER_EXPIRED.equals(codeRes)) {
			log.error("phone number expired");
			response.setCode(ResponseMessage.PHONE_NUMBER_EXPIRED.getCode());
			response.setMessage(messageUtil.get("error.phone.number.expired",
					servletRequest.getLocale()));
		} else if (ERROR_CODE_BILL_ALREADY_PAID.equals(codeRes)
				|| ERROR_CODE_BILL_NOT_AVAILABLE.equals(codeRes)) {
			log.error("bill already paid");
			response.setCode(ResponseMessage.ERROR_BILL_ALREADY_PAID.getCode());
			response.setMessage(messageUtil.get("error.bill.already.paid",
					servletRequest.getLocale()));
		} else if (GIRO_LIMIT_TRANSFER.equals(codeRes)
				|| GIRO_OVER_LIMIT.equals(codeRes)) {
			log.error("exceed limit");
			response = new CommonResponse();
			response.setCode(ResponseMessage.LIMIT_TRANSFER_DAY.getCode());
			response.setMessage(messageUtil.get("error.exceed.limit",
					servletRequest.getLocale()));
		} else if (ERROR_CODE_CUTT_OFF.equals(codeRes)
				|| GIRO_CUT_OFF.equals(codeRes)) {
			log.error("cutoff in progress");
			response.setCode(ResponseMessage.ERROR_CUT_OFF_PLN.getCode());
			response.setMessage(messageUtil.get("error.cutoff",
					servletRequest.getLocale()));
		} else if (GIRO_DUPLICATE_DATA.equals(codeRes)) {
			log.error("Giro Duplicate Data");
			response = new CommonResponse();
			response.setCode(ResponseMessage.DUPLICATE_DATA.getCode());
			response.setMessage(messageUtil.get("error.duplicate.data",
					servletRequest.getLocale()));
		} else if (ERROR_CODE_SYSTEM_FAILURE.equals(codeRes)
				|| ERROR_CODE_SYSTEM_FAILURE_2.equals(codeRes)) {
			log.error("system failure");
			response.setCode(ResponseMessage.ERROR_SYSTEM_FAILURE.getCode());
			response.setMessage(messageUtil.get("error.system.failure",
					servletRequest.getLocale()));
		} else if (ERROR_CODE_PHONE_NUMBER_NOT_FOUND.equals(codeRes)
				|| ERROR_CODE_SPECIAL_NUMBER.equals(codeRes)
				|| ERROR_CODE_INVALID_PHONE_NUMBER.equals(codeRes)
				|| ERROR_CODE_NUMBER_NOT_REGISTERED.equals(codeRes)) {
			log.error("data not found with code : " + codeRes);
			response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
			response.setMessage(messageUtil.get("error.number.not.found",
					servletRequest.getLocale()));
		} else if (ERROR_CODE_INVALID_AMOUNT.equals(codeRes)) {
			log.error("invalid amount");
			response.setCode(ResponseMessage.INVALID_AMOUNT.getCode());
			response.setMessage(messageUtil.get("error.invalid.amount",
					servletRequest.getLocale()));
		} else if (ERROR_CODE_BLOCKED_ACCOUNT.equals(codeRes)
				|| GIRO_ACCOUNT_WAS_BLOCKED.equals(codeRes)
				|| GIRO_ACCOUNT_BLOCKED.equals(codeRes)) {
			log.error("blocked account");
			response.setCode(ResponseMessage.CUST_BLOCKED.getCode());
			response.setMessage(messageUtil.get("error.account.was.blocked",
					servletRequest.getLocale()));
		} else if (ERROR_CODE_CUSTOMER_NAME.equals(codeRes)) {
			log.error("customer name are not same in two bill at the same phone number");
			response.setCode(ResponseMessage.ERROR_CUSTOMER_NAME_NOT_SAME
					.getCode());
			response.setMessage(messageUtil.get("error.customer.not.same",
					servletRequest.getLocale()));
		} else if (ERROR_CODE_EXCEED_BILL_LIMIT.equals(codeRes)) {
			log.error("phone number exceed bill limit");
			response.setCode(ResponseMessage.ERROR_EXCEED_BILL_LIMIT.getCode());
			response.setMessage(messageUtil.get("error.exceed.bill.limit",
					servletRequest.getLocale()));
		} else if (ERROR_CODE_DIFFERENT_BILL.equals(codeRes)) {
			log.error("different bill at same month");
			response.setCode(ResponseMessage.ERROR_DIFFERENT_BILL.getCode());
			response.setMessage(messageUtil.get("error.different.bill",
					servletRequest.getLocale()));
		} else {
			log.error("error from aranet with code : " + codeRes);
			throw new MiddlewareException(codeRes);
		}

		return response;
	}

	/**
	 * Payment Postpaid Telco Telkom PSTN and SPEEDY
	 * 
	 * @param req
	 *            Telco Postpaid Payment Request please fill "type" with
	 *            TELKOM_PSTN or SPEEDY
	 * @return CommonResponse with Telco Postpaid Payment Response in body, else
	 *         Error.
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	@PostMapping("/TelkomPostpaid/purchase")
	public CommonResponse purchaseTelkomPostpaid(
			@RequestBody CommonRequest<TelcoPostpaidPaymentRequest> req)
			throws IOException {
		log.debug("REST request to Payment TELKOM/SPEEDY Postpaid : {}",
				req.getData());
		CommonResponse response = new CommonResponse();

		String type = req.getData().getType();

		String pin = req.getData().getPin();
		String username = req.getData().getUsername();

		ObjectMapper omapper = new ObjectMapper();

		// Validate Token and Phone Owner
		CommonRequest<VerifyPhoneOwnerRequest> phoneReq = new CommonRequest<>();
		VerifyPhoneOwnerRequest phoneReqData = new VerifyPhoneOwnerRequest();
		phoneReqData.setUsername(req.getData().getUsername());
		phoneReqData.setToken(servletRequest
				.getHeader(HttpHeaders.AUTHORIZATION));
		phoneReqData.setPhoneIdentity(servletRequest
				.getHeader(BkpmConstants.HTTP_HEADER_DEVICE_ID));
		phoneReq.setData(phoneReqData);
		CommonResponse resPhone = Services.create(UserModuleService.class)
				.verifyPhoneOwner(phoneReq).execute().body();

		if (!ResponseMessage.SUCCESS.getCode().equals(resPhone.getCode())) {
			log.error("Validate Token and Phone owner error..");
			return resPhone;
		}

		Map<String, String> resPhoneData = omapper.convertValue(
				resPhone.getData(), Map.class);
		String valid = String.valueOf(resPhoneData.get("valid"));

		if (valid.equalsIgnoreCase(ISFALSE)) {
			log.error("Token owner is not match...");
			response.setCode(ResponseMessage.DATA_NOT_MATCH.getCode());
			response.setMessage(messageUtil.get("error.data.not.match",
					servletRequest.getLocale()));

			return response;
		}

		// Check Cut Off
		long cutoffId = TelcoUtils.getCutOffId(req.getData().getType());
		CommonResponse cutOffResponse = Services
				.create(MasterModuleService.class)
				.checkCutOffStatus(servletRequest.getLocale().getLanguage(),
						cutoffId).execute().body();
		if (!ResponseMessage.SUCCESS.getCode().equals(cutOffResponse.getCode())) {
			log.error("Error Cutoff");
			return cutOffResponse;
		}

		// verify pin
		GetVerifyPINRequest verifyPinData = new GetVerifyPINRequest();
		verifyPinData.setPin(pin);
		verifyPinData.setUsername(username);

		CommonRequest<GetVerifyPINRequest> verifyPinReq = new CommonRequest<>();
		verifyPinReq.setIdentity(req.getIdentity());
		verifyPinReq.setData(verifyPinData);

		CommonResponse verifyPinRes = Services
				.create(UserModuleService.class)
				.verifyPIN(
						servletRequest.getHeader(HttpHeaders.ACCEPT_LANGUAGE),
						verifyPinReq).execute().body();

		if ((verifyPinRes == null)
				|| (!ResponseMessage.SUCCESS.getCode().equals(
						verifyPinRes.getCode()))) {

			return verifyPinRes;
		}

		// validate account number's owner user
		VerifyAccountOwnerRequest verifyAccountOwnerReqData = new VerifyAccountOwnerRequest();
		verifyAccountOwnerReqData.setAccountNo(req.getData().getAccountNo());
		verifyAccountOwnerReqData.setUsername(req.getData().getUsername());

		CommonRequest<VerifyAccountOwnerRequest> verifyAccOwnerRequest = new CommonRequest<>();
		verifyAccOwnerRequest.setIdentity(req.getIdentity());
		verifyAccOwnerRequest.setData(verifyAccountOwnerReqData);

		CommonResponse verifyAccOwnerResponse = Services
				.create(UserModuleService.class)
				.verifyAccountOwner(verifyAccOwnerRequest).execute().body();
		if (!ResponseMessage.SUCCESS.getCode().equals(
				verifyAccOwnerResponse.getCode())) {
			log.error("Error while verify account owner");
			return verifyAccOwnerResponse;
		}
		ObjectMapper oMapper = new ObjectMapper();
		VerifyAccountOwnerResponse verifyAccOwnRespObj = oMapper.convertValue(
				verifyAccOwnerResponse.getData(),
				VerifyAccountOwnerResponse.class);
		if (!verifyAccOwnRespObj.isValid()) {
			log.error("User and Account Info didn't match: "
					+ req.getData().getAccountNo());
			response.setCode(ResponseMessage.DATA_NOT_MATCH.getCode());
			response.setMessage(messageUtil.get("error.invalid.user.accountno",
					servletRequest.getLocale()));
			return response;
		}

		User user = verifyAccOwnRespObj.getUser();

		String forwardInsCode = env
				.getProperty("config.forwarding-institution-code");

		CommonResponse institutionRes = Services
				.create(MasterModuleService.class)
				.findCodeByProvider(servletRequest.getLocale().getLanguage(),
						type).execute().body();
		if (!ResponseMessage.SUCCESS.getCode().equals(institutionRes.getCode())) {
			// response not success
			return institutionRes;
		}

		String codeArra = req.getData().getCodeArra();
		String codeCbs = req.getData().getCodeCbs();

		String accType = verifyAccOwnRespObj.getAccountInfo().getAccountType()
				.name();
		// cek limit harian
		String amount = req.getData().getElement61().substring(78, 89);

		CommonRequest<limitUserDailyClass> lmtDl = new CommonRequest<>();
		limitUserDailyClass lmtDLClass = new limitUserDailyClass();
		lmtDLClass.setAccNo(req.getData().getAccountNo());
		lmtDLClass.setUsername(req.getData().getUsername());
		lmtDLClass.setAmount(new BigDecimal(amount + ""));
		lmtDLClass.setJenis("payment");
		lmtDl.setData(lmtDLClass);
		lmtDl.setIdentity(req.getIdentity());
		CommonResponse resLmtDL = Services.create(TransferModuleService.class)
				.verifydailylimit(lmtDl).execute().body();
		log.debug("log dari limit harian telco postpaid " + lmtDl
				+ " response " + resLmtDL);
		if (resLmtDL.getCode().equals("000")) {
			TelkomPSTNSpeedyPurchaseTibcoRequest purchaseTelkomPostpaidReq = TelcoUtils
					.generateTelkomPSTNSPeedyPurchaseRequest(req.getData(),
							forwardInsCode, codeArra, codeCbs, accType);

			log.debug("Request to Tibco : "
					+ BkpmUtil.convertToJson(purchaseTelkomPostpaidReq));

			TelkomPSTNSpeedyPurchaseTibcoResponse purchaseTelkomPostpaidTibcoResp = Services
					.create(TelcoModuleService.class)
					.purchaseTelkomPSTNSpeedy(purchaseTelkomPostpaidReq)
					.execute().body();

			log.debug("Response from Tibco : "
					+ BkpmUtil.convertToJson(purchaseTelkomPostpaidTibcoResp));

			String codeRes = purchaseTelkomPostpaidTibcoResp.getRespayment()
					.getResult().getElement39();

			if (SUCCESS_CODE.contains(codeRes)) {
				TelcoPostpaidPaymentResponse resp = new TelcoPostpaidPaymentResponse();

				resp = TelcoUtils.generatePurchaseTelkomPSTNSPeedyResponse(
						purchaseTelkomPostpaidTibcoResp.getRespayment()
								.getResult(), req.getData().getCustNo());

				// save to db (Destination and transaction)
				log.debug("saving to database destination and transaction");
				CommonRequest<DestinationCommonRequest> destReq = TelcoUtils
						.generateDestinationReq(req.getIdentity(),
								req.getData(), resp);
				CommonResponse saveFavRes = Services
						.create(MasterModuleService.class)
						.saveToFavouriteCommon(destReq).execute().body();
				if (SUCCESS_CODE.equals(saveFavRes.getCode())) {
					log.debug("saving to database telco postpaid");
					// save to db (Telco Postpaid)
					ObjectMapper objMapper = new ObjectMapper();
					Transaction transaction = objMapper.convertValue(
							saveFavRes.getData(), Transaction.class);
					String category = transaction.getDestination()
							.getCategory().getName();
					TelcoPostpaid telco = TelcoUtils.generateDataTelcoPostpaid(
							resp, transaction, destReq.getData());
					telcoPostpaidService.saveTelcoPostpaid(telco);
					log.debug("data has been saved successfully");

					// sendEmail
					log.debug("Send email receipt telco postpaid");
					telcoService.sendEmailReceiptPostpaidTelco(resp, user,
							category, servletRequest.getLocale());

					// set destination id to response
					resp.setDestinationId(transaction.getDestination().getId());

					response.setCode(ResponseMessage.SUCCESS.getCode());
					response.setMessage(messageUtil.get("success",
							servletRequest.getLocale()));
					response.setData(resp);
					log.debug("Payment postpaid " + req.getData().getType()
							+ " Success");
					// save limit harian
					log.debug("param save limit " + resLmtDL.getData());
					CommonResponse prosesLimit = Services
							.create(TransferModuleService.class)
							.prosesdailyLimit(resLmtDL.getData()).execute()
							.body();
					log.debug("log dari proses simpan limit telcopostpaid "
							+ prosesLimit);
				} else {
					log.error("Save to favourite Failed");
					response.setCode(ResponseMessage.INTERNAL_SERVER_ERROR
							.getCode());
					response.setMessage(messageUtil.get(
							"error.internal.server", servletRequest.getLocale()));
				}

			} else if (ERROR_CODE_PHONE_NUMBER_EXPIRED.equals(codeRes)) {
				log.error("phone number expired");
				response.setCode(ResponseMessage.PHONE_NUMBER_EXPIRED.getCode());
				response.setMessage(messageUtil.get(
						"error.phone.number.expired",
						servletRequest.getLocale()));
			} else if (ERROR_NOT_ENOUGH_BALANCE.equals(codeRes)
					|| GIRO_AMOUNT_NOT_ENOUGH_BALANCE.equals(codeRes)
					|| GIRO_ERROR_VALUTA_CODE.equals(codeRes)
					|| GIRO_LIMITED_BALANCE.equals(codeRes)) {
				log.error("Not enough balance");
				response.setCode(ResponseMessage.AMOUNT_NOT_ENOUGH.getCode());
				response.setMessage(messageUtil.get("error.amount.not.enough",
						servletRequest.getLocale()));
				return response;
			} else if (ERROR_ACCOUNT_INACTIVE.equals(codeRes)
					|| GIRO_INACTIVE_ACCOUNT.equalsIgnoreCase(codeRes)
					|| GIRO_CLOSED_ACCOUNT.equalsIgnoreCase(codeRes)) {
				log.error("account inactive");
				response.setCode(ResponseMessage.ERROR_INACTIVE_BANK_ACCOUNT
						.getCode());
				response.setMessage(messageUtil.get(
						"error.inactive.bank.account",
						servletRequest.getLocale()));
			} else if (ERROR_CODE_BILL_ALREADY_PAID.equals(codeRes)
					|| ERROR_CODE_BILL_NOT_AVAILABLE.equals(codeRes)) {
				log.error("bill already paid");
				response.setCode(ResponseMessage.ERROR_BILL_ALREADY_PAID
						.getCode());
				response.setMessage(messageUtil.get("error.bill.already.paid",
						servletRequest.getLocale()));
			} else if (ERROR_CODE_CUTT_OFF.equals(codeRes)) {
				log.error("cutoff in progress");
				response.setCode(ResponseMessage.ERROR_CUT_OFF_PLN.getCode());
				response.setMessage(messageUtil.get("error.cutoff",
						servletRequest.getLocale()));
			} else if (ERROR_CODE_SYSTEM_FAILURE.equals(codeRes)
					|| ERROR_CODE_SYSTEM_FAILURE_2.equals(codeRes)) {
				log.error("system failure");
				response.setCode(ResponseMessage.ERROR_SYSTEM_FAILURE.getCode());
				response.setMessage(messageUtil.get("error.system.failure",
						servletRequest.getLocale()));
			} else if (ERROR_CODE_PHONE_NUMBER_NOT_FOUND.equals(codeRes)
					|| ERROR_CODE_SPECIAL_NUMBER.equals(codeRes)
					|| ERROR_CODE_INVALID_PHONE_NUMBER.equals(codeRes)
					|| ERROR_CODE_NUMBER_NOT_REGISTERED.equals(codeRes)
					|| GIRO_USER_NOT_FOUND.equalsIgnoreCase(codeRes)) {
				log.error("data not found with code : " + codeRes);
				response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
				response.setMessage(messageUtil.get("error.data.not.found",
						servletRequest.getLocale()));
			} else if (ERROR_CODE_INVALID_AMOUNT.equals(codeRes)) {
				log.error("invalid amount");
				response.setCode(ResponseMessage.INVALID_AMOUNT.getCode());
				response.setMessage(messageUtil.get("error.invalid.amount",
						servletRequest.getLocale()));
			} else if (ERROR_CODE_BLOCKED_ACCOUNT.equals(codeRes)) {
				log.error("blocked account");
				response.setCode(ResponseMessage.CUST_BLOCKED.getCode());
				response.setMessage(messageUtil.get(
						"error.account.was.blocked", servletRequest.getLocale()));
			} else if (ERROR_CODE_CUSTOMER_NAME.equals(codeRes)) {
				log.error("customer name are not same in two bill at the same phone number");
				response.setCode(ResponseMessage.ERROR_CUSTOMER_NAME_NOT_SAME
						.getCode());
				response.setMessage(messageUtil.get("error.customer.not.same",
						servletRequest.getLocale()));
			} else if (ERROR_CODE_EXCEED_BILL_LIMIT.equals(codeRes)) {
				log.error("phone number exceed bill limit");
				response.setCode(ResponseMessage.ERROR_EXCEED_BILL_LIMIT
						.getCode());
				response.setMessage(messageUtil.get("error.exceed.bill.limit",
						servletRequest.getLocale()));
			} else if (ERROR_CODE_DIFFERENT_BILL.equals(codeRes)) {
				log.error("different bill at same month");
				response.setCode(ResponseMessage.ERROR_DIFFERENT_BILL.getCode());
				response.setMessage(messageUtil.get("error.different.bill",
						servletRequest.getLocale()));
			} else {
				log.error("error aranet with code : " + codeRes);
				throw new MiddlewareException(codeRes);
			}

		} else {
			if (resLmtDL.getMessage().equals("Limit user not set")) {
				response.setCode(resLmtDL.getCode());
				response.setMessage(messageUtil.get("error.limit.unset",
						servletRequest.getLocale()));

			} else if (resLmtDL.getMessage().equals(
					"amount more than daily limit user")) {
				response.setCode(resLmtDL.getCode());
				response.setMessage(messageUtil.get("error.limit.exceed",
						servletRequest.getLocale()));
			} else if (resLmtDL.getMessage().equals(
					"transactions exceed daily limit")) {
				response.setCode(resLmtDL.getCode());
				response.setMessage(messageUtil.get("error.limit.exceed",
						servletRequest.getLocale()));
			} else {
				response.setCode(resLmtDL.getCode());
				response.setMessage(resLmtDL.getMessage());
			}
		}

		return response;
	}

	/* Overrides: */

}
