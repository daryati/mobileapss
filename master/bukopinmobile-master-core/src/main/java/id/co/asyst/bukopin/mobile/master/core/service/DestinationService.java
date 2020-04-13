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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.co.asyst.bukopin.mobile.master.core.repository.DestinationRepository;
import id.co.asyst.bukopin.mobile.master.model.entity.Destination;
import id.co.asyst.bukopin.mobile.user.model.entity.User;

/**
 * Service for Favorite Destination Purchase Receiver
 * 
 * @author Ihsan Firman
 * @version $Revision$, Dec 7, 2019
 * @since 2.0
 */
@Service
@Transactional
public class DestinationService {
    /* Constants: */

    /* Attributes: */
    @Autowired
    DestinationRepository deRepository;

    /* Transient Attributes: */

    /* Constructors: */
    public DestinationService(DestinationRepository deRepository) {
	this.deRepository = deRepository;
    }
    
    /* Getters & setters for attributes: */

    /* Getters & setters for transient attributes: */

    /* Functionalities: */
    /**
     * Save data to Destination 
     * @param destination to be saved
     * @return Code Success and the Destination data
     */
    @Transactional
    public Destination save(Destination dest) {
	return deRepository.save(dest);
    }
    
    /**
     * Get All Favorite Destination Data
     * @return List of Favorite Destination Data's
     */
    @Transactional(readOnly = true)
    public List<Destination> findAll(){
	return deRepository.findAll();
    }
  
    /**
     * Find Destination by Destination id
     * @param id
     * @return Single Data of Destination
     */
    @Transactional(readOnly = true)
    public Optional<Destination> findById(Long id) {
	return deRepository.findById(id);
    }
    
    /**
     * Find Destination by user id and Category id
     * @param userId
     * @param categoryId
     * @return List Data of Destination
     */
    @Transactional(readOnly = true)
    public List<Destination> findByUserIdAndCategory(Long userId, long categoryId){
	boolean isFavorite = true;
	return deRepository.findAllDestinationByUserIdAndCategory(userId, isFavorite, categoryId);
    }
    
    /**
     * Find Destination by user id
     * @param userId
     * @return List Data of Destination
     */
    @Transactional(readOnly = true)
    public List<Destination> findByUserId(Long userId){
  	boolean isFavorite = true;
  	return deRepository.findAllDestinationByUserId(userId, isFavorite);
      }

    /**
     * Find Destination by Subscriber Number and User id
     * @param userId
     * @param number
     * @return Single data of Destination
     */
    @Transactional(readOnly = true)
    public Destination findBySubNumAndUserId(long userId, String number){
	return deRepository.findAllDestinationByUserIdAndSubNum(userId, number);
    }

    /**
     * Delete Destination data by Destination id
     * @param the id of Destination
     */
    public void deleteById(Long id) {
	this.deRepository.deleteById(id);
    }
    
    /**
     * Find Destination by user id and Category id
     * @param username
     * @param categoryId
     * @return List data of Destination
     */
    public List<Destination> getFavoriteByUserAndCategory(String username, Long categoryId) {
//	List<Destination> favs = new ArrayList<Destination>();
	return deRepository.getFavoriteByUserAndCategory(username, categoryId);
    }
    
    /**
     * Find User's Destination by Type
     * 
     * @param userId The User's Id
     * @param subscriberNumber The Subscriber Number
     * @param destinationType The Destination Type
     * @return Persisted Destination
     */
    @Transactional(readOnly = true)
    public Destination findUserDestinationType(long userId, String subsNumber, String destType){
	return deRepository.findUserDestinationType(userId, subsNumber, destType);
    }
    
/*    @Transactional
    public List<Destination> getMostFavoriteByUsernameAndCategory(String username, Long categoryId){
	return deRepository.getMostFavoriteByUserAndCategory(username, categoryId);
    }*/
    /* Overrides: */
}
