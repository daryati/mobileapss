/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.telco.core.util;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import id.co.asyst.bukopin.mobile.common.core.util.BkpmUtil;
import id.co.asyst.bukopin.mobile.common.model.BkpmConstants;
import id.co.asyst.bukopin.mobile.common.model.payload.Identity;
import id.co.asyst.bukopin.mobile.service.model.payload.telco.prepaid.PrepaidTelcoCredentialsRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.telco.prepaid.PrepaidTelcoIdentityRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.telco.prepaid.PrepaidTelcoInquiryPaketDataRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.telco.prepaid.PrepaidTelcoInquiryPaketRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.telco.prepaid.PrepaidTelcoListPaketDataRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.telco.prepaid.PrepaidTelcoListPaketRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.telco.prepaid.PrepaidTelcoPurchaseDataRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.telco.prepaid.PrepaidTelcoPurchaseRequest;
import id.co.asyst.bukopin.mobile.telco.model.PrepaidTelcoEnum;
import id.co.asyst.bukopin.mobile.telco.model.payload.InstitutionMapper;
import id.co.asyst.bukopin.mobile.telco.model.payload.PaymentPrepaidTelcoRequest;
import id.co.asyst.bukopin.mobile.user.model.AccountTypeEnum;

/**
 * 
 * 
 * @author Kartika Dwi H
 * @version $Revision$, Feb 14, 2020
 * @since 1.1.Alpha1
 */
public class PrepaidTelcoUtils {
    /* Constants: */
    private static final String NUMBER = "0123456789";
    private static SecureRandom random = new SecureRandom();
    private static final String FORWARD_ID_BUKOPIN = "441";
    private static final String MTI_PREPAID_INQUIRY = "2100";
    private static final String MTI_PREPAID_PURCHASE = "2200";
   // private static final String PROCCESSING_CODE_PURCHASE_TELKOMSEL = "500000";
    private static final String PROCCESSING_CODE_INQ_LIST = "381000";
    private static final String PROCCESSING_CODE_PURCHASE_SAVING = "501000";
    private static final String PROCCESSING_CODE_PURCHASE_GIRO = "502000";
    
    private static final String MERCHANT_TYPE_MOBILE = "6017";
    private static final String ACQUIRING_INSTITUTION_CODE = "4410010";
    private static final String TERMINAL_ID = "5413954120960104";
    private static final String SETTLEMENT_ID = "441001";
    private static final String SETTLEMENT_ARANET_ID = "00000441001";
    private static final String IDR_CURRENCY_TRAN = "360";
    private static final String IDR_CURRENCY_CODE = "360";
    
    //-- description
    private static final String TRDES1_PULSA_TELKOMSEL = "MB : PULSA TSEL ";
    private static final String TRDES1_MOBILEDATA_TELKOMSEL = "MB : PAKET DATA TSEL ";
    private static final String TRDES1_PULSA_INDOSAT = "MB : PULSA INDOSAT ";
    private static final String TRDES1_MOBILEDATA_INDOSAT = "MB : PAKET DATA ISAT ";
    private static final String TRDES1_PULSA_XL = "MB : PULSA XL ";
    private static final String TRDES1_MOBILEDATA_XL = "MB : PAKET DATA XL ";
    private static final String TRDES1_PULSA_TRI = "MB : PULSA TRI ";
    private static final String TRDES1_PULSA_SMARTFREN = "MB : PULSA SMARTFREN ";    
    private static final String TRDES2 = "Dr. ";
    private static final String TRDES3_PULSA_TELKOMSEL = "";
    private static final String TRDES3_MOBILEDATA_TELKOMSEL = "";
    private static final String TRDES3_PULSA_INDOSAT = "";
    private static final String TRDES3_MOBILEDATA_INDOSAT = "";
    private static final String TRDES3_PULSA_XL = "";
    private static final String TRDES3_MOBILEDATA_XL = "";
    private static final String TRDES3_PULSA_TRI = "";
    private static final String TRDES3_PULSA_SMARTFREN = "";
    
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
    private static int PREPAID_TELCO_LENGTH_TRXID = 12;
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
     * generate Purchase prepaid telco Request
     * 
     * @param request information to set purchase
     * 
     * @return request for purchase
     */
 
    public static PrepaidTelcoPurchaseRequest generatePurchasePrepaidTelcoReq(PaymentPrepaidTelcoRequest request, 
    		String forwardInsCode, String providerGroup, String accType) {
    	PrepaidTelcoPurchaseRequest purchaseReq = new PrepaidTelcoPurchaseRequest();
	Date date = new Date();

	//-- setidentity
	PrepaidTelcoIdentityRequest identity = new PrepaidTelcoIdentityRequest();
	identity.setReqdatetime(date);
	//-- get transaction ID
	String txId = BkpmUtil.generateTrxId(PREPAID_TELCO_LENGTH_TRXID);
	identity.setClienttxnid(txId);

	//-- set credential
	PrepaidTelcoCredentialsRequest credentials = new PrepaidTelcoCredentialsRequest();
	credentials.setClientid("MBUKOPIN");
	identity.setCredentials(credentials);

	//-- set element 3 
	String elm3="";
	if(accType.equalsIgnoreCase(AccountTypeEnum.SAVING.name())) {
		elm3 = PROCCESSING_CODE_PURCHASE_SAVING;
	} else if(accType.equalsIgnoreCase(AccountTypeEnum.GIRO.name())){
		elm3 = PROCCESSING_CODE_PURCHASE_GIRO;
	}
	
	//-- set request element 28 (admin fee)
	String adminFee = request.getAdminFee().toString();
	Integer admFee = Integer.valueOf(adminFee);
	adminFee = admFee + "00";
	String paddingAdminFee = new String(new char[20 - adminFee.length()]).replace('\0', '0');
	paddingAdminFee = paddingAdminFee.concat(adminFee);
	
	//-- set request for element 4
	String amount = request.getAmount().toBigInteger().toString() + "00";
	String padding = new String(new char[20 - amount.length()]).replace('\0', '0');
	String paddingZeroElm4 = padding + amount;

	//-- set request element 7
	String transmissionDateAndTime = element7Format.format(new Date());
	
	
	//-- set elmt 11,37,61, description, element 122 	
	String randomElm11 ="";
	String retrievalRefNumber="";
	String elmt61="";
	
	String des1="";
	String des2 ="";
	String des3="";
	if(PrepaidTelcoEnum.PREPAID.getName().equalsIgnoreCase(request.getInstitutionType())) {
		System.out.println("---------------PULSA-----------------");
		//-- set request element 11 (random 6 digit)
		randomElm11 = generateSTAN(6);
		
		//-- set request element 37
		retrievalRefNumber = generateSTAN(12);

		//-- set element 61
		String element61 = request.getPhoneNumber();
		String paddingPhoneNumber = new String(new char[13 - element61.length()]).replace('\0', ' ');
		elmt61 = element61.concat(paddingPhoneNumber);
		
		
		if("TELKOMSEL".equalsIgnoreCase(providerGroup)) {
			des1 = StringUtils.rightPad(TRDES1_PULSA_TELKOMSEL.concat(request.getPhoneNumber()), 40);
			des2 = StringUtils.rightPad(TRDES2.concat(request.getAccountNumber()), 40);
			des3 = StringUtils.leftPad(TRDES3_PULSA_TELKOMSEL, 60);
    	} else if("INDOSAT".equalsIgnoreCase(providerGroup)) {
    		des1 = StringUtils.rightPad(TRDES1_PULSA_INDOSAT.concat(request.getPhoneNumber()), 40);
    		des2 = StringUtils.rightPad(TRDES2.concat(request.getAccountNumber()), 40);
    		des3 = StringUtils.leftPad(TRDES3_PULSA_INDOSAT, 60);
    	} else if("XL".equalsIgnoreCase(providerGroup)) {
    		des1 = StringUtils.rightPad(TRDES1_PULSA_XL.concat(request.getPhoneNumber()), 40);
    		des2 = StringUtils.rightPad(TRDES2.concat(request.getAccountNumber()), 40);
    		des3 = StringUtils.leftPad(TRDES3_PULSA_XL, 60);
    	} else if("TRI".equalsIgnoreCase(providerGroup)) {
    		des1 = StringUtils.rightPad(TRDES1_PULSA_TRI.concat(request.getPhoneNumber()), 40);
    		des2 = StringUtils.rightPad(TRDES2.concat(request.getAccountNumber()), 40);
    		des3 = StringUtils.leftPad(TRDES3_PULSA_TRI, 60);
    	} else if("SMARTFREN".equalsIgnoreCase(providerGroup)) {
    		des1 = StringUtils.rightPad(TRDES1_PULSA_SMARTFREN.concat(request.getPhoneNumber()), 40);
    		des2 = StringUtils.rightPad(TRDES2.concat(request.getAccountNumber()), 40);
    		des3 = StringUtils.leftPad(TRDES3_PULSA_SMARTFREN, 60);
    	}
		
	} else if(PrepaidTelcoEnum.PAKET_DATA.getName().equalsIgnoreCase(request.getInstitutionType())) {
		System.out.println("----------------PAKET DATA--------------------");
		if("TELKOMSEL".equalsIgnoreCase(providerGroup)) {
			//-- set request element 11 (random 6 digit)
			randomElm11 = generateSTAN(6);
			
			//-- set request element 37
			retrievalRefNumber = generateSTAN(12);
			
			//-- set element 61
			String element61= request.getPhoneNumber();
			String paddingPhoneNumber = new String(new char[13 - element61.length()]).replace('\0', ' ');
			elmt61 = element61.concat(paddingPhoneNumber);
			
			des1 = StringUtils.rightPad(TRDES1_MOBILEDATA_TELKOMSEL.concat(request.getPhoneNumber()), 40);
			des2 = StringUtils.rightPad(TRDES2.concat(request.getAccountNumber()), 40);
			des3 = StringUtils.leftPad(TRDES3_MOBILEDATA_TELKOMSEL, 60);
    	} else if("INDOSAT".equalsIgnoreCase(providerGroup)) {
    		//-- set request element 11 (random 6 digit)
    		randomElm11 = generateSTAN(6);
    		
    		//-- set request element 37
    		retrievalRefNumber = generateSTAN(12);
    		
    		//-- set element 61
    		amount = amount.substring(0, amount.length()-2);
    		adminFee = adminFee.substring(0, adminFee.length()-2);
    		
    		String element61= request.getPhoneNumber();
    		String paddingPhoneNumber = new String(new char[13 - element61.length()]).replace('\0', ' ');    		
    		String paddingPackagecode = new String(new char[4 - request.getPackageCode().length()]).replace('\0', ' ');    		
    		String paddingTitle= new String(new char[30 - request.getTitle().length()]).replace('\0', ' ');    		
    		String leftpaddingamount= new String(new char[12 - amount.length()]).replace('\0', '0');    		
    		String leftpadAdminFee = new String(new char[8 - adminFee.length()]).replace('\0', '0');
    		String desc61 = "PPOB BANK BUKOPIN     JAKARTA      JKTID";
    		
    		elmt61 = element61.concat(paddingPhoneNumber).concat(request.getPackageCode()).concat(paddingPackagecode).
    				concat(request.getTitle()).concat(paddingTitle).concat(leftpaddingamount).concat(amount)
    				.concat(leftpadAdminFee).concat(adminFee).concat(desc61);
    		
    		des1 = StringUtils.rightPad(TRDES1_MOBILEDATA_INDOSAT.concat(request.getPhoneNumber()), 40);
    		des2 = StringUtils.rightPad(TRDES2.concat(request.getAccountNumber()), 40);
    		des3 = StringUtils.leftPad(TRDES3_MOBILEDATA_INDOSAT, 60);
    	} else if("XL".equalsIgnoreCase(providerGroup)) {
    		
    		//-- set request element 11
    		//randomElm11 = request.getElement11();
    		randomElm11=generateSTAN(6);
    		
    		//-- set request element 37
    		retrievalRefNumber= request.getElement37();
    		
    		//-- set element 61
    		elmt61 = request.getElement61();
    		
    		
    		des1 = StringUtils.rightPad(TRDES1_MOBILEDATA_XL.concat(request.getPhoneNumber()), 40);
    		des2 = StringUtils.rightPad(TRDES2.concat(request.getAccountNumber()), 40);
    		des3 = StringUtils.leftPad(TRDES3_MOBILEDATA_XL, 60);
    	}
		
	}	
	String description = des1.concat(des2).concat(des3);
	

	//-- set description, element 123
	String spasi118_147 = new String(new char[148 - 118]).replace('\0', ' ');
	String username = StringUtils.rightPad(request.getUsername(), 15);

	String elemt123 = IDR_CURRENCY_CODE + CONVENTION_RATE + FEE_CODE + request.getCodeCbs() + OPERATION_CODE + MIA_POST
		+ BIT_43_52 + BIT_53_72 + BIT_73_76 + BIT_77_96 + username + NOT_BRANCH + NOT_LOCATION + spasi118_147;

	PrepaidTelcoPurchaseDataRequest param = new PrepaidTelcoPurchaseDataRequest();
	param.setMti(MTI_PREPAID_PURCHASE);
	param.setElement3(elm3);
	param.setElement4(paddingZeroElm4);
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
	param.setElement49(IDR_CURRENCY_TRAN);	
	param.setElement61(elmt61);
	param.setElement63(request.getProductCode());
	param.setElement100(SETTLEMENT_ID);
	param.setElement102(request.getAccountNumber());
	param.setElement103(request.getAccountNumber());
	param.setElement120(SETTLEMENT_ARANET_ID + request.getCodeCbs() + MERCHANT_TYPE_MOBILE + FORWARD_ID_BUKOPIN);
	param.setElement122(description);
	param.setElement123(elemt123);

	purchaseReq.setIdentity(identity);
	purchaseReq.setParameter(param);

	return purchaseReq;
    }
    
    
    
    /**
     * generate inquiry list paket data
     * 
     * @param request information to set inquiry list
     * 
     * @return request for inquiry list
     */
 
    public static PrepaidTelcoListPaketRequest generateInquiryListPaketDataReq(String phoneNumber, InstitutionMapper institution,
	    String forwardInsCode, String providerGroup) {
    	PrepaidTelcoListPaketRequest listPaketReq = new PrepaidTelcoListPaketRequest();
	Date date = new Date();

	//-- setidentity
	PrepaidTelcoIdentityRequest identity = new PrepaidTelcoIdentityRequest();
	identity.setReqdatetime(date);
	//-- get transaction ID
	String txId = BkpmUtil.generateTrxId(PREPAID_TELCO_LENGTH_TRXID);
	identity.setClienttxnid(txId);

	//-- set credential
	PrepaidTelcoCredentialsRequest credentials = new PrepaidTelcoCredentialsRequest();
	credentials.setClientid("MBUKOPIN");
	identity.setCredentials(credentials);

	//-- set element 3 
	String elm3=PROCCESSING_CODE_INQ_LIST;
	

	//-- set request element 7
	String transmissionDateAndTime = element7Format.format(new Date());
	
	//-- set request element 11 (random 6 digit)
	String randomElm11 = generateSTAN(6);
	
	//-- set element 61
	String element61 = phoneNumber;
	String paddingPhoneNumber = new String(new char[13 - element61.length()]).replace('\0', ' ');
	paddingPhoneNumber = element61.concat(paddingPhoneNumber);


	PrepaidTelcoListPaketDataRequest param = new PrepaidTelcoListPaketDataRequest();
	param.setMti(MTI_PREPAID_INQUIRY);
	param.setElement3(elm3);
	param.setElement7(transmissionDateAndTime);
	param.setElement11(randomElm11);
	param.setElement12(timeLocal.format(date));
	param.setElement13(dateLocal.format(date));
	param.setElement18(MERCHANT_TYPE_MOBILE);
	param.setElement32(ACQUIRING_INSTITUTION_CODE);
	param.setElement33(forwardInsCode);
	param.setElement41(TERMINAL_ID);
	param.setElement49(IDR_CURRENCY_TRAN);	
	param.setElement61(paddingPhoneNumber);
	param.setElement63(institution.getCodeArra());
	param.setElement120(SETTLEMENT_ARANET_ID + institution.getCodeCbs() + MERCHANT_TYPE_MOBILE + FORWARD_ID_BUKOPIN);

	listPaketReq.setIdentity(identity);
	listPaketReq.setParameter(param);

	return listPaketReq;
    }
    
    /**
     * generate inquiry paket data
     * 
     * @param request information to set inquiry paket data
     * 
     * @return request for inquiry paket data
     */
 
    public static PrepaidTelcoInquiryPaketRequest generateInquiryPaketDataReq(String phoneNumber, String productCode,
    		String codeCbs, String packageCode, String forwardInsCode) {
    	PrepaidTelcoInquiryPaketRequest inqPaketReq = new PrepaidTelcoInquiryPaketRequest();
	Date date = new Date();

	//-- setidentity
	PrepaidTelcoIdentityRequest identity = new PrepaidTelcoIdentityRequest();
	identity.setReqdatetime(date);
	//-- get transaction ID
	String txId = BkpmUtil.generateTrxId(PREPAID_TELCO_LENGTH_TRXID);
	identity.setClienttxnid(txId);

	//-- set credential
	PrepaidTelcoCredentialsRequest credentials = new PrepaidTelcoCredentialsRequest();
	credentials.setClientid("MBUKOPIN");
	identity.setCredentials(credentials);

	//-- set element 3 
	String elm3=PROCCESSING_CODE_INQ_LIST;
	

	//-- set request element 7
	String transmissionDateAndTime = element7Format.format(new Date());
	
	//-- set request element 11 (random 6 digit)
	String randomElm11 = generateSTAN(6);
	
	//-- set request element 37 (random 12 digit)
	String randomElm37 = generateSTAN(12);
	
	//-- set element 61
	String element61 = phoneNumber;
	String paddingPhoneNumber = new String(new char[13 - element61.length()]).replace('\0', ' ');
	String paddingPackageCode = new String(new char[6 -packageCode.length()]).replace('\0', ' ');
	String padding61 = element61.concat(paddingPhoneNumber).concat(packageCode).concat(paddingPackageCode);


	PrepaidTelcoInquiryPaketDataRequest param = new PrepaidTelcoInquiryPaketDataRequest();
	param.setMti(MTI_PREPAID_INQUIRY);
	param.setElement3(elm3);
	param.setElement7(transmissionDateAndTime);
	param.setElement11(randomElm11);
	param.setElement12(timeLocal.format(date));
	param.setElement13(dateLocal.format(date));
	param.setElement18(MERCHANT_TYPE_MOBILE);
	param.setElement32(ACQUIRING_INSTITUTION_CODE);
	param.setElement33(forwardInsCode);
	param.setElement37(randomElm37);
	param.setElement41(TERMINAL_ID);
	param.setElement49(IDR_CURRENCY_TRAN);	
	param.setElement61(padding61);
	param.setElement63(productCode);
	param.setElement120(SETTLEMENT_ARANET_ID + codeCbs + MERCHANT_TYPE_MOBILE + FORWARD_ID_BUKOPIN);

	inqPaketReq.setIdentity(identity);
	inqPaketReq.setParameter(param);

	return inqPaketReq;
    }
    

    /**
     * Generator Stan
     * 
     * @param length of stan
     * @return string, stan
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
