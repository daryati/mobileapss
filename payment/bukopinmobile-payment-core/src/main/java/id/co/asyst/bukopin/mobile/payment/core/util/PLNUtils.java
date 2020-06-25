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
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;

import id.co.asyst.bukopin.mobile.common.core.util.BkpmUtil;
import id.co.asyst.bukopin.mobile.common.model.payload.CommonResponse;
import id.co.asyst.bukopin.mobile.payment.model.payload.PLNPostpaidPaymentRequest;
import id.co.asyst.bukopin.mobile.payment.model.payload.PLNPostpaidPaymentResponse;
import id.co.asyst.bukopin.mobile.service.model.payload.pln.GetVerifyPINRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.pln.postpaid.PostpaidDataRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.pln.postpaid.PostpaidDataResponse;
import id.co.asyst.bukopin.mobile.service.model.payload.pln.postpaid.PostpaidRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.pln.postpaid.PostpaidResponse;
import id.co.asyst.bukopin.mobile.service.model.payload.pln.prepaid.PrepaidCredentialsRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.pln.prepaid.PrepaidDataRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.pln.prepaid.PrepaidDataResponse;
import id.co.asyst.bukopin.mobile.service.model.payload.pln.prepaid.PrepaidIdentityRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.pln.prepaid.PrepaidInquiryRequest;
import id.co.asyst.bukopin.mobile.user.model.AccountTypeEnum;
import id.co.asyst.foundation.service.connector.Services;
import retrofit2.Response;

/**
 * 
 * 
 * @author Gibran Haq
 * @version $Revision$, Jan 15, 2020
 * @since 1.0.Alpha1-SNAPSHOT
 */
public class PLNUtils {
    /* Constants: */
    private static final String NUMBER = "0123456789";
    private static SecureRandom random = new SecureRandom();

    private static final String MTI_INQUIRY = "2100";
    private static final String MTI_PAYMENT = "2100";
    private static final String MTI_PURCHASE = "2200";
    private static final String MTI_ADVICE = "2220";
    private static final String ACCOUNT_NUMBER_POSTPAID = "99501";
    private static final String PROCCESSING_CODE_INQUIRY_POSTPAID = "200000";
    private static final String PROCCESSING_CODE_PAYMENT = "210000";
    private static final String PROCCESSING_CODE_PAYMENT_SAVING = "211000";
    private static final String PROCCESSING_CODE_PAYMENT_GIRO = "212000";
    private static final String PROCCESSING_CODE_ADVICE = "120000";
    private static final String MERCHANT_TYPE_MOBILE = "6017";
    private static final String ACQUIRING_INSTITUTION_CODE = "4410010";
    private static final String TERMINAL_IDENTIFICATION = "MBLBKP1234567890";
    private static final String ROUTING_INFO_POSTPAID = "000004410010030016017441";
    private static final String TERMINAL_ID = "BUKOPINMBPLN123";
    private static final String IDR_CURRENCY_TRAN = "360";
    private static final String SETTLEMENT_ID = "441001";
    private static final String SETTLEMENT_ARANET_ID = "00000441001";
    private static final String PRODUCT_CODE_POSTPAID = "003001";
    private static final String FORWARD_ID_BUKOPIN = "441";
    private static final String TRDES1 = "MB : BAYAR LISTRIK ";// +<nopel> paddingRight(40, space)
    private static final String TRDES2 = "Dr. "; // +<norek sumber> paddingRight(40, space)
    private static final String TRDES3 = ""; // paddingRight(60, space)
    private static final String ALPHA_NUMERIC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    private static SimpleDateFormat paymentResTime = new SimpleDateFormat("dd MMM yyyy - HH:mm");
    private static DateFormat element7Format = new SimpleDateFormat("MMddHHmmss");
    private static SimpleDateFormat timeLocal = new SimpleDateFormat("HHmmss");
    private static SimpleDateFormat dateLocal = new SimpleDateFormat("MMdd");
    private static int PLN_LENGTH_TRXID = 12;
    private static String TIBCO_CLIENT_ID = "MBUKOPIN";
    private static String ADDITIONAL_FEE_DIGIT="00";
    
    /* element 123 */
    private static final String IDR_CURRENCY_CODE = "360";
    private static final String CONVENTION_RATE = "0000000000000000000";
    private static final String FEE_CODE = "00";
    private static final String OPERATION_CODE = "05";
    private static final String WARKAT_DATE = "0000000000";
    private static final String WARKAT_NO = "00000000000000000000";
    private static final String INDICATOR_APPROVAL = "0000";
    private static final String DEVICE_CODE = "00000000000000000000";
    private static final String USER_ID = "MIAPOST000";
    private static final String USER_LOGIN = "";
    private static final String NOT_BRANCH = "000";
    private static final String NOT_LOCATION = "000";
    private static final String FT24 = "                    ";
    private static final String DEPT_CODE = "   ";
    private static final String FLAG_VA = "  ";
    private static final String FLAG_CBS = "     ";
    private static final int USER_LOGIN_LENGTH = 15;
    private static final String PAY_ELEMENT_183 = "0000000000000000000"; // 19 chars
    
    private static final String KEY_PREV_METER = "previous";
    private static final String KEY_CURRENT_METER = "current";

    /* Attributes: */

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */

    /* Getters & setters for transient attributes: */

    /* Functionalities: */
    public static PrepaidInquiryRequest generateInquiryPostpaidPLNReq(String id, String forwardInsCode) {
	PrepaidInquiryRequest req = new PrepaidInquiryRequest();
	// generate STAN
	String STAN = generateSTAN(6);

	Date date = new Date();

	PrepaidIdentityRequest identity = new PrepaidIdentityRequest();
	identity.setReqdatetime(new Date());
	// get transaction ID
	String txId = BkpmUtil.generateTrxId(PLN_LENGTH_TRXID);
	identity.setClienttxnid(txId);

	PrepaidCredentialsRequest credentials = new PrepaidCredentialsRequest();
	credentials.setClientid(TIBCO_CLIENT_ID);

	identity.setCredentials(credentials);

	PrepaidDataRequest param = new PrepaidDataRequest();
	param.setMti(MTI_INQUIRY);

	param.setElement2(ACCOUNT_NUMBER_POSTPAID);
	param.setElement3(PROCCESSING_CODE_INQUIRY_POSTPAID);
	param.setElement7(element7Format.format(date));
	param.setElement11(STAN);
	param.setElement12(timeLocal.format(date));
	param.setElement13(dateLocal.format(date));
	param.setElement18(MERCHANT_TYPE_MOBILE);
	param.setElement32(ACQUIRING_INSTITUTION_CODE);
	param.setElement33(forwardInsCode);
	param.setElement41(TERMINAL_IDENTIFICATION);
	param.setElement48("0000000" + id);
	param.setElement120(ROUTING_INFO_POSTPAID);

	req.setIdentity(identity);
	req.setParameter(param);

	return req;
    }

    /**
     * Generate Payment Postpaid Request
     * 
     * @param forwardInsCode Institution Code
     * @param accType 
     * 		Account Type, SAVING or GIRO
     * @return Purchase Request
     */
    public static PostpaidRequest generatePaymentPostpaidRequest(PLNPostpaidPaymentRequest request, 
	    String forwardInsCode, String accType) {
	PostpaidRequest paymentRequest = new PostpaidRequest();
	Date date = new Date();
	
	// get transaction ID
	String txId = BkpmUtil.generateTrxId(PLN_LENGTH_TRXID);
	PrepaidCredentialsRequest credentials = new PrepaidCredentialsRequest();
	credentials.setClientid(TIBCO_CLIENT_ID);
	
	PrepaidIdentityRequest identity = new PrepaidIdentityRequest();
	identity.setClienttxnid(txId);
	identity.setReqdatetime(date);
	identity.setCredentials(credentials);
	paymentRequest.setIdentity(identity);
	
	// trdesc1+nopel
	String trdesc1 = StringUtils.rightPad(TRDES1+request.getSubscriberNumber(), 40);
	// trdesc2+sumberdana
	String trdesc2 = StringUtils.rightPad(TRDES2+request.getAccountNo(), 40);
	// trdesc3
	String trdesc3 = StringUtils.rightPad(TRDES3, 60);
	// set description
	String description = trdesc1+trdesc2+trdesc3;
	
	// set element 3
	String elm3 = "";
	if (accType.equalsIgnoreCase(AccountTypeEnum.SAVING.name())) {
	    elm3 = PROCCESSING_CODE_PAYMENT_SAVING;
	} else if (accType.equalsIgnoreCase(AccountTypeEnum.GIRO.name())) {
	    elm3 = PROCCESSING_CODE_PAYMENT_GIRO;
	}
	
	// set request element 37
	String retrievalRefNumber = generateAlphaNumeric(12);
	PostpaidDataRequest param = new PostpaidDataRequest();
	param.setMti(MTI_INQUIRY);
	param.setElement2(ACCOUNT_NUMBER_POSTPAID);
	param.setElement3(elm3);
	BigDecimal amount = request.getAmount().add(request.getPenaltyFee());
	param.setElement4(String.valueOf(amount)+ADDITIONAL_FEE_DIGIT);
	param.setElement7(element7Format.format(date));
	param.setElement11(request.getElement11());
	param.setElement12(timeLocal.format(date));
	param.setElement13(dateLocal.format(date));
	param.setElement18(MERCHANT_TYPE_MOBILE);
	param.setElement28(String.valueOf(request.getAdminFee())+ADDITIONAL_FEE_DIGIT);
	param.setElement32(ACQUIRING_INSTITUTION_CODE);
	param.setElement33(forwardInsCode);
	param.setElement37(retrievalRefNumber);
	param.setElement41(TERMINAL_IDENTIFICATION);
	param.setElement48(request.getElement48());
	param.setElement49(IDR_CURRENCY_TRAN);
	param.setElement51(IDR_CURRENCY_CODE);
	param.setElement61(request.getElement61());
	param.setElement100(SETTLEMENT_ID);
	param.setElement102(request.getAccountNo());
	param.setElement103(PAY_ELEMENT_183);
	param.setElement120(SETTLEMENT_ARANET_ID+PRODUCT_CODE_POSTPAID+MERCHANT_TYPE_MOBILE+FORWARD_ID_BUKOPIN);
	param.setElement122(description);
	String username = StringUtils.rightPad(request.getUsername().toUpperCase(), USER_LOGIN_LENGTH);
	param.setElement123(
		IDR_CURRENCY_CODE 
		+ CONVENTION_RATE 
		+ FEE_CODE 
		+ PRODUCT_CODE_POSTPAID 
		+ OPERATION_CODE
		+ USER_ID
		+ WARKAT_DATE
		+ WARKAT_NO 
		+ INDICATOR_APPROVAL
		+ DEVICE_CODE
		+ username
		+ NOT_BRANCH
		+ NOT_LOCATION
		+ FT24
		+ DEPT_CODE
		+ FLAG_VA
		+ FLAG_CBS);
	paymentRequest.setParameter(param);
	
	return paymentRequest;
    }
    
    /**
     * Generate Payment Postpaid Response
     * 
     * @param tibcoResponse Postpaid Response from Tibco
     * @return Payment Postpaid Response
     */
    public static PLNPostpaidPaymentResponse generatePaymentPostpaidResponse(
	    PostpaidDataResponse tibcoResponse, PLNPostpaidPaymentRequest request) {
	PLNPostpaidPaymentResponse response = new PLNPostpaidPaymentResponse();
	
	response = extractElement48(tibcoResponse.getElement48());
	response.setInfo(tibcoResponse.getElement63());
	response.setDateTime(paymentResTime.format(new Date()));
	response.setAccountNo(request.getAccountNo());
	response.setAmount(request.getAmount());
	response.setAdminFee(request.getAdminFee());
	response.setPenaltyFee(request.getPenaltyFee());
	response.setTotalAmount(request.getTotalAmount());
	
	// reference number element 122 posisi 145-159
	response.setReferenceNumber(tibcoResponse.getElement122().substring(144, 159));
	
	return response;
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
    
    /**
     * Extract Information from Element/Bit 48 tibco.
     * 
     * @param bit48 The element/ bit 48 from tibco.
     * @return Postpaid response.
     */
    private static PLNPostpaidPaymentResponse extractElement48(String bit48) {
	PLNPostpaidPaymentResponse response = new PLNPostpaidPaymentResponse();
	
	// subscriber number
	response.setSubscriberNumber(bit48.substring(7, 19).trim());
	// subs name
	response.setSubscriberName(bit48.substring(55, 80).trim());
	// paymentStatus
	response.setPaymentStatus(Integer.valueOf(bit48.substring(20, 21)));
	// bill status (n bills)
	response.setBillStatus(Integer.valueOf(bit48.substring(19, 20)));
	// bill period
	response.setBillPeriod(constructBillPeriod(Integer.valueOf(response.getBillStatus()), bit48));
	// reference number . Update refnum move to element 122 posisi 145-159.
	//response.setReferenceNumber(bit48.substring(23, 55));
	// tarif (subs segment)
	response.setTarif(StringUtils.replace(bit48.substring(100, 104), " ", ""));
	// daya
	response.setDaya(String.valueOf(new BigDecimal(bit48.substring(104, 113))));
	
	// remain bill = outstanding bill - bill status
	int outStandingBill = Integer.valueOf(bit48.substring(21, 23));
	int remainBill = outStandingBill - response.getBillStatus();
	response.setRemainBill(remainBill);
	
	Map<String, String> meterMap = constructCurPrevMeter(Integer.valueOf(response.getBillStatus()), bit48);
	// prev meter
	response.setPrevMeter(meterMap.get(KEY_PREV_METER));
	// next meter
	response.setCurrMeter(meterMap.get(KEY_CURRENT_METER));
	
//	// amount
//	
//	// admin fee
//	response.setAmount(new BigDecimal(bit48.substring(113, 122)));
//	// penalty fee
//	response.setPenaltyFee(new BigDecimal(bit48.substring(177, 189)));
//	// total amount
	
	return response;
    }
    
    /**
     * Construct Bill Period
     * 
     * @param n Total bill periods.
     * @return Map of bill period. Key: increment. e.g: 1, Value: MMMYY. e.g: JAN20.
     */
    public static Map<String, String> constructBillPeriod(int n, String bit48) {
	Map<String, String> result = new LinkedHashMap<>();
	for(int i=1; i<=n; i++) {
	    String bill = "";
	    if(i==1) {
		bill = bit48.substring(122,128);
	    } else if(i==2) {
		bill = bit48.substring(237,243);
	    } else if(i==3) {
		bill = bit48.substring(352,358);
	    } else {
		bill = bit48.substring(467,473);
	    }
	    int monthInt = Integer.valueOf(bill.substring(4, 6));
	    String month = new DateFormatSymbols().getMonths()[monthInt-1];
	    month = month.substring(0, 3);
	    String year = bill.substring(2, 4);
	    String newPeriod = month.toUpperCase()+year;
	    result.put(String.valueOf(i), newPeriod);
	}
	
	return result;
    }
    
    /**
     * Construct Current and Previous Meter
     * 
     * @param n N of bills
     * @param bit48 Element 48 from tibco
     * @return Current Meter in key "current", Previous Meter in key "previous".
     */
    public static Map<String, String> constructCurPrevMeter(int n, String bit48) {
	Map<String, String> result = new LinkedHashMap<>();
	String prevMeter = "";
	String curMeter = "";
	for(int i=1; i<=n; i++) {
	    if(i==1) {
		prevMeter = bit48.substring(189, 197);
		curMeter = bit48.substring(197, 205);
	    } else if(i==2) {
		curMeter = bit48.substring(311,319);
	    } else if(i==3) {
		curMeter = bit48.substring(426,434);
	    } else {
		curMeter = bit48.substring(542, 550);
	    }
	}
	result.put(KEY_PREV_METER, prevMeter);
	result.put(KEY_CURRENT_METER, curMeter);
	return result;
    }

    /* Overrides: */
}
