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
import java.util.Date;
import java.util.List;

/**
 * Field table content for Statement.pdf
 * 
 * @author Ihsan Firman
 * @version $Revision$, Jan 17, 2020
 * @since 2.0
 */
public class AccountStatementFieldTable {
    /* Constants: */

    /* Attributes: */
    String postingDate;
    String description;
    String reference;
    String amount;


    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */
    /**
     * Gets <code>postingDate</code>.
     * 
     * @return The <code>postingDate</code>.
     */
    public String getPostingDate() {
	return postingDate;
    }

    /**
     * Sets <code>postingDate</code>.
     * 
     * @param postingDate The <code>postingDate</code> to set.
     */
    public void setPostingDate(String postingDate) {
	this.postingDate = postingDate;
    }

    /**
     * Gets <code>description</code>.
     * 
     * @return The <code>description</code>.
     */
    public String getDescription() {
	return description;
    }

    /**
     * Sets <code>description</code>.
     * 
     * @param description The <code>description</code> to set.
     */
    public void setDescription(String description) {
	this.description = description;
    }

    /**
     * Gets <code>reference</code>.
     * 
     * @return The <code>reference</code>.
     */
    public String getReference() {
	return reference;
    }

    /**
     * Sets <code>reference</code>.
     * 
     * @param reference The <code>reference</code> to set.
     */
    public void setReference(String reference) {
	this.reference = reference;
    }

    /**
     * Gets <code>amount</code>.
     * 
     * @return The <code>amount</code>.
     */
    public String getAmount() {
	return amount;
    }

    /**
     * Sets <code>amount</code>.
     * 
     * @param amount The <code>amount</code> to set.
     */
    public void setAmount(String amount) {
	this.amount = amount;
    }
    

    /* Getters & setters for transient attributes: */

    /* Functionalities: */

    /* Overrides: */
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "AccountMutationFieldTable [postingDate=" + postingDate + ", description=" + description + ", reference="
		+ reference + ", amount=" + amount + "]";
    }

}
