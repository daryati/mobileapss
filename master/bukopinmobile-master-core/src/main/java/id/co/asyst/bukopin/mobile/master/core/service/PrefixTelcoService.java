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

import id.co.asyst.bukopin.mobile.common.model.ResponseMessage;
import id.co.asyst.bukopin.mobile.master.core.repository.PrefixTelcoRepository;
import id.co.asyst.bukopin.mobile.master.model.entity.PrefixTelco;

/**
 * Service for Configuration 
 * 
 * @author Kartika Dwi H
 * @version $Revision$, Feb 13, 2020
 * @since 1.0.Alpha1
 */
@Service
@Transactional
public class PrefixTelcoService {
	
	private final Logger log = LoggerFactory.getLogger(PrefixTelcoService.class);
	
    /* Constants: */
	public static final String KEY = "PREFIXTELCO";
	
    /* Attributes: */
    @Autowired
    PrefixTelcoRepository prefixTelcoRepository;
    
   
    private RedisTemplate<String, PrefixTelco> redisPrefixTelcoTemplate;
    private HashOperations hashOperations;

     //Constructors:
    public PrefixTelcoService(RedisTemplate<String, PrefixTelco> redisPrefixTelcoTemplate) {
        this.redisPrefixTelcoTemplate = redisPrefixTelcoTemplate;
        hashOperations = redisPrefixTelcoTemplate.opsForHash();
    }
    
    
    /* Getters & setters for attributes: */

    /* Getters & setters for transient attributes: */

    /* Functionalities: */
    /**
     * findAll PrefixTelco data
     * @return List of PrefixTelco data
     */
    @Transactional(readOnly = true)
    public List<PrefixTelco> findAll() {
	return prefixTelcoRepository.findAll();
    }
    
    /**
     * findById, Find PrefixTelco by Id
     * 
     * @param id
     * @return, Single data of PrefixTelco
     */
    @Transactional(readOnly = true)
    public Optional<PrefixTelco> findById(Long id) {
	return prefixTelcoRepository.findById(id);
    }
    
    
    /**
     * findByCode, Find PrefixTelco by Code
     * 
     * @param Code
     * @return, Single data of PrefixTelco
     */
    @Transactional(readOnly = true)
    public List<PrefixTelco> findByPrefixNo(String prefix) {
	return prefixTelcoRepository.findPrefixTelcoByPrefixNo(prefix);
    }
    
    /**
     * findByPrefixNoAndType
     * 
     * @param prefixId
     * @param Type
     * @return, Single data of PrefixTelco
     */
    @Transactional(readOnly = true)
    public PrefixTelco findByPrefixNoAndType(String prefix, String type) {
	return prefixTelcoRepository.findPrefixTelcoByPrefixNoAndType(prefix, type);
    }
    
    @Transactional(readOnly = true)
    public PrefixTelco findByIdAndInstitutionType(Long id, String institutionType) {
	return prefixTelcoRepository.findPrefixTelcoBidNoAndIntitutionType(id, institutionType);
    }
    
    /**
     * findOneByPrefixNo
     * @param prefix
     * @return
     */
    @Transactional(readOnly = true)
    public Object findOneByPrefixNo(String prefix) {
	return prefixTelcoRepository.findOnePrefixTelcoByPrefixNo(prefix);
    }
    
    //-- Cache Repository (REDIS)
    
    // Getting a specific item by item id from table
    @SuppressWarnings("unchecked")
	public PrefixTelco getItem(Long id){
        return (PrefixTelco) hashOperations.get(KEY,id);
    }

    // Adding an item into redis database
    @SuppressWarnings("unchecked")
	public void addItem(PrefixTelco item){
        hashOperations.put(KEY,item.getId(),item);
    }
    // delete an item from database
    @SuppressWarnings("unchecked")
	public void deleteItem(Long id){
        hashOperations.delete(KEY,id);
    }

    //update an item from database
    public void updateItem(PrefixTelco item){
        addItem(item);
    }
    
   
    public void removeConfigKey(){
    	redisPrefixTelcoTemplate.delete(KEY);
    }
    
    //Getting all item from table
  	@SuppressWarnings("rawtypes")
  	public List<PrefixTelco> getAllPrefixTelcoCache(){ 
  		log.debug("get all PrefixTelco from cache...");		
  		Map<Object, Object> map = redisPrefixTelcoTemplate.opsForHash().entries(KEY);
  		log.debug("size from cache... "+map.size());
  		List<PrefixTelco> list = new ArrayList<>();
  		if(0 != map.size()) {
  			Iterator iter = map.entrySet().iterator();
  	  		
  	          while (iter.hasNext()) {
  	              Map.Entry entry = (Map.Entry) iter.next();
  	              list.add((PrefixTelco) entry.getValue());
  	          }
  		} else {
  			log.debug("cache null, get PrefixTelco from db and update cache");
  			list = findAll();
  			if(null == list) {
  				log.info("data PrefixTelco not found");
  			}  else {
  				for(PrefixTelco con : list) {
  		        	log.debug("add item to cache");
  		        	addItem(con);	
  		        }
  			}
  		}
          return list;
      }
  	
  //Getting all item from table
  	@SuppressWarnings("rawtypes")
  	public PrefixTelco findByPrefixNoAndTypeCache(String prefixNo, String type){ 
  		log.debug("get all PrefixTelco from cache...");		
  		Map<Object, Object> map = redisPrefixTelcoTemplate.opsForHash().entries(KEY);
  		log.debug("size from PrefixTelco... "+map.size());
  		Iterator iter = map.entrySet().iterator();
  		PrefixTelco cur = new PrefixTelco();
          while (iter.hasNext()) {
              Map.Entry entry = (Map.Entry) iter.next();
              PrefixTelco c = (PrefixTelco) entry.getValue();
              if(c.getPrefixNo().contains(prefixNo) && type.equalsIgnoreCase(c.getType())) {
            	  cur = c;
            	  break;
              }              
          }
        return cur;
   }
  	
  //Getting all item from table
  	@SuppressWarnings("rawtypes")
  	public PrefixTelco findByPrefixNoCache(String prefixNo){ 
  		log.debug("get all currency from cache...");		
  		Map<Object, Object> map = redisPrefixTelcoTemplate.opsForHash().entries(KEY);
  		log.debug("size from cache... "+map.size());
  		Iterator iter = map.entrySet().iterator();
  		PrefixTelco cur = new PrefixTelco();
          while (iter.hasNext()) {
              Map.Entry entry = (Map.Entry) iter.next();
              PrefixTelco c = (PrefixTelco) entry.getValue();
              if(prefixNo.equalsIgnoreCase(c.getPrefixNo())) {
            	  cur = c;
            	  break;
              }      
          }
        return cur;
   }
      
  	//Store all item from table to cache
  	@SuppressWarnings("rawtypes")
  	public List<PrefixTelco> storePrefixTelcoToCache(){ 
  		log.debug("store all PrefixTelco to cache...");
  		//set expired time for cache CONFIG
  		//redisTemplate.expire(KEY, 1800, TimeUnit.SECONDS); // 24H = 86400 second
  		redisPrefixTelcoTemplate.hasKey(KEY);
  		//redisTemplateEMoney.expire(KEY, 1800, TimeUnit.SECONDS); // 30 minute = 1800 second
  		
  		//remove old cache
  		Map<Object, Object> map = redisPrefixTelcoTemplate.opsForHash().entries(KEY);
  		log.debug("size... "+map.size());
  		Iterator iter = map.entrySet().iterator();
  		List<PrefixTelco> list = new ArrayList<>();
          while (iter.hasNext()) {
              Map.Entry entry = (Map.Entry) iter.next();
              list.add((PrefixTelco) entry.getValue());
          }
  		
          if(null != list) {
          	log.debug("remove old cache... "+list.size());
          	this.removeConfigKey();
          } 
  		
  		List<PrefixTelco> item = this.findAll();
          for(PrefixTelco con : item) {
          	addItem(con);	
          }
          
          //check new cache
          map = redisPrefixTelcoTemplate.opsForHash().entries(KEY);
  		log.debug("size... "+map.size());
  		iter = map.entrySet().iterator();
  		list = new ArrayList<>();
          while (iter.hasNext()) {
              Map.Entry entry = (Map.Entry) iter.next();
              PrefixTelco telco = (PrefixTelco) entry.getValue();
              list.add(telco);
          }
          
          //redisTemplateEMoney.expireAt(key, date)
          redisPrefixTelcoTemplate.expire(KEY, 86400, TimeUnit.SECONDS); // 30 minute = 1800 second
          log.debug("PrefixTelco TTL : "+redisPrefixTelcoTemplate.getExpire(KEY));
          
          return list;
      }
    
    
}
