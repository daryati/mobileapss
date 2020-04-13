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

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * Account Activation Model Entity
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Nov 7, 2019
 * @since 1.0.Alpha1-SNAPSHOT
 */
public class AccountVerificationRequest {
    /* Constants: */

    /* Attributes: */

    /**
     * OTP
     */
    @NotBlank(message = "OTP is required.")
    private String otp;

    /**
     * OTP receiver, it should be mail address or phone number.
     */
    @NotBlank(message = "Receiver is required.")
    private String receiver;

    /**
     * Account Activation Request
     */
    @Valid
    private ActivateDebitCardRequest debitCard;

    /* Constructors: */

    /* Getters & setters for attributes: */
    /**
     * Gets <code>otp</code>.
     * 
     * @return The <code>otp</code>.
     */
    public String getOtp() {
	return otp;
    }

    /**
     * Sets <code>otp</code>.
     * 
     * @param otp
     *            The <code>otp</code> to set.
     */
    public void setOtp(String otp) {
	this.otp = otp;
    }

    /**
     * Gets <code>receiver</code>.
     * 
     * @return The <code>receiver</code>.
     */
    public String getReceiver() {
	return receiver;
    }

    /**
     * Sets <code>receiver</code>.
     * 
     * @param receiver
     *            The <code>receiver</code> to set.
     */
    public void setReceiver(String receiver) {
	this.receiver = receiver;
    }

    /**
     * Gets <code>debitCard</code>.
     * 
     * @return The <code>debitCard</code>.
     */
    public ActivateDebitCardRequest getDebitCard() {
	return debitCard;
    }

    /**
     * Sets <code>debitCard</code>.
     * 
     * @param debitCard
     *            The <code>debitCard</code> to set.
     */
    public void setDebitCard(ActivateDebitCardRequest debitCard) {
	this.debitCard = debitCard;
    }

    /* Functionalities: */

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "AccountVerificationRequest [otp=" + otp + ", receiver=" + receiver + ", debitCard=" + debitCard + "]";
    }
}
