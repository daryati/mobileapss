/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.transfer.model;

import javax.validation.constraints.NotBlank;

/**
 * Receipt Model for Transfer
 * 
 * @author Abi Nanel
 * @version $Revision$, Nov 20, 2019
 * @since 2.0
 */
public class ReceiptTransfer {
    /* Constants: */

    /* Attributes: */
    private String referensi;

    @NotBlank(message = "from Account Name is Required!")
    private String fromAccountName;

    @NotBlank(message = "from Saving Name is Required!")
    private String fromSavingName;

    @NotBlank(message = "from Account No is Required!")
    private String fromAccountNo;

    @NotBlank(message = "to Account Name is Required!")
    private String toAccountName;

    @NotBlank(message = "to Bank Name is Required!")
    private String toBankName;

    @NotBlank(message = "to Account No is Required!")
    private String toAccountNo;

    @NotBlank(message = "Amount is Required!")
    private String amount;

    @NotBlank(message = "Charge is Required!")
    private String charge;

    @NotBlank(message = "Total is Required!")
    private String total;

    /* Transient Attributes: */

    /* Constructors: */
    public ReceiptTransfer() {
	// TO DO : Nothing
    }

    /* Getters & setters for attributes: */
    /**
     * Gets <code>referensi</code>.
     * 
     * @return The <code>referensi</code>.
     */
    public String getReferensi() {
	return referensi;
    }

    /**
     * Sets <code>referensi</code>.
     * 
     * @param referensi
     *            The <code>referensi</code> to set.
     */
    public void setReferensi(String referensi) {
	this.referensi = referensi;
    }

    /**
     * Gets <code>fromAccountName</code>.
     * 
     * @return The <code>fromAccountName</code>.
     */
    public String getFromAccountName() {
	return fromAccountName;
    }

    /**
     * Sets <code>fromAccountName</code>.
     * 
     * @param fromAccountName
     *            The <code>fromAccountName</code> to set.
     */
    public void setFromAccountName(String fromAccountName) {
	this.fromAccountName = fromAccountName;
    }

    /**
     * Gets <code>fromSavingName</code>.
     * 
     * @return The <code>fromSavingName</code>.
     */
    public String getFromSavingName() {
	return fromSavingName;
    }

    /**
     * Sets <code>fromSavingName</code>.
     * 
     * @param fromSavingName
     *            The <code>fromSavingName</code> to set.
     */
    public void setFromSavingName(String fromSavingName) {
	this.fromSavingName = fromSavingName;
    }

    /**
     * Gets <code>fromAccountNo</code>.
     * 
     * @return The <code>fromAccountNo</code>.
     */
    public String getFromAccountNo() {
	return fromAccountNo;
    }

    /**
     * Sets <code>fromAccountNo</code>.
     * 
     * @param fromAccountNo
     *            The <code>fromAccountNo</code> to set.
     */
    public void setFromAccountNo(String fromAccountNo) {
	this.fromAccountNo = fromAccountNo;
    }

    /**
     * Gets <code>toAccountName</code>.
     * 
     * @return The <code>toAccountName</code>.
     */
    public String getToAccountName() {
	return toAccountName;
    }

    /**
     * Sets <code>toAccountName</code>.
     * 
     * @param toAccountName
     *            The <code>toAccountName</code> to set.
     */
    public void setToAccountName(String toAccountName) {
	this.toAccountName = toAccountName;
    }

    /**
     * Gets <code>toBankName</code>.
     * 
     * @return The <code>toBankName</code>.
     */
    public String getToBankName() {
	return toBankName;
    }

    /**
     * Sets <code>toBankName</code>.
     * 
     * @param toBankName
     *            The <code>toBankName</code> to set.
     */
    public void setToBankName(String toBankName) {
	this.toBankName = toBankName;
    }

    /**
     * Gets <code>toAccountNo</code>.
     * 
     * @return The <code>toAccountNo</code>.
     */
    public String getToAccountNo() {
	return toAccountNo;
    }

    /**
     * Sets <code>toAccountNo</code>.
     * 
     * @param toAccountNo
     *            The <code>toAccountNo</code> to set.
     */
    public void setToAccountNo(String toAccountNo) {
	this.toAccountNo = toAccountNo;
    }

    /**
     * Gets <code>amount</code>.
     * 
     * @return The <code>amount</code>.
     */
    public String getAmount() {
	return amount;
    }

    /**
     * Sets <code>amount</code>.
     * 
     * @param amount
     *            The <code>amount</code> to set.
     */
    public void setAmount(String amount) {
	this.amount = amount;
    }

    /**
     * Gets <code>charge</code>.
     * 
     * @return The <code>charge</code>.
     */
    public String getCharge() {
	return charge;
    }

    /**
     * Sets <code>charge</code>.
     * 
     * @param charge
     *            The <code>charge</code> to set.
     */
    public void setCharge(String charge) {
	this.charge = charge;
    }

    /**
     * Gets <code>total</code>.
     * 
     * @return The <code>total</code>.
     */
    public String getTotal() {
	return total;
    }

    /**
     * Sets <code>total</code>.
     * 
     * @param total
     *            The <code>total</code> to set.
     */
    public void setTotal(String total) {
	this.total = total;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "ReceiptTransfer [referensi=" + referensi + ", fromAccountName=" + fromAccountName + ", fromSavingName="
		+ fromSavingName + ", fromAccountNo=" + fromAccountNo + ", toAccountName=" + toAccountName
		+ ", toBankName=" + toBankName + ", toAccountNo=" + toAccountNo + ", amount=" + amount + ", charge="
		+ charge + ", total=" + total + "]";
    }
}
