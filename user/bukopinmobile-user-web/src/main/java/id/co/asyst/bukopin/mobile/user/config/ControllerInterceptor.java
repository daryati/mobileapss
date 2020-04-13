/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.user.config;

import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.DispatcherType;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import id.co.asyst.bukopin.mobile.common.core.service.LoggingService;
import id.co.asyst.bukopin.mobile.common.core.service.impl.BkpmService;
import id.co.asyst.bukopin.mobile.common.core.util.MessageUtil;
import id.co.asyst.bukopin.mobile.common.model.ResponseMessage;
import id.co.asyst.bukopin.mobile.common.model.payload.CommonResponse;
import id.co.asyst.bukopin.mobile.user.core.service.UserService;
import id.co.asyst.bukopin.mobile.user.core.service.UserTokenService;
import id.co.asyst.bukopin.mobile.user.core.util.AuthUtil;
import id.co.asyst.bukopin.mobile.user.model.security.UserToken;

/**
 * Controller Interceptor
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Nov 28, 2019
 * @since 1.0.Alpha1-SNAPSHOT
 */
@Component
public class ControllerInterceptor implements HandlerInterceptor {

    /**
     * Logger.
     */
    private final Logger log = LoggerFactory.getLogger(ControllerInterceptor.class);
    
    /**
     * User Token Service
     */
    @Autowired
    private UserTokenService userTokenService;
    
    /**
     * Logging Service 
     */
    @Autowired
    private LoggingService loggingService;
    
    /**
     * Bkpm Common Service
     */
    @Autowired
    private BkpmService commonService;

    /**
     * Http Servlet Request
     */
    @Autowired
    private HttpServletRequest httpServletRequest;    

    /* Constants: */

    /* Attributes: */

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */

    /* Getters & setters for transient attributes: */

    /* Functionalities: */

    /* Overrides: */
    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet.
     * http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
     * java.lang.Object)
     */
    @SuppressWarnings("unchecked")
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
	    throws Exception {
	log.debug("Pre handle User");
	// log GET request
	if (DispatcherType.REQUEST.name().equals(request.getDispatcherType().name())
		&& request.getMethod().equals(HttpMethod.GET.name())) {
	    loggingService.logRequest(request, null);
	}
	boolean status = HandlerInterceptor.super.preHandle(request, response, handler);
	
	if (commonService.verifyLocalIp(httpServletRequest)) { // ip local doesn't need token
	    log.debug("ip local");
	    status = HandlerInterceptor.super.preHandle(request, response, handler);
	} else {
	    log.debug("ip luar");
	    JSONObject dataJson = new JSONObject();
	    String token = request.getHeader(HttpHeaders.AUTHORIZATION);
	    log.info("token: "+token);
	    CommonResponse sessionResponse = userTokenService.validateLogginSession(token, request.getLocale());
	    if(ResponseMessage.SUCCESS.getCode().equals(sessionResponse.getCode())) {
		status = HandlerInterceptor.super.preHandle(request, response, handler);
	    } else {
		status = false;
		dataJson.put("message", sessionResponse.getMessage());
		dataJson.put("code", sessionResponse.getCode());
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.print(dataJson.toJSONString());
		out.flush();
	    }
	}

	return status;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.web.servlet.HandlerInterceptor#postHandle(javax.servlet.
     * http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
     * java.lang.Object, org.springframework.web.servlet.ModelAndView)
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
	    ModelAndView modelAndView) throws Exception {
	// System.out.println("post handle");
	HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.web.servlet.HandlerInterceptor#afterCompletion(javax.
     * servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
     * java.lang.Object, java.lang.Exception)
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
	    throws Exception {
	// System.out.println("after completion");
	HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
