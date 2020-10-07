/*
 * $Id$
 * 
 * Copyright (c) 2020 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
/*
 * Copyright (c) 2020 Lufthansa Systems Indonesia, PT. All rights reserved.
 */
package id.co.asyst.bukopin.mobile.transfer.model.payload;

import java.io.Serializable;
import java.util.List;

/**
 * Transaction History Response with Pagination
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Sep 17, 2020
 * @since 1.5.Alpha1
 */
public class TransactionHistoryPagingResponse implements Serializable {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 2082112576090874070L;
    
    /* Constants: */

    /* Attributes: */
    /**
     * Transaction Date
     */
    private String date;
    /**
     * Detail of Transaction History
     */
    private List<TransactionHistoryResponse> detail;
    
    
    /* Constructors: */
    public TransactionHistoryPagingResponse(String date, List<TransactionHistoryResponse> detail) {
	this.date = date;
	this.detail = detail;
    }
    

    /* Getters & setters for attributes: */
    /**
     * Gets <code>date</code>.
     * @return The <code>date</code>.
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets <code>date</code>.
     * @param date The <code>date</code> to set.
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Gets <code>detail</code>.
     * @return The <code>detail</code>.
     */
    public List<TransactionHistoryResponse> getDetail() {
        return detail;
    }

    /**
     * Sets <code>detail</code>.
     * @param detail The <code>detail</code> to set.
     */
    public void setDetail(List<TransactionHistoryResponse> detail) {
        this.detail = detail;
    }

    /* Functionalities: */

    /* Overrides: */
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "TransactionHistoryPagingResponse [date=" + date + ", detail=" + detail + "]";
    }
}
