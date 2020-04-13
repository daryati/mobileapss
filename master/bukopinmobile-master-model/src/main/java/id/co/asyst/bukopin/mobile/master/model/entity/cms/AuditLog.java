/*
 * $Id$
 * 
 * Copyright (c) 2020 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.master.model.entity.cms;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import id.co.asyst.foundation.base.model.IdBasedObject;

/**
 * Audit Log Model
 * 
 * @author Abi Nanel
 * @version $Revision$, Mar 13, 2020
 * @since 1.1.Alpha1
 */
@Entity
@Table(name = "AUDIT_LOG")
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuditLog extends IdBasedObject {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /* Constants: */

    /* Attributes: */
    @Column(name = "USERNAME", length = 50)
    private String username;

    @Column(name = "CREATED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column(name = "DESCRIPTION", length = 255)
    private String description;

    @Column(name = "MENU", length = 50)
    private String menu;

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
     * Gets <code>description</code>.
     * 
     * @return The <code>description</code>.
     */
    public String getDescription() {
	return description;
    }

    /**
     * Sets <code>description</code>.
     * 
     * @param description
     *            The <code>description</code> to set.
     */
    public void setDescription(String description) {
	this.description = description;
    }

    /**
     * Gets <code>menu</code>.
     * 
     * @return The <code>menu</code>.
     */
    public String getMenu() {
	return menu;
    }

    /**
     * Sets <code>menu</code>.
     * 
     * @param menu
     *            The <code>menu</code> to set.
     */
    public void setMenu(String menu) {
	this.menu = menu;
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
	return "AuditLog [username=" + username + ", createdDate=" + createdDate + ", description=" + description
		+ ", menu=" + menu + "]";
    }
}
