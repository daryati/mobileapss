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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import id.co.asyst.bukopin.mobile.common.core.util.BkpmUtil;
import id.co.asyst.bukopin.mobile.common.model.payload.CommonRequest;
import id.co.asyst.bukopin.mobile.common.model.payload.Identity;
import id.co.asyst.bukopin.mobile.master.model.CategoryEnum;
import id.co.asyst.bukopin.mobile.master.model.DestinationTypeEnum;
import id.co.asyst.bukopin.mobile.master.model.TransactionTypeEnum;
import id.co.asyst.bukopin.mobile.master.model.entity.Transaction;
import id.co.asyst.bukopin.mobile.master.model.payload.DestinationCommonRequest;
import id.co.asyst.bukopin.mobile.payment.model.entity.Samolnas;
import id.co.asyst.bukopin.mobile.payment.model.payload.SamolnasInquiryResponse;
import id.co.asyst.bukopin.mobile.payment.model.payload.SamolnasPaymentRequest;
import id.co.asyst.bukopin.mobile.payment.model.payload.SamolnasPaymentResponse;
import id.co.asyst.bukopin.mobile.service.model.payload.base.IdentityBaseCredentialTibco;
import id.co.asyst.bukopin.mobile.service.model.payload.base.IdentityBaseTibco;
import id.co.asyst.bukopin.mobile.service.model.payload.samolnas.SamolnasInquiryTibcoDataRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.samolnas.SamolnasInquiryTibcoDataResult;
import id.co.asyst.bukopin.mobile.service.model.payload.samolnas.SamolnasInquiryTibcoRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.samolnas.SamolnasInquiryTibcoResponse;
import id.co.asyst.bukopin.mobile.service.model.payload.samolnas.SamolnasPaymentTibcoDataRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.samolnas.SamolnasPaymentTibcoDataResult;
import id.co.asyst.bukopin.mobile.service.model.payload.samolnas.SamolnasPaymentTibcoRequest;
import id.co.asyst.bukopin.mobile.user.model.AccountTypeEnum;

/**
 * 
 * 
 * @author Gibran Haq
 * @version $Revision$, Apr 27, 2020
 * @since 1.2.Alpha1
 */
public class SamolnasUtils {
    /* Constants: */
    private static final String NUMBER = "0123456789";
    private static SecureRandom random = new SecureRandom();

    private static final String CREDENTIALS_MBUKOPIN = "MBUKOPIN";
    private static final int LENGTH_TRXID = 12;

    private static final String MTI_INQUIRY = "2100";
    private static final String MTI_PAYMENT = "2200";
    private static final String PROCCESSING_CODE_INQUIRY = "380000";
    private static final String PROCCESSING_CODE_PAYMENT_TABUNGAN = "501000";
    private static final String PROCCESSING_CODE_PAYMENT_GIRO = "502000";
    private static final String MERCHANT_TYPE_MOBILE = "6017";
    private static final String ACQUIRING_INSTITUTION_CODE = "4410010";
    private static final String RECEIVING_INSTITUTION_CODE = "441001";
    private static final String TERMINAL_IDENTIFICATION = "5413954120960104";
    private static final String ROUTING_INFO_PART_1 = "00000441001";
    private static final String ROUTING_INFO_PART_2 = "6017441";

    private static final String TRDES1 = "MB : BYR SAMOLNAS ";
    private static final String TRDES2 = "Dr. ";
    private static final String TRDES3 = " ";
    private static final String CURRENCY_CODE_IDR = "360";
    private static final String ELEMENT123_BIT_4_24 = "000000000000000000000";
    private static final String ELEMENT123_BIT_31_96 = "05MIAPOST000000000000000000000000000000000000000000000000000000000";
    private static final String ELEMENT123_BIT_112_147 = "000000                          ";

    private static DateFormat element7Format = new SimpleDateFormat("MMddHHmmss");
    private static SimpleDateFormat timeLocal = new SimpleDateFormat("HHmmss");
    private static SimpleDateFormat dateLocal = new SimpleDateFormat("MMdd");
    private static SimpleDateFormat yearLocal = new SimpleDateFormat("yyMMdd");

    private static SimpleDateFormat dateParse = new SimpleDateFormat("yyyyMMdd");
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    /* Attributes: */

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */

    /* Getters & setters for transient attributes: */

    /* Functionalities: */
    /**
     * generateSamolnasInquiryRequest
     * 
     * @param payCode
     * @param nik
     * @param forwardInsCode
     * @param codeArra
     * @param codeCbs
     * @return
     */
    public static SamolnasInquiryTibcoRequest generateSamolnasInquiryRequest(String payCode, String nik,
	    String forwardInsCode, String codeArra, String codeCbs) {
	SamolnasInquiryTibcoRequest request = new SamolnasInquiryTibcoRequest();

	Date date = new Date();

	String STAN = generateNumber(6);
	String RRN = generateNumber(12);
	// get transaction ID
	String txId = BkpmUtil.generateTrxId(LENGTH_TRXID);

	IdentityBaseCredentialTibco credential = new IdentityBaseCredentialTibco();
	credential.setClientid(CREDENTIALS_MBUKOPIN);

	// set identity
	IdentityBaseTibco identity = new IdentityBaseTibco();
	identity.setClienttxnid(txId);
	identity.setReqdatetime(date);
	identity.setCredentials(credential);

	// set param
	SamolnasInquiryTibcoDataRequest param = new SamolnasInquiryTibcoDataRequest();
	param.setMti(MTI_INQUIRY);
	param.setElement3(PROCCESSING_CODE_INQUIRY);
	param.setElement7(element7Format.format(date));
	param.setElement11(STAN);
	param.setElement12(timeLocal.format(date));
	param.setElement13(dateLocal.format(date));
	param.setElement18(MERCHANT_TYPE_MOBILE);
	param.setElement32(ACQUIRING_INSTITUTION_CODE);
	param.setElement33(forwardInsCode);
	param.setElement37(RRN);
	param.setElement41(TERMINAL_IDENTIFICATION);
	param.setElement61(StringUtils.rightPad(payCode, 18).concat(nik));
	param.setElement63(codeArra);
	param.setElement120(ROUTING_INFO_PART_1.concat(codeCbs).concat(ROUTING_INFO_PART_2));

	request.setIdentity(identity);
	request.setParameter(param);

	return request;
    }

    /**
     * generateSamolnasInquiryResponse
     * 
     * @param dataResp
     * @return
     */
    public static SamolnasInquiryResponse generateSamolnasInquiryResponse(SamolnasInquiryTibcoDataResult dataResp) {
	SamolnasInquiryResponse resp = new SamolnasInquiryResponse();

	String element11 = dataResp.getElement11();
	String element37 = dataResp.getElement37();
	String element61 = dataResp.getElement61();

	String payCode = element61.substring(0, 18);
	String subscriberName = element61.substring(306, 331);
	String nik = element61.substring(424, 440);
	String npkb = element61.substring(18, 30);
	String machineNo = element61.substring(146, 171);
	String amount = element61.substring(478, 490);
	String adminFee = element61.substring(468, 478);

	resp.setPayCode(payCode.trim());
	resp.setSubscriberName(subscriberName.trim());
	resp.setNik(nik);
	resp.setNpkb(npkb.trim());
	resp.setMachineNo(machineNo.trim());
	resp.setAmount(new BigDecimal(amount));
	resp.setAdminFee(new BigDecimal(adminFee));
	resp.setTotalAmount(resp.getAmount().add(resp.getAdminFee()));
	resp.setElement11(element11);
	resp.setElement37(Long.parseLong(element37));
	resp.setElement61(element61);

	return resp;

    }

    /**
     * generateSamolnasPaymentRequest
     * @param dataReq
     * @param forwardInsCode
     * @param codeArra
     * @param codeCbs
     * @param accType
     * @return
     */
    public static SamolnasPaymentTibcoRequest generateSamolnasPaymentRequest(SamolnasPaymentRequest dataReq,
	    String forwardInsCode, String codeArra, String codeCbs, String accType) {
	SamolnasPaymentTibcoRequest req = new SamolnasPaymentTibcoRequest();

	String element37 = dataReq.getElement37();
	String element61 = dataReq.getElement61();
	String amount = String.valueOf(dataReq.getAmount());
	String adminFee = String.valueOf(dataReq.getAdminFee());
	String accNo = dataReq.getAccountNumber();
	String element11 = dataReq.getElement11();
	String username = dataReq.getUsername();
	String payCode = dataReq.getPayCode();

	Date date = new Date();

	// get transaction ID
	String txId = BkpmUtil.generateTrxId(LENGTH_TRXID);

	IdentityBaseCredentialTibco credential = new IdentityBaseCredentialTibco();
	credential.setClientid(CREDENTIALS_MBUKOPIN);

	// set identity
	IdentityBaseTibco identity = new IdentityBaseTibco();
	identity.setClienttxnid(txId);
	identity.setReqdatetime(date);
	identity.setCredentials(credential);

	// set param
	SamolnasPaymentTibcoDataRequest param = new SamolnasPaymentTibcoDataRequest();
	param.setMti(MTI_PAYMENT);

	String STAN = generateNumber(6);
	// element 11 in payment must be different with in inquiry
	if (element11.equals(STAN)) {
	    STAN = generateNumber(6);
	}
	
	if (accType.equalsIgnoreCase(AccountTypeEnum.SAVING.name())) {
	    param.setElement3(PROCCESSING_CODE_PAYMENT_TABUNGAN);
	} else if (accType.equalsIgnoreCase(AccountTypeEnum.GIRO.name())) {
	    param.setElement3(PROCCESSING_CODE_PAYMENT_GIRO);
	}

	param.setElement4(StringUtils.leftPad(amount + "00", 20, "0"));
	param.setElement7(element7Format.format(date));
	param.setElement11(STAN);
	param.setElement12(timeLocal.format(date));
	param.setElement13(dateLocal.format(date));
	param.setElement18(MERCHANT_TYPE_MOBILE);
	param.setElement28(StringUtils.leftPad(adminFee + "00", 20, "0"));
	param.setElement32(ACQUIRING_INSTITUTION_CODE);
	param.setElement33(forwardInsCode);
	param.setElement37(element37);
	param.setElement41(TERMINAL_IDENTIFICATION);
	param.setElement49(CURRENCY_CODE_IDR);
	param.setElement61(element61);
	param.setElement63(codeArra);
	param.setElement100(RECEIVING_INSTITUTION_CODE);
	param.setElement102(accNo);
	param.setElement123(CURRENCY_CODE_IDR.concat(ELEMENT123_BIT_4_24).concat(codeCbs).concat(ELEMENT123_BIT_31_96)
		.concat(StringUtils.rightPad(username, 15)).concat(ELEMENT123_BIT_112_147));
	param.setElement120(ROUTING_INFO_PART_1.concat(codeCbs).concat(ROUTING_INFO_PART_2));

	String desc1 = StringUtils.rightPad(TRDES1.concat(payCode), 40);
	String desc2 = StringUtils.rightPad(TRDES2.concat(accNo), 40);
	String desc3 = StringUtils.leftPad(TRDES3, 60);
	String description = desc1.concat(desc2).concat(desc3);
	param.setElement122(description);
	//String element122_bit41_80 = TRDES2.concat(yearLocal.format(date)).concat(param.getElement12().substring(0, 4));
	//param.setElement122(TRDES1.concat(payCode).concat(element122_bit41_80).concat(TRDES3));

	req.setIdentity(identity);
	req.setParameter(param);

	return req;

    }

    /**
     * generateSamolnasPaymentResponse
     * @param dataResp
     * @return
     * @throws ParseException
     */
    public static SamolnasPaymentResponse generateSamolnasPaymentResponse(SamolnasPaymentTibcoDataResult dataResp)
	    throws ParseException {
	SamolnasPaymentResponse resp = new SamolnasPaymentResponse();

	String time = dataResp.getElement12();
	String element61 = dataResp.getElement61();
	String accountNumber = dataResp.getElement102();
	String element122 = dataResp.getElement122();

	Date date = new Date();
	String dt = dateParse.format(date);
	
	//String date = element122.substring(92, 100);
	String referenceNumber = element122.substring(144, 159);
	String payCode = element61.substring(0, 18);
	String subscriberName = element61.substring(306, 331);
	String nik = element61.substring(424, 440);
	String npkb = element61.substring(18, 30);
	String machineNo = element61.substring(146, 171);
	String tbpkb = element61.substring(371, 379);
	String newTbpkb = element61.substring(379, 387);
	String amount = element61.substring(466, 478);
	String adminFee = element61.substring(456, 466);
	String merk = element61.substring(72, 92);
	String type = element61.substring(92, 112);
	String model = element61.substring(32, 72);
	String progresif = element61.substring(303, 306);
	String year = element61.substring(407, 411);

	Date tbpkbParser = dateParse.parse(tbpkb);
	Date newTbpkbParser = dateParse.parse(newTbpkb);

	// set validity
	Calendar cal = Calendar.getInstance();
	cal.add(Calendar.DAY_OF_MONTH, 30);

	resp.setAccountNumber(accountNumber);
	resp.setAdminFee(new BigDecimal(adminFee));
	resp.setAmount(new BigDecimal(amount));
	resp.setDate(dt);
	resp.setMachineNo(machineNo.trim());
	resp.setMerk(merk.trim());
	resp.setModel(model.trim());
	resp.setNewTbpkb(dateFormat.format(newTbpkbParser));
	resp.setNik(nik);
	resp.setNpkb(npkb.trim());
	resp.setPayCode(payCode.trim());
	resp.setProgresif(progresif);
	resp.setReferenceNumber(referenceNumber);
	resp.setSubscriberName(subscriberName.trim());
	resp.setTbpkb(dateFormat.format(tbpkbParser));
	resp.setTime(time);
	resp.setTotalAmount(resp.getAmount().add(resp.getAdminFee()));
	resp.setType(type.trim());
	resp.setValidityPeriod(dateFormat.format(cal.getTime()));
	resp.setYear(year);

	return resp;

    }

    /**
     * generateDestinationReq
     * @param identity
     * @param reqPay
     * @param resp
     * @return
     */
    public static CommonRequest<DestinationCommonRequest> generateDestinationReq(Identity identity,
	    SamolnasPaymentRequest reqPay, SamolnasPaymentResponse resp) {
	CommonRequest<DestinationCommonRequest> req = new CommonRequest<>();
	DestinationCommonRequest destReq = new DestinationCommonRequest();
	destReq.setCategoryId(CategoryEnum.SAMOLNAS.getId());
	destReq.setDestinationType(DestinationTypeEnum.POSTSAMOLNAS.name());

	destReq.setUsername(reqPay.getUsername());
	destReq.setSubscriberNumber(resp.getPayCode());
	destReq.setSubscriberName(resp.getSubscriberName());

	destReq.setTransactionType(TransactionTypeEnum.SAMOLNAS.name());
	destReq.setReference(resp.getReferenceNumber());
	destReq.setAccountNumber(resp.getAccountNumber());
	destReq.setTotalAmount(resp.getTotalAmount());

	req.setIdentity(identity);
	req.setData(destReq);

	return req;
    }

    /**
     * generateDataSamolnas
     * @param resp
     * @param transaction
     * @param destReq
     * @return
     */
    public static Samolnas generateDataSamolnas(SamolnasPaymentResponse resp, Transaction transaction,
	    DestinationCommonRequest destReq) {
	Samolnas samolnas = new Samolnas();

	samolnas.setAdminFee(resp.getAdminFee());
	samolnas.setAmount(resp.getAmount());
	samolnas.setMachineNo(resp.getMachineNo());
	samolnas.setMerk(resp.getMerk());
	samolnas.setModel(resp.getModel());
	samolnas.setNewtbpkb(resp.getNewTbpkb());
	samolnas.setNik(resp.getNik());
	samolnas.setNpkb(resp.getNpkb());
	samolnas.setProgresif(resp.getProgresif());
	samolnas.setTbpkb(resp.getTbpkb());
	samolnas.setTransaction(transaction);
	samolnas.setType(resp.getType());
	samolnas.setValidity("20202020");
	samolnas.setYear(resp.getYear());

	return samolnas;
    }

    /**
     * generateNumber
     * 
     * @param length
     * @return
     */
    public static String generateNumber(int length) {
	if (length < 1)
	    throw new IllegalArgumentException();

	StringBuilder sb = new StringBuilder(length);
	for (int i = 0; i < length; i++) {

	    // choose character randomly from PASSWORD_ALLOW
	    int rndCharAt = random.nextInt(NUMBER.length());
	    char rndChar = NUMBER.charAt(rndCharAt);

	    sb.append(rndChar);

	}
	return sb.toString();
    }

    /* Overrides: */}
