/*
 * $Id$
 * 
 * Copyright (c) 2020 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.payment.core.service;

import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

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

import id.co.asyst.bukopin.mobile.common.core.util.MessageUtil;
import id.co.asyst.bukopin.mobile.common.model.ResponseMessage;
import id.co.asyst.bukopin.mobile.common.model.payload.CommonResponse;
import id.co.asyst.bukopin.mobile.payment.model.payload.SamolnasPaymentResponse;
import id.co.asyst.bukopin.mobile.user.model.entity.User;

/**
 * 
 * 
 * @author Gibran Haq
 * @version $Revision$, Apr 29, 2020
 * @since 1.2.Alpha1
 */
@Service
public class PaymentServices {
    /**
     * Logger
     */
    private final Logger log = LoggerFactory.getLogger(PaymentServices.class);

    /* Constants: */
    private static final String SAMOLNAS_TEMPLATE_NAME = "html/SamolnasTemplate";

    /* Attributes: */
    @Autowired
    private MessageUtil messageUtil;

    /**
     * Environment
     */
    @Autowired
    private Environment env;

    /**
     * Java Mail Sender
     */
    @Autowired
    private JavaMailSender javaMailSender;

    /**
     * Template Engine for email
     */
    @Autowired
    private TemplateEngine htmlTemplateEngine;
    /* Attributes: */

    /* Transient Attributes: */

    /* Constructors: */

    /**
     * Send Receipt email after payment Samolnas
     * 
     * @param resPayment information from payment
     * @param user       information from user's
     * @return information success send email
     */
    @Async("paymentAsyncExecutor")
    public void sendEmailReceiptSamolnas(SamolnasPaymentResponse resPayment, User user, Locale loc) {
	CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(),
		messageUtil.get("success", loc));

	try {
	    // set date and time
	    DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
	    DateFormat formatter1 = new SimpleDateFormat("dd MMM yyyy");
	    DateFormat formatterTm = new SimpleDateFormat("HHmmss");
	    DateFormat formatterTm1 = new SimpleDateFormat("HH:mm");

	    // set subject
	    String subject = "Samolnas";

	    // set full name
	    String fullName = "";
	    String middleName = user.getMiddleName() != null ? user.getMiddleName() : "";
	    String lastName = user.getLastName() != null ? user.getLastName() : "";
	    fullName = user.getFirstName() + " " + middleName + " " + lastName;

	    String date = "";
	    String time = "";
	    try {
		date = formatter1.format(formatter.parse(resPayment.getDate()));
		time = formatterTm1.format(formatterTm.parse(resPayment.getTime()));
	    } catch (ParseException e) {
		e.printStackTrace();
	    }
	    String dateTime = date.concat(", " + time);

	    // set format rupiah
	    NumberFormat id = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));

	    // set rupiah format without ,00
	    String total = id.format(resPayment.getTotalAmount()).replace(",00", "");
	    String adminCharge = id.format(resPayment.getAdminFee()).replace(",00", "");
	    String amount = id.format(resPayment.getAmount()).replace(",00", "");

	    // set account number per segment
	    String accNoOri = resPayment.getAccountNumber();
	    String accNoSeg1 = accNoOri.substring(0, 1);
	    String accNoSeg2 = accNoOri.substring(1, 4);
	    String accNoSeg3 = accNoOri.substring(4, 7);
	    String accNoSeg4 = accNoOri.substring(7, 10);
	    String accountNumber = accNoSeg1.concat(" " + accNoSeg2.concat(" " + accNoSeg3.concat(" " + accNoSeg4)));

	    // set pay Code
	    String payCode = resPayment.getPayCode();
	    String payCode1 = payCode.substring(0, 4);
	    String payCode2 = payCode.substring(4, 8);
	    String payCode3 = payCode.substring(8, 12);
	    String payCode4 = payCode.substring(12, payCode.length());
	    String payCodeFormat = payCode1.concat(" " + payCode2).concat(" " + payCode3).concat(" " + payCode4);
	    
	    // set nik
	    String nik = resPayment.getNik();
	    String nik1 = nik.substring(0, 4);
	    String nik2 = nik.substring(4, 8);
	    String nik3 = nik.substring(8, 12);
	    String nik4 = nik.substring(12, 16);
	    String nikFormat = nik1.concat(" " + nik2).concat(" " + nik3).concat(" " + nik4);

	    // thymleaf template mail
	    // Prepare the evaluation context
	    final Locale locale = new Locale("en_US.UTF-8");
	    final Context ctx = new Context(locale);
	    ctx.setVariable("subject", subject);
	    ctx.setVariable("fullname", fullName);
	    ctx.setVariable("reference", resPayment.getReferenceNumber());
	    ctx.setVariable("dateTime", dateTime);
	    ctx.setVariable("accountNumber", accountNumber);
	    ctx.setVariable("payCode", payCodeFormat);
	    ctx.setVariable("subscriberName", resPayment.getSubscriberName());
	    ctx.setVariable("nik", nikFormat);
	    ctx.setVariable("npkb", resPayment.getNpkb());
	    ctx.setVariable("machineNo", resPayment.getMachineNo());
	    ctx.setVariable("allTbkb", resPayment.getTbpkb().concat(" / " + resPayment.getNewTbpkb()));
	    ctx.setVariable("merkType", resPayment.getMerk().concat(" / " + resPayment.getType()));
	    ctx.setVariable("mdel", resPayment.getModel());
	    ctx.setVariable("progresifYear", resPayment.getProgresif().concat(" / " + resPayment.getYear()));
	    ctx.setVariable("validityPeriod", resPayment.getValidityPeriod());
	    ctx.setVariable("total", total);
	    ctx.setVariable("adminFee", adminCharge);
	    ctx.setVariable("amount", amount);

	    // Prepare message using a Spring helper
	    final MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
	    final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, StandardCharsets.UTF_8.name());
	    message.setSubject(subject);
	    message.setFrom(env.getProperty("spring.mail.username"));
	    message.setTo(user.getEmail());

	    // Create the HTML body using Thymeleaf
	    final String htmlContent = this.htmlTemplateEngine.process(SAMOLNAS_TEMPLATE_NAME, ctx);
	    message.setText(htmlContent, true); // true = isHtml
	    message.addInline("header", new ClassPathResource("/mail/images/Header-S.png"));
	    message.addInline("footer", new ClassPathResource("/mail/images/Footers-S.png"));
	    message.addInline("fb", new ClassPathResource("/mail/images/ic_Facebook-S.png"));
	    message.addInline("halo", new ClassPathResource("/mail/images/ic_HaloBukopin-S.png"));
	    message.addInline("ig", new ClassPathResource("/mail/images/ic_Instagram-S.png"));
	    message.addInline("twitter", new ClassPathResource("/mail/images/ic_Twitter-S.png"));

	    javaMailSender.send(mimeMessage);
	    log.debug("Samolnas receipt has been sent successfully");

	} catch (MailAuthenticationException e) {
	    log.error(e.getMessage(), e);
	    response.setCode(ResponseMessage.SUCCESS.getCode());
	    response.setMessage(messageUtil.get("success", loc));
	} catch (MailSendException e) {
	    log.error("Failed sending email to: ");
	    log.error(e.getMessage(), e);
	    response.setCode(ResponseMessage.SUCCESS.getCode());
	    response.setMessage(messageUtil.get("success", loc));
	} catch (MessagingException e) {
	    log.error("Failed sending email to: ");
	    log.error(e.getMessage(), e);
	    response.setCode(ResponseMessage.SUCCESS.getCode());
	    response.setMessage(messageUtil.get("success", loc));
	}

    }

    /* Overrides: */

}
