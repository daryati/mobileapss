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
package id.co.asyst.bukopin.mobile.user.core.repository.elastic;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import id.co.asyst.bukopin.mobile.user.model.entity.elastic.CustomerLoginElastic;

/**
 * Elasticsearch Repository for Login Data
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Nov 18, 2020
 * @since 1.4.Alpha1
 */
@Repository
public interface ElasticLoginRepository extends ElasticsearchRepository<CustomerLoginElastic, String> {
    
}
