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
 * Data Not Match Exception.
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Jan 30, 2020
 * @since 1.1.Alpha1
 */
@ResponseStatus(HttpStatus.OK)
public class DataNotMatchException extends RuntimeException {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -648119344030444782L;
    
    public DataNotMatchException() {
	super();
    }
    
    public DataNotMatchException(String message) {
	super(message);
    }
}
