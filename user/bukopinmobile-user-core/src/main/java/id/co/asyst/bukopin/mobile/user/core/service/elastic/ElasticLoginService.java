/*
 * $Id$
 * 
 * Copyright (c) 2020 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
/*
 * Copyright (c) 2020 Lufthansa Systems Indonesia, PT. All rights reserved.
 */
package id.co.asyst.bukopin.mobile.user.core.service.elastic;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import id.co.asyst.bukopin.mobile.user.core.repository.elastic.ElasticLoginRepository;
import id.co.asyst.bukopin.mobile.user.model.entity.elastic.CustomerLoginElastic;

/**
 * Elasticsearch Service for Login Data
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Nov 18, 2020
 * @since 1.4.Alpha1
 */
@Service
public class ElasticLoginService {
    
    /**
     * Logger
     */
    private final Logger log = LoggerFactory.getLogger(ElasticLoginService.class);
    
    /* Constants: */

    /* Attributes: */
    /**
     * Elasticsearch Login Repository
     */
    @Autowired
    private ElasticLoginRepository elasticLoginRepository;

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */

    /* Getters & setters for transient attributes: */

    /* Functionalities: */
    /**
     * Save Customer Login Data
     * 
     * @param custLogins The Customer Login data to Save
     */
    @Async("userAsyncExecutor")
    public void saveCustomerLogin(List<CustomerLoginElastic> custLogins) {
	elasticLoginRepository.saveAll(custLogins);
    }
    
    /**
     * Save Customer Login Data
     * 
     * @param custLogins The Customer Login data to Save
     */
    @Async("userAsyncExecutor")
    public void saveCustomerLogin(CustomerLoginElastic custLogins) {
	elasticLoginRepository.save(custLogins);
    }

    /**
     * Find All Customer Login Data
     * 
     * @return Iterable of All Customer Login Data
     */
    public Iterable<CustomerLoginElastic> findAllCustomerLogin() {
	return elasticLoginRepository.findAll();
    }

    /* Overrides: */
}
