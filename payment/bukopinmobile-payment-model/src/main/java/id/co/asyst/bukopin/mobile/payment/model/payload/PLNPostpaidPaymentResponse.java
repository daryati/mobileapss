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

/**
 * PLN Postpaid Payment Response
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Jan 16, 2020
 * @since 1.0.Alpha1-SNAPSHOT
 */
public class PLNPostpaidPaymentResponse {
    /* Constants: */

    /* Attributes: */
    /**
     * Account Number
     */
    private String accountNo;
    
    /**
     * Subscriber Number
     */
    private String subscriberNumber;
    
    /**
     * Subscriber Name
     */
    private String subscriberName;
    
    /**
     * Payment Status
     */
    private int paymentStatus;
    
    /**
     * Bill Status
     */
    private int billStatus;
    
    /**
     * Bill Perios
     */
    private Map<String, String> billPeriod;
    
    /**
     * Reference Number
     */
    private String referenceNumber;
    
    /**
     * Tarif
     */
    private String tarif;
    
    /**
     * Daya
     */
    private String daya;
    
    /**
     * Amount
     */
    private BigDecimal amount;
    
    /**
     * Admin Fee
     */
    private BigDecimal adminFee;
    
    /**
     * Penalty Fee
     */
    private BigDecimal penaltyFee;
    
    /**
     * Total Amount
     */
    private BigDecimal totalAmount;
    
    /**
     * N months of remain bill
     */
    private int remainBill;
    
    /**
     * Info
     */
    private String info;
    
    /**
     * Previous Meter
     */
    private String prevMeter;
    
    /**
     * Current Meter
     */
    private String currMeter;
    
    /**
     * Date Time Transaction 
     */
    private String dateTime;
    
    /**
     * Destination Id from table Destination
     */
    private long destinationId;


    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */
    /**
     * Gets <code>subscriberNumber</code>.
     * @return The <code>subscriberNumber</code>.
     */
    public String getSubscriberNumber() {
        return subscriberNumber;
    }

    /**
     * Sets <code>subscriberNumber</code>.
     * @param subscriberNumber The <code>subscriberNumber</code> to set.
     */
    public void setSubscriberNumber(String subscriberNumber) {
        this.subscriberNumber = subscriberNumber;
    }

    /**
     * Gets <code>subscriberName</code>.
     * @return The <code>subscriberName</code>.
     */
    public String getSubscriberName() {
        return subscriberName;
    }

    /**
     * Sets <code>subscriberName</code>.
     * @param subscriberName The <code>subscriberName</code> to set.
     */
    public void setSubscriberName(String subscriberName) {
        this.subscriberName = subscriberName;
    }

    /**
     * Gets <code>paymentStatus</code>.
     * @return The <code>paymentStatus</code>.
     */
    public int getPaymentStatus() {
        return paymentStatus;
    }

    /**
     * Sets <code>paymentStatus</code>.
     * @param paymentStatus The <code>paymentStatus</code> to set.
     */
    public void setPaymentStatus(int paymentStatus) {
        this.paymentStatus = paymentStatus;
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
     * @return The <code>billPeriod</code>.
     */
    public Map<String, String> getBillPeriod() {
        return billPeriod;
    }

    /**
     * Sets <code>billPeriod</code>.
     * @param billPeriod The <code>billPeriod</code> to set.
     */
    public void setBillPeriod(Map<String, String> billPeriod) {
        this.billPeriod = billPeriod;
    }

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
     * Gets <code>tarif</code>.
     * @return The <code>tarif</code>.
     */
    public String getTarif() {
        return tarif;
    }

    /**
     * Sets <code>tarif</code>.
     * @param tarif The <code>tarif</code> to set.
     */
    public void setTarif(String tarif) {
        this.tarif = tarif;
    }

    /**
     * Gets <code>daya</code>.
     * @return The <code>daya</code>.
     */
    public String getDaya() {
        return daya;
    }

    /**
     * Sets <code>daya</code>.
     * @param daya The <code>daya</code> to set.
     */
    public void setDaya(String daya) {
        this.daya = daya;
    }

    /**
     * Gets <code>amount</code>.
     * @return The <code>amount</code>.
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * Sets <code>amount</code>.
     * @param amount The <code>amount</code> to set.
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * Gets <code>adminFee</code>.
     * @return The <code>adminFee</code>.
     */
    public BigDecimal getAdminFee() {
        return adminFee;
    }

    /**
     * Sets <code>adminFee</code>.
     * @param adminFee The <code>adminFee</code> to set.
     */
    public void setAdminFee(BigDecimal adminFee) {
        this.adminFee = adminFee;
    }

    /**
     * Gets <code>penaltyFee</code>.
     * @return The <code>penaltyFee</code>.
     */
    public BigDecimal getPenaltyFee() {
        return penaltyFee;
    }

    /**
     * Sets <code>penaltyFee</code>.
     * @param penaltyFee The <code>penaltyFee</code> to set.
     */
    public void setPenaltyFee(BigDecimal penaltyFee) {
        this.penaltyFee = penaltyFee;
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

    /**
     * Gets <code>info</code>.
     * @return The <code>info</code>.
     */
    public String getInfo() {
        return info;
    }

    /**
     * Sets <code>info</code>.
     * @param info The <code>info</code> to set.
     */
    public void setInfo(String info) {
        this.info = info;
    }
    
    /**
     * Gets <code>prevMeter</code>.
     * @return The <code>prevMeter</code>.
     */
    public String getPrevMeter() {
        return prevMeter;
    }

    /**
     * Sets <code>prevMeter</code>.
     * @param prevMeter The <code>prevMeter</code> to set.
     */
    public void setPrevMeter(String prevMeter) {
        this.prevMeter = prevMeter;
    }

    /**
     * Gets <code>currMeter</code>.
     * @return The <code>currMeter</code>.
     */
    public String getCurrMeter() {
        return currMeter;
    }

    /**
     * Sets <code>currMeter</code>.
     * @param currMeter The <code>currMeter</code> to set.
     */
    public void setCurrMeter(String currMeter) {
        this.currMeter = currMeter;
    }
    
    /**
     * Gets <code>dateTime</code>.
     * @return The <code>dateTime</code>.
     */
    public String getDateTime() {
        return dateTime;
    }

    /**
     * Sets <code>dateTime</code>.
     * @param dateTime The <code>dateTime</code> to set.
     */
    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
    
    /**
     * Gets <code>accountNo</code>.
     * @return The <code>accountNo</code>.
     */
    public String getAccountNo() {
        return accountNo;
    }

    /**
     * Sets <code>accountNo</code>.
     * @param accountNo The <code>accountNo</code> to set.
     */
    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    /**
     * Gets <code>destinationId</code>.
     * @return The <code>destinationId</code>.
     */
    public long getDestinationId() {
        return destinationId;
    }

    /**
     * Sets <code>destinationId</code>.
     * @param destinationId The <code>destinationId</code> to set.
     */
    public void setDestinationId(long destinationId) {
        this.destinationId = destinationId;
    }
    
    /* Functionalities: */

    /* Overrides: */
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "PLNPostpaidPaymentResponse [accountNo=" + accountNo + ", subscriberNumber=" + subscriberNumber
		+ ", subscriberName=" + subscriberName + ", paymentStatus=" + paymentStatus + ", billStatus="
		+ billStatus + ", billPeriod=" + billPeriod + ", referenceNumber=" + referenceNumber + ", tarif="
		+ tarif + ", daya=" + daya + ", amount=" + amount + ", adminFee=" + adminFee + ", penaltyFee="
		+ penaltyFee + ", totalAmount=" + totalAmount + ", remainBill=" + remainBill + ", info=" + info
		+ ", prevMeter=" + prevMeter + ", currMeter=" + currMeter
		+ ", dateTime=" + dateTime
		+ ", destinationId=" + destinationId + "]";
    }
    
}
