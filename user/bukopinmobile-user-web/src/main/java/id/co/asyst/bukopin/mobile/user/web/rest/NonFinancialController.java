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
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
import id.co.asyst.bukopin.mobile.user.core.service.NonFinancialService;
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
import id.co.asyst.bukopin.mobile.user.model.entity.NonFinancial;
import id.co.asyst.bukopin.mobile.user.model.entity.User;
import id.co.asyst.bukopin.mobile.user.model.payload.AccountBalanceSimpleResp;
import id.co.asyst.bukopin.mobile.user.model.payload.InquiryNotificationRequest;
import id.co.asyst.bukopin.mobile.user.model.payload.VerifyTokenOwnerResponse;


/**
 * REST Controller for managing Account Info.
 * 
 * @author Kartika Dwi Handini
 * @version $Revision$,(Created on Nov 01, 2019)
 * @since 1.0.Alpha1
 */
@RestController
@RequestMapping("/nonFinancial")
public class NonFinancialController {
	/**
     * Logger.
     */
    private final Logger log = LoggerFactory.getLogger(NonFinancialController.class);

    /* Constants: */

    /* Attributes: */

    /* Services: */
    
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
     * Non Financial Service
     */
    @Autowired
    private NonFinancialService nonFinancialService;
    
    @Autowired
    private UserService userService;

    /* Constructors: */
    /**
     * Default Constructor.
     */
    public NonFinancialController() {
        // do nothing.
    }

    /* Getters & setters for attributes: */

    /* Functions: */
    /**
     * POST /getInquiryTransaction : get all Transaction.
     *
     * @return the response with status 200 (OK) and the list of Transaction in body.
     */
	@PostMapping("/saveReport")
    public CommonResponse saveReport(@Valid @RequestBody CommonRequest<NonFinancial> req) throws URISyntaxException {
        log.debug("REST request to save non financial Report");
        CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(), messageUtil.get("success", httpServletRequest.getLocale()));
        nonFinancialService.saveNonFinancial(req.getData());	    
        return response;
	}
	
	@PostMapping("/inquiryNotification")
    public CommonResponse inquiryNotification(@Valid @RequestBody CommonRequest<InquiryNotificationRequest> req) throws URISyntaxException {
        log.debug("REST request to save report inquiry notificaion");
        CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(), messageUtil.get("success", httpServletRequest.getLocale()));
         
        return response;
	}
    
    
}
