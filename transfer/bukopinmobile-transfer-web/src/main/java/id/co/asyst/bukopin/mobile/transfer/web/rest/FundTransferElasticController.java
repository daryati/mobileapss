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
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import id.co.asyst.bukopin.mobile.common.model.BkpmConstants;
import id.co.asyst.bukopin.mobile.transfer.core.service.FundTransferService;
import id.co.asyst.bukopin.mobile.transfer.core.service.elastic.FundTransferElasticService;
import id.co.asyst.bukopin.mobile.transfer.model.entity.FundTransfer;
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
	private final Logger log = LoggerFactory.getLogger(FundTransferElasticController.class);
    /* Attributes: */
    @Autowired
    private FundTransferElasticService elasticService;
    
    @Autowired
    private FundTransferService ftService;

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
    
    @GetMapping(value= "/storeTransactionData")
	public String storeTransaction() {
		log.debug("Store transfer transaction to elastic...");
		//ObjectMapper oMapper = new ObjectMapper();
		//get alll insurance data's form db
		List<FundTransfer> tfs = ftService.findAllFundTransfer();
		log.debug("size transaction: "+tfs.size());
		List<FundTransferElastic> trxElastic = new ArrayList<>();
		for(FundTransfer tf : tfs) {
			if( null != tf) {
				if(null != tf.getStatus()) {
					if(!"FAIL".equalsIgnoreCase(tf.getStatus())) {
						FundTransferElastic trx = new FundTransferElastic();
						trx.setAdminFee(tf.getAdminFee());
						trx.setAmount(tf.getAmount());
						trx.setStatus(tf.getStatus());
						trx.setTotalAmount(tf.getAdminFee().add(tf.getAmount()));
						Date dt = Date.from(tf.getCreatedOn().atZone(ZoneId.systemDefault()).toInstant());
						trx.setDateTime(dt);
						trx.setType(tf.getMethod());
						trx.setUsername(tf.getUsername().getUsername());
						trxElastic.add(trx);						
					}
					
				}
			}
		}
		// delete old customer login elastic data
		//transactionElasticService.deleteTransaction();
		
		//store new customer login elastic data
		elasticService.saveAllTransaction(trxElastic);
		return "Records saved in the db.... "+trxElastic.size();
	}

    /* Overrides: */
}
