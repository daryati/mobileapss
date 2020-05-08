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

import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.co.asyst.bukopin.mobile.transfer.core.dao.TransactionHistoryDao;
import id.co.asyst.bukopin.mobile.transfer.model.payload.TransactionHistoryCreditCardResponse;
import id.co.asyst.bukopin.mobile.transfer.model.payload.TransactionHistoryEmoneyResponse;
import id.co.asyst.bukopin.mobile.transfer.model.payload.TransactionHistoryFTOverbookResponse;
import id.co.asyst.bukopin.mobile.transfer.model.payload.TransactionHistoryInsuranceResponse;
import id.co.asyst.bukopin.mobile.transfer.model.payload.TransactionHistoryPLNPostpaidResponse;
import id.co.asyst.bukopin.mobile.transfer.model.payload.TransactionHistoryPLNPrepaidResponse;
import id.co.asyst.bukopin.mobile.transfer.model.payload.TransactionHistoryResponse;
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
     * Get Detail Credit Card
     * @param id transaction
     * @return detail transaction of Credit Card
     */
    public Optional<TransactionHistoryCreditCardResponse> getDetailCreditCard(Long id){
	return transactionHistoryDao.getDetailCreditCardHistory(id);
    }
    /* Overrides: */}
