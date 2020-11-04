/*
 * $Id$
 * 
 * Copyright (c) 2020 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.payment;

import java.util.concurrent.Executor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * Payment Main Application.
 * 
 * @author Gibran Haq
 * @version $Revision$, Jan 15, 2020
 * @since 1.0.Alpha1-SNAPSHOT
 */
@SpringBootApplication(scanBasePackages = { "id.co.asyst.bukopin.mobile.payment",
	"id.co.asyst.bukopin.mobile.common.core.util",
	"id.co.asyst.bukopin.mobile.common.core.service"})
@EntityScan(basePackages = { "id.co.asyst.bukopin.mobile.payment.model.entity", 
	"id.co.asyst.bukopin.mobile.master.model.entity",
	"id.co.asyst.bukopin.mobile.transfer.model.entity",
	"id.co.asyst.bukopin.mobile.user.model.entity"})
@EnableAsync
public class Application {

    /**
     * Payment Async Task Executor
     * 
     * @return The Executor.
     */
    @Bean("paymentAsyncExecutor")
    public Executor taskExecutor() {
	ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
	executor.setCorePoolSize(5); // number of concurrent
	executor.setMaxPoolSize(5);  // limit of queue
	executor.setQueueCapacity(20);
	executor.setThreadNamePrefix("PAYMENT-");
	executor.initialize();
	return executor;
    }
    
    /**
     * Main method.
     * 
     * @param args
     *            Arguments.
     */
    public static void main(String[] args) {
	SpringApplication.run(Application.class, args);
    }
    
}
