/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.master.core.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.co.asyst.bukopin.mobile.master.core.repository.TransactionRepository;
import id.co.asyst.bukopin.mobile.master.model.entity.ListEMoney;
import id.co.asyst.bukopin.mobile.master.model.entity.Transaction;

/**
 * Service Implementation for managing <code>Transaction</code>.
 * 
 * @author Abi Nanel
 * @version $Revision$, Dec 11, 2019
 * @since 1.0.Alpha1
 */
@Service
@Transactional
public class TransactionService {
    /* Constants: */

    /* Attributes: */
    @Autowired
    private TransactionRepository transactionRepository;

    /* Transient Attributes: */

    /* Constructors: */
    public TransactionService(TransactionRepository transactionRepository) {
	this.transactionRepository = transactionRepository;
    }

    /* Getters & setters for attributes: */

    /* Getters & setters for transient attributes: */

    /* Functionalities: */
    /**
     * Save Transaction
     * 
     * @param transaction
     * @return already save Transaction
     */
    @Transactional
    public Transaction save(Transaction transaction) {
	return transactionRepository.save(transaction);
    }

    @Transactional
    public Transaction findByReffNumberAndDestinationId(String refNumber, Long id) {
    	return transactionRepository.findByRefandDestinationId(refNumber, id);
    }
    
    /**
     * findAll Transaction data
     * @return List of Transaction data
     */
    @Transactional()
    public List<Transaction> findAll() {
	return transactionRepository.findAll();
    }
    /* Overrides: */
}
