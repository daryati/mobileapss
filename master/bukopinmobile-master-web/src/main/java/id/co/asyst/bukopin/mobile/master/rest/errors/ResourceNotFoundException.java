/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.master.rest.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Resource Not Found Exception.
 * 
 * @author Stephen Dharma
 * @version $Revision$, (Created on Oct 24, 2019)
 * @since 1.0.Alpha1
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = -4840871153857132359L;

    public ResourceNotFoundException() {
	super();
    }
    
    public ResourceNotFoundException(String message) {
	super(message);
    }
}
