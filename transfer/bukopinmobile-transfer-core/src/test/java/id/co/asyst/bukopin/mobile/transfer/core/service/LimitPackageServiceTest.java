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
package id.co.asyst.bukopin.mobile.transfer.core.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

/**
 * Test Class for Limit Package Service
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Jul 6, 2020
 * @since 1.4.Alpha1
 */
@SpringBootTest
@Import(TransferCoreTestConfiguration.class)
public class LimitPackageServiceTest {
    /* Constants: */

    /* Attributes: */
    @Autowired
    private LimitPackageService limitPackageService;

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */

    /* Getters & setters for transient attributes: */

    /* Functionalities: */
    @Test
    public void testGetDefault() {
	if(limitPackageService==null) {
	    System.out.println("null");
	} else {
	    System.out.println("is not null");
	}
    }

    /* Overrides: */
}
