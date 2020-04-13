/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.master.web.rest;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import id.co.asyst.bukopin.mobile.common.core.util.MessageUtil;
import id.co.asyst.bukopin.mobile.common.model.ResponseMessage;
import id.co.asyst.bukopin.mobile.common.model.payload.CommonRequest;
import id.co.asyst.bukopin.mobile.common.model.payload.CommonResponse;
import id.co.asyst.bukopin.mobile.master.core.service.FAQService;
import id.co.asyst.bukopin.mobile.master.model.entity.FAQ;

/**
 * REST Controller for Frequently Asked Question
 * 
 * @author Ihsan Firman
 * @version $Revision$, Nov 20, 2019
 * @since 2.0
 */
@RestController
@RequestMapping("/faq")
public class FAQController {
    /* Constants: */
    private final Logger log = LoggerFactory.getLogger(FAQController.class);
    /* Attributes: */
    
    @Autowired
    FAQService faqService;
    
    /**
     * Get Message Util
     */
    @Autowired
    private MessageUtil messageUtil;
    
    /**
     * Http Servlet Request
     */
    @Autowired
    private HttpServletRequest httpServletRequest;
    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */

    /* Getters & setters for transient attributes: */

    /* Functionalities: */
    
    /**
     * create new FAQ
     * 
     * @param faq to be create
     * 
     * @return Response status and FAQ's data
     */
    @PostMapping("/createFaq")
    @ResponseStatus(HttpStatus.CREATED)
    public CommonResponse createFAQ(@Valid @RequestBody CommonRequest<FAQ> faq) {
	log.debug("Creating FAQ...");
	CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(), messageUtil.get("success", httpServletRequest.getLocale()));
	
	FAQ fq = new FAQ();
	Date now = new Date();
	fq.setQuestion(faq.getData().getQuestion());
	fq.setAnswer(faq.getData().getAnswer());
	fq.setCreatedBy(faq.getData().getCreatedBy());
	fq.setCreatedDate(now);
	
	faqService.saveFAQ(fq);
	
	response.setData(fq);
	
	
	return response;
    }
    
    /**
     * find All of FAQ's data
     * 
     * @return List of FAQ's data
     */
    @GetMapping("/allFaq")
    @ResponseStatus(HttpStatus.OK)
    public CommonResponse findAllFAQ() {
	log.debug("Find all FAQ's....");
	CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(), messageUtil.get("success", httpServletRequest.getLocale()));
	
	List<FAQ> findAll = faqService.findAll();
	if(findAll.isEmpty()) {
		response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
	    response.setMessage(messageUtil.get("error.data.not.found", httpServletRequest.getLocale()));
	    return response;
	}
	response.setData(findAll);
	
	return response;
    }
    
    /**
     * updateFAQ
     * 
     * @param faq, FAQ data to be update
     * @return Updated status and body of updated FAQ's
     */
    @PutMapping("/updateFaq")
    @ResponseStatus(HttpStatus.OK)
    public CommonResponse updateFAQ(@Valid @RequestBody CommonRequest<FAQ> faq) {
	
	log.debug("Find all FAQ's....");
	CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(), messageUtil.get("success", httpServletRequest.getLocale()));
	
	Date now = new Date();
	Optional<FAQ> oldFaq = faqService.findById(faq.getData().getId());
	if(!oldFaq.isPresent()) {
		response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
	    response.setMessage(messageUtil.get("error.data.not.found", httpServletRequest.getLocale()));
	    
	    return response;
	}
	oldFaq.get().setAnswer(faq.getData().getAnswer());
	oldFaq.get().setQuestion(faq.getData().getQuestion());
	oldFaq.get().setUpdatedDate(now);
	oldFaq.get().setUpdatedBy(faq.getData().getUpdatedBy());
	
	faqService.saveFAQ(oldFaq.get());
	
	response.setData(oldFaq.get());
	
	return response;
    }
    
    /**
     * deleteFAQbyId
     * @param id
     * @return Delete Status
     */
    @DeleteMapping("/deleteFaq/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CommonResponse deleteFAQbyId(@PathVariable Long id) {
	log.debug("Delete FAQ by Id = " + id);
	
	CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(), messageUtil.get("success", httpServletRequest.getLocale()));
	
	Optional<FAQ> findById = faqService.findById(id);
	if(!findById.isPresent()) {
		response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
	    response.setMessage(messageUtil.get("error.data.not.found", httpServletRequest.getLocale()));
	    
	    return response;
	}
	faqService.deleteFAQById(id);
	
	return response;
    }
    
    

    /* Overrides: */}
