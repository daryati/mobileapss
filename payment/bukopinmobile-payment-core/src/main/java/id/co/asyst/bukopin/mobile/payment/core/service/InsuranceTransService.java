/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.payment.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.co.asyst.bukopin.mobile.payment.core.service.repository.InsuranceRepository;
import id.co.asyst.bukopin.mobile.payment.model.entity.Insurance;

/**
 * 
 * 
 * @author Kartika Dwi H
 * @version $Revision$, Jan 20, 2020
 * @since 1.1.Alpha1
 */
@Service
@Transactional
public class InsuranceTransService {
    /**
     * Logger
     */
    private final Logger log = LoggerFactory.getLogger(InsuranceTransService.class);
    
    /* Constants: */
    /**
     * PLN Prepaid Repository
     */
    @Autowired
    private InsuranceRepository insuranceRepository;

    /* Attributes: */

    /* Transient Attributes: */

    /* Constructors: */
    /**
     * @param userRepository PLNPrepaid Repository
     */
    public InsuranceTransService(InsuranceRepository insuranceRepository) {
	this.insuranceRepository = insuranceRepository;
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
    public Insurance save(Insurance insurance) {
	log.debug("Save Insurance {}"+insurance.getParticipant());
	return insuranceRepository.save(insurance);
    }

    /* Overrides: */
}
