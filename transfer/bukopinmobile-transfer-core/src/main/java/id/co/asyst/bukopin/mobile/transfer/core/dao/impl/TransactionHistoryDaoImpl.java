/*
 * $Id$
 * 
 * Copyright (c) 2020 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.transfer.core.dao.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import id.co.asyst.bukopin.mobile.common.core.util.CryptoUtil;
import id.co.asyst.bukopin.mobile.transfer.core.dao.TransactionHistoryDao;
import id.co.asyst.bukopin.mobile.transfer.model.PostingFromReq;
import id.co.asyst.bukopin.mobile.transfer.model.PostingToReq;
import id.co.asyst.bukopin.mobile.transfer.model.payload.TransactionHistoryCreditCardResponse;
import id.co.asyst.bukopin.mobile.transfer.model.payload.TransactionHistoryEmoneyResponse;
import id.co.asyst.bukopin.mobile.transfer.model.payload.TransactionHistoryFTOverbookResponse;
import id.co.asyst.bukopin.mobile.transfer.model.payload.TransactionHistoryInsuranceResponse;
import id.co.asyst.bukopin.mobile.transfer.model.payload.TransactionHistoryPLNPostpaidResponse;
import id.co.asyst.bukopin.mobile.transfer.model.payload.TransactionHistoryPLNPrepaidResponse;
import id.co.asyst.bukopin.mobile.transfer.model.payload.TransactionHistoryResponse;
import id.co.asyst.bukopin.mobile.transfer.model.payload.TransactionHistorySamolnasResponse;
import id.co.asyst.bukopin.mobile.transfer.model.payload.TransactionHistoryTelcoDataResponse;
import id.co.asyst.bukopin.mobile.transfer.model.payload.TransactionHistoryTelcoPostpaidResponse;
import id.co.asyst.bukopin.mobile.transfer.model.payload.TransactionHistoryTelcoPrepaidResponse;

/**
 * 
 * 
 * @author Gibran Haq
 * @version $Revision$, Mar 5, 2020
 * @since 1.1.Alpha1-SNAPSHOT
 */
@Repository
@Transactional
public class TransactionHistoryDaoImpl implements TransactionHistoryDao {
    /* Constants: */
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    final private String DB_MSSQL = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    final private String DB_POSTGRE = "org.postgresql.Driver";
    SimpleDateFormat dF = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat tF = new SimpleDateFormat("HH:mm:ss");

    /* Attributes: */
    @PersistenceContext
    private EntityManager entityManager;

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */

    /* Getters & setters for transient attributes: */

    /* Functionalities: */

    /* Overrides: */
    
    /* (non-Javadoc)
     * @see id.co.asyst.bukopin.mobile.transfer.core.dao.TransactionHistoryDao#getAllTransactionHistory(java.lang.Long)
     */
    @Override
    public List<TransactionHistoryResponse> getAllTransactionHistory(Long id, Locale loc, String db) {
	System.out.println("Transaction History Dao - Get All Transaction History with user id: "+id);
	
	String note = "";
	if(loc.getLanguage().equals(new Locale("in").getLanguage())) {
	    note = "NOTE_ID";
	} else if (loc.getLanguage().equals(new Locale("en").getLanguage())) {
	    note = "NOTE_EN";
	}
	
	String interval3Month = "";
	if(db.equalsIgnoreCase(DB_POSTGRE)) {
	    interval3Month = "CURRENT_DATE - INTERVAL '3 months'";
	} else if (db.equalsIgnoreCase(DB_MSSQL)) {
	    interval3Month = "DATEADD(MONTH, -3, GETDATE())";
	}
	// from fund transfer table to get overbook and fund transfer data
	String sqlFT = "SELECT" + 
		" ID, ACCOUNT_NUMBER, (ADMIN_FEE + AMOUNT) AS TOTAL_AMOUNT," + 
		" METHOD AS TYPE, REFERENCE_CODE AS REFERENCE_NUMBER," + 
		" CREATED_ON, " + note + 
		" FROM FUND_TRANSFER" + 
		" WHERE" + 
		" USER_ID = "+ id + 
		" AND CREATED_ON >= "+ interval3Month +";";
	
	// from transaction table to get purchase and payment data
	String slqT = "SELECT" +
		" ID, ACCOUNT_NUMBER, TOTAL_AMOUNT, TYPE, REFERENCE_NUMBER," +
		" CREATED_DATE, " + note + 
		" FROM TRX" + 
		" WHERE" +
		" ID_USER = "+ id + 
		" AND CREATED_DATE >= "+ interval3Month +";";
	
	// run query
	Session session = entityManager.unwrap(Session.class);
	List<Object> ft = session.createSQLQuery(sqlFT).list();
	List<Object> t = session.createSQLQuery(slqT).list();

	// Mapping result
	List<TransactionHistoryResponse> result = new ArrayList<TransactionHistoryResponse>();
	ft.forEach(row -> {
	    Object[] cols = (Object[]) row;
	    TransactionHistoryResponse th = new TransactionHistoryResponse();
	    th.setIdDetail(Long.valueOf(cols[0].toString()));
	    th.setAccountNumber(String.valueOf(cols[1]));
	    th.setAmount(String.valueOf(cols[2]).replace(".00", ""));
	    th.setType(String.valueOf(cols[3]));
	    th.setReferenceNumber(String.valueOf(cols[4]).trim());
	    th.setDateTime(String.valueOf(cols[5]));
	    th.setNote(String.valueOf(cols[6]));
	    result.add(th);
	});
	
	t.forEach(row -> {
	    Object[] cols = (Object[]) row;
	    TransactionHistoryResponse th = new TransactionHistoryResponse();
	    th.setIdDetail(Long.valueOf(cols[0].toString()));
	    th.setAccountNumber(String.valueOf(cols[1] == null ? "" : cols[1]));
	    th.setAmount(String.valueOf(cols[2] == null ? "" : cols[2]));
	    th.setType(String.valueOf(cols[3] == null ? "" : cols[3]));
	    th.setReferenceNumber(String.valueOf(cols[4]));
	    th.setDateTime(String.valueOf(cols[5]));
	    th.setNote(String.valueOf(cols[6]));
	    result.add(th);
	});
	// sort by create date descending 
	result.sort(Comparator.comparing(TransactionHistoryResponse::getDateTime).reversed());
	entityManager.close();
	
	return result;
    }
    
    @Override
    public List<TransactionHistoryResponse> getAllWithPaging(Long id, Locale loc, String db, int offset, int limit) {
	String note = "";
	if (loc.getLanguage().equals(new Locale("in").getLanguage())) {
	    note = "NOTE_ID";
	} else if (loc.getLanguage().equals(new Locale("en").getLanguage())) {
	    note = "NOTE_EN";
	}

	String interval3Month = "";
	if (db.equalsIgnoreCase(DB_POSTGRE)) {
	    interval3Month = "CURRENT_DATE - INTERVAL '3 months'";
	} else if (db.equalsIgnoreCase(DB_MSSQL)) {
	    interval3Month = "DATEADD(MONTH, -3, GETDATE())";
	}

	String sqlUnion = "select * from " + 
		"(SELECT ID, ACCOUNT_NUMBER, (ADMIN_FEE + AMOUNT) AS TOTAL_AMOUNT," + 
		" METHOD AS TYPE, REFERENCE_CODE AS REFERENCE_NUMBER, CREATED_ON AS CREATED_DATE, "+ note + 
		" FROM FUND_TRANSFER " + 
		" WHERE STATUS = 'SUCCESS' " + 
		" AND USER_ID = " + id + " AND CREATED_ON >= " + interval3Month + 
		" UNION ALL " + 
		"SELECT ID, ACCOUNT_NUMBER, TOTAL_AMOUNT, TYPE, REFERENCE_NUMBER, CREATED_DATE, NOTE_EN" + 
		" FROM DB_MBANKING.dbo.TRX" + 
		" WHERE STATUS = 'SUCCESS'" + 
		" AND ID_USER = " + id + " AND CREATED_DATE >= " + interval3Month + 
		") A ORDER BY CREATED_DATE DESC"+ 
		" OFFSET "+offset+" ROWS FETCH NEXT "+limit+" ROWS ONLY";

	// run query
	Session session = entityManager.unwrap(Session.class);
	List<Object> union = session.createSQLQuery(sqlUnion).list();

	// Mapping result
	List<TransactionHistoryResponse> result = new ArrayList<TransactionHistoryResponse>();
	union.forEach(row -> {
	    Object[] cols = (Object[]) row;
	    TransactionHistoryResponse th = new TransactionHistoryResponse();
	    th.setIdDetail(Long.valueOf(cols[0].toString()));
	    th.setAccountNumber(String.valueOf(cols[1] == null ? "" : cols[1]));
	    th.setAmount(String.valueOf(cols[2] == null ? "" : cols[2]));
	    th.setType(String.valueOf(cols[3] == null ? "" : cols[3]));
	    th.setReferenceNumber(String.valueOf(cols[4]));
	    th.setDateTime(String.valueOf(cols[5]));
	    th.setNote(String.valueOf(cols[6]));
	    result.add(th);
	});
	entityManager.close();

	return result;
    }

    /* (non-Javadoc)
     * @see id.co.asyst.bukopin.mobile.transfer.core.dao.TransactionHistoryDao#getDetailFundTransferOverbookHistory(java.lang.Long)
     */
    @Override
    public Optional<TransactionHistoryFTOverbookResponse> getDetailFundTransferOverbookHistory(Long id, String type) {
	System.out.println("Transaction History Dao - Get Detail Fund Transfer Overbook with id: " + id);
	Optional<TransactionHistoryFTOverbookResponse> response = Optional.empty();
	TransactionHistoryFTOverbookResponse result = new TransactionHistoryFTOverbookResponse();
	
	String sql = "SELECT" +
		" A.ID, A.REFERENCE_CODE, A.CREATED_ON, A.ACCOUNT_NUMBER," +
		" A.AMOUNT, A.ADMIN_FEE, A.MESSAGE, A.METHOD, B.ACCOUNT_NAME," +
		" B.ACCOUNT_NUMBER AS RECEIVERNUMBER, B.ALIAS, C.BANK_NAME" + 
		" FROM FUND_TRANSFER A" +
		" JOIN RECEIVER_INFO B ON B.ID = A.RECEIVER_INFO_ID" +
		" JOIN BANK C ON B.BANK_ID = C.ID" +
		" WHERE A.ID = "+id+" AND A.METHOD LIKE '%"+type+"%';";

	// run query
	Session session = entityManager.unwrap(Session.class);
	Object thfo = session.createSQLQuery(sql).uniqueResult();
	
	// data not found handler
	if(thfo == null) {
	    return response;
	}
	ObjectMapper objMapper = new ObjectMapper();
	List<String> fundTransferOverbookResp = objMapper.convertValue(thfo, ArrayList.class);
	String dateLong = String.valueOf(fundTransferOverbookResp.get(2));
	Date date = new Date(Long.valueOf(dateLong));
	
	// encrypt account number
	String accountNumEnc = CryptoUtil.encryptBase64(fundTransferOverbookResp.get(3));
	
	// sql account info
	String sqlAccInfo = "SELECT" +
		" A.ACCOUNT_NAME, B.PRODUCT_NAME" +
		" FROM ACCOUNT_INFO A" +
		" JOIN PRODUCT B ON B.ID = A.PRODUCT_ID" +
		" WHERE A.ACCOUNT_NO LIKE '" + accountNumEnc + "';";
	Object accInfoObj = session.createSQLQuery(sqlAccInfo).uniqueResult();
	List<String> accInfoResp = objMapper.convertValue(accInfoObj, ArrayList.class);
	
	// set posting from
	PostingFromReq postingFrom = new PostingFromReq();
	postingFrom.setAccountNumber(fundTransferOverbookResp.get(3));
	postingFrom.setAccountName(accInfoResp.get(0).trim());
	
	// set posting to
	PostingToReq postingTo = new PostingToReq();
	postingTo.setAccountName(fundTransferOverbookResp.get(8));
	postingTo.setAccountNumber(fundTransferOverbookResp.get(9));
	postingTo.setAlias(fundTransferOverbookResp.get(10));
	
	// Mapping result
	result.setBankReference(fundTransferOverbookResp.get(1));
	result.setTransmissionDateTime(String.valueOf(sdf.format(date)));
	result.setPostingFrom(postingFrom);
	result.setPostingTo(postingTo);
	result.setAmount(new BigDecimal(String.valueOf(fundTransferOverbookResp.get(4)).replace(".00", "")));
	result.setAdminFee(new BigDecimal(String.valueOf(fundTransferOverbookResp.get(5))));
	result.setTotalAmount(result.getAmount().add(result.getAdminFee()));
	result.setMessage(fundTransferOverbookResp.get(6));
	result.setMethod(fundTransferOverbookResp.get(7));
	result.setBankName(fundTransferOverbookResp.get(11));
	result.setProductName(accInfoResp.get(1));
	response = Optional.of(result);

	entityManager.close();
	
	return response;
    }

    /* (non-Javadoc)
     * @see id.co.asyst.bukopin.mobile.transfer.core.dao.TransactionHistoryDao#getDetailPLNPrepaidHistory(java.lang.Long)
     */
    @Override
    public Optional<TransactionHistoryPLNPrepaidResponse> getDetailPLNPrepaidHistory(Long id) {
	System.out.println("Transaction History Dao - Get Detail PLN Prepaid with id: " + id);
	Optional<TransactionHistoryPLNPrepaidResponse> response = Optional.empty();
	TransactionHistoryPLNPrepaidResponse result = new TransactionHistoryPLNPrepaidResponse();
	
	String sql = "SELECT" +
		" A.ID, A.REFERENCE_NUMBER, A.CREATED_DATE, A.TYPE, B.ACCOUNT_NUMBER," +
		" B.ADMIN_FEE, B.AMOUNT, B.DAYA, B.TARIF, B.TOKEN, B.TOTAL_AMOUNT," +
		" C.SUBSCRIBER_NAME, C.SUBSCRIBER_NUMBER, C.ALIAS, B.JUMLAH_KWH" + 
		" FROM TRX A" +
		" JOIN PLN_PREPAID B ON B.ID_TRANSACTION = A.ID" +
		" JOIN DESTINATION C ON C.ID = A.ID_DESTINATION" +
		" WHERE A.ID = "+id+";";

	// run query
	Session session = entityManager.unwrap(Session.class);
	Object thp = session.createSQLQuery(sql).uniqueResult();
	
	// data not found handler
	if(thp == null) {
	    return response;
	}
	ObjectMapper objMapper = new ObjectMapper();
	List<String> plnPrepaidResp = objMapper.convertValue(thp, ArrayList.class);
	String dateLong = String.valueOf(plnPrepaidResp.get(2));
	Date date = new Date(Long.valueOf(dateLong));
	
	// Mapping result
	result.setReferenceNumber(plnPrepaidResp.get(1));
	result.setDateTime(String.valueOf(sdf.format(date)));
	result.setAccountNumber(plnPrepaidResp.get(4));
	result.setAdminFee(new BigDecimal(String.valueOf(plnPrepaidResp.get(5)).replace(".00", "")));
	result.setAmount(new BigDecimal(String.valueOf(plnPrepaidResp.get(6)).replace(".00", "")));
	result.setPowerConsuming(plnPrepaidResp.get(8)+"/"+plnPrepaidResp.get(7));
	result.setToken(plnPrepaidResp.get(9));
	result.setTotalAmount(new BigDecimal(String.valueOf(plnPrepaidResp.get(10)).replace(".00", "")));
	result.setSubscriberName(plnPrepaidResp.get(11));
	result.setSubscriberNumber(plnPrepaidResp.get(12));
	result.setAlias(plnPrepaidResp.get(13));
	result.setTotalKwh(plnPrepaidResp.get(14));
	response = Optional.of(result);

	entityManager.close();
	
	return response;
    }

    /* (non-Javadoc)
     * @see id.co.asyst.bukopin.mobile.transfer.core.dao.TransactionHistoryDao#getDetailPLNPostpaidHistory(java.lang.Long)
     */
    @Override
    public Optional<TransactionHistoryPLNPostpaidResponse> getDetailPLNPostpaidHistory(Long id) {
	System.out.println("Transaction History Dao - Get Detail PLN Postpaid with id: " + id);
	Optional<TransactionHistoryPLNPostpaidResponse> response = Optional.empty();
	TransactionHistoryPLNPostpaidResponse result = new TransactionHistoryPLNPostpaidResponse();
	
	String sql = "SELECT" +
		" A.ID, A.REFERENCE_NUMBER, A.CREATED_DATE, B.ACCOUNT_NUMBER," +
		" B.ADMIN_FEE, B.AMOUNT, B.DAYA, B.TARIF, B.TOTAL_AMOUNT, B.BULAN, B.METER_START," +
		" B.METER_END, B.PENALTY_FEE, C.SUBSCRIBER_NAME, C.SUBSCRIBER_NUMBER, C.ALIAS" + 
		" FROM TRX A" +
		" JOIN PLN_POSTPAID B ON B.TRANSACTION_ID = A.ID" +
		" JOIN DESTINATION C ON C.ID = A.ID_DESTINATION" +
		" WHERE A.ID = "+id+";";

	// run query
	Session session = entityManager.unwrap(Session.class);
	Object thp = session.createSQLQuery(sql).uniqueResult();
	
	// data not found handler
	if(thp == null) {
	    return response;
	}
	ObjectMapper objMapper = new ObjectMapper();
	List<String> plnPostpaidResp = objMapper.convertValue(thp, ArrayList.class);
	String dateLong = String.valueOf(plnPostpaidResp.get(2));
	Date date = new Date(Long.valueOf(dateLong));
	
	// set bill period
	int no = 1;
	Pattern pattern = Pattern.compile("([|])");
	Map<String, String> billPeriod = new HashMap<String, String>();
	String allBillPeriod = plnPostpaidResp.get(9);
	String[] listBillPeriod = pattern.split(allBillPeriod);
	for(String month : listBillPeriod) {
	    billPeriod.put(String.valueOf(no), month);
	    no++;
	}
	
	// Mapping result
	result.setReferenceNumber(plnPostpaidResp.get(1));
	result.setDateTime(String.valueOf(sdf.format(date)));
	result.setAccountNo(plnPostpaidResp.get(3));
	result.setAdminFee(new BigDecimal(String.valueOf(plnPostpaidResp.get(4)).replace(".00", "")));
	result.setAmount(new BigDecimal(String.valueOf(plnPostpaidResp.get(5)).replace(".00", "")));
	result.setDaya(plnPostpaidResp.get(6));
	result.setTarif(plnPostpaidResp.get(7));
	result.setTotalAmount(new BigDecimal(String.valueOf(plnPostpaidResp.get(8)).replace(".00", "")));
	result.setBillPeriod(billPeriod);
	result.setPrevMeter(String.valueOf(plnPostpaidResp.get(10)).replace(".00", ""));
	result.setCurrMeter(String.valueOf(plnPostpaidResp.get(11)).replace(".00", ""));
	result.setPenaltyFee(new BigDecimal(String.valueOf(plnPostpaidResp.get(12)).replace(".00", "")));
	result.setSubscriberName(plnPostpaidResp.get(13));
	result.setSubscriberNumber(plnPostpaidResp.get(14));
	result.setAlias(plnPostpaidResp.get(15));
	response = Optional.of(result);

	entityManager.close();
	
	return response;
    }

    /* (non-Javadoc)
     * @see id.co.asyst.bukopin.mobile.transfer.core.dao.TransactionHistoryDao#getDetailTelcoPrepaidHistory(java.lang.Long)
     */
    @Override
    public Optional<TransactionHistoryTelcoPrepaidResponse> getDetailTelcoPrepaidHistory(Long id) {
	System.out.println("Transaction History Dao - Get Detail Telco Prepaid with id: " + id);
	Optional<TransactionHistoryTelcoPrepaidResponse> response = Optional.empty();
	TransactionHistoryTelcoPrepaidResponse result = new TransactionHistoryTelcoPrepaidResponse();
	
	String sql = "SELECT" +
		" A.ID, A.REFERENCE_NUMBER, A.CREATED_DATE, B.ACCOUNT_NUMBER," +
		" B.ADMIN_FEE, B.AMOUNT, B.TOTAL_AMOUNT, B.TYPE_TELCO," +
		" C.SUBSCRIBER_NAME, C.SUBSCRIBER_NUMBER, C.ALIAS" + 
		" FROM TRX A" +
		" JOIN TELCO_PREPAID B ON B.ID_TRANSACTION = A.ID" +
		" JOIN DESTINATION C ON C.ID = A.ID_DESTINATION" +
		" WHERE A.ID = "+id+";";

	// run query
	Session session = entityManager.unwrap(Session.class);
	Object tht = session.createSQLQuery(sql).uniqueResult();
	
	// data not found handler
	if(tht == null) {
	    return response;
	}
	ObjectMapper objMapper = new ObjectMapper();
	List<String> telcoPrepaidResp = objMapper.convertValue(tht, ArrayList.class);
	String dateLong = String.valueOf(telcoPrepaidResp.get(2));
	Date date = new Date(Long.valueOf(dateLong));
	
	String pGroup = telcoPrepaidResp.get(7).substring(6);
	
	// Mapping result
	result.setReferenceNumber(telcoPrepaidResp.get(1));
	result.setDateTime(String.valueOf(sdf.format(date)));
	result.setAccountNumber(telcoPrepaidResp.get(3));
	result.setAdminFee(new BigDecimal(String.valueOf(telcoPrepaidResp.get(4)).replace(".00", "")));
	result.setAmount(new BigDecimal(String.valueOf(telcoPrepaidResp.get(5)).replace(".00", "")));
	result.setTotalAmount(new BigDecimal(String.valueOf(telcoPrepaidResp.get(6)).replace(".00", "")));
	result.setpGroup(pGroup);
	result.setTypeTelco(telcoPrepaidResp.get(7));
	result.setSubscriberName(telcoPrepaidResp.get(8));
	result.setPhoneNumber(telcoPrepaidResp.get(9));
	result.setAlias(telcoPrepaidResp.get(10));
	response = Optional.of(result);

	entityManager.close();
	
	return response;
    }

    /* (non-Javadoc)
     * @see id.co.asyst.bukopin.mobile.transfer.core.dao.TransactionHistoryDao#getDetailTelcoPostpaidHistory(java.lang.Long)
     */
    @Override
    public Optional<TransactionHistoryTelcoPostpaidResponse> getDetailTelcoPostpaidHistory(Long id) {
	System.out.println("Transaction History Dao - Get Detail Telco Postpaid with id: " + id);
	Optional<TransactionHistoryTelcoPostpaidResponse> response = Optional.empty();
	TransactionHistoryTelcoPostpaidResponse result = new TransactionHistoryTelcoPostpaidResponse();
	
	String sql = "SELECT" +
		" A.ID, A.REFERENCE_NUMBER, A.CREATED_DATE, A.ACCOUNT_NUMBER," +
		" B.AMOUNT_FEE, B.AMOUNT, B.TOTAL_AMOUNT, B.PERIODE," +
		" B.PRODUCT_NAME, C.SUBSCRIBER_NAME, C.SUBSCRIBER_NUMBER, C.ALIAS, B.NPWP" + 
		" FROM TRX A" +
		" JOIN TELCO_POSTPAID B ON B.ID_TRANSACTION = A.ID" +
		" JOIN DESTINATION C ON C.ID = A.ID_DESTINATION" +
		" WHERE A.ID = "+id+";";

	// run query
	Session session = entityManager.unwrap(Session.class);
	Object tht = session.createSQLQuery(sql).uniqueResult();
	
	// data not found handler
	if(tht == null) {
	    return response;
	}
	ObjectMapper objMapper = new ObjectMapper();
	List<String> telcoPostpaidResp = objMapper.convertValue(tht, ArrayList.class);
	String dateLong = String.valueOf(telcoPostpaidResp.get(2));
	Date date = new Date(Long.valueOf(dateLong));
	
	// Mapping result
	result.setReferensi(telcoPostpaidResp.get(1).trim());
	result.setDate(String.valueOf(dF.format(date)));
	result.setTime(String.valueOf(tF.format(date)));
	result.setAccountNo(telcoPostpaidResp.get(3));
	result.setAmountFee(new BigDecimal(String.valueOf(telcoPostpaidResp.get(4)).replace(".00", "")));
	result.setAmount(new BigDecimal(String.valueOf(telcoPostpaidResp.get(5)).replace(".00", "")));
	result.setTotalAmount(new BigDecimal(String.valueOf(telcoPostpaidResp.get(6)).replace(".00", "")));
	result.setBillPeriode(telcoPostpaidResp.get(7));
	result.setProductName(telcoPostpaidResp.get(8).trim());
	result.setCustName(telcoPostpaidResp.get(9).trim());
	result.setCustNo(telcoPostpaidResp.get(10).trim());
	result.setAlias(telcoPostpaidResp.get(11));
	result.setNpwp(telcoPostpaidResp.get(12));
	response = Optional.of(result);

	entityManager.close();
	
	return response;
    }

    /* (non-Javadoc)
     * @see id.co.asyst.bukopin.mobile.transfer.core.dao.TransactionHistoryDao#getDetailEmoneyHistory(java.lang.Long)
     */
    @Override
    public Optional<TransactionHistoryEmoneyResponse> getDetailEmoneyHistory(Long id) {
	System.out.println("Transaction History Dao - Get Detail Emoney with id: " + id);
	Optional<TransactionHistoryEmoneyResponse> response = Optional.empty();
	TransactionHistoryEmoneyResponse result = new TransactionHistoryEmoneyResponse();
	
	String sql = "SELECT" +
		" A.ID, A.REFERENCE_NUMBER, A.CREATED_DATE, A.ACCOUNT_NUMBER," +
		" B.AMOUNT_FEE, B.AMOUNT, B.TOTAL_AMOUNT, B.TYPE_E_MONEY," +
		" C.SUBSCRIBER_NAME, C.SUBSCRIBER_NUMBER, C.ALIAS" + 
		" FROM TRX A" +
		" JOIN E_MONEY B ON B.ID_TRANSACTION = A.ID" +
		" JOIN DESTINATION C ON C.ID = A.ID_DESTINATION" +
		" WHERE A.ID = "+id+";";

	// run query
	Session session = entityManager.unwrap(Session.class);
	Object the = session.createSQLQuery(sql).uniqueResult();
	
	// data not found handler
	if(the == null) {
	    return response;
	}
	ObjectMapper objMapper = new ObjectMapper();
	List<String> emoneyResp = objMapper.convertValue(the, ArrayList.class);
	String dateLong = String.valueOf(emoneyResp.get(2));
	Date date = new Date(Long.valueOf(dateLong));
	
	// Mapping result
	result.setReference(emoneyResp.get(1));
	result.setDate(String.valueOf(dF.format(date)));
	result.setTime(String.valueOf(tF.format(date)));
	result.setAccountNo(emoneyResp.get(3));
	result.setAmountFee(new BigDecimal(String.valueOf(emoneyResp.get(4)).replace(".00", "")));
	result.setAmount(new BigDecimal(String.valueOf(emoneyResp.get(5)).replace(".00", "")));
	result.setTotalAmount(new BigDecimal(String.valueOf(emoneyResp.get(6)).replace(".00", "")));
	result.setType(emoneyResp.get(7));
	result.setCustName(emoneyResp.get(8));
	result.setCustNo(emoneyResp.get(9));
	result.setAlias(emoneyResp.get(10));
	response = Optional.of(result);

	entityManager.close();
	
	return response;
    }

    /* (non-Javadoc)
     * @see id.co.asyst.bukopin.mobile.transfer.core.dao.TransactionHistoryDao#getDetailInsuranceHistory(java.lang.Long)
     */
    @Override
    public Optional<TransactionHistoryInsuranceResponse> getDetailInsuranceHistory(Long id) {
	System.out.println("Transaction History Dao - Get Detail Insurance with id: " + id);
	Optional<TransactionHistoryInsuranceResponse> response = Optional.empty();
	TransactionHistoryInsuranceResponse result = new TransactionHistoryInsuranceResponse();
	
	String sql = "SELECT" +
		" A.ID, A.REFERENCE_NUMBER, A.CREATED_DATE, A.ACCOUNT_NUMBER," +
		" B.*, C.SUBSCRIBER_NUMBER, C.SUBSCRIBER_NAME" +
		" FROM TRX A" +
		" JOIN INSURANCE B ON B.ID_TRANSACTION = A.ID" +
		" JOIN DESTINATION C ON C.ID = A.ID_DESTINATION" +
		" WHERE A.ID = "+id+";";


	// run query
	Session session = entityManager.unwrap(Session.class);
	Object thi = session.createSQLQuery(sql).uniqueResult();
	
	// data not found handler
	if(thi == null) {
	    return response;
	}
	ObjectMapper objMapper = new ObjectMapper();
	List<String> insuranceResp = objMapper.convertValue(thi, ArrayList.class);
	String dateLong = String.valueOf(insuranceResp.get(2));
	Date date = new Date(Long.valueOf(dateLong));
	
	// Mapping result
	result.setReferenceNumber(insuranceResp.get(1));
	result.setDateTime(String.valueOf(sdf.format(date)));
	result.setAccountNumber(insuranceResp.get(3));
	result.setAdminFee(new BigDecimal(String.valueOf(insuranceResp.get(5)).replace(".00", "")));
	result.setAmount(new BigDecimal(String.valueOf(insuranceResp.get(6)).replace(".00", "")));
	result.setMonth(Integer.valueOf(insuranceResp.get(7)));
	result.setParticipant(insuranceResp.get(8));
	result.setPrepaidInsurance(new BigDecimal(String.valueOf(insuranceResp.get(9)).replace(".00", "")));
	result.setTotalAmount(new BigDecimal(String.valueOf(insuranceResp.get(10)).replace(".00", "")));
	result.setCurrentAmount(new BigDecimal(String.valueOf(insuranceResp.get(13)).replace(".00", "")));
	result.setSubscriberNumber(insuranceResp.get(14).trim());
	result.setSubscriberName(insuranceResp.get(15).trim());
	response = Optional.of(result);

	entityManager.close();
	
	return response;
    }
        

    /* (non-Javadoc)
     * @see id.co.asyst.bukopin.mobile.transfer.core.dao.TransactionHistoryDao#getDetailCreditCardHistory(java.lang.Long)
     */
    @Override
    public Optional<TransactionHistoryCreditCardResponse> getDetailCreditCardHistory(Long id) {
	System.out.println("Transaction History Dao - Get Detail Credit Card with id: " + id);
	Optional<TransactionHistoryCreditCardResponse> response = Optional.empty();
	TransactionHistoryCreditCardResponse result = new TransactionHistoryCreditCardResponse();
	
	String sql = "SELECT" + " A.ID, A.REFERENCE_NUMBER, A.CREATED_DATE, A.ACCOUNT_NUMBER,"
		+ " B.TYPE, B.BILLED_AMOUNT, B.MINIMUM_AMOUNT, B.AMOUNT,"
		+ " C.SUBSCRIBER_NAME, C.SUBSCRIBER_NUMBER, C.ALIAS" + " FROM TRX A"
		+ " JOIN CREDIT_CARD B ON B.ID_TRANSACTION = A.ID" + " JOIN DESTINATION C ON C.ID = A.ID_DESTINATION"
		+ " WHERE A.STATUS = 'SUCCESS' AND A.ID = " + id + ";";

	
	// run query
	Session session = entityManager.unwrap(Session.class);
	Object thp = session.createSQLQuery(sql).uniqueResult();
	
	// data not found handler
	if(thp == null) {
	    return response;
	}
	ObjectMapper objMapper = new ObjectMapper();
	List<String> creditCardResp = objMapper.convertValue(thp, ArrayList.class);
	String dateLong = String.valueOf(creditCardResp.get(2));
	Date date = new Date(Long.valueOf(dateLong));
	
	// Mapping result
	result.setReferenceNumber(creditCardResp.get(1));
	result.setDateTime(String.valueOf(sdf.format(date)));
	result.setAccountNumber(creditCardResp.get(3));
	result.setName(creditCardResp.get(4));
	result.setBilledAmount(new BigDecimal(String.valueOf(creditCardResp.get(5)).replace(".00", "")));
	result.setMinimumPayment(new BigDecimal(String.valueOf(creditCardResp.get(6)).replace(".00", "")));
	result.setAmount(new BigDecimal(String.valueOf(creditCardResp.get(7)).replace(".00", "")));
	result.setSubscriberName(creditCardResp.get(8));
	result.setSubscriberNumber(creditCardResp.get(9));
	result.setAlias(creditCardResp.get(10));
	
	
	response = Optional.of(result);

	entityManager.close();
	
	return response;
    }


    @Override
    public Optional<TransactionHistorySamolnasResponse> getDetailSamolnasHistory(Long id) {
	System.out.println("Transaction History Dao - Get Detail Samolnas with id: " + id);
	Optional<TransactionHistorySamolnasResponse> response = Optional.empty();
	TransactionHistorySamolnasResponse result = new TransactionHistorySamolnasResponse();
	
	String sql = "SELECT" +
		" A.ID, A.REFERENCE_NUMBER, A.CREATED_DATE, A.ACCOUNT_NUMBER," +
		" A.TOTAL_AMOUNT, B.*, C.SUBSCRIBER_NUMBER, C.SUBSCRIBER_NAME, C.ALIAS" +
		" FROM TRX A" +
		" JOIN SAMOLNAS B ON B.TRANSACTION_ID = A.ID" +
		" JOIN DESTINATION C ON C.ID = A.ID_DESTINATION" +
		" WHERE A.ID = "+id+";";


	// run query
	Session session = entityManager.unwrap(Session.class);
	Object ths = session.createSQLQuery(sql).uniqueResult();
	
	// data not found handler
	if(ths == null) {
	    return response;
	}
	ObjectMapper objMapper = new ObjectMapper();
	List<String> samolnasResp = objMapper.convertValue(ths, ArrayList.class);
	String dateLong = String.valueOf(samolnasResp.get(2));
	Date date = new Date(Long.valueOf(dateLong));
	
	// Mapping result
	result.setReferenceNumber(samolnasResp.get(1));
	result.setDate(String.valueOf(dF.format(date)));
	result.setTime(String.valueOf(tF.format(date)));
	result.setAccountNumber(samolnasResp.get(3));
	result.setTotalAmount(new BigDecimal(String.valueOf(samolnasResp.get(4)).replace(".00", "")));
	result.setAdminFee(new BigDecimal(String.valueOf(samolnasResp.get(6)).replace(".00", "")));
	result.setAmount(new BigDecimal(String.valueOf(samolnasResp.get(7)).replace(".00", "")));
	result.setMachineNo(samolnasResp.get(8));
	result.setMerk(samolnasResp.get(9));
	result.setModel(samolnasResp.get(10));
	result.setNewTbpkb(samolnasResp.get(11));
	result.setNik(samolnasResp.get(12));
	result.setNpkb(samolnasResp.get(13));
	result.setProgresif(samolnasResp.get(14));
	result.setTbpkb(samolnasResp.get(15));
	result.setType(samolnasResp.get(16));
	result.setValidityPeriod(samolnasResp.get(17));
	result.setYear(samolnasResp.get(18));
	result.setPayCode(samolnasResp.get(20));
	result.setSubscriberName(samolnasResp.get(21));
	result.setAlias(samolnasResp.get(22));
	response = Optional.of(result);

	entityManager.close();
	
	return response;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see id.co.asyst.bukopin.mobile.transfer.core.dao.TransactionHistoryDao#
     * getDetailTelcoPrepaidHistory(java.lang.Long)
     */
    @Override
    public Optional<TransactionHistoryTelcoDataResponse> getDetailTelcoDataHistory(Long id) {
	System.out.println("Transaction History Dao - Get Detail Telco Data with id: " + id);
	Optional<TransactionHistoryTelcoDataResponse> response = Optional.empty();
	TransactionHistoryTelcoDataResponse result = new TransactionHistoryTelcoDataResponse();

	String sql = "SELECT"
		+ " A.ID, A.REFERENCE_NUMBER, A.CREATED_DATE, A.ACCOUNT_NUMBER, B.*, C.SUBSCRIBER_NUMBER, C.ALIAS"
		+ " FROM TRX A" + " JOIN TELCO_DATA B ON B.ID_TRANSACTION = A.ID"
		+ " JOIN DESTINATION C ON C.ID = A.ID_DESTINATION" + " WHERE A.ID = " + id + ";";

	// run query
	Session session = entityManager.unwrap(Session.class);
	Object tht = session.createSQLQuery(sql).uniqueResult();

	// data not found handler
	if (tht == null) {
	    return response;
	}
	ObjectMapper objMapper = new ObjectMapper();
	List<String> telcoDataResp = objMapper.convertValue(tht, ArrayList.class);
	String dateLong = String.valueOf(telcoDataResp.get(2));
	Date date = new Date(Long.valueOf(dateLong));

	BigDecimal amount = new BigDecimal(String.valueOf(telcoDataResp.get(6)).replace(".00", ""));
	BigDecimal adminFee = new BigDecimal(String.valueOf(telcoDataResp.get(5)).replace(".00", ""));
	BigDecimal totalAmount = amount.add(adminFee);

	// Mapping result
	result.setDateTime(String.valueOf(sdf.format(date)));
	result.setReferenceNumber(telcoDataResp.get(1));
	result.setAccountNumber(telcoDataResp.get(3));
	result.setPhoneNumber(telcoDataResp.get(10));
	result.setTitle(telcoDataResp.get(7));
	result.setTypeData(telcoDataResp.get(8).substring(11));
	result.setAmount(amount);
	result.setAdminFee(adminFee);
	result.setTotalAmount(totalAmount);
	result.setAlias(telcoDataResp.get(11));

	response = Optional.of(result);

	entityManager.close();

	return response;
    }
}
