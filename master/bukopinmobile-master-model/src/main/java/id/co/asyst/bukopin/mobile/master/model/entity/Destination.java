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

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import id.co.asyst.bukopin.mobile.user.model.entity.User;
import id.co.asyst.foundation.common.model.IdBasedObjectAclAware;

/**
 * Destination Model
 * 
 * @author Ihsan Firman
 * @version $Revision$, Dec 7, 2019
 * @since 2.0
 */
@Entity
@Table(name = "DESTINATION")
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class Destination extends IdBasedObjectAclAware implements Serializable {

    /* Constants: */
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /* Attributes: */
    @Column(name = "SUBSCRIBER_NUMBER")
    private String subscriberNumber;

    @Column(name = "ALIAS", length=15)
    private String alias;

    @Column(name = "SUBSCRIBER_NAME")
    private String subscriberName;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "ID_USER", referencedColumnName = "id")
    private User user;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "ID_CATEGORY", referencedColumnName = "ID_CATEGORY", nullable = false)
    private PurchaseCategory category;

    @Column(name = "IS_FAVORITE")
    @Type(type = "yes_no")
    private Boolean isFavorite;

    @Column(name = "DESTINATION_TYPE")
    private String destinationType;

    /* Transient Attributes: */

    @Transient
    private Long categoryId;

    @Transient
    private String userName;

    /* Constructors: */

    /* Getters & setters for attributes: */

    /**
     * Gets <code>alias</code>.
     * 
     * @return The <code>alias</code>.
     */
    public String getAlias() {
	return alias;
    }

    /**
     * Sets <code>alias</code>.
     * 
     * @param alias The <code>alias</code> to set.
     */
    public void setAlias(String alias) {
	this.alias = alias;
    }

    /**
     * Gets <code>userId</code>.
     * 
     * @return The <code>userId</code>.
     */
    public User getUser() {
	return user;
    }

    /**
     * Sets <code>userId</code>.
     * 
     * @param userId The <code>userId</code> to set.
     */
    public void setUser(User user) {
	this.user = user;
    }

    /**
     * Gets <code>category</code>.
     * 
     * @return The <code>category</code>.
     */
    public PurchaseCategory getCategory() {
	return category;
    }

    /**
     * Sets <code>category</code>.
     * 
     * @param category The <code>category</code> to set.
     */
    public void setCategory(PurchaseCategory category) {
	this.category = category;
    }

    /**
     * Gets <code>isFavorite</code>.
     * 
     * @return The <code>isFavorite</code>.
     */
    public Boolean getIsFavorite() {
	return isFavorite;
    }

    /**
     * Sets <code>isFavorite</code>.
     * 
     * @param isFavorite The <code>isFavorite</code> to set.
     */
    public void setIsFavorite(Boolean isFavorite) {
	this.isFavorite = isFavorite;
    }

    /**
     * Gets <code>subscriberNumber</code>.
     * 
     * @return The <code>subscriberNumber</code>.
     */
    public String getSubscriberNumber() {
	return subscriberNumber;
    }

    /**
     * Sets <code>subscriberNumber</code>.
     * 
     * @param subscriberNumber The <code>subscriberNumber</code> to set.
     */
    public void setSubscriberNumber(String subscriberNumber) {
	this.subscriberNumber = subscriberNumber;
    }

    /**
     * Gets <code>subscriberName</code>.
     * 
     * @return The <code>subscriberName</code>.
     */
    public String getSubscriberName() {
	return subscriberName;
    }

    /**
     * Sets <code>subscriberName</code>.
     * 
     * @param subscriberName The <code>subscriberName</code> to set.
     */
    public void setSubscriberName(String subscriberName) {
	this.subscriberName = subscriberName;
    }

    /**
     * Gets <code>categoryId</code>.
     * 
     * @return The <code>categoryId</code>.
     */
    public Long getCategoryId() {
	return categoryId;
    }

    /**
     * Sets <code>categoryId</code>.
     * 
     * @param categoryId The <code>categoryId</code> to set.
     */
    public void setCategoryId(Long categoryId) {
	this.categoryId = categoryId;
    }

    /**
     * Gets <code>idUser</code>.
     * 
     * @return The <code>idUser</code>.
     */
    public String getUserName() {
	return userName;
    }

    /**
     * Sets <code>idUser</code>.
     * 
     * @param idUser The <code>idUser</code> to set.
     */
    public void setUserName(String name) {
	this.userName = name;
    }

    /**
     * Gets <code>counter</code>.
     * 
     * @return The <code>counter</code>.
     */
    public String getDestinationType() {
	return destinationType;
    }

    /**
     * Sets <code>counter</code>.
     * 
     * @param counter The <code>counter</code> to set.
     */
    public void setDestinationType(String destinationType) {
	this.destinationType = destinationType;
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
	return "Destination [subscriberNumber=" + subscriberNumber + ", alias=" + alias + ", subscriberName="
		+ subscriberName + ", user=" + user + ", category=" + category + ", isFavorite=" + isFavorite
		+ ", destinationType=" + destinationType + ", categoryId=" + categoryId + ", userName=" + userName + "]";
    }
}
