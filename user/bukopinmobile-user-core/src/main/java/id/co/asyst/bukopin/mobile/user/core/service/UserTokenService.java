package id.co.asyst.bukopin.mobile.user.core.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.co.asyst.bukopin.mobile.common.core.service.impl.BkpmService;
import id.co.asyst.bukopin.mobile.common.core.util.MessageUtil;
import id.co.asyst.bukopin.mobile.common.model.BkpmConstants;
import id.co.asyst.bukopin.mobile.common.model.ResponseMessage;
import id.co.asyst.bukopin.mobile.common.model.payload.CommonResponse;
import id.co.asyst.bukopin.mobile.service.core.CentagateService;
import id.co.asyst.bukopin.mobile.service.model.payload.CentagateCommonResponse;
import id.co.asyst.bukopin.mobile.service.model.payload.ChallengeQuestionRequest;
import id.co.asyst.bukopin.mobile.user.core.repository.UserTokenRepository;
import id.co.asyst.bukopin.mobile.user.core.util.AuthUtil;
import id.co.asyst.bukopin.mobile.user.model.entity.User;
import id.co.asyst.bukopin.mobile.user.model.payload.VerifyTokenOwnerResponse;
import id.co.asyst.bukopin.mobile.user.model.security.UserToken;
import id.co.asyst.foundation.service.connector.Services;

/**
 * Service Implementation for managing UserToken.
 * 
 * @author Stephen Dharma
 * @version $Revision$, (Created on Oct 24, 2019)
 * @since 1.0.Alpha1
 */
@Service
@Transactional
public class UserTokenService {

    /**
     * Logger.
     */
    private final Logger log = LoggerFactory.getLogger(UserTokenService.class);

    /**
     * User Token Repository.
     */
    private final UserTokenRepository userTokenRepository;
    
    @Autowired
    private UserService userService;
    
    /**
     * Bkpm Common Service
     */
    @Autowired
    private BkpmService commonService;
    
    /**
     * Get Message Util
     */
    @Autowired
    private MessageUtil messageUtil;

    /**
     * Constructor.
     * 
     * @param userTokenRepository User Token Repository.
     */
    public UserTokenService(UserTokenRepository userTokenRepository) {
        this.userTokenRepository = userTokenRepository;
    }

    /**
     * Saves a UserToken.
     *
     * @param schedule The UserToken to save.
     * @return The persisted UserToken.
     */
    public UserToken save(UserToken userToken) {
        return userTokenRepository.save(userToken);
    }

    /**
     * Get all userTokens.
     *
     * @return The list of all UserTokens.
     */
    @Transactional(readOnly = true)
    public List<UserToken> findAll() {
        return userTokenRepository.findAll();
    }

    /**
     * Get all schedules (pageable).
     *
     * @param pageable The pagination information.
     * @return The list of entities.
     */
    @Transactional(readOnly = true)
    public Page<UserToken> findAll(Pageable pageable) {
        return userTokenRepository.findAll(pageable);
    }

    /**
     * Get one UserToken by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<UserToken> findOne(String id) {
        return userTokenRepository.findById(id);
    }
    
    @Transactional(readOnly = true)
    public UserToken findByToken(String token) {
        return userTokenRepository.getUserTOkenByToken(token);
    }

    /**
     * Delete the UserToken by id.
     *
     * @param id the id of the entity
     */
    public void delete(String id) {
        userTokenRepository.deleteById(id);
    }
    
    /**
     * Find Token Owner and Status
     * 
     * @param username
     *            The Username of User's to find
     * @param token
     *            The login token
     * @param active
     *            The token status
     * @return Persisted UserToken
     */
    public UserToken findTokenOwnerAndStatus(String username, String token, boolean tokenStatus) {
	return userTokenRepository.findByUsernameAndTokenAndActive(username, token, tokenStatus);
    }
    
    /**
     * Verify Token Owner
     * 
     * @param username The User's username
     * @param token The User's Token
     * @return Return object contains value of "valid" true if Owner and
     *         Token valid, else "valid" value is false. If success, in
     *         body will contains User and UserToken.
     */
    public VerifyTokenOwnerResponse verifyTokenOwner(String username, String token) {
	VerifyTokenOwnerResponse data = new VerifyTokenOwnerResponse();
	
	// find active token
	UserToken userToken = userTokenRepository.findByUsernameAndTokenAndActive(username, token, true);
	if(null == userToken) {
	    // token not found
	    data.setValid(false);
	} else {
	    // token found, set data response
	    data.setUserToken(userToken);
	    data.setUser(userService.findUserByUsername(username));
	    data.setValid(true);
	}
	
	return data;
    }
    
    /**
     * Verify Token and Phone Owner
     * 
     * @param username The User's username
     * @param token The User's Token
     * @param pohoneId The User's Phone Identity
     * @return Return object contains value of "valid" true if Owner and
     *         Token valid, else "valid" value is false. If success, in
     *         body will contains User and UserToken.
     */
    public VerifyTokenOwnerResponse verifyTokenAndPhoneOwner(String username, String token, String phoneId) {
	VerifyTokenOwnerResponse data = new VerifyTokenOwnerResponse();
	if(phoneId==null) {
	    phoneId = "";
	}
	
	// find active token
	UserToken userToken = userTokenRepository.findByUsernameAndTokenAndActive(username, token, true);
	User user = userService.findUserByUsername(username);
	if(null == userToken || null == user || !phoneId.equals(user.getPhoneIdentity())) {
	    // token/ user not valid
	    data.setValid(false);
	} else {
	    // token and user found, set data response
	    data.setUserToken(userToken);
	    data.setUser(user);
	    data.setValid(true);
	}
	
	return data;
    }
    
    /**
     * Validate Login Session
     * 
     * @param token The user's token.
     * @throws Exception 
     */
    public CommonResponse validateLogginSession(String token, Locale locale) throws Exception {
	CommonResponse response = new CommonResponse(ResponseMessage.SUCCESS.getCode(),
		messageUtil.get("success", locale));
	UserToken userToken = this.findByToken(token);
	// Check double device
	if (null != userToken) {
//	    log.info("token login exist");
	    // Check session
	    boolean isSessionValid = checkSession(userToken);
	    User user = userService.findUserByUsername(userToken.getUsername());
	    if (isSessionValid) {
//		log.info("session valid");
		// Check User locked
		if(user.isLocked()) { // user locked in db
		    String username = user.getUsername();
//		    log.error("user locked in db: {}",username);
		    // Check locked status in centagate
		    ChallengeQuestionRequest getQuestReq = AuthUtil.generateChallengeRequest(username, "");
		    CentagateCommonResponse getQuestCTGRes = Services.create(CentagateService.class)
			    .requestQuestion(getQuestReq).execute().body();
//		    log.info("check locked status to ctg: {}",username);
		    if (BkpmConstants.CODE_CTG_SUCCESS.equals(getQuestCTGRes.getCode())) {
//			log.info("user is not locked in ctg: {}",username);
			// unlock user status db
			user.setLocked(false);
			user = userService.save(user);
		    } else if (BkpmConstants.CODE_STG_USER_LOCKED.equals(getQuestCTGRes.getCode())) {
//			log.error("user locked in ctg: {}", username);
//			log.error("User Locked: {}", user.getUsername());
			// logout
			userService.logoutCentagate(user, token, locale);

			// set response locked
			response = new CommonResponse(ResponseMessage.USER_IS_LOCKED.getCode(),
				messageUtil.get("user.locked", locale));
		    }
		}
	    } else {
		// session timeout
//		log.error("Session Timeout: " + userToken.getUsername());

		// logout
		userService.logoutCentagate(user, token, locale);
		
		// return response
		String errorCode = ResponseMessage.SESSION_TIME_OUT.getCode();
		String errorMessage = messageUtil.get("error.session.timeout", locale);
		response.setCode(errorCode);
		response.setMessage(errorMessage);
	    }
	} else {
	    // status = false;
//	    log.error("double device login ");

	    String errorCode = ResponseMessage.ERROR_DOUBLE_LOGIN.getCode();
	    String errorMessage = messageUtil.get("error.double.login", locale);
	    response.setCode(errorCode);
	    response.setMessage(errorMessage);
	}
	
	return response;
    }
    
    /**
     * Check Validity of Login Session
     */
    private boolean checkSession(UserToken token) {
	boolean isValid = false;

	// Get duration from Configuration
	String duration = commonService.getConfigValue(BkpmConstants.KEY_SESSION_TIME_OUT);
	duration = StringUtils.isBlank(duration) ? "5" : duration;
	int intDuration = Integer.valueOf(duration);

	// Get User Token
	Calendar cal = Calendar.getInstance();
	Date now = cal.getTime();
	cal.setTime(token.getUpdateDate()); // expired session
	cal.add(Calendar.MINUTE, intDuration);

	// is session not expired?
	if (!now.after(cal.getTime())) {
	    isValid = true;
	    // Extend login session
	    token.setUpdateDate(now);

	    // Update user token
	    this.save(token);
	}

	return isValid;
    }
}
