/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.common.model.payload;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Common Request Model
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Oct 23, 2019
 * @since 1.0.Alpha1-SNAPSHOT
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonRequest<T> implements Serializable {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -7360549256483400221L;

    /* Constants: */

    /* Attributes: */
    /**
     * Request Identity
     */
    private Identity identity;

    /**
     * Reqeust Data
     */
    @NotNull(message="Parameter is required.")
    @Valid
    @JsonProperty("parameter")
    private T data;

    /* Constructors: */

    /* Getters & setters for attributes: */
    /**
     * Gets <code>identity</code>.
     * 
     * @return The <code>identity</code>.
     */
    public Identity getIdentity() {
	return identity;
    }

    /**
     * Sets <code>identity</code>.
     * 
     * @param identity
     *            The <code>identity</code> to set.
     */
    public void setIdentity(Identity identity) {
	this.identity = identity;
    }

    /**
     * Gets <code>data</code>.
     * 
     * @return The <code>data</code>.
     */
    public T getData() {
	return data;
    }

    /**
     * Sets <code>data</code>.
     * 
     * @param data
     *            The <code>data</code> to set.
     */
    public void setData(T data) {
	this.data = data;
    }

    /* Functionalities: */

    /* Overrides: */
    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "CommonRequest [identity=" + identity + ", data=" + data + "]";
    }
}
