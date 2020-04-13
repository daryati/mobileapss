/*
 * $Id$
 * 
 * Copyright (c) 2020 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.common.core.service.impl;

import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import id.co.asyst.bukopin.mobile.common.core.service.LoggingService;
import id.co.asyst.bukopin.mobile.common.core.util.BkpmUtil;
import id.co.asyst.bukopin.mobile.common.model.BkpmConstants;

/**
 * Logging Service Implementation
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Feb 6, 2020
 * @since 1.0.Alpha1-SNAPSHOT
 */
@Service("loggingService")
public class LoggingServiceImpl implements LoggingService {

    /**
     * Logger
     */
    private final Logger log = LoggerFactory.getLogger(LoggingServiceImpl.class);
    
    /* Constants: */
    private final static int MAX_RESPONSE_CHARS = 3000;

    /* Attributes: */

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */

    /* Getters & setters for transient attributes: */

    /* Functionalities: */
    /* (non-Javadoc)
     * @see id.co.asyst.bukopin.mobile.common.core.service.LoggingService#logRequest(javax.servlet.http.HttpServletRequest, java.lang.Object)
     */
    public void logRequest(HttpServletRequest request, Object body) {
	String ip = request.getHeader("X-FORWARDED-FOR") == null ? request.getRemoteAddr()
		: request.getHeader("X-FORWARDED-FOR");
	String uuid = UUID.randomUUID().toString().replace("-", "");
	// uuid request
	MDC.put(BkpmConstants.MDC_KEY_LOG, uuid);
	// start time request
	MDC.put(BkpmConstants.MDC_KEY_LOG_DURATION, String.valueOf(new Date().getTime()));
	
	log.debug("\n-------------------- REQUEST BEGIN --------------------"
		+ "\nRequest-ID  : " + uuid
		+ "\nURI         : " + request.getRequestURI()
		+ "\nHeader      : "
		+ "\n - Authorization  : " + request.getHeader(HttpHeaders.AUTHORIZATION)
		+ "\n - Accept-Language: " + request.getHeader(HttpHeaders.ACCEPT_LANGUAGE)
		+ "\n - Phone_Identity : " + request.getHeader(BkpmConstants.HTTP_HEADER_DEVICE_ID)
		+ "\nFrom        : " + ip
		+ "\nMethod      : " + request.getMethod()
		+ (body==null ? "" : "\nRequest body: " + BkpmUtil.convertToJson(body))
		+ "\n--------------------- REQUEST END ---------------------");
    }

    /* (non-Javadoc)
     * @see id.co.asyst.bukopin.mobile.common.core.service.LoggingService#logResponse(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
     */
    public void logResponse(HttpServletRequest request, HttpServletResponse response, Object body) {
	String truncted = BkpmUtil.convertToJson(body);
	if(!StringUtils.isBlank(truncted) && truncted.length() > MAX_RESPONSE_CHARS) {
	    truncted = StringUtils.substring(truncted, 0, MAX_RESPONSE_CHARS)+"...[truncted]"; 
	}
	    
	log.debug("\n-------------------- RESPONSE BEGIN --------------------"
		+ "\nResponse-ID  : " + MDC.get(BkpmConstants.MDC_KEY_LOG)
		+ "\nHttp Status  : " + response.getStatus()
		+ "\nDuration     : " + countRequestDuration()
		+ "\nResponse body: " + truncted
		+ "\n--------------------- RESPONSE END ---------------------");
    }
    
    /**
     * Count Request Duration
     * 
     * @return Request's duration in seconds.
     */
    private String countRequestDuration() {
	// get start time
	String start = MDC.get(BkpmConstants.MDC_KEY_LOG_DURATION);
	
	if(StringUtils.isBlank(start)) {
	    start = String.valueOf(new Date().getTime());
	}
	Long init = Long.valueOf(start);
	Long end = new Date().getTime();
	// count duration
	Long dur = end-init; // duration in miliseconds
	String duration = String.valueOf(dur/1000); // duration in seconds
	if("0".equals(duration)) { // if milis
	    duration = String.valueOf(dur)+" ms";
	} else {
	    duration = duration+" s";
	}
	
	return duration;
    }

    /* Overrides: */
}
