/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.purchase.web.rest;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
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
import id.co.asyst.bukopin.mobile.purchase.core.service.PLNPrepaidService;
import id.co.asyst.bukopin.mobile.purchase.core.service.PLNServices;
import id.co.asyst.bukopin.mobile.purchase.core.util.PLNUtils;
import id.co.asyst.bukopin.mobile.purchase.model.entity.PLNPrepaid;
import id.co.asyst.bukopin.mobile.purchase.model.payload.PurchasePrepaidRequestPLN;
import id.co.asyst.bukopin.mobile.purchase.model.payload.PurchasePrepaidResponsePLN;
import id.co.asyst.bukopin.mobile.purchase.model.payload.inquiryPrepaidRequestPLN;
import id.co.asyst.bukopin.mobile.purchase.model.payload.inquiryPrepaidResponsePLN;
import id.co.asyst.bukopin.mobile.service.core.MasterModuleService;
import id.co.asyst.bukopin.mobile.service.core.PLNService;
import id.co.asyst.bukopin.mobile.service.core.UserModuleService;
import id.co.asyst.bukopin.mobile.service.model.payload.pln.GetVerifyPINRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.pln.prepaid.PrepaidAdviceRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.pln.prepaid.PrepaidAdviceResponse;
import id.co.asyst.bukopin.mobile.service.model.payload.pln.prepaid.PrepaidInquiryRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.pln.prepaid.PrepaidInquiryResponse;
import id.co.asyst.bukopin.mobile.service.model.payload.pln.prepaid.PrepaidPurchaseRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.pln.prepaid.PrepaidPurchaseResponse;
import id.co.asyst.bukopin.mobile.user.model.entity.User;
import id.co.asyst.bukopin.mobile.user.model.payload.VerifyAccountOwnerRequest;
import id.co.asyst.bukopin.mobile.user.model.payload.VerifyAccountOwnerResponse;
import id.co.asyst.bukopin.mobile.user.model.payload.VerifyPhoneOwnerRequest;
import id.co.asyst.foundation.service.connector.Services;

/**
 * PLN Prepaid REST Controller
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Dec 6, 2019
 * @since 1.0.Alpha1-SNAPSHOT
 */
@RestController
@RequestMapping("/PLN/prepaid")
public class PLNPrepaidController {
    /* Constants: */
    private final Logger log = LoggerFactory.getLogger(PLNPrepaidController.class);

    private static final String SUCCESS_CODE = "000";
    private static final String ARANET_TIME_OUT_CODE = "999";
    private static final String UNREGISTERED_BILLER = "104";
    private static final String UNREGISTERED_SUBSCRIBER_NUMBER = "114";
    private static final String UNREGISTERED_METER_NUMBER = "115";
    private static final String PRR_SUBSCRIBER = "116";
    private static final String SUBSCRIBER_SUSPENDED = "177";
    private static final String PLN_CUT_OFF = "190";
    private static final String PLN_NOT_ENOUGH_BALANCE = "851";
    private static final String PLN_INACTIVE_ACCOUNT = "839";
    
    private static final String ADVICE_DATA_48 = "data48";
    private static final String ADVICE_DATA_63 = "data63";
    
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
    private PLNServices plnServices;
    
    /**
     * PLN Prepaid service
     */
    @Autowired
    private PLNPrepaidService plnPrepaidService;
    
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
     * POST /inquiry : inquiry pre paid PLN
     * 
     * @param req
     * @return
     * @throws IOException
     */
    @PostMapping("/inquiry")
    private CommonResponse inquiryResult(@Valid @RequestBody CommonRequest<inquiryPrepaidRequestPLN> req)
	    throws IOException {
	log.debug("REST request to inquiry PLN : {}", req.getData());
	CommonResponse response = new CommonResponse();

	String forwardInsCode = env.getProperty("spring.pln.forwarding-institution-code");
	PrepaidInquiryRequest reqInquiryPrepaidPLNAranet = PLNUtils
		.generateInquiryPrepaidPLNReq(req.getData().getSubscriberID(), forwardInsCode);
	PrepaidInquiryResponse resInquiryPrepaidPLNAranet = Services.create(PLNService.class)
		.prepaidInquiry(reqInquiryPrepaidPLNAranet).execute().body();

	String codeRes = resInquiryPrepaidPLNAranet.getRespayment().getResult().getElement39();
	if (PLN_CUT_OFF.equals(codeRes)) {
	    log.error("Cut Off PLN");
	    response.setCode(ResponseMessage.ERROR_CUT_OFF_PLN.getCode());
	    response.setMessage(messageUtil.get("error.cutoff.pln", servletRequest.getLocale()));
	} else if (UNREGISTERED_BILLER.equals(codeRes) || UNREGISTERED_SUBSCRIBER_NUMBER.equals(codeRes)) {
	    log.error("Unregistered biller or subscriber number");
	    response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
	    response.setMessage(messageUtil.get("error.id.pln.not.found", servletRequest.getLocale()));
	} else if (UNREGISTERED_METER_NUMBER.equals(codeRes)) {
	    log.error("Unregistered meter number");
	    response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
	    response.setMessage(messageUtil.get("error.no.meter.not.found", servletRequest.getLocale()));
	} else if (PRR_SUBSCRIBER.equals(codeRes) || SUBSCRIBER_SUSPENDED.equals(codeRes)) {
	    log.error("Subscriber was blocked");
	    response.setCode(ResponseMessage.PLN_SUBSCRIBER_SUSPENDED.getCode());
	    response.setMessage(messageUtil.get("error.user.pln.block",
		    new Object[] { req.getData().getSubscriberID() }, servletRequest.getLocale()));
	} else if (!SUCCESS_CODE.equals(codeRes)) {
	    log.error("Error from Aranet with code : "+codeRes);
	    throw new MiddlewareException(codeRes);
	} else {
	    log.debug("Inquiry PLN success");
	    String dataResp = resInquiryPrepaidPLNAranet.getRespayment().getResult().getElement48();

	    String subsName = dataResp.substring(95, 120);
	    String tarif = dataResp.substring(120, 124);
	    String daya = dataResp.substring(126, 133);
	    String adminCharge = dataResp.substring(138, 142);

	    inquiryPrepaidResponsePLN inqPrepaidResPLN = new inquiryPrepaidResponsePLN();
	    inqPrepaidResPLN.setSubscriberID(req.getData().getSubscriberID());
	    inqPrepaidResPLN.setSubscriberName(subsName.trim());
	    inqPrepaidResPLN.setTarifDaya(tarif.trim() + "/" + new BigDecimal(daya));
	    inqPrepaidResPLN.setNominal(req.getData().getNominal());
	    inqPrepaidResPLN.setAdminFee(new BigDecimal(adminCharge));

	    BigDecimal total = inqPrepaidResPLN.getNominal().add(inqPrepaidResPLN.getAdminFee());
	    inqPrepaidResPLN.setTotal(total);

	    inqPrepaidResPLN.setElement11(resInquiryPrepaidPLNAranet.getRespayment().getResult().getElement11());
	    inqPrepaidResPLN.setElement48(dataResp);
	    inqPrepaidResPLN.setElement62(resInquiryPrepaidPLNAranet.getRespayment().getResult().getElement62());
	    inqPrepaidResPLN.setFlag(dataResp.substring(30, 31));

	    response.setCode(ResponseMessage.SUCCESS.getCode());
	    response.setMessage(messageUtil.get("success", servletRequest.getLocale()));
	    response.setData(inqPrepaidResPLN);
	}
	return response;

    }

    /**
     * GET /inquiry/{id} : get inquiry prepaid pln only subscriber name
     * 
     * @param id
     * @return
     * @throws IOException
     */
    @GetMapping("/inquiry/{id}")
    private CommonResponse inquirySubscriberName(@PathVariable String id) throws IOException {
	log.debug("REST request to inquiry search subscriber name by ID : {}", id);
	CommonResponse response = new CommonResponse();

	String forwardInsCode = env.getProperty("spring.pln.forwarding-institution-code");
	PrepaidInquiryRequest reqInquiryPrepaidPLNAranet = PLNUtils.generateInquiryPrepaidPLNReq(id, forwardInsCode);
	log.debug("request to aranet : "+BkpmUtil.convertToJson(reqInquiryPrepaidPLNAranet));
	PrepaidInquiryResponse resInquiryPrepaidPLNAranet = Services.create(PLNService.class)
		.prepaidInquiry(reqInquiryPrepaidPLNAranet).execute().body();
	log.debug("pln inquiry req: "+BkpmUtil.convertToJson(resInquiryPrepaidPLNAranet));

	String codeRes = resInquiryPrepaidPLNAranet.getRespayment().getResult().getElement39();
	if (PLN_CUT_OFF.equals(codeRes)) {
	    log.error("Cut Off PLN");
	    response.setCode(ResponseMessage.ERROR_CUT_OFF_PLN.getCode());
	    response.setMessage(messageUtil.get("error.cutoff.pln", servletRequest.getLocale()));
	} else if (UNREGISTERED_BILLER.equals(codeRes) || UNREGISTERED_SUBSCRIBER_NUMBER.equals(codeRes)) {
	    log.error("Unregistered biller or subscriber number");
	    response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
	    response.setMessage(messageUtil.get("error.id.pln.not.found", servletRequest.getLocale()));
	} else if (UNREGISTERED_METER_NUMBER.equals(codeRes)) {
	    log.error("Unregistered meter number");
	    response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
	    response.setMessage(messageUtil.get("error.no.meter.not.found", servletRequest.getLocale()));
	} else if (PRR_SUBSCRIBER.equals(codeRes) || SUBSCRIBER_SUSPENDED.equals(codeRes)) {
	    log.error("Subscriber was blocked");
	    response.setCode(ResponseMessage.ERROR.getCode());
	    response.setMessage(messageUtil.get("error.user.pln.block",new Object[] {id}, servletRequest.getLocale()));
	} else if (!SUCCESS_CODE.equals(codeRes)) {
	    log.error("Error from Aranet with code : "+codeRes);
	    throw new MiddlewareException(codeRes);
	} else {
	    log.debug("Inquiry subscriber PLN success");
	    String dataResp = resInquiryPrepaidPLNAranet.getRespayment().getResult().getElement48();
	    String subsName = dataResp.substring(95, 120);

	    inquiryPrepaidResponsePLN inqPrepaidResPLN = new inquiryPrepaidResponsePLN();
	    inqPrepaidResPLN.setSubscriberID(id);
	    inqPrepaidResPLN.setSubscriberName(subsName.trim());
	    inqPrepaidResPLN.setFlag(dataResp.substring(30, 31));

	    response.setCode(ResponseMessage.SUCCESS.getCode());
	    response.setMessage(messageUtil.get("success", servletRequest.getLocale()));
	    response.setData(inqPrepaidResPLN);
	}
	return response;

    }

    /**
     * POST /purchase : purchase pre paid PLN
     * 
     * @param req
     * @return information after purchase pre paid PLN
     * @throws IOException
     * @throws MessagingException 
     */
    @SuppressWarnings("unchecked")
    @PostMapping("/purchase")
    private CommonResponse purchaseResult(@Valid @RequestBody CommonRequest<PurchasePrepaidRequestPLN> req) throws IOException, MessagingException {
	CommonResponse response = new CommonResponse();
	
	PurchasePrepaidRequestPLN dataRequest = req.getData();
	String username = dataRequest.getUsername();
	String accountNo = dataRequest.getAccountNo();
	
	if (!commonService.verifyLocalIp(servletRequest)) {
	    // Validate Token and Phone Owner
	    CommonRequest<VerifyPhoneOwnerRequest> phoneReq = new CommonRequest<>();
	    VerifyPhoneOwnerRequest phoneReqData = new VerifyPhoneOwnerRequest();
	    phoneReqData.setUsername(req.getData().getUsername());
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

	String forwardInsCode = env.getProperty("spring.pln.forwarding-institution-code");
	Identity identity = PLNUtils.generateIdentity();
	GetVerifyPINRequest pinRequest = new GetVerifyPINRequest();
	pinRequest.setUsername(req.getData().getUsername());
	pinRequest.setPin(req.getData().getPin());

	CommonRequest<GetVerifyPINRequest> commPinRequest = new CommonRequest<GetVerifyPINRequest>();
	commPinRequest.setIdentity(identity);
	commPinRequest.setData(pinRequest);

	// verify PIN
	response = Services.create(UserModuleService.class).verifyPIN(commPinRequest).execute().body();
	if (!SUCCESS_CODE.equals(response.getCode())) {
	    log.error("PIN Tidak Sesuai!");
	    response.setCode(ResponseMessage.DATA_NOT_MATCH.getCode());
	    response.setMessage(messageUtil.get("auth.pin.failed", servletRequest.getLocale()));
	    return response;
	}
	
	// Validate Account Number's Owner
	VerifyAccountOwnerRequest verifyAccountData = new VerifyAccountOwnerRequest(username, accountNo);
	log.debug("Verify account owner: {}", BkpmUtil.convertToJson(verifyAccountData));
	CommonRequest<VerifyAccountOwnerRequest> verifyAccountRequest = new CommonRequest<>();
	verifyAccountRequest.setIdentity(req.getIdentity());
	verifyAccountRequest.setData(verifyAccountData);
	CommonResponse accountResponse = Services.create(UserModuleService.class)
		.verifyAccountOwner(verifyAccountRequest).execute().body();
	if(!ResponseMessage.SUCCESS.getCode().equals(accountResponse.getCode())) {
	    log.error("Error verify account owner: "+accountNo);
	    // response not success
	    return accountResponse;
	}
	ObjectMapper obMapper = new ObjectMapper();
	VerifyAccountOwnerResponse resAccount = obMapper.convertValue(accountResponse.getData(), VerifyAccountOwnerResponse.class);
	if(!resAccount.isValid()) {
	    log.error("User and Account Info didn't match: "+dataRequest.getAccountNo());
	    String message = messageUtil.get("error.invalid.account.owner", servletRequest.getLocale());
	    throw new DataNotMatchException(message);
	}
	String accType = resAccount.getAccountInfo().getAccountType().name();

	PrepaidPurchaseRequest reqPurchasePrepaidPLNAranet = PLNUtils.generatePurchasePrepaidPLNReq(req.getData(), forwardInsCode, accType);
	log.debug("request to aranet : " + BkpmUtil.convertToJson(reqPurchasePrepaidPLNAranet.getParameter()));
	PrepaidPurchaseResponse resPurchasePrepaidPLNAranet = new PrepaidPurchaseResponse();
	resPurchasePrepaidPLNAranet = Services.create(PLNService.class)
	    	.prepaidPurchase(reqPurchasePrepaidPLNAranet).execute().body();
	log.debug("response : " + BkpmUtil.convertToJson(resPurchasePrepaidPLNAranet.getRespayment().getResult()));
	String codeRes = resPurchasePrepaidPLNAranet.getRespayment().getResult().getElement39();
	String postRes = resPurchasePrepaidPLNAranet.getRespayment().getResult().getElement121().substring(0, 3);
	
	if(PLN_NOT_ENOUGH_BALANCE.equals(codeRes)) {
	    log.error("Not enough balance: "+accountNo);
	    response.setCode(ResponseMessage.AMOUNT_NOT_ENOUGH.getCode());
	    response.setMessage(messageUtil.get("error.amount.not.enough", servletRequest.getLocale()));
	} else if(PLN_INACTIVE_ACCOUNT.equals(codeRes)) {
	    log.error("Account inactive: "+accountNo);
	    response.setCode(ResponseMessage.ERROR_INACTIVE_BANK_ACCOUNT.getCode());
	    response.setMessage(messageUtil.get("error.inactive.bank.account", servletRequest.getLocale()));
	} else if(!SUCCESS_CODE.equals(codeRes) && !ARANET_TIME_OUT_CODE.equals(codeRes)) {
	    log.error("Purchase Failed (response) : "+codeRes);
	    throw new MiddlewareException(codeRes);
	}
	
	PurchasePrepaidResponsePLN purchasePrepaidResPLN = new PurchasePrepaidResponsePLN();
	if(SUCCESS_CODE.equals(codeRes)) {
	    log.info("Purchase Success");
	    String dataResp = resPurchasePrepaidPLNAranet.getRespayment().getResult().getElement48();
	    String footNote = resPurchasePrepaidPLNAranet.getRespayment().getResult().getElement63();
	    String element122 = resPurchasePrepaidPLNAranet.getRespayment().getResult().getElement122();
	    purchasePrepaidResPLN = PLNUtils.generateResponsePurchase(dataResp, req.getData(), footNote, element122);
	    response.setCode(ResponseMessage.SUCCESS.getCode());
	    response.setMessage(messageUtil.get("success", servletRequest.getLocale()));
	    response.setData(purchasePrepaidResPLN);
	}
	
	// Handle if posting success but ARANET is timeout
	String codeResAdvice = "";
	if (ARANET_TIME_OUT_CODE.equals(codeRes) && SUCCESS_CODE.equals(postRes)) {
	    response = callAdvice(req.getData());
	    codeResAdvice = response.getCode();
	    if(SUCCESS_CODE.equals(response.getCode())) {
		log.debug("resp Advice : "+response.getData());
		Map<String, String> dataResult = (Map<String, String>) response.getData();
		String resDataAdvice = dataResult.get(ADVICE_DATA_48);
		String footNote = dataResult.get(ADVICE_DATA_63);
		
		//String resDataAdvice = (String) response.getData();
		purchasePrepaidResPLN = PLNUtils.generateResponsePurchase(resDataAdvice, req.getData(), footNote, null);
		response.setCode(ResponseMessage.SUCCESS.getCode());
		response.setMessage(messageUtil.get("success", servletRequest.getLocale()));
		response.setData(purchasePrepaidResPLN);
	    }
	}
	
	//send email receipt and save to favorite
	CommonResponse getUser = Services.create(UserModuleService.class,
		servletRequest.getHeader(HttpHeaders.AUTHORIZATION))
		.getUserByUsername(req.getData().getUsername())
		.execute().body();
	if(null!=getUser && (SUCCESS_CODE.equals(codeRes) || SUCCESS_CODE.equals(codeResAdvice))) {
	    log.debug("Send Email.. ");
	    ObjectMapper oMapper = new ObjectMapper();
	    Map<String, Object> res = oMapper.convertValue(getUser.getData(), Map.class);
	    Map<String, String> resUser = oMapper.convertValue(res.get("user"), Map.class);
	    
	    // send receipt asynchronously
	    plnServices.sendEmailReceiptPLN(purchasePrepaidResPLN, resUser, servletRequest.getLocale());
	    
	    // save pln prepaid
	    Transaction trx = savePurchase(req.getIdentity(), purchasePrepaidResPLN, resUser);
	    purchasePrepaidResPLN.setDestinationId(String.valueOf(trx.getDestination().getId()));
	    response.setData(purchasePrepaidResPLN);
	}
	
	return response;
    }
    
    /**
     * Call this Advice Process if posting success but aranet is timeout.
     * this advice process is to ask token number to tibco
     * 
     * @param req
     * @return information after advice PLN
     * @throws IOException
     */
    private CommonResponse callAdvice(PurchasePrepaidRequestPLN req) throws IOException {
	log.debug("Call Advice...");
	CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(),
		messageUtil.get("success", servletRequest.getLocale()));
	
	String forwardInsCode = env.getProperty("spring.pln.forwarding-institution-code");
	PrepaidAdviceRequest reqAdvicePrepaidPLNAranet = PLNUtils.generateAdvicePrepaidPLNReq(req, forwardInsCode);
	log.debug("request to aranet (Advice) : " + BkpmUtil.convertToJson(reqAdvicePrepaidPLNAranet.getParameter()));
	PrepaidAdviceResponse resAdvicePrepaidPLNAranet = Services.create(PLNService.class)
		.prepaidAdvice(reqAdvicePrepaidPLNAranet).execute().body();
	log.debug("response (Advice) : " + BkpmUtil.convertToJson(resAdvicePrepaidPLNAranet.getRespayment().getResult()));

	String codeResAdvice = resAdvicePrepaidPLNAranet.getRespayment().getResult().getElement39();
	if (!SUCCESS_CODE.equals(codeResAdvice)) {
	    log.error("Advice Error Code : " + resAdvicePrepaidPLNAranet.getRespayment().getResult().getElement39());
	    response.setCode(ResponseMessage.ERROR_ADVICE_FAILED.getCode());
	    response.setMessage(messageUtil.get("error.advice.failed", servletRequest.getLocale()));
	} else {
	    log.info("Advice Success");
	    String dataRespAdvice = resAdvicePrepaidPLNAranet.getRespayment().getResult().getElement48();
	    String footNote = resAdvicePrepaidPLNAranet.getRespayment().getResult().getElement63();
	    Map<String, String> dataResult = new HashMap<String, String>();
	    dataResult.put(ADVICE_DATA_48, dataRespAdvice);
	    dataResult.put(ADVICE_DATA_63, footNote);
	    
	    response.setCode(ResponseMessage.SUCCESS.getCode());
	    response.setMessage(messageUtil.get("success", servletRequest.getLocale()));
	    response.setData(dataResult);
	}
	
	return response;
    }
    
    /**
     * Save Purchase Data
     * 
     * @param identity The Identity object request
     * @param data The postpaid data to save
     * @param username The User's username
     * @return The Transaction object data include destination ID will send as response to frontend.
     * @throws IOException The Connection exception.
     */
    public Transaction savePurchase(Identity identity, PurchasePrepaidResponsePLN resPln, Map<String, String> user) 
	    throws IOException {
	Transaction transaction = new Transaction();
	String username = user.get("username");
	
	// Prepare data request to save transaction
	DestinationCommonRequest dataReq = new DestinationCommonRequest();
	dataReq.setCategoryId(CategoryEnum.LISTRIK.getId());
	dataReq.setUsername(username);
	dataReq.setSubscriberNumber(resPln.getSubscriberID());
	dataReq.setSubscriberName(resPln.getSubscriberName());
	dataReq.setDestinationType(DestinationTypeEnum.LISTRIK.name());
	dataReq.setTransactionType(TransactionTypeEnum.PLNPRE.name());
	dataReq.setReference(resPln.getReference());
	dataReq.setAccountNumber(resPln.getAccountNo());
	dataReq.setTotalAmount(resPln.getTotal());
	
	// Call service to save Destination/ Favorite
	CommonRequest<DestinationCommonRequest> destinationReq = new CommonRequest<>();
	destinationReq.setIdentity(identity);
	destinationReq.setData(dataReq);
	
	log.debug("Call saveToFavouriteCommon: {}", BkpmUtil.convertToJson(destinationReq));
	CommonResponse res = Services.create(MasterModuleService.class).saveToFavouriteCommon(destinationReq).execute().body();
	log.debug(BkpmUtil.convertToJson(res));
	ObjectMapper oMapper = new ObjectMapper();
	if (ResponseMessage.SUCCESS.getCode().equals(res.getCode())) {
	    transaction = oMapper.convertValue(res.getData(), Transaction.class);
	    PLNPrepaid prepaid = new PLNPrepaid();
	    String[] tarif = resPln.getPowerConsuming().split("/");
	    prepaid.setAccountNumber(resPln.getAccountNo());
	    prepaid.setAdminFee(resPln.getAdminCharge());
	    prepaid.setAmount(resPln.getNominal().toString());
	    prepaid.setDaya(tarif[1]);
	    prepaid.setJumlahKwh(resPln.getPurchasedKWH());
	    prepaid.setTarif(tarif[0]);
	    prepaid.setToken(resPln.getStroomToken());
	    prepaid.setTotalAmount(resPln.getTotal());
	    prepaid.setTransaction(transaction);

	    log.debug("save purchase to PLN_PREPAID");
	    plnPrepaidService.save(prepaid);
	} else {
	    // TODO handle error save transaction
	    log.error("error wile saving transaction...");
	}

	return transaction;
    }

    /* Overrides: */
}
