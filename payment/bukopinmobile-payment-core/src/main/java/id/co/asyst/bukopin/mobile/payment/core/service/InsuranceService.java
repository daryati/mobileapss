/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
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
import java.util.Map;

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

import id.co.asyst.bukopin.mobile.common.core.util.MessageUtil;
import id.co.asyst.bukopin.mobile.common.model.ResponseMessage;
import id.co.asyst.bukopin.mobile.common.model.payload.CommonResponse;
import id.co.asyst.bukopin.mobile.master.model.DestinationTypeEnum;
import id.co.asyst.bukopin.mobile.payment.model.payload.PaymentInsuranceResponse;

/**
 * 
 * 
 * @author Kartika Dwi H
 * @version $Revision$, Mar 16, 2020
 * @since 1.1.Alpha1
 */
@Service
public class InsuranceService {
    /**
     * Logger
     */
    private final Logger log = LoggerFactory.getLogger(InsuranceService.class);
    
    /* Constants: */
    private static final String INSURANCE_TEMPLATE_NAME = "html/InsuranceTemplate";
    
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
     * Send Receipt email after purchase Emoney
     * 
     * @param resPurchase information from purchase
     * @param user information from user's
     * @return information success send email
     */
    @Async("paymentAsyncExecutor")
    public void sendEmailReceiptInsurance(PaymentInsuranceResponse resPurchase, Map<String, String> user, Locale loc) {
	CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(), messageUtil.get("success", loc));
	
	try {
	    // set date and time
	    DateFormat formatter = new SimpleDateFormat("ddMMyyyy");
	    DateFormat formatter1 = new SimpleDateFormat("dd MMM yyyy");
	    DateFormat formatterTm = new SimpleDateFormat("HHmmss");
	    DateFormat formatterTm1 = new SimpleDateFormat("HH:mm");
	    
	    // send struk emoney by email
	    String subject = "";
	    if(DestinationTypeEnum.POSTBPJSKES.name().equalsIgnoreCase(resPurchase.getCodeIns())) {
		subject = "BPJS Kesehatan";
	    }else if (DestinationTypeEnum.POSTINHEALTH.name().equalsIgnoreCase(resPurchase.getCodeIns())) {
		subject = "Mandiri Inhealth";
		formatter = new SimpleDateFormat("yyyyMMdd");
	    }
	    
	    //set full name
	    String fullName = "";
	    String middleName = user.get("middleName") != null ? user.get("middleName") : "";
	    String lastName = user.get("lastName") != null ? user.get("lastName") : "";
	    fullName = user.get("firstName") + " " + middleName + " " + lastName;
	    
	    String dateTime = resPurchase.getDateTime().replace("//-", ",");
	    
	   /* String date = "";
	    String time = "";
		try {
			date = formatter1.format(formatter.parse(resPurchase.getDateTime()));
			time = formatterTm1.format(formatterTm.parse(resPurchase.getTime()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     */
	    
	    //set format rupiah
	    NumberFormat id = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
	    
	    
	    //set rupiah format without ,00
	    String total = id.format(resPurchase.getTotalAmount()).replace(",00", "");
	    String adminCharge = id.format(resPurchase.getAdminFee()).replace(",00", "");
	    String amount = id.format(resPurchase.getAmount()).replace(",00", "");
	    String prepaidInsurance = id.format(resPurchase.getPrepaidInsurance()).replace(",00", "");
	    String currentAmount = id.format(resPurchase.getCurrentAmount()).replace(",00", "");
	    
	    //set account number per segment
	    String accNoOri = resPurchase.getAccountNumber();
	    String accNoSeg1 = accNoOri.substring(0, 1);
	    String accNoSeg2 = accNoOri.substring(1, 4);
	    String accNoSeg3 = accNoOri.substring(4, 7);
	    String accNoSeg4 = accNoOri.substring(7, 10);
	    String accountNumber = accNoSeg1.concat(" "+accNoSeg2.concat(" "+accNoSeg3.concat(" "+accNoSeg4)));
	    
	  //set subscriber number
	    String subsNum = resPurchase.getSubscriberNumber();
	    String subsNum1 = subsNum.substring(0, 5);
	    String subsNum2 = subsNum.substring(5, 6);
	    String subsNum3 = subsNum.substring(6, subsNum.length());
	    String subscriberNumber = subsNum1.concat(" "+subsNum2.concat(" "+subsNum3));
	    
	    // thymleaf template mail
	    // Prepare the evaluation context
	    final Locale locale = new Locale("en_US.UTF-8");
	    final Context ctx = new Context(locale);
	    ctx.setVariable("subject", subject);
	    ctx.setVariable("fullname", fullName);
	    ctx.setVariable("reference", resPurchase.getReferenceNumber());
	    ctx.setVariable("dateTime", dateTime);
	   // ctx.setVariable("time", time);
	    ctx.setVariable("accountNumber", accountNumber);
	    ctx.setVariable("subscriberID", subscriberNumber);
	    ctx.setVariable("subscriberName", resPurchase.getSubscriberName());
	    ctx.setVariable("participant", resPurchase.getParticipant());
	    ctx.setVariable("month", resPurchase.getMonth().toString().concat(" Bulan"));
	    ctx.setVariable("total", total.replace("Rp", "Rp "));
	    ctx.setVariable("adminFee", adminCharge.replace("Rp", "Rp "));
	    ctx.setVariable("prepaidAmount", prepaidInsurance.replace("Rp", "Rp "));
	    ctx.setVariable("currentAmount", currentAmount.replace("Rp", "Rp "));// to be define in template
	    ctx.setVariable("amount", amount.replace("Rp", "Rp "));
	    ctx.setVariable("notes1", resPurchase.getNotes1());
	    ctx.setVariable("notes2", resPurchase.getNotes2());
	    
	    

	    // Prepare message using a Spring helper
	    final MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
	    final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, StandardCharsets.UTF_8.name());
	    message.setSubject(subject);
	    message.setFrom(env.getProperty("spring.mail.username"));
	    message.setTo(user.get("email"));

	    // Create the HTML body using Thymeleaf
	    final String htmlContent = this.htmlTemplateEngine.process(INSURANCE_TEMPLATE_NAME, ctx);
	    message.setText(htmlContent, true); // true = isHtml
	    message.addInline("header", new ClassPathResource("/mail/images/Header-S.png"));
	    message.addInline("footer", new ClassPathResource("/mail/images/Footers-S.png"));
	    message.addInline("fb", new ClassPathResource("/mail/images/ic_Facebook-S.png"));
	    message.addInline("halo", new ClassPathResource("/mail/images/ic_HaloBukopin-S.png"));
	    message.addInline("ig", new ClassPathResource("/mail/images/ic_Instagram-S.png"));
	    message.addInline("twitter", new ClassPathResource("/mail/images/ic_Twitter-S.png"));

	    javaMailSender.send(mimeMessage);
	    log.debug("Insurance receipt has been sent successfully");

	} catch (MailAuthenticationException e) {
	    log.error(e.getMessage(), e);
	    response.setCode(ResponseMessage.SUCCESS.getCode());
	    response.setMessage(messageUtil.get("success", loc));
	} catch (MailSendException e) {
	    log.error("Failed sending email to: " );
	    log.error(e.getMessage(), e);
	    response.setCode(ResponseMessage.SUCCESS.getCode());
	    response.setMessage(messageUtil.get("success", loc));
	} catch (MessagingException e) {
	    log.error("Failed sending email to: " );
	    log.error(e.getMessage(), e);
	    response.setCode(ResponseMessage.SUCCESS.getCode());
	    response.setMessage(messageUtil.get("success", loc));
	}
	
    }

    /* Overrides: */
}
