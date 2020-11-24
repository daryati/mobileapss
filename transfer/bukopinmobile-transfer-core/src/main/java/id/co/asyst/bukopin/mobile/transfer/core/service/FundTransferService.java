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

import id.co.asyst.bukopin.mobile.transfer.core.repository.FundTransferRepository;
import id.co.asyst.bukopin.mobile.transfer.model.entity.FundTransfer;


/**
 * 
 * 
 * @author Kartika Dwi Handini
 * @version $Revision$, Nov 25, 2019
 * @since 2.0
 */
@Service("fundTransferService")
@Transactional
public class FundTransferService {
    /* Constants: */

    /* Attributes: */
    @Autowired
    FundTransferRepository fundTransferRepository;

    /* Transient Attributes: */

    /* Constructors: */
    public FundTransferService(FundTransferRepository fundTransferRepository) {
	this.fundTransferRepository = fundTransferRepository;
    }

    /* Getters & setters for attributes: */

    /* Getters & setters for transient attributes: */

    /* Functionalities: */

    /**
     * Save data to FundTransfer 
     * @param fundTransfer to be saved
     * @return Code Success and the fundTransfer data
     */
    public FundTransfer saveFundTransfer(FundTransfer req) {
	return fundTransferRepository.save(req);
    }
    
    /**
     * Find FundTransfer by username
     * @param username
     * @return Single data of FundTransfer
     */
    @Transactional(readOnly = true)
    public FundTransfer findFundTransferByUsername(String username){
	return fundTransferRepository.findFundTransferByUsername(username);
    }
    
    /**
     * Find FundTransfer by accountNumber
     * @param accountNumber
     * @return Single data of FundTransfer
     */
    @Transactional(readOnly = true)
    public FundTransfer findFundTransferByAccountNumber(Integer accNum){
	return fundTransferRepository.findFundTransferByAccountNumber(accNum);
    }
    
  /*  
    @Transactional(readOnly = true)
    public List<ReceiverInfo> findByName(ReceiverInfo favorite){
	String username = favorite.getAccountName();
	String name = favorite.getName();
	return favoriteDestinationRepository.findFavoriteByname(username, name);
	
    }*/
    
    /**
     * Find FundTransfer by id
     * @param fundTransfer id
     * @return Single data of FundTransfer
     */
    @Transactional(readOnly = true)
    public Optional<FundTransfer> findFundTransferById(Long id) {
	return fundTransferRepository.findById(id);
    }
    
    /**
     * Delete FundTransfer data by fundTransfer id
     * @param the id of FundTransfer
     */
    public void deletefundTransferRepositoryById (Long id) {
	 this.fundTransferRepository.deleteById(id);
    }
    
    public List<FundTransfer> findAllFundTransfer() {
    	return fundTransferRepository.findAll();
        }

}
