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

import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * Test Configuration Class
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Jul 6, 2020
 * @since 1.4.Alpha1
 */
@TestConfiguration
public class TransferCoreTestConfiguration {
    /* Constants: */

    /* Attributes: */
    @Bean
    public TransferCoreTestConfiguration transferCoreTestConfiguration() {
	// TODO Auto-generated constructor stub
	return new TransferCoreTestConfiguration();
    }

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */

    /* Getters & setters for transient attributes: */

    /* Functionalities: */
    @Bean
    public LimitPackageService limitPackageService() {
	return Mockito.mock(LimitPackageService.class);
    }

    /* Overrides: */
}
