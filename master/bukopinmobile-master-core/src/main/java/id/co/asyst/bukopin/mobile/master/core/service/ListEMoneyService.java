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

import java.math.BigInteger;
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

import com.fasterxml.jackson.databind.ObjectMapper;

import id.co.asyst.bukopin.mobile.master.core.repository.ListEMoneyRepository;
import id.co.asyst.bukopin.mobile.master.model.entity.ListEMoney;
import id.co.asyst.bukopin.mobile.master.model.entity.PrefixTelco;

/**
 * Service for List E-Money
 * 
 * @author Kartika Dwi H
 * @version $Revision$, Jan 30, 2020
 * @since 1.1.Alpha1
 */
@Service
@Transactional
public class ListEMoneyService {
	
	private final Logger log = LoggerFactory.getLogger(ListEMoneyService.class);
	
    /* Constants: */
	public static final String KEY = "EMONEY";
	
    /* Attributes: */
    @Autowired
    ListEMoneyRepository listEMoneyRepository;
   
    //@Autowired
    private RedisTemplate<String, ListEMoney> redisTemplateEMoney;
    
    //@Autowired
    private HashOperations hashOperations;

     //Constructors: 
    public ListEMoneyService(RedisTemplate<String, ListEMoney> redisTemplate) {
        this.redisTemplateEMoney = redisTemplate;
        hashOperations = redisTemplate.opsForHash();
    }
    
    
    /* Getters & setters for attributes: */

    /* Getters & setters for transient attributes: */

    /* Functionalities: */
    /**
     * create Config
     * 
     * @param emoney, the ListEMoney to be saved
     * @return response status and ListEMoney data
     */
    @Transactional
    public ListEMoney saveEmoney(ListEMoney emoney) {
	return listEMoneyRepository.save(emoney);
    }
    
    /**
     * findAll ListEMoney data
     * @return List of ListEMoney data
     */
    @Transactional(readOnly = true)
    public List<ListEMoney> findAll() {
	return listEMoneyRepository.findAll();
    }
    
    /**
     * findById, Find ListEMoney by Id
     * 
     * @param id
     * @return, Single data of ListEMoney
     */
    @Transactional(readOnly = true)
    public Optional<ListEMoney> findById(Long id) {
	return listEMoneyRepository.findById(id);
    }
    
    
    /**
     * findByName, Find ListEMoney by Name
     * 
     * @param Name
     * @return, Single data of ListEMoney
     */
    @Transactional(readOnly = true)
    public ListEMoney findByName(String name) {
	return listEMoneyRepository.findEMoneyByName(name);
    }
    
    /**
     * delete ListEMoney by ListEMoney Id
     * @param id of the ListEMoney
     */
    @Transactional
    public void deleteEMoneyById(Long id) {
    	listEMoneyRepository.deleteById(id);
    }
    
    @SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
    public List<ListEMoney> findAllEmoneyWithPic() {
    	List<ListEMoney> result = new ArrayList<>();
    	List<List<Object>> objs = listEMoneyRepository.findAllEMoneywithPic();
    	for(Object o : objs) {    		
    		ObjectMapper mapper = new ObjectMapper();	
    		List<Object> list = mapper.convertValue(o, ArrayList.class);
    		
    		
    		Map<String,Object> map = (Map<String,Object>) list.get(0);
    		Map<String,Object> map1 = (Map<String,Object>) list.get(1);
    		ListEMoney emoney = new ListEMoney();
    		emoney.setId((Long)map.get("id"));
    		emoney.setBottomLimit((BigInteger)map.get("bottomLimit"));
    		emoney.setImage((String)map1.get("picture"));
    		emoney.setName((String)map.get("name"));
    		emoney.setUpperLimit((BigInteger)map.get("upperLimit"));
    		
    		result.add(emoney);
    	}
    	
    	
	return result;
    }
 
    //-- REDIS Function
    /** Cache Repository */
    
    //Getting a specific item by item id from table
    @SuppressWarnings("unchecked")
	public ListEMoney getItem(Long id){
        return (ListEMoney) hashOperations.get(KEY,id);
    }

    //Adding an item into redis database
    @SuppressWarnings("unchecked")
	public void addItem(ListEMoney item){
        hashOperations.put(KEY,item.getId(),item);
    }
    //delete an item from database
    @SuppressWarnings("unchecked")
	public void deleteItem(Long id){
        hashOperations.delete(KEY,id);
    }

    //update an item from database
    public void updateItem(ListEMoney item){
        addItem(item);
    }
    
    public void removeConfigKey(){
    	redisTemplateEMoney.delete(KEY);
    }
    
    //Getting all item from table
	@SuppressWarnings("rawtypes")
	public List<ListEMoney> getAllEMoneyCache(){ 
		log.debug("get all emoney from cache...");		
		Map<Object, Object> map = redisTemplateEMoney.opsForHash().entries(KEY);
		log.debug("size from cache... "+map.size());
		Iterator iter = map.entrySet().iterator();
		List<ListEMoney> list = new ArrayList<>();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            list.add((ListEMoney) entry.getValue());
        }
        return list;
    }
    
	//Store all item from table to cache
	@SuppressWarnings("rawtypes")
	public List<ListEMoney> storeEMoneyToCache(){ 
		log.debug("store all emoney to cache...");
		//set expired time for cache CONFIG
		//redisTemplate.expire(KEY, 1800, TimeUnit.SECONDS); // 24H = 86400 second
		redisTemplateEMoney.hasKey(KEY);
		//redisTemplateEMoney.expire(KEY, 1800, TimeUnit.SECONDS); // 30 minute = 1800 second
		
		//remove old cache
		Map<Object, Object> map = redisTemplateEMoney.opsForHash().entries(KEY);
		log.debug("size... "+map.size());
		Iterator iter = map.entrySet().iterator();
		List<ListEMoney> list = new ArrayList<>();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            list.add((ListEMoney) entry.getValue());
        }
		
        if(null != list) {
        	log.debug("remove old cache... "+list.size());
        	this.removeConfigKey();
        } 
		
		//List<ListEMoney> item = this.findAll();
        List<ListEMoney> item = this.findAllEmoneyWithPic();
        for(ListEMoney con : item) {
        	//log.debug("add item to cache "+con.getName());
        	addItem(con);	
        }
        
        //check new cache
        map = redisTemplateEMoney.opsForHash().entries(KEY);
		log.debug("size... "+map.size());
		iter = map.entrySet().iterator();
		list = new ArrayList<>();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            list.add((ListEMoney) entry.getValue());
        }
        
        //redisTemplateEMoney.expireAt(key, date)
        redisTemplateEMoney.expire(KEY, 86400, TimeUnit.SECONDS); // 30 minute = 1800 second
        log.debug("EMONEY TTL : "+redisTemplateEMoney.getExpire(KEY));
        
        return list;
    }
    
	
    
    
    
}
