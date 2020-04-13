/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.user.web.rest.util;

import id.co.asyst.bukopin.mobile.user.web.rest.errors.ResourceNotFoundException;

/**
 * REST Preconditions utility.
 * 
 * @author Stephen Dharma
 * @version $Revision$, (Created on Oct 24, 2019)
 * @since 1.0.Alpha1
 */
public class RestPreconditionsUtil {
    /**
     * Checks found Resource.
     * 
     * @param resource The Resource to find.
     * @return Check Result.
     */
    public static <T> T checkFound(T resource) {
        if (resource == null) {
            throw new ResourceNotFoundException();
        }
        return resource;
    }
}
