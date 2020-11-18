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
package id.co.asyst.bukopin.mobile.transfer.core.service.elastic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import id.co.asyst.bukopin.mobile.transfer.core.repository.elastic.FundTransferElasticRepository;
import id.co.asyst.bukopin.mobile.transfer.model.entity.elastic.FundTransferElastic;

/**
 * Elasticsearch Service for Overbook & Fund Transfer
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Nov 19, 2020
 * @since 1.4.Alpha1
 */
@Service
public class FundTransferElasticService {
    /**
     * Logger
     */
    private final Logger log = LoggerFactory.getLogger(FundTransferElasticService.class);
    
    /* Constants: */

    /* Attributes: */
    /**
     * Elasticsearch Login Repository
     */
    @Autowired
    private FundTransferElasticRepository elasticRepository;

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
    @Async("transferAsyncExecutor")
    public void saveTransaction(FundTransferElastic transaction) {
	elasticRepository.save(transaction);
    }

    /* Overrides: */
 }
