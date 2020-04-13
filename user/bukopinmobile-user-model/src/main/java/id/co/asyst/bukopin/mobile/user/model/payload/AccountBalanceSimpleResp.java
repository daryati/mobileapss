/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.user.model.payload;

import java.math.BigDecimal;


/**
 * 
 * 
 * @author Gibran Haq
 * @version $Revision$, Dec 4, 2019
 * @since 2.0
 */
public class AccountBalanceSimpleResp {
    /* Constants: */

    /* Attributes: */
    private String productID;

    private BigDecimal effectiveBalance;

    private BigDecimal holdAmount;

    private BigDecimal availableBalance;

    /**
     * Gets <code>productID</code>.
     * @return The <code>productID</code>.
     */
    public String getProductID() {
        return productID;
    }

    /**
     * Sets <code>productID</code>.
     * @param productID The <code>productID</code> to set.
     */
    public void setProductID(String productID) {
        this.productID = productID;
    }

    /**
     * Gets <code>effectiveBalance</code>.
     * @return The <code>effectiveBalance</code>.
     */
    public BigDecimal getEffectiveBalance() {
        return effectiveBalance;
    }

    /**
     * Sets <code>effectiveBalance</code>.
     * @param effectiveBalance The <code>effectiveBalance</code> to set.
     */
    public void setEffectiveBalance(BigDecimal effectiveBalance) {
        this.effectiveBalance = effectiveBalance;
    }

    /**
     * Gets <code>holdAmount</code>.
     * @return The <code>holdAmount</code>.
     */
    public BigDecimal getHoldAmount() {
        return holdAmount;
    }

    /**
     * Sets <code>holdAmount</code>.
     * @param holdAmount The <code>holdAmount</code> to set.
     */
    public void setHoldAmount(BigDecimal holdAmount) {
        this.holdAmount = holdAmount;
    }

    /**
     * Gets <code>availableBalance</code>.
     * @return The <code>availableBalance</code>.
     */
    public BigDecimal getAvailableBalance() {
        return availableBalance;
    }

    /**
     * Sets <code>availableBalance</code>.
     * @param availableBalance The <code>availableBalance</code> to set.
     */
    public void setAvailableBalance(BigDecimal availableBalance) {
        this.availableBalance = availableBalance;
    }

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */

    /* Getters & setters for transient attributes: */

    /* Functionalities: */

    /* Overrides: */}
