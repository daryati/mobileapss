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
import id.co.asyst.bukopin.mobile.master.core.service.PurchaseCategoryService;
import id.co.asyst.bukopin.mobile.master.model.entity.PurchaseCategory;

/**
 * 
 * 
 * @author Ihsan Firman
 * @version $Revision$, Dec 7, 2019
 * @since 2.0
 */
@RestController
@RequestMapping("/purchaseCategory")
public class PurchaseCategoryController {
    /* Constants: */
    private final Logger log = LoggerFactory.getLogger(PurchaseCategory.class);

    /* Attributes: */
    @Autowired
    PurchaseCategoryService pCategoryService;

    @Autowired
    private MessageUtil messageUtil;

    @Autowired
    private HttpServletRequest servletRequest;

    /* Transient Attributes: */

    /* Constructors: */
    public PurchaseCategoryController(PurchaseCategoryService pCategoryService, MessageUtil messageUtil,
	    HttpServletRequest servletRequest) {

	this.pCategoryService = pCategoryService;

    }

    /* Getters & setters for attributes: */

    /* Getters & setters for transient attributes: */

    /* Functionalities: */
    @PostMapping("/createCategory")
    @ResponseStatus(HttpStatus.CREATED)
    public CommonResponse createCategory(@Valid @RequestBody CommonRequest<PurchaseCategory> request) {
	CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(),
		messageUtil.get("success", servletRequest.getLocale()));

	Optional<PurchaseCategory> findById = pCategoryService.findById(request.getData().getIdCategory());
	if (findById.isPresent()) {
	    log.debug("DATA CATEGORY ID IS ALREADY EXIST...");
	    response.setCode(ResponseMessage.DUPLICATE_DATA.getCode());
	    response.setMessage(messageUtil.get("error.duplicate.data", servletRequest.getLocale()));
	    return response;
	}

	PurchaseCategory category = new PurchaseCategory();
	category.setIdCategory(request.getData().getIdCategory());
	category.setName(request.getData().getName());
	category.setIsActive(true);
	pCategoryService.saveCategory(category);
	
	log.debug("DATA CATEGORY SUCCESSFULLY SAVED...");
	response.setData(category);

	return response;
    }

    @GetMapping("/findAll")
    @ResponseStatus(HttpStatus.OK)
    public CommonResponse findAllCategories() {
	CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(),
		messageUtil.get("success", servletRequest.getLocale()));

	List<PurchaseCategory> findAll = pCategoryService.findAll();

	if (findAll.isEmpty()) {
	    log.debug("DATA CATEGORY IS NOT FOUND....");
	    response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
	    response.setMessage(messageUtil.get("error.data.not.found", servletRequest.getLocale()));
	}
	response.setData(findAll);
	log.debug("SHOWING CATEGORY DATA : " + findAll.size());

	return response;

    }

    @GetMapping("/findById/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CommonResponse findCategoryById(@PathVariable Long id) {
	CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(),
		messageUtil.get("success", servletRequest.getLocale()));

	Optional<PurchaseCategory> findById = pCategoryService.findById(id);

	if (findById.isPresent()) {
	    response.setData(findById);
	    log.debug("DATA SUCCESSFULLY RETRIEVED....");
	} else {
	    log.debug("DATA CATEGORY IS NOT FOUND....");
	    response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
	    response.setMessage(messageUtil.get("error.data.not.found", servletRequest.getLocale()));
	}

	return response;
    }

    @DeleteMapping("/deleteById/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CommonResponse deleteById(@PathVariable Long id) {
	CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(),
		messageUtil.get("success", servletRequest.getLocale()));
	Optional<PurchaseCategory> findById = pCategoryService.findById(id);
	if (findById.isPresent()) {
	    findById.get().setIsActive(false);
	    pCategoryService.saveCategory(findById.get());
	    log.debug("CATEGORY DELETED SUCCESSFULLY..");
	} else {
	    response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
	    response.setMessage(messageUtil.get("error.data.not.found", servletRequest.getLocale()));
	    log.debug("CATEGORY ID  " + id + " IS NOT FOUND...");
	}

	return response;
    }

    @PutMapping("/updateCategory")
    @ResponseStatus(HttpStatus.OK)
    public CommonResponse updateCategory(@Valid @RequestBody CommonRequest<PurchaseCategory> request) {
	CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(),
		messageUtil.get("success", servletRequest.getLocale()));

	Optional<PurchaseCategory> oldData = pCategoryService.findById(request.getData().getIdCategory());
	
	if (oldData.isPresent()) {
	    oldData.get().setName(request.getData().getName());
	    oldData.get().setIsActive(request.getData().getIsActive());
	    pCategoryService.saveCategory(oldData.get());
	    log.debug("DATA SUCCESSFULLY UPDATED....");

	    response.setData(oldData);
	    return response;
	}else {
	    response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
	    response.setMessage(messageUtil.get("error.data.not.found", servletRequest.getLocale()));
	    log.debug("CATEGORY ID IS NOT FOUND...");
	}

	return response;

    }

    /* Overrides: */}
