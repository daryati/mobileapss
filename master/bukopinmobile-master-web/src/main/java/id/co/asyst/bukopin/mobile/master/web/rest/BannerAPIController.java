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

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.co.asyst.bukopin.mobile.common.core.util.MessageUtil;
import id.co.asyst.bukopin.mobile.common.model.ResponseMessage;
import id.co.asyst.bukopin.mobile.common.model.payload.CommonResponse;
import id.co.asyst.bukopin.mobile.master.core.service.BannerService;
import id.co.asyst.bukopin.mobile.master.model.entity.Banner;

/**
 * 
 * 
 * @author Ihsan Firman
 * @version $Revision$, Apr 29, 2020
 * @since 2.0
 */
@RestController
@RequestMapping("/banner")
public class BannerAPIController {
	/* Constants: */
	private Logger log = LoggerFactory.getLogger(BannerAPIController.class);

	/* Attributes: */
	@Autowired
	private BannerService bannerService;
	
	@Autowired
	private MessageUtil messageUtil;
	
	@Autowired
	HttpServletRequest servletRequest;

	/* Transient Attributes: */

	/* Constructors: */

	/* Getters & setters for attributes: */

	/* Getters & setters for transient attributes: */

	/* Functionalities: */
	@GetMapping("/getBanner")
	public CommonResponse getBanner() {
		CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(),
				messageUtil.get("success", servletRequest.getLocale()));
		log.debug("Get active Banner...");
		
		List<Banner> result = bannerService.getActiveBanner(true);
		
		if(result.isEmpty()) {
			log.error("Banner not found");
			response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
			response.setMessage(messageUtil.get("error.data.not.found", servletRequest.getLocale()));
			
			return response;
		}
		
		response.setData(result);
		
		return response;
		
	}
	/* Overrides: */}
