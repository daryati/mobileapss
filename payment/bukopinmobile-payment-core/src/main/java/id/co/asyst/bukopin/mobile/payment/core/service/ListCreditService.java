/*
 * $Id$
 * 
 * Copyright (c) 2020 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.payment.core.service;

import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.co.asyst.bukopin.mobile.payment.core.service.repository.ListCreditRepository;
import id.co.asyst.bukopin.mobile.payment.model.entity.ListCredit;

/**
 * Service for List Credit
 * 
 * @author Ihsan Firman
 * @version $Revision$, Apr 6, 2020
 * @since 2.0
 */

@Service
@Transactional
public class ListCreditService {
    /* Constants: */
    /**
     * Logger
     */
    private final Logger log = LoggerFactory.getLogger(InsuranceTransService.class);
    
    /* Attributes: */
    @Autowired
    ListCreditRepository listCreditRepository;

    /* Transient Attributes: */

    /* Constructors: */
    public ListCreditService(ListCreditRepository listCreditRepository) {
	this.listCreditRepository = listCreditRepository;
    }

    /* Getters & setters for attributes: */

    /* Getters & setters for transient attributes: */

    /* Functionalities: */
    
    /**
     * Save List Credit
     * @param listCredit data to be saved
     * @return list credit data saved
     */
    @Transactional
    public ListCredit saveListCredit(ListCredit listCredit) {
	log.debug("List credit saved {} " + listCredit.getName());
	return listCreditRepository.save(listCredit);
    }
    
    /**
     * Find List Credit By Code CC
     * @param codeCc
     * @return Single data of List Credit
     */
    @Transactional
    public ListCredit findByCodeCc(String codeCc) {
	return listCreditRepository.findByCodeCc(codeCc);
    }
    
    /**
     * Find List Credit By CC Name
     * @param name
     * @return Single data of List Credit
     */
    @Transactional
    public ListCredit findByName(String name) {
	return listCreditRepository.findByName(name);
    } 
    
    @Transactional(readOnly = true)
    public List<ListCredit> findAll(){
	return listCreditRepository.findAll();
    }

    /* Overrides: */}
