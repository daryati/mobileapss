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

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import id.co.asyst.bukopin.mobile.master.core.service.CurrencyService;
import id.co.asyst.bukopin.mobile.master.core.service.ListEMoneyService;
import id.co.asyst.bukopin.mobile.master.model.entity.Configuration;
import id.co.asyst.bukopin.mobile.master.model.entity.Currency;
import id.co.asyst.bukopin.mobile.master.model.entity.ListEMoney;

/**
 * Model for Currency Cache
 * 
 * @author Kartika Dwi H
 * @version $Revision$, Feb 07, 2020
 * @since 1.1.Alpha1
 */
@Profile({"devCache","localCache"})
@Component
public class CurrencyCache {
	
	private final Logger log = LoggerFactory.getLogger(CurrencyCache.class);
	
	@Autowired
    CurrencyService currencyRepo;
    
    @PostConstruct
    public void init(){
        log.debug("Store all Currency to cache Component..");            
        currencyRepo.storeCurrencyToCache();
    }
}
