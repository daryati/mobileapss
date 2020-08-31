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

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import id.co.asyst.bukopin.mobile.common.core.service.impl.BkpmService;
import id.co.asyst.bukopin.mobile.common.core.util.BkpmUtil;
import id.co.asyst.bukopin.mobile.common.core.util.MessageUtil;
import id.co.asyst.bukopin.mobile.common.model.BkpmConstants;
import id.co.asyst.bukopin.mobile.common.model.ResponseMessage;
import id.co.asyst.bukopin.mobile.common.model.payload.CommonRequest;
import id.co.asyst.bukopin.mobile.common.model.payload.CommonResponse;
import id.co.asyst.bukopin.mobile.master.core.service.DestinationService;
import id.co.asyst.bukopin.mobile.master.core.service.PurchaseCategoryService;
import id.co.asyst.bukopin.mobile.master.core.service.TransactionService;
import id.co.asyst.bukopin.mobile.master.model.TransactionTypeEnum;
import id.co.asyst.bukopin.mobile.master.model.entity.Destination;
import id.co.asyst.bukopin.mobile.master.model.entity.PurchaseCategory;
import id.co.asyst.bukopin.mobile.master.model.entity.Transaction;
import id.co.asyst.bukopin.mobile.master.model.payload.DestinationCommonRequest;
import id.co.asyst.bukopin.mobile.master.model.payload.FavoriteResponse;
import id.co.asyst.bukopin.mobile.master.web.rest.util.DestinationUtil;
import id.co.asyst.bukopin.mobile.service.core.UserModuleService;
import id.co.asyst.bukopin.mobile.user.model.entity.User;
import id.co.asyst.bukopin.mobile.user.model.payload.VerifyPhoneOwnerRequest;
import id.co.asyst.foundation.service.connector.Services;
import io.netty.util.internal.StringUtil;

/**
 * REST Controller for Favorite Destination Purchase Receiver
 * 
 * @author Ihsan Firman
 * @version $Revision$, Dec 7, 2019
 * @since 2.0
 */
@RestController
@RequestMapping("/destination")
public class DestinationController {
	/* Constants: */

	private Logger log = LoggerFactory.getLogger(DestinationController.class);
	private static final int FAVORITE_MAX = 10;

	private static final String PULSADATA = "1";
	private static final String LISTRIK = "2";
	private static final String KARTU_KREDIT = "3";
	private static final String TELEPON = "4";
	private static final String EMONEY = "5";
	private static final String ASURANSI = "6";
	private static final String FLAG_SUBSCRIBER_NUMBER = "1";
	private static final String FLAG_METER_NUMBER = "0";
	private static final String PLN_PRE_TYPE = "PLNPRE";
	private static final String DEST_LISTRIK = "LISTRIK";
	private ObjectMapper oMapper = new ObjectMapper();
	private static final String NOTE_ID_PLNPRE = "BELI PLN ";
	private static final String NOTE_EN_PLNPRE = "PURC PLN ";
	private static final String NOTE_ID_PLNPOST = "BAYAR PLN ";
	private static final String NOTE_EN_PLNPOST = "PAY PLN ";
	private static final String NOTE_ID_EMONEY = "BELI EMONEY ";
	private static final String NOTE_EN_EMONEY = "PURC EMONEY ";
	private static final String NOTE_ID_TELCOPRE = "BELI PULSA/PAKET DATA ";
	private static final String NOTE_EN_TELCOPRE = "PURC PULSA/DATA ";
	private static final String NOTE_ID_TELCOPOST = "BAYAR TGHN ";
	private static final String NOTE_EN_TELCOPOST = "PAY TELCO ";
	private static final String NOTE_ID_INSURANCEPOST = "BAYAR ASURANSI ";
	private static final String NOTE_EN_INSURANCEPOST = "PAY INSURANCE ";
	private static final String NOTE_ID_CREDITCARDPOST = "BAYAR KARTU KREDIT ";
	private static final String NOTE_EN_CREDITCARDPOST = "PAY CREDIT CARD ";
	private static final String NOTE_ID_SAMOLNAS = "BAYAR SAMOLNAS ";
	private static final String NOTE_EN_SAMOLNAS = "PAY SAMOLNAS ";

	/* Attributes: */

	@Autowired
	DestinationService destinationService;

	@Autowired
	TransactionService transactionService;

	@Autowired
	PurchaseCategoryService categoryService;

	@Autowired
	private MessageUtil messageUtil;

	@Autowired
	private HttpServletRequest servletRequest;

	@Autowired
	private Environment env;

	/**
	 * Bkpm Common Service
	 */
	@Autowired
	private BkpmService commonService;

	/* Transient Attributes: */

	/* Constructors: */
	public DestinationController(DestinationService destinationService) {
		this.destinationService = destinationService;
	}

	/* Getters & setters for attributes: */

	/* Getters & setters for transient attributes: */

	/* Functionalities: */

	/**
	 * Update Destination Purchase
	 * 
	 * @param request include id of the Destination, categoryId, subscriberNumber,
	 *                isFavorite and alias
	 * @return Updating data of Destination
	 */
	@PutMapping("/updateDestination")
	@ResponseStatus(HttpStatus.OK)
	public CommonResponse updateDestination(@Valid @RequestBody CommonRequest<Destination> request) {
		CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(),
				messageUtil.get("success", servletRequest.getLocale()));
//	Optional<PurchaseCategory> category = categoryService.findById(request.getData().getCategoryId());

		String locale = servletRequest.getLocale().toString();

		Optional<Destination> oldData = destinationService.findById(request.getData().getId());

		if (!oldData.isPresent()) {
			log.debug("DATA NOT FOUND....");
			response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
			response.setMessage(messageUtil.get("error.data.not.found", servletRequest.getLocale()));
		} else {
//	    oldData.get().setCategory(category.get());
			oldData.get().setAlias(request.getData().getAlias());
//	    oldData.get().setSubscriberNumber(request.getData().getSubscriberNumber());
			oldData.get().setIsFavorite(request.getData().getIsFavorite());

			destinationService.save(oldData.get());

			if (locale.equals("in")) {

				oldData.get().getCategory().setEnglishName(null);
			} else {
				String englishName = oldData.get().getCategory().getEnglishName();

				oldData.get().getCategory().setEnglishName(null);
				oldData.get().getCategory().setName(englishName);
			}

			response.setData(oldData.get());

			log.debug("DATA SUCCESSFULLY UPDATED!");
		}
		return response;
	}

	/**
	 * Delete Favorite Destination by id Destination
	 * 
	 * @param request include id Destination as mandatory field
	 * @return Updating status isFavorite to be false
	 */
	@DeleteMapping("/deleteDestination")
	@ResponseStatus(HttpStatus.OK)
	public CommonResponse deleteById(@Valid @RequestBody CommonRequest<Destination> request) {
		CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(),
				messageUtil.get("success", servletRequest.getLocale()));

		Optional<Destination> oldData = destinationService.findById(request.getData().getId());
		if (!oldData.isPresent()) {
			log.debug("DATA NOT FOUND....");
			response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
			response.setMessage(messageUtil.get("error.data.not.found", servletRequest.getLocale()));
		} else {
			oldData.get().setIsFavorite(false);
			destinationService.save(oldData.get());
			log.debug("DATA SUCCESSFULLY DELETED...");
		}

		return response;
	}

	/**
	 * Find Favorite Destination By Destination Id
	 * 
	 * @param request include id of Destination as mandatory
	 * @return Single data of Destination Purchase Receiver
	 */
	@PostMapping("/findById")
	@ResponseStatus(HttpStatus.OK)
	public CommonResponse findById(@Valid @RequestBody CommonRequest<Destination> request) {
		CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(),
				messageUtil.get("success", servletRequest.getLocale()));

		String locale = servletRequest.getLocale().toString();

		Optional<Destination> oldData = destinationService.findById(request.getData().getId());

		if (!oldData.isPresent()) {
			log.debug("DATA NOT FOUND....");
			response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
			response.setMessage(messageUtil.get("error.data.not.found", servletRequest.getLocale()));
		} else {
			if (locale.equals("in")) {

				oldData.get().getCategory().setEnglishName(null);
			} else {
				String englishName = oldData.get().getCategory().getEnglishName();

				oldData.get().getCategory().setEnglishName(null);
				oldData.get().getCategory().setName(englishName);
			}

			response.setData(oldData);
			log.debug("SHOWING DESTINATION DATA FOR ID : " + request.getData().getId());
		}

		return response;

	}

	/**
	 * Add Destination Purchase To Favorite
	 * 
	 * @param request Request body include id_destination field as required field.
	 * @return
	 */
	@PostMapping("/checkData")
	public CommonResponse checkData(@RequestBody CommonRequest<Map<String, String>> request) {
		CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(),
				messageUtil.get("success", servletRequest.getLocale()));

		String locale = servletRequest.getLocale().toString();

		// validate, id is required
		String idDestinationStr = request.getData().get("id_destination");
		if (StringUtil.isNullOrEmpty(idDestinationStr)) {
			response.setCode(ResponseMessage.INVALID_INPUT.getCode());
			response.setMessage(messageUtil.get("error.validation.required", new Object[] { "ID Destination" },
					servletRequest.getLocale()));
			return response;
		}

		// find data destination by Id
		Long idDestination = Long.valueOf(idDestinationStr);
		Destination destination = destinationService.findById(idDestination).orElse(null);
		if (destination == null) {
			// data not found
			response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
			response.setMessage(messageUtil.get("error.data.not.found", servletRequest.getLocale()));
			return response;
		}

		if (destination.getIsFavorite()) {
			// data already set as favorite
			response.setCode(ResponseMessage.INPUT_ALREADY_ADDED.getCode());
			response.setMessage(messageUtil.get("favorite.already.add", servletRequest.getLocale()));
			return response;
		} else {
			// get favorite by user and category
			List<Destination> favs = destinationService.getFavoriteByUserAndCategory(
					destination.getUser().getUsername(), destination.getCategory().getIdCategory());
			log.debug("Already have Favorite List : " + favs.size());
			if (favs != null && favs.size() >= FAVORITE_MAX) {
				// max favs
				log.error("MAXIMUM LIMIT EXCEEDED....");
				response.setCode(ResponseMessage.DATA_OVER_LIMIT.getCode());
				response.setMessage(messageUtil.get("favorite.max", servletRequest.getLocale()));
				return response;
			}
		}
		// set response
		Destination responseData = DestinationUtil.generateAddFavResponse(destination);

		if(locale.equals("in")) {
			
			responseData.getCategory().setEnglishName(null);
		}else {
			String englishName = responseData.getCategory().getEnglishName();
			
			responseData.getCategory().setEnglishName(null);
			responseData.getCategory().setName(englishName);
		}

		response.setData(responseData);

		return response;
	}

	/**
	 * Find all Favorite Destination by username
	 * 
	 * @param request include username as the mandatory field
	 * @return List of Favorite Destination
	 * 
	 * @throws IOException
	 */
	@PostMapping("/findAll")
	@ResponseStatus(HttpStatus.OK)
	public CommonResponse findByUserId(@RequestBody CommonRequest<Destination> request) throws IOException {
		CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(),
				messageUtil.get("success", servletRequest.getLocale()));

		String locale = servletRequest.getLocale().toString();

		if (!commonService.verifyLocalIp(servletRequest)) {
			// Validate Token and Phone Owner
			CommonRequest<VerifyPhoneOwnerRequest> phoneReq = new CommonRequest<>();
			VerifyPhoneOwnerRequest phoneReqData = new VerifyPhoneOwnerRequest();
			phoneReqData.setUsername(request.getData().getUserName());
			phoneReqData.setToken(servletRequest.getHeader(HttpHeaders.AUTHORIZATION));
			phoneReqData.setPhoneIdentity(servletRequest.getHeader(BkpmConstants.HTTP_HEADER_DEVICE_ID));
			phoneReq.setData(phoneReqData);
			CommonResponse resPhone = Services.create(UserModuleService.class).verifyPhoneOwner(phoneReq).execute()
					.body();
			if (!ResponseMessage.SUCCESS.getCode().equals(resPhone.getCode())) {
				log.error("Validate Token and Phone owner error..");
				return resPhone;
			}
		}

		CommonResponse findUserByUsername = Services.create(UserModuleService.class)
				.getUserIdByUsername(request.getData().getUserName()).execute().body();
		int userId = (int) findUserByUsername.getData();
		long id = Long.parseLong(String.valueOf(userId));

		List<PurchaseCategory> categories = categoryService.findAll();
		List<Destination> destinations = destinationService.findByUserId(id);

		if (destinations.isEmpty()) {
			log.error("DATA IS EMPTY....");
			response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
			response.setMessage(messageUtil.get("error.data.not.found", servletRequest.getLocale()));
			return response;
		}

		List<FavoriteResponse> respons = new ArrayList<FavoriteResponse>();

		for (int i = 0; i < categories.size(); i++) {
			FavoriteResponse fav = new FavoriteResponse();
			fav.setId_category(categories.get(i).getIdCategory().toString());
			if (locale.equals("in")) {
				fav.setCategory_name(categories.get(i).getName());
			} else {
				fav.setCategory_name(categories.get(i).getEnglishName());
			}

			respons.add(fav);

			List<Destination> data = new ArrayList<Destination>();

			for (int index = 0; index < destinations.size(); index++) {
				Destination destResponse = DestinationUtil.generateGetFavResponse(destinations.get(index));

				String idFavorite = destinations.get(index).getCategory().getIdCategory().toString();
				String idCategory = categories.get(i).getIdCategory().toString();
				if (idFavorite.equalsIgnoreCase(idCategory)) {
					data.add(destResponse);
				}
				respons.get(i).setCategory(data);
			}

		}

		response.setData(respons);

		return response;
	}

	/**
	 * save to Destination (but not set as Favorite)
	 * 
	 * @param PLN purchase prepaid Response
	 * @return Success Code and Result Destination saved Data
	 * @throws IOException
	 * @Deprecated move to saveDestinationCommon
	 */
	@SuppressWarnings("unchecked")
//    @PostMapping("/saveToDestination")
	@ResponseStatus(HttpStatus.OK)
	@Deprecated
	public CommonResponse saveDestination(@RequestBody Map<String, Object> req) {
		CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(),
				messageUtil.get("success", servletRequest.getLocale()));

		ObjectMapper oMapper = new ObjectMapper();
		Map<String, String> resPln = oMapper.convertValue(req.get("purchase"), Map.class);
		log.debug(BkpmUtil.convertToJson(resPln));

		Optional<PurchaseCategory> findCategory = categoryService.findById(Long.parseLong(LISTRIK));
		if (null == findCategory) {
			log.error("Category not Found");
			response.setCode(ResponseMessage.INTERNAL_SERVER_ERROR.getCode());
			response.setMessage(messageUtil.get("error.internal.server", servletRequest.getLocale()));
			return response;
		}

		CommonResponse findUser = new CommonResponse();
		try {
			findUser = Services.create(UserModuleService.class).getUserByUsername(resPln.get("username")).execute()
					.body();
		} catch (IOException e) {
			log.error(e.getMessage(), e);
			response.setCode(ResponseMessage.INTERNAL_SERVER_ERROR.getCode());
			response.setMessage(messageUtil.get("error.internal.server", servletRequest.getLocale()));
			return response;
		}

		if (null == findUser) {
			log.error("user not found");
			response.setCode(ResponseMessage.INTERNAL_SERVER_ERROR.getCode());
			response.setMessage(messageUtil.get("error.internal.server", servletRequest.getLocale()));
			return response;
		}

		String number;
		if (resPln.get("flag").equalsIgnoreCase(FLAG_SUBSCRIBER_NUMBER)) {
			number = resPln.get("subscriberID");
		} else {
			number = resPln.get("meterSerialNumber");
		}

		log.debug("find user by username " + resPln.get("username"));
		Map<String, Object> res = oMapper.convertValue(findUser.getData(), Map.class);
		User userMap = oMapper.convertValue(res.get("user"), User.class);
		log.debug(BkpmUtil.convertToJson(userMap));

		Destination findDest = destinationService.findBySubNumAndUserId(userMap.getId(), number);
		Date now = new Date();

		try {
			if (null == findDest) {
				log.info("Destination not exist");
				Destination saveDest = new Destination();
				saveDest.setSubscriberNumber(number);
				saveDest.setCategory(findCategory.get());
				saveDest.setIsFavorite(false);
				saveDest.setSubscriberName(resPln.get("subscriberName"));
				saveDest.setDestinationType(DEST_LISTRIK);
				saveDest.setUser(userMap);

				log.info("save purchase to Destination");
				findDest = destinationService.save(saveDest);
			}

			// save transaction
			Transaction transaction = new Transaction();
			transaction.setCreatedDate(now);
			transaction.setDestination(findDest);
			transaction.setType(PLN_PRE_TYPE);
			transaction.setRefNumber(resPln.get("reference"));
			transaction.setUser(userMap);

			log.info("save purchase to Transaction");
			transaction = transactionService.save(transaction);

//	    Map<String, Object> result = new HashMap<>();
//	    result.put("transaction", transaction);
			response.setData(transaction);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			response.setCode(ResponseMessage.INTERNAL_SERVER_ERROR.getCode());
			response.setMessage(messageUtil.get("error.internal.server", servletRequest.getLocale()));
		}

		return response;
	}

	/**
	 * Save to Destination (but not set as Favorite) <br>
	 * This service is general. It can be used to save all of Transaction.
	 * 
	 * @param destination to be create
	 * @return Response status and Destination's data
	 */
	@SuppressWarnings("unchecked")
	@PostMapping("/saveToDestinationCommon")
	@ResponseStatus(HttpStatus.OK)
	public CommonResponse saveToDestinationCommon(@Valid @RequestBody CommonRequest<DestinationCommonRequest> request) {
		CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(),
				messageUtil.get("success", servletRequest.getLocale()));

		String username = request.getData().getUsername();
		long categoryId = request.getData().getCategoryId();
		String destinationType = request.getData().getDestinationType();
		String transactionType = request.getData().getTransactionType();
		String subNumber = request.getData().getSubscriberNumber();
		String subName = request.getData().getSubscriberName();
		String ref = request.getData().getReference();
		String accountNumber = request.getData().getAccountNumber();
		BigDecimal totalAmount = request.getData().getTotalAmount();

		PurchaseCategory findCategory = categoryService.findById(categoryId).orElse(null);
		if (null == findCategory) {
			log.error("Category not Found");
			response.setCode(ResponseMessage.INTERNAL_SERVER_ERROR.getCode());
			response.setMessage(messageUtil.get("error.internal.server", servletRequest.getLocale()));
			return response;
		}

		CommonResponse findUser = new CommonResponse();
		try {
			findUser = Services.create(UserModuleService.class).getUserByUsername(username).execute().body();
		} catch (IOException e) {
			log.error(e.getMessage(), e);
			response.setCode(ResponseMessage.INTERNAL_SERVER_ERROR.getCode());
			response.setMessage(messageUtil.get("error.internal.server", servletRequest.getLocale()));
			return response;
		}

		if (null == findUser) {
			log.error("user not found");
			response.setCode(ResponseMessage.INTERNAL_SERVER_ERROR.getCode());
			response.setMessage(messageUtil.get("error.internal.server", servletRequest.getLocale()));
			return response;
		}

		log.debug("find user by username " + username);
		Map<String, Object> res = oMapper.convertValue(findUser.getData(), Map.class);
		User userMap = oMapper.convertValue(res.get("user"), User.class);
		log.debug(BkpmUtil.convertToJson(userMap));

		Destination findDest = destinationService.findUserDestinationType(userMap.getId(), subNumber, destinationType);
		Date now = new Date();

		try {
			if (null == findDest) {
				log.info("Destination not exist");
				Destination saveDest = new Destination();
				saveDest.setSubscriberNumber(subNumber);
				saveDest.setCategory(findCategory);
				saveDest.setIsFavorite(false);
				saveDest.setSubscriberName(subName);
				saveDest.setDestinationType(destinationType);
				saveDest.setUser(userMap);

				log.info("Save to Destination");
				findDest = destinationService.save(saveDest);
			}

			// save transaction
			Transaction transaction = new Transaction();
			transaction.setCreatedDate(now);
			transaction.setDestination(findDest);
			transaction.setType(transactionType);
			transaction.setRefNumber(ref);
			transaction.setUser(userMap);
			transaction.setAccountNumber(accountNumber);
			transaction.setTotalAmount(totalAmount);

			// set note id and note en
			if (TransactionTypeEnum.PLNPRE.name().equalsIgnoreCase(transactionType)) {
				transaction.setNoteId(NOTE_ID_PLNPRE.concat(subNumber));
				transaction.setNoteEn(NOTE_EN_PLNPRE.concat(subNumber));
			} else if (TransactionTypeEnum.PLNPOST.name().equalsIgnoreCase(transactionType)) {
				transaction.setNoteId(NOTE_ID_PLNPOST.concat(subNumber));
				transaction.setNoteEn(NOTE_EN_PLNPOST.concat(subNumber));
			} else if (TransactionTypeEnum.EMONEY.name().equalsIgnoreCase(transactionType)) {
				transaction.setNoteId(NOTE_ID_EMONEY.concat(subNumber));
				transaction.setNoteEn(NOTE_EN_EMONEY.concat(subNumber));
			} else if (TransactionTypeEnum.TELCOPRE.name().equalsIgnoreCase(transactionType)) {
				transaction.setNoteId(NOTE_ID_TELCOPRE.concat(subNumber));
				transaction.setNoteEn(NOTE_EN_TELCOPRE.concat(subNumber));
			} else if (TransactionTypeEnum.TELCOPOST.name().equalsIgnoreCase(transactionType)) {
				transaction.setNoteId(NOTE_ID_TELCOPOST.concat(subNumber));
				transaction.setNoteEn(NOTE_EN_TELCOPOST.concat(subNumber));
			} else if (TransactionTypeEnum.INSURANCE.name().equalsIgnoreCase(transactionType)) {
				transaction.setNoteId(NOTE_ID_INSURANCEPOST.concat(subNumber));
				transaction.setNoteEn(NOTE_EN_INSURANCEPOST.concat(subNumber));
			} else if (TransactionTypeEnum.CREDITCARD.name().equalsIgnoreCase(transactionType)) {
				transaction.setNoteId(NOTE_ID_CREDITCARDPOST.concat(subNumber));
				transaction.setNoteEn(NOTE_EN_CREDITCARDPOST.concat(subNumber));
			} else if (TransactionTypeEnum.SAMOLNAS.name().equalsIgnoreCase(transactionType)) {
				transaction.setNoteId(NOTE_ID_SAMOLNAS.concat(subNumber));
				transaction.setNoteEn(NOTE_EN_SAMOLNAS.concat(subNumber));
			}

			log.info("Save to Transaction");
			transaction = transactionService.save(transaction);

			response.setData(transaction);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			response.setCode(ResponseMessage.INTERNAL_SERVER_ERROR.getCode());
			response.setMessage(messageUtil.get("error.internal.server", servletRequest.getLocale()));
		}

		return response;
	}

	/* Overrides: */
}
