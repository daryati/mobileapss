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
import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Inquiry Account Request.
 * 
 * @author Kartika Dwi Handini
 * @version $Revision$, (Created on Nov 22, 2019)
 * @since Asyst-Foundation 1.0.Alpha1
 */

public class InquiryAccountRes implements Serializable {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = -282503329867556084L;

    /* Constants: */

    /* Attributes: */

    /**
     * amount.
     */
    @JsonProperty("amount")
    private BigDecimal amount;

    /**
     * admin fee.
     */
    @JsonProperty("adminFee")
    private BigDecimal adminFee;

    /**
     * method.
     */
    @JsonProperty("method")
    private String method;

    /**
     * message.
     */
    @JsonProperty("message")
    private String message;

    /**
     * posting from.
     */
    @JsonProperty("from")
    private PostingFromReq postingFrom;

    /**
     * posting to.
     */
    @JsonProperty("to")
    private PostingToReq postingTo;

    /**
     * confirmation code.
     */
    @JsonProperty("confirmationCode")
    private String confirmationCode;

    /**
     * status code.
     */
    @JsonProperty("statusCode")
    private String statusCode;

    /**
     * status description.
     */
    @JsonProperty("statusDesc")
    private String statusDesc;

    /**
     * stan.
     */
    @JsonProperty("STAN")
    private String stan;

    /**
     * Reference Code: AES(from|to|confirmationCode|merchantID)
     */
    private String referenceCode;

    /* Constructors: */
    /**
     * Constructor.
     */
    public InquiryAccountRes() {
	// do nothing.
    }

    /* Getters & setters for attributes: */

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
     * @param amount
     *            The <code>amount</code> to set.
     */
    public void setAmount(BigDecimal amount) {
	this.amount = amount;
    }

    /**
     * Gets <code>message</code>.
     * 
     * @return The <code>message</code>.
     */
    public String getMessage() {
	return message;
    }

    /**
     * Sets <code>message</code>.
     * 
     * @param message
     *            The <code>message</code> to set.
     */
    public void setMessage(String message) {
	this.message = message;
    }

    /**
     * Gets <code>postingFrom</code>.
     * 
     * @return The <code>postingFrom</code>.
     */
    public PostingFromReq getPostingFrom() {
	return postingFrom;
    }

    /**
     * Sets <code>postingFrom</code>.
     * 
     * @param postingFrom
     *            The <code>postingFrom</code> to set.
     */
    public void setPostingFrom(PostingFromReq postingFrom) {
	this.postingFrom = postingFrom;
    }

    /**
     * Gets <code>postingTo</code>.
     * 
     * @return The <code>postingTo</code>.
     */
    public PostingToReq getPostingTo() {
	return postingTo;
    }

    /**
     * Sets <code>postingTo</code>.
     * 
     * @param postingTo
     *            The <code>postingTo</code> to set.
     */
    public void setPostingTo(PostingToReq postingTo) {
	this.postingTo = postingTo;
    }

    /**
     * Gets <code>confirmationCode</code>.
     * 
     * @return The <code>confirmationCode</code>.
     */
    public String getConfirmationCode() {
	return confirmationCode;
    }

    /**
     * Sets <code>confirmationCode</code>.
     * 
     * @param confirmationCode
     *            The <code>confirmationCode</code> to set.
     */
    public void setConfirmationCode(String confirmationCode) {
	this.confirmationCode = confirmationCode;
    }

    /**
     * Gets <code>statusCode</code>.
     * 
     * @return The <code>statusCode</code>.
     */
    public String getStatusCode() {
	return statusCode;
    }

    /**
     * Sets <code>statusCode</code>.
     * 
     * @param statusCode
     *            The <code>statusCode</code> to set.
     */
    public void setStatusCode(String statusCode) {
	this.statusCode = statusCode;
    }

    /**
     * Gets <code>statusDesc</code>.
     * 
     * @return The <code>statusDesc</code>.
     */
    public String getStatusDesc() {
	return statusDesc;
    }

    /**
     * Sets <code>statusDesc</code>.
     * 
     * @param statusDesc
     *            The <code>statusDesc</code> to set.
     */
    public void setStatusDesc(String statusDesc) {
	this.statusDesc = statusDesc;
    }

    /**
     * Gets <code>stan</code>.
     * 
     * @return The <code>stan</code>.
     */
    public String getStan() {
	return stan;
    }

    /**
     * Sets <code>stan</code>.
     * 
     * @param stan
     *            The <code>stan</code> to set.
     */
    public void setStan(String stan) {
	this.stan = stan;
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
     * @param adminFee
     *            The <code>adminFee</code> to set.
     */
    public void setAdminFee(BigDecimal adminFee) {
	this.adminFee = adminFee;
    }

    /**
     * Gets <code>method</code>.
     * 
     * @return The <code>method</code>.
     */
    public String getMethod() {
	return method;
    }

    /**
     * Sets <code>method</code>.
     * 
     * @param method
     *            The <code>method</code> to set.
     */
    public void setMethod(String method) {
	this.method = method;
    }
    
    /**
     * Gets <code>referenceCode</code>.
     * @return The <code>referenceCode</code>.
     */
    public String getReferenceCode() {
        return referenceCode;
    }

    /**
     * Sets <code>referenceCode</code>.
     * @param referenceCode The <code>referenceCode</code> to set.
     */
    public void setReferenceCode(String referenceCode) {
        this.referenceCode = referenceCode;
    }
    
    /* Functionalities: x */

    /* Overrides: */

    /*
     * (non-Javadoc)
     * 
     * @see id.co.asyst.foundation.common.model.BaseObject#toString()
     */
    @Override
    public String toString() {
	ToStringBuilder builder = new ToStringBuilder(this);
	builder.append("amount", amount);
	builder.append("message", message);
	builder.append("postingFrom", postingFrom);
	builder.append("postingTo", postingTo);
	builder.append("confirmationCode", confirmationCode);
	builder.append("statusCode", statusCode);
	builder.append("statusDesc", statusDesc);
	builder.append("stan", stan);
	builder.append("adminFee", adminFee);
	builder.append("method", method);
	builder.append("referenceCode", referenceCode);
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
	InquiryAccountRes other = (InquiryAccountRes) obj;
	if (adminFee == null) {
	    if (other.adminFee != null)
		return false;
	} else if (!adminFee.equals(other.adminFee))
	    return false;
	if (amount == null) {
	    if (other.amount != null)
		return false;
	} else if (!amount.equals(other.amount))
	    return false;
	if (confirmationCode == null) {
	    if (other.confirmationCode != null)
		return false;
	} else if (!confirmationCode.equals(other.confirmationCode))
	    return false;
	if (message == null) {
	    if (other.message != null)
		return false;
	} else if (!message.equals(other.message))
	    return false;
	if (method == null) {
	    if (other.method != null)
		return false;
	} else if (!method.equals(other.method))
	    return false;
	if (postingFrom == null) {
	    if (other.postingFrom != null)
		return false;
	} else if (!postingFrom.equals(other.postingFrom))
	    return false;
	if (postingTo == null) {
	    if (other.postingTo != null)
		return false;
	} else if (!postingTo.equals(other.postingTo))
	    return false;
	if (stan == null) {
	    if (other.stan != null)
		return false;
	} else if (!stan.equals(other.stan))
	    return false;
	if (statusCode == null) {
	    if (other.statusCode != null)
		return false;
	} else if (!statusCode.equals(other.statusCode))
	    return false;
	if (statusDesc == null) {
	    if (other.statusDesc != null)
		return false;
	} else if (!statusDesc.equals(other.statusDesc))
	    return false;
	if (referenceCode == null) {
	    if (other.referenceCode != null)
		return false;
	} else if (!referenceCode.equals(other.referenceCode))
	    return false;
	return true;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((adminFee == null) ? 0 : adminFee.hashCode());
	result = prime * result + ((amount == null) ? 0 : amount.hashCode());
	result = prime * result + ((confirmationCode == null) ? 0 : confirmationCode.hashCode());
	result = prime * result + ((message == null) ? 0 : message.hashCode());
	result = prime * result + ((method == null) ? 0 : method.hashCode());
	result = prime * result + ((postingFrom == null) ? 0 : postingFrom.hashCode());
	result = prime * result + ((postingTo == null) ? 0 : postingTo.hashCode());
	result = prime * result + ((stan == null) ? 0 : stan.hashCode());
	result = prime * result + ((statusCode == null) ? 0 : statusCode.hashCode());
	result = prime * result + ((statusDesc == null) ? 0 : statusDesc.hashCode());
	result = prime * result + ((referenceCode == null) ? 0 : referenceCode.hashCode());
	return result;
    }
}
