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

/**
 * 
 * 
 * @author Ihsan Firman
 * @version $Revision$, Jan 20, 2020
 * @since 2.0
 */
public class MutationTableHeader {
    /* Constants: */

    /* Attributes: */
    String headDate;
    String headDesc;
    String headRef;
    String headAmount;

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */
    /**
     * Gets <code>headDate</code>.
     * @return The <code>headDate</code>.
     */
    public String getHeadDate() {
        return headDate;
    }

    /**
     * Sets <code>headDate</code>.
     * @param headDate The <code>headDate</code> to set.
     */
    public void setHeadDate(String headDate) {
        this.headDate = headDate;
    }

    /**
     * Gets <code>headDesc</code>.
     * @return The <code>headDesc</code>.
     */
    public String getHeadDesc() {
        return headDesc;
    }

    /**
     * Sets <code>headDesc</code>.
     * @param headDesc The <code>headDesc</code> to set.
     */
    public void setHeadDesc(String headDesc) {
        this.headDesc = headDesc;
    }

    /**
     * Gets <code>headReff</code>.
     * @return The <code>headReff</code>.
     */
    public String getHeadRef() {
        return headRef;
    }

    /**
     * Sets <code>headReff</code>.
     * @param headReff The <code>headReff</code> to set.
     */
    public void setHeadRef(String headRef) {
        this.headRef = headRef;
    }

    /**
     * Gets <code>headAmount</code>.
     * @return The <code>headAmount</code>.
     */
    public String getHeadAmount() {
        return headAmount;
    }

    /**
     * Sets <code>headAmount</code>.
     * @param headAmount The <code>headAmount</code> to set.
     */
    public void setHeadAmount(String headAmount) {
        this.headAmount = headAmount;
    }

    
    /* Getters & setters for transient attributes: */

    /* Functionalities: */

    /* Overrides: */
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "MutationTableHeader [headDate=" + headDate + ", headDesc=" + headDesc + ", headRef=" + headRef
		+ ", headAmount=" + headAmount + "]";
    }

}
