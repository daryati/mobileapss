/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */

package id.co.asyst.bukopin.mobile.master.core.cache;

import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import id.co.asyst.bukopin.mobile.master.core.service.ConfigurationService;
import id.co.asyst.bukopin.mobile.master.model.entity.Configuration;

/**
 * Model for Configuration Cache
 * 
 * @author Kartika Dwi H
 * @version $Revision$, Dec 04, 2019
 * @since 2.0
 */
@Profile({"localCache","devCache"})
@Component
public class ConfigurationCache {
	
	private final Logger log = LoggerFactory.getLogger(ConfigurationCache.class);
			
	@Autowired
    ConfigurationService configRepo;
    @PostConstruct
    public void init(){
        log.debug("Store all config to cache Component..");
        
        
        List<Configuration> olditem = configRepo.getAllConfigCache();
        if(null != olditem) {
        	log.debug("remove old cache... "+olditem.size());
        	configRepo.removeConfigKey();
        } 
        
        List<Configuration> item = configRepo.findAll();
        for(Configuration con : item) {
        	//log.debug("add item to cache");
        	configRepo.addItem(con);	
        }
    }
}
