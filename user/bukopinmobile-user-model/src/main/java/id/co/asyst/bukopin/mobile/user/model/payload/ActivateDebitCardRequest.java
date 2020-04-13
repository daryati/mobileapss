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

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Activate Debit Card Request Model
 * 
 * @author Abi Nanel
 * @version $Revision$, Nov 6, 2019
 * @since 2.0
 */
public class ActivateDebitCardRequest {
    /* Constants: */

    /* Attributes: */
    @NotBlank
    private String username;

    @NotBlank
    private String registeredCard;

    // @NotBlank(message = "birth date is required.")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @NotBlank
    private String validMonth;

    @NotBlank
    private String validYear;
    
    /**
     * Application Signature (hashed string need by frontend to autofill SMS OTP)
     */
    private String appSignature;

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */
    /**
     * Gets <code>username</code>.
     * 
     * @return The <code>username</code>.
     */
    public String getUsername() {
	return username;
    }

    /**
     * Sets <code>username</code>.
     * 
     * @param username
     *            The <code>username</code> to set.
     */
    public void setUsername(String username) {
	this.username = username;
    }

    /**
     * Gets <code>registeredCard</code>.
     * 
     * @return The <code>registeredCard</code>.
     */
    public String getRegisteredCard() {
	return registeredCard;
    }

    /**
     * Sets <code>registeredCard</code>.
     * 
     * @param registeredCard
     *            The <code>registeredCard</code> to set.
     */
    public void setRegisteredCard(String registeredCard) {
	this.registeredCard = registeredCard;
    }

    /**
     * Gets <code>birthDate</code>.
     * 
     * @return The <code>birthDate</code>.
     */
    public Date getBirthDate() {
	return birthDate;
    }

    /**
     * Sets <code>birthDate</code>.
     * 
     * @param birthDate
     *            The <code>birthDate</code> to set.
     */
    public void setBirthDate(Date birthDate) {
	this.birthDate = birthDate;
    }

    /**
     * Gets <code>validMonth</code>.
     * 
     * @return The <code>validMonth</code>.
     */
    public String getValidMonth() {
	return validMonth;
    }

    /**
     * Sets <code>validMonth</code>.
     * 
     * @param validMonth
     *            The <code>validMonth</code> to set.
     */
    public void setValidMonth(String validMonth) {
	this.validMonth = validMonth;
    }

    /**
     * Gets <code>validYear</code>.
     * 
     * @return The <code>validYear</code>.
     */
    public String getValidYear() {
	return validYear;
    }

    /**
     * Sets <code>validYear</code>.
     * 
     * @param validYear
     *            The <code>validYear</code> to set.
     */
    public void setValidYear(String validYear) {
	this.validYear = validYear;
    }
    
    /**
     * Gets <code>appSignature</code>.
     * @return The <code>appSignature</code>.
     */
    public String getAppSignature() {
        return appSignature;
    }

    /**
     * Sets <code>appSignature</code>.
     * @param appSignature The <code>appSignature</code> to set.
     */
    public void setAppSignature(String appSignature) {
        this.appSignature = appSignature;
    }

    /* Getters & setters for transient attributes: */

    /* Functionalities: */

    /* Overrides: */
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "ActivateDebitCardRequest [username=" + username + ", registeredCard=" + registeredCard + ", birthDate="
		+ birthDate + ", validMonth=" + validMonth + ", validYear=" + validYear + ", appSignature="
		+ appSignature + "]";
    }
}
