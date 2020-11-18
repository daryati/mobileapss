/*
 * $Id$
 * 
 * Copyright (c) 2020 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.user.model.entity.elastic;

import java.util.Date;

import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import id.co.asyst.bukopin.mobile.user.model.entity.CustomerLogin;

/**
 * Elasticsearch Index(Entity) of Customer Login Data
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Nov 18, 2020
 * @since 1.4.Alpha1
 */
@Document(indexName= "bkpm-login")
public class CustomerLoginElastic {

    /* Constants: */

    /* Attributes: */
    private String id;
    
    private String username;

    @Field(name="dateTime", type=FieldType.Date, format=DateFormat.date_time)
    private Date dateTime;

    /* Transient Attributes: */

    /* Constructors: */
    /**
     * @param customerLogin
     */
    public CustomerLoginElastic() {
	
    }
    
    /**
     * @param customerLogin
     */
    public CustomerLoginElastic(CustomerLogin customerLogin) {
	this.username = customerLogin.getUsername();
	this.dateTime = customerLogin.getLoginAt();
    }

    /* Getters & setters for attributes: */
    /**
     * Gets <code>id</code>.
     * @return The <code>id</code>.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets <code>id</code>.
     * @param id The <code>id</code> to set.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets <code>username</code>.
     * @return The <code>username</code>.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets <code>username</code>.
     * @param username The <code>username</code> to set.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets <code>dateTime</code>.
     * @return The <code>dateTime</code>.
     */
    public Date getDateTime() {
        return dateTime;
    }

    /**
     * Sets <code>dateTime</code>.
     * @param dateTime The <code>dateTime</code> to set.
     */
    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "CustomerLoginElastic [id=" + id + ", username=" + username + ", dateTime=" + dateTime + "]";
    }
    
    
}
