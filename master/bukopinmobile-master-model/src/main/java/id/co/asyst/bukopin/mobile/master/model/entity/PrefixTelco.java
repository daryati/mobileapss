/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.master.model.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import id.co.asyst.bukopin.mobile.master.model.payload.PrefixTelcoDetailResponse;
import id.co.asyst.foundation.common.model.IdBasedObject;

/**
 * Model for Frequently Asked Question
 * 
 * @author Kartika Dwi H
 * @version $Revision$, Feb 13, 2020
 * @since 1.0.Alpha1
 */
@Entity
@Table(name = "PREFIX_TELCO")
// @JsonIgnoreProperties(ignoreUnknown=true)
public class PrefixTelco extends IdBasedObject implements Serializable {

    /* Constants: */

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /* Attributes: */

    /**
     * prefix no
     */
    @JsonIgnore
    @Column(name = "prefix_no", length = 1000)
    private String prefixNo;

    /**
     * provider
     */
    @JsonProperty("provider")
    @Column(name = "provider", length = 50)
    private String provider;

    /**
     * p group
     */
    @JsonProperty("pGroup")
    @Column(name = "pgroup", length = 50)
    private String pgroup;

    /**
     * picture
     */
    @JsonProperty("description")
    @Column(name = "description", length = 255)
    private String description;

    /**
     * type
     */
    @JsonIgnore
    @Column(name = "type", length = 10)
    private String type;

    /**
     * picture
     */
    @JsonProperty("picture")
    @Column(name = "picture", length = 255)
    private String picture;

    /**
     * institutions
     */
    @JsonIgnore
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "prefixTelcoId", cascade = CascadeType.ALL)
    private List<Institution> institutions;

    /**
     * pulsa
     */
    @Transient
    @JsonProperty("pulsa")
    private PrefixTelcoDetailResponse<?> pulsa;

    /**
     * paket Data
     */
    @Transient
    @JsonProperty("paketData")
    private PrefixTelcoDetailResponse<?> mobileData;

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
     * Gets <code>description</code>.
     * 
     * @return The <code>description</code>.
     */
    public String getDescription() {
	return description;
    }

    /**
     * Sets <code>description</code>.
     * 
     * @param description
     *            The <code>description</code> to set.
     */
    public void setDescription(String description) {
	this.description = description;
    }

    /**
     * Gets <code>institutions</code>.
     * 
     * @return The <code>institutions</code>.
     */
    public List<Institution> getInstitutions() {
	return institutions;
    }

    /**
     * Sets <code>institutions</code>.
     * 
     * @param institutions
     *            The <code>institutions</code> to set.
     */
    public void setInstitutions(List<Institution> institutions) {
	this.institutions = institutions;
    }

    /**
     * Gets <code>pulsa</code>.
     * 
     * @return The <code>pulsa</code>.
     */
    public PrefixTelcoDetailResponse<?> getPulsa() {
	return pulsa;
    }

    /**
     * Sets <code>pulsa</code>.
     * 
     * @param pulsa
     *            The <code>pulsa</code> to set.
     */
    public void setPulsa(PrefixTelcoDetailResponse<?> pulsa) {
	this.pulsa = pulsa;
    }

    /**
     * Gets <code>mobileData</code>.
     * 
     * @return The <code>mobileData</code>.
     */
    public PrefixTelcoDetailResponse<?> getMobileData() {
	return mobileData;
    }

    /**
     * Sets <code>mobileData</code>.
     * 
     * @param mobileData
     *            The <code>mobileData</code> to set.
     */
    public void setMobileData(PrefixTelcoDetailResponse<?> mobileData) {
	this.mobileData = mobileData;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "PrefixTelco [prefixNo=" + prefixNo + ", provider=" + provider + ", pgroup=" + pgroup + ", description="
		+ description + ", type=" + type + ", picture=" + picture + ", institutions=" + institutions
		+ ", pulsa=" + pulsa + ", mobileData=" + mobileData + "]";
    }

}
