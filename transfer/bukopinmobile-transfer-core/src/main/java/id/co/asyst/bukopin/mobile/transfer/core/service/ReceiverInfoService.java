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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.co.asyst.bukopin.mobile.transfer.core.repository.ReceiverInfoRepository;
import id.co.asyst.bukopin.mobile.transfer.model.entity.ReceiverInfo;
import id.co.asyst.bukopin.mobile.user.model.entity.User;


/**
 * 
 * 
 * @author Ihsan Firman
 * @version $Revision$, Nov 14, 2019
 * @since 2.0
 */
@Service("receiverInfoService")
@Transactional
public class ReceiverInfoService {
    /* Constants: */

    /* Attributes: */
    @Autowired
    ReceiverInfoRepository favoriteDestinationRepository;

    /* Transient Attributes: */

    /* Constructors: */
    public ReceiverInfoService(ReceiverInfoRepository favoriteDestinationRepository) {
	this.favoriteDestinationRepository = favoriteDestinationRepository;
    }

    /* Getters & setters for attributes: */

    /* Getters & setters for transient attributes: */

    /* Functionalities: */

    public ReceiverInfo saveFavorite(ReceiverInfo fav) {
	return favoriteDestinationRepository.save(fav);
    }
    
    @Transactional(readOnly = true)
    public List<ReceiverInfo> findAllByUsername(String userId){
	boolean isSave = true;
	return favoriteDestinationRepository.findAllByusername(userId, isSave);
    }
  
    @Transactional(readOnly = true)
    public List<ReceiverInfo> findByBukopinAccount(long username, int rows){
	String bankCode = "441";
	boolean isSave = true;
	int firstPage = 0;
	return favoriteDestinationRepository.findBukopinReceiver(username, bankCode, isSave, PageRequest.of(firstPage, rows));
	
    }
    
    @Transactional(readOnly = true)
    public List<ReceiverInfo> findNotBukopinAccount(long username, int rows){
	String bankCode = "441";
	boolean isSave = true;
	int firstPage = 0;
	return favoriteDestinationRepository.findNotBukopinReceiver(username, bankCode, isSave, PageRequest.of(firstPage, rows));
    }
    
    @Transactional(readOnly = true)
    public Optional<ReceiverInfo> findFavById(Long id) {
	return favoriteDestinationRepository.findById(id);
    }
    
    public void deleteFavById (Long id) {
	 this.favoriteDestinationRepository.deleteById(id);
    }
    
//    @Transactional(readOnly = true)
//    public ReceiverInfo findAccountByUsernameAndAccountNumber(User idUser, Integer accountNumber) {
//	return favoriteDestinationRepository.findFavByUsernameAndAccountNum(idUser, accountNumber);
//    }
    
    @Transactional(readOnly = true)
    public ReceiverInfo findAccountByUsernameAndAccountNumber(User info, String accountNumber){
	Long idUser = info.getId();
	//Integer accountNumber = info.getAccountNumber();
	return favoriteDestinationRepository.findFavByUserIdAndAccountNum(info, accountNumber);
	
    }

    /**
     * Find Most Frequent Transaction
     * 
     * @param username The username to find the transactions
     * @param rows Limit result
     * @return
     */
    @Transactional(readOnly = true)
    public List<ReceiverInfo> findMostFrequent(String username, int rows) {
	boolean isSave = true;
	int firstPage = 0;
	return favoriteDestinationRepository.findMostFrequent(username, isSave, PageRequest.of(firstPage, rows));
    }

    /* Overrides: */}
