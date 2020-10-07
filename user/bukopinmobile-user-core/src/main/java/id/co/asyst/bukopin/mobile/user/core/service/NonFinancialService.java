/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.user.core.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.co.asyst.bukopin.mobile.common.core.util.CryptoUtil;
import id.co.asyst.bukopin.mobile.user.core.repository.NonFinancialRepository;
import id.co.asyst.bukopin.mobile.user.model.entity.NonFinancial;
import id.co.asyst.bukopin.mobile.user.model.entity.User;

/**
 * Service Implementation for managing <code>NonFinancial</code>.
 * 
 * @author Kartika Dwi H
 * @version $Revision$, Jun 16, 2020
 * @since 1.3.Alpha1
 */
@Service
public class NonFinancialService {
    /**
     * Logger.
     */
    private final Logger log = LoggerFactory.getLogger(NonFinancialService.class);
    
    /**
     * Non Financial Repository
     */
    private final NonFinancialRepository nonFinancialRepository;
    
    @Autowired
    private UserService userService;
    
    /**
     * Constructor
     * 
     * @param userPINRepository The userPINRepository Bean
     */
    public NonFinancialService(NonFinancialRepository repository) {
	this.nonFinancialRepository = repository;
    }
    
    /* Functionalities: */
    // TODO: is each User only has 1 accountCard?
    /**
     * Find Non Financial by Username
     * 
     * @param username user's username
     * @return List of Nonfinancial by username
     */
    @Transactional(readOnly=true)
    public List<NonFinancial> findByUsername(String username) {
	log.debug("Find User by username : {} " + username);
	User user = userService.findUserByUsername(username);
	if(user==null) {
	    return null;
	} else {
	    return nonFinancialRepository.findByUserId(user.getId());
	}
    }
    
    /**
     * create nonFinancial
     * 
     * @param nonFinancial, the nonFinancial to be saved
     * @return response status and nonFinancial's data
     */
    @Transactional
    public NonFinancial saveNonFinancial(NonFinancial nonFinancial) {
	return nonFinancialRepository.save(nonFinancial);
    }
    
    /**
     * findAll nonFinancial data
     * @return List of nonFinancial's data
     */
    @Transactional(readOnly = true)
    public List<NonFinancial> findAll() {
	return nonFinancialRepository.findAll();
    }
    
    /**
     * findById, Find nonFinancial by Id
     * 
     * @param id
     * @return, Single data of nonFinancial
     */
    @Transactional(readOnly = true)
    public Optional<NonFinancial> findById(Long id) {
	return nonFinancialRepository.findById(id);
    }
 
    /* Overrides: */
}
