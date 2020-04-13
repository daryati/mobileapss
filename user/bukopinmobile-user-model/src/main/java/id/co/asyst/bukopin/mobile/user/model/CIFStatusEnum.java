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
 * Account Card Status
 * 
 * @author Kartika Dwi H
 * @version $Revision$, Nov 19, 2019
 * @since 1.0.Alpha1-SNAPSHOT
 */
public enum CIFStatusEnum {
    ACTIVE(1), PASSIVE(2), CLOSED(9);

    private final int value;

    CIFStatusEnum(final int newValue) {
	this.value = newValue;
    }

    public int getValue() {
	return value;
    }

    public static CIFStatusEnum getEnum(int val) {
	return Arrays.stream(values())
		.filter(status -> status.value == val)
		.findFirst().orElse(null);
    }
}
