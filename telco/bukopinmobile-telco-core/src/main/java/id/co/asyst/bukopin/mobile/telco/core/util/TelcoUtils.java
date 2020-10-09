/*
 * $Id$
 * 
 * Copyright (c) 2020 Aero Systems Indonesia, PT.
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

import org.apache.commons.lang3.StringUtils;

import id.co.asyst.bukopin.mobile.common.core.util.BkpmUtil;
import id.co.asyst.bukopin.mobile.common.model.SystemCutOffEnum;
import id.co.asyst.bukopin.mobile.common.model.payload.CommonRequest;
import id.co.asyst.bukopin.mobile.common.model.payload.Identity;
import id.co.asyst.bukopin.mobile.master.model.CategoryEnum;
import id.co.asyst.bukopin.mobile.master.model.DestinationTypeEnum;
import id.co.asyst.bukopin.mobile.master.model.TransactionTypeEnum;
import id.co.asyst.bukopin.mobile.master.model.entity.Transaction;
import id.co.asyst.bukopin.mobile.master.model.payload.DestinationCommonRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.base.IdentityBaseCredentialTibco;
import id.co.asyst.bukopin.mobile.service.model.payload.base.IdentityBaseTibco;
import id.co.asyst.bukopin.mobile.service.model.payload.telco.postpaid.TelcoPostpaidInquiryDataTibcoRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.telco.postpaid.TelcoPostpaidInquiryTibcoDataResult;
import id.co.asyst.bukopin.mobile.service.model.payload.telco.postpaid.TelcoPostpaidInquiryTibcoRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.telco.postpaid.TelcoPostpaidPaymentDataTibcoRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.telco.postpaid.TelcoPostpaidPaymentTibcoDataResult;
import id.co.asyst.bukopin.mobile.service.model.payload.telco.postpaid.TelcoPostpaidPaymentTibcoRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.telco.telkomPSTN.speedy.TelkomPSTNSpeedyInquiryRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.telco.telkomPSTN.speedy.TelkomPSTNSpeedyInquiryTibcoDataReq;
import id.co.asyst.bukopin.mobile.service.model.payload.telco.telkomPSTN.speedy.TelkomPSTNSpeedyInquiryTibcoDataResp;
import id.co.asyst.bukopin.mobile.service.model.payload.telco.telkomPSTN.speedy.TelkomPSTNSpeedyPurchaseTibcoDataReq;
import id.co.asyst.bukopin.mobile.service.model.payload.telco.telkomPSTN.speedy.TelkomPSTNSpeedyPurchaseTibcoDataResp;
import id.co.asyst.bukopin.mobile.service.model.payload.telco.telkomPSTN.speedy.TelkomPSTNSpeedyPurchaseTibcoRequest;
import id.co.asyst.bukopin.mobile.telco.model.entity.TelcoPostpaid;
import id.co.asyst.bukopin.mobile.telco.model.payload.TelcoPostpaidInquiryResponse;
import id.co.asyst.bukopin.mobile.telco.model.payload.TelcoPostpaidPaymentRequest;
import id.co.asyst.bukopin.mobile.telco.model.payload.TelcoPostpaidPaymentResponse;
import id.co.asyst.bukopin.mobile.telco.model.payload.TelkomPSTNSpeedyInquiryReq;
import id.co.asyst.bukopin.mobile.telco.model.payload.TelkomPSTNSpeedyInquiryRes;
import id.co.asyst.bukopin.mobile.user.model.AccountTypeEnum;

/**
 * 
 * 
 * @author Gibran Haq
 * @version $Revision$, Feb 13, 2020
 * @since 1.1.Alpha1-SNAPSHOT
 */
public class TelcoUtils {
    /* Constants: */
    private static final String NUMBER = "0123456789";
    private static SecureRandom random = new SecureRandom();

    private static final String MTI_INQUIRY = "2100";
    private static final String MTI_PAYMENT = "2200";
    private static final String PROCCESSING_CODE_INQUIRY = "380000";
    private static final String PROCCESSING_CODE_PAYMENT = "500000";
    private static final String PROCCESSING_CODE_PAYMENT_TABUNGAN = "501000";
    private static final String PROCCESSING_CODE_PAYMENT_GIRO = "502000";
    private static final String MERCHANT_TYPE_MOBILE = "6017";
    private static final String ACQUIRING_INSTITUTION_CODE = "4410010";
    private static final String RECEIVING_INSTITUTION_CODE = "441001";
    private static final String TERMINAL_IDENTIFICATION = "TERMINALID123456";
    private static final String ROUTING_INFO_PART_1 = "00000441001";
    private static final String ROUTING_INFO_PART_2 = "6017441";
    
    private static final String CREDENTIALS_MBUKOPIN = "MBUKOPIN";
    private static final int TELCO_LENGTH_TRXID = 12;

    private static final String TRDES1_INDOSAT = "MB : BAYAR INDOSAT ";
    private static final String TRDES2 = "Dr. ";
    //private static final String TRDES3_INDOSAT = "INET : BAYAR INDOSAT 01";
    private static final String TRDES1_TELKOMSEL = "MB : BAYAR HALO ";
    //private static final String TRDES3_TELKOMSEL = "INET : BAYAR HALO 01";
    private static final String TRDES1_XL = "MB : BAYAR XL ";
    //private static final String TRDES3_XL = "INET : BAYAR XL 01";
    private static final String TRDES1_TRI = "MB : BAYAR TRI ";
    //private static final String TRDES3_TRI = "INET : BAYAR TRI 01";
    private static final String TRDES1_SMARTFREN = "MB : BAYAR SMARTFREN ";
    //private static final String TRDES3_SMARTFREN = "INET : BAYAR SMARTFREN 01";
    private static final String CURRENCY_CODE_IDR = "360";
    private static final String ELEMENT123_BIT_4_24 = "000000000000000000000";
    private static final String ELEMENT123_BIT_31_147 = "05                                                                               000000                    0000000000";

    private static final String IDR_CURRENCY_TRAN = "360";
    private static final String IDR_CURRENCY_CODE = "360";
    private static final String SETTLEMENT_ID = "441001";
    private static final String SETTLEMENT_ARANET_ID = "00000441001";
    private static final String FORWARD_ID_BUKOPIN = "441";
    private static final String CONVENTION_RATE = "0000000000000000000";
    private static final String FEE_CODE = "00";
    private static final String OPERATION_CODE = "05";
    private static final String MIA_POST = "MIAPOST000";
    private static final String BIT_43_52 = "0000000000";
    private static final String BIT_53_72 = "00000000000000000000";
    private static final String BIT_73_76 = "0000";
    private static final String BIT_77_96 = "00000000000000000000";
    private static final String element103 = "0000000000";
    private static final String NOT_BRANCH = "000";
    private static final String NOT_LOCATION = "000";
    private static final String TELKOMPSTN = "TELKOMPSTN";
    private static final String TRDES1_PSTN = "MB : BAYAR TELKOM ";
    private static final String TRDES1_SPEEDY = "MB : BAYAR TELKOM ";
    private static final String TRDES3_PSTN = "";
    private static final String TRDES3_SPEEDY = "";

    private static final String SPEEDY = "SPEEDY";
    private static final String ROUTING_INF = "00000441001";
    private static final String ELEMENT_20_441 = "441";

    private static SimpleDateFormat trdes2 = new SimpleDateFormat("yyMMddHHmm");
    private static DateFormat element7Format = new SimpleDateFormat("MMddHHmmss");
    private static SimpleDateFormat timeLocal = new SimpleDateFormat("HHmmss");
    private static SimpleDateFormat dateLocal = new SimpleDateFormat("MMdd");
    private static SimpleDateFormat dateYear = new SimpleDateFormat("yyyyMMdd");
    
    private static final String PGROUP_INDOSAT = "INDOSAT";
    private static final String PGROUP_TELKOMSEL = "TELKOMSEL";
    private static final String PGROUP_XL = "XL";
    private static final String PGROUP_TRI = "TRI";
    private static final String PGROUP_SMARTFREN = "SMARTFREN";
    private static final String PROVIDER_TELKOM = "TELKOM"; // pstn
    private static final String PROVIDER_SPEEDY = "SPEEDY"; // indihome

    
    private static final String HALO = "HALO";
    private static final String MATRIX = "MATRIX";
    private static final String XPLOR = "XPLOR";
    private static final String XL = "XL";
    private static final String TRI = "TRI";
    private static final String SMARTFREN = "SMARTFREN";

    /* Attributes: */

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */

    /* Getters & setters for transient attributes: */

    /* Functionalities: */
    /**
     * generateTelcoPostpaidInquiryRequest
     * 
     * @param custNo
     * @param forwardInsCode
     * @param type
     * @return
     */
    public static TelcoPostpaidInquiryTibcoRequest generateTelcoPostpaidInquiryRequest(String custNo,
	    String forwardInsCode, String codeArra, String codeCbs) {
	TelcoPostpaidInquiryTibcoRequest request = new TelcoPostpaidInquiryTibcoRequest();

	Date date = new Date();

	String STAN = generateNumber(6);
	String RRN = generateNumber(12);
	// get transaction ID
	String txId = BkpmUtil.generateTrxId(TELCO_LENGTH_TRXID);

	IdentityBaseCredentialTibco credential = new IdentityBaseCredentialTibco();
	credential.setClientid(CREDENTIALS_MBUKOPIN);

	// set identity
	IdentityBaseTibco identity = new IdentityBaseTibco();
	identity.setClienttxnid(txId);
	identity.setReqdatetime(date);
	identity.setCredentials(credential);

	// set param
	TelcoPostpaidInquiryDataTibcoRequest param = new TelcoPostpaidInquiryDataTibcoRequest();
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
	param.setElement61(StringUtils.rightPad(custNo, 13));
	param.setElement63(codeArra);
	param.setElement120(ROUTING_INFO_PART_1.concat(codeCbs).concat(ROUTING_INFO_PART_2));

	request.setIdentity(identity);
	request.setParameter(param);

	return request;
    }

    /**
     * generateTelcoPostpaidInquiryResp
     * 
     * @param dataResp
     * @return
     */
    public static TelcoPostpaidInquiryResponse generateTelcoPostpaidInquiryResp(
	    TelcoPostpaidInquiryTibcoDataResult dataResp) {
	TelcoPostpaidInquiryResponse resp = new TelcoPostpaidInquiryResponse();

	String element61 = dataResp.getElement61();
	String element11 = dataResp.getElement11();
	String element37 = dataResp.getElement37();
	String element63 = dataResp.getElement63();
	String element120 = dataResp.getElement120();

	String custNo = element61.substring(0, 13);
	String custName = element61.substring(19, 64);
	String billPeriod = element61.substring(89, 97);
	String amount = element61.substring(114, 126);
	String amountFee = element61.substring(126, 134);
	String codeCbs = element120.substring(11, 17);

	resp.setCustNo(custNo.trim());
	resp.setCustName(custName.trim());
	resp.setBillPeriode(billPeriod);
	resp.setAmountFee(new BigDecimal(amountFee));
	resp.setAmount(new BigDecimal(amount));
	resp.setTotalAmount(resp.getAmount().add(resp.getAmountFee()));
	resp.setElement11(element11);
	resp.setElement37(element37);
	resp.setElement61(element61);
	resp.setCodeArra(element63);
	resp.setCodeCbs(codeCbs);

	return resp;
    }

    /**
     * Generate Telco Postpaid Request to Tibco for Payment
     * 
     * @param dataReq
     * @param forwardInsCode
     * @param type
     * @param productCode
     * @return
     */
    public static TelcoPostpaidPaymentTibcoRequest generateTelcoPostpaidPaymentReq(TelcoPostpaidPaymentRequest dataReq,
	    String forwardInsCode, String group, String codeArra, String codeCbs, String accType) {
	TelcoPostpaidPaymentTibcoRequest req = new TelcoPostpaidPaymentTibcoRequest();

	String element37 = dataReq.getElement37();
	String element61 = dataReq.getElement61();
	String amount = element61.substring(114, 126);
	String accNo = dataReq.getAccountNo();
	String custNo = dataReq.getCustNo();
	String element11 = dataReq.getElement11();

	Date date = new Date();

	String STAN = generateNumber(6);
	// element 11 in payment must be different with in inquiry
	if (element11.equals(STAN)) {
	    STAN = generateNumber(6);
	}
	// get transaction ID
	String txId = BkpmUtil.generateTrxId(TELCO_LENGTH_TRXID);

	IdentityBaseCredentialTibco credential = new IdentityBaseCredentialTibco();
	credential.setClientid(CREDENTIALS_MBUKOPIN);

	// set identity
	IdentityBaseTibco identity = new IdentityBaseTibco();
	identity.setClienttxnid(txId);
	identity.setReqdatetime(date);
	identity.setCredentials(credential);

	// set param
	TelcoPostpaidPaymentDataTibcoRequest param = new TelcoPostpaidPaymentDataTibcoRequest();
	param.setMti(MTI_PAYMENT);
	
	if(accType.equalsIgnoreCase(AccountTypeEnum.SAVING.name())) {
	    param.setElement3(PROCCESSING_CODE_PAYMENT_TABUNGAN);
	} else if (accType.equalsIgnoreCase(AccountTypeEnum.GIRO.name())) {
	    param.setElement3(PROCCESSING_CODE_PAYMENT_GIRO);
	}
	
	param.setElement4(StringUtils.leftPad(amount.trim() + "00", 20, "0"));
	param.setElement7(element7Format.format(date));
	param.setElement11(STAN);
	param.setElement12(timeLocal.format(date));
	param.setElement13(dateLocal.format(date));
	param.setElement18(MERCHANT_TYPE_MOBILE);
	param.setElement32(ACQUIRING_INSTITUTION_CODE);
	param.setElement33(forwardInsCode);
	param.setElement37(element37);
	param.setElement41(TERMINAL_IDENTIFICATION);
	param.setElement49("360");
	param.setElement51("360");
	param.setElement61(element61);
	param.setElement63(codeArra);
	param.setElement100(RECEIVING_INSTITUTION_CODE);
	param.setElement102(StringUtils.leftPad(accNo, 19));
	param.setElement123(
		CURRENCY_CODE_IDR.concat(ELEMENT123_BIT_4_24).concat(codeCbs).concat(ELEMENT123_BIT_31_147));
	param.setElement120(ROUTING_INFO_PART_1.concat(codeCbs).concat(ROUTING_INFO_PART_2));

	/*String element122_bit41_80 = custNo.concat(TRDES2).concat(param.getElement13())
		.concat(param.getElement12().substring(0, 4));*/

	if (PGROUP_TELKOMSEL.equalsIgnoreCase(group)) {
	    param.setElement122(StringUtils.rightPad(TRDES1_TELKOMSEL.concat(custNo), 40).concat(StringUtils.rightPad(TRDES2.concat(accNo), 40)).concat(StringUtils.rightPad("", 60)));
	} else if (PGROUP_INDOSAT.equalsIgnoreCase(group)) {
	    param.setElement122(StringUtils.rightPad(TRDES1_INDOSAT.concat(custNo), 40).concat(StringUtils.rightPad(TRDES2.concat(accNo), 40)).concat(StringUtils.rightPad("", 60)));
	} else if (PGROUP_XL.equalsIgnoreCase(group)) {
	    param.setElement122(StringUtils.rightPad(TRDES1_XL.concat(custNo), 40).concat(StringUtils.rightPad(TRDES2.concat(accNo), 40)).concat(StringUtils.rightPad("", 60)));
	} else if (PGROUP_TRI.equalsIgnoreCase(group)) {
	    param.setElement122(StringUtils.rightPad(TRDES1_TRI.concat(custNo), 40).concat(StringUtils.rightPad(TRDES2.concat(accNo), 40)).concat(StringUtils.rightPad("", 60)));
	} else if (PGROUP_SMARTFREN.equalsIgnoreCase(group)) {
	    param.setElement122(StringUtils.rightPad(TRDES1_SMARTFREN.concat(custNo), 40).concat(StringUtils.rightPad(TRDES2.concat(accNo), 40)).concat(StringUtils.rightPad("", 60)));
	}

	req.setIdentity(identity);
	req.setParameter(param);

	return req;
    }

    /**
     * generateTelcoPostpaidPaymentRes
     * 
     * @param dataResp
     * @return
     */
    public static TelcoPostpaidPaymentResponse generateTelcoPostpaidPaymentRes(
	    TelcoPostpaidPaymentTibcoDataResult dataResp) {
	TelcoPostpaidPaymentResponse resp = new TelcoPostpaidPaymentResponse();

	Date date = new Date();

	String element61 = dataResp.getElement61();

	String custNo = element61.substring(0, 13);
	String custName = element61.substring(19, 64);
	String billPeriod = element61.substring(89, 97);
	//String reference = element61.substring(97, 113);
	String reference = dataResp.getElement122().substring(144, 159);
	String amount = element61.substring(114, 126);
	String amountFee = element61.substring(126, 134);

	resp.setCustNo(custNo.trim());
	resp.setCustName(custName.trim());
	resp.setBillPeriode(billPeriod);
	resp.setAmountFee(new BigDecimal(amountFee));
	resp.setAmount(new BigDecimal(amount));
	resp.setTotalAmount(resp.getAmount().add(resp.getAmountFee()));
	resp.setDate(dateYear.format(date));
	resp.setTime(timeLocal.format(date));
	resp.setReferensi(reference.trim());
	resp.setAccountNo(dataResp.getElement102().trim());

	return resp;

    }

    /**
     * generateDestinationReq
     * 
     * @param reqPay
     * @param resp
     * @return
     */
    public static CommonRequest<DestinationCommonRequest> generateDestinationReq(Identity identity,
	    TelcoPostpaidPaymentRequest reqPay, TelcoPostpaidPaymentResponse resp) {
	CommonRequest<DestinationCommonRequest> req = new CommonRequest<>();
	DestinationCommonRequest destReq = new DestinationCommonRequest();
	destReq.setCategoryId(CategoryEnum.PULSA.getId());
	String speedy = "Speedy";
	String pstn = "Telkom";

	// set destination type
	if (HALO.equalsIgnoreCase(reqPay.getType())) {
	    destReq.setDestinationType(DestinationTypeEnum.POSTHALO.name());
	} else if (MATRIX.equalsIgnoreCase(reqPay.getType())) {
	    destReq.setDestinationType(DestinationTypeEnum.POSTMATRIX.name());
	} else if (XPLOR.equalsIgnoreCase(reqPay.getType())) {
	    destReq.setDestinationType(DestinationTypeEnum.POSTXPLOR.name());
	} else if (XL.equalsIgnoreCase(reqPay.getType())) {
	    destReq.setDestinationType(DestinationTypeEnum.POSTXL.name());
	} else if (TRI.equalsIgnoreCase(reqPay.getType())) {
	    destReq.setDestinationType(DestinationTypeEnum.POSTTRI.name());
	} else if (SMARTFREN.equalsIgnoreCase(reqPay.getType())) {
	    destReq.setDestinationType(DestinationTypeEnum.POSTSMARTFREN.name());
	} else if (speedy.equalsIgnoreCase(reqPay.getType())) {
	    destReq.setDestinationType(DestinationTypeEnum.POSTSPEEDY.name());
	    destReq.setCategoryId(CategoryEnum.TELEPON_TV.getId());
	} else if (pstn.equalsIgnoreCase(reqPay.getType())) {
	    destReq.setDestinationType(DestinationTypeEnum.POSTTELKOM.name());
	    destReq.setCategoryId(CategoryEnum.TELEPON_TV.getId());
	}

	destReq.setUsername(reqPay.getUsername());
	destReq.setSubscriberNumber(resp.getCustNo());
	destReq.setSubscriberName(resp.getCustName());
	
	destReq.setTransactionType(TransactionTypeEnum.TELCOPOST.name());
	destReq.setReference(resp.getReferensi());
	destReq.setAccountNumber(resp.getAccountNo());
	destReq.setTotalAmount(resp.getTotalAmount());

	req.setIdentity(identity);
	req.setData(destReq);

	return req;
    }

    public static TelcoPostpaid generateDataTelcoPostpaid(TelcoPostpaidPaymentResponse resp, Transaction transaction,
	    DestinationCommonRequest destReq) {
	TelcoPostpaid telco = new TelcoPostpaid();

	telco.setCustomerNo(resp.getCustNo());
	telco.setCustomerName(resp.getCustName());
	telco.setPeriode(resp.getBillPeriode());
	telco.setProductName(resp.getProductName());
	telco.setType(destReq.getDestinationType());
	telco.setAmount(resp.getAmount());
	telco.setAmountFee(resp.getAmountFee());
	telco.setTotalAmount(resp.getTotalAmount());
	telco.setIdTransaction(transaction);
	telco.setNpwp(resp.getNpwp());

	return telco;
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

    /**
     * Generate Data Request Telkom PSTN and SPEEDY to Tibco
     * @param dataReq
     * @param forwardInsCode
     * @param codeArra
     * @param codeCbs
     * @return Data Request to Tibco
     */
    public static TelkomPSTNSpeedyInquiryRequest generatePSTNSpeedyTIbcoReq(TelkomPSTNSpeedyInquiryReq dataReq, String custNo,
	    String forwardInsCode, String codeArra, String codeCbs) {
	TelkomPSTNSpeedyInquiryRequest req = new TelkomPSTNSpeedyInquiryRequest();

	Date today = new Date();

	String STAN = generateNumber(6);
	String RRN = generateNumber(12);
	String type = dataReq.getType();


	// generate transaction id
	String txId = BkpmUtil.generateTrxId(TELCO_LENGTH_TRXID);

	IdentityBaseCredentialTibco credentials = new IdentityBaseCredentialTibco();
	credentials.setClientid(CREDENTIALS_MBUKOPIN);

	IdentityBaseTibco identity = new IdentityBaseTibco();
	identity.setClienttxnid(txId);
	identity.setReqdatetime(today);
	identity.setCredentials(credentials);

	// set parameters
	TelkomPSTNSpeedyInquiryTibcoDataReq param = new TelkomPSTNSpeedyInquiryTibcoDataReq();
	param.setMti(MTI_INQUIRY);
	param.setElement3(PROCCESSING_CODE_INQUIRY);
	param.setElement7(element7Format.format(today));
	param.setElement11(STAN);
	param.setElement12(timeLocal.format(today));
	param.setElement13(dateLocal.format(today));
	param.setElement18(MERCHANT_TYPE_MOBILE);
	param.setElement32(ACQUIRING_INSTITUTION_CODE);
	param.setElement33(forwardInsCode);
	param.setElement37(RRN);
	param.setElement41(TERMINAL_IDENTIFICATION);
	param.setElement61(StringUtils.rightPad(custNo, 13));
	param.setElement63(codeArra);
	param.setElement120(ROUTING_INF + codeCbs + MERCHANT_TYPE_MOBILE + ELEMENT_20_441);

	req.setIdentity(identity);
	req.setParameter(param);

	return req;
    }

    /**
     * Generate Response for Inquiry Telkom PSTN and SPEEDY from Tibco 
     * @param dataResp
     * @return Data Inquiry Response
     */
    public static TelkomPSTNSpeedyInquiryRes generatePSTNSpeedyTibcoResp(
	    TelkomPSTNSpeedyInquiryTibcoDataResp dataResp) {
	TelkomPSTNSpeedyInquiryRes response = new TelkomPSTNSpeedyInquiryRes();

	String element61 = dataResp.getElement61();
	String element11 = dataResp.getElement11();
	String element37 = dataResp.getElement37();
	String element20 = dataResp.getElement120();

	String custNo = element61.substring(0, 13);
	String custName = element61.substring(90, 119);
	String productDesc = element61.substring(134, 159);
	String billPeriod = element61.substring(19, 20);
	String amount = element61.substring(78, 89);
	String amountFee = element61.substring(160, 167);

	String codeCbs = element20.substring(11, 17);
	String npwp = element61.substring(120, 134);

	response.setCustNo(custNo.trim());
	response.setCustName(custName.trim());
	response.setBillPeriode(billPeriod);
	response.setProductName(productDesc.trim());
	response.setCodeArr(dataResp.getElement63());
	response.setCodeCbs(codeCbs);
	response.setAmount(new BigDecimal(amount));
	response.setAmountFee(new BigDecimal(amountFee));
	response.setTotalAmount(response.getAmount().add(response.getAmountFee()));
	response.setNpwp(npwp.trim());
	response.setElement11(element11);
	response.setElement37(element37);
	response.setElement61(element61);

	return response;
    }

    /**
     * Genrate Request to Tibco for Purchase Telkom PSTN and SPEEDY
     * @param dataReq
     * @param forwardInsCode
     * @param codeArra
     * @param codeCbs
     * @return Data Request to Tibco
     */
    public static TelkomPSTNSpeedyPurchaseTibcoRequest generateTelkomPSTNSPeedyPurchaseRequest(
    		TelcoPostpaidPaymentRequest dataReq, String forwardInsCode, String codeArra, String codeCbs, String accType) {
	TelkomPSTNSpeedyPurchaseTibcoRequest request = new TelkomPSTNSpeedyPurchaseTibcoRequest();

	Date today = new Date();
	String username = dataReq.getUsername();

	// generate transaction id
	String txId = BkpmUtil.generateTrxId(TELCO_LENGTH_TRXID);

	IdentityBaseCredentialTibco credentials = new IdentityBaseCredentialTibco();
	credentials.setClientid(CREDENTIALS_MBUKOPIN);

	IdentityBaseTibco identity = new IdentityBaseTibco();
	identity.setClienttxnid(txId);
	identity.setReqdatetime(today);
	identity.setCredentials(credentials);

	String accNo = dataReq.getAccountNo();
	String amount = dataReq.getElement61();
	String amt = amount.substring(78, 89);
	String amountFee = amount.substring(160, 167);
	
	String paddingEl4 = new String(new char[18 - amt.length()]).replace('\0', '0');

	String element4 = paddingEl4 + amt + "00";
	
	String paddingEl28 = new String(new char[18 - amountFee.length()]).replace('\0', '0');

	String element28 = paddingEl28 + amountFee + "00";
	

	String STAN = generateNumber(6);
	if(dataReq.getElement11().equals(STAN)) {
	    STAN = generateNumber(6);
	}

	String description = "";
	if (SPEEDY.equals(dataReq.getType())) {
	    String desc1 = StringUtils.rightPad(TRDES1_SPEEDY.concat(dataReq.getCustNo()), 40);
	    String desc2 = StringUtils.rightPad(TRDES2.concat(accNo), 40);
	    String desc3 = StringUtils.leftPad(TRDES3_SPEEDY, 60);
	    description = desc1.concat(desc2).concat(desc3);

	} else {
	    String desc1 = StringUtils.rightPad(TRDES1_PSTN.concat(dataReq.getCustNo()), 40);
	    String desc2 = StringUtils.rightPad(TRDES2.concat(accNo), 40);
	    String desc3 = StringUtils.leftPad(TRDES3_PSTN, 60);
	    description = desc1.concat(desc2).concat(desc3);
	}

	
			
	String space118_147 = new String(new char[148 - 118]).replace('\0', ' ');

	String element123 = IDR_CURRENCY_CODE + CONVENTION_RATE + FEE_CODE + codeCbs + OPERATION_CODE + MIA_POST
		+ BIT_43_52 + BIT_53_72 + BIT_73_76 + BIT_77_96 + username + NOT_BRANCH + NOT_LOCATION + space118_147;
	
	//element3
	String elm3 = "";
	if (accType.equalsIgnoreCase(AccountTypeEnum.SAVING.name())) {
	    elm3 = PROCCESSING_CODE_PAYMENT_TABUNGAN;
	} else if (accType.equalsIgnoreCase(AccountTypeEnum.GIRO.name())) {
	    elm3 = PROCCESSING_CODE_PAYMENT_GIRO;
	}
	
	TelkomPSTNSpeedyPurchaseTibcoDataReq param = new TelkomPSTNSpeedyPurchaseTibcoDataReq();
	param.setMti(MTI_PAYMENT);
	param.setElement3(elm3);
	param.setElement4(element4);
	param.setElement7(element7Format.format(today));
	param.setElement11(STAN);
	param.setElement12(timeLocal.format(today));
	param.setElement13(dateLocal.format(today));
	param.setElement18(MERCHANT_TYPE_MOBILE);
	param.setElement28(element28);
	param.setElement32(ACQUIRING_INSTITUTION_CODE);
	param.setElement33(forwardInsCode);
	param.setElement37(dataReq.getElement37());
	param.setElement41(TERMINAL_IDENTIFICATION);
	param.setElement49(IDR_CURRENCY_CODE);
	param.setElement61(dataReq.getElement61());
	param.setElement63(codeArra);
	param.setElement100(SETTLEMENT_ID);
	param.setElement102(dataReq.getAccountNo());
	param.setElement120(SETTLEMENT_ARANET_ID + codeCbs + MERCHANT_TYPE_MOBILE + FORWARD_ID_BUKOPIN);
	param.setElement122(description);
	param.setElement123(element123);

	request.setIdentity(identity);
	request.setParameter(param);

	return request;

    }

    /**
     * Generate Response for Purchase Telkom PSTN and SPEEDY
     * @param resp
     * @return Telco Purchase Data Response
     */
    public static TelcoPostpaidPaymentResponse generatePurchaseTelkomPSTNSPeedyResponse(
	    TelkomPSTNSpeedyPurchaseTibcoDataResp resp, String custNo) {
	TelcoPostpaidPaymentResponse response = new TelcoPostpaidPaymentResponse();

	Date today = new Date();

	String element61 = resp.getElement61();

	String custName = element61.substring(90, 119);
	String billPeriod = element61.substring(19, 20);
	String amount = element61.substring(78, 89);
	String amountFee = element61.substring(160, 167);
	//String referensi = element61.substring(67, 77);
	String referensi = resp.getElement122().substring(144, 159);
	String accountNo = resp.getElement102();
	String productName = element61.substring(134, 159);
	String npwp = element61.substring(120, 134);

	response.setCustNo(custNo.trim());
	response.setCustName(custName.trim());
	response.setBillPeriode(billPeriod);
	response.setAmount(new BigDecimal(amount));
	response.setAmountFee(new BigDecimal(amountFee));
	response.setTotalAmount(new BigDecimal(amount).add(new BigDecimal(amountFee)));
	response.setDate(dateYear.format(today));
	response.setTime(timeLocal.format(today));
	response.setReferensi(referensi.trim());
	response.setNpwp(npwp.trim());
	response.setAccountNo(accountNo.trim());
	response.setProductName(productName.trim());

	return response;
    }
    
    /**
     * Get CutOffId
     * 
     * @param pgroup Provider Group
     * @return Cut Off Id
     */
    public static long getCutOffId(String pgroup) {
	long cutoffId = SystemCutOffEnum.TELKOMSEL.getId();
	
	if(TelcoUtils.PGROUP_INDOSAT.equals(pgroup)) {
	    cutoffId = SystemCutOffEnum.INDOSAT.getId();
	} else if (TelcoUtils.PGROUP_XL.equals(pgroup)) {
	    cutoffId = SystemCutOffEnum.XL.getId();
	} else if (TelcoUtils.PGROUP_TRI.equals(pgroup)) {
	    cutoffId = SystemCutOffEnum.TRI.getId();
	} else if (TelcoUtils.PGROUP_SMARTFREN.equals(pgroup)) {
	    cutoffId = SystemCutOffEnum.SMARTFREN.getId();
	} else if (TelcoUtils.PROVIDER_TELKOM.equals(pgroup)) {
	    cutoffId = SystemCutOffEnum.TELKOM.getId();
	} else if (TelcoUtils.PROVIDER_SPEEDY.equals(pgroup)) {
	    cutoffId = SystemCutOffEnum.TELKOM_INTERNET.getId();
	} else {
	    cutoffId = SystemCutOffEnum.TELKOMSEL.getId();
	}
	
	return cutoffId;
    }

    /* Overrides: */    
}
