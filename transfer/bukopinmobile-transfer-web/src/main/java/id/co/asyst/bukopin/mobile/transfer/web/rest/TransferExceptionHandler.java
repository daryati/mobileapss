/*
 * $Id$
 * 
 * Copyright (c) 2020 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.transfer.web.rest;

import java.io.IOException;
import java.net.NoRouteToHostException;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

import org.bouncycastle.crypto.DataLengthException;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.JDBCConnectionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import id.co.asyst.bukopin.mobile.common.core.exception.DataNotMatchException;
import id.co.asyst.bukopin.mobile.common.core.exception.ForbiddenAccessException;
import id.co.asyst.bukopin.mobile.common.core.exception.MiddlewareException;
import id.co.asyst.bukopin.mobile.common.core.util.BkpmUtil;
import id.co.asyst.bukopin.mobile.common.core.util.MessageUtil;
import id.co.asyst.bukopin.mobile.common.model.FieldValidatorEnum;
import id.co.asyst.bukopin.mobile.common.model.ResponseMessage;
import id.co.asyst.bukopin.mobile.common.model.payload.CommonResponse;

/**
 * Global Exception Handler for Transfer Module
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Jan 2, 2020
 * @since 1.0.Alpha1-SNAPSHOT
 */
@RestControllerAdvice
public class TransferExceptionHandler extends ResponseEntityExceptionHandler {
    private final Logger log = LoggerFactory.getLogger(TransferExceptionHandler.class);

    /* Constants: */
    private static final String FIELD_SEPARATOR = "\\.";

    @Autowired
    private MessageUtil messageUtil;

    /**
     * Http Servlet Request
     */
    @Autowired
    private HttpServletRequest servletRequest;

    /* Attributes: */

    /* Services: */

    /* Transient Attributes: */

    /* Constructors: */
    /**
     * Default Constructor
     */
    public TransferExceptionHandler() {

    }

    /* Getters & setters for attributes: */

    /* Getters & setters for transient attributes: */

    /* Functions: */
    // 403 - Forbidden Access Exception
    @ExceptionHandler({ ForbiddenAccessException.class })
    protected ResponseEntity<Object> handleForbiddenException(final RuntimeException ex, final WebRequest request) {
	log.error("Forbidden Access Exception: " + ex.getMessage(), ex);
	
	CommonResponse response = new CommonResponse();
	response.setCode(String.valueOf(HttpStatus.FORBIDDEN.value()));
	response.setMessage(messageUtil.get("error.forbidden.access", servletRequest.getLocale()));

	return handleExceptionInternal(ex, response, new HttpHeaders(), HttpStatus.OK, request);
    }
    
    // 500 - Middleware Exception
    @ExceptionHandler({ MiddlewareException.class })
    protected ResponseEntity<Object> handleMiddlewareException(final RuntimeException ex, final WebRequest request) {
	log.error("Middleware Exception: " + ex.getMessage(), ex);
	
	CommonResponse response = new CommonResponse();
	response.setCode(ResponseMessage.ERROR_EXTERNAL.getCode());
	response.setMessage(messageUtil.get("error.middleware",new Object[] {ex.getMessage()},
		servletRequest.getLocale()));

	return handleExceptionInternal(ex, response, new HttpHeaders(), HttpStatus.OK, request);
    }
    
    // 500 - Connection Exception
    @ExceptionHandler({ IOException.class })
    protected ResponseEntity<Object> handleTimeout(Exception ex) {
	log.error("Connection Timeout Exception: " + ex.getMessage(), ex);
	
	CommonResponse response = new CommonResponse();
	response.setCode(ResponseMessage.ERROR_EXTERNAL.getCode());
	response.setMessage(messageUtil.get("error.connection",new Object[] {ex.getMessage()},
		servletRequest.getLocale()));

	return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    // 200 - Data Not Match Exception
    @ExceptionHandler({ DataNotMatchException.class })
    protected ResponseEntity<Object> handleDataNotMatchException(final RuntimeException ex, final WebRequest request) {
	log.error("Data Not Match Exception: " + ex.getMessage(), ex);
	
	CommonResponse response = new CommonResponse();
	response.setCode(ResponseMessage.DATA_NOT_MATCH.getCode());
	response.setMessage(messageUtil.get("error.data.not.match",new Object[] {ex.getMessage()},
		servletRequest.getLocale()));

	return handleExceptionInternal(ex, response, new HttpHeaders(), HttpStatus.OK, request);
    }
    
    // 500 - Null Pointer, Entity Not Found.
    @ExceptionHandler({ NullPointerException.class, EntityNotFoundException.class, 
	NoRouteToHostException.class, JDBCConnectionException.class, IllegalArgumentException.class,
	DataLengthException.class, StringIndexOutOfBoundsException.class, ConstraintViolationException.class})
    protected ResponseEntity<Object> handleInternal(final RuntimeException ex, final WebRequest request) {
	log.error("Internal Error, caused by: " + ex.getCause(), ex);

	CommonResponse response = new CommonResponse();
	response.setCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
	response.setMessage(messageUtil.get("error.internal.server", servletRequest.getLocale()));

	return handleExceptionInternal(ex, response, new HttpHeaders(), HttpStatus.OK, request);
    }

    /* Overrides: */
    // 400 - Message Not Readable.
    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.web.servlet.mvc.method.annotation.
     * ResponseEntityExceptionHandler#handleHttpMessageNotReadable(org.
     * springframework.http.converter.HttpMessageNotReadableException,
     * org.springframework.http.HttpHeaders, org.springframework.http.HttpStatus,
     * org.springframework.web.context.request.WebRequest)
     */
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
	    HttpHeaders headers, HttpStatus status, WebRequest request) {
	log.error("Message not readable exception: " + ex.getMessage(), ex);

	CommonResponse response = new CommonResponse();
	response.setCode(String.valueOf(HttpStatus.BAD_REQUEST.value()));
	response.setMessage(messageUtil.get("error.invalid.request", servletRequest.getLocale()));

	return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Exception Handler of Bean Validation (@Valid).
    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.web.servlet.mvc.method.annotation.
     * ResponseEntityExceptionHandler#handleMethodArgumentNotValid(org.
     * springframework.web.bind.MethodArgumentNotValidException,
     * org.springframework.http.HttpHeaders, org.springframework.http.HttpStatus,
     * org.springframework.web.context.request.WebRequest)
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
	    HttpHeaders headers, HttpStatus status, WebRequest request) {
	BindingResult br = ex.getBindingResult();

	// get error field name
	String[] fields = br.getFieldError().getField().split(FIELD_SEPARATOR);
	String field = fields[fields.length - 1];
	field = BkpmUtil.convertReadableField(field);
	
	// get validator types, @NotBlank, @Pattern, etc
	String typeError = br.getFieldError().getCode();
	String codeError = ResponseMessage.INVALID_INPUT.getCode();
	// default error message
	String message = br.getFieldError().getDefaultMessage();
	// set error message
	if(FieldValidatorEnum.PATTERN.value().equals(typeError)) {
	    message = messageUtil.get("error.validation.regex", new Object[] { field }, 
		    servletRequest.getLocale());
	} else if(FieldValidatorEnum.NOT_BLANK.value().equals(typeError)) {
	    message  = messageUtil.get("error.validation.required", new Object[] { field }, 
		    servletRequest.getLocale());
	    codeError = ResponseMessage.DATA_REQUIRED.getCode();
	} else if(FieldValidatorEnum.EMAIL.value().equals(typeError)) {
	    message  = messageUtil.get("error.validation.email", new Object[] { field }, 
		    servletRequest.getLocale());
	}

	CommonResponse response = new CommonResponse();
	response.setCode(codeError);
	response.setMessage(message);
	
	return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }
}
