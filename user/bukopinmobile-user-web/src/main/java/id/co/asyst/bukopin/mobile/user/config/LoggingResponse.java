/*
 * $Id$
 * 
 * Copyright (c) 2020 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.user.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import id.co.asyst.bukopin.mobile.common.core.service.LoggingService;

/**
 * Response Logger
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Feb 7, 2020
 * @since 1.0.Alpha1-SNAPSHOT
 */
@ControllerAdvice
public class LoggingResponse implements ResponseBodyAdvice<Object> {

    /* Constants: */

    /* Attributes: */
    /**
     * Logging Service 
     */
    @Autowired
    private LoggingService loggingService;

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */

    /* Getters & setters for transient attributes: */

    /* Functionalities: */

    /* Overrides: */
    /* (non-Javadoc)
     * @see org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice#supports(org.springframework.core.MethodParameter, java.lang.Class)
     */
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
	return true;
    }

    /* (non-Javadoc)
     * @see org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice#beforeBodyWrite(java.lang.Object, org.springframework.core.MethodParameter, org.springframework.http.MediaType, java.lang.Class, org.springframework.http.server.ServerHttpRequest, org.springframework.http.server.ServerHttpResponse)
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
	    Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
	    ServerHttpResponse response) {
	if (request instanceof ServletServerHttpRequest &&
                response instanceof ServletServerHttpResponse) {
            loggingService.logResponse(
                    ((ServletServerHttpRequest) request).getServletRequest(),
                    ((ServletServerHttpResponse) response).getServletResponse(), body);
        }
	return body;
    }
}
