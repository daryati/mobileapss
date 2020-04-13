/*
 * $Id$
 * 
 * Copyright (c) 2020 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.service.model.payload.pln.postpaid;

/**
 * PLN Payment Data Request to Tibco
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Jan 16, 2020
 * @since 1.0.Alpha1-SNAPSHOT
 */
public class PostpaidDataResponse extends PostpaidDataRequest {
    /* Constants: */

    /* Attributes: */
    private String element39;
    
    private String element63;
    
    private String element93;
    
    private String element121;

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */
    /**
     * Gets <code>element39</code>.
     * @return The <code>element39</code>.
     */
    public String getElement39() {
        return element39;
    }
    /**
     * Sets <code>element39</code>.
     * @param element39 The <code>element39</code> to set.
     */
    public void setElement39(String element39) {
        this.element39 = element39;
    }
    /**
     * Gets <code>element63</code>.
     * @return The <code>element63</code>.
     */
    public String getElement63() {
        return element63;
    }
    /**
     * Sets <code>element63</code>.
     * @param element63 The <code>element63</code> to set.
     */
    public void setElement63(String element63) {
        this.element63 = element63;
    }
    /**
     * Gets <code>element121</code>.
     * @return The <code>element121</code>.
     */
    public String getElement121() {
        return element121;
    }
    /**
     * Sets <code>element121</code>.
     * @param element121 The <code>element121</code> to set.
     */
    public void setElement121(String element121) {
        this.element121 = element121;
    }
    
    /**
     * Gets <code>element93</code>.
     * @return The <code>element93</code>.
     */
    public String getElement93() {
        return element93;
    }
    /**
     * Sets <code>element93</code>.
     * @param element93 The <code>element93</code> to set.
     */
    public void setElement93(String element93) {
        this.element93 = element93;
    }

    /* Getters & setters for transient attributes: */

    /* Functionalities: */

    /* Overrides: */
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "PostpaidDataResponse [element39=" + element39 + ", element63=" + element63 + ", element93=" + element93
		+ ", element121=" + element121 + "]";
    }
}
