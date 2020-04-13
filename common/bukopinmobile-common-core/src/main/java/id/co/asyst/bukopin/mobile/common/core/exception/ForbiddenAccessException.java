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
 * Forbidden Access Exception
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Feb 3, 2020
 * @since 1.1.Alpha1
 */
@ResponseStatus(HttpStatus.FORBIDDEN)
public class ForbiddenAccessException extends RuntimeException {
    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 6982279623887153385L;
    
    public ForbiddenAccessException() {
	super();
    }
    
    public ForbiddenAccessException(String message) {
	super(message);
    }
}
