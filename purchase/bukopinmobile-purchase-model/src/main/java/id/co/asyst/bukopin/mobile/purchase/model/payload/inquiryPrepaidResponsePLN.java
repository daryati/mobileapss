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

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 
 * 
 * @author Gibran Haq
 * @version $Revision$, Dec 7, 2019
 * @since 1.0.Alpha1
 */
@JsonInclude(Include.NON_NULL)
public class inquiryPrepaidResponsePLN {
	/* Constants: */

	/* Attributes: */
	private String subscriberID;
	
	private String subscriberName;
	
	private String tarifDaya;
	
	private BigDecimal nominal;
	
	private BigDecimal adminFee;
	
	private BigDecimal total;
	
	private String element11;
	
	private String element48;
	
	private String element62;
	
	private String flag;

	/* Transient Attributes: */

	/* Constructors: */

	/* Getters & setters for attributes: */
	/**
	 * Gets <code>subscriberID</code>.
	 * @return The <code>subscriberID</code>.
	 */
	public String getSubscriberID() {
		return subscriberID;
	}

	/**
	 * Sets <code>subscriberID</code>.
	 * @param subscriberID The <code>subscriberID</code> to set.
	 */
	public void setSubscriberID(String subscriberID) {
		this.subscriberID = subscriberID;
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
	 * Gets <code>tarifDaya</code>.
	 * @return The <code>tarifDaya</code>.
	 */
	public String getTarifDaya() {
		return tarifDaya;
	}

	/**
	 * Sets <code>tarifDaya</code>.
	 * @param tarifDaya The <code>tarifDaya</code> to set.
	 */
	public void setTarifDaya(String tarifDaya) {
		this.tarifDaya = tarifDaya;
	}

	/**
	 * Gets <code>nominal</code>.
	 * @return The <code>nominal</code>.
	 */
	public BigDecimal getNominal() {
		return nominal;
	}

	/**
	 * Sets <code>nominal</code>.
	 * @param nominal The <code>nominal</code> to set.
	 */
	public void setNominal(BigDecimal nominal) {
		this.nominal = nominal;
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
	 * Gets <code>total</code>.
	 * @return The <code>total</code>.
	 */
	public BigDecimal getTotal() {
		return total;
	}

	/**
	 * Sets <code>total</code>.
	 * @param total The <code>total</code> to set.
	 */
	public void setTotal(BigDecimal total) {
		this.total = total;
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
	 * Gets <code>element62</code>.
	 * @return The <code>element62</code>.
	 */
	public String getElement62() {
		return element62;
	}

	/**
	 * Sets <code>element62</code>.
	 * @param element62 The <code>element62</code> to set.
	 */
	public void setElement62(String element62) {
		this.element62 = element62;
	}

	/**
	 * Gets <code>flag</code>.
	 * @return The <code>flag</code>.
	 */
	public String getFlag() {
	    return flag;
	}

	/**
	 * Sets <code>flag</code>.
	 * @param flag The <code>flag</code> to set.
	 */
	public void setFlag(String flag) {
	    this.flag = flag;
	}

	/* Getters & setters for transient attributes: */

	/* Functionalities: */

	/* Overrides: */}
