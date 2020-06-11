/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.payment.web.rest;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.xml.datatype.DatatypeConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import id.co.asyst.bukopin.mobile.common.core.exception.MiddlewareException;
import id.co.asyst.bukopin.mobile.common.core.util.BkpmUtil;
import id.co.asyst.bukopin.mobile.common.core.util.MessageUtil;
import id.co.asyst.bukopin.mobile.common.model.ResponseMessage;
import id.co.asyst.bukopin.mobile.common.model.payload.CommonRequest;
import id.co.asyst.bukopin.mobile.common.model.payload.CommonResponse;
import id.co.asyst.bukopin.mobile.common.model.payload.Identity;
import id.co.asyst.bukopin.mobile.payment.core.service.InsuranceService;
import id.co.asyst.bukopin.mobile.payment.core.service.InsuranceTransService;
import id.co.asyst.bukopin.mobile.payment.core.util.InsuranceUtils;
import id.co.asyst.bukopin.mobile.payment.model.entity.Insurance;
import id.co.asyst.bukopin.mobile.payment.model.payload.InquiryInsuranceRequest;
import id.co.asyst.bukopin.mobile.payment.model.payload.InquiryInsuranceResponse;
import id.co.asyst.bukopin.mobile.payment.model.payload.InstitutionMapper;
import id.co.asyst.bukopin.mobile.payment.model.payload.PaymentInsuranceRequest;
import id.co.asyst.bukopin.mobile.payment.model.payload.PaymentInsuranceResponse;
import id.co.asyst.bukopin.mobile.service.core.InsuranceModuleService;
import id.co.asyst.bukopin.mobile.service.core.MasterModuleService;
import id.co.asyst.bukopin.mobile.service.core.UserModuleService;
import id.co.asyst.bukopin.mobile.service.model.payload.insurance.InsuranceInquiryRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.insurance.InsuranceInquiryResponse;
import id.co.asyst.bukopin.mobile.service.model.payload.insurance.InsurancePurchaseRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.insurance.InsurancePurchaseResponse;
import id.co.asyst.bukopin.mobile.service.model.payload.pln.GetVerifyPINRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.telco.prepaid.PrepaidTelcoPurchaseRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.telco.prepaid.PrepaidTelcoPurchaseResponse;

import id.co.asyst.bukopin.mobile.master.model.CategoryEnum;
import id.co.asyst.bukopin.mobile.master.model.DestinationTypeEnum;
import id.co.asyst.bukopin.mobile.master.model.TransactionTypeEnum;
import id.co.asyst.bukopin.mobile.master.model.entity.Transaction;
import id.co.asyst.bukopin.mobile.master.model.payload.DestinationCommonRequest;
import id.co.asyst.bukopin.mobile.user.model.payload.VerifyAccountOwnerRequest;
import id.co.asyst.bukopin.mobile.user.model.payload.VerifyAccountOwnerResponse;
import id.co.asyst.foundation.service.connector.Services;

/**
 * REST Controller for Configuration
 * 
 * @author Kartika Dwi H
 * @version $Revision$, Mar 16, 2020
 * @since 1.1.Alpha1
 */
@RestController
@RequestMapping("/insurance")
public class InsuranceController {
    /* Constants: */
    private final Logger log = LoggerFactory.getLogger(InsuranceController.class);
    private final String PREPAID_TYPE = "PRE";
    
    private static final String SUCCESS_CODE = "000";
    private static final String ARANET_TIME_OUT_CODE = "999";
    private static final String INVALID_AMOUNT = "113";
    private static final String SUBSCRIBERNUMBER_NOT_FOUND = "114";
    private static final String INVALID_MAX_LIMIT_ACC = "161";
    private static final String INVALID_EXCEED = "165";
    private static final String ACCOUNT_WAS_BLOCKED = "170";
    private static final String DATA_NOT_FOUND = "180";
    private static final String SUBSCRIBER_NUMBER_EXPIRED = "181";
    private static final String INVALID_INTERVAL = "183";
    private static final String BILL_NOT_AVAILABLE= "187";
    private static final String BILL_ALREADY_PAID= "188";
    private static final String AMOUNT_NOT_ENOUGH_BALANCE = "851";
       
   
    private static final String SYSTEM_BROKEN_189 = "189";
    private static final String SYSTEM_BROKEN_196 = "196";
    private static final String PASSIVE_ACCOUNT = "839";
    
    //giro error handling
    private static final String GIRO_AMOUNT_NOT_ENOUGH_BALANCE = "805";
    private static final String GIRO_LIMIT_TRANSFER = "802";
    private static final String GIRO_ACCOUNT_WAS_BLOCKED = "806";
    private static final String GIRO_OVER_LIMIT = "808";
    private static final String GIRO_ACCOUNT_BLOCKED= "814";
    private static final String GIRO_CUT_OFF= "818";
    private static final String GIRO_INACTIVE_ACCOUNT= "822";
    private static final String GIRO_USER_NOT_FOUND= "831";
    private static final String GIRO_DUPLICATE_DATA= "869";
    private static final String GIRO_CLOSED_ACCOUNT= "878";
    private static final String GIRO_ERROR_VALUTA_CODE= "885";
    private static final String GIRO_LIMITED_BALANCE= "897";
    private DateFormat paymentTelkomselDate = new SimpleDateFormat("dd MMM yyyy");
    
    
    
    /* Attributes: */
    
    
    /**
     * insurance service
     */
    @Autowired
    InsuranceService insuranceService;
    
    @Autowired
    InsuranceTransService insuranceTransService;
    
    
    /**
     * Get Message Util
     */
    @Autowired
    private MessageUtil messageUtil;
    
    /**
     * Http Servlet Request
     */
    @Autowired
    private HttpServletRequest httpServletRequest;
    
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
    private CommonResponse inquiryInsurance(@Valid @RequestBody CommonRequest<InquiryInsuranceRequest> req)
	    throws IOException {
	log.debug("REST request to inquiry Insurance : {}", req.getData());
	CommonResponse response = new CommonResponse();

	String forwardInsCode = env.getProperty("config.pln.forwarding-institution-code");
	
	//-- find provider
	CommonRequest<InstitutionMapper> ins = new CommonRequest<InstitutionMapper>();
	CommonResponse institutionRes = Services.create(MasterModuleService.class)
			.findCodeByProvider(req.getData().getCodeIns()).execute().body();
	ObjectMapper mapper = new ObjectMapper();	
	InstitutionMapper institution = mapper.convertValue(institutionRes.getData(), InstitutionMapper.class);
	//log.debug("institutionRes: "+institution.toString());
	
	
	//-- generate request tibco
	InsuranceInquiryRequest reqInquiryInsuraneTibco = InsuranceUtils.generateInquiryInsuranceReq(
			req.getData().getCodeIns(), req.getData().getMonth(), req.getData().getSubscriberNumber(), 
			forwardInsCode, institution, httpServletRequest.getLocale().getLanguage());
		
	
	log.debug("request to TIBCO : "+BkpmUtil.convertToJson(reqInquiryInsuraneTibco));
	InsuranceInquiryResponse resInquiryInsuranceTibco = Services.create(InsuranceModuleService.class)
		.inquiryInsurance(reqInquiryInsuraneTibco).execute().body();

	log.debug("response from TIBCO : "+ BkpmUtil.convertToJson(resInquiryInsuranceTibco));
	
	
	String codeRes = resInquiryInsuranceTibco.getRespayment().getResult().getElement39();
	if (INVALID_AMOUNT.equals(codeRes)) {
	    log.error("insurance Invalid Amount");
	    response.setCode(ResponseMessage.INVALID_AMOUNT.getCode());
	    response.setMessage(messageUtil.get("error.invalid.amount", httpServletRequest.getLocale()));
	} else if (SUBSCRIBERNUMBER_NOT_FOUND.equalsIgnoreCase(codeRes) 
			|| GIRO_USER_NOT_FOUND.equalsIgnoreCase(codeRes)) {
	    log.error("SUbscriber not found/invalid");
	    response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
	    response.setMessage(messageUtil.get("error.subs.number.not.found", httpServletRequest.getLocale()));
	}  else if (BILL_ALREADY_PAID.equalsIgnoreCase(codeRes) ) {
	    log.error("Bill already Paid");
	    response.setCode(ResponseMessage.ERROR_BILL_ALREADY_PAID.getCode());
	    response.setMessage(messageUtil.get("error.pln.already.paid", httpServletRequest.getLocale()));
	} else if (INVALID_MAX_LIMIT_ACC.equalsIgnoreCase(codeRes) ) {
	    log.error("invalid maximum limit account");
	    response.setCode(ResponseMessage.INVALID_MAX_LIMIT_AMOUNT.getCode()); //cek lg ntr message nya
	    response.setMessage(messageUtil.get("error.invalid.max.limit.amount", httpServletRequest.getLocale()));
	} else if (INVALID_EXCEED.equals(codeRes)) {
	    log.error("invalid exceed");
	    response.setCode(ResponseMessage.ERROR_VOUCHER_OUT_OF_STOCK.getCode());
	    response.setMessage(messageUtil.get("error.invalid.exceed", httpServletRequest.getLocale()));
	} else if (ACCOUNT_WAS_BLOCKED.equals(codeRes)
			|| GIRO_ACCOUNT_WAS_BLOCKED.equals(codeRes)
			|| GIRO_ACCOUNT_BLOCKED.equals(codeRes)) {
	    log.error("account wass blocked");
	    response.setCode(ResponseMessage.CUST_BLOCKED.getCode());
	    response.setMessage(messageUtil.get("error.account.blocked", httpServletRequest.getLocale()));
	} else if (DATA_NOT_FOUND.equals(codeRes) 
			|| GIRO_USER_NOT_FOUND.equals(codeRes)) {
	    log.error("data not found");
	    response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
	    response.setMessage(messageUtil.get("error.subs.number.not.found", httpServletRequest.getLocale()));
	} else if (SUBSCRIBER_NUMBER_EXPIRED.equals(codeRes)) {
	    log.error("data Exipred");
	    response.setCode(ResponseMessage.PHONE_NUMBER_EXPIRED.getCode());
	    response.setMessage(messageUtil.get("error.subcriber.expired", httpServletRequest.getLocale()));
	} else if (AMOUNT_NOT_ENOUGH_BALANCE.equals(codeRes) 
			|| GIRO_AMOUNT_NOT_ENOUGH_BALANCE.equals(codeRes)
			|| GIRO_ERROR_VALUTA_CODE.equals(codeRes)
			|| GIRO_LIMITED_BALANCE.equals(codeRes)) {
	    log.error("not enough balance");
	    response.setCode(ResponseMessage.AMOUNT_NOT_ENOUGH.getCode());
	    response.setMessage(messageUtil.get("error.amount.not.enough", httpServletRequest.getLocale()));
	} else if (PASSIVE_ACCOUNT.equals(codeRes)
			|| GIRO_INACTIVE_ACCOUNT.equals(codeRes)
			|| GIRO_CLOSED_ACCOUNT.equalsIgnoreCase(codeRes)) {
	    log.error("passive account");
	    response.setCode(ResponseMessage.ERROR_INACTIVE_BANK_ACCOUNT.getCode());
	    response.setMessage(messageUtil.get("error.inactive.bank.account", httpServletRequest.getLocale()));
	} else if (INVALID_INTERVAL.equals(codeRes) ) {
	    log.error("invalid interval");
	    response.setCode(ResponseMessage.ERROR_INVALID_INTERVAL.getCode());
	    response.setMessage(messageUtil.get("error.invalid.interval", httpServletRequest.getLocale()));
	} else if (GIRO_LIMIT_TRANSFER.equals(codeRes) 
			|| GIRO_OVER_LIMIT.equals(codeRes)) {
	    log.error("exceed limit");
	    response = new CommonResponse();
	    response.setCode(ResponseMessage.LIMIT_TRANSFER_DAY.getCode());
	    response.setMessage(messageUtil.get("error.exceed.limit", httpServletRequest.getLocale()));
	} else if (GIRO_CUT_OFF.equals(codeRes)) {
	    log.error("Giro cut off");
	    response = new CommonResponse();
	    response.setCode(ResponseMessage.ERROR_CUT_OFF_PLN.getCode());
	    response.setMessage(messageUtil.get("error.cutoff.pln", httpServletRequest.getLocale()));
	} else if (GIRO_DUPLICATE_DATA.equals(codeRes)) {
	    log.error("Giro Duplicate Data");
	    response = new CommonResponse();
	    response.setCode(ResponseMessage.DUPLICATE_DATA.getCode());
	    response.setMessage(messageUtil.get("error.duplicate.data", httpServletRequest.getLocale()));
	} else	if (!SUCCESS_CODE.equals(codeRes)) {
		log.error("Error (result) Insurance inquiry : "+codeRes);
	    // Throw middleware error
	    throw new MiddlewareException(codeRes);
	} else {
	    log.debug("Inquiry Insurance success");

	    String element48Res = resInquiryInsuranceTibco.getRespayment().getResult().getElement48();
	    String subsName =element48Res.substring(56, 86).trim();
	    String SubNo = element48Res.substring(5,29).trim();
	    String participant = element48Res.substring(166,176).trim();
	    if(participant.length()==1) {
	    	participant="0".concat(participant);
	    }
	    
	    String month = element48Res.substring(30,32);
	    BigDecimal amount = new BigDecimal(element48Res.substring(32,44));
	    BigDecimal prepaidInsurance = new BigDecimal(element48Res.substring(284,296));
	    BigDecimal adminFee = new BigDecimal(element48Res.substring(44, 56));	    
	    BigDecimal totalAmount= amount.add(prepaidInsurance).add(adminFee);
	    
	    // mapping response
	    InquiryInsuranceResponse inqInsuranceRes= new InquiryInsuranceResponse();
	    inqInsuranceRes.setAdminFee(adminFee);
	    inqInsuranceRes.setAmount(amount);
	    inqInsuranceRes.setElement11(resInquiryInsuranceTibco.getRespayment().getResult().getElement11());
	    inqInsuranceRes.setElement120(resInquiryInsuranceTibco.getRespayment().getResult().getElement120());
	    inqInsuranceRes.setElement37(resInquiryInsuranceTibco.getRespayment().getResult().getElement37());
	    inqInsuranceRes.setElement4(resInquiryInsuranceTibco.getRespayment().getResult().getElement4());
	    inqInsuranceRes.setElement48(resInquiryInsuranceTibco.getRespayment().getResult().getElement48());
	    inqInsuranceRes.setElement63(resInquiryInsuranceTibco.getRespayment().getResult().getElement63());
	    inqInsuranceRes.setMonth(Integer.valueOf(month));
	    inqInsuranceRes.setParticipant(participant);
	    inqInsuranceRes.setPrepaidInsurance(prepaidInsurance);
	    inqInsuranceRes.setSubscriberName(subsName);
	    inqInsuranceRes.setSubscriberNumber(SubNo);
	    inqInsuranceRes.setTotalAmount(totalAmount);
	    
	    
	    response.setCode(ResponseMessage.SUCCESS.getCode());
	    response.setMessage(messageUtil.get("success", httpServletRequest.getLocale()));
	    response.setData(inqInsuranceRes);
	}
	
	
	return response;

    }
	

	
	
	@SuppressWarnings("unchecked")
	@PostMapping("/payment")
    private CommonResponse purchaseInsurance(@Valid @RequestBody CommonRequest<PaymentInsuranceRequest> req) throws IOException, MessagingException {
	CommonResponse response = new CommonResponse();
	
	ObjectMapper oMapper = new ObjectMapper();
	//-- Validate Account Number's Owner
	VerifyAccountOwnerRequest verifyAccountData = new VerifyAccountOwnerRequest(req.getData().getUsername(), req.getData().getAccountNumber());
	log.debug("Verify account owner: {}", BkpmUtil.convertToJson(verifyAccountData));
	
	CommonRequest<VerifyAccountOwnerRequest> verifyAccountRequest = new CommonRequest<>();
	verifyAccountRequest.setIdentity(req.getIdentity());
	verifyAccountRequest.setData(verifyAccountData);
	CommonResponse accountResponse = Services.create(UserModuleService.class)
		.verifyAccountOwner(verifyAccountRequest).execute().body();
	if(!ResponseMessage.SUCCESS.getCode().equals(accountResponse.getCode())) {
	    log.error("Error verify account owner: "+req.getData().getAccountNumber());
	    // response not success
	    return accountResponse;
	}
	VerifyAccountOwnerResponse resAccount = oMapper.convertValue(accountResponse.getData(), VerifyAccountOwnerResponse.class);
	if(!resAccount.isValid()) {
	    log.error("User and Account Info didn't match: "+req.getData().getAccountNumber());
	    response.setCode(ResponseMessage.DATA_NOT_MATCH.getCode());
	    response.setMessage(messageUtil.get("error.invalid.user.accountno", httpServletRequest.getLocale()));
	    return response;
	}
	
	
	//User user = resAccount.getUser();
	
	//-- Prepare verify PIN
	log.debug("verify PIN: "+req.getData().getUsername());
	GetVerifyPINRequest pinRequest = new GetVerifyPINRequest();
	pinRequest.setPin(req.getData().getPin());
	pinRequest.setUsername(req.getData().getUsername());
	CommonRequest<GetVerifyPINRequest> commonPinRequest = new CommonRequest<>();
	commonPinRequest.setIdentity(req.getIdentity());
	commonPinRequest.setData(pinRequest);
	// Verify PIN
	CommonResponse pinResponse = Services.create(UserModuleService.class)
		.verifyPIN(commonPinRequest).execute().body();
	if(!ResponseMessage.SUCCESS.getCode().equals(pinResponse.getCode())) {
	    // response not success
	    return pinResponse;
	}

	String forwardInsCode = env.getProperty("config.pln.forwarding-institution-code");
	Identity identity = InsuranceUtils.generateIdentity();
	String accType = resAccount.getAccountInfo().getAccountType().name();
		
	//generate purchase request
	InsurancePurchaseRequest reqPurchaseInsuranceTibco = InsuranceUtils.generatePurchaseInsuranceReq(req.getData(),  
			forwardInsCode, accType);
			
	log.debug("request to tibco : " + BkpmUtil.convertToJson(reqPurchaseInsuranceTibco));
	
	InsurancePurchaseResponse resPurchaseInsuranceTibco = new InsurancePurchaseResponse();
	resPurchaseInsuranceTibco = Services.create(InsuranceModuleService.class)
	    	.purchaseInsurance(reqPurchaseInsuranceTibco).execute().body();
	log.debug("response from tibco: " + BkpmUtil.convertToJson(resPurchaseInsuranceTibco));
	
	
	String codeRes = resPurchaseInsuranceTibco.getRespayment().getResult().getElement39();
	//String postRes = resPurchaseEMoneyAranet.getRespayment().getResult().getElement121().substring(0, 3);
	
	
	PaymentInsuranceResponse purchaseInsuranceRes = new PaymentInsuranceResponse();
	if(SUCCESS_CODE.equals(codeRes)) {
	    log.info("Purchase Insurance Success");
	    
	    String el48Res = resPurchaseInsuranceTibco.getRespayment().getResult().getElement48();
	    String element122 = resPurchaseInsuranceTibco.getRespayment().getResult().getElement122();
	    
	    //-- date time
	    StringBuilder time = new StringBuilder(resPurchaseInsuranceTibco.getRespayment().getResult().getElement12().substring(0,4));
	    time.insert(2, ":");
	    
	    String date = paymentTelkomselDate.format(new Date());
	    purchaseInsuranceRes.setDateTime(date.concat(" - ").concat(time.toString()));
	    
	    //purchaseInsuranceRes.setReferenceNumber(resPurchaseInsuranceTibco.getRespayment().getResult().getElement37());
	    purchaseInsuranceRes.setReferenceNumber(element122.substring(144,159).trim());
	    purchaseInsuranceRes.setAccountNumber(resPurchaseInsuranceTibco.getRespayment().getResult().getElement102());
	    purchaseInsuranceRes.setSubscriberNumber(el48Res.substring(5,29).trim());
	    purchaseInsuranceRes.setSubscriberName(el48Res.substring(56,86).trim());
	    
	    String participant = el48Res.substring(166,176).trim();
	    if(participant.length()==1) {
	    	participant="0".concat(participant);
	    }
	    purchaseInsuranceRes.setParticipant(participant);
	    purchaseInsuranceRes.setMonth(Integer.valueOf(el48Res.substring(30,32)));
	    
	    BigDecimal amount =new BigDecimal(el48Res.substring(32,44));
	    BigDecimal adminFee = new BigDecimal(el48Res.substring(44,56));
	    BigDecimal prepaidInsurance = new BigDecimal(el48Res.substring(284,296));
	    purchaseInsuranceRes.setAmount(amount);
	    purchaseInsuranceRes.setAdminFee(adminFee);
	    purchaseInsuranceRes.setPrepaidInsurance(prepaidInsurance);
	    purchaseInsuranceRes.setTotalAmount(amount.add(adminFee).add(prepaidInsurance));
	    purchaseInsuranceRes.setUsername(req.getData().getUsername());
	    purchaseInsuranceRes.setCodeIns("POST"+req.getData().getCodeIns());
	    
	    
	    response.setCode(ResponseMessage.SUCCESS.getCode());
	    response.setMessage(messageUtil.get("success", httpServletRequest.getLocale()));
	    response.setData(purchaseInsuranceRes);
	    
	    
	  //send email receipt and save to favorite	
		CommonResponse getUser = Services.create(UserModuleService.class).getUserByUsername(req.getData().getUsername())
				.execute().body();
		if(null!=getUser && (SUCCESS_CODE.equals(codeRes))) {
		    log.debug("Send Email Insurance.. ");
		    log.debug("USER:...... "+getUser.toString());
		    
		    oMapper = new ObjectMapper();
		    Map<String, Object> res = oMapper.convertValue(getUser.getData(), Map.class);
		    Map<String, String> resUser = oMapper.convertValue(res.get("user"), Map.class);
		    log.debug("email : "+resUser.get("email"));
		    insuranceService.sendEmailReceiptInsurance(purchaseInsuranceRes, resUser, httpServletRequest.getLocale());
		    //log.debug("responseeeee "+response);
		    
		   // if(SUCCESS_CODE.equals(response.getCode())){
			//response.setData(purchaseEMoneyRes);
		   // }
		    
		    // save prepaid telco
		    CommonResponse saveRes = savePurchaseInsurancePrepaid(identity,purchaseInsuranceRes);
		    //log.debug("save RES EMoney.... "+saveRes.toString());
		    if(SUCCESS_CODE.equals(saveRes.getCode())) {
		    	//log.debug("ID DESTINATION .... "+saveRes.getData().toString());
		    	purchaseInsuranceRes.setIdDestination(Long.parseLong(saveRes.getData().toString()));
			response.setData(purchaseInsuranceRes);
		    }
		} else {
			log.error("SUbscriber not found/invalid");
		    response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
		    response.setMessage(messageUtil.get("error.subs.number.not.found", httpServletRequest.getLocale()));
		}
		
	} else if (INVALID_AMOUNT.equals(codeRes)) {
	    log.error("insurance Invalid Amount");
	    response.setCode(ResponseMessage.INVALID_AMOUNT.getCode());
	    response.setMessage(messageUtil.get("error.invalid.amount", httpServletRequest.getLocale()));
	} else if (SUBSCRIBERNUMBER_NOT_FOUND.equalsIgnoreCase(codeRes) 
			|| GIRO_USER_NOT_FOUND.equalsIgnoreCase(codeRes)) {
	    log.error("SUbscriber not found/invalid");
	    response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
	    response.setMessage(messageUtil.get("error.subs.number.not.found", httpServletRequest.getLocale()));
	}  else if (BILL_ALREADY_PAID.equalsIgnoreCase(codeRes) ) {
	    log.error("Bill already Paid");
	    response.setCode(ResponseMessage.ERROR_BILL_ALREADY_PAID.getCode());
	    response.setMessage(messageUtil.get("error.pln.already.paid", httpServletRequest.getLocale()));
	} else if (INVALID_MAX_LIMIT_ACC.equalsIgnoreCase(codeRes) ) {
	    log.error("invalid maximum limit account");
	    response.setCode(ResponseMessage.INVALID_MAX_LIMIT_AMOUNT.getCode()); //cek lg ntr message nya
	    response.setMessage(messageUtil.get("error.invalid.max.limit.amount", httpServletRequest.getLocale()));
	} else if (INVALID_EXCEED.equals(codeRes)) {
	    log.error("invalid exceed");
	    response.setCode(ResponseMessage.ERROR_VOUCHER_OUT_OF_STOCK.getCode());
	    response.setMessage(messageUtil.get("error.invalid.exceed", httpServletRequest.getLocale()));
	} else if (ACCOUNT_WAS_BLOCKED.equals(codeRes)
			|| GIRO_ACCOUNT_WAS_BLOCKED.equals(codeRes)
			|| GIRO_ACCOUNT_BLOCKED.equals(codeRes)) {
	    log.error("account wass blocked");
	    response.setCode(ResponseMessage.CUST_BLOCKED.getCode());
	    response.setMessage(messageUtil.get("error.account.blocked", httpServletRequest.getLocale()));
	} else if (DATA_NOT_FOUND.equals(codeRes) 
			|| GIRO_USER_NOT_FOUND.equals(codeRes)) {
	    log.error("data not found");
	    response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
	    response.setMessage(messageUtil.get("error.subs.number.not.found", httpServletRequest.getLocale()));
	} else if (SUBSCRIBER_NUMBER_EXPIRED.equals(codeRes)) {
	    log.error("data Exipred");
	    response.setCode(ResponseMessage.PHONE_NUMBER_EXPIRED.getCode());
	    response.setMessage(messageUtil.get("error.subcriber.expired", httpServletRequest.getLocale()));
	} else if (AMOUNT_NOT_ENOUGH_BALANCE.equals(codeRes) 
			|| GIRO_AMOUNT_NOT_ENOUGH_BALANCE.equals(codeRes)
			|| GIRO_ERROR_VALUTA_CODE.equals(codeRes)
			|| GIRO_LIMITED_BALANCE.equals(codeRes)) {
	    log.error("not enough balance");
	    response.setCode(ResponseMessage.AMOUNT_NOT_ENOUGH.getCode());
	    response.setMessage(messageUtil.get("error.amount.not.enough", httpServletRequest.getLocale()));
	} else if (PASSIVE_ACCOUNT.equals(codeRes)
			|| GIRO_INACTIVE_ACCOUNT.equals(codeRes)
			|| GIRO_CLOSED_ACCOUNT.equalsIgnoreCase(codeRes)) {
	    log.error("passive account");
	    response.setCode(ResponseMessage.ERROR_INACTIVE_BANK_ACCOUNT.getCode());
	    response.setMessage(messageUtil.get("error.inactive.bank.account", httpServletRequest.getLocale()));
	} else if (INVALID_INTERVAL.equals(codeRes) ) {
	    log.error("invalid interval");
	    response.setCode(ResponseMessage.ERROR_INVALID_INTERVAL.getCode());
	    response.setMessage(messageUtil.get("error.invalid.interval", httpServletRequest.getLocale()));
	} else if (GIRO_LIMIT_TRANSFER.equals(codeRes) 
			|| GIRO_OVER_LIMIT.equals(codeRes)) {
	    log.error("exceed limit");
	    response = new CommonResponse();
	    response.setCode(ResponseMessage.LIMIT_TRANSFER_DAY.getCode());
	    response.setMessage(messageUtil.get("error.exceed.limit", httpServletRequest.getLocale()));
	} else if (GIRO_CUT_OFF.equals(codeRes)) {
	    log.error("Giro cut off");
	    response = new CommonResponse();
	    response.setCode(ResponseMessage.ERROR_CUT_OFF_PLN.getCode());
	    response.setMessage(messageUtil.get("error.cutoff.pln", httpServletRequest.getLocale()));
	} else if (GIRO_DUPLICATE_DATA.equals(codeRes)) {
	    log.error("Giro Duplicate Data");
	    response = new CommonResponse();
	    response.setCode(ResponseMessage.DUPLICATE_DATA.getCode());
	    response.setMessage(messageUtil.get("error.duplicate.data", httpServletRequest.getLocale()));
	} else if (AMOUNT_NOT_ENOUGH_BALANCE.equals(codeRes)
			|| GIRO_AMOUNT_NOT_ENOUGH_BALANCE.equals(codeRes)
			|| GIRO_ERROR_VALUTA_CODE.equals(codeRes)
			|| GIRO_LIMITED_BALANCE.equals(codeRes)) {		
	    log.error("not enough balance");
	    response = new CommonResponse();
	    response.setCode(ResponseMessage.AMOUNT_NOT_ENOUGH.getCode());
	    response.setMessage(messageUtil.get("error.amount.not.enough", httpServletRequest.getLocale()));
	} else {
		log.error("Error (result) Insurance payment : "+codeRes);
	    // Throw middleware error
	    throw new MiddlewareException(codeRes);
	}
	
	return response;
    }
	

	public CommonResponse savePurchaseInsurancePrepaid(Identity identity, PaymentInsuranceResponse resInsurance) {
		CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(), 
			messageUtil.get("success", httpServletRequest.getLocale()));
		
		try {
		    // save to Destination and Transaction
		    
		    // Prepare data request to save transaction
			DestinationCommonRequest dataReq = new DestinationCommonRequest();
			dataReq.setCategoryId(CategoryEnum.ASURANSI.getId());
			dataReq.setUsername(resInsurance.getUsername());
			dataReq.setSubscriberNumber(resInsurance.getSubscriberNumber());
			dataReq.setSubscriberName(resInsurance.getSubscriberName());
			dataReq.setTransactionType(TransactionTypeEnum.INSURANCE.name());
			dataReq.setReference(resInsurance.getReferenceNumber());
			dataReq.setAccountNumber(resInsurance.getAccountNumber());
			dataReq.setTotalAmount(resInsurance.getTotalAmount());
			
			// destination type by provider
		    if (DestinationTypeEnum.POSTBPJSKES.name().equalsIgnoreCase(resInsurance.getCodeIns())) {
			dataReq.setDestinationType(DestinationTypeEnum.POSTBPJSKES.name());
		    } else if (DestinationTypeEnum.POSTINHEALTH.name().equalsIgnoreCase(resInsurance.getCodeIns())) {
			dataReq.setDestinationType(DestinationTypeEnum.POSTINHEALTH.name());
		    } 
			
			// Call service to save Destination/ Favorite
			CommonRequest<DestinationCommonRequest> destinationReq = new CommonRequest<>();
			destinationReq.setIdentity(identity);
			destinationReq.setData(dataReq);
			
		    
		    
		    CommonResponse resSaveFav = Services.create(MasterModuleService.class).saveToFavouriteCommon(destinationReq).execute().body();
		   //log.debug("res SAVE FAV... "+resSaveFav.toString());
		    if (null != resSaveFav) {
			if (!SUCCESS_CODE.equals(resSaveFav.getCode())) {
			    log.error("Save to favourite Failed");
			    response.setCode(ResponseMessage.INTERNAL_SERVER_ERROR.getCode());
			    response.setMessage(messageUtil.get("error.internal.server", httpServletRequest.getLocale()));
			} else {
			    ObjectMapper oMapper = new ObjectMapper();
			    Transaction transaction = oMapper.convertValue(resSaveFav.getData(), Transaction.class);
			   
			    
			    log.debug("create");
			    Insurance insurance = new Insurance();
			    insurance.setAdminFee(resInsurance.getAdminFee());
			    insurance.setAmount(resInsurance.getAmount());
			    insurance.setMonth(resInsurance.getMonth().toString());
			    insurance.setParticipant(resInsurance.getParticipant());
			    insurance.setPreInsurance(resInsurance.getPrepaidInsurance());
			    insurance.setTotalAmount(resInsurance.getTotalAmount());
			    insurance.setTransaction(transaction);
			    insurance.setDestination(insurance.getTransaction().getDestination());
			    
			    log.debug("save payment to insurance");
			    //log.debug("isinya..... "+telcoPrepaid.toString());
			    insuranceTransService.save(insurance);
			    log.debug("save OK.");
			    response.setData(insurance.getTransaction().getDestination().getId());
			}
		    }
		} catch (IOException e) {
		    log.error(e.getMessage(), e);
		    response.setCode(ResponseMessage.INTERNAL_SERVER_ERROR.getCode());
		    response.setMessage(messageUtil.get("error.internal.server", httpServletRequest.getLocale()));
		}
		
		return response;
	    }

	
/*	
	@GetMapping("/storeManualPrefixTelcoCache")
    @ResponseStatus(HttpStatus.OK)
    public CommonResponse storePrefixTelcoToCache() {
	log.debug("Store Prefix Telco to Cache");
	CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(), messageUtil.get("success", httpServletRequest.getLocale()));
	
	// Store to Cache
	List<PrefixTelco> list = prefixTelcoService.storePrefixTelcoToCache();
	if(null != list) {
		response.setData(list);
	}
	
	
	return response;
    }
	
	@GetMapping("/storeManualInstitutionCache")
    @ResponseStatus(HttpStatus.OK)
    public CommonResponse storeInstitutionToCache() {
	log.debug("Store Institution to Cache");
	CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(), messageUtil.get("success", httpServletRequest.getLocale()));
	
	// Store to Cache
	List<Institution> list = institutionService.storeInstitutionToCache();
	if(null != list) {
		response.setData(list);
	}
	
	
	return response;
    }
	
	@GetMapping("/getAllPrefixTelco")
    @ResponseStatus(HttpStatus.OK)
    public CommonResponse getAllPrefixTelco() {
	log.debug("get all Prefix Telco ");
	CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(), messageUtil.get("success", httpServletRequest.getLocale()));
	
	// Store to Cache
	List<PrefixTelco> list = prefixTelcoService.findAll();
	if(null != list) {
		for(PrefixTelco telco : list) {
			log.debug("institution "+telco.getInstitutions().toString());
		}
		response.setData(list);
	}
	
	return response;
    }
*/
}
