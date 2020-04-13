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
import id.co.asyst.bukopin.mobile.master.core.service.CurrencyService;
import id.co.asyst.bukopin.mobile.master.model.entity.Currency;

/**
 * REST Controller for Currency
 * 
 * @author Kartika Dwi H
 * @version $Revision$, Jan 30, 2020
 * @since 1.1.Alpha1
 */
@RestController
@RequestMapping("/currency")
public class CurrencyController {
    /* Constants: */
    private final Logger log = LoggerFactory.getLogger(CurrencyController.class);
    
    
    /* Attributes: */
    
    /**
    * currency service
    */
    @Autowired
    CurrencyService currencyService;
    
    
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
     * create new Currency
     * 
     * @param req to be create
     * 
     * @return Response status and Currency's data
     */
    @PostMapping("/createCurrency")
    @ResponseStatus(HttpStatus.CREATED)
    public CommonResponse createCurrency(@Valid @RequestBody CommonRequest<Currency> req) {
	log.debug("Creating Currency...");
	CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(), messageUtil.get("success", httpServletRequest.getLocale()));
	String redisStatus = env.getProperty("config.status.redis");
	
	Currency currency = currencyService.findByCode(req.getData().getCode());
	
	if(null != currency) {
		response.setCode(ResponseMessage.DUPLICATE_DATA.getCode());
	    response.setMessage(messageUtil.get("error.duplicate.data", httpServletRequest.getLocale()));
		 return response;
	} else {
		currency = new Currency();
		currency.setCode(req.getData().getCode());
		currency.setCurrencyNo(req.getData().getCurrencyNo());
		currency.setDescription(req.getData().getDescription());
		currencyService.saveCurrency(currency);
		
		//use redis?
		if("1".equalsIgnoreCase(redisStatus)) {
			currencyService.addItem(currency);
		}
		response.setData(currency);
	}
	return response;
    }

    
    
    /**
     * find All of Currency's data
     * 
     * @return List of Currency's data
     */
    @GetMapping("/allCurrency")
    @ResponseStatus(HttpStatus.OK)
    public CommonResponse findAllCurrency() {
	log.debug("Find all Currency....");
	CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(), messageUtil.get("success", httpServletRequest.getLocale()));
	String redisStatus = env.getProperty("config.status.redis");
	
	// get all Currency, use redis?
	List<Currency> findAll = new ArrayList<>();
	if("1".equalsIgnoreCase(redisStatus)) {
		findAll = currencyService.getAllCurrencyCache();
		if(findAll.isEmpty()) {
			log.debug("currency cache null, get from db ");
			findAll = currencyService.findAll();
			if(null == findAll) {
				response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
			    response.setMessage(messageUtil.get("error.data.not.found", httpServletRequest.getLocale()));
			    return response;
			} 
				for(Currency con : findAll) {
		        	//log.debug("add item to cache");
		        	currencyService.addItem(con);	
		        }
			
		}
	} else {
		findAll = currencyService.findAll();
	}
	
	response.setData(findAll);
	
	return response;
    }
    
    
    
    /**
     * update Currency
     * 
     * @param faq, Currency data to be update
     * @return Updated status and body of updated Currency's
     */
    @PutMapping("/updateCurrency")
    @ResponseStatus(HttpStatus.OK)
    public CommonResponse updateCurrency(@Valid @RequestBody CommonRequest<Currency> currency) {
	
	log.debug("Update Currency....");
	CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(), messageUtil.get("success", httpServletRequest.getLocale()));
	String redisStatus = env.getProperty("config.status.redis");
	
	Optional <Currency> oldData = currencyService.findById(currency.getData().getId());
	if(oldData.isPresent()){
		Currency currencyOld = oldData.get();
		currencyOld.setCode(currency.getData().getCode());
		currencyOld.setCurrencyNo(currency.getData().getCurrencyNo());
		currencyOld.setDescription(currency.getData().getDescription());
		
		if("1".equalsIgnoreCase(redisStatus)) {
			currencyService.updateItem(currencyOld);
		} else {
			currencyService.saveCurrency(currencyOld);
		}
		
		//currencyCache.updateItem(currencyOld);
		response.setData(currencyOld);		
	} else {
		// data not found
		response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
	    response.setMessage(messageUtil.get("error.data.not.found", httpServletRequest.getLocale()));
	    
	    return response;
	}
	return response;
    }
    
    /**
     * deleteCurrencybyId
     * @param id
     * @return Delete Status
     */
    @DeleteMapping("/deleteCurrency/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CommonResponse deleteCurrencybyId(@PathVariable Long id) {
	log.debug("Delete Currency by Id = " + id);
	String redisStatus = env.getProperty("config.status.redis");
	CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(), messageUtil.get("success", httpServletRequest.getLocale()));
	
	Optional<Currency> findById = currencyService.findById(id);
	if(!findById.isPresent()) {
		response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
	    response.setMessage(messageUtil.get("error.data.not.found", httpServletRequest.getLocale()));
	    
	    return response;
	}
	
	// use redis?
	if("1".equalsIgnoreCase(redisStatus)) {
		currencyService.deleteItem(id);
	} else {
		currencyService.deleteConfigurationById(id);
	}
	
	return response;
    }
    
    /**
     * find Currency by id
     * @param id
     * @return Currency's data
     */
    @GetMapping("/getCurrencyById/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CommonResponse findEMoneyById(@PathVariable Long id) {
	log.debug("Find currency by Id: "+id);
	String redisStatus = env.getProperty("config.status.redis");
	CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(), messageUtil.get("success", httpServletRequest.getLocale()));
	
	// get currency, user redis?
	Currency getById = new Currency();
	if("1".equalsIgnoreCase(redisStatus)) {
		getById = currencyService.getItem(id);
	} else {
		Optional<Currency> get = currencyService.findById(id);
		if(get.isPresent()) {
			getById = get.get();
		}
	}
	
	
	if(null != getById) {
		response.setData(getById);
	} else {
		response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
	    response.setMessage(messageUtil.get("error.data.not.found", httpServletRequest.getLocale()));
	    return response;
	}
	
	
	return response;
    }
    
    /**
     * store currency data to cache
     * 
     * @return List of Currency's data
     */
    @GetMapping("/storeManualCurrencyCache")
    @ResponseStatus(HttpStatus.OK)
    public CommonResponse storeEMoneyToCache() {
	log.debug("Store Currency to Cache");
	CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(), messageUtil.get("success", httpServletRequest.getLocale()));
	String redisStatus = env.getProperty("config.status.redis");
	
	if("1".equalsIgnoreCase(redisStatus)) {
		List<Currency> list = currencyService.storeCurrencyToCache();
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
