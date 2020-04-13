/*
 * $Id$
 * 
 * Copyright (c) 2020 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.common.core.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Logging Service Interface
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Feb 6, 2020
 * @since 1.0.Alpha1-SNAPSHOT
 */
public interface LoggingService {
    
    public void logRequest(HttpServletRequest request, Object body);
    
    public void logResponse(HttpServletRequest request, HttpServletResponse response, Object body);
}
