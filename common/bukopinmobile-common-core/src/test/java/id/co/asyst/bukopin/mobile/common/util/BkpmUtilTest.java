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

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.security.NoSuchAlgorithmException;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import id.co.asyst.bukopin.mobile.common.core.util.BkpmUtil;

/**
 * 
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Nov 15, 2019
 * @since 1.0.Alpha1-SNAPSHOT
 */
@SpringBootTest
public class BkpmUtilTest {
    
    /**
     * Logger 
     */
    Logger log = LoggerFactory.getLogger(BkpmUtilTest.class);
    
    /* Constants: */
    private static final String FIELD_NAME = "phoneNumber";
    private static final String FIELD_NAME_RESULT = "Phone Number";
    

    /* Attributes: */

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */

    /* Getters & setters for transient attributes: */

    /* Functionalities: */
    
    @Test
    public void testConvertReadableField() {
	System.out.println(FIELD_NAME);
	String str = BkpmUtil.convertReadableField(FIELD_NAME);
	System.out.println(str);
	assertTrue(FIELD_NAME_RESULT.equals(str));
    }

    /* Overrides: */
}
