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
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.co.asyst.bukopin.mobile.master.core.repository.InstitutionRepository;
import id.co.asyst.bukopin.mobile.master.model.entity.Institution;


/**
 * Service for Institution
 * 
 * @author Kartika Dwi H
 * @version $Revision$, Feb 13, 2020
 * @since 1.0.Alpha1
 */
@Service
@Transactional
public class InstitutionService {
	
	private final Logger log = LoggerFactory.getLogger(InstitutionService.class);
	
    /* Constants: */
	public static final String KEY = "INSTITUTION";
	
    /* Attributes: */
    @Autowired
    InstitutionRepository institutionRepository;
    
   
    private RedisTemplate<String, Institution> redisInstitutionTemplate;
    private HashOperations hashOperations;

     //Constructors:
    public InstitutionService(RedisTemplate<String, Institution> redisInstitutionTemplate) {
        this.redisInstitutionTemplate = redisInstitutionTemplate;
        hashOperations = redisInstitutionTemplate.opsForHash();
    }
    
    
    /* Getters & setters for attributes: */

    /* Getters & setters for transient attributes: */

    /* Functionalities: */  
    /**
     * findAll Institution data
     * @return List of Currency data
     */
    @Transactional(readOnly = true)
    public List<Institution> findAll() {
	return institutionRepository.findAll();
    }
    
    /**
     * findById, Find Institution by Id
     * 
     * @param id
     * @return, Single data of Institution
     */
    @Transactional(readOnly = true)
    public Optional<Institution> findById(Long id) {
	return institutionRepository.findById(id);
    }
    
    
    /**
     * findByCode, Find Institution by Code
     * 
     * @param Code
     * @return, Single data of Institution
     */
    @Transactional(readOnly = true)
    public List<Institution> findByPrefixNo(Long prefix) {
	return institutionRepository.findInstitutionByPrefixId(prefix);
    }
    
    /**
     * findByPrefixNoAndInstitutionType
     * 
     * @param prefixId
     * @param institutionType
     * @return, Single data of Currency
     */
    @Transactional(readOnly = true)
    public Institution findByPrefixIdAndInstitutionType(Long prefixId, String institutionType) {
	return institutionRepository.findPrefixTelcoByPrefixNoAndType(prefixId, institutionType);
    }
    
    
    
    //Cache Repository
    
    // Getting a specific item by item id from table
    @SuppressWarnings("unchecked")
	public Institution getItem(Long id){
        return (Institution) hashOperations.get(KEY,id);
    }

    // Adding an item into redis database
    @SuppressWarnings("unchecked")
	public void addItem(Institution item){
        hashOperations.put(KEY,item.getId(),item);
    }
    // delete an item from database
    @SuppressWarnings("unchecked")
	public void deleteItem(Long id){
        hashOperations.delete(KEY,id);
    }

    //update an item from database
    public void updateItem(Institution item){
        addItem(item);
    }
    
    public void removeConfigKey(){
    	redisInstitutionTemplate.delete(KEY);
    }
    
    //Getting all item from table
  	@SuppressWarnings("rawtypes")
  	public List<Institution> getAllInstitutionCache(){ 
  		log.debug("get all currency from cache...");		
  		Map<Object, Object> map = redisInstitutionTemplate.opsForHash().entries(KEY);
  		log.debug("size from cache... "+map.size());
  		List<Institution> list = new ArrayList<>();
  		if(0 != map.size()) {
  			Iterator iter = map.entrySet().iterator();
  	  		
  	          while (iter.hasNext()) {
  	              Map.Entry entry = (Map.Entry) iter.next();
  	              list.add((Institution) entry.getValue());
  	          }
  		} else {
  			log.debug("cahce null, get Institution from db and update cache");
  			list = findAll();
  			if(null == list) {
  				log.info("data currency not found");
  			}  else {
  				for(Institution con : list) {
  		        	log.debug("add item to cache");
  		        	addItem(con);	
  		        }
  			}
  		}
          return list;
      }
  	
  	
  //Getting all item from table
  	@SuppressWarnings("rawtypes")
  	public Institution findByPrefixIdAndInstitutionTypeCache(Long prefixId, String type){ 
  		log.debug("get all institution from cache...");		
  		Map<Object, Object> map = redisInstitutionTemplate.opsForHash().entries(KEY);
  		log.debug("size from cache... "+map.size());
  		Iterator iter = map.entrySet().iterator();
  		Institution cur = new Institution();
          while (iter.hasNext()) {
              Map.Entry entry = (Map.Entry) iter.next();
              Institution c = (Institution) entry.getValue();
              int retResult =  prefixId.compareTo(c.getPrefixTelcoId().getId());     
              if(retResult == 0 && type.equalsIgnoreCase(c.getInstitutionType())) {
            	  cur = c;
            	  break;
              }              
          }
        return cur;
   }
      
  	//Store all item from table to cache
  	@SuppressWarnings("rawtypes")
  	public List<Institution> storeInstitutionToCache(){ 
  		log.debug("store all Institution to cache...");
  		//set expired time for cache CONFIG
  		//redisTemplate.expire(KEY, 1800, TimeUnit.SECONDS); // 24H = 86400 second
  		redisInstitutionTemplate.hasKey(KEY);
  		//redisTemplateEMoney.expire(KEY, 1800, TimeUnit.SECONDS); // 30 minute = 1800 second
  		
  		//remove old cache
  		Map<Object, Object> map = redisInstitutionTemplate.opsForHash().entries(KEY);
  		log.debug("size... "+map.size());
  		Iterator iter = map.entrySet().iterator();
  		List<Institution> list = new ArrayList<>();
          while (iter.hasNext()) {
              Map.Entry entry = (Map.Entry) iter.next();
              list.add((Institution) entry.getValue());
          }
  		
          if(null != list) {
          	log.debug("remove old cache... "+list.size());
          	this.removeConfigKey();
          } 
  		
  		List<Institution> item = this.findAll();
          for(Institution con : item) {
          	//log.debug("add item to cache "+con.getCode());
          	addItem(con);	
          }
          
          //check new cache
          map = redisInstitutionTemplate.opsForHash().entries(KEY);
  		log.debug("size... "+map.size());
  		iter = map.entrySet().iterator();
  		list = new ArrayList<>();
          while (iter.hasNext()) {
              Map.Entry entry = (Map.Entry) iter.next();
              list.add((Institution) entry.getValue());
          }
          
          //redisTemplateEMoney.expireAt(key, date)
          redisInstitutionTemplate.expire(KEY, 86400, TimeUnit.SECONDS); // 30 minute = 1800 second
          log.debug("Institution TTL : "+redisInstitutionTemplate.getExpire(KEY));
          
          return list;
      }
    
  	@Transactional
  	public Institution findCodeByProvider(String provider) {
  	    return institutionRepository.findProvider(provider);
  	}
    
}
