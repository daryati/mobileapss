/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.purchase.model.payload;

import java.math.BigDecimal;

/**
 * 
 * 
 * @author Abi Nanel
 * @version $Revision$, Dec 9, 2019
 * @since 2.0
 */
public class PurchasePrepaidResponsePLN {
    /* Constants: */

    /* Attributes: */
    private String dateTime;

    private String reference;

    private String accountNo;

    private String meterSerialNumber;

    private String subscriberID;

    private String subscriberName;

    private String powerConsuming;

    private BigDecimal nominal;

    private BigDecimal adminCharge;

    private BigDecimal total;

    private String purchasedKWH;

    private String stroomToken;

    private String stampDuty;

    private String valueAddedTax;

    private String publicLightTax;

    private String customerPayableInstallment;

    private String destinationId;

    private String username;

    private String flag;
    
    private String footNote;

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */

    /**
     * Gets <code>flag</code>.
     * 
     * @return The <code>flag</code>.
     */
    public String getFlag() {
	return flag;
    }

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
     * Sets <code>flag</code>.
     * 
     * @param flag
     *            The <code>flag</code> to set.
     */
    public void setFlag(String flag) {
	this.flag = flag;
    }

    /**
     * Gets <code>destinationId</code>.
     * 
     * @return The <code>destinationId</code>.
     */
    public String getDestinationId() {
	return destinationId;
    }

    /**
     * Sets <code>destinationId</code>.
     * 
     * @param destinationId
     *            The <code>destinationId</code> to set.
     */
    public void setDestinationId(String destinationId) {
	this.destinationId = destinationId;
    }

    /**
     * Gets <code>dateTime</code>.
     * 
     * @return The <code>dateTime</code>.
     */
    public String getDateTime() {
	return dateTime;
    }

    /**
     * Gets <code>stampDuty</code>.
     * 
     * @return The <code>stampDuty</code>.
     */
    public String getStampDuty() {
	return stampDuty;
    }

    /**
     * Sets <code>stampDuty</code>.
     * 
     * @param stampDuty
     *            The <code>stampDuty</code> to set.
     */
    public void setStampDuty(String stampDuty) {
	this.stampDuty = stampDuty;
    }

    /**
     * Gets <code>valueAddedTax</code>.
     * 
     * @return The <code>valueAddedTax</code>.
     */
    public String getValueAddedTax() {
	return valueAddedTax;
    }

    /**
     * Sets <code>valueAddedTax</code>.
     * 
     * @param valueAddedTax
     *            The <code>valueAddedTax</code> to set.
     */
    public void setValueAddedTax(String valueAddedTax) {
	this.valueAddedTax = valueAddedTax;
    }

    /**
     * Gets <code>publicLightTax</code>.
     * 
     * @return The <code>publicLightTax</code>.
     */
    public String getPublicLightTax() {
	return publicLightTax;
    }

    /**
     * Sets <code>publicLightTax</code>.
     * 
     * @param publicLightTax
     *            The <code>publicLightTax</code> to set.
     */
    public void setPublicLightTax(String publicLightTax) {
	this.publicLightTax = publicLightTax;
    }

    /**
     * Gets <code>customerPayableInstallment</code>.
     * 
     * @return The <code>customerPayableInstallment</code>.
     */
    public String getCustomerPayableInstallment() {
	return customerPayableInstallment;
    }

    /**
     * Sets <code>customerPayableInstallment</code>.
     * 
     * @param customerPayableInstallment
     *            The <code>customerPayableInstallment</code> to set.
     */
    public void setCustomerPayableInstallment(String customerPayableInstallment) {
	this.customerPayableInstallment = customerPayableInstallment;
    }

    /**
     * Sets <code>dateTime</code>.
     * 
     * @param dateTime
     *            The <code>dateTime</code> to set.
     */
    public void setDateTime(String dateTime) {
	this.dateTime = dateTime;
    }

    /**
     * Gets <code>reference</code>.
     * 
     * @return The <code>reference</code>.
     */
    public String getReference() {
	return reference;
    }

    /**
     * Sets <code>reference</code>.
     * 
     * @param reference
     *            The <code>reference</code> to set.
     */
    public void setReference(String reference) {
	this.reference = reference;
    }

    /**
     * Gets <code>accountNo</code>.
     * 
     * @return The <code>accountNo</code>.
     */
    public String getAccountNo() {
	return accountNo;
    }

    /**
     * Sets <code>accountNo</code>.
     * 
     * @param accountNo
     *            The <code>accountNo</code> to set.
     */
    public void setAccountNo(String accountNo) {
	this.accountNo = accountNo;
    }

    /**
     * Gets <code>meterSerialNumber</code>.
     * 
     * @return The <code>meterSerialNumber</code>.
     */
    public String getMeterSerialNumber() {
	return meterSerialNumber;
    }

    /**
     * Sets <code>meterSerialNumber</code>.
     * 
     * @param meterSerialNumber
     *            The <code>meterSerialNumber</code> to set.
     */
    public void setMeterSerialNumber(String meterSerialNumber) {
	this.meterSerialNumber = meterSerialNumber;
    }

    /**
     * Gets <code>subscriberID</code>.
     * 
     * @return The <code>subscriberID</code>.
     */
    public String getSubscriberID() {
	return subscriberID;
    }

    /**
     * Sets <code>subscriberID</code>.
     * 
     * @param subscriberID
     *            The <code>subscriberID</code> to set.
     */
    public void setSubscriberID(String subscriberID) {
	this.subscriberID = subscriberID;
    }

    /**
     * Gets <code>subscriberName</code>.
     * 
     * @return The <code>subscriberName</code>.
     */
    public String getSubscriberName() {
	return subscriberName;
    }

    /**
     * Sets <code>subscriberName</code>.
     * 
     * @param subscriberName
     *            The <code>subscriberName</code> to set.
     */
    public void setSubscriberName(String subscriberName) {
	this.subscriberName = subscriberName;
    }

    /**
     * Gets <code>powerConsuming</code>.
     * 
     * @return The <code>powerConsuming</code>.
     */
    public String getPowerConsuming() {
	return powerConsuming;
    }

    /**
     * Sets <code>powerConsuming</code>.
     * 
     * @param powerConsuming
     *            The <code>powerConsuming</code> to set.
     */
    public void setPowerConsuming(String powerConsuming) {
	this.powerConsuming = powerConsuming;
    }

    /**
     * Gets <code>nominal</code>.
     * 
     * @return The <code>nominal</code>.
     */
    public BigDecimal getNominal() {
	return nominal;
    }

    /**
     * Sets <code>nominal</code>.
     * 
     * @param nominal
     *            The <code>nominal</code> to set.
     */
    public void setNominal(BigDecimal nominal) {
	this.nominal = nominal;
    }

    /**
     * Gets <code>adminCharge</code>.
     * 
     * @return The <code>adminCharge</code>.
     */
    public BigDecimal getAdminCharge() {
	return adminCharge;
    }

    /**
     * Sets <code>adminCharge</code>.
     * 
     * @param adminCharge
     *            The <code>adminCharge</code> to set.
     */
    public void setAdminCharge(BigDecimal adminCharge) {
	this.adminCharge = adminCharge;
    }

    /**
     * Gets <code>total</code>.
     * 
     * @return The <code>total</code>.
     */
    public BigDecimal getTotal() {
	return total;
    }

    /**
     * Sets <code>total</code>.
     * 
     * @param total
     *            The <code>total</code> to set.
     */
    public void setTotal(BigDecimal total) {
	this.total = total;
    }

    /**
     * Gets <code>purchasedKWH</code>.
     * 
     * @return The <code>purchasedKWH</code>.
     */
    public String getPurchasedKWH() {
	return purchasedKWH;
    }

    /**
     * Sets <code>purchasedKWH</code>.
     * 
     * @param purchasedKWH
     *            The <code>purchasedKWH</code> to set.
     */
    public void setPurchasedKWH(String purchasedKWH) {
	this.purchasedKWH = purchasedKWH;
    }

    /**
     * Gets <code>stroomToken</code>.
     * 
     * @return The <code>stroomToken</code>.
     */
    public String getStroomToken() {
	return stroomToken;
    }

    /**
     * Sets <code>stroomToken</code>.
     * 
     * @param stroomToken
     *            The <code>stroomToken</code> to set.
     */
    public void setStroomToken(String stroomToken) {
	this.stroomToken = stroomToken;
    }
    
    /**
     * Gets <code>footNote</code>.
     * @return The <code>footNote</code>.
     */
    public String getFootNote() {
        return footNote;
    }

    /**
     * Sets <code>footNote</code>.
     * @param footNote The <code>footNote</code> to set.
     */
    public void setFootNote(String footNote) {
        this.footNote = footNote;
    }

    /* Getters & setters for transient attributes: */

    /* Functionalities: */
    
    /* Overrides: */
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "PurchasePrepaidResponsePLN [dateTime=" + dateTime + ", reference=" + reference + ", AccountNo="
		+ accountNo + ", meterSerialNumber=" + meterSerialNumber + ", subscriberID=" + subscriberID
		+ ", subscriberName=" + subscriberName + ", powerConsuming=" + powerConsuming + ", nominal=" + nominal
		+ ", adminCharge=" + adminCharge + ", total=" + total + ", purchasedKWH=" + purchasedKWH
		+ ", stroomToken=" + stroomToken + ", stampDuty=" + stampDuty + ", valueAddedTax=" + valueAddedTax
		+ ", publicLightTax=" + publicLightTax + ", customerPayableInstallment=" + customerPayableInstallment
		+ ", destinationId=" + destinationId + ", username=" + username + ", flag=" + flag + ", footNote="
		+ footNote + "]";
    }

}
