/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.user.core.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import id.co.asyst.bukopin.mobile.common.core.util.CryptoUtil;
import id.co.asyst.bukopin.mobile.common.core.util.MessageUtil;
import id.co.asyst.bukopin.mobile.common.model.BkpmConstants;
import id.co.asyst.bukopin.mobile.common.model.payload.CommonRequest;
import id.co.asyst.bukopin.mobile.common.model.payload.Identity;
import id.co.asyst.bukopin.mobile.service.model.payload.ChallengeQuestionRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.CtgDeleteUser;
import id.co.asyst.bukopin.mobile.service.model.payload.LoginCentagateRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.LogoutCentagateRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.QuestionAuthRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.RegRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.RegistrationCentagateRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.ResetPasswordCentagateRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.ResetQARequest;
import id.co.asyst.bukopin.mobile.service.model.payload.SaveQARequest;
import id.co.asyst.bukopin.mobile.service.model.payload.UnlockUserRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.UpdateUserStatusCentagateRequest;
import id.co.asyst.bukopin.mobile.user.model.payload.VerifyPINRequest;

/**
 * 
 * 
 * @author Gibran Haq
 * @version $Revision$, Nov 6, 2019
 * @since 1.0.Alpha1
 */
@Component
public class AuthUtil {
    /**
     * Logger.
     */
    private static final Logger log = LoggerFactory.getLogger(AuthUtil.class);
    
    /* Constants: */
    private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
    private static final String NUMBER = "0123456789";
    private static final String PLATFORM_ANDROID = "Android";

    private static final String PASSWORD_ALLOW_BASE = CHAR_LOWER + CHAR_UPPER + NUMBER;
    private static final String PASSWORD_ALLOW_BASE_SHUFFLE = shuffleString(PASSWORD_ALLOW_BASE);
    private static final String PASSWORD_ALLOW = PASSWORD_ALLOW_BASE_SHUFFLE;
    private static final String CHAR_OTP = "bkpm2019";

    private static SecureRandom random = new SecureRandom();

    /* Beans: */
    /**
     * Environment
     */
    private static Environment env;

    /**
     * Get Message Util
     */
    private static MessageUtil messageUtil;
    
    /* Transient Attributes: */

    /* Constructors: */
    public AuthUtil(Environment env, MessageUtil messageUtil) {
	AuthUtil.env = env;
	AuthUtil.messageUtil = messageUtil;
    }

    /* Getters & setters for attributes: */

    /* Getters & setters for transient attributes: */

    /* Functionalities: */
    /**
     * Generate Admin Centagate Login Request
     * 
     * @return The Object request.
     * @throws Exception
     */
    public static LoginCentagateRequest generateAdminRequest() throws Exception {
	LoginCentagateRequest req = generateLoginRequest(
		env.getProperty("centagate.user.admin"), 
		env.getProperty("centagate.password.admin"));
	return req;
    }

    /**
     * Generate Centagate Login Request
     * 
     * @param username The Centagate User's username
     * @param password The Centagate user's password
     * @return The Object request.
     * @throws Exception
     */
    public static LoginCentagateRequest generateLoginRequest(String username, String password) throws Exception {
	LoginCentagateRequest req = new LoginCentagateRequest();
	req.setUsername(username);
	req.setPassword(password);
	req.setIntegrationKey(env.getProperty("centagate.key.integration"));
	req.setUnixTimestamp(String.valueOf(Instant.now().getEpochSecond()));
	req.setSupportFido("");
	req.setIpAddress("192.168.0.108");
	req.setUserAgent("");
	req.setBrowserFp("");
	List<String> excludeFields = new ArrayList<>();
	excludeFields.add(BkpmConstants.FIELD_HMAC);
	req.setHmac(CryptoUtil.getHmacObject(req, excludeFields, 
		env.getProperty("centagate.key.secret")));

	return req;
    }

    /**
     * Generator new password
     * 
     * @param length of password
     * @return string, new password
     */
    public static String generatePassword(int length) {
	if (length < 1)
	    throw new IllegalArgumentException();

	StringBuilder sb = new StringBuilder(length);
	for (int i = 0; i < length; i++) {

	    // choose character randomly from PASSWORD_ALLOW
	    int rndCharAt = random.nextInt(PASSWORD_ALLOW.length());
	    char rndChar = PASSWORD_ALLOW.charAt(rndCharAt);

	    // debug
	    // System.out.format("%d\t:\t%c%n", rndCharAt, rndChar);

	    sb.append(rndChar);

	}

	return sb.toString();
    }

    /**
     * Shuffle string
     * 
     * @param string
     * @return string has been shuffled
     */
    public static String shuffleString(String string) {
	List<String> letters = Arrays.asList(string.split(""));
	Collections.shuffle(letters);
	return letters.stream().collect(Collectors.joining());
    }

    /**
     * Generate reset password request
     * 
     * @param username
     * @param newPassword
     * @param secretCode
     * @return Reset password centagate request
     * @throws Exception
     */
    public static ResetPasswordCentagateRequest generateResetPassRequest(String username, String newPassword,
	    String secretCode, String authToken) throws Exception {
	ResetPasswordCentagateRequest resetPassRequest = new ResetPasswordCentagateRequest();
	resetPassRequest.setUsername(username);
	resetPassRequest.setNewPassword(newPassword);
	resetPassRequest.setNotifyUser("");

	String key = secretCode;
	String plainText = env.getProperty("centagate.user.admin") + authToken;
	// encode cenToken using hmac SHA256
	resetPassRequest.setCenToken(CryptoUtil.encryptHmacSHA256(key, plainText));

	return resetPassRequest;
    }

    /**
     * Send Email
     * 
     * @param email   The receiver's email
     * @param subject The email's subject
     * @param text    The email's body
     * @return
     */
    public static SimpleMailMessage sendEmail(String email, String subject, String text) {
	SimpleMailMessage msg = new SimpleMailMessage();
	msg.setFrom("admin.api@bukopin.co.id");
	msg.setTo(email);
	msg.setSubject(subject);
	msg.setText(text);

	return msg;
    }

    /**
     * Generate Centagate Unlock User Request
     * 
     * @param username   The Centagate User's username
     * @param secretCode from admin response login
     * @param plaintext  username admin concat authToken from admin response login
     * @return The Object request.
     * @throws Exception
     */
    public static UnlockUserRequest generateUnlockUserRequest(String username, String secretCode, String plaintext)
	    throws Exception {
	UnlockUserRequest req = new UnlockUserRequest();
	req.setLockedUsername(username);
	req.setCenToken(CryptoUtil.encryptHmacSHA256(secretCode, plaintext));

	return req;
    }

    /* Overrides: */
    /**
     * Generate OTP (One Time Password)
     * 
     * @param otpLength The length of OTP.
     * @return The OTP string
     * @throws NoSuchAlgorithmException
     */
    public static String generateOTP(int otpLength) throws NoSuchAlgorithmException {
	StringBuilder otp = new StringBuilder();
	SecureRandom sr = SecureRandom.getInstance(BkpmConstants.OTP_ALGORITHM);
	for (int i = 1; i <= 6; i++) {
	    otp.append(String.valueOf(sr.nextInt(9)));
	}

	return otp.toString();
    }

    /**
     * Generate OTP Message
     * 
     * @param otp The OTP to be send.
     * @param locale The Locale from servlet request.
     * @param appSignature The hashed string need by frontend to autofill SMS OTP.
     * @return The OTP message to be send.
     */
    public static String generateOTPMessage(String otp, Locale locale, String appSignature) {
	String charOTP = appSignature;
	if (StringUtils.isBlank(appSignature)) {
//	    charOTP = Base64.getEncoder().encodeToString(CHAR_OTP.getBytes());
//	    charOTP = charOTP.replace("=", "");
	    charOTP = "";
	}
	String msg = messageUtil.get("sms.otp.message.template", new Object[] {otp, charOTP}, locale);

	return msg;
    }

    /* Request Generator: */
    /**
     * Generate Challenge Question Request
     * 
     * @param username The user's username.
     * @param ip       The IP Address request.
     * @return The reques object to send to Centagate.
     * @throws Exception
     */
    public static ChallengeQuestionRequest generateChallengeRequest(String username, String ip) throws Exception {
	ChallengeQuestionRequest request = new ChallengeQuestionRequest();
	request.setUsername(username);
	request.setIntegrationKey(env.getProperty("centagate.key.integration"));
	request.setUnixTimestamp(String.valueOf(Instant.now().getEpochSecond()));
	request.setAuthToken("");
	request.setIpAddress(ip);
	request.setUserAgent("");
	List<String> excludeFields = new ArrayList<>();
	excludeFields.add(BkpmConstants.FIELD_HMAC);
	excludeFields.add(BkpmConstants.FIELD_SERIAL_UID);
	request.setHmac(CryptoUtil.getHmacObject(request, excludeFields, 
		env.getProperty("centagate.key.secret")));

	return request;
    }

    /**
     * Generate Centagate Update User Status
     * 
     * @param username   Centagate User's username
     * @param status     active/inactive (1/0)
     * @param secretCode from admin response login
     * @param plaintext  username admin concat authToken from admin response login
     * @return The Object request.
     * @throws Exception
     */
    public static UpdateUserStatusCentagateRequest generateUpdateUserStatusRequest(String username, String status,
	    String secretCode, String plaintext) throws Exception {
	UpdateUserStatusCentagateRequest req = new UpdateUserStatusCentagateRequest();
	req.setUsername(username);
	req.setStatus(status);
	req.setCenToken(CryptoUtil.encryptHmacSHA256(secretCode, plaintext));

	return req;
    }

    public static SaveQARequest generateSaveQARequest(String secretCode, String plainText, String data)
	    throws Exception {
	SaveQARequest request = new SaveQARequest();
	request.setCenToken(CryptoUtil.encryptHmacSHA256(secretCode, plainText));
	request.setData(Base64.getEncoder().encodeToString(data.getBytes()));

	return request;
    }

    public static RegistrationCentagateRequest generateRegRequest(RegRequest regRequest, String secretCode,
	    String plainText) throws Exception {
	RegistrationCentagateRequest req = new RegistrationCentagateRequest();
	req.setFirstName("firstName");
	req.setLastName("lastName");
	req.setUsername(regRequest.getUsername());
	req.setUserApp("MOB-01");
	req.setUseruniqueId("userUniqueId");
	req.setUserClientId("userClientId");
	req.setUserAdditionalData1("");
	req.setUserAdditionalData2("");
	req.setUserAdditionalData3("");
	req.setUserAdditionalData4("");
	req.setUserAdditionalData5("");
	req.setUserEmail(regRequest.getEmail());
	req.setCenToken(CryptoUtil.encryptHmacSHA256(secretCode, plainText));

	return req;
    }

	public static CtgDeleteUser reqDeleteUser(String username, String secretCode, String plainText) throws Exception {
		CtgDeleteUser req = new CtgDeleteUser();
		req.setUsername(username);
		req.setCenToken(CryptoUtil.encryptHmacSHA256(secretCode, plainText));

		return req;
	}
	
	public static ResetQARequest generateResetQARequest(String username, String secretCode, String plainText, String qaId, String qaMessage, String ans) throws Exception {
		ResetQARequest req = new ResetQARequest();
		ans = Base64.getEncoder().encodeToString(ans.getBytes());
		String data = qaId + ":" + qaMessage + ":" + ans;
		
		req.setUsername(username);
		req.setData(data);
		req.setCenToken(CryptoUtil.encryptHmacSHA256(secretCode, plainText));
		
		return req;
	}
    
   /* public static String generateTrxId(){
		// generated uuid
		String uuid = UUID.randomUUID().toString().replace("-", "");		
		return uuid;
	}*/
	
    /**
     * Generate Security Question Authentication Request
     * 
     * @param username The user's username
     * @param questId  The question id
     * @param answer   The answer
     * @return
     * @throws Exception
     */
    public static QuestionAuthRequest generateSecurityQuestionAuthRequest(String username, String questId,
	    String answer) throws Exception {
	QuestionAuthRequest req = new QuestionAuthRequest();
	req.setUsername(username);
	req.setIntegrationKey(env.getProperty("centagate.key.integration"));
	req.setUnixTimestamp(String.valueOf(Instant.now().getEpochSecond()));

	String data = questId + ":" + answer;
	req.setData(Base64.getEncoder().encodeToString(data.getBytes()));

	req.setAuthToken("");
	req.setSupportFido("");
	req.setIpAddress("");
	req.setUserAgent("");
	req.setBrowserFp("");

	List<String> excludeFields = new ArrayList<>();
	excludeFields.add(BkpmConstants.FIELD_HMAC);
	excludeFields.add(BkpmConstants.FIELD_SERIAL_UID);
	req.setHmac(CryptoUtil.getHmacObject(req, excludeFields, env.getProperty("centagate.key.secret")));

	return req;
    }
    
    /**
     * Generate Logout Request Util
     * 
     * @param username The user's username will logout
     * @param authToken The user's authentication token got from response login
     * @return
     * @throws Exception
     */
    public static LogoutCentagateRequest generateLogoutRequest(String username, String authToken) throws Exception {
	LogoutCentagateRequest req = new LogoutCentagateRequest();
	
	req.setUsername(username);
	req.setIntegrationKey(env.getProperty("centagate.key.integration"));
	req.setUnixTimestamp(String.valueOf(Instant.now().getEpochSecond()));
	req.setAuthToken(authToken);
	
	List<String> excludeFields = new ArrayList<>();
	excludeFields.add(BkpmConstants.FIELD_HMAC);
	req.setHmac(CryptoUtil.getHmacObject(req, excludeFields, env.getProperty("centagate.key.secret")));
	
	return req;
    }
    
    public static CommonRequest<VerifyPINRequest> verifyPinReq(String username, String pin){
	Date now = new Date();
	
	VerifyPINRequest req = new VerifyPINRequest();
	req.setUsername(username);
	req.setPin(pin);
	
	Identity identity = new Identity();
	identity.setReqTime(now.toString());
	identity.setPlatform(PLATFORM_ANDROID);
	identity.setUserAgent("");
	identity.setToken("");
	identity.setSecretCode("");
	
	CommonRequest<VerifyPINRequest> request = new CommonRequest<VerifyPINRequest>();
	request.setIdentity(identity);
	request.setData(req);
	
	return request;
	
	
    }

}