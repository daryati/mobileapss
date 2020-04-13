/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.master.web.rest.util;

import id.co.asyst.bukopin.mobile.master.model.entity.Destination;

/**
 * Destination Util
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Dec 11, 2019
 * @since 1.0.Alpha1-SNAPSHOT
 */
public class DestinationUtil {
    /* Constants: */

    /* Attributes: */

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */

    /* Getters & setters for transient attributes: */

    /* Functionalities: */
    /**
     * Generate Add Favorite Service Response
     * 
     * @param destination The destination object to generate as response.
     * @return add favorite response.
     */
    public static Destination generateAddFavResponse(Destination destination) {
	Destination response = new Destination();
	response.setSubscriberName(destination.getSubscriberName());
	response.setSubscriberNumber(destination.getSubscriberNumber());
	response.setCategory(destination.getCategory());
	response.setDestinationType(destination.getDestinationType());
	
	return response;
    }
    
    public static Destination generateGetFavResponse(Destination dest) {
	Destination response = new Destination();
	response.setId(dest.getId());
	response.setSubscriberName(dest.getSubscriberName());
	response.setSubscriberNumber(dest.getSubscriberNumber());
	response.setAlias(dest.getAlias());
	response.setDestinationType(dest.getDestinationType());
	
	return response;
    }
    
    

    /* Overrides: */
}
