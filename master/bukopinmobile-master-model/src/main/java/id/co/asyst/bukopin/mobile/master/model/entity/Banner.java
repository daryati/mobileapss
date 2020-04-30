/*
 * $Id$
 * 
 * Copyright (c) 2020 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
/*
 * Copyright (c) 2020 Lufthansa Systems Indonesia, PT. All rights reserved.
 */
package id.co.asyst.bukopin.mobile.master.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import id.co.asyst.foundation.base.model.IdBasedObject;

/**
 * 
 * 
 * @author Ihsan Firman
 * @version $Revision$, Apr 29, 2020
 * @since 2.0
 */

@Entity
@Table(name = "BANNER")
public class Banner extends IdBasedObject{
	/* Constants: */

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/* Attributes: */
	@Column(name = "TITLE")
	private String title;
	
	@Column(name = "SUB_TITLE")
	private String subTitle;
	
	@Column(name = "CONTENT")
	private String content;
	
	@Column(name = "IMG")
	private String img;
	
	@Column(name = "STATUS")
	private Boolean status;

	
	/* Transient Attributes: */

	/* Constructors: */
	/**
	 * 
	 */
	public Banner() {
		// TODO Auto-generated constructor stub
	}

	/* Getters & setters for attributes: */
	/**
	 * Gets <code>title</code>.
	 * @return The <code>title</code>.
	 */
	public String getTitle() {
		return title;
	}


	/**
	 * Sets <code>title</code>.
	 * @param title The <code>title</code> to set.
	 */
	public void setTitle(String title) {
		this.title = title;
	}


	/**
	 * Gets <code>subTitle</code>.
	 * @return The <code>subTitle</code>.
	 */
	public String getSubTitle() {
		return subTitle;
	}


	/**
	 * Sets <code>subTitle</code>.
	 * @param subTitle The <code>subTitle</code> to set.
	 */
	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}


	/**
	 * Gets <code>content</code>.
	 * @return The <code>content</code>.
	 */
	public String getContent() {
		return content;
	}


	/**
	 * Sets <code>content</code>.
	 * @param content The <code>content</code> to set.
	 */
	public void setContent(String content) {
		this.content = content;
	}


	/**
	 * Gets <code>img</code>.
	 * @return The <code>img</code>.
	 */
	public String getImg() {
		return img;
	}


	/**
	 * Sets <code>img</code>.
	 * @param img The <code>img</code> to set.
	 */
	public void setImg(String img) {
		this.img = img;
	}


	/**
	 * Gets <code>status</code>.
	 * @return The <code>status</code>.
	 */
	public Boolean getStatus() {
		return status;
	}


	/**
	 * Sets <code>status</code>.
	 * @param status The <code>status</code> to set.
	 */
	public void setStatus(Boolean status) {
		this.status = status;
	}

	/* Getters & setters for transient attributes: */

	/* Functionalities: */

	/* Overrides: */
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Banner [title=" + title + ", subTitle=" + subTitle + ", content=" + content + ", img=" + img
				+ ", status=" + status + "]";
	}

}
