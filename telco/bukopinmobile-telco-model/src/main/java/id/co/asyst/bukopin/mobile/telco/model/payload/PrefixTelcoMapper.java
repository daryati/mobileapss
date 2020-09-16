/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.telco.model.payload;

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
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import id.co.asyst.foundation.common.model.IdBasedObject;

/**
 * Model for Frequently Asked Question
 * 
 * @author Kartika Dwi H
 * @version $Revision$, Feb 13, 2020
 * @param <T>
 * @since 1.0.Alpha1
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class PrefixTelcoMapper<T> extends IdBasedObject implements Serializable {

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
    
    /**
	 * institutions
	 */
    @JsonIgnore
    //@JsonProperty("institution")
    private List<InstitutionMapper> institutions;
    
    /**
	 * pulsa
	 */
    @JsonProperty("pulsa")
    private PrefixTelcoDetailMapper<T> pulsa;
	
	/**
	 * paket Data
	 */
	@JsonProperty("paketData")
    private PrefixTelcoDetailMapper<T> mobileData;

	
	/* Transient Attributes: */

    /* Constructors: */
   

    /* Getters & setters for attributes: */

	/* Getters & setters for attributes: */
    /**
     * Gets <code>prefixNo</code>.
     * 
     * @return The <code>prefixNo</code>.
     */
	public String getPrefixNo() {
		return prefixNo;
	}

	/**
     * Sets <code>prefixNo</code>.
     * 
     * @param prefixNo
     *            The <code>prefixNo</code> to set.
     */
	public void setPrefixNo(String prefixNo) {
		this.prefixNo = prefixNo;
	}

	/**
     * Gets <code>provider</code>.
     * 
     * @return The <code>provider</code>.
     */
	public String getProvider() {
		return provider;
	}

	/**
     * Sets <code>provider</code>.
     * 
     * @param provider
     *            The <code>provider</code> to set.
     */
	public void setProvider(String provider) {
		this.provider = provider;
	}

	/**
     * Gets <code>pgroup</code>.
     * 
     * @return The <code>pgroup</code>.
     */
	public String getPgroup() {
		return pgroup;
	}

	/**
     * Sets <code>pgroup</code>.
     * 
     * @param pgroup
     *            The <code>pgroup</code> to set.
     */
	public void setPgroup(String pgroup) {
		this.pgroup = pgroup;
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
     * Gets <code>picture</code>.
     * 
     * @return The <code>picture</code>.
     */
	public String getPicture() {
		return picture;
	}

	/**
     * Sets <code>picture</code>.
     * 
     * @param picture
     *            The <code>picture</code> to set.
     */
	public void setPicture(String picture) {
		this.picture = picture;
	}

	/**
     * Gets <code>institutions</code>.
     * 
     * @return The <code>institutions</code>.
     */
	public List<InstitutionMapper> getInstitutions() {
		return institutions;
	}

	/**
     * Sets <code>institutions</code>.
     * 
     * @param institutions
     *            The <code>institutions</code> to set.
     */
	public void setInstitutions(List<InstitutionMapper> institutions) {
		this.institutions = institutions;
	}

	/**
     * Gets <code>pulsa</code>.
     * 
     * @return The <code>pulsa</code>.
     */
	public PrefixTelcoDetailMapper<T> getPulsa() {
		return pulsa;
	}

	/**
     * Sets <code>pulsa</code>.
     * 
     * @param pulsa
     *            The <code>pulsa</code> to set.
     */
	public void setPulsa(PrefixTelcoDetailMapper<T> pulsa) {
		this.pulsa = pulsa;
	}

	/**
     * Gets <code>mobileData</code>.
     * 
     * @return The <code>mobileData</code>.
     */
	public PrefixTelcoDetailMapper<T> getMobileData() {
		return mobileData;
	}

	/**
     * Sets <code>mobileData</code>.
     * 
     * @param mobileData
     *            The <code>mobileData</code> to set.
     */
	public void setMobileData(PrefixTelcoDetailMapper<T> mobileData) {
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
