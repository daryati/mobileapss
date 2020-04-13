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

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonProperty;

import id.co.asyst.foundation.common.model.BaseObjectAclAware;

/**
 * Inquiry Transaction Response.
 * 
 * @author Kartika Dwi Handini
 * @version $Revision$, (Created on Nov 21, 2019)
 * @since Asyst-Foundation 1.0.Alpha1
 */

public class PostingElementRes implements Serializable {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = -282503329867556084L;

    /* Constants: */
    
    /* Attributes: */
    
    //Type
    @JsonProperty("type")
    private String type;
    
    //MTI
    @JsonProperty("MTI")
    private String MTI;
    
    //processing code
    @JsonProperty("processingCode")
    private String processingCode;
    
     // transaction amount
    @JsonProperty("transctionAmount")
    private String transactionAmount;
    
    // card holder billing amount
    @JsonProperty("cardHolderBillAmount")
    private String cardHolderBillAmount;
    
    //transmission date time
    @JsonProperty("transmissionDateTime")
    private String transmissionDateTime;
    
    // conversionrate
    @JsonProperty("convertionRate")
    private String conversionRate;
    
    //STAN
    @JsonProperty("STAN")
    private String STAN;
    
    // time local transaction
    @JsonProperty("timeLocalTransaction")
    private String timeLocalTransaction;
    
    // date local transaction
    @JsonProperty("dateLocalTranasaction")
    private String dateLocalTranasaction;
    
    // merchant id
    @JsonProperty("merchantID")
    private String merchantId;
    
    // transaction amount fee
    @JsonProperty("transactionAmountFee")
    private String transactionAmountFee;
    
    // forwading institution code
    @JsonProperty("forwardingInstitutionCode")
    private String forwardingInstitutionCode;
    
    // retrieval reference number
    @JsonProperty("retrievalRefNumber")
    private String retrievalRefNumber;
    
    //response code
    @JsonProperty("responseCode")
    private String responseCode;
    
    // card acceptor
    @JsonProperty("cardAcceptor")
    private String cardAcceptor;
    
    //additional response data
    @JsonProperty("additionalResponseData")
    private String additionalResponseData;
    
    //additional data
    @JsonProperty("additionalData")
    private String additionalData;
    
    //transaction currency
    @JsonProperty("transactionCurrency")
    private String transactionCurrency;
    
    //currency code
    @JsonProperty("currencyCode")
    private String currencyCode;
    
    // additional amount
    @JsonProperty("additionalAmount")
    private String additionalAmount;
    
    //application information
    @JsonProperty("applicationInformation")
    private String applicationInformation;
    
    // settlement id
    @JsonProperty("settlementId")
    private String settlementId;
    
    //pengirim
    @JsonProperty("senderAccountNumber")
    private String senderAccountNumber;
    
    // penerima
    @JsonProperty("receiverAccountNumber")
    private String receiverAccountNumber;
    
    
    /* Constructors: */
    /**
     * Constructor.
     */
    public PostingElementRes() {
        // do nothing.
    }
    
    
    
    /* Getters & setters for attributes: */

	/**
     * Gets <code>processingCode</code>.
     * 
     * @return The <code>processingCode</code>.
     */
    public String getProcessingCode() {
		return processingCode;
	}


    /**
     * Sets <code>processingCode</code>.
     * 
     * @param processingCode
     *            The <code>processingCode</code> to set.
     */
	public void setProcessingCode(String processingCode) {
		this.processingCode = processingCode;
	}


	/**
     * Gets <code>transactionAmount</code>.
     * 
     * @return The <code>transactionAmount</code>.
     */
	public String getTransactionAmount() {
		return transactionAmount;
	}


	/**
     * Sets <code>transactionAmount</code>.
     * 
     * @param transactionAmount
     *            The <code>transactionAmount</code> to set.
     */
	public void setTransactionAmount(String transactionAmount) {
		this.transactionAmount = transactionAmount;
	}


	/**
     * Gets <code>cardHolderBillAmount</code>.
     * 
     * @return The <code>cardHolderBillAmount</code>.
     */
	public String getCardHolderBillAmount() {
		return cardHolderBillAmount;
	}


	/**
     * Sets <code>cardHolderBillAmount</code>.
     * 
     * @param cardHolderBillAmount
     *            The <code>cardHolderBillAmount</code> to set.
     */
	public void setCardHolderBillAmount(String cardHolderBillAmount) {
		this.cardHolderBillAmount = cardHolderBillAmount;
	}


	/**
     * Gets <code>transmissionDateTime</code>.
     * 
     * @return The <code>transmissionDateTime</code>.
     */
	public String getTransmissionDateTime() {
		return transmissionDateTime;
	}


	/**
     * Sets <code>transmissionDateTime</code>.
     * 
     * @param transmissionDateTime
     *            The <code>transmissionDateTime</code> to set.
     */
	public void setTransmissionDateTime(String transmissionDateTime) {
		this.transmissionDateTime = transmissionDateTime;
	}


	/**
     * Gets <code>conversionRate</code>.
     * 
     * @return The <code>conversionRate</code>.
     */
	public String getConversionRate() {
		return conversionRate;
	}


	/**
     * Sets <code>conversionRate</code>.
     * 
     * @param conversionRate
     *            The <code>conversionRate</code> to set.
     */
	public void setConversionRate(String conversionRate) {
		this.conversionRate = conversionRate;
	}


	/**
     * Gets <code>STAN</code>.
     * 
     * @return The <code>STAN</code>.
     */
	public String getSTAN() {
		return STAN;
	}


	/**
     * Sets <code>STAN</code>.
     * 
     * @param STAN
     *            The <code>STAN</code> to set.
     */
	public void setSTAN(String sTAN) {
		STAN = sTAN;
	}


	/**
     * Gets <code>timeLocalTransaction</code>.
     * 
     * @return The <code>timeLocalTransaction</code>.
     */
	public String getTimeLocalTransaction() {
		return timeLocalTransaction;
	}


	/**
     * Sets <code>timeLocalTransaction</code>.
     * 
     * @param timeLocalTransaction
     *            The <code>timeLocalTransaction</code> to set.
     */
	public void setTimeLocalTransaction(String timeLocalTransaction) {
		this.timeLocalTransaction = timeLocalTransaction;
	}


	/**
     * Gets <code>dateLocalTranasaction</code>.
     * 
     * @return The <code>dateLocalTranasaction</code>.
     */
	public String getDateLocalTranasaction() {
		return dateLocalTranasaction;
	}


	/**
     * Sets <code>dateLocalTranasaction</code>.
     * 
     * @param dateLocalTranasaction
     *            The <code>dateLocalTranasaction</code> to set.
     */
	public void setDateLocalTranasaction(String dateLocalTranasaction) {
		this.dateLocalTranasaction = dateLocalTranasaction;
	}


	/**
     * Gets <code>merchantId</code>.
     * 
     * @return The <code>merchantId</code>.
     */
	public String getMerchantId() {
		return merchantId;
	}


	/**
     * Sets <code>merchantId</code>.
     * 
     * @param merchantId
     *            The <code>merchantId</code> to set.
     */
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}


	/**
     * Gets <code>transactionAmountFee</code>.
     * 
     * @return The <code>transactionAmountFee</code>.
     */
	public String getTransactionAmountFee() {
		return transactionAmountFee;
	}


	/**
     * Sets <code>transactionAmountFee</code>.
     * 
     * @param transactionAmountFee
     *            The <code>transactionAmountFee</code> to set.
     */
	public void setTransactionAmountFee(String transactionAmountFee) {
		this.transactionAmountFee = transactionAmountFee;
	}


	/**
     * Gets <code>forwardingInstitutionCode</code>.
     * 
     * @return The <code>forwardingInstitutionCode</code>.
     */
	public String getForwardingInstitutionCode() {
		return forwardingInstitutionCode;
	}


	/**
     * Sets <code>forwardingInstitutionCode</code>.
     * 
     * @param forwardingInstitutionCode
     *            The <code>forwardingInstitutionCode</code> to set.
     */
	public void setForwardingInstitutionCode(String forwardingInstitutionCode) {
		this.forwardingInstitutionCode = forwardingInstitutionCode;
	}


	/**
     * Gets <code>retrievalRefNumber</code>.
     * 
     * @return The <code>retrievalRefNumber</code>.
     */
	public String getRetrievalRefNumber() {
		return retrievalRefNumber;
	}


	/**
     * Sets <code>retrievalRefNumber</code>.
     * 
     * @param retrievalRefNumber
     *            The <code>retrievalRefNumber</code> to set.
     */
	public void setRetrievalRefNumber(String retrievalRefNumber) {
		this.retrievalRefNumber = retrievalRefNumber;
	}


	/**
     * Gets <code>responseCode</code>.
     * 
     * @return The <code>responseCode</code>.
     */
	public String getResponseCode() {
		return responseCode;
	}


	/**
     * Sets <code>responseCode</code>.
     * 
     * @param responseCode
     *            The <code>responseCode</code> to set.
     */
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}


	/**
     * Gets <code>cardAcceptor</code>.
     * 
     * @return The <code>cardAcceptor</code>.
     */
	public String getCardAcceptor() {
		return cardAcceptor;
	}


	/**
     * Sets <code>cardAcceptor</code>.
     * 
     * @param cardAcceptor
     *            The <code>cardAcceptor</code> to set.
     */
	public void setCardAcceptor(String cardAcceptor) {
		this.cardAcceptor = cardAcceptor;
	}


	/**
     * Gets <code>additionalResponseData</code>.
     * 
     * @return The <code>additionalResponseData</code>.
     */
	public String getAdditionalResponseData() {
		return additionalResponseData;
	}


	/**
     * Sets <code>additionalResponseData</code>.
     * 
     * @param additionalResponseData
     *            The <code>additionalResponseData</code> to set.
     */
	public void setAdditionalResponseData(String additionalResponseData) {
		this.additionalResponseData = additionalResponseData;
	}


	/**
     * Gets <code>additionalData</code>.
     * 
     * @return The <code>additionalData</code>.
     */
	public String getAdditionalData() {
		return additionalData;
	}


	/**
     * Sets <code>additionalData</code>.
     * 
     * @param additionalData
     *            The <code>additionalData</code> to set.
     */
	public void setAdditionalData(String additionalData) {
		this.additionalData = additionalData;
	}


	/**
     * Gets <code>transactionCurrency</code>.
     * 
     * @return The <code>transactionCurrency</code>.
     */
	public String getTransactionCurrency() {
		return transactionCurrency;
	}


	/**
     * Sets <code>transactionCurrency</code>.
     * 
     * @param transactionCurrency
     *            The <code>transactionCurrency</code> to set.
     */
	public void setTransactionCurrency(String transactionCurrency) {
		this.transactionCurrency = transactionCurrency;
	}


	/**
     * Gets <code>currencyCode</code>.
     * 
     * @return The <code>currencyCode</code>.
     */
	public String getCurrencyCode() {
		return currencyCode;
	}


	/**
     * Sets <code>currencyCode</code>.
     * 
     * @param currencyCode
     *            The <code>currencyCode</code> to set.
     */
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}


	/**
     * Gets <code>additionalAmount</code>.
     * 
     * @return The <code>additionalAmount</code>.
     */
	public String getAdditionalAmount() {
		return additionalAmount;
	}


	/**
     * Sets <code>additionalAmount</code>.
     * 
     * @param additionalAmount
     *            The <code>additionalAmount</code> to set.
     */
	public void setAdditionalAmount(String additionalAmount) {
		this.additionalAmount = additionalAmount;
	}


	/**
     * Gets <code>applicationInformation</code>.
     * 
     * @return The <code>applicationInformation</code>.
     */
	public String getApplicationInformation() {
		return applicationInformation;
	}


	/**
     * Sets <code>applicationInformation</code>.
     * 
     * @param applicationInformation
     *            The <code>applicationInformation</code> to set.
     */
	public void setApplicationInformation(String applicationInformation) {
		this.applicationInformation = applicationInformation;
	}


	/**
     * Gets <code>settlementId</code>.
     * 
     * @return The <code>settlementId</code>.
     */
	public String getSettlementId() {
		return settlementId;
	}


	/**
     * Sets <code>settlementId</code>.
     * 
     * @param settlementId
     *            The <code>settlementId</code> to set.
     */
	public void setSettlementId(String settlementId) {
		this.settlementId = settlementId;
	}


	/**
     * Gets <code>senderAccountNumber</code>.
     * 
     * @return The <code>senderAccountNumber</code>.
     */
	public String getSenderAccountNumber() {
		return senderAccountNumber;
	}


	/**
     * Sets <code>senderAccountNumber</code>.
     * 
     * @param senderAccountNumber
     *            The <code>senderAccountNumber</code> to set.
     */
	public void setSenderAccountNumber(String senderAccountNumber) {
		this.senderAccountNumber = senderAccountNumber;
	}


	/**
     * Gets <code>receiverAccountNumber</code>.
     * 
     * @return The <code>receiverAccountNumber</code>.
     */
	public String getReceiverAccountNumber() {
		return receiverAccountNumber;
	}


	/**
     * Sets <code>receiverAccountNumber</code>.
     * 
     * @param receiverAccountNumber
     *            The <code>receiverAccountNumber</code> to set.
     */
	public void setReceiverAccountNumber(String receiverAccountNumber) {
		this.receiverAccountNumber = receiverAccountNumber;
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
     * @param type
     *            The <code>type</code> to set.
     */
	public void setType(String type) {
		this.type = type;
	}


	/**
     * Gets <code>MTI</code>.
     * 
     * @return The <code>MTI</code>.
     */
	public String getMTI() {
		return MTI;
	}


	/**
     * Sets <code>MTI</code>.
     * 
     * @param MTI
     *            The <code>MTI</code> to set.
     */
	public void setMTI(String mTI) {
		MTI = mTI;
	}

	
	/* Functionalities: x */
	/* Overrides: */


	/*
     * (non-Javadoc)
     * 
     * @see id.co.asyst.foundation.common.model.BaseObject#toString()
     */
   /* @Override
    public String toString() {
        ToStringBuilder builder = new ToStringBuilder(this);
        builder.append("accountNumber", accountNumber);
        builder.append("alias", alias);
        builder.append("bank", bank);
        builder.append("accountName", accountName);
        return builder.toString();
    }*/
	
	

    



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PostingElementRes other = (PostingElementRes) obj;
		if (STAN == null) {
			if (other.STAN != null)
				return false;
		} else if (!STAN.equals(other.STAN))
			return false;
		if (additionalAmount == null) {
			if (other.additionalAmount != null)
				return false;
		} else if (!additionalAmount.equals(other.additionalAmount))
			return false;
		if (additionalData == null) {
			if (other.additionalData != null)
				return false;
		} else if (!additionalData.equals(other.additionalData))
			return false;
		if (additionalResponseData == null) {
			if (other.additionalResponseData != null)
				return false;
		} else if (!additionalResponseData.equals(other.additionalResponseData))
			return false;
		if (applicationInformation == null) {
			if (other.applicationInformation != null)
				return false;
		} else if (!applicationInformation.equals(other.applicationInformation))
			return false;
		if (cardAcceptor == null) {
			if (other.cardAcceptor != null)
				return false;
		} else if (!cardAcceptor.equals(other.cardAcceptor))
			return false;
		if (cardHolderBillAmount == null) {
			if (other.cardHolderBillAmount != null)
				return false;
		} else if (!cardHolderBillAmount.equals(other.cardHolderBillAmount))
			return false;
		if (conversionRate == null) {
			if (other.conversionRate != null)
				return false;
		} else if (!conversionRate.equals(other.conversionRate))
			return false;
		if (currencyCode == null) {
			if (other.currencyCode != null)
				return false;
		} else if (!currencyCode.equals(other.currencyCode))
			return false;
		if (dateLocalTranasaction == null) {
			if (other.dateLocalTranasaction != null)
				return false;
		} else if (!dateLocalTranasaction.equals(other.dateLocalTranasaction))
			return false;
		if (forwardingInstitutionCode == null) {
			if (other.forwardingInstitutionCode != null)
				return false;
		} else if (!forwardingInstitutionCode.equals(other.forwardingInstitutionCode))
			return false;
		if (merchantId == null) {
			if (other.merchantId != null)
				return false;
		} else if (!merchantId.equals(other.merchantId))
			return false;
		if (processingCode == null) {
			if (other.processingCode != null)
				return false;
		} else if (!processingCode.equals(other.processingCode))
			return false;
		if (receiverAccountNumber == null) {
			if (other.receiverAccountNumber != null)
				return false;
		} else if (!receiverAccountNumber.equals(other.receiverAccountNumber))
			return false;
		if (responseCode == null) {
			if (other.responseCode != null)
				return false;
		} else if (!responseCode.equals(other.responseCode))
			return false;
		if (retrievalRefNumber == null) {
			if (other.retrievalRefNumber != null)
				return false;
		} else if (!retrievalRefNumber.equals(other.retrievalRefNumber))
			return false;
		if (senderAccountNumber == null) {
			if (other.senderAccountNumber != null)
				return false;
		} else if (!senderAccountNumber.equals(other.senderAccountNumber))
			return false;
		if (settlementId == null) {
			if (other.settlementId != null)
				return false;
		} else if (!settlementId.equals(other.settlementId))
			return false;
		if (timeLocalTransaction == null) {
			if (other.timeLocalTransaction != null)
				return false;
		} else if (!timeLocalTransaction.equals(other.timeLocalTransaction))
			return false;
		if (transactionAmount == null) {
			if (other.transactionAmount != null)
				return false;
		} else if (!transactionAmount.equals(other.transactionAmount))
			return false;
		if (transactionAmountFee == null) {
			if (other.transactionAmountFee != null)
				return false;
		} else if (!transactionAmountFee.equals(other.transactionAmountFee))
			return false;
		if (transactionCurrency == null) {
			if (other.transactionCurrency != null)
				return false;
		} else if (!transactionCurrency.equals(other.transactionCurrency))
			return false;
		if (transmissionDateTime == null) {
			if (other.transmissionDateTime != null)
				return false;
		} else if (!transmissionDateTime.equals(other.transmissionDateTime))
			return false;
		return true;
	}

    @Override
	public String toString() {
		return "PostingElementRes [processingCode=" + processingCode + ", transactionAmount=" + transactionAmount
				+ ", cardHolderBillAmount=" + cardHolderBillAmount + ", transmissionDateTime=" + transmissionDateTime
				+ ", conversionRate=" + conversionRate + ", STAN=" + STAN + ", timeLocalTransaction="
				+ timeLocalTransaction + ", dateLocalTranasaction=" + dateLocalTranasaction + ", merchantId="
				+ merchantId + ", transactionAmountFee=" + transactionAmountFee + ", forwardingInstitutionCode="
				+ forwardingInstitutionCode + ", retrievalRefNumber=" + retrievalRefNumber + ", responseCode="
				+ responseCode + ", cardAcceptor=" + cardAcceptor + ", additionalResponseData=" + additionalResponseData
				+ ", additionalData=" + additionalData + ", transactionCurrency=" + transactionCurrency
				+ ", currencyCode=" + currencyCode + ", additionalAmount=" + additionalAmount
				+ ", applicationInformation=" + applicationInformation + ", settlementId=" + settlementId
				+ ", senderAccountNumber=" + senderAccountNumber + ", receiverAccountNumber=" + receiverAccountNumber
				+ "]";
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((STAN == null) ? 0 : STAN.hashCode());
		result = prime * result + ((additionalAmount == null) ? 0 : additionalAmount.hashCode());
		result = prime * result + ((additionalData == null) ? 0 : additionalData.hashCode());
		result = prime * result + ((additionalResponseData == null) ? 0 : additionalResponseData.hashCode());
		result = prime * result + ((applicationInformation == null) ? 0 : applicationInformation.hashCode());
		result = prime * result + ((cardAcceptor == null) ? 0 : cardAcceptor.hashCode());
		result = prime * result + ((cardHolderBillAmount == null) ? 0 : cardHolderBillAmount.hashCode());
		result = prime * result + ((conversionRate == null) ? 0 : conversionRate.hashCode());
		result = prime * result + ((currencyCode == null) ? 0 : currencyCode.hashCode());
		result = prime * result + ((dateLocalTranasaction == null) ? 0 : dateLocalTranasaction.hashCode());
		result = prime * result + ((forwardingInstitutionCode == null) ? 0 : forwardingInstitutionCode.hashCode());
		result = prime * result + ((merchantId == null) ? 0 : merchantId.hashCode());
		result = prime * result + ((processingCode == null) ? 0 : processingCode.hashCode());
		result = prime * result + ((receiverAccountNumber == null) ? 0 : receiverAccountNumber.hashCode());
		result = prime * result + ((responseCode == null) ? 0 : responseCode.hashCode());
		result = prime * result + ((retrievalRefNumber == null) ? 0 : retrievalRefNumber.hashCode());
		result = prime * result + ((senderAccountNumber == null) ? 0 : senderAccountNumber.hashCode());
		result = prime * result + ((settlementId == null) ? 0 : settlementId.hashCode());
		result = prime * result + ((timeLocalTransaction == null) ? 0 : timeLocalTransaction.hashCode());
		result = prime * result + ((transactionAmount == null) ? 0 : transactionAmount.hashCode());
		result = prime * result + ((transactionAmountFee == null) ? 0 : transactionAmountFee.hashCode());
		result = prime * result + ((transactionCurrency == null) ? 0 : transactionCurrency.hashCode());
		result = prime * result + ((transmissionDateTime == null) ? 0 : transmissionDateTime.hashCode());
		return result;
	}
}
