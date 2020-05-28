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
package id.co.asyst.bukopin.mobile.master.core.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.co.asyst.bukopin.mobile.master.core.repository.SystemCutOffRepository;
import id.co.asyst.bukopin.mobile.master.model.entity.cms.SystemCutOff;

/**
 * System Cut Off Service
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, May 28, 2020
 * @since 1.4.Alpha1
 */
@Service
@Transactional
public class SystemCutOffService {
    /* Constants: */
    
    /* Beans: */
    @Autowired
    private SystemCutOffRepository systemCutOffRepository;

    /* Attributes: */

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */

    /* Getters & setters for transient attributes: */

    /* Functionalities: */
    /**
     * Find <code>SystemCutOff</code> By Id
     * @param id The id to find.
     * @return Persisted <code>SystemCutOff</code> if exist, else will return null.
     */
    public SystemCutOff findById(Long id) {
	return systemCutOffRepository.findById(id).orElse(null);
    }

    /* Overrides: */
}
