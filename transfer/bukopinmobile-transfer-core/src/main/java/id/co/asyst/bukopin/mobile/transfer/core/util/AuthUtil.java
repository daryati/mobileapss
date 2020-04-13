/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.transfer.core.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.mail.SimpleMailMessage;

import id.co.asyst.bukopin.mobile.common.core.util.CryptoUtil;
import id.co.asyst.bukopin.mobile.common.model.BkpmConstants;
import id.co.asyst.bukopin.mobile.common.model.payload.CommonRequest;
import id.co.asyst.bukopin.mobile.common.model.payload.Identity;
import id.co.asyst.bukopin.mobile.service.model.payload.ChallengeQuestionRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.CtgDeleteUser;
import id.co.asyst.bukopin.mobile.service.model.payload.GetAccountBalanceRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.LoginCentagateRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.QuestionAuthRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.RegRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.RegistrationCentagateRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.ResetPasswordCentagateRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.ResetQARequest;
import id.co.asyst.bukopin.mobile.service.model.payload.SaveQARequest;
import id.co.asyst.bukopin.mobile.service.model.payload.UnlockUserRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.UpdateUserStatusCentagateRequest;

/**
 * 
 * 
 * @author Gibran Haq
 * @version $Revision$, Nov 6, 2019
 * @since 1.0.Alpha1
 */
public class AuthUtil {
    /* Constants: */
    private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
    private static final String NUMBER = "0123456789";

    private static final String PASSWORD_ALLOW_BASE = CHAR_LOWER + CHAR_UPPER + NUMBER;

    private static SecureRandom random = new SecureRandom();

    /* Attributes: */

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */

    /* Getters & setters for transient attributes: */

    /* Functionalities: */   
   

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

   public static CommonRequest<GetAccountBalanceRequest> request(String accountType, String accountNumber, String username){
       Date now = new Date();
       
	GetAccountBalanceRequest request = new GetAccountBalanceRequest();
	request.setAccountType(accountType);
	request.setAccountNumber(accountNumber);
	request.setUsername(username);
	
	
	Identity identity = new Identity();
	identity.setReqTime(now.toString());
	identity.setPlatform("Android");
	identity.setUserAgent("");
	identity.setToken("");
	identity.setSecretCode("");
	
	CommonRequest<GetAccountBalanceRequest> commonRequest = new CommonRequest<GetAccountBalanceRequest>();
	commonRequest.setIdentity(identity);
	commonRequest.setData(request);
	
	return commonRequest;
   }
	
}