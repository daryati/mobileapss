/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.common.model;

/**
 * Field Validator Enum 
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Nov 27, 2019
 * @since 1.0.Alpha1-SNAPSHOT
 */
public enum FieldValidatorEnum {
    PATTERN("Pattern"),
    NOT_BLANK("NotBlank"),
    EMAIL("Email"),
    NOT_NULL("NotNull");
    
    private String name;
    
    FieldValidatorEnum(String name) {
	this.name=name;
    }

    public String value() {
        return name;
    }
    
}
