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

import javax.validation.constraints.NotBlank;

/**
 * 
 * 
 * @author Abi Nanel
 * @version $Revision$, Dec 9, 2019
 * @since 2.0
 */
public class PurchasePrepaidRequestPLN {
    /* Constants: */

    /* Attributes: */
    /**
     * Username
     */
    @NotBlank(message = "Username is required")
    private String username;

    /**
     * PIN
     */
    @NotBlank(message = "PIN is required")
    private String pin;

    @NotBlank(message = "Account Number is required")
    private String accountNo;

    @NotBlank(message = "Nominal is required")
    private String nominal;

    @NotBlank(message = "Admin Charge is required")
    private String adminCharge;

    @NotBlank(message = "Element 11 is required")
    private String element11;

    @NotBlank(message = "Element 48 is required")
    private String element48;

    @NotBlank(message = "Element 62 is required")
    private String element62;

    @NotBlank(message = "flag is required")
    private String flag;

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */
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
     * Gets <code>pin</code>.
     * 
     * @return The <code>pin</code>.
     */
    public String getPin() {
	return pin;
    }

    /**
     * Sets <code>pin</code>.
     * 
     * @param pin
     *            The <code>pin</code> to set.
     */
    public void setPin(String pin) {
	this.pin = pin;
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
     * Gets <code>element11</code>.
     * 
     * @return The <code>element11</code>.
     */
    public String getElement11() {
	return element11;
    }

    /**
     * Sets <code>element11</code>.
     * 
     * @param element11
     *            The <code>element11</code> to set.
     */
    public void setElement11(String element11) {
	this.element11 = element11;
    }

    /**
     * Gets <code>nominal</code>.
     * 
     * @return The <code>nominal</code>.
     */
    public String getNominal() {
	return nominal;
    }

    /**
     * Sets <code>nominal</code>.
     * 
     * @param nominal
     *            The <code>nominal</code> to set.
     */
    public void setNominal(String nominal) {
	this.nominal = nominal;
    }

    /**
     * Gets <code>adminCharge</code>.
     * 
     * @return The <code>adminCharge</code>.
     */
    public String getAdminCharge() {
	return adminCharge;
    }

    /**
     * Sets <code>adminCharge</code>.
     * 
     * @param adminCharge
     *            The <code>adminCharge</code> to set.
     */
    public void setAdminCharge(String adminCharge) {
	this.adminCharge = adminCharge;
    }

    /**
     * Gets <code>element48</code>.
     * 
     * @return The <code>element48</code>.
     */
    public String getElement48() {
	return element48;
    }

    /**
     * Sets <code>element48</code>.
     * 
     * @param element48
     *            The <code>element48</code> to set.
     */
    public void setElement48(String element48) {
	this.element48 = element48;
    }

    /**
     * Gets <code>element62</code>.
     * 
     * @return The <code>element62</code>.
     */
    public String getElement62() {
	return element62;
    }

    /**
     * Sets <code>element62</code>.
     * 
     * @param element62
     *            The <code>element62</code> to set.
     */
    public void setElement62(String element62) {
	this.element62 = element62;
    }

    /**
     * Gets <code>flag</code>.
     * 
     * @return The <code>flag</code>.
     */
    public String getFlag() {
	return flag;
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

    /* Getters & setters for transient attributes: */

    /* Functionalities: */

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "PurchasePrepaidRequestPLN [username=" + username + ", pin=" + pin + ", accountNo=" + accountNo
		+ ", nominal=" + nominal + ", adminCharge=" + adminCharge + ", element11=" + element11 + ", element48="
		+ element48 + ", element62=" + element62 + ", flag=" + flag + "]";
    }
}
