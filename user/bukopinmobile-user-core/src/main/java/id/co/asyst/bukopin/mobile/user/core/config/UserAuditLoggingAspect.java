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
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import id.co.asyst.bukopin.mobile.common.model.BkpmConstants;
import id.co.asyst.bukopin.mobile.common.model.ResponseMessage;
import id.co.asyst.bukopin.mobile.common.model.payload.CommonRequest;
import id.co.asyst.bukopin.mobile.common.model.payload.CommonResponse;
import id.co.asyst.bukopin.mobile.service.model.payload.UpdatePasswordCentagateRequest;
import id.co.asyst.bukopin.mobile.user.core.service.AccountInfoUserService;
import id.co.asyst.bukopin.mobile.user.core.service.NonFinancialService;
import id.co.asyst.bukopin.mobile.user.core.service.UserService;
import id.co.asyst.bukopin.mobile.user.model.AccountBalanceByAccNoReq;
import id.co.asyst.bukopin.mobile.user.model.AccountBalanceQRReq;
import id.co.asyst.bukopin.mobile.user.model.AccountBalanceReq;
import id.co.asyst.bukopin.mobile.user.model.InquiryTransactionReq;
import id.co.asyst.bukopin.mobile.user.model.centagate.LoginResultData;
import id.co.asyst.bukopin.mobile.user.model.entity.AccountInfo;
import id.co.asyst.bukopin.mobile.user.model.entity.NonFinancial;
import id.co.asyst.bukopin.mobile.user.model.entity.User;
import id.co.asyst.bukopin.mobile.user.model.payload.InquiryNotificationRequest;
import id.co.asyst.bukopin.mobile.user.model.payload.UpdatePinRequest;

// import liquibase.integration.spring.SpringLiquibase;

/**
 * Persistence Configuration beans.
 * 
 * @author Kartika Dwi Handini
 * @version $Revision$, (Created on Jun, 2020)
 * @since 1.0.Alpha1
 */
@Aspect
@Component
public class UserAuditLoggingAspect {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private NonFinancialService nonFinancialService;
    
    @Autowired
    private UserService userService;
    
    /**
     * Account Info Service
     */
    @Autowired
    private AccountInfoUserService accInfoUserService;

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
        "within(id.co.asyst.bukopin.mobile.user.web.rest..Non*)"+
    	" || within(id.co.asyst.bukopin.mobile.user.web.rest..Auth*)"+
    	" || within(id.co.asyst.bukopin.mobile.user.web.rest..AccountInfo*)")
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
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@Around("applicationPackagePointcut() && springBeanPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
    	Object dataReq = new Object();
        if (log.isDebugEnabled()) {
            log.debug("User Start AOP: {}.{}() with argument[s] = {}", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
            
            
            	if("forgotPassword".equalsIgnoreCase(joinPoint.getSignature().getName())||
            			"updatePassword".equalsIgnoreCase(joinPoint.getSignature().getName())||
            			"updateUserPin".equalsIgnoreCase(joinPoint.getSignature().getName())||
            			"getInquiryTransaction".equalsIgnoreCase(joinPoint.getSignature().getName())||
            			"getAccountBalance".equalsIgnoreCase(joinPoint.getSignature().getName())||
            			"getAccountBalanceQR".equalsIgnoreCase(joinPoint.getSignature().getName())||
            			"getAccountBalanceByAccNo".equalsIgnoreCase(joinPoint.getSignature().getName())||
            			"inquiryNotification".equalsIgnoreCase(joinPoint.getSignature().getName())) {
            		Object[] results = joinPoint.getArgs();
             	   for(int i=0; i<results.length; i++){
             		   CommonRequest res = (CommonRequest) results[i];          	       
             	       dataReq = res.getData() ;
             	   }
             	   //log.debug("METODENYAAAA "+joinPoint.getSignature().getName());
            	}
            	
        }
        
        
        //proceed joincut
        try {
            Object result = joinPoint.proceed();
            if (log.isDebugEnabled()) {
            	log.debug("Function "+joinPoint.getSignature().getName());
            	String method = joinPoint.getSignature().getName();   
            	// get response 
            	CommonResponse res = (CommonResponse) result;  
            	
            	if("forgotPassword".equals(method)) {
                	//mapping request
            		String username = ((Map<String,String>)dataReq).get("username");
            		User user = new User();
            		if(null!=username &&! username.isEmpty()) {
            			user = userService.findUserByUsername(username);   
            		}
            		
            		
            		if(BkpmConstants.CODE_SOAP_SUCCESS.equalsIgnoreCase(res.getCode())) {
            			// save report SUCCESS to Non_Financial table
                   	    NonFinancial non = new NonFinancial();
                   	    non.setMenu("Forgot Password");
                   	    non.setNote("Request forgot password");
                   	    non.setReason("");
                   	    non.setStatus(BkpmConstants.STATUS_SUCCESS);
                   	    non.setUser(user);
                   	    non.setCreatedDate(new Date());
                   	    nonFinancialService.saveNonFinancial(non);
            		} else {
            			// save report Failed to Non_Financial table
            			NonFinancial non = new NonFinancial();
            		    non.setMenu("Forgot Password");
            		    non.setNote("Request forgot password");
            		    non.setReason("code: "+res.getCode()+" - message: "+ res.getMessage()+" ("+username +")");
            		    non.setStatus(BkpmConstants.STATUS_FAILED);
            		    non.setUser(user);
            		    non.setCreatedDate(new Date());
            		    nonFinancialService.saveNonFinancial(non);
            		}
            		
            	} else if("updatePassword".equals(method)) {
            		//mapping request
            		String username = ((UpdatePasswordCentagateRequest)dataReq).getUsername();
            		User user = new User();
            		if(null!=username &&! username.isEmpty()) {
            			user = userService.findUserByUsername(username);   
            		}
            		
            		if(BkpmConstants.CODE_SOAP_SUCCESS.equalsIgnoreCase(res.getCode())) {
            			// save report SUCCESS to Non_Financial table
            		    NonFinancial non = new NonFinancial();
            		    non.setMenu("Update Password");
            		    non.setNote("Update User Password");
            		    non.setReason("");
            		    non.setStatus(BkpmConstants.STATUS_SUCCESS);
            		    non.setCreatedDate(new Date());
            		    non.setUser(user);
            		    nonFinancialService.saveNonFinancial(non);
            		} else {
            			// save report Failed to Non_Financial table
            			NonFinancial non = new NonFinancial();
        			    non.setMenu("Update Password");
        			    non.setNote("Update User Password");
        			    non.setReason("code: "+res.getCode()+" - message: "+ res.getMessage()+" ("+username +")");
            		    non.setStatus(BkpmConstants.STATUS_FAILED);
        			    non.setCreatedDate(new Date());
        			    non.setUser(user);
        			    nonFinancialService.saveNonFinancial(non);
            		}
            		
            	} else if("updateUserPin".equals(method)) {
            		//mapping request
            		String username = ((UpdatePinRequest)dataReq).getUsername();
            		User user = new User();
            		if(null!=username &&! username.isEmpty()) {
            			user = userService.findUserByUsername(username);   
            		}
            		
            		if(BkpmConstants.CODE_SOAP_SUCCESS.equalsIgnoreCase(res.getCode())) {
            			// save report SUCCESS to Non_Financial table
            		    NonFinancial non = new NonFinancial();
            		    non.setMenu("Update Pin");
            		    non.setNote("Update User Pin");
            		    non.setReason("");
            		    non.setStatus(BkpmConstants.STATUS_SUCCESS);
            		    non.setCreatedDate(new Date());
            		    non.setUser(user);
            		    nonFinancialService.saveNonFinancial(non);
            		} else {
            			// save report Failed to Non_Financial table
            		    NonFinancial non = new NonFinancial();
            		    non.setMenu("Update Pin");
            		    non.setNote("Update User Pin");
            		    non.setReason("code: "+res.getCode()+" - message: "+ res.getMessage()+" ("+username +")");
            		    non.setStatus(BkpmConstants.STATUS_FAILED);
            		    non.setCreatedDate(new Date());
            		    non.setUser(user);
            		    nonFinancialService.saveNonFinancial(non);
            		}
            		
            	} else if("getInquiryTransaction".equals(method)) {
            		//mapping request
            		String accountNumber = ((InquiryTransactionReq)dataReq).getAccountNumber();
            		
            		//get user
            		AccountInfo accInfo = accInfoUserService.findByAccountNo(accountNumber);
            		NonFinancial non = new NonFinancial();
            		if(null!=accInfo) {
            			if(null != accInfo.getAccountCard().getUser()) {
            				non.setUser(accInfo.getAccountCard().getUser());
            			}
            		}
            		
            		if(BkpmConstants.CODE_SOAP_SUCCESS.equalsIgnoreCase(res.getCode())) {
            			// save report SUCCESS to Non_Financial table
                	    non.setMenu("Transaction Inquiry");
            		    non.setNote("Inquiry account statement");
                	    non.setReason("");
                	    non.setStatus(BkpmConstants.STATUS_SUCCESS);
                	    non.setCreatedDate(new Date());
                	    nonFinancialService.saveNonFinancial(non);
            		} else {
            			// save report Failed to Non_Financial table
            		    non.setMenu("Transaction Inquiry");
            		    non.setNote("Inquiry account statement");
            		    non.setReason("code: "+res.getCode()+" - message: "+ res.getMessage());
            		    non.setStatus(BkpmConstants.STATUS_FAILED);
            		    non.setCreatedDate(new Date());
            		    nonFinancialService.saveNonFinancial(non);
            		}
            		
            	} else if("getAccountBalance".equals(method)) {
            		//mapping request
            		String username = ((AccountBalanceReq)dataReq).getUsername();
            		User user = new User();
            		if(null!=username &&! username.isEmpty()) {
            			user = userService.findUserByUsername(username);   
            		}
            		
            		if(BkpmConstants.CODE_SOAP_SUCCESS.equalsIgnoreCase(res.getCode())) {
            			// save report SUCCESS to Non_Financial table
                	    NonFinancial non = new NonFinancial();
                	    non.setMenu("Account Balance");
                	    non.setNote("Inquiry account balance");
                	    non.setReason("");
                	    non.setStatus(BkpmConstants.STATUS_SUCCESS);
                	    non.setUser(user);
                	    non.setCreatedDate(new Date());
                	    nonFinancialService.saveNonFinancial(non);
            		} else {
            			// save report Failed to Non_Financial table
            			NonFinancial non = new NonFinancial();
        			    non.setMenu("Account Balance");
        			    non.setNote("Inquiry account balance");
        			    non.setReason("code: "+res.getCode()+" - message: "+res.getMessage());
        			    non.setStatus(BkpmConstants.STATUS_FAILED);
        			    non.setUser(user);
        			    non.setCreatedDate(new Date());
        			    nonFinancialService.saveNonFinancial(non);
            		}
            		
            	} else if("getAccountBalanceQR".equals(method)) {
            		//mapping request
            		String username = ((AccountBalanceQRReq)dataReq).getUsername();
            		User user = new User();
            		if(null!=username &&! username.isEmpty()) {
            			user = userService.findUserByUsername(username);   
            		}
            		
            		if(BkpmConstants.CODE_SOAP_SUCCESS.equalsIgnoreCase(res.getCode())) {
            			// save report SUCCESS to Non_Financial table
                	    NonFinancial non = new NonFinancial();
                	    non.setMenu("Account Balance");
                	    non.setNote("Inquiry account balance QR");
                	    non.setReason("");
                	    non.setStatus(BkpmConstants.STATUS_SUCCESS);
                	    non.setUser(user);
                	    non.setCreatedDate(new Date());
                	    nonFinancialService.saveNonFinancial(non);
            		} else {
            			// save report Failed to Non_Financial table
            			NonFinancial non = new NonFinancial();
        			    non.setMenu("Account Balance");
        			    non.setNote("Inquiry account balance QR");
        			    non.setReason("code: "+res.getCode()+" - message: "+res.getMessage());
        			    non.setStatus(BkpmConstants.STATUS_FAILED);
        			    non.setUser(user);
        			    non.setCreatedDate(new Date());
        			    nonFinancialService.saveNonFinancial(non);
            		}
            		
            	} else if("getAccountBalanceByAccNo".equals(method)) {
            		//mapping request
            		String username = ((AccountBalanceByAccNoReq)dataReq).getUsername();
            		User user = new User();
            		if(null!=username &&! username.isEmpty()) {
            			user = userService.findUserByUsername(username);   
            		}
            		
            		if(BkpmConstants.CODE_SOAP_SUCCESS.equalsIgnoreCase(res.getCode())) {
            			// save report SUCCESS to Non_Financial table
                	    NonFinancial non = new NonFinancial();
                	    non.setMenu("Account Balance");
                	    non.setNote("Inquiry account balance by acc no");
                	    non.setReason("");
                	    non.setStatus(BkpmConstants.STATUS_SUCCESS);
                	    non.setUser(user);
                	    non.setCreatedDate(new Date());
                	    nonFinancialService.saveNonFinancial(non);
            		} else {
            			// save report Failed to Non_Financial table
            			NonFinancial non = new NonFinancial();
        			    non.setMenu("Account Balance");
        			    non.setNote("Inquiry account balance by acc no");
        			    non.setReason("code: "+res.getCode()+" - message: "+res.getMessage());
        			    non.setStatus(BkpmConstants.STATUS_FAILED);
        			    non.setUser(user);
        			    non.setCreatedDate(new Date());
        			    nonFinancialService.saveNonFinancial(non);
            		}
            		
            	} else if("inquiryNotification".equals(method)) {
            		
            		String username = ((InquiryNotificationRequest)dataReq).getUsername();
            		
            		// save report to Non_Financial table
                    User user = userService.findUserByUsername(username);                
            	    NonFinancial non = new NonFinancial();
            	    non.setMenu("Notification");
            	    non.setNote("Inquiry list notification");
            	    non.setReason("");
            	    non.setStatus(BkpmConstants.STATUS_SUCCESS);
            	    non.setCreatedDate(new Date());
            	    non.setUser(user);        
                    nonFinancialService.saveNonFinancial(non);
            	}
            	
            	
            	
                log.debug("User End AOP: {}.{}() with result = {}", joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName(), result);
                
                
            }
            return result;
        } catch (IllegalArgumentException e) {
            log.error("Illegal argument: {} in {}.{}()", Arrays.toString(joinPoint.getArgs()),
                joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
            throw e;
        }
    }
}
