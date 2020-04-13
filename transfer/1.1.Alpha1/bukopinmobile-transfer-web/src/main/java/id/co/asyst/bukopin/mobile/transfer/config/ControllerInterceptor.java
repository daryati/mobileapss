/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.transfer.config;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.DispatcherType;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
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
import id.co.asyst.bukopin.mobile.common.core.util.ConnectionUtils;
import id.co.asyst.bukopin.mobile.common.core.util.MessageUtil;
import id.co.asyst.bukopin.mobile.common.model.ResponseMessage;
import id.co.asyst.bukopin.mobile.transfer.core.config.GetConfiguration;

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

    @Autowired
    private GetConfiguration config;
    
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
    @SuppressWarnings("unchecked")
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
	    throws Exception {
	log.debug("Pre handle Transfer");
	// log GET request
	if (DispatcherType.REQUEST.name().equals(request.getDispatcherType().name())
		&& request.getMethod().equals(HttpMethod.GET.name())) {
	    loggingService.logRequest(request, null);
	}

	String url = request.getRequestURL().toString();
	String[] lastString = url.split("/");
	boolean status = HandlerInterceptor.super.preHandle(request, response, handler);

	String ip = request.getHeader("X-FORWARDED-FOR") == null ? request.getRemoteAddr()
		: request.getHeader("X-FORWARDED-FOR");
	log.debug("client ip address: " + ip);

	String passtrough = env.getProperty("config.ip.passtrough", DEFAULT_LOCAL_IP);
	if (passtrough.contains(ip)) { // ip local doesn't need token
	    log.debug("ip local");
	    status = HandlerInterceptor.super.preHandle(request, response, handler);
	} else {
	    log.debug("ip luar");
	    if ("preHandle".equals(lastString[lastString.length - 1])) {
		String token = request.getHeader(HttpHeaders.AUTHORIZATION);

		// get userToken from user module by token
		String urlUserToken = config.getConfigValue("USER_BASE_URL")
			+ config.getConfigValue("URL_USER_GETUSERTOKEN_BYTOKEN");
		JSONObject dataJson = new JSONObject();
		dataJson.put("token", token);

		Map<String, String> header = new HashMap<String, String>();
		header = new HashMap<String, String>();
		header.put("Content-Type", "application/json");
		header.put("Accept", "application/json");

		try {
		    String res = ConnectionUtils.urlAccess(urlUserToken, dataJson.toString(), header).toString();
		    JSONParser parser = new JSONParser();
		    if (res != null) {
			JSONObject responseJson = (JSONObject) parser.parse(res);
			log.debug("response " + responseJson);

			JSONObject userObj = new JSONObject();
			userObj = (JSONObject) responseJson.get("result");
			if (null != userObj) {
			    System.out.println("token login success");
			    String errorCode = ResponseMessage.SUCCESS.getCode();
			    String errorMessage = messageUtil.get("success", httpServletRequest.getLocale());

			    dataJson.put("message", errorMessage);
			    dataJson.put("code", errorCode);
			    status = true;
			} else {
			    status = false;
			    System.out.println("double device login ");
			    String errorCode = ResponseMessage.ERROR_DOUBLE_LOGIN.getCode();
			    String errorMessage = messageUtil.get("error.double.login", httpServletRequest.getLocale());
			    dataJson.put("message", errorMessage);
			    dataJson.put("code", errorCode);

			    PrintWriter out = response.getWriter();
			    response.setContentType("application/json");
			    response.setCharacterEncoding("UTF-8");
			    out.print(dataJson.toJSONString());
			    out.flush();
			}
		    }
		} catch (Exception e) {
		    log.error(e.toString(), e);
		}

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
