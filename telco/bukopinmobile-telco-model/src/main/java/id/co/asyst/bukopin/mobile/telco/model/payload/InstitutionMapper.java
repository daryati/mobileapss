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
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import id.co.asyst.foundation.common.model.IdBasedObject;

/**
 * Model for Frequently Asked Question
 * 
 * @author Kartika Dwi H
 * @version $Revision$, Feb 13, 2020
 * @since 1.0.Alpha1
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class InstitutionMapper extends IdBasedObject  implements Serializable {

    /* Constants: */
	 /**
     * 
     */
    private static final long serialVersionUID = 1L;
	
    /* Attributes: */
    
    /**
	 * codeArra
	 */    
    @JsonProperty("codeArra")
    private String codeArra;
    
    /**
	 * codeCbs
	 */ 
    @JsonProperty("codeCbs")
    private String codeCbs;
    
    /**
	 * name
	 */ 
    private String name;
	
    /**
	 * status
	 */ 
    @JsonProperty("status")
    private Boolean status;
	
    /**
	 * billSummary
	 */ 
    @JsonProperty("billSummary")
    private Boolean billSummary;
    
    /**
	 * institutionType
	 */ 
    @JsonProperty("institutionType")
    private String institutionType;
    
    /**
	 * adminFee
	 */ 
    @JsonProperty("adminFee")
    private BigDecimal adminFee;
	
    /**
	 * displayMessage
	 */ 
    @JsonProperty("displayMessage")
    private String displayMessage;    
    
    /**
	 * selection
	 */ 
    @Column(name = "selection")
    private String selection;
    
    /**
	 * nameLocal
	 */ 
    @Column(name = "name_local", length = 255)
    private String nameLocal;
	
    /**
	 * nameEnglish
	 */ 
    @JsonProperty("nameEnglish")
    private String nameEnglish;
	
    /**
	 * prefixTelcoMapper
	 */ 
    @JsonProperty("prefixTelcoId")
    private PrefixTelcoMapper prefixTelcoMapper;
    
   
    /* Transient Attributes: */

    /* Constructors: */
   

    /* Getters & setters for attributes: */

	/* Getters & setters for attributes: */
    /**
     * Gets <code>codeArra</code>.
     * 
     * @return The <code>codeArra</code>.
     */
	public String getCodeArra() {
		return codeArra;
	}

	/**
     * Sets <code>codeArra</code>.
     * 
     * @param codeArra
     *            The <code>codeArra</code> to set.
     */
	public void setCodeArra(String codeArra) {
		this.codeArra = codeArra;
	}

	/**
     * Gets <code>codeCbs</code>.
     * 
     * @return The <code>codeCbs</code>.
     */
	public String getCodeCbs() {
		return codeCbs;
	}

	/**
     * Sets <code>codeCbs</code>.
     * 
     * @param codeCbs
     *            The <code>codeCbs</code> to set.
     */
	public void setCodeCbs(String codeCbs) {
		this.codeCbs = codeCbs;
	}

	/**
     * Gets <code>name</code>.
     * 
     * @return The <code>name</code>.
     */
	public String getName() {
		return name;
	}

	/**
     * Sets <code>name</code>.
     * 
     * @param name
     *            The <code>name</code> to set.
     */
	public void setName(String name) {
		this.name = name;
	}

	/**
     * Gets <code>status</code>.
     * 
     * @return The <code>status</code>.
     */
	public Boolean getStatus() {
		return status;
	}

	/**
     * Sets <code>status</code>.
     * 
     * @param status
     *            The <code>status</code> to set.
     */
	public void setStatus(Boolean status) {
		this.status = status;
	}

	/**
     * Gets <code>billSummary</code>.
     * 
     * @return The <code>billSummary</code>.
     */
	public Boolean getBillSummary() {
		return billSummary;
	}

	/**
     * Sets <code>billSummary</code>.
     * 
     * @param billSummary
     *            The <code>billSummary</code> to set.
     */
	public void setBillSummary(Boolean billSummary) {
		this.billSummary = billSummary;
	}

	/**
     * Gets <code>institutionType</code>.
     * 
     * @return The <code>institutionType</code>.
     */
	public String getInstitutionType() {
		return institutionType;
	}

	/**
     * Sets <code>institutionType</code>.
     * 
     * @param institutionType
     *            The <code>institutionType</code> to set.
     */
	public void setInstitutionType(String institutionType) {
		this.institutionType = institutionType;
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
     * Gets <code>displayMessage</code>.
     * 
     * @return The <code>displayMessage</code>.
     */
	public String getDisplayMessage() {
		return displayMessage;
	}

	/**
     * Sets <code>displayMessage</code>.
     * 
     * @param displayMessage
     *            The <code>displayMessage</code> to set.
     */
	public void setDisplayMessage(String displayMessage) {
		this.displayMessage = displayMessage;
	}

	/**
     * Gets <code>selection</code>.
     * 
     * @return The <code>selection</code>.
     */
	public String getSelection() {
		return selection;
	}

	/**
     * Sets <code>selection</code>.
     * 
     * @param selection
     *            The <code>selection</code> to set.
     */
	public void setSelection(String selection) {
		this.selection = selection;
	}

	/**
     * Gets <code>nameLocal</code>.
     * 
     * @return The <code>nameLocal</code>.
     */
	public String getNameLocal() {
		return nameLocal;
	}

	/**
     * Sets <code>nameLocal</code>.
     * 
     * @param nameLocal
     *            The <code>nameLocal</code> to set.
     */
	public void setNameLocal(String nameLocal) {
		this.nameLocal = nameLocal;
	}

	/**
     * Gets <code>nameEnglish</code>.
     * 
     * @return The <code>nameEnglish</code>.
     */
	public String getNameEnglish() {
		return nameEnglish;
	}

	/**
     * Sets <code>nameEnglish</code>.
     * 
     * @param nameEnglish
     *            The <code>nameEnglish</code> to set.
     */
	public void setNameEnglish(String nameEnglish) {
		this.nameEnglish = nameEnglish;
	}

	/**
     * Gets <code>id</code>.
     * 
     * @return The <code>id</code>.
     */
	public Long getId() {
		return id;
	}

	/**
     * Sets <code>id</code>.
     * 
     * @param id
     *            The <code>id</code> to set.
     */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
     * Gets <code>prefixTelcoMapper</code>.
     * 
     * @return The <code>prefixTelcoMapper</code>.
     */
	public PrefixTelcoMapper getPrefixTelcoMapper() {
		return prefixTelcoMapper;
	}

	/**
     * Sets <code>prefixTelcoMapper</code>.
     * 
     * @param prefixTelcoMapper
     *            The <code>prefixTelcoMapper</code> to set.
     */
	public void setPrefixTelcoMapper(PrefixTelcoMapper prefixTelcoMapper) {
		this.prefixTelcoMapper = prefixTelcoMapper;
	}

	@Override
	public String toString() {
		return "InstitutionMapper [codeArra=" + codeArra + ", codeCbs="
				+ codeCbs + ", name=" + name + ", status=" + status
				+ ", billSummary=" + billSummary + ", institutionType="
				+ institutionType + ", adminFee=" + adminFee
				+ ", displayMessage=" + displayMessage + ", selection="
				+ selection + ", nameLocal=" + nameLocal + ", nameEnglish="
				+ nameEnglish + "]";
	}

	
    
	
	
	
	
	
	
	
	
    
    
    
    
    
    }
