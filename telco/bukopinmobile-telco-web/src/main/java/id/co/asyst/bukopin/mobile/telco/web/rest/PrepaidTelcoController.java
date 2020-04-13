/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.telco.web.rest;

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
import id.co.asyst.bukopin.mobile.master.model.DestinationTypeEnum;
import id.co.asyst.bukopin.mobile.master.model.entity.Transaction;
import id.co.asyst.bukopin.mobile.master.model.payload.DestinationCommonRequest;
import id.co.asyst.bukopin.mobile.master.model.payload.InstitutionRequest;
import id.co.asyst.bukopin.mobile.service.core.MasterModuleService;
import id.co.asyst.bukopin.mobile.service.core.TelcoModuleService;
import id.co.asyst.bukopin.mobile.service.core.UserModuleService;
import id.co.asyst.bukopin.mobile.service.model.payload.pln.GetVerifyPINRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.telco.prepaid.PrepaidTelcoPurchaseRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.telco.prepaid.PrepaidTelcoPurchaseResponse;
import id.co.asyst.bukopin.mobile.telco.core.service.TelcoPrepaidService;
import id.co.asyst.bukopin.mobile.telco.core.service.TelcoService;
import id.co.asyst.bukopin.mobile.telco.core.util.PrepaidTelcoUtils;
import id.co.asyst.bukopin.mobile.master.model.CategoryEnum;
import id.co.asyst.bukopin.mobile.telco.model.PrepaidTelcoEnum;
import id.co.asyst.bukopin.mobile.telco.model.TelcoTypeEnum;
import id.co.asyst.bukopin.mobile.master.model.TransactionTypeEnum;
import id.co.asyst.bukopin.mobile.telco.model.entity.TelcoPrepaid;
import id.co.asyst.bukopin.mobile.telco.model.payload.InquiryMobileDataPrepaidTelcoRequest;
import id.co.asyst.bukopin.mobile.telco.model.payload.InquiryMobileDataPrepaidTelcoResponse;
import id.co.asyst.bukopin.mobile.telco.model.payload.InquiryPulsaPrepaidTelcoRequest;
import id.co.asyst.bukopin.mobile.telco.model.payload.InquiryPulsaPrepaidTelcolResponse;
import id.co.asyst.bukopin.mobile.telco.model.payload.InstitutionMapper;
import id.co.asyst.bukopin.mobile.telco.model.payload.MobileDataSelectionResponse;
import id.co.asyst.bukopin.mobile.telco.model.payload.PaymentPrepaidTelcoRequest;
import id.co.asyst.bukopin.mobile.telco.model.payload.PaymentPrepaidTelcoResponse;
import id.co.asyst.bukopin.mobile.telco.model.payload.PrefixTelcoDetailMapper;
import id.co.asyst.bukopin.mobile.telco.model.payload.PrefixTelcoMapper;
import id.co.asyst.bukopin.mobile.telco.model.payload.PrefixTelcoRequest;
import id.co.asyst.bukopin.mobile.telco.model.payload.PulsaSelectionResponse;
import id.co.asyst.bukopin.mobile.user.model.payload.VerifyAccountOwnerRequest;
import id.co.asyst.bukopin.mobile.user.model.payload.VerifyAccountOwnerResponse;
import id.co.asyst.foundation.service.connector.Services;

/**
 * REST Controller for Prepaid telco
 * 
 * @author Kartika Dwi H
 * @version $Revision$, Jan 30, 2020
 * @since 1.1.Alpha1
 */
@RestController
@RequestMapping("/telco/prepaid")
public class PrepaidTelcoController {
    /* Constants: */
    private final Logger log = LoggerFactory.getLogger(PrepaidTelcoController.class);
    private final String PREPAID_TYPE = "PRE";
    
    private static final String SUCCESS_CODE = "000";
    private static final String ARANET_TIME_OUT_CODE = "999";
    private static final String INVALID_AMOUNT = "113";
    private static final String PHONENUMBER_NOT_FOUND = "114";
    private static final String VOUCHER_OUT_OF_STOCK = "170";
    private static final String ACCOUNT_WAS_BLOCKED = "179";
    private static final String PHONE_NUMBER_EXPIRED = "181";
    private static final String SYSTEM_BROKEN_189 = "189";
    private static final String SYSTEM_BROKEN_196 = "196";
    private static final String AMOUNT_NOT_ENOUGH_BALANCE = "851";
    private static final String PASSIVE_ACCOUNT = "839";
    private DateFormat paymentTelkomselDate = new SimpleDateFormat("dd MMM yyyy");
    
    
    
    /* Attributes: */
    
    
    /**
     * telco prepaid service
     */
    @Autowired
    TelcoPrepaidService telcoPrepaidService;
    
    
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
    
    /**
     * Telco service
     */
    @Autowired
    private TelcoService telcoService;
    
    
    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */

    /* Getters & setters for transient attributes: */

    /* Functionalities: */
    /**
     * get list of mobile data and pulsa
     * 
     * @param req 
     * 
     * @return Response status and list of mobile data and pulsa
     */
    
	@PostMapping("/listMobileDataPulsa")
    @ResponseStatus(HttpStatus.OK)
	public CommonResponse listMobileDataAndPulsa(@Valid @RequestBody CommonRequest<PrefixTelcoRequest> request)
		    throws DatatypeConfigurationException {		
		CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(),
			messageUtil.get("success", httpServletRequest.getLocale()));		
		PrefixTelcoRequest req =request.getData();
		
		if(!(req.getPhoneNumber().length()<4)) {
			req.setType(PREPAID_TYPE);
			request.setData(req);
			
			try {
				CommonResponse prefixTelcoRes = Services.create(MasterModuleService.class)
						.findByPrefixNoAndType(request).execute().body();
				

				if (!ResponseMessage.SUCCESS.getCode().equals(prefixTelcoRes.getCode())) {
					// response not success
					return prefixTelcoRes;
				}
			    
				log.debug("RESPONSE : "+prefixTelcoRes.toString());
				
				ObjectMapper mapper = new ObjectMapper();	
				PrefixTelcoMapper prefixRes = mapper.convertValue(prefixTelcoRes.getData(), PrefixTelcoMapper.class);
				log.debug("prefixTelcoRes: "+prefixRes.toString());
				response.setData(prefixRes);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		} else {
			log.error("Phone number length less than 4");
		    response.setCode(ResponseMessage.INVALID_INPUT.getCode());
		    response.setMessage(messageUtil.get("error.invalid.input", httpServletRequest.getLocale()));
		    return response;
		}
		
		
		return response;
	}
    
	/**
     * inquiry pulsa
     * 
     * @param request 
     * 
     * @return Response status and inquiry pulsa's data
     */
	@PostMapping("/inquiryPulsa")
    @ResponseStatus(HttpStatus.OK)
	public CommonResponse inquiryPulsa(@Valid @RequestBody CommonRequest<InquiryPulsaPrepaidTelcoRequest> request)
		    throws DatatypeConfigurationException {		
		CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(),
			messageUtil.get("success", httpServletRequest.getLocale()));				
		InquiryPulsaPrepaidTelcolResponse res = new InquiryPulsaPrepaidTelcolResponse();
		
		if(PrepaidTelcoEnum.PREPAID.getName().equalsIgnoreCase(request.getData().getInstitutionType())) {
			res.setAdminFee(request.getData().getAdminFee());
			res.setAmount(request.getData().getAmount());
			res.setIdPrefix(request.getData().getIdPrefix());
			res.setpGroup(request.getData().getpGroup());
			res.setPhoneNumber(request.getData().getPhoneNumber());
			res.setProvider(request.getData().getProvider());
			BigDecimal totalAmount = request.getData().getAmount().add(request.getData().getAdminFee());
			res.setTotalAmount(totalAmount);
			res.setInstitutionType(request.getData().getInstitutionType());
			
			response.setData(res);
		} else {
			log.error("invalid institution type");
		    response.setCode(ResponseMessage.INVALID_INPUT.getCode());
		    response.setMessage(messageUtil.get("error.invalid.input", httpServletRequest.getLocale()));
		    return response;
		}
		
		return response;
	}
    
	/**
     * inquiry mobile data
     * 
     * @param request 
     * 
     * @return Response status and inquiry mobile data's data
     */
	@PostMapping("/inquiryMobileData")
    @ResponseStatus(HttpStatus.OK)
	public CommonResponse inquiryMobileData(@Valid @RequestBody CommonRequest<InquiryMobileDataPrepaidTelcoRequest> request)
		    throws DatatypeConfigurationException {		
		CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(),
			messageUtil.get("success", httpServletRequest.getLocale()));	
		InquiryMobileDataPrepaidTelcoResponse res = new InquiryMobileDataPrepaidTelcoResponse();
		
		if(PrepaidTelcoEnum.PAKET_DATA.getName().equalsIgnoreCase(request.getData().getInstitutionType())) {
			if("TELKOMSEL".equalsIgnoreCase(request.getData().getpGroup())) {
				res.setAdminFee(request.getData().getAdminFee());
				res.setAmount(request.getData().getAmount());
				res.setIdPrefix(request.getData().getIdPrefix());
				res.setpGroup(request.getData().getpGroup());
				res.setPhoneNumber(request.getData().getPhoneNumber());
				res.setProvider(request.getData().getProvider());
				res.setPackageCode(request.getData().getPackageCode());
				res.setTitle(request.getData().getTitle());
				BigDecimal totalAmount = request.getData().getAmount().add(request.getData().getAdminFee());
				res.setTotalAmount(totalAmount);
				res.setInstitutionType(request.getData().getInstitutionType());
			}else {
				//besides Telkomsel
			}
			
			response.setData(res);
		} else {
			log.error("invalid institution type");
		    response.setCode(ResponseMessage.INVALID_INPUT.getCode());
		    response.setMessage(messageUtil.get("error.invalid.input", httpServletRequest.getLocale()));
		    return response;
		}
		
		return response;
	}
	

	
	/**
     * payment pulsa and mobile data
     * 
     * @param request 
     * 
     * @return Response status and payment's data
     */
	@SuppressWarnings("unchecked")
	@PostMapping("/paymentMobileDataPulsa")
    private CommonResponse purchasePrepaidTelcoResult(@Valid @RequestBody CommonRequest<PaymentPrepaidTelcoRequest> req) throws IOException, MessagingException {
	
	CommonResponse response = new CommonResponse();
	
	// validate institution type
	if(!PrepaidTelcoEnum.PAKET_DATA.getName().equalsIgnoreCase(req.getData().getInstitutionType()) && 
			!PrepaidTelcoEnum.PREPAID.getName().equalsIgnoreCase(req.getData().getInstitutionType())) {
		log.error("invalid institution type");
	    response.setCode(ResponseMessage.INVALID_INPUT.getCode());
	    response.setMessage(messageUtil.get("error.invalid.input", httpServletRequest.getLocale()));
	    return response;
	}
	
	
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
	

	String forwardInsCode = env.getProperty("config.forwarding-institution-code");
	Identity identity = PrepaidTelcoUtils.generateIdentity();
	String accType = resAccount.getAccountInfo().getAccountType().name();
	
	// get institution data
	InstitutionRequest insReq = new InstitutionRequest();
	insReq.setIdPrefix(req.getData().getIdPrefix());
	insReq.setInstitutionType(req.getData().getInstitutionType());
	CommonRequest<InstitutionRequest> ins = new CommonRequest<InstitutionRequest>();
	ins.setData(insReq);
	CommonResponse institutionRes = Services.create(MasterModuleService.class)
			.findByPrefixIdAndInstitutionType(ins).execute().body();
	if (!ResponseMessage.SUCCESS.getCode().equals(institutionRes.getCode())) {
		// response not success
		return institutionRes;
	}
    
	log.debug("RESPONSE : "+institutionRes.toString());	
	ObjectMapper mapper = new ObjectMapper();	
	InstitutionMapper institution = mapper.convertValue(institutionRes.getData(), InstitutionMapper.class);
	log.debug("institutionRes: "+institution.toString());
	response.setData(institution);
	
		
	//generate purchase request
	PrepaidTelcoPurchaseRequest reqPurchaseTelkomselAranet = PrepaidTelcoUtils.generatePurchasePrepaidTelcoReq(
			req.getData(), institution, forwardInsCode, req.getData().getpGroup(), accType);
	log.debug("request to tibco : " + BkpmUtil.convertToJson(reqPurchaseTelkomselAranet));
	
	PrepaidTelcoPurchaseResponse resPurchaseTelkomselAranet = new PrepaidTelcoPurchaseResponse();
	resPurchaseTelkomselAranet = Services.create(TelcoModuleService.class)
	    	.purchasePrepaidTelco(reqPurchaseTelkomselAranet).execute().body();
	log.debug("response from tibco: " + BkpmUtil.convertToJson(resPurchaseTelkomselAranet));
	
	
	String codeRes = resPurchaseTelkomselAranet.getRespayment().getResult().getElement39();
	//String postRes = resPurchaseEMoneyAranet.getRespayment().getResult().getElement121().substring(0, 3);
	
	
	PaymentPrepaidTelcoResponse purchaseTelkomselRes = new PaymentPrepaidTelcoResponse();
	if(SUCCESS_CODE.equals(codeRes)) {
	    log.info("Purchase Prepaid Telco Success");
	   // String dataResp = resPurchaseEMoneyAranet.getRespayment().getResult().getElement48();
	   // String footNote = resPurchaseEMoneyAranet.getRespayment().getResult().getElement63();
	    String element61 = resPurchaseTelkomselAranet.getRespayment().getResult().getElement61();
	    String elmFee = resPurchaseTelkomselAranet.getRespayment().getResult().getElement28();
	    String elmAmt = resPurchaseTelkomselAranet.getRespayment().getResult().getElement4();
	    String element122 = resPurchaseTelkomselAranet.getRespayment().getResult().getElement122();
	    
	    Integer fee = Integer.valueOf(elmFee.substring(0,elmFee.length()-2));
	    Integer amt = Integer.valueOf(elmAmt.substring(0,elmAmt.length()-2));
	    
	    purchaseTelkomselRes.setAccountNumber(req.getData().getAccountNumber());
	    purchaseTelkomselRes.setAdminFee(new BigDecimal(fee.toString()));
	    purchaseTelkomselRes.setAmount(new BigDecimal(amt.toString()));
	    
	    StringBuilder time = new StringBuilder(resPurchaseTelkomselAranet.getRespayment().getResult().getElement12().substring(0,4));
	    time.insert(2, ":");
	    
	    String date = paymentTelkomselDate.format(new Date());
	    purchaseTelkomselRes.setDateTime(date.concat(" - ").concat(time.toString()));
	    
	    purchaseTelkomselRes.setpGroup(req.getData().getpGroup());
	    purchaseTelkomselRes.setPhoneNumber(element61.substring(0, 13).trim());
	    purchaseTelkomselRes.setReferenceNumber(element122.substring(144, 159).trim());
	    
	    
	    String fullName = "";
	    String middleName = resAccount.getUser().getMiddleName() != null ? resAccount.getUser().getMiddleName() : "";
	    String lastName = resAccount.getUser().getLastName() != null ? resAccount.getUser().getLastName() : "";
	    fullName = resAccount.getUser().getFirstName() + " " + middleName + " " + lastName;
	    
	    purchaseTelkomselRes.setSubscriberName(fullName); 
	    
	    purchaseTelkomselRes.setTotalAmount(purchaseTelkomselRes.getAmount().add(purchaseTelkomselRes.getAdminFee()));
	    purchaseTelkomselRes.setUsername(req.getData().getUsername());
	    purchaseTelkomselRes.setProvider(req.getData().getProvider());
	    purchaseTelkomselRes.setInstitutionType(req.getData().getInstitutionType());
	    purchaseTelkomselRes.setTitle(req.getData().getTitle());
	    
	    
	    //resAccount.getUser().getFirstName()
	    
	    response.setCode(ResponseMessage.SUCCESS.getCode());
	    response.setMessage(messageUtil.get("success", httpServletRequest.getLocale()));
	    response.setData(purchaseTelkomselRes);
	    
	    
	  //send email receipt and save to favorite	
		CommonResponse getUser = Services.create(UserModuleService.class).getUserByUsername(req.getData().getUsername())
				.execute().body();
		if(null!=getUser && (SUCCESS_CODE.equals(codeRes))) {
		    log.debug("Send Email Prepaid Telco.. ");
		    oMapper = new ObjectMapper();
		    Map<String, Object> res = oMapper.convertValue(getUser.getData(), Map.class);
		    Map<String, String> resUser = oMapper.convertValue(res.get("user"), Map.class);
		    log.debug("email : "+resUser.get("email"));
		    telcoService.sendEmailReceiptPrepaidTelco(purchaseTelkomselRes, resUser, httpServletRequest.getLocale());
		    //log.debug("responseeeee "+response);
		    
		   // if(SUCCESS_CODE.equals(response.getCode())){
			//response.setData(purchaseEMoneyRes);
		   // }
		    
		    // save prepaid telco
		    CommonResponse saveRes = savePurchaseTelcoPrepaid(identity,purchaseTelkomselRes);
		    //log.debug("save RES EMoney.... "+saveRes.toString());
		    if(SUCCESS_CODE.equals(saveRes.getCode())) {
		    	//log.debug("ID DESTINATION .... "+saveRes.getData().toString());
		    	purchaseTelkomselRes.setIdDestination(Long.parseLong(saveRes.getData().toString()));
			response.setData(purchaseTelkomselRes);
		    }
		} else {
			log.error("SUbscriber not found/invalid");
			response = new CommonResponse();
		    response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
		    response.setMessage(messageUtil.get("error.id.emoney.not.found", httpServletRequest.getLocale()));
		}
	} else if (INVALID_AMOUNT.equals(codeRes)) {
	    log.error("payment prepaid telco Invalid Amount");
	    response = new CommonResponse();
	    response.setCode(ResponseMessage.INVALID_AMOUNT.getCode());
	    response.setMessage(messageUtil.get("error.invalid.amount", httpServletRequest.getLocale()));
	} else if (PHONENUMBER_NOT_FOUND.equals(codeRes)) {
	    log.error("prepaid telco - phone number not found");
	    response = new CommonResponse();
	    response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
	    response.setMessage(messageUtil.get("error.phone.number.not.found", httpServletRequest.getLocale()));
	}else if (VOUCHER_OUT_OF_STOCK.equals(codeRes)) {
	    log.error("prepaid telco - Voucher out of stock");
	    response = new CommonResponse();
	    response.setCode(ResponseMessage.ERROR_VOUCHER_OUT_OF_STOCK.getCode());
	    response.setMessage(messageUtil.get("error.voucher.out.of.stock", httpServletRequest.getLocale()));
	} else if(ACCOUNT_WAS_BLOCKED.equals(codeRes)) {
	    log.error("prepaid telco - blocked account");
	    response = new CommonResponse();
	    response.setCode(ResponseMessage.CUST_BLOCKED.getCode());
	    response.setMessage(messageUtil.get("error.account.was.blocked", httpServletRequest.getLocale()));
	    return response;
	} else if (PHONE_NUMBER_EXPIRED.equalsIgnoreCase(codeRes) ) {
	    log.error("Prepaid telco - phone number expired");
	    response = new CommonResponse();
	    response.setCode(ResponseMessage.PHONE_NUMBER_EXPIRED.getCode());
	    response.setMessage(messageUtil.get("error.phone.number.expired", httpServletRequest.getLocale()));
	} else if (AMOUNT_NOT_ENOUGH_BALANCE.equals(codeRes)) {
	    log.error("not enough balance");
	    response = new CommonResponse();
	    response.setCode(ResponseMessage.AMOUNT_NOT_ENOUGH.getCode());
	    response.setMessage(messageUtil.get("error.amount.not.enough", httpServletRequest.getLocale()));
	} else if (PASSIVE_ACCOUNT.equals(codeRes)) {
	    log.error("passive account");
	    response = new CommonResponse();
	    response.setCode(ResponseMessage.ERROR_INACTIVE_BANK_ACCOUNT.getCode());
	    response.setMessage(messageUtil.get("error.inactive.bank.account", httpServletRequest.getLocale()));
	} else if (SYSTEM_BROKEN_189.equalsIgnoreCase(codeRes) || SYSTEM_BROKEN_196.equalsIgnoreCase(codeRes) ) {
	    log.error("prepaid telco - System failure");
	    response = new CommonResponse();
	    response.setCode(ResponseMessage.ERROR_SYSTEM_FAILURE.getCode());
	    response.setMessage(messageUtil.get("error.system.failure", httpServletRequest.getLocale()));
	}	
	//else if (SYSTEM_BROKEN_168.equals(codeRes) || SYSTEM_BROKEN_169.equals(codeRes)) {
	//    log.error("System was broken");
	//    response.setCode(ResponseMessage.ERROR_ADVICE_FAILED.getCode());
	//    response.setMessage(messageUtil.get("error.internal.server", servletRequest.getLocale()));
	//}
	else {
		log.error("Error (result) Prepaid Telco : "+codeRes);
	    // Throw middleware error
	    throw new MiddlewareException(codeRes);
	}
	
	return response;
    }
	

	
	/**
     * save payment data
     * 
     * @param resTelco
     * 
     * @return Response status and payment's data
     */
	public CommonResponse savePurchaseTelcoPrepaid(Identity identity, PaymentPrepaidTelcoResponse resTelco) {
		CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(), 
			messageUtil.get("success", httpServletRequest.getLocale()));
		String providerGroup = resTelco.getpGroup();
		String provider = resTelco.getProvider();
		try {
		    // save to Destination and Transaction
		    
		    // Prepare data request to save transaction
			DestinationCommonRequest dataReq = new DestinationCommonRequest();
			dataReq.setCategoryId(CategoryEnum.PULSA.getId());
			dataReq.setUsername(resTelco.getUsername());
			dataReq.setSubscriberNumber(resTelco.getPhoneNumber());
			dataReq.setSubscriberName(resTelco.getSubscriberName());
			dataReq.setTransactionType(TransactionTypeEnum.TELCOPRE.name());
			dataReq.setReference(resTelco.getReferenceNumber());
			dataReq.setAccountNumber(resTelco.getAccountNumber());
			dataReq.setTotalAmount(resTelco.getTotalAmount());
			
			// destination type by provider
		    if (DestinationTypeEnum.PRESIMPATI.name().equalsIgnoreCase(provider)) {
			dataReq.setDestinationType(DestinationTypeEnum.PRESIMPATI.name());
		    } else if (DestinationTypeEnum.PREAS.name().equalsIgnoreCase(provider)) {
			dataReq.setDestinationType(DestinationTypeEnum.PREAS.name());
		    } else if (DestinationTypeEnum.PREIM3.name().equalsIgnoreCase(provider)) {
			dataReq.setDestinationType(DestinationTypeEnum.PREIM3.name());
		    } else if (DestinationTypeEnum.PREMENTARI.name().equalsIgnoreCase(provider)) {
			dataReq.setDestinationType(DestinationTypeEnum.PREMENTARI.name());
		    }else if (DestinationTypeEnum.PRESMARTFREN.name().equalsIgnoreCase(provider)) {
			dataReq.setDestinationType(DestinationTypeEnum.PRESMARTFREN.name());
		    }else if (DestinationTypeEnum.PRETRI.name().equalsIgnoreCase(provider)) {
			dataReq.setDestinationType(DestinationTypeEnum.PRETRI.name());
		    }else if ("XL".equalsIgnoreCase(provider)) {
			dataReq.setDestinationType(DestinationTypeEnum.PREXLAXIS.name());
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
			    TelcoPrepaid telcoPrepaid = new TelcoPrepaid();
			    telcoPrepaid.setAmount(resTelco.getAmount());
			    telcoPrepaid.setAdminFee(resTelco.getAdminFee());
			    telcoPrepaid.setAccountNumber(resTelco.getAccountNumber());
			    telcoPrepaid.setTotalAmount(resTelco.getTotalAmount());
			    
			  //set telco type
			    String typeTelco ="";
			    if("PREPAID".equalsIgnoreCase(resTelco.getInstitutionType())) {
			    	if("TELKOMSEL".equalsIgnoreCase(providerGroup)) {
			    		typeTelco = TelcoTypeEnum.PULSA_TELKOMSEL.name();
			    	} else if("INDOSAT".equalsIgnoreCase(providerGroup)) {
			    		typeTelco = TelcoTypeEnum.PULSA_INDOSAT.name();
			    	} else if("XL".equalsIgnoreCase(providerGroup)) {
			    		typeTelco = TelcoTypeEnum.PULSA_XL.name();
			    	} else if("TRI".equalsIgnoreCase(providerGroup)) {
			    		typeTelco = TelcoTypeEnum.PULSA_TRI.name();
			    	} else if("SMARTFREN".equalsIgnoreCase(providerGroup)) {
			    		typeTelco = TelcoTypeEnum.PULSA_SMARTFREN.name();
			    	}
			    	
			    } else if("PAKET DATA".equalsIgnoreCase(resTelco.getInstitutionType())){
			    	if("TELKOMSEL".equalsIgnoreCase(providerGroup)) {
			    		typeTelco = TelcoTypeEnum.PAKET_DATA_TELKOMSEL.name();
			    	} else if("INDOSAT".equalsIgnoreCase(providerGroup)) {
			    		typeTelco = TelcoTypeEnum.PAKET_DATA_INDOSAT.name();
			    	} else if("XL".equalsIgnoreCase(providerGroup)) {
			    		typeTelco = TelcoTypeEnum.PAKET_DATA_XL.name();
			    	}
			    }
			    
			    telcoPrepaid.setTypeTelco(typeTelco);
			    telcoPrepaid.setTransaction(transaction);
			    telcoPrepaid.setDestination(telcoPrepaid.getTransaction().getDestination());
			    
			    
			    
			    log.debug("save purchase to Prepaid Telco");
			    //log.debug("isinya..... "+telcoPrepaid.toString());
			    telcoPrepaidService.save(telcoPrepaid);
			    log.debug("save OK.");
			    response.setData(telcoPrepaid.getTransaction().getDestination().getId());
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
