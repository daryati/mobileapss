/*
 * $Id$
 * 
 * Copyright (c) 2020 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.master.model.entity.cms;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import id.co.asyst.foundation.common.model.IdBasedObject;

/**
 * Push Notification Model
 * 
 * @author Abi Nanel
 * @version $Revision$, Jun 3, 2020
 * @since 1.1.Alpha1
 */
@Entity
@Table(name = "PUSH_NOTIFICATION")
@JsonIgnoreProperties(ignoreUnknown = true)
public class PushNotification extends IdBasedObject {

    /* Constants: */
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /* Attributes: */
    /**
     * title
     */
    @JsonProperty("title")
    @Column(name = "title", length = 50, nullable = false)
    private String title;

    /**
     * content
     */
    @JsonProperty("content")
    @Column(name = "content", length = 255, nullable = false)
    private String content;

    /**
     * img
     */
    @JsonProperty("img")
    @Column(name = "img", length = 50, nullable = true)
    private String img;

    /**
     * createdBy
     */
    @JsonProperty("createdBy")
    @Column(name = "created_by", length = 50, nullable = false)
    private String createdBy;

    /**
     * createdBy
     */
    @Column(name = "created_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    /**
     * updatedBy
     */
    @JsonProperty("updatedBy")
    @Column(name = "updated_by", length = 50, nullable = true)
    private String updatedBy;

    /**
     * updatedDate
     */
    @Column(name = "updated_date", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */
    /**
     * Gets <code>title</code>.
     * 
     * @return The <code>title</code>.
     */
    public String getTitle() {
	return title;
    }

    /**
     * Sets <code>title</code>.
     * 
     * @param title
     *            The <code>title</code> to set.
     */
    public void setTitle(String title) {
	this.title = title;
    }

    /**
     * Gets <code>content</code>.
     * 
     * @return The <code>content</code>.
     */
    public String getContent() {
	return content;
    }

    /**
     * Sets <code>content</code>.
     * 
     * @param content
     *            The <code>content</code> to set.
     */
    public void setContent(String content) {
	this.content = content;
    }

    /**
     * Gets <code>img</code>.
     * 
     * @return The <code>img</code>.
     */
    public String getImg() {
	return img;
    }

    /**
     * Sets <code>img</code>.
     * 
     * @param img
     *            The <code>img</code> to set.
     */
    public void setImg(String img) {
	this.img = img;
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
     * @param createdBy
     *            The <code>createdBy</code> to set.
     */
    public void setCreatedBy(String createdBy) {
	this.createdBy = createdBy;
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
     * @param createdDate
     *            The <code>createdDate</code> to set.
     */
    public void setCreatedDate(Date createdDate) {
	this.createdDate = createdDate;
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
     * @param updatedBy
     *            The <code>updatedBy</code> to set.
     */
    public void setUpdatedBy(String updatedBy) {
	this.updatedBy = updatedBy;
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
     * @param updatedDate
     *            The <code>updatedDate</code> to set.
     */
    public void setUpdatedDate(Date updatedDate) {
	this.updatedDate = updatedDate;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "PushNotification [title=" + title + ", content=" + content + ", img=" + img + ", createdBy=" + createdBy
		+ ", createdDate=" + createdDate + ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate + "]";
    }

    /* Getters & setters for transient attributes: */

    /* Functionalities: */

    /* Overrides: */

}
