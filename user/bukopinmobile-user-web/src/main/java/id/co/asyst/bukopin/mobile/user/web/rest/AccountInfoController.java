/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.user.web.rest;

import java.math.BigInteger;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.lucene.store.VerifyingLockFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.net.HttpHeaders;

import id.co.asyst.bukopin.mobile.common.core.exception.DataNotMatchException;
import id.co.asyst.bukopin.mobile.common.core.exception.ForbiddenAccessException;
import id.co.asyst.bukopin.mobile.common.core.service.impl.BkpmService;
import id.co.asyst.bukopin.mobile.common.core.util.BkpmUtil;
import id.co.asyst.bukopin.mobile.common.core.util.CryptoUtil;
import id.co.asyst.bukopin.mobile.common.core.util.MessageUtil;
import id.co.asyst.bukopin.mobile.common.model.BkpmConstants;
import id.co.asyst.bukopin.mobile.common.model.ResponseMessage;
import id.co.asyst.bukopin.mobile.common.model.payload.CommonRequest;
import id.co.asyst.bukopin.mobile.common.model.payload.CommonResponse;
import id.co.asyst.bukopin.mobile.user.core.service.AccountBalanceService;
import id.co.asyst.bukopin.mobile.user.core.service.AccountInfoUserService;
import id.co.asyst.bukopin.mobile.user.core.service.InquiryTransactionService;
import id.co.asyst.bukopin.mobile.user.core.service.UserMailService;
import id.co.asyst.bukopin.mobile.user.core.service.UserPINService;
import id.co.asyst.bukopin.mobile.user.core.service.UserService;
import id.co.asyst.bukopin.mobile.user.core.service.UserTokenService;
import id.co.asyst.bukopin.mobile.user.core.util.AuthUtil;
import id.co.asyst.bukopin.mobile.user.model.AccountBalanceByAccNoReq;
import id.co.asyst.bukopin.mobile.user.model.AccountBalanceQRReq;
import id.co.asyst.bukopin.mobile.user.model.AccountBalanceReq;
import id.co.asyst.bukopin.mobile.user.model.AccountBalanceRes;
import id.co.asyst.bukopin.mobile.user.model.CIFStatusEnum;
import id.co.asyst.bukopin.mobile.user.model.InquiryTransactionReq;
import id.co.asyst.bukopin.mobile.user.model.InquiryTransactionRes;
import id.co.asyst.bukopin.mobile.user.model.entity.AccountInfo;
import id.co.asyst.bukopin.mobile.user.model.entity.User;
import id.co.asyst.bukopin.mobile.user.model.payload.AccountBalanceSimpleResp;
import id.co.asyst.bukopin.mobile.user.model.payload.VerifyTokenOwnerResponse;


/**
 * REST Controller for managing Account Info.
 * 
 * @author Kartika Dwi Handini
 * @version $Revision$,(Created on Nov 01, 2019)
 * @since 1.0.Alpha1
 */
@RestController
@RequestMapping("/api")
public class AccountInfoController {
	/**
     * Logger.
     */
    private final Logger log = LoggerFactory.getLogger(AccountInfoController.class);

    /* Constants: */

    /* Attributes: */

    /* Services: */
    /**
     * User Repository (auto-wired, this means to get the bean called
     * <code>userTokenRepository</code>. Which is auto-generated by Spring, we will
     * use it to handle the data).
     */
    @Autowired
    private InquiryTransactionService inquiryTransactionService;
    
    @Autowired
    private AccountBalanceService accountBalanceService;
    
    @Autowired
    private UserService UserService;
    
    @Autowired
    private AccountInfoUserService acUserService;
    
    /**
     * User Token Service
     */
    @Autowired
    private UserTokenService userTokenService;
    
    /**
     * User PIN Service
     */
    @Autowired
    private UserPINService userPinService;
    
    /**
     * Get Message Util
     */
    @Autowired
    private MessageUtil messageUtil;
    
    /**
     * Bkpm Common Service
     */
    @Autowired
    private BkpmService commonService;
    
    /**
     * User Mail Service
     */
    @Autowired
    private UserMailService userMailService;
    
    /**
     * Http Servlet Request
     */
    @Autowired
    private HttpServletRequest httpServletRequest;

    /* Constructors: */
    /**
     * Default Constructor.
     */
    public AccountInfoController() {
        // do nothing.
    }

    /* Getters & setters for attributes: */

    /* Functions: */
    /**
     * POST /getInquiryTransaction : get all Transaction.
     *
     * @return the response with status 200 (OK) and the list of Transaction in body.
     */
	@PostMapping("/getInquiryTransaction/preHandle")
    public CommonResponse getInquiryTransaction(@Valid @RequestBody CommonRequest<InquiryTransactionReq> inquiryTransactionReq) throws URISyntaxException {
        log.debug("REST request to get Inquiry Transaction");
        CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(), messageUtil.get("success", httpServletRequest.getLocale()));
        InquiryTransactionRes result = inquiryTransactionService.getInquiry(inquiryTransactionReq.getData());
        if (null != result) {
        	response.setData(result);		   
		}else {
			 response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
			 response.setMessage(messageUtil.get("error.data.not.found", httpServletRequest.getLocale()));
		}
	    
        return response;
	}
    
    @PostMapping("/getAccountBalance/preHandle")
    public  CommonResponse getAccountBalance(@Valid @RequestBody CommonRequest<AccountBalanceReq> accountBalanceReq) throws Exception {
        log.debug("REST request to get account balance");
        CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(), messageUtil.get("success", httpServletRequest.getLocale()));
        
        User user = UserService.findUserByUsername(accountBalanceReq.getData().getUsername());
	if (!commonService.verifyLocalIp(httpServletRequest)) {
	    // Validate Token and Phone Owner
	    VerifyTokenOwnerResponse verifyToken = userTokenService.verifyTokenAndPhoneOwner(
		    accountBalanceReq.getData().getUsername(), httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION),
		    httpServletRequest.getHeader(BkpmConstants.HTTP_HEADER_DEVICE_ID));
	    if (!verifyToken.isValid()) {
		log.error("Token or device owner not valid");
		throw new ForbiddenAccessException();
	    }
	}
        
        // Add verify PIN
        response = userPinService.verifyPIN(accountBalanceReq.getData().getUsername(), 
        	accountBalanceReq.getData().getPin(), httpServletRequest.getLocale());
        if(ResponseMessage.SUCCESS.getCode().equals(response.getCode())) {
            AccountBalanceRes result = accountBalanceService.getAccountBalance(accountBalanceReq.getData());
            response.setData(result);
        } else if (response.getCode().equals(ResponseMessage.USER_IS_LOCKED.getCode())){
 	   String receiver = user.getEmail();
 	   String type = "PIN";
 	   userMailService.sentBlockedMail(receiver, type, httpServletRequest.getLocale());
 	}
        
        // 221 = response code from tibco if account number is passive
        /*if("221".equals(result.getResponseCode())) {
        	response.setCode(result.getResponseCode());
			response.setMessage(result.getResponseMessage());
        } else {
        	response.setData(result);
        }*/
        
        
        /*if("000".equals(result.getResponseCode())) {
        	response.setData(result);
       	  } else {
        	response.setCode(result.getResponseCode());
			response.setMessage(result.getResponseMessage());
        }*/ 
        
        return response;
    }
    
    /**
     * Get Account Balance by QR Code
     *  <br>Similar to /getAccountBalance/preHandle service
     *  with Token Owner validation instead of PIN Verification.
     *  
     * @param accountBalanceReq
     * @return
     * @throws Exception
     */
    @PostMapping("/getAccountBalanceQR")
    public  CommonResponse getAccountBalanceQR(@Valid @RequestBody CommonRequest<AccountBalanceQRReq> request) throws Exception {
        log.debug("REST request to get account balance by QR Code: {}"+BkpmUtil.convertToJson(request));
        CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(), messageUtil.get("success", httpServletRequest.getLocale()));
        
	if (!commonService.verifyLocalIp(httpServletRequest)) {
	    // Validate Token and Phone Owner
	    VerifyTokenOwnerResponse verifyToken = userTokenService.verifyTokenAndPhoneOwner(
		    request.getData().getUsername(), httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION),
		    httpServletRequest.getHeader(BkpmConstants.HTTP_HEADER_DEVICE_ID));
	    if (!verifyToken.isValid()) {
		log.error("Token or device owner not valid");
		throw new ForbiddenAccessException();
	    }
	}
     	
        AccountBalanceReq req = new AccountBalanceReq();
        req.setUsername(request.getData().getUsername());
        req.setAccountType(request.getData().getAccountType());
     	AccountBalanceRes result = accountBalanceService.getAccountBalance(req);
        result.getTransactionDetails().forEach(td -> td.setTransactionDetails(new ArrayList()));
        result.setTotalBalance(null);
        result.setResponseCode(null);
        result.setResponseMessage(null);
        response.setData(result);
        
        // 221 = response code from tibco if account number is passive
        /*if("221".equals(result.getResponseCode())) {
        	response.setCode(result.getResponseCode());
			response.setMessage(result.getResponseMessage());
        } else {
        	response.setData(result);
        }*/
        
        
        /*if("000".equals(result.getResponseCode())) {
        	response.setData(result);
       	  } else {
        	response.setCode(result.getResponseCode());
			response.setMessage(result.getResponseMessage());
        }*/ 
        
        return response;
    }
    
    @GetMapping("/getAccountInfo/{username}/preHandle")
    public CommonResponse getAccountInfo(@PathVariable String username) {
	log.debug("Find Account Info for username " + username);
	CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(), messageUtil.get("success", httpServletRequest.getLocale()));
	
	User user = null;
	String decryptedUsername = CryptoUtil.decryptAESHex(username);
	if (!commonService.verifyLocalIp(httpServletRequest)) {
	    // Validate Token and Phone Owner
	    VerifyTokenOwnerResponse verifyToken = userTokenService.verifyTokenAndPhoneOwner(decryptedUsername,
		    httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION),
		    httpServletRequest.getHeader(BkpmConstants.HTTP_HEADER_DEVICE_ID));
	    if (!verifyToken.isValid()) {
		log.error("Token or device owner not valid");
		throw new ForbiddenAccessException();
	    }
	    user = verifyToken.getUser();
	} else {
	    user = UserService.findUserByUsername(decryptedUsername);
	}
	
	// Validate User
	if(user == null) {
	    log.debug("Cannot find user with username " + username);
	    response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
	    response.setMessage(messageUtil.get("error.data.not.found", httpServletRequest.getLocale()));
	    return response;
	}
	
	List<AccountInfo> findAccountInfo = acUserService.findByActiveCif(user.getCifNumber());
	if (findAccountInfo.isEmpty()) {
	    log.debug("Cannot find account info with CIF number" + user.getCifNumber());
	    response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
	    response.setMessage(messageUtil.get("error.data.not.found", httpServletRequest.getLocale()));
	    return response;
	}
	
	// add account balance to account info response
	for (int i = 0; i < findAccountInfo.size(); i++) {
	    // set request get account balance
	    AccountBalanceByAccNoReq accountBalanceReq = new AccountBalanceByAccNoReq();
	    log.debug("account type "+findAccountInfo.get(i).getAccountType().getValue());
	    accountBalanceReq.setAccountType(BigInteger.valueOf(findAccountInfo.get(i).getAccountType().getValue()));
	    accountBalanceReq.setUsername(username);
	    accountBalanceReq.setAccountNo(findAccountInfo.get(i).getAccountNo());
	    // get account balance
	    AccountBalanceRes accountBalanceRes = accountBalanceService.getAccountBalanceByAccountNum(accountBalanceReq);
	  
	    AccountBalanceSimpleResp accBalanceSimpleRes = new AccountBalanceSimpleResp();
	    // set account balance simple		    
		accBalanceSimpleRes.setProductID(accountBalanceRes.getTransactionDetails().get(0).getProductID());
		accBalanceSimpleRes.setEffectiveBalance(accountBalanceRes.getTransactionDetails().get(0).getEffectiveBalance());
		accBalanceSimpleRes.setHoldAmount(accountBalanceRes.getTransactionDetails().get(0).getHoldAmount());
		accBalanceSimpleRes.setAvailableBalance(accountBalanceRes.getTransactionDetails().get(0).getAvailableBalance());
		
		
		
		if("221".equalsIgnoreCase(accountBalanceRes.getResponseCode())) {
			findAccountInfo.get(i).setCifStatus(CIFStatusEnum.PASSIVE);
		} else if("224".equalsIgnoreCase(accountBalanceRes.getResponseCode())) {
			findAccountInfo.get(i).setCifStatus(CIFStatusEnum.CLOSED);
		} else {
			findAccountInfo.get(i).setCifStatus(accountBalanceRes.getTransactionDetails().get(0).getCifStatus());
		}
	    
	    
	    // add account balance simple to account info response
	    findAccountInfo.get(i).setAccountBalance(accBalanceSimpleRes);
	}
	
	response.setData(findAccountInfo);
	
	return response;
    }
    
    @PostMapping("/getAccountBalanceByAccNo/preHandle")
    public  CommonResponse getAccountBalanceByAccNo(@Valid @RequestBody CommonRequest<AccountBalanceByAccNoReq> accountBalanceByAccNoReq) throws URISyntaxException {
        log.debug("REST request to get account balance by Account Number");
        CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(), messageUtil.get("success", httpServletRequest.getLocale()));
        AccountBalanceRes result = accountBalanceService.getAccountBalanceByAccountNum(accountBalanceByAccNoReq.getData());
        response.setData(result);
        
        /*if("000".equals(result.getResponseCode())) {
        	response.setData(result);
        } else {
        	response.setCode(result.getResponseCode());
			response.setMessage(result.getResponseMessage());
        }*/
        return response;
    }
    
    

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
