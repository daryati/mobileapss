/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.user.model.entity.internal;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.SqlResultSetMapping;

/**
 * Debit Card Custom Model (Data Transfer Object)
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Nov 5, 2019
 * @since 1.0.Alpha1-SNAPSHOT
 */
//@Entity
//@SqlResultSetMapping(
//	name="debitResult", 
//	classes= {
//		@ConstructorResult(
//			targetClass=DebitCardResult.class,
//			columns= {
//				@ColumnResult(name = "cardNumber"),
//				@ColumnResult(name="cardStatus"),
//				@ColumnResult(name="expiredDate"),
//				@ColumnResult(name="cif")
//			})
//	}
//	)
public class DebitCardInfo {
    /* Constants: */

    /* Attributes: */
    
    /**
     * NOMOR_KARTU 
     */
    private String cardNumber;
    
    /**
     * STATUS_KARTU
     */
    private String cardStatus;
    
    /**
     * EXPIRED_DATE
     */
    private String expiredDate;
    
    /**
     * CIF
     */
    private String cif;
    
    /**
     * ACCOUNT_NUMBER
     */
    private String accountNumber;


    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */
    /**
     * Gets <code>cardNumber</code>.
     * @return The <code>cardNumber</code>.
     */
    public String getCardNumber() {
        return cardNumber;
    }

    /**
     * Sets <code>cardNumber</code>.
     * @param cardNumber The <code>cardNumber</code> to set.
     */
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    /**
     * Gets <code>cardStatus</code>.
     * @return The <code>cardStatus</code>.
     */
    public String getCardStatus() {
        return cardStatus;
    }

    /**
     * Sets <code>cardStatus</code>.
     * @param cardStatus The <code>cardStatus</code> to set.
     */
    public void setCardStatus(String cardStatus) {
        this.cardStatus = cardStatus;
    }

    /**
     * Gets <code>expiredDate</code>.
     * @return The <code>expiredDate</code>.
     */
    public String getExpiredDate() {
        return expiredDate;
    }

    /**
     * Sets <code>expiredDate</code>.
     * @param expiredDate The <code>expiredDate</code> to set.
     */
    public void setExpiredDate(String expiredDate) {
        this.expiredDate = expiredDate;
    }

    /**
     * Gets <code>cif</code>.
     * @return The <code>cif</code>.
     */
    public String getCif() {
        return cif;
    }

    /**
     * Sets <code>cif</code>.
     * @param cif The <code>cif</code> to set.
     */
    public void setCif(String cif) {
        this.cif = cif;
    }

    /**
     * Gets <code>accountNumber</code>.
     * @return The <code>accountNumber</code>.
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * Sets <code>accountNumber</code>.
     * @param accountNumber The <code>accountNumber</code> to set.
     */
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    /* Getters & setters for transient attributes: */

    /* Functionalities: */

    /* Overrides: */
}
