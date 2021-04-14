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
import java.util.Date;
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
import id.co.asyst.bukopin.mobile.payment.core.service.repository.CreditCardRepository;
import id.co.asyst.bukopin.mobile.payment.model.entity.CreditCard;
import id.co.asyst.bukopin.mobile.payment.model.payload.cc.PaymentCreditCardResponse;
import id.co.asyst.bukopin.mobile.user.model.entity.User;

/**
 * 
 * 
 * @author Ihsan Firman
 * @version $Revision$, Apr 6, 2020
 * @since 2.0
 */
@Service
@Transactional
public class CreditCardService {
    /* Constants: */
    /**
     * Logger
     */
    private final Logger log = LoggerFactory.getLogger(InsuranceTransService.class);

    private static final String SUBJECT_CC = "Kartu Kredit";
    private static final String CODE_CC_BKP = "CCBKP";
    private static final String CC_BKP_TEMPLATE_NAME = "html/CcBKPTemplate";
    private static final String CC_NON_BKP_TEMPLATE_NAME = "html/CcNonBKPTemplate";

    /* Attributes: */

    @Autowired
    CreditCardRepository creditCardRepository;

    @Autowired
    MessageUtil messageUtil;

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    TemplateEngine htmlTemplateEngine;

    @Autowired
    Environment env;

    /* Transient Attributes: */

    /* Constructors: */
    public CreditCardService(CreditCardRepository creditCardRepository) {
	this.creditCardRepository = creditCardRepository;
    }

    /* Getters & setters for attributes: */

    /* Getters & setters for transient attributes: */

    /* Functionalities: */
    @Transactional
    public CreditCard saveCreditCard(CreditCard creditCard) {
	log.debug("Saving Credit Card {} " + creditCard.getAmount());
	return creditCardRepository.save(creditCard);
    }

    @Async("paymentAsyncExecutor")
    public void sendEmailReceiptCreditCard(PaymentCreditCardResponse res, String codeCc, User user, Locale loc) {
	CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(),
		messageUtil.get("success", loc));

	try {
		
		Date today = new Date();
		
	    /*String resDate = res.getDate();
	    String resTime = res.getTime();*/

	    // set date and time
	    DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
	    DateFormat formatter1 = new SimpleDateFormat("dd MMM yyyy");
	    DateFormat formatterTm = new SimpleDateFormat("HHmmss");
	    DateFormat formatterTm1 = new SimpleDateFormat("HH:mm");

	    String subject = SUBJECT_CC;

	    String fullname = "";

	    String middleName = user.getMiddleName() != null ? user.getMiddleName() : "";
	    String lastName = user.getLastName() != null ? user.getLastName() : "";
	    fullname = user.getFirstName() + " " + middleName + " " + lastName;

	    String date = formatter1.format(today);
        String time = formatterTm1.format(today);

	    /*try {
		date = formatter1.format(formatter.parse(resDate));
		time = formatterTm1.format(formatterTm.parse(resTime));
	    } catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }*/
		
	    // set Currency Rupiah
	    NumberFormat id = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));

	    // set Rupiah without 00
	    String total = id.format(res.getAmount()).replace(",00", "");
	    String minimumPayment = "";
	    String billedAmount = "";

	    // format account number
	    String accNum = res.getAccountNumber();
	    String parse1 = accNum.substring(0, 1);
	    String parse2 = accNum.substring(1, 4);
	    String parse3 = accNum.substring(4, 7);
	    String parse4 = accNum.substring(7, 10);

	    String accountNumber = parse1.concat(" " + parse2.concat(" " + parse3.concat(" " + parse4)));

	    // format registered card
	    String regCard = res.getSubscriberNumber();
	    String card1 = regCard.substring(0, 4);
	    String card2 = regCard.substring(4, 8);
	    String card3 = regCard.substring(8, 12);
	    String card4 = regCard.substring(12, 16);

	    String registrationCard = card1.concat(" " + card2.concat(" " + card3.concat(" " + card4)));

	    final Locale locale = new Locale("en_US.UTF-8");
	    final Context ctx = new Context(locale);
	    String htmlContent = "";

	    // thymleaf template mail
	    // Prepare the evaluation context
	    ctx.setVariable("desc", subject);
	    ctx.setVariable("fullname", fullname);
	    ctx.setVariable("reference", res.getReferenceNumber());
	    ctx.setVariable("date", date);
	    ctx.setVariable("time", time);
	    ctx.setVariable("accountNumber", accountNumber);
	    ctx.setVariable("bankName", res.getName());
	    ctx.setVariable("registrationCard", registrationCard);
	    ctx.setVariable("amount", total);

	    // Prepare message using a Spring helper
	    final MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
	    final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, StandardCharsets.UTF_8.name());
	    message.setSubject(subject);
	    message.setFrom(env.getProperty("spring.mail.username"));
	    message.setTo(user.getEmail());

	    // Create the HTML body using Thymeleaf
	    htmlContent = this.htmlTemplateEngine.process(CC_NON_BKP_TEMPLATE_NAME, ctx);
	    //for CCBKP
	    if (codeCc.equalsIgnoreCase(CODE_CC_BKP)) {
		minimumPayment = id.format(res.getMinimumPayment()).replace(",00", "");
		billedAmount = id.format(res.getBilledAmount()).replace(",00", "");

		ctx.setVariable("subscriberName", res.getSubscriberName());
		ctx.setVariable("billedAmount", billedAmount);
		ctx.setVariable("minimumPayment", minimumPayment);
		
		htmlContent = this.htmlTemplateEngine.process(CC_BKP_TEMPLATE_NAME, ctx);
	    }

	    message.setText(htmlContent, true); // true = isHtml
	    message.addInline("header", new ClassPathResource("/mail/images/Header-L.png"));
	    message.addInline("footer", new ClassPathResource("/mail/images/Footers-L.png"));

	    javaMailSender.send(mimeMessage);
	    log.debug("Payment receipt has been sent successfully");

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

    /* Overrides: */}
