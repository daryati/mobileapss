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

import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * 
 * 
 * @author Ihsan Firman
 * @version $Revision$, Feb 18, 2020
 * @since 2.0
 */
public class CodeArraCbsProductName {
    /* Constants: */

    /* Attributes: */
    @JsonProperty("codeArra")
    private String codeArra;
    
    @JsonProperty("codeCbs")
    private String codeCbs;
    
    @JsonProperty("productCode")
    private String productCode;

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */
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
     * Gets <code>productCode</code>.
     * @return The <code>productCode</code>.
     */
    public String getProductCode() {
        return productCode;
    }

    /**
     * Sets <code>productCode</code>.
     * @param productCode The <code>productCode</code> to set.
     */
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

 

    /* Getters & setters for transient attributes: */

    /* Functionalities: */

    /* Overrides: */
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "CodeArraCbsProductName [codeArra=" + codeArra + ", codeCbs=" + codeCbs + ", productCode=" + productCode
		+ "]";
    }
}
