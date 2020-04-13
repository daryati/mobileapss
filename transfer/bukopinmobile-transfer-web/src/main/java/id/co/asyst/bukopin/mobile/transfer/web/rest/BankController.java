/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.transfer.web.rest;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import id.co.asyst.bukopin.mobile.common.core.util.MessageUtil;
import id.co.asyst.bukopin.mobile.common.model.ResponseMessage;
import id.co.asyst.bukopin.mobile.common.model.payload.CommonRequest;
import id.co.asyst.bukopin.mobile.common.model.payload.CommonResponse;
import id.co.asyst.bukopin.mobile.transfer.core.service.BankService;
import id.co.asyst.bukopin.mobile.transfer.model.entity.Bank;

/**
 * 
 * 
 * @author Ihsan Firman
 * @version $Revision$, Nov 14, 2019
 * @since 2.0
 */

@RestController
@RequestMapping("/bank")
public class BankController {
    /* Constants: */

    private final Logger log = LoggerFactory.getLogger(BankController.class);
    /* Attributes: */
    @Autowired
    BankService bankService;
    
    @Autowired
    private MessageUtil messageUtil;
    
    @Autowired
    private HttpServletRequest servletRequest;
    

    /* Transient Attributes: */

    /* Constructors: */
    public BankController(BankService bankService) {
	this.bankService = bankService;
    }

    /* Getters & setters for attributes: */

    /* Getters & setters for transient attributes: */

    /* Functionalities: */
    /**
     * Create Favorite Account Destination
     * <ol>
     * <li>Validate duplicate account number</li>
     * <li>Save data into bkpm db</li>
     * </ol>
     * @param ReceiverInfo data
     * @return Data saved status
     */
    @PostMapping("/createBank")
    @ResponseStatus(HttpStatus.CREATED)
    public CommonResponse createBank(@Valid @RequestBody CommonRequest<Bank> bank) {
	log.debug("REST create Bank...");
	CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(), messageUtil.get("success", servletRequest.getLocale()));

	Bank findBankByBankCode = bankService.findBankByBankCode(bank.getData().getBankCode());

	if (findBankByBankCode != null) {
	    response.setCode(ResponseMessage.DUPLICATE_DATA.getCode());
	    response.setMessage(messageUtil.get("error.duplicate.data", servletRequest.getLocale()));
	    return response;
	}

	Bank bankData = new Bank();

	bankData.setBankCode(bank.getData().getBankCode());
	bankData.setBankName(bank.getData().getBankName());

	bankService.saveBank(bankData);

	response.setData(bankData);

	log.debug("REST response: {}", response);
	return response;
    }
    /* Overrides: */

    /**
     * Get All Favorite Account Destination Data's
     * 
     * @param username
     * @return All Favorite Destination Account Data's
     */
    @GetMapping("/findByBankCode/{bankCode}")
    @ResponseStatus(HttpStatus.OK)
    public CommonResponse findBankByBankCode(@PathVariable String bankCode) {
	log.debug("REST find Bank from bank code" + bankCode);

	CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(), messageUtil.get("success", servletRequest.getLocale()));
	Bank bankData = bankService.findBankByBankCode(bankCode);

	if (null != bankData ) {
		response.setData(bankData);

	} else {
		log.error("Data Bank not found! ");
	    response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
	    response.setMessage(messageUtil.get("data.not.found", servletRequest.getLocale()));
	    
	}

	return response;
    }
    
    @GetMapping("/findAllBank")
    @ResponseStatus(HttpStatus.OK)
    public CommonResponse findAllBank() {
	log.debug("REST get all Data Bank...");
	CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(), messageUtil.get("success", servletRequest.getLocale()));

	List<Bank> getAllBank = bankService.findAll();

	if (getAllBank != null) {
	    response.setData(getAllBank);
	} else {
		response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
	    response.setMessage(messageUtil.get("data.not.found", servletRequest.getLocale()));
	    return response;
	}

	//log.debug("REST response: {}", response);
	return response;
    }


    /**
     * Delete Favorite Destination Account By Id
     * @param id of the Account
     * 
     * @return Deleted Status
     */
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CommonResponse deleteById(@PathVariable Long id) {
	log.debug("Delete Bank by id " + id);
	CommonResponse response = new CommonResponse();
	Optional<Bank> findById = bankService.findBankById(id);
	if (!findById.isPresent()) {
	    log.error("Data Bank is empty! ");
	    response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
	    response.setMessage(messageUtil.get("data.not.found", servletRequest.getLocale()));
	} else {
	    log.debug("Deleting data Bank with id " + id);
	    bankService.deleteBankById(id);
	    response.setCode(ResponseMessage.SUCCESS.getCode());
	    response.setMessage(messageUtil.get("success", servletRequest.getLocale()));
	}

	return response;
    }
}
