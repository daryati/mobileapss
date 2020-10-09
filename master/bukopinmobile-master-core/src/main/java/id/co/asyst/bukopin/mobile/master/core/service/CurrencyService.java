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
import id.co.asyst.bukopin.mobile.master.core.repository.ConfigurationRepository;
import id.co.asyst.bukopin.mobile.master.core.repository.CurrencyRepository;
import id.co.asyst.bukopin.mobile.master.model.entity.Configuration;
import id.co.asyst.bukopin.mobile.master.model.entity.Currency;
import id.co.asyst.bukopin.mobile.master.model.entity.ListEMoney;

/**
 * Service for Currency
 * 
 * @author Kartika Dwi H
 * @version $Revision$, Dec, 2019
 * @since 2.0
 */
@Service
@Transactional
public class CurrencyService {
	
	private final Logger log = LoggerFactory.getLogger(CurrencyService.class);
	
    /* Constants: */
	public static final String KEY = "CURRENCY";
	
    /* Attributes: */
    @Autowired
    CurrencyRepository currencyRepository;
    
   
    private RedisTemplate<String, Currency> redisCurrencyTemplate;
    private HashOperations hashOperations;

     //Constructors:
    public CurrencyService(RedisTemplate<String, Currency> redisCurrencyTemplate) {
        this.redisCurrencyTemplate = redisCurrencyTemplate;
        hashOperations = redisCurrencyTemplate.opsForHash();
    }
    
    
    /* Getters & setters for attributes: */

    /* Getters & setters for transient attributes: */

    /* Functionalities: */
    /**
     * create Currency
     * 
     * @param currency, the Currency to be saved
     * @return response status and Currency's data
     */
    @Transactional
    public Currency saveCurrency(Currency currency) {
	return currencyRepository.save(currency);
    }
    
    /**
     * findAll Currency data
     * @return List of Currency data
     */
    @Transactional(readOnly = true)
    public List<Currency> findAll() {
	return currencyRepository.findAll();
    }
    
    /**
     * findById, Find Currency by Id
     * 
     * @param id
     * @return, Single data of Currency
     */
    @Transactional(readOnly = true)
    public Optional<Currency> findById(Long id) {
	return currencyRepository.findById(id);
    }
    
    
    /**
     * findByCode, Find Currency by Code
     * 
     * @param Code
     * @return, Single data of Currency
     */
    @Transactional(readOnly = true)
    public Currency findByCode(String code) {
	return currencyRepository.findCurrencyByCode(code);
    }
    
    /**
     * findBycurrencyNo, Find Currency by currency no
     * 
     * @param Code
     * @return, Single data of Currency
     */
    @Transactional(readOnly = true)
    public Currency findByCurrencyNo(Integer currencyNo) {
	return currencyRepository.findCurrencyByCurrencyNo(currencyNo);
    }
    
    /**
     * delete Currency by Currency Id
     * @param id of the Currency
     */
    @Transactional
    public void deleteConfigurationById(Long id) {
    	currencyRepository.deleteById(id);
    }

    
    //-- REDIS Function 
    //Cache Repository
    
    // Getting a specific item by item id from table
    @SuppressWarnings("unchecked")
	public Currency getItem(Long id){
        return (Currency) hashOperations.get(KEY,id);
    }

    // Adding an item into redis database
    @SuppressWarnings("unchecked")
	public void addItem(Currency item){
        hashOperations.put(KEY,item.getId(),item);
    }
    // delete an item from database
    @SuppressWarnings("unchecked")
	public void deleteItem(Long id){
        hashOperations.delete(KEY,id);
    }

    //update an item from database
    public void updateItem(Currency item){
        addItem(item);
    }
    
    public void removeConfigKey(){
    	redisCurrencyTemplate.delete(KEY);
    }
    
    //Getting all item from table
  	@SuppressWarnings("rawtypes")
  	public List<Currency> getAllCurrencyCache(){ 
  		log.debug("get all currency from cache...");		
  		Map<Object, Object> map = redisCurrencyTemplate.opsForHash().entries(KEY);
  		log.debug("size from cache... "+map.size());
  		List<Currency> list = new ArrayList<>();
  		if(0 != map.size()) {
  			Iterator iter = map.entrySet().iterator();
  	  		
  	          while (iter.hasNext()) {
  	              Map.Entry entry = (Map.Entry) iter.next();
  	              list.add((Currency) entry.getValue());
  	          }
  		} else {
  			log.debug("cahce null, get currency from db and update cache");
  			list = findAll();
  			if(null == list) {
  				log.info("data currency not found");
  			}  else {
  				for(Currency con : list) {
  		        	log.debug("add item to cache");
  		        	addItem(con);	
  		        }
  			}
  		}
          return list;
      }
  	
  /*//Getting all item from table
  	@SuppressWarnings("rawtypes")
  	public Currency getCurrencyByCodeCache(String code){ 
  		log.debug("get all currency from cache...");		
  		Map<Object, Object> map = redisCurrencyTemplate.opsForHash().entries(KEY);
  		log.debug("size from cache... "+map.size());
  		Iterator iter = map.entrySet().iterator();
  		Currency cur = new Currency();
          while (iter.hasNext()) {
              Map.Entry entry = (Map.Entry) iter.next();
              Currency c = (Currency) entry.getValue();
              if(code.equalsIgnoreCase(c.getCode())) {
            	  cur = c;
            	  break;
              }              
          }
        return cur;
   }*/
  	
  //Getting all item from table
  	@SuppressWarnings("rawtypes")
  	public Currency getCurrencyByCurrencyNoCache(String currencyNo){ 
  		log.debug("get all currency from cache...");		
  		Map<Object, Object> map = redisCurrencyTemplate.opsForHash().entries(KEY);
  		log.debug("size from cache... "+map.size());
  		Iterator iter = map.entrySet().iterator();
  		Currency cur = new Currency();
          while (iter.hasNext()) {
              Map.Entry entry = (Map.Entry) iter.next();
              Currency c = (Currency) entry.getValue();
              int retResult =  Integer.valueOf(currencyNo).compareTo(c.getCurrencyNo());     
              if(retResult == 0) {
            	  cur = c;
            	  break;
              }              
          }
        return cur;
   }
      
  	//Store all item from table to cache
  	@SuppressWarnings("rawtypes")
  	public List<Currency> storeCurrencyToCache(){ 
  		log.debug("store all currency to cache...");
  		//set expired time for cache CONFIG
  		//redisTemplate.expire(KEY, 1800, TimeUnit.SECONDS); // 24H = 86400 second
  		redisCurrencyTemplate.hasKey(KEY);
  		//redisTemplateEMoney.expire(KEY, 1800, TimeUnit.SECONDS); // 30 minute = 1800 second
  		
  		//remove old cache
  		Map<Object, Object> map = redisCurrencyTemplate.opsForHash().entries(KEY);
  		log.debug("size... "+map.size());
  		Iterator iter = map.entrySet().iterator();
  		List<Currency> list = new ArrayList<>();
          while (iter.hasNext()) {
              Map.Entry entry = (Map.Entry) iter.next();
              list.add((Currency) entry.getValue());
          }
  		
          if(null != list) {
          	log.debug("remove old cache... "+list.size());
          	this.removeConfigKey();
          } 
  		
  		List<Currency> item = this.findAll();
          for(Currency con : item) {
          	//log.debug("add item to cache "+con.getCode());
          	addItem(con);	
          }
          
          //check new cache
          map = redisCurrencyTemplate.opsForHash().entries(KEY);
  		log.debug("size... "+map.size());
  		iter = map.entrySet().iterator();
  		list = new ArrayList<>();
          while (iter.hasNext()) {
              Map.Entry entry = (Map.Entry) iter.next();
              list.add((Currency) entry.getValue());
          }
          
          //redisTemplateEMoney.expireAt(key, date)
          redisCurrencyTemplate.expire(KEY, 86400, TimeUnit.SECONDS); // 30 minute = 1800 second
          log.debug("CURRENCY TTL : "+redisCurrencyTemplate.getExpire(KEY));
          
          return list;
      }
    
    
}
