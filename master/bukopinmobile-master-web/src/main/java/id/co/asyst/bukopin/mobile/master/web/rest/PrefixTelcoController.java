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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.xml.datatype.DatatypeConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.env.Environment;
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

import com.fasterxml.jackson.databind.ObjectMapper;

import id.co.asyst.bukopin.mobile.common.core.util.BkpmUtil;
import id.co.asyst.bukopin.mobile.common.core.util.MessageUtil;
import id.co.asyst.bukopin.mobile.common.model.ResponseMessage;
import id.co.asyst.bukopin.mobile.common.model.payload.CommonRequest;
import id.co.asyst.bukopin.mobile.common.model.payload.CommonResponse;
import id.co.asyst.bukopin.mobile.master.core.service.InstitutionService;
import id.co.asyst.bukopin.mobile.master.core.service.ListEMoneyService;
import id.co.asyst.bukopin.mobile.master.core.service.PrefixTelcoService;
import id.co.asyst.bukopin.mobile.master.model.entity.Institution;
import id.co.asyst.bukopin.mobile.master.model.entity.ListEMoney;
import id.co.asyst.bukopin.mobile.master.model.entity.PrefixTelco;
import id.co.asyst.bukopin.mobile.master.model.payload.InstitutionRequest;
import id.co.asyst.bukopin.mobile.master.model.payload.MobileDataSelectionResponse;
import id.co.asyst.bukopin.mobile.master.model.payload.PrefixTelcoDetailResponse;
import id.co.asyst.bukopin.mobile.master.model.payload.PrefixTelcoRequest;
import id.co.asyst.bukopin.mobile.master.model.payload.PulsaSelectionResponse;

/**
 * REST Controller for Prefic Telco
 * 
 * @author Kartika Dwi H
 * @version $Revision$, Jan 30, 2020
 * @since 1.1.Alpha1
 */
@RestController
@RequestMapping("/prefixTelco")
public class PrefixTelcoController {
    /* Constants: */
    private final Logger log = LoggerFactory.getLogger(PrefixTelcoController.class);
    
    private final String INSTITUTIION_TYPE_PULSA = "Prepaid";
    private final String INSTITUTIION_TYPE_MOBILEDATA = "Paket Data";
    /* Attributes: */
    
    /**
     * Environment
     */
    @Autowired
    private Environment env;
    
    /**
     * prefixTelco service
     */
    @Autowired
    private PrefixTelcoService prefixTelcoService;
    
    /**
     * institution service
     */
    @Autowired
    private InstitutionService institutionService;
    
    
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
     * find prefix telco by no and type
     * 
     * @return List of prefix telco data
     */
	@SuppressWarnings({"unchecked", "rawtypes"})
	@PostMapping("/findByPrefixNoAndType")
    @ResponseStatus(HttpStatus.OK)
	public CommonResponse findByPrefixNoAndType(@Valid @RequestBody CommonRequest<PrefixTelcoRequest> request)
		    throws DatatypeConfigurationException {		
		CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(),
			messageUtil.get("success", httpServletRequest.getLocale()));		
		String redisStatus = env.getProperty("config.status.redis");
		
		// use redis??
		PrefixTelco prefixRes = new PrefixTelco();
		if("1".equalsIgnoreCase(redisStatus)) {
			log.debug("get data from cache");
			prefixRes = prefixTelcoService.findByPrefixNoAndTypeCache(request.getData().getPhoneNumber(), request.getData().getType());
			if(null == prefixRes) {
				//institutionService.storeInstitutionToCache();
				prefixRes = prefixTelcoService.findByPrefixNoAndType("%"+request.getData().getPhoneNumber()+"%", request.getData().getType());
				prefixTelcoService.storePrefixTelcoToCache();
			}
		} else {
			log.debug("get data from table");
			prefixRes = prefixTelcoService.findByPrefixNoAndType("%"+request.getData().getPhoneNumber()+"%", request.getData().getType());
		}
		
		
		if(null != prefixRes) {
			// get institution data based on prefix id
			List<Institution> institutionList = prefixRes.getInstitutions();			
			if(null != institutionList && institutionList.size()>0) {
				for(Institution ins : institutionList) {	
					if(INSTITUTIION_TYPE_PULSA.equalsIgnoreCase(ins.getInstitutionType())) {
						PrefixTelcoDetailResponse detail = new PrefixTelcoDetailResponse();
						detail.setAdminFee(ins.getAdminFee());
						detail.setInstitutionType(ins.getInstitutionType());
						detail.setName(ins.getName());
						detail.setProductCode(ins.getCodeCbs());
											
						//set selection
						String[] sel = ins.getSelection().split("\\|");
						
						List<PulsaSelectionResponse> pulsa = new ArrayList<>();
						for(int a=0; a<sel.length; a++) {
							PulsaSelectionResponse  selection = new PulsaSelectionResponse();
							selection.setAmount(new BigDecimal(sel[a]));
							pulsa.add(selection);
						}
						detail.setSelection(pulsa);						
						prefixRes.setPulsa(detail);
						
					} else if (INSTITUTIION_TYPE_MOBILEDATA.equalsIgnoreCase(ins.getInstitutionType())) {
						PrefixTelcoDetailResponse detail = new PrefixTelcoDetailResponse();
						detail.setAdminFee(ins.getAdminFee());
						detail.setInstitutionType(ins.getInstitutionType());
						detail.setName(ins.getName());
						detail.setProductCode(ins.getCodeCbs());
											
						//set selection
						String[] sel = ins.getSelection().split("\\|");		
						List<MobileDataSelectionResponse> mobileData = new ArrayList<>();
						
						for(int a=0; a<sel.length; a++) {
							String[] data = sel[a].split("\\#");
							MobileDataSelectionResponse selection = new MobileDataSelectionResponse();
							if(data.length>=4) {
								selection.setTitle(data[0]);
								selection.setDescription(data[1]);
								selection.setAmount(new BigDecimal(data[2]));
								selection.setPackageCode(data[3]);
								
								mobileData.add(selection);
							}
							
						}
						detail.setSelection(mobileData);						
						prefixRes.setMobileData(detail);
					}
				}
				
			}
			
			response.setData(prefixRes);
			
			
		} else {
			 log.debug("data not found ");
			 response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
			 response.setMessage(messageUtil.get("error.data.not.found", httpServletRequest.getLocale()));
		}
		return response;
	}
    

	/**
     * find prefix telco by id and institution type
     * 
     * @return List of prefix telco data
     */
	@PostMapping("/findByPrefixIdAndInstitutionType")
    @ResponseStatus(HttpStatus.OK)
	public CommonResponse findByPrefixIdAndInstitutionType(@Valid @RequestBody CommonRequest<InstitutionRequest> request)
		    throws DatatypeConfigurationException {		
		CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(),
			messageUtil.get("success", httpServletRequest.getLocale()));		
		String redisStatus = env.getProperty("config.status.redis");
		
		Institution institution = new Institution();
		PrefixTelco pref = new PrefixTelco();
		
		if("1".equalsIgnoreCase(redisStatus)) {
			institution = institutionService.findByPrefixIdAndInstitutionTypeCache(request.getData().getIdPrefix(), request.getData().getInstitutionType());
		} else {
			pref = prefixTelcoService.findByIdAndInstitutionType(request.getData().getIdPrefix(), request.getData().getInstitutionType());
			institution = pref.getInstitutions().get(0);
			
		}
		
		if(null != institution) {
			response.setData(institution);
		} else {
			 log.debug("data not found ");
			 response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
			 response.setMessage(messageUtil.get("error.data.not.found", httpServletRequest.getLocale()));
		}
		return response;
	}
    
	/**
     * find prefix telco by prefix number
     * 
     * @return List of prefix telco data
     */
	@SuppressWarnings("unchecked")
	@GetMapping("/findOneByPrefixNo/{custNo}")
    @ResponseStatus(HttpStatus.OK)
	public CommonResponse findOneByPrefixNo(@PathVariable String custNo)
		    throws DatatypeConfigurationException {		
		CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(),
			messageUtil.get("success", httpServletRequest.getLocale()));

		
		Object prefixRespObj = prefixTelcoService.findOneByPrefixNo("%"+custNo+"%");
		log.debug(BkpmUtil.convertToJson(prefixRespObj));
		ObjectMapper objMapper = new ObjectMapper();
		List<String> prefixResp = objMapper.convertValue(prefixRespObj, ArrayList.class);
		
		if(null == prefixRespObj) {
		    log.error("error while get prefix with number : "+custNo);
		    response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
		    response.setMessage(messageUtil.get("error.data.not.found", httpServletRequest.getLocale()));
		    return response;
		}
		
		response.setData(prefixResp);
		
		return response;
	}
   
    
    
    
    
    
    
    
    
	
    
    
    
    
    
    
    
    
    
    
    
    
    
    


    /* Overrides: */}
