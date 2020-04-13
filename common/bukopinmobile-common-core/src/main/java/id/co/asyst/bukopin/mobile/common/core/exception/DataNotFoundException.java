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

/**
 * Data Not Found Exception.
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Feb 24, 2020
 * @since 1.1.Alpha1
 */
public class DataNotFoundException extends RuntimeException {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -1969155076623017774L;
    
    /* Constructors: */
    public DataNotFoundException() {
	super();
    }
    
    public DataNotFoundException(String message) {
	super(message);
    }
}
