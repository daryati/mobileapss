/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.transfer.web.rest;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.co.asyst.bukopin.mobile.common.core.exception.DataNotMatchException;
import id.co.asyst.bukopin.mobile.common.core.exception.MiddlewareException;
import id.co.asyst.bukopin.mobile.common.core.util.BkpmUtil;
import id.co.asyst.bukopin.mobile.common.core.util.CryptoUtil;
import id.co.asyst.bukopin.mobile.common.core.util.MessageUtil;
import id.co.asyst.bukopin.mobile.common.model.BkpmConstants;
import id.co.asyst.bukopin.mobile.common.model.ResponseMessage;
import id.co.asyst.bukopin.mobile.common.model.payload.CommonRequest;
import id.co.asyst.bukopin.mobile.common.model.payload.CommonResponse;
import id.co.asyst.bukopin.mobile.transfer.web.rest.TransferExceptionHandler;
import id.co.asyst.bukopin.mobile.service.core.UserModuleService;
import id.co.asyst.bukopin.mobile.service.model.payload.pln.GetVerifyPINRequest;
import id.co.asyst.bukopin.mobile.transfer.core.service.OverbookService;
import id.co.asyst.bukopin.mobile.transfer.model.InquiryAccountReq;
import id.co.asyst.bukopin.mobile.transfer.model.InquiryAccountRes;
import id.co.asyst.bukopin.mobile.transfer.model.PostingElementRes;
import id.co.asyst.bukopin.mobile.transfer.model.PostingReq;
import id.co.asyst.bukopin.mobile.transfer.model.PostingRes;
import id.co.asyst.bukopin.mobile.user.model.MiniStatementReq;
import id.co.asyst.foundation.service.connector.Services;


/**
 * REST Controller for managing Statement.
 * 
 * @author Kartika Dwi Handini
 * @version $Revision$,(Created on Nov 05, 2019)
 * @since 1.0.Alpha1
 */
@RestController
@RequestMapping("/api")
public class OverbookController {
	/**
     * Logger.
     */
    private final Logger log = LoggerFactory.getLogger(OverbookController.class);

    /* Constants: */
    /**
     * Separator: Pipe
     */
    private final static String SEPARATOR_PIPE = "|";
    private static final String TRANSFER_NOT_ENOUGH_BALANCE = "08";
    private static final String TRANSFER_INACTIVE_ACCOUNT = "15";
    private static final String TRANSFER_NOT_ENOUGH_BALANCE_FUNDS = "51";

    /* Attributes: */

    /* Services: */
    /**
     * User Repository (auto-wired, this means to get the bean called
     * <code>userTokenRepository</code>. Which is auto-generated by Spring, we will
     * use it to handle the data).
     */
    @Autowired
    private OverbookService overbookService;
    
    /**
     * Get message util
     */
    @Autowired
    private MessageUtil messageUtil;
    
    /**
     * Http servlet request
     */
    @Autowired
    private HttpServletRequest servletRequest;

    /* Constructors: */
    /**
     * Default Constructor.
     */
    public OverbookController() {
        // do nothing.
    }

    /* Getters & setters for attributes: */

    /* Functions: */
    /**
     * POST /getInquiryTransaction : get all Transaction.
     *
     * @return the response with status 200 (OK) and the list of Transaction in body.
     */
    @PostMapping("/postTransaction/preHandle")
    public CommonResponse postTransaction(@Valid @RequestBody CommonRequest<PostingReq> request)
	    throws URISyntaxException {
	log.debug("REST request to postTransaction: {}", BkpmUtil.convertToJson(request));
	CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(),
		messageUtil.get("success", servletRequest.getLocale()));

	// Prepare verify PIN
	log.debug("verify PIN: " + request.getData().getPin());
	GetVerifyPINRequest pinRequest = new GetVerifyPINRequest();
	pinRequest.setPin(request.getData().getPin());
	pinRequest.setUsername(request.getData().getPostingFrom().getUsername());
	CommonRequest<GetVerifyPINRequest> commonPinRequest = new CommonRequest<>();
	commonPinRequest.setIdentity(request.getIdentity());
	commonPinRequest.setData(pinRequest);

	try {
	    // Verify PIN
	    CommonResponse pinResponse = Services.create(UserModuleService.class).verifyPIN(commonPinRequest).execute()
		    .body();
	    if (!ResponseMessage.SUCCESS.getCode().equals(pinResponse.getCode())) {
		// response not success
		return pinResponse;
	    }
	} catch (IOException e) {
	    e.printStackTrace();
	}

	// Verify Reference Code
	// from|to|confirmationcode|merchant
	String ref = request.getData().getPostingFrom().getAccountNumber() + SEPARATOR_PIPE
		+ request.getData().getPostingTo().getAccountNumber() + SEPARATOR_PIPE
		+ request.getData().getConfirmationCode() + SEPARATOR_PIPE + BkpmConstants.MERCHANT_ID;
	boolean referenceValid = CryptoUtil.matchAES(ref, request.getData().getReferenceCode());
	if (!referenceValid) {
	    // reference not valid
	    log.error("Reference not valid: {}", request.getData().getReferenceCode());
	    throw new DataNotMatchException();
	}

	// Posting Process

	PostingRes res = overbookService.postTransactionViaAPI(request.getData(), request.getIdentity());
	if (null != res.getStatusCode() && !"".equals(res.getStatusCode()) && "200".equals(res.getStatusCode())) {
	    response.setData(res);
	} else if(TRANSFER_NOT_ENOUGH_BALANCE.equalsIgnoreCase(res.getStatusCode())){
    	log.debug("not enough balance");
    	response.setCode(ResponseMessage.AMOUNT_NOT_ENOUGH.getCode());
	    response.setMessage(messageUtil.get("error.amount.not.enough", servletRequest.getLocale()));
    }  else if(TRANSFER_NOT_ENOUGH_BALANCE_FUNDS.equalsIgnoreCase(res.getStatusCode())){
    	log.debug("not enough balance");
    	response.setCode(ResponseMessage.AMOUNT_NOT_ENOUGH.getCode());
	    response.setMessage(messageUtil.get("error.amount.not.enough", servletRequest.getLocale()));
    } else if(TRANSFER_INACTIVE_ACCOUNT.equals(res.getStatusCode())) {
	    log.error("Account inactive: "+request.getData().getPostingFrom().getAccountNumber());
	    response.setCode(ResponseMessage.ERROR_INACTIVE_BANK_ACCOUNT.getCode());
	    response.setMessage(messageUtil.get("error.inactive.bank.account", servletRequest.getLocale()));
	} else {
	    log.error("error code " + res.getStatusCode() + " message: " + res.getStatusDesc());
	    // response = new CommonResponse(res.getStatusCode(), res.getStatusDesc());
	    throw new MiddlewareException(res.getStatusCode());
	}
	return response;
    }
    
    @PostMapping("/inquiryAccount/preHandle")
    public CommonResponse inquiryAccount (@Valid @RequestBody CommonRequest<InquiryAccountReq> request) throws URISyntaxException {
        log.debug("REST request to postTransaction: {}", BkpmUtil.convertToJson(request));
        CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(), messageUtil.get("success", servletRequest.getLocale()));       
        
        InquiryAccountRes res = overbookService.inquiryAmountViaAPI(request.getData());
        if(null != res.getConfirmationCode() && !"".equals(res.getConfirmationCode())) {
        	 response.setData(res);
        } else if(TRANSFER_NOT_ENOUGH_BALANCE.equalsIgnoreCase(res.getStatusCode())){
        	log.debug("not enough balance");
        	response.setCode(ResponseMessage.AMOUNT_NOT_ENOUGH.getCode());
    	    response.setMessage(messageUtil.get("error.amount.not.enough", servletRequest.getLocale()));
        } else if(TRANSFER_NOT_ENOUGH_BALANCE_FUNDS.equalsIgnoreCase(res.getStatusCode())){
        	log.debug("not enough balance");
        	response.setCode(ResponseMessage.AMOUNT_NOT_ENOUGH.getCode());
    	    response.setMessage(messageUtil.get("error.amount.not.enough", servletRequest.getLocale()));
        }else if(TRANSFER_INACTIVE_ACCOUNT.equals(res.getStatusCode())) {
    	    log.error("Account inactive: "+request.getData().getPostingFrom().getAccountNumber());
    	    response.setCode(ResponseMessage.ERROR_INACTIVE_BANK_ACCOUNT.getCode());
    	    response.setMessage(messageUtil.get("error.inactive.bank.account", servletRequest.getLocale()));
    	} else {
        	log.error("error code "+res.getStatusCode()+" message: "+res.getStatusDesc());
        	//response = new CommonResponse(res.getStatusCode(), res.getStatusDesc());   
        	throw new MiddlewareException(res.getStatusCode());
        }
       
        
        return response;
    }
/*    
    @PostMapping("/getAccountBalance")
    public AccountBalanceRes getAccountBalance(@Valid @RequestBody AccountBalanceReq accountBalanceReq) throws URISyntaxException {
        log.debug("REST request to get account balance");
        return accountBalanceService.getAccountBalance(accountBalanceReq);
    }
    */
    
    

    /**
     * POST /userTokens : Creates a new UserToken.
     *
     * @param userToken The userToken to create.
     * @return The response with status 201 (Created) and with body of the new
     * UserToken, or with status 400 (Bad Request) if the userToken has already an ID.
     *//*
    @PostMapping("/userTokens")
    @ResponseStatus(HttpStatus.CREATED)
    public UserToken createUserToken(@Valid @RequestBody UserToken userToken) throws URISyntaxException {
        log.debug("REST request to save UserToken : {}", userToken);
        if (userToken.getId() != null) {
            // throw new BadRequestAlertException("A new userToken cannot already have an
            // ID", "UserToken", "idexists");
        }
        return userTokenService.save(userToken);
    }*/

    

    /* Overrides: */
}
