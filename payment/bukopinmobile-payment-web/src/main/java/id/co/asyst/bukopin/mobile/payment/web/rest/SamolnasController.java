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
import java.text.ParseException;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import id.co.asyst.bukopin.mobile.common.core.exception.MiddlewareException;
import id.co.asyst.bukopin.mobile.common.core.util.BkpmUtil;
import id.co.asyst.bukopin.mobile.common.core.util.MessageUtil;
import id.co.asyst.bukopin.mobile.common.model.BkpmConstants;
import id.co.asyst.bukopin.mobile.common.model.ResponseMessage;
import id.co.asyst.bukopin.mobile.common.model.SystemCutOffEnum;
import id.co.asyst.bukopin.mobile.common.model.payload.CommonRequest;
import id.co.asyst.bukopin.mobile.common.model.payload.CommonResponse;
import id.co.asyst.bukopin.mobile.master.model.entity.Transaction;
import id.co.asyst.bukopin.mobile.master.model.payload.DestinationCommonRequest;
import id.co.asyst.bukopin.mobile.master.model.payload.InstitutionRequest;
import id.co.asyst.bukopin.mobile.payment.core.service.PaymentServices;
import id.co.asyst.bukopin.mobile.payment.core.service.SamolnasService;
import id.co.asyst.bukopin.mobile.payment.core.util.SamolnasUtils;
import id.co.asyst.bukopin.mobile.payment.model.entity.Samolnas;
import id.co.asyst.bukopin.mobile.payment.model.payload.SamolnasInquiryRequest;
import id.co.asyst.bukopin.mobile.payment.model.payload.SamolnasInquiryResponse;
import id.co.asyst.bukopin.mobile.payment.model.payload.SamolnasPaymentRequest;
import id.co.asyst.bukopin.mobile.payment.model.payload.SamolnasPaymentResponse;
import id.co.asyst.bukopin.mobile.service.core.MasterModuleService;
import id.co.asyst.bukopin.mobile.service.core.SamolnasModuleService;
import id.co.asyst.bukopin.mobile.service.core.UserModuleService;
import id.co.asyst.bukopin.mobile.service.model.payload.pln.GetVerifyPINRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.samolnas.SamolnasInquiryTibcoRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.samolnas.SamolnasInquiryTibcoResponse;
import id.co.asyst.bukopin.mobile.service.model.payload.samolnas.SamolnasPaymentTibcoRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.samolnas.SamolnasPaymentTibcoResponse;
import id.co.asyst.bukopin.mobile.user.model.entity.User;
import id.co.asyst.bukopin.mobile.user.model.payload.VerifyAccountOwnerRequest;
import id.co.asyst.bukopin.mobile.user.model.payload.VerifyAccountOwnerResponse;
import id.co.asyst.bukopin.mobile.user.model.payload.VerifyPhoneOwnerRequest;
import id.co.asyst.foundation.service.connector.Services;

/**
 * 
 * 
 * @author Gibran Haq
 * @version $Revision$, Apr 27, 2020
 * @since 1.2.Alpha1
 */
@RestController
@RequestMapping("/samolnas")
public class SamolnasController {
    /* Constants: */
    private final Logger log = LoggerFactory.getLogger(SamolnasController.class);

    private static final long PREFIX_SAMOLNAS = 29;
    private static final String INSTITUTION_TYPE_SAMOLNAS = "Postpaid";

    private static final String SUCCESS_CODE = "000";
    private static final String ERROR_PAYCODE = "110";
    private static final String ERROR_MISC = "199";
    private static final String ERROR_PAYMENT_TIMEOUT = "168";
    private static final String ERROR_PAYMENT_TIMEOUT_2 = "068";
    private static final String ERROR_INVALID_AMOUNT = "150";
    private static final String ERROR_CODE_BILL_ALREADY_PAID = "188";
    private static final String ERROR_NOT_ENOUGH_BALANCE = "851";
    private static final String ERROR_ACCOUNT_INACTIVE = "839";

    /* Attributes: */
    @Autowired
    private Environment env;

    @Autowired
    private MessageUtil messageUtil;

    @Autowired
    private HttpServletRequest servletRequest;

    @Autowired
    private SamolnasService samolnasService;

    @Autowired
    PaymentServices paymentServices;

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */

    /* Getters & setters for transient attributes: */

    /* Functionalities: */

    /**
     * inquirySamolnas
     * 
     * @param req
     * @return
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("/inquiry")
    private CommonResponse inquirySamolnas(@Valid @RequestBody CommonRequest<SamolnasInquiryRequest> req)
	    throws IOException {
	log.debug("REST request to inquiry Samolnas : {}", req.getData());
	CommonResponse response = new CommonResponse();

	// Validate Token and Phone Owner
	CommonRequest<VerifyPhoneOwnerRequest> phoneReq = new CommonRequest<>();
	VerifyPhoneOwnerRequest phoneReqData = new VerifyPhoneOwnerRequest();
	phoneReqData.setUsername(req.getData().getUsername());
	phoneReqData.setToken(servletRequest.getHeader(HttpHeaders.AUTHORIZATION));
	phoneReqData.setPhoneIdentity(servletRequest.getHeader(BkpmConstants.HTTP_HEADER_DEVICE_ID));
	phoneReq.setData(phoneReqData);
	CommonResponse resPhone = Services.create(UserModuleService.class).verifyPhoneOwner(phoneReq).execute().body();
	if (!ResponseMessage.SUCCESS.getCode().equals(resPhone.getCode())) {
	    log.error("Validate Token and Phone owner error..");
	    return resPhone;
	}
	ObjectMapper oMapper = new ObjectMapper();
	Map<String, Boolean> resultPhoneObj = oMapper.convertValue(resPhone.getData(), Map.class);
	if (!resultPhoneObj.get("valid")) {
	    log.error("Token and phone owner invalid");
	    response.setCode(ResponseMessage.DATA_NOT_MATCH.getCode());
	    response.setMessage(messageUtil.get("error.invalid.token.phone.owner", servletRequest.getLocale()));
	    return response;
	}
	
	// Check Cut Off
	long cutoffId = SystemCutOffEnum.SAMOLNAS.getId();
	CommonResponse cutOffResponse = Services.create(MasterModuleService.class)
		.checkCutOffStatus(servletRequest.getLocale().getLanguage(), cutoffId).execute().body();
	if (!ResponseMessage.SUCCESS.getCode().equals(cutOffResponse.getCode())) {
	    log.error("Error Cutoff");
	    return cutOffResponse;
	}

	String payCode = req.getData().getPayCode();
	String nik = req.getData().getNik();
	String forwardInsCode = env.getProperty("config.pln.forwarding-institution-code");

	// get institution data
	InstitutionRequest insReq = new InstitutionRequest();
	insReq.setIdPrefix(PREFIX_SAMOLNAS);
	insReq.setInstitutionType(INSTITUTION_TYPE_SAMOLNAS);
	CommonRequest<InstitutionRequest> ins = new CommonRequest<InstitutionRequest>();
	ins.setData(insReq);
	CommonResponse institutionRes = Services.create(MasterModuleService.class).findByPrefixIdAndInstitutionType(ins)
		.execute().body();
	if (!ResponseMessage.SUCCESS.getCode().equals(institutionRes.getCode())) {
	    // response not success
	    return institutionRes;
	}
	ObjectMapper mapper = new ObjectMapper();
	Map<String, String> instRespObj = mapper.convertValue(institutionRes.getData(), Map.class);
	// set code arra and code cbs from institution
	String codeArra = String.valueOf(instRespObj.get("codeArra"));
	String codeCbs = String.valueOf(instRespObj.get("codeCbs"));

	SamolnasInquiryTibcoRequest inquiryTibcoReq = SamolnasUtils.generateSamolnasInquiryRequest(payCode, nik,
		forwardInsCode, codeArra, codeCbs);
	log.debug("Request inquiry samolnas to tibco : " + BkpmUtil.convertToJson(inquiryTibcoReq));

	SamolnasInquiryTibcoResponse inquiryTibcoRes = Services.create(SamolnasModuleService.class)
		.inquirySamolnas(inquiryTibcoReq).execute().body();
	String codeRes = inquiryTibcoRes.getRespayment().getResult().getElement39();
	log.debug("Response inquiry samolnas to tibco : " + BkpmUtil.convertToJson(inquiryTibcoRes));

	if (SUCCESS_CODE.equals(codeRes)) {
	    log.debug("Inquiry samolnas success");

	    // set response
	    SamolnasInquiryResponse resp = new SamolnasInquiryResponse();
	    resp = SamolnasUtils.generateSamolnasInquiryResponse(inquiryTibcoRes.getRespayment().getResult());
	    resp.setCodeArra(codeArra);
	    resp.setCodeCbs(codeCbs);

	    response.setCode(ResponseMessage.SUCCESS.getCode());
	    response.setMessage(messageUtil.get("success", servletRequest.getLocale()));
	    response.setData(resp);
	} else if (ERROR_PAYCODE.equals(codeRes)) {
	    log.error("invalid pay code");
	    response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
	    response.setMessage(messageUtil.get("error.data.not.found", servletRequest.getLocale()));
	} else if (ERROR_CODE_BILL_ALREADY_PAID.equals(codeRes)) {
	    log.error("bill already paid");
	    response.setCode(ResponseMessage.ERROR_BILL_ALREADY_PAID.getCode());
	    response.setMessage(messageUtil.get("error.bill.already.paid", servletRequest.getLocale()));
	} else if (ERROR_MISC.equals(codeRes)) {
	    log.error("Misc error");
	    throw new MiddlewareException(codeRes);
	} else if (ERROR_INVALID_AMOUNT.equals(codeRes)) {
	    log.error("invalid amount");
	    response.setCode(ResponseMessage.INVALID_AMOUNT.getCode());
	    response.setMessage(messageUtil.get("error.invalid.amount", servletRequest.getLocale()));
	} else {
	    log.error("error from aranet with code : " + codeRes);
	    throw new MiddlewareException(codeRes);
	}

	return response;
    }

    @RequestMapping("/payment")
    private CommonResponse paymentSamolnas(@Valid @RequestBody CommonRequest<SamolnasPaymentRequest> req)
	    throws IOException, ParseException {
	log.debug("REST request to payment Samolnas : {}", req.getData());
	CommonResponse response = new CommonResponse();

	// Validate Token and Phone Owner
	CommonRequest<VerifyPhoneOwnerRequest> phoneReq = new CommonRequest<>();
	VerifyPhoneOwnerRequest phoneReqData = new VerifyPhoneOwnerRequest();
	phoneReqData.setUsername(req.getData().getUsername());
	phoneReqData.setToken(servletRequest.getHeader(HttpHeaders.AUTHORIZATION));
	phoneReqData.setPhoneIdentity(servletRequest.getHeader(BkpmConstants.HTTP_HEADER_DEVICE_ID));
	phoneReq.setData(phoneReqData);
	CommonResponse resPhone = Services.create(UserModuleService.class).verifyPhoneOwner(phoneReq).execute().body();
	if (!ResponseMessage.SUCCESS.getCode().equals(resPhone.getCode())) {
	    log.error("Validate Token and Phone owner error..");
	    return resPhone;
	}
	ObjectMapper mapper = new ObjectMapper();
	Map<String, Boolean> resultPhoneObj = mapper.convertValue(resPhone.getData(), Map.class);
	if (!resultPhoneObj.get("valid")) {
	    log.error("Token and phone owner invalid");
	    response.setCode(ResponseMessage.DATA_NOT_MATCH.getCode());
	    response.setMessage(messageUtil.get("error.invalid.token.phone.owner", servletRequest.getLocale()));
	    return response;
	}
	
	// Check Cut Off
	long cutoffId = SystemCutOffEnum.SAMOLNAS.getId();
	CommonResponse cutOffResponse = Services.create(MasterModuleService.class)
		.checkCutOffStatus(servletRequest.getLocale().getLanguage(), cutoffId).execute().body();
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

	CommonResponse verifyPINRes = Services.create(UserModuleService.class).verifyPIN(verifyPINReq).execute().body();
	if (!ResponseMessage.SUCCESS.getCode().equals(verifyPINRes.getCode())) {
	    log.error("Error while verify PIN");
	    return verifyPINRes;
	}

	// validate account number's owner user
	VerifyAccountOwnerRequest verifyAccountOwnerReqData = new VerifyAccountOwnerRequest();
	verifyAccountOwnerReqData.setAccountNo(req.getData().getAccountNumber());
	verifyAccountOwnerReqData.setUsername(req.getData().getUsername());

	CommonRequest<VerifyAccountOwnerRequest> verifyAccOwnerRequest = new CommonRequest<>();
	verifyAccOwnerRequest.setIdentity(req.getIdentity());
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
	    log.error("User and Account Info didn't match: " + req.getData().getAccountNumber());
	    response.setCode(ResponseMessage.DATA_NOT_MATCH.getCode());
	    response.setMessage(messageUtil.get("error.invalid.user.accountno", servletRequest.getLocale()));
	    return response;
	}
	User user = verifyAccOwnRespObj.getUser();
	String accType = verifyAccOwnRespObj.getAccountInfo().getAccountType().name();

	String forwardInsCode = env.getProperty("config.pln.forwarding-institution-code");
	String codeArra = req.getData().getCodeArra();
	String codeCbs = req.getData().getCodeCbs();

	SamolnasPaymentTibcoRequest reqPaymentTibco = SamolnasUtils.generateSamolnasPaymentRequest(req.getData(),
		forwardInsCode, codeArra, codeCbs, accType);
	log.debug("Request payment samolnas to tibco : " + BkpmUtil.convertToJson(reqPaymentTibco));

	SamolnasPaymentTibcoResponse resPaymentTibco = Services.create(SamolnasModuleService.class)
		.paymentSamolnas(reqPaymentTibco).execute().body();
	String codeRes = resPaymentTibco.getRespayment().getResult().getElement39();
	log.debug("Response payment samolnas to tibco : " + BkpmUtil.convertToJson(resPaymentTibco));

	if (SUCCESS_CODE.equals(codeRes)) {
	    // set response
	    SamolnasPaymentResponse resp = new SamolnasPaymentResponse();
	    resp = SamolnasUtils.generateSamolnasPaymentResponse(resPaymentTibco.getRespayment().getResult());

	    // save to db (Destination and transaction)
	    log.debug("saving to database destination and transaction");
	    CommonRequest<DestinationCommonRequest> destReq = SamolnasUtils.generateDestinationReq(req.getIdentity(),
		    req.getData(), resp);
	    CommonResponse saveFavRes = Services.create(MasterModuleService.class).saveToFavouriteCommon(destReq)
		    .execute().body();
	    if (SUCCESS_CODE.equals(saveFavRes.getCode())) {
		log.debug("saving to database samolnas");
		// save to db (Samolnas)
		ObjectMapper objMapper = new ObjectMapper();
		Transaction transaction = objMapper.convertValue(saveFavRes.getData(), Transaction.class);
		Samolnas samolnas = SamolnasUtils.generateDataSamolnas(resp, transaction, destReq.getData());
		samolnasService.saveSamolnas(samolnas);
		log.debug("data has been saved successfully");

		// sendEmail
		log.debug("Send email receipt samolnas");
		paymentServices.sendEmailReceiptSamolnas(resp, user, servletRequest.getLocale());
		// set destination id to response
		resp.setIdDestination(transaction.getDestination().getId());

		response.setCode(ResponseMessage.SUCCESS.getCode());
		response.setMessage(messageUtil.get("success", servletRequest.getLocale()));
		response.setData(resp);
		log.debug("Payment samolnas Success");
	    } else {
		log.error("Save to favourite Failed");
		response.setCode(ResponseMessage.INTERNAL_SERVER_ERROR.getCode());
		response.setMessage(messageUtil.get("error.internal.server", servletRequest.getLocale()));
	    }
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
	} else if (ERROR_PAYMENT_TIMEOUT.equals(codeRes)) {
	    log.error("Payment timeout no response");
	    throw new MiddlewareException(codeRes);
	} else if (ERROR_PAYMENT_TIMEOUT_2.equals(codeRes)) {
	    log.error("Payment timeout");
	    throw new MiddlewareException(codeRes);
	} else {
	    log.error("error aranet with code : " + codeRes);
	    throw new MiddlewareException(codeRes);
	}

	return response;
    }

    /* Overrides: */

}
