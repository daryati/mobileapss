/*
 * $Id$
 * 
 * Copyright (c) 2020 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.user.core.service;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import id.co.asyst.bukopin.mobile.common.core.util.CryptoUtil;
import id.co.asyst.bukopin.mobile.common.core.util.MessageUtil;
import id.co.asyst.bukopin.mobile.common.model.ResponseMessage;
import id.co.asyst.bukopin.mobile.common.model.payload.CommonResponse;
import id.co.asyst.bukopin.mobile.user.model.entity.AccountCard;
import id.co.asyst.bukopin.mobile.user.model.entity.AccountInfo;
import id.co.asyst.bukopin.mobile.user.model.entity.User;

/**
 * 
 * 
 * @author Ihsan Firman
 * @version $Revision$, Mar 5, 2020
 * @since 2.0
 */
@Service
public class UserMailService {
    /* Constants: */

    /**
     * Logger
     */
    private final Logger log = LoggerFactory.getLogger(UserMailService.class);

    private static final String EMAIL_REGISTRATION_NAME = "html/emailRegistration";
    private static final String EMAIL_BLOCKED_NAME = "html/emailBlocked";
    private static final String EMAIL_ACTIVATION_NAME = "html/emailActivation";
    private static final String EMAIL_NEW_DEVICE = "html/newDeviceEmail";
    private static final String EMAIL_CHANGE_PASSWORD = "html/changePassword";
    private static final String EMAIL_CHANGE_PIN = "html/changePin";
    private static final String EMAIL_FORGOT_PASSWORD = "html/forgotpasswordtemplate";
    
    private static final String PWD = "Password";
    private static final String PIN = "PIN";
    private static final String PASSWORD_DESC = " akun Bukopin Mobile Anda telah\n terkunci sementara, coba lagi setelah 60 menit";
    private static final String PIN_DESC = " akun Bukopin Mobile Anda telah terblokir,\n harap menghubungi customer service kami";
    private static final String PIN_DESC2 = "diblokir";
    private static final String PASSWORD_DESC2 = "dikunci untuk sementara waktu";
    private static final String SUBJECT_REGISTRATION = "Registrasi Akun Berhasil";
    private static final String SUBJECT_PIN = "Blokir PIN";
    private static final String SUBJECT_PASSWORD = "Blokir Password";
    private static final String SUBJECT_ACIVATION = "Aktivasi Nomor Rekening Berhasil";
    private static final String SUBJECT_CHANGE_PASSWORD = "Ubah Password";
    private static final String SUBJECT_CHANGE_PIN = "Ubah PIN Mobile";
    
    // Change password or pin params
    public static final String TYPE_CHANGE_PIN = "CHANGE_PIN";
    public static final String TYPE_CHANGE_PASSWORD = "CHANGE_PASSWORD";
    
    // main image
    private static final String MAIN_IMAGE_CHANGE_PASSWORD = "/mail/images/il_UbahPassword-M.png";
    private static final String MAIN_IMAGE_CHANGE_PIN = "/mail/images/il_UbahPINMobile-M.png";
    
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy', PUKUL 'hh:mm");
    
    /* Attributes: */
    @Autowired
    MessageUtil messageUtil;

    @Autowired
    HttpServletRequest servletRequest;

    @Autowired
    Environment env;

    @Autowired
    TemplateEngine htmlTemplateEngine;

    @Autowired
    JavaMailSender javaMailSender;

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */

    /* Getters & setters for transient attributes: */

    /* Functionalities: */
    /**
     * Send email after User Registration Successfully
     * 
     * @param receiver The user's email address
     * @param loc      The Locale from httprequest
     */
    @Async("userAsyncExecutor")
    public void sendRegistrationMail(String receiver, Locale loc) {
	CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(),
		messageUtil.get("success", loc));

	try {
	    String subject = SUBJECT_REGISTRATION;

	    final Context ctx = new Context(loc);
	    // Prepare message using a Spring helper
	    final MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
	    final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, StandardCharsets.UTF_8.name());
	    message.setSubject(subject);
	    message.setFrom(env.getProperty("spring.mail.username"));
	    message.setTo(receiver);

	    // Create the HTML body using Thymeleaf
	    final String htmlContent = this.htmlTemplateEngine.process(EMAIL_REGISTRATION_NAME, ctx);
	    message.setText(htmlContent, true);
	    message.addInline("reg", new ClassPathResource("/mail/images/il_RegisBerhasil-M.png"));
	    message.addInline("header", new ClassPathResource("/mail/images/Header-M.png"));
	    message.addInline("footer", new ClassPathResource("/mail/images/Footers-M.png"));
	    message.addInline("fb", new ClassPathResource("/mail/images/ic_Facebook-M.png"));
	    message.addInline("halo", new ClassPathResource("/mail/images/ic_HaloBukopin-M.png"));
	    message.addInline("ig", new ClassPathResource("/mail/images/ic_Instagram-M.png"));
	    message.addInline("twitter", new ClassPathResource("/mail/images/ic_Twitter-M.png"));

	    // send email
	    javaMailSender.send(mimeMessage);
	    log.debug("Registration mail successfully sent");

	} catch (MailAuthenticationException e) {
	    log.error(e.getMessage(), e);
	    response.setCode(ResponseMessage.INTERNAL_SERVER_ERROR.getCode());
	} catch (MailSendException e) {
	    log.error("Failed sending email to: " + receiver);
	    log.error(e.getMessage(), e);
	    response.setCode(ResponseMessage.INTERNAL_SERVER_ERROR.getCode());
	} catch (MessagingException e) {
	    log.error("Failed sending email to: " + receiver);
	    log.error(e.getMessage(), e);
	    response.setCode(ResponseMessage.INTERNAL_SERVER_ERROR.getCode());
	}
    }

    /**
     * Sent email for Blocked Password/PIN
     * 
     * @param receiver The user's email address
     * @param type     PASSWORD or PIN
     * @param loc      The Locale from httprequest
     */
    @Async("userAsyncExecutor")
    public void sentBlockedMail(String receiver, String type, Locale loc) {
	CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(),
		messageUtil.get("success", loc));

	try {
	    final Context ctx = new Context(loc);
	    String subject = "";
	    if (type.equalsIgnoreCase(PIN)) {
		subject = SUBJECT_PIN;
		ctx.setVariable("desc", PIN);
		ctx.setVariable("desc1", PIN_DESC);
		ctx.setVariable("desc2", PIN_DESC2);
	    } else {
		subject = SUBJECT_PASSWORD;
		ctx.setVariable("desc", PWD);
		ctx.setVariable("desc1", PASSWORD_DESC);
		ctx.setVariable("desc2", PASSWORD_DESC2);
	    }

	    final MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
	    final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, StandardCharsets.UTF_8.name());
	    message.setTo(receiver);
	    message.setSubject(subject);
	    message.setFrom(env.getProperty("spring.mail.username"));

	    // Create the HTML body using Thymeleaf
	    final String htmlContent = this.htmlTemplateEngine.process(EMAIL_BLOCKED_NAME, ctx);
	    message.setText(htmlContent, true);
	    message.addInline("blocked", new ClassPathResource("/mail/images/il_BlokirPIN-M.png"));
	    message.addInline("header", new ClassPathResource("/mail/images/Header-M.png"));
	    message.addInline("footer", new ClassPathResource("/mail/images/Footers-M.png"));
	    message.addInline("fb", new ClassPathResource("/mail/images/ic_Facebook-M.png"));
	    message.addInline("halo", new ClassPathResource("/mail/images/ic_HaloBukopin-M.png"));
	    message.addInline("ig", new ClassPathResource("/mail/images/ic_Instagram-M.png"));
	    message.addInline("twitter", new ClassPathResource("/mail/images/ic_Twitter-M.png"));

	    // send mail
	    javaMailSender.send(mimeMessage);
	    log.debug("Blocked PIN/Password mail successfully sent");

	} catch (MailAuthenticationException e) {
	    log.error(e.getMessage(), e);
	    response.setCode(ResponseMessage.INTERNAL_SERVER_ERROR.getCode());
	} catch (MailSendException e) {
	    log.error("Failed sending email to: " + receiver);
	    log.error(e.getMessage(), e);
	    response.setCode(ResponseMessage.INTERNAL_SERVER_ERROR.getCode());
	} catch (MessagingException e) {
	    log.error("Failed sending email to: " + receiver);
	    log.error(e.getMessage(), e);
	    response.setCode(ResponseMessage.INTERNAL_SERVER_ERROR.getCode());
	}

    }

    /**
     * Send Email for Activation
     * 
     * @param receiver     The user's email address
     * @param accountCardn The user's account card
     * @param loc          The Locale from httprequest
     */
    @Async("userAsyncExecutor")
    public void sentActivationMail(String receiver, AccountCard accountCard, Locale loc) {
	CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(),
		messageUtil.get("success", loc));

	List<AccountInfo> accounts = accountCard.getAccounts();
	List<String> accountNo = new ArrayList<>();
	
	for(int i = 0; i < accounts.size(); i++) {
//	    String accountNoDecrypted = CryptoUtil.decryptBase64(accounts.get(i).getAccountNo());
	    // account number already decrypted when get account card, no need to decrypted again.
	    String accountNoDecrypted = accounts.get(i).getAccountNo();
	    String sub1 = accountNoDecrypted.substring(0,1);
	    String sub2 = accountNoDecrypted.substring(1, 4);
	    String sub3 = accountNoDecrypted.substring(4, 7);
	    String sub4 = accountNoDecrypted.substring(7, 10);
	    
	    String number = sub1.concat(" " + sub2).concat(" " + sub3).concat(" " + sub4);
	    	
	    accountNo.add(number);
	}
	
	try {
	    String subject = SUBJECT_ACIVATION;

	    final Context ctx = new Context(loc);
	    
	    ctx.setVariable("nums", accountNo);

	    final MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
	    final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, StandardCharsets.UTF_8.name());
	    message.setSubject(subject);
	    message.setFrom(env.getProperty("spring.mail.username"));
	    message.setTo(receiver);

	    final String htmlContent = this.htmlTemplateEngine.process(EMAIL_ACTIVATION_NAME, ctx);
	    message.setText(htmlContent, true);
	    message.addInline("activate", new ClassPathResource("/mail/images/il_AktivasiNorekBerhasil-M.png"));
	    message.addInline("header", new ClassPathResource("/mail/images/Header-M.png"));
	    message.addInline("footer", new ClassPathResource("/mail/images/Footers-M.png"));
	    message.addInline("fb", new ClassPathResource("/mail/images/ic_Facebook-M.png"));
	    message.addInline("halo", new ClassPathResource("/mail/images/ic_HaloBukopin-M.png"));
	    message.addInline("ig", new ClassPathResource("/mail/images/ic_Instagram-M.png"));
	    message.addInline("twitter", new ClassPathResource("/mail/images/ic_Twitter-M.png"));

	    // send mail
	    javaMailSender.send(mimeMessage);
	    log.debug("Activation mail sent successfully");
	} catch (MailAuthenticationException e) {
	    log.error(e.getMessage(), e);
	    response.setCode(ResponseMessage.INTERNAL_SERVER_ERROR.getCode());
	} catch (MailSendException e) {
	    log.error("Failed sending email to: " + receiver);
	    log.error(e.getMessage(), e);
	    response.setCode(ResponseMessage.INTERNAL_SERVER_ERROR.getCode());
	} catch (MessagingException e) {
	    log.error("Failed sending email to: " + receiver);
	    log.error(e.getMessage(), e);
	    response.setCode(ResponseMessage.INTERNAL_SERVER_ERROR.getCode());
	}
    }

    /**
     * Send New Device Mail
     * 
     * @param user   The logged in User
     * @param locale The Locale from httprequest
     */
    @Async("userAsyncExecutor")
    public void sendNewDeviceMail(User user, Locale locale) {
	String email = user.getEmail();
	log.debug("Send new device mail: " + email);
	Date date = new Date();
	String dateTime = sdf.format(date);
	dateTime = dateTime.toUpperCase();

	try {
	    // prepare content
	    String subject = "New Device Login";
	    // var to pass
	    final Context ctx = new Context(locale);
	    ctx.setVariable("dateTime", dateTime);

	    // Prepare message using a Spring helper
	    final MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
	    final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, StandardCharsets.UTF_8.name());
	    message.setSubject(subject);
	    message.setFrom(env.getProperty("spring.mail.username"));
	    // message.setTo(req.getData().get("email"));
	    message.setTo(user.getEmail());

	    // Create the HTML body using Thymeleaf
	    final String htmlContent = this.htmlTemplateEngine.process(EMAIL_NEW_DEVICE, ctx);
	    message.setText(htmlContent, true); // true = isHtml
	    message.addInline("header", new ClassPathResource("/mail/images/Header-M.png"), "image/png");
	    message.addInline("footer", new ClassPathResource("/mail/images/Footers-M.png"), "image/png");
	    message.addInline("fb", new ClassPathResource("/mail/images/ic_Facebook-M.png"), "image/png");
	    message.addInline("twitter", new ClassPathResource("/mail/images/ic_Twitter-M.png"), "image/png");
	    message.addInline("ig", new ClassPathResource("/mail/images/ic_Instagram-M.png"), "image/png");
	    message.addInline("halo", new ClassPathResource("/mail/images/ic_HaloBukopin-M.png"), "image/png");
	    message.addInline("mainImg", new ClassPathResource("/mail/images/il_BedaDevice-M.png"), "image/png");

	    javaMailSender.send(mimeMessage);
	    log.error("New Device email successfully sent to: " + email);
	} catch (MessagingException e) {
	    log.error("Error send New Device email to: " + email + " caused by: " + e.getMessage(), e);
	}
    }
    
    /**
     * Send Change Password And PIN
     * 
     * @param email User's mail
     * @param changeType TYPE_CHANGE_PIN or TYPE_CHANGE_PASSWORD 
     * @param locale The Locale from httprequest
     */
    @Async("userAsyncExecutor")
    public void sendChangePasswordAndPIN(String email, String changeType, Locale locale) {
	log.debug("Send Change Password/ PIN email: " + email);
	Date date = new Date();
	String dateTime = sdf.format(date);
	dateTime = dateTime.toUpperCase();

	try {
	    String subject = SUBJECT_CHANGE_PASSWORD;
	    String template = EMAIL_CHANGE_PASSWORD;
	    String mainImage = MAIN_IMAGE_CHANGE_PASSWORD;
	    if(TYPE_CHANGE_PIN.equals(changeType)) {
		subject = SUBJECT_CHANGE_PIN;
		template = EMAIL_CHANGE_PIN;
		mainImage = MAIN_IMAGE_CHANGE_PIN;
	    }
	    
	    // var to pass
	    final Context ctx = new Context(locale);
	    ctx.setVariable("dateTime", dateTime);

	    // Prepare message using a Spring helper
	    final MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
	    final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, StandardCharsets.UTF_8.name());
	    message.setSubject(subject);
	    message.setFrom(env.getProperty("spring.mail.username"));
	    message.setTo(email);

	    // Create the HTML body using Thymeleaf
	    final String htmlContent = this.htmlTemplateEngine.process(template, ctx);
	    message.setText(htmlContent, true); // true = isHtml
	    message.addInline("header", new ClassPathResource("/mail/images/Header-M.png"), "image/png");
	    message.addInline("footer", new ClassPathResource("/mail/images/Footers-M.png"), "image/png");
	    message.addInline("fb", new ClassPathResource("/mail/images/ic_Facebook-M.png"), "image/png");
	    message.addInline("twitter", new ClassPathResource("/mail/images/ic_Twitter-M.png"), "image/png");
	    message.addInline("ig", new ClassPathResource("/mail/images/ic_Instagram-M.png"), "image/png");
	    message.addInline("halo", new ClassPathResource("/mail/images/ic_HaloBukopin-M.png"), "image/png");
	    message.addInline("mainImg", new ClassPathResource(mainImage), "image/png");
	    
	    javaMailSender.send(mimeMessage);
	    log.error("Change Password/ PIN email successfully sent to: "+email); 
	} catch (MessagingException e) {
	    log.error("Error send Change Password/ PIN email to: "+email+" caused by: "+e.getMessage(), e);
	}
    }
    
    /**
     * Send Forgot Password
     * 
     * @param user The User's entity
     * @param newPassword The new password
     * @param locale The Locale from httprequest
     */
    @Async("userAsyncExecutor")
    public void sendForgotPassword(User user, String newPassword, Locale locale) {
	// send new password by email
	String email = user.getEmail();
	String subject = "New Password";
	String fullName = "";
	if (user.isActivation()) {
	    String middleName = user.getMiddleName() != null ? user.getMiddleName() : "";
	    String lastName = user.getLastName() != null ? user.getLastName() : "";
	    fullName = user.getFirstName() + " " + middleName + " " + lastName;
	}

	try {
	    // thymleaf template mail
	    // Prepare the evaluation context
	    final Context ctx = new Context(locale);
	    ctx.setVariable("password", newPassword);
	    ctx.setVariable("fullname", fullName);

	    // Prepare message using a Spring helper
	    final MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
	    final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, StandardCharsets.UTF_8.name()); // true
														       // multipart
	    message.setSubject(subject);
	    message.setFrom(env.getProperty("spring.mail.username"));
	    message.setTo(user.getEmail());

	    // Create the HTML body using Thymeleaf
	    final String htmlContent = this.htmlTemplateEngine.process(EMAIL_FORGOT_PASSWORD, ctx);
	    message.setText(htmlContent, true); // true = isHtml
	    message.addInline("header", new ClassPathResource("/mail/images/Header-M.png"));
	    message.addInline("footer", new ClassPathResource("/mail/images/Footers-M.png"));
	    message.addInline("fb", new ClassPathResource("/mail/images/ic_Facebook-M.png"));
	    message.addInline("halo", new ClassPathResource("/mail/images/ic_HaloBukopin-M.png"));
	    message.addInline("ig", new ClassPathResource("/mail/images/ic_Instagram-M.png"));
	    message.addInline("twitter", new ClassPathResource("/mail/images/ic_Twitter-M.png"));

	    javaMailSender.send(mimeMessage);
	    log.debug("New Password has been sent successfully to: "+email);
	} catch (MessagingException e) {
	    log.error("Error send Forgot Password email to: " + email + " caused by: " + e.getMessage(), e);
	}
    }

    /* Overrides: */
}
