/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.user;

import java.util.concurrent.Executor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
@SpringBootApplication(scanBasePackages = {"id.co.asyst.bukopin.mobile.user",
	"id.co.asyst.bukopin.mobile.common.core.util",
	"id.co.asyst.bukopin.mobile.common.core.service"})
@EnableAsync
public class Application {
    
    /**
     * Payment Async Task Executor
     * 
     * @return The Executor.
     */
    @Bean("userAsyncExecutor")
    public Executor taskExecutor() {
	ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
	executor.setCorePoolSize(5); // number of concurrent
	executor.setMaxPoolSize(5);  // limit of queue
	executor.setQueueCapacity(20);
	executor.setThreadNamePrefix("USER-");
	executor.initialize();
	return executor;
    }

    /**
     * Main method.
     * 
     * @param args Arguments.
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
