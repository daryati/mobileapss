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
import id.co.asyst.bukopin.mobile.master.core.service.ListEMoneyService;
import id.co.asyst.bukopin.mobile.master.model.entity.ListEMoney;

/**
 * REST Controller for emoney
 * 
 * @author Kartika Dwi H
 * @version $Revision$, Jan 30, 2020
 * @since 1.1.Alpha1
 */
@RestController
@RequestMapping("/listEMoney")
public class ListEMoneyController {
    /* Constants: */
    private final Logger log = LoggerFactory.getLogger(ListEMoneyController.class);
    /* Attributes: */
    
    /**
     * Environment
     */
    @Autowired
    private Environment env;
    
    /**
     * emoney service
     */
    @Autowired
    ListEMoneyService emoneyService;
    
    
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
     * create new Emoney
     * 
     * @param req to be create
     * 
     * @return Response status and EMoney's data
     */
    @PostMapping("/createEMoney")
    @ResponseStatus(HttpStatus.CREATED)
   // @Cacheable(value = "emoney", key = "#p0")
    public CommonResponse createEmoney(@Valid @RequestBody CommonRequest<ListEMoney> req) {
	log.debug("Creating EMoney...");
	CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(), messageUtil.get("success", httpServletRequest.getLocale()));
	String redisStatus = env.getProperty("config.status.redis");
	ListEMoney emoney = emoneyService.findByName(req.getData().getName());
	
	if(null != emoney) {
		response.setCode(ResponseMessage.DUPLICATE_DATA.getCode());
	    response.setMessage(messageUtil.get("error.duplicate.data", httpServletRequest.getLocale()));
		 return response;
	} else {
		emoney = new ListEMoney();
		emoney.setBottomLimit(req.getData().getBottomLimit());
		emoney.setName(req.getData().getName());
		emoney.setUpperLimit(req.getData().getUpperLimit());
		emoneyService.saveEmoney(emoney);
		
		//-- set redis
		if("1".equalsIgnoreCase(redisStatus)) {
			log.debug("set emoney data to cache");
			emoneyService.addItem(emoney);
		}
		
		response.setData(emoney);
	}
	return response;
    }

    
    
    /**
     * find All of EMoney's data
     * 
     * @return List of EMoney's data
     */
    @GetMapping("/allEMoney")
    @ResponseStatus(HttpStatus.OK)
 // @Cacheable(value = "emoney", key = "#p0")
    public CommonResponse findAllEMoney() {
	log.debug("Find all EMoney....");
	String redisStatus = env.getProperty("config.status.redis");
	
	// get all configuration, user redis?	
	List<ListEMoney> findAll = new ArrayList<>();
	if("1".equalsIgnoreCase(redisStatus)) {
		log.debug("get from cache");
		findAll = emoneyService.getAllEMoneyCache();
	} else {
		//findAll = emoneyService.findAll();
		findAll = emoneyService.findAllEmoneyWithPic();
	}
	
	CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(), messageUtil.get("success", httpServletRequest.getLocale()));
	if(findAll.isEmpty()) {
		log.debug("emoney cache null, get from db ");
		findAll = emoneyService.findAllEmoneyWithPic();
		if(null == findAll) {
			response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
		    response.setMessage(messageUtil.get("error.data.not.found", httpServletRequest.getLocale()));
		    return response;
		} 
			for(ListEMoney con : findAll) {
	        	log.debug("add item to cache");
	        	emoneyService.addItem(con);	
	        }
		
	}
	response.setData(findAll);
	
	return response;
    }
    
    
    
    
    /**
     * updateEMoney
     * 
     * @param faq, EMoney data to be update
     * @return Updated status and body of updated EMoney's
     */
    @PutMapping("/updateEMoney")
    @ResponseStatus(HttpStatus.OK)
    public CommonResponse updateConfig(@Valid @RequestBody CommonRequest<ListEMoney> emoney) {	
	log.debug("Update EMoney....");
	CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(), messageUtil.get("success", httpServletRequest.getLocale()));
	String redisStatus = env.getProperty("config.status.redis");
	
	Optional <ListEMoney> oldData = emoneyService.findById(emoney.getData().getId());
	if(oldData.isPresent()){
		ListEMoney emoneyOld = oldData.get();
		emoneyOld.setBottomLimit(emoney.getData().getBottomLimit());
		emoneyOld.setName(emoney.getData().getName());
		emoneyOld.setUpperLimit(emoney.getData().getUpperLimit());		
		// use redis?
		if("1".equalsIgnoreCase(redisStatus)) {
			log.debug("update from cache");
			emoneyService.updateItem(emoneyOld);
		} else {
			emoneyService.saveEmoney(emoneyOld);
		}
		response.setData(emoneyOld);
		
	} else {
		// data not found
		response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
	    response.setMessage(messageUtil.get("error.data.not.found", httpServletRequest.getLocale()));
	    
	    return response;
	}	
	return response;
    }
    
    /**
     * deleteEMoneybyId
     * @param id
     * @return Delete Status
     */
    @DeleteMapping("/deleteEMoney/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CommonResponse deleteEMOneybyId(@PathVariable Long id) {
	log.debug("Delete EMoney by Id = " + id);
	String redisStatus = env.getProperty("config.status.redis");
	
	CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(), messageUtil.get("success", httpServletRequest.getLocale()));
	
	Optional<ListEMoney> findById = emoneyService.findById(id);
	if(!findById.isPresent()) {
		response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
	    response.setMessage(messageUtil.get("error.data.not.found", httpServletRequest.getLocale()));
	    
	    return response;
	}
	
	// use redis?
	if("1".equalsIgnoreCase(redisStatus)) {
		log.debug("delete from cache");
		emoneyService.deleteItem(id);
	} else {
		emoneyService.deleteEMoneyById(id);
	}
	return response;
    }
    
    @GetMapping("/getEmoneyById/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CommonResponse findEMoneyById(@PathVariable Long id) {
	log.debug("Find EMoney by Id: "+id);
	CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(), messageUtil.get("success", httpServletRequest.getLocale()));
	String redisStatus = env.getProperty("config.status.redis");
	
	// use redis??
	ListEMoney getByName = new ListEMoney();
	if("1".equalsIgnoreCase(redisStatus)) {
		log.debug("get from cache");
		getByName = emoneyService.getItem(id);
	} else {
		Optional<ListEMoney> emoney = emoneyService.findById(id);
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
     * Store manual emoney to cache
     * 
     * @return List of EMoney's data
     */
    @GetMapping("/storeManualEmoneyCache")
    @ResponseStatus(HttpStatus.OK)
    public CommonResponse storeEMoneyToCache() {
	log.debug("Store EMoney to Cache");
	CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(), messageUtil.get("success", httpServletRequest.getLocale()));
	String redisStatus = env.getProperty("config.status.redis");
	
	// Store to Cache
	if("1".equalsIgnoreCase(redisStatus)) {
		List<ListEMoney> list = emoneyService.storeEMoneyToCache();
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
