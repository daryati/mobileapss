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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.jfree.util.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import id.co.asyst.bukopin.mobile.common.core.util.BkpmUtil;
import id.co.asyst.bukopin.mobile.common.core.util.CryptoUtil;
import id.co.asyst.bukopin.mobile.common.core.util.MessageUtil;
import id.co.asyst.bukopin.mobile.common.model.BkpmConstants;
import id.co.asyst.bukopin.mobile.common.model.ResponseMessage;
import id.co.asyst.bukopin.mobile.common.model.payload.CommonRequest;
import id.co.asyst.bukopin.mobile.common.model.payload.CommonResponse;
import id.co.asyst.bukopin.mobile.common.model.payload.PagingRequest;
import id.co.asyst.bukopin.mobile.common.model.payload.PagingResponse;
import id.co.asyst.bukopin.mobile.master.model.DestinationTypeEnum;
import id.co.asyst.bukopin.mobile.master.model.TransactionTypeEnum;
import id.co.asyst.bukopin.mobile.service.core.UserModuleService;
import id.co.asyst.bukopin.mobile.transfer.core.service.TransactionHistoryService;
import id.co.asyst.bukopin.mobile.transfer.model.payload.TransactionHistoryCreditCardResponse;
import id.co.asyst.bukopin.mobile.transfer.model.payload.TransactionHistoryEmoneyResponse;
import id.co.asyst.bukopin.mobile.transfer.model.payload.TransactionHistoryFTOverbookResponse;
import id.co.asyst.bukopin.mobile.transfer.model.payload.TransactionHistoryInsuranceResponse;
import id.co.asyst.bukopin.mobile.transfer.model.payload.TransactionHistoryPLNPostpaidResponse;
import id.co.asyst.bukopin.mobile.transfer.model.payload.TransactionHistoryPLNPrepaidResponse;
import id.co.asyst.bukopin.mobile.transfer.model.payload.TransactionHistoryResponse;
import id.co.asyst.bukopin.mobile.transfer.model.payload.TransactionHistorySamolnasResponse;
import id.co.asyst.bukopin.mobile.transfer.model.payload.TransactionHistoryTelcoDataResponse;
import id.co.asyst.bukopin.mobile.transfer.model.payload.TransactionHistoryTelcoPostpaidResponse;
import id.co.asyst.bukopin.mobile.transfer.model.payload.TransactionHistoryTelcoPrepaidResponse;
import id.co.asyst.bukopin.mobile.user.core.service.UserService;
import id.co.asyst.bukopin.mobile.user.model.entity.User;
import id.co.asyst.foundation.service.connector.Services;
import retrofit2.Call;

/**
 * 
 * 
 * @author Gibran Haq
 * @version $Revision$, Mar 5, 2020
 * @since 1.1.Alpha1-SNAPSHOT
 */
@RestController
@RequestMapping("/transactionHistory")
public class TransactionHistoryController {
    /* Constants: */
    private final Logger log = LoggerFactory.getLogger(TransactionHistoryController.class);

    /* Attributes: */
    @Autowired
    private MessageUtil messageUtil;

    @Autowired
    private HttpServletRequest servletRequest;
    
    @Autowired
    TransactionHistoryService transactionHistoryService;
    
    @Autowired
    private Environment env;

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */

    /* Getters & setters for transient attributes: */

    /* Functionalities: */
    /**
     * Get All Transaction History
     * @param usernameEnc
     * @return
     * @throws IOException
     */
//    @GetMapping("/getAll/{usernameEnc}")
    @Deprecated
    public CommonResponse getAllTransactionHistory(@PathVariable String usernameEnc) throws IOException {
	log.debug("REST request to get all transaction history: {}");
	CommonResponse response = new CommonResponse();
	
	String username = CryptoUtil.decryptAESHex(usernameEnc);
	log.debug(username);
	
	CommonResponse user = Services.create(UserModuleService.class).getUserByUsername(username).execute().body();
	log.debug("response user: "+user);
	if(!ResponseMessage.SUCCESS.getCode().equals(user.getCode())) {
	    log.error("Error while get user by username");
	    response.setCode(ResponseMessage.INTERNAL_SERVER_ERROR.getCode());
	    response.setMessage(messageUtil.get("error.internal.server", servletRequest.getLocale()));
	    return response;
	}
	
	ObjectMapper mapper = new ObjectMapper();
	Map<String, Object> resultUserObj = mapper.convertValue(user.getData(), Map.class);
	Map<String, Object> resUser = (Map<String, Object>) resultUserObj.get("user");
	Long userId = Long.valueOf(resUser.get("id").toString());
	
	String db = env.getProperty("spring.datasource.driver-class-name");
	List<TransactionHistoryResponse> respData = transactionHistoryService.getAllTransactionHistory(userId, servletRequest.getLocale(), db);
	if (null == respData || respData.isEmpty()) {
	    log.error("transaction history not found");
	    response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
	    response.setMessage(messageUtil.get("data.not.found", servletRequest.getLocale()));
	} else {
	    log.debug("get all transaction history success");
	    response.setCode(ResponseMessage.SUCCESS.getCode());
	    response.setMessage(messageUtil.get("success", servletRequest.getLocale()));
	    response.setData(respData);
	}
	
	return response;
	
    }
    
    /**
     * Get All Transaction History with Pagination
     * 
     * @param request
     * @return
     * @throws IOException
     */
    @PostMapping("/getAll")
    public PagingResponse getAllTransactionHistoryPaging(@RequestBody PagingRequest<Map<String, String>> request) throws IOException {
	PagingResponse response = new PagingResponse();
	
	String username = request.getData().get("username");
	if (StringUtils.isBlank(username)) {
	    log.error("username is required");
	    response.setCode(ResponseMessage.DATA_REQUIRED.getCode());
	    response.setMessage(messageUtil.get("error.validation.required",new Object[] {"username"}, servletRequest.getLocale()));
	    return response;
	}
	
	CommonResponse user = Services.create(UserModuleService.class).getUserByUsername(username).execute().body();
	if(!ResponseMessage.SUCCESS.getCode().equals(user.getCode())) {
	    log.error("Error while get user by username");
	    response.setCode(ResponseMessage.INTERNAL_SERVER_ERROR.getCode());
	    response.setMessage(messageUtil.get("error.internal.server", servletRequest.getLocale()));
	    return response;
	}
	
	ObjectMapper mapper = new ObjectMapper();
	Map<String, Object> resultUserObj = mapper.convertValue(user.getData(), Map.class);
	Map<String, Object> resUser = (Map<String, Object>) resultUserObj.get("user");
	Long userId = Long.valueOf(resUser.get("id").toString());
	
	String db = env.getProperty("spring.datasource.driver-class-name");
	List<TransactionHistoryResponse> trxHistory = transactionHistoryService.getAllTransactionHistory(
		userId, servletRequest.getLocale(), db);
	if (null == trxHistory || trxHistory.isEmpty()) {
	    log.error("transaction history not found");
	    response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
	    response.setMessage(messageUtil.get("data.not.found", servletRequest.getLocale()));
	} else {
	    log.debug("get all transaction history success");
	    response = transactionHistoryService
		    .generateTrxHistoryPagingResponse(trxHistory, request.getPaging().getPage(), request.getPaging().getLimit());
	    
	    response.setCode(ResponseMessage.SUCCESS.getCode());
	    response.setMessage(messageUtil.get("success", servletRequest.getLocale()));
	}
	
	return response;
	
    }
    
    /**
     * Get Detail Transaction History Fund Transfer and Overbook
     * @param request
     * @return
     */
    @PostMapping("/getDetail")
    public CommonResponse getDetailTransactionHistoryFTOverbook(@RequestBody CommonRequest<Map<String, String>> request) {
	log.debug("REST request to get detail transaction history: {}", request);
	CommonResponse response = new CommonResponse();
	
	String idDetail = request.getData().get("idDetail");
	if (null == idDetail || idDetail.isEmpty()) {
	    log.error("idDetail is required");
	    response.setCode(ResponseMessage.DATA_REQUIRED.getCode());
	    response.setMessage(messageUtil.get("error.validation.required",new Object[] {"idDetail"}, servletRequest.getLocale()));
	    return response;
	}
	
	Long id = Long.valueOf(request.getData().get("idDetail"));
	String type = request.getData().get("type");
	if (null == type || type.isEmpty()) {
	    log.error("type is required");
	    response.setCode(ResponseMessage.DATA_REQUIRED.getCode());
	    response.setMessage(messageUtil.get("error.validation.required",new Object[] {"type"}, servletRequest.getLocale()));
	    return response;
	}
	
	if(BkpmConstants.OVERBOOK.equalsIgnoreCase(type) || BkpmConstants.FUND_TRANSFER.equalsIgnoreCase(type)) {
	    Optional<TransactionHistoryFTOverbookResponse> respFTOverbookData = transactionHistoryService.getDetailFTOverbook(id,type);
	    if (!respFTOverbookData.isPresent()) {
		log.error("transaction history "+type+" not found");
		response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
		response.setMessage(messageUtil.get("data.not.found", servletRequest.getLocale()));
	    } else {
		log.debug("get detail transaction history "+type+" success");
		response.setCode(ResponseMessage.SUCCESS.getCode());
		response.setMessage(messageUtil.get("success", servletRequest.getLocale()));
		response.setData(respFTOverbookData.get());
	    }
	} else if(TransactionTypeEnum.PLNPRE.name().equalsIgnoreCase(type)) {
	    Optional<TransactionHistoryPLNPrepaidResponse> respPLNPrepaidData = transactionHistoryService.getDetailPLNPrepaid(id);
	    if (!respPLNPrepaidData.isPresent()) {
		log.error("transaction history "+type+" not found");
		response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
		response.setMessage(messageUtil.get("data.not.found", servletRequest.getLocale()));
	    } else {
		log.debug("get detail transaction history "+type+" success");
		response.setCode(ResponseMessage.SUCCESS.getCode());
		response.setMessage(messageUtil.get("success", servletRequest.getLocale()));
		response.setData(respPLNPrepaidData.get());
	    }
	} else if(TransactionTypeEnum.PLNPOST.name().equalsIgnoreCase(type)){
	    Optional<TransactionHistoryPLNPostpaidResponse> respPLNPostpaidData = transactionHistoryService.getDetailPLNPostpaid(id);
	    if (!respPLNPostpaidData.isPresent()) {
		log.error("transaction history "+type+" not found");
		response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
		response.setMessage(messageUtil.get("data.not.found", servletRequest.getLocale()));
	    } else {
		log.debug("get detail transaction history "+type+" success");
		response.setCode(ResponseMessage.SUCCESS.getCode());
		response.setMessage(messageUtil.get("success", servletRequest.getLocale()));
		response.setData(respPLNPostpaidData.get());
	    }
	} else if(TransactionTypeEnum.TELCOPRE.name().equalsIgnoreCase(type)){
	    Optional<TransactionHistoryTelcoPrepaidResponse> respTelcoPrepaidData = transactionHistoryService.getDetailTelcoPrepaid(id);
	    if (!respTelcoPrepaidData.isPresent()) {
		log.error("transaction history "+type+" not found");
		response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
		response.setMessage(messageUtil.get("data.not.found", servletRequest.getLocale()));
	    } else {
		log.debug("get detail transaction history "+type+" success");
		response.setCode(ResponseMessage.SUCCESS.getCode());
		response.setMessage(messageUtil.get("success", servletRequest.getLocale()));
		response.setData(respTelcoPrepaidData.get());
	    }
	} else if(TransactionTypeEnum.TELCOPOST.name().equalsIgnoreCase(type)){
	    Optional<TransactionHistoryTelcoPostpaidResponse> respTelcoPostpaidData = transactionHistoryService.getDetailTelcoPostpaid(id);
	    if (!respTelcoPostpaidData.isPresent()) {
		log.error("transaction history "+type+" not found");
		response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
		response.setMessage(messageUtil.get("data.not.found", servletRequest.getLocale()));
	    } else {
		log.debug("get detail transaction history "+type+" success");
		response.setCode(ResponseMessage.SUCCESS.getCode());
		response.setMessage(messageUtil.get("success", servletRequest.getLocale()));
		response.setData(respTelcoPostpaidData.get());
	    }
	} else if(TransactionTypeEnum.EMONEY.name().equalsIgnoreCase(type)){
	    Optional<TransactionHistoryEmoneyResponse> respEmoneyData = transactionHistoryService.getDetailEmoney(id);
	    if (!respEmoneyData.isPresent()) {
		log.error("transaction history "+type+" not found");
		response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
		response.setMessage(messageUtil.get("data.not.found", servletRequest.getLocale()));
	    } else {
		log.debug("get detail transaction history "+type+" success");
		response.setCode(ResponseMessage.SUCCESS.getCode());
		response.setMessage(messageUtil.get("success", servletRequest.getLocale()));
		response.setData(respEmoneyData.get());
	    }
	} else if(TransactionTypeEnum.INSURANCE.name().equalsIgnoreCase(type)){
	    Optional<TransactionHistoryInsuranceResponse> respInsuranceData = transactionHistoryService.getDetailInsurance(id);
	    if (!respInsuranceData.isPresent()) {
		log.error("transaction history "+type+" not found");
		response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
		response.setMessage(messageUtil.get("data.not.found", servletRequest.getLocale()));
	    } else {
		log.debug("get detail transaction history "+type+" success");
		response.setCode(ResponseMessage.SUCCESS.getCode());
		response.setMessage(messageUtil.get("success", servletRequest.getLocale()));
		response.setData(respInsuranceData.get());
	    }
	} else if(TransactionTypeEnum.CREDITCARD.name().equalsIgnoreCase(type)){
	    Optional<TransactionHistoryCreditCardResponse> respCreditCardData = transactionHistoryService.getDetailCreditCard(id);
	    if (!respCreditCardData.isPresent()) {
		log.error("transaction history "+type+" not found");
		response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
		response.setMessage(messageUtil.get("data.not.found", servletRequest.getLocale()));
	    } else {
		log.debug("get detail transaction history "+type+" success");
		response.setCode(ResponseMessage.SUCCESS.getCode());
		response.setMessage(messageUtil.get("success", servletRequest.getLocale()));
		response.setData(respCreditCardData.get());
	    }
	} else if(TransactionTypeEnum.SAMOLNAS.name().equalsIgnoreCase(type)){
	    Optional<TransactionHistorySamolnasResponse> respSamolnasData = transactionHistoryService.getDetailSamolnas(id);
	    if (!respSamolnasData.isPresent()) {
		log.error("transaction history "+type+" not found");
		response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
		response.setMessage(messageUtil.get("data.not.found", servletRequest.getLocale()));
	    } else {
		log.debug("get detail transaction history "+type+" success");
		response.setCode(ResponseMessage.SUCCESS.getCode());
		response.setMessage(messageUtil.get("success", servletRequest.getLocale()));
		response.setData(respSamolnasData.get());
	    }
	} else if (TransactionTypeEnum.TELCODATA.name().equalsIgnoreCase(type)) {
	    Optional<TransactionHistoryTelcoDataResponse> result = transactionHistoryService.getDetailTelcoData(id);
	    if (!result.isPresent()) {
		log.error("transaction history " + type + " not found");
		response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
		response.setMessage(messageUtil.get("data.not.found", servletRequest.getLocale()));
	    } else {
		log.debug("get detail transaction history " + type + " success");
		response.setCode(ResponseMessage.SUCCESS.getCode());
		response.setMessage(messageUtil.get("success", servletRequest.getLocale()));
		response.setData(result.get());
	    }

	} else {
	    log.error("type incorrect");
	    response.setCode(ResponseMessage.INTERNAL_SERVER_ERROR.getCode());
	    response.setMessage(messageUtil.get("error.internal.server", servletRequest.getLocale()));
	    return response;
	}
	
	return response;
    }

    /* Overrides: */
    
}
