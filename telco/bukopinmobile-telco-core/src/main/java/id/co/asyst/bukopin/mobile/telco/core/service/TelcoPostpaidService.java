/*
 * $Id$
 * 
 * Copyright (c) 2020 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.telco.core.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.co.asyst.bukopin.mobile.telco.core.repository.TelcoPostpaidRepository;
import id.co.asyst.bukopin.mobile.telco.model.entity.TelcoPostpaid;

/**
 * 
 * 
 * @author Gibran Haq
 * @version $Revision$, Feb 21, 2020
 * @since 1.1.Alpha1-SNAPSHOT
 */
@Service
@Transactional
public class TelcoPostpaidService {
    /* Constants: */
    private final Logger log = LoggerFactory.getLogger(TelcoPostpaidService.class);

    /* Attributes: */
    @Autowired
    TelcoPostpaidRepository telcoPostpaidRepository;

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */

    /* Getters & setters for transient attributes: */

    /* Functionalities: */
    /**
     * save Telco Postpaid
     * @param telcoPostpaid
     * @return
     */
    @Transactional
    public TelcoPostpaid saveTelcoPostpaid(TelcoPostpaid telcoPostpaid) {
	return telcoPostpaidRepository.save(telcoPostpaid);
    }
    
    /**
     * findAll
     * @return
     */
    @Transactional(readOnly = true)
    public List<TelcoPostpaid> findAll(){
	return telcoPostpaidRepository.findAll();
    }
    
    /**
     * findById
     * @param id
     * @return
     */
    @Transactional(readOnly = true)
    public Optional<TelcoPostpaid> findById(Long id){
	return telcoPostpaidRepository.findById(id);
    }

    /* Overrides: */
    
}
