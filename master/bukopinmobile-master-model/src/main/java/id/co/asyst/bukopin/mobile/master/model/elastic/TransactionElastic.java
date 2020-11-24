/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.master.model.elastic;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.stereotype.Component;

import id.co.asyst.bukopin.mobile.master.model.payload.TransactionCommonRequest;


/**
 * Entity Elastic Model Purchase
 * 
 * @author Kartika Dwi H
 * @version $Revision$, Nov 09, 2020
 * @since 1.1.Alpha1
 */
@Component
//Elastic search annotation
@Document(indexName= "bkpm-trx")
public class TransactionElastic {

	 private static final long serialVersionUID = 1L;

	    /* Constants: */

	    /* Attributes: */
	    private String id;
	    
	    private String username;

	    @Field(name="dateTime", type = FieldType.Date, format = DateFormat.date_time)
	    private Date dateTime;

	    private BigDecimal amount;
	    
	    private BigDecimal adminFee;
	    
	    private BigDecimal totalAmount;
	    
	    private String type;
	    
	    private String status;

	   
		/**
		 * @return the id
		 */
		public String getId() {
			return id;
		}

		/**
		 * @param id the id to set
		 */
		public void setId(String id) {
			this.id = id;
		}

		/**
		 * @return the username
		 */
		public String getUsername() {
			return username;
		}

		/**
		 * @param username the username to set
		 */
		public void setUsername(String username) {
			this.username = username;
		}

		/**
		 * @return the transactionTime
		 */
		public Date getDateTime() {
			return dateTime;
		}

		/**
		 * @param transactionTime the transactionTime to set
		 */
		public void setDateTime(Date dateTime) {
			this.dateTime = dateTime;
		}

		/**
		 * @return the amount
		 */
		public BigDecimal getAmount() {
			return amount;
		}

		public void setAmount(BigDecimal amount) {
			this.amount = amount;
		}

		/**
		 * @return the adminFee
		 */
		public BigDecimal getAdminFee() {
			return adminFee;
		}		

		/**
		 * @param adminFee the adminFee to set
		 */
		public void setAdminFee(BigDecimal adminFee) {
			this.adminFee = adminFee;
		}

		/**
		 * @return the type
		 */
		public String getType() {
			return type;
		}

		/**
		 * @param type the type to set
		 */
		public void setType(String type) {
			this.type = type;
		}

		/**
		 * @return the status
		 */
		public String getStatus() {
			return status;
		}

		/**
		 * @param status the status to set
		 */
		public void setStatus(String status) {
			this.status = status;
		}

		
		/**
		 * @return the totalAmount
		 */
		public BigDecimal getTotalAmount() {
			return totalAmount;
		}

		/**
		 * @param totalAmount the totalAmount to set
		 */
		public void setTotalAmount(BigDecimal totalAmount) {
			this.totalAmount = totalAmount;
		}

		@Override
		public String toString() {
			return "TransactionElastic [id=" + id + ", username=" + username + ", dateTime=" + dateTime + ", amount="
					+ amount + ", adminFee=" + adminFee + ", totalAmount=" + totalAmount + ", type=" + type
					+ ", status=" + status + "]";
		}
	    
	    
}
