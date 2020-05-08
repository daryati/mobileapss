/*
 * $Id$
 * 
 * Copyright (c) 2020 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.transfer.core.dao;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

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
public interface TransactionHistoryDao {
    
    /**
     * Get All Transaction History
     * @param id User's ID
     * @param loc Language
     * @param db database use (postgre or ms sql)
     * @return All transaction history in 3 months ago
     */
    public List<TransactionHistoryResponse> getAllTransactionHistory(Long id, Locale loc, String db);
    
    /**
     * Get Detail Fund Transfer or Overbook History
     * @param id
     * @return
     */
    public Optional<TransactionHistoryFTOverbookResponse> getDetailFundTransferOverbookHistory(Long id, String type);
    
    /**
     * Get Detail PLN Prepaid History
     * @param id
     * @return
     */
    public Optional<TransactionHistoryPLNPrepaidResponse> getDetailPLNPrepaidHistory(Long id);
    
    /**
     * Get Detail PLN Postpaid History
     * @param id
     * @return
     */
    public Optional<TransactionHistoryPLNPostpaidResponse> getDetailPLNPostpaidHistory(Long id);
    
    /**
     * Get Detail Telco Prepaid History
     * @param id
     * @return
     */
    public Optional<TransactionHistoryTelcoPrepaidResponse> getDetailTelcoPrepaidHistory(Long id);
    
    /**
     * Get Detail Telco Postpaid History
     * @param id
     * @return
     */
    public Optional<TransactionHistoryTelcoPostpaidResponse> getDetailTelcoPostpaidHistory(Long id);
    
    /**
     * Get Detail Emoney History
     * @param id
     * @return
     */
    public Optional<TransactionHistoryEmoneyResponse> getDetailEmoneyHistory(Long id);
    
    /**
     * Get Detail Insurance History
     * @param id
     * @return
     */
    public Optional<TransactionHistoryInsuranceResponse> getDetailInsuranceHistory(Long id);
    
    /**
     * Get Detail Credit Card History
     * @param id
     * @return
     */
    public Optional<TransactionHistoryCreditCardResponse> getDetailCreditCardHistory(Long id);

}
