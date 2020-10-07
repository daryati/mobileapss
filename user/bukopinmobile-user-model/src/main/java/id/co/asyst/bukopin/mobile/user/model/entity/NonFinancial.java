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
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import id.co.asyst.bukopin.mobile.user.model.CardStatusEnum;
import id.co.asyst.foundation.common.model.IdBasedObject;

/**
 * Account Card Entity Model
 * 
 * @author Kartika Dwi H
 * @version $Revision$, Jun 16, 2020
 * @since 1.3.Alpha1-SNAPSHOT
 */
@Entity
@Table(name="NON_FINANCIAL")
public class NonFinancial extends IdBasedObject implements Serializable {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 2671398255305575443L;
    
    /* Constants: */

    /* Attributes: */
    /**
     * Menu
     */
    @Column(name="MENU", length=50)
    private String menu;
    
    /**
     * Note
     */
    @Column(name="NOTE", length=255)
    private String note;
    
    /**
     * Created Date
     */
    @Column(name="CREATED_DATE")
    //@Temporal(TemporalType.DATE)
    private Date createdDate;
    
    /**
     * Status
     */
    @Column(name="STATUS", length=50)
    private String status;
    
    /**
     * Reason
     */
    @Column(name="REASON", length=255)
    private String reason;
    
        
    /**
     * User Foreign Key
     */
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="ID_USER")
    private User user;
    
    
    
    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */
    
    /**
     * Gets <code>registeredCard</code>.
     * @return The <code>registeredCard</code>.
     */
    public String getMenu() {
        return menu;
    }

    /**
     * Gets <code>registeredCard</code>.
     * @return The <code>registeredCard</code>.
     */
    public String getNote() {
		return note;
	}

    /**
     * Sets <code>registeredCard</code>.
     * @param registeredCard The <code>registeredCard</code> to set.
     */
	public void setNote(String note) {
		this.note = note;
	}

	/**
     * Sets <code>registeredCard</code>.
     * @param registeredCard The <code>registeredCard</code> to set.
     */
	public void setMenu(String menu) {
		this.menu = menu;
	}
    
	/**
     * Gets <code>registeredCard</code>.
     * @return The <code>registeredCard</code>.
     */
	public String getStatus() {
		return status;
	}

	/**
     * Sets <code>registeredCard</code>.
     * @param registeredCard The <code>registeredCard</code> to set.
     */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
     * Gets <code>registeredCard</code>.
     * @return The <code>registeredCard</code>.
     */
	public String getReason() {
		return reason;
	}

	/**
     * Sets <code>registeredCard</code>.
     * @param registeredCard The <code>registeredCard</code> to set.
     */
	public void setReason(String reason) {
		this.reason = reason;
	}
    
    
    
    /* Getters & setters for transient attributes: */

    /* Functionalities: */

    public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "NonFinancial [menu=" + menu + ", note=" + note + ", createdDate=" + createdDate + ", status=" + status
				+ ", reason=" + reason + ", user=" + user + "]";
	}
}
