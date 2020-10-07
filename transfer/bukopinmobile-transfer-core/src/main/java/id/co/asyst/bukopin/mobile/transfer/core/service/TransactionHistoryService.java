/*
 * $Id$
 * 
 * Copyright (c) 2020 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.transfer.core.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.co.asyst.bukopin.mobile.common.model.payload.Paging;
import id.co.asyst.bukopin.mobile.common.model.payload.PagingResponse;
import id.co.asyst.bukopin.mobile.transfer.core.dao.TransactionHistoryDao;
import id.co.asyst.bukopin.mobile.transfer.model.payload.TransactionHistoryCreditCardResponse;
import id.co.asyst.bukopin.mobile.transfer.model.payload.TransactionHistoryEmoneyResponse;
import id.co.asyst.bukopin.mobile.transfer.model.payload.TransactionHistoryFTOverbookResponse;
import id.co.asyst.bukopin.mobile.transfer.model.payload.TransactionHistoryInsuranceResponse;
import id.co.asyst.bukopin.mobile.transfer.model.payload.TransactionHistoryPLNPostpaidResponse;
import id.co.asyst.bukopin.mobile.transfer.model.payload.TransactionHistoryPLNPrepaidResponse;
import id.co.asyst.bukopin.mobile.transfer.model.payload.TransactionHistoryPagingResponse;
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
@Service
public class TransactionHistoryService {
    /* Constants: */

    /* Attributes: */
    @Autowired
    private TransactionHistoryDao transactionHistoryDao;

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */

    /* Getters & setters for transient attributes: */

    /* Functionalities: */
    /**
     * Get All Transaction History
     * @param id User's Id
     * @return All transaction history in 3 months ago
     */
    public List<TransactionHistoryResponse> getAllTransactionHistory(Long id, Locale loc, String db){
	return transactionHistoryDao.getAllTransactionHistory(id, loc, db);
    }
    
    /**
     * Get All Transaction History With Paging
     * 
     * @param id The User Id
     * @param loc The Locale request
     * @param db Database Type
     * @param page Page to get
     * @param limit Limit data per page
     * @return Current page of Transaction History
     */
    public List<TransactionHistoryResponse> getAllWithPaging(Long userId, Locale loc, String db, int page, int limit){
	int offset = 0;
	offset = (page -1) * limit;
	return transactionHistoryDao.getAllWithPaging(userId, loc, db, offset, limit);
    }
    
    /**
     * Get Detail Fund Transfer or Overbook
     * @param id transaction
     * @return detail transaction of fund transfer or overbook
     */
    public Optional<TransactionHistoryFTOverbookResponse> getDetailFTOverbook(Long id, String type) {
	return transactionHistoryDao.getDetailFundTransferOverbookHistory(id, type);
    }
    
    /**
     * Get Detail PLN Prepaid
     * @param id transaction
     * @return detail transaction of PLN prepaid
     */
    public Optional<TransactionHistoryPLNPrepaidResponse> getDetailPLNPrepaid(Long id){
	return transactionHistoryDao.getDetailPLNPrepaidHistory(id);
    }
    
    /**
     * Get Detail PLN Postpaid
     * @param id transaction
     * @return detail transaction of PLN postpaid
     */
    public Optional<TransactionHistoryPLNPostpaidResponse> getDetailPLNPostpaid(Long id){
	return transactionHistoryDao.getDetailPLNPostpaidHistory(id);
    }
    
    /**
     * Get Detail Telco Prepaid
     * @param id transaction
     * @return detail transaction of Telco prepaid
     */
    public Optional<TransactionHistoryTelcoPrepaidResponse> getDetailTelcoPrepaid(Long id){
	return transactionHistoryDao.getDetailTelcoPrepaidHistory(id);
    }
    
    /**
     * Get Detail Telco Postpaid
     * @param id transaction
     * @return detail transaction of Telco postpaid
     */
    public Optional<TransactionHistoryTelcoPostpaidResponse> getDetailTelcoPostpaid(Long id){
	return transactionHistoryDao.getDetailTelcoPostpaidHistory(id);
    }
    
    /**
     * Get Detail Emoney
     * @param id transaction
     * @return detail transaction of emoney
     */
    public Optional<TransactionHistoryEmoneyResponse> getDetailEmoney(Long id){
	return transactionHistoryDao.getDetailEmoneyHistory(id);
    }
    
    /**
     * Get Detail Insurance
     * @param id transaction
     * @return detail transaction of insurance
     */
    public Optional<TransactionHistoryInsuranceResponse> getDetailInsurance(Long id){
	return transactionHistoryDao.getDetailInsuranceHistory(id);
    }
    
    /**
     * Get Detail Samolnas
     * @param id transaction
     * @return detail transaction of samolnas
     */
    public Optional<TransactionHistorySamolnasResponse> getDetailSamolnas(Long id){
	return transactionHistoryDao.getDetailSamolnasHistory(id);
    }

    /**
     * Get Detail Credit Card
     * @param id transaction
     * @return detail transaction of Credit Card
     */
    public Optional<TransactionHistoryCreditCardResponse> getDetailCreditCard(Long id){
	return transactionHistoryDao.getDetailCreditCardHistory(id);
    }
    
    /**
     * Get Detail Telco Data Transaction
     * @param id transaction
     * @return detail transaction of telco data
     */
    public Optional<TransactionHistoryTelcoDataResponse> getDetailTelcoData(Long id){
    	return transactionHistoryDao.getDetailTelcoDataHistory(id);
    }
    
    /**
     * Generate Trx History Response with Paging
     * 
     * @param trxHistory Transaction History List
     * @param page Page to get
     * @param limit Limit data per page
     * @return Transaction history response order by create date descending
     */
    public PagingResponse generateTrxHistoryPagingResponse (
	    List<TransactionHistoryResponse> trxHistory, int page, int limit) {
	List<TransactionHistoryPagingResponse> respData = new ArrayList<TransactionHistoryPagingResponse>();
	int nData = trxHistory.size(); // size of all data
	
	if (page > 0) {
	    // SORTING by create date descending
	    trxHistory.sort(Comparator.comparing(TransactionHistoryResponse::getDateTime).reversed());

	    // PAGING
	    int offset = 0;
	    offset = (page - 1) * limit;
	    int limitData = (offset + limit) < nData ? (offset + limit) : nData; // data ke n to get
	    // current page data
	    List<TransactionHistoryResponse> currentPage = new ArrayList<>();
	    // Get current page data
	    for (int i = offset; i < limitData; i++) {
		currentPage.add(trxHistory.get(i));
	    }

	    // GROUPING By date (yyyy-mm-dd)
	    Map<String, List<TransactionHistoryResponse>> grouped = currentPage.stream()
		    .collect(Collectors.groupingBy(trx -> StringUtils.split(trx.getDateTime())[0]));

	    // RESPONSE data
	    grouped.forEach((trxDate, trxs) -> {
		respData.add(new TransactionHistoryPagingResponse(trxDate, trxs));
	    });
	    // Sort by create date descending
	    respData.sort(Comparator.comparing(TransactionHistoryPagingResponse::getDate).reversed());
	}
	
	// RESPONSE paging
	PagingResponse response = new PagingResponse();
	Paging paging = new Paging(page, limit, nData);
	response.setPaging(paging);
	response.setData(respData);
	
	return response;
    }
    
    /* Overrides: */}
