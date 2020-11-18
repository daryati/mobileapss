/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.user.core.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.co.asyst.bukopin.mobile.common.core.util.BkpmUtil;
import id.co.asyst.bukopin.mobile.common.core.util.MessageUtil;
import id.co.asyst.bukopin.mobile.common.model.BkpmConstants;
import id.co.asyst.bukopin.mobile.common.model.ResponseMessage;
import id.co.asyst.bukopin.mobile.common.model.payload.CommonResponse;
import id.co.asyst.bukopin.mobile.service.core.CentagateService;
import id.co.asyst.bukopin.mobile.service.model.payload.CentagateCommonResponse;
import id.co.asyst.bukopin.mobile.service.model.payload.LogoutCentagateRequest;
import id.co.asyst.bukopin.mobile.user.core.config.GetConfiguration;
import id.co.asyst.bukopin.mobile.user.core.repository.UserRepository;
import id.co.asyst.bukopin.mobile.user.core.service.elastic.ElasticLoginService;
import id.co.asyst.bukopin.mobile.user.core.util.AuthUtil;
import id.co.asyst.bukopin.mobile.user.model.centagate.LoginResultData;
import id.co.asyst.bukopin.mobile.user.model.entity.CustomerLogin;
import id.co.asyst.bukopin.mobile.user.model.entity.User;
import id.co.asyst.bukopin.mobile.user.model.entity.elastic.CustomerLoginElastic;
import id.co.asyst.bukopin.mobile.user.model.security.UserToken;
import id.co.asyst.foundation.service.connector.Services;
import retrofit2.Response;

/**
 * 
 * 
 * @author Gibran Haq
 * @version $Revision$, Oct 31, 2019
 * @since 1.0.Alpha1
 */
@Service("userService")
@Transactional
public class UserService {
    
    /* Constants: */

    /**
     * Logger
     */
    private final Logger log = LoggerFactory.getLogger(UserService.class);

    /**
     * User Repository
     */
    private final UserRepository userRepository;
    
    /**
     * User Token Service
     */
    private final UserTokenService userTokenService;
    
    /**
     * Get Configuration Service
     */
    @Autowired
    private GetConfiguration configuration;
    
    /**
     * Get Message Util
     */
    @Autowired
    private MessageUtil messageUtil;
    
    /**
     * Customer Login Service
     */
    @Autowired
    private CustomerLoginService customerLoginService;
    
    /**
     * Elasticsearch Service
     */
    @Autowired
    private ElasticLoginService elasticLoginService;
    
    /* Attributes: */

    /* Transient Attributes: */

    /* Constructors: */
    
    /**
     * @param userRepository
     *            User Repository
     * @param userTokenService
     *            User Token Service
     */
    public UserService(UserRepository userRepository, UserTokenService userTokenService) {
	this.userRepository = userRepository;
	this.userTokenService = userTokenService;
    }
    /* Getters & setters for attributes: */

    /* Getters & setters for transient attributes: */

    /* Functionalities: */

    /**
     * Save User
     * 
     * @param user
     * @return user
     */
    public User save(User user) {
	return userRepository.save(user);
    }

    /**
     * Find all user
     * 
     * @return All User
     */
    @Transactional(readOnly = true)
    public List<User> findAll() {
	return userRepository.findAll();
    }

    /**
     * Find User By Username
     * 
     * @param username
     * @return user
     */
    @Transactional(readOnly = true)
    public User findUserByUsername(String username) {
	return userRepository.findByUsername(username);
    }

    /**
     * Find User By Username
     * 
     * @param username
     * @return user
     */
    @Transactional(readOnly = true)
    public User findUserByMobilePhone(String mobilePhone) {
	return userRepository.findByMobilePhone(mobilePhone);
    }

    @Transactional(readOnly = true)
    public User findUserByEmail(String email) {
	return userRepository.getUserByEmail(email);
    }

    /**
     * Delete user by username
     * 
     * @param username
     */
    public void delete(String username) {
	User user = findUserByUsername(username);
	userRepository.deleteById(user.getId());
    }
    
    /**
     * Reset Password Validation
     * 
     * @param username The Username to reset the password.
     * @param locale The Locale from HtppServletRequest.
     * @return The response validation.
     */
    public CommonResponse resetPasswordValidation(String username, Locale locale) {
	CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(),
		messageUtil.get("success", locale));
	
	// Get idle time
	Integer duration = Integer.valueOf(configuration.getConfigValue(
		BkpmConstants.KEY_RESET_PASSWORD_DURATION));
		
	// Get user "last reset password"
	User user = findUserByUsername(username);
	if (user.getLastResetPassword() != null) {
	    Calendar last = Calendar.getInstance();
	    last.setTime(user.getLastResetPassword());
	    last.add(Calendar.MINUTE, duration);

	    // Validate duration
	    Date now = new Date();
	    if (now.before(last.getTime())) {
		// Handle wait for a moment
		response = new CommonResponse(ResponseMessage.ERROR_WAIT_A_MOMENT.getCode(),
			messageUtil.get("error.wait.moment", locale));
	    }
	}
	
	return response;
    }
    
    /**
     * Insert Login Data
     * 
     * @param user The User logged in.
     * @param ctgObj The Object result from Centagate.
     * @param servletRequest The Http Servlet Request.
     */
    public UserToken insertLoginData(User user, LoginResultData ctgObj) {
	Date now = new Date();
	
	// Save User Token
	UserToken uToken = new UserToken();
	uToken.setUsername(user.getUsername());
	uToken.setUid(String.valueOf(UUID.randomUUID()));
	uToken.setToken(ctgObj.getAuthToken());
	uToken.setCreateDate(now);
	uToken.setUpdateDate(now);
	uToken.setActive(true);
	uToken.setSecretCode(ctgObj.getSecretCode());
	userTokenService.save(uToken);

	// Update User
	user.setLocked(false);
	user.setLogin(true);
	save(user);
	
	// Save Customer Login
	CustomerLogin custLogin = new CustomerLogin();
	custLogin.setUsername(user.getUsername());
	custLogin.setLoginAt(now);
	customerLoginService.save(custLogin);
	
	// Save Customer Login into Elasticsearch
	elasticLoginService.saveCustomerLogin(new CustomerLoginElastic(custLogin));
	
	return uToken;
    }
    
    /**
     * Save Logout Data
     * 
     * @param user The User to Logout
     */
    public void insertLogoutData(User user) {
	Date now = new Date();
	// update user
	userTokenService.delete(user.getUsername());
	user.setLogin(false);
	this.save(user);
	
	// update customer login
	CustomerLogin cust = customerLoginService.findTopUsernameByLoginAtDesc(user.getUsername());
	cust.setLogoutAt(now);
	customerLoginService.save(cust);
    }
    
    /**
     * Logout Centagate User and Clear User's Token.
     * 
     * @param user The registered user.
     * @param token The user's token to clear.
     * @param locale The Locale of Http Request.
     * @return Status logout in Common Response.
     * @throws Exception
     */
    public CommonResponse logoutCentagate (User user, String token, Locale locale) throws Exception {
	CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(),
		messageUtil.get("success", locale));
	
	LogoutCentagateRequest logoutCTGRequest = AuthUtil.generateLogoutRequest(
		user.getUsername(), token);
	Response<CentagateCommonResponse> logoutResponse = Services.create(CentagateService.class)
		.logout(logoutCTGRequest).execute();

	if (logoutResponse.isSuccessful() && BkpmConstants.CODE_CTG_SUCCESS.equals(logoutResponse.body().getCode())) {
	    this.insertLogoutData(user);
	} else {
	    log.debug(BkpmUtil.convertToJson(logoutResponse.body()));

	    response.setCode(ResponseMessage.ERROR.getCode());
	    response.setMessage(messageUtil.get("error", locale));
	}
	
	return response;
    }
    
    /* Overrides: */
 }
