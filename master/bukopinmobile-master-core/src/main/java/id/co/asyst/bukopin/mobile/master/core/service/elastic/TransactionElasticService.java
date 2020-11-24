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
package id.co.asyst.bukopin.mobile.master.core.service.elastic;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import id.co.asyst.bukopin.mobile.master.core.repository.elastic.TransactionElasticRepository;
import id.co.asyst.bukopin.mobile.master.model.elastic.TransactionElastic;


/**
 * Elasticsearch Service for Transaction
 * 
 * @author Kartika Dwi H
 * @version $Revision$, Nov 19, 2020
 * @since 1.4.Alpha1
 */
@Service
public class TransactionElasticService {
    /**
     * Logger
     */
    private final Logger log = LoggerFactory.getLogger(TransactionElasticService.class);
    
    /* Constants: */

    /* Attributes: */
    /**
     * Elasticsearch Login Repository
     */
    @Autowired
    private TransactionElasticRepository elasticRepository;

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */

    /* Getters & setters for transient attributes: */

    /* Functionalities: */
    /**
     * Save Fund Transfer/ Overbook Transaction
     * 
     * @param transaction The Transaction to Save
     */
    @Async("masterAsyncExecutor")
    public void saveTransaction(TransactionElastic transaction) {
	elasticRepository.save(transaction);
    }
    
    @Async("masterAsyncExecutor")
    public void saveAllTransaction(List<TransactionElastic> transaction) {
	elasticRepository.saveAll(transaction);
    }
    
    @Async("masterAsyncExecutor")
    public void deleteTransaction(TransactionElastic transaction) {
	elasticRepository.delete(transaction);
    }
    
    @Async("masterAsyncExecutor")
    public void deleteAllTransaction() {
	elasticRepository.deleteAll();
    }
    
    
    public Iterable<TransactionElastic> findAllTransaction() {
	return elasticRepository.findAll();
    }

    /* Overrides: */
 }
