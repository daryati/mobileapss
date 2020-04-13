/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.user.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import id.co.asyst.bukopin.mobile.user.model.OTPTypeEnum;
import id.co.asyst.foundation.common.model.IdBasedObject;

/**
 * OTP Entity Model
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Nov 1, 2019
 * @since 1.0.Alpha1-SNAPSHOT
 */
@Entity
@Table(name = "OTP")
public class OneTimePassword extends IdBasedObject {

    /**
     * Serial UID Version
     */
    private static final long serialVersionUID = 4313823039681963411L;
    /* Constants: */

    /* Attributes: */
    /**
     * Receiver
     */
    @Column(name = "RECEIVER", columnDefinition = "VARCHAR(50)", nullable = false)
    private String receiver;

    /**
     * Generated OTP
     */
    @Column(name = "OTP", columnDefinition = "VARCHAR(20)", nullable = false)
    private String otp;

    /**
     * OTP Type
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE", columnDefinition = "VARCHAR(10)", nullable = true)
    private OTPTypeEnum type; // SMS/ Email

    /**
     * Token Expired Time
     */
    @Column(name = "VALID_UNTIL", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date validUntil;

    /**
     * Token Create DateTime.
     */
    @Column(name = "CREATE_DATE", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */
    /**
     * Gets <code>receiver</code>.
     * @return The <code>receiver</code>.
     */
    public String getReceiver() {
        return receiver;
    }

    /**
     * Sets <code>receiver</code>.
     * @param receiver The <code>receiver</code> to set.
     */
    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

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
     * Gets <code>type</code>.
     * 
     * @return The <code>type</code>.
     */
    public OTPTypeEnum getType() {
	return type;
    }

    /**
     * Sets <code>type</code>.
     * 
     * @param type
     *            The <code>type</code> to set.
     */
    public void setType(OTPTypeEnum type) {
	this.type = type;
    }

    /**
     * Gets <code>validUntil</code>.
     * 
     * @return The <code>validUntil</code>.
     */
    public Date getValidUntil() {
	return validUntil;
    }

    /**
     * Sets <code>validUntil</code>.
     * 
     * @param validUntil
     *            The <code>validUntil</code> to set.
     */
    public void setValidUntil(Date validUntil) {
	this.validUntil = validUntil;
    }

    /**
     * Gets <code>createDate</code>.
     * 
     * @return The <code>createDate</code>.
     */
    public Date getCreateDate() {
	return createDate;
    }

    /**
     * Sets <code>createDate</code>.
     * 
     * @param createDate
     *            The <code>createDate</code> to set.
     */
    public void setCreateDate(Date createDate) {
	this.createDate = createDate;
    }

    /**
     * Gets <code>serialversionuid</code>.
     * 
     * @return The <code>serialversionuid</code>.
     */
    public static long getSerialversionuid() {
	return serialVersionUID;
    }

    /* Functionalities: */

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "OneTimePassword [receiver=" + receiver + ", otp=" + otp + ", type=" + type
		+ ", validUntil=" + validUntil + ", createDate=" + createDate + "]";
    }
}
