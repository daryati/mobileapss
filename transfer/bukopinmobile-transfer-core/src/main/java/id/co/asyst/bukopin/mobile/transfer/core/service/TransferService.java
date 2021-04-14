/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.transfer.core.service;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import id.co.asyst.bukopin.mobile.common.core.util.BkpmUtil;
import id.co.asyst.bukopin.mobile.common.core.util.MessageUtil;
import id.co.asyst.bukopin.mobile.common.model.BkpmConstants;
import id.co.asyst.bukopin.mobile.common.model.ResponseMessage;
import id.co.asyst.bukopin.mobile.common.model.payload.CommonResponse;
import id.co.asyst.bukopin.mobile.transfer.model.PostingRes;
import id.co.asyst.bukopin.mobile.transfer.model.entity.Bank;
import id.co.asyst.bukopin.mobile.transfer.model.entity.ReceiverInfo;
import id.co.asyst.bukopin.mobile.user.model.entity.User;

/**
 * 
 * 
 * @author Kartika Dwi H
 * @version $Revision$, Feb 26, 2020
 * @since 1.1.Alpha1
 */
@Service
public class TransferService {
    /**
     * Logger
     */
    private final Logger log = LoggerFactory.getLogger(TransferService.class);
    
    /* Constants: */
    private static final String TRANSFER_OVER_TEMPLATE_NAME = "html/TransferOverTemplate";    
    private static final String TRANSFER_FUND_TEMPLATE_NAME = "html/TransferFundTemplate";
    private static final String SAVED_FUND_TEMPLATE_NAME = "html/SavedReceiverFundTemplate";
    private static final String SAVED_OVER_TEMPLATE_NAME = "html/SavedReceiverOverTemplate";
    
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
    
    @Autowired
    private BankService bankService;
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
    @Async("transferAsyncExecutor")
    public void sendEmailReceiptTransfer(PostingRes resTransfer, Map<String, String> user, Locale loc, String productName) {
	CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(), messageUtil.get("success", loc));
	
	try {
	    // set date and time
	    DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
	    DateFormat formatter1 = new SimpleDateFormat("dd MMM yyyy");
	    DateFormat formatterTm = new SimpleDateFormat("HHmmss");
	    DateFormat formatterTm1 = new SimpleDateFormat("HH:mm");
	    
		
	    // send struk emoney by email
		String subject = "";
		
	    
	    //From set full name
	    String fullName = "";
	    String middleName = user.get("middleName") != null ? user.get("middleName") : "";
	    String lastName = user.get("lastName") != null ? user.get("lastName") : "";
	    fullName = user.get("firstName") + " " + middleName + " " + lastName;
	   
		
	    // datetime sample: 20191204105933
	    String dateTime = resTransfer.getTransmissionDateTime();
	    String date="";
	    String time="";
		try {
			Date dt1 = formatter.parse(dateTime.substring(0,8));
		    Date tm1 = formatterTm.parse(dateTime.substring(8, dateTime.length()));
		    date= formatter1.format(dt1);
			time = formatterTm1.format(tm1);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		
	    //set format rupiah
	    NumberFormat id = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
	    
	    
	    //set rupiah format without ,00
	    BigDecimal totalBig = resTransfer.getAmount().add(resTransfer.getAdminFee());
	    String total = id.format(totalBig).replace(",00", "");
	    String adminCharge = id.format(resTransfer.getAdminFee()).replace(",00", "");
	    String amount = id.format(resTransfer.getAmount()).replace(",00", "");
	    
	    
	    //from - set account number per segment
	    String accNoOri = resTransfer.getPostingFrom().getAccountNumber().toString();
	    String accNoSeg1 = accNoOri.substring(0, 1);
	    String accNoSeg2 = accNoOri.substring(1, 4);
	    String accNoSeg3 = accNoOri.substring(4, 7);
	    String accNoSeg4 = accNoOri.substring(7, accNoOri.length());
	    String fromAccountNumber = accNoSeg1.concat(" "+accNoSeg2.concat(" "+accNoSeg3.concat(" "+accNoSeg4)));
	    
	    //to - set account number per segment
	    accNoOri = resTransfer.getPostingTo().getAccountNumber().toString();
	    accNoSeg1 = accNoOri.substring(0, 1);
	    accNoSeg2 = accNoOri.substring(1, 4);
	    accNoSeg3 = accNoOri.substring(4, 7);
	    accNoSeg4 = accNoOri.substring(7, accNoOri.length());
	    String toAccountNumber = accNoSeg1.concat(" "+accNoSeg2.concat(" "+accNoSeg3.concat(" "+accNoSeg4)));
	    
	    // thymleaf template mail
	    // Prepare the evaluation context
	    final Locale locale = new Locale("en_US.UTF-8");
	    final Context ctx = new Context(locale);	    
	    ctx.setVariable("fullname", fullName);
	    ctx.setVariable("reference", resTransfer.getBankReference());
	    ctx.setVariable("date", date);
	    ctx.setVariable("time", time);
	    ctx.setVariable("fromName", resTransfer.getPostingFrom().getAccountName());
	    ctx.setVariable("fromAccountType", productName);
	    ctx.setVariable("fromAccountNumber", fromAccountNumber);	    
	    ctx.setVariable("toName", resTransfer.getPostingTo().getAccountName());
	    Bank bank = bankService.findBankByBankCode(resTransfer.getPostingTo().getBankCode());
	    ctx.setVariable("toAccountType", bank.getBankName()); //?? dpt account type / bank name dr mn?
	    ctx.setVariable("toAccountNumber", toAccountNumber);	   
	    ctx.setVariable("total", total.replace("Rp", "Rp"));
	    ctx.setVariable("adminFee", adminCharge.replace("Rp", "Rp"));
	    ctx.setVariable("amount", amount.replace("Rp", "Rp"));
	    

		
	    
	    // Create the HTML body using Thymeleaf
	    String htmlContent = "";
	    if(BkpmConstants.OVERBOOK.equalsIgnoreCase(resTransfer.getTransferType())) {
	    	subject="Pemindahbukuan";
	    	ctx.setVariable("subject", subject);
	    	htmlContent = this.htmlTemplateEngine.process(TRANSFER_OVER_TEMPLATE_NAME, ctx);
		} else if(BkpmConstants.FUND_TRANSFER.equalsIgnoreCase(resTransfer.getTransferType())) {
			subject="Transfer Bank Lain";
			ctx.setVariable("subject", subject);
			htmlContent = this.htmlTemplateEngine.process(TRANSFER_FUND_TEMPLATE_NAME, ctx);
		}
	    // Prepare message using a Spring helper
	    final MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
	    final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, StandardCharsets.UTF_8.name());
	    message.setSubject(subject);
	    message.setFrom(env.getProperty("spring.mail.username"));
	    message.setTo(user.get("email"));
	    
	    message.setText(htmlContent, true); // true = isHtml
	    message.addInline("header", new ClassPathResource("/mail/images/Header-L.png"));
	    message.addInline("footer", new ClassPathResource("/mail/images/Footers-L.png"));

	    javaMailSender.send(mimeMessage);
	    log.debug("Transfer receipt struck has been sent successfully");

	} catch (MailAuthenticationException e) {
	    log.error(e.getMessage(), e);
	    response.setCode(ResponseMessage.SUCCESS.getCode());
	    response.setMessage(messageUtil.get("success", loc));
	} catch (MailSendException e) {
	    log.error("Failed sending email to: "+user.get("email") );
	    log.error(e.getMessage(), e);
	    response.setCode(ResponseMessage.SUCCESS.getCode());
	    response.setMessage(messageUtil.get("success", loc));
	} catch (MessagingException e) {
	    log.error("Failed sending email to: "+user.get("email") );
	    log.error(e.getMessage(), e);
	    response.setCode(ResponseMessage.SUCCESS.getCode());
	    response.setMessage(messageUtil.get("success", loc));
	}
	
    }
    
    @Async("transferAsyncExecutor")
    public void sendEmailReceiptSaved(ReceiverInfo receiver, User user, Locale loc, String method) {
	CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(), messageUtil.get("success", loc));
	
	try {
	    // set date and time
	    DateFormat formatter1 = new SimpleDateFormat("dd MMM yyyy");
	    DateFormat formatterTm1 = new SimpleDateFormat("HH:mm");
	    
		String subject = "Daftar Penerima";
		
	    //From set full name
	    String fullName = "";
	    String middleName = user.getMiddleName() != null ? user.getMiddleName() : "";
	    String lastName = user.getLastName() != null ? user.getLastName() : "";
	    fullName = user.getFirstName() + " " + middleName + " " + lastName;
	   
		
	    // datetime sample: 20191204105933
	    Date dt = new Date();
	    
		String date = formatter1.format(dt);
		String time = formatterTm1.format(dt);
		
	    //set account number
		String acc = receiver.getAccountNumber();
	    String acc1 = acc.substring(0,acc.length()-4).replaceAll("[0-9]", "*");
		String acc2 = acc.substring(acc.length()-4, acc.length());
		String toAccountNumber = acc1.concat(acc2);
	    
	    
	    // thymleaf template mail
	    // Prepare the evaluation context
	    final Locale locale = new Locale("en_US.UTF-8");
	    final Context ctx = new Context(locale);
	    ctx.setVariable("fullname", fullName);
	    ctx.setVariable("alias", receiver.getAlias());
	    ctx.setVariable("date", date);
	    ctx.setVariable("time", time);	    
	    ctx.setVariable("toAccountNumber", toAccountNumber);	   


		
	    // Prepare message using a Spring helper
	    final MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
	    final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, StandardCharsets.UTF_8.name());
	    message.setSubject(subject);
	    message.setFrom(env.getProperty("spring.mail.username"));
	    message.setTo(user.getEmail());

	    // Create the HTML body using Thymeleaf
	    String htmlContent = "";
	    if(BkpmConstants.OVERBOOK.equalsIgnoreCase(method)) {
	    	htmlContent = this.htmlTemplateEngine.process(SAVED_OVER_TEMPLATE_NAME, ctx);
		} else if(BkpmConstants.FUND_TRANSFER.equalsIgnoreCase(method)) {
			ctx.setVariable("toBankName", receiver.getBank().getBankName());
			htmlContent = this.htmlTemplateEngine.process(SAVED_FUND_TEMPLATE_NAME, ctx);
		}
	    
	    message.setText(htmlContent, true); // true = isHtml
	    message.addInline("header", new ClassPathResource("/mail/images/Header-L.png"));
	    message.addInline("footer", new ClassPathResource("/mail/images/Footers-L.png"));

	    javaMailSender.send(mimeMessage);
	    log.debug("Transfer receipt Saved has been sent successfully");

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
