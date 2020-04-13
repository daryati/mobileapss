/*
 * $Id$
 * 
 * Copyright (c) 2020 Aero Systems Indonesia, PT.
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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import id.co.asyst.foundation.common.model.IdBasedObject;

/**
 * Customer Login Model
 * 
 * @author Abi Nanel
 * @version $Revision$, Mar 3, 2020
 * @since 1.1.Alpha1
 */
@Entity
@Table(name = "CUSTOMER_LOGIN")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerLogin extends IdBasedObject implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /* Constants: */

    /* Attributes: */
    @Column(name = "USERNAME", nullable = false, length = 50)
    private String username;

    @Column(name = "LOGIN_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date loginAt;

    @Column(name = "LOGOUT_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date logoutAt;

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
     * Gets <code>loginAt</code>.
     * 
     * @return The <code>loginAt</code>.
     */
    public Date getLoginAt() {
	return loginAt;
    }

    /**
     * Sets <code>loginAt</code>.
     * 
     * @param loginAt
     *            The <code>loginAt</code> to set.
     */
    public void setLoginAt(Date loginAt) {
	this.loginAt = loginAt;
    }

    /**
     * Gets <code>logoutAt</code>.
     * 
     * @return The <code>logoutAt</code>.
     */
    public Date getLogoutAt() {
	return logoutAt;
    }

    /**
     * Sets <code>logoutAt</code>.
     * 
     * @param logoutAt
     *            The <code>logoutAt</code> to set.
     */
    public void setLogoutAt(Date logoutAt) {
	this.logoutAt = logoutAt;
    }

    /* Getters & setters for transient attributes: */

    /* Functionalities: */

    /* Overrides: */
    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "CustomerLogin [username=" + username + ", loginAt=" + loginAt + ", logoutAt=" + logoutAt + "]";
    }
}
