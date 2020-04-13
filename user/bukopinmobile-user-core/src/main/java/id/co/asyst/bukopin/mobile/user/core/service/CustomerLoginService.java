/*
 * $Id$
 * 
 * Copyright (c) 2020 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.user.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.co.asyst.bukopin.mobile.user.core.repository.CustomerLoginRepository;
import id.co.asyst.bukopin.mobile.user.model.entity.CustomerLogin;

/**
 * Service Implementation for managing <code>CustomerLogin</code>.
 * 
 * @author Abi Nanel
 * @version $Revision$, Mar 4, 2020
 * @since 1.1.Alpha1
 */
@Service
public class CustomerLoginService {
    
    /* Constants: */
    /**
     * Logger.
     */
    private final Logger log = LoggerFactory.getLogger(CustomerLoginService.class);

    /* Attributes: */
    private final CustomerLoginRepository custLoginRepository;

    /* Transient Attributes: */

    /* Constructors: */
    /**
     * Constructor
     * 
     * @param userPINRepository The userPINRepository Bean
     */
    public CustomerLoginService(CustomerLoginRepository repository) {
	this.custLoginRepository = repository;
    }

    /* Getters & setters for attributes: */

    /* Getters & setters for transient attributes: */

    /* Functionalities: */
    /**
     * Save CustomerLogin
     * 
     * @param custLogin
     * @return already save CustomerLogin
     */
    @Transactional
    public CustomerLogin save(CustomerLogin custLogin) {
	log.debug("Save Customer Login with username : {} "+ custLogin.getUsername());
	return custLoginRepository.save(custLogin);
    }
    
    /**
     * Find Top CustomerLogin by username
     * 
     * @param username
     * @return already save CustomerLogin
     */
    @Transactional
    public CustomerLogin findTopUsernameByLoginAtDesc(String username) {
	log.debug("Find Top Customer Login with username : {} "+ username);
	return custLoginRepository.findTopByUsernameOrderByLoginAtDesc(username);
    }

    /* Overrides: */
}
