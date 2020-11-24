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
package id.co.asyst.bukopin.mobile.transfer.model.entity.elastic;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import id.co.asyst.bukopin.mobile.transfer.model.entity.FundTransfer;

/**
 *  Elasticsearch Index(Entity) of Transfer/ Overbook
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Nov 19, 2020
 * @since 1.4.Alpha1
 */
@Document(indexName="bkpm-trx")
public class FundTransferElastic {
    /* Constants: */

    /* Attributes: */
    /**
     * Document Identity
     */
    private String id;
    
    /**
     * Username who transfer
     */
    private String username;

    /**
     * Transaction date time 
     */
    @Field(name="dateTime", type=FieldType.Date, format=DateFormat.date_time)
    private Date dateTime;
    
    /**
     * Transaction Amount
     */
    private BigDecimal amount;
    
    /**
     * Admin Fee
     */
    private BigDecimal adminFee;
    
    /**
     * Total Amount
     */
    private BigDecimal totalAmount;
    
    /**
     * Transaction type ("FUND_TRANSFER" or "OVERBOOK")
     */
    private String type;
    
    /**
     * Status transaction ("FAILED" or "SUCCESS")
     */
    private String status;

    
    /* Transient Attributes: */

    /* Constructors: */
    public FundTransferElastic() {
	
    }
    
    /**
     * @param trx Fund Transfer Object
     */
    public FundTransferElastic(FundTransfer trx) {
	this.username = trx.getUsername().getUsername();
	this.dateTime = Date.from(trx.getCreatedOn().atZone(ZoneId.systemDefault()).toInstant());
	this.amount = trx.getAmount();
	this.adminFee = trx.getAdminFee();
	this.type = trx.getMethod();
	this.status = trx.getStatus();
	this.totalAmount = trx.getAmount().add(trx.getAdminFee());
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

    /**
     * Gets <code>amount</code>.
     * @return The <code>amount</code>.
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * Sets <code>amount</code>.
     * @param amount The <code>amount</code> to set.
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * Gets <code>adminFee</code>.
     * @return The <code>adminFee</code>.
     */
    public BigDecimal getAdminFee() {
        return adminFee;
    }

    /**
     * Sets <code>adminFee</code>.
     * @param adminFee The <code>adminFee</code> to set.
     */
    public void setAdminFee(BigDecimal adminFee) {
        this.adminFee = adminFee;
    }

    /**
     * Gets <code>type</code>.
     * @return The <code>type</code>.
     */
    public String getType() {
        return type;
    }

    /**
     * Sets <code>type</code>.
     * @param type The <code>type</code> to set.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets <code>status</code>.
     * @return The <code>status</code>.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets <code>status</code>.
     * @param status The <code>status</code> to set.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
    
    /* Getters & setters for transient attributes: */

    /* Functionalities: */

	@Override
	public String toString() {
		return "FundTransferElastic [id=" + id + ", username=" + username + ", dateTime=" + dateTime + ", amount="
				+ amount + ", adminFee=" + adminFee + ", totalAmount=" + totalAmount + ", type=" + type + ", status="
				+ status + "]";
	}
}
