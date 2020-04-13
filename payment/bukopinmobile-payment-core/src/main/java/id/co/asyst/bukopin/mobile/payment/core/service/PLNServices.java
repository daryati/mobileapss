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
import java.text.NumberFormat;
import java.util.Locale;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import id.co.asyst.bukopin.mobile.payment.model.payload.PLNPostpaidPaymentRequest;
import id.co.asyst.bukopin.mobile.payment.model.payload.PLNPostpaidPaymentResponse;
import id.co.asyst.bukopin.mobile.user.model.entity.User;

/**
 * 
 * 
 * @author Gibran Haq
 * @version $Revision$, Jan 23, 2020
 * @since 1.0.Alpha1-SNAPSHOT
 */
@Service
public class PLNServices {
    /* Constants: */
    private final Logger log = LoggerFactory.getLogger(PLNServices.class);
    
    private static final String POSTPAID_PLN_TEMPLATE_NAME = "html/postpaidplntemplate";

    /* Attributes: */
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

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */

    /* Getters & setters for transient attributes: */

    /* Functionalities: */    
    @Async("paymentAsyncExecutor")
    public void sendEmailReceiptPLN(PLNPostpaidPaymentRequest reqPayment,
	    PLNPostpaidPaymentResponse resPayment, User user) {
	log.debug("Sending Postpaid PLN email receipt to: "+user.getEmail());
	
	try {
	    // send struk pln prepaid by email
	    String subject = "PLN PASCABAYAR";
	    //set full name
	    String fullName = "";
	    String middleName = user.getMiddleName() != null ? user.getMiddleName() : "";
	    String lastName = user.getLastName() != null ? user.getLastName() : "";
	    fullName = user.getFirstName() + " " + middleName + " " + lastName;
	    
	    //set date and time
	    String date = resPayment.getDateTime().substring(0, 11);
	    String time = resPayment.getDateTime().substring(14);
	    
	    //set format rupiah
	    NumberFormat id = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
	    
	    //set footnote
	    String footnote = resPayment.getInfo();
	    String footnoteline1 = footnote.substring(0, footnote.lastIndexOf(". ") + 1);
	    
	    //set rupiah format without ,00
	    String amount = id.format(resPayment.getAmount()).replace(",00", "");
	    String totalAmount = id.format(resPayment.getTotalAmount()).replace(",00", "");
	    String adminCharge = id.format(resPayment.getAdminFee()).replace(",00", "");
	    
	    //set account number per segment
	    String accNoOri = reqPayment.getAccountNo();
	    String accNoSeg1 = accNoOri.substring(0, 1);
	    String accNoSeg2 = accNoOri.substring(1, 4);
	    String accNoSeg3 = accNoOri.substring(4, 7);
	    String accNoSeg4 = accNoOri.substring(7, 10);
	    String accountNumber = accNoSeg1.concat(" "+accNoSeg2.concat(" "+accNoSeg3.concat(" "+accNoSeg4)));
	    
	    //set bill period
	    String billPeriod = "";
	    for (int i = 0; i < resPayment.getBillPeriod().size(); i++) {
		billPeriod = billPeriod.concat(resPayment.getBillPeriod().get(String.valueOf(i+1))).concat(",");
	    }
	    billPeriod = billPeriod.substring(0, billPeriod.length() - 1);
	    
	    // thymleaf template mail
	    // Prepare the evaluation context
	    final Locale locale = new Locale("en_US.UTF-8");
	    final Context ctx = new Context(locale);
	    ctx.setVariable("fullname", fullName);
	    ctx.setVariable("reference", resPayment.getReferenceNumber());
	    ctx.setVariable("date", date);
	    ctx.setVariable("time", time);
	    ctx.setVariable("accountNumber", accountNumber);
	    ctx.setVariable("subscriberID", resPayment.getSubscriberNumber());
	    ctx.setVariable("subscriberName", resPayment.getSubscriberName());
	    ctx.setVariable("powerConsuming", resPayment.getTarif().concat("/").concat(resPayment.getDaya()).concat("VA"));
	    ctx.setVariable("billPeriod", billPeriod);
	    ctx.setVariable("standMeter", resPayment.getCurrMeter().concat("-").concat(resPayment.getPrevMeter()));
	    ctx.setVariable("outstandingBill", resPayment.getRemainBill());
	    ctx.setVariable("amount", amount.replace("Rp", ""));
	    ctx.setVariable("totalAmount", totalAmount.replace("Rp", "Rp "));
	    ctx.setVariable("adminCharge", adminCharge.replace("Rp", "Rp "));
	    ctx.setVariable("footnoteLine1", footnoteline1);

	    // Prepare message using a Spring helper
	    final MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
	    final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, StandardCharsets.UTF_8.name());
	    message.setSubject(subject);
	    message.setFrom(env.getProperty("spring.mail.username"));
	    //message.setTo(req.getData().get("email"));
	    message.setTo(user.getEmail());

	    // Create the HTML body using Thymeleaf
	    final String htmlContent = this.htmlTemplateEngine.process(POSTPAID_PLN_TEMPLATE_NAME, ctx);
	    message.setText(htmlContent, true); // true = isHtml
	    message.addInline("header", new ClassPathResource("/mail/images/Header-M.png"));
	    message.addInline("footer", new ClassPathResource("/mail/images/Footers-M.png"));
	    message.addInline("fb", new ClassPathResource("/mail/images/ic_Facebook-M.png"));
	    message.addInline("twitter", new ClassPathResource("/mail/images/ic_Twitter-M.png"));
	    message.addInline("ig", new ClassPathResource("/mail/images/ic_Instagram-M.png"));
	    message.addInline("halo", new ClassPathResource("/mail/images/ic_HaloBukopin-M.png"));

	    javaMailSender.send(mimeMessage);
	    log.debug("PLN Postpaid receipt has been sent successfully to: "+user.getEmail());

	} catch (Exception e) {
	    log.error("Error while sending email receipt to: "+user.getEmail()+" caused by: "+e.getMessage(), e);
	} 
	
    }

    /* Overrides: */}
