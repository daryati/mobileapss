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

import java.util.Calendar;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.co.asyst.bukopin.mobile.common.core.exception.DataNotFoundException;
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
    
    /* Logger: */
    private final Logger log = LoggerFactory.getLogger(SystemCutOffService.class);
    
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
    
    /**
     * Check Status Cut Off
     * 
     * @param id The Module id of System Cut Off
     * @return true if Cut Off is in Progress, else false.
     */
    public boolean isCutOff(Long id) {
	boolean isCutOff = false;
	
	SystemCutOff cutOff = this.findById(id);
	if(cutOff==null) {
	    log.error("System cut off not found: {}", id);
	    throw new DataNotFoundException();
	} else {
	    // Prepare
	    Calendar now = Calendar.getInstance(); // current time
	    Calendar start = Calendar.getInstance(); // Cut Off start time
	    start.setTime(cutOff.getStartTime());
	    Calendar end = Calendar.getInstance(); // Cut Off end time
	    end.setTime(cutOff.getEndTime());

	    // set start & end date
	    start = copyDate(now, start);
	    end = copyDate(now, end);
	    
//	    log.debug("is start before end (sameday)? "+ start.getTime().before(end.getTime()));
	    
	    // if same time between start and end, cutoff disabled
	    if (start.getTime().equals(end.getTime())) {
		log.debug("Cut Off for {} was disabled",cutOff.getSystemName());
	    } else if (start.getTime().before(end.getTime())) {
//		log.debug("Same Day");
	    } else {
//		log.debug("Different Day");
		// Check is time access before or after day change?
		if (now.getTime().before(end.getTime())) {
//		    log.debug("after day change");
		    // set start date with yesterday
		    start.add(Calendar.DATE, -1);
		} else { // before day change
//		    log.debug("before day change");
		    // set end date with tomorrow
		    end.add(Calendar.DATE, 1);
		}
	    }

	    // Check Cut Off Status
	    if (now.getTime().after(start.getTime()) && now.getTime().before(end.getTime())) {
//		log.debug("Cut Off");
		isCutOff = true;
	    } else {
//		log.debug("No Cut Off");
	    }
	}
	
	return isCutOff;
    }
    
    /**
     * Copy Date
     * 
     * @param from Source of Copy
     * @param to Destination of Copy
     * @return new Destination Calendar
     */
    private static Calendar copyDate(Calendar from, Calendar to) {
	to.set(Calendar.DATE, from.get(Calendar.DATE));
	to.set(Calendar.MONTH, from.get(Calendar.MONTH));
	to.set(Calendar.YEAR, from.get(Calendar.YEAR));
	
	return to;
    }

    /* Overrides: */
}
