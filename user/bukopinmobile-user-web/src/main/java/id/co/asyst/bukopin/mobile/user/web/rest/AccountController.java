/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.user.web.rest;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.ws.Holder;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import id.co.asyst.bukopin.mobile.common.core.util.BkpmUtil;
import id.co.asyst.bukopin.mobile.common.core.util.CryptoUtil;
import id.co.asyst.bukopin.mobile.common.model.BkpmConstants;
import id.co.asyst.bukopin.mobile.common.model.ResponseMessage;
import id.co.asyst.bukopin.mobile.common.model.payload.CommonRequest;
import id.co.asyst.bukopin.mobile.common.model.payload.CommonResponse;
import id.co.asyst.bukopin.mobile.common.core.util.MessageUtil;
import id.co.asyst.bukopin.mobile.service.core.CentagateService;
import id.co.asyst.bukopin.mobile.service.model.payload.CentagateCommonResponse;
import id.co.asyst.bukopin.mobile.service.model.payload.LoginCentagateRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.UpdateUserProfileRequest;
import id.co.asyst.bukopin.mobile.user.core.service.AccountCardService;
import id.co.asyst.bukopin.mobile.user.core.service.AccountInfoUserService;
import id.co.asyst.bukopin.mobile.user.core.service.BukopinService;
import id.co.asyst.bukopin.mobile.user.core.service.CIFService;
import id.co.asyst.bukopin.mobile.user.core.service.OTPService;
import id.co.asyst.bukopin.mobile.user.core.service.ProductService;
import id.co.asyst.bukopin.mobile.user.core.service.UserMailService;
import id.co.asyst.bukopin.mobile.user.core.service.UserService;
import id.co.asyst.bukopin.mobile.user.core.util.AccountUtil;
import id.co.asyst.bukopin.mobile.user.core.util.AuthUtil;
import id.co.asyst.bukopin.mobile.user.model.AccountInfoStatusEnum;
import id.co.asyst.bukopin.mobile.user.model.OTPTypeEnum;
import id.co.asyst.bukopin.mobile.user.model.centagate.LoginResultData;
import id.co.asyst.bukopin.mobile.user.model.entity.AccountCard;
import id.co.asyst.bukopin.mobile.user.model.entity.AccountInfo;
import id.co.asyst.bukopin.mobile.user.model.entity.Product;
import id.co.asyst.bukopin.mobile.user.model.entity.User;
import id.co.asyst.bukopin.mobile.user.model.entity.internal.DebitCardInfo;
import id.co.asyst.bukopin.mobile.user.model.payload.AccountActivationRequest;
import id.co.asyst.bukopin.mobile.user.model.payload.AccountVerificationRequest;
import id.co.asyst.bukopin.mobile.user.model.payload.ActivateDebitCardRequest;
import id.co.asyst.bukopin.mobile.user.model.payload.VerifyAccountOwnerRequest;
import id.co.asyst.bukopin.mobile.user.model.payload.VerifyAccountOwnerResponse;
import id.co.asyst.bukopin.mobile.user.model.soap.cif.GetInquiryCIFReqType;
import id.co.asyst.bukopin.mobile.user.model.soap.cif.GetInquiryCIFResType;
import id.co.asyst.bukopin.mobile.user.model.soap.cif.HeaderRQ;
import id.co.asyst.bukopin.mobile.user.model.soap.cif.HeaderRS;
import id.co.asyst.bukopin.mobile.user.web.rest.errors.ResourceNotFoundException;
import id.co.asyst.foundation.service.connector.Services;
import retrofit2.Response;

/**
 * REST Controller to Manage Bank Account
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Nov 7, 2019
 * @since 1.0.Alpha1-SNAPSHOT
 */
@RestController
@RequestMapping("/account")
public class AccountController {

    /**
     * Logger
     */
    private final Logger log = LoggerFactory.getLogger(AccountController.class);

    /* Services: */
    /**
     * OTP Service (auto-wired, this means to get the bean called
     * <code>otpService</code>. Which is auto-generated by Spring, we will use it to
     * handle the data).
     */
    @Autowired
    private OTPService otpService;

    /**
     * User Service
     */
    @Autowired
    private UserService userService;

    /**
     * Account Service
     */
    @Autowired
    private AccountCardService accountCardService;

    /**
     * Account Info Service
     */
    @Autowired
    private AccountInfoUserService accInfoUserService;

    /**
     * Bukopin Service
     */
    @Autowired
    private BukopinService bukopinService;

    /**
     * CIF Service
     */
    @Autowired
    private CIFService cifService;

    /**
     * Product Service
     */
    @Autowired
    private ProductService productService;

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

    @Autowired
    private UserMailService userMailService;
    /**
     * Environment
     */
    @Autowired
    private Environment env;

    /* Constants: */

    /* Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */

    /* Getters & setters for transient attributes: */

    /* Functionalities: */
    /**
     * Account Info Verification
     * <ol>
     * <li>Verify SMS OTP</li>
     * <li>Get CIF number from Tibco</li>
     * <li>Get Inquiry CIF from SOAP</li>
     * <li>Find Account Info by CIF number</li>
     * <li>Insert Account Card, Account Info if Account Info not Exist</li>
     * <li>OR Delete All Account Info by CIF Then Insert All Account Info</li>
     * </ol>
     * 
     * @param request
     *            information for Activation Debit Card
     * @return Success or Failed and list of Account Info
     * @throws DatatypeConfigurationException
     */
    @SuppressWarnings("unchecked")
    @PostMapping("/verification")
    public CommonResponse verification(@Valid @RequestBody CommonRequest<AccountVerificationRequest> request)
	    throws DatatypeConfigurationException {
	log.debug("REST request to Verification : {}", request.getData());
	CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(),
		messageUtil.get("success", servletRequest.getLocale()));
	String username = request.getData().getDebitCard().getUsername();
	String receiver = request.getData().getReceiver();
	String otp = request.getData().getOtp();
	ActivateDebitCardRequest debitCard = request.getData().getDebitCard();
	Date currentTime = new Date();
	log.debug("Account - List Account for receiver : " + receiver);
	
	// Verify User
	User user = userService.findUserByUsername(username);
	if (user == null) {
	    log.error("User not found: {}", username);
	    response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
	    response.setMessage(messageUtil.get("error.data.x.not.found", 
		    new Object[] { "User", username }, servletRequest.getLocale()));
	    return response;
	}

	// Verify OTP
	// --------------------------------------
	boolean isValid = otpService.verifyOtp(receiver, otp, OTPTypeEnum.SMS, currentTime);
	if (!isValid) {
	    log.error("OTP not valid: " + otp);
	    // handle error
	    response.setCode(ResponseMessage.DATA_NOT_MATCH.getCode());
	    response.setMessage(messageUtil.get("otp.invalid", servletRequest.getLocale()));
	    return response;
	}

	// Delete OTP
	otpService.inactivateOTP(receiver, OTPTypeEnum.SMS, currentTime);
	
	// Get CIF from Tibco and Get Inquiry CIF from SOAP
	// --------------------------------------
	List<DebitCardInfo> cards = bukopinService.getCardByCardNumber(debitCard.getRegisteredCard());
	if (cards.isEmpty()) {
	    log.error("debit card is empty");
	    response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
	    response.setMessage(messageUtil.get("card.not.found", servletRequest.getLocale()));
	} else {
	    log.debug("Card found, get Inquiry CIF to WSDL (verification)");
	    response = gettingInquiryCIF(cards.get(0).getCif());
	}

	if (!ResponseMessage.SUCCESS.getCode().equals(response.getCode())) {
	    return response;
	}

	ObjectMapper oMapper = new ObjectMapper();
	Map<String, Object> res = oMapper.convertValue(response.getData(), Map.class);
	GetInquiryCIFResType CIFResType = oMapper.convertValue(res.get("rsBody"), GetInquiryCIFResType.class);

	if (CIFResType.getAccounts().size() < 1) {
	    throw new ResourceNotFoundException("Debit Card has no Account info : " + debitCard.getRegisteredCard());
	}

	// List all Products
	List<Product> products = productService.findAll();
	List<Product> listProduct = new ArrayList<>();
	CIFResType.getAccounts().forEach(accounts -> {
	    if(BkpmConstants.CODE_TYPE_SAVING.equals(String.valueOf(accounts.getAcctype()))
			|| BkpmConstants.CODE_TYPE_GIRO.equals(String.valueOf(accounts.getAcctype()))) {
		// get accounts only in pdid in table PRODUCT 
		listProduct.addAll(
			products.stream().filter(
				p -> accounts.getProductid()==p.getPdId())
			.collect(Collectors.toList()));
	    }
	});
	
//	for (GetInquiryCIFResType.Accounts accounts : CIFResType.getAccounts()) {
//	    if(BkpmConstants.CODE_TYPE_SAVING.equals(String.valueOf(accounts.getAcctype()))
//			|| BkpmConstants.CODE_TYPE_GIRO.equals(String.valueOf(accounts.getAcctype()))) {
//		listProduct.add(productService.findByPdId(accounts.getProductid()));
//	    }
//	}

	List<AccountInfo> listSaveAccInfo = new ArrayList<>();
	AccountCard accCard = new AccountCard();
//	String cif = CIFResType.getAccInfo().getCifnumber();
	String registCard = cards.get(0).getCardNumber();
	
	accCard = accountCardService.findByRegisteredCard(registCard);

	if (accCard == null) {
	    log.debug("Account Info with Regist Card " + registCard + " is Not Exist");
	    accCard = AccountUtil.setDataAccountCard(debitCard, CIFResType, user);
	    accCard = accountCardService.save(accCard);
	    listSaveAccInfo = AccountUtil.setDataAccountInfo(CIFResType, accCard, cards, listProduct);
	    if(listSaveAccInfo==null || listSaveAccInfo.isEmpty()) {
		log.error("Account Number not match between db Xlink and Tibco");
		// no accNo match between db xlink and tibco
		response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
		response.setMessage(messageUtil.get("account.not.found", 
			new Object[] {cards.get(0).getAccountNumber()}, servletRequest.getLocale()));
		response.setData(null);
		return response;
	    }
	    accInfoUserService.saveAll(listSaveAccInfo);
	} else {
	    log.debug("Account Info with Regist Card " + registCard + " is Exist");
	    accInfoUserService.deleteByAccountCardId(accCard.getId());
	    listSaveAccInfo = AccountUtil.setDataAccountInfo(CIFResType, accCard, cards, listProduct);
	    if(listSaveAccInfo==null || listSaveAccInfo.isEmpty()) {
		log.error("Account Number not match between db Xlink and Tibco");
		// no accNo match between db xlink and tibco
		response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
		response.setMessage(messageUtil.get("account.not.found", 
			new Object[] {cards.get(0).getAccountNumber()}, servletRequest.getLocale()));
		response.setData(null);
		return response;
	    }
	    accInfoUserService.saveAll(listSaveAccInfo);
	}

//	List<AccountInfo> listAccInfoByCIF = accInfoUserService.findByCIF(cif);
	List<AccountInfo> listAccInfoByCIF = AccountUtil.generateResponseVerification(
		CIFResType, cards, products);
	if (listAccInfoByCIF==null || listAccInfoByCIF.size() < 1) {
	    throw new ResourceNotFoundException("Account Info Not Found: " + user.getUsername());
	}

	response.setData(listAccInfoByCIF);
	return response;
    }

    /**
     * User Activation
     * <ol>
     * <li>Get all account cards by Username</li>
     * <li>Set main account</li>
     * <li>Update Centagate user profile</li>
     * </ol>
     * 
     * @param request
     *            The User info request.
     * @return Response success or failed.
     * @throws Exception
     */
    @PostMapping("/activation")
    public CommonResponse activation(@Valid @RequestBody CommonRequest<AccountActivationRequest> request)
	    throws Exception {
	CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(),
		messageUtil.get("success", servletRequest.getLocale()));
	log.debug("Account - Activation: " + request.getData().getUsername());

	String username = request.getData().getUsername();
	String mainAccount = request.getData().getMainAccountNo();

	// Update Account Status (Backend DB)
	// --------------------------------------
	AccountCard accountCard = accountCardService.findByUsername(username);
	if (accountCard == null) {
	    log.error("Account not found: " + username);
	    String message = messageUtil.get("activation.failed", new Object[] { username },
		    servletRequest.getLocale());
	    response.setCode(ResponseMessage.ACTIVATION_USER_FAILED.getCode());
	    response.setMessage(message);
	    return response;
	}
	
	String receiver = accountCard.getUser().getEmail();
	
	log.debug("Accounts: " + accountCard.getAccounts().size());
	// find mainAccount
	AtomicBoolean isMainAccountFound = new AtomicBoolean(false);
	accountCard.getAccounts().forEach(acc -> {
	    // verifying all account
	    acc.setStatus(AccountInfoStatusEnum.VERIFIED);
	    // set main account
	    if (mainAccount.equals(acc.getAccountNo())) {
		isMainAccountFound.set(true);
		acc.setMainAccount(true);
	    } else {
		// set other main account false
		acc.setMainAccount(false);
	    }
	});
	// error main account not found
	if (!isMainAccountFound.get()) {
	    log.error("Account number not found: " + mainAccount);
	    response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
	    response.setMessage(
		    messageUtil.get("account.not.found", new Object[] { mainAccount }, servletRequest.getLocale()));
	    return response;
	}
	accountCard = accountCardService.save(accountCard);
	
	//sent activation email
	userMailService.sentActivationMail(receiver, accountCard,servletRequest.getLocale());
	
	// Login Centagate Admin
	// --------------------------------------
	// LoginCentagateRequest reqLoginAdmin = AuthUtil.generateAdminRequest();
	LoginCentagateRequest reqAdm = AuthUtil.generateLoginRequest(env.getProperty("centagate.user.admin"),
		env.getProperty("centagate.password.admin"));
	// log.debug(BkpmUtils.convertToJson(request));
	CentagateCommonResponse respAdm = Services.create(CentagateService.class).login(reqAdm).execute().body();
	if (!BkpmConstants.CODE_CTG_SUCCESS.equals(respAdm.getCode())) {
	    // handle error login Centagate Admin
	    log.error("Login admin failed to Update User Profile.");
	    response.setCode(ResponseMessage.ACTIVATION_USER_FAILED.getCode());
	    response.setMessage(
		    messageUtil.get("activation.failed", new Object[] { username }, servletRequest.getLocale()));
	    return response;
	}
	// Mapping Object Result
	ObjectMapper mapper = new ObjectMapper();
	LoginResultData admObj = mapper.readValue(String.valueOf(respAdm.getObject()), LoginResultData.class);

	// Update Centagate User Profile
	// --------------------------------------
	User user = userService.findUserByUsername(username);
	UpdateUserProfileRequest updateProfileRequest = new UpdateUserProfileRequest();
	updateProfileRequest.setUsername(username);
	updateProfileRequest.setFirstName(user.getFirstName());
	updateProfileRequest.setLastName(user.getLastName());

	// hashedCentoken = secretcode, adminusername+authtoken
	String centoken = env.getProperty("centagate.user.admin") + admObj.getAuthToken();
	String secretCode = admObj.getSecretCode();
	String hashedCentoken = CryptoUtil.encryptHmacSHA256(secretCode, centoken);
	updateProfileRequest.setCenToken(hashedCentoken);
	log.debug(BkpmUtil.convertToJson(updateProfileRequest));

	Response<CentagateCommonResponse> updateRes = Services.create(CentagateService.class)
		.updateUserProfile(env.getProperty("centagate.user.admin"), updateProfileRequest).execute();
	// handle error response
	if (updateRes.code() != HttpStatus.OK.value()) {
	    log.error("Failed update User profile: " + username);
	    response.setCode(ResponseMessage.ACTIVATION_USER_FAILED.getCode());
	    response.setMessage(
		    messageUtil.get("activation.failed", new Object[] { username }, servletRequest.getLocale()));
	    return response;
	}
	
	// save activated user
	user.setActivation(true);
	userService.save(user);

	return response;
    }

    /**
     * Pre Account Verification
     * <ol>
     * <li>Query to tibco, get CIF number from registered Card</li>
     * <li>Get Inquiry CIF result from SOAP by CIF number</li>
     * <li>Validate birthdate from request and result from SOAP</li>
     * <li>Send OTP code to mobile phone number</li>
     * </ol>
     * 
     * @param request
     *            Debit Card info request.
     * @return Response success or failed.
     * @throws DatatypeConfigurationException
     * @throws IOException
     * @throws NoSuchAlgorithmException
     * @throws ParseException
     */
    @SuppressWarnings("unchecked")
    @PostMapping("/preverification")
    public CommonResponse preVerification(@Valid @RequestBody CommonRequest<ActivateDebitCardRequest> request)
	    throws DatatypeConfigurationException, NoSuchAlgorithmException, IOException, ParseException {
	log.debug("REST request to Preverification : {}", request.getData().getRegisteredCard());
	ActivateDebitCardRequest debitCard = request.getData();
	CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(),
		messageUtil.get("success", servletRequest.getLocale()));
	SimpleDateFormat formatBirthDate = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat formatExpired = new SimpleDateFormat("MM-yy");
	SimpleDateFormat formatCard = new SimpleDateFormat("yyyyMM");
	
	// Check account by query to Bukopin Internal DB
	List<DebitCardInfo> cards = bukopinService.getCardByCardNumber(debitCard.getRegisteredCard());
	if (cards.isEmpty()) {
	    log.error("card info not found");
	    response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
	    response.setMessage(messageUtil.get("card.not.found", servletRequest.getLocale()));
	} else {
	    log.debug("Card found, get Inquiry CIF to WSDL (preverification)");
	    response = gettingInquiryCIF(cards.get(0).getCif());

	    if (!ResponseMessage.SUCCESS.getCode().equals(response.getCode())) {
		response.setData(null);
		return response;
	    }

	    ObjectMapper oMapper = new ObjectMapper();
	    Map<String, Object> res = oMapper.convertValue(response.getData(), Map.class);
	    GetInquiryCIFResType CIFResType = oMapper.convertValue(res.get("rsBody"), GetInquiryCIFResType.class);

	    // Validate Expired Card
	    String expiredDate = debitCard.getValidMonth() + "-" + debitCard.getValidYear();
	    Date convertDate = formatExpired.parse(expiredDate);
	    Date now = new Date();
	    Calendar c = Calendar.getInstance();
	    c.setTime(convertDate);
	    c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
	    if (!c.getTime().after(now)) {
		log.error("The Card is Expired!");
		response.setCode(ResponseMessage.CARD_EXPIRED.getCode());
		response.setMessage(messageUtil.get("card.invalid", servletRequest.getLocale()));
		response.setData(null);
		return response;
	    } else {
		String expiredMonthYear = cards.get(0).getExpiredDate().substring(0, 6);
		if (!formatCard.format(c.getTime()).equals(expiredMonthYear)) {
		    log.error("Valid Month and Year is not same!");
		    response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
		    response.setMessage(messageUtil.get("card.invalid", servletRequest.getLocale()));
		    response.setData(null);
		    return response;
		}
	    }

	    String birthDate = "";
	    birthDate = formatBirthDate.format(CIFResType.getAccInfo().getBirthdate().toGregorianCalendar().getTime());
	    if (!formatBirthDate.format(debitCard.getBirthDate()).equals(birthDate)) {
		log.error("Birth Date is not same!");
		response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
		response.setMessage(messageUtil.get("card.invalid", servletRequest.getLocale()));
		response.setData(null);
		return response;
	    }

	    AccountCard accCard = new AccountCard();
	    accCard = accountCardService.findByRegisteredCard(debitCard.getRegisteredCard());
	    if (accCard != null) {
		if (!accCard.getUser().getUsername().equals(debitCard.getUsername())) {
		    log.error("Kartu sudah digunakan");
		    response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
		    response.setMessage(messageUtil.get("card.used", servletRequest.getLocale()));
		    response.setData(null);
		    return response;
		}
	    }

	    // send OTP by phone number
	    response = otpService.sendOTP(CIFResType.getAccInfo().getMobilephone().replace("-", ""), 
		    OTPTypeEnum.SMS, servletRequest.getLocale(), request.getData().getAppSignature());
	    if (!ResponseMessage.SUCCESS.getCode().equals(response.getCode())) {
		response.setData(null);
		return response;
	    }
	    
	    if(CIFResType.getAccInfo().getMobilephone().isEmpty()) {
		log.error("Nomor Ponsel Tidak Ada");
		response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
		response.setMessage(messageUtil.get("mobile.phone.empty", servletRequest.getLocale()));
		response.setData(null);
		return response;
	    }
	    
	    response.setMessage(messageUtil.get("success", servletRequest.getLocale()));
	    response.setData(CIFResType.getAccInfo().getMobilephone().replace("-", ""));
	}

	return response;
    }

    /**
     * Getting Inquiry CIF from SOAP
     * 
     * @param cif
     *            number.
     * @return Response success or failed and result data from SOAP get Inquiry CIF.
     * @throws DatatypeConfigurationException
     */
    public CommonResponse gettingInquiryCIF(String cif) throws DatatypeConfigurationException {
	CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(),
		messageUtil.get("success", servletRequest.getLocale()));
	HeaderRQ headerRQ = new HeaderRQ();
	GetInquiryCIFReqType CIFReqType = new GetInquiryCIFReqType();
	Holder<HeaderRS> holderHeaderRS = new Holder<>();
	Holder<GetInquiryCIFResType> holderInquiryCIFRS = new Holder<>();

	headerRQ = AccountUtil.generateHeaderRQInquiryCIF();
	CIFReqType = AccountUtil.generateBodyInquiryCIF(cif);

	try {
	    cifService.getInquiryCIF(headerRQ, CIFReqType, holderHeaderRS, holderInquiryCIFRS);
	} catch (Exception e) {
	    log.error(e.getMessage());
	    response.setCode(ResponseMessage.ERROR.getCode());
	    response.setMessage("Get CIF to Soap Failed!");
	    return response;
	}

	if (!BkpmConstants.CODE_SOAP_SUCCESS.equals(holderInquiryCIFRS.value.getResponse().getRescode())) {
	    response.setCode(ResponseMessage.ERROR.getCode());
	    response.setMessage("Get CIF Succes, Status is Failed!");
	    return response;
	}

	Map<String, Object> res = new HashMap<>();
	res.put("rsHeader", holderHeaderRS.value);
	res.put("rsBody", holderInquiryCIFRS.value);
	response.setData(res);

	return response;
    }

    /**
     * Find User by username
     * 
     * @param request
     *            common request user.
     * @return Response success or failed and result data object user.
     * @throws DatatypeConfigurationException
     */
    // @SuppressWarnings("unchecked")
    @PostMapping("/findUserByUsername")
    public CommonResponse findUserByUsername(@Valid @RequestBody CommonRequest<User> request)
	    throws DatatypeConfigurationException, NoSuchAlgorithmException, IOException {
	log.debug("REST find user by username... " + request.getData().getUsername());
	CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(),
		messageUtil.get("success", servletRequest.getLocale()));

	User user = userService.findUserByUsername(request.getData().getUsername());
	if (null != user) {
	    response.setData(user);
	} else {
	    response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
	    response.setMessage(messageUtil.get("error.data.not.found", servletRequest.getLocale()));
	    response.setData(null);
	    return response;
	}

	return response;
    }

    /**
     * Find User ID by username
     * 
     * @param username
     *            user's username.
     * @return Response success or failed and result ID user.
     */
    @GetMapping("/findUserIdByUsername/{username}")
    public CommonResponse findUserIdByUsername(@PathVariable String username) {
	log.debug("Find user Id by Username " + username);

	CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(),
		messageUtil.get("success", servletRequest.getLocale()));

	User user = userService.findUserByUsername(username);
	if (null != user) {
	    log.debug("User id Successfully retrieved ....");
	    response.setData(user.getId());
	} else {
	    response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
	    response.setMessage(messageUtil.get("error.data.not.found", servletRequest.getLocale()));
	    response.setData(null);
	    return response;
	}

	return response;
    }

    /**
     * Change User's Main Account
     * 
     * @param request
     *            The User info request.
     * @return Response success or failed.
     */
    @PostMapping("/changeMainAccount")
    public CommonResponse changeMainAccount(@Valid @RequestBody CommonRequest<AccountActivationRequest> request) {
	CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(),
		messageUtil.get("success", servletRequest.getLocale()));
	log.debug("Account - Change main account: " + request.getData().getUsername());

	String username = request.getData().getUsername();
	String mainAccount = request.getData().getMainAccountNo();

	// Update Account Status (Backend DB)
	// --------------------------------------
	AccountCard accountCard = accountCardService.findByUsername(username);
	if (accountCard == null) {
	    log.error("Account not found: " + username);
	    String message = messageUtil.get("change.main.account.failed", new Object[] { username },
		    servletRequest.getLocale());
	    response.setCode(ResponseMessage.ERROR_UPDATE_USER.getCode());
	    response.setMessage(message);
	    return response;
	}

	log.debug("Accounts: " + accountCard.getAccounts().size());
	// find mainAccount
	AtomicBoolean isMainAccountFound = new AtomicBoolean(false);
	accountCard.getAccounts().forEach(acc -> {
	    // set main account
	    if (mainAccount.equals(acc.getAccountNo())) {
		isMainAccountFound.set(true);
		acc.setMainAccount(true);
	    } else {
		// set other main account false
		acc.setMainAccount(false);
	    }
	});
	// error main account not found
	if (!isMainAccountFound.get()) {
	    log.error("Account number not found: " + mainAccount);
	    response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
	    response.setMessage(
		    messageUtil.get("account.not.found", new Object[] { mainAccount }, servletRequest.getLocale()));
	    return response;
	}
	accountCardService.save(accountCard);

	return response;
    }

    /**
     * Find User by username
     * 
     * @param username
     *            user's username.
     * @return Response success or failed and result data Map object.
     */
    @GetMapping("/findUserByUsername/{username}")
    public CommonResponse findUserByUsername(@PathVariable String username) {
	log.debug("Find user by Username " + username);

	CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(),
		messageUtil.get("success", servletRequest.getLocale()));

	User user = userService.findUserByUsername(username);
	if (null != user) {
	    log.debug("User Successfully retrieved ....");
	    Map<String, Object> res = new HashMap<>();
	    res.put("user", user);
	    response.setData(res);
	} else {
	    response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
	    response.setMessage(messageUtil.get("error.data.not.found", servletRequest.getLocale()));
	    response.setData(null);
	    return response;
	}

	return response;
    }
    
    /**
     * Find Account Info by Account No
     * 
     * @param accountNo
     *            user's account number.
     * @return Response success or failed and result data Map object.
     */
    @GetMapping("/findAccountInfoByAccountNo/{accountNo}")
    public CommonResponse findAccountInfoByAccountNo(@PathVariable String accountNo) {
	log.debug("Find Account Info by Account No : " + accountNo);
	CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(),
		messageUtil.get("success", servletRequest.getLocale()));
	
	accountNo = CryptoUtil.decryptAESHex(accountNo);
	AccountInfo accInfo = accInfoUserService.findByAccountNo(accountNo);
	if(null!=accInfo) {
	    log.debug("Account Info Successfully retrieved ....");
	    Map<String, Object> res = new HashMap<>();
	    res.put("accountInfo", accInfo);
	    response.setData(res);
	} else {
	    response.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
	    response.setMessage(messageUtil.get("error.data.not.found", servletRequest.getLocale()));
	    response.setData(null);
	    return response;
	}
	
	return response;
    }
    
    /**
     * Verify Account Owner
     * 
     * @param request The request contains Username and Account Number.
     * @return Http Status 200 with body contains value of "valid" true if Owner and Account Number valid, 
     * else "valid" value is false. If success, in body will contains User and Account Info.
     */
    @PostMapping("/verifyAccountOwner")
    @ResponseStatus(HttpStatus.OK)
    public CommonResponse verifyAccountOwner(@Valid @RequestBody 
	    CommonRequest<VerifyAccountOwnerRequest> request) {
	log.debug("Verify account owner request: " +BkpmUtil.convertToJson(request));
	CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(),
		messageUtil.get("success", servletRequest.getLocale()));
	
	String username = request.getData().getUsername();
	String accountNo = request.getData().getAccountNo();
	
	// Get User
	User user = userService.findUserByUsername(username);
	if(user==null) {
	    // User not found
	    String message = messageUtil.get("error.data.x.not.found", 
		    new Object[] {new String("USER"), username}, servletRequest.getLocale());
	    response = new CommonResponse(ResponseMessage.DATA_NOT_FOUND.getCode(), message);
	    return response;
	}
	
	// Get Account Info
	AccountInfo accInfo = accInfoUserService.findByAccountNo(accountNo);
	if(accInfo==null) {
	    // Account not found
	    String message = messageUtil.get("error.data.x.not.found", 
		    new Object[] {new String("ACCOUNT"), accountNo}, servletRequest.getLocale());
	    response = new CommonResponse(ResponseMessage.DATA_NOT_FOUND.getCode(), message);
	} else {
	    VerifyAccountOwnerResponse data = new VerifyAccountOwnerResponse();
	    // is username the owner of account number?
	    if(username.equals(
		    accInfo.getAccountCard().getUser().getUsername())) {
		// yes, Valid
		data.setValid(true);
		data.setUser(user);
		data.setAccountInfo(accInfo);
	    } else {
		// no, Not valid
		data.setValid(false);
	    }
	    response.setData(data);
	}
	
	log.debug("Verify account owner response: " +BkpmUtil.convertToJson(response));
	return response;
    }

    /* Overrides: */
}
