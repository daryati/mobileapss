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
import java.net.URISyntaxException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import id.co.asyst.bukopin.mobile.common.core.exception.DataNotMatchException;
import id.co.asyst.bukopin.mobile.common.core.exception.MiddlewareException;
import id.co.asyst.bukopin.mobile.common.core.util.CryptoUtil;
import id.co.asyst.bukopin.mobile.common.core.util.MessageUtil;
import id.co.asyst.bukopin.mobile.common.model.BkpmConstants;
import id.co.asyst.bukopin.mobile.common.model.ResponseMessage;
import id.co.asyst.bukopin.mobile.common.model.SystemCutOffEnum;
import id.co.asyst.bukopin.mobile.common.model.payload.CommonRequest;
import id.co.asyst.bukopin.mobile.common.model.payload.CommonResponse;
import id.co.asyst.bukopin.mobile.service.core.MasterModuleService;
import id.co.asyst.bukopin.mobile.service.core.UserModuleService;
import id.co.asyst.bukopin.mobile.service.model.payload.pln.GetVerifyPINRequest;
import id.co.asyst.bukopin.mobile.transfer.core.service.DailyLimitService;
import id.co.asyst.bukopin.mobile.transfer.core.service.LimitPackageService;
import id.co.asyst.bukopin.mobile.transfer.core.service.OverbookService;
import id.co.asyst.bukopin.mobile.transfer.core.service.TransferService;
import id.co.asyst.bukopin.mobile.transfer.model.InquiryAccountReq;
import id.co.asyst.bukopin.mobile.transfer.model.InquiryAccountRes;
import id.co.asyst.bukopin.mobile.transfer.model.PostingReq;
import id.co.asyst.bukopin.mobile.transfer.model.PostingRes;
import id.co.asyst.bukopin.mobile.transfer.model.payload.ProductMapper;
import id.co.asyst.bukopin.mobile.user.model.payload.VerifyPhoneOwnerRequest;
import id.co.asyst.foundation.service.connector.Services;

/**
 * REST Controller for managing Statement.
 * 
 * @author Kartika Dwi Handini
 * @version $Revision$,(Created on Nov 05, 2019)
 * @since 1.0.Alpha1
 */
@RestController
@RequestMapping("/api")
public class OverbookController {
	/**
	 * Logger.
	 */
	private final Logger log = LoggerFactory
			.getLogger(OverbookController.class);

	/* Constants: */
	/**
	 * Separator: Pipe
	 */
	private final static String SEPARATOR_PIPE = "|";
	private static final String TRANSFER_NOT_ENOUGH_BALANCE = "08";
	private static final String TRANSFER_INACTIVE_ACCOUNT = "15";
	private static final String TRANSFER_ACCOUNT_DEST_NOT_FOUND = "14";
	private static final String TRANSFER_NOT_ENOUGH_BALANCE_FUNDS = "51";
	private static final String SUCCESS_CODE = "000";
	// Prefix Wokee Account Number
	public static final String WOKEE_ACCNO_PREFIX = "89";
	public static final String WOKEE_ERROR_CODE = "16";

	/* Attributes: */

	/* Services: */
	/**
	 * User Repository (auto-wired, this means to get the bean called
	 * <code>userTokenRepository</code>. Which is auto-generated by Spring, we
	 * will use it to handle the data).
	 */
	@Autowired
	private OverbookService overbookService;

	/**
	 * Get message util
	 */
	@Autowired
	private MessageUtil messageUtil;

	/**
	 * Http servlet request
	 */
	@Autowired
	private HttpServletRequest servletRequest;

	/**
	 * Transfer Service
	 */
	@Autowired
	private TransferService transferService;

	/**
	 * Limit Package Transfer
	 */
	@Autowired
	private LimitPackageService limitService;

	@Autowired
	private DailyLimitService dailyLimitService;

	/* Constructors: */
	/**
	 * Default Constructor.
	 */
	public OverbookController() {
		// do nothing.
	}

	/* Getters & setters for attributes: */

	/* Functions: */
	/**
	 * POST /getInquiryTransaction : get all Transaction.
	 *
	 * @return the response with status 200 (OK) and the list of Transaction in
	 *         body.
	 * @throws IOException
	 * @throws ParseException
	 */
	@SuppressWarnings("unchecked")
	@PostMapping("/postTransaction/preHandle")
	public CommonResponse postTransaction(
			@Valid @RequestBody CommonRequest<PostingReq> request)
			throws URISyntaxException, IOException, ParseException {
		CommonResponse response = new CommonResponse(
				ResponseMessage.SUCCESS.getCode(), messageUtil.get("success",
						servletRequest.getLocale()));

		// Validate Token and Phone Owner
		CommonRequest<VerifyPhoneOwnerRequest> phoneReq = new CommonRequest<>();
		VerifyPhoneOwnerRequest phoneReqData = new VerifyPhoneOwnerRequest();
		phoneReqData.setUsername(request.getData().getPostingFrom()
				.getUsername());
		phoneReqData.setToken(servletRequest
				.getHeader(HttpHeaders.AUTHORIZATION));
		phoneReqData.setPhoneIdentity(servletRequest
				.getHeader(BkpmConstants.HTTP_HEADER_DEVICE_ID));
		phoneReq.setData(phoneReqData);
		CommonResponse resPhone = Services.create(UserModuleService.class)
				.verifyPhoneOwner(phoneReq).execute().body();
		if (!ResponseMessage.SUCCESS.getCode().equals(resPhone.getCode())) {
			log.error("Validate Token and Phone owner error..");
			return resPhone;
		}

		// Check Cut Off
		long cutoffId = SystemCutOffEnum.FUND_TRANSFER.getId();
		if (BkpmConstants.BUKOPIN_BANK_CODE.equals(request.getData()
				.getPostingTo().getBankCode())) {
			cutoffId = SystemCutOffEnum.OVERBOOK.getId();
		}
		CommonResponse cutOffResponse = Services
				.create(MasterModuleService.class)
				.checkCutOffStatus(servletRequest.getLocale().getLanguage(),
						cutoffId).execute().body();
		if (!ResponseMessage.SUCCESS.getCode().equals(cutOffResponse.getCode())) {
			log.error("Error Cutoff");
			return cutOffResponse;
		}

		// Prepare verify PIN
		GetVerifyPINRequest pinRequest = new GetVerifyPINRequest();
		pinRequest.setPin(request.getData().getPin());
		pinRequest
				.setUsername(request.getData().getPostingFrom().getUsername());
		CommonRequest<GetVerifyPINRequest> commonPinRequest = new CommonRequest<>();
		commonPinRequest.setIdentity(request.getIdentity());
		commonPinRequest.setData(pinRequest);

		try {
			// Verify PIN
			CommonResponse pinResponse = Services
					.create(UserModuleService.class)
					.verifyPIN(
							servletRequest
									.getHeader(HttpHeaders.ACCEPT_LANGUAGE),
							commonPinRequest).execute().body();
			if (!ResponseMessage.SUCCESS.getCode()
					.equals(pinResponse.getCode())) {
				// response not success
				return pinResponse;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Verify Reference Code
		// from|to|confirmationcode|merchant
		String ref = request.getData().getPostingFrom().getAccountNumber()
				+ SEPARATOR_PIPE
				+ request.getData().getPostingTo().getAccountNumber()
				+ SEPARATOR_PIPE + request.getData().getConfirmationCode()
				+ SEPARATOR_PIPE + BkpmConstants.MERCHANT_ID;
		boolean referenceValid = CryptoUtil.matchAES(ref, request.getData()
				.getReferenceCode());
		if (!referenceValid) {
			// reference not valid
			log.error("Reference not valid: {}", request.getData()
					.getReferenceCode());
			throw new DataNotMatchException();
		}
		// validasi limit harian user
		String jenis = "";
		if (BkpmConstants.BUKOPIN_BANK_CODE.equals(request.getData()
				.getPostingTo().getBankCode())) {
			jenis = "overbook";
		} else {
			jenis = "fundtransfer";
		}
		CommonResponse resvalharian = dailyLimitService.checkdailyLimit(request
				.getData().getPostingFrom().getUsername(), request.getData()
				.getPostingFrom().getAccountNumber(), request.getData()
				.getAmount(), jenis);

		log.debug("response check limit harian " + resvalharian);
		if (resvalharian.getCode().equals("000")) {
			// Posting Process
			// Posting Process
			PostingRes res = overbookService.postTransactionViaAPI(
					request.getData(), request.getIdentity());
			if (null != res.getStatusCode() && !"".equals(res.getStatusCode())
					&& "200".equals(res.getStatusCode())) {
				// send email receipt transfer
				try {
					CommonResponse getUser = Services
							.create(UserModuleService.class)
							.getUserByUsername(
									request.getData().getPostingFrom()
											.getUsername()).execute().body();

					if (null != getUser) {
						// get account info data by accountNo
						CommonResponse findAccountInfo = Services
								.create(UserModuleService.class)
								.getAccountInfoByAccountNo(
										CryptoUtil.encryptAESHex(request
												.getData().getPostingFrom()
												.getAccountNumber())).execute()
								.body();

						if (!ResponseMessage.SUCCESS.getCode().equals(
								findAccountInfo.getCode())) {
							// response not success
							return findAccountInfo;
						}

						// get account info by account no
						ObjectMapper mapper = new ObjectMapper();
						Map<String, Object> resultAccInfo = mapper
								.convertValue(findAccountInfo.getData(),
										Map.class);
						Map<String, Object> resPro = (Map<String, Object>) resultAccInfo
								.get("accountInfo");
						ProductMapper productMapper = mapper.convertValue(
								resPro.get("product"),
								new TypeReference<ProductMapper>() {
								});
						String productName = productMapper.getProductName();

						// convert getuser
						ObjectMapper oMapper = new ObjectMapper();
						oMapper = new ObjectMapper();
						Map<String, Object> result = oMapper.convertValue(
								getUser.getData(), Map.class);
						Map<String, String> resUser = oMapper.convertValue(
								result.get("user"), Map.class);
						transferService.sendEmailReceiptTransfer(res, resUser,
								servletRequest.getLocale(), productName);
						response.setData(res);
						// untuk insert/edit data
						CommonResponse resProses = dailyLimitService
								.prosesValidateLimit(resvalharian.getData());
						log.debug("proses limit harian " + resProses
								+ " convert object to json object "
								+ resvalharian.getData());
					} else {
						log.error("SUbscriber not found/invalid");
						response.setCode(ResponseMessage.DATA_NOT_FOUND
								.getCode());
						response.setMessage(messageUtil.get(
								"error.id.emoney.not.found",
								servletRequest.getLocale()));
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// response.setData(res);
			} else if (TRANSFER_NOT_ENOUGH_BALANCE.equalsIgnoreCase(res
					.getStatusCode())) {
				log.debug("not enough balance");
				response.setCode(ResponseMessage.AMOUNT_NOT_ENOUGH.getCode());
				response.setMessage(messageUtil.get("error.amount.not.enough",
						servletRequest.getLocale()));
			} else if (TRANSFER_NOT_ENOUGH_BALANCE_FUNDS.equalsIgnoreCase(res
					.getStatusCode())) {
				log.debug("not enough balance");
				response.setCode(ResponseMessage.AMOUNT_NOT_ENOUGH.getCode());
				response.setMessage(messageUtil.get("error.amount.not.enough",
						servletRequest.getLocale()));
			} else if (TRANSFER_INACTIVE_ACCOUNT.equals(res.getStatusCode())) {
				log.error("Account inactive: "
						+ request.getData().getPostingFrom().getAccountNumber());
				response.setCode(ResponseMessage.ERROR_INACTIVE_BANK_ACCOUNT
						.getCode());
				response.setMessage(messageUtil.get(
						"error.inactive.bank.account",
						servletRequest.getLocale()));
			} else if (TRANSFER_ACCOUNT_DEST_NOT_FOUND.equals(res
					.getStatusCode())) {
				if (isWokeeAccount(request.getData().getPostingTo()
						.getAccountNumber())) {
					log.error("Account destination is Wokee: "
							+ request.getData().getPostingTo()
									.getAccountNumber());
					response.setCode(ResponseMessage.ERROR_INVALID_WOKEE_ACCOUNT
							.getCode());
					response.setMessage(messageUtil.get(
							"error.account.dest.wokee",
							servletRequest.getLocale()));
				} else {
					log.error("Account destination not found: "
							+ request.getData().getPostingTo()
									.getAccountNumber());
					response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
					response.setMessage(messageUtil.get(
							"error.account.dest.not.found",
							servletRequest.getLocale()));
				}
			} else if (WOKEE_ERROR_CODE.equals(res.getStatusCode())) {
				if ("Rekening tujuan tidak aktif / pasif".equalsIgnoreCase(res
						.getStatusDesc())) {
					log.error("Inactive wokee account: "
							+ request.getData().getPostingTo()
									.getAccountNumber());
					response.setCode(ResponseMessage.ERROR_INACTIVE_BANK_ACCOUNT
							.getCode());
					response.setMessage(messageUtil.get(
							"error.inactive.wokee.account",
							servletRequest.getLocale()));
				} else if ("Rekening tujuan harus rekening utama"
						.equalsIgnoreCase(res.getStatusDesc())) {
					log.error("Beneficiary account must be the main account: "
							+ request.getData().getPostingTo()
									.getAccountNumber());
					response.setCode(ResponseMessage.ERROR_MUST_BE_MAIN_ACCOUNT
							.getCode());
					response.setMessage(messageUtil.get(
							"error.must.be.main.account",
							servletRequest.getLocale()));
				} else if ("Rekening tujuan tidak ada / bukan rekening utama"
						.equalsIgnoreCase(res.getStatusDesc())) {
					log.error("Wokee Account Not Found / Closed – Rekening Wokee tidak ada / tutup: "
							+ request.getData().getPostingTo()
									.getAccountNumber());
					response.setCode(ResponseMessage.ERROR_WOKEE_ACCOUNT_CLOSED
							.getCode());
					response.setMessage(messageUtil.get(
							"error.wokee.account.closed",
							servletRequest.getLocale()));
				} else {
					log.error("error code " + res.getStatusCode()
							+ " message: " + res.getStatusDesc());
					// response = new CommonResponse(res.getStatusCode(),
					// res.getStatusDesc());
					throw new MiddlewareException(res.getStatusCode() + " "
							+ res.getStatusDesc());
				}

			} else {
				log.error("error code " + res.getStatusCode() + " message: "
						+ res.getStatusDesc());
				// response = new CommonResponse(res.getStatusCode(),
				// res.getStatusDesc());
				throw new MiddlewareException(res.getStatusCode());
			}

		} else {
			if (resvalharian.getMessage().equals("Limit user not set")) {
				response.setCode(resvalharian.getCode());
				response.setMessage(messageUtil.get("error.limit.unset",
						servletRequest.getLocale()));

			} else if (resvalharian.getMessage().equals(
					"amount more than daily limit user")) {
				response.setCode(resvalharian.getCode());
				response.setMessage(messageUtil.get("error.limit.exceed",
						servletRequest.getLocale()));
			} else if (resvalharian.getMessage().equals(
					"transactions exceed daily limit")) {
				response.setCode(resvalharian.getCode());
				response.setMessage(messageUtil.get("error.limit.exceed",
						servletRequest.getLocale()));
			} else {
				response.setCode(resvalharian.getCode());
				response.setMessage(resvalharian.getMessage());
			}
		}

		return response;
	}

	/**
	 * Inquiry Account
	 * 
	 * @param request
	 * @return
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	@PostMapping("/inquiryAccount/preHandle")
	public CommonResponse inquiryAccount(
			@Valid @RequestBody CommonRequest<InquiryAccountReq> request)
			throws URISyntaxException, IOException {
		CommonResponse response = new CommonResponse(
				ResponseMessage.SUCCESS.getCode(), messageUtil.get("success",
						servletRequest.getLocale()));

		// Check Cut Off
		long cutoffId = SystemCutOffEnum.FUND_TRANSFER.getId();
		if (BkpmConstants.BUKOPIN_BANK_CODE.equals(request.getData()
				.getPostingTo().getBankCode())) {
			cutoffId = SystemCutOffEnum.OVERBOOK.getId();
		}
		CommonResponse cutOffResponse = Services
				.create(MasterModuleService.class)
				.checkCutOffStatus(servletRequest.getLocale().getLanguage(),
						cutoffId).execute().body();
		if (!ResponseMessage.SUCCESS.getCode().equals(cutOffResponse.getCode())) {
			log.error("Error Cutoff");
			return cutOffResponse;
		}

		// Get User
		String username = request.getData().getPostingFrom().getUsername();
		CommonResponse userResponse = Services.create(UserModuleService.class)
				.getUserByUsername(username).execute().body();
		if (!ResponseMessage.SUCCESS.getCode().equals(userResponse.getCode())) {
			log.error("Error while get user by username");
			response.setCode(ResponseMessage.INTERNAL_SERVER_ERROR.getCode());
			response.setMessage(messageUtil.get("error.internal.server",
					servletRequest.getLocale()));
			return response;
		}

		// Get User's limit package Id
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> resultUserObj = mapper.convertValue(
				userResponse.getData(), Map.class);
		log.debug("resultUserObj: {}", resultUserObj);
		Map<String, Object> user = (Map<String, Object>) resultUserObj
				.get("user");
		log.debug("user: {}", user);
		String limitStr = String.valueOf(user.get("limitId"));
		log.debug("limitStr: {}", limitStr);
		long limitId = 0L;
		if (!StringUtils.isBlank(limitStr)
				&& !"null".equalsIgnoreCase(limitStr)) {
			limitId = Long.valueOf(limitStr);
		}
		log.debug("limitId: {}", limitId);

		// Limit transfer validation
		int accountType = Integer.valueOf(request.getData().getPostingFrom()
				.getAccountType());
		boolean isValid = limitService.checkLimit(limitId, accountType, request
				.getData().getAmount(), request.getData().getPostingTo()
				.getBankCode());
		if (!isValid) {
			response.setCode(ResponseMessage.LIMIT_TRANSFER_DAY.getCode());
			response.setMessage(messageUtil.get("error.limit.day.exceed",
					servletRequest.getLocale()));
		} else {
			InquiryAccountRes res = overbookService.inquiryAmountViaAPI(request
					.getData());
			log.debug("resssss: " + res);

			if (null != res.getConfirmationCode()
					&& !"".equals(res.getConfirmationCode())) {
				response.setData(res);
			} else if (TRANSFER_NOT_ENOUGH_BALANCE.equalsIgnoreCase(res
					.getStatusCode())) {
				log.debug("not enough balance");
				response.setCode(ResponseMessage.AMOUNT_NOT_ENOUGH.getCode());
				response.setMessage(messageUtil.get("error.amount.not.enough",
						servletRequest.getLocale()));
			} else if (TRANSFER_NOT_ENOUGH_BALANCE_FUNDS.equalsIgnoreCase(res
					.getStatusCode())) {
				log.debug("not enough balance");
				response.setCode(ResponseMessage.AMOUNT_NOT_ENOUGH.getCode());
				response.setMessage(messageUtil.get("error.amount.not.enough",
						servletRequest.getLocale()));
			} else if (TRANSFER_INACTIVE_ACCOUNT.equals(res.getStatusCode())) {
				log.error("Account inactive: "
						+ request.getData().getPostingFrom().getAccountNumber());
				response.setCode(ResponseMessage.ERROR_INACTIVE_BANK_ACCOUNT
						.getCode());
				response.setMessage(messageUtil.get(
						"error.inactive.bank.account",
						servletRequest.getLocale()));
			} else if (TRANSFER_ACCOUNT_DEST_NOT_FOUND.equals(res
					.getStatusCode())) {
				if (isWokeeAccount(request.getData().getPostingTo()
						.getAccountNumber())) {
					log.error("Account destination is Wokee: "
							+ request.getData().getPostingTo()
									.getAccountNumber());
					response.setCode(ResponseMessage.ERROR_INVALID_WOKEE_ACCOUNT
							.getCode());
					response.setMessage(messageUtil.get(
							"error.account.dest.wokee",
							servletRequest.getLocale()));
				} else {
					log.error("Account destination not found: "
							+ request.getData().getPostingTo()
									.getAccountNumber());
					response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
					response.setMessage(messageUtil.get(
							"error.account.dest.not.found",
							servletRequest.getLocale()));
				}
			} else if (WOKEE_ERROR_CODE.equals(res.getStatusCode())) {
				if ("Rekening tujuan tidak aktif / pasif".equalsIgnoreCase(res
						.getStatusDesc())) {
					log.error("Inactive wokee account: "
							+ request.getData().getPostingTo()
									.getAccountNumber());
					response.setCode(ResponseMessage.ERROR_INACTIVE_BANK_ACCOUNT
							.getCode());
					response.setMessage(messageUtil.get(
							"error.inactive.wokee.account",
							servletRequest.getLocale()));
				} else if ("Rekening tujuan harus rekening utama"
						.equalsIgnoreCase(res.getStatusDesc())) {
					log.error("Beneficiary account must be the main account: "
							+ request.getData().getPostingTo()
									.getAccountNumber());
					response.setCode(ResponseMessage.ERROR_MUST_BE_MAIN_ACCOUNT
							.getCode());
					response.setMessage(messageUtil.get(
							"error.must.be.main.account",
							servletRequest.getLocale()));
				} else if ("Rekening tujuan tidak ada / bukan rekening utama"
						.equalsIgnoreCase(res.getStatusDesc())) {
					log.error("Wokee Account Not Found / Closed – Rekening Wokee tidak ada / tutup: "
							+ request.getData().getPostingTo()
									.getAccountNumber());
					response.setCode(ResponseMessage.ERROR_WOKEE_ACCOUNT_CLOSED
							.getCode());
					response.setMessage(messageUtil.get(
							"error.wokee.account.closed",
							servletRequest.getLocale()));
				} else {
					log.error("error code " + res.getStatusCode()
							+ " message: " + res.getStatusDesc());
					// response = new CommonResponse(res.getStatusCode(),
					// res.getStatusDesc());
					throw new MiddlewareException(res.getStatusCode() + " "
							+ res.getStatusDesc());
				}

			} else {
				log.error("error code " + res.getStatusCode() + " message: "
						+ res.getStatusDesc());
				// response = new CommonResponse(res.getStatusCode(),
				// res.getStatusDesc());
				throw new MiddlewareException(res.getStatusCode());
			}
		}

		return response;
	}

	/*
	 * @PostMapping("/getAccountBalance") public AccountBalanceRes
	 * getAccountBalance(@Valid @RequestBody AccountBalanceReq
	 * accountBalanceReq) throws URISyntaxException {
	 * log.debug("REST request to get account balance"); return
	 * accountBalanceService.getAccountBalance(accountBalanceReq); }
	 */

	/**
	 * POST /userTokens : Creates a new UserToken.
	 *
	 * @param userToken
	 *            The userToken to create.
	 * @return The response with status 201 (Created) and with body of the new
	 *         UserToken, or with status 400 (Bad Request) if the userToken has
	 *         already an ID.
	 */
	/*
	 * @PostMapping("/userTokens")
	 * 
	 * @ResponseStatus(HttpStatus.CREATED) public UserToken
	 * createUserToken(@Valid @RequestBody UserToken userToken) throws
	 * URISyntaxException { log.debug("REST request to save UserToken : {}",
	 * userToken); if (userToken.getId() != null) { // throw new
	 * BadRequestAlertException("A new userToken cannot already have an //
	 * ID", "UserToken", "idexists"); } return userTokenService.save(userToken);
	 * }
	 */

	/**
	 * isWokeeAccount
	 * 
	 * @param accountNumber
	 * @return
	 */
	private boolean isWokeeAccount(String accountNumber) {
		boolean isWokee = false;

		// woke account number: prefix 89, 12 digit length
		// get first 2 digit
		if (accountNumber.length() == 12
				&& WOKEE_ACCNO_PREFIX.equals(accountNumber.substring(0, 2))) {
			isWokee = true;
		}

		return isWokee;
	}

	/* Overrides: */
}
