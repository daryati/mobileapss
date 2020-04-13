/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.payment.model.payload;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import id.co.asyst.foundation.common.model.IdBasedObject;

/**
 * Model for Frequently Asked Question
 * 
 * @author Kartika Dwi H
 * @version $Revision$, Mar 16, 2020
 * @since 1.0.Alpha1
 */
//@JsonIgnoreProperties(ignoreUnknown=true)
public class PrefixTelcoMapper extends IdBasedObject implements Serializable {

    /* Constants: */

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /* Attributes: */
    
    /**
	 * prefix no
	 */
    @JsonIgnore
    private String prefixNo;
	
	/**
	 * provider
	 */
    @JsonProperty("provider")
    private String provider;
    
    /**
	 * p group
	 */
    @JsonProperty("pGroup")
    private String pgroup;
    
    /**
	 * type
	 */
    @JsonIgnore
    private String type;
    
    /**
	 * picture
	 */
    @JsonProperty("picture")
    private String picture;
    
    
    @JsonIgnore
    //@JsonProperty("institution")
    private List<InstitutionMapper> institutions;
    
    /**
	 * pulsa
	 */
    @JsonProperty("pulsa")
    private PrefixTelcoDetailMapper<?> pulsa;
	
	/**
	 * paket Data
	 */
	@JsonProperty("paketData")
    private PrefixTelcoDetailMapper<?> mobileData;

	public String getPrefixNo() {
		return prefixNo;
	}

	public void setPrefixNo(String prefixNo) {
		this.prefixNo = prefixNo;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public String getPgroup() {
		return pgroup;
	}

	public void setPgroup(String pgroup) {
		this.pgroup = pgroup;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	
	public List<InstitutionMapper> getInstitutions() {
		return institutions;
	}

	public void setInstitutions(List<InstitutionMapper> institutions) {
		this.institutions = institutions;
	}

	public PrefixTelcoDetailMapper<?> getPulsa() {
		return pulsa;
	}

	public void setPulsa(PrefixTelcoDetailMapper<?> pulsa) {
		this.pulsa = pulsa;
	}

	public PrefixTelcoDetailMapper<?> getMobileData() {
		return mobileData;
	}

	public void setMobileData(PrefixTelcoDetailMapper<?> mobileData) {
		this.mobileData = mobileData;
	}

	@Override
	public String toString() {
		return "PrefixTelcoMapper [prefixNo=" + prefixNo + ", provider="
				+ provider + ", pgroup=" + pgroup + ", type=" + type
				+ ", picture=" + picture + ", institutions="
				+ institutions + ", pulsa=" + pulsa + ", mobileData="
				+ mobileData + "]";
	}

    
    }
