/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.purchase.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.co.asyst.bukopin.mobile.purchase.core.repository.PLNPrepaidRepository;
import id.co.asyst.bukopin.mobile.purchase.model.entity.PLNPrepaid;

/**
 * Service Implementation for managing <code>PLNPrepaid</code>.
 * 
 * @author Abi Nanel
 * @version $Revision$, Dec 11, 2019
 * @since 1.0.Alpha1
 */
@Service
@Transactional
public class PLNPrepaidService {
    /**
     * Logger
     */
    private final Logger log = LoggerFactory.getLogger(PLNPrepaidService.class);
    
    /* Constants: */
    /**
     * PLN Prepaid Repository
     */
    @Autowired
    private PLNPrepaidRepository plnPrepaidRepository;

    /* Attributes: */

    /* Transient Attributes: */

    /* Constructors: */
    /**
     * @param userRepository PLNPrepaid Repository
     */
    public PLNPrepaidService(PLNPrepaidRepository plnPrepaidRepository) {
	this.plnPrepaidRepository = plnPrepaidRepository;
    }

    /* Getters & setters for attributes: */

    /* Getters & setters for transient attributes: */

    /* Functionalities: */
    /**
     * Save PLNPrepaid
     * 
     * @param plnPrepaid
     * @return plnPrepaid
     */
    @Transactional
    public PLNPrepaid save(PLNPrepaid plnPrepaid) {
	log.debug("Save PLN PREPAID {}");
	return plnPrepaidRepository.save(plnPrepaid);
    }

    /* Overrides: */
}
