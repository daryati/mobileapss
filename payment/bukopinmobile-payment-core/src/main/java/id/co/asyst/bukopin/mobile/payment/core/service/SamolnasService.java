/*
 * $Id$
 * 
 * Copyright (c) 2020 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.payment.core.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.co.asyst.bukopin.mobile.payment.core.service.repository.SamolnasRepository;
import id.co.asyst.bukopin.mobile.payment.model.entity.Samolnas;

/**
 * 
 * 
 * @author Gibran Haq
 * @version $Revision$, Apr 29, 2020
 * @since 1.2.Alpha1
 */
@Service
@Transactional
public class SamolnasService {
    /* Constants: */

    /* Attributes: */
    @Autowired
    SamolnasRepository samolnasRepository;

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */

    /* Getters & setters for transient attributes: */

    /* Functionalities: */
    /**
     * saveSamolnas
     * @param samolnas
     * @return
     */
    @Transactional
    public Samolnas saveSamolnas(Samolnas samolnas) {
	return samolnasRepository.save(samolnas);
    }
    
    /**
     * findAll
     * @return
     */
    @Transactional(readOnly = true)
    public List<Samolnas> findAll(){
	return samolnasRepository.findAll();
    }
    
    /**
     * findById
     * @param id
     * @return
     */
    @Transactional(readOnly = true)
    public Optional<Samolnas> findById(Long id){
	return samolnasRepository.findById(id);
    }

    /* Overrides: */}
