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

import java.math.BigInteger;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.xml.datatype.DatatypeConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import id.co.asyst.bukopin.mobile.common.core.util.MessageUtil;
import id.co.asyst.bukopin.mobile.common.model.ResponseMessage;
import id.co.asyst.bukopin.mobile.common.model.payload.CommonRequest;
import id.co.asyst.bukopin.mobile.common.model.payload.CommonResponse;
import id.co.asyst.bukopin.mobile.master.core.service.CurrencyService;
import id.co.asyst.bukopin.mobile.master.core.service.ExchangeRateService;
import id.co.asyst.bukopin.mobile.master.core.service.ProductInfoExchangeRateService;
import id.co.asyst.bukopin.mobile.master.model.entity.Currency;
import id.co.asyst.bukopin.mobile.master.model.payload.DepositoDetailResponse;
import id.co.asyst.bukopin.mobile.master.model.payload.ExchangeRateDetailResponse;
import id.co.asyst.bukopin.mobile.master.model.payload.ExchangeRateRS;
import id.co.asyst.bukopin.mobile.master.model.payload.ExchangeRateResponse;
import id.co.asyst.bukopin.mobile.master.model.payload.GiroResponse;
import id.co.asyst.bukopin.mobile.master.model.payload.SavingsDetailResponse;
import id.co.asyst.bukopin.mobile.master.model.payload.SukuBungaRequest;


/**
 * REST Controller for managing Exchange Rate.
 * 
 * @author Kartika Dwi Handini
 * @version $Revision$,(Created on Feb 07, 2020)
 * @since 1.1.Alpha1
 */
@RestController
@RequestMapping("/rate")
public class ExchangeRateController {
	/**
     * Logger.
     */
    private final Logger log = LoggerFactory.getLogger(ExchangeRateController.class);

    /* Constants: */
    private static final Integer BANK_RATE_DEPOSITO = 32;
    private static final Integer BANK_RATE_TABUNGAN = 31;    
    private static final Integer BANK_RATE_GIRO = 30;
    private static final DateFormat exchangeDate = new SimpleDateFormat("dd MMMM yyyy");
    private static final DateFormat exchangeTime = new SimpleDateFormat("HH:mm:ss");

    /* Attributes: */

    /* Services: */
    
    /**
     * Exchange Rate Service
     */
    @Autowired
    private ExchangeRateService exchangeRateService;
    
    /**
     * Product info Service
     */
    @Autowired
    private ProductInfoExchangeRateService productInfoService;
    
    /**
     * Currency Service
     */
    @Autowired
    private CurrencyService currencyService;
    
    
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

    /**
     * Environment
     */
    @Autowired
    private Environment env;
    
    /* Constructors: */
    /**
     * Default Constructor.
     */
    public ExchangeRateController() {
        // do nothing.
    }

    /* Getters & setters for attributes: */

    /* Functions: */
    /**
     * GET /exchangeRate : get all Exchange rate
     *
     * @return the response with status 200 (OK) and the list of exchange rate in body.
     */
	@GetMapping("/exchangeRate")
	@ResponseStatus(HttpStatus.OK)
    public CommonResponse getExchangeRate(){
		log.debug("REST request to exchange rate");
        CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(), messageUtil.get("success", httpServletRequest.getLocale()));
        String redisStatus = env.getProperty("config.status.redis");
        ExchangeRateRS res = new ExchangeRateRS();
        List<ExchangeRateDetailResponse> tt = new ArrayList<>();
        List<ExchangeRateDetailResponse> bankNote = new ArrayList<>();
        
        //use redis??
        List<Currency> currency = new ArrayList<>();
        if("1".equalsIgnoreCase(redisStatus)) {
        	currency = currencyService.getAllCurrencyCache();
        } else {
        	currency = currencyService.findAll();
        }        
        
        if(null != currency && currency.size()>0) {
        	for(Currency cur : currency) {
           	 ExchangeRateResponse result = exchangeRateService.getExchangeRate(cur.getCurrencyNo().toString());
           	 tt.add(result.getTT());
           	 bankNote.add(result.getBankNote());        	 
           }
        	
           res.setBankNote(bankNote);
           res.setTT(tt);
           
           //reqTime
           Date dateTime = new Date();
           String dt = exchangeDate.format(dateTime);
           String tm = exchangeTime.format(dateTime);
           res.setReqTime(dt.concat(" - ").concat(tm));
           response.setData(res);
        } else {
        	response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
			response.setMessage(messageUtil.get("error.data.not.found", httpServletRequest.getLocale()));
        }
        
        return response;
	}
    
	/* Getters & setters for attributes: */

    /* Functions: */
    /**
     * POST /bankRate : get all bank rate
     *
     *@param request
     * @return the response with status 200 (OK) and the bank rate in body.
     */
	@PostMapping("/bankRate")
	@ResponseStatus(HttpStatus.OK)
	public CommonResponse GetSukuBunga(@Valid @RequestBody CommonRequest<SukuBungaRequest> request)
		    throws DatatypeConfigurationException {		
		CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(),
			messageUtil.get("success", httpServletRequest.getLocale()));
		
		if(BANK_RATE_DEPOSITO == request.getData().getProductCode()) {
			log.debug("bank rate request to DEPOSITO", request.getData().getProductCode());
			List<DepositoDetailResponse> deposito = productInfoService.getDeposito(request.getData().getProductCode());
			if(null != deposito) {
				response.setData(deposito);
			} else {
				log.debug("Deposito not found ");
				 response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
				 response.setMessage(messageUtil.get("error.product.info.not.found", httpServletRequest.getLocale()));
			}
			
		} else if(BANK_RATE_TABUNGAN == request.getData().getProductCode()) {
			log.debug("bank rate request to SAVINGS", request.getData().getProductCode());
			List<SavingsDetailResponse> saving = productInfoService.getSavings(request.getData().getProductCode());
			if(null != saving) {
				response.setData(saving);
			} else {
				log.debug("savings not found ");
				 response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
				 response.setMessage(messageUtil.get("error.product.info.not.found", httpServletRequest.getLocale()));
			}
			
		} else if(BANK_RATE_GIRO == request.getData().getProductCode()) {
			log.debug("bank rate request to GIRO", request.getData().getProductCode());
			GiroResponse giro = productInfoService.getGiro(request.getData().getProductCode());
			response.setData(giro);
		} else {
			log.debug("bank rate request not found code ", request.getData().getProductCode());
			response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
			response.setMessage(messageUtil.get("error.product.info.not.found", httpServletRequest.getLocale()));
		}
		
		return response;
	}
}
