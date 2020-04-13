/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.service.model.payload;

import java.io.Serializable;

/**
 * SMS Gateway Request Model
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Oct 30, 2019
 * @since 1.0.Alpha1-SNAPSHOT
 */
public class SMSGatewayRequest implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1950840379678866760L;
	
	/* Constants: */

	/* Attributes: */
	private String clientTxnID;
	private String dateTimeTrx;
	private String merchantTrx;
	private String productID;
	private String noPonsel;
	private String smsMessage;

	/* Constructors: */

	/* Getters & setters for attributes: */
	/**
	 * Gets <code>clientTxnID</code>.
	 * @return The <code>clientTxnID</code>.
	 */
	public String getClientTxnID() {
		return clientTxnID;
	}
	/**
	 * Sets <code>clientTxnID</code>.
	 * @param clientTxnID The <code>clientTxnID</code> to set.
	 */
	public void setClientTxnID(String clientTxnID) {
		this.clientTxnID = clientTxnID;
	}
	/**
	 * Gets <code>dateTimeTrx</code>.
	 * @return The <code>dateTimeTrx</code>.
	 */
	public String getDateTimeTrx() {
		return dateTimeTrx;
	}
	/**
	 * Sets <code>dateTimeTrx</code>.
	 * @param dateTimeTrx The <code>dateTimeTrx</code> to set.
	 */
	public void setDateTimeTrx(String dateTimeTrx) {
		this.dateTimeTrx = dateTimeTrx;
	}
	/**
	 * Gets <code>merchantTrx</code>.
	 * @return The <code>merchantTrx</code>.
	 */
	public String getMerchantTrx() {
		return merchantTrx;
	}
	/**
	 * Sets <code>merchantTrx</code>.
	 * @param merchantTrx The <code>merchantTrx</code> to set.
	 */
	public void setMerchantTrx(String merchantTrx) {
		this.merchantTrx = merchantTrx;
	}
	/**
	 * Gets <code>productID</code>.
	 * @return The <code>productID</code>.
	 */
	public String getProductID() {
		return productID;
	}
	/**
	 * Sets <code>productID</code>.
	 * @param productID The <code>productID</code> to set.
	 */
	public void setProductID(String productID) {
		this.productID = productID;
	}
	/**
	 * Gets <code>noPonsel</code>.
	 * @return The <code>noPonsel</code>.
	 */
	public String getNoPonsel() {
		return noPonsel;
	}
	/**
	 * Sets <code>noPonsel</code>.
	 * @param noPonsel The <code>noPonsel</code> to set.
	 */
	public void setNoPonsel(String noPonsel) {
		this.noPonsel = noPonsel;
	}
	/**
	 * Gets <code>smsMessage</code>.
	 * @return The <code>smsMessage</code>.
	 */
	public String getSmsMessage() {
		return smsMessage;
	}
	/**
	 * Sets <code>smsMessage</code>.
	 * @param smsMessage The <code>smsMessage</code> to set.
	 */
	public void setSmsMessage(String smsMessage) {
		this.smsMessage = smsMessage;
	}

	/* Functionalities: */

	/* Overrides: */
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SMSGatewayRequest [clientTxnID=" + clientTxnID + ", dateTimeTrx=" + dateTimeTrx + ", merchantTrx="
				+ merchantTrx + ", productID=" + productID + ", noPonsel=" + noPonsel + ", smsMessage=" + smsMessage
				+ "]";
	}
}
