/*
 * $Id$
 * 
 * Copyright (c) 2020 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.user.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Model for field Statements.pdf
 * 
 * @author Ihsan Firman
 * @version $Revision$, Jan 15, 2020
 * @since 2.0
 */
public class DownloadAccountStatement {
    /* Constants: */

    /* Attributes: */
    private String vOwner;
    private String vAccountNo;
    private String vCurrency;
    private String vTo;
    private String vFrom;
    private Date vIssuedDate;
    List<AccountStatementFieldTable> accountMutationFieldTable;
    
        

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */
    /**
     * Gets <code>vOwner</code>.
     * @return The <code>vOwner</code>.
     */
    public String getvOwner() {
        return vOwner;
    }
    /**
     * Sets <code>vOwner</code>.
     * @param vOwner The <code>vOwner</code> to set.
     */
    public void setvOwner(String vOwner) {
        this.vOwner = vOwner;
    }
    /**
     * Gets <code>vAccountNo</code>.
     * @return The <code>vAccountNo</code>.
     */
    public String getvAccountNo() {
        return vAccountNo;
    }
    /**
     * Sets <code>vAccountNo</code>.
     * @param vAccountNo The <code>vAccountNo</code> to set.
     */
    public void setvAccountNo(String vAccountNo) {
        this.vAccountNo = vAccountNo;
    }
    /**
     * Gets <code>vCurrency</code>.
     * @return The <code>vCurrency</code>.
     */
    public String getvCurrency() {
        return vCurrency;
    }
    /**
     * Sets <code>vCurrency</code>.
     * @param vCurrency The <code>vCurrency</code> to set.
     */
    public void setvCurrency(String vCurrency) {
        this.vCurrency = vCurrency;
    }
   
    /**
     * Gets <code>vTo</code>.
     * @return The <code>vTo</code>.
     */
    public String getvTo() {
        return vTo;
    }
    /**
     * Sets <code>vTo</code>.
     * @param vTo The <code>vTo</code> to set.
     */
    public void setvTo(String vTo) {
        this.vTo = vTo;
    }
    /**
     * Gets <code>vFrom</code>.
     * @return The <code>vFrom</code>.
     */
    public String getvFrom() {
        return vFrom;
    }
    /**
     * Sets <code>vFrom</code>.
     * @param vFrom The <code>vFrom</code> to set.
     */
    public void setvFrom(String vFrom) {
        this.vFrom = vFrom;
    }
    /**
     * Gets <code>vIssuedDate</code>.
     * @return The <code>vIssuedDate</code>.
     */
    public Date getvIssuedDate() {
        return vIssuedDate;
    }
    /**
     * Sets <code>vIssuedDate</code>.
     * @param vIssuedDate The <code>vIssuedDate</code> to set.
     */
    public void setvIssuedDate(Date vIssuedDate) {
        this.vIssuedDate = vIssuedDate;
    }
    /**
     * Gets <code>accountMutationFieldTable</code>.
     * @return The <code>accountMutationFieldTable</code>.
     */
    public List<AccountStatementFieldTable> getAccountMutationFieldTable() {
        return accountMutationFieldTable;
    }
    /**
     * Sets <code>accountMutationFieldTable</code>.
     * @param accountMutationFieldTable The <code>accountMutationFieldTable</code> to set.
     */
    public void setAccountMutationFieldTable(List<AccountStatementFieldTable> accountMutationFieldTable) {
        this.accountMutationFieldTable = accountMutationFieldTable;
    }
    /* Getters & setters for transient attributes: */

    /* Functionalities: */

    /* Overrides: */
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "AccountMutationModel [vOwner=" + vOwner + ", vAccountNo=" + vAccountNo + ", vCurrency=" + vCurrency
		+ ", vTo=" + vTo + ", vFrom=" + vFrom + ", vIssuedDate=" + vIssuedDate + ", accountMutationFieldTable="
		+ accountMutationFieldTable + "]";
    }
 
}
