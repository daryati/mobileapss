/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.master.model.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import id.co.asyst.bukopin.mobile.user.model.entity.User;
import id.co.asyst.foundation.base.model.IdBasedObject;

/**
 * Transaction Model
 * 
 * @author Abi Nanel
 * @version $Revision$, Dec 11, 2019
 * @since 1.0.Alpha1
 */
@Entity
@Table(name = "TRX")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Transaction extends IdBasedObject {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /* Constants: */

    /* Attributes: */
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "ID_USER", referencedColumnName = "id")
    private User user;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "ID_DESTINATION", referencedColumnName = "id")
    private Destination destination;

    @Column(name = "CREATED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column(name = "REFERENCE_NUMBER", length = 100)
    private String refNumber;

    @Column(name = "TYPE", length = 20)
    private String type;
    
    @Column(name = "ACCOUNT_NUMBER", length = 50)
    private String accountNumber;
    
    @Column(name = "TOTAL_AMOUNT")
    private BigDecimal totalAmount;
    
    @Column(name = "NOTE_ID", length = 255)
    private String noteId;
    
    @Column(name = "NOTE_EN", length = 255)
    private String noteEn;
    
    @Column(name = "BILLER_PRODUCT", length = 100)
    private String billerProduct;
    
    @Column(name = "MENU", length = 50)
    private String menu;
    
    @Column(name = "STATUS", length = 50)
    private String status;
    
    @Column(name = "REASON", length = 255)
    private String reason;

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */
    /**
     * Gets <code>user</code>.
     * 
     * @return The <code>user</code>.
     */
    public User getUser() {
	return user;
    }

    /**
     * Gets <code>type</code>.
     * 
     * @return The <code>type</code>.
     */
    public String getType() {
	return type;
    }

    /**
     * Sets <code>type</code>.
     * 
     * @param type
     *            The <code>type</code> to set.
     */
    public void setType(String type) {
	this.type = type;
    }

    /**
     * Sets <code>user</code>.
     * 
     * @param user
     *            The <code>user</code> to set.
     */
    public void setUser(User user) {
	this.user = user;
    }

    /**
     * Gets <code>destination</code>.
     * 
     * @return The <code>destination</code>.
     */
    public Destination getDestination() {
	return destination;
    }

    /**
     * Sets <code>destination</code>.
     * 
     * @param destination
     *            The <code>destination</code> to set.
     */
    public void setDestination(Destination destination) {
	this.destination = destination;
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
     * Gets <code>refNumber</code>.
     * 
     * @return The <code>refNumber</code>.
     */
    public String getRefNumber() {
	return refNumber;
    }

    /**
     * Sets <code>refNumber</code>.
     * 
     * @param refNumber
     *            The <code>refNumber</code> to set.
     */
    public void setRefNumber(String refNumber) {
	this.refNumber = refNumber;
    }

    
    /* Getters & setters for transient attributes: */

    /* Functionalities: */

    public String getAccountNumber() {
	return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
	this.accountNumber = accountNumber;
    }

    public BigDecimal getTotalAmount() {
	return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
	this.totalAmount = totalAmount;
    }

    /**
     * @return the noteId
     */
    public String getNoteId() {
	return noteId;
    }

    /**
     * @param noteId the noteId to set
     */
    public void setNoteId(String noteId) {
        this.noteId = noteId;
    }

    /**
     * @return the noteEn
     */
    public String getNoteEn() {
        return noteEn;
    }

    /**
     * @param noteEn the noteEn to set
     */
    public void setNoteEn(String noteEn) {
        this.noteEn = noteEn;
    }
    
    /**
     * Gets <code>billerProduct</code>.
     * @return The <code>billerProduct</code>.
     */
    public String getBillerProduct() {
        return billerProduct;
    }

    /**
     * Sets <code>billerProduct</code>.
     * @param billerProduct The <code>billerProduct</code> to set.
     */
    public void setBillerProduct(String billerProduct) {
        this.billerProduct = billerProduct;
    }

    /**
     * Gets <code>menu</code>.
     * @return The <code>menu</code>.
     */
    public String getMenu() {
        return menu;
    }

    /**
     * Sets <code>menu</code>.
     * @param menu The <code>menu</code> to set.
     */
    public void setMenu(String menu) {
        this.menu = menu;
    }

    /**
     * Gets <code>status</code>.
     * @return The <code>status</code>.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets <code>status</code>.
     * @param status The <code>status</code> to set.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Gets <code>reason</code>.
     * @return The <code>reason</code>.
     */
    public String getReason() {
        return reason;
    }

    /**
     * Sets <code>reason</code>.
     * @param reason The <code>reason</code> to set.
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "Transaction [user=" + user + ", destination=" + destination + ", createdDate=" + createdDate
		+ ", refNumber=" + refNumber + ", type=" + type + ", accountNumber=" + accountNumber + ", totalAmount="
		+ totalAmount + ", noteId=" + noteId + ", noteEn=" + noteEn + ", billerProduct=" + billerProduct
		+ ", menu=" + menu + ", status=" + status + ", reason=" + reason + "]";
    }
}
