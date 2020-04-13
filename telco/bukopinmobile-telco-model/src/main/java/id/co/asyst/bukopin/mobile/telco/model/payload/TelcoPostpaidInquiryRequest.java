/*
 * $Id$
 * 
 * Copyright (c) 2020 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.telco.model.payload;

import javax.validation.constraints.NotBlank;

/**
 * 
 * 
 * @author Gibran Haq
 * @version $Revision$, Feb 13, 2020
 * @since 1.1.Alpha1-SNAPSHOT
 */
public class TelcoPostpaidInquiryRequest {
    /* Constants: */

    /* Attributes: */
    @NotBlank
    private String type;
    
    @NotBlank
    private String codeArra;
    
    @NotBlank
    private String codeCbs;
    
    @NotBlank
    private String custNo;
    
    @NotBlank
    private String productName;

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */


    /**
     * Gets <code>type</code>.
     * @return The <code>type</code>.
     */
    public String getType() {
        return type;
    }

    /**
     * Sets <code>type</code>.
     * @param type The <code>type</code> to set.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets <code>custNo</code>.
     * @return The <code>custNo</code>.
     */
    public String getCustNo() {
        return custNo;
    }

    /**
     * Sets <code>custNo</code>.
     * @param custNo The <code>custNo</code> to set.
     */
    public void setCustNo(String custNo) {
        this.custNo = custNo;
    }

    /* Getters & setters for transient attributes: */

    /* Functionalities: */

    /**
     * Gets <code>codeArra</code>.
     * @return The <code>codeArra</code>.
     */
    public String getCodeArra() {
        return codeArra;
    }

    /**
     * Sets <code>codeArra</code>.
     * @param codeArra The <code>codeArra</code> to set.
     */
    public void setCodeArra(String codeArra) {
        this.codeArra = codeArra;
    }

    /**
     * Gets <code>codeCbs</code>.
     * @return The <code>codeCbs</code>.
     */
    public String getCodeCbs() {
        return codeCbs;
    }

    /**
     * Sets <code>codeCbs</code>.
     * @param codeCbs The <code>codeCbs</code> to set.
     */
    public void setCodeCbs(String codeCbs) {
        this.codeCbs = codeCbs;
    }

    /**
     * Gets <code>productName</code>.
     * @return The <code>productName</code>.
     */
    public String getProductName() {
        return productName;
    }

    /**
     * Sets <code>productName</code>.
     * @param productName The <code>productName</code> to set.
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /* Overrides: */
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "TelcoPostpaidInquiryRequest [type=" + type + ", codeArra=" + codeArra + ", codeCbs=" + codeCbs
		+ ", custNo=" + custNo + ", productName=" + productName + "]";
    }
    
}
