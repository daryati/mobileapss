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

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import id.co.asyst.bukopin.mobile.common.core.util.ConnectionUtils;

/**
 * Common Bukopin Mobile Service
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Mar 6, 2020
 * @since 1.1.Alpha1
 */
@Service
public class BkpmService {
    
    /**
     * Logger.
     */
    private final Logger log = LoggerFactory.getLogger(BkpmService.class);
    
    /* Constants: */
    /**
     * Default IP Local
     */
    private static final String DEFAULT_LOCAL_IP = "0.0.0.1";

    /**
     * Environment Bean
     */
    @Autowired
    private Environment env;

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */

    /* Getters & setters for transient attributes: */

    /* Functionalities: */
    /**
     * Verify Local is request From local or Ip
     * 
     * @return true if request from local, either false.
     */
    public boolean verifyLocalIp(HttpServletRequest request) {
	boolean isLocalIp = false;
	String ip = request.getHeader("X-FORWARDED-FOR") == null ? request.getRemoteAddr()
		: request.getHeader("X-FORWARDED-FOR");
	ip = ip.trim();
	
	String passtrough = env.getProperty("config.ip.passtrough", DEFAULT_LOCAL_IP);
	if (passtrough.contains(ip)) { // ip local doesn't need token
	    log.debug("ip local");
	    isLocalIp = true;
	}
	
	return isLocalIp;
    }
    
    /**
     * Get Configuration Value from Configuration table.
     * 
     * @param hostMaster Host of Master module. e.g. http://localhost:8082
     * @param name Name of Configuration to get.
     * 
     * @return Value of persisted Configuration.
     */
    public String getConfigValue(String name) {
	String result = "";
	String url = env.getProperty("url.masterdata.configuration") 
		+ "bukopinmobile-master/config/getConfigByName/" + name;

	Map<String, String> header = new HashMap<String, String>();
	header = new HashMap<String, String>();
	header.put("Content-Type", "application/json");
	header.put("Accept", "application/json");

	String res;
	try {
	    res = ConnectionUtils.urlAccess(url, header).toString();
	    JSONParser parser = new JSONParser();
	    if (res != null) {
		JSONObject responseJson = (JSONObject) parser.parse(res);

		String statusObj = (String) responseJson.get("message");
		if ("success".equalsIgnoreCase(statusObj)) {
		    JSONObject resultobj = (JSONObject) responseJson.get("result");
		    result = (String) resultobj.get("value");
		}
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return result;
    }

    /* Overrides: */
}
