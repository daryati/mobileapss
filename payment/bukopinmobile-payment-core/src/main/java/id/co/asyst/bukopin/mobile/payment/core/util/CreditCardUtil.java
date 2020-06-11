/*
 * $Id$
 * 
 * Copyright (c) 2020 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.payment.core.util;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;

import id.co.asyst.bukopin.mobile.common.core.util.BkpmUtil;
import id.co.asyst.bukopin.mobile.common.model.payload.CommonRequest;
import id.co.asyst.bukopin.mobile.common.model.payload.Identity;
import id.co.asyst.bukopin.mobile.master.model.CategoryEnum;
import id.co.asyst.bukopin.mobile.master.model.TransactionTypeEnum;
import id.co.asyst.bukopin.mobile.master.model.entity.Destination;
import id.co.asyst.bukopin.mobile.master.model.payload.DestinationCommonRequest;
import id.co.asyst.bukopin.mobile.payment.model.payload.cc.CheckBINRequest;
import id.co.asyst.bukopin.mobile.payment.model.payload.cc.InquiryCreditCardRequest;
import id.co.asyst.bukopin.mobile.payment.model.payload.cc.InquiryCreditCardResponse;
import id.co.asyst.bukopin.mobile.payment.model.payload.cc.PaymentCreditCardRequest;
import id.co.asyst.bukopin.mobile.payment.model.payload.cc.PaymentCreditCardResponse;
import id.co.asyst.bukopin.mobile.service.model.payload.base.IdentityBaseCredentialTibco;
import id.co.asyst.bukopin.mobile.service.model.payload.base.IdentityBaseTibco;
import id.co.asyst.bukopin.mobile.service.model.payload.cc.InquiryCreditCardDataRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.cc.InquiryCreditCardTibcoReq;
import id.co.asyst.bukopin.mobile.service.model.payload.cc.InquiryCreditCardTibcoResponse;
import id.co.asyst.bukopin.mobile.service.model.payload.cc.PaymentCreditCardBkpTibcoDataReq;
import id.co.asyst.bukopin.mobile.service.model.payload.cc.PaymentCreditCardTibcoDataResp;
import id.co.asyst.bukopin.mobile.service.model.payload.cc.PaymentCreditCardTibcoReq;
import id.co.asyst.bukopin.mobile.service.model.payload.cc.PaymentCreditCardTibcoResponse;
import id.co.asyst.bukopin.mobile.user.model.AccountTypeEnum;

/**
 * 
 * 
 * @author Ihsan Firman
 * @version $Revision$, Apr 8, 2020
 * @since 2.0
 */
public class CreditCardUtil {
    /* Constants: */
    private static SecureRandom random = new SecureRandom();

    private static final String ALPHA_NUMERIC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    private static final String NUMBER = "0123456789";
    private static final String MTI_INQUIRY = "0200";
    private static final String MTI_PAYMENT = "0200";
    private static final int CC_LENGTH_TRXID = 12;
    private static final String CREDENTIALS_MBUKOPIN = "MBUKOPIN";
    private static final String PROCCESSING_CODE_INQUIRY = "380000";
    private static final String PROCCESSING_CODE_PAYMENT_SAVING = "501000";
    private static final String PROCCESSING_CODE_PAYMENT_GIRO = "502000";
    private static final String ACQUIRING_INSTITUTION_CODE = "4410010";
    private static final String MERCHANT_TYPE_MOBILE = "6017";
    private static final String IDR_CURRENCY_CODE = "360";
    private static final String TERMINAL_ID = "BKPM6017";
    private static final String TRDES_CCBKP = "MB : BAYAR KK BUKOPIN ";
	private static final String TRDES_CCBKP_01 = "-";

    // desc non BKP
    private static final String TRDES_CC_NON_BKP = "MB : BAYAR KK ";
    private static final String TRDES_CC_NON_BKP_01 = ": INET : KK ";

    /*private static final String TRDES2 = "00000000000000000000000IB0101";*/
    private static final String TRDES2 = "Dr. ";

    private static final String CODE_CC_BKP = "CCBKP";



    // element123 Components
    private static final String CONVENTION_RATE = "0000000000000000000";
    private static final String FEE_CODE = "00";
    private static final String OPERATION_CODE = "05";
    private static final String MIA_POST = "MIAPOST000";
    private static final String BIT_43_52 = "0000000000";
    private static final String BIT_53_72 = "00000000000000000000";
    private static final String BIT_73_76 = "0000";
    private static final String BIT_77_96 = "00000000000000000000";
    private static final String NOT_BRANCH = "000";
    private static final String NOT_LOCATION = "000";

    //
    private static final String ELEMENT_22 = "021";
    private static final String ROUTING_INF = "00000000441";
    private static final String FORWARD_ID_BUKOPIN = "441";

    private static SimpleDateFormat trdes2 = new SimpleDateFormat("yyMMddHHmm");
    private static DateFormat element7Format = new SimpleDateFormat("MMddHHmmss");
    private static SimpleDateFormat timeLocal = new SimpleDateFormat("HHmmss");
    private static SimpleDateFormat dateLocal = new SimpleDateFormat("MMdd");

    /* Attributes: */

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */

    /* Getters & setters for transient attributes: */

    /* Functionalities: */

    /* Overrides: */
    /**
     * Generate Request Inquiry for Tibco
     * @param req
     * @param regCard
     * @param codeCbs
     * @return
     */
    public static InquiryCreditCardTibcoReq generateInquiryRequest(InquiryCreditCardRequest req, String regCard,
	    String codeCbs) {
	InquiryCreditCardTibcoReq request = new InquiryCreditCardTibcoReq();

	// generate transaction id
	String txId = BkpmUtil.generateTrxId(CC_LENGTH_TRXID);

	Date today = new Date();

	IdentityBaseTibco identity = new IdentityBaseTibco();

	// set credential
	IdentityBaseCredentialTibco credential = new IdentityBaseCredentialTibco();
	credential.setClientid(CREDENTIALS_MBUKOPIN);

	// set identity
	identity.setClienttxnid(txId);
	identity.setReqdatetime(today);
	identity.setCredentials(credential);

	String STAN = generateNumber(6);
	String RRN = generateNumber(12);

	String paddingElement4 = new String(new char[20]).replace('\0', '0');

	// set element 4
	String element4 = paddingElement4 + "00";

	String element7 = element7Format.format(today);

	String subscriberNum = req.getSubscriberNumber();

	InquiryCreditCardDataRequest param = new InquiryCreditCardDataRequest();
	param.setMti(MTI_INQUIRY);
	param.setElement2(regCard);
	param.setElement3(PROCCESSING_CODE_INQUIRY);
	param.setElement4(element4);
	param.setElement7(element7);
	param.setElement11(STAN);
	param.setElement12(timeLocal.format(today));
	param.setElement13(dateLocal.format(today));
	param.setElement18(MERCHANT_TYPE_MOBILE);
	param.setElement22(ELEMENT_22);
	param.setElement32(FORWARD_ID_BUKOPIN);
	param.setElement37(RRN);
	param.setElement41(TERMINAL_ID);
	param.setElement48(subscriberNum);
	param.setElement49(IDR_CURRENCY_CODE);
	param.setElement120(ROUTING_INF + codeCbs + MERCHANT_TYPE_MOBILE + FORWARD_ID_BUKOPIN);

	request.setIdentity(identity);
	request.setParameter(param);

	return request;
    }

    public static InquiryCreditCardResponse generateInquiryResponse(InquiryCreditCardTibcoResponse tibcoResp,
	    String codeCc, String name) {
	InquiryCreditCardResponse response = new InquiryCreditCardResponse();

	String element62 = tibcoResp.getRespayment().getResult().getElement62();
	String subscriberNumber = tibcoResp.getRespayment().getResult().getElement48();
	String subscriberName = element62.substring(78);
	String billedAmount = element62.substring(22, 33);
	String minimumPayment = element62.substring(35, 46);
	String element11 = tibcoResp.getRespayment().getResult().getElement11();
	String element37 = tibcoResp.getRespayment().getResult().getElement37();
	String element120 = tibcoResp.getRespayment().getResult().getElement120();

	response.setCodeCc(codeCc);
	response.setName(name);
	response.setSubscriberNumber(subscriberNumber);
	response.setSubscriberName(subscriberName);
	response.setBilledAmount(new BigDecimal(billedAmount));
	response.setMinimumPayment(new BigDecimal(minimumPayment));
	response.setElement11(element11);
	response.setElement37(element37);
	response.setElement120(element120);

	return response;
    }

    /**
     * Generate request Payment for TIbco
     * @param req
     * @param bankName
     * @param regCard
     * @param accType
     * @param forwardInsCode
     * @param codeCbs
     * @return
     */
    public static PaymentCreditCardTibcoReq generatePaymentCreditCardTibcoReq(PaymentCreditCardRequest req,
	    String bankName, String regCard, String accType, String forwardInsCode, String codeCbs) {
	PaymentCreditCardTibcoReq request = new PaymentCreditCardTibcoReq();

	Date today = new Date();

	String username = req.getUsername();
	String subscriberNumber = req.getSubscriberNumber();
	String codeCc = req.getCodeCc();

	// generate transaction id
	String txId = BkpmUtil.generateTrxId(CC_LENGTH_TRXID);

	IdentityBaseCredentialTibco credentials = new IdentityBaseCredentialTibco();
	credentials.setClientid(CREDENTIALS_MBUKOPIN);

	IdentityBaseTibco identity = new IdentityBaseTibco();
	identity.setClienttxnid(txId);
	identity.setReqdatetime(today);
	identity.setCredentials(credentials);

	// set element 3
	String elm3 = "";
	if (accType.equalsIgnoreCase(AccountTypeEnum.SAVING.name())) {
	    elm3 = PROCCESSING_CODE_PAYMENT_SAVING;
	} else if (accType.equalsIgnoreCase(AccountTypeEnum.GIRO.name())) {
	    elm3 = PROCCESSING_CODE_PAYMENT_GIRO;
	}

	// set element 4
	String amt = req.getAmount().toString();
	String paddingEl4 = new String(new char[18 - amt.length()]).replace('\0', '0');

	String element4 = paddingEl4 + amt + "00";
	PaymentCreditCardBkpTibcoDataReq param = new PaymentCreditCardBkpTibcoDataReq();

	// element 11 for NON BKP
	String STAN = generateNumber(6);
	String STAN7 = generateNumber(7);

	String element120 = req.getElement120();

	String des = TRDES_CCBKP;
	String des2 = "";
	String des3 = TRDES_CCBKP_01;
	
	String usernamePad = StringUtils.rightPad(username, 15).replace("\0", " ");

	if (codeCc.equalsIgnoreCase(CODE_CC_BKP)) {
	    param.setElement2(regCard);
	    param.setElement11(req.getElement11());
	    param.setElement22(ELEMENT_22);
	    param.setElement32(FORWARD_ID_BUKOPIN);
	    param.setElement33(FORWARD_ID_BUKOPIN);
	    param.setElement37(req.getElement37());
	    param.setElement48(subscriberNumber);

	    des = StringUtils.rightPad(TRDES_CCBKP.concat(req.getSubscriberNumber()), 40);
	    des2 = StringUtils.rightPad(TRDES2.concat(req.getAccountNumber()), 40);
	    des3 = StringUtils.leftPad(TRDES_CCBKP_01, 60);

	} else {
	    param.setElement11(STAN);
	    String randAlphaNum = generateAlphanumeric(12);

	    // set element 28
	    // String amt = req.getAmount().toString();
	    String paddingEl28 = new String(new char[20]).replace('\0', '0');
	    param.setElement28(paddingEl28);
	    param.setElement32(ACQUIRING_INSTITUTION_CODE);
	    param.setElement33(forwardInsCode);
	    param.setElement37(randAlphaNum);

	    element120 = "00000000441".concat(codeCbs+"6017441");

	    des = StringUtils.rightPad(TRDES_CC_NON_BKP + " " + bankName.concat(req.getSubscriberNumber()), 40);
	    des2 = StringUtils.rightPad(TRDES2.concat(req.getAccountNumber()), 40);
	    des3 = StringUtils.leftPad(TRDES_CC_NON_BKP_01 + " " + bankName, 60);
	}

	String description = des + des2 + des3;

	String space118_147 = new String(new char[148 - 118]).replace('\0', ' ');

	String element123 = IDR_CURRENCY_CODE + CONVENTION_RATE + FEE_CODE + codeCbs + OPERATION_CODE + MIA_POST
		+ BIT_43_52 + BIT_53_72 + BIT_73_76 + BIT_77_96 + usernamePad + NOT_BRANCH + NOT_LOCATION + space118_147;

	param.setMti(MTI_PAYMENT);
	param.setElement3(elm3);
	param.setElement4(element4);
	param.setElement7(element7Format.format(today));

	param.setElement12(timeLocal.format(today));
	param.setElement13(dateLocal.format(today));
	param.setElement18(MERCHANT_TYPE_MOBILE);

	

	param.setElement41(TERMINAL_ID);
	param.setElement49(IDR_CURRENCY_CODE);
	param.setElement63(codeCbs);
	param.setElement100(FORWARD_ID_BUKOPIN);
	param.setElement102(req.getAccountNumber());
	param.setElement120(element120);
	param.setElement122(description);
	param.setElement123(element123);

	request.setIdentity(identity);
	request.setParameter(param);

	return request;
    }

    /**
     * Generate Response Data
     * @param req
     * @param resp
     * @return
     */
    public static PaymentCreditCardResponse generatePaymentCreditCardResponse(PaymentCreditCardRequest req,
	    PaymentCreditCardTibcoResponse resp) {
	PaymentCreditCardResponse response = new PaymentCreditCardResponse();

	String subscriberName = "";
	BigDecimal billedAmount = null;
	BigDecimal minimumPayment = null;

	Date today = new Date();
	String subscriberNumber = req.getSubscriberNumber();
	
	if (req.getCodeCc().equalsIgnoreCase(CODE_CC_BKP)) {
	    subscriberName = req.getSubscriberName();
	    subscriberNumber = resp.getRespayment().getResult().getElement48();
	    billedAmount = new BigDecimal(req.getBilledAmount());
	    minimumPayment = new BigDecimal(req.getMinimumPayment());
	}
	String element122 = resp.getRespayment().getResult().getElement122();
	String time = resp.getRespayment().getResult().getElement12();
	String accountNumber = resp.getRespayment().getResult().getElement102();
	

	String date = element122.substring(140, 148);
	String referenceNumber = element122.substring(144, 159);

	response.setDate(date);
	response.setTime(time);
	response.setReferenceNumber(referenceNumber);
	response.setAccountNumber(accountNumber);
	response.setName(req.getName());
	response.setSubscriberNumber(subscriberNumber);
	response.setSubscriberName(subscriberName);
	response.setBilledAmount(billedAmount);
	response.setMinimumPayment(minimumPayment);
	response.setAmount(new BigDecimal(req.getAmount()));

	return response;
    }


    /**
     * Generate Numeric String
     * @param length
     * @return
     */
    private static String generateNumber(int length) {
	if (length < 1) {
	    throw new IllegalArgumentException();
	}

	StringBuilder sb = new StringBuilder();
	for (int i = 0; i < length; i++) {

	    int rndCharAt = random.nextInt(NUMBER.length());
	    char rndChar = NUMBER.charAt(rndCharAt);

	    sb.append(rndChar);
	}

	return sb.toString();
    }

    /**
     * Generate Alphanumeric String
     * 
     * @param length
     * @return
     */
    private static String generateAlphanumeric(int length) {
	if (length < 1) {
	    throw new IllegalArgumentException();
	}

	StringBuilder sb = new StringBuilder();
	Random rnd = new Random();
	String charNum = ALPHA_NUMERIC;
	while (sb.length() < length) { // length of the random string.
	    int index = (int) (rnd.nextFloat() * charNum.length());
	    sb.append(charNum.charAt(index));
	}
	String aplhaNumber = sb.toString();

	return aplhaNumber;
    }
    
}
