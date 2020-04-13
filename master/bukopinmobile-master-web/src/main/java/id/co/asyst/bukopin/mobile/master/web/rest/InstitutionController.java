/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.master.web.rest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.xml.datatype.DatatypeConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import id.co.asyst.bukopin.mobile.common.core.util.MessageUtil;
import id.co.asyst.bukopin.mobile.common.model.ResponseMessage;
import id.co.asyst.bukopin.mobile.common.model.payload.CommonRequest;
import id.co.asyst.bukopin.mobile.common.model.payload.CommonResponse;
import id.co.asyst.bukopin.mobile.master.core.service.InstitutionService;
import id.co.asyst.bukopin.mobile.master.core.service.ListEMoneyService;
import id.co.asyst.bukopin.mobile.master.core.service.PrefixTelcoService;
import id.co.asyst.bukopin.mobile.master.model.entity.Institution;
import id.co.asyst.bukopin.mobile.master.model.entity.ListEMoney;
import id.co.asyst.bukopin.mobile.master.model.entity.PrefixTelco;
import id.co.asyst.bukopin.mobile.master.model.payload.InstitutionRequest;
import id.co.asyst.bukopin.mobile.master.model.payload.MobileDataSelectionResponse;
import id.co.asyst.bukopin.mobile.master.model.payload.PrefixTelcoDetailResponse;
import id.co.asyst.bukopin.mobile.master.model.payload.PrefixTelcoRequest;
import id.co.asyst.bukopin.mobile.master.model.payload.PulsaSelectionResponse;

/**
 * REST Controller for Institution
 * 
 * @author Kartika Dwi H
 * @version $Revision$, Jan 30, 2020
 * @since 1.1.Alpha1
 */
@RestController
@RequestMapping("/institution")
public class InstitutionController {
    /* Constants: */
    private final Logger log = LoggerFactory.getLogger(InstitutionController.class);
    
    /* Attributes: */
    
    /**
     * Institution service
     */
    @Autowired
    private InstitutionService institutionService;
    
    
    /**
     * Get Message Util
     */
    @Autowired
    private MessageUtil messageUtil;
    
    /**
     * Http Servlet Request
     */
    @Autowired
    private HttpServletRequest httpServletRequest;
    
    
    
    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */

    /* Getters & setters for transient attributes: */

    /* Functionalities: */

    /**
     * GET /findCodeByProvider/{provider} : get institution by code
     *
     * @return the response with status 200 (OK) and the institution in body.
     */
	@GetMapping("/findCodeByProvider/{provider}")
    @ResponseStatus(HttpStatus.OK)
	public CommonResponse findByPrefixNoAndType(@PathVariable String provider)
		    throws DatatypeConfigurationException {		
		CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(),
			messageUtil.get("success", httpServletRequest.getLocale()));		
		
		Institution institution =institutionService.findCodeByProvider(provider);		
		if(null != institution) {			
			response.setData(institution);	
		} else {
			 log.debug("data not found ");
			 response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
			 response.setMessage(messageUtil.get("error.data.not.found", httpServletRequest.getLocale()));
		}
		return response;
	}
    

   
    /* Overrides: */
}
