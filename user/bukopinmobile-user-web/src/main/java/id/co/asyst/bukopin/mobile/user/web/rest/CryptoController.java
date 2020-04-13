/*
 * $Id$
 * 
 * Copyright (c) 2020 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.user.web.rest;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.co.asyst.bukopin.mobile.common.core.util.CryptoUtil;
import id.co.asyst.bukopin.mobile.common.core.util.MessageUtil;
import id.co.asyst.bukopin.mobile.common.model.ResponseMessage;
import id.co.asyst.bukopin.mobile.common.model.payload.CommonResponse;

/**
 * Crypto Controller
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Jan 13, 2020
 * @since 1.0.Alpha1-SNAPSHOT
 */
@Profile("!prod")
@RestController
@RequestMapping("/api/crypto")
public class CryptoController {
    /**
     * Logger
     */
    private final Logger log = LoggerFactory.getLogger(CryptoController.class);
    
    /**
     * Http Servlet Request
     */
    @Autowired
    private HttpServletRequest servletRequest;
    
    /**
     * Get Message Util
     */
    @Autowired
    private MessageUtil messageUtil;
    
    /* Constants: */

    /* Attributes: */

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */

    /* Getters & setters for transient attributes: */

    /* Functionalities: */
    /**
     * Encrypt Password
     * 
     * @param password The password to encrypt.
     * @return Encrypted password.
     */
    @PostMapping("/encrypt")
    public CommonResponse encryptPassword(@RequestBody Map<String, String> request) {
	CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(),
		messageUtil.get("success", servletRequest.getLocale()));
	
	String password = request.get("plainText");
	if(StringUtils.isBlank(password)) {
	    response = new CommonResponse(ResponseMessage.ERROR.getCode(),
			messageUtil.get("error.validation.required", new Object[] {"Plain Text"}, 
				servletRequest.getLocale()));
	} else {
	    String encrypted = CryptoUtil.encryptPassword(password);
	    Map<String, String> data = new HashMap<>();
	    data.put("encrypted", encrypted);
	    response.setData(data);
	}
	
	return response;
    }
    
    /**
     * Decrypt Password
     * 
     * @param encrypted The Encrypted Password to decrypt.
     * @return The decrypted password.
     */
    @PostMapping("/decrypt")
    public CommonResponse decryptPassword(@RequestBody Map<String, String> request) {
	CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(),
		messageUtil.get("success", servletRequest.getLocale()));
	
	String encrypted = request.get("encrypted");
	if(StringUtils.isBlank(encrypted)) {
	    response = new CommonResponse(ResponseMessage.ERROR.getCode(),
			messageUtil.get("error.validation.required", new Object[] {"Encrypted"}, 
				servletRequest.getLocale()));
	} else {
	    String password = CryptoUtil.decryptPassword(encrypted);
	    Map<String, String> data = new HashMap<>();
	    data.put("decrypted", password);
	    response.setData(data);
	}
	
	return response;
    }
    
    /**
     * Decrypt AES
     * 
     * @param encrypted The Encrypted AES to decrypt.
     * @return The decrypted password.
     */
    @PostMapping("/decrypt/AES")
    public CommonResponse decryptAES(@RequestBody Map<String, String> request) {
	CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(),
		messageUtil.get("success", servletRequest.getLocale()));
	
	String encrypted = request.get("encrypted");
	if(StringUtils.isBlank(encrypted)) {
	    response = new CommonResponse(ResponseMessage.ERROR.getCode(),
			messageUtil.get("error.validation.required", new Object[] {"Encrypted"}, 
				servletRequest.getLocale()));
	} else {
	    String password = CryptoUtil.decryptAES(encrypted);
	    Map<String, String> data = new HashMap<>();
	    data.put("decrypted", password);
	    response.setData(data);
	}
	
	return response;
    }
    
    /**
     * Encrypt AES
     * 
     * @param plainText The Plain Text to encrypt.
     * @return Encrypted text in AES.
     */
    @PostMapping("/encrypt/AES")
    public CommonResponse encryptAES(@RequestBody Map<String, String> request) {
	log.debug("REST encrypt AES : {}", request);
	CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(),
		messageUtil.get("success", servletRequest.getLocale()));
	
	String plainText = request.get("plainText");
	if(StringUtils.isBlank(plainText)) {
	    response = new CommonResponse(ResponseMessage.ERROR.getCode(),
			messageUtil.get("error.validation.required", new Object[] {"Plain Text"}, 
				servletRequest.getLocale()));
	} else {
	    String encrypted = CryptoUtil.encryptAES(plainText);
	    Map<String, String> data = new HashMap<>();
	    data.put("encrypted", encrypted);
	    response.setData(data);
	}
	
	return response;
    }
    
    /**
     * Decrypt AES HEX
     * 
     * @param encrypted The Encrypted AES in Hex to decrypt.
     * @return The decrypted password.
     */
    @PostMapping("/decrypt/AESHex")
    public CommonResponse decryptAESHex(@RequestBody Map<String, String> request) {
	CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(),
		messageUtil.get("success", servletRequest.getLocale()));
	
	String encrypted = request.get("encrypted");
	if(StringUtils.isBlank(encrypted)) {
	    response = new CommonResponse(ResponseMessage.ERROR.getCode(),
			messageUtil.get("error.validation.required", new Object[] {"Encrypted"}, 
				servletRequest.getLocale()));
	} else {
	    String password = CryptoUtil.decryptAESHex(encrypted);
	    Map<String, String> data = new HashMap<>();
	    data.put("decrypted", password);
	    response.setData(data);
	}
	
	return response;
    }
    
    /**
     * Encrypt AES Hex
     * 
     * @param plainText The Plain Text to encrypt.
     * @return Encrypted text in AES Hex.
     */
    @PostMapping("/encrypt/AESHex")
    public CommonResponse encryptAESHex(@RequestBody Map<String, String> request) {
	CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(),
		messageUtil.get("success", servletRequest.getLocale()));
	
	String plainText = request.get("plainText");
	if(StringUtils.isBlank(plainText)) {
	    response = new CommonResponse(ResponseMessage.ERROR.getCode(),
			messageUtil.get("error.validation.required", new Object[] {"Plain Text"}, 
				servletRequest.getLocale()));
	} else {
	    String encrypted = CryptoUtil.encryptAESHex(plainText);
	    Map<String, String> data = new HashMap<>();
	    data.put("encrypted", encrypted);
	    response.setData(data);
	}
	
	return response;
    }
    
    /* Overrides: */
}
