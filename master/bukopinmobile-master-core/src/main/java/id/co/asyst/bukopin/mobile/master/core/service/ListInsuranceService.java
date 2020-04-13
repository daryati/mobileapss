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
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import id.co.asyst.bukopin.mobile.master.core.repository.ListInsuranceRepository;
import id.co.asyst.bukopin.mobile.master.model.entity.ListInsurance;


/**
 * Service for ListInsurance
 * 
 * @author Kartika Dwi H
 * @version $Revision$, Mar 16, 2020
 * @since 1.1.Alpha1
 */
@Service
@Transactional
public class ListInsuranceService {
	
	private final Logger log = LoggerFactory.getLogger(ListInsuranceService.class);
	
    /* Constants: */
	public static final String KEY = "INSURANCE";
	
    /* Attributes: */
    @Autowired
    ListInsuranceRepository listInsuranceRepository;
   
    //@Autowired
    private RedisTemplate<String, ListInsurance> redisTemplateInsurance;
    
    //@Autowired
    private HashOperations hashOperations;

     //Constructors: 
    public ListInsuranceService(RedisTemplate<String, ListInsurance> redisTemplate) {
        this.redisTemplateInsurance = redisTemplate;
        hashOperations = redisTemplate.opsForHash();
    }
    
    
    /* Getters & setters for attributes: */

    /* Getters & setters for transient attributes: */

    /* Functionalities: */
    /**
     * create Config
     * 
     * @param config, the ListInsurance to be saved
     * @return response status and ListInsurance data
     */
    @Transactional
    public ListInsurance saveInsurance(ListInsurance insurance) {
	return listInsuranceRepository.save(insurance);
    }
    
    /**
     * findAll ListInsurance data
     * @return List of ListInsurance data
     */
    @Transactional(readOnly = true)
    public List<ListInsurance> findAll() {
	return listInsuranceRepository.findAll();
    }
    
    /**
     * findById, Find ListInsurance by Id
     * 
     * @param id
     * @return, Single data of ListInsurance
     */
    @Transactional(readOnly = true)
    public Optional<ListInsurance> findById(Long id) {
	return listInsuranceRepository.findById(id);
    }
    
    
    /**
     * findByName, Find ListInsurance by Name
     * 
     * @param Name
     * @return, Single data of ListInsurance
     */
    @Transactional(readOnly = true)
    public ListInsurance findByCode(String code) {
	return listInsuranceRepository.findInsuranceByCode(code);
    }
    
    /**
     * delete ListInsurance by ListInsurance Id
     * @param id of the ListInsurance
     */
    @Transactional
    public void deleteEMoneyById(Long id) {
    	listInsuranceRepository.deleteById(id);
    }
    
    @SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
    public List<ListInsurance> findAllEmoneyWithPic() {
    	List<ListInsurance> result = new ArrayList<>();
    	List<List<Object>> objs = listInsuranceRepository.findAllInsurancewithPic();
    	for(Object o : objs) {    		
    		ObjectMapper mapper = new ObjectMapper();	
    		List<Object> list = mapper.convertValue(o, ArrayList.class);
    		
    		
    		Map<String,Object> map = (Map<String,Object>) list.get(0);
    		Map<String,Object> map1 = (Map<String,Object>) list.get(1);
    		ListInsurance insurance = new ListInsurance();
    		insurance.setId((Long)map.get("id"));
    		insurance.setCodeIns((String)map.get("codeIns"));
    		insurance.setImage((String)map1.get("picture"));
    		insurance.setName((String)map.get("name"));
    		
    		result.add(insurance);
    	}
    	
    	
	return result;
    }
 
    //-- REDIS Function
    /** Cache Repository */
    
    //Getting a specific item by item id from table
    @SuppressWarnings("unchecked")
	public ListInsurance getItem(Long id){
        return (ListInsurance) hashOperations.get(KEY,id);
    }

    //Adding an item into redis database
    @SuppressWarnings("unchecked")
	public void addItem(ListInsurance item){
        hashOperations.put(KEY,item.getId(),item);
    }
    //delete an item from database
    @SuppressWarnings("unchecked")
	public void deleteItem(Long id){
        hashOperations.delete(KEY,id);
    }

    //update an item from database
    public void updateItem(ListInsurance item){
        addItem(item);
    }
    
    public void removeConfigKey(){
    	redisTemplateInsurance.delete(KEY);
    }
    
    //Getting all item from table
	@SuppressWarnings("rawtypes")
	public List<ListInsurance> getAllEMoneyCache(){ 
		log.debug("get all emoney from cache...");		
		Map<Object, Object> map = redisTemplateInsurance.opsForHash().entries(KEY);
		log.debug("size from cache... "+map.size());
		Iterator iter = map.entrySet().iterator();
		List<ListInsurance> list = new ArrayList<>();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            list.add((ListInsurance) entry.getValue());
        }
        return list;
    }
    
	//Store all item from table to cache
	@SuppressWarnings("rawtypes")
	public List<ListInsurance> storeEMoneyToCache(){ 
		log.debug("store all emoney to cache...");
		//set expired time for cache CONFIG
		//redisTemplate.expire(KEY, 1800, TimeUnit.SECONDS); // 24H = 86400 second
		redisTemplateInsurance.hasKey(KEY);
		//redisTemplateEMoney.expire(KEY, 1800, TimeUnit.SECONDS); // 30 minute = 1800 second
		
		//remove old cache
		Map<Object, Object> map = redisTemplateInsurance.opsForHash().entries(KEY);
		log.debug("size... "+map.size());
		Iterator iter = map.entrySet().iterator();
		List<ListInsurance> list = new ArrayList<>();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            list.add((ListInsurance) entry.getValue());
        }
		
        if(null != list) {
        	log.debug("remove old cache... "+list.size());
        	this.removeConfigKey();
        } 
		
		//List<ListEMoney> item = this.findAll();
        List<ListInsurance> item = this.findAllEmoneyWithPic();
        for(ListInsurance con : item) {
        	//log.debug("add item to cache "+con.getName());
        	addItem(con);	
        }
        
        //check new cache
        map = redisTemplateInsurance.opsForHash().entries(KEY);
		log.debug("size... "+map.size());
		iter = map.entrySet().iterator();
		list = new ArrayList<>();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            list.add((ListInsurance) entry.getValue());
        }
        
        //redisTemplateEMoney.expireAt(key, date)
        redisTemplateInsurance.expire(KEY, 86400, TimeUnit.SECONDS); // 30 minute = 1800 second
        log.debug("EMONEY TTL : "+redisTemplateInsurance.getExpire(KEY));
        
        return list;
    }
    
	
    
    
    
}
