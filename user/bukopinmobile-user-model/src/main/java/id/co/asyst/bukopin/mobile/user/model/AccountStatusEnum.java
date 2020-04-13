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
 * Account Info Status
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Nov 6, 2019
 * @since 1.0.Alpha1-SNAPSHOT
 */
public enum AccountStatusEnum {
    NOT_AUTHORIZED(0), ACTIVE(1), PASSIVE(2), CLOSED(9);
    
    private final int value;
    
    AccountStatusEnum(final int newValue) {
	this.value = newValue;
    }
    
    public int getValue() {
	return value;
    }
    
    public static AccountStatusEnum getEnum(int val) {
	return Arrays.stream(values())
		.filter(status -> status.value == val)
		.findFirst().orElse(null);
    }
}
