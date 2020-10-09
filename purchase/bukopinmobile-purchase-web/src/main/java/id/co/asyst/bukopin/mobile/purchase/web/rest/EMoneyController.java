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
import java.util.Map;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import id.co.asyst.bukopin.mobile.common.core.exception.MiddlewareException;
import id.co.asyst.bukopin.mobile.common.core.service.impl.BkpmService;
import id.co.asyst.bukopin.mobile.common.core.util.BkpmUtil;
import id.co.asyst.bukopin.mobile.common.core.util.MessageUtil;
import id.co.asyst.bukopin.mobile.common.model.BkpmConstants;
import id.co.asyst.bukopin.mobile.common.model.ResponseMessage;
import id.co.asyst.bukopin.mobile.common.model.SystemCutOffEnum;
import id.co.asyst.bukopin.mobile.common.model.payload.CommonRequest;
import id.co.asyst.bukopin.mobile.common.model.payload.CommonResponse;
import id.co.asyst.bukopin.mobile.common.model.payload.Identity;
import id.co.asyst.bukopin.mobile.master.model.CategoryEnum;
import id.co.asyst.bukopin.mobile.master.model.DestinationTypeEnum;
import id.co.asyst.bukopin.mobile.master.model.EmoneyTypeEnum;
import id.co.asyst.bukopin.mobile.master.model.TransactionTypeEnum;
import id.co.asyst.bukopin.mobile.master.model.entity.Transaction;
import id.co.asyst.bukopin.mobile.master.model.payload.DestinationCommonRequest;
import id.co.asyst.bukopin.mobile.purchase.core.service.EMoneyService;
import id.co.asyst.bukopin.mobile.purchase.core.service.EMoneyTransService;
import id.co.asyst.bukopin.mobile.purchase.core.util.EMoneyUtils;
import id.co.asyst.bukopin.mobile.purchase.model.entity.EMoney;
import id.co.asyst.bukopin.mobile.purchase.model.payload.PurchaseEMoneyRequest;
import id.co.asyst.bukopin.mobile.purchase.model.payload.PurchaseEMoneyResponse;
import id.co.asyst.bukopin.mobile.purchase.model.payload.inquiryEMoneyRequest;
import id.co.asyst.bukopin.mobile.purchase.model.payload.inquiryEMoneyResponse;
import id.co.asyst.bukopin.mobile.service.core.EMoneyModuleService;
import id.co.asyst.bukopin.mobile.service.core.MasterModuleService;
import id.co.asyst.bukopin.mobile.service.core.UserModuleService;
import id.co.asyst.bukopin.mobile.service.model.payload.emoney.EMoneyInquiryRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.emoney.EMoneyInquiryResponse;
import id.co.asyst.bukopin.mobile.service.model.payload.emoney.EMoneyPurchaseRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.emoney.EMoneyPurchaseResponse;
import id.co.asyst.bukopin.mobile.service.model.payload.pln.GetVerifyPINRequest;
import id.co.asyst.bukopin.mobile.user.model.payload.VerifyAccountOwnerRequest;
import id.co.asyst.bukopin.mobile.user.model.payload.VerifyAccountOwnerResponse;
import id.co.asyst.bukopin.mobile.user.model.payload.VerifyPhoneOwnerRequest;
import id.co.asyst.foundation.service.connector.Services;

/**
 * EMoney REST Controller
 * 
 * @author Kartika Dwi H
 * @version $Revision$, Jan 20, 2020
 * @since 1.1.Alpha1-SNAPSHOT
 */
@RestController
@RequestMapping("/EMoney")
public class EMoneyController {
    /* Constants: */
    private final Logger log = LoggerFactory.getLogger(EMoneyController.class);

    private static final String SUCCESS_CODE = "000";
    private static final String ARANET_TIME_OUT_CODE = "999";
    private static final String INVALID_AMOUNT = "113";
    private static final String INVALID_SUBSCRIBER_ID = "114";
    private static final String TRANSACTION_PROCESSING = "168";
    private static final String EMONEY_NOT_ENOUGH_BALANCE = "851";
   // private static final String EMONEY_NOT_ENOUGH_BALANCE2 = "806";
    private static final String EMONEY_ACCOUNT_INACTIVE = "839";
    private static final String BILL_ALREADY_PAID = "188";
    private static final String SYSTEM_BROKEN_168 = "168";
    private static final String SYSTEM_BROKEN_169 = "169";
    private static final BigDecimal MINIMUM_GOPAY_AMOUNT = new BigDecimal("20000");
    private static final BigDecimal MAXIMUM_GOPAY_AMOUNT = new BigDecimal("1000000");
    private static final BigDecimal MINIMUM_OVO_AMOUNT = new BigDecimal("10000");
    private static final BigDecimal MAXIMUM_OVO_AMOUNT = new BigDecimal("2000000");
    
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
     * EMoney service
     */
    @Autowired
    private EMoneyService eMoneyService;
    
    /**
     * EMoney Transactional service
     */
    @Autowired
    private EMoneyTransService eMoneyTransService;
    
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
     * POST /inquiry : inquiry emoney gopay
     * 
     * @param req
     * @return
     * @throws IOException
     */
    @PostMapping("/inquiry/GOPAY")
    private CommonResponse inquiryGoPayResult(@Valid @RequestBody CommonRequest<inquiryEMoneyRequest> req)
	    throws IOException {
	CommonResponse response = new CommonResponse();

	// Check Cut Off
	long cutoffId = SystemCutOffEnum.GOPAY.getId();
	CommonResponse cutOffResponse = Services.create(MasterModuleService.class)
		.checkCutOffStatus(servletRequest.getLocale().getLanguage(), cutoffId).execute().body();
	if (!ResponseMessage.SUCCESS.getCode().equals(cutOffResponse.getCode())) {
	    log.error("Error Cutoff");
	    return cutOffResponse;
	}

	String forwardInsCode = env.getProperty("config.emoney.forwarding-institution-code");
	EMoneyInquiryRequest reqInquiryEMoneyAranet = EMoneyUtils.generateInquiryEMoneyReq(
		req.getData().getCustomerNumber(), forwardInsCode,servletRequest.getLocale().getLanguage(),
		req.getData().getType(), req.getData().getAmount());
	
	boolean isValid =true;
	//check minimum and maximum amount
	if(req.getData().getAmount().compareTo(MINIMUM_GOPAY_AMOUNT) == -1) {
		log.error("GoPay Amount less than minimum");
	    response.setCode(ResponseMessage.INVALID_MIN_LIMIT_AMOUNT.getCode());
	    response.setMessage(messageUtil.get("error.invalid.min.amount", servletRequest.getLocale()));
	    isValid=false;
	}
	
	if(req.getData().getAmount().compareTo(MAXIMUM_GOPAY_AMOUNT) == 1) {
		log.error("GoPay Amount greater than maximum");
	    response.setCode(ResponseMessage.INVALID_MAX_LIMIT_AMOUNT.getCode());
	    response.setMessage(messageUtil.get("error.invalid.max.amount", servletRequest.getLocale()));
	    isValid=false;
	}
	
	
	if(isValid) {
		log.debug("request to TIBCO : "+BkpmUtil.convertToJson(reqInquiryEMoneyAranet));
		EMoneyInquiryResponse resInquiryEMoneyAranet = Services.create(EMoneyModuleService.class)
			.emoneyInquiry(reqInquiryEMoneyAranet).execute().body();

		log.debug("response from TIBCO : "+ BkpmUtil.convertToJson(resInquiryEMoneyAranet));
		
		
		String codeRes = resInquiryEMoneyAranet.getRespayment().getResult().getElement39();
		if (INVALID_AMOUNT.equals(codeRes)) {
		    log.error("GoPay Invalid Amount");
		    response.setCode(ResponseMessage.INVALID_AMOUNT.getCode());
		    response.setMessage(messageUtil.get("error.invalid.amount", servletRequest.getLocale()));
		} else if (INVALID_SUBSCRIBER_ID.equalsIgnoreCase(codeRes) 
				|| GIRO_USER_NOT_FOUND.equalsIgnoreCase(codeRes)) {
		    log.error("SUbscriber not found/invalid");
		    response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
		    response.setMessage(messageUtil.get("error.id.emoney.not.found", servletRequest.getLocale()));
		}  else if (BILL_ALREADY_PAID.equalsIgnoreCase(codeRes) ) {
		    log.error("Bill already Paid");
		    response.setCode(ResponseMessage.ERROR_BILL_ALREADY_PAID.getCode());
		    response.setMessage(messageUtil.get("error.emoney.already.paid", servletRequest.getLocale()));
		} else if (EMONEY_ACCOUNT_INACTIVE.equalsIgnoreCase(codeRes) 
				|| GIRO_INACTIVE_ACCOUNT.equalsIgnoreCase(codeRes)
				|| GIRO_CLOSED_ACCOUNT.equalsIgnoreCase(codeRes)) {
		    log.error("account in active");
		    response.setCode(ResponseMessage.ERROR_INACTIVE_BANK_ACCOUNT.getCode());
		    response.setMessage(messageUtil.get("error.inactive.bank.account", servletRequest.getLocale()));
		} else if (TRANSACTION_PROCESSING.equals(codeRes)) {
		    log.error("Transaction processing");
		    response.setCode(ResponseMessage.TRANSACTION_PROCESSING.getCode());
		    response.setMessage(messageUtil.get("error.transaction.processing", servletRequest.getLocale()));
		} else if (GIRO_LIMIT_TRANSFER.equals(codeRes) 
				|| GIRO_OVER_LIMIT.equals(codeRes)) {
		    log.error("exceed limit");
		    response = new CommonResponse();
		    response.setCode(ResponseMessage.LIMIT_TRANSFER_DAY.getCode());
		    response.setMessage(messageUtil.get("error.exceed.limit", servletRequest.getLocale()));
		} else if (GIRO_CUT_OFF.equals(codeRes)) {
		    log.error("Giro cut off");
		    response = new CommonResponse();
		    response.setCode(ResponseMessage.ERROR_CUT_OFF_PLN.getCode());
		    response.setMessage(messageUtil.get("error.cutoff.pln", servletRequest.getLocale()));
		} else if (GIRO_DUPLICATE_DATA.equals(codeRes)) {
		    log.error("Giro Duplicate Data");
		    response = new CommonResponse();
		    response.setCode(ResponseMessage.DUPLICATE_DATA.getCode());
		    response.setMessage(messageUtil.get("error.duplicate.data", servletRequest.getLocale()));
		} else if (GIRO_ACCOUNT_WAS_BLOCKED.equals(codeRes)
				|| GIRO_ACCOUNT_BLOCKED.equals(codeRes)) {
		    log.error("account wass blocked");
		    response.setCode(ResponseMessage.CUST_BLOCKED.getCode());
		    response.setMessage(messageUtil.get("error.customer.was.blocked", servletRequest.getLocale()));
		} else if (SYSTEM_BROKEN_168.equals(codeRes) || SYSTEM_BROKEN_169.equals(codeRes)) {
		    log.error("System was broken");
		    response.setCode(ResponseMessage.ERROR_ADVICE_FAILED.getCode());
		    response.setMessage(messageUtil.get("error.internal.server", servletRequest.getLocale()));
		} else if (!SUCCESS_CODE.equals(codeRes)) {
		    // TODO throw middleware exception
		    log.error("Error from Aranet with code : "+codeRes);
		    response.setCode(ResponseMessage.INTERNAL_SERVER_ERROR.getCode());
		    response.setMessage(messageUtil.get("error.internal.server", servletRequest.getLocale()));
		    
		    /*log.error("Error (result) PLN Postpaid: "+codeRes);
		    // Throw middleware error
		    throw new MiddlewareException(codeRes);*/
		} else {
		    log.debug("Inquiry EMoney success");

		    inquiryEMoneyResponse inqEMoneyRes= new inquiryEMoneyResponse();
		    String name = resInquiryEMoneyAranet.getRespayment().getResult().getElement61().substring(35, 65).trim();
		    inqEMoneyRes.setCustomerName(name);
		    inqEMoneyRes.setCustomerNumber(req.getData().getCustomerNumber());
		    inqEMoneyRes.setAmount(req.getData().getAmount());
		    inqEMoneyRes.setElement37(resInquiryEMoneyAranet.getRespayment().getResult().getElement37());
		    
		    String amountFee = resInquiryEMoneyAranet.getRespayment().getResult().getElement61().substring(78, 89);
		    inqEMoneyRes.setAmountFee(new BigDecimal(amountFee));
		    // add one character in element 61
		    inqEMoneyRes.setElement61(resInquiryEMoneyAranet.getRespayment().getResult().getElement61().concat("1"));
		    Integer st =new Integer(resInquiryEMoneyAranet.getRespayment().getResult().getElement11());
		    inqEMoneyRes.setElement11(st.toString());
		    inqEMoneyRes.setElement48(resInquiryEMoneyAranet.getRespayment().getResult().getElement48());
		    inqEMoneyRes.setTotalAmount(new BigDecimal(amountFee).add(req.getData().getAmount()));
		    
		    response.setCode(ResponseMessage.SUCCESS.getCode());
		    response.setMessage(messageUtil.get("success", servletRequest.getLocale()));
		    response.setData(inqEMoneyRes);
		}
	}
	
	return response;

    }

/**
     * POST /purchase : purchase EMoney gopay
     * 
     * @param req
     * @return information after purchase pre paid PLN
     * @throws IOException
     * @throws MessagingException 
     */
    @SuppressWarnings("unchecked")
    @PostMapping("/purchase/GOPAY")
    private CommonResponse purchaseGoPayResult(@Valid @RequestBody CommonRequest<PurchaseEMoneyRequest> req) throws IOException, MessagingException {
	CommonResponse response = new CommonResponse();
	
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
	
	// Check Cut Off
	long cutoffId = SystemCutOffEnum.GOPAY.getId();
	CommonResponse cutOffResponse = Services.create(MasterModuleService.class)
		.checkCutOffStatus(servletRequest.getLocale().getLanguage(), cutoffId).execute().body();
	if (!ResponseMessage.SUCCESS.getCode().equals(cutOffResponse.getCode())) {
	    log.error("Error Cutoff");
	    return cutOffResponse;
	}
	
	ObjectMapper oMapper = new ObjectMapper();
	// Validate Account Number's Owner
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
	    response.setMessage(messageUtil.get("error.invalid.user.accountno", servletRequest.getLocale()));
	    return response;
	}
	//User user = resAccount.getUser();
	
	// Prepare verify PIN
	log.debug("verify PIN: "+req.getData().getUsername());
	GetVerifyPINRequest pinRequest = new GetVerifyPINRequest();
	pinRequest.setPin(req.getData().getPin());
	pinRequest.setUsername(req.getData().getUsername());
	CommonRequest<GetVerifyPINRequest> commonPinRequest = new CommonRequest<>();
	commonPinRequest.setIdentity(req.getIdentity());
	commonPinRequest.setData(pinRequest);
	// Verify PIN
	CommonResponse pinResponse = Services.create(UserModuleService.class)
		.verifyPIN(servletRequest.getHeader(HttpHeaders.ACCEPT_LANGUAGE), commonPinRequest)
		.execute().body();
	if(!ResponseMessage.SUCCESS.getCode().equals(pinResponse.getCode())) {
	    // response not success
	    return pinResponse;
	}
	

	String forwardInsCode = env.getProperty("config.emoney.forwarding-institution-code");
	Identity identity = EMoneyUtils.generateIdentity();
	String accType = resAccount.getAccountInfo().getAccountType().name();
	
	EMoneyPurchaseRequest reqPurchaseEMoneyAranet = EMoneyUtils.generatePurchaseEMoneyReq(req.getData(), forwardInsCode, accType);
	log.debug("request to aranet : " + BkpmUtil.convertToJson(reqPurchaseEMoneyAranet));
	
	EMoneyPurchaseResponse resPurchaseEMoneyAranet = new EMoneyPurchaseResponse();
	resPurchaseEMoneyAranet = Services.create(EMoneyModuleService.class)
	    	.emoneyPurchase(reqPurchaseEMoneyAranet).execute().body();
	log.debug("response from aranet: " + BkpmUtil.convertToJson(resPurchaseEMoneyAranet));
	
	
	String codeRes = resPurchaseEMoneyAranet.getRespayment().getResult().getElement39();
	//String postRes = resPurchaseEMoneyAranet.getRespayment().getResult().getElement121().substring(0, 3);
	
	
	PurchaseEMoneyResponse purchaseEMoneyRes = new PurchaseEMoneyResponse();
	if(SUCCESS_CODE.equals(codeRes)) {
	    log.info("Purchase Success");
	   /* String dataResp = resPurchaseEMoneyAranet.getRespayment().getResult().getElement48();
	    String footNote = resPurchaseEMoneyAranet.getRespayment().getResult().getElement63();*/
	    String element61 = resPurchaseEMoneyAranet.getRespayment().getResult().getElement61();
	    String element122 = resPurchaseEMoneyAranet.getRespayment().getResult().getElement122();
	    		
		purchaseEMoneyRes.setAccountNumber(req.getData().getAccountNumber());
		purchaseEMoneyRes.setAmount(req.getData().getAmount());
		
		BigDecimal adminFee = new BigDecimal(element61.substring(78,89).trim());
		purchaseEMoneyRes.setAmountFee(adminFee);
		
		purchaseEMoneyRes.setCustomerName(element61.substring(35, 65).trim());
		purchaseEMoneyRes.setCustomerNumber(req.getData().getCustomerNumber());
		purchaseEMoneyRes.setDate(element61.substring(21, 29).trim());
		//purchaseEMoneyRes.setReference(element61.substring(89, 109).trim());
		purchaseEMoneyRes.setReference(element122.substring(144, 159).trim());
		purchaseEMoneyRes.setTime(element61.substring(29, 35).trim());
		purchaseEMoneyRes.setTotalAmount(req.getData().getAmount().add(adminFee));
		purchaseEMoneyRes.setType(EmoneyTypeEnum.GOPAY.name());
		purchaseEMoneyRes.setUsername(req.getData().getUsername());
	    
	    response.setCode(ResponseMessage.SUCCESS.getCode());
	    response.setMessage(messageUtil.get("success", servletRequest.getLocale()));
	    response.setData(purchaseEMoneyRes);
	    
	    
	  //send email receipt and save to favorite	
		CommonResponse getUser = Services.create(UserModuleService.class).getUserByUsername(req.getData().getUsername())
				.execute().body();
		if(null!=getUser && (SUCCESS_CODE.equals(codeRes))) {
		    log.debug("Send Email.. ");
		    oMapper = new ObjectMapper();
		    Map<String, Object> res = oMapper.convertValue(getUser.getData(), Map.class);
		    Map<String, String> resUser = oMapper.convertValue(res.get("user"), Map.class);
		    log.debug("email : "+resUser.get("email"));
		    eMoneyService.sendEmailReceiptEMoney(purchaseEMoneyRes, resUser, servletRequest.getLocale());
		    /*log.debug("responseeeee "+response);
		    
		    if(SUCCESS_CODE.equals(response.getCode())){
			response.setData(purchaseEMoneyRes);
		    }*/
		    
		    // save EMoney
		    CommonResponse saveRes = savePurchaseEMoney(identity,purchaseEMoneyRes);
		    //log.debug("save RES EMoney.... "+saveRes.toString());
		    if(SUCCESS_CODE.equals(saveRes.getCode())) {
		    	log.debug("ID DESTINATION .... "+saveRes.getData().toString());
		    	purchaseEMoneyRes.setDestinationId(saveRes.getData().toString());
			response.setData(purchaseEMoneyRes);
		    }
		} else {
			log.error("SUbscriber not found/invalid");
		    response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
		    response.setMessage(messageUtil.get("error.id.emoney.not.found", servletRequest.getLocale()));
		}
	} else if (INVALID_AMOUNT.equals(codeRes)) {
	    log.error("GoPay Invalid Amount");
	    response.setCode(ResponseMessage.INVALID_AMOUNT.getCode());
	    response.setMessage(messageUtil.get("error.invalid.amount", servletRequest.getLocale()));
	}else if (TRANSACTION_PROCESSING.equals(codeRes)) {
	    log.error("Transaction processing");
	    response.setCode(ResponseMessage.TRANSACTION_PROCESSING.getCode());
	    response.setMessage(messageUtil.get("error.transaction.processing", servletRequest.getLocale()));
	} else if(EMONEY_NOT_ENOUGH_BALANCE.equals(codeRes)
		|| GIRO_AMOUNT_NOT_ENOUGH_BALANCE.equals(codeRes)
		|| GIRO_ERROR_VALUTA_CODE.equals(codeRes)
		|| GIRO_LIMITED_BALANCE.equals(codeRes)) {
	    log.error("Not enough balance: "+req.getData().getAccountNumber());
	    response.setCode(ResponseMessage.AMOUNT_NOT_ENOUGH.getCode());
	    response.setMessage(messageUtil.get("error.amount.not.enough", servletRequest.getLocale()));
	    return response;
	} else if (EMONEY_ACCOUNT_INACTIVE.equalsIgnoreCase(codeRes) ) {
	    log.error("account in active");
	    response.setCode(ResponseMessage.ERROR_INACTIVE_BANK_ACCOUNT.getCode());
	    response.setMessage(messageUtil.get("error.inactive.bank.account", servletRequest.getLocale()));
	}else if (BILL_ALREADY_PAID.equalsIgnoreCase(codeRes) ) {
	    log.error("Bill already Paid");
	    response.setCode(ResponseMessage.ERROR_BILL_ALREADY_PAID.getCode());
	    response.setMessage(messageUtil.get("error.emoney.already.paid", servletRequest.getLocale()));
	}	/*else if (SYSTEM_BROKEN_168.equals(codeRes) || SYSTEM_BROKEN_169.equals(codeRes)) {
	    log.error("System was broken");
	    response.setCode(ResponseMessage.ERROR_ADVICE_FAILED.getCode());
	    response.setMessage(messageUtil.get("error.internal.server", servletRequest.getLocale()));
	}*/ else {
		log.error("Error (result) EMoney GoPay: "+codeRes);
	    // Throw middleware error
	    throw new MiddlewareException(codeRes);
	}
	
	return response;
    }
     
    
    
    
    public CommonResponse savePurchaseEMoney(Identity identity, PurchaseEMoneyResponse resEMoney) {
	CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(), 
		messageUtil.get("success", servletRequest.getLocale()));
	String typeEmoney = resEMoney.getType();
	try {
	    // save to Destination and Transaction
	    /*Map<String, Object> res = new HashMap<String, Object>();
	    res.put("purchase", resEMoney);*/
	    
	    // Prepare data request to save transaction
		DestinationCommonRequest dataReq = new DestinationCommonRequest();
		dataReq.setCategoryId(CategoryEnum.E_MONEY.getId());
		dataReq.setUsername(resEMoney.getUsername());
		dataReq.setSubscriberNumber(resEMoney.getCustomerNumber());
		dataReq.setSubscriberName(resEMoney.getCustomerName());
		dataReq.setTransactionType(TransactionTypeEnum.EMONEY.name());
		dataReq.setReference(resEMoney.getReference());
		dataReq.setAccountNumber(resEMoney.getAccountNumber());
		dataReq.setTotalAmount(resEMoney.getTotalAmount());
		
		// Call service to save Destination/ Favorite
		CommonRequest<DestinationCommonRequest> destinationReq = new CommonRequest<>();
		destinationReq.setIdentity(identity);
		destinationReq.setData(dataReq);
		
	    // gopay and ovo condition
	    if (EmoneyTypeEnum.GOPAY.name().equalsIgnoreCase(typeEmoney)) {
		dataReq.setDestinationType(DestinationTypeEnum.PREGOPAY.name());
	    } else if (EmoneyTypeEnum.OVO.name().equalsIgnoreCase(typeEmoney)) {
		dataReq.setDestinationType(DestinationTypeEnum.PREOVO.name());
	    }
	    
	    CommonResponse resSaveFav = Services.create(MasterModuleService.class).saveToFavouriteCommon(destinationReq).execute().body();
	   //log.debug("res SAVE FAV... "+resSaveFav.toString());
	    if (null != resSaveFav) {
		if (!SUCCESS_CODE.equals(resSaveFav.getCode())) {
		    log.error("Save to favourite Failed");
		    response.setCode(ResponseMessage.INTERNAL_SERVER_ERROR.getCode());
		    response.setMessage(messageUtil.get("error.internal.server", servletRequest.getLocale()));
		} else {
		    ObjectMapper oMapper = new ObjectMapper();
		    Transaction transaction = oMapper.convertValue(resSaveFav.getData(), Transaction.class);
		    log.debug("create");
		    EMoney emoney = new EMoney();
		    emoney.setAmount(resEMoney.getAmount());
		    emoney.setAmountFee(resEMoney.getAmountFee());
		    emoney.setCustomerNumber(resEMoney.getCustomerNumber());
		    emoney.setTotalAmount(resEMoney.getTotalAmount());
		    emoney.setTypeEMoney(resEMoney.getType());
		    emoney.setTransaction(transaction);
		    emoney.setDestination(emoney.getTransaction().getDestination());
		    
		    log.debug("save purchase to EMONEY");
		    eMoneyTransService.save(emoney);
		    log.debug("save OK.");
		    response.setData(emoney.getTransaction().getDestination().getId());
		}
	    }
	} catch (IOException e) {
	    log.error(e.getMessage(), e);
	    response.setCode(ResponseMessage.INTERNAL_SERVER_ERROR.getCode());
	    response.setMessage(messageUtil.get("error.internal.server", servletRequest.getLocale()));
	}
	
	return response;
    }
    
    /**
     * inquiry OVO
     * @param req Inquiry Emoney Request
     * @return
     * @throws IOException
     */
    @PostMapping("/inquiry/OVO")
    public CommonResponse inquiryOVO(@RequestBody CommonRequest<inquiryEMoneyRequest> req) throws IOException {
	CommonResponse response = new CommonResponse();

	String custNo = req.getData().getCustomerNumber();
	String type = req.getData().getType();
	
	// Check Cut Off
	long cutoffId = SystemCutOffEnum.OVO.getId();
	CommonResponse cutOffResponse = Services.create(MasterModuleService.class)
		.checkCutOffStatus(servletRequest.getLocale().getLanguage(), cutoffId).execute().body();
	if (!ResponseMessage.SUCCESS.getCode().equals(cutOffResponse.getCode())) {
	    log.error("Error Cutoff");
	    return cutOffResponse;
	}
	
	//handle minimum and maximum topup
	if(req.getData().getAmount().compareTo(MINIMUM_OVO_AMOUNT) == -1) {
	    log.error("Amount less than minimum");
	    response.setCode(ResponseMessage.INVALID_MIN_LIMIT_AMOUNT.getCode());
	    response.setMessage(messageUtil.get("error.invalid.min.amount", servletRequest.getLocale()));
	    return response;
	}
	if(req.getData().getAmount().compareTo(MAXIMUM_OVO_AMOUNT) == 1) {
	    log.error("Amount greater than minimum");
	    response.setCode(ResponseMessage.INVALID_MAX_LIMIT_AMOUNT.getCode());
	    response.setMessage(messageUtil.get("error.invalid.max.amount", servletRequest.getLocale()));
	    return response;
	}

	String forwardInsCode = env.getProperty("config.emoney.forwarding-institution-code");
	EMoneyInquiryRequest requestInquiry = EMoneyUtils.generateInquiryEMoneyReq(custNo, forwardInsCode,
		servletRequest.getLocale().getLanguage(), type, req.getData().getAmount());
	log.debug("Request inquiry OVO to tibco : " + BkpmUtil.convertToJson(requestInquiry));

	EMoneyInquiryResponse responseInquiry = Services.create(EMoneyModuleService.class).emoneyInquiry(requestInquiry)
		.execute().body();
	log.debug("Response inquiry OVO from tibco : " + BkpmUtil.convertToJson(responseInquiry));
	
	String codeRes = responseInquiry.getRespayment().getResult().getElement39();
	if (codeRes.equals(SUCCESS_CODE)) {
	    log.debug("Inquiry OVO success");
	    
	    //set response inquiry OVO
	    inquiryEMoneyResponse result = new inquiryEMoneyResponse();
	    result = EMoneyUtils.generateInquiryOVOResponse(req.getData(), responseInquiry);
	    
	    response.setCode(ResponseMessage.SUCCESS.getCode());
	    response.setMessage(messageUtil.get("success", servletRequest.getLocale()));
	    response.setData(result);
	} else if (INVALID_AMOUNT.equals(codeRes)) {
	    log.error("Error invallid amount");
	    response.setCode(ResponseMessage.INVALID_AMOUNT.getCode());
	    response.setMessage(messageUtil.get("error.invalid.amount", servletRequest.getLocale()));
	} else if (INVALID_SUBSCRIBER_ID.equals(codeRes)
		|| GIRO_USER_NOT_FOUND.equalsIgnoreCase(codeRes)) {
	    log.error("Error invalid number");
	    response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
	    response.setMessage(messageUtil.get("error.id.emoney.not.found", servletRequest.getLocale()));
	} else if (EMONEY_ACCOUNT_INACTIVE.equals(codeRes)
		|| GIRO_INACTIVE_ACCOUNT.equalsIgnoreCase(codeRes)
		|| GIRO_CLOSED_ACCOUNT.equalsIgnoreCase(codeRes)) {
	    log.error("account inactive");
	    response.setCode(ResponseMessage.ERROR_INACTIVE_BANK_ACCOUNT.getCode());
	    response.setMessage(messageUtil.get("error.inactive.bank.account", servletRequest.getLocale()));
	} else if (BILL_ALREADY_PAID.equalsIgnoreCase(codeRes) ) {
	    log.error("Bill already Paid");
	    response.setCode(ResponseMessage.ERROR_BILL_ALREADY_PAID.getCode());
	    response.setMessage(messageUtil.get("error.emoney.already.paid", servletRequest.getLocale()));
	} else if (GIRO_LIMIT_TRANSFER.equals(codeRes) 
			|| GIRO_OVER_LIMIT.equals(codeRes)) {
	    log.error("exceed limit");
	    response = new CommonResponse();
	    response.setCode(ResponseMessage.LIMIT_TRANSFER_DAY.getCode());
	    response.setMessage(messageUtil.get("error.exceed.limit", servletRequest.getLocale()));
	} else if (GIRO_CUT_OFF.equals(codeRes)) {
	    log.error("Giro cut off");
	    response = new CommonResponse();
	    response.setCode(ResponseMessage.ERROR_CUT_OFF_PLN.getCode());
	    response.setMessage(messageUtil.get("error.cutoff.pln", servletRequest.getLocale()));
	} else if (GIRO_DUPLICATE_DATA.equals(codeRes)) {
	    log.error("Giro Duplicate Data");
	    response = new CommonResponse();
	    response.setCode(ResponseMessage.DUPLICATE_DATA.getCode());
	    response.setMessage(messageUtil.get("error.duplicate.data", servletRequest.getLocale()));
	} else if (GIRO_ACCOUNT_WAS_BLOCKED.equals(codeRes)
			|| GIRO_ACCOUNT_BLOCKED.equals(codeRes)) {
	    log.error("account was blocked");
	    response.setCode(ResponseMessage.CUST_BLOCKED.getCode());
	    response.setMessage(messageUtil.get("error.customer.was.blocked", servletRequest.getLocale()));    
	} else if (SYSTEM_BROKEN_168.equals(codeRes) || SYSTEM_BROKEN_169.equals(codeRes)) {
	    log.error("System error from aranet");
	    response.setCode(ResponseMessage.INTERNAL_SERVER_ERROR.getCode());
	    response.setMessage(messageUtil.get("error.internal.server", servletRequest.getLocale()));
	} else {
	    // TODO throw middleware exception
	    log.error("Error from Aranet with code : "+codeRes);
	    throw new MiddlewareException(codeRes);
	}

	return response;
    }
    
    /**
     * purchase OVO
     * @param req Purchase EMoney Request
     * @return
     * @throws IOException
     */
    @PostMapping("/purchase/OVO")
    public CommonResponse purchaseOVO(@RequestBody CommonRequest<PurchaseEMoneyRequest> req) throws IOException {
	CommonResponse response = new CommonResponse();
	
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
	
	// Check Cut Off
	long cutoffId = SystemCutOffEnum.OVO.getId();
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
	
	CommonResponse verifyPINRes = Services.create(UserModuleService.class).verifyPIN(
		servletRequest.getHeader(HttpHeaders.ACCEPT_LANGUAGE), verifyPINReq).execute().body();
	if(!ResponseMessage.SUCCESS.getCode().equals(verifyPINRes.getCode())) {
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

	String forwardInsCode = env.getProperty("config.emoney.forwarding-institution-code");
	String accType = verifyAccOwnRespObj.getAccountInfo().getAccountType().name();
	
	
	// set request purchase OVO
	EMoneyPurchaseRequest purchaseOVOReq = EMoneyUtils.generatePurchaseEMoneyReq(req.getData(), forwardInsCode, accType);
	log.debug("Request purchase OVO to Tibco : " + BkpmUtil.convertToJson(purchaseOVOReq));
	// call service purchase OVO from tibco
	EMoneyPurchaseResponse purchaseOVORes = Services.create(EMoneyModuleService.class)
		.emoneyPurchase(purchaseOVOReq).execute().body();
	log.debug("Response purchase OVO to Tibco : " + BkpmUtil.convertToJson(purchaseOVORes));
	String codeRes = purchaseOVORes.getRespayment().getResult().getElement39();

	if (SUCCESS_CODE.equals(codeRes)) {
	    // set response purchase OVO
	    PurchaseEMoneyResponse resp = new PurchaseEMoneyResponse();
	    resp = EMoneyUtils.generatePurchaseOVOResponse(req.getData(), purchaseOVORes);

	    // send email
	    CommonResponse userResp = Services.create(UserModuleService.class)
		    .getUserByUsername(req.getData().getUsername()).execute().body();
	    if (null != userResp) {
		oMapper = new ObjectMapper();
		Map<String, Object> res = oMapper.convertValue(userResp.getData(), Map.class);
		Map<String, String> resUser = oMapper.convertValue(res.get("user"), Map.class);
		log.debug("Send email receipt OVO");
		eMoneyService.sendEmailReceiptEMoney(resp, resUser, servletRequest.getLocale());
	    }

	    // save EMoney
	    log.debug("save purchase emoney to DB");
	    CommonResponse saveRes = savePurchaseEMoney(req.getIdentity(), resp);
	    if (SUCCESS_CODE.equals(saveRes.getCode())) {
		log.debug("ID DESTINATION .... " + saveRes.getData().toString());
		resp.setDestinationId(saveRes.getData().toString());
	    }

	    response.setCode(ResponseMessage.SUCCESS.getCode());
	    response.setMessage(messageUtil.get("success", servletRequest.getLocale()));
	    response.setData(resp);
	    log.debug("Success purchase OVO");
	} else if (BILL_ALREADY_PAID.equalsIgnoreCase(codeRes) ) {
	    log.error("Bill already Paid");
	    response.setCode(ResponseMessage.ERROR_BILL_ALREADY_PAID.getCode());
	    response.setMessage(messageUtil.get("error.emoney.already.paid", servletRequest.getLocale()));
	} else if (INVALID_AMOUNT.equals(codeRes)) {
	    log.error("Error invallid amount");
	    response.setCode(ResponseMessage.INVALID_AMOUNT.getCode());
	    response.setMessage(messageUtil.get("error.invalid.amount", servletRequest.getLocale()));
	} else if (EMONEY_NOT_ENOUGH_BALANCE.equals(codeRes)
		|| GIRO_AMOUNT_NOT_ENOUGH_BALANCE.equals(codeRes)
		|| GIRO_ERROR_VALUTA_CODE.equals(codeRes)
		|| GIRO_LIMITED_BALANCE.equals(codeRes)) {
	    log.error("Not enough balance: "+req.getData().getAccountNumber());
	    response.setCode(ResponseMessage.AMOUNT_NOT_ENOUGH.getCode());
	    response.setMessage(messageUtil.get("error.amount.not.enough", servletRequest.getLocale()));
	    return response;
	} else if (EMONEY_ACCOUNT_INACTIVE.equals(codeRes) ) {
	    log.error("account inactive");
	    response.setCode(ResponseMessage.ERROR_INACTIVE_BANK_ACCOUNT.getCode());
	    response.setMessage(messageUtil.get("error.inactive.bank.account", servletRequest.getLocale()));
	} else if (TRANSACTION_PROCESSING.equals(codeRes)) {
	    log.error("Error Transaction still processing");
	    response.setCode(ResponseMessage.TRANSACTION_PROCESSING.getCode());
	    response.setMessage(messageUtil.get("error.transaction.processing", servletRequest.getLocale()));
	} else if (SYSTEM_BROKEN_168.equals(codeRes) || SYSTEM_BROKEN_169.equals(codeRes)) {
	    log.error("System error from aranet");
	    response.setCode(ResponseMessage.INTERNAL_SERVER_ERROR.getCode());
	    response.setMessage(messageUtil.get("error.internal.server", servletRequest.getLocale()));
	} else {
	    log.error("Error from Aranet with code : " + codeRes);
	    throw new MiddlewareException(codeRes);
	}
	return response;
    }

    /* Overrides: */
}
