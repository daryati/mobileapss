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
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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
import id.co.asyst.bukopin.mobile.master.core.service.ConfigurationService;

import id.co.asyst.bukopin.mobile.master.model.entity.Configuration;

/**
 * REST Controller for Configuration
 * 
 * @author Kartika Dwi H
 * @version $Revision$, Nov 27, 2019
 * @since 2.0
 */
@RestController
@RequestMapping("/config")
public class ConfigurationController {
    /* Constants: */
    private final Logger log = LoggerFactory.getLogger(ConfigurationController.class);
    /* Attributes: */
    
    
    @Autowired
    ConfigurationService configService;
    
    
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
    
    
    /**
     * Environment
     */
    @Autowired
    private Environment env;
    
    
    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */

    /* Getters & setters for transient attributes: */

    /* Functionalities: */
    
    /**
     * create new Configuration
     * 
     * @param configuration to be create
     * 
     * @return Response status and Configuration's data
     */
    @PostMapping("/createConfig")
    @ResponseStatus(HttpStatus.CREATED)
    @Cacheable(value = "configurations", key = "#p0")
    public CommonResponse createConfig(@Valid @RequestBody CommonRequest<Configuration> config) {
	log.debug("Creating Configuration...");
	CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(), messageUtil.get("success", httpServletRequest.getLocale()));
	String redisStatus = env.getProperty("config.status.redis");
	
	Configuration configuration = configService.findByName(config.getData().getName());
	
	if(null != configuration) {
		 response.setCode("999999");
		 response.setMessage("config name duplicate");
		 return response;
	} else {
		configuration = new Configuration();
		configuration.setCreateBy(config.getData().getCreateBy());
		configuration.setCreateDate(new Date());
		configuration.setDescription(config.getData().getDescription());
		configuration.setName(config.getData().getName());
		configuration.setUpdateBy(config.getData().getUpdateBy());
		configuration.setUpdateDate(new Date());
		configuration.setValue(config.getData().getValue());
		configService.saveConfiguration(configuration);
		
		// use redis??
		if("1".equalsIgnoreCase(redisStatus)) {
			configService.addItem(configuration);
		}
		response.setData(configuration);
	}
	return response;
    }

    
    
    /**
     * find All of Configuration's data
     * 
     * @return List of Configuration's data
     */
    @GetMapping("/allConfig")
    @ResponseStatus(HttpStatus.OK)
    public CommonResponse findAllConfig() {
	log.debug("Find all Configuration....");
	CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(), messageUtil.get("success", httpServletRequest.getLocale()));
	String redisStatus = env.getProperty("config.status.redis");
	
	// get all configuration from cache, use redis?
	List<Configuration> findAll = new ArrayList<>();
	if("1".equalsIgnoreCase(redisStatus)) {
		findAll = configService.getAllConfigCache();
		
		if(findAll.isEmpty()) {
			log.debug("config cache null, get from db ");
			findAll = configService.findAll();
			if(null == findAll) {
				response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
			    response.setMessage(messageUtil.get("error.data.not.found", httpServletRequest.getLocale()));
			    return response;
			} 
				for(Configuration con : findAll) {
		        	//log.debug("add item to cache");
		        	configService.addItem(con);	
		        }
			
		}
	} else {
		findAll = configService.findAll();
	}
	
	
	response.setData(findAll);
	
	return response;
    }
    
    /**
     * find Configuration by name
     * 
     * @return Configuration's data
     */
    @GetMapping("/getConfigByName/{name}")
    @ResponseStatus(HttpStatus.OK)
    public CommonResponse findConfigByName(@PathVariable String name) {
	log.debug("Find Configuration by name: "+name);
	CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(), messageUtil.get("success", httpServletRequest.getLocale()));
	String redisStatus = env.getProperty("config.status.redis");
	
	// get configuration, use Redis??
	Configuration getByName = new Configuration();
	if("1".equalsIgnoreCase(redisStatus)) {
		getByName = configService.getItem(name);
	} else {
		getByName = configService.findByName(name);
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
     * updateConfiguration
     * 
     * @param con, Configuration data to be update
     * @return Updated status and body of updated Configuration's
     */
    @PutMapping("/updateConfig")
    @ResponseStatus(HttpStatus.OK)
    public CommonResponse updateConfig(@Valid @RequestBody CommonRequest<Configuration> con) {
	
	log.debug("Update Configuration....");
	CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(), messageUtil.get("success", httpServletRequest.getLocale()));
	String redisStatus = env.getProperty("config.status.redis");
	
	Date now = new Date();
	Configuration oldConfig = configService.findByName(con.getData().getName());
	if(null != oldConfig) {
		oldConfig.setDescription(con.getData().getDescription());
		oldConfig.setValue(con.getData().getValue());
		oldConfig.setUpdateDate(now);
		oldConfig.setUpdateBy(con.getData().getUpdateBy());
		
		if("1".equalsIgnoreCase(redisStatus)) {
			configService.updateItem(oldConfig);
		} else {
			configService.saveConfiguration(oldConfig);
		}
		response.setData(oldConfig);
	} else {
		response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
	    response.setMessage(messageUtil.get("error.data.not.found", httpServletRequest.getLocale()));
	    
	    return response;
	}
	
	
	return response;
    }
    
    /**
     * deleteConfigurationbyId
     * @param id
     * @return Delete Status
     */
    @DeleteMapping("/deleteConfig/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CommonResponse deleteConfigbyId(@PathVariable Long id) {
	log.debug("Delete Config by Id = " + id);
	String redisStatus = env.getProperty("config.status.redis");
	CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(), messageUtil.get("success", httpServletRequest.getLocale()));
	
	Optional<Configuration> findById = configService.findById(id);
	if(!findById.isPresent()) {
		response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
	    response.setMessage(messageUtil.get("error.data.not.found", httpServletRequest.getLocale()));
	    
	    return response;
	}
	
	//use redis?
	if("1".equalsIgnoreCase(redisStatus)) {
		configService.deleteItem(findById.get().getName());
	} else {
		configService.deleteConfigurationById(id);
	}
	
	return response;
    }
    
    

    /* Overrides: */}
