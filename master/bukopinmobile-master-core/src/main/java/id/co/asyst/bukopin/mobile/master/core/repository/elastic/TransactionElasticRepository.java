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
package id.co.asyst.bukopin.mobile.master.core.repository.elastic;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import id.co.asyst.bukopin.mobile.master.model.elastic.TransactionElastic;

/**
 *  Elasticsearch Repository for Transaction
 * 
 * @author Kartika Dwi H
 * @version $Revision$, Nov 19, 2020
 * @since 1.4.Alpha1
 */
@Repository
public interface TransactionElasticRepository extends ElasticsearchRepository<TransactionElastic, String> {

}
