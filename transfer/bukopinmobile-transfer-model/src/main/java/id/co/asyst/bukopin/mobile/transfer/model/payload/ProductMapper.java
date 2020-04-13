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

import java.math.BigInteger;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import id.co.asyst.bukopin.mobile.user.model.AccountInfoStatusEnum;
import id.co.asyst.bukopin.mobile.user.model.AccountStatusEnum;
import id.co.asyst.bukopin.mobile.user.model.AccountTypeEnum;
import id.co.asyst.bukopin.mobile.user.model.entity.Product;

/**
 * 
 * 
 * @author Ihsan Firman
 * @version $Revision$, Dec 2, 2019
 * @since 2.0
 */

@JsonIgnoreProperties(ignoreUnknown=true)
public class ProductMapper {
    /* Constants: */

    private Long id;
    
    private String productName;

    private Long pdId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Long getPdId() {
		return pdId;
	}

	public void setPdId(Long pdId) {
		this.pdId = pdId;
	}

	@Override
	public String toString() {
		return "ProductMapper [id=" + id + ", productName=" + productName
				+ ", pdId=" + pdId + ", getId()=" + getId()
				+ ", getProductName()=" + getProductName() + ", getPdId()="
				+ getPdId() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}

    

    /* Constructors: */

    
   

    /* Overrides: */}
