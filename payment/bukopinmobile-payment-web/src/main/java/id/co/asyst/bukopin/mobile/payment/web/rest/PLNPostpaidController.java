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
import java.math.BigInteger;
import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import id.co.asyst.bukopin.mobile.common.core.exception.DataNotMatchException;
import id.co.asyst.bukopin.mobile.common.core.exception.MiddlewareException;
import id.co.asyst.bukopin.mobile.common.core.service.impl.BkpmService;
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
import id.co.asyst.bukopin.mobile.payment.core.service.PLNPostpaidService;
import id.co.asyst.bukopin.mobile.payment.core.service.PLNServices;
import id.co.asyst.bukopin.mobile.payment.core.util.PLNUtils;
import id.co.asyst.bukopin.mobile.payment.model.entity.PLNPostpaid;
import id.co.asyst.bukopin.mobile.payment.model.payload.PLNPostpaidPaymentRequest;
import id.co.asyst.bukopin.mobile.payment.model.payload.PLNPostpaidPaymentResponse;
import id.co.asyst.bukopin.mobile.payment.model.payload.inquiryPostpaidResponsePLN;
import id.co.asyst.bukopin.mobile.service.core.MasterModuleService;
import id.co.asyst.bukopin.mobile.service.core.PLNService;
import id.co.asyst.bukopin.mobile.service.core.UserModuleService;
import id.co.asyst.bukopin.mobile.service.model.payload.pln.GetVerifyPINRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.pln.postpaid.PostpaidRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.pln.postpaid.PostpaidResponse;
import id.co.asyst.bukopin.mobile.service.model.payload.pln.prepaid.PrepaidInquiryRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.pln.prepaid.PrepaidInquiryResponse;
import id.co.asyst.bukopin.mobile.user.model.entity.User;
import id.co.asyst.bukopin.mobile.user.model.payload.VerifyAccountOwnerRequest;
import id.co.asyst.bukopin.mobile.user.model.payload.VerifyAccountOwnerResponse;
import id.co.asyst.bukopin.mobile.user.model.payload.VerifyPhoneOwnerRequest;
import id.co.asyst.foundation.service.connector.Services;

/**
 * 
 * 
 * @author Gibran Haq
 * @version $Revision$, Jan 15, 2020
 * @since 1.0.Alpha1-SNAPSHOT
 */
@RestController
@RequestMapping("/PLN/postpaid")
public class PLNPostpaidController {
    /* Constants: */
    private final Logger log = LoggerFactory.getLogger(PLNPostpaidController.class);

    private static final String SUCCESS_CODE = "000";
    private static final String ARANET_TIME_OUT_CODE = "999";
    private static final String UNREGISTERED_BILLER = "104";
    private static final String UNREGISTERED_SUBSCRIBER_NUMBER = "114";
    private static final String PRR_SUBSCRIBER = "116";
    private static final String SUBSCRIBER_SUSPENDED = "177";
    private static final String PLN_CUT_OFF = "190";
    private static final String PLN_ALREADY_PAID = "188";
    private static final String PLN_BILL_NOT_AVAILABLE = "189";
    private static final String PLN_NOT_ENOUGH_BALANCE = "851";
    private static final String PLN_INACTIVE_ACCOUNT = "839";

    /* Attributes: */
    @Autowired
    private MessageUtil messageUtil;

    @Autowired
    private HttpServletRequest servletRequest;

    /**
     * Environment
     */
    @Autowired
    private Environment env;
    
    /**
     * PLN Services
     */
    @Autowired
    private PLNServices plnService;
    
    /**
     * PLN Postpaid Service
     */
    @Autowired
    private PLNPostpaidService postpaidService;
    
    /**
     * Bkpm Common Service
     */
    @Autowired
    private BkpmService commonService;

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */

    /* Getters & setters for transient attributes: */

    /* Functionalities: */
    /**
     * Inquiry Postpaid PLN
     * 
     * @param request Subscriber ID
     * @return Http Response 200 with data in body, else Error.
     * @throws IOException Will returns Connection Exception.
     */
    @PostMapping("/inquiry")
    private CommonResponse inquiryPostPaidPLN(@RequestBody CommonRequest<Map<String, String>> req) throws IOException {
	CommonResponse response = new CommonResponse();
	Calendar cal = Calendar.getInstance();
	String monthName = new DateFormatSymbols().getMonths()[cal.get(Calendar.MONTH)];

	String forwardInsCode = env.getProperty("config.pln.forwarding-institution-code");
	PrepaidInquiryRequest reqInquiryPostpaidPLNAranet = PLNUtils
		.generateInquiryPostpaidPLNReq(req.getData().get("subscriberID"), forwardInsCode);
	log.debug("request : " + BkpmUtil.convertToJson(reqInquiryPostpaidPLNAranet));
	PrepaidInquiryResponse resInquiryPostpaidPLNAranet = Services.create(PLNService.class)
		.prepaidInquiry(reqInquiryPostpaidPLNAranet).execute().body();

	log.debug("response : " + BkpmUtil.convertToJson(resInquiryPostpaidPLNAranet.getRespayment().getResult()));

	String codeRes = resInquiryPostpaidPLNAranet.getRespayment().getResult().getElement39();
	if (PLN_CUT_OFF.equals(codeRes)) {
	    log.error("Cut Off PLN");
	    response.setCode(ResponseMessage.ERROR_CUT_OFF_PLN.getCode());
	    response.setMessage(messageUtil.get("error.cutoff.pln", servletRequest.getLocale()));
	} else if (UNREGISTERED_BILLER.equals(codeRes) || UNREGISTERED_SUBSCRIBER_NUMBER.equals(codeRes)) {
	    log.error("Unregistered biller or subscriber number");
	    response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
	    response.setMessage(messageUtil.get("error.id.pln.not.found", servletRequest.getLocale()));
	} else if (PRR_SUBSCRIBER.equals(codeRes) || SUBSCRIBER_SUSPENDED.equals(codeRes)) {
	    log.error("Subscriber was blocked");
	    response.setCode(ResponseMessage.PLN_SUBSCRIBER_SUSPENDED.getCode());
	    response.setMessage(messageUtil.get("error.user.pln.block",
		    new Object[] { req.getData().get("subscriberID") }, servletRequest.getLocale()));
	} else if (PLN_ALREADY_PAID.equals(codeRes)) {
	    log.error("PLN already paid");
	    response.setCode(ResponseMessage.ERROR_BILL_ALREADY_PAID.getCode());
	    response.setMessage(messageUtil.get("error.pln.already.paid", servletRequest.getLocale()));
	} else if (PLN_BILL_NOT_AVAILABLE.equals(codeRes)) {
	    log.error("Current Bill not available");
	    response.setCode(ResponseMessage.ERROR_BILL_NOT_AVAILABLE.getCode());
	    response.setMessage(messageUtil.get("error.bill.not.available",
		    new Object[] { monthName }, servletRequest.getLocale()));
	} else if (!SUCCESS_CODE.equals(codeRes)) {
	    log.error("Error from Aranet with code : " + codeRes);
	    response.setCode(ResponseMessage.INTERNAL_SERVER_ERROR.getCode());
	    response.setMessage(messageUtil.get("error.internal.server", servletRequest.getLocale()));
	} else {
	    log.debug("Inquiry Postpaid PLN success");
	    String dataResp = resInquiryPostpaidPLNAranet.getRespayment().getResult().getElement48();
	    inquiryPostpaidResponsePLN inqPostpaidResPLN = new inquiryPostpaidResponsePLN();

	    String subsName = dataResp.substring(54, 79);
	    String tarif = dataResp.substring(100, 103);
	    String daya = dataResp.substring(103, 112);
	    String adminFee = dataResp.substring(112, 121);
	    String outstandingBill = dataResp.substring(20, 22);
	    int outStandBillInteger = Integer.parseInt(outstandingBill);
	    Map<String, String> billPeriod = new HashMap<String, String>();
	    String billStatus = dataResp.substring(19,20);
	    int billStatusInteger = Integer.parseInt(billStatus);

	    // repeated variable in element 48
	    // bill period 1
	    String billPeriod1 = "";
	    String yearBill1 = "";
	    String monthBill1 = "";
	    String monthBillString1 = "";
	    String totalElectricity1 = "";
	    String penaltyFee1 = "";
	    String prevMeter1 = "";
	    String currMeter1 = "";

	    // bill period 2
	    String billPeriod2 = "";
	    String yearBill2 = "";
	    String monthBill2 = "";
	    String monthBillString2 = "";
	    String totalElectricity2 = "";
	    String penaltyFee2 = "";
	    String prevMeter2 = "";
	    String currMeter2 = "";

	    // bill period 3
	    String billPeriod3 = "";
	    String yearBill3 = "";
	    String monthBill3 = "";
	    String monthBillString3 = "";
	    String totalElectricity3 = "";
	    String penaltyFee3 = "";
	    String prevMeter3 = "";
	    String currMeter3 = "";

	    // bill period 4
	    String billPeriod4 = "";
	    String yearBill4 = "";
	    String monthBill4 = "";
	    String monthBillString4 = "";
	    String totalElectricity4 = "";
	    String penaltyFee4 = "";
	    String prevMeter4 = "";
	    String currMeter4 = "";
	    if (billStatusInteger == 1) {
		// bill period 1
		billPeriod1 = dataResp.substring(121, 127);
		yearBill1 = billPeriod1.substring(2, 4);
		monthBill1 = billPeriod1.substring(4, 6);
		monthBillString1 = new DateFormatSymbols().getMonths()[Integer.parseInt(monthBill1) - 1];
		monthBillString1 = monthBillString1.substring(0, 3).toUpperCase();
		totalElectricity1 = dataResp.substring(143, 155);
		penaltyFee1 = dataResp.substring(175, 188);
		prevMeter1 = dataResp.substring(188, 196);
		currMeter1 = dataResp.substring(196, 204);

		billPeriod.put("1", monthBillString1.concat(yearBill1));
		inqPostpaidResPLN.setAmount(new BigDecimal(totalElectricity1));
		inqPostpaidResPLN.setPenaltyFee(new BigDecimal(penaltyFee1));
		inqPostpaidResPLN.setPrevMeter(prevMeter1);
		inqPostpaidResPLN.setCurrMeter(currMeter1);
	    } else if (billStatusInteger == 2) {
		// bill period 1
		billPeriod1 = dataResp.substring(121, 127);
		yearBill1 = billPeriod1.substring(2, 4);
		monthBill1 = billPeriod1.substring(4, 6);
		monthBillString1 = new DateFormatSymbols().getMonths()[Integer.parseInt(monthBill1) - 1];
		monthBillString1 = monthBillString1.substring(0, 3).toUpperCase();
		totalElectricity1 = dataResp.substring(143, 155);
		penaltyFee1 = dataResp.substring(175, 188);
		prevMeter1 = dataResp.substring(188, 196);
		currMeter1 = dataResp.substring(196, 204);

		// bill period 2
		billPeriod2 = dataResp.substring(236, 242);
		yearBill2 = billPeriod2.substring(2, 4);
		monthBill2 = billPeriod2.substring(4, 6);
		monthBillString2 = new DateFormatSymbols().getMonths()[Integer.parseInt(monthBill2) - 1];
		monthBillString2 = monthBillString2.substring(0, 3).toUpperCase();
		totalElectricity2 = dataResp.substring(258, 270);
		penaltyFee2 = dataResp.substring(291, 303);
		prevMeter2 = dataResp.substring(303, 311);
		currMeter2 = dataResp.substring(311, 319);

		billPeriod.put("1", monthBillString1.concat(yearBill1));
		billPeriod.put("2", monthBillString2.concat(yearBill2));
		inqPostpaidResPLN.setAmount(new BigDecimal(totalElectricity1).add(new BigDecimal(totalElectricity2)));
		inqPostpaidResPLN.setPenaltyFee(new BigDecimal(penaltyFee1).add(new BigDecimal(penaltyFee2)));
		inqPostpaidResPLN.setPrevMeter(prevMeter1);
		inqPostpaidResPLN.setCurrMeter(currMeter2);
	    } else if (billStatusInteger == 3) {
		// bill period 1
		billPeriod1 = dataResp.substring(121, 127);
		yearBill1 = billPeriod1.substring(2, 4);
		monthBill1 = billPeriod1.substring(4, 6);
		monthBillString1 = new DateFormatSymbols().getMonths()[Integer.parseInt(monthBill1) - 1];
		monthBillString1 = monthBillString1.substring(0, 3).toUpperCase();
		totalElectricity1 = dataResp.substring(143, 155);
		penaltyFee1 = dataResp.substring(175, 188);
		prevMeter1 = dataResp.substring(188, 196);
		currMeter1 = dataResp.substring(196, 204);

		// bill period 2
		billPeriod2 = dataResp.substring(236, 242);
		yearBill2 = billPeriod2.substring(2, 4);
		monthBill2 = billPeriod2.substring(4, 6);
		monthBillString2 = new DateFormatSymbols().getMonths()[Integer.parseInt(monthBill2) - 1];
		monthBillString2 = monthBillString2.substring(0, 3).toUpperCase();
		totalElectricity2 = dataResp.substring(258, 270);
		penaltyFee2 = dataResp.substring(291, 303);
		prevMeter2 = dataResp.substring(303, 311);
		currMeter2 = dataResp.substring(311, 319);

		// bill period 3
		billPeriod3 = dataResp.substring(351, 357);
		yearBill3 = billPeriod3.substring(2, 4);
		monthBill3 = billPeriod3.substring(4, 6);
		monthBillString3 = new DateFormatSymbols().getMonths()[Integer.parseInt(monthBill3) - 1];
		monthBillString3 = monthBillString3.substring(0, 3).toUpperCase();
		totalElectricity3 = dataResp.substring(373, 385);
		penaltyFee3 = dataResp.substring(406, 418);
		prevMeter3 = dataResp.substring(418, 426);
		currMeter3 = dataResp.substring(426, 434);

		billPeriod.put("1", monthBillString1.concat(yearBill1));
		billPeriod.put("2", monthBillString2.concat(yearBill2));
		billPeriod.put("3", monthBillString3.concat(yearBill3));
		inqPostpaidResPLN.setAmount(new BigDecimal(totalElectricity1).add(new BigDecimal(totalElectricity2))
			.add(new BigDecimal(totalElectricity3)));
		inqPostpaidResPLN.setPenaltyFee(
			new BigDecimal(penaltyFee1).add(new BigDecimal(penaltyFee2)).add(new BigDecimal(penaltyFee3)));
		inqPostpaidResPLN.setPrevMeter(prevMeter1);
		inqPostpaidResPLN.setCurrMeter(currMeter3);
	    } else if (billStatusInteger == 4) {
		// // bill period 1
		billPeriod1 = dataResp.substring(121, 127);
		yearBill1 = billPeriod1.substring(2, 4);
		monthBill1 = billPeriod1.substring(4, 6);
		monthBillString1 = new DateFormatSymbols().getMonths()[Integer.parseInt(monthBill1) - 1];
		monthBillString1 = monthBillString1.substring(0, 3).toUpperCase();
		totalElectricity1 = dataResp.substring(143, 155);
		penaltyFee1 = dataResp.substring(175, 188);
		prevMeter1 = dataResp.substring(188, 196);
		currMeter1 = dataResp.substring(196, 204);

		// bill period 2
		billPeriod2 = dataResp.substring(236, 242);
		yearBill2 = billPeriod2.substring(2, 4);
		monthBill2 = billPeriod2.substring(4, 6);
		monthBillString2 = new DateFormatSymbols().getMonths()[Integer.parseInt(monthBill2) - 1];
		monthBillString2 = monthBillString2.substring(0, 3).toUpperCase();
		totalElectricity2 = dataResp.substring(258, 270);
		penaltyFee2 = dataResp.substring(291, 303);
		prevMeter2 = dataResp.substring(303, 311);
		currMeter2 = dataResp.substring(311, 319);

		// bill period 3
		billPeriod3 = dataResp.substring(351, 357);
		yearBill3 = billPeriod3.substring(2, 4);
		monthBill3 = billPeriod3.substring(4, 6);
		monthBillString3 = new DateFormatSymbols().getMonths()[Integer.parseInt(monthBill3) - 1];
		monthBillString3 = monthBillString3.substring(0, 3).toUpperCase();
		totalElectricity3 = dataResp.substring(373, 385);
		penaltyFee3 = dataResp.substring(406, 418);
		prevMeter3 = dataResp.substring(418, 426);
		currMeter3 = dataResp.substring(426, 434);

		// bill period 4
		billPeriod4 = dataResp.substring(466, 472);
		yearBill4 = billPeriod4.substring(2, 4);
		monthBill4 = billPeriod4.substring(4, 6);
		monthBillString4 = new DateFormatSymbols().getMonths()[Integer.parseInt(monthBill4) - 1];
		monthBillString4 = monthBillString4.substring(0, 3).toUpperCase();
		totalElectricity4 = dataResp.substring(488, 500);
		penaltyFee4 = dataResp.substring(521, 533);
		prevMeter4 = dataResp.substring(533, 541);
		currMeter4 = dataResp.substring(541, 549);

		billPeriod.put("1", monthBillString1.concat(yearBill1));
		billPeriod.put("2", monthBillString2.concat(yearBill2));
		billPeriod.put("3", monthBillString3.concat(yearBill3));
		billPeriod.put("4", monthBillString4.concat(yearBill4));
		inqPostpaidResPLN.setAmount(new BigDecimal(totalElectricity1).add(new BigDecimal(totalElectricity2))
			.add(new BigDecimal(totalElectricity3)).add(new BigDecimal(totalElectricity4)));
		inqPostpaidResPLN.setPenaltyFee(new BigDecimal(penaltyFee1).add(new BigDecimal(penaltyFee2))
			.add(new BigDecimal(penaltyFee3)).add(new BigDecimal(penaltyFee4)));
		inqPostpaidResPLN.setPrevMeter(prevMeter1);
		inqPostpaidResPLN.setCurrMeter(currMeter4);
	    }

	    inqPostpaidResPLN.setSubscriberID(req.getData().get("subscriberID"));
	    inqPostpaidResPLN.setSubscriberName(subsName.trim());
	    inqPostpaidResPLN.setBillStatus(billStatusInteger);
	    inqPostpaidResPLN.setBillPeriod(billPeriod);
	    inqPostpaidResPLN.setTarif(tarif.trim());
	    inqPostpaidResPLN.setDaya(new BigDecimal(daya).toString());
	    inqPostpaidResPLN.setAdminFee(new BigDecimal(adminFee));

	    BigDecimal total = inqPostpaidResPLN.getAmount()
		    .add(inqPostpaidResPLN.getAdminFee().add(inqPostpaidResPLN.getPenaltyFee()));
	    inqPostpaidResPLN.setTotalAmount(total);
	    
	    inqPostpaidResPLN.setElement11(resInquiryPostpaidPLNAranet.getRespayment().getResult().getElement11());
	    
	    StringBuilder sbElement48Req = new StringBuilder(dataResp);
	    sbElement48Req = sbElement48Req.insert(19, dataResp.substring(19, 20));
	    String element48ReqPayment = sbElement48Req.toString();
	    inqPostpaidResPLN.setElement48(element48ReqPayment);
	    
	    inqPostpaidResPLN.setElement61(dataResp.substring(22, 54));
	    
	    int remainBill = outStandBillInteger - billStatusInteger;
	    inqPostpaidResPLN.setRemainBill(remainBill);

	    response.setCode(ResponseMessage.SUCCESS.getCode());
	    response.setMessage(messageUtil.get("success", servletRequest.getLocale()));
	    response.setData(inqPostpaidResPLN);
	}
	return response;

    }
    
    /**
     * Payment PLN
     * 
     * @param request PLN Postpaid Request
     * @return Http Response 200 with data in body, else Error.
     * @throws IOException Will returns Connection Exception.
     */
    @PostMapping("/payment")
    public CommonResponse paymentPLN(@Valid @RequestBody CommonRequest<PLNPostpaidPaymentRequest> request) throws IOException {
	CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(),
		messageUtil.get("success", servletRequest.getLocale()));
	
	PLNPostpaidPaymentRequest dataRequest = request.getData();
	String username = dataRequest.getUsername();
	String accountNo = dataRequest.getAccountNo();
	
	if (!commonService.verifyLocalIp(servletRequest)) {
	    // Validate Token and Phone Owner
	    CommonRequest<VerifyPhoneOwnerRequest> phoneReq = new CommonRequest<>();
	    VerifyPhoneOwnerRequest phoneReqData = new VerifyPhoneOwnerRequest();
	    phoneReqData.setUsername(request.getData().getUsername());
	    phoneReqData.setToken(servletRequest.getHeader(HttpHeaders.AUTHORIZATION));
	    phoneReqData.setPhoneIdentity(servletRequest.getHeader(BkpmConstants.HTTP_HEADER_DEVICE_ID));
	    phoneReq.setData(phoneReqData);
	    CommonResponse resPhone = Services.create(UserModuleService.class).verifyPhoneOwner(phoneReq).execute()
		    .body();
	    if (!ResponseMessage.SUCCESS.getCode().equals(resPhone.getCode())) {
		log.error("Validate Token and Phone owner error..");
		return resPhone;
	    }
	}

	// Prepare verify PIN
	log.debug("verify PIN: "+username);
	GetVerifyPINRequest pinRequest = new GetVerifyPINRequest();
	pinRequest.setPin(dataRequest.getPinTransaction());
	pinRequest.setUsername(username);
	CommonRequest<GetVerifyPINRequest> commonPinRequest = new CommonRequest<>();
	commonPinRequest.setIdentity(request.getIdentity());
	commonPinRequest.setData(pinRequest);
	// Verify PIN
	CommonResponse pinResponse = Services.create(UserModuleService.class)
		.verifyPIN(commonPinRequest).execute().body();
	if(!ResponseMessage.SUCCESS.getCode().equals(pinResponse.getCode())) {
	    // response not success
	    return pinResponse;
	}
	
	ObjectMapper oMapper = new ObjectMapper();
	// Validate Account Number's Owner
	VerifyAccountOwnerRequest verifyAccountData = new VerifyAccountOwnerRequest(username, accountNo);
	log.debug("Verify account owner: {}", BkpmUtil.convertToJson(verifyAccountData));
	CommonRequest<VerifyAccountOwnerRequest> verifyAccountRequest = new CommonRequest<>();
	verifyAccountRequest.setIdentity(request.getIdentity());
	verifyAccountRequest.setData(verifyAccountData);
	CommonResponse accountResponse = Services.create(UserModuleService.class)
		.verifyAccountOwner(verifyAccountRequest).execute().body();
	if(!ResponseMessage.SUCCESS.getCode().equals(accountResponse.getCode())) {
	    log.error("Error verify account owner: "+accountNo);
	    // response not success
	    return accountResponse;
	}
	VerifyAccountOwnerResponse resAccount = oMapper.convertValue(accountResponse.getData(), VerifyAccountOwnerResponse.class);
	if(!resAccount.isValid()) {
	    log.error("User and Account Info didn't match: "+dataRequest.getAccountNo());
	    String message = messageUtil.get("error.invalid.account.owner", servletRequest.getLocale());
	    throw new DataNotMatchException(message);
	}
	User user = resAccount.getUser();
	String accType = resAccount.getAccountInfo().getAccountType().name();
	
	// Call postpaid tibco
	String forwardInsCode = env.getProperty("config.pln.forwarding-institution-code");
	PostpaidRequest reqTibco = PLNUtils.generatePaymentPostpaidRequest(dataRequest, forwardInsCode, accType);
	log.debug("request to tibco : " + BkpmUtil.convertToJson(reqTibco));
	// get tibco response
	PostpaidResponse tibcoResponse = Services.create(PLNService.class).postpaid(reqTibco).execute().body(); 
	log.debug("response from tibco : " + BkpmUtil.convertToJson(tibcoResponse));
	String codeRes = tibcoResponse.getRespayment().getResult().getElement39();
//	String postRes = tibcoResponse.getRespayment().getResult().getElement121().substring(0, 3);
	String monthName = new DateFormatSymbols().getMonths()[Calendar.getInstance().get(Calendar.MONTH)];
	if (PLN_CUT_OFF.equals(codeRes)) {
	    log.error("Cut Off PLN");
	    response.setCode(ResponseMessage.ERROR_CUT_OFF_PLN.getCode());
	    response.setMessage(messageUtil.get("error.cutoff.pln", servletRequest.getLocale()));
	} else if (UNREGISTERED_BILLER.equals(codeRes) || UNREGISTERED_SUBSCRIBER_NUMBER.equals(codeRes)) {
	    log.error("Unregistered biller or subscriber number");
	    response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
	    response.setMessage(messageUtil.get("error.id.pln.not.found", servletRequest.getLocale()));
	} else if (PRR_SUBSCRIBER.equals(codeRes) || SUBSCRIBER_SUSPENDED.equals(codeRes)) {
	    log.error("Subscriber was blocked");
	    response.setCode(ResponseMessage.PLN_SUBSCRIBER_SUSPENDED.getCode());
	    response.setMessage(messageUtil.get("error.user.pln.block",
		    new Object[] { dataRequest.getSubscriberNumber() }, servletRequest.getLocale()));
	} else if (PLN_ALREADY_PAID.equals(codeRes)) {
	    log.error("PLN already paid");
	    response.setCode(ResponseMessage.ERROR_BILL_ALREADY_PAID.getCode());
	    response.setMessage(messageUtil.get("error.pln.already.paid", servletRequest.getLocale()));
	} else if (PLN_BILL_NOT_AVAILABLE.equals(codeRes)) {
	    log.error("Current Bill not available");
	    response.setCode(ResponseMessage.ERROR_BILL_NOT_AVAILABLE.getCode());
	    response.setMessage(messageUtil.get("error.bill.not.available",
		    new Object[] { monthName }, servletRequest.getLocale()));
	} else if(PLN_NOT_ENOUGH_BALANCE.equals(codeRes)) {
	    log.error("Not enough balance: "+accountNo);
	    response.setCode(ResponseMessage.AMOUNT_NOT_ENOUGH.getCode());
	    response.setMessage(messageUtil.get("error.amount.not.enough", servletRequest.getLocale()));
	} else if(PLN_INACTIVE_ACCOUNT.equals(codeRes)) {
	    log.error("Account inactive: "+accountNo);
	    response.setCode(ResponseMessage.ERROR_INACTIVE_BANK_ACCOUNT.getCode());
	    response.setMessage(messageUtil.get("error.inactive.bank.account", servletRequest.getLocale()));
	} else if(!SUCCESS_CODE.equals(codeRes)) {
	    log.error("Error (result) PLN Postpaid: "+codeRes);
	    // Throw middleware error
	    throw new MiddlewareException(codeRes);
	} else {
	    // if tibco returns success
	    // generate success response
	    PLNPostpaidPaymentResponse data = PLNUtils
		    .generatePaymentPostpaidResponse(tibcoResponse.getRespayment().getResult(), dataRequest);

	    // Send receipt asynchronously
	    plnService.sendEmailReceiptPLN(dataRequest, data, user);

	    // Save data
	    Transaction trx = saveTransaction(request.getIdentity(), data, username);
	    log.debug("after save transaction");
	    // Set destination id in response
	    data.setDestinationId(trx.getDestination().getId());
	    response.setData(data);
	}
	
	return response;
    }
    
    /**
     * Save Transaction Data
     * 
     * @param identity The Identity object request
     * @param data The postpaid data to save
     * @param username The User's username
     * @return The Transaction object data include destination ID will send as response to frontend.
     * @throws IOException The Connection exception.
     */
    private Transaction saveTransaction(Identity identity, PLNPostpaidPaymentResponse data, String username) 
	    throws IOException {
	// Prepare data request to save transaction
	DestinationCommonRequest dataReq = new DestinationCommonRequest();
	dataReq.setCategoryId(CategoryEnum.LISTRIK.getId());
	dataReq.setUsername(username);
	dataReq.setSubscriberNumber(data.getSubscriberNumber());
	dataReq.setSubscriberName(data.getSubscriberName());
	dataReq.setDestinationType(DestinationTypeEnum.POSTLISTRIK.name());
	dataReq.setTransactionType(TransactionTypeEnum.PLNPOST.name());
	dataReq.setReference(data.getReferenceNumber());
	dataReq.setAccountNumber(data.getAccountNo());
	dataReq.setTotalAmount(data.getTotalAmount());
	
	// Call service to save Destination/ Favorite
	CommonRequest<DestinationCommonRequest> destinationReq = new CommonRequest<>();
	destinationReq.setIdentity(identity);
	destinationReq.setData(dataReq);
	
	log.debug("Call saveToFavouriteCommon: {}", BkpmUtil.convertToJson(destinationReq));
	CommonResponse res = Services.create(MasterModuleService.class).saveToFavouriteCommon(destinationReq).execute().body();
	log.debug(BkpmUtil.convertToJson(res));
	ObjectMapper oMapper = new ObjectMapper();
	Transaction transaction = new Transaction();
	if(ResponseMessage.SUCCESS.getCode().equals(res.getCode())) {
	    transaction = oMapper.convertValue(res.getData(), Transaction.class);
	    // Save postpaid
	    PLNPostpaid postpaid = new PLNPostpaid();
	    postpaid.setAccountNumber(data.getAccountNo());
	    postpaid.setAmount(data.getAmount());
	    postpaid.setAdminFee(data.getAdminFee());
	    postpaid.setPenaltyFee(data.getPenaltyFee());
	    postpaid.setTarif(data.getTarif());
	    postpaid.setDaya(data.getDaya());
	    postpaid.setMeterStart(new BigInteger(data.getPrevMeter()));
	    postpaid.setMeterEnd(new BigInteger(data.getCurrMeter()));
	    // bulan format: JAN19|FEB19|MAR19
	    String bulan = data.getBillPeriod().values().stream().map(
		    Object::toString).collect(Collectors.joining("|"));
	    postpaid.setBulan(bulan);
	    postpaid.setTotalBill(data.getBillPeriod().size());
	    postpaid.setTotalAmount(data.getTotalAmount());
	    postpaid.setTransaction(transaction);
	    
	    postpaidService.savePLNPostpaid(postpaid);
	} else {
	    // TODO handle error save transaction
	    log.error("error wile saving transaction...");
	}
	
	return transaction;
    }

    /* Overrides: */
}
