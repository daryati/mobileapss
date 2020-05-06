/*
 * $Id$
 * 
 * Copyright (c) 2020 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.payment.web.rest;

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
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import id.co.asyst.bukopin.mobile.common.core.exception.MiddlewareException;
import id.co.asyst.bukopin.mobile.common.core.util.BkpmUtil;
import id.co.asyst.bukopin.mobile.common.core.util.MessageUtil;
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
import id.co.asyst.bukopin.mobile.payment.core.service.CreditCardService;
import id.co.asyst.bukopin.mobile.payment.core.service.ListCreditService;
import id.co.asyst.bukopin.mobile.payment.core.util.CreditCardUtil;
import id.co.asyst.bukopin.mobile.payment.model.entity.CreditCard;
import id.co.asyst.bukopin.mobile.payment.model.entity.ListCredit;
import id.co.asyst.bukopin.mobile.payment.model.payload.InstitutionMapper;
import id.co.asyst.bukopin.mobile.payment.model.payload.cc.CheckBINRequest;
import id.co.asyst.bukopin.mobile.payment.model.payload.cc.InquiryCreditCardRequest;
import id.co.asyst.bukopin.mobile.payment.model.payload.cc.InquiryCreditCardResponse;
import id.co.asyst.bukopin.mobile.payment.model.payload.cc.PaymentCreditCardRequest;
import id.co.asyst.bukopin.mobile.payment.model.payload.cc.PaymentCreditCardResponse;
import id.co.asyst.bukopin.mobile.service.core.MasterModuleService;
import id.co.asyst.bukopin.mobile.service.core.PaymentModuleService;
import id.co.asyst.bukopin.mobile.service.core.UserModuleService;
import id.co.asyst.bukopin.mobile.service.model.payload.cc.InquiryCreditCardTibcoReq;
import id.co.asyst.bukopin.mobile.service.model.payload.cc.InquiryCreditCardTibcoResponse;
import id.co.asyst.bukopin.mobile.service.model.payload.cc.PaymentCreditCardTibcoReq;
import id.co.asyst.bukopin.mobile.service.model.payload.cc.PaymentCreditCardTibcoResponse;
import id.co.asyst.bukopin.mobile.service.model.payload.pln.GetVerifyPINRequest;
import id.co.asyst.bukopin.mobile.user.model.entity.AccountCard;
import id.co.asyst.bukopin.mobile.user.model.entity.User;
import id.co.asyst.bukopin.mobile.user.model.payload.VerifyAccountOwnerRequest;
import id.co.asyst.bukopin.mobile.user.model.payload.VerifyAccountOwnerResponse;
import id.co.asyst.bukopin.mobile.user.model.payload.VerifyPhoneOwnerRequest;
import id.co.asyst.foundation.service.connector.Services;

/**
 * 
 * 
 * @author Ihsan Firman
 * @version $Revision$, Apr 9, 2020
 * @since 2.0
 */
@RestController
@RequestMapping("/creditCard")
public class CreditCardController {
	/* Constants: */
	private Logger log = LoggerFactory.getLogger(CreditCardController.class);

	private static final String SUCCESS_CODE = "000";
	private static final String ERROR_CODE_BILL_ALREADY_PAID = "188";
	private static final String ERROR_CODE_PHONE_NUMBER_EXPIRED = "181";
	private static final String ERROR_CODE_SYSTEM_FAILURE = "191";
	private static final String ERROR_CODE_PREFIX_UNKNOWN = "192";
	private static final String ERROR_CODE_SYSTEM_FAILURE_2 = "196";
	private static final String ERROR_CODE_CUTT_OFF = "190";
	private static final String ERROR_CODE_PHONE_NUMBER_NOT_FOUND = "214";
	private static final String ERROR_CODE_BLOCKED_ACCOUNT = "183";
	private static final String ERROR_CODE_INVALID_AMOUNT = "113";
	private static final String ERROR_CODE_INVALID_AMOUNT2 = "050";
	private static final String ERROR_CODE_CARD_NOT_FOUND = "114";
	private static final String ERROR_CODE_CARD_EXPIRED = "154";
	private static final String ERROR_CODE_BILL_ALREADY_PAID2 = "080";
	private static final String ERROR_CODE_BANK_ACCOUNT_BLOCKED = "814";
	private static final String ERROR_CODE_SPECIAL_NUMBER = "179";
	private static final String ERROR_CODE_INVALID_PHONE_NUMBER = "185";
	private static final String ERROR_CODE_CUSTOMER_NAME = "176";
	private static final String ERROR_CODE_EXCEED_BILL_LIMIT = "177";
	private static final String ERROR_CODE_DIFFERENT_BILL = "178";
	private static final String IRREGULAR_AREA_CODE = "IRREGULAR_AREA_CODE";
	private static final String ERROR_NOT_ENOUGH_BALANCE = "851";
	private static final String ERROR_ACCOUNT_INACTIVE = "839";
	private static final String CODE_CC_BKP = "CCBKP";
	private static final String NAME_BKP = "Bukopin";
	private static final String TRANSACTION_TYPE_POST = "POST";

	/* Attributes: */
	@Autowired
	private MessageUtil messageUtil;

	@Autowired
	private HttpServletRequest servletRequest;

	@Autowired
	private ListCreditService listCreditService;

	@Autowired
	private CreditCardService creditCardService;

	/**
	 * Environment
	 */
	@Autowired
	private Environment env;

	/* Transient Attributes: */

	/* Constructors: */

	/* Getters & setters for attributes: */

	/* Getters & setters for transient attributes: */

	/* Functionalities: */
	@PostMapping("/inquiry")
	@ResponseStatus(HttpStatus.OK)
	private CommonResponse inquiryCreditCard(@Valid @RequestBody CommonRequest<InquiryCreditCardRequest> request)
			throws IOException {
		log.debug("Process Credit Card Inquiry {}" + BkpmUtil.convertToJson(request.getData()));
		CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(),
				messageUtil.get("success", servletRequest.getLocale()));

		String username = request.getData().getUsername();
		String codeCc = request.getData().getCodeCc();
		String name = request.getData().getName();

		ObjectMapper omapper = new ObjectMapper();

		// Validate Token and Phone Owner
		CommonRequest<VerifyPhoneOwnerRequest> phoneReq = new CommonRequest<>();
		VerifyPhoneOwnerRequest phoneReqData = new VerifyPhoneOwnerRequest();
		phoneReqData.setUsername(request.getData().getUsername());
		phoneReqData.setToken(servletRequest.getHeader(HttpHeaders.AUTHORIZATION));
		phoneReqData.setPhoneIdentity(servletRequest.getHeader(BkpmConstants.HTTP_HEADER_DEVICE_ID));
		phoneReq.setData(phoneReqData);
		CommonResponse resPhone = Services.create(UserModuleService.class).verifyPhoneOwner(phoneReq).execute().body();
		if (!ResponseMessage.SUCCESS.getCode().equals(resPhone.getCode())) {
		    log.error("Validate Token and Phone owner error..");
		    return resPhone;
		}

		// get Registered Card
		CommonResponse findAccountCard = Services.create(UserModuleService.class).getAccountCardByUsername(username)
				.execute().body();
		if (!ResponseMessage.SUCCESS.getCode().equals(findAccountCard.getCode())) {
			return findAccountCard;
		}
		AccountCard accCard = omapper.convertValue(findAccountCard.getData(), AccountCard.class);
		String regCard = accCard.getRegisteredCard();

		// get code Cbs
		CommonResponse institutionRes = Services.create(MasterModuleService.class).findCodeByProvider(codeCc).execute()
				.body();
		InstitutionMapper findInstitution = omapper.convertValue(institutionRes.getData(), InstitutionMapper.class);

		String codeCbs = findInstitution.getCodeCbs();

		InquiryCreditCardTibcoReq inquiryTibco = CreditCardUtil.generateInquiryRequest(request.getData(), regCard,
				codeCbs);
		log.debug("Request Inquiry Credit Card to Tibco {}" + BkpmUtil.convertToJson(inquiryTibco));

		InquiryCreditCardTibcoResponse inquiryTibcoResp = Services.create(PaymentModuleService.class)
				.creditCardInquiryTibcoResponse(inquiryTibco).execute().body();
		log.debug("Response Inquiry Credit Card to Tibco {}" + BkpmUtil.convertToJson(inquiryTibcoResp));

		String codeRes = inquiryTibcoResp.getRespayment().getResult().getElement39();

		if (SUCCESS_CODE.equalsIgnoreCase(codeRes)) {
			log.debug("Inquiry Credit Card Success...");
			InquiryCreditCardResponse resp = new InquiryCreditCardResponse();
			resp = CreditCardUtil.generateInquiryResponse(inquiryTibcoResp, codeCc, name);

			response.setData(resp);
		} else if (ERROR_CODE_PHONE_NUMBER_EXPIRED.equals(codeRes)) {
			log.error("phone number expired");
			response.setCode(ResponseMessage.PHONE_NUMBER_EXPIRED.getCode());
			response.setMessage(messageUtil.get("error.phone.number.expired", servletRequest.getLocale()));
		} else if (ERROR_NOT_ENOUGH_BALANCE.equals(codeRes)) {
			log.error("Not enough balance");
			response.setCode(ResponseMessage.AMOUNT_NOT_ENOUGH.getCode());
			response.setMessage(messageUtil.get("error.amount.not.enough", servletRequest.getLocale()));
			return response;
		} else if (ERROR_ACCOUNT_INACTIVE.equals(codeRes)) {
			log.error("account inactive");
			response.setCode(ResponseMessage.ERROR_INACTIVE_BANK_ACCOUNT.getCode());
			response.setMessage(messageUtil.get("error.inactive.bank.account", servletRequest.getLocale()));
		} else if (ERROR_CODE_BILL_ALREADY_PAID.equals(codeRes)) {
			log.error("bill already paid");
			response.setCode(ResponseMessage.ERROR_BILL_ALREADY_PAID.getCode());
			response.setMessage(messageUtil.get("error.bill.already.paid", servletRequest.getLocale()));
		} else if (ERROR_CODE_CUTT_OFF.equals(codeRes)) {
			log.error("cutoff in progress");
			response.setCode(ResponseMessage.ERROR_CUT_OFF_PLN.getCode());
			response.setMessage(messageUtil.get("error.cutoff", servletRequest.getLocale()));
		} else if (ERROR_CODE_SYSTEM_FAILURE.equals(codeRes) || ERROR_CODE_SYSTEM_FAILURE_2.equals(codeRes)) {
			log.error("system failure");
			response.setCode(ResponseMessage.ERROR_SYSTEM_FAILURE.getCode());
			response.setMessage(messageUtil.get("error.system.failure", servletRequest.getLocale()));
		} else if (ERROR_CODE_PHONE_NUMBER_NOT_FOUND.equals(codeRes) || ERROR_CODE_SPECIAL_NUMBER.equals(codeRes)
				|| ERROR_CODE_INVALID_PHONE_NUMBER.equals(codeRes)) {
			log.error("data not found with code : " + codeRes);
			response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
			response.setMessage(messageUtil.get("error.data.not.found", servletRequest.getLocale()));
		} else if (ERROR_CODE_INVALID_AMOUNT.equals(codeRes)) {
			log.error("invalid amount");
			response.setCode(ResponseMessage.INVALID_AMOUNT.getCode());
			response.setMessage(messageUtil.get("error.invalid.amount", servletRequest.getLocale()));
		} else if (ERROR_CODE_BLOCKED_ACCOUNT.equals(codeRes)) {
			log.error("blocked account");
			response.setCode(ResponseMessage.CUST_BLOCKED.getCode());
			response.setMessage(messageUtil.get("error.account.was.blocked", servletRequest.getLocale()));
		} else if (ERROR_CODE_CUSTOMER_NAME.equals(codeRes)) {
			log.error("customer name are not same in two bill at the same phone number");
			response.setCode(ResponseMessage.ERROR_CUSTOMER_NAME_NOT_SAME.getCode());
			response.setMessage(messageUtil.get("error.customer.not.same", servletRequest.getLocale()));
		} else if (ERROR_CODE_EXCEED_BILL_LIMIT.equals(codeRes)) {
			log.error("phone number exceed bill limit");
			response.setCode(ResponseMessage.ERROR_EXCEED_BILL_LIMIT.getCode());
			response.setMessage(messageUtil.get("error.exceed.bill.limit", servletRequest.getLocale()));
		} else if (ERROR_CODE_DIFFERENT_BILL.equals(codeRes)) {
			log.error("different bill at same month");
			response.setCode(ResponseMessage.ERROR_DIFFERENT_BILL.getCode());
			response.setMessage(messageUtil.get("error.different.bill", servletRequest.getLocale()));
		} else {
			log.error("error aranet with code : " + codeRes);
			throw new MiddlewareException(codeRes);
		}

		return response;
	}

	@PostMapping("/payment")
	@ResponseStatus(HttpStatus.OK)
	private CommonResponse paymentCreditCard(@Valid @RequestBody CommonRequest<PaymentCreditCardRequest> request)
			throws IOException {
		log.debug("Process Credit Card Payment {}" + BkpmUtil.convertToJson(request.getData()));
		CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(),
				messageUtil.get("success", servletRequest.getLocale()));

		String username = request.getData().getUsername();
		String pin = request.getData().getPin();
		String codeCc = request.getData().getCodeCc();
		String name = request.getData().getName();

		// verify pin
		GetVerifyPINRequest verifyPinData = new GetVerifyPINRequest();
		verifyPinData.setPin(pin);
		verifyPinData.setUsername(username);

		CommonRequest<GetVerifyPINRequest> verifyPinReq = new CommonRequest<>();
		verifyPinReq.setIdentity(request.getIdentity());
		verifyPinReq.setData(verifyPinData);

		CommonResponse verifyPinRes = Services.create(UserModuleService.class).verifyPIN(verifyPinReq).execute().body();

		if ((verifyPinRes == null) || (!ResponseMessage.SUCCESS.getCode().equals(verifyPinRes.getCode()))) {

			return verifyPinRes;
		}

		// Validate Token and Phone Owner
		CommonRequest<VerifyPhoneOwnerRequest> phoneReq = new CommonRequest<>();
		VerifyPhoneOwnerRequest phoneReqData = new VerifyPhoneOwnerRequest();
		phoneReqData.setUsername(request.getData().getUsername());
		phoneReqData.setToken(servletRequest.getHeader(HttpHeaders.AUTHORIZATION));
		phoneReqData.setPhoneIdentity(servletRequest.getHeader(BkpmConstants.HTTP_HEADER_DEVICE_ID));
		phoneReq.setData(phoneReqData);
		CommonResponse resPhone = Services.create(UserModuleService.class).verifyPhoneOwner(phoneReq).execute().body();
		if (!ResponseMessage.SUCCESS.getCode().equals(resPhone.getCode())) {
			log.error("Validate Token and Phone owner error..");
			return resPhone;
		}

		// validate account number's owner user
		VerifyAccountOwnerRequest verifyAccountOwnerReqData = new VerifyAccountOwnerRequest();
		verifyAccountOwnerReqData.setAccountNo(request.getData().getAccountNumber());
		verifyAccountOwnerReqData.setUsername(request.getData().getUsername());

		CommonRequest<VerifyAccountOwnerRequest> verifyAccOwnerRequest = new CommonRequest<>();
		verifyAccOwnerRequest.setIdentity(request.getIdentity());
		verifyAccOwnerRequest.setData(verifyAccountOwnerReqData);

		CommonResponse verifyAccOwnerResponse = Services.create(UserModuleService.class)
				.verifyAccountOwner(verifyAccOwnerRequest).execute().body();
		if (!ResponseMessage.SUCCESS.getCode().equals(verifyAccOwnerResponse.getCode())) {
			log.error("Error while verify account owner");
			return verifyAccOwnerResponse;
		}
		ObjectMapper oMapper = new ObjectMapper();
		VerifyAccountOwnerResponse verifyAccOwnRespObj = oMapper.convertValue(verifyAccOwnerResponse.getData(),
				VerifyAccountOwnerResponse.class);
		if (!verifyAccOwnRespObj.isValid()) {
			log.error("User and Account Info didn't match: " + request.getData().getAccountNumber());
			response.setCode(ResponseMessage.DATA_NOT_MATCH.getCode());
			response.setMessage(messageUtil.get("error.invalid.user.accountno", servletRequest.getLocale()));
			return response;
		}

		User user = verifyAccOwnRespObj.getUser();

		String accType = verifyAccOwnRespObj.getAccountInfo().getAccountType().name();

		String forwardInsCode = env.getProperty("config.pln.forwarding-institution-code");

		// get Registered Card
		CommonResponse findAccountCard = Services.create(UserModuleService.class).getAccountCardByUsername(username)
				.execute().body();
		if (!ResponseMessage.SUCCESS.getCode().equals(findAccountCard.getCode())) {
			return findAccountCard;
		}
		AccountCard accCard = oMapper.convertValue(findAccountCard.getData(), AccountCard.class);
		String regCard = accCard.getRegisteredCard();

		// get code Cbs
		CommonResponse institutionRes = Services.create(MasterModuleService.class).findCodeByProvider(codeCc).execute()
				.body();
		InstitutionMapper findInstitution = oMapper.convertValue(institutionRes.getData(), InstitutionMapper.class);
		String codeCbs = findInstitution.getCodeCbs();

		if (!codeCc.equalsIgnoreCase(CODE_CC_BKP)) {
			CheckBINRequest checkBinData = new CheckBINRequest();
			checkBinData.setUsername(username);
			checkBinData.setCodeCc(codeCc);
			checkBinData.setName(name);
			checkBinData.setAmount(request.getData().getAmount());
			checkBinData.setSubscriberNumber(request.getData().getSubscriberNumber());

			CommonRequest<CheckBINRequest> binReq = new CommonRequest<>();
			binReq.setIdentity(request.getIdentity());
			binReq.setData(checkBinData);

			CommonResponse checkBin = checkBin(binReq);

			if (!SUCCESS_CODE.equals(checkBin.getCode())) {
				response.setCode(ResponseMessage.DATA_NOT_MATCH.getCode());
				response.setMessage(messageUtil.get("error.data.not.match", servletRequest.getLocale()));

				return response;
			}
		}
		PaymentCreditCardTibcoReq tibcoRequest = CreditCardUtil.generatePaymentCreditCardTibcoReq(request.getData(),
				name, regCard, accType, forwardInsCode, codeCbs);
		log.debug("Request Payment credit card to Tibco {}" + BkpmUtil.convertToJson(tibcoRequest));

		PaymentCreditCardTibcoResponse tibcoResponse = Services.create(PaymentModuleService.class)
				.creditCardPaymentTibcoResponse(tibcoRequest).execute().body();
		log.debug("Response Payment Credit Card from Tibco{}" + BkpmUtil.convertToJson(tibcoResponse));

		String codeRes = tibcoResponse.getRespayment().getResult().getElement39();

		if (SUCCESS_CODE.equals(codeRes)) {
			PaymentCreditCardResponse resp = new PaymentCreditCardResponse();
			resp = CreditCardUtil.generatePaymentCreditCardResponse(request.getData(), tibcoResponse);

			// save data
			log.debug("save purchase Credit Card to DB");
			CommonResponse saveRes = savePurchaseCreditCard(request.getIdentity(), resp, codeCc, username);

			if (SUCCESS_CODE.equals(saveRes.getCode())) {
				log.debug("ID DESTINATION .... " + saveRes.getData().toString());
				resp.setIdDestination(saveRes.getData().toString());

				log.debug("Send email receipt payment credit card...");
				creditCardService.sendEmailReceiptCreditCard(resp, codeCc, user, servletRequest.getLocale());

				response.setCode(ResponseMessage.SUCCESS.getCode());
				response.setMessage(messageUtil.get("success", servletRequest.getLocale()));
				response.setData(resp);

				log.debug("Credit Card Payment Saved to Favorite...");
			} else {
				log.error("Save to Favourite Failed");
				response.setCode(ResponseMessage.INTERNAL_SERVER_ERROR.getCode());
				response.setMessage(messageUtil.get("error.internal.server", servletRequest.getLocale()));
			}

		} else if (ERROR_CODE_PHONE_NUMBER_EXPIRED.equals(codeRes)) {
			log.error("phone number expired");
			response.setCode(ResponseMessage.PHONE_NUMBER_EXPIRED.getCode());
			response.setMessage(messageUtil.get("error.phone.number.expired", servletRequest.getLocale()));
		} else if (ERROR_NOT_ENOUGH_BALANCE.equals(codeRes)) {
			log.error("Not enough balance");
			response.setCode(ResponseMessage.AMOUNT_NOT_ENOUGH.getCode());
			response.setMessage(messageUtil.get("error.amount.not.enough", servletRequest.getLocale()));
			return response;
		} else if (ERROR_ACCOUNT_INACTIVE.equals(codeRes)) {
			log.error("account inactive");
			response.setCode(ResponseMessage.ERROR_INACTIVE_BANK_ACCOUNT.getCode());
			response.setMessage(messageUtil.get("error.inactive.bank.account", servletRequest.getLocale()));
		} else if (ERROR_CODE_BILL_ALREADY_PAID.equals(codeRes)) {
			log.error("bill already paid");
			response.setCode(ResponseMessage.ERROR_BILL_ALREADY_PAID.getCode());
			response.setMessage(messageUtil.get("error.bill.already.paid", servletRequest.getLocale()));
		} else if (ERROR_CODE_CUTT_OFF.equals(codeRes)) {
			log.error("cutoff in progress");
			response.setCode(ResponseMessage.ERROR_CUT_OFF_PLN.getCode());
			response.setMessage(messageUtil.get("error.cutoff", servletRequest.getLocale()));
		} else if (ERROR_CODE_SYSTEM_FAILURE.equals(codeRes) || ERROR_CODE_SYSTEM_FAILURE_2.equals(codeRes)) {
			log.error("system failure");
			response.setCode(ResponseMessage.ERROR_SYSTEM_FAILURE.getCode());
			response.setMessage(messageUtil.get("error.system.failure", servletRequest.getLocale()));
		} else if (ERROR_CODE_PHONE_NUMBER_NOT_FOUND.equals(codeRes) || ERROR_CODE_SPECIAL_NUMBER.equals(codeRes)
				|| ERROR_CODE_INVALID_PHONE_NUMBER.equals(codeRes)) {
			log.error("data not found with code : " + codeRes);
			response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
			response.setMessage(messageUtil.get("error.data.not.found", servletRequest.getLocale()));
		} else if (ERROR_CODE_INVALID_AMOUNT.equals(codeRes)) {
			log.error("invalid amount");
			response.setCode(ResponseMessage.INVALID_AMOUNT.getCode());
			response.setMessage(messageUtil.get("error.invalid.amount", servletRequest.getLocale()));
		} else if (ERROR_CODE_BLOCKED_ACCOUNT.equals(codeRes)) {
			log.error("blocked account");
			response.setCode(ResponseMessage.CUST_BLOCKED.getCode());
			response.setMessage(messageUtil.get("error.account.was.blocked", servletRequest.getLocale()));
		} else if (ERROR_CODE_CUSTOMER_NAME.equals(codeRes)) {
			log.error("customer name are not same in two bill at the same phone number");
			response.setCode(ResponseMessage.ERROR_CUSTOMER_NAME_NOT_SAME.getCode());
			response.setMessage(messageUtil.get("error.customer.not.same", servletRequest.getLocale()));
		} else if (ERROR_CODE_EXCEED_BILL_LIMIT.equals(codeRes)) {
			log.error("phone number exceed bill limit");
			response.setCode(ResponseMessage.ERROR_EXCEED_BILL_LIMIT.getCode());
			response.setMessage(messageUtil.get("error.exceed.bill.limit", servletRequest.getLocale()));
		} else if (ERROR_CODE_DIFFERENT_BILL.equals(codeRes)) {
			log.error("different bill at same month");
			response.setCode(ResponseMessage.ERROR_DIFFERENT_BILL.getCode());
			response.setMessage(messageUtil.get("error.different.bill", servletRequest.getLocale()));
		} else {
			log.error("error aranet with code : " + codeRes);
			throw new MiddlewareException(codeRes);
		}

		return response;
	}

	public CommonResponse savePurchaseCreditCard(Identity identity, PaymentCreditCardResponse res, String codeCc,
			String username) {
		CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(),
				messageUtil.get("success", servletRequest.getLocale()));
		try {

			// Prepare data request to save transaction
			DestinationCommonRequest dataReq = new DestinationCommonRequest();
			dataReq.setCategoryId(CategoryEnum.KARTU_KREDIT.getId());
			dataReq.setUsername(username);
			dataReq.setSubscriberNumber(res.getSubscriberNumber());
			dataReq.setSubscriberName(res.getSubscriberName());
			dataReq.setTransactionType(TransactionTypeEnum.CREDITCARD.name());
			dataReq.setReference(res.getReferenceNumber());
			dataReq.setAccountNumber(res.getAccountNumber());
			dataReq.setTotalAmount(res.getAmount());

			// Call service to save Destination/ Favorite
			CommonRequest<DestinationCommonRequest> destinationReq = new CommonRequest<>();
			destinationReq.setIdentity(identity);
			destinationReq.setData(dataReq);
			dataReq.setDestinationType(TRANSACTION_TYPE_POST.concat(codeCc));

			CommonResponse resSaveFav = Services.create(MasterModuleService.class).saveToFavouriteCommon(destinationReq)
					.execute().body();
			if (null != resSaveFav) {
				if (!SUCCESS_CODE.equals(resSaveFav.getCode())) {
					log.error("Save to favourite Failed");
					response.setCode(ResponseMessage.INTERNAL_SERVER_ERROR.getCode());
					response.setMessage(messageUtil.get("error.internal.server", servletRequest.getLocale()));
				} else {
					ObjectMapper oMapper = new ObjectMapper();
					Transaction transaction = oMapper.convertValue(resSaveFav.getData(), Transaction.class);
					log.debug("create");

					CreditCard cc = new CreditCard();
					cc.setAmount(res.getAmount());
					cc.setBilledAmount(res.getBilledAmount());
					cc.setMinimumAmount(res.getMinimumPayment());
					cc.setType(res.getName());
					cc.setTransaction(transaction);

					if (!codeCc.equalsIgnoreCase(CODE_CC_BKP)) {
						cc.setBilledAmount(new BigDecimal("0"));
						cc.setMinimumAmount(new BigDecimal("0"));
					}

					log.debug("save Payment to Credit Card");
					creditCardService.saveCreditCard(cc);

					log.debug("save OK.");
					response.setData(cc.getTransaction().getDestination().getId());

				}
			}
		} catch (IOException e) {
			log.error(e.getMessage(), e);
			response.setCode(ResponseMessage.INTERNAL_SERVER_ERROR.getCode());
			response.setMessage(messageUtil.get("error.internal.server", servletRequest.getLocale()));
		}

		return response;

	}

	@SuppressWarnings("unchecked")
	@PostMapping("/checkBin")
	public CommonResponse checkBin(@Valid @RequestBody CommonRequest<CheckBINRequest> request) throws IOException {
		CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(),
				messageUtil.get("success", servletRequest.getLocale()));
		log.debug("Check BIN by request {}" + BkpmUtil.convertToJson(request));

		String username = request.getData().getUsername();

		String codeCc = request.getData().getCodeCc();

		ObjectMapper mapper = new ObjectMapper();
		// Validate Token and Phone Owner
		CommonRequest<VerifyPhoneOwnerRequest> phoneReq = new CommonRequest<>();
		VerifyPhoneOwnerRequest phoneReqData = new VerifyPhoneOwnerRequest();
		phoneReqData.setUsername(username);
		phoneReqData.setToken(servletRequest.getHeader(HttpHeaders.AUTHORIZATION));
		phoneReqData.setPhoneIdentity(servletRequest.getHeader(BkpmConstants.HTTP_HEADER_DEVICE_ID));
		phoneReq.setData(phoneReqData);
		CommonResponse resPhone = Services.create(UserModuleService.class).verifyPhoneOwner(phoneReq).execute().body();
		if (!ResponseMessage.SUCCESS.getCode().equals(resPhone.getCode())) {
			log.error("Validate Token and Phone owner error..");
			return resPhone;
		}

		String subsNum = request.getData().getSubscriberNumber();

		String subscriberNum = subsNum.substring(0, 8);

		CommonResponse prefixCheck = Services.create(MasterModuleService.class).findOneByPrefixNo(subscriberNum)
				.execute().body();

		// check 8 digit subscriber number
		if (prefixCheck.getData() != null) {
			List<String> prefix = mapper.convertValue(prefixCheck.getData(), ArrayList.class);
			if (codeCc.equalsIgnoreCase(prefix.get(0))) {
				response.setData(request.getData());
				return response;

			} else {
				log.error("Prefix not found for subscriber number: " + subscriberNum);
				response.setCode(ResponseMessage.DATA_NOT_MATCH.getCode());
				response.setMessage(messageUtil.get("error.data.not.match", servletRequest.getLocale()));
			}
		} else {
			// check 6 digit subscriber number
			String digit6 = subscriberNum.substring(0, 6);

			prefixCheck = Services.create(MasterModuleService.class).findOneByPrefixNo(digit6).execute().body();
			if (prefixCheck.getData() != null) {
				List<String> prefix6 = mapper.convertValue(prefixCheck.getData(), ArrayList.class);
				if (codeCc.equalsIgnoreCase(prefix6.get(0))) {
					response.setData(request.getData());
					return response;

				} else {
					log.error("Prefix not found for subscriber number: " + subscriberNum);
					response.setCode(ResponseMessage.DATA_NOT_MATCH.getCode());
					response.setMessage(messageUtil.get("error.data.not.match", servletRequest.getLocale()));
				}
			} else {
				log.error("Prefix not found for subscriber number: " + subscriberNum);
				response.setCode(ResponseMessage.DATA_NOT_MATCH.getCode());
				response.setMessage(messageUtil.get("error.data.not.match", servletRequest.getLocale()));
			}

		}

		return response;

	}
	/* Overrides: */
}
