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

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import id.co.asyst.bukopin.mobile.common.core.exception.ForbiddenAccessException;
import id.co.asyst.bukopin.mobile.common.core.service.impl.BkpmService;
import id.co.asyst.bukopin.mobile.common.core.util.CryptoUtil;
import id.co.asyst.bukopin.mobile.common.core.util.MessageUtil;
import id.co.asyst.bukopin.mobile.common.model.BkpmConstants;
import id.co.asyst.bukopin.mobile.common.model.ResponseMessage;
import id.co.asyst.bukopin.mobile.common.model.payload.CommonResponse;
import id.co.asyst.bukopin.mobile.user.core.service.UserService;
import id.co.asyst.bukopin.mobile.user.core.service.UserTokenService;
import id.co.asyst.bukopin.mobile.user.model.entity.User;
import id.co.asyst.bukopin.mobile.user.model.payload.UserProfileResponse;
import id.co.asyst.bukopin.mobile.user.model.payload.VerifyTokenOwnerResponse;

/**
 * 
 * 
 * @author Gibran Haq
 * @version $Revision$, Nov 29, 2019
 * @since 1.0.Alpha1
 */

@RestController
@RequestMapping("/api/userProfile")
public class UserProfileController {
    /* Constants: */

    private final Logger log = LoggerFactory.getLogger(UserProfileController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private MessageUtil messageUtil;
    
    /**
     * User Token Service
     */
    @Autowired
    private UserTokenService userTokenService;
    
    /**
     * Bkpm Common Service
     */
    @Autowired
    private BkpmService commonService;

    @Autowired
    private HttpServletRequest servletRequest;

    /* Attributes: */

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */

    /* Getters & setters for transient attributes: */

    /* Functionalities: */
    /**
     * POST /getUserProfile/{username} : retrive User Profile
     * 
     * @param username The user's username that would show his/her profile
     * @return
     */
    @GetMapping("/getUserProfile/{username}")
    @ResponseStatus(HttpStatus.OK)
    public CommonResponse retriveUserProfile(@PathVariable String username) {
	CommonResponse response = new CommonResponse();

	// Validate Token and Phone Owner
	String decryptedUsername = CryptoUtil.decryptAESHex(username);
	VerifyTokenOwnerResponse verifyToken = new VerifyTokenOwnerResponse();
	User user = null;
	if (!commonService.verifyLocalIp(servletRequest)) {
	    verifyToken = userTokenService.verifyTokenAndPhoneOwner(decryptedUsername,
		    servletRequest.getHeader(HttpHeaders.AUTHORIZATION),
		    servletRequest.getHeader(BkpmConstants.HTTP_HEADER_DEVICE_ID));
	    if (!verifyToken.isValid()) {
		log.error("Token or device owner not valid");
		throw new ForbiddenAccessException();
	    }
	    user = verifyToken.getUser();
	} else {
	    user = userService.findUserByUsername(decryptedUsername);
	}

	// error handle when user not found
	if (user == null) {
	    log.error("User not found");
	    response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
	    response.setMessage(messageUtil.get("error.data.not.found", servletRequest.getLocale()));
	} else {
	    // set user profile response
	    UserProfileResponse userProfile = new UserProfileResponse();

	    if (user.getMiddleName()==null || user.getMiddleName().isEmpty()) {
		userProfile.setProfileName(user.getFirstName() + BkpmConstants.WHITE_SPACE + user.getLastName());
	    } else {
		userProfile.setProfileName(user.getFirstName() + BkpmConstants.WHITE_SPACE + user.getMiddleName()
			+ BkpmConstants.WHITE_SPACE + user.getLastName());
	    }
	    userProfile.setUserID(user.getUsername());
	    userProfile.setPhoneNumber(user.getMobilePhone());
	    userProfile.setEmail(user.getEmail());

	    log.debug("User has been retrieved successfully");
	    response.setCode(ResponseMessage.SUCCESS.getCode());
	    response.setMessage(messageUtil.get("success", servletRequest.getLocale()));
	    response.setData(userProfile);
	}

	return response;
    }

    /* Overrides: */}
