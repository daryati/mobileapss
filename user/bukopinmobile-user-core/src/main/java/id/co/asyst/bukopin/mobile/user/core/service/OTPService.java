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

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import id.co.asyst.bukopin.mobile.common.core.util.BkpmUtil;
import id.co.asyst.bukopin.mobile.common.core.util.CryptoUtil;
import id.co.asyst.bukopin.mobile.common.model.BkpmConstants;
import id.co.asyst.bukopin.mobile.common.model.ResponseMessage;
import id.co.asyst.bukopin.mobile.common.model.payload.CommonResponse;
import id.co.asyst.bukopin.mobile.service.core.SMSService;
import id.co.asyst.bukopin.mobile.service.model.payload.SMSGatewayRequest;
import id.co.asyst.bukopin.mobile.user.core.repository.OTPRepository;
import id.co.asyst.bukopin.mobile.user.core.util.AuthUtil;
import id.co.asyst.bukopin.mobile.user.model.OTPTypeEnum;
import id.co.asyst.bukopin.mobile.user.model.entity.OneTimePassword;
import id.co.asyst.foundation.service.connector.Services;
import retrofit2.Response;

/**
 * Service Implements for managing OneTimePassword.
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Nov 1, 2019
 * @since 1.0.Alpha1-SNAPSHOT
 */
@Service("otpService")
@Transactional
public class OTPService {

    /**
     * Logger
     */
    private final Logger log = LoggerFactory.getLogger(OTPService.class);
    
    /**
     * Constant
     */
    private final String FIELD_OTP_VALIDITY = "otpExpiredTime";
    private static final String EMAIL_TEMPLATE_NAME = "html/emailtemplate";

    /**
     * OTP Repository
     */
    private final OTPRepository otpRepository;
    
    /**
     * Java Mail Sender
     */
    @Autowired
    private JavaMailSender javaMailSender;
    
    @Autowired
    private TemplateEngine htmlTemplateEngine;
    
    @Autowired
    private Environment env;

    /**
     * Constructor
     * 
     * @param repository
     *            The OTPRepository bean
     */
    public OTPService(OTPRepository repository) {
	this.otpRepository = repository;
    }

    /**
     * Save One Time Password
     * 
     * @param otp
     *            The OTP to save.
     * @return The persisted OTP.
     */
    public OneTimePassword save(OneTimePassword otp) {
	return otpRepository.save(otp);
    }

    /**
     * Validate OTP
     * 
     * @param username
     *            Username to find
     * @param otp
     *            OTP to find
     * @param currentDate
     *            Current date
     * 
     * @return Persist valid otp.
     */
    public boolean verifyOtp(String receiver, String otp, OTPTypeEnum type, Date currentDate) {
	boolean isValid = true;

	// validate otp
	String encryptedOtp = CryptoUtil.encryptBase64(otp);
	OneTimePassword result = otpRepository.findByReceiverAndOtpAndValidUntilAfter(receiver, encryptedOtp, currentDate);
	if (result == null) {
	    isValid = false;
	}

	return isValid;
    }

    /**
     * Find Active OTPs
     * 
     * @param username
     * @param type
     * @param currentDate
     * @return
     */
    public List<OneTimePassword> findActiveOTP(String receiver, OTPTypeEnum type, Date currentDate) {
	return otpRepository.findByReceiverAndTypeAndValidUntilAfter(receiver, type, currentDate);
    }

    /**
     * Save multiple OTP
     * 
     * @param otps
     *            The OTPs data to save
     */
    public void saveList(List<OneTimePassword> otps) {
	otpRepository.saveAll(otps);
    }
    
    /**
     * Send OTP by Email or phone number
     * 
     * @param receiver the user's email or phone number
     * @param otpType  type of OTP (SMS or Email)
     * @param locale The Locale from servlet request.
     * @param appSignature The hashed string need by frontend to autofill SMS OTP.
     * 
     * @return
     * @throws NoSuchAlgorithmException
     * @throws IOException
     */
    public CommonResponse sendOTP(String receiver, OTPTypeEnum otpType, Locale locale, String appSignature) 
	    throws NoSuchAlgorithmException, IOException {
	CommonResponse response = new CommonResponse();

	// Set Inactive to existing OTP
	Date currentDate = new Date();
	List<OneTimePassword> existingOtp = findActiveOTP(receiver, otpType, currentDate);
	if (existingOtp != null) {
	    existingOtp.forEach(otp -> {
		otp.setValidUntil(currentDate);
	    });
	    saveList(existingOtp);
	}

	// Generate new OTP
	String otpString = AuthUtil.generateOTP(BkpmConstants.OTP_LENGTH);
	
	try {
	    if (OTPTypeEnum.SMS.equals(otpType)) {
		// Send OTP to registered mobile phone number
		// - generate request
		SMSGatewayRequest smsRequest = new SMSGatewayRequest();
		// get transaction ID
		String txnId = BkpmUtil.generateTrxId();
		smsRequest.setClientTxnID(txnId);
		smsRequest.setMerchantTrx(BkpmConstants.MERCHANT_ID);
		smsRequest.setProductID(BkpmConstants.PRODUCT_ID);

		smsRequest.setDateTimeTrx(BkpmConstants.sdfDateTime.format(new Date()));
		smsRequest.setNoPonsel(receiver);
		smsRequest.setSmsMessage(AuthUtil.generateOTPMessage(otpString, locale, appSignature));
		log.debug("SMS Gateway -- http://10.0.14.115:80/E-MobileSMSMTPushBKP/EMobileSMSMTPushBKP");
		log.debug("Request: {}",BkpmUtil.convertToJson(smsRequest));

		// - sending sms
		Response<Object> resSms = Services.create(SMSService.class).sendSMS(smsRequest).execute();
		log.debug("Response: {}",BkpmUtil.convertToJson(resSms));
		// - error handling
		if (resSms.code() != HttpStatus.OK.value()) {
		    log.error("Failed sending sms to: " + receiver);
		    // handle error
		    response.setCode(ResponseMessage.ERROR_SEND_OTP.getCode());
		    return response;
		}
	    } else {
		// send otp by email
		String subject = "OTP";
		
		// thymleaf template mail
		// Prepare the evaluation context
		final Context ctx = new Context(locale);
		ctx.setVariable("otp", otpString);

		// Prepare message using a Spring helper
		final MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
		final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, StandardCharsets.UTF_8.name()); // true = multipart
		message.setSubject(subject);
		message.setFrom(env.getProperty("spring.mail.username"));
		message.setTo(receiver);

		// Create the HTML body using Thymeleaf
		final String htmlContent = this.htmlTemplateEngine.process(EMAIL_TEMPLATE_NAME, ctx);
		message.setText(htmlContent, true); // true = isHtml
		message.addInline("header", new ClassPathResource("/mail/images/Header-M.png"));
		message.addInline("footer", new ClassPathResource("/mail/images/Footers-M.png"));
		message.addInline("fb", new ClassPathResource("/mail/images/ic_Facebook-M.png"));
		message.addInline("halo", new ClassPathResource("/mail/images/ic_HaloBukopin-M.png"));
		message.addInline("ig", new ClassPathResource("/mail/images/ic_Instagram-M.png"));
		message.addInline("twitter", new ClassPathResource("/mail/images/ic_Twitter-M.png"));
		
		// Send mail
		javaMailSender.send(mimeMessage);
		log.debug("OTP has been sent successfully");
	    }

	    Calendar cal = Calendar.getInstance();
	    // Save OTP to DB
	    // Find existing OTP by type and receiver
	    OneTimePassword otp = new OneTimePassword();
	    List<OneTimePassword> otps = findByReceiverAndType(receiver, otpType);
	    if(otps!=null && !otps.isEmpty()) { 
		otp = otps.get(0);
	    }
	    otp.setReceiver(receiver);
	    otp.setOtp(CryptoUtil.encryptBase64(otpString)); // encrypt to base64
	    otp.setType(otpType);
	    otp.setCreateDate(cal.getTime());
	    cal.add(Calendar.MINUTE, BkpmConstants.OTP_SMS_VALID_TIME);
	    otp.setValidUntil(cal.getTime());
	    save(otp);

	    // set "valid until" response
	    SimpleDateFormat sdf = new SimpleDateFormat(BkpmConstants.FORMAT_TIME_WITH_ZONE);
	    Map<String, String> result = new HashMap<>();
	    result.put(FIELD_OTP_VALIDITY, sdf.format(otp.getValidUntil()));
	    
	    response.setData(result);
	    response.setCode(ResponseMessage.SUCCESS.getCode());
	} catch (MailAuthenticationException e) {
	    log.error(e.getMessage(), e);
	    response.setCode(ResponseMessage.INTERNAL_SERVER_ERROR.getCode());
	} catch (MailSendException e) {
	    log.error("Failed sending email to: " + receiver);
	    log.error(e.getMessage(), e);
	    response.setCode(ResponseMessage.ERROR_SEND_OTP.getCode());
	} catch (MessagingException e) {
	    log.error(e.getMessage(), e);
	    response.setCode(ResponseMessage.INTERNAL_SERVER_ERROR.getCode());
	}

	return response;
    }
    
    /**
     * Deletes <code>OneTimePassword</code>
     * 
     * @param id List of OTP's to delete.
     * @return true if delete success, false if not.
     */
    public boolean deleteOtp(List<OneTimePassword> otps) {
	boolean success = false;
	try {
	    otpRepository.deleteAll(otps);
	    success = true;
	} catch(Exception e) {
	    log.error("Delete OTPs failed, caused by: ", e.getCause());
	}
	
	return success;
    }
    
    /**
     * Delete OTP by Receiver and Type
     * @param receiver The OTP's receiver, it could be Phone Number or Email.
     * @param type The Type of OTP, it could be SMS or EMAIL.
     * @return Status delete OTP.
     */
    public boolean deleteOtp(String receiver, OTPTypeEnum type) {
	boolean success = false;
	List<OneTimePassword> otps = findByReceiverAndType(receiver, type);
	try {
	    otpRepository.deleteAll(otps);
	    success = true;
	} catch(Exception e) {
	    log.error("Delete OTPs failed, caused by: ", e.getCause());
	}
	
	return success;
    }
    
    /**
     * Inactivate OTP
     * 
     * @param receiver The OTP's receiver, it can be an Email Address or Phone Number.
     * @param type OTP Type, it can be SMS or EMAIL.
     * @param currentTime The current time.
     * 
     * @return true if delete success, false if not.
     */
    public boolean inactivateOTP(String receiver, OTPTypeEnum type, Date currentTime) {
	boolean success = false;
	try {
	    List<OneTimePassword> otps = findActiveOTP(receiver, type, currentTime);
	    deleteOtp(otps);
	    success = true;
	} catch (Exception e) {
	    log.error("Inactivate OTP failed, caused by: ", e.getCause());
	}
	return success;
    }
    
    /**
     * Find By Receiver And Type
     * 
     * @param Receiver The receiver of OTP, it should be emaill address or phone number.
     * @param type OTP Type, SMS or EMAIL.
     * @return List of OTP.
     */
    public List<OneTimePassword> findByReceiverAndType(String receiver, OTPTypeEnum type) {
	return otpRepository.findByReceiverAndType(receiver, type);
    }
}
