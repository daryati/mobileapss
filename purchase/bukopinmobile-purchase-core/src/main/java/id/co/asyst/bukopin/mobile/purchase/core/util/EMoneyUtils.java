/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.purchase.core.util;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;

import id.co.asyst.bukopin.mobile.common.core.util.BkpmUtil;
import id.co.asyst.bukopin.mobile.common.model.BkpmConstants;
import id.co.asyst.bukopin.mobile.common.model.payload.Identity;
import id.co.asyst.bukopin.mobile.master.model.DestinationTypeEnum;
import id.co.asyst.bukopin.mobile.master.model.EmoneyTypeEnum;
import id.co.asyst.bukopin.mobile.purchase.model.payload.InquiryLinkAjaRequest;
import id.co.asyst.bukopin.mobile.purchase.model.payload.PurchaseEMoneyRequest;
import id.co.asyst.bukopin.mobile.purchase.model.payload.PurchaseEMoneyResponse;
import id.co.asyst.bukopin.mobile.purchase.model.payload.PurchaseLinkAjaRequest;
import id.co.asyst.bukopin.mobile.purchase.model.payload.PurchaseLinkAjaResponse;
import id.co.asyst.bukopin.mobile.purchase.model.payload.PurchasePrepaidRequestPLN;
import id.co.asyst.bukopin.mobile.purchase.model.payload.PurchasePrepaidResponsePLN;
import id.co.asyst.bukopin.mobile.purchase.model.payload.inquiryEMoneyRequest;
import id.co.asyst.bukopin.mobile.purchase.model.payload.inquiryEMoneyResponse;
import id.co.asyst.bukopin.mobile.service.model.payload.emoney.EMoneyCredentialsRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.emoney.EMoneyDataRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.emoney.EMoneyIdentityRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.emoney.EMoneyInquiryRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.emoney.EMoneyInquiryResponse;
import id.co.asyst.bukopin.mobile.service.model.payload.emoney.EMoneyPurchaseDataRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.emoney.EMoneyPurchaseRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.emoney.EMoneyPurchaseResponse;
import id.co.asyst.bukopin.mobile.service.model.payload.emoney.LinkAjaDataRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.emoney.LinkAjaDataResponse;
import id.co.asyst.bukopin.mobile.service.model.payload.emoney.LinkAjaInquiryRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.emoney.LinkAjaPurchaseDataRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.emoney.LinkAjaPurchaseRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.emoney.LinkAjaPurchaseResponse;
import id.co.asyst.bukopin.mobile.service.model.payload.pln.prepaid.AdviceDataRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.pln.prepaid.PrepaidAdviceRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.pln.prepaid.PrepaidCredentialsRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.pln.prepaid.PrepaidDataRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.pln.prepaid.PrepaidIdentityRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.pln.prepaid.PrepaidInquiryRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.pln.prepaid.PrepaidPurchaseRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.pln.prepaid.PurchaseDataRequest;
import id.co.asyst.bukopin.mobile.user.model.AccountTypeEnum;

/**
 * 
 * 
 * @author Kartika Dwi H
 * @version $Revision$, Jan 20, 2020
 * @since 1.1.Alpha1
 */
public class EMoneyUtils {
    /* Constants: */
    private static final String NUMBER = "0123456789";
    private static SecureRandom random = new SecureRandom();

    private static final String MTI_INQUIRY = "2100";
    private static final String MTI_PURCHASE = "2200";
    private static final String MTI_ADVICE = "2220";
    private static final String ACCOUNT_NUMBER_PREPAID = "99502";
    private static final String PROCCESSING_CODE_INQUIRY = "380000";
    private static final String PROCCESSING_CODE_PURCHASE = "500000";
    private static final String PROCCESSING_CODE_PURCHASE_SAVING = "501000";
    private static final String PROCCESSING_CODE_PURCHASE_GIRO = "502000";
    private static final String PROCCESSING_CODE_ADVICE = "120000";
    private static final String MERCHANT_TYPE_MOBILE = "6017";
    private static final String ACQUIRING_INSTITUTION_CODE = "4410010";
    private static final String TERMINAL_IDENTIFICATION = "6184959459694705";
    private static final String ROUTING_INFO = "000004410013018016017441";
    private static final String TERMINAL_ID = "5413954120960104";
    private static final String IDR_CURRENCY_TRAN = "360";
    private static final String IDR_CURRENCY_CODE = "360";
    private static final String SETTLEMENT_ID = "441001";
    private static final String SETTLEMENT_ARANET_ID = "00000441001";
    private static final String PRODUCT_CODE_EMONEY = "810128";
    private static final String PRODUCT_CODE_EMONEY_CBS = "301801";
    private static final String FORWARD_ID_BUKOPIN = "441";
    private static final String TRDES1 = "MB : TOP UP GOPAY ";
    private static final String TRDES2 = "Dr. ";
    private static final String TRDES3 = "-";
    private static final String CONVENTION_RATE = "0000000000000000000";
    private static final String FEE_CODE = "00";
    private static final String OPERATION_CODE = "05";
    private static final String MIA_POST = "MIAPOST000";
    private static final String BIT_43_52 = "0000000000";
    private static final String BIT_53_72 = "00000000000000000000";
    private static final String BIT_73_76 = "0000";
    private static final String BIT_77_96 = "00000000000000000000";
    private static final String element103 = "0000000000";
    private static final String BIT_97_111 = "USERNAME       "; // ?? username dr mana?
    private static final String NOT_BRANCH = "000";
    private static final String NOT_LOCATION = "000";
    private static final String GOPAY = "GOPAY";
    private static final String OVO = "OVO";
    private static final String CREDENTIALS_MBUKOPIN = "MBUKOPIN";
    private static final String ELEMENT_60 = "05";

    // OVO
    private static final String PRODUCT_CODE_EMONEY_OVO = "812277";
    private static final String ROUTING_INFO_OVO = "000004410013018026017441";
    private static final String TRDES1_OVO = "MB : TOP UP OVO ";
    //private static final String TRDES3_OVO = "INET : ISI OVO 01";
    private static final String PRODUCT_CODE_EMONEY_CBS_OVO = "301802";

    // LINK AJA
    private static final String PRODUCT_CODE_EMONEY_LINK_AJA = "301803";
    private static final String PRIVATE_DATA = "017007";
    private static final String ROUTING_INFO_LINK_AJA = "000004410013018036017441";
    private static final String TRDES1_LA = "MB : TOP UP LINKAJA ";
    private static final String TRDES3_LA = "-";

    private static final String ALPHA_NUMERIC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    private static SimpleDateFormat purchaseResTime = new SimpleDateFormat("dd MMM yyyy - HH:mm");
    private static DateFormat element7Format = new SimpleDateFormat("MMddHHmmss");
    private static SimpleDateFormat timeLocal = new SimpleDateFormat("HHmmss");
    private static SimpleDateFormat dateLocal = new SimpleDateFormat("MMdd");
    private static SimpleDateFormat dateLocalServer = new SimpleDateFormat("yyMMdd");
    private static SimpleDateFormat trdes2 = new SimpleDateFormat("yyMMddHHmm");
    private static int EMONEY_LENGTH_TRXID = 12;

    /* Attributes: */

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */

    /* Getters & setters for transient attributes: */

    /* Functionalities: */
    /**
     * generate Inquiry Prepaid PLN Request
     * 
     * @param id subscriber ID or meter ID
     * @return
     */
    public static EMoneyInquiryRequest generateInquiryEMoneyReq(String custNo, String forwardInsCode, String language,
	    String flagEmoney, BigDecimal reqAmount) {
	EMoneyInquiryRequest req = new EMoneyInquiryRequest();
	// generate STAN
	String STAN = generateSTAN(6);

	Date date = new Date();

	EMoneyIdentityRequest identity = new EMoneyIdentityRequest();
	identity.setReqdatetime(new Date());
	// get transaction ID
	String txId = BkpmUtil.generateTrxId(EMONEY_LENGTH_TRXID);
	identity.setClienttxnid(txId);

	EMoneyCredentialsRequest credentials = new EMoneyCredentialsRequest();
	credentials.setClientid("MBUKOPIN");

	identity.setCredentials(credentials);

	EMoneyDataRequest param = new EMoneyDataRequest();
	param.setMti(MTI_INQUIRY);
	param.setElement3(PROCCESSING_CODE_INQUIRY);
	param.setElement7(element7Format.format(date));
	param.setElement11(STAN);
	param.setElement12(timeLocal.format(date));
	param.setElement13(dateLocal.format(date));
	param.setElement18(MERCHANT_TYPE_MOBILE);
	param.setElement32(ACQUIRING_INSTITUTION_CODE);
	param.setElement33(forwardInsCode);
	param.setElement41(TERMINAL_IDENTIFICATION);
	param.setElement49(IDR_CURRENCY_CODE);
	param.setElement37(generateSTAN(12));
	
	String padding = new String(new char[20 - custNo.length()]).replace('\0', ' ');// (untuk PROD)
	// String padding =new String(new char[18-custNo.length()]).replace('\0', '
	// ');//(untuk DEV)
	// System.out.println("PADDING "+padding.length());

	// set language
	String lang = "";
	if ("ID".equalsIgnoreCase(language) || "IN".equalsIgnoreCase(language)) {
	    lang = "1";
	} else if ("EN".equalsIgnoreCase(language)) {
	    lang = "0";
	}

	// gopay ovo filter for set element 61 and 63
	if (GOPAY.equalsIgnoreCase(flagEmoney)) {
	    String el61 = custNo + padding + lang;
	    param.setElement61(el61);
	    param.setElement63(PRODUCT_CODE_EMONEY);
	    param.setElement120(ROUTING_INFO);
	} else if (OVO.equalsIgnoreCase(flagEmoney)) {
	    // padding 20 number + code language
	    String element61 = StringUtils.rightPad(custNo, 20).concat(lang);
	    param.setElement61(element61);
	    param.setElement63(PRODUCT_CODE_EMONEY_OVO);
	    param.setElement120(ROUTING_INFO_OVO);
	    
	    // set request for element 4
	    String amount = reqAmount.toString() + "00";
	    String padding4 = new String(new char[20 - amount.length()]).replace('\0', '0');
	    String paddingZeroElm4 = padding4 + amount;
	    param.setElement4(paddingZeroElm4);
	}

	req.setIdentity(identity);
	req.setParameter(param);

	return req;
    }

    /**
     * generate Purchase EMoney Request
     * 
     * @param request information to set purchase
     * 
     * @return request for purchase
     */
    public static EMoneyPurchaseRequest generatePurchaseEMoneyReq(PurchaseEMoneyRequest request, String forwardInsCode,
	    String accType) {
	EMoneyPurchaseRequest purchaseReq = new EMoneyPurchaseRequest();
	Date date = new Date();
	String typeEmoney = request.getType();

	EMoneyIdentityRequest identity = new EMoneyIdentityRequest();
	identity.setReqdatetime(date);
	// get transaction ID
	String txId = BkpmUtil.generateTrxId(EMONEY_LENGTH_TRXID);
	identity.setClienttxnid(txId);

	EMoneyCredentialsRequest credentials = new EMoneyCredentialsRequest();
	credentials.setClientid("MBUKOPIN");
	identity.setCredentials(credentials);

	// set element 3
	String elm3 = "";
	if (accType.equalsIgnoreCase(AccountTypeEnum.SAVING.name())) {
	    elm3 = PROCCESSING_CODE_PURCHASE_SAVING;
	} else if (accType.equalsIgnoreCase(AccountTypeEnum.GIRO.name())) {
	    elm3 = PROCCESSING_CODE_PURCHASE_GIRO;
	}

	// TO DO : get saving type for element 4
	// set request for element 4
	String amount = request.getAmount().toBigInteger().toString() + "00";
	String padding = new String(new char[20 - amount.length()]).replace('\0', '0');
	String paddingZeroElm4 = padding + amount;

	// set request element 7
	String transmissionDateAndTime = element7Format.format(new Date());

	// set request element 28 (admin fee)
	String adminFee = request.getElement61().substring(78, 89);
	Integer admFee = Integer.valueOf(adminFee);
	adminFee = admFee + "00";
	String paddingAdminFee = new String(new char[20 - adminFee.length()]).replace('\0', '0');
	paddingAdminFee = paddingAdminFee.concat(adminFee);

	/*
	 * String paddingAdminFee = StringUtils.leftPad(admFee.toString().concat("00"),
	 * 12); paddingAdminFee = paddingAdminFee.replace("\\s+", "0");
	 */

	// System.out.println("..... admin FEEEE "+paddingAdminFee);

	// set request element 37 : only numeric.
	// Elment37 should equal with inquiry
	String retrievalRefNumber = request.getElement37();

	// set request element 48, add '0' for new generate pln token
	// String additionalData = request.getElement48();

	EMoneyPurchaseDataRequest param = new EMoneyPurchaseDataRequest();

	// set description, element 122 for gopay or ovo
	String description = "";
	String productCodeEmoney = "";
	if (GOPAY.equalsIgnoreCase(typeEmoney)) {
	    String des1 = StringUtils.rightPad(TRDES1.concat(request.getCustomerNumber()), 40);
	    String des2 = StringUtils.rightPad(TRDES2.concat(request.getAccountNumber()), 40);
	    String des3 = StringUtils.leftPad(TRDES3, 60);
	    description = des1.concat(des2).concat(des3);
	    productCodeEmoney = PRODUCT_CODE_EMONEY_CBS;
	    param.setElement63(PRODUCT_CODE_EMONEY);
	}
	if (OVO.equalsIgnoreCase(typeEmoney)) {
	    String des1 = StringUtils.rightPad(TRDES1_OVO.concat(request.getCustomerNumber()), 40);
	    String des2 = StringUtils.rightPad(TRDES2.concat(request.getAccountNumber()), 40);
	    String des3 = StringUtils.leftPad("", 60);
	    description = des1.concat(des2).concat(des3);
	    productCodeEmoney = PRODUCT_CODE_EMONEY_CBS_OVO;
	    param.setElement63(PRODUCT_CODE_EMONEY_OVO);
	}

	// set description, element 123
	String spasi118_147 = new String(new char[148 - 118]).replace('\0', ' ');
	String username = StringUtils.rightPad(request.getUsername(), 15);

	String elemt123 = IDR_CURRENCY_CODE + CONVENTION_RATE + FEE_CODE + productCodeEmoney + OPERATION_CODE + MIA_POST
		+ BIT_43_52 + BIT_53_72 + BIT_73_76 + BIT_77_96 + username + NOT_BRANCH + NOT_LOCATION + spasi118_147;

	param.setMti(MTI_PURCHASE);
	param.setElement3(elm3);
	param.setElement4(paddingZeroElm4);
	param.setElement7(transmissionDateAndTime);
	param.setElement11(request.getElement11());
	param.setElement12(timeLocal.format(date));
	param.setElement13(dateLocal.format(date));
	param.setElement18(MERCHANT_TYPE_MOBILE);
	param.setElement28(paddingAdminFee);
	param.setElement32(ACQUIRING_INSTITUTION_CODE);
	param.setElement33(forwardInsCode);
	param.setElement37(retrievalRefNumber);
	param.setElement41(TERMINAL_ID);
	// param.setElement48(additionalData);
	// param.setElement48(request.getElement48());
	param.setElement49(IDR_CURRENCY_TRAN);
	String element61 = request.getElement61();
	param.setElement61(element61.substring(0, element61.length() - 1));
	param.setElement100(SETTLEMENT_ID);
	param.setElement102(request.getAccountNumber());
	param.setElement103(element103);
	param.setElement120(SETTLEMENT_ARANET_ID + productCodeEmoney + MERCHANT_TYPE_MOBILE + FORWARD_ID_BUKOPIN);
	param.setElement122(description);
	param.setElement123(elemt123);

	purchaseReq.setIdentity(identity);
	purchaseReq.setParameter(param);

	return purchaseReq;
    }

    /**
     * Generator new password
     * 
     * @param length of password
     * @return string, new password
     */
    public static String generateSTAN(int length) {
	if (length < 1)
	    throw new IllegalArgumentException();

	StringBuilder sb = new StringBuilder(length);
	for (int i = 0; i < length; i++) {

	    // choose character randomly from PASSWORD_ALLOW
	    int rndCharAt = random.nextInt(NUMBER.length());
	    char rndChar = NUMBER.charAt(rndCharAt);

	    // debug
	    // System.out.format("%d\t:\t%c%n", rndCharAt, rndChar);

	    sb.append(rndChar);

	}
	return sb.toString();
    }

    /*    
    *//**
        * generate Advice Prepaid PLN Request
        * 
        * @param request information to set purchase
        * 
        * @return mapped resquest Advice
        *//*
	   * public static PrepaidAdviceRequest
	   * generateAdvicePrepaidPLNReq(PurchasePrepaidRequestPLN request, String
	   * forwardInsCode) { PrepaidAdviceRequest adviceReq = new
	   * PrepaidAdviceRequest(); Date date = new Date();
	   * 
	   * PrepaidIdentityRequest identity = new PrepaidIdentityRequest();
	   * identity.setReqdatetime(date); // get transaction ID String txId =
	   * BkpmUtil.generateTrxId(PLN_LENGTH_TRXID); identity.setClienttxnid(txId);
	   * 
	   * PrepaidCredentialsRequest credentials = new PrepaidCredentialsRequest();
	   * credentials.setClientid("MBUKOPIN"); identity.setCredentials(credentials);
	   * 
	   * // set request for element 4 String adminCharge =
	   * request.getElement48().substring(138, 142); BigDecimal nominalDec = new
	   * BigDecimal(request.getNominal()); BigDecimal adminChargeDec = new
	   * BigDecimal(adminCharge); String total =
	   * String.valueOf(nominalDec.add(adminChargeDec).doubleValue()); int
	   * sizeElement4 = 10; int sizeAmount = request.getNominal().length(); String
	   * paddingZeroElm4 = ""; for (int i = 1; i <= sizeElement4 - sizeAmount; i++) {
	   * paddingZeroElm4 = paddingZeroElm4 + "0"; } paddingZeroElm4 = paddingZeroElm4
	   * + total.replace(".0", "") + "00";
	   * 
	   * // set request element 48, add '0' for new generate pln token String
	   * additionalData = request.getElement48() + 0;
	   * 
	   * AdviceDataRequest param = new AdviceDataRequest(); param.setMti(MTI_ADVICE);
	   * param.setElement2(ACCOUNT_NUMBER_PREPAID);
	   * param.setElement3(PROCCESSING_CODE_ADVICE);
	   * param.setElement4(paddingZeroElm4);
	   * param.setElement11(request.getElement11());
	   * param.setElement12(timeLocal.format(date));
	   * param.setElement13(dateLocal.format(date));
	   * param.setElement18(MERCHANT_TYPE_MOBILE);
	   * param.setElement32(ACQUIRING_INSTITUTION_CODE);
	   * param.setElement33(forwardInsCode); param.setElement41(TERMINAL_ID);
	   * param.setElement48(additionalData); param.setElement49(IDR_CURRENCY_TRAN);
	   * param.setElement62(request.getElement62());
	   * param.setElement120(SETTLEMENT_ARANET_ID + PRODUCT_CODE_PREPAID +
	   * MERCHANT_TYPE_MOBILE + FORWARD_ID_BUKOPIN);
	   * 
	   * adviceReq.setIdentity(identity); adviceReq.setParameter(param);
	   * 
	   * return adviceReq; }
	   * 
	   */

    /**
     * Generate Request Inquiry LinkAja to Tibco
     * 
     * @param amount
     * @param custNo
     * @param forwardInsCode
     * @param language
     * @return
     */
    public static LinkAjaInquiryRequest generateInquiryLinkAja(String amount, String custNo, String forwardInsCode,
	    String language) {
	System.out.println("generate request inquiry Link Aja...");
	LinkAjaInquiryRequest req = new LinkAjaInquiryRequest();
	LinkAjaDataRequest param = new LinkAjaDataRequest();

	Date date = new Date();

	String txid = BkpmUtil.generateTrxId(EMONEY_LENGTH_TRXID);

	EMoneyCredentialsRequest credentials = new EMoneyCredentialsRequest();
	credentials.setClientid(CREDENTIALS_MBUKOPIN);

	EMoneyIdentityRequest identity = new EMoneyIdentityRequest();
	identity.setReqdatetime(new Date());
	identity.setClienttxnid(txid);

	identity.setCredentials(credentials);

	String STAN6 = generateSTAN(6);
	String STAN12 = generateSTAN(12);

	String padding = new String(new char[15 - custNo.length()]).replace('\0', ' ');
	String paddingElement4 = new String(new char[18 - amount.length()]).replace('\0', '0');

	// set element 4
	String element4 = paddingElement4 + amount + "00";

	// set request element 7
	String transmissionDateAndTime = element7Format.format(new Date());

	// set request element 48
	String element48 = custNo + padding;

	param.setMti(MTI_INQUIRY);
	param.setElement3(PROCCESSING_CODE_INQUIRY);
	param.setElement4(element4);
	param.setElement7(transmissionDateAndTime);
	param.setElement11(STAN6);
	param.setElement12(timeLocal.format(date));
	param.setElement13(dateLocal.format(date));
	param.setElement18(MERCHANT_TYPE_MOBILE);
	param.setElement32(ACQUIRING_INSTITUTION_CODE);
	param.setElement33(forwardInsCode);
	param.setElement37(STAN12);
	param.setElement41(TERMINAL_ID);
	param.setElement48(element48);
	param.setElement49(IDR_CURRENCY_CODE);
	param.setElement63(PRIVATE_DATA);
	param.setElement60(ELEMENT_60);
	param.setElement120(ROUTING_INFO_LINK_AJA);

	req.setIdentity(identity);
	req.setParameter(param);
	return req;

    }

    /**
     * generatePurchaseLinkAja
     * 
     * @param request
     * @param forwardInsCode
     * @return
     */
    public static LinkAjaPurchaseRequest generatePurchaseLinkAja(PurchaseLinkAjaRequest request, String forwardInsCode,
	    String accType) {
	System.out.println("Generate request Purchase LinkAja for TIbco...");
	LinkAjaPurchaseRequest purchaseRequest = new LinkAjaPurchaseRequest();

	Date date = new Date();
	// identity
	EMoneyIdentityRequest identity = new EMoneyIdentityRequest();
	identity.setReqdatetime(date);
	String txId = BkpmUtil.generateTrxId(EMONEY_LENGTH_TRXID);
	identity.setClienttxnid(txId);

	// credentials
	EMoneyCredentialsRequest credentials = new EMoneyCredentialsRequest();
	credentials.setClientid(CREDENTIALS_MBUKOPIN);

	identity.setCredentials(credentials);
	String amt = String.valueOf(request.getAmount());

	String paddingElement4 = new String(new char[18 - amt.length()]).replace('\0', '0');

	// set element 3
	String elm3 = "";
	if (accType.equalsIgnoreCase(AccountTypeEnum.SAVING.name())) {
	    elm3 = PROCCESSING_CODE_PURCHASE_SAVING;
	} else if (accType.equalsIgnoreCase(AccountTypeEnum.GIRO.name())) {
	    elm3 = PROCCESSING_CODE_PURCHASE_GIRO;
	}

	// set element 4
	String element4 = paddingElement4 + amt + "00";

	// element7
	String transmissionDateAndTime = element7Format.format(date);

	// element28
	/* String amtFee = String.valueOf(request.getAmountFee()); */

	String[] el48 = request.getElement48().split("\\|", -1);
	String adminFee = el48[6];
	String paddingAdminFee = new String(new char[12 - adminFee.length()]).replace('\0', '0');
	paddingAdminFee = paddingAdminFee.concat(adminFee).concat("00");

	// element37
	String STAN12 = generateSTAN(12);

	// element48
	String element48 = request.getElement48();
	
	String accNo = request.getAccountNo();

	// set description, element 122
	String des1 = StringUtils.rightPad(TRDES1_LA.concat(request.getCustNo()), 40);
	String des2 = StringUtils.rightPad(TRDES2.concat(accNo), 40);
	String des3 = StringUtils.rightPad("", 60);
	String description = des1.concat(des2).concat(des3);

	// set description, element 123
	String spasi118_147 = new String(new char[148 - 118]).replace('\0', ' ');
	String username = StringUtils.rightPad(request.getUsername(), 15);

	String elemt123 = IDR_CURRENCY_CODE + CONVENTION_RATE + FEE_CODE + PRODUCT_CODE_EMONEY_LINK_AJA + OPERATION_CODE
		+ MIA_POST + BIT_43_52 + BIT_53_72 + BIT_73_76 + BIT_77_96 + username + NOT_BRANCH + NOT_LOCATION
		+ spasi118_147;

	LinkAjaPurchaseDataRequest param = new LinkAjaPurchaseDataRequest();
	System.out.println("Add parameters Purchase LinkAja for TIbco...");
	param.setMti(MTI_PURCHASE);
	param.setElement3(elm3);
	param.setElement4(element4);
	param.setElement7(transmissionDateAndTime);
	param.setElement11(request.getElement11());
	param.setElement12(timeLocal.format(date));
	param.setElement13(dateLocal.format(date));
	param.setElement18(MERCHANT_TYPE_MOBILE);
	param.setElement28(paddingAdminFee);
	param.setElement32(ACQUIRING_INSTITUTION_CODE);
	param.setElement33(forwardInsCode);
	param.setElement37(STAN12);
	param.setElement41(TERMINAL_ID);
	param.setElement48(element48);
	param.setElement49(IDR_CURRENCY_TRAN);
	param.setElement51(IDR_CURRENCY_TRAN);
	param.setElement60(ELEMENT_60);
	param.setElement63(PRIVATE_DATA);
	param.setElement100(SETTLEMENT_ID);
	param.setElement102(request.getAccountNo());
	param.setElement103(element103);
	param.setElement120(
		SETTLEMENT_ARANET_ID + PRODUCT_CODE_EMONEY_LINK_AJA + MERCHANT_TYPE_MOBILE + FORWARD_ID_BUKOPIN);
	param.setElement122(description);
	param.setElement123(elemt123);

	// complete request
	purchaseRequest.setIdentity(identity);
	purchaseRequest.setParameter(param);

	return purchaseRequest;
    }

    /**
     * Generator random alpha numeric
     * 
     * @param length of alpha numeric
     * @return string, new combination of alpha numeric
     */
    public static String generateAlphaNumeric(int length) {
	String charNumber = ALPHA_NUMERIC;
	StringBuilder salt = new StringBuilder();
	Random rnd = new Random();
	while (salt.length() < length) { // length of the random string.
	    int index = (int) (rnd.nextFloat() * charNumber.length());
	    salt.append(charNumber.charAt(index));
	}
	String aplhaNumber = salt.toString();
	return aplhaNumber;
    }

    /**
     * Generate Identity
     * 
     * @return identity Object
     */
    public static Identity generateIdentity() {
	Identity identity = new Identity();
	identity.setReqTime(new Date().toString());
	identity.setPlatform("Android");
	identity.setUserAgent("");
	identity.setToken("");
	identity.setSecretCode("");

	return identity;
    }

    /**
     * add commas '.' inside string
     * 
     * @param charToCommas string added commas
     * @param digits       add commas to index string
     * @return string, that has been added commas
     */
    public static String addDigitString(String charToCommas, int digits) {
	String result = "";
	int length = charToCommas.length();
	int putCommas = length - digits;
	for (int i = 1; i <= length; i++) {
	    result = result + String.valueOf(charToCommas.charAt(i - 1));
	    if (i == putCommas) {
		result = result + ".";
	    }
	}

	return result;
    }

    /**
     * generate Inquiry OVO Response
     * 
     * @param req     inquiry emoney
     * @param inquiry Response Tibco
     * @return
     */
    public static inquiryEMoneyResponse generateInquiryOVOResponse(inquiryEMoneyRequest req,
	    EMoneyInquiryResponse inquiryResponseTibco) {
	String element11 = inquiryResponseTibco.getRespayment().getResult().getElement11();
	String element61 = inquiryResponseTibco.getRespayment().getResult().getElement61();
	String element62 = inquiryResponseTibco.getRespayment().getResult().getElement62();

	String custName = element61.substring(35, 65);
	String amountFee = element61.substring(78, 89);
	String screenField1 = element62.substring(0, 31);
	String screenField2 = element62.substring(31, 62);
	String screenField3 = element62.substring(62, 93);
	String screenField4 = element62.substring(93, 124);
	String screenField5 = element62.substring(124, 155);

	inquiryEMoneyResponse result = new inquiryEMoneyResponse();
	result.setCustomerName(custName.trim());
	result.setCustomerNumber(req.getCustomerNumber());
	result.setAmountFee(new BigDecimal(amountFee));
	result.setAmount(req.getAmount());
	result.setTotalAmount(result.getAmount().add(result.getAmountFee()));
	result.setElement11(element11);
	// add one character in element 61
	result.setElement61(element61.concat("1"));
	result.setScreenField1(screenField1.trim());
	result.setScreenField2(screenField2.trim());
	result.setScreenField3(screenField3.trim());
	result.setScreenField4(screenField4.trim());
	result.setScreenField5(screenField5.trim());
	result.setElement37(inquiryResponseTibco.getRespayment().getResult().getElement37());

	return result;
    }

    /**
     * Generate Purchase OVO Response
     * 
     * @param req      purchase emoney
     * @param purchase Response Tibco
     * @return
     */
    public static PurchaseEMoneyResponse generatePurchaseOVOResponse(PurchaseEMoneyRequest req,
	    EMoneyPurchaseResponse purchaseResponseTibco) {
	PurchaseEMoneyResponse resp = new PurchaseEMoneyResponse();
	String element61 = purchaseResponseTibco.getRespayment().getResult().getElement61();
	String element62 = purchaseResponseTibco.getRespayment().getResult().getElement62();

	String customerName = element61.substring(35, 65);
	String amountFee = element61.substring(78, 89);
	BigDecimal amount = req.getAmount();
	String date = element61.substring(21, 29);
	String time = element61.substring(29, 35);
	//String reference = element61.substring(89, 109);
	String reference = purchaseResponseTibco.getRespayment().getResult().getElement122().substring(144, 159);
	String receiptField1 = element62.substring(155, 186);
	String receiptField2 = element62.substring(186, 217);
	String receiptField3 = element62.substring(217, 247);
	String receiptField4 = element62.substring(247, 278);
	String receiptField5 = element62.substring(278, 309);
	String receiptField6 = element62.substring(309, 340);

	resp.setCustomerNumber(req.getCustomerNumber());
	resp.setCustomerName(customerName.trim());
	resp.setAmountFee(new BigDecimal(amountFee));
	resp.setAmount(amount);
	resp.setTotalAmount(amount.add(resp.getAmountFee()));
	resp.setDate(date);
	resp.setTime(time);
	resp.setReference(reference.trim());
	resp.setAccountNumber(req.getAccountNumber());
	resp.setType(EmoneyTypeEnum.OVO.name());
	resp.setUsername(req.getUsername());
	resp.setReceiptField1(receiptField1.trim());
	resp.setReceiptField2(receiptField2.trim());
	resp.setReceiptField3(receiptField3.trim());
	resp.setReceiptField4(receiptField4.trim());
	resp.setReceiptField5(receiptField5.trim());
	resp.setReceiptField6(receiptField6.trim());
	return resp;
    }

    /**
     * Generate Purchase LinkAja Response
     * 
     * @param req      purchase LinkAja
     * @param purchase Response Tibco
     * @return
     */
    public static PurchaseEMoneyResponse purchaseLinkAjaResponse(PurchaseLinkAjaRequest request,
	    LinkAjaPurchaseResponse responseTibco) {
	PurchaseEMoneyResponse resp = new PurchaseEMoneyResponse();
	String element48 = responseTibco.getRespayment().getResult().getElement48();

	String[] splitEl48 = element48.split("\\|", -1);

	String telpNum = request.getCustNo();
	String custName = splitEl48[1];
	//String reference = splitEl48[5];
	String reference = responseTibco.getRespayment().getResult().getElement122().substring(144, 159);
	BigDecimal amount = request.getAmount();

	String[] el48 = element48.split("\\|", -1);
	String adm = el48[7];
	String adminFee = adm.substring(0, 7).concat("00");
	BigDecimal amountFee = new BigDecimal(adminFee);

	BigDecimal totalAmount = amount.add(amountFee);

	Date today = new Date();
	SimpleDateFormat formatter = new SimpleDateFormat("HHmmss");
	SimpleDateFormat formatter1 = new SimpleDateFormat("yyyyMMdd");
	resp.setCustomerNumber(telpNum.trim());
	resp.setCustomerName(custName.trim());
	resp.setAmountFee(amountFee);
	resp.setAmount(amount);
	resp.setTotalAmount(totalAmount);
	resp.setTime(formatter.format(today));
	resp.setReference(reference.trim());
	resp.setAccountNumber(request.getAccountNo().trim());
	resp.setType(EmoneyTypeEnum.LINKAJA.name());
	resp.setUsername(request.getUsername());
	resp.setDate(formatter1.format(today));

	resp.setTotalAmount(totalAmount);

	return resp;
    }

    /* Overrides: */
}
