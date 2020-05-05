/*
 * $Id$
 * 
 * Copyright (c) 2020 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.payment.web.rest;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import id.co.asyst.bukopin.mobile.common.core.util.BkpmUtil;
import id.co.asyst.bukopin.mobile.common.core.util.CryptoUtil;
import id.co.asyst.bukopin.mobile.common.core.util.MessageUtil;
import id.co.asyst.bukopin.mobile.common.model.BkpmConstants;
import id.co.asyst.bukopin.mobile.common.model.ResponseMessage;
import id.co.asyst.bukopin.mobile.common.model.payload.CommonRequest;
import id.co.asyst.bukopin.mobile.common.model.payload.CommonResponse;
import id.co.asyst.bukopin.mobile.payment.core.service.ListCreditService;
import id.co.asyst.bukopin.mobile.payment.core.service.repository.ListCreditRepository;
import id.co.asyst.bukopin.mobile.payment.model.entity.ListCredit;
import id.co.asyst.bukopin.mobile.service.core.UserModuleService;
import id.co.asyst.bukopin.mobile.user.model.payload.VerifyPhoneOwnerRequest;
import id.co.asyst.foundation.service.connector.Services;

/**
 * List Credit Controller
 * 
 * @author Ihsan Firman
 * @version $Revision$, Apr 8, 2020
 * @since 2.0
 */
@RestController
@RequestMapping("/listCredit")
@Profile("!prod")
public class ListCreditController {
	/* Constants: */
	/**
	 * Logger
	 */
	private final Logger log = LoggerFactory.getLogger(ListCreditController.class);
	private static final String SUCCESS_CODE = "000";
	/* Attributes: */

	/* Transient Attributes: */

	@Autowired
	ListCreditService listCreditService;

	@Autowired
	ListCreditRepository listCreditRepository;

	@Autowired
	MessageUtil messageUtil;

	@Autowired
	HttpServletRequest servletRequest;
	/* Constructors: */

	/* Getters & setters for attributes: */

	/* Getters & setters for transient attributes: */

	/* Functionalities: */
	@GetMapping("/findListCredit/{username}")
	@ResponseStatus(HttpStatus.OK)
	public CommonResponse findCreditByCode(@PathVariable String username) throws IOException {
		log.debug("Find List Credit...");
		CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(),
				messageUtil.get("success", servletRequest.getLocale()));

		String decryptedUsername = CryptoUtil.decryptAESHex(username);

		// Validate Token and Phone Owner
		CommonRequest<VerifyPhoneOwnerRequest> phoneReq = new CommonRequest<>();
		VerifyPhoneOwnerRequest phoneReqData = new VerifyPhoneOwnerRequest();
		phoneReqData.setUsername(decryptedUsername);
		phoneReqData.setToken(servletRequest.getHeader(HttpHeaders.AUTHORIZATION));
		phoneReqData.setPhoneIdentity(servletRequest.getHeader(BkpmConstants.HTTP_HEADER_DEVICE_ID));
		phoneReq.setData(phoneReqData);
		CommonResponse resPhone = Services.create(UserModuleService.class).verifyPhoneOwner(phoneReq).execute().body();
		if (!ResponseMessage.SUCCESS.getCode().equals(resPhone.getCode())) {
			log.error("Validate Token and Phone owner error..");
			return resPhone;
		}

		List<ListCredit> listCredit = listCreditService.findAll();
		if (listCredit.isEmpty()) {
			log.error("List Credit Not Found.");
			response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
			response.setMessage(messageUtil.get("error.data.not.found", servletRequest.getLocale()));
			return response;
		}

		log.debug("Data List Credit {}" + BkpmUtil.convertToJson(listCredit));
		response.setData(listCredit);
		return response;
	}

	/* Overrides: */}
