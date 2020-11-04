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
package id.co.asyst.bukopin.mobile.transfer.web.rest;

import java.math.BigDecimal;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import id.co.asyst.bukopin.mobile.common.core.util.MessageUtil;
import id.co.asyst.bukopin.mobile.common.model.ResponseMessage;
import id.co.asyst.bukopin.mobile.common.model.payload.CommonRequest;
import id.co.asyst.bukopin.mobile.common.model.payload.CommonResponse;
import id.co.asyst.bukopin.mobile.transfer.core.service.DailyLimitService;
import id.co.asyst.bukopin.mobile.transfer.core.service.LimitPackageService;
import id.co.asyst.bukopin.mobile.transfer.model.limitUserDailyClass;
import id.co.asyst.bukopin.mobile.transfer.model.entity.LimitPackage;
import id.co.asyst.bukopin.mobile.user.model.entity.User;



/**
 * REST Controller for Limit Package
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Jul 7, 2020
 * @since 1.4.Alpha1
 */
@RestController
@RequestMapping("/limitPackage")
public class LimitPackageController {
	private final Logger log = LoggerFactory
			.getLogger(LimitPackageController.class);
    /* Constants: */

    /* Attributes: */

    /* Beans: */
    /**
     * Get Message Util
     */
    @Autowired
    private MessageUtil messageUtil;

    /**
     * Http Servlet Request
     */
    @Autowired
    private HttpServletRequest servletRequest;
    
    /**
     * Limit Package Service
     */
    @Autowired
    private LimitPackageService limitService;
    
    @Autowired
	private DailyLimitService dailyLimitService;

    /* Constructors: */

    /* Functionalities: */
    /**
     * GET : Get Default Limit Transfer/ Overbook
     * 
     * @return Success if there is default Limit Transfer, else Not Found.
     */
    @GetMapping("/getDefault")
    public CommonResponse getDefaultLimit() {
	CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(), 
		messageUtil.get("success", servletRequest.getLocale()));
	LimitPackage defaultLimit = limitService.getDefault();
	if(defaultLimit==null) {
	    response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
	    response.setMessage(messageUtil.get("data.not.found", servletRequest.getLocale()));
	} else {
	    response.setData(defaultLimit);
	}
	return response;
    }

    /* Overrides: */
    
    @PostMapping("/verifydailylimit")
	@ResponseStatus(HttpStatus.OK)
	public CommonResponse verifydailyLimit(
			@Valid @RequestBody CommonRequest<limitUserDailyClass> request)
			throws ParseException {
		CommonResponse response = new CommonResponse(
				ResponseMessage.SUCCESS.getCode(), messageUtil.get("success",
						servletRequest.getLocale()));
		String accNo = request.getData().getAccNo();
		String username = request.getData().getUsername();
		String jenis = request.getData().getJenis();
		BigDecimal amount = request.getData().getAmount();
		response = dailyLimitService.checkdailyLimit(username, accNo, amount,
				jenis);
		return response;

	}

	@PostMapping("/prosesdailyLimit")
	@ResponseStatus(HttpStatus.OK)
	public CommonResponse prosesdailyLimit(@Valid @RequestBody Object request)
			throws ParseException {
		CommonResponse response = new CommonResponse(
				ResponseMessage.SUCCESS.getCode(), messageUtil.get("success",
						servletRequest.getLocale()));
		log.debug("isi data request : " + request);
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> reqData = mapper.convertValue(request, Map.class);
		log.debug("isi " + reqData);
		JSONObject dataLimit = new JSONObject();
		if (reqData.get("jenis").equals("purchase")) {
			dataLimit.put("totalAmountpurchase",
					new BigDecimal("" + reqData.get("totalAmountpurchase")));
		} else if (reqData.get("jenis").equals("payment")) {
			dataLimit.put("totalAmountpayment",
					new BigDecimal("" + reqData.get("totalAmountpayment")));
		}
		if (reqData.get("action").equals("update")) {
			String id =""+ reqData.get("id");
			dataLimit.put("id", Long.parseLong(id));
		}
		dataLimit.put("createDate", (String) reqData.get("createDate"));
		dataLimit.put("limitId", Long.parseLong("" + reqData.get("limitId")));
		ObjectMapper mapuser = new ObjectMapper();
		Map<String, Object> userData = mapuser.convertValue(
				reqData.get("username"), Map.class);
		User user = new User();
		user.setId(Long.parseLong(userData.get("id") + ""));
		user.setUsername(userData.get("id") + "");
		dataLimit.put("username", user);
		dataLimit.put("status", (String) reqData.get("status"));
		dataLimit.put("reason", (String) reqData.get("reason"));
		dataLimit.put("menu", (String) reqData.get("menu"));
		dataLimit.put("action", (String) reqData.get("action"));
		dataLimit.put("jenis", (String) reqData.get("jenis"));
		dataLimit.put("accountNumber", (String) reqData.get("accountNumber"));
		log.debug("data limit controller " + dataLimit);
		response = dailyLimitService.prosesValidateLimit(dataLimit);
		return response;

	}

}
