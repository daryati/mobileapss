/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.telco.core.service;

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
import id.co.asyst.bukopin.mobile.service.model.payload.telco.prepaid.PrepaidTelcoPurchaseResponse;
import id.co.asyst.bukopin.mobile.telco.model.TelcoTypeEnum;
import id.co.asyst.bukopin.mobile.telco.model.payload.PaymentPrepaidTelcoResponse;
import id.co.asyst.bukopin.mobile.telco.model.payload.TelcoPostpaidPaymentResponse;
import id.co.asyst.bukopin.mobile.user.model.entity.User;

/**
 * 
 * 
 * @author Kartika Dwi H
 * @version $Revision$, Feb 19, 2020
 * @since 1.1.Alpha1
 */
@Service
public class TelcoService {
    /**
     * Logger
     */
    private final Logger log = LoggerFactory.getLogger(TelcoService.class);

    /* Constants: */
    private static final String TELCO_PULSA_TEMPLATE_NAME = "html/PrepaidTelcoPulsaTemplate";
    private static final String TELCO_DATA_TEMPLATE_NAME = "html/PrepaidTelcoDataTemplate";
    private static final String TELCO_POSTPAID_TEMPLATE_NAME = "html/PostpaidTelcoTemplate";
    private static final String CATEGORY_TELEPHONE_TV = "Telepon & TV Kabel";
   
    
    /* Attributes: */
    
    /**
     * message util
     */
    @Autowired
    private MessageUtil messageUtil;
    
    /**
     * http servlet
     */
    @Autowired
    private HttpServletRequest servletRequest;

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
     * Send Receipt email after purchase prepaid telco
     * 
     * @param resPurchase information from purchase
     * @param user        information from user's
     * @return information success send email
     */
    @Async("telcoAsyncExecutor")
    public void sendEmailReceiptPrepaidTelco(PaymentPrepaidTelcoResponse resPurchase, Map<String, String> user,
	    Locale loc) {
	CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(),
		messageUtil.get("success", loc));

	try {
	    // set date and time
	    /*
	     * DateFormat formatter = new SimpleDateFormat("ddMMyyyy"); DateFormat
	     * formatter1 = new SimpleDateFormat("dd MMM yyyy"); DateFormat formatterTm =
	     * new SimpleDateFormat("HHmmss"); DateFormat formatterTm1 = new
	     * SimpleDateFormat("HH:mm");
	     */

	    // send struk emoney by email
	    String subject = "";
	    // set full name
	    String fullName = "";
	    String middleName = user.get("middleName") != null ? user.get("middleName") : "";
	    String lastName = user.get("lastName") != null ? user.get("lastName") : "";
	    fullName = user.get("firstName") + " " + middleName + " " + lastName;

	    String[] dateTime = resPurchase.getDateTime().split("\\-");
	    String date = dateTime[0];
	    String time = dateTime[1];

	    // set format rupiah
	    NumberFormat id = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));

	    // set rupiah format without ,00
	    String total = id.format(resPurchase.getTotalAmount()).replace(",00", "");
	    String adminCharge = id.format(resPurchase.getAdminFee()).replace(",00", "");
	    String amount = id.format(resPurchase.getAmount()).replace(",00", "");

	    // set account number per segment
	    String accNoOri = resPurchase.getAccountNumber();
	    String accNoSeg1 = accNoOri.substring(0, 1);
	    String accNoSeg2 = accNoOri.substring(1, 4);
	    String accNoSeg3 = accNoOri.substring(4, 7);
	    String accNoSeg4 = accNoOri.substring(7, 10);
	    String accountNumber = accNoSeg1.concat(" " + accNoSeg2.concat(" " + accNoSeg3.concat(" " + accNoSeg4)));

	    // set mobile number per segmant
	    String mobNum = resPurchase.getPhoneNumber();
	    String mob1 = mobNum.substring(0,4);
	    String mob2 = mobNum.substring(4,8);
	    String mob3 = mobNum.substring(8,mobNum.length());
	    String mobileNumber = mob1.concat(" "+mob2.concat(" "+mob3));

	    // thymleaf template mail
	    // Prepare the evaluation context
	    final Locale locale = new Locale("en_US.UTF-8");
	    final Context ctx = new Context(locale);
	    
	    ctx.setVariable("fullname", fullName);
	    ctx.setVariable("reference", resPurchase.getReferenceNumber());
	    ctx.setVariable("date", date);
	    ctx.setVariable("time", time);
	    ctx.setVariable("accountNumber", accountNumber);
	    ctx.setVariable("mobileNumber", mobileNumber);
	    ctx.setVariable("provider", resPurchase.getpGroup());
	    ctx.setVariable("total", total.replace("Rp", "RP "));
	    ctx.setVariable("adminCharge", adminCharge.replace("Rp", "Rp "));
	    ctx.setVariable("voucher", amount.replace("Rp", "Rp "));

	    String htmlContent = "";
	    if ("Prepaid".equalsIgnoreCase(resPurchase.getInstitutionType())) {
		subject = "Pulsa Prabayar";
		ctx.setVariable("subject", subject);
		htmlContent = this.htmlTemplateEngine.process(TELCO_PULSA_TEMPLATE_NAME, ctx);
	    } else {
		subject = "Paket Data Prabayar";
		ctx.setVariable("subject", subject);
		ctx.setVariable("packageType", resPurchase.getTitle());
		htmlContent = this.htmlTemplateEngine.process(TELCO_DATA_TEMPLATE_NAME, ctx);

	    }

	    // Prepare message using a Spring helper
	    final MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
	    final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, StandardCharsets.UTF_8.name());
	    message.setSubject(subject);
	    message.setFrom(env.getProperty("spring.mail.username"));
	    message.setTo(user.get("email"));

	    // Create the HTML body using Thymeleaf
	    // final String htmlContent =
	    // this.htmlTemplateEngine.process(TELCO_TEMPLATE_NAME, ctx);
	    message.setText(htmlContent, true); // true = isHtml
	    message.addInline("header", new ClassPathResource("/mail/images/Header-S.png"));
	    message.addInline("footer", new ClassPathResource("/mail/images/Footers-S.png"));
	    message.addInline("fb", new ClassPathResource("/mail/images/ic_Facebook-S.png"));
	    message.addInline("halo", new ClassPathResource("/mail/images/ic_HaloBukopin-S.png"));
	    message.addInline("ig", new ClassPathResource("/mail/images/ic_Instagram-S.png"));
	    message.addInline("twitter", new ClassPathResource("/mail/images/ic_Twitter-S.png"));

	    javaMailSender.send(mimeMessage);
	    log.debug("Prepaid Telco receipt has been sent successfully");

	} catch (MailAuthenticationException e) {
	    log.error(e.getMessage(), e);
	    response.setCode(ResponseMessage.SUCCESS.getCode());
	    response.setMessage(messageUtil.get("success", loc));
	} catch (MailSendException e) {
	    log.error("Failed sending email to: " + user.get("email"));
	    log.error(e.getMessage(), e);
	    response.setCode(ResponseMessage.SUCCESS.getCode());
	    response.setMessage(messageUtil.get("success", loc));
	} catch (MessagingException e) {
	    log.error("Failed sending email to: " + user.get("email"));
	    log.error(e.getMessage(), e);
	    response.setCode(ResponseMessage.SUCCESS.getCode());
	    response.setMessage(messageUtil.get("success", loc));
	}

    }

    /**
     * Send Email Receipt Postpaid Telco
     * 
     * @param resTelcoPayment response from postpaid payment
     * @param user Object user
     * @param loc Locale
     */
    @Async("telcoAsyncExecutor")
    public void sendEmailReceiptPostpaidTelco(TelcoPostpaidPaymentResponse resTelcoPayment, User user, String category, Locale loc) {
	CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(), messageUtil.get("success", loc));
	
	try {
	    // set date and time
	    DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
	    DateFormat formatter1 = new SimpleDateFormat("dd MMM yyyy");
	    DateFormat formatterTm = new SimpleDateFormat("HHmmss");
	    DateFormat formatterTm1 = new SimpleDateFormat("HH:mm");
	    DateFormat formatterBill = new SimpleDateFormat("ddMMyyyy");
	    DateFormat formatterBill1 = new SimpleDateFormat("MMM yyyy");
	    
	    String subject = " ";
	    String desc = "";
	    if(category.equalsIgnoreCase(CATEGORY_TELEPHONE_TV)) {
		// send struk emoney by email
	    	subject = "Telepon & TV Kabel";
			desc ="Telepon & TV Kabel";
		
	    } else {
	    	subject = "Pulsa Pascabayar";
			desc ="Pulsa Pascabayar";
	    }
	    
	    //set full name
	    String fullName = "";
	    String middleName = user.getMiddleName() != null ? user.getMiddleName() : "";
	    String lastName = user.getLastName() != null ? user.getLastName() : "";
	    fullName = user.getFirstName() + " " + middleName + " " + lastName;
	    
	    String date = "";
	    String time = "";
	    String billPeriode = "";
	    try {
		date = formatter1.format(formatter.parse(resTelcoPayment.getDate()));
		time = formatterTm1.format(formatterTm.parse(resTelcoPayment.getTime()));
		billPeriode = formatterBill1.format(formatterBill.parse(resTelcoPayment.getBillPeriode()));
	    } catch (ParseException e) {
		log.error(e.getMessage());
	    }
		
	     
	    
	    //set format rupiah
	    NumberFormat id = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
	    
	    
	    //set rupiah format without ,00
	    String total = id.format(resTelcoPayment.getTotalAmount()).replace(",00", "");
	    String adminCharge = id.format(resTelcoPayment.getAmountFee()).replace(",00", "");
	    String amount = id.format(resTelcoPayment.getAmount()).replace(",00", "");
	    
	    
	    //set account number per segment
	    String accNoOri = resTelcoPayment.getAccountNo();
	    String accNoSeg1 = accNoOri.substring(0, 1);
	    String accNoSeg2 = accNoOri.substring(1, 4);
	    String accNoSeg3 = accNoOri.substring(4, 7);
	    String accNoSeg4 = accNoOri.substring(7, 10);
	    String accountNumber = accNoSeg1.concat(" "+accNoSeg2.concat(" "+accNoSeg3.concat(" "+accNoSeg4)));
	    
	    //set mobile number
	    String custNoOri = resTelcoPayment.getCustNo();
	    String custNoSeg1 = custNoOri.substring(0, 4);
	    String custNoSeg2 = custNoOri.substring(4, 8);
	    String custNoSeg3 = custNoOri.substring(8, custNoOri.length());
	    String custNumber = custNoSeg1.concat(" "+custNoSeg2).concat(" "+custNoSeg3);
	    
	    // thymleaf template mail
	    // Prepare the evaluation context
	    final Locale locale = new Locale("en_US.UTF-8");
	    final Context ctx = new Context(locale);
	    ctx.setVariable("desc", desc);
	    ctx.setVariable("fullname", fullName);
	    ctx.setVariable("reference", resTelcoPayment.getReferensi());
	    ctx.setVariable("date", date);
	    ctx.setVariable("time", time);
	    ctx.setVariable("accountNumber", accountNumber);
	    ctx.setVariable("mobileNumber", custNumber);
	    ctx.setVariable("customerName", resTelcoPayment.getCustName());
	    ctx.setVariable("billPeriode", billPeriode);
	    ctx.setVariable("provider", resTelcoPayment.getProductName());
	    ctx.setVariable("total", total);
	    ctx.setVariable("adminCharge", adminCharge);
	    ctx.setVariable("bill", amount);
	    

	    // Prepare message using a Spring helper
	    final MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
	    final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, StandardCharsets.UTF_8.name());
	    message.setSubject(subject);
	    message.setFrom(env.getProperty("spring.mail.username"));
	    message.setTo(user.getEmail());

	    // Create the HTML body using Thymeleaf
	    final String htmlContent = this.htmlTemplateEngine.process(TELCO_POSTPAID_TEMPLATE_NAME, ctx);
	    message.setText(htmlContent, true); // true = isHtml
	    message.addInline("header", new ClassPathResource("/mail/images/Header-S.png"));
	    message.addInline("footer", new ClassPathResource("/mail/images/Footers-S.png"));
	    message.addInline("fb", new ClassPathResource("/mail/images/ic_Facebook-S.png"));
	    message.addInline("halo", new ClassPathResource("/mail/images/ic_HaloBukopin-S.png"));
	    message.addInline("ig", new ClassPathResource("/mail/images/ic_Instagram-S.png"));
	    message.addInline("twitter", new ClassPathResource("/mail/images/ic_Twitter-S.png"));

	    javaMailSender.send(mimeMessage);
	    log.debug("Postpaid Telco receipt has been sent successfully");

	} catch (MailAuthenticationException e) {
	    log.error(e.getMessage(), e);
	    response.setCode(ResponseMessage.SUCCESS.getCode());
	    response.setMessage(messageUtil.get("success", loc));
	} catch (MailSendException e) {
	    log.error("Failed sending email to: "+user.getEmail() );
	    log.error(e.getMessage(), e);
	    response.setCode(ResponseMessage.SUCCESS.getCode());
	    response.setMessage(messageUtil.get("success", loc));
	} catch (MessagingException e) {
	    log.error("Failed sending email to: "+user.getEmail() );
	    log.error(e.getMessage(), e);
	    response.setCode(ResponseMessage.SUCCESS.getCode());
	    response.setMessage(messageUtil.get("success", loc));
	}
	
    }

    /* Overrides: */
}
