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
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import id.co.asyst.foundation.base.model.IdBasedObjectAclAware;

/**
 * Model for Frequently Asked Question
 * 
 * @author Ihsan Firman
 * @version $Revision$, Nov 20, 2019
 * @since 2.0
 */
@Entity
@Table(name = "FAQ")
@JsonInclude(Include.NON_NULL)
public class FAQ extends IdBasedObjectAclAware implements Serializable {

    /* Constants: */

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /* Attributes: */
    /**
     * The Question to be asked
     */
    @NotBlank
    @Column(name = "QUESTION", length = 2000, nullable = false)
    private String question;

    /**
     * Answer
     */
    @NotBlank
    @Column(name = "ANSWER", length = 2000, nullable = false)
    private String answer;

    /**
     * Date Created for the FAQ
     */
    @Column(name = "CREATED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    /**
     * user who create this FAQ
     */
    @Column(name = "CREATED_BY")
    private String createdBy;

    /**
     * Updated date of the FAQ
     */
    @Column(name = "UPDATED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;

    /**
     * Username of do some Update for the FAQ's
     */
    @Column(name = "UPDATED_BY")
    private String updatedBy;
    
    /**
     * Sort
     */
    @NotBlank
    @Column(name = "SORT")
    private int sort;

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */
    /**
     * Gets <code>question</code>.
     * 
     * @return The <code>question</code>.
     */
    public String getQuestion() {
	return question;
    }

    /**
     * Sets <code>question</code>.
     * 
     * @param question The <code>question</code> to set.
     */
    public void setQuestion(String question) {
	this.question = question;
    }

    /**
     * Gets <code>answer</code>.
     * 
     * @return The <code>answer</code>.
     */
    public String getAnswer() {
	return answer;
    }

    /**
     * Sets <code>answer</code>.
     * 
     * @param answer The <code>answer</code> to set.
     */
    public void setAnswer(String answer) {
	this.answer = answer;
    }

    /**
     * Gets <code>createdDate</code>.
     * 
     * @return The <code>createdDate</code>.
     */
    public Date getCreatedDate() {
	return createdDate;
    }

    /**
     * Sets <code>createdDate</code>.
     * 
     * @param createdDate The <code>createdDate</code> to set.
     */
    public void setCreatedDate(Date createdDate) {
	this.createdDate = createdDate;
    }

    /**
     * Gets <code>createdBy</code>.
     * 
     * @return The <code>createdBy</code>.
     */
    public String getCreatedBy() {
	return createdBy;
    }

    /**
     * Sets <code>createdBy</code>.
     * 
     * @param createdBy The <code>createdBy</code> to set.
     */
    public void setCreatedBy(String createdBy) {
	this.createdBy = createdBy;
    }

    /**
     * Gets <code>updatedDate</code>.
     * 
     * @return The <code>updatedDate</code>.
     */
    public Date getUpdatedDate() {
	return updatedDate;
    }

    /**
     * Sets <code>updatedDate</code>.
     * 
     * @param updatedDate The <code>updatedDate</code> to set.
     */
    public void setUpdatedDate(Date updatedDate) {
	this.updatedDate = updatedDate;
    }

    /**
     * Gets <code>updatedBy</code>.
     * 
     * @return The <code>updatedBy</code>.
     */
    public String getUpdatedBy() {
	return updatedBy;
    }

    /**
     * Sets <code>updatedBy</code>.
     * 
     * @param updatedBy The <code>updatedBy</code> to set.
     */
    public void setUpdatedBy(String updatedBy) {
	this.updatedBy = updatedBy;
    }

    /**
     * Gets <code>sort</code>.
     * @return The <code>sort</code>.
     */
    public int getSort() {
        return sort;
    }

    /**
     * Sets <code>sort</code>.
     * @param sort The <code>sort</code> to set.
     */
    public void setSort(int sort) {
        this.sort = sort;
    }

    /* Getters & setters for transient attributes: */

    /* Functionalities: */

    /* Overrides: */}
