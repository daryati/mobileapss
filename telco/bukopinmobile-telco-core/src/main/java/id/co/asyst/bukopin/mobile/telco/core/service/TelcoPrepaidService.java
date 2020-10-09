/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.telco.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.co.asyst.bukopin.mobile.telco.core.repository.TelcoDataRepository;
import id.co.asyst.bukopin.mobile.telco.core.repository.TelcoPrepaidRepository;
import id.co.asyst.bukopin.mobile.telco.model.entity.TelcoData;
import id.co.asyst.bukopin.mobile.telco.model.entity.TelcoPrepaid;

/**
 * service for TelcoPrepaid
 * 
 * @author Kartika Dwi H
 * @version $Revision$, Feb 19, 2020
 * @since 1.0.Alpha1
 */
@Service
@Transactional
public class TelcoPrepaidService {
    /**
     * Logger
     */
    private final Logger log = LoggerFactory.getLogger(TelcoPrepaidService.class);
    
    /* Constants: */
    /**
     * telco Prepaid Repository
     */
    @Autowired
    private TelcoPrepaidRepository telcoPrepaidRepository;
    
    /**
     * telco Data Repository
     */
    @Autowired
    private TelcoDataRepository telcoDataRepository;
    
    /* Attributes: */

    /* Transient Attributes: */

    /* Constructors: */
    /**
     * @param userRepository TelcoPrepaid Repository
     */
    public TelcoPrepaidService(TelcoPrepaidRepository telcoPrepaidRepository) {
	this.telcoPrepaidRepository = telcoPrepaidRepository;
    }

    /* Getters & setters for attributes: */

    /* Getters & setters for transient attributes: */

    /* Functionalities: */
    /**
     * Save TelcoPrepaid
     * 
     * @param plnPrepaid
     * @return plnPrepaid
     */
    @Transactional
    public TelcoPrepaid saveTelcoPrepaid(TelcoPrepaid telcoPrepaid) {
	log.debug("Save Telco Prepaid {}"+telcoPrepaid.getTypeTelco());
	return telcoPrepaidRepository.save(telcoPrepaid);
    }
    
    @Transactional
    public TelcoData saveTelcoData(TelcoData telcoData) {
	log.debug("Save Telco Data {}"+telcoData.getTypeData());
	return telcoDataRepository.save(telcoData);
    }

    /* Overrides: */
}
