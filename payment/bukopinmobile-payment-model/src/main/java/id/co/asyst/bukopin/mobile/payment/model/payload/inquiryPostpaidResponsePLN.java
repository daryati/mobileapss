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
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 
 * 
 * @author Gibran Haq
 * @version $Revision$, Jan 15, 2020
 * @since 1.0.Alpha1-SNAPSHOT
 */
@JsonInclude(Include.NON_NULL)
public class inquiryPostpaidResponsePLN {
    /* Constants: */

    /* Attributes: */
    private String subscriberID;

    private String subscriberName;

    private int billStatus;

    private Map<String, String> billPeriod;

    private String tarif;

    private String daya;

    private BigDecimal amount;

    private BigDecimal adminFee;

    private BigDecimal penaltyFee;

    private BigDecimal totalAmount;

    private String prevMeter;

    private String currMeter;

    private String element11;

    private String element48;
    
    private String element61;
    
    private int remainBill;

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */
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
     * @param subscriberID The <code>subscriberID</code> to set.
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
     * @param subscriberName The <code>subscriberName</code> to set.
     */
    public void setSubscriberName(String subscriberName) {
	this.subscriberName = subscriberName;
    }

    /**
     * Gets <code>billStatus</code>.
     * @return The <code>billStatus</code>.
     */
    public int getBillStatus() {
        return billStatus;
    }

    /**
     * Sets <code>billStatus</code>.
     * @param billStatus The <code>billStatus</code> to set.
     */
    public void setBillStatus(int billStatus) {
        this.billStatus = billStatus;
    }

    /**
     * Gets <code>billPeriod</code>.
     * 
     * @return The <code>billPeriod</code>.
     */
    public Map<String, String> getBillPeriod() {
	return billPeriod;
    }

    /**
     * Sets <code>billPeriod</code>.
     * 
     * @param billPeriod The <code>billPeriod</code> to set.
     */
    public void setBillPeriod(Map<String, String> billPeriod) {
	this.billPeriod = billPeriod;
    }

    /**
     * Gets <code>tarif</code>.
     * 
     * @return The <code>tarif</code>.
     */
    public String getTarif() {
	return tarif;
    }

    /**
     * Sets <code>tarif</code>.
     * 
     * @param tarif The <code>tarif</code> to set.
     */
    public void setTarif(String tarif) {
	this.tarif = tarif;
    }

    /**
     * Gets <code>daya</code>.
     * 
     * @return The <code>daya</code>.
     */
    public String getDaya() {
	return daya;
    }

    /**
     * Sets <code>daya</code>.
     * 
     * @param daya The <code>daya</code> to set.
     */
    public void setDaya(String daya) {
	this.daya = daya;
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
     * Gets <code>penaltyFee</code>.
     * 
     * @return The <code>penaltyFee</code>.
     */
    public BigDecimal getPenaltyFee() {
	return penaltyFee;
    }

    /**
     * Sets <code>penaltyFee</code>.
     * 
     * @param penaltyFee The <code>penaltyFee</code> to set.
     */
    public void setPenaltyFee(BigDecimal penaltyFee) {
	this.penaltyFee = penaltyFee;
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
     * Gets <code>prevMeter</code>.
     * 
     * @return The <code>prevMeter</code>.
     */
    public String getPrevMeter() {
	return prevMeter;
    }

    /**
     * Sets <code>prevMeter</code>.
     * 
     * @param prevMeter The <code>prevMeter</code> to set.
     */
    public void setPrevMeter(String prevMeter) {
	this.prevMeter = prevMeter;
    }

    /**
     * Gets <code>currMeter</code>.
     * 
     * @return The <code>currMeter</code>.
     */
    public String getCurrMeter() {
	return currMeter;
    }

    /**
     * Sets <code>currMeter</code>.
     * 
     * @param currMeter The <code>currMeter</code> to set.
     */
    public void setCurrMeter(String currMeter) {
	this.currMeter = currMeter;
    }

    /**
     * Gets <code>element11</code>.
     * @return The <code>element11</code>.
     */
    public String getElement11() {
        return element11;
    }

    /**
     * Sets <code>element11</code>.
     * @param element11 The <code>element11</code> to set.
     */
    public void setElement11(String element11) {
        this.element11 = element11;
    }

    /**
     * Gets <code>element48</code>.
     * @return The <code>element48</code>.
     */
    public String getElement48() {
        return element48;
    }

    /**
     * Sets <code>element48</code>.
     * @param element48 The <code>element48</code> to set.
     */
    public void setElement48(String element48) {
        this.element48 = element48;
    }

    /**
     * Gets <code>element61</code>.
     * @return The <code>element61</code>.
     */
    public String getElement61() {
        return element61;
    }

    /**
     * Sets <code>element61</code>.
     * @param element61 The <code>element61</code> to set.
     */
    public void setElement61(String element61) {
        this.element61 = element61;
    }

    /**
     * Gets <code>remainBill</code>.
     * @return The <code>remainBill</code>.
     */
    public int getRemainBill() {
        return remainBill;
    }

    /**
     * Sets <code>remainBill</code>.
     * @param remainBill The <code>remainBill</code> to set.
     */
    public void setRemainBill(int remainBill) {
        this.remainBill = remainBill;
    }

    /* Getters & setters for transient attributes: */

    /* Functionalities: */

    /* Overrides: */}
