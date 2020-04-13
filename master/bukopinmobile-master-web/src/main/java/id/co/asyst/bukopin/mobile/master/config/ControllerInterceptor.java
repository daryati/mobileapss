/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.master.config;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.DispatcherType;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import id.co.asyst.bukopin.mobile.common.core.service.LoggingService;
import id.co.asyst.bukopin.mobile.common.core.util.MessageUtil;
import id.co.asyst.bukopin.mobile.common.model.ResponseMessage;
import id.co.asyst.bukopin.mobile.common.model.payload.CommonResponse;
import id.co.asyst.bukopin.mobile.service.core.UserModuleService;
import id.co.asyst.foundation.service.connector.Services;

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
     * Get Message Util
     */
    @Autowired
    private MessageUtil messageUtil;
    
    /**
     * Logging Service 
     */
    @Autowired
    private LoggingService loggingService;

    /**
     * Http Servlet Request
     */
    @Autowired
    private HttpServletRequest httpServletRequest;
    
    /**
     * Environment
     */
    @Autowired
    private Environment env;

    /* Constants: */
    /**
     * Default IP Local
     */
    private static final String DEFAULT_LOCAL_IP = "0.0.0.1";

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
    //@SuppressWarnings("unchecked")
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
	
	String url = request.getRequestURL().toString();
	log.debug("source url: "+url);
	boolean status = HandlerInterceptor.super.preHandle(request, response, handler);

//	String ip = request.getHeader("X-FORWARDED-FOR") == null ? request.getRemoteAddr()
//		: request.getHeader("X-FORWARDED-FOR");
//	ip = ip.trim();
//	log.debug("client ip address: " + ip);
//	log.debug("token: "+request.getHeader(HttpHeaders.AUTHORIZATION));
//	
//	String passtrough = env.getProperty("config.ip.passtrough", DEFAULT_LOCAL_IP);
//	log.debug("passtrough: "+passtrough);
//	if (passtrough.contains(ip)) { // ip local doesn't need token
//	    log.debug("ip local");
//	    status = HandlerInterceptor.super.preHandle(request, response, handler);
//	} else {
//	    log.debug("ip luar");
//	    log.debug("ke pre handle dr ip luar");
//		String token = request.getHeader(HttpHeaders.AUTHORIZATION);
//		Map<String,String> tokenReq = new HashMap<>();
//		tokenReq.put("token", token);
//		CommonResponse findUser = Services.create(UserModuleService.class).findUserByToken(tokenReq)
//		    	.execute().body();		
//		log.debug("SISNIII "+findUser);
//		/*UserToken userToken = userTokenService.findByToken(token);
//		JSONObject dataJson = new JSONObject();*/
//		JSONObject dataJson = new JSONObject();
//		if (ResponseMessage.SUCCESS.getCode().equalsIgnoreCase(findUser.getCode())) {
//		    System.out.println("token login success");
//
//		    String errorCode = ResponseMessage.SUCCESS.getCode();
//		    String errorMessage = messageUtil.get("success", httpServletRequest.getLocale());
//
//		    dataJson.put("message", errorMessage);
//		    dataJson.put("code", errorCode);
//		    // status= true;
//		    status = HandlerInterceptor.super.preHandle(request, response, handler);
//		} else {
//		    status = false;
//		    System.out.println("double device login ");
//
//		    String errorCode = ResponseMessage.ERROR_DOUBLE_LOGIN.getCode();
//		    String errorMessage = messageUtil.get("error.double.login", httpServletRequest.getLocale());
//		    dataJson.put("message", errorMessage);
//		    dataJson.put("code", errorCode);
//		    PrintWriter out = response.getWriter();
//		    response.setContentType("application/json");
//		    response.setCharacterEncoding("UTF-8");
//		    out.print(dataJson.toJSONString());
//		    out.flush();
//		}
//	    
//	}

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
