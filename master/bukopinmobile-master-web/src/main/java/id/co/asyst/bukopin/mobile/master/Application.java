/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.master;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import id.co.asyst.bukopin.mobile.master.model.entity.Configuration;
import id.co.asyst.bukopin.mobile.master.model.entity.Currency;
import id.co.asyst.bukopin.mobile.master.model.entity.Institution;
import id.co.asyst.bukopin.mobile.master.model.entity.ListEMoney;
import id.co.asyst.bukopin.mobile.master.model.entity.ListInsurance;
import id.co.asyst.bukopin.mobile.master.model.entity.PrefixTelco;

/**
 * Main Application.
 * 
 * @author Stephen Dharma
 * @version $Revision$, (Created on Oct 24, 2019)
 * @since 1.0.Alpha1
 */
	@SpringBootApplication(scanBasePackages = { "id.co.asyst.bukopin.mobile.master",
		"id.co.asyst.bukopin.mobile.common.core.util",
		"id.co.asyst.bukopin.mobile.common.core.service"})
	@EnableCaching
	@EntityScan(basePackages = { "id.co.asyst.bukopin.mobile.master.model.entity", 
			"id.co.asyst.bukopin.mobile.user.model.entity" })
	public class Application {
    

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

    @Bean
    RedisTemplate<String, Configuration> redisTemplateConfig() {
	RedisTemplate<String, Configuration> redisTemplate = new RedisTemplate<String, Configuration>();
	redisTemplate.setConnectionFactory(jedisConnectionFactory());
	return redisTemplate;
    }
    
    @Bean
    RedisTemplate<String, ListEMoney> redisTemplateEMoney() {
 	RedisTemplate<String, ListEMoney> redisTemplate = new RedisTemplate<String, ListEMoney>();
 	redisTemplate.setConnectionFactory(jedisConnectionFactory());
 	return redisTemplate;
     }
    
    @Bean
    RedisTemplate<String, Currency> redisTemplateCurrency() {
 	RedisTemplate<String, Currency> redisTemplate = new RedisTemplate<String, Currency>();
 	redisTemplate.setConnectionFactory(jedisConnectionFactory());
 	return redisTemplate;
     }
    
    @Bean
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
    }

    @Bean
    RedisTemplate<String, ListInsurance> redisTemplateInsurance() {
 	RedisTemplate<String, ListInsurance> redisTemplate = new RedisTemplate<String, ListInsurance>();
 	redisTemplate.setConnectionFactory(jedisConnectionFactory());
 	return redisTemplate;
     }
}
