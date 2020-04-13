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
import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import id.co.asyst.bukopin.mobile.master.core.service.ListEMoneyService;

/**
 * Model for List Emoney Cache
 * 
 * @author Kartika Dwi H
 * @version $Revision$, Jan 30, 2020
 * @since 1.1.Alpha1
 */
@Profile({"devCache","localCache"})
@Component
public class ListEMoneyCache {
	
	private final Logger log = LoggerFactory.getLogger(ListEMoneyCache.class);
	
	@Autowired
    ListEMoneyService emoneyRepo;
	
    
    
    @PostConstruct
    public void init(){
        log.debug("Store all EMoney to cache Component..");      
        
       emoneyRepo.storeEMoneyToCache();
    }
}
