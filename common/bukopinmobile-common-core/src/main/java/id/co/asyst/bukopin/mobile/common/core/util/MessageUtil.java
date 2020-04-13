/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.common.core.util;

import java.util.Locale;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;

/**
 * Message Util
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Nov 22, 2019
 * @since 1.0.Alpha1-SNAPSHOT
 */
@Component
public class MessageUtil {
    
    /**
     * Logger 
     */
    private final static Logger log = LoggerFactory.getLogger(MessageUtil.class);
    
    /* Constants: */

    /* Attributes: */
    @Autowired
    public MessageSource messageSource;
    
    public MessageSourceAccessor accessor;

    /* Transient Attributes: */

    /* Constructors: */
    @PostConstruct
    private void init() {
        accessor = new MessageSourceAccessor(messageSource);
    }

    /* Getters & setters for attributes: */

    /* Getters & setters for transient attributes: */

    /* Functionalities: */
    
    public String get(String code) {
	String message = code;
	try {
	    message = accessor.getMessage(code);
	} catch(Exception e) {
	    log.error("message not found: "+code);
	}
	
        return message;
    }
    
    public String get(String code, Locale locale) {
	String message = code;
	try {
	    message = accessor.getMessage(code, locale);
	} catch(Exception e) {
	    log.error("message not found: "+code);
	}
	
        return message;
    }
    
    public String get(String code, Object[] args) {
        String message = code;
	try {
	    message = accessor.getMessage(code, args);
	} catch(Exception e) {
	    log.error("message not found: "+code);
	}
	
        return message;
    }
    
    public String get(String code, Object[] args, Locale locale) {
	String message = code;
	try {
	    message = accessor.getMessage(code, args, locale);
	} catch(Exception e) {
	    log.error("message not found: "+code);
	}
	
        return message;
    }

    /* Overrides: */
}
