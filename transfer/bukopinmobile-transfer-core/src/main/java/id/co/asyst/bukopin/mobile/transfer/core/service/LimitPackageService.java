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

import java.math.BigDecimal;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.co.asyst.bukopin.mobile.transfer.core.repository.LimitPackageRepository;
import id.co.asyst.bukopin.mobile.transfer.model.entity.LimitPackage;
import id.co.asyst.bukopin.mobile.user.model.AccountTypeEnum;

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
	boolean defaultFlag = true;
	List<LimitPackage> limits = limitReposirtory.findByIsDefault(defaultFlag);
	if(limits==null || limits.isEmpty()) {
	    return null;
	} else {
	    int firstIndex = 0;
	    return limits.get(firstIndex);
	}
    }
    
    public boolean checkLimit(Long limitId, int accountType, BigDecimal transferValue) {
	boolean valid = false;
	
	LimitPackage limit = limitReposirtory.findById(limitId).orElse(null);
	if(limit!=null) { // if limit exist
	    if(AccountTypeEnum.SAVING.getValue()==accountType) {
		// saving
		if(transferValue.compareTo(limit.getLimitSaving())!=1) {
		    // transfer not exceed limit
		    valid = true;
		}
	    } else if(AccountTypeEnum.GIRO.getValue()==accountType) {
		// giro
		if(transferValue.compareTo(limit.getLimitGiro())!=1) {
		    // transfer not exceed limit
		    valid = true;
		}
	    }
	}
	
	return valid;
    }

    /* Overrides: */
}
