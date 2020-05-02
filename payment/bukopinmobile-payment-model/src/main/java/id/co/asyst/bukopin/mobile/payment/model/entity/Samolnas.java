/*
 * $Id$
 * 
 * Copyright (c) 2020 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.payment.model.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import id.co.asyst.bukopin.mobile.master.model.entity.Transaction;
import id.co.asyst.foundation.base.model.IdBasedObject;

/**
 * 
 * 
 * @author Gibran Haq
 * @version $Revision$, Apr 27, 2020
 * @since 1.2.Alpha1
 */
@Entity
@Table(name = "SAMOLNAS")
public class Samolnas extends IdBasedObject {
    /**
     * 
     */
    private static final long serialVersionUID = -8829705039708688010L;
    /* Constants: */

    /* Attributes: */
    @OneToOne
    @JoinColumn(name = "TRANSACTION_ID")
    private Transaction transaction;

    @Column(name = "NIK", nullable = false, length = 50)
    private String nik;

    @Column(name = "NPKB", nullable = false, length = 50)
    private String npkb;

    @Column(name = "MACHINE_NO", nullable = false, length = 50)
    private String machineNo;

    @Column(name = "TBPKB", nullable = false, length = 50)
    private String tbpkb;

    @Column(name = "NEWTBPKB", nullable = false, length = 50)
    private String newtbpkb;

    @Column(name = "VALIDITY", nullable = false, length = 50)
    private String validity;

    @Column(name = "AMOUNT", nullable = false)
    private BigDecimal amount;

    @Column(name = "ADMIN_FEE", nullable = false)
    private BigDecimal adminFee;

    @Column(name = "MERK", nullable = false, length = 50)
    private String merk;

    @Column(name = "TYPE", nullable = false, length = 50)
    private String type;

    @Column(name = "MODEL", nullable = false, length = 50)
    private String model;

    @Column(name = "PROGRESIF", nullable = false, length = 50)
    private String progresif;

    @Column(name = "YEAR", nullable = false, length = 50)
    private String year;
    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */
    /**
     * Gets <code>transaction</code>.
     * 
     * @return The <code>transaction</code>.
     */
    public Transaction getTransaction() {
	return transaction;
    }

    /**
     * Sets <code>transaction</code>.
     * 
     * @param transaction The <code>transaction</code> to set.
     */
    public void setTransaction(Transaction transaction) {
	this.transaction = transaction;
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
     * Gets <code>newtbpkb</code>.
     * 
     * @return The <code>newtbpkb</code>.
     */
    public String getNewtbpkb() {
	return newtbpkb;
    }

    /**
     * Sets <code>newtbpkb</code>.
     * 
     * @param newtbpkb The <code>newtbpkb</code> to set.
     */
    public void setNewtbpkb(String newtbpkb) {
	this.newtbpkb = newtbpkb;
    }

    /**
     * Gets <code>validity</code>.
     * 
     * @return The <code>validity</code>.
     */
    public String getValidity() {
	return validity;
    }

    /**
     * Sets <code>validity</code>.
     * 
     * @param validity The <code>validity</code> to set.
     */
    public void setValidity(String validity) {
	this.validity = validity;
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
	return "Samolnas [transaction=" + transaction + ", nik=" + nik + ", npkb=" + npkb + ", machineNo=" + machineNo
		+ ", tbpkb=" + tbpkb + ", newtbpkb=" + newtbpkb + ", validity=" + validity + ", amount=" + amount
		+ ", adminFee=" + adminFee + ", merk=" + merk + ", type=" + type + ", model=" + model + ", progresif="
		+ progresif + ", year=" + year + "]";
    }

}
