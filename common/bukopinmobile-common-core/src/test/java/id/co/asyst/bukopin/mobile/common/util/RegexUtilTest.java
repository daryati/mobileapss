/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.common.util;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import id.co.asyst.bukopin.mobile.common.core.util.RegexUtil;

/**
 * Regex Util Test Class
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Nov 18, 2019
 * @since 1.0.Alpha1-SNAPSHOT
 */
@SpringBootTest
public class RegexUtilTest {
    /* Constants: */
    private static final String VALID_EMAIL = "eka.ariyansyah@asyst.co.id";
    private static final String VALID_PHONE_NUMBER = "081122334455";

    /* Attributes: */

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */

    /* Getters & setters for transient attributes: */

    /* Functionalities: */
    @Test
    public void testValidateEmail() {
	boolean isValid = RegexUtil.validateEmail(VALID_EMAIL);
	assertTrue(isValid);
    }
    
    @Test
    public void testValidatePhoneNumber() {
	boolean isValid = RegexUtil.validatePhoneNumber(VALID_PHONE_NUMBER);
	assertTrue(isValid);
    }

    /* Overrides: */
}
