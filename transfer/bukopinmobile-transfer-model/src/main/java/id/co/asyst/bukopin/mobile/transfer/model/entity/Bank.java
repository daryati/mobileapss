/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.transfer.model.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import id.co.asyst.bukopin.mobile.user.model.entity.AccountInfo;
import id.co.asyst.foundation.common.model.IdBasedObject;
import id.co.asyst.foundation.common.model.IdBasedObjectAclAware;

/**
 * Bank Entity Model
 * 
 * @author Kartika Dwi Handini
 * @version $Revision$, Nov 25, 2019
 * @since 2.0
 */
@Entity
@Table(name = "BANK")
@JsonInclude(Include.NON_NULL)
public class Bank extends IdBasedObject implements Serializable {
    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = 1L;

    /* Constants: */

    /* Attributes: */
    
    /* bank name */
    @NotBlank
    @Column(name = "BANK_NAME")
    @JsonProperty("bankName")
    private String bankName;

    /*bank code */
    @NotBlank
    @Column(name = "BANK_CODE", unique = true)
    @JsonProperty("bankCode")
    private String bankCode;

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */  

    /**
     * Gets <code>bankName</code>.
     * 
     * @return The <code>bankName</code>.
     */
    public String getBankName() {
	return bankName;
    }

    /**
     * Sets <code>bankName</code>.
     * 
     * @param bankName The <code>bankName</code> to set.
     */
    public void setBankName(String bankName) {
	this.bankName = bankName;
    }
    

    /**
     * Gets <code>bankCode</code>.
     * 
     * @return The <code>bankCode</code>.
     */
    public String getBankCode() {
        return bankCode;
    }

    /**
     * Sets <code>bankCode</code>.
     * 
     * @param bankCode The <code>bankCode</code> to set.
     */
    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }
    
    
    /* Getters & setters for transient attributes: */

    /* Functionalities: */

   

    @Override
    public String toString() {
	return "Bank [ bankName=" + bankName + ", bankCode=" + bankCode + "]";
    }

}
