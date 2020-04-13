/*
 * $Id$
 * 
 * Copyright (c) 2020 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.payment.core.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 
 * 
 * @author Gibran Haq
 * @version $Revision$, Jan 16, 2020
 * @since 1.0.Alpha1-SNAPSHOT
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
    /* Constants: */
    private static final Set<String> DEFAULT_PRODUCES = 
	      new HashSet<String>(Arrays.asList("application/json"));
    private static final Set<String> DEFAULT_CONSUMES = 
	      new HashSet<String>(Arrays.asList("application/json"));

    /* Attributes: */

    /* Configurations: */
    /**
     * Swagger
     * Main bean for Swagger configuration.
     * 
     * @return the Docket bean.
     */
    @Bean
    public Docket swagger() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("id.co.asyst.bukopin.mobile.payment.web.rest"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .consumes(DEFAULT_CONSUMES)
                .produces(DEFAULT_PRODUCES);
    }
    
    /**
     * apiInfo
     * API detail information.
     * 
     * @return the API info entity.
     */
    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Bukopin Mobile API Documentation",
                "Bukopin Mobile API for 3rd Party Systems.",
                "1.0.Alpha1",
                "Terms of service",
                new Contact("Aero Systems Indonesia", "https://www.asyst.co.id", "helpdesk@asyst.co.id"),
                "License of API", "https://www.bukopin.co.id", Collections.emptyList()
                );
    }

    /* Overrides: */
    
}
