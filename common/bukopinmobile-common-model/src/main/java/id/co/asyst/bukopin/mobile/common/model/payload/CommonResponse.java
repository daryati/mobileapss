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

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Common Response Model
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Oct 23, 2019
 * @since 1.0.Alpha1-SNAPSHOT
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonResponse implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -8290097669626506508L;

    /* Constants: */

    /* Attributes: */
    /**
     * Response Code
     */
    private String code;

    /**
     * Response Message
     */
    private String message;

    /**
     * Single Data Response
     */
    @JsonProperty("result")
    private Object data;

    /* Constructors: */
    /**
     * Default Constructor
     */
    public CommonResponse() {

    }

    /**
     * Custom Constructor
     * 
     * @param code
     *            The response code.
     * @param message
     *            The response message.
     */
    public CommonResponse(String code, String message) {
	this.code = code;
	this.message = message;
    }

    /* Getters & setters for attributes: */
    /**
     * Gets <code>code</code>.
     * 
     * @return The <code>code</code>.
     */
    public String getCode() {
	return code;
    }

    /**
     * Sets <code>code</code>.
     * 
     * @param code
     *            The <code>code</code> to set.
     */
    public void setCode(String code) {
	this.code = code;
    }

    /**
     * Gets <code>message</code>.
     * 
     * @return The <code>message</code>.
     */
    public String getMessage() {
	return message;
    }

    /**
     * Sets <code>message</code>.
     * 
     * @param message
     *            The <code>message</code> to set.
     */
    public void setMessage(String message) {
	this.message = message;
    }

    /**
     * Gets <code>data</code>.
     * 
     * @return The <code>data</code>.
     */
    public Object getData() {
	return data;
    }

    /**
     * Sets <code>data</code>.
     * 
     * @param data
     *            The <code>data</code> to set.
     */
    public void setData(Object data) {
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
	return "CommonResponse [code=" + code + ", message=" + message + ", data=" + data + "]";
    }
}
