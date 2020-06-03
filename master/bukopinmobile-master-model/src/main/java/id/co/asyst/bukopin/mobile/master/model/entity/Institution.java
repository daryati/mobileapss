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
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import id.co.asyst.bukopin.mobile.master.model.entity.cms.InstitutionCategory;
import id.co.asyst.foundation.common.model.IdBasedObject;

/**
 * Model for Institution
 * 
 * @author Kartika Dwi H
 * @version $Revision$, Feb 13, 2020
 * @since 1.0.Alpha1
 */
@Entity
@Table(name = "INSTITUTION")
public class Institution extends IdBasedObject implements Serializable {

    /* Constants: */
    /**
    * serialVersionUID
    */
    private static final long serialVersionUID = 1L;

    /* Attributes: */
    /**
     * code arranet
     */
    @JsonProperty("codeArra")
    @Column(name = "code_arra", length = 255)
    private String codeArra;

    /**
     * code cbs
     */
    @JsonProperty("codeCbs")
    @Column(name = "code_cbs", length = 50)
    private String codeCbs;

    /**
     * name
     */
    @JsonProperty("name")
    @Column(name = "name", length = 50)
    private String name;

    /**
     * status
     */
    @JsonProperty("status")
    @Column(name = "status")
    private Boolean status;

    /**
     * bill sumarry
     */
    @JsonProperty("billSummary")
    @Column(name = "bill_summary")
    private Boolean billSummary;

    /**
     * institution type
     */
    @JsonProperty("institutionType")
    @Column(name = "institution_type", length = 50)
    private String institutionType;

    /**
     * admin fee
     */
    @JsonProperty("adminFee")
    @Column(name = "admin_fee")
    private BigDecimal adminFee;

    /**
     * display message
     */
    @JsonProperty("displayMessage")
    @Column(name = "display_message", length = 100)
    private String displayMessage;

    /**
     * selection
     */
    @Column(name = "selection", length = 1000)
    @JsonProperty("selection")
    private String selection;

    /**
     * name local
     */
    @Column(name = "name_local", length = 255)
    @JsonProperty("nameLocal")
    private String nameLocal;

    /**
     * name english
     */
    @JsonProperty("nameEnglish")
    @Column(name = "name_english", length = 255)
    private String nameEnglish;

    /**
     * prefix telco id
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PREFIX_TELCO_ID")
    @JsonProperty("prefixTelcoId")
    private PrefixTelco prefixTelcoId;

    /**
     * institution category
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "INSTITUTION_CATEGORY_ID")
    @JsonProperty("insCategoryId")
    private InstitutionCategory insCategoryId;

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
     * Gets <code>status</code>.
     * 
     * @return The <code>status</code>.
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
     * Gets <code>billSummary</code>.
     * 
     * @return The <code>billSummary</code>.
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
     * Gets <code>prefixTelcoId</code>.
     * 
     * @return The <code>prefixTelcoId</code>.
     */
    public PrefixTelco getPrefixTelcoId() {
	return prefixTelcoId;
    }

    /**
     * Sets <code>prefixTelcoId</code>.
     * 
     * @param prefixTelcoId
     *            The <code>prefixTelcoId</code> to set.
     */
    public void setPrefixTelcoId(PrefixTelco prefixTelcoId) {
	this.prefixTelcoId = prefixTelcoId;
    }

    /**
     * Gets <code>insCategoryId</code>.
     * 
     * @return The <code>insCategoryId</code>.
     */
    public InstitutionCategory getInsCategoryId() {
	return insCategoryId;
    }

    /**
     * Sets <code>insCategoryId</code>.
     * 
     * @param insCategoryId
     *            The <code>insCategoryId</code> to set.
     */
    public void setInsCategoryId(InstitutionCategory insCategoryId) {
	this.insCategoryId = insCategoryId;
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

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "Institution [codeArra=" + codeArra + ", codeCbs=" + codeCbs + ", name=" + name + ", status=" + status
		+ ", billSummary=" + billSummary + ", institutionType=" + institutionType + ", adminFee=" + adminFee
		+ ", displayMessage=" + displayMessage + ", selection=" + selection + ", nameLocal=" + nameLocal
		+ ", nameEnglish=" + nameEnglish + ", prefixTelcoId=" + prefixTelcoId + ", insCategoryId="
		+ insCategoryId + "]";
    }

}
