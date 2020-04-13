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
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.co.asyst.bukopin.mobile.master.core.repository.PurchaseCategoryRepository;
import id.co.asyst.bukopin.mobile.master.model.entity.PurchaseCategory;

/**
 * Category Service
 * 
 * @author Ihsan Firman
 * @version $Revision$, Dec 7, 2019
 * @since 2.0
 */
@Service
@Transactional
public class PurchaseCategoryService {
    /* Constants: */

    /* Attributes: */
    @Autowired
    PurchaseCategoryRepository pRepository;

    /* Transient Attributes: */

    /* Constructors: */
    public PurchaseCategoryService(PurchaseCategoryRepository pRepository) {
	this.pRepository = pRepository;
    }

    /* Getters & setters for attributes: */

    /* Getters & setters for transient attributes: */

    /* Functionalities: */
    /**
     * Save Category
     * @param category
     * @return Success code and Destination data
     */
    public PurchaseCategory saveCategory(PurchaseCategory category) {
	return pRepository.save(category);
    }
    
    /**
     * Find all data in Category
     * @return List Data in table Category
     */ 
    public List<PurchaseCategory> findAll() {
	return pRepository.findAll();
    }
    
    /**
     * Find Category by category Id
     * @param id
     * @return single data of category
     */
    public Optional<PurchaseCategory> findById(Long id){
	return pRepository.findById(id);
    }
    
    /**
     * delete category by category id
     * @param id of category
     */
    public void deleteById(Long id) {
	this.pRepository.deleteById(id);
    }

    /* Overrides: */}
