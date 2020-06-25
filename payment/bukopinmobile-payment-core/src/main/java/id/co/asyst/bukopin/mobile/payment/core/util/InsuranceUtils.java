/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.payment.core.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;

import id.co.asyst.bukopin.mobile.common.core.util.BkpmUtil;
import id.co.asyst.bukopin.mobile.common.model.BkpmConstants;
import id.co.asyst.bukopin.mobile.common.model.payload.Identity;
import id.co.asyst.bukopin.mobile.master.model.entity.Institution;
import id.co.asyst.bukopin.mobile.payment.model.payload.InstitutionMapper;
import id.co.asyst.bukopin.mobile.payment.model.payload.PaymentInsuranceRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.emoney.EMoneyCredentialsRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.emoney.EMoneyDataRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.emoney.EMoneyIdentityRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.emoney.EMoneyInquiryRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.insurance.InsuranceCredentialsRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.insurance.InsuranceDataRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.insurance.InsuranceIdentityRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.insurance.InsuranceInquiryRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.insurance.InsurancePurchaseDataRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.insurance.InsurancePurchaseRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.telco.prepaid.PrepaidTelcoCredentialsRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.telco.prepaid.PrepaidTelcoIdentityRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.telco.prepaid.PrepaidTelcoPurchaseDataRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.telco.prepaid.PrepaidTelcoPurchaseRequest;
import id.co.asyst.bukopin.mobile.user.model.AccountTypeEnum;

/**
 * 
 * 
 * @author Kartika Dwi H
 * @version $Revision$, Feb 14, 2020
 * @since 1.1.Alpha1
 */
public class InsuranceUtils {
    /* Constants: */
    private static final String NUMBER = "0123456789";
    private static SecureRandom random = new SecureRandom();
    private static final String FORWARD_ID_BUKOPIN = "441";
    private static final String MTI_PREPAID_PURCHASE = "2200";
    private static final String MTI_INQUIRY = "2100";
    //private static final String PROCCESSING_CODE_PURCHASE_TELKOMSEL = "500000";
    private static final String PROCCESSING_CODE_PURCHASE_SAVING = "501000";
    private static final String PROCCESSING_CODE_PURCHASE_GIRO = "502000";
    
    
    private static final String PROCCESSING_CODE_INQUIRY = "380000";
    private static final String MERCHANT_TYPE_MOBILE = "6017";
    private static final String ACQUIRING_INSTITUTION_CODE = "4410010";
    private static final String TERMINAL_ID = "5413954120960104";
    private static final String SETTLEMENT_ID = "441001";
    private static final String SETTLEMENT_ARANET_ID = "00000441001";
    private static final String IDR_CURRENCY_TRAN = "360";
    private static final String IDR_CURRENCY_CODE = "360";
    private static final String TERMINAL_IDENTIFICATION = "5413954120960104";
    //private static final String LANGUAGE_CODE = "0";
    
    //-- description
    private static final String TRDES1_BPJSKES = "MB : BYR BPJS KESEHATAN ";
    //private static final String TRDES2 = "00000000000000000000000IB0101 ";
    private static final String TRDES2_BPJSKES= "Dr. ";
    private static final String TRDES3_BPJSKES = " ";
    
    
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
    private static int INSURANCE_LENGTH_TRXID = 12;
    private static SimpleDateFormat trdes2 = new SimpleDateFormat("yyMMddHHmm");
    private static DateFormat element7Format = new SimpleDateFormat("MMddHHmmss");
    private static SimpleDateFormat timeLocal = new SimpleDateFormat("HHmmss");
    private static SimpleDateFormat dateLocal = new SimpleDateFormat("MMdd");
    private static final String ALPHA_NUMERIC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";


    /* Attributes: */

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */

    /* Getters & setters for transient attributes: */

    /* Functionalities: */

    /**
     * generate Inquiry Insurance
     * 
     * @param id subscriber ID, codeIns, month
     * @return
     */
    public static InsuranceInquiryRequest generateInquiryInsuranceReq(String codeIns, Integer month, String subscribernumber,
    		String forwardInsCode, InstitutionMapper institution, String language) {
    	InsuranceInquiryRequest req = new InsuranceInquiryRequest();
    	String adminFee= String.valueOf(Integer.valueOf(institution.getAdminFee().intValue()));
    	// generate STAN
    	String STAN = generateSTAN(6);

    	Date date = new Date();

    	InsuranceIdentityRequest identity = new InsuranceIdentityRequest();
    	identity.setReqdatetime(new Date());
    	// get transaction ID
    	String txId = BkpmUtil.generateTrxId(INSURANCE_LENGTH_TRXID);
    	identity.setClienttxnid(txId);

    	InsuranceCredentialsRequest credentials = new InsuranceCredentialsRequest();
    	credentials.setClientid("MBUKOPIN");

    	identity.setCredentials(credentials);
    	
    	//-- set element 48
    	String LANGUAGE_CODE = "";
    	if ("ID".equalsIgnoreCase(language) || "IN".equalsIgnoreCase(language)) {
    		LANGUAGE_CODE = "0";
    	} else if ("EN".equalsIgnoreCase(language)) {
    		LANGUAGE_CODE = "1";
    	}
    			
    	String paddingSubsNumb = subscribernumber.concat(new String(new char[24 - subscribernumber.length()]).replace('\0', ' '));
    	String paddingMonth = new String(new char[2 - String.valueOf(month).length()]).replace('\0', '0').concat(String.valueOf(month));
    	String paddingAdminFee = new String(new char[12 - adminFee.length()]).replace('\0', '0').concat(adminFee);
    	String element48="1".concat("7283").concat(paddingSubsNumb).concat(LANGUAGE_CODE).concat(paddingMonth).concat(paddingAdminFee);
    	
    	//-- set element 63    	
    	String element63 = institution.getCodeArra();
    	
    	//-- set element120
    	String element120 = SETTLEMENT_ARANET_ID + institution.getCodeCbs() + MERCHANT_TYPE_MOBILE + FORWARD_ID_BUKOPIN;
    	
    	InsuranceDataRequest param = new InsuranceDataRequest();
    	param.setMti(MTI_INQUIRY);
    	param.setElement3(PROCCESSING_CODE_INQUIRY);
    	param.setElement7(element7Format.format(date));
    	param.setElement11(STAN);
    	param.setElement12(timeLocal.format(date));
    	param.setElement13(dateLocal.format(date));
    	param.setElement18(MERCHANT_TYPE_MOBILE);
    	param.setElement32(ACQUIRING_INSTITUTION_CODE);
    	param.setElement33(forwardInsCode);
    	param.setElement37(generateSTAN(12));
    	param.setElement41(TERMINAL_IDENTIFICATION);
    	param.setElement48(element48);
    	param.setElement63(element63);
    	param.setElement120(element120);

    	req.setIdentity(identity);
    	req.setParameter(param);

    	return req;
        }


    
 
    public static InsurancePurchaseRequest generatePurchaseInsuranceReq(PaymentInsuranceRequest request, 
    		 String forwardInsCode, String accType) {
    	//System.out.print("REQUEST....... "+request.toString());
    	
    	InsurancePurchaseRequest purchaseReq = new InsurancePurchaseRequest();
	Date date = new Date();

	//-- setidentity
	InsuranceIdentityRequest identity = new InsuranceIdentityRequest();
	identity.setReqdatetime(date);
	//-- get transaction ID
	String txId = BkpmUtil.generateTrxId(INSURANCE_LENGTH_TRXID);
	identity.setClienttxnid(txId);

	//-- set credential
	InsuranceCredentialsRequest credentials = new InsuranceCredentialsRequest();
	credentials.setClientid("MBUKOPIN");
	identity.setCredentials(credentials);

	//-- set request for element 3
	String elm3="";
	if(accType.equalsIgnoreCase(AccountTypeEnum.SAVING.name())) {
		elm3 = PROCCESSING_CODE_PURCHASE_SAVING;
	} else if(accType.equalsIgnoreCase(AccountTypeEnum.GIRO.name())){
		elm3 =PROCCESSING_CODE_PURCHASE_GIRO;
	}
	
	//-- set request for element 4
	BigInteger elm4= request.getAmount().toBigInteger().add(request.getPrepaidInsurance().toBigInteger());
	String amount = elm4 + "00";
	String padding = new String(new char[20 - amount.length()]).replace('\0', '0');
	String paddingZeroElm4 = padding + amount;

	//-- set request element 7
	String transmissionDateAndTime = element7Format.format(new Date());
	
	//-- set request element 11 (random 6 digit)
	String randomElm11 = generateSTAN(6);
	
	//-- set request element 28 (admin fee)
	String adminFee = request.getAdminFee().toString();
	Integer admFee = Integer.valueOf(adminFee);
	adminFee = admFee + "00";
	String paddingAdminFee = new String(new char[20 - adminFee.length()]).replace('\0', '0');
	paddingAdminFee = paddingAdminFee.concat(adminFee);
	// System.out.println("..... admin FEEEE "+paddingAdminFee);

	//-- set request element 37
	String retrievalRefNumber = request.getElement37();

	

	//-- set description, element 122 
	String des1= "";
	String des2="";
	String des3="";
	
	if("BPJSKES".equalsIgnoreCase(request.getCodeIns())) {
		des1 = StringUtils.rightPad(TRDES1_BPJSKES.concat(request.getSubscriberNumber()), 40);
		des2 = StringUtils.rightPad(TRDES2_BPJSKES.concat(request.getAccountNumber()), 40);
		des3 = StringUtils.leftPad(TRDES3_BPJSKES, 60);
	} else if("INHEALTH".equalsIgnoreCase(request.getCodeIns())) {
		//des1 = StringUtils.rightPad(TRDES1_PULSA_INDOSAT.concat(request.getPhoneNumber()), 40);
		//des2 = StringUtils.rightPad(TRDES2.concat(trdes2.format(date)), 40);
		//des3 = StringUtils.leftPad(TRDES3_PULSA_INDOSAT, 60);
	} 
		
	 
	String description = des1.concat(des2).concat(des3);
	

	//-- set description, element 123
	String spasi118_147 = new String(new char[148 - 118]).replace('\0', ' ');
	String username = StringUtils.rightPad(request.getUsername(), 15);
	String el12_17 = request.getElement120().substring(11,17);

	String elemt123 = IDR_CURRENCY_CODE + CONVENTION_RATE + FEE_CODE + el12_17 + OPERATION_CODE + MIA_POST
		+ BIT_43_52 + BIT_53_72 + BIT_73_76 + BIT_77_96 + username + NOT_BRANCH + NOT_LOCATION + spasi118_147;

	InsurancePurchaseDataRequest param = new InsurancePurchaseDataRequest();
	param.setMti(MTI_PREPAID_PURCHASE);
	param.setElement3(elm3);
	param.setElement4(request.getElement4());
	param.setElement7(transmissionDateAndTime);
	param.setElement11(randomElm11);
	param.setElement12(timeLocal.format(date));
	param.setElement13(dateLocal.format(date));
	param.setElement18(MERCHANT_TYPE_MOBILE);
	param.setElement28(paddingAdminFee);
	param.setElement32(ACQUIRING_INSTITUTION_CODE);
	param.setElement33(forwardInsCode);
	param.setElement37(retrievalRefNumber);
	param.setElement41(TERMINAL_ID);
	param.setElement48(request.getElement48());
	param.setElement49(IDR_CURRENCY_TRAN);	
	param.setElement63(request.getElement63());
	param.setElement100(SETTLEMENT_ID);
	param.setElement102(request.getAccountNumber());
	param.setElement120(request.getElement120());
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

    

    /* Overrides: */
}
