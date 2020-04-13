/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.transfer.core.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.co.asyst.bukopin.mobile.transfer.core.repository.BankRepository;
import id.co.asyst.bukopin.mobile.transfer.model.entity.Bank;



/**
 * Service Implementation for managing Bank
 * 
 * @author Kartika Dwi H
 * @version $Revision$, Nov 25, 2019
 * @since 1.0.Alpha1
 */
@Service
@Transactional
public class BankService {
    /* Constants: */

    /* Attributes: */
    @Autowired
    BankRepository bankRepository;

    /* Transient Attributes: */

    /* Constructors: */
    public BankService(BankRepository bankRepository) {
	this.bankRepository = bankRepository;
    }

    /* Getters & setters for attributes: */

    /* Getters & setters for transient attributes: */

    /* Functionalities: */

    /**
     * Save data to Bank 
     * @param bank to be saved
     * @return Code Success and the bank data
     */
    public Bank saveBank(Bank bank) {
	return bankRepository.save(bank);
    }
    
    /**
     * Get All Bank Data
     * @return List of Bank Data's
     */
    @Transactional(readOnly = true)
    public List<Bank> findAll(){
	return bankRepository.findAll();
    }
    
    /**
     * Find Bank by bankCode
     * @param bankCode
     * @return Single data of Bank
     */
    @Transactional(readOnly = true)
    public Bank findBankByBankCode(String bankCode){
	return bankRepository.findBankByBankCode(bankCode);
	
    }
    
    /**
     * Find Bank by user id
     * @param bankId
     * @return Single data of Bank.
     */
    @Transactional(readOnly = true)
    public Optional<Bank> findBankById(Long id) {
	return bankRepository.findById(id);
    }
    
    /**
     * Delete Bank data by bank id
     * @param the id of Bank
     */
    public void deleteBankById (Long id) {
	 this.bankRepository.deleteById(id);
    }
   
}
