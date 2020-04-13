/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.master.core.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

// import liquibase.integration.spring.SpringLiquibase;

/**
 * Persistence Configuration beans.
 * 
 * @author Stephen Dharma
 * @version $Revision$, (Created on Oct 24, 2019)
 * @since 1.0.Alpha1
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
	basePackages = {"id.co.asyst.bukopin.mobile.master.core.repository",
		"id.co.asyst.bukopin.mobile.user.core.repository",
		"id.co.asyst.bukopin.mobile.purchase.core.repository"})
public class PersistenceConfiguration {

    /* Constants: */

    /* Attributes: */
    @Autowired
    private Environment env;

    /* Constructors: */
    /**
     * Default Constructor.
     */
    public PersistenceConfiguration() {
        super();
    }

    /**
     * Constructor with environment parameter.
     * 
     * @param env Environment.
     */
    public PersistenceConfiguration(Environment env) {
        super();
        this.env = env;
    }

    /* Getters & setters for attributes: */

    /* Configurations: */
    /**
     * Data Source Configuration.
     * 
     * @return Data Source.
     */
    @Bean
    @Primary
    public DataSource dataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
        dataSource.setUrl(env.getProperty("spring.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.password"));
        return dataSource;
    }

//    /**
//     * Liquibase Configuration.
//     * 
//     * @return Spring Liquibase.
//     */
//    // @Bean
//    public SpringLiquibase liquibase() {
//        final SpringLiquibase liquibase = new SpringLiquibase();
//        liquibase.setChangeLog("classpath:config/liquibase/initial.xml");
//        liquibase.setDataSource(dataSource());
//        return liquibase;
//    }
}
