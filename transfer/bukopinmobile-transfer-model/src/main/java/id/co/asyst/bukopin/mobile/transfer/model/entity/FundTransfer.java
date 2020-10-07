/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.transfer.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import id.co.asyst.bukopin.mobile.user.model.entity.User;
import id.co.asyst.foundation.common.model.IdBasedObject;
import id.co.asyst.foundation.common.model.IdBasedObjectAclAware;

/**
 * Fund Transfer Entity Model
 * 
 * @author Kartika Dwi Handini
 * @version $Revision$, Nov 25, 2019
 * @since 2.0
 */
@Entity
@Table(name = "FUND_TRANSFER")
public class FundTransfer extends IdBasedObject implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /* Constants: */

    /* Attributes: */

    /**
     * Account number
     */
    @Column(name = "ACCOUNT_NUMBER")
    private String accountNumber;

    /**
     * amount
     */
    @Column(name = "AMOUNT")
    private BigDecimal amount;

    /**
     * admin fee
     */
    @Column(name = "ADMIN_FEE")
    private BigDecimal adminFee;
    
    
    /**
     * message
     */
    @Column(name = "MESSAGE")
    private String message;
    
    
    /**
     * created on
     */
    @Column(name = "CREATED_ON")
    private LocalDateTime createdOn;
    
    /**
     * method
     */
    @Column(name = "METHOD")
    private String method;
    
    /**
     * username
     */
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "USER_ID")
    private User username;
    
    /**
     * reference code
     */
    @Column(name = "REFERENCE_CODE")
    private String referenceCode;
    
    /**
     * receiver info
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "RECEIVER_INFO_ID")
    private ReceiverInfo receiverInfo;
    
    /**
     * Status eg. SUCCESS, FAILED
     */
    @Column(name = "STATUS")
    private String status;
    
    @Column(name = "NOTE_ID")
    private String noteId;
    
    @Column(name = "NOTE_EN")
    private String noteEn;
    
    @Column(name = "MENU", length = 50)
    private String menu;
    
    @Column(name = "REASON", length = 255)
    private String reason;

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */
    
    /**
     * Gets <code>id</code>.
     * 
     * @return The <code>id</code>.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets <code>id</code>.
     * 
     * @param id The <code>id</code> to set.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets <code>accountNo</code>.
     * 
     * @return The <code>accountNo</code>.
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * Sets <code>accountNo</code>.
     * 
     * @param accountNo The <code>accountNo</code> to set.
     */
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * Gets <code>amount</code>.
     * 
     * @return The <code>amount</code>.
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * Sets <code>amount</code>.
     * 
     * @param amount The <code>amount</code> to set.
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * Gets <code>adminFee</code>.
     * 
     * @return The <code>adminFee</code>.
     */
    public BigDecimal getAdminFee() {
        return adminFee;
    }

    /**
     * Sets <code>adminFee</code>.
     * 
     * @param adminFee The <code>adminFee</code> to set.
     */
    public void setAdminFee(BigDecimal adminFee) {
        this.adminFee = adminFee;
    }

    /**
     * Gets <code>message</code>.
     * 
     * @return The <code>message</code>.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets <code>message</code>.
     * 
     * @param message The <code>message</code> to set.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Gets <code>createdOn</code>.
     * 
     * @return The <code>createdOn</code>.
     */
    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    /**
     * Sets <code>createdOn</code>.
     * 
     * @param createdOn The <code>createdOn</code> to set.
     */
    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    /**
     * Gets <code>metode</code>.
     * 
     * @return The <code>metode</code>.
     */
    public String getMethod() {
        return method;
    }

    /**
     * Sets <code>metode</code>.
     * 
     * @param metode The <code>metode</code> to set.
     */
    public void setMethod(String method) {
        this.method = method;
    }

    /**
     * Gets <code>referenceCode</code>.
     * 
     * @return The <code>referenceCode</code>.
     */
    public String getReferenceCode() {
        return referenceCode;
    }

    /**
     * Sets <code>referenceCode</code>.
     * 
     * @param referenceCode The <code>referenceCode</code> to set.
     */
    public void setReferenceCode(String referenceCode) {
        this.referenceCode = referenceCode;
    }
    
    /**
     * Gets <code>status</code>.
     * 
     * @return The <code>status</code>.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets <code>status</code>.
     * 
     * @param status The <code>status</code> to set.
     */
    public void setStatus(String status) {
        this.status = status;
    }
   
    /**
     * Gets <code>username</code>.
     * 
     * @return The <code>username</code>.
     */
   public User getUsername() {
        return username;
    }

   /**
    * Sets <code>username</code>.
    * 
    * @param username The <code>username</code> to set.
    */
    public void setUsername(User username) {
        this.username = username;
    }

    /**
     * Gets <code>receiverInfo</code>.
     * 
     * @return The <code>receiverInfo</code>.
     */
    public ReceiverInfo getReceiverInfo() {
	return receiverInfo;
    }

    /**
     * Sets <code>receiverInfo</code>.
     * 
     * @param receiverInfo The <code>receiverInfo</code> to set.
     */
    public void setReceiverInfo(ReceiverInfo receiverInfo) {
	this.receiverInfo = receiverInfo;
    }

    /**
     * Gets <code>noteId</code>.
     * @return The <code>noteId</code>.
     */
    public String getNoteId() {
        return noteId;
    }

    /**
     * Sets <code>noteId</code>.
     * @param noteId The <code>noteId</code> to set.
     */
    public void setNoteId(String noteId) {
        this.noteId = noteId;
    }

    /**
     * Gets <code>noteEn</code>.
     * @return The <code>noteEn</code>.
     */
    public String getNoteEn() {
        return noteEn;
    }

    /**
     * Sets <code>noteEn</code>.
     * @param noteEn The <code>noteEn</code> to set.
     */
    public void setNoteEn(String noteEn) {
        this.noteEn = noteEn;
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

    /* Getters & setters for transient attributes: */

    /* Functionalities: */

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "FundTransfer [accountNumber=" + accountNumber + ", amount=" + amount + ", adminFee=" + adminFee
		+ ", message=" + message + ", createdOn=" + createdOn + ", method=" + method + ", username=" + username
		+ ", referenceCode=" + referenceCode + ", receiverInfo=" + receiverInfo + ", status=" + status
		+ ", noteId=" + noteId + ", noteEn=" + noteEn + ", menu=" + menu + ", reason=" + reason + "]";
    }
}
