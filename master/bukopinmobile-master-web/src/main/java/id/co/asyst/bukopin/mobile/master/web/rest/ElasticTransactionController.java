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
package id.co.asyst.bukopin.mobile.master.web.rest;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.co.asyst.bukopin.mobile.master.core.service.TransactionService;
import id.co.asyst.bukopin.mobile.master.core.service.elastic.TransactionElasticService;
import id.co.asyst.bukopin.mobile.master.model.elastic.TransactionElastic;
import id.co.asyst.bukopin.mobile.master.model.entity.Transaction;


/**
 * Elasticsearch Service for Transaction
 * 
 * @author Kartika Dwi H
 * @version $Revision$, Nov 19, 2020
 * @since 1.4.Alpha1
 */
@RestController
@RequestMapping("/elastic")
public class ElasticTransactionController {
	
	private final Logger log = LoggerFactory.getLogger(ElasticTransactionController.class);
	
	@Autowired
	private TransactionService transactionService;	

	@Autowired
	private TransactionElasticService transactionElasticService;
	
	@GetMapping(value= "/getall")
	public Iterable<TransactionElastic> getAllTransaction() {
		log.debug("get all data transaction from elastic");
		return transactionElasticService.findAllTransaction();
	}
	
	@GetMapping(value= "/storeTransactionData")
	public String storeTransaction() {
		log.debug("Store data transaction to elastic...");
		//ObjectMapper oMapper = new ObjectMapper();
		//get alll insurance data's form db
		List<Transaction> tfs = transactionService.findAll();
		log.debug("size transaction: "+tfs.size());
		List<TransactionElastic> trxElastic = new ArrayList<>();
		for(Transaction tf : tfs) {
			
			
			if( null != tf) {
				if(null != tf.getStatus()) {
					if(!"FAIL".equalsIgnoreCase(tf.getStatus())) {
						TransactionElastic trx = new TransactionElastic();
						//trx.setAdminFee();
						//trx.setAmount(amount);
						trx.setStatus(tf.getStatus());
						trx.setTotalAmount(tf.getTotalAmount());
						trx.setDateTime(tf.getCreatedDate());
						trx.setType(tf.getType());
						trx.setUsername(tf.getUser().getUsername());
						trxElastic.add(trx);
						/*if(tf.getStatus().equalsIgnoreCase(BkpmConstants.STATUS_SUCCESS)) {
							if(tf.getType().equalsIgnoreCase(TransactionTypeEnum.EMONEY.name())) {					
								try {
									CommonResponse getEmoney = Services.create(PurchaseModuleService.class).findEmoneyByTransactionId(tf.getId())
											.execute().body();
									
									if(null!=getEmoney) {
										 oMapper = new ObjectMapper();
										 Map<String, Object> resEmoney = oMapper.convertValue(getEmoney.getData(), Map.class);
										 
										 //log.debug("amount : "+resEmoney.get("amount"));
										 //log.debug("amount fee : "+resEmoney.get("amountFee"));
										 
										 trx.setAdminFee(new BigDecimal((Double) resEmoney.get("amountFee")));
										 trx.setAmount(new BigDecimal((Double) resEmoney.get("amount")));
									}
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
							}
						} */						
					}
					
				}
			}
		}
		// delete old customer login elastic data
		//transactionElasticService.deleteTransaction();
		
		//store new customer login elastic data
		transactionElasticService.saveAllTransaction(trxElastic);
		return "Records saved in the db.... "+trxElastic.size();
	}
	
	
	
	@DeleteMapping(value= "/deleteTransactionIndex")
	public void deleteTransactionIndex() {
		log.debug("DELETE Transaction Elastic Index");
		// delete old customer login elastic data
		transactionElasticService.deleteAllTransaction();
		
	}
	

}
