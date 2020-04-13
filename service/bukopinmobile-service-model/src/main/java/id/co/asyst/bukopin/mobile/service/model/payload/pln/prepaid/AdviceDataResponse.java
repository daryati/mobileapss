/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.service.model.payload.pln.prepaid;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 
 * 
 * @author Abi Nanel
 * @version $Revision$, Dec 17, 2019
 * @since 1.0.Alpha1-SNAPSHOT
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class AdviceDataResponse extends AdviceDataRequest {
    /* Constants: */

    /* Attributes: */
    private String element39;

    private String element61;

    private String element63;
    
    private String element44;

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */
    /**
     * Gets <code>element39</code>.
     * 
     * @return The <code>element39</code>.
     */
    public String getElement39() {
	return element39;
    }

    /**
     * Sets <code>element39</code>.
     * 
     * @param element39
     *            The <code>element39</code> to set.
     */
    public void setElement39(String element39) {
	this.element39 = element39;
    }

    /**
     * Gets <code>element61</code>.
     * 
     * @return The <code>element61</code>.
     */
    public String getElement61() {
	return element61;
    }

    /**
     * Sets <code>element61</code>.
     * 
     * @param element61
     *            The <code>element61</code> to set.
     */
    public void setElement61(String element61) {
	this.element61 = element61;
    }

    /**
     * Gets <code>element63</code>.
     * 
     * @return The <code>element63</code>.
     */
    public String getElement63() {
	return element63;
    }

    /**
     * Sets <code>element63</code>.
     * 
     * @param element63
     *            The <code>element63</code> to set.
     */
    public void setElement63(String element63) {
	this.element63 = element63;
    }
    
    /**
     * Gets <code>element44</code>.
     * @return The <code>element44</code>.
     */
    public String getElement44() {
        return element44;
    }

    /**
     * Sets <code>element44</code>.
     * @param element44 The <code>element44</code> to set.
     */
    public void setElement44(String element44) {
        this.element44 = element44;
    }

    /* Getters & setters for transient attributes: */

    /* Functionalities: */

    /* Overrides: */
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "AdviceDataResponse [element39=" + element39 + ", element61=" + element61 + ", element63=" + element63
		+ ", element44=" + element44 + "]";
    }
}
