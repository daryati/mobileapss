/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.user.core.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.co.asyst.bukopin.mobile.user.core.repository.ProductRepository;
import id.co.asyst.bukopin.mobile.user.model.entity.Product;

/**
 * Service Implementation for managing <code>AccountCard</code>.
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Nov 6, 2019
 * @since 1.0.Alpha1-SNAPSHOT
 */
@Service
@Transactional
public class ProductService {
    /**
     * Logger.
     */
    private final Logger log = LoggerFactory.getLogger(ProductService.class);
    
    /**
     * User PIN Repository
     */
    private final ProductRepository productRepository;
    
    /**
     * Constructor
     * 
     * @param userPINRepository The userPINRepository Bean
     */
    public ProductService(ProductRepository repository) {
	this.productRepository = repository;
    }
    
    /* Functionalities: */
    public Product findByPdId(int pdId) {
	return productRepository.findByPdId(pdId);
    }
    
    /**
     * Find All Products
     * @return List all of persisted product.
     */
    public List<Product> findAll() {
	return productRepository.findAll();
    }

    /* Overrides: */
}
