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

import org.json.JSONObject;
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

import com.fasterxml.jackson.core.type.TypeReference;
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
import id.co.asyst.bukopin.mobile.master.model.DestinationTypeEnum;
import id.co.asyst.bukopin.mobile.master.model.entity.Transaction;
import id.co.asyst.bukopin.mobile.master.model.payload.DestinationCommonRequest;
import id.co.asyst.bukopin.mobile.master.model.payload.InstitutionRequest;
import id.co.asyst.bukopin.mobile.service.core.MasterModuleService;
import id.co.asyst.bukopin.mobile.service.core.TelcoModuleService;
import id.co.asyst.bukopin.mobile.service.core.TransferModuleService;
import id.co.asyst.bukopin.mobile.service.core.UserModuleService;
import id.co.asyst.bukopin.mobile.service.model.payload.pln.GetVerifyPINRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.telco.prepaid.PrepaidTelcoInquiryPaketRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.telco.prepaid.PrepaidTelcoInquiryPaketResponse;
import id.co.asyst.bukopin.mobile.service.model.payload.telco.prepaid.PrepaidTelcoListPaketRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.telco.prepaid.PrepaidTelcoListPaketResponse;
import id.co.asyst.bukopin.mobile.service.model.payload.telco.prepaid.PrepaidTelcoPurchaseRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.telco.prepaid.PrepaidTelcoPurchaseResponse;
import id.co.asyst.bukopin.mobile.telco.core.service.TelcoPrepaidService;
import id.co.asyst.bukopin.mobile.telco.core.service.TelcoService;
import id.co.asyst.bukopin.mobile.telco.core.util.PrepaidTelcoUtils;
import id.co.asyst.bukopin.mobile.telco.core.util.TelcoUtils;
import id.co.asyst.bukopin.mobile.master.model.CategoryEnum;
import id.co.asyst.bukopin.mobile.telco.model.PrepaidTelcoEnum;
import id.co.asyst.bukopin.mobile.telco.model.TelcoTypeEnum;
import id.co.asyst.bukopin.mobile.master.model.TransactionTypeEnum;
import id.co.asyst.bukopin.mobile.telco.model.entity.TelcoData;
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
import id.co.asyst.bukopin.mobile.transfer.model.limitUserDailyClass;
import id.co.asyst.bukopin.mobile.user.model.payload.VerifyAccountOwnerRequest;
import id.co.asyst.bukopin.mobile.user.model.payload.VerifyAccountOwnerResponse;
import id.co.asyst.bukopin.mobile.user.model.payload.VerifyPhoneOwnerRequest;
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
    private static final String INVALID_TRANSACTION = "112";
    private static final String INVALID_AMOUNT = "113";
    private static final String PHONENUMBER_NOT_FOUND = "114";
    private static final String VOUCHER_OUT_OF_STOCK = "170";
    private static final String ACCOUNT_WAS_BLOCKED = "179";
    private static final String PHONE_NUMBER_EXPIRED = "181";
    private static final String SYSTEM_BROKEN_189 = "189";
    private static final String SYSTEM_BROKEN_196 = "196";
    private static final String AMOUNT_NOT_ENOUGH_BALANCE = "851";
    private static final String PASSIVE_ACCOUNT = "839";

    // giro error handling
    private static final String GIRO_AMOUNT_NOT_ENOUGH_BALANCE = "805";
    private static final String GIRO_LIMIT_TRANSFER = "802";
    private static final String GIRO_ACCOUNT_WAS_BLOCKED = "806";
    private static final String GIRO_OVER_LIMIT = "808";
    private static final String GIRO_ACCOUNT_BLOCKED = "814";
    private static final String GIRO_CUT_OFF = "818";
    private static final String GIRO_INACTIVE_ACCOUNT = "822";
    private static final String GIRO_USER_NOT_FOUND = "831";
    private static final String GIRO_ACCOUNT_NOT_ACCEPTED = "839";
    private static final String GIRO_DUPLICATE_DATA = "869";
    private static final String GIRO_CLOSED_ACCOUNT = "878";
    private static final String GIRO_ERROR_VALUTA_CODE = "885";
    private static final String GIRO_LIMITED_BALANCE = "897";

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
    
    /**
     * Bkpm service
     */
    @Autowired
    private BkpmService commonService;

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
     * @throws IOException
     */

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @PostMapping("/listMobileDataPulsa")
    @ResponseStatus(HttpStatus.OK)
    public CommonResponse listMobileDataAndPulsa(@Valid @RequestBody CommonRequest<PrefixTelcoRequest> request)
	    throws DatatypeConfigurationException, IOException {
	CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(),
		messageUtil.get("success", httpServletRequest.getLocale()));

	PrefixTelcoRequest req = request.getData();
	String forwardInsCode = env.getProperty("config.forwarding-institution-code");

	if (!(req.getPhoneNumber().length() < 4)) {
	    String phoneNumber = req.getPhoneNumber();
	    String prefix = req.getPhoneNumber().substring(0, 4);

	    req.setPhoneNumber(prefix);
	    req.setType(PREPAID_TYPE);
	    request.setData(req);

	    CommonResponse prefixTelcoRes = Services.create(MasterModuleService.class).findByPrefixNoAndType(request)
		    .execute().body();

	    if (!ResponseMessage.SUCCESS.getCode().equals(prefixTelcoRes.getCode())) {
		// response not success
		return prefixTelcoRes;
	    }

	    ObjectMapper mapper = new ObjectMapper();
	    PrefixTelcoMapper prefixRes = mapper.convertValue(prefixTelcoRes.getData(), PrefixTelcoMapper.class);
	    if ("XL".equalsIgnoreCase(prefixRes.getPgroup())) {
			if (null != prefixRes.getMobileData()) {
			    InstitutionMapper paketData = new InstitutionMapper();
			    paketData.setAdminFee(prefixRes.getMobileData().getAdminFee());
			    paketData.setCodeArra("013006");
			    paketData.setCodeCbs("013007");
			    paketData.setInstitutionType(prefixRes.getMobileData().getInstitutionType());
			    paketData.setName(prefixRes.getMobileData().getName());
	
			    // generate get list paket data request
			    PrepaidTelcoListPaketRequest reqListPaketXLAranet = PrepaidTelcoUtils
				    .generateInquiryListPaketDataReq(phoneNumber, paketData, forwardInsCode,
					    prefixRes.getPgroup());
			    log.debug("request to tibco (XL) : " + BkpmUtil.convertToJson(reqListPaketXLAranet));
	
			    // get paket data list from tibco
			    PrepaidTelcoListPaketResponse resListPaketDataAranet = new PrepaidTelcoListPaketResponse();
			    resListPaketDataAranet = Services.create(TelcoModuleService.class)
				    .listPaketDataPrepaidTelco(reqListPaketXLAranet).execute().body();
			    log.debug("response from tibco: " + BkpmUtil.convertToJson(resListPaketDataAranet));
	
			    String codeRes = resListPaketDataAranet.getRespayment().getResult().getElement39();
			    if (SUCCESS_CODE.equals(codeRes)) {
					// mapping selection
					String[] select = resListPaketDataAranet.getRespayment().getResult().getElement61()
						.split("\\;");
					List<MobileDataSelectionResponse> selections = new ArrayList<>();
					for (String sel : select) {
					    MobileDataSelectionResponse selection = new MobileDataSelectionResponse();
					    selection.setTitle(sel.substring(3, sel.length()).trim());
					    selection.setDescription("");
					    selection.setPackageCode(sel.substring(0, 3).trim());
					    selection.setAmount(new BigDecimal("0"));
					    selections.add(selection);
					}
					prefixRes.getMobileData().setSelection(selections);
					response.setData(prefixRes);
				
			    } else if (INVALID_TRANSACTION.equals(codeRes)) {
				    log.error("Invalid Transaction");
				    response = new CommonResponse();
				    response.setCode(ResponseMessage.INVALID_TRANSACTION.getCode());
				    response.setMessage(messageUtil.get("error.invalid.transaction", httpServletRequest.getLocale()));
				} else if (INVALID_AMOUNT.equals(codeRes)) {
				    log.error("payment prepaid telco Invalid Amount");
				    response = new CommonResponse();
				    response.setCode(ResponseMessage.INVALID_AMOUNT.getCode());
				    response.setMessage(messageUtil.get("error.invalid.amount", httpServletRequest.getLocale()));
				} else {
				log.error("Error (result) List inq Prepaid Telco : " + codeRes);
				// Throw middleware error
				throw new MiddlewareException(codeRes);
			    }
			}
	    } else {
	    	response.setData(prefixRes);
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
     * @throws IOException
     */
    @PostMapping("/inquiryPulsa")
    @ResponseStatus(HttpStatus.OK)
    public CommonResponse inquiryPulsa(@Valid @RequestBody CommonRequest<InquiryPulsaPrepaidTelcoRequest> request)
	    throws DatatypeConfigurationException, IOException {
	CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(),
		messageUtil.get("success", httpServletRequest.getLocale()));
	InquiryPulsaPrepaidTelcolResponse res = new InquiryPulsaPrepaidTelcolResponse();

	// Check Cut Off
	long cutoffId = TelcoUtils.getCutOffId(request.getData().getpGroup());
	CommonResponse cutOffResponse = Services.create(MasterModuleService.class)
		.checkCutOffStatus(httpServletRequest.getLocale().getLanguage(), cutoffId).execute().body();
	if (!ResponseMessage.SUCCESS.getCode().equals(cutOffResponse.getCode())) {
	    log.error("Error Cutoff");
	    return cutOffResponse;
	}

	if (PrepaidTelcoEnum.PREPAID.getName().equalsIgnoreCase(request.getData().getInstitutionType())) {
	    res.setAdminFee(request.getData().getAdminFee());
	    res.setAmount(request.getData().getAmount());
	    res.setIdPrefix(request.getData().getIdPrefix());
	    res.setpGroup(request.getData().getpGroup());
	    res.setPhoneNumber(request.getData().getPhoneNumber());
	    res.setProvider(request.getData().getProvider());
	    BigDecimal totalAmount = request.getData().getAmount().add(request.getData().getAdminFee());
	    res.setTotalAmount(totalAmount);
	    res.setInstitutionType(request.getData().getInstitutionType());
	    res.setPackageCode(request.getData().getPackageCode());
	    res.setCodeCbs(request.getData().getCodeCbs());
	    res.setProductCode(request.getData().getProductCode());

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
    public CommonResponse inquiryMobileData(
	    @Valid @RequestBody CommonRequest<InquiryMobileDataPrepaidTelcoRequest> request)
	    throws DatatypeConfigurationException, IOException {
	CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(),
		messageUtil.get("success", httpServletRequest.getLocale()));
	String forwardInsCode = env.getProperty("config.forwarding-institution-code");

	// Check Cut Off
	long cutoffId = TelcoUtils.getCutOffId(request.getData().getpGroup());
	CommonResponse cutOffResponse = Services.create(MasterModuleService.class)
		.checkCutOffStatus(httpServletRequest.getLocale().getLanguage(), cutoffId).execute().body();
	if (!ResponseMessage.SUCCESS.getCode().equals(cutOffResponse.getCode())) {
	    log.error("Error Cutoff");
	    return cutOffResponse;
	}

	InquiryMobileDataPrepaidTelcoResponse res = new InquiryMobileDataPrepaidTelcoResponse();

	if (PrepaidTelcoEnum.PAKET_DATA.getName().equalsIgnoreCase(request.getData().getInstitutionType())) {
	    if ("TELKOMSEL".equalsIgnoreCase(request.getData().getpGroup())
		    || "INDOSAT".equalsIgnoreCase(request.getData().getpGroup())) {
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
		res.setProductCode(request.getData().getProductCode());
		res.setCodeCbs(request.getData().getCodeCbs());
		res.setElement11("");
		res.setElement37("");
		res.setElement61("");
		response.setData(res);

	    } else if ("XL".equalsIgnoreCase(request.getData().getpGroup())) {

		// generate get inquiry paket data request
		PrepaidTelcoInquiryPaketRequest reqInqPaketXLAranet = PrepaidTelcoUtils.generateInquiryPaketDataReq(
			request.getData().getPhoneNumber(), request.getData().getProductCode(),
			request.getData().getCodeCbs(), request.getData().getPackageCode(), forwardInsCode);
		log.debug("request inquiry to tibco (XL) : " + BkpmUtil.convertToJson(reqInqPaketXLAranet));

		// get paket data list from tibco
		PrepaidTelcoInquiryPaketResponse resInqPaketDataAranet = new PrepaidTelcoInquiryPaketResponse();
		resInqPaketDataAranet = Services.create(TelcoModuleService.class)
			.inquiryPaketDataPrepaidTelco(reqInqPaketXLAranet).execute().body();
		log.debug("response from tibco: " + BkpmUtil.convertToJson(resInqPaketDataAranet));

		String codeRes = resInqPaketDataAranet.getRespayment().getResult().getElement39();
		if (SUCCESS_CODE.equals(codeRes)) {
		    String element61 = resInqPaketDataAranet.getRespayment().getResult().getElement61();
		    String packageCode = element61.substring(13, 19).trim();
		    String title = element61.substring(19, 49).trim();
		    String amount = element61.substring(49, 61);
		    String adminFee = element61.substring(61, 69);

		    res.setPhoneNumber(request.getData().getPhoneNumber());
		    res.setPackageCode(packageCode);
		    res.setTitle(title);
		    res.setProvider(request.getData().getProvider());
		    res.setpGroup(request.getData().getpGroup());
		    res.setIdPrefix(request.getData().getIdPrefix());
		    res.setAmount(new BigDecimal(amount));
		    res.setAdminFee(new BigDecimal(adminFee));
		    res.setTotalAmount(res.getAmount().add(res.getAdminFee()));
		    res.setInstitutionType(request.getData().getInstitutionType());
		    res.setElement11(resInqPaketDataAranet.getRespayment().getResult().getElement11());
		    res.setElement37(resInqPaketDataAranet.getRespayment().getResult().getElement37());
		    res.setElement61(element61);
		    res.setProductCode(request.getData().getProductCode());
		    res.setCodeCbs(request.getData().getCodeCbs());
		    response.setData(res);
		} else if (VOUCHER_OUT_OF_STOCK.equals(codeRes)) {
		    log.error("prepaid telco - Voucher out of stock");
		    response = new CommonResponse();
		    response.setCode(ResponseMessage.ERROR_VOUCHER_OUT_OF_STOCK.getCode());
		    response.setMessage(messageUtil.get("error.voucher.out.of.stock", httpServletRequest.getLocale()));
		} else if (PHONENUMBER_NOT_FOUND.equals(codeRes) || GIRO_USER_NOT_FOUND.equals(codeRes)) {
		    log.error("prepaid telco - phone number not found");
		    response = new CommonResponse();
		    response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
		    response.setMessage(messageUtil.get("error.number.not.found", httpServletRequest.getLocale()));
		} else if (INVALID_AMOUNT.equals(codeRes)) {
		    log.error("payment prepaid telco Invalid Amount");
		    response = new CommonResponse();
		    response.setCode(ResponseMessage.INVALID_AMOUNT.getCode());
		    response.setMessage(messageUtil.get("error.invalid.amount", httpServletRequest.getLocale()));
		} else if (INVALID_TRANSACTION.equals(codeRes)) {
		    log.error("Invalid Transaction");
		    response = new CommonResponse();
		    response.setCode(ResponseMessage.INVALID_TRANSACTION.getCode());
		    response.setMessage(messageUtil.get("error.invalid.transaction", httpServletRequest.getLocale()));
		} else {
		    log.error("Error (result) Inq Prepaid Telco : " + codeRes);
		    // Throw middleware error
		    throw new MiddlewareException(codeRes);
		}

	    }

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
    public CommonResponse purchasePrepaidTelcoResult(@Valid @RequestBody CommonRequest<PaymentPrepaidTelcoRequest> req) throws IOException, MessagingException {
	
	CommonResponse response = new CommonResponse();

	// validate institution type
	if (!PrepaidTelcoEnum.PAKET_DATA.getName().equalsIgnoreCase(req.getData().getInstitutionType())
		&& !PrepaidTelcoEnum.PREPAID.getName().equalsIgnoreCase(req.getData().getInstitutionType())) {
	    log.error("invalid institution type");
	    response.setCode(ResponseMessage.INVALID_INPUT.getCode());
	    response.setMessage(messageUtil.get("error.invalid.input", httpServletRequest.getLocale()));
	    return response;
	}

	if (!commonService.verifyLocalIp(httpServletRequest)) {
		log.debug("Verify phone owner");
	    // Validate Token and Phone Owner
	    CommonRequest<VerifyPhoneOwnerRequest> phoneReq = new CommonRequest<>();
	    VerifyPhoneOwnerRequest phoneReqData = new VerifyPhoneOwnerRequest();
	    phoneReqData.setUsername(req.getData().getUsername());
	    phoneReqData.setToken(httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION));
	    phoneReqData.setPhoneIdentity(httpServletRequest.getHeader(BkpmConstants.HTTP_HEADER_DEVICE_ID));
	    phoneReq.setData(phoneReqData);
	    CommonResponse resPhone = Services.create(UserModuleService.class).verifyPhoneOwner(phoneReq).execute()
		    .body();
	    if (!ResponseMessage.SUCCESS.getCode().equals(resPhone.getCode())) {
		log.error("Validate Token and Phone owner error..");
		return resPhone;
	    }
	}
	
	
	ObjectMapper oMapper = new ObjectMapper();
	// -- Validate Account Number's Owner
	VerifyAccountOwnerRequest verifyAccountData = new VerifyAccountOwnerRequest(req.getData().getUsername(),
		req.getData().getAccountNumber());
	log.debug("Verify account owner: {}", BkpmUtil.convertToJson(verifyAccountData));
	CommonRequest<VerifyAccountOwnerRequest> verifyAccountRequest = new CommonRequest<>();
	verifyAccountRequest.setIdentity(req.getIdentity());
	verifyAccountRequest.setData(verifyAccountData);
	CommonResponse accountResponse = Services.create(UserModuleService.class)
		.verifyAccountOwner(verifyAccountRequest).execute().body();
	if (!ResponseMessage.SUCCESS.getCode().equals(accountResponse.getCode())) {
	    log.error("Error verify account owner: " + req.getData().getAccountNumber());
	    // response not success
	    return accountResponse;
	}
	VerifyAccountOwnerResponse resAccount = oMapper.convertValue(accountResponse.getData(),
		VerifyAccountOwnerResponse.class);
	if (!resAccount.isValid()) {
	    log.error("User and Account Info didn't match: " + req.getData().getAccountNumber());
	    response.setCode(ResponseMessage.DATA_NOT_MATCH.getCode());
	    response.setMessage(messageUtil.get("error.invalid.user.accountno", httpServletRequest.getLocale()));
	    return response;
	}
	// User user = resAccount.getUser();
	
	// Check Cut Off
	long cutoffId = TelcoUtils.getCutOffId(req.getData().getpGroup());
	CommonResponse cutOffResponse = Services.create(MasterModuleService.class)
		.checkCutOffStatus(httpServletRequest.getLocale().getLanguage(), cutoffId).execute().body();
	if (!ResponseMessage.SUCCESS.getCode().equals(cutOffResponse.getCode())) {
	    log.error("Error Cutoff");
	    return cutOffResponse;
	}

	// -- Prepare verify PIN
	GetVerifyPINRequest pinRequest = new GetVerifyPINRequest();
	pinRequest.setPin(req.getData().getPin());
	pinRequest.setUsername(req.getData().getUsername());
	CommonRequest<GetVerifyPINRequest> commonPinRequest = new CommonRequest<>();
	commonPinRequest.setIdentity(req.getIdentity());
	commonPinRequest.setData(pinRequest);
	// Verify PIN
	CommonResponse pinResponse = Services.create(UserModuleService.class)
		.verifyPIN(httpServletRequest.getHeader(HttpHeaders.ACCEPT_LANGUAGE), commonPinRequest).execute()
		.body();
	if (!ResponseMessage.SUCCESS.getCode().equals(pinResponse.getCode())) {
	    // response not success
	    return pinResponse;
	}
	
	String forwardInsCode = env.getProperty("config.forwarding-institution-code");
	Identity identity = PrepaidTelcoUtils.generateIdentity();
	String accType = resAccount.getAccountInfo().getAccountType().name();
	
	// cek limit harian
			CommonRequest<limitUserDailyClass> lmtDl = new CommonRequest<>();
			limitUserDailyClass lmtDLClass = new limitUserDailyClass();
			lmtDLClass.setAccNo(req.getData().getAccountNumber());
			lmtDLClass.setUsername(req.getData().getUsername());
			lmtDLClass.setAmount(new BigDecimal(req.getData().getAmount() + ""));
			lmtDLClass.setJenis("purchase");
			lmtDl.setData(lmtDLClass);
			lmtDl.setIdentity(req.getIdentity());
			CommonResponse resLmtDL = Services.create(TransferModuleService.class)
					.verifydailylimit(lmtDl).execute().body();
			log.debug("log dari limit harian prepaid telco " + lmtDl + " response "
					+ resLmtDL);
			if (resLmtDL.getCode().equals("000")) {
				// generate purchase request
				PrepaidTelcoPurchaseRequest reqPurchaseTelkomselAranet = PrepaidTelcoUtils
					.generatePurchasePrepaidTelcoReq(req.getData(), forwardInsCode, req.getData().getpGroup(), accType);
				log.debug("request to tibco : " + BkpmUtil.convertToJson(reqPurchaseTelkomselAranet));

				PrepaidTelcoPurchaseResponse resPurchaseTelkomselAranet = new PrepaidTelcoPurchaseResponse();
				resPurchaseTelkomselAranet = Services.create(TelcoModuleService.class)
					.purchasePrepaidTelco(reqPurchaseTelkomselAranet).execute().body();
				log.debug("response from tibco: " + BkpmUtil.convertToJson(resPurchaseTelkomselAranet));

				String codeRes = resPurchaseTelkomselAranet.getRespayment().getResult().getElement39();
				// String postRes =
				// resPurchaseEMoneyAranet.getRespayment().getResult().getElement121().substring(0,
				// 3);

				PaymentPrepaidTelcoResponse purchaseTelkomselRes = new PaymentPrepaidTelcoResponse();
				if (SUCCESS_CODE.equals(codeRes)) {
				    log.info("Purchase Prepaid Telco Success");
				    //nomor resi bit 61 diambil dari index 59 - 89
				    // String dataResp =
				    // resPurchaseEMoneyAranet.getRespayment().getResult().getElement48();
				    // String footNote =
				    // resPurchaseEMoneyAranet.getRespayment().getResult().getElement63();
				    String refbayar=null;
				    String element61 = resPurchaseTelkomselAranet.getRespayment().getResult().getElement61();
				    String elmFee = resPurchaseTelkomselAranet.getRespayment().getResult().getElement28();
				    String elmAmt = resPurchaseTelkomselAranet.getRespayment().getResult().getElement4();
				    String element122 = resPurchaseTelkomselAranet.getRespayment().getResult().getElement122();
				    String element37 = resPurchaseTelkomselAranet.getRespayment().getResult().getElement37();
				    
				    
				    
				    Integer fee = Integer.valueOf(elmFee.substring(0, elmFee.length() - 2));
				    Integer amt = Integer.valueOf(elmAmt.substring(0, elmAmt.length() - 2));

				    purchaseTelkomselRes.setAccountNumber(req.getData().getAccountNumber());
				    purchaseTelkomselRes.setAdminFee(new BigDecimal(fee.toString()));
				    purchaseTelkomselRes.setAmount(new BigDecimal(amt.toString()));

				    StringBuilder time = new StringBuilder(
					    resPurchaseTelkomselAranet.getRespayment().getResult().getElement12().substring(0, 4));
				    time.insert(2, ":");

				    String date = paymentTelkomselDate.format(new Date());
				    purchaseTelkomselRes.setDateTime(date.concat(" - ").concat(time.toString()));

				    purchaseTelkomselRes.setpGroup(req.getData().getpGroup());
				    purchaseTelkomselRes.setPhoneNumber(element61.substring(0, 13).trim());
				    purchaseTelkomselRes.setReferenceNumber(element122.substring(144, 159).trim());

				    String fullName = "";
				    String middleName = resAccount.getUser().getMiddleName() != null ? resAccount.getUser().getMiddleName()
					    : "";
				    String lastName = resAccount.getUser().getLastName() != null ? resAccount.getUser().getLastName() : "";
				    fullName = resAccount.getUser().getFirstName() + " " + middleName + " " + lastName;

				    purchaseTelkomselRes.setSubscriberName(fullName);

				    purchaseTelkomselRes
					    .setTotalAmount(purchaseTelkomselRes.getAmount().add(purchaseTelkomselRes.getAdminFee()));
				    purchaseTelkomselRes.setUsername(req.getData().getUsername());
				    purchaseTelkomselRes.setProvider(req.getData().getProvider());
				    purchaseTelkomselRes.setInstitutionType(req.getData().getInstitutionType());
				    purchaseTelkomselRes.setTitle(req.getData().getTitle());
				    if(req.getData().getInstitutionType().equals("Paket Data") && resPurchaseTelkomselAranet.getRespayment().getResult().getElement63().equals("001160")){
				    	refbayar = element61.substring(59, 89).trim();
				    	purchaseTelkomselRes.setRefBayar(refbayar);
					    purchaseTelkomselRes.setRrn(element37);
					    purchaseTelkomselRes.setProdCode(resPurchaseTelkomselAranet.getRespayment().getResult().getElement63());
					    log.debug("element 37 "+element37+" ref bayar "+refbayar);
				    }
				    
				    
				    
				    

				    // resAccount.getUser().getFirstName()

				    response.setCode(ResponseMessage.SUCCESS.getCode());
				    response.setMessage(messageUtil.get("success", httpServletRequest.getLocale()));
				    response.setData(purchaseTelkomselRes);
				    
				    
				    // 	// save limit harian
					log.debug("param save limit " + resLmtDL.getData());
					CommonResponse prosesLimit = Services
							.create(TransferModuleService.class)
							.prosesdailyLimit(resLmtDL.getData()).execute().body();
					log.debug("log dari proses simpan limit " + prosesLimit);
					
					

				    // send email receipt and save to favorite
				    CommonResponse getUser = Services.create(UserModuleService.class)
					    .getUserByUsername(req.getData().getUsername()).execute().body();
				    if (null != getUser && (SUCCESS_CODE.equals(codeRes))) {
					log.debug("Send Email Prepaid Telco.. ");
					oMapper = new ObjectMapper();
					Map<String, Object> res = oMapper.convertValue(getUser.getData(), Map.class);
					Map<String, String> resUser = oMapper.convertValue(res.get("user"), Map.class);
					log.debug("email : " + resUser.get("email"));
					telcoService.sendEmailReceiptPrepaidTelco(purchaseTelkomselRes, resUser,
						httpServletRequest.getLocale());
					log.debug("responseeeee "+response);

					// if(SUCCESS_CODE.equals(response.getCode())){
					// response.setData(purchaseEMoneyRes);
					// }

					// save prepaid telco
					CommonResponse saveRes = savePurchaseTelcoPrepaid(identity, purchaseTelkomselRes);
					// log.debug("save RES EMoney.... "+saveRes.toString());
					if (SUCCESS_CODE.equals(saveRes.getCode())) {
					    // log.debug("ID DESTINATION .... "+saveRes.getData().toString());
					    purchaseTelkomselRes.setIdDestination(Long.parseLong(saveRes.getData().toString()));
					    response.setData(purchaseTelkomselRes);
					    log.debug("cek data res "+purchaseTelkomselRes.getRefBayar()+" "+purchaseTelkomselRes.getRrn());
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
				} else if (PHONENUMBER_NOT_FOUND.equals(codeRes) || GIRO_USER_NOT_FOUND.equals(codeRes)) {
				    log.error("prepaid telco - phone number not found");
				    response = new CommonResponse();
//				    response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
//				    response.setMessage(messageUtil.get("error.number.not.found", httpServletRequest.getLocale()));
				    response.setCode(ResponseMessage.INVALID_TRANSACTION.getCode());
				    response.setMessage(messageUtil.get("error.phone.number.not.found", httpServletRequest.getLocale()));
				
				
				} else if (VOUCHER_OUT_OF_STOCK.equals(codeRes)) {
				    log.error("prepaid telco - Voucher out of stock");
				    response = new CommonResponse();
				    response.setCode(ResponseMessage.ERROR_VOUCHER_OUT_OF_STOCK.getCode());
				    response.setMessage(messageUtil.get("error.voucher.out.of.stock", httpServletRequest.getLocale()));
				} else if (GIRO_ACCOUNT_BLOCKED.equals(codeRes)
					|| GIRO_ACCOUNT_WAS_BLOCKED.equals(codeRes)) {
				    log.error("prepaid telco - blocked account");
				    response = new CommonResponse();
				    response.setCode(ResponseMessage.CUST_BLOCKED.getCode());
				    response.setMessage(messageUtil.get("error.account.was.blocked", httpServletRequest.getLocale()));
				    return response;
				} else if (ACCOUNT_WAS_BLOCKED.equals(codeRes) ) {
				    log.error("prepaid telco - blocked phone number");
				    response = new CommonResponse();
				    response.setCode(ResponseMessage.CUST_BLOCKED.getCode());
				    response.setMessage(messageUtil.get("error.phone.number.was.blocked", httpServletRequest.getLocale()));
				    return response;
				} else if (PHONE_NUMBER_EXPIRED.equalsIgnoreCase(codeRes)) {
				    log.error("Prepaid telco - phone number expired");
				    response = new CommonResponse();
				    response.setCode(ResponseMessage.PHONE_NUMBER_EXPIRED.getCode());
				    response.setMessage(messageUtil.get("error.phone.number.expired", httpServletRequest.getLocale()));
				} else if (AMOUNT_NOT_ENOUGH_BALANCE.equals(codeRes) || GIRO_AMOUNT_NOT_ENOUGH_BALANCE.equals(codeRes)
					|| GIRO_ERROR_VALUTA_CODE.equals(codeRes) || GIRO_LIMITED_BALANCE.equals(codeRes)) {
				    log.error("not enough balance");
				    response = new CommonResponse();
				    response.setCode(ResponseMessage.AMOUNT_NOT_ENOUGH.getCode());
				    response.setMessage(messageUtil.get("error.amount.not.enough", httpServletRequest.getLocale()));
				} else if (PASSIVE_ACCOUNT.equals(codeRes) || GIRO_INACTIVE_ACCOUNT.equals(codeRes)
					|| GIRO_ACCOUNT_NOT_ACCEPTED.equals(codeRes) || GIRO_CLOSED_ACCOUNT.equals(codeRes)) {
				    log.error("passive account");
				    response = new CommonResponse();
				    response.setCode(ResponseMessage.ERROR_INACTIVE_BANK_ACCOUNT.getCode());
				    response.setMessage(messageUtil.get("error.inactive.bank.account", httpServletRequest.getLocale()));
				} else if (GIRO_LIMIT_TRANSFER.equals(codeRes) || GIRO_OVER_LIMIT.equals(codeRes)) {
				    log.error("exceed limit");
				    response = new CommonResponse();
				    response.setCode(ResponseMessage.LIMIT_TRANSFER_DAY.getCode());
				    response.setMessage(messageUtil.get("error.exceed.limit", httpServletRequest.getLocale()));
				} else if (GIRO_CUT_OFF.equals(codeRes)) {
				    log.error("Giro cut off");
				    response = new CommonResponse();
				    response.setCode(ResponseMessage.ERROR_CUT_OFF_PLN.getCode());
				    response.setMessage(messageUtil.get("error.cutoff", httpServletRequest.getLocale()));
				} else if (GIRO_DUPLICATE_DATA.equals(codeRes)) {
				    log.error("Giro Duplicate Data");
				    response = new CommonResponse();
				    response.setCode(ResponseMessage.DUPLICATE_DATA.getCode());
				    response.setMessage(messageUtil.get("error.duplicate.data", httpServletRequest.getLocale()));
				} else if (INVALID_TRANSACTION.equals(codeRes)) {
				    log.error("Invalid Transaction");
				    response = new CommonResponse();
				    response.setCode(ResponseMessage.INVALID_TRANSACTION.getCode());
				    response.setMessage(messageUtil.get("error.invalid.transaction", httpServletRequest.getLocale()));
				} else if (SYSTEM_BROKEN_189.equalsIgnoreCase(codeRes) || SYSTEM_BROKEN_196.equalsIgnoreCase(codeRes)) {
				    log.error("prepaid telco - System failure");
				    response = new CommonResponse();
				    response.setCode(ResponseMessage.ERROR_SYSTEM_FAILURE.getCode());
				    response.setMessage(messageUtil.get("error.system.failure", httpServletRequest.getLocale()));
				}
				// else if (SYSTEM_BROKEN_168.equals(codeRes) ||
				// SYSTEM_BROKEN_169.equals(codeRes)) {
				// log.error("System was broken");
				// response.setCode(ResponseMessage.ERROR_ADVICE_FAILED.getCode());
				// response.setMessage(messageUtil.get("error.internal.server",
				// servletRequest.getLocale()));
				// }
				else {
				    log.error("Error (result) Payment Prepaid Telco : " + codeRes);
				    // Throw middleware error
				    throw new MiddlewareException(codeRes);
				}

			}else {
				if (resLmtDL.getMessage().equals("Limit user not set")) {
					response.setCode(resLmtDL.getCode());
					response.setMessage(messageUtil.get("error.limit.unset",
							httpServletRequest.getLocale()));

				} else if (resLmtDL.getMessage().equals(
						"amount more than daily limit user")) {
					response.setCode(resLmtDL.getCode());
					response.setMessage(messageUtil.get("error.limit.exceed",
							httpServletRequest.getLocale()));
				} else if (resLmtDL.getMessage().equals(
						"transactions exceed daily limit")) {
					response.setCode(resLmtDL.getCode());
					response.setMessage(messageUtil.get("error.limit.exceed",
							httpServletRequest.getLocale()));
				} else {
					response.setCode(resLmtDL.getCode());
					response.setMessage(resLmtDL.getMessage());
				}
			}

	//log.debug((String) response.getData());
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
	String provider = "PRE" + resTelco.getProvider();
	try {
	    // save to Destination and Transaction

	    // Prepare data request to save transaction
	    DestinationCommonRequest dataReq = new DestinationCommonRequest();
	    dataReq.setCategoryId(CategoryEnum.PULSA.getId());
	    dataReq.setUsername(resTelco.getUsername());
	    dataReq.setSubscriberNumber(resTelco.getPhoneNumber());
	    dataReq.setSubscriberName(resTelco.getSubscriberName());

	    if ("PREPAID".equalsIgnoreCase(resTelco.getInstitutionType())) {
		dataReq.setTransactionType(TransactionTypeEnum.TELCOPRE.name());
	    } else if ("PAKET DATA".equalsIgnoreCase(resTelco.getInstitutionType())) {
		dataReq.setTransactionType(TransactionTypeEnum.TELCODATA.name());
	    }

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
	    } else if (DestinationTypeEnum.PRESMARTFREN.name().equalsIgnoreCase(provider)) {
		dataReq.setDestinationType(DestinationTypeEnum.PRESMARTFREN.name());
	    } else if (DestinationTypeEnum.PRETRI.name().equalsIgnoreCase(provider)) {
		dataReq.setDestinationType(DestinationTypeEnum.PRETRI.name());
	    } else if (DestinationTypeEnum.PREXL.name().equalsIgnoreCase(provider)) {
		dataReq.setDestinationType(DestinationTypeEnum.PREXL.name());
	    }

	    // Call service to save Destination/ Favorite
	    CommonRequest<DestinationCommonRequest> destinationReq = new CommonRequest<>();
	    destinationReq.setIdentity(identity);
	    destinationReq.setData(dataReq);

	    CommonResponse resSaveFav = Services.create(MasterModuleService.class).saveToFavouriteCommon(destinationReq)
		    .execute().body();
	    // log.debug("res SAVE FAV... "+resSaveFav.toString());
	    if (null != resSaveFav) {
		if (!SUCCESS_CODE.equals(resSaveFav.getCode())) {
		    log.error("Save to favourite Failed");
		    response.setCode(ResponseMessage.INTERNAL_SERVER_ERROR.getCode());
		    response.setMessage(messageUtil.get("error.internal.server", httpServletRequest.getLocale()));
		} else {
		    ObjectMapper oMapper = new ObjectMapper();
		    Transaction transaction = oMapper.convertValue(resSaveFav.getData(), Transaction.class);
		    log.debug("create");
		    // set telco type

		    if ("PREPAID".equalsIgnoreCase(resTelco.getInstitutionType())) {

			// set data for telco prepaid
			TelcoPrepaid telcoPrepaid = new TelcoPrepaid();
			telcoPrepaid.setAmount(resTelco.getAmount());
			telcoPrepaid.setAdminFee(resTelco.getAdminFee());
			telcoPrepaid.setAccountNumber(resTelco.getAccountNumber());
			telcoPrepaid.setTotalAmount(resTelco.getTotalAmount());

			String typeTelco = "";
			if ("TELKOMSEL".equalsIgnoreCase(providerGroup)) {
			    typeTelco = TelcoTypeEnum.PULSA_TELKOMSEL.name();
			} else if ("INDOSAT".equalsIgnoreCase(providerGroup)) {
			    typeTelco = TelcoTypeEnum.PULSA_INDOSAT.name();
			} else if ("XL".equalsIgnoreCase(providerGroup)) {
			    typeTelco = TelcoTypeEnum.PULSA_XL.name();
			} else if ("TRI".equalsIgnoreCase(providerGroup)) {
			    typeTelco = TelcoTypeEnum.PULSA_TRI.name();
			} else if ("SMARTFREN".equalsIgnoreCase(providerGroup)) {
			    typeTelco = TelcoTypeEnum.PULSA_SMARTFREN.name();
			}

			telcoPrepaid.setTypeTelco(typeTelco);
			telcoPrepaid.setTransaction(transaction);
			telcoPrepaid.setDestination(telcoPrepaid.getTransaction().getDestination());

			// save telco prepaid
			log.debug("save purchase to Telco Prepaid");
			telcoPrepaidService.saveTelcoPrepaid(telcoPrepaid);
			log.debug("save OK.");
			response.setData(telcoPrepaid.getTransaction().getDestination().getId());

		    } else if ("PAKET DATA".equalsIgnoreCase(resTelco.getInstitutionType())) {
			// set data for telco data
			TelcoData telcoData = new TelcoData();
			telcoData.setAmount(resTelco.getAmount());
			telcoData.setAdminFee(resTelco.getAdminFee());
			telcoData.setTitle(resTelco.getTitle());
			// telcoData.setAccountNumber(resTelco.getAccountNumber());
			// telcoData.setTotalAmount(resTelco.getTotalAmount());

			String typeTelco = "";
			if ("TELKOMSEL".equalsIgnoreCase(providerGroup)) {
			    typeTelco = TelcoTypeEnum.PAKET_DATA_TELKOMSEL.name();
			} else if ("INDOSAT".equalsIgnoreCase(providerGroup)) {
			    typeTelco = TelcoTypeEnum.PAKET_DATA_INDOSAT.name();
			} else if ("XL".equalsIgnoreCase(providerGroup)) {
			    typeTelco = TelcoTypeEnum.PAKET_DATA_XL.name();
			}

			telcoData.setTypeData(typeTelco);
			telcoData.setTransaction(transaction);
			// telcoPrepaid.setDestination(telcoPrepaid.getTransaction().getDestination());

			// save telco data
			log.debug("save purchase to Telco Data");
			telcoPrepaidService.saveTelcoData(telcoData);
			log.debug("save OK.");
			response.setData(telcoData.getTransaction().getDestination().getId());

		    }
		}
	    }
	} catch (IOException e) {
	    log.error(e.getMessage(), e);
	    response.setCode(ResponseMessage.INTERNAL_SERVER_ERROR.getCode());
	    response.setMessage(messageUtil.get("error.internal.server", httpServletRequest.getLocale()));
	}
	//log.debug("response bayar paket data "+response.getData());
	return response;

    }

    /*
     * @GetMapping("/storeManualPrefixTelcoCache")
     * 
     * @ResponseStatus(HttpStatus.OK) public CommonResponse
     * storePrefixTelcoToCache() { log.debug("Store Prefix Telco to Cache");
     * CommonResponse response = new
     * CommonResponse(ResponseMessage.SUCCESS.getCode(), messageUtil.get("success",
     * httpServletRequest.getLocale()));
     * 
     * // Store to Cache List<PrefixTelco> list =
     * prefixTelcoService.storePrefixTelcoToCache(); if(null != list) {
     * response.setData(list); }
     * 
     * 
     * return response; }
     * 
     * @GetMapping("/storeManualInstitutionCache")
     * 
     * @ResponseStatus(HttpStatus.OK) public CommonResponse
     * storeInstitutionToCache() { log.debug("Store Institution to Cache");
     * CommonResponse response = new
     * CommonResponse(ResponseMessage.SUCCESS.getCode(), messageUtil.get("success",
     * httpServletRequest.getLocale()));
     * 
     * // Store to Cache List<Institution> list =
     * institutionService.storeInstitutionToCache(); if(null != list) {
     * response.setData(list); }
     * 
     * 
     * return response; }
     * 
     * @GetMapping("/getAllPrefixTelco")
     * 
     * @ResponseStatus(HttpStatus.OK) public CommonResponse getAllPrefixTelco() {
     * log.debug("get all Prefix Telco "); CommonResponse response = new
     * CommonResponse(ResponseMessage.SUCCESS.getCode(), messageUtil.get("success",
     * httpServletRequest.getLocale()));
     * 
     * // Store to Cache List<PrefixTelco> list = prefixTelcoService.findAll();
     * if(null != list) { for(PrefixTelco telco : list) {
     * log.debug("institution "+telco.getInstitutions().toString()); }
     * response.setData(list); }
     * 
     * return response; }
     */
}
