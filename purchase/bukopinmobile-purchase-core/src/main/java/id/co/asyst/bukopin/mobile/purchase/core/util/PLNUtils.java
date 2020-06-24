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
import id.co.asyst.bukopin.mobile.common.model.payload.Identity;
import id.co.asyst.bukopin.mobile.purchase.model.payload.PurchasePrepaidRequestPLN;
import id.co.asyst.bukopin.mobile.purchase.model.payload.PurchasePrepaidResponsePLN;
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
 * @author Gibran Haq
 * @version $Revision$, Dec 7, 2019
 * @since 1.0.Alpha1
 */
public class PLNUtils {
    /* Constants: */
    private static final String NUMBER = "0123456789";
    private static SecureRandom random = new SecureRandom();

    private static final String MTI_INQUIRY = "2100";
    private static final String MTI_PURCHASE = "2200";
    private static final String MTI_ADVICE = "2220";
    private static final String ACCOUNT_NUMBER_PREPAID = "99502";
    private static final String PROCCESSING_CODE_INQUIRY = "100000";
    private static final String PROCCESSING_CODE_PURCHASE = "110000";
    private static final String PROCCESSING_CODE_PURCHASE_SAVING = "111000";
    private static final String PROCCESSING_CODE_PURCHASE_GIRO = "112000";
    private static final String PROCCESSING_CODE_ADVICE = "120000";
    private static final String MERCHANT_TYPE_MOBILE = "6017";
    private static final String ACQUIRING_INSTITUTION_CODE = "4410010";
    private static final String TERMINAL_IDENTIFICATION = "6184959459694705";
    private static final String ROUTING_INFO = "000004410010030046017441";
    private static final String TERMINAL_ID = "BUKOPINMBPLN123";
    private static final String IDR_CURRENCY_TRAN = "360";
    private static final String IDR_CURRENCY_CODE = "360";
    private static final String SETTLEMENT_ID = "441001";
    private static final String SETTLEMENT_ARANET_ID = "00000441001";
    private static final String PRODUCT_CODE_PREPAID = "003004";
    private static final String FORWARD_ID_BUKOPIN = "441";
    private static final String TRDES1 = "MB : PULSA LISTRIK ";// +<nopel> paddingRight(40, space)
    private static final String TRDES2 = "Dr. "; // +<norek sumber> paddingRight(40, space)
    private static final String TRDES3 = ""; // paddingRight(60, space)
    private static final String CONVENTION_RATE = "0000000000000000000";
    private static final String FEE_CODE = "00";
    private static final String OPERATION_CODE = "05";
    private static final String WARKAT_DATE = "          ";
    private static final String WARKAT_NO = "                    ";
    private static final String DEVICE_CODE = "                    ";
    private static final String USER_ID = "               ";
    private static final String NOT_BRANCH = "000";
    private static final String NOT_LOCATION = "000";
    private static final String ALPHA_NUMERIC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    private static SimpleDateFormat purchaseResTime = new SimpleDateFormat("dd MMM yyyy - HH:mm");
    private static DateFormat element7Format = new SimpleDateFormat("MMddHHmmss");
    private static SimpleDateFormat timeLocal = new SimpleDateFormat("HHmmss");
    private static SimpleDateFormat dateLocal = new SimpleDateFormat("MMdd");
    private static SimpleDateFormat trdes2 = new SimpleDateFormat("yyMMddHHmm");
    private static int PLN_LENGTH_TRXID = 12;
    
    /* Attributes: */

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */

    /* Getters & setters for transient attributes: */

    /* Functionalities: */
    /**
     * generate Inquiry Prepaid PLN Request
     * 
     * @param id
     *            subscriber ID or meter ID
     * @return
     */
    public static PrepaidInquiryRequest generateInquiryPrepaidPLNReq(String id, String forwardInsCode) {
	PrepaidInquiryRequest req = new PrepaidInquiryRequest();
	// generate STAN
	String STAN = generateSTAN(6);
	// default value
	String subsId = "000000000000";
	String meterId = "00000000000";
	String flag = "";

	Date date = new Date();

	PrepaidIdentityRequest identity = new PrepaidIdentityRequest();
	identity.setReqdatetime(new Date());
	// get transaction ID
	String txId = BkpmUtil.generateTrxId(PLN_LENGTH_TRXID);
	identity.setClienttxnid(txId);

	PrepaidCredentialsRequest credentials = new PrepaidCredentialsRequest();
	credentials.setClientid("MBUKOPIN");

	identity.setCredentials(credentials);

	// classification meterID or subsID
	if (id.length() == 11) {
	    meterId = id;
	    flag = "0";
	}
	if (id.length() == 12) {
	    subsId = id;
	    flag = "1";
	}

	PrepaidDataRequest param = new PrepaidDataRequest();
	param.setMti(MTI_INQUIRY);
	param.setElement2(ACCOUNT_NUMBER_PREPAID);
	param.setElement3(PROCCESSING_CODE_INQUIRY);
	param.setElement7(element7Format.format(date));
	param.setElement11(STAN);
	param.setElement12(timeLocal.format(date));
	param.setElement13(dateLocal.format(date));
	param.setElement18(MERCHANT_TYPE_MOBILE);
	param.setElement32(ACQUIRING_INSTITUTION_CODE);
	param.setElement33(forwardInsCode);
	param.setElement41(TERMINAL_IDENTIFICATION);
	param.setElement48("0000000" + meterId + subsId + flag);
	param.setElement120(ROUTING_INFO);

	req.setIdentity(identity);
	req.setParameter(param);

	return req;
    }

    /**
     * generate Purchase Prepaid PLN Request
     * 
     * @param request
     *            information to set purchase
     * @param accType 
     * 		Account Type, SAVING or GIRO
     * 
     * @return request for purchase
     */
    public static PrepaidPurchaseRequest generatePurchasePrepaidPLNReq(PurchasePrepaidRequestPLN request, 
	    String forwardInsCode, String accType) {
	PrepaidPurchaseRequest purchaseReq = new PrepaidPurchaseRequest();
	Date date = new Date();

	PrepaidIdentityRequest identity = new PrepaidIdentityRequest();
	identity.setReqdatetime(date);
	// get transaction ID
	String txId = BkpmUtil.generateTrxId(PLN_LENGTH_TRXID);
	identity.setClienttxnid(txId);

	PrepaidCredentialsRequest credentials = new PrepaidCredentialsRequest();
	credentials.setClientid("MBUKOPIN");
	identity.setCredentials(credentials);

	// TO DO : get saving type for element 4

	// set request for element 4
	String adminCharge = request.getElement48().substring(138, 142);
	BigDecimal nominalDec = new BigDecimal(request.getNominal());
	BigDecimal adminChargeDec = new BigDecimal(adminCharge);
	String total = String.valueOf(nominalDec.add(adminChargeDec).doubleValue());
	int sizeElement4 = 10;
	int sizeAmount = request.getNominal().length();
	String paddingZeroElm4 = "";
	for (int i = 1; i <= sizeElement4 - sizeAmount; i++) {
	    paddingZeroElm4 = paddingZeroElm4 + "0";
	}
	paddingZeroElm4 = paddingZeroElm4 + total.replace(".0", "") + "00";

	// set request element 7
	String transmissionDateAndTime = element7Format.format(new Date());

	// set request element 37
	String retrievalRefNumber = generateAlphaNumeric(12);

	// set request element 48, add '0' for new generate pln token
	String additionalData = request.getElement48() + 0;
	
	// set description, element 122
	String flag = request.getElement48().substring(30, 31);
	String trDes1="";
	if("0".equals(flag)) {
	    trDes1 = TRDES1.concat(request.getElement48().substring(7, 18));
	}
	if("1".equals(flag)) {
	    trDes1 = TRDES1.concat(request.getElement48().substring(18, 30));
	}
	// padding 40
	trDes1 = StringUtils.rightPad(trDes1, 40);
	String trDes2 = StringUtils.rightPad(TRDES2.concat(request.getAccountNo()), 40);
	// trdesc3
	String trDes3 = StringUtils.rightPad(TRDES3, 60);
	String description = trDes1.concat(trDes2).concat(trDes3);
	
	// set element 3
	String elm3 = "";
	if (accType.equalsIgnoreCase(AccountTypeEnum.SAVING.name())) {
	    elm3 = PROCCESSING_CODE_PURCHASE_SAVING;
	} else if (accType.equalsIgnoreCase(AccountTypeEnum.GIRO.name())) {
	    elm3 = PROCCESSING_CODE_PURCHASE_GIRO;
	}

	PurchaseDataRequest param = new PurchaseDataRequest();
	param.setMti(MTI_PURCHASE);
	param.setElement2(ACCOUNT_NUMBER_PREPAID);
	param.setElement3(elm3);
	param.setElement4(paddingZeroElm4);
	param.setElement7(transmissionDateAndTime);
	param.setElement11(request.getElement11());
	param.setElement12(timeLocal.format(date));
	param.setElement13(dateLocal.format(date));
	param.setElement18(MERCHANT_TYPE_MOBILE);
	param.setElement32(ACQUIRING_INSTITUTION_CODE);
	param.setElement33(forwardInsCode);
	param.setElement37(retrievalRefNumber);
	param.setElement41(TERMINAL_ID);
	param.setElement48(additionalData);
	param.setElement49(IDR_CURRENCY_TRAN);
	param.setElement51(IDR_CURRENCY_CODE);
	param.setElement62(request.getElement62());
	param.setElement100(SETTLEMENT_ID);
	param.setElement102(request.getAccountNo());
	param.setElement120(SETTLEMENT_ARANET_ID + PRODUCT_CODE_PREPAID + MERCHANT_TYPE_MOBILE + FORWARD_ID_BUKOPIN);
	param.setElement122(description);
	param.setElement123(IDR_CURRENCY_CODE + CONVENTION_RATE + FEE_CODE + PRODUCT_CODE_PREPAID + OPERATION_CODE
		+ WARKAT_DATE + WARKAT_NO + DEVICE_CODE + USER_ID + NOT_BRANCH + NOT_LOCATION);

	purchaseReq.setIdentity(identity);
	purchaseReq.setParameter(param);

	return purchaseReq;
    }

    /**
     * Generator new password
     * 
     * @param length
     *            of password
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

    /**
     * generate Purchase Prepaid PLN Response
     * 
     * @param element48
     *            response purchase from TIBCO element 48
     * @param req
     *            data request for response
     *            
     * @param footNote
     *            response purchase from TIBCO element 63
     * @param element122
     *            response purchase from TIBCO element 122 to get Reference Number.
     * 
     * @return mapped response purchase
     */
    public static PurchasePrepaidResponsePLN generateResponsePurchase(String element48, PurchasePrepaidRequestPLN req, 
	    String footNote, String element122) {
	PurchasePrepaidResponsePLN purchasePrepaidResPLN = new PurchasePrepaidResponsePLN();

	String meterSerialNumber = element48.substring(7, 18);
	String subscriberID = element48.substring(18, 30);
	// reference number change to element 122 posisi 145-159
//	String referenceBKP = element48.substring(63, 95);
	String referenceBKP = element122.substring(144, 159);
	String subscriberName = element48.substring(103, 128);
	String segmentation = element48.substring(128, 132);
	String powerConsuming = element48.substring(132, 141);
	String adminCharge = element48.substring(143, 151);
	String minorKWH = element48.substring(210, 211);
	String purchaseKWHUnit = element48.substring(211, 221);
	String stroomToken = element48.substring(221, 241);
	String minorSD = element48.substring(153, 154);
	String stampDuty = element48.substring(154, 164);
	String minorVAT = element48.substring(164, 165);
	String valueAddedTax = element48.substring(165, 175);
	String minorPLT = element48.substring(175, 176);
	String publicLightTax = element48.substring(176, 186);
	String minorCPI = element48.substring(186, 187);
	String cusPayInstallment = element48.substring(187, 197);
	String dateTime = purchaseResTime.format(new Date());

	purchasePrepaidResPLN.setDateTime(dateTime);
	purchasePrepaidResPLN.setReference(referenceBKP);
	purchasePrepaidResPLN.setAccountNo(req.getAccountNo());
	purchasePrepaidResPLN.setMeterSerialNumber(meterSerialNumber);
	purchasePrepaidResPLN.setSubscriberID(subscriberID);
	purchasePrepaidResPLN.setSubscriberName(subscriberName.trim());
	purchasePrepaidResPLN.setPowerConsuming(segmentation.trim() + "/" + new BigDecimal(powerConsuming));
	purchasePrepaidResPLN.setNominal(new BigDecimal(req.getNominal()));
	purchasePrepaidResPLN.setAdminCharge(new BigDecimal(adminCharge));
	purchasePrepaidResPLN.setTotal(purchasePrepaidResPLN.getNominal().add(purchasePrepaidResPLN.getAdminCharge()));
	purchasePrepaidResPLN.setPurchasedKWH(addDigitString(new BigDecimal(purchaseKWHUnit).toString(), Integer.parseInt(minorKWH)));
	purchasePrepaidResPLN.setStroomToken(stroomToken);
	purchasePrepaidResPLN.setStampDuty(addDigitString(new BigDecimal(stampDuty).toString(), Integer.parseInt(minorSD)));
	purchasePrepaidResPLN.setValueAddedTax(addDigitString(new BigDecimal(valueAddedTax).toString(), Integer.parseInt(minorVAT)));
	purchasePrepaidResPLN.setPublicLightTax(addDigitString(new BigDecimal(publicLightTax).toString(), Integer.parseInt(minorPLT)));
	purchasePrepaidResPLN.setCustomerPayableInstallment(addDigitString(new BigDecimal(cusPayInstallment).toString(), Integer.parseInt(minorCPI)));
	purchasePrepaidResPLN.setFlag(req.getFlag());
	purchasePrepaidResPLN.setFootNote(footNote);
	purchasePrepaidResPLN.setUsername(req.getUsername());

	return purchasePrepaidResPLN;
    }
    
    /**
     * generate Advice Prepaid PLN Request
     * 
     * @param request
     *            information to set purchase
     * 
     * @return mapped resquest Advice
     */
    public static PrepaidAdviceRequest generateAdvicePrepaidPLNReq(PurchasePrepaidRequestPLN request, String forwardInsCode) {
	PrepaidAdviceRequest adviceReq = new PrepaidAdviceRequest();
	Date date = new Date();
	
	PrepaidIdentityRequest identity = new PrepaidIdentityRequest();
	identity.setReqdatetime(date);
	// get transaction ID
	String txId = BkpmUtil.generateTrxId(PLN_LENGTH_TRXID);
	identity.setClienttxnid(txId);

	PrepaidCredentialsRequest credentials = new PrepaidCredentialsRequest();
	credentials.setClientid("MBUKOPIN");
	identity.setCredentials(credentials);
	
	// set request for element 4
	String adminCharge = request.getElement48().substring(138, 142);
	BigDecimal nominalDec = new BigDecimal(request.getNominal());
	BigDecimal adminChargeDec = new BigDecimal(adminCharge);
	String total = String.valueOf(nominalDec.add(adminChargeDec).doubleValue());
	int sizeElement4 = 10;
	int sizeAmount = request.getNominal().length();
	String paddingZeroElm4 = "";
	for (int i = 1; i <= sizeElement4 - sizeAmount; i++) {
	    paddingZeroElm4 = paddingZeroElm4 + "0";
	}
	paddingZeroElm4 = paddingZeroElm4 + total.replace(".0", "") + "00";
	
	// set request element 48, add '0' for new generate pln token
	String additionalData = request.getElement48() + 0;
	
	AdviceDataRequest param = new AdviceDataRequest();
	param.setMti(MTI_ADVICE);
	param.setElement2(ACCOUNT_NUMBER_PREPAID);
	param.setElement3(PROCCESSING_CODE_ADVICE);
	param.setElement4(paddingZeroElm4);
	param.setElement11(request.getElement11());
	param.setElement12(timeLocal.format(date));
	param.setElement13(dateLocal.format(date));
	param.setElement18(MERCHANT_TYPE_MOBILE);
	param.setElement32(ACQUIRING_INSTITUTION_CODE);
	param.setElement33(forwardInsCode);
	param.setElement41(TERMINAL_ID);
	param.setElement48(additionalData);
	param.setElement49(IDR_CURRENCY_TRAN);
	param.setElement62(request.getElement62());
	param.setElement120(SETTLEMENT_ARANET_ID + PRODUCT_CODE_PREPAID + MERCHANT_TYPE_MOBILE + FORWARD_ID_BUKOPIN);
	
	adviceReq.setIdentity(identity);
	adviceReq.setParameter(param);
	
	return adviceReq;
    }

    /**
     * Generator random alpha numeric
     * 
     * @param length
     *            of alpha numeric
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
     * @param charToCommas
     *            string added commas
     * @param digits
     *            add commas to index string
     * @return string, that has been added commas
     */
    public static String addDigitString(String charToCommas, int digits) {
	String result = "";
	int length = charToCommas.length();
	int putCommas = length-digits;
	for(int i=1; i<=length; i++) {
	    result = result + String.valueOf(charToCommas.charAt(i-1));
	    if(i==putCommas) {
		result = result + ".";
	    }
	}
	
	return result;
    }

    /* Overrides: */
}
