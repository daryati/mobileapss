/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.purchase;

import java.util.concurrent.Executor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * Main Application.
 * 
 * @author Stephen Dharma
 * @version $Revision$, (Created on Oct 24, 2019)
 * @since 1.0.Alpha1
 */
@SpringBootApplication(scanBasePackages = {"id.co.asyst.bukopin.mobile.purchase",
	"id.co.asyst.bukopin.mobile.common.core.util",
	"id.co.asyst.bukopin.mobile.common.core.service"})
@EntityScan(basePackages = { "id.co.asyst.bukopin.mobile.purchase.model.entity", "id.co.asyst.bukopin.mobile.master.model.entity", 
"id.co.asyst.bukopin.mobile.transfer.model.entity",
	"id.co.asyst.bukopin.mobile.user.model.entity" })
@EnableAsync
public class Application {

    /**
     * Main method.
     * 
     * @param args Arguments.
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
    @Bean("purchaseAsyncExecutor")
    public Executor taskExecutor() {
	ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
	executor.setCorePoolSize(5);
	executor.setMaxPoolSize(5);
	executor.setQueueCapacity(100);
	executor.setThreadNamePrefix("PURCHASE-");
	executor.initialize();
	return executor;
    }
}
