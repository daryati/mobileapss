/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.transfer.model.payload;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import id.co.asyst.bukopin.mobile.user.model.AccountBalanceRes;
import id.co.asyst.bukopin.mobile.user.model.AccountBalanceTransactionDetailRes;
import id.co.asyst.bukopin.mobile.user.model.TotalBalanceRes;

/**
 * 
 * 
 * @author Ihsan Firman
 * @version $Revision$, Dec 4, 2019
 * @since 2.0
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountBalanceMapper implements Serializable {

	    /**
	     * Serial Version UID.
	     */
	    private static final long serialVersionUID = -282503329867556084L;

	    /* Constants: */
	 

	    /* Attributes: */
	    
	    
	    /**
	     * transaction detail.
	     */    
	    @JsonProperty("accountDetails")
	    private List<TransactionDetailsMapper> transactionDetails;
	    
	    /**
	     * totalBalance.
	     */
	    @JsonProperty("totalBalance")
	    private TotalBalanceRes totalBalance;
	   
	    

	    /* Constructors: */
	    /**
	     * Constructor.
	     */
	    public AccountBalanceMapper() {
	        // do nothing.
	    }


	    /* Getters & setters for attributes: */

	    /**
	     * Gets <code>transactionDetails</code>.
	     * 
	     * @return The <code>transactionDetails</code>.
	     */
		 public List<TransactionDetailsMapper> getTransactionDetails() {
			return transactionDetails;
		}

		 /**
		     * Sets <code>transactionDetails</code>.
		     * 
		     * @param transactionDetails
		     *            The <code>transactionDetails</code> to set.
		     */
		public void setTransactionDetails(List<TransactionDetailsMapper> transactionDetails) {
			this.transactionDetails = transactionDetails;
		}

		/**
	     * Gets <code>totalBalance</code>.
	     * 
	     * @return The <code>totalBalance</code>.
	     */
		public TotalBalanceRes getTotalBalance() {
			return totalBalance;
		}

		/**
	     * Sets <code>totalBalance</code>.
	     * 
	     * @param totalBalance
	     *            The <code>totalBalance</code> to set.
	     */
		public void setTotalBalance(TotalBalanceRes totalBalance) {
			this.totalBalance = totalBalance;
		}

		

		/* Functionalities: x */
		
	    

		/*
	     * (non-Javadoc)
	     * 
	     * @see id.co.asyst.foundation.common.model.BaseObject#toString()
	     */
	    @Override
	    public String toString() {
	        ToStringBuilder builder = new ToStringBuilder(this);
	        builder.append("totalBalance", totalBalance);
	        builder.append("transactionDetails", transactionDetails);
	        return builder.toString();
	    }

	    @Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			AccountBalanceMapper other = (AccountBalanceMapper) obj;
			
			if (totalBalance == null) {
				if (other.totalBalance != null)
					return false;
			} else if (!totalBalance.equals(other.totalBalance))
				return false;
			if (transactionDetails == null) {
				if (other.transactionDetails != null)
					return false;
			} else if (!transactionDetails.equals(other.transactionDetails))
				return false;
			return true;
		}

	    @Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((totalBalance == null) ? 0 : totalBalance.hashCode());
			result = prime * result + ((transactionDetails == null) ? 0 : transactionDetails.hashCode());
			return result;
		}
	}
