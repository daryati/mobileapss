/*
 * $Id$
 * 
 * Copyright (c) 2020 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
/*
 * Copyright (c) 2020 Lufthansa Systems Indonesia, PT. All rights reserved.
 */
package id.co.asyst.bukopin.mobile.master.web.rest;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import id.co.asyst.bukopin.mobile.common.core.util.BkpmUtil;
import id.co.asyst.bukopin.mobile.common.core.util.MessageUtil;
import id.co.asyst.bukopin.mobile.common.model.ResponseMessage;
import id.co.asyst.bukopin.mobile.common.model.payload.CommonRequest;
import id.co.asyst.bukopin.mobile.common.model.payload.CommonResponse;
import id.co.asyst.bukopin.mobile.master.core.service.TransactionService;
import id.co.asyst.bukopin.mobile.master.model.entity.Transaction;
import id.co.asyst.bukopin.mobile.master.model.payload.TransactionCommonRequest;
import id.co.asyst.bukopin.mobile.service.core.UserModuleService;
import id.co.asyst.bukopin.mobile.user.model.entity.User;
import id.co.asyst.foundation.service.connector.Services;

/**
 * 
 * 
 * @author Ihsan Firman
 * @version $Revision$, Jul 22, 2020
 * @since 2.0
 */

@RestController
@RequestMapping("/transaction")
public class TransactionController {
	/* Constants: */
	
	Logger log = LoggerFactory.getLogger(TransactionCommonRequest.class);

	/* Attributes: */
	@Autowired
	private TransactionService transactionService;

	@Autowired
	private MessageUtil messageUtil;

	@Autowired
	private HttpServletRequest servletRequest;
	/* Transient Attributes: */

	/* Constructors: */

	/* Getters & setters for attributes: */

	/* Getters & setters for transient attributes: */

	/* Functionalities: */
	@PostMapping("/findByRefAndDestinationId")
	@ResponseStatus(HttpStatus.OK)
	private CommonResponse findTransactionByRefandDestinationId(@Valid @RequestBody CommonRequest<TransactionCommonRequest> request) {
		CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(),
				messageUtil.get("success", servletRequest.getLocale()));

		String reffNumber = request.getData().getReferenceNumber();
		Long destinationId = Long.parseLong(request.getData().getDestinationId());
		Transaction findTransaction = transactionService.findByReffNumberAndDestinationId(reffNumber, destinationId);

		if (findTransaction == null) {
			response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
			response.setMessage(messageUtil.get("error.data.not.found", servletRequest.getLocale()));
			return response;
		}

		response.setData(findTransaction);

		return response;
	}

	@PostMapping("/updateTransaction")
	@ResponseStatus(HttpStatus.OK)
	private CommonResponse updateTransaction(@Valid @RequestBody CommonRequest<TransactionCommonRequest> request) {
		CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(),
				messageUtil.get("success", servletRequest.getLocale()));

		String reffNumber = request.getData().getReferenceNumber();
		Long destinationId = Long.parseLong(request.getData().getDestinationId());
		Transaction theTransaction = transactionService.findByReffNumberAndDestinationId(reffNumber, destinationId);

		if (theTransaction == null) {
			response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
			response.setMessage(messageUtil.get("error.data.not.found", servletRequest.getLocale()));
			return response;
		}

		theTransaction.setStatus(request.getData().getStatus());

		transactionService.save(theTransaction);

		return response;
	}
	
	@SuppressWarnings("unchecked")
	@PostMapping("/saveTransaction")
	@ResponseStatus(HttpStatus.CREATED)
	private CommonResponse saveTransaction(@RequestBody CommonRequest<TransactionCommonRequest> request) {
		CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(),
				messageUtil.get("success", servletRequest.getLocale()));
		
		log.debug("Create Transaction : {}", BkpmUtil.convertToJson(request.getData()));
		
		Transaction transaction = new Transaction();
		
		String username = request.getData().getUsername();
		
		CommonResponse findUser = new CommonResponse();
		
		try {
			findUser = Services.create(UserModuleService.class).getUserByUsername(username).execute()
					.body();
		} catch (IOException e) {
			log.error(e.getMessage(), e);
			response.setCode(ResponseMessage.INTERNAL_SERVER_ERROR.getCode());
			response.setMessage(messageUtil.get("error.internal.server", servletRequest.getLocale()));
			return response;
		}
		
		ObjectMapper oMapper = new ObjectMapper();
		
		Map<String, Object> res = oMapper.convertValue(findUser.getData(), Map.class);
		User userMap = oMapper.convertValue(res.get("user"), User.class);
		
		transaction.setUser(userMap);
		transaction.setAccountNumber(request.getData().getAccountNumber());
		transaction.setCreatedDate(new Date());
		transaction.setTotalAmount(request.getData().getTotalAmount());
		transaction.setType(request.getData().getType());
		transaction.setNoteEn(request.getData().getNoteEn());
		transaction.setNoteId(request.getData().getNoteId());
		transaction.setBillerProduct(request.getData().getBillerProduct());
		transaction.setMenu(request.getData().getMenu());
		transaction.setStatus(request.getData().getStatus());
		transaction.setReason(request.getData().getReason());
		
		transactionService.save(transaction);
		
		response.setData(transaction);
		
		return response;
	}
	/* Overrides: */

}
