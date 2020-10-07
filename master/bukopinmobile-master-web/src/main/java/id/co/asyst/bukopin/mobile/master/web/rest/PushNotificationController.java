/*
 * $Id$
 * 
 * Copyright (c) 2020 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.master.web.rest;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import id.co.asyst.bukopin.mobile.common.core.util.BkpmUtil;
import id.co.asyst.bukopin.mobile.common.model.ResponseMessage;
import id.co.asyst.bukopin.mobile.common.model.payload.CommonResponse;
import id.co.asyst.bukopin.mobile.master.model.entity.Configuration;
import id.co.asyst.bukopin.mobile.service.core.MasterModuleService;
import id.co.asyst.bukopin.mobile.service.core.PushNotificationService;
import id.co.asyst.foundation.service.connector.Services;

/**
 * 
 * 
 * @author Gibran Haq
 * @version $Revision$, Jun 2, 2020
 * @since 1.2.Alpha1
 */
@RestController
@RequestMapping("/notification")
public class PushNotificationController {
    /* Constants: */
    private final Logger log = LoggerFactory.getLogger(PushNotificationController.class);
    
    private static final String FCM ="FCM_KEY";
    private static final String CONTENT_TYPE = "application/json";
    private static final String KEY = "key=";

    /* Attributes: */

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */

    /* Getters & setters for transient attributes: */

    /* Functionalities: */
    /**
     * Push Notif
     * 
     * @param req from CMS
     * @return 
     * @throws IOException
     */
    @PostMapping("/push")
    public Object pushNotif(@RequestBody Object req) throws IOException{
	log.debug("REST request to push notification : {}", req);
	
	// get FCM key from db
	CommonResponse fcmKeyRes = Services.create(MasterModuleService.class)
		.findConfigByName(FCM).execute().body();
	if (!ResponseMessage.SUCCESS.getCode().equals(fcmKeyRes.getCode())) {
		return fcmKeyRes;
	}
	ObjectMapper oMapper = new ObjectMapper();
	Configuration configurationValue = oMapper.convertValue(fcmKeyRes.getData(), Configuration.class);
	String fcmValue = KEY.concat(configurationValue.getValue());
	//String fcmValue = KEY.concat("AAAArAyrsLg:APA91bE2hOK7uRHQEqZzFysDIup6cFKZr6Cj6-kQikiThaGslwzrMIhJdOhuRxwLmNBw8ZWHNrGfT8son0pBJIL5msXQkr_g9ME3svlrdNTJeP6Ff4xcf1l4ohI3JY-aTQAQpSzZoXsj");
	
	Object pushNotifResFirebase = Services.create(PushNotificationService.class)
		.pushNotif(fcmValue, CONTENT_TYPE, req).execute().body();
	log.debug("Response push notification : " + BkpmUtil.convertToJson(pushNotifResFirebase));
	
	return pushNotifResFirebase;
    }

    /* Overrides: */}
