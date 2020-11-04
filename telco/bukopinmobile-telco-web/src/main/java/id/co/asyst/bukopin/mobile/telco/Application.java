/*
 * $Id$
 * 
 * Copyright (c) 2020 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.telco;

import java.util.concurrent.Executor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
/*import id.co.asyst.bukopin.mobile.telco.model.entity.Institution;
import id.co.asyst.bukopin.mobile.telco.model.entity.PrefixTelco;*/

/**
 * Payment Main Application.
 * 
 * @author Kartika Dwi H
 * @version $Revision$, Feb 12, 2020
 * @since 1.0.Alpha1-SNAPSHOT
 */
@SpringBootApplication(scanBasePackages = { "id.co.asyst.bukopin.mobile.telco",
	"id.co.asyst.bukopin.mobile.common.core.util",
	"id.co.asyst.bukopin.mobile.common.core.service"})
@EntityScan(basePackages = { "id.co.asyst.bukopin.mobile.telco.model.entity", 
	"id.co.asyst.bukopin.mobile.master.model.entity",
	"id.co.asyst.bukopin.mobile.transfer.model.entity",
	"id.co.asyst.bukopin.mobile.user.model.entity"})
@EnableAsync
public class Application {
	

    /**
     * Telco Async Task Executor
     * 
     * @return The Executor.
     */
    @Bean("telcoAsyncExecutor")
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
    
    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
	return new JedisConnectionFactory();
    }
    
   /* @Bean
    RedisTemplate<String, PrefixTelco> redisPrefixTelcoTemplate() {
	RedisTemplate<String, PrefixTelco> redisTemplate = new RedisTemplate<String, PrefixTelco>();
	redisTemplate.setConnectionFactory(jedisConnectionFactory());
	return redisTemplate;
    }
    
    @Bean
    RedisTemplate<String, Institution> redisInstitutionTemplate() {
	RedisTemplate<String, Institution> redisTemplate = new RedisTemplate<String, Institution>();
	redisTemplate.setConnectionFactory(jedisConnectionFactory());
	return redisTemplate;
    }*/
}
