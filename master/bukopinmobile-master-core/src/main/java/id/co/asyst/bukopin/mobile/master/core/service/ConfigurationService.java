/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.master.core.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.co.asyst.bukopin.mobile.master.core.repository.ConfigurationRepository;
import id.co.asyst.bukopin.mobile.master.model.entity.Configuration;

/**
 * Service for Configuration 
 * 
 * @author Kartika Dwi H
 * @version $Revision$, Dec, 2019
 * @since 2.0
 */
@Service
@Transactional
public class ConfigurationService {
	
	private final Logger log = LoggerFactory.getLogger(ConfigurationService.class);
	
    /* Constants: */
	public static final String KEY = "CONFIG";
	
    /* Attributes: */
    @Autowired
    ConfigurationRepository configurationRepository;
    
    /* REDIS */
    private RedisTemplate<String, Configuration> redisTemplate;
    private HashOperations hashOperations;

     //Constructors: 
    public ConfigurationService(RedisTemplate<String, Configuration> redisTemplate) {
        this.redisTemplate = redisTemplate;
        hashOperations = redisTemplate.opsForHash();
    }
    
    
    /* Getters & setters for attributes: */

    /* Getters & setters for transient attributes: */

    /* Functionalities: */
    /**
     * create Config
     * 
     * @param config, the COnfiguration to be saved
     * @return response status and Configuration's data
     */
    @Transactional
    public Configuration saveConfiguration(Configuration config) {
	return configurationRepository.save(config);
    }
    
    /**
     * findAll Configuration data
     * @return List of Configuration's data
     */
    @Transactional(readOnly = true)
    public List<Configuration> findAll() {
	return configurationRepository.findAll();
    }
    
    /**
     * findById, Find Configuration by Id
     * 
     * @param id
     * @return, Single data of Configuration
     */
    @Transactional(readOnly = true)
    public Optional<Configuration> findById(Long id) {
	return configurationRepository.findById(id);
    }
    
    
    /**
     * findByName, Find Configuration by Name
     * 
     * @param Name
     * @return, Single data of Configuration
     */
    @Transactional(readOnly = true)
    public Configuration findByName(String name) {
	return configurationRepository.findConfigByName(name);
    }
    
    /**
     * delete Configuration by Configuration's Id
     * @param id of the FConfigurationAQ
     */
    @Transactional
    public void deleteConfigurationById(Long id) {
	configurationRepository.deleteById(id);
    }

    
    //-- REDIS function  
    //Cache Repository
    
    // Getting a specific item by item id from table
    @SuppressWarnings("unchecked")
	public Configuration getItem(String name){
        return (Configuration) hashOperations.get(KEY,name);
    }

    // Adding an item into redis database
    @SuppressWarnings("unchecked")
	public void addItem(Configuration item){
        hashOperations.put(KEY,item.getName(),item);
    }
    
    //delete an item from database
    @SuppressWarnings("unchecked")
	public void deleteItem(String name){
        hashOperations.delete(KEY,name);
    }

    //update an item from database
    public void updateItem(Configuration item){
        addItem(item);
    }
    
    public void removeConfigKey(){
    	redisTemplate.delete(KEY);
    }
    
    //Getting all item from table
	@SuppressWarnings("rawtypes")
	public List<Configuration> getAllConfigCache(){ 
		log.debug("get all config from cache...");
		
		//set expired time for cache CONFIG
		//redisTemplate.expire(KEY, 1800, TimeUnit.SECONDS);
		
		Map<Object, Object> map = redisTemplate.opsForHash().entries(KEY);
		log.debug("size... "+map.size());
		Iterator iter = map.entrySet().iterator();
		List<Configuration> list = new ArrayList<>();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            list.add((Configuration) entry.getValue());
        }
        return list;
    }
    
    
}
