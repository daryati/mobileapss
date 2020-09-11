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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import id.co.asyst.foundation.base.model.IdBasedObject;

/**
 * Banner Model
 * 
 * @author Abi Nanel
 * @version $Revision$, Feb 7, 2020
 * @since 1.1.Alpha1
 */
@Entity
@Table(name = "BANNER")
@JsonIgnoreProperties(ignoreUnknown = true)
@Deprecated
public class Banner extends IdBasedObject {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /* Constants: */

    /* Attributes: */
    @Column(name = "TITLE", length = 50)
    private String title;

    @Column(name = "SUB_TITLE", length = 50)
    private String subTitle;

    @Column(name = "CONTENT", length = 50)
    private String content;

    @Column(name = "IMG", length = 50)
    private String img;

    @Type(type = "yes_no")
    @Column(name = "STATUS")
    private boolean status;

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
     * Gets <code>subTitle</code>.
     * 
     * @return The <code>subTitle</code>.
     */
    public String getSubTitle() {
	return subTitle;
    }

    /**
     * Sets <code>subTitle</code>.
     * 
     * @param subTitle
     *            The <code>subTitle</code> to set.
     */
    public void setSubTitle(String subTitle) {
	this.subTitle = subTitle;
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
     * Gets <code>status</code>.
     * 
     * @return The <code>status</code>.
     */
    public boolean isStatus() {
	return status;
    }

    /**
     * Sets <code>status</code>.
     * 
     * @param status
     *            The <code>status</code> to set.
     */
    public void setStatus(boolean status) {
	this.status = status;
    }

    /* Getters & setters for transient attributes: */

    /* Functionalities: */

    /* Overrides: */
    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "Banner [title=" + title + ", subTitle=" + subTitle + ", content=" + content + ", img=" + img
		+ ", status=" + status + "]";
    }
}
