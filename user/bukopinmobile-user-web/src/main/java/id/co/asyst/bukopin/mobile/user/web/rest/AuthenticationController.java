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

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.net.HttpHeaders;

import id.co.asyst.foundation.service.connector.Services;
import retrofit2.Response;
import id.co.asyst.bukopin.mobile.common.core.exception.ForbiddenAccessException;
import id.co.asyst.bukopin.mobile.common.core.service.impl.BkpmService;
import id.co.asyst.bukopin.mobile.common.core.util.BkpmUtil;
import id.co.asyst.bukopin.mobile.common.core.util.CryptoUtil;
import id.co.asyst.bukopin.mobile.common.core.util.MessageUtil;
import id.co.asyst.bukopin.mobile.common.model.BkpmConstants;
import id.co.asyst.bukopin.mobile.common.model.ResponseMessage;
import id.co.asyst.bukopin.mobile.common.model.payload.CommonRequest;
import id.co.asyst.bukopin.mobile.common.model.payload.CommonResponse;
import id.co.asyst.bukopin.mobile.service.core.CentagateService;
import id.co.asyst.bukopin.mobile.service.core.TransferModuleService;
import id.co.asyst.bukopin.mobile.service.model.payload.CentagateCommonResponse;
import id.co.asyst.bukopin.mobile.service.model.payload.CtgDeleteUser;
import id.co.asyst.bukopin.mobile.service.model.payload.LoginCentagateRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.RegRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.RegistrationCentagateRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.ResetPasswordCentagateRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.ResetQARequest;
import id.co.asyst.bukopin.mobile.service.model.payload.UnlockUserRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.UpdatePasswordCentagateRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.UpdateUserStatusCentagateRequest;
import id.co.asyst.bukopin.mobile.user.core.service.AccountCardService;
import id.co.asyst.bukopin.mobile.user.core.service.OTPService;
import id.co.asyst.bukopin.mobile.user.core.service.UserMailService;
import id.co.asyst.bukopin.mobile.user.core.service.UserPINService;
import id.co.asyst.bukopin.mobile.user.core.service.UserService;
import id.co.asyst.bukopin.mobile.user.core.service.UserTokenService;
import id.co.asyst.bukopin.mobile.user.core.util.AuthUtil;
import id.co.asyst.bukopin.mobile.user.model.OTPTypeEnum;
import id.co.asyst.bukopin.mobile.user.model.centagate.LoginResultData;
import id.co.asyst.bukopin.mobile.user.model.entity.User;
import id.co.asyst.bukopin.mobile.user.model.payload.LoginRequest;
import id.co.asyst.bukopin.mobile.user.model.payload.LoginResponse;
import id.co.asyst.bukopin.mobile.user.model.payload.SendOTPEmailRequest;
import id.co.asyst.bukopin.mobile.user.model.payload.UpdatePinRequest;
import id.co.asyst.bukopin.mobile.user.model.payload.UpdateUserStatusRequest;
import id.co.asyst.bukopin.mobile.user.model.payload.VerifyOTPLoginRequest;
import id.co.asyst.bukopin.mobile.user.model.payload.VerifyOTPRequest;
import id.co.asyst.bukopin.mobile.user.model.payload.VerifyPINRequest;
import id.co.asyst.bukopin.mobile.user.model.payload.VerifyTokenOwnerResponse;
import id.co.asyst.bukopin.mobile.user.model.security.UserToken;
import id.co.asyst.bukopin.mobile.common.core.util.RegexUtil;

/**
 * REST Controller to Manage Authentication
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Oct 28, 2019
 * @since 1.0.Alpha1-SNAPSHOT
 */
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    /**
     * Logger
     */
    private final Logger log = LoggerFactory.getLogger(AuthenticationController.class);

    /* Constants: */

    /* Services: */
    /**
     * OTP Service (auto-wired, this means to get the bean called
     * <code>otpService</code>. Which is auto-generated by Spring, we will use it to
     * handle the data).
     */
    @Autowired
    private OTPService otpService;

    /**
     * User Token Service
     */
    @Autowired
    private UserTokenService userTokenService;

    /**
     * User Repository (auto-wired, this means to get the bean called
     * <code>userRepository</code>. Which is auto-generated by Spring, we will use
     * it to handle the data).
     */
    @Autowired
    private UserService userService;

    @Autowired
    private UserPINService userPINService;

    @Autowired
    private AccountCardService accountCardService;
    
    /**
     * Bkpm Common Service
     */
    @Autowired
    private BkpmService commonService;

    /**
     * Java Mail Sender
     */
    @Autowired
    private JavaMailSender javaMailSender;

    /**
     * Http Servlet Request
     */
    @Autowired
    private HttpServletRequest servletRequest;

    /**
     * Get Message Util
     */
    @Autowired
    private MessageUtil messageUtil;

    /**
     * Environment
     */
    @Autowired
    private Environment env;
    
    /**
     * Template Engine for email
     */
    @Autowired
    private TemplateEngine htmlTemplateEngine;
    
    /**
     * User Mail Service
     */
    @Autowired
    private UserMailService userMailService;

    /* Constants: */
    private static final int PASSWORD_LENGTH = 10;
    private static final String defaultFirstName = "firstName";
    private static final String defaultLastName = "lastName";
    private static final String defaultPhoneNumber = "011111111111";

    /* Constructors: */

    /* Functionalities: */
    /**
     * POST /login : authenticate Username & Password. <br />
     * 
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping("/login")
    public CommonResponse login(@Valid @RequestBody CommonRequest<LoginRequest> request) throws Exception {
	CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(),
		messageUtil.get("success", servletRequest.getLocale()));
	LoginRequest reqData = request.getData();
	
	// Phone Id is required
	if(StringUtils.isBlank(servletRequest.getHeader(BkpmConstants.HTTP_HEADER_DEVICE_ID))) {
	    response.setCode(ResponseMessage.DATA_REQUIRED.getCode());
	    response.setMessage(messageUtil.get("error.validation.required", 
		    new Object[] {BkpmConstants.HTTP_HEADER_DEVICE_ID},
		    servletRequest.getLocale()));
	    return response;
	}
	
	String username = request.getData().getUsername();
	// Verify User
	User user = userService.findUserByUsername(username);
	if (user == null) {
	    log.error("user is not register: " + request.getData().getUsername());
	    response = new CommonResponse(ResponseMessage.USER_NOT_REGISTERED.getCode(), messageUtil.get(
		    "user.not.register", new Object[] { request.getData().getUsername() }, servletRequest.getLocale()));
	    return response;
	}
	
	// decrypted password
	String decryptedPassword = CryptoUtil.decryptPassword(reqData.getPassword());
	LoginCentagateRequest reqLoginAdmin = AuthUtil.generateLoginRequest(username, decryptedPassword);
	CentagateCommonResponse ctgResponse = Services.create(CentagateService.class).login(reqLoginAdmin).execute()
		.body();

	if (BkpmConstants.CODE_CTG_SUCCESS.equals(ctgResponse.getCode())) {
	    // Mapping Object Result
	    ObjectMapper mapper = new ObjectMapper();
	    LoginResultData ctgObj = mapper.readValue(String.valueOf(ctgResponse.getObject()), LoginResultData.class);
	    
	    // Verify Phone Identity
	    String phoneIdReq = servletRequest.getHeader(BkpmConstants.HTTP_HEADER_DEVICE_ID);
	    boolean isSameDevice = false;
	    if (phoneIdReq.equals(user.getPhoneIdentity())) {
		// Same device
		isSameDevice = true;
	    } else {
		// Different device
		if (!user.isActivation()) {
		    // Not activated yet
		    user.setPhoneIdentity(phoneIdReq);
		    isSameDevice = true;
		}
	    }
	    
	    log.debug("is same device? {}",isSameDevice);
	    log.debug("is activated? {}",user.isActivation());
	    log.debug("user phone id: {}",user.getPhoneIdentity());
	    
	    // Insert login data
	    UserToken uToken = userService.insertLoginData(user, ctgObj);
	    
	    // prepare response
	    LoginResponse loginResponse = new LoginResponse();
	    loginResponse.setUsername(username);
	    loginResponse.setFirstName(user.getFirstName());
	    loginResponse.setLastName(user.getLastName());
	    loginResponse.setToken(uToken.getToken());
	    loginResponse.setTokenTimeout(ctgObj.getSessionTimeout());
	    loginResponse.setSecretCode(uToken.getSecretCode());
	    loginResponse.setActivation(user.isActivation());
	    loginResponse.setSameDevice(isSameDevice);

	    // set response
	    response.setData(loginResponse);
	} else {
	    // throw error exception
	    log.error("login failed: " + request.getData().getUsername());
	    log.debug(BkpmUtil.convertToJson(ctgResponse));
	    if (BkpmConstants.CODE_CTG_INVALID_CREDENTIAL.equalsIgnoreCase(ctgResponse.getCode())) {
		response = new CommonResponse(ResponseMessage.DATA_NOT_MATCH.getCode(),
			messageUtil.get("auth.failed", servletRequest.getLocale()));
	    } else if (BkpmConstants.CODE_STG_USER_LOCKED.equals(ctgResponse.getCode())) {
		// update user locked
		User uLogin = userService.findUserByUsername(reqData.getUsername());
		if (uLogin == null) {
		    log.error("user is not register: " + request.getData().getUsername());
		    response = new CommonResponse(ResponseMessage.USER_NOT_REGISTERED.getCode(),
			    messageUtil.get("user.not.register", new Object[] { request.getData().getUsername() },
				    servletRequest.getLocale()));
		    return response;
		}
		uLogin.setLocked(true);
		userService.save(uLogin);
		
		String receiver = user.getEmail();
		String type = "password";
		userMailService.sentBlockedMail(receiver, type, servletRequest.getLocale());

		// return locked response
		response = new CommonResponse(ResponseMessage.USER_IS_LOCKED.getCode(),
			messageUtil.get("user.locked", servletRequest.getLocale()));
	    } else {
		response = new CommonResponse(ResponseMessage.INTERNAL_SERVER_ERROR.getCode(),
			ctgResponse.getMessage());
	    }
	}
	
	return response;
    }
    
    /**
     * Register User to Centagate<br>
     * Steps:
     * <ol>
     * <li>Validate Email OTP</li>
     * <li>Login as admin to centagate to get auth token.</li>
     * <li>Delete OTP.</li>
     * <li>Centagate User Registration.</li>
     * <li>Save User info into bkpm db.</li>
     * <li>Return response to mobile apps.</li>
     * </ol>
     * 
     * @param regRequest Login request data.
     * @return Login status.
     * @throws Exception
     */
    @PostMapping("/registerUser")
    public CommonResponse registerUser(@RequestBody CommonRequest<RegRequest> commonRequest) throws Exception {
	CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(),
		messageUtil.get("success", servletRequest.getLocale()));
	RegRequest regRequest = commonRequest.getData();
	Date currentTime = new Date();
	
	// Phone Id is required
	String phoneId = servletRequest.getHeader(BkpmConstants.HTTP_HEADER_DEVICE_ID);
	if (StringUtils.isBlank(phoneId)) {
	    response.setCode(ResponseMessage.DATA_REQUIRED.getCode());
	    response.setMessage(messageUtil.get("error.validation.required",
		    new Object[] { BkpmConstants.HTTP_HEADER_DEVICE_ID }, servletRequest.getLocale()));
	    return response;
	}

	// Validate Email OTP
	boolean isValid = otpService.verifyOtp(regRequest.getEmail(), regRequest.getOtp(), OTPTypeEnum.EMAIL,
		currentTime);

	if (!isValid) {
	    log.error("OTP not valid for: " + regRequest.getEmail());
	    response.setCode(ResponseMessage.DATA_NOT_MATCH.getCode());
	    response.setMessage(messageUtil.get("otp.invalid", servletRequest.getLocale()));
	    return response;
	}

	// Login as Admin
	LoginCentagateRequest loginRequest = AuthUtil.generateAdminRequest();
	log.debug(BkpmUtil.convertToJson(loginRequest));
	CentagateCommonResponse loginResponse = Services.create(CentagateService.class).login(loginRequest).execute()
		.body();
	ObjectMapper mapper = new ObjectMapper();
	LoginResultData ctgObj = mapper.readValue(String.valueOf(loginResponse.getObject()), LoginResultData.class);

	log.debug(BkpmUtil.convertToJson(ctgObj));

	if (BkpmConstants.CODE_CTG_SUCCESS.equals(loginResponse.getCode())) {
	    // Create Request to be sent to User Registration Service
	    RegistrationCentagateRequest request = AuthUtil.generateRegRequest(regRequest, ctgObj.getSecretCode(),
		    env.getProperty("centagate.user.admin") + ctgObj.getAuthToken());
	    log.debug(BkpmUtil.convertToJson(request));

	    // validate is email unique
	    if (userService.findUserByEmail(regRequest.getEmail()) != null) {
		response = new CommonResponse(ResponseMessage.DUPLICATE_DATA.getCode(),
			messageUtil.get("register.duplicate.email", servletRequest.getLocale()));
	    } else {
		// Call service User Registration
		CentagateCommonResponse ctgResponse = Services.create(CentagateService.class)
			.registration(env.getProperty("centagate.user.admin"), request).execute().body();

		if (BkpmConstants.CODE_CTG_SUCCESS.equals(ctgResponse.getCode())) {

		    // decrypted password
		    String decryptedPassword = CryptoUtil.decryptPassword(regRequest.getPassword());
		    CommonResponse resetPassword = resetPassword(regRequest.getUsername(), decryptedPassword,
			    ctgObj.getSecretCode(), ctgObj.getAuthToken());

		    // Save Data User into DB
		    if (ResponseMessage.SUCCESS.getCode().equals(resetPassword.getCode())) {
			log.debug("Password successfully generated");
			
			// Get default limit Id
			Long limitId = 0L;
			CommonResponse limitResponse = Services.create(TransferModuleService.class).getDefaultLimit().execute().body();
			if (ResponseMessage.SUCCESS.getCode().equals(limitResponse.getCode())) {
			    mapper = new ObjectMapper();
			    Map<String, Object> resultLimitObj = mapper.convertValue(limitResponse.getData(), Map.class);
			    log.debug("resultLimitObj: {}", resultLimitObj);

			    String limitStr = String.valueOf(resultLimitObj.get("id"));
			    if (!StringUtils.isBlank(limitStr) && !"null".equalsIgnoreCase(limitStr)) {
				limitId = Long.valueOf(limitStr);
			    }
			    log.debug("limitId: {}", limitId);
			}

			User usr = new User();
			usr.setUsername(request.getUsername());
			usr.setFirstName(defaultFirstName);
			usr.setLastName(defaultLastName);
			usr.setEmail(request.getUserEmail());
			usr.setMobilePhone(defaultPhoneNumber);
			usr.setCreatedBy(ctgObj.getUsername());
			usr.setCreatedDate(new Date());
			usr.setPhoneIdentity(phoneId);
			usr.setLimitId(limitId);

			userService.save(usr);

			// decrypted pin
			String pin = CryptoUtil.decryptPassword(regRequest.getPin());
			CommonResponse setPIN = createPin(request.getUsername(), pin,
				ctgObj.getUsername(), ctgObj.getSecretCode(), ctgObj.getAuthToken());

			response.setData(usr);
			
			String receiver = request.getUserEmail();
			
			userMailService.sendRegistrationMail(receiver, servletRequest.getLocale());

			// Delete OTP
			otpService.inactivateOTP(regRequest.getEmail(), OTPTypeEnum.EMAIL, currentTime);
		    } else {
			log.error("Registration failed. Error set password.");
			response = new CommonResponse(ResponseMessage.REGISTER_USER_FAILED.getCode(),
				messageUtil.get("register.failed", servletRequest.getLocale()));
			deleteUser(regRequest.getUsername());
		    }
		} else if (BkpmConstants.CODE_CTG_DUPLICATE_USERNAME.equals(ctgResponse.getCode())) {
		    log.error("Duplicate username CTG: " + regRequest.getUsername());
		    response = new CommonResponse(ResponseMessage.DUPLICATE_DATA.getCode(),
			    messageUtil.get("register.duplicate.username", new Object[] { regRequest.getUsername() },
				    servletRequest.getLocale()));
		} else if (BkpmConstants.CODE_CTG_DUPLICATE_EMAIL.equals(ctgResponse.getCode())) {
		    log.error("Duplicate email CTG: " + regRequest.getEmail());
		    response = new CommonResponse(ResponseMessage.DUPLICATE_DATA.getCode(),
				messageUtil.get("register.duplicate.email", servletRequest.getLocale()));
		} else if (BkpmConstants.CODE_CTG_USER_LIMIT_REACH.equals(ctgResponse.getCode())) {
		    log.error("User limit reach CTG: " + regRequest.getEmail());
		    response = new CommonResponse(ResponseMessage.DATA_OVER_LIMIT.getCode(),
				messageUtil.get("register.user.limit.reach", servletRequest.getLocale()));
		} else if (BkpmConstants.CODE_CTG_INVALID_LICENSE_FILE.equals(ctgResponse.getCode())) {
		    log.error("Invalid license file CTG: " + regRequest.getEmail());
		    response = new CommonResponse(ResponseMessage.ERROR_EXTERNAL.getCode(),
				messageUtil.get("register.invalid.license.file", servletRequest.getLocale()));
		} else {
		    log.error("Registration failed CTG: " + regRequest.getUsername()
		    	+". CTG: "+ctgResponse.getCode()+ " - "+ctgResponse.getMessage());
		    response = new CommonResponse(ResponseMessage.ERROR_EXTERNAL.getCode(),
			    ctgResponse.getMessage());
		}
	    }
	} else if (BkpmConstants.CODE_CTG_INVALID_LICENSE_FILE.equals(loginResponse.getCode())) {
	    log.error("Invalid license file CTG: " + regRequest.getEmail());
	    response = new CommonResponse(ResponseMessage.ERROR_EXTERNAL.getCode(),
			messageUtil.get("register.invalid.license.file", servletRequest.getLocale()));
	} else {
	    log.error("Registration failed CTG: " + regRequest.getUsername()
	    	+". CTG: "+loginResponse.getCode()+ " - "+loginResponse.getMessage());
	    response = new CommonResponse(ResponseMessage.ERROR_EXTERNAL.getCode(),
		    loginResponse.getMessage());
	}

	return response;
    }

    /**
     * resetPassword
     * 
     * @param username
     * @param password
     * @param secretCode
     * @param cenToken
     * @return
     * @throws Exception
     */
    public CommonResponse resetPassword(String username, String password,
	    String secretCode, String authToken) throws Exception {
	CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(),
		messageUtil.get("success", servletRequest.getLocale()));

	ResetPasswordCentagateRequest rqst = AuthUtil.generateResetPassRequest(username, password, secretCode,
		authToken);
	log.debug(BkpmUtil.convertToJson(rqst));

	// Call User Reset Password service
	Response<CentagateCommonResponse> setPasswordResponse = Services.create(CentagateService.class)
		.resetPassword(rqst, env.getProperty("centagate.user.admin")).execute();

	log.error("response set password centagate: " + setPasswordResponse.code());
	CentagateCommonResponse setPasswordResponse2 = setPasswordResponse.body();

	if (!BkpmConstants.CODE_CTG_SUCCESS.equals(setPasswordResponse2.getCode())) {
	    log.error("Error reset password: " + username);
	    response = new CommonResponse(ResponseMessage.ERROR_RESET_PASSWORD.getCode(),
		    messageUtil.get("error.reset.password", servletRequest.getLocale()));
	}
	return response;
    }

    /**
     * GET /SMSToken/{phonenumber} : Send SMS Token
     * 
     * @param username The User's Username to send SMS Token
     * @return
     * @throws NoSuchAlgorithmException
     * @throws IOException
     */
    @GetMapping("/SMSToken/{username}")
    public CommonResponse sendSMSToken(@PathVariable String username) throws NoSuchAlgorithmException, IOException {
	CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(),
		messageUtil.get("success", servletRequest.getLocale()));

	String decryptedUsername = CryptoUtil.decryptAESHex(username);
	User user = userService.findUserByUsername(decryptedUsername);
	// Handle user not found
	if (user == null) {
	    log.error("User not found with username : " + decryptedUsername);
	    response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
	    response.setMessage(messageUtil.get("error.data.not.found", servletRequest.getLocale()));
	    return response;
	}
	
	// Handle phone null
	if (StringUtils.isBlank(user.getMobilePhone())) {
	    log.error("User doesn't have phone number yet: " + decryptedUsername);
	    response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
	    response.setMessage(messageUtil.get("error.data.not.found", servletRequest.getLocale()));
	    return response;
	}
	
	// send OTP by phone number
	response = otpService.sendOTP(user.getMobilePhone(), OTPTypeEnum.SMS, servletRequest.getLocale(), "gqvQAdUZlsd");
	if (ResponseMessage.SUCCESS.getCode().equals(response.getCode())) {
	    response.setMessage(messageUtil.get("success", servletRequest.getLocale()));
	    response.setData(user.getMobilePhone());
	} else if (ResponseMessage.INTERNAL_SERVER_ERROR.getCode().equals(response.getCode())) {
	    response.setMessage(messageUtil.get("error.internal.server", servletRequest.getLocale()));
	} else {
	    response.setMessage(messageUtil.get("send.otp.failed", servletRequest.getLocale()));
	}

	return response;
    }

    /**
     * POST /OTP/verify : Verify OTP
     * 
     * @param request
     * @return success if there is valid OTP, else OTP not valid.
     */
//    @PostMapping("/OTP/verify")
    public CommonResponse verifyOTP(@Valid @RequestBody CommonRequest<VerifyOTPRequest> request) {
	CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(),
		messageUtil.get("success", servletRequest.getLocale()));
	VerifyOTPRequest reqData = request.getData();
	
	// Phone Id is required
	if (StringUtils.isBlank(servletRequest.getHeader(BkpmConstants.HTTP_HEADER_DEVICE_ID))) {
	    response.setCode(ResponseMessage.DATA_REQUIRED.getCode());
	    response.setMessage(messageUtil.get("error.validation.required",
		    new Object[] { BkpmConstants.HTTP_HEADER_DEVICE_ID }, servletRequest.getLocale()));
	    return response;
	}

	OTPTypeEnum otpType = OTPTypeEnum.SMS;
	// Validate request
	boolean isReqValid = false;
	String receiverErrorMessage = "";
	if (reqData.getReceiver().contains("@")) {
	    // if receiver is email
	    isReqValid = RegexUtil.validateEmail(reqData.getReceiver());
	    receiverErrorMessage = messageUtil.get("error.validation.regex", new Object[] { "Email" },
		    servletRequest.getLocale());
	    otpType = OTPTypeEnum.EMAIL;
	} else {
	    // receiver is phone number
	    isReqValid = RegexUtil.validatePhoneNumber(reqData.getReceiver());
	    receiverErrorMessage = messageUtil.get("error.validation.regex", new Object[] { "Phone Number" },
		    servletRequest.getLocale());
	}

	if (!isReqValid) {
	    response.setCode(String.valueOf(HttpStatus.BAD_REQUEST.value()));
	    response.setMessage(receiverErrorMessage);
	    // return error validation
	    return response;
	}

	boolean isValid = otpService.verifyOtp(reqData.getReceiver(), reqData.getOtp(), otpType, new Date());
	if (!isValid) {
	    log.error("OTP is not valid: " + reqData.getOtp());
	    // handle error
	    response.setCode(ResponseMessage.DATA_NOT_MATCH.getCode());
	    response.setMessage(messageUtil.get("otp.invalid", servletRequest.getLocale()));
	} else {
	    otpService.deleteOtp(reqData.getReceiver(), otpType);
	}

	return response;
    }
    
    /**
     * POST /OTP/verify/login : Verify OTP Login
     * 
     * @param request
     * @return success if there is valid OTP, else OTP not valid.
     * @throws Exception 
     */
//    @PostMapping("/OTP/verify/login")
    @PostMapping("/OTP/verify")
    public CommonResponse verifyOTPLogin(@Valid @RequestBody CommonRequest<VerifyOTPLoginRequest> request) throws Exception {
	CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(),
		messageUtil.get("success", servletRequest.getLocale()));
	VerifyOTPLoginRequest reqData = request.getData();
	
	// Phone Id is required
	String phoneId = servletRequest.getHeader(BkpmConstants.HTTP_HEADER_DEVICE_ID);
	if (StringUtils.isBlank(phoneId)) {
	    response.setCode(ResponseMessage.DATA_REQUIRED.getCode());
	    response.setMessage(messageUtil.get("error.validation.required",
		    new Object[] { BkpmConstants.HTTP_HEADER_DEVICE_ID }, servletRequest.getLocale()));
	    return response;
	}
	
	User user = userService.findUserByUsername(reqData.getUsername());
	// Validate user existence
	if (user == null) {
	    log.error("user is not register: " + request.getData().getUsername());
	    response = new CommonResponse(ResponseMessage.USER_NOT_REGISTERED.getCode(), messageUtil.get(
		    "user.not.register", new Object[] { request.getData().getUsername() }, servletRequest.getLocale()));
	    return response;
	}

	boolean isValid = otpService.verifyOtp(user.getMobilePhone(), reqData.getOtp(), OTPTypeEnum.SMS, new Date());
	if (isValid) { // otp valid
	    // remove otp
	    otpService.deleteOtp(user.getMobilePhone(), OTPTypeEnum.SMS);
	    
	    // update phone id
	    user.setPhoneIdentity(phoneId);
	    userService.save(user);
	    
	    // Send new device login email
	    userMailService.sendNewDeviceMail(user, servletRequest.getLocale());
	} else { // otp not valid
	    log.error("OTP is not valid: " + reqData.getOtp());
	    // Logout
	    HashMap<String, String> logout = new HashMap<>();
	    logout.put("username", reqData.getUsername());
	    CommonRequest<HashMap<String, String>> reqLogout = new CommonRequest<>();
	    reqLogout.setData(logout);
	    logout(reqLogout);
	    
	    // error response
	    response.setCode(ResponseMessage.DATA_NOT_MATCH.getCode());
	    response.setMessage(messageUtil.get("otp.invalid", servletRequest.getLocale()));
	}

	return response;
    }

    /**
     * GET /PIN/create : Save new PIN
     * 
     * @param username The User's username who has PIN.
     * @param pin      The User's PIN.
     * @return
     * @throws Exception
     */
    public CommonResponse createPin(String username, String pin,
	    @RequestBody String adminUsername, String secretCode, String authToken)
	    throws Exception {
	CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(),
		messageUtil.get("success", servletRequest.getLocale()));

	/*
	 * LoginCentagateRequest loginRequest = AuthUtil.generateAdminRequest();
	 * log.debug(BkpmUtil.convertToJson(loginRequest)); CentagateCommonResponse
	 * loginResponse =
	 * Services.create(CentagateService.class).login(loginRequest).execute()
	 * .body();
	 */
	ObjectMapper mapper = new ObjectMapper();
	/*
	 * LoginResultData ctgObj =
	 * mapper.readValue(String.valueOf(loginResponse.getObject()),
	 * LoginResultData.class);
	 */
	ResetQARequest resetQA = AuthUtil.generateResetQARequest(username, secretCode, adminUsername + authToken,
		BkpmConstants.pinId, BkpmConstants.pinQuestion, pin);

	log.debug("Save Question Request " + BkpmUtil.convertToJson(resetQA));

	Response<CentagateCommonResponse> saveResponse = Services.create(CentagateService.class)
		.resetQA(adminUsername, resetQA).execute();
	CentagateCommonResponse responseSavePIN = saveResponse.body();
	if (BkpmConstants.CODE_CTG_SUCCESS.equals(responseSavePIN.getCode())) {
	    return response;

	} else {
	    response.setCode(ResponseMessage.INTERNAL_SERVER_ERROR.getCode());
	    response.setMessage(messageUtil.get("error.internal.server", servletRequest.getLocale()));

	}

	return response;
    }

    /**
     * POST /PIN/verify: Validate PIN
     * 
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping("/PIN/verify")
    public CommonResponse verifyPin(@Valid @RequestBody CommonRequest<VerifyPINRequest> request) throws Exception {
	CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(),
		messageUtil.get("success", servletRequest.getLocale()));
	String username = request.getData().getUsername();
	String encryptedPin = request.getData().getPin();

	response = userPINService.verifyPIN(username, encryptedPin, servletRequest.getLocale());
	
	if(response.getCode().equals(ResponseMessage.USER_IS_LOCKED.getCode())){
	   User user = userService.findUserByUsername(username);
	   String receiver = user.getEmail();
	   String type = "PIN";
	    userMailService.sentBlockedMail(receiver, type, servletRequest.getLocale());
	}

	return response;
    }

    /**
     * POST /forgotPassword : generate new password.
     * 
     * @param username The user's username that would generate new password
     * @return
     * @throws Exception
     */
    @PostMapping("/forgotPassword")
    public CommonResponse forgotPassword(@RequestBody CommonRequest<HashMap<String, String>> request) throws Exception {
	String username = request.getData().get("username");
	CommonResponse response = new CommonResponse();
	// validate parameter
	if (username == null || username.isEmpty()) {
	    response.setCode(ResponseMessage.DATA_REQUIRED.getCode());
	    response.setMessage(messageUtil.get("error.validation.required", new Object[] { "username" },
		    servletRequest.getLocale()));
	    return response;
	}

	String newPassword = "";
	CentagateCommonResponse resetCTGResponse = new CentagateCommonResponse();

	// Validate existing user
	User user = userService.findUserByUsername(username);
	if (user == null) {
	    log.error("User not found with username : " + username);
	    response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
	    response.setMessage(messageUtil.get("error.data.not.found", servletRequest.getLocale()));
	    return response;
	}

	// Validate iddle duration
	response = userService.resetPasswordValidation(username, servletRequest.getLocale());
	if (!ResponseMessage.SUCCESS.getCode().equals(response.getCode())) {
	    return response;
	}

	// generate new password
	log.debug("generating new password....");
	newPassword = AuthUtil.generatePassword(PASSWORD_LENGTH);

	// login to centagate as admin
	log.debug("Login to centagate as admin");
	LoginCentagateRequest loginRequest = AuthUtil.generateAdminRequest();
	CentagateCommonResponse loginCTGResponse = Services.create(CentagateService.class).login(loginRequest).execute()
		.body();

	if (BkpmConstants.CODE_CTG_SUCCESS.equals(loginCTGResponse.getCode())) {
	    // Mapping Object Result
	    ObjectMapper mapper = new ObjectMapper();
	    LoginResultData loginCTGObj = mapper.readValue(String.valueOf(loginCTGResponse.getObject()),
		    LoginResultData.class);

	    // update new password
	    String secretCode = loginCTGObj.getSecretCode();
	    String authToken = loginCTGObj.getAuthToken();
	    log.debug("Reset password for username : " + username);
	    ResetPasswordCentagateRequest resetPassRequest = AuthUtil.generateResetPassRequest(username, newPassword,
		    secretCode, authToken);
	    resetCTGResponse = Services.create(CentagateService.class)
		    .resetPassword(resetPassRequest, env.getProperty("centagate.user.admin")).execute().body();
	} else {
	    log.error("Error while login to centagate cause " + loginCTGResponse.getMessage());
	    response.setCode(loginCTGResponse.getCode());
	    response.setMessage(loginCTGResponse.getMessage());
	}

	if (BkpmConstants.CODE_CTG_SUCCESS.equals(resetCTGResponse.getCode())) {
	    // send mail
	    userMailService.sendForgotPassword(user, newPassword, servletRequest.getLocale());

	    response.setCode(ResponseMessage.SUCCESS.getCode());
	    response.setMessage(messageUtil.get("success", servletRequest.getLocale()));

	    // Save last reset password
	    user.setLastResetPassword(new Date());
	    userService.save(user);
	} else {
	    log.error(resetCTGResponse.getMessage());
	    response.setCode(ResponseMessage.ERROR_FORGOT_PASSWORD.getCode());
	    response.setMessage(messageUtil.get("error.forgot.password", servletRequest.getLocale()));
	}

	return response;
    }

    /**
     * POST /unlockUser : Unlock User from locked. 1. Login as Admin 2. map response
     * from login 3. set request to unlock user 4. send unlock user and get response
     * 
     * @param username locked user's username
     * @return success or error from response
     * @throws Exception
     */
    @PostMapping("/unlockUser")
    public CommonResponse unlockUser(@RequestParam(required = true) String username) throws Exception {
	CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(),
		messageUtil.get("success", servletRequest.getLocale()));
	// Check if the parameters is empty
	if (username.isEmpty()) {
	    response = new CommonResponse(ResponseMessage.DATA_REQUIRED.getCode(), messageUtil
		    .get("error.validation.required", new Object[] { "username" }, servletRequest.getLocale()));
	    return response;
	}

	LoginCentagateRequest ctgRequestLogin = AuthUtil.generateAdminRequest();
	CentagateCommonResponse ctgResponseLogin = Services.create(CentagateService.class).login(ctgRequestLogin)
		.execute().body();

	if (BkpmConstants.CODE_CTG_SUCCESS.equals(ctgResponseLogin.getCode())) {
	    ObjectMapper mapper = new ObjectMapper();
	    LoginResultData ctgObj = mapper.readValue(String.valueOf(ctgResponseLogin.getObject()),
		    LoginResultData.class);

	    // get Request unlock user model and send unlock user to Centagate
	    UnlockUserRequest ctgRequestUnlock = AuthUtil.generateUnlockUserRequest(username, ctgObj.getSecretCode(),
		    ctgObj.getUsername() + ctgObj.getAuthToken());

	    CentagateCommonResponse ctgResponseUnlock = Services.create(CentagateService.class)
		    .unlockUser(ctgObj.getUsername(), ctgRequestUnlock).execute().body();

	    if (!BkpmConstants.CODE_CTG_SUCCESS.equals(ctgResponseUnlock.getCode()) 
		    && !BkpmConstants.CODE_CTG_USER_NOT_LOCKED.equals(ctgResponseUnlock.getCode())) {
		log.error("unlock user failed: " + username);
		log.debug(BkpmUtil.convertToJson(ctgResponseUnlock));
		response = new CommonResponse(ResponseMessage.ERROR_UNLOCK_USER.getCode(),
			messageUtil.get("error.unlock.user", servletRequest.getLocale()));
	    } else {
		// update user locked = false
		User uLogin = userService.findUserByUsername(username);
		if (uLogin == null) {
		    log.error("user is not register: " + username);
		    response = new CommonResponse(ResponseMessage.USER_NOT_REGISTERED.getCode(),
			    messageUtil.get("user.not.register", new Object[] { username },
				    servletRequest.getLocale()));
		    return response;
		}
		uLogin.setLocked(false);
		userService.save(uLogin);
	    }
	} else {
	    response = new CommonResponse(ResponseMessage.INTERNAL_SERVER_ERROR.getCode(),
		    messageUtil.get("error.internal.server", servletRequest.getLocale()));
	}

	return response;
    }

    /**
     * POST /updateUserStatus : update user status active/inactive. 1. Login as
     * Admin 2. map response from login 3. set request to update status user 4. send
     * update status user and get response
     * 
     * @param username that need to update
     * @param status   (1/0) active/inactive status
     * @return success or error from response
     * @throws Exception
     */
    @PostMapping("/updateUserStatus")
    public CommonResponse updateUserStatus(@Valid @RequestBody CommonRequest<UpdateUserStatusRequest> request)
	    throws Exception {
	UpdateUserStatusRequest updateUserStatus = request.getData();
	CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(),
		messageUtil.get("success", servletRequest.getLocale()));

	LoginCentagateRequest ctgRequestLogin = AuthUtil.generateAdminRequest();
	CentagateCommonResponse ctgResponseLogin = Services.create(CentagateService.class).login(ctgRequestLogin)
		.execute().body();

	if (BkpmConstants.CODE_CTG_SUCCESS.equals(ctgResponseLogin.getCode())) {
	    ObjectMapper mapper = new ObjectMapper();
	    LoginResultData ctgObj = mapper.readValue(String.valueOf(ctgResponseLogin.getObject()),
		    LoginResultData.class);

	    // get Request update user status model and send update user status to Centagate
	    UpdateUserStatusCentagateRequest ctgRequestUpdate = AuthUtil.generateUpdateUserStatusRequest(
		    updateUserStatus.getUsername(), updateUserStatus.getStatus(), ctgObj.getSecretCode(),
		    ctgObj.getUsername() + ctgObj.getAuthToken());

	    CentagateCommonResponse ctgResponseUpdate = Services.create(CentagateService.class)
		    .updateUserStatus(ctgObj.getUsername(), ctgRequestUpdate).execute().body();

	    if (!BkpmConstants.CODE_CTG_SUCCESS.equals(ctgResponseUpdate.getCode())) {
		response = new CommonResponse(ResponseMessage.ERROR_UPDATE_STATUS_USER.getCode(),
			messageUtil.get("error.update.status.user", servletRequest.getLocale()));
	    }

	    // TO DO : logout admin
	} else {
	    response = new CommonResponse(ResponseMessage.INTERNAL_SERVER_ERROR.getCode(),
		    messageUtil.get("error.internal.server", servletRequest.getLocale()));
	}

	return response;
    }

    /**
     * POST /sendOTPEmail : send OTP by Email
     * 
     * @param email The user's email that would send OTP
     * @return
     * @throws NoSuchAlgorithmException
     * @throws IOException
     */
    @PostMapping("/sendOTPEmail")
    public CommonResponse sendOTPByEmail(@Valid @RequestBody CommonRequest<SendOTPEmailRequest> request)
	    throws NoSuchAlgorithmException, IOException {
	CommonResponse response = new CommonResponse();
	String email = request.getData().getEmail();

	// send otp by email
	response = otpService.sendOTP(email, OTPTypeEnum.EMAIL, servletRequest.getLocale(), null);
	if (ResponseMessage.SUCCESS.getCode().equals(response.getCode())) {
	    response.setMessage(messageUtil.get("success", servletRequest.getLocale()));
	} else if (ResponseMessage.INTERNAL_SERVER_ERROR.getCode().equals(response.getCode())) {
	    response.setMessage(messageUtil.get("error.internal.server", servletRequest.getLocale()));
	} else {
	    response.setMessage(messageUtil.get("send.otp.failed", servletRequest.getLocale()));
	}

	return response;
    }

    /**
     * deleteUser
     * 
     * @param username
     * @return
     * @throws Exception
     */
//    @PostMapping("/deleteUser/{username}")
//    public CommonResponse deleteUser(@PathVariable String username) throws Exception {
    private CommonResponse deleteUser(String username) throws Exception {
	CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(),
		messageUtil.get("success", servletRequest.getLocale()));
	LoginCentagateRequest loginRequest = AuthUtil.generateAdminRequest();
	log.debug(BkpmUtil.convertToJson(loginRequest));
	CentagateCommonResponse loginResponse = Services.create(CentagateService.class).login(loginRequest).execute()
		.body();
	ObjectMapper mapper = new ObjectMapper();
	LoginResultData ctgObj = mapper.readValue(String.valueOf(loginResponse.getObject()), LoginResultData.class);
	log.debug(BkpmUtil.convertToJson(ctgObj));

	CtgDeleteUser request = AuthUtil.reqDeleteUser(username, ctgObj.getSecretCode(),
		ctgObj.getUsername() + ctgObj.getAuthToken());

	Response<CentagateCommonResponse> deleteUserResponse = Services.create(CentagateService.class)
		.deleteUser(ctgObj.getUsername(), request).execute();
	log.debug("REST delete user Response : " + BkpmUtil.convertToJson(deleteUserResponse));
	log.debug("Username " + username + "is deleted!");

	return response;

    }

    /**
     * PUT /updatePassword: Update user's password
     * 
     * @param username
     * @param request
     * @return status of update password
     * @throws IOException
     */
    @PutMapping("/updatePassword")
    @ResponseStatus(HttpStatus.OK)
    public CommonResponse updatePassword(@Valid @RequestBody CommonRequest<UpdatePasswordCentagateRequest> request)
	    throws IOException {
	CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(),
		messageUtil.get("success", servletRequest.getLocale()));
	
	String username = request.getData().getUsername();
	
	if (!commonService.verifyLocalIp(servletRequest)) {
	    // Validate Token and Phone Owner
	    VerifyTokenOwnerResponse verifyToken = userTokenService.verifyTokenAndPhoneOwner(
		    username, servletRequest.getHeader(HttpHeaders.AUTHORIZATION),
		    servletRequest.getHeader(BkpmConstants.HTTP_HEADER_DEVICE_ID));
	    if (!verifyToken.isValid()) {
		log.error("Token or device owner not valid");
		throw new ForbiddenAccessException();
	    }
	}
	
	// is user exist?
	User user = userService.findUserByUsername(username);
	if (user == null) {
	    log.error("User not found with username : " + username);
	    response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
	    response.setMessage(messageUtil.get("error.data.not.found", servletRequest.getLocale()));
	    return response;
	}
	
	// decrypted password
	String decryptedPassword = CryptoUtil.decryptPassword(request.getData().getPassword());
	request.getData().setPassword(decryptedPassword);
	// decrypt new password
	String decryptedNewPassword = CryptoUtil.decryptPassword(request.getData().getNewPassword());
	request.getData().setNewPassword(decryptedNewPassword);

	CentagateCommonResponse updatePasswordResponse = Services.create(CentagateService.class)
		.updatePasswordCentagate(username, request.getData()).execute().body();
	if (updatePasswordResponse.getCode().equalsIgnoreCase(BkpmConstants.CODE_CTG_SUCCESS)) {
	    // update Password success, send email
	    userMailService.sendChangePasswordAndPIN(user.getEmail(), UserMailService.TYPE_CHANGE_PASSWORD,
		    servletRequest.getLocale());
	    return response;
	} else if (updatePasswordResponse.getCode().equalsIgnoreCase(BkpmConstants.CODE_CTG_INVALID_CREDENTIAL)) {
	    response.setCode(ResponseMessage.DATA_NOT_MATCH.getCode());
	    response.setMessage(messageUtil.get("invalid.credentials", servletRequest.getLocale()));
	} else if (updatePasswordResponse.getCode().equalsIgnoreCase(BkpmConstants.CODE_CTG_PASSWORD_CANNOT_SAME)) {
	    response.setCode(ResponseMessage.ERROR_UPDATE_USER.getCode());
	    response.setMessage(messageUtil.get("password.cannot.same.with.previous", servletRequest.getLocale()));
	    ;
	} else if (updatePasswordResponse.getCode()
		.equalsIgnoreCase(BkpmConstants.CODE_CTG_PASSWORD_CANNOT_SAME_WITH_USERNAME)) {
	    response.setCode(ResponseMessage.ERROR_UPDATE_USER.getCode());
	    response.setMessage(messageUtil.get("password.cannot.same.with.username", servletRequest.getLocale()));
	} else if (updatePasswordResponse.getCode()
		.equals(BkpmConstants.CODE_CTG_PASSWORD_NOT_ALLOWED_LAST)) {
	    response.setCode(ResponseMessage.ERROR_UPDATE_USER.getCode());
	    response.setMessage(messageUtil.get("password.not.allowed.last", servletRequest.getLocale()));
    	} else {
	    log.debug("ERROR DUE TO : " + updatePasswordResponse.getMessage());
	    response.setCode(ResponseMessage.ERROR.getCode());
	    response.setMessage(messageUtil.get("error", servletRequest.getLocale()));
	}

	return response;
    }

    /**
     * POST /logout : logout user
     * 
     * @param req The user's username
     * @return
     * @throws Exception
     */
    @PostMapping("/logout")
    private CommonResponse logout(@RequestBody CommonRequest<HashMap<String, String>> req) throws Exception {
	CommonResponse response = new CommonResponse();
	String username = req.getData().get("username");
	// validate parameter
	if (username == null || username.isEmpty()) {
	    response.setCode(ResponseMessage.DATA_REQUIRED.getCode());
	    response.setMessage(messageUtil.get("error.validation.required", new Object[] { "username" },
		    servletRequest.getLocale()));
	    return response;
	}
	Optional<UserToken> userToken = userTokenService.findOne(username);
	User user = userService.findUserByUsername(username);
	if (!userToken.isPresent() || user==null) {
	    log.error("User not found with username : " + username);
	    response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
	    response.setMessage(messageUtil.get("error.data.not.found", servletRequest.getLocale()));
	    return response;
	}

	response = userService.logoutCentagate(user, userToken.get().getToken(), 
		servletRequest.getLocale());

	return response;
    }

    @PutMapping("/updatePin")
    @ResponseStatus(HttpStatus.OK)
    public CommonResponse updateUserPin(@Valid @RequestBody CommonRequest<UpdatePinRequest> request) throws Exception {
	CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(),
		messageUtil.get("success", servletRequest.getLocale()));
	
	String username = request.getData().getUsername();
	
	if (!commonService.verifyLocalIp(servletRequest)) {
	    // Validate Token and Phone Owner
	    VerifyTokenOwnerResponse verifyToken = userTokenService.verifyTokenAndPhoneOwner(
		    username, servletRequest.getHeader(HttpHeaders.AUTHORIZATION),
		    servletRequest.getHeader(BkpmConstants.HTTP_HEADER_DEVICE_ID));
	    if (!verifyToken.isValid()) {
		log.error("Token or device owner not valid");
		throw new ForbiddenAccessException();
	    }
	}
	
	// is user exist?
	User user = userService.findUserByUsername(username);
	if (user == null) {
	    log.error("User not found with username : " + username);
	    response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
	    response.setMessage(messageUtil.get("error.data.not.found", servletRequest.getLocale()));
	    return response;
	}
		
	// Login as Admin
	LoginCentagateRequest loginRequest = AuthUtil.generateAdminRequest();
	log.debug(BkpmUtil.convertToJson(loginRequest));
	CentagateCommonResponse loginResponse = Services.create(CentagateService.class).login(loginRequest).execute()
		.body();
	ObjectMapper mapper = new ObjectMapper();
	LoginResultData ctgObj = mapper.readValue(String.valueOf(loginResponse.getObject()), LoginResultData.class);

	CommonResponse verifyPin = verifyPin(
		AuthUtil.verifyPinReq(username, request.getData().getDefaultPin()));
	if (request.getData().getDefaultPin().equalsIgnoreCase(request.getData().getNewPin())) {
	    response.setCode(ResponseMessage.ERROR_UPDATE_USER.getCode());
	    response.setMessage(messageUtil.get("pin.failed", servletRequest.getLocale()));

	    return response;
	}

	if (!verifyPin.getCode().equalsIgnoreCase(response.getCode())) {
	    response.setCode(verifyPin.getCode());
	    response.setMessage(verifyPin.getMessage());
	} else {
	    // decrypted pin
	    String pin = CryptoUtil.decryptPassword(request.getData().getNewPin());
	    CommonResponse setPIN = createPin(username, pin,
		    ctgObj.getUsername(), ctgObj.getSecretCode(), ctgObj.getAuthToken());
	    if (!setPIN.getCode().equalsIgnoreCase(response.getCode())) {
		response.setCode(setPIN.getCode());
		response.setMessage(setPIN.getMessage());
	    } else {
		// update PIN success, send email
		userMailService.sendChangePasswordAndPIN(user.getEmail(), UserMailService.TYPE_CHANGE_PIN, 
			servletRequest.getLocale());
	    }
	}

	return response;
    }
    
    /* Overrides: */
}
