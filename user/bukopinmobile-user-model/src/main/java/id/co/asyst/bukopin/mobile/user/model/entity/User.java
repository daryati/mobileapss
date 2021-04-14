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

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import id.co.asyst.foundation.common.model.IdBasedObject;

/**
 * User Model
 * 
 * @author Gibran Haq
 * @version $Revision$, Oct 30, 2019
 * @since 1.0.Alpha1
 */
@Entity
@Table(name = "APP_USER")
@JsonIgnoreProperties(ignoreUnknown=true)
public class User extends IdBasedObject implements Serializable {
    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -5462934747797851696L;

    /* Constants: */
    /**
     * Username maximum length (value <code>{@value}</code>).
     */
    @Transient
    private static final int USERNAME_MAX_LENGTH = 32;

    /* Attributes: */
    /**
     * Username.
     */
    @Column(name = "USERNAME", unique = true, nullable = false, length = USERNAME_MAX_LENGTH)
    private String username;

    @Column(name = "FIRST_NAME", nullable = false, length = 50)
    private String firstName;

    @Column(name = "MIDDLE_NAME", length = 50)
    private String middleName;

    @Column(name = "LAST_NAME", length = 50)
    private String lastName;

    /**
     * User email
     */
    @Column(name = "EMAIL", nullable = false)
    private String email;

    /**
     * User Mobile phone
     */
    @Column(name = "MOBILE_PHONE", nullable = false)
    private String mobilePhone;

    /**
     * User CIF number
     */
    @Column(name = "CIF_NUMBER")
    private String cifNumber;

    /**
     * User Created date and time
     */
    @Column(name = "CREATED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    /**
     * Created by
     */
    @Column(name = "CREATED_BY")
    private String createdBy;

    /**
     * User Updated date and time
     */
    @Column(name = "UPDATED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;

    /**
     * Updated by
     */
    @Column(name = "UPDATED_BY")
    private String updatedBy;
    
    @Type(type = "yes_no")
    @Column(name="ACTIVATION", columnDefinition="CHAR(1) DEFAULT 'N'")
    private boolean activation;
    
    /**
     * Status locked user
     */
    @Type(type = "yes_no")
    @Column(name="IS_LOCKED", columnDefinition="CHAR(1) DEFAULT 'N'")
    private boolean isLocked;
    
    /**
     * Phone Identity
     */
    @Column(name="PHONE_IDENTITY")
    private String phoneIdentity;
    
    /**
     * Last reset password
     */
    @Column(name="LAST_RESET_PASSWORD")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastResetPassword;
    
    /**
     * Status login user
     */
    @Type(type = "yes_no")
    @Column(name="IS_LOGIN", columnDefinition="CHAR(1) DEFAULT 'N'")
    private boolean isLogin;
    
    /**
     * Id in table LIMIT_PACKAGE
     */
    @Column(name="LIMIT_ID")
    private Long limitId;

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
     * Gets <code>email</code>.
     * 
     * @return The <code>email</code>.
     */
    public String getEmail() {
	return email;
    }

    /**
     * Sets <code>email</code>.
     * 
     * @param email
     *            The <code>email</code> to set.
     */
    public void setEmail(String email) {
	this.email = email;
    }

    /**
     * Gets <code>mobilePhone</code>.
     * 
     * @return The <code>mobilePhone</code>.
     */
    public String getMobilePhone() {
	return mobilePhone;
    }

    /**
     * Sets <code>mobilePhone</code>.
     * 
     * @param mobilePhone
     *            The <code>mobilePhone</code> to set.
     */
    public void setMobilePhone(String mobilePhone) {
	this.mobilePhone = mobilePhone;
    }

    /**
     * Gets <code>cifNumber</code>.
     * 
     * @return The <code>cifNumber</code>.
     */
    public String getCifNumber() {
	return cifNumber;
    }

    /**
     * Sets <code>cifNumber</code>.
     * 
     * @param cifNumber
     *            The <code>cifNumber</code> to set.
     */
    public void setCifNumber(String cifNumber) {
	this.cifNumber = cifNumber;
    }

    /**
     * Gets <code>createdDate</code>.
     * 
     * @return The <code>createdDate</code>.
     */
    public Date getCreatedDate() {
	return createdDate;
    }

    /**
     * Sets <code>createdDate</code>.
     * 
     * @param createdDate
     *            The <code>createdDate</code> to set.
     */
    public void setCreatedDate(Date createdDate) {
	this.createdDate = createdDate;
    }

    /**
     * Gets <code>createdBy</code>.
     * 
     * @return The <code>createdBy</code>.
     */
    public String getCreatedBy() {
	return createdBy;
    }

    /**
     * Sets <code>createdBy</code>.
     * 
     * @param createdBy
     *            The <code>createdBy</code> to set.
     */
    public void setCreatedBy(String createdBy) {
	this.createdBy = createdBy;
    }

    /**
     * Gets <code>updatedDate</code>.
     * 
     * @return The <code>updatedDate</code>.
     */
    public Date getUpdatedDate() {
	return updatedDate;
    }

    /**
     * Sets <code>updatedDate</code>.
     * 
     * @param updatedDate
     *            The <code>updatedDate</code> to set.
     */
    public void setUpdatedDate(Date updatedDate) {
	this.updatedDate = updatedDate;
    }

    /**
     * Gets <code>updatedBy</code>.
     * 
     * @return The <code>updatedBy</code>.
     */
    public String getUpdatedBy() {
	return updatedBy;
    }

    /**
     * Sets <code>updatedBy</code>.
     * 
     * @param updatedBy
     *            The <code>updatedBy</code> to set.
     */
    public void setUpdatedBy(String updatedBy) {
	this.updatedBy = updatedBy;
    }

    /**
     * Gets <code>firstName</code>.
     * @return The <code>firstName</code>.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets <code>firstName</code>.
     * @param firstName The <code>firstName</code> to set.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets <code>middleName</code>.
     * @return The <code>middleName</code>.
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * Sets <code>middleName</code>.
     * @param middleName The <code>middleName</code> to set.
     */
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    /**
     * Gets <code>lastName</code>.
     * @return The <code>lastName</code>.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets <code>lastName</code>.
     * @param lastName The <code>lastName</code> to set.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets <code>activation</code>.
     * @return The <code>activation</code>.
     */
    public boolean isActivation() {
        return activation;
    }

    /**
     * Sets <code>activation</code>.
     * @param activation The <code>activation</code> to set.
     */
    public void setActivation(boolean activation) {
        this.activation = activation;
    }
    
    /**
     * Gets <code>isLocked</code>.
     * @return The <code>isLocked</code>.
     */
    public boolean isLocked() {
        return isLocked;
    }

    /**
     * Sets <code>isLocked</code>.
     * @param isLocked The <code>isLocked</code> to set.
     */
    public void setLocked(boolean isLocked) {
        this.isLocked = isLocked;
    }

    /**
     * Gets <code>phoneIdentity</code>.
     * @return The <code>phoneIdentity</code>.
     */
    public String getPhoneIdentity() {
        return phoneIdentity;
    }

    /**
     * Sets <code>phoneIdentity</code>.
     * @param phoneIdentity The <code>phoneIdentity</code> to set.
     */
    public void setPhoneIdentity(String phoneIdentity) {
        this.phoneIdentity = phoneIdentity;
    }
    
    /**
     * Gets <code>lastResetPassword</code>.
     * @return The <code>lastResetPassword</code>.
     */
    public Date getLastResetPassword() {
        return lastResetPassword;
    }

    /**
     * Sets <code>lastResetPassword</code>.
     * @param lastResetPassword The <code>lastResetPassword</code> to set.
     */
    public void setLastResetPassword(Date lastResetPassword) {
        this.lastResetPassword = lastResetPassword;
    }
    
    /**
     * Gets <code>isLogin</code>.
     * @return The <code>isLogin</code>.
     */
    public boolean isLogin() {
        return isLogin;
    }

    /**
     * Sets <code>isLogin</code>.
     * @param isLogin The <code>isLogin</code> to set.
     */
    public void setLogin(boolean isLogin) {
        this.isLogin = isLogin;
    }
    
    /**
     * Gets <code>limitId</code>.
     * @return The <code>limitId</code>.
     */
    public Long getLimitId() {
        return limitId;
    }

    /**
     * Sets <code>limitId</code>.
     * @param limitId The <code>limitId</code> to set.
     */
    public void setLimitId(Long limitId) {
        this.limitId = limitId;
    }

    /* Getters & setters for transient attributes: */

    /* Functionalities: */

    /* Overrides: */
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "User [username=" + username + ", firstName=" + firstName + ", middleName=" + middleName + ", lastName="
		+ lastName + ", email=" + email + ", mobilePhone=" + mobilePhone + ", cifNumber=" + cifNumber
		+ ", createdDate=" + createdDate + ", createdBy=" + createdBy + ", updatedDate=" + updatedDate
		+ ", updatedBy=" + updatedBy + ", activation=" + activation + ", isLocked=" + isLocked
		+ ", phoneIdentity=" + phoneIdentity + ", lastResetPassword=" + lastResetPassword 
		+ ", isLogin=" + isLogin + ", limitId=" + limitId
		+ "]";
    }
}
