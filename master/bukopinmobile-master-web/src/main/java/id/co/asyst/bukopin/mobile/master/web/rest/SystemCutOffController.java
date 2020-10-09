/*
 * $Id$
 * 
 * Copyright (c) 2020 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
/*
 * Copyright (c) 2020 Lufthansa Systems Indonesia, PT. All rights reserved.
 */
package id.co.asyst.bukopin.mobile.master.web.rest;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import id.co.asyst.bukopin.mobile.common.core.exception.DataNotFoundException;
import id.co.asyst.bukopin.mobile.common.core.util.MessageUtil;
import id.co.asyst.bukopin.mobile.common.model.ResponseMessage;
import id.co.asyst.bukopin.mobile.common.model.payload.CommonResponse;
import id.co.asyst.bukopin.mobile.master.core.service.SystemCutOffService;
import id.co.asyst.bukopin.mobile.master.model.entity.cms.SystemCutOff;

/**
 * REST Controller for System Cut Off
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, May 28, 2020
 * @since 1.4.Alpha1
 */
@RestController
@RequestMapping("/cutOff")
public class SystemCutOffController {
    
    /* Logger: */
    private final Logger log = LoggerFactory.getLogger(SystemCutOffController.class);
    
    /* Constants: */

    /* Attributes: */
    
    /* Beans: */
    @Autowired
    private SystemCutOffService cutOffService;
    
    @Autowired
    private MessageUtil messageUtil;

    @Autowired
    private HttpServletRequest servletRequest;

    /* Constructors: */

    /* Services: */
    /**
     * GET: Get System Cut Off By Id
     * @param id The id to find.
     * @return Common Response with System Cut Off data if exist, else Data Not Found.
     */
    @GetMapping("/get/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CommonResponse getById(@PathVariable Long id) {
	CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(),
		messageUtil.get("success", servletRequest.getLocale()));
	
	SystemCutOff cutOff = cutOffService.findById(id);
	if(cutOff==null) {
	    log.error("System cut off not found: {}", id);
	    throw new DataNotFoundException();
	} else {
	    response.setData(cutOff);
	}
	
	return response;
    }
    
    /**
     * GET: Check Cut Off Status
     * 
     * @param id id The Module id of System Cut Off
     * @return return Code 217 in body, if Cut off is in Progress, else 000.
     */
    @GetMapping("/checkStatus/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CommonResponse checkCutOffStatus(@PathVariable Long id) {
	CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(),
		messageUtil.get("success", servletRequest.getLocale()));

	// Check is cut off in progress?
	boolean isCutOff = cutOffService.isCutOff(id);
	if(isCutOff) {
	    // Set cut off message
	    response = new CommonResponse(ResponseMessage.ERROR_CUT_OFF_PLN.getCode(),
			messageUtil.get("system.cut.off", servletRequest.getLocale()));
	}

	return response;
    }

    /* Functionalities: */

    /* Overrides: */
}
