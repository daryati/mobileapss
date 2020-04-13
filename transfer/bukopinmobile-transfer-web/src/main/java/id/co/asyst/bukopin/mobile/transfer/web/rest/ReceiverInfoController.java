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

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import id.co.asyst.bukopin.mobile.common.core.exception.ForbiddenAccessException;
import id.co.asyst.bukopin.mobile.common.core.util.BkpmUtil;
import id.co.asyst.bukopin.mobile.common.core.util.CryptoUtil;
import id.co.asyst.bukopin.mobile.common.core.util.MessageUtil;
import id.co.asyst.bukopin.mobile.common.model.BkpmConstants;
import id.co.asyst.bukopin.mobile.common.model.ResponseMessage;
import id.co.asyst.bukopin.mobile.common.model.payload.CommonRequest;
import id.co.asyst.bukopin.mobile.common.model.payload.CommonResponse;
import id.co.asyst.bukopin.mobile.service.core.UserModuleService;
import id.co.asyst.bukopin.mobile.service.model.payload.GetAccountBalanceRequest;
import id.co.asyst.bukopin.mobile.transfer.core.service.ReceiverInfoService;
import id.co.asyst.bukopin.mobile.transfer.core.util.AuthUtil;
import id.co.asyst.bukopin.mobile.transfer.model.entity.ReceiverInfo;
import id.co.asyst.bukopin.mobile.transfer.model.payload.AccountBalanceMapper;
import id.co.asyst.bukopin.mobile.transfer.model.payload.AccountBalanceResponse;
import id.co.asyst.bukopin.mobile.transfer.model.payload.MyAccountMapper;
import id.co.asyst.bukopin.mobile.transfer.model.payload.MyAccountResponse;
import id.co.asyst.bukopin.mobile.transfer.model.payload.OverbookResponse;
import id.co.asyst.bukopin.mobile.transfer.model.payload.ReceiverInfoResponse;
import id.co.asyst.bukopin.mobile.transfer.model.payload.TransferResponse;
import id.co.asyst.bukopin.mobile.user.model.payload.VerifyPhoneOwnerRequest;
import id.co.asyst.bukopin.mobile.user.model.payload.VerifyTokenOwnerResponse;
import id.co.asyst.foundation.service.connector.Services;
import retrofit2.Response;

/**
 * 
 * 
 * @author Ihsan Firman
 * @version $Revision$, Nov 14, 2019
 * @since 2.0
 */

@RestController
@RequestMapping("/favorite")
public class ReceiverInfoController {
    /* Constants: */

    private final Logger log = LoggerFactory.getLogger(ReceiverInfoController.class);
    /* Attributes: */
    @Autowired
    ReceiverInfoService favService;

    @Autowired
    private MessageUtil messageUtil;
    
    @Autowired
    private ReceiverInfoService receiverService;

    @Autowired
    private HttpServletRequest servletRequest;
    
    @Autowired
    private Environment env;

    /* Transient Attributes: */

    /* Constructors: */
    public ReceiverInfoController(ReceiverInfoService favService) {
	this.favService = favService;
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
     * 
     * @param ReceiverInfo data
     * @return Data saved status
     */
    @PostMapping("/createReceiver")
    @ResponseStatus(HttpStatus.CREATED)
    public CommonResponse createFavorite(@Valid @RequestBody CommonRequest<ReceiverInfo> request) {
	log.debug("REST create Favourite...");
	CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(),
		messageUtil.get("success", servletRequest.getLocale()));

	ReceiverInfo findAccountByName = favService.findAccountByUsernameAndAccountNumber(
		request.getData().getUsername(), request.getData().getAccountNumber());

	if (findAccountByName != null) {
	    response.setCode(ResponseMessage.DUPLICATE_DATA.getCode());
	    response.setMessage(messageUtil.get("error.duplicate.data", servletRequest.getLocale()));
	    return response;
	}

	ReceiverInfo receiver = new ReceiverInfo();

	receiver.setAccountName(request.getData().getAccountName());
	receiver.setAccountNumber(request.getData().getAccountNumber());
	receiver.setAlias(request.getData().getAlias());
	receiver.setCounter(1);
	receiver.setBank(request.getData().getBank());
	receiver.setSave(true);
	receiver.setUsername(request.getData().getUsername());

	/*
	 * receiver.setCode(request.getData().getCode());
	 * receiver.setBankName(request.getData().getBankName());
	 */

	favService.saveFavorite(receiver);

	response.setData(receiver);

	log.debug("REST response: {}", response);
	
	return response;
    }
    /* Overrides: */

    /**
     * Get All Receiver Account Destinations Data
     * 
     * @param username
     * @return All Receiver Destination Accounts Data
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
    @GetMapping("/favorites/{username}")
    @ResponseStatus(HttpStatus.OK)
    public CommonResponse findAllFavorite(@PathVariable String username) throws IOException {
	log.debug("REST find all favorite from userId : " + username);
	
	CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(),
		messageUtil.get("success", servletRequest.getLocale()));
	String decryptedUsername = CryptoUtil.decryptAESHex(username);
	
	// Validate Token and Phone Owner
	CommonRequest<VerifyPhoneOwnerRequest> phoneReq = new CommonRequest<>();
	VerifyPhoneOwnerRequest phoneReqData = new VerifyPhoneOwnerRequest();
	phoneReqData.setUsername(decryptedUsername);
	phoneReqData.setToken(servletRequest.getHeader(HttpHeaders.AUTHORIZATION));
	phoneReqData.setPhoneIdentity(servletRequest.getHeader(
		BkpmConstants.HTTP_HEADER_DEVICE_ID));
	phoneReq.setData(phoneReqData);
	CommonResponse resPhone = Services.create(UserModuleService.class)
		.verifyPhoneOwner(phoneReq).execute().body();
	if(!ResponseMessage.SUCCESS.getCode().equals(resPhone.getCode())) {
	    log.error("Validate Token and Phone owner error..");
	    return resPhone;
	}
	
	ObjectMapper mapper = new ObjectMapper();
	CommonResponse findAccountInfo = Services.create(UserModuleService.class,
		servletRequest.getHeader(HttpHeaders.AUTHORIZATION))
		.getAccountInfo(username).execute().body();
	CommonResponse findUserByUsername = Services.create(UserModuleService.class).getUserIdByUsername(decryptedUsername)
		.execute().body();
	
	log.debug(BkpmUtil.convertToJson(findAccountInfo));
	log.debug(BkpmUtil.convertToJson(findUserByUsername));
	
	// handle error service user
	if(!ResponseMessage.SUCCESS.getCode().equals(findAccountInfo.getCode())) {
	    return findAccountInfo;
	} else if(!ResponseMessage.SUCCESS.getCode().equals(findUserByUsername.getCode())) {
	    return findUserByUsername;
	}
	
	int userId = (int) findUserByUsername.getData();
	long id = Long.parseLong(String.valueOf(userId));

	List<ReceiverInfo> listBukopinReceiver = favService.findByBukopinAccount(id);
	List<ReceiverInfo> listNotBukopinReceiver = favService.findNotBukopinAccount(id);

	if(findAccountInfo.getData() == null) {
	    log.error("Account info null");
	 // data not found
	    response.setCode(ResponseMessage.DATA_NOT_FOUND.name());
	    response.setMessage(messageUtil.get("error.data.not.found", servletRequest.getLocale()));
	    return response;
	}
	List<MyAccountMapper> accountInfo = mapper.convertValue(findAccountInfo.getData(),
		new TypeReference<List<MyAccountMapper>>() {
		});

	
	List<MyAccountResponse> resp = new ArrayList<MyAccountResponse>();

	for (int x = 0; x < accountInfo.size(); x++) {
	    MyAccountResponse myResponse = new MyAccountResponse();
	    GetAccountBalanceRequest request = new GetAccountBalanceRequest();
	    request.setAccountType(Integer.toString(accountInfo.get(x).getAccountType().getValue()));
	    request.setAccountNumber(accountInfo.get(x).getAccountNo());
	    request.setUsername(decryptedUsername);

	    log.info("Get account balance -"+x);
	    CommonRequest<GetAccountBalanceRequest> req = AuthUtil.request(request.getAccountType(),
		    request.getAccountNumber(), decryptedUsername);
	    log.debug(BkpmUtil.convertToJson(req));
	    CommonResponse findAccountBalance = Services.create(UserModuleService.class).getAccountBalance(req)
		    .execute().body();
	    log.debug(BkpmUtil.convertToJson(findAccountBalance));
	    
	    AccountBalanceMapper accBalance = mapper.convertValue(findAccountBalance.getData(),
		    AccountBalanceMapper.class);

	    AccountBalanceResponse accBalanceResponse = new AccountBalanceResponse();
	    accBalanceResponse.setProductID(accBalance.getTransactionDetails().get(0).getProductID());
	    accBalanceResponse.setAvailableBalance(accBalance.getTransactionDetails().get(0).getAvailableBalance());
	    accBalanceResponse.setEffectiveBalance(accBalance.getTransactionDetails().get(0).getEffectiveBalance());
	    accBalanceResponse.setHoldAmount(accBalance.getTransactionDetails().get(0).getHoldAmount());

	    myResponse.setAccountName(accountInfo.get(x).getAccountName());
	    myResponse.setAccountNo(accountInfo.get(x).getAccountNo());
	    myResponse.setAccountStatus(accountInfo.get(x).getAccountStatus());
	    myResponse.setStatus(accountInfo.get(x).getStatus());
	    myResponse.setAccountType(accountInfo.get(x).getAccountType());
	    myResponse.setCif(accountInfo.get(x).getCif());
	    myResponse.setPdId(accountInfo.get(x).getPdId());
	    myResponse.setProduct(accountInfo.get(x).getProduct());
	    myResponse.setMainAccount(accountInfo.get(x).getMainAccount());
	    myResponse.setAccountBalance(accBalanceResponse);

	    resp.add(myResponse);
	}
	OverbookResponse overbookResponse = new OverbookResponse();
	overbookResponse.setMyAccount(resp);
	
	// limit "other" result
	int limitOther = Integer.valueOf(env.getProperty("config.max.receiver.overbook"));
	listBukopinReceiver = listBukopinReceiver.stream().limit(limitOther).collect(Collectors.toList());
	overbookResponse.setOther(listBukopinReceiver);

	// limit "transfer" result
	TransferResponse others = new TransferResponse();
	int limitTransfer = Integer.valueOf(env.getProperty("config.max.receiver.transfer"));
	listNotBukopinReceiver = listNotBukopinReceiver.stream().limit(limitTransfer).collect(Collectors.toList());
	others.setTransfer(listNotBukopinReceiver);

	ReceiverInfoResponse receiverResponse = new ReceiverInfoResponse();

	receiverResponse.setOverbook(overbookResponse);
	receiverResponse.setTransfer(others);

	response.setData(receiverResponse);

	return response;
    }

    /**
     * Get Favorite Destination Account based on Name
     * 
     * @param ReceiverInfo data of UserId and Name
     * @return List data of Favorite Destination Account
     */
    /*
     * @GetMapping("/getByName")
     * 
     * @ResponseStatus(HttpStatus.OK) public CommonResponse
     * findFavoriteByName(@Valid @RequestBody CommonRequest<ReceiverInfo> fav) {
     * log.debug( "Finding Favorite data by name " + fav.getData().getName() +
     * " For user " + fav.getData().getUsername());
     * 
     * CommonResponse response = new CommonResponse();
     * 
     * List<ReceiverInfo> findByName = favService.findByName(fav.getData());
     * 
     * if (findByName.isEmpty()) { log.error("Data Favorite is empty! ");
     * response.setCode(BkpmConstants.CODE_DATA_NOT_FOUND);
     * response.setMessage(BkpmConstants.MSG_DATA_NOT_FOUND);
     * 
     * } else { response.setData(findByName); }
     * 
     * return response; }
     */

    /**
     * Get Favorite Destination Account by Id
     * 
     * @param id of the Destination Account
     * @return Destination Account Data
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CommonResponse findFavById(@PathVariable Long id) {
	log.debug("Finding Favorite data by id " + id);

	CommonResponse response = new CommonResponse();

	Optional<ReceiverInfo> findbyId = favService.findFavById(id);

	if (!findbyId.isPresent()) {
	    log.error("Data Favorite is empty! ");
	    response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
	    response.setMessage(messageUtil.get("data.not.found", servletRequest.getLocale()));
	} else {

	    response.setData(findbyId);
	}

	return response;
    }

    /**
     * Delete Favorite Destination Account By Id
     * 
     * @param id of the Account
     * 
     * @return Deleted Status
     */
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CommonResponse deleteById(@PathVariable Long id) {
	log.debug("Delete Favorite by id " + id);
	CommonResponse response = new CommonResponse();
	Optional<ReceiverInfo> findById = favService.findFavById(id);
	if (!findById.isPresent()) {
	    log.error("Data Favorite is empty! ");
	    response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
	    response.setMessage(messageUtil.get("data.not.found", servletRequest.getLocale()));
	} else {
	    log.debug("Deleting data Favorite with id " + id);
	    ReceiverInfo fav = findById.get();
	    fav.setSave(false);
	    fav.setCounter(0);
	    
	    response.setCode(ResponseMessage.SUCCESS.getCode());
	    response.setMessage(messageUtil.get("success", servletRequest.getLocale()));
	    
	    receiverService.saveFavorite(fav);
	}

	return response;
    }
    
    /**
     * GetMostFrequent
     * @param request
     * @return
     * @throws IOException 
     */
    @SuppressWarnings("unchecked")
    @GetMapping("/mostFrequent/{username}")
    public CommonResponse mostFrequent(@PathVariable String username) throws IOException {
	log.debug("REST get most frequent...");
	CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(), messageUtil.get("success", servletRequest.getLocale()));

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
	
	// Query Most Frequent
	List<ReceiverInfo> most = receiverService.findMostFrequent(decryptedUsername);
	if(most==null || most.isEmpty()) {
	    // set empty object
	    most = new ArrayList<ReceiverInfo>();
	    // set error message
	    response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
	    response.setMessage(messageUtil.get("data.not.found", servletRequest.getLocale()));
	} else {
	    // limit favourite
	    int limitFav = Integer.valueOf(env.getProperty("config.max.most.frequent"));
	    most = most.stream().limit(limitFav).collect(Collectors.toList());
	    // remove unnecessary field
	    most.forEach(ri -> {
		ri.setUsername(null);
	    });
	}
	
	response.setData(most);
	return response;
    }
}
