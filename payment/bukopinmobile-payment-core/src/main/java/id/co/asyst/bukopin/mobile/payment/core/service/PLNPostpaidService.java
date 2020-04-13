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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.co.asyst.bukopin.mobile.payment.core.service.repository.PLNPostpaidRepository;
import id.co.asyst.bukopin.mobile.payment.model.entity.PLNPostpaid;


/**
 * PLN Postpaid Service Implementation
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Jan 24, 2020
 * @since 1.0.Alpha1-SNAPSHOT
 */
@Service
public class PLNPostpaidService {
    /**
     * Logger.
     */
    private final Logger log = LoggerFactory.getLogger(PLNPostpaidService.class);
    
    /* Constants: */

    /* Attributes: */
    @Autowired
    private PLNPostpaidRepository postpaidRepository;

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */

    /* Getters & setters for transient attributes: */

    /* Functionalities: */
    /**
     * Save PLN Postpaid
     * 
     * @param postpaid The Postpaid data to save.
     * @return Persist Postpaid data.
     */
    public PLNPostpaid savePLNPostpaid(PLNPostpaid postpaid) {
	return postpaidRepository.save(postpaid);
    }

    /* Overrides: */
}
