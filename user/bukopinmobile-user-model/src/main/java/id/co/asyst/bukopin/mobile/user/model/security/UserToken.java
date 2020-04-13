/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.user.model.security;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.Type;

import id.co.asyst.foundation.common.model.BaseObjectAclAware;

/**
 * Persistent User Remember-Me Token.
 * 
 * @author Stephen Dharma
 * @version $Revision$, (Created on Oct 24, 2019)
 * @since Asyst-Foundation 1.0.Alpha1
 */
@Entity
@Table(name = "USER_TOKEN")
public class UserToken extends BaseObjectAclAware implements Serializable {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = -282503329867556084L;

    /* Constants: */
    /**
     * Username maximum length (value <code>{@value}</code>).
     */
    @Transient
    private static final int USERNAME_MAX_LENGTH = 32;
    /**
     * Field(s) maximum length (value <code>{@value}</code>).
     */
    @Transient
    private static final int FIELD_MAX_LENGTH = 64;
    /**
     * UID length (value <code>{@value}</code>). Equals to the length of
     * <code>java.lang.UUID</code>.
     */
    @Transient
    public static final int UID_LENGTH = 36;
    /**
     * Default Token Validity (value <code>{@value}</code> minutes).
     */
    @Transient
    public static final int DEFAULT_VALIDITY_MINUTE = 2880;
    

    /* Attributes: */
    /**
     * Username.
     */
    @Id
    @Column(name = "USERNAME", nullable = false, length = USERNAME_MAX_LENGTH)
    private String username;
    /**
     * Unique ID, used as Username replacement in URLs. This UID will not be
     * used to generate Token (another UUID will be used to generate Token and
     * will not be stored).<br>
     * <b>Notes:</b><br>
     * <ul>
     * <li>'-' replaced by '0'.</li>
     * <li>Oracle invalid identifier for create table: "UID".</li>
     * </ul>
     */
    @Column(name = "UID_CODE", nullable = false, length = UID_LENGTH, unique = true)
    private String uid;
    /**
     * Token (Hashed for secure storage).
     */
    @Column(name = "TOKEN", nullable = false, length = FIELD_MAX_LENGTH)
    private String token;
    // , updatable = false
    /**
     * Token Create DateTime.
     */
    @Column(name = "CREATE_DATE", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    
    /**
     * Token Update DateTime.
     */
    @Column(name = "UPDATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;
    
    /**
     * Active Flag (Y/N).
     */
    @Column(name = "ACTIVE", nullable = false)
    @Type(type = "yes_no")
    private boolean active;
    /**
     * Secret Code used as key in hmac.
     */
    @Column(name = "SECRET_CODE", nullable = false, length = FIELD_MAX_LENGTH)
    private String secretCode;
    
    /**
     * mobile device id. (Will be deprecated, move to user.phoneIdentity)
     */
    @Column(name = "DEVICE_ID", nullable = true)
    private String deviceId;
    
    

    /* Transient Attributes: */
    /**
     * Signature.
     */
    @Transient
    private String signature;

    /* Constructors: */
    /**
     * Constructor.
     */
    public UserToken() {
        // do nothing.
    }

    /**
     * Constructor with Username parameter.
     * 
     * @param username
     *            The Username.
     */
    public UserToken(String username) {
        super();
        this.username = username;
    }

    /* Getters & setters for attributes: */
    /**
     * Gets <code>username</code>.
     * 
     * @return The <code>username</code>.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets <code>username</code>.
     * 
     * @param username
     *            The <code>username</code> to set.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets <code>uid</code>.
     * 
     * @return The <code>uid</code>.
     */
    public String getUid() {
        return uid;
    }

    /**
     * Sets <code>uid</code>.
     * 
     * @param uid
     *            The <code>uid</code> to set.
     */
    public void setUid(String uid) {
        this.uid = uid;
    }

    /**
     * Gets hashed <code>token</code>.
     * 
     * @return The <code>token</code>.
     */
    public String getToken() {
        return token;
    }

    /**
     * Sets hashed <code>token</code>.
     * 
     * @param token
     *            The <code>token</code> to set.
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * Gets <code>createDate</code>.
     * 
     * @return The <code>createDate</code>.
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * Sets <code>createDate</code>.
     * 
     * @param createDate
     *            The <code>createDate</code> to set.
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * Gets <code>active</code>.
     * 
     * @return The <code>active</code>.
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Sets <code>active</code>.
     * 
     * @param active
     *            The <code>active</code> to set.
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /* Getters & setters for transient attributes: */
    /**
     * Gets <code>signature</code> (transient).
     * 
     * @return The <code>signature</code>.
     */
    public String getSignature() {
        return signature;
    }

    /**
     * Sets <code>signature</code> (transient).
     * 
     * @param signature
     *            The <code>signature</code> to set.
     */
    public void setSignature(String signature) {
        this.signature = signature;
    }

    /* Functionalities: x */

    /* Overrides: */
    /*
     * (non-Javadoc)
     * 
     * @see id.co.asyst.foundation.common.model.BaseObjectAclAware#getId()
     */
    @Override
    public Serializable getId() {
        return getUsername();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * id.co.asyst.foundation.common.model.BaseObjectAclAware#getUniqueKey()
     */
    @Override
    public Serializable getUniqueKey() {
        return getUsername();
    }

    /**
	 * Gets <code>secretCode</code>.
	 * @return The <code>secretCode</code>.
	 */
	public String getSecretCode() {
		return secretCode;
	}

	/**
	 * Sets <code>secretCode</code>.
	 * @param secretCode The <code>secretCode</code> to set.
	 */
	public void setSecretCode(String secretCode) {
		this.secretCode = secretCode;
	}

	/**
	 * Gets <code>deviceId</code>.
	 * @return The <code>deviceId</code>.
	 */
	public String getDeviceId() {
		return deviceId;
	}

	/**
	 * Sets <code>deviceId</code>.
	 * @param deviceId The <code>deviceId</code> to set.
	 */
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	/**
	 * Gets <code>updateDate</code>.
	 * @return The <code>updateDate</code>.
	 */
	public Date getUpdateDate() {
	    return updateDate;
	}

	/**
	 * Sets <code>updateDate</code>.
	 * @param updateDate The <code>updateDate</code> to set.
	 */
	public void setUpdateDate(Date updateDate) {
	    this.updateDate = updateDate;
	}

    /*
     * (non-Javadoc)
     * 
     * @see id.co.asyst.foundation.common.model.BaseObject#toString()
     */
    @Override
    public String toString() {
        ToStringBuilder builder = new ToStringBuilder(this);
        builder.append("username", username);
        builder.append("uid", uid);
        builder.append("token", token);
        builder.append("createDate", createDate);
        builder.append("updateDate", updateDate);
        builder.append("active", active);
        builder.append("secretCode", secretCode);
        builder.append("deviceId", secretCode);
        return builder.toString();
    }

    @Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserToken other = (UserToken) obj;
		if (active != other.active)
			return false;
		if (createDate == null) {
			if (other.createDate != null)
				return false;
		} else if (!createDate.equals(other.createDate))
			return false;
		if (deviceId == null) {
			if (other.deviceId != null)
				return false;
		} else if (!deviceId.equals(other.deviceId))
			return false;
		if (secretCode == null) {
			if (other.secretCode != null)
				return false;
		} else if (!secretCode.equals(other.secretCode))
			return false;
		if (signature == null) {
			if (other.signature != null)
				return false;
		} else if (!signature.equals(other.signature))
			return false;
		if (token == null) {
			if (other.token != null)
				return false;
		} else if (!token.equals(other.token))
			return false;
		if (uid == null) {
			if (other.uid != null)
				return false;
		} else if (!uid.equals(other.uid))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (active ? 1231 : 1237);
		result = prime * result
				+ ((createDate == null) ? 0 : createDate.hashCode());
		result = prime * result
				+ ((deviceId == null) ? 0 : deviceId.hashCode());
		result = prime * result
				+ ((secretCode == null) ? 0 : secretCode.hashCode());
		result = prime * result
				+ ((signature == null) ? 0 : signature.hashCode());
		result = prime * result + ((token == null) ? 0 : token.hashCode());
		result = prime * result + ((uid == null) ? 0 : uid.hashCode());
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
		return result;
	}
}
