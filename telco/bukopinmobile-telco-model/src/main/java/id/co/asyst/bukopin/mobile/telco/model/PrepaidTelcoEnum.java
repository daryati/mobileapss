/*
 * $Id$
 * 
 * Copyright (c) 2020 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.telco.model;

/**
 * Catogory Enum
 * Id should be equals with id in table Category.
 * 
 * @author Kartika Dwi H
 * @version $Revision$, Mar 26, 2020
 * @since 1.0.Alpha1-SNAPSHOT
 */
public enum PrepaidTelcoEnum {
    PREPAID("PREPAID"),
    PAKET_DATA("PAKET DATA");
    
    private String name;
    
    private PrepaidTelcoEnum(String name) {
	this.name = name;
    }
    
    public String getName() {
	return name;
    }
}
