/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.user.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Pre Execute Controller Configuration
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Nov 28, 2019
 * @since 1.0.Alpha1-SNAPSHOT
 */
@Configuration
public class PreExecuteConfiguration implements WebMvcConfigurer {
    /* Constants: */

    /* Attributes: */
    @Autowired
    private ControllerInterceptor controllerInterceptor;

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */

    /* Getters & setters for transient attributes: */

    /* Functionalities: */

    /* Overrides: */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
	registry.addInterceptor(controllerInterceptor)
//		.addPathPatterns("/**")
		.excludePathPatterns(
			"/v2/api-docs",
			"/swagger-resources/**",
			"/swagger-ui.html",
			"/webjars/**",
			"/error",
			"/**/sendOTPEmail",
			"/**/SMSToken/**",
			"/**/OTP/verify",
			"/**/registerUser",
			"/**/login",
			"/**/logout",
			"/**/forgotPassword",
			"/**/crypto/**",
			"/**/validateSession/**",
			"/**/verifyPhoneOwner",
			"/**/verifyTokenOwner",
			"/**/unlockUser",
			"/**/public/**");
    }
}
