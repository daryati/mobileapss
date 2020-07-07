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
package id.co.asyst.bukopin.mobile.transfer.core.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.co.asyst.bukopin.mobile.transfer.core.repository.LimitPackageRepository;
import id.co.asyst.bukopin.mobile.transfer.model.entity.LimitPackage;

/**
 * Service Implemenntation for Limit Package
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Jul 6, 2020
 * @since 1.4.Alpha1
 */
@Service
@Transactional
public class LimitPackageService {
    /* Constants: */

    /* Attributes: */
    @Autowired
    private LimitPackageRepository limitReposirtory;

    /* Transient Attributes: */

    /* Constructors: */

    /* Functionalities: */
    /**
     * Get Default of Limit Package
     * 
     * @return null if no default exist, else get first indes of result.
     */
    public LimitPackage getDefault() {
	String defaultFlag = "Y";
	List<LimitPackage> limits = limitReposirtory.findByIsDefault(defaultFlag);
	if(limits==null || limits.isEmpty()) {
	    return null;
	} else {
	    int firstIndex = 0;
	    return limits.get(firstIndex);
	}
    }

    /* Overrides: */
}
