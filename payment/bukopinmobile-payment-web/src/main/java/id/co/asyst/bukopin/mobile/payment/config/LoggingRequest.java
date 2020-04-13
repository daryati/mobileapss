/*
 * $Id$
 * 
 * Copyright (c) 2020 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.payment.config;

import java.lang.reflect.Type;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import id.co.asyst.bukopin.mobile.common.core.service.LoggingService;

/**
 * Request Logger
 * <br>This class will only loggs POST request. GET request handled by ControllerInterceptor.
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Feb 6, 2020
 * @since 1.0.Alpha1-SNAPSHOT
 */
@ControllerAdvice
public class LoggingRequest extends RequestBodyAdviceAdapter {

    /* Constants: */

    /* Attributes: */
    /**
     * Logging Service 
     */
    @Autowired
    private LoggingService loggingService;
    
    /**
     * Http Servlet Request
     */
    @Autowired
    private HttpServletRequest httpSerlvetRequest;

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */

    /* Getters & setters for transient attributes: */

    /* Functionalities: */

    /* Overrides: */
    /* (non-Javadoc)
     * @see org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice#supports(org.springframework.core.MethodParameter, java.lang.reflect.Type, java.lang.Class)
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType,
	    Class<? extends HttpMessageConverter<?>> converterType) {
	return true;
    }

    /* (non-Javadoc)
     * @see org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter#afterBodyRead(java.lang.Object, org.springframework.http.HttpInputMessage, org.springframework.core.MethodParameter, java.lang.reflect.Type, java.lang.Class)
     */
    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType,
	    Class<? extends HttpMessageConverter<?>> converterType) {
	// log request
	loggingService.logRequest(httpSerlvetRequest, body);
	
	return super.afterBodyRead(body, inputMessage, parameter, targetType, converterType);
    }
    
    
}
