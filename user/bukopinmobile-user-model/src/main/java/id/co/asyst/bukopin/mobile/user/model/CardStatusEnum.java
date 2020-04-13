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
 * @author Eka Ariyansyah
 * @version $Revision$, Nov 6, 2019
 * @since 1.0.Alpha1-SNAPSHOT
 */
public enum CardStatusEnum {
    UNACTIVATED(0), ACTIVATED(1);

    private final int value;

    CardStatusEnum(final int newValue) {
	this.value = newValue;
    }

    public int getValue() {
	return value;
    }

    public static CardStatusEnum getEnum(int val) {
	return Arrays.stream(values())
		.filter(status -> status.value == val)
		.findFirst().orElse(null);
    }
}
