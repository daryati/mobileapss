/*
 * $Id$
 * 
 * Copyright (c) 2020 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.common.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Middleware Exception
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Jan 16, 2020
 * @since 1.0.Alpha1
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class MiddlewareException extends RuntimeException {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = 1231669530607739496L;
    
    public MiddlewareException() {
	super();
    }
    
    public MiddlewareException(String message) {
	super(message);
    }
}
