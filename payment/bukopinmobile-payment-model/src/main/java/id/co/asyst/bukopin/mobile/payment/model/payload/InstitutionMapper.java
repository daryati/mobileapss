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
import com.fasterxml.jackson.annotation.JsonProperty;

import id.co.asyst.foundation.common.model.IdBasedObject;

/**
 * Model for Frequently Asked Question
 * 
 * @author Kartika Dwi H
 * @version $Revision$, Feb 13, 2020
 * @since 1.0.Alpha1
 */
public class InstitutionMapper extends IdBasedObject  implements Serializable {

    /* Constants: */
	 /**
     * 
     */
    private static final long serialVersionUID = 1L;
	
    /* Attributes: */
    /**
	 * prefix no
	 */
    
    @JsonProperty("codeArra")
    private String codeArra;
    
    @JsonProperty("codeCbs")
    private String codeCbs;
    
    private String name;
	
    @JsonProperty("status")
    private Boolean status;
	
    @JsonProperty("billSummary")
    private Boolean billSummary;
    
    @JsonProperty("institutionType")
    private String institutionType;
    
    @JsonProperty("adminFee")
    private BigDecimal adminFee;
	
    @JsonProperty("displayMessage")
    private String displayMessage;    
    
    @Column(name = "selection")
    private String selection;
    
    @Column(name = "name_local", length = 255)
    private String nameLocal;
	
    @JsonProperty("nameEnglish")
    private String nameEnglish;
	
    @JsonProperty("prefixTelcoId")
    private PrefixTelcoMapper prefixTelcoMapper;
    
   

	public String getCodeArra() {
		return codeArra;
	}

	public void setCodeArra(String codeArra) {
		this.codeArra = codeArra;
	}

	public String getCodeCbs() {
		return codeCbs;
	}

	public void setCodeCbs(String codeCbs) {
		this.codeCbs = codeCbs;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Boolean getBillSummary() {
		return billSummary;
	}

	public void setBillSummary(Boolean billSummary) {
		this.billSummary = billSummary;
	}

	public String getInstitutionType() {
		return institutionType;
	}

	public void setInstitutionType(String institutionType) {
		this.institutionType = institutionType;
	}

	public BigDecimal getAdminFee() {
		return adminFee;
	}

	public void setAdminFee(BigDecimal adminFee) {
		this.adminFee = adminFee;
	}

	public String getDisplayMessage() {
		return displayMessage;
	}

	public void setDisplayMessage(String displayMessage) {
		this.displayMessage = displayMessage;
	}

	public String getSelection() {
		return selection;
	}

	public void setSelection(String selection) {
		this.selection = selection;
	}

	public String getNameLocal() {
		return nameLocal;
	}

	public void setNameLocal(String nameLocal) {
		this.nameLocal = nameLocal;
	}

	public String getNameEnglish() {
		return nameEnglish;
	}

	public void setNameEnglish(String nameEnglish) {
		this.nameEnglish = nameEnglish;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	

	

	public PrefixTelcoMapper getPrefixTelcoMapper() {
		return prefixTelcoMapper;
	}

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
