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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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
import id.co.asyst.bukopin.mobile.master.core.service.ListInsuranceService;
import id.co.asyst.bukopin.mobile.master.model.entity.ListInsurance;
/**
 * REST Controller for ListInsurance
 * 
 * @author Kartika Dwi H
 * @version $Revision$, Jan 30, 2020
 * @since 1.1.Alpha1
 */
@RestController
@RequestMapping("/listInsurance")
public class ListInsuranceController {
    /* Constants: */
    private final Logger log = LoggerFactory.getLogger(ListInsuranceController.class);
    /* Attributes: */
    
    /**
     * Environment
     */
    @Autowired
    private Environment env;
    
    /**
     * insurance service
     */
    @Autowired
    ListInsuranceService insuranceService;
    
    
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
     * find All of ListInsurance's data
     * 
     * @return List of ListInsurance data
     */
    @GetMapping("/allInsurance")
    @ResponseStatus(HttpStatus.OK)
    public CommonResponse findAllEMoney() {
	log.debug("Find all List Insurance....");
	String redisStatus = env.getProperty("config.status.redis");
	
	// get all configuration, user redis?	
	List<ListInsurance> findAll = new ArrayList<>();
	if("1".equalsIgnoreCase(redisStatus)) {
		log.debug("get from cache");
		findAll = insuranceService.getAllEMoneyCache();
	} else {
		//findAll = emoneyService.findAll();
		findAll = insuranceService.findAllEmoneyWithPic();
	}
	
	CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(), messageUtil.get("success", httpServletRequest.getLocale()));
	if(findAll.isEmpty()) {
		log.debug("insurance cache null, get from db ");
		findAll = insuranceService.findAllEmoneyWithPic();
		if(null == findAll) {
			response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
		    response.setMessage(messageUtil.get("error.data.not.found", httpServletRequest.getLocale()));
		    return response;
		} 
			for(ListInsurance con : findAll) {
	        	log.debug("add item to cache");
	        	insuranceService.addItem(con);	
	        }
		
	}
	response.setData(findAll);
	
	return response;
    }
    
    
    /**
     * find ListInsurance by id
     * 
     * @return List ListInsurance data
     */
    
    @GetMapping("/getInsuranceById/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CommonResponse findEMoneyById(@PathVariable Long id) {
	log.debug("Find insurance by Id: "+id);
	CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(), messageUtil.get("success", httpServletRequest.getLocale()));
	String redisStatus = env.getProperty("config.status.redis");
	
	// use redis??
	ListInsurance getByName = new ListInsurance();
	if("1".equalsIgnoreCase(redisStatus)) {
		log.debug("get from cache");
		getByName = insuranceService.getItem(id);
	} else {
		Optional<ListInsurance> emoney = insuranceService.findById(id);
		if(emoney.isPresent()) {
			getByName = emoney.get();
		}
	}
	
	
	if(null != getByName) {
		response.setData(getByName);
	} else {
		response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
	    response.setMessage(messageUtil.get("error.data.not.found", httpServletRequest.getLocale()));
	    return response;
	}
	
	
	return response;
    }
    
    /**
     * Store manual listinsurance to cache
     * 
     * @return List ListInsurance data
     */
    @GetMapping("/storeManualInsuranceCache")
    @ResponseStatus(HttpStatus.OK)
    public CommonResponse storeInsuranceToCache() {
	log.debug("Store Insurance to Cache");
	CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(), messageUtil.get("success", httpServletRequest.getLocale()));
	String redisStatus = env.getProperty("config.status.redis");
	
	// Store to Cache
	if("1".equalsIgnoreCase(redisStatus)) {
		List<ListInsurance> list = insuranceService.storeEMoneyToCache();
		if(null != list) {
			response.setData(list);
		}
	} else {
		response.setCode("999999");
		response.setMessage("redis not active");
		response.setData(new ArrayList<>());
	}
	
	
	return response;
    }

    /* Overrides: */}
