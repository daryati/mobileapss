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

import id.co.asyst.bukopin.mobile.purchase.core.repository.EMoneyRepository;
import id.co.asyst.bukopin.mobile.purchase.core.repository.PLNPrepaidRepository;
import id.co.asyst.bukopin.mobile.purchase.model.entity.EMoney;
import id.co.asyst.bukopin.mobile.purchase.model.entity.PLNPrepaid;

/**
 * 
 * 
 * @author Kartika Dwi H
 * @version $Revision$, Jan 20, 2020
 * @since 1.1.Alpha1
 */
@Service
@Transactional
public class EMoneyTransService {
    /**
     * Logger
     */
    private final Logger log = LoggerFactory.getLogger(EMoneyTransService.class);
    
    /* Constants: */
    /**
     * PLN Prepaid Repository
     */
    @Autowired
    private EMoneyRepository emoneyRepository;

    /* Attributes: */

    /* Transient Attributes: */

    /* Constructors: */
    /**
     * @param userRepository PLNPrepaid Repository
     */
    public EMoneyTransService(EMoneyRepository emoneyRepository) {
	this.emoneyRepository = emoneyRepository;
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
    public EMoney save(EMoney emoney) {
	log.debug("Save EMoney {}"+emoney.getTypeEMoney());
	return emoneyRepository.save(emoney);
    }

    /* Overrides: */
}
