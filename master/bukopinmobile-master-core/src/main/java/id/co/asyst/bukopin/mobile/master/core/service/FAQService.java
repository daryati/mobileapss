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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.co.asyst.bukopin.mobile.master.core.repository.FAQRepository;
import id.co.asyst.bukopin.mobile.master.model.entity.FAQ;

/**
 * Service for Frequently Asked Question
 * 
 * @author Ihsan Firman
 * @version $Revision$, Nov 20, 2019
 * @since 2.0
 */
@Service
@Transactional
public class FAQService {
    /* Constants: */

    /* Attributes: */
    @Autowired
    FAQRepository faqRepository;
    
    /* Transient Attributes: */

    /* Constructors: */
    public FAQService(FAQRepository faqRepository) {
	this.faqRepository = faqRepository;
    }


    /* Getters & setters for attributes: */

    /* Getters & setters for transient attributes: */

    /* Functionalities: */
    /**
     * create Frequently Asked Question
     * 
     * @param faq, the FAQ to be saved
     * @return response status and FAQ's data
     */
    @Transactional
    public FAQ saveFAQ(FAQ faq) {
	return faqRepository.save(faq);
    }
    
    /**
     * findAll Frequently Asked Question data
     * @return List of FAQ's data
     */
    @Transactional(readOnly = true)
    public List<FAQ> findAll() {
	return faqRepository.findAll();
    }
    
    /**
     * findById, Find FAQ by Id
     * 
     * @param id
     * @return, Single data of FAQ
     */
    @Transactional(readOnly = true)
    public Optional<FAQ> findById(Long id) {
	return faqRepository.findById(id);
    }
    
    /**
     * delete FAQ by FAQ's Id
     * @param id of the FAQ
     */
    @Transactional
    public void deleteFAQById(Long id) {
	this.faqRepository.deleteById(id);
    }

    /* Overrides: */}
