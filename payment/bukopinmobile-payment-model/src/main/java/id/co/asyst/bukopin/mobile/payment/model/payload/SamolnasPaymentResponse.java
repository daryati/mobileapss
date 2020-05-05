/*
 * $Id$
 * 
 * Copyright (c) 2020 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.payment.model.payload;

import java.math.BigDecimal;

/**
 * 
 * 
 * @author Gibran Haq
 * @version $Revision$, Apr 28, 2020
 * @since 1.2.Alpha1
 */
public class SamolnasPaymentResponse {
    /* Constants: */

    /* Attributes: */
    private String date;

    private String time;

    private String referenceNumber;

    private String accountNumber;

    private String payCode;

    private String subscriberName;

    private String nik;

    private String npkb;

    private String machineNo;

    private String tbpkb;

    private String newTbpkb;

    private String validityPeriod;

    private BigDecimal amount;

    private BigDecimal adminFee;

    private BigDecimal totalAmount;

    private String merk;

    private String type;

    private String model;

    private String progresif;

    private String year;

    private long idDestination;

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */

    /**
     * Gets <code>date</code>.
     * 
     * @return The <code>date</code>.
     */
    public String getDate() {
	return date;
    }

    /**
     * Sets <code>date</code>.
     * 
     * @param date The <code>date</code> to set.
     */
    public void setDate(String date) {
	this.date = date;
    }

    /**
     * Gets <code>time</code>.
     * 
     * @return The <code>time</code>.
     */
    public String getTime() {
	return time;
    }

    /**
     * Sets <code>time</code>.
     * 
     * @param time The <code>time</code> to set.
     */
    public void setTime(String time) {
	this.time = time;
    }

    /**
     * Gets <code>referenceNumber</code>.
     * 
     * @return The <code>referenceNumber</code>.
     */
    public String getReferenceNumber() {
	return referenceNumber;
    }

    /**
     * Sets <code>referenceNumber</code>.
     * 
     * @param referenceNumber The <code>referenceNumber</code> to set.
     */
    public void setReferenceNumber(String referenceNumber) {
	this.referenceNumber = referenceNumber;
    }

    /**
     * Gets <code>accountNumber</code>.
     * 
     * @return The <code>accountNumber</code>.
     */
    public String getAccountNumber() {
	return accountNumber;
    }

    /**
     * Sets <code>accountNumber</code>.
     * 
     * @param accountNumber The <code>accountNumber</code> to set.
     */
    public void setAccountNumber(String accountNumber) {
	this.accountNumber = accountNumber;
    }

    /**
     * Gets <code>payCode</code>.
     * 
     * @return The <code>payCode</code>.
     */
    public String getPayCode() {
	return payCode;
    }

    /**
     * Sets <code>payCode</code>.
     * 
     * @param payCode The <code>payCode</code> to set.
     */
    public void setPayCode(String payCode) {
	this.payCode = payCode;
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
     * @param subscriberName The <code>subscriberName</code> to set.
     */
    public void setSubscriberName(String subscriberName) {
	this.subscriberName = subscriberName;
    }

    /**
     * Gets <code>nik</code>.
     * 
     * @return The <code>nik</code>.
     */
    public String getNik() {
	return nik;
    }

    /**
     * Sets <code>nik</code>.
     * 
     * @param nik The <code>nik</code> to set.
     */
    public void setNik(String nik) {
	this.nik = nik;
    }

    /**
     * Gets <code>npkb</code>.
     * 
     * @return The <code>npkb</code>.
     */
    public String getNpkb() {
	return npkb;
    }

    /**
     * Sets <code>npkb</code>.
     * 
     * @param npkb The <code>npkb</code> to set.
     */
    public void setNpkb(String npkb) {
	this.npkb = npkb;
    }

    /**
     * Gets <code>machineNo</code>.
     * 
     * @return The <code>machineNo</code>.
     */
    public String getMachineNo() {
	return machineNo;
    }

    /**
     * Sets <code>machineNo</code>.
     * 
     * @param machineNo The <code>machineNo</code> to set.
     */
    public void setMachineNo(String machineNo) {
	this.machineNo = machineNo;
    }

    /**
     * Gets <code>tbpkb</code>.
     * 
     * @return The <code>tbpkb</code>.
     */
    public String getTbpkb() {
	return tbpkb;
    }

    /**
     * Sets <code>tbpkb</code>.
     * 
     * @param tbpkb The <code>tbpkb</code> to set.
     */
    public void setTbpkb(String tbpkb) {
	this.tbpkb = tbpkb;
    }

    /**
     * Gets <code>newTbpkb</code>.
     * 
     * @return The <code>newTbpkb</code>.
     */
    public String getNewTbpkb() {
	return newTbpkb;
    }

    /**
     * Sets <code>newTbpkb</code>.
     * 
     * @param newTbpkb The <code>newTbpkb</code> to set.
     */
    public void setNewTbpkb(String newTbpkb) {
	this.newTbpkb = newTbpkb;
    }

    /**
     * Gets <code>validityPeriod</code>.
     * 
     * @return The <code>validityPeriod</code>.
     */
    public String getValidityPeriod() {
	return validityPeriod;
    }

    /**
     * Sets <code>validityPeriod</code>.
     * 
     * @param validityPeriod The <code>validityPeriod</code> to set.
     */
    public void setValidityPeriod(String validityPeriod) {
	this.validityPeriod = validityPeriod;
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
     * Gets <code>totalAmount</code>.
     * 
     * @return The <code>totalAmount</code>.
     */
    public BigDecimal getTotalAmount() {
	return totalAmount;
    }

    /**
     * Sets <code>totalAmount</code>.
     * 
     * @param totalAmount The <code>totalAmount</code> to set.
     */
    public void setTotalAmount(BigDecimal totalAmount) {
	this.totalAmount = totalAmount;
    }

    /**
     * Gets <code>merk</code>.
     * 
     * @return The <code>merk</code>.
     */
    public String getMerk() {
	return merk;
    }

    /**
     * Sets <code>merk</code>.
     * 
     * @param merk The <code>merk</code> to set.
     */
    public void setMerk(String merk) {
	this.merk = merk;
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
     * @param type The <code>type</code> to set.
     */
    public void setType(String type) {
	this.type = type;
    }

    /**
     * Gets <code>model</code>.
     * 
     * @return The <code>model</code>.
     */
    public String getModel() {
	return model;
    }

    /**
     * Sets <code>model</code>.
     * 
     * @param model The <code>model</code> to set.
     */
    public void setModel(String model) {
	this.model = model;
    }

    /**
     * Gets <code>progresif</code>.
     * 
     * @return The <code>progresif</code>.
     */
    public String getProgresif() {
	return progresif;
    }

    /**
     * Sets <code>progresif</code>.
     * 
     * @param progresif The <code>progresif</code> to set.
     */
    public void setProgresif(String progresif) {
	this.progresif = progresif;
    }

    /**
     * Gets <code>year</code>.
     * 
     * @return The <code>year</code>.
     */
    public String getYear() {
	return year;
    }

    /**
     * Sets <code>year</code>.
     * 
     * @param year The <code>year</code> to set.
     */
    public void setYear(String year) {
	this.year = year;
    }

    /**
     * Gets <code>idDestination</code>.
     * 
     * @return The <code>idDestination</code>.
     */
    public long getIdDestination() {
	return idDestination;
    }

    /**
     * Sets <code>idDestination</code>.
     * 
     * @param idDestination The <code>idDestination</code> to set.
     */
    public void setIdDestination(long idDestination) {
	this.idDestination = idDestination;
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
	return "SamolnasPaymentResponse [date=" + date + ", time=" + time + ", referenceNumber=" + referenceNumber
		+ ", accountNumber=" + accountNumber + ", payCode=" + payCode + ", subscriberName=" + subscriberName
		+ ", nik=" + nik + ", npkb=" + npkb + ", machineNo=" + machineNo + ", tbpkb=" + tbpkb + ", newTbpkb="
		+ newTbpkb + ", validityPeriod=" + validityPeriod + ", amount=" + amount + ", adminFee=" + adminFee
		+ ", totalAmount=" + totalAmount + ", merk=" + merk + ", type=" + type + ", model=" + model
		+ ", progresif=" + progresif + ", year=" + year + ", idDestination=" + idDestination + "]";
    }

}
