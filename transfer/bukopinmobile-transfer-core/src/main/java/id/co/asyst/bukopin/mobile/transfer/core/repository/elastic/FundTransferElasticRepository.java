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
package id.co.asyst.bukopin.mobile.transfer.core.repository.elastic;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import id.co.asyst.bukopin.mobile.transfer.model.entity.elastic.FundTransferElastic;

/**
 *  Elasticsearch Repository for Overbook & Fund Transfer
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Nov 19, 2020
 * @since 1.4.Alpha1
 */
@Repository
public interface FundTransferElasticRepository extends ElasticsearchRepository<FundTransferElastic, String> {

}
