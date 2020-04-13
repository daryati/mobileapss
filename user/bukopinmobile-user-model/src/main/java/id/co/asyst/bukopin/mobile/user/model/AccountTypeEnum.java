/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.user.model;

import java.util.Arrays;

/**
 * Account Type
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Nov 6, 2019
 * @since 1.0.Alpha1-SNAPSHOT
 */
public enum AccountTypeEnum {
    SAVING(10), GIRO(20);
    
    private final int value;
    
    AccountTypeEnum(final int newValue) {
	this.value = newValue;
    }
    
    public int getValue() {
	return value;
    }
    
    public static AccountTypeEnum getEnum(int val) {
	return Arrays.stream(values())
		.filter(Status -> Status.value == val)
		.findFirst().orElse(null);
    }
}
