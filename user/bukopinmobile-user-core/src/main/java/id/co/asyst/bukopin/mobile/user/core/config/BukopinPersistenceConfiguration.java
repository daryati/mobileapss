/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.user.core.config;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Bukopin Persistence Configuration beans.
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Nov 5, 2019
 * @since 1.0.Alpha1-SNAPSHOT
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "id.co.asyst.bukopin.mobile.user.core.dao",
	entityManagerFactoryRef = "bukopinEntityManagerFactory", transactionManagerRef = "bukopinTransactionManager")
public class BukopinPersistenceConfiguration {
    /* Constants: */

    /* Attributes: */
    @Autowired
    private Environment env;

    /* Constructors: */
    /**
     * Default Constructor.
     */
    public BukopinPersistenceConfiguration() {
	super();
    }
    
    /**
     * Default Constructor.
     */
    public BukopinPersistenceConfiguration(Environment env) {
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
    @Bean(name="bukopinDataSource")
    public DataSource bukopinDataSource() {
	final DriverManagerDataSource dataSource = new DriverManagerDataSource();
	dataSource.setDriverClassName(env.getProperty("bukopin.datasource.driver-class-name"));
	dataSource.setUrl(env.getProperty("bukopin.datasource.url"));
	dataSource.setUsername(env.getProperty("bukopin.datasource.username"));
	dataSource.setPassword(env.getProperty("bukopin.datasource.password"));
	return dataSource;
    }

    @Bean(name = "bukopinEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean bukopinEntityManager() {
        LocalContainerEntityManagerFactoryBean em
          = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(bukopinDataSource());
        em.setPackagesToScan(
          new String[] { "id.co.asyst.bukopin.mobile.user.model.entity.internal" });
 
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<>();
//        properties.put("hibernate.hbm2ddl.auto",
//        	env.getProperty("bukopin.hibernate.ddl-auto"));
        properties.put("hibernate.dialect", 
        	env.getProperty("bukopin.hibernate.dialect"));
        em.setJpaPropertyMap(properties);
 
        return em;
    }

    @Bean(name="bukopinTransactionManager")
    public PlatformTransactionManager bukopinTransactionManager() {
	JpaTransactionManager transactionManager = new JpaTransactionManager();
	transactionManager.setEntityManagerFactory(bukopinEntityManager().getObject());
	return transactionManager;
    }

    /* Functionalities: */

    /* Overrides: */
}
