/*
 * $Id$
 * 
 * Copyright (c) 2020 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.payment.model.payload.cc;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * 
 * @author Ihsan Firman
 * @version $Revision$, Apr 2, 2020
 * @since 2.0
 */
public class InquiryCreditCardRequest {
    /* Constants: */

    /* Attributes: */
    @NotBlank
    @JsonProperty("username")
    private String username;
    
    @NotBlank
    @JsonProperty("codeCc")
    private String codeCc;
    
    @NotBlank
    @JsonProperty("name")
    private String name;
    
    @NotBlank
    @JsonProperty("subscriberNumber")
    private String subscriberNumber;

    /**
     * Gets <code>username</code>.
     * @return The <code>username</code>.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets <code>username</code>.
     * @param username The <code>username</code> to set.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets <code>codeCc</code>.
     * @return The <code>codeCc</code>.
     */
    public String getCodeCc() {
        return codeCc;
    }

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */
    /**
     * Sets <code>codeCc</code>.
     * @param codeCc The <code>codeCc</code> to set.
     */
    public void setCodeCc(String codeCc) {
        this.codeCc = codeCc;
    }

    /**
     * Gets <code>name</code>.
     * @return The <code>name</code>.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets <code>name</code>.
     * @param name The <code>name</code> to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets <code>subscriberNumber</code>.
     * @return The <code>subscriberNumber</code>.
     */
    public String getSubscriberNumber() {
        return subscriberNumber;
    }

    /**
     * Sets <code>subscriberNumber</code>.
     * @param subscriberNumber The <code>subscriberNumber</code> to set.
     */
    public void setSubscriberNumber(String subscriberNumber) {
        this.subscriberNumber = subscriberNumber;
    }



    /* Getters & setters for transient attributes: */

    /* Functionalities: */

    /* Overrides: */
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "InquiryCreditCardDataRequest [username=" + username + ", codeCc=" + codeCc + ", name=" + name
		+ ", subscriberNumber=" + subscriberNumber + "]";
    }
}
