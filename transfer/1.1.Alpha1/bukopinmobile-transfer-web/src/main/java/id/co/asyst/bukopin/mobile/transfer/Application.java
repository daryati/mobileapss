/*

 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.transfer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * Main Application
 * 
 * @author Gibran Haq
 * @version $Revision$, Nov 12, 2019
 * @since 1.0.Alpha1
 */
@SpringBootApplication(scanBasePackages = {"id.co.asyst.bukopin.mobile.transfer",
	"id.co.asyst.bukopin.mobile.common.core.util",
	"id.co.asyst.bukopin.mobile.common.core.service"})
@EntityScan(basePackages = {"id.co.asyst.bukopin.mobile.transfer", "id.co.asyst.bukopin.mobile.user.model"})
public class Application {
	
	/**
     * Main method.
     * 
     * @param args Arguments.
     */
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		System.setProperty("com.sun.xml.internal.ws.transport.http.client.HttpTransportPipe.dump", "true");
	}
	
}
