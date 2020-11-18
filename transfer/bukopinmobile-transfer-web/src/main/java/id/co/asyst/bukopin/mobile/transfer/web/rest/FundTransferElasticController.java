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
package id.co.asyst.bukopin.mobile.transfer.web.rest;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import id.co.asyst.bukopin.mobile.common.model.BkpmConstants;
import id.co.asyst.bukopin.mobile.transfer.core.service.elastic.FundTransferElasticService;
import id.co.asyst.bukopin.mobile.transfer.model.entity.elastic.FundTransferElastic;

/**
 * Elastic FT/ OB Controller
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Nov 19, 2020
 * @since 1.4.Alpha1
 */
@RestController
@RequestMapping("/elastic")
public class FundTransferElasticController {
    /* Constants: */

    /* Attributes: */
    @Autowired
    private FundTransferElasticService elasticService;

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */

    /* Getters & setters for transient attributes: */

    /* Functionalities: */
    @GetMapping(value= "/storeFT/{type}/{status}")
    @ResponseStatus(HttpStatus.CREATED)
    public void storeCustomerLogin(@PathVariable String type, @PathVariable String status) {
	FundTransferElastic transaction = new FundTransferElastic();
	transaction.setUsername("userdummy");
	transaction.setAdminFee(new BigDecimal(1500));
	transaction.setAmount(new BigDecimal(300000));
	transaction.setDateTime(new Date());
	if("OB".equalsIgnoreCase(type)) {
	    transaction.setType(BkpmConstants.OVERBOOK);
	} else {
	    transaction.setType(BkpmConstants.FUND_TRANSFER);
	}
	if("SUCCESS".equalsIgnoreCase(status)) {
	    transaction.setStatus(BkpmConstants.STATUS_SUCCESS);
	} else {
	    transaction.setStatus(BkpmConstants.STATUS_FAILED);
	}
	
	elasticService.saveTransaction(transaction);
    }

    /* Overrides: */
}
