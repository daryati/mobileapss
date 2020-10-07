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

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import id.co.asyst.bukopin.mobile.common.core.util.CryptoUtil;
import id.co.asyst.bukopin.mobile.common.model.BkpmConstants;
import id.co.asyst.bukopin.mobile.common.model.payload.CommonRequest;
import id.co.asyst.bukopin.mobile.common.model.payload.CommonResponse;
import id.co.asyst.bukopin.mobile.common.model.payload.Identity;
import id.co.asyst.bukopin.mobile.service.core.NonFinancialService;
import id.co.asyst.bukopin.mobile.user.model.entity.NonFinancial;
import id.co.asyst.foundation.service.connector.Services;

// import liquibase.integration.spring.SpringLiquibase;

/**
 * AUdit Logging Aspect
 * @author Kartika Dwi H
 * @version $Revision$, (Created on Jun 22, 2020)
 * @since 1.3.Alpha1
 */
@Aspect
@Component
public class MasterAuditLoggingAspect {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    

    /**
     * Pointcut that matches all repositories, services and Web REST endpoints.
     */
    @Pointcut("within(@org.springframework.stereotype.Repository *)" +
        " || within(@org.springframework.stereotype.Service *)" +
        " || within(@org.springframework.web.bind.annotation.RestController *)")
    public void springBeanPointcut() {
        // Method is empty as this is just a Pointcut, the implementations are in the advices.
    }

    /**
     * Pointcut that matches all Spring beans in the application's main packages.
     */
    @Pointcut(
        "within(id.co.asyst.bukopin.mobile.master.web.rest..Exchange*)")
    public void applicationPackagePointcut() {
        // Method is empty as this is just a Pointcut, the implementations are in the advices.
    }

    /**
     * Advice that logs methods throwing exceptions.
     *
     * @param joinPoint join point for advice
     * @param e exception
     */
    @AfterThrowing(pointcut = "applicationPackagePointcut() && springBeanPointcut()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
        log.error("Exception in {}.{}() with cause = {}", joinPoint.getSignature().getDeclaringTypeName(),
            joinPoint.getSignature().getName(), e.getCause() != null ? e.getCause() : "NULL");
    }

    /**
     * Advice that logs when a method is entered and exited.
     *
     * @param joinPoint join point for advice
     * @return result
     * @throws Throwable throws IllegalArgumentException
     */
    
	@Around("applicationPackagePointcut() && springBeanPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
    	Object dataReq = new Object();
        if (log.isDebugEnabled()) {
            log.debug("Master Start AOP: {}.{}() with argument[s] = {}", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
            	
          /*  String method = joinPoint.getSignature().getName();
            if("getAllTransactionHistory".equals(method)) {
            	Object[] results = joinPoint.getArgs();
            	for(int i=0; i<results.length; i++){
         		   dataReq = (Object) results[i];     
         	       
         	   }
            }*/
        }
        
        
        //proceed joincut
        try {
            Object result = joinPoint.proceed();
            if (log.isDebugEnabled()) {
            	log.debug("Function "+joinPoint.getSignature().getName());
            	String method = joinPoint.getSignature().getName();   
            	// get response 
            	CommonResponse res = (CommonResponse) result;  
            	
            	if("getExchangeRate".equals(method)) {
            		
            		if(BkpmConstants.CODE_SOAP_SUCCESS.equalsIgnoreCase(res.getCode())) {
            			// save report SUCCESS to Non_Financial table
            			NonFinancial non = new NonFinancial();
            		   	   non.setMenu("Exchange Rate");
            		   	   non.setNote("Inquiry exchange rate");
            		   	   non.setReason("");
            		   	   non.setStatus(BkpmConstants.STATUS_SUCCESS);
            		   	   non.setCreatedDate(new Date());
            	           
            	           CommonRequest<NonFinancial> nonFinancial = new CommonRequest<>();
            	           nonFinancial.setIdentity(generateIdentity());
            	           nonFinancial.setData(non);
            		       try {
            				Services.create(NonFinancialService.class).saveReportNonFinancial(nonFinancial).execute().body();
            		       } catch (IOException e) {
            				// TODO Auto-generated catch block
            				e.printStackTrace();
            		       }
            	           
            		} else {
            			// save report Failed to Non_Financial table
            			NonFinancial non = new NonFinancial();
            	   	    non.setMenu("Exchange Rate");
            	   	    non.setNote("Inquiry exchange rate");
            	   	    non.setReason("code : "+res.getCode()+" - message: "+res.getMessage());
            	   	    non.setStatus(BkpmConstants.STATUS_FAILED);
            	   	    non.setCreatedDate(new Date());
                       
                        CommonRequest<NonFinancial> nonFinancial = new CommonRequest<>();
                        nonFinancial.setIdentity(generateIdentity());
                        nonFinancial.setData(non);
            	        try {
            		    	Services.create(NonFinancialService.class).saveReportNonFinancial(nonFinancial).execute().body();
            	        } catch (IOException e) {
            			    // TODO Auto-generated catch block
            			    e.printStackTrace();
            	        }
            		}
            		
            	} else if("GetSukuBunga".equals(method)) {
            		
            		
            		if(BkpmConstants.CODE_SOAP_SUCCESS.equalsIgnoreCase(res.getCode())) {
            			// save report SUCCESS to Non_Financial table
            			NonFinancial non = new NonFinancial();
	      			   	   non.setMenu("Interest Rate");
	      			   	   non.setNote("Inquiry interest rate");
	      			   	   non.setReason("");
	      			   	   non.setStatus(BkpmConstants.STATUS_SUCCESS);
	      			   	   non.setCreatedDate(new Date());
	      		           
	      		           CommonRequest<NonFinancial> nonFinancial = new CommonRequest<>();
	      		           nonFinancial.setIdentity(generateIdentity());
	      		           nonFinancial.setData(non);
	      			       try {
	      					Services.create(NonFinancialService.class).saveReportNonFinancial(nonFinancial).execute().body();
	      			       } catch (IOException e) {
	      					// TODO Auto-generated catch block
	      					e.printStackTrace();
	      			       }
            		} else {
            			// save report Failed to Non_Financial table
            			NonFinancial non = new NonFinancial();
    			   	    non.setMenu("Interest Rate");
    			   	    non.setNote("Inquiry interest rate");
    			   	    non.setReason("code : "+res.getCode()+" - message: "+res.getMessage());
    			   	    non.setStatus(BkpmConstants.STATUS_FAILED);
    			   	    non.setCreatedDate(new Date());
    		           
    		            CommonRequest<NonFinancial> nonFinancial = new CommonRequest<>();
    		            nonFinancial.setIdentity(generateIdentity());
    		            nonFinancial.setData(non);
    			        try {
    				    	Services.create(NonFinancialService.class).saveReportNonFinancial(nonFinancial).execute().body();
    			        } catch (IOException e) {
    					    // TODO Auto-generated catch block
    					    e.printStackTrace();
    			        }
            		}
            		
            		
            		
            	} /*else if("GetSukuBunga".equals(method)) {
            		
            		
            		if(BkpmConstants.CODE_SOAP_SUCCESS.equalsIgnoreCase(res.getCode())) {
            			// save report SUCCESS to Non_Financial table
            			NonFinancial non = new NonFinancial();
	      			   	   non.setMenu("Interest Rate");
	      			   	   non.setNote("Inquiry interest rate");
	      			   	   non.setReason("");
	      			   	   non.setStatus(BkpmConstants.STATUS_SUCCESS);
	      			   	   non.setCreatedDate(new Date());
	      		           
	      		           CommonRequest<NonFinancial> nonFinancial = new CommonRequest<>();
	      		           nonFinancial.setIdentity(generateIdentity());
	      		           nonFinancial.setData(non);
	      			       try {
	      					Services.create(NonFinancialService.class).saveReportNonFinancial(nonFinancial).execute().body();
	      			       } catch (IOException e) {
	      					// TODO Auto-generated catch block
	      					e.printStackTrace();
	      			       }
            		} else {
            			// save report Failed to Non_Financial table
            			NonFinancial non = new NonFinancial();
    			   	    non.setMenu("Interest Rate");
    			   	    non.setNote("Inquiry interest rate");
    			   	    non.setReason("code : "+res.getCode()+" - message: "+res.getMessage());
    			   	    non.setStatus(BkpmConstants.STATUS_FAILED);
    			   	    non.setCreatedDate(new Date());
    		           
    		            CommonRequest<NonFinancial> nonFinancial = new CommonRequest<>();
    		            nonFinancial.setIdentity(generateIdentity());
    		            nonFinancial.setData(non);
    			        try {
    				    	Services.create(NonFinancialService.class).saveReportNonFinancial(nonFinancial).execute().body();
    			        } catch (IOException e) {
    					    // TODO Auto-generated catch block
    					    e.printStackTrace();
    			        }
            		}
            		
            		
            		
            	}*/
            	
                log.debug("Master End AOP: {}.{}() with result = {}", joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName(), result);
                
                
            }
            return result;
        } catch (IllegalArgumentException e) {
            log.error("Illegal argument: {} in {}.{}()", Arrays.toString(joinPoint.getArgs()),
                joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
            throw e;
        }
    }
    
    public static Identity generateIdentity() {
		Identity identity = new Identity();
		identity.setReqTime(new Date().toString());
		identity.setPlatform("Android");
		identity.setUserAgent("");
		identity.setToken("");
		identity.setSecretCode("");

		return identity;
	    }
}
