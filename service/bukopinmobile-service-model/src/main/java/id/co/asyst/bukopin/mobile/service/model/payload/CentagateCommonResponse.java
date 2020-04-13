/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.service.model.payload;

import java.io.Serializable;

/**
 * Centagate Common Response Model
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Oct 27, 2019
 * @since 1.0.Alpha1-SNAPSHOT
 */
public class CentagateCommonResponse implements Serializable {

	/**
     * Serial Version UID.
     */
	private static final long serialVersionUID = 7947147117684085778L;
	
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
	 * Response Object
	 */
	private Object object;

	/* Constructors: */

	/* Getters & setters for attributes: */
	/**
	 * Gets <code>code</code>.
	 * @return The <code>code</code>.
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Sets <code>code</code>.
	 * @param code The <code>code</code> to set.
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * Gets <code>message</code>.
	 * @return The <code>message</code>.
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Sets <code>message</code>.
	 * @param message The <code>message</code> to set.
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Gets <code>object</code>.
	 * @return The <code>object</code>.
	 */
	public Object getObject() {
		return object;
	}

	/**
	 * Sets <code>object</code>.
	 * @param object The <code>object</code> to set.
	 */
	public void setObject(Object object) {
		this.object = object;
	}

	/* Functionalities: */

	/* Overrides: */
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CentagateCommonResponse [code=" + code + ", message=" + message + ", object=" + object + "]";
	}
}
