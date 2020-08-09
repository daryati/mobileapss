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
package id.co.asyst.bukopin.mobile.transfer.web.rest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.co.asyst.bukopin.mobile.common.core.util.MessageUtil;
import id.co.asyst.bukopin.mobile.common.model.ResponseMessage;
import id.co.asyst.bukopin.mobile.common.model.payload.CommonResponse;
import id.co.asyst.bukopin.mobile.transfer.core.service.LimitPackageService;
import id.co.asyst.bukopin.mobile.transfer.model.entity.LimitPackage;

/**
 * REST Controller for Limit Package
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Jul 7, 2020
 * @since 1.4.Alpha1
 */
@RestController
@RequestMapping("/limitPackage")
public class LimitPackageController {
    /* Constants: */

    /* Attributes: */

    /* Beans: */
    /**
     * Get Message Util
     */
    @Autowired
    private MessageUtil messageUtil;

    /**
     * Http Servlet Request
     */
    @Autowired
    private HttpServletRequest servletRequest;
    
    /**
     * Limit Package Service
     */
    @Autowired
    private LimitPackageService limitService;

    /* Constructors: */

    /* Functionalities: */
    /**
     * GET : Get Default Limit Transfer/ Overbook
     * 
     * @return Success if there is default Limit Transfer, else Not Found.
     */
    @GetMapping("/getDefault")
    public CommonResponse getDefaultLimit() {
	CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(), 
		messageUtil.get("success", servletRequest.getLocale()));
	LimitPackage defaultLimit = limitService.getDefault();
	if(defaultLimit==null) {
	    response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
	    response.setMessage(messageUtil.get("data.not.found", servletRequest.getLocale()));
	} else {
	    response.setData(defaultLimit);
	}
	return response;
    }

    /* Overrides: */
}
