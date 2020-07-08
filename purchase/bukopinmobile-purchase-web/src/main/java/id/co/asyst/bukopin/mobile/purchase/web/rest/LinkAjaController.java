/*
 * $Id$
 * 
 * Copyright (c) 2020 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.purchase.web.rest;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import id.co.asyst.bukopin.mobile.common.core.exception.MiddlewareException;
import id.co.asyst.bukopin.mobile.common.core.util.BkpmUtil;
import id.co.asyst.bukopin.mobile.common.core.util.MessageUtil;
import id.co.asyst.bukopin.mobile.common.model.BkpmConstants;
import id.co.asyst.bukopin.mobile.common.model.ResponseMessage;
import id.co.asyst.bukopin.mobile.common.model.payload.CommonRequest;
import id.co.asyst.bukopin.mobile.common.model.payload.CommonResponse;
import id.co.asyst.bukopin.mobile.common.model.payload.Identity;
import id.co.asyst.bukopin.mobile.master.model.CategoryEnum;
import id.co.asyst.bukopin.mobile.master.model.DestinationTypeEnum;
import id.co.asyst.bukopin.mobile.master.model.TransactionTypeEnum;
import id.co.asyst.bukopin.mobile.master.model.entity.Transaction;
import id.co.asyst.bukopin.mobile.master.model.payload.DestinationCommonRequest;
import id.co.asyst.bukopin.mobile.purchase.core.service.EMoneyService;
import id.co.asyst.bukopin.mobile.purchase.core.service.EMoneyTransService;
import id.co.asyst.bukopin.mobile.purchase.core.util.EMoneyUtils;
import id.co.asyst.bukopin.mobile.purchase.model.entity.EMoney;
import id.co.asyst.bukopin.mobile.purchase.model.payload.InquiryLinkAjaRequest;
import id.co.asyst.bukopin.mobile.purchase.model.payload.InquiryLinkAjaResponse;
import id.co.asyst.bukopin.mobile.purchase.model.payload.PurchaseEMoneyResponse;
import id.co.asyst.bukopin.mobile.purchase.model.payload.PurchaseLinkAjaRequest;
import id.co.asyst.bukopin.mobile.purchase.model.payload.PurchaseLinkAjaResponse;
import id.co.asyst.bukopin.mobile.service.core.EMoneyModuleService;
import id.co.asyst.bukopin.mobile.service.core.MasterModuleService;
import id.co.asyst.bukopin.mobile.service.core.UserModuleService;
import id.co.asyst.bukopin.mobile.service.model.payload.emoney.EMoneyInquiryResponse;
import id.co.asyst.bukopin.mobile.service.model.payload.emoney.LinkAjaInquiryRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.emoney.LinkAjaInquiryResponse;
import id.co.asyst.bukopin.mobile.service.model.payload.emoney.LinkAjaInquiryResult;
import id.co.asyst.bukopin.mobile.service.model.payload.emoney.LinkAjaPurchaseRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.emoney.LinkAjaPurchaseResponse;
import id.co.asyst.bukopin.mobile.service.model.payload.pln.GetVerifyPINRequest;
import id.co.asyst.bukopin.mobile.user.model.payload.VerifyAccountOwnerRequest;
import id.co.asyst.bukopin.mobile.user.model.payload.VerifyAccountOwnerResponse;
import id.co.asyst.bukopin.mobile.user.model.payload.VerifyPhoneOwnerRequest;
import id.co.asyst.foundation.service.connector.Services;

/**
 * REST Controller of Link Aja
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Jan 23, 2020
 * @since 1.0.Alpha1-SNAPSHOT
 */
@RestController
@RequestMapping("/EMoney")
public class LinkAjaController {

	/* Logger */
	private final Logger log = LoggerFactory.getLogger(LinkAjaController.class);

	/* Constants: */
	private static final String SUCCESS_CODE = "000";
	private static final String ARANET_TIME_OUT_CODE = "999";
	private static final String CUSTNO_BLOCKED = "111";
	private static final String INVALID_AMOUNT = "113";
	private static final String INVALID_SUBSCRIBER_ID = "114";
	private static final String TRANSACTION_PROCESSING = "168";
	private static final String SYSTEM_BROKEN_168 = "168";
	private static final String SYSTEM_BROKEN_169 = "169";
	private static final String BILL_ALREADY_PAID = "188";
	private static final String EMONEY_ACCOUNT_INACTIVE = "839";
	private static final String EMONEY_NOT_ENOUGH_BALANCE = "851";
	private static final BigDecimal MINIMUM_LINKAJA_AMOUNT = new BigDecimal("10000");
	private static final BigDecimal MAXIMUM_LINKAJA_AMOUNT = new BigDecimal("2000000");

	// giro error handling
	private static final String GIRO_AMOUNT_NOT_ENOUGH_BALANCE = "805";
	private static final String GIRO_LIMIT_TRANSFER = "802";
	private static final String GIRO_ACCOUNT_WAS_BLOCKED = "806";
	private static final String GIRO_OVER_LIMIT = "808";
	private static final String GIRO_ACCOUNT_BLOCKED = "814";
	private static final String GIRO_CUT_OFF = "818";
	private static final String GIRO_INACTIVE_ACCOUNT = "822";
	private static final String GIRO_USER_NOT_FOUND = "831";
	private static final String GIRO_DUPLICATE_DATA = "869";
	private static final String GIRO_CLOSED_ACCOUNT = "878";
	private static final String GIRO_ERROR_VALUTA_CODE = "885";
	private static final String GIRO_LIMITED_BALANCE = "897";

	private static final String EMONEY_TEMPLATE_NAME = "html/EMoneytemplate";

	/* Attributes: */
	@Autowired
	private MessageUtil messageUtil;

	@Autowired
	private HttpServletRequest servletRequest;

	/**
	 * Environment
	 */
	@Autowired
	private Environment env;

	/**
	 * Emoney Service
	 */
	@Autowired
	private EMoneyService eMoneyService;

	/**
	 * EMoney Transactional service
	 */
	@Autowired
	private EMoneyTransService eMoneyTransService;

	/* Transient Attributes: */

	/* Constructors: */

	/* Getters & setters for attributes: */

	/* Getters & setters for transient attributes: */

	/* Functionalities: */
	/**
	 * POST /purchase: Purchasing Link Aja
	 * 
	 * @return The response with status 200 (OK) and with body transaction summary.
	 * @throws IOException
	 */
	@PostMapping("/purchase/LINKAJA")
	@ResponseStatus(HttpStatus.OK)
	private CommonResponse purchase(@RequestBody CommonRequest<PurchaseLinkAjaRequest> request) throws IOException {
		log.debug("REST request to Purchasing Link Aja : {}", BkpmUtil.convertToJson(request.getData()));
		CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(),
				messageUtil.get("success", servletRequest.getLocale()));

		// Validate Token and Phone Owner
		CommonRequest<VerifyPhoneOwnerRequest> phoneReq = new CommonRequest<>();
		VerifyPhoneOwnerRequest phoneReqData = new VerifyPhoneOwnerRequest();
		phoneReqData.setUsername(request.getData().getUsername());
		phoneReqData.setToken(servletRequest.getHeader(HttpHeaders.AUTHORIZATION));
		phoneReqData.setPhoneIdentity(servletRequest.getHeader(BkpmConstants.HTTP_HEADER_DEVICE_ID));
		phoneReq.setData(phoneReqData);
		CommonResponse resPhone = Services.create(UserModuleService.class).verifyPhoneOwner(phoneReq).execute().body();
		if (!ResponseMessage.SUCCESS.getCode().equals(resPhone.getCode())) {
			log.error("Validate Token and Phone owner error..");
			return resPhone;
		}

		// verify PIN
		GetVerifyPINRequest verifyPINReqData = new GetVerifyPINRequest();
		verifyPINReqData.setUsername(request.getData().getUsername());
		verifyPINReqData.setPin(request.getData().getPin());

		CommonRequest<GetVerifyPINRequest> verifyPINReq = new CommonRequest<>();
		verifyPINReq.setIdentity(request.getIdentity());
		verifyPINReq.setData(verifyPINReqData);

		CommonResponse verifyPINRes = Services.create(UserModuleService.class).verifyPIN(
			servletRequest.getHeader(HttpHeaders.ACCEPT_LANGUAGE), verifyPINReq).execute().body();
		log.debug("Verify PIN Response {} : " + BkpmUtil.convertToJson(verifyPINRes));
		if (!ResponseMessage.SUCCESS.getCode().equals(verifyPINRes.getCode())) {

			return verifyPINRes;
		}

		// verify account
		VerifyAccountOwnerRequest verifyAccountOwnerReqData = new VerifyAccountOwnerRequest();
		verifyAccountOwnerReqData.setAccountNo(request.getData().getAccountNo());
		verifyAccountOwnerReqData.setUsername(request.getData().getUsername());

		CommonRequest<VerifyAccountOwnerRequest> verifyAccOwnerRequest = new CommonRequest<>();
		verifyAccOwnerRequest.setIdentity(request.getIdentity());
		verifyAccOwnerRequest.setData(verifyAccountOwnerReqData);

		CommonResponse verifyAccOwnerResponse = Services.create(UserModuleService.class)
				.verifyAccountOwner(verifyAccOwnerRequest).execute().body();

		if (!ResponseMessage.SUCCESS.getCode().equals(verifyAccOwnerResponse.getCode())) {
			log.error("Error while verify account owner");
			return verifyAccOwnerResponse;
		}
		ObjectMapper oMapper = new ObjectMapper();
		VerifyAccountOwnerResponse verifyAccOwnRespObj = oMapper.convertValue(verifyAccOwnerResponse.getData(),
				VerifyAccountOwnerResponse.class);
		if (!verifyAccOwnRespObj.isValid()) {
			log.error("User and Account Info didn't match: " + request.getData().getAccountNo());
			response.setCode(ResponseMessage.DATA_NOT_MATCH.getCode());
			response.setMessage(messageUtil.get("error.invalid.user.accountno", servletRequest.getLocale()));
			return response;
		}

		String accType = verifyAccOwnRespObj.getAccountInfo().getAccountType().name();
		String forwardInsCode = env.getProperty("config.emoney.forwarding-institution-code");

		// Set Request Purchase
		LinkAjaPurchaseRequest purchaseRequest = EMoneyUtils.generatePurchaseLinkAja(request.getData(), forwardInsCode,
				accType);
		log.debug("Request to aranet : " + BkpmUtil.convertToJson(purchaseRequest));

		LinkAjaPurchaseResponse purchaseResponse = Services.create(EMoneyModuleService.class)
				.linkAjaPurchase(purchaseRequest).execute().body();
		log.debug("Response from aranet : " + BkpmUtil.convertToJson(purchaseResponse));

		String codeRes = purchaseResponse.getRespayment().getResult().getElement39();
		if (SUCCESS_CODE.equals(codeRes)) {
			PurchaseEMoneyResponse resp = new PurchaseEMoneyResponse();
			resp = EMoneyUtils.purchaseLinkAjaResponse(request.getData(), purchaseResponse);

			// send email
			CommonResponse userResp = Services.create(UserModuleService.class)
					.getUserByUsername(request.getData().getUsername()).execute().body();
			if (null != userResp) {
				oMapper = new ObjectMapper();
				Map<String, Object> res = oMapper.convertValue(userResp.getData(), Map.class);
				Map<String, String> resUser = oMapper.convertValue(res.get("user"), Map.class);
				log.debug("Send email receipt LinkAja...");
				eMoneyService.sendEmailReceiptEMoney(resp, resUser, servletRequest.getLocale());
			}
			// save data
			log.debug("save purchase emoney to DB");
			CommonResponse saveRes = savePurchaseEMoney(request.getIdentity(), resp);

			if (SUCCESS_CODE.equals(saveRes.getCode())) {
				log.debug("ID DESTINATION .... " + saveRes.getData().toString());
				resp.setDestinationId(saveRes.getData().toString());
			}

			response.setCode(ResponseMessage.SUCCESS.getCode());
			response.setMessage(messageUtil.get("success", servletRequest.getLocale()));
			response.setData(resp);
			log.debug("Success purchase LinkAja...");
		} else if (EMONEY_ACCOUNT_INACTIVE.equalsIgnoreCase(codeRes)) {
			log.error("account in active");
			response.setCode(ResponseMessage.ERROR_INACTIVE_BANK_ACCOUNT.getCode());
			response.setMessage(messageUtil.get("error.inactive.bank.account", servletRequest.getLocale()));
		} else if (CUSTNO_BLOCKED.equalsIgnoreCase(codeRes)) {
			log.error("Custumer Number was blocked");
			response.setCode(ResponseMessage.CUST_BLOCKED.getCode());
			response.setMessage(messageUtil.get("error.customer.was.blocked", servletRequest.getLocale()));
		} else if (BILL_ALREADY_PAID.equalsIgnoreCase(codeRes)) {
			log.error("Bill already Paid");
			response.setCode(ResponseMessage.ERROR_BILL_ALREADY_PAID.getCode());
			response.setMessage(messageUtil.get("error.emoney.already.paid", servletRequest.getLocale()));
		} else if (INVALID_AMOUNT.equals(codeRes)) {
			log.error("Error invallid amount");
			response.setCode(ResponseMessage.INVALID_AMOUNT.getCode());
			response.setMessage(messageUtil.get("error.invalid.amount", servletRequest.getLocale()));
		} else if (EMONEY_NOT_ENOUGH_BALANCE.equals(codeRes) || GIRO_AMOUNT_NOT_ENOUGH_BALANCE.equals(codeRes)
				|| GIRO_ERROR_VALUTA_CODE.equals(codeRes) || GIRO_LIMITED_BALANCE.equals(codeRes)) {
			log.error("Not enough balance: " + request.getData().getAccountNo());
			response.setCode(ResponseMessage.AMOUNT_NOT_ENOUGH.getCode());
			response.setMessage(messageUtil.get("error.amount.not.enough", servletRequest.getLocale()));
			return response;
		} else if (TRANSACTION_PROCESSING.equals(codeRes)) {
			log.error("Error Transaction still processing");
			response.setCode(ResponseMessage.TRANSACTION_PROCESSING.getCode());
			response.setMessage(messageUtil.get("error.transaction.processing", servletRequest.getLocale()));
		} else if (SYSTEM_BROKEN_168.equals(codeRes) || SYSTEM_BROKEN_169.equals(codeRes)) {
			log.error("System error from aranet");
			response.setCode(ResponseMessage.INTERNAL_SERVER_ERROR.getCode());
			response.setMessage(messageUtil.get("error.internal.server", servletRequest.getLocale()));
		} else {
			/*
			 * log.error("Error from Aranet with code : " + codeRes);
			 * response.setCode(ResponseMessage.INTERNAL_SERVER_ERROR.getCode());
			 * response.setMessage(messageUtil.get("error.internal.server",
			 * servletRequest.getLocale()));
			 */
			log.error("Error (result) EMoney Link Aja: " + codeRes);
			throw new MiddlewareException(codeRes);
		}
		return response;
	}

	/**
	 * POST /Inquiry: Inquiry Link Aja
	 * 
	 * @return The response with status 200 (OK) and with body transaction summary.
	 * @throws IOException
	 */
	@PostMapping("/inquiry/LINKAJA")
	@ResponseStatus(HttpStatus.OK)
	private CommonResponse inquiryLinkAja(@RequestBody CommonRequest<InquiryLinkAjaRequest> req) throws IOException {
		log.debug("REST request to Purchasing Link Aja : {}", BkpmUtil.convertToJson(req.getData()));
		CommonResponse response = new CommonResponse();

		String custNo = req.getData().getCustNo();
		String amount = String.valueOf(req.getData().getAmount());

		String forwardInsCode = env.getProperty("config.emoney.forwarding-institution-code");

		// handle minimum and maximum topup
		if (req.getData().getAmount().compareTo(MINIMUM_LINKAJA_AMOUNT) == -1) {
			log.error("Amount less than minimum");
			response.setCode(ResponseMessage.INVALID_MIN_LIMIT_AMOUNT.getCode());
			response.setMessage(messageUtil.get("error.invalid.min.amount", servletRequest.getLocale()));
			return response;
		}
		if (req.getData().getAmount().compareTo(MAXIMUM_LINKAJA_AMOUNT) == 1) {
			log.error("Amount greater than minimum");
			response.setCode(ResponseMessage.INVALID_MAX_LIMIT_AMOUNT.getCode());
			response.setMessage(messageUtil.get("error.invalid.max.amount", servletRequest.getLocale()));
			return response;
		}

		LinkAjaInquiryRequest requestInquiry = EMoneyUtils.generateInquiryLinkAja(amount, custNo, forwardInsCode,
				servletRequest.getLocale().getLanguage());

		log.debug("Request inquiry LinkAja to tibco : " + BkpmUtil.convertToJson(requestInquiry));

		LinkAjaInquiryResponse responseInquiry = Services.create(EMoneyModuleService.class)
				.linkAjaInquiry(requestInquiry).execute().body();
		log.debug("Response inquiry LinkAja from tibco : " + BkpmUtil.convertToJson(responseInquiry));

		// set customer name

		String codeRes = responseInquiry.getRespayment().getResult().getElement39();
		if (codeRes.equals(SUCCESS_CODE)) {
			log.debug("Inquiry LinkAja success");

			String element48 = responseInquiry.getRespayment().getResult().getElement48();
			String[] datas = element48.split("\\|", -1);
			String custName = datas[1];

			String resAmountFee = datas[6];

			BigDecimal resAmount = req.getData().getAmount();
			BigDecimal amountFee = new BigDecimal(resAmountFee);
			BigDecimal totalAmount = resAmount.add(amountFee);

			InquiryLinkAjaResponse result = new InquiryLinkAjaResponse();
			result.setCustName(custName.trim());
			result.setCustNo(custNo);
			result.setAmount(req.getData().getAmount());
			result.setAmountFee(amountFee);
			result.setElement11(responseInquiry.getRespayment().getResult().getElement11());
			result.setElement48(element48);
			result.setTotalAmount(totalAmount);

			response.setCode(ResponseMessage.SUCCESS.getCode());
			response.setMessage(messageUtil.get("success", servletRequest.getLocale()));
			response.setData(result);

		} else if (CUSTNO_BLOCKED.equalsIgnoreCase(codeRes)) {
			log.error("Custumer Number was blocked");
			response.setCode(ResponseMessage.CUST_BLOCKED.getCode());
			response.setMessage(messageUtil.get("error.customer.was.blocked", servletRequest.getLocale()));
		} else if (EMONEY_ACCOUNT_INACTIVE.equalsIgnoreCase(codeRes) || GIRO_INACTIVE_ACCOUNT.equalsIgnoreCase(codeRes)
				|| GIRO_CLOSED_ACCOUNT.equalsIgnoreCase(codeRes)) {
			log.error("account inactive");
			response.setCode(ResponseMessage.ERROR_INACTIVE_BANK_ACCOUNT.getCode());
			response.setMessage(messageUtil.get("error.inactive.bank.account", servletRequest.getLocale()));
		} else if (GIRO_LIMIT_TRANSFER.equals(codeRes) || GIRO_OVER_LIMIT.equals(codeRes)) {
			log.error("exceed limit");
			response.setCode(ResponseMessage.LIMIT_TRANSFER_DAY.getCode());
			response.setMessage(messageUtil.get("error.exceed.limit", servletRequest.getLocale()));
		} else if (GIRO_CUT_OFF.equals(codeRes)) {
			log.error("Giro cut off");
			response = new CommonResponse();
			response.setCode(ResponseMessage.ERROR_CUT_OFF_PLN.getCode());
			response.setMessage(messageUtil.get("error.cutoff.pln", servletRequest.getLocale()));
		} else if (GIRO_DUPLICATE_DATA.equals(codeRes)) {
			log.error("Giro Duplicate Data");
			response = new CommonResponse();
			response.setCode(ResponseMessage.DUPLICATE_DATA.getCode());
			response.setMessage(messageUtil.get("error.duplicate.data", servletRequest.getLocale()));
		} else if (GIRO_ACCOUNT_WAS_BLOCKED.equals(codeRes) || GIRO_ACCOUNT_BLOCKED.equals(codeRes)) {
			log.error("account wass blocked");
			response.setCode(ResponseMessage.CUST_BLOCKED.getCode());
			response.setMessage(messageUtil.get("error.customer.was.blocked", servletRequest.getLocale()));
		} else if (INVALID_AMOUNT.equals(codeRes)) {
			log.error("Error invallid amount");
			response.setCode(ResponseMessage.INVALID_AMOUNT.getCode());
			response.setMessage(messageUtil.get("error.invalid.amount", servletRequest.getLocale()));
		} else if (INVALID_SUBSCRIBER_ID.equals(codeRes) || GIRO_USER_NOT_FOUND.equalsIgnoreCase(codeRes)) {
			log.error("Error invalid number");
			response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
			response.setMessage(messageUtil.get("error.id.emoney.not.found", servletRequest.getLocale()));
		} else if (BILL_ALREADY_PAID.equalsIgnoreCase(codeRes)) {
			log.error("Bill already Paid");
			response.setCode(ResponseMessage.ERROR_BILL_ALREADY_PAID.getCode());
			response.setMessage(messageUtil.get("error.emoney.already.paid", servletRequest.getLocale()));
		} else if (SYSTEM_BROKEN_168.equals(codeRes) || SYSTEM_BROKEN_169.equals(codeRes)) {
			log.error("System error from aranet");
			response.setCode(ResponseMessage.INTERNAL_SERVER_ERROR.getCode());
			response.setMessage(messageUtil.get("error.internal.server", servletRequest.getLocale()));
		} else {
			// TODO throw middleware exception
			log.error("Error from Aranet with code : " + codeRes);
			response.setCode(ResponseMessage.INTERNAL_SERVER_ERROR.getCode());
			response.setMessage(messageUtil.get("error.internal.server", servletRequest.getLocale()));
		}
		return response;
	}

	/**
	 * Save the purchase to DB
	 * 
	 * @param identity
	 * @param resEMoney
	 * @return The response with status 200 (OK)
	 */
	public CommonResponse savePurchaseEMoney(Identity identity, PurchaseEMoneyResponse resEMoney) {
		CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(),
				messageUtil.get("success", servletRequest.getLocale()));
		try {

			// Prepare data request to save transaction
			DestinationCommonRequest dataReq = new DestinationCommonRequest();
			dataReq.setCategoryId(CategoryEnum.E_MONEY.getId());
			dataReq.setUsername(resEMoney.getUsername());
			dataReq.setSubscriberNumber(resEMoney.getCustomerNumber());
			dataReq.setSubscriberName(resEMoney.getCustomerName());
			dataReq.setTransactionType(TransactionTypeEnum.EMONEY.name());
			dataReq.setReference(resEMoney.getReference());
			dataReq.setAccountNumber(resEMoney.getAccountNumber());
			dataReq.setTotalAmount(resEMoney.getTotalAmount());

			// Call service to save Destination/ Favorite
			CommonRequest<DestinationCommonRequest> destinationReq = new CommonRequest<>();
			destinationReq.setIdentity(identity);
			destinationReq.setData(dataReq);
			dataReq.setDestinationType(DestinationTypeEnum.PRELINKAJA.name());

			CommonResponse resSaveFav = Services.create(MasterModuleService.class).saveToFavouriteCommon(destinationReq)
					.execute().body();
			if (null != resSaveFav) {
				if (!SUCCESS_CODE.equals(resSaveFav.getCode())) {
					log.error("Save to favourite Failed");
					response.setCode(ResponseMessage.INTERNAL_SERVER_ERROR.getCode());
					response.setMessage(messageUtil.get("error.internal.server", servletRequest.getLocale()));
				} else {
					ObjectMapper oMapper = new ObjectMapper();
					Transaction transaction = oMapper.convertValue(resSaveFav.getData(), Transaction.class);
					log.debug("create");
					EMoney emoney = new EMoney();
					emoney.setAmount(resEMoney.getAmount());
					emoney.setAmountFee(resEMoney.getAmountFee());
					emoney.setCustomerNumber(resEMoney.getCustomerNumber());
					emoney.setTotalAmount(resEMoney.getTotalAmount());
					emoney.setTypeEMoney(resEMoney.getType());
					emoney.setTransaction(transaction);
					emoney.setDestination(emoney.getTransaction().getDestination());

					log.debug("save purchase to EMONEY");
					eMoneyTransService.save(emoney);
					log.debug("save OK.");
					response.setData(emoney.getTransaction().getDestination().getId());
				}
			}
		} catch (IOException e) {
			log.error(e.getMessage(), e);
			response.setCode(ResponseMessage.INTERNAL_SERVER_ERROR.getCode());
			response.setMessage(messageUtil.get("error.internal.server", servletRequest.getLocale()));
		}

		return response;
	}
	/* Overrides: */
}
