/*
 * $Id$
 * 
 * Copyright (c) 2020 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.purchase.core.service;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

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

import id.co.asyst.bukopin.mobile.purchase.model.payload.PurchasePrepaidResponsePLN;

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
    
    private static final String PREPAID_PLN_TEMPLATE_NAME = "html/prepaidplntemplate";

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
    /**
     * Send Receipt email after purchase pre paid PLN
     * 
     * @param resPurchase information from purchase
     * @param user information from user's
     * @return information success send email
     */
    @Async("purchaseAsyncExecutor")
    public void sendEmailReceiptPLN(PurchasePrepaidResponsePLN resPurchase, Map<String, String> user,
	    Locale locale) {
	try {
	    log.debug("Send PLN Prepaid receipt to: "+ user.get("email"));
	    
	    // send struk pln prepaid by email
	    String subject = "PLN PRABAYAR";
	    //set full name
	    String fullName = "";
	    String middleName = user.get("middleName") != null ? user.get("middleName") : "";
	    String lastName = user.get("lastName") != null ? user.get("lastName") : "";
	    fullName = user.get("firstName") + " " + middleName + " " + lastName;
	    
	    //set date and time
	    String date = resPurchase.getDateTime().substring(0, 11);
	    String time = resPurchase.getDateTime().substring(14);
	    
	    //set format rupiah
	    NumberFormat id = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
	    
	    //set footnote
	    String footnote = resPurchase.getFootNote();
	    String footnoteline1 = footnote.substring(0, footnote.lastIndexOf(". ") + 1);
	    String footnoteline2 = footnote.substring(footnote.lastIndexOf(". ") + 1);
	    
	    //set rupiah format without ,00
	    String total = id.format(resPurchase.getTotal()).replace(",00", "");
	    String adminCharge = id.format(resPurchase.getAdminCharge()).replace(",00", "");
	    
	    //set stroom token per segment
	    String stroomTokenOri = resPurchase.getStroomToken();
	    String sTokenSeg1 = stroomTokenOri.substring(0, 4);
	    String sTokenSeg2 = stroomTokenOri.substring(4, 8);
	    String sTokenSeg3 = stroomTokenOri.substring(8, 12);
	    String sTokenSeg4 = stroomTokenOri.substring(12, 16);
	    String sTokenSeg5 = stroomTokenOri.substring(16, 20);
	    String stroomToken = sTokenSeg1 + " " + sTokenSeg2 + " " + sTokenSeg3 + " " + sTokenSeg4 + " " + sTokenSeg5;
	    
	    //set account number per segment
	    String accNoOri = resPurchase.getAccountNo();
	    String accNoSeg1 = accNoOri.substring(0, 1);
	    String accNoSeg2 = accNoOri.substring(1, 4);
	    String accNoSeg3 = accNoOri.substring(4, 7);
	    String accNoSeg4 = accNoOri.substring(7, 10);
	    String accountNumber = accNoSeg1.concat(" "+accNoSeg2.concat(" "+accNoSeg3.concat(" "+accNoSeg4)));
	    
	    // thymleaf template mail
	    // Prepare the evaluation context
	    final Context ctx = new Context(locale);
	    ctx.setVariable("fullname", fullName);
	    ctx.setVariable("meter", resPurchase.getMeterSerialNumber());
	    ctx.setVariable("reference", resPurchase.getReference());
	    ctx.setVariable("date", date);
	    ctx.setVariable("time", time);
	    ctx.setVariable("accountNumber", accountNumber);
	    ctx.setVariable("subscriberID", resPurchase.getSubscriberID());
	    ctx.setVariable("subscriberName", resPurchase.getSubscriberName());
	    ctx.setVariable("powerConsuming", resPurchase.getPowerConsuming());
	    ctx.setVariable("total", total.replace("Rp", "RP "));
	    ctx.setVariable("stampDuty", id.format(new BigDecimal(resPurchase.getStampDuty())).replace("Rp", "RP "));
	    ctx.setVariable("valueAddedTax", id.format(new BigDecimal(resPurchase.getValueAddedTax())).replace("Rp", "RP "));
	    ctx.setVariable("publicLightTax", id.format(new BigDecimal(resPurchase.getPublicLightTax())).replace("Rp", "RP "));
	    ctx.setVariable("customerPayableInstallment", id.format(new BigDecimal(resPurchase.getCustomerPayableInstallment())).replace("Rp", "RP "));
	    ctx.setVariable("nominal", id.format(resPurchase.getNominal()).replace("Rp", "RP "));
	    ctx.setVariable("purchasedKWH", new Double(resPurchase.getPurchasedKWH()));
	    ctx.setVariable("stroomToken", stroomToken);
	    ctx.setVariable("adminCharge", adminCharge.replace("Rp", "RP "));
	    ctx.setVariable("footnoteLine1", footnoteline1);
	    ctx.setVariable("footnoteLine2", footnoteline2);

	    // Prepare message using a Spring helper
	    final MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
	    final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, StandardCharsets.UTF_8.name());
	    message.setSubject(subject);
	    message.setFrom(env.getProperty("spring.mail.username"));
	    message.setTo(user.get("email"));

	    // Create the HTML body using Thymeleaf
	    final String htmlContent = this.htmlTemplateEngine.process(PREPAID_PLN_TEMPLATE_NAME, ctx);
	    message.setText(htmlContent, true); // true = isHtml
	    message.addInline("header", new ClassPathResource("/mail/images/Header-L.png"));
	    message.addInline("footer", new ClassPathResource("/mail/images/Footers-L.png"));

	    javaMailSender.send(mimeMessage);
	    log.debug("PLN Prepaid receipt has been sent successfully");

	} catch (Exception e) {
	    log.error("Error while sending email receipt to: "+user.get("email")+" caused by: "+e.getMessage(), e);
	}
    }

    /* Overrides: */
}
