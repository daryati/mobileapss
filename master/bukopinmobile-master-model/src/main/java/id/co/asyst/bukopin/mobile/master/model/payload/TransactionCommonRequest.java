/*
 * $Id$
 * 
 * Copyright (c) 2020 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
/*
 * Copyright (c) 2020 Lufthansa Systems Indonesia, PT. All rights reserved.
 */
package id.co.asyst.bukopin.mobile.master.model.payload;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonProperty;

import id.co.asyst.bukopin.mobile.master.model.entity.Destination;
import id.co.asyst.bukopin.mobile.user.model.entity.User;

/**
 * 
 * 
 * @author Ihsan Firman
 * @version $Revision$, Jul 22, 2020
 * @since 2.0
 */
public class TransactionCommonRequest {
	/* Constants: */

	/* Attributes: */
	@JsonProperty("username")
    private String username;

    @JsonProperty("type")
    private String type;
    
    @JsonProperty("accountNumber")
    private String accountNumber;
    
	@JsonProperty("referenceNumber")
	private String referenceNumber;
	
	@JsonProperty("totalAmount")
	private BigDecimal totalAmount;
	
	@JsonProperty("destinationId")
	private String destinationId;
	
	@JsonProperty("billerProduct")
    private String billerProduct;
    
    @JsonProperty("menu")
    private String menu;
    
    @JsonProperty("status")
    private String status;
    
    @JsonProperty("reason")
    private String reason;
    
    @JsonProperty("noteEn")
    private String noteEn;

    @JsonProperty("noteId")
    private String noteId;
    
    @JsonProperty("adminFee")
	private BigDecimal adminFee;
    
    @JsonProperty("amount")
	private BigDecimal amount;
	/* Transient Attributes: */

	/* Constructors: */

	/* Getters & setters for attributes: */
	/**
	 * Gets <code>referenceNumber</code>.
	 * @return The <code>referenceNumber</code>.
	 */
	public String getReferenceNumber() {
		return referenceNumber;
	}

	/**
	 * Sets <code>referenceNumber</code>.
	 * @param referenceNumber The <code>referenceNumber</code> to set.
	 */
	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	/**
	 * Gets <code>destinationId</code>.
	 * @return The <code>destinationId</code>.
	 */
	public String getDestinationId() {
		return destinationId;
	}

	/**
	 * Sets <code>destinationId</code>.
	 * @param destinationId The <code>destinationId</code> to set.
	 */
	public void setDestinationId(String destinationId) {
		this.destinationId = destinationId;
	}


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
	 * Gets <code>type</code>.
	 * @return The <code>type</code>.
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets <code>type</code>.
	 * @param type The <code>type</code> to set.
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Gets <code>accountNumber</code>.
	 * @return The <code>accountNumber</code>.
	 */
	public String getAccountNumber() {
		return accountNumber;
	}

	/**
	 * Sets <code>accountNumber</code>.
	 * @param accountNumber The <code>accountNumber</code> to set.
	 */
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
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
	 * Gets <code>totalAmount</code>.
	 * @return The <code>totalAmount</code>.
	 */
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	/**
	 * Sets <code>totalAmount</code>.
	 * @param totalAmount The <code>totalAmount</code> to set.
	 */
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public BigDecimal getAdminFee() {
		return adminFee;
	}

	public void setAdminFee(BigDecimal adminFee) {
		this.adminFee = adminFee;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	

	/* Getters & setters for transient attributes: */

	/* Functionalities: */

	

	@Override
	public String toString() {
		return "TransactionCommonRequest [username=" + username + ", type=" + type + ", accountNumber=" + accountNumber
				+ ", referenceNumber=" + referenceNumber + ", totalAmount=" + totalAmount + ", destinationId="
				+ destinationId + ", billerProduct=" + billerProduct + ", menu=" + menu + ", status=" + status
				+ ", reason=" + reason + ", noteEn=" + noteEn + ", noteId=" + noteId + ", adminFee=" + adminFee
				+ ", amount=" + amount + "]";
	}
	


}
