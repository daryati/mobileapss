/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.user.core.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.co.asyst.bukopin.mobile.common.core.util.CryptoUtil;
import id.co.asyst.bukopin.mobile.common.model.BkpmConstants;
import id.co.asyst.bukopin.mobile.user.core.config.GetConfiguration;
import id.co.asyst.bukopin.mobile.user.core.repository.AccountCardRepository;
import id.co.asyst.bukopin.mobile.user.model.AccountBalanceRes;
import id.co.asyst.bukopin.mobile.user.model.AccountStatusEnum;
import id.co.asyst.bukopin.mobile.user.model.CIFStatusEnum;
import id.co.asyst.bukopin.mobile.user.model.entity.AccountCard;
import id.co.asyst.bukopin.mobile.user.model.entity.AccountInfo;
import id.co.asyst.bukopin.mobile.user.model.entity.Product;
import id.co.asyst.bukopin.mobile.user.model.entity.User;
import id.co.asyst.bukopin.mobile.user.model.entity.internal.DebitCardInfo;
import id.co.asyst.bukopin.mobile.user.model.soap.account.GetAccountBalanceResType;
import id.co.asyst.bukopin.mobile.user.model.soap.cif.GetInquiryCIFResType;
import id.co.asyst.bukopin.mobile.user.model.soap.cif.GetInquiryCIFResType.Accounts;

/**
 * Service Implementation for managing <code>AccountCard</code>.
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Nov 6, 2019
 * @since 1.0.Alpha1
 */
@Service
public class AccountCardService {
    /**
     * Logger.
     */
    private final Logger log = LoggerFactory.getLogger(AccountCardService.class);
    
    /**
     * User PIN Repository
     */
    private final AccountCardRepository accountCardRepository;
    
    @Autowired
    private UserService userService;
    
   /**
    * Account Balance Service
    */
   @Autowired
   private AccountBalanceService accountBalanceService;
    
    /**
     * Constructor
     * 
     * @param userPINRepository The userPINRepository Bean
     */
    public AccountCardService(AccountCardRepository repository) {
	this.accountCardRepository = repository;
    }
    
    /* Functionalities: */
    // TODO: is each User only has 1 accountCard?
    /**
     * Find AccountCard by Username
     * 
     * @param username user's username
     * @return AccountCard by username
     */
    @Transactional(readOnly=true)
    public AccountCard findByUsername(String username) {
	log.debug("Find User by username : {} " + username);
	User user = userService.findUserByUsername(username);
	if(user==null) {
	    return null;
	} else {
	    return decryptResponse(accountCardRepository.findByUserId(user.getId()));
//	    return accountCardRepository.findByUserId(user.getId());
	}
    }
    
    /**
     * Find Account Cards By Username
     * 
     * @param username The User's username
     * @return List of User's Account Cards.
     */
    @Transactional(readOnly=true)
    public List<AccountCard> findListByUsername(String username) {
	log.debug("Find User by username : {} " + username);
	User user = userService.findUserByUsername(username);
	if(user==null) {
	    return null;
	} else {
	    List<AccountCard> list = accountCardRepository.findListByUserId(user.getId());
	    List<AccountCard> result = new ArrayList<>();
	    for(AccountCard ac: list) {
		result.add(decryptResponse(ac));
	    }
	    return result;
	    //return decryptResponse(accountCardRepository.findByUserId(user.getId()));
//	    return accountCardRepository.findByUserId(user.getId());
	}
    }
    
    /**
     * Save AccountCard
     * 
     * @param accountCard
     * @return already save AccountCard
     */
    @Transactional
    public AccountCard save(AccountCard accountCard) {
	log.debug("Save Account Card with username : {} "+ accountCard.getUser().getUsername());
	accountCard.setRegisteredCard(CryptoUtil.encryptBase64(
		accountCard.getRegisteredCard()));
	if(null!=accountCard.getAccounts()) {
	    accountCard.getAccounts().forEach(ai -> {
		String encrypted = CryptoUtil.encryptBase64(ai.getAccountNo());
		ai.setAccountNo(encrypted);
	    });
	}
	accountCardRepository.save(accountCard);
	
	AccountCard ac = decryptResponse(accountCard); 
	return ac;
    }
    
    /**
     * Save List Account Card
     * 
     * @param listAccCard
     * @return
     */
    public List<AccountCard> saveAll(List<AccountCard> listAccCard){
	log.debug("Save all Account card with username : {} "+ listAccCard.get(0).getUser().getUsername());
	List<AccountCard> listAcDecrypt = new ArrayList<>();
	for (int i = 0; i < listAccCard.size(); i++) {
	    listAccCard.get(i).setRegisteredCard(CryptoUtil.encryptBase64(
		    listAccCard.get(i).getRegisteredCard()));
	    if(null != listAccCard.get(i).getAccounts()) {
		listAccCard.get(i).getAccounts().forEach(ai -> {
		    String encrypted = CryptoUtil.encryptBase64(ai.getAccountNo());
		    ai.setAccountNo(encrypted);
		});
	    }
	    AccountCard ac = decryptResponse(listAccCard.get(i));
	    listAcDecrypt.add(ac);
	}
	accountCardRepository.saveAll(listAccCard);
	accountCardRepository.flush();
	
	return listAcDecrypt;
    }
    
    /**
     * Find AccountCard by CIF
     * 
     * @param cif user's CIF
     * @return AccountCard by CIF
     */
    @Transactional(readOnly=true)
    public AccountCard findByCIF(String cif) {
	log.debug("Find Account Card with CIF : {} "+cif);
	AccountCard ac = accountCardRepository.findByCif(cif);
	if(ac==null) {
	    return ac;
	} else {
	    return decryptResponse(ac);
	}
//	return accountCardRepository.findByCif(cif);
    }
    
    /**
     * Find AccountCard by Registered Card
     * 
     * @param registCard user's Registered Card
     * @return AccountCard by Registered Card
     */
    @Transactional(readOnly=true)
    public AccountCard findByRegisteredCard(String registCard) {
	log.debug("Find Account Card with Registered Card : {} "+registCard);
	registCard = CryptoUtil.encryptBase64(registCard);
	AccountCard accountCard = accountCardRepository.findByRegisteredCard(registCard);
	if(accountCard!=null) {
	    accountCard = decryptResponse(accountCard);
	}
	
	return accountCard;
//	return accountCardRepository.findByRegisteredCard(registCard);
    }
    
    /**
     * Decrypt encrypted field.
     * 
     * @param accountCards List Account Card to decrypt.
     * @return List Account Card with decrypted fields.
     */
    private List<AccountCard> decryptResponse(List<AccountCard> accountCards) {
	List<AccountCard> decrypted = new ArrayList<>();
	for(AccountCard ai : accountCards) {
	    decrypted.add(decryptResponse(ai));
	}
	
	return accountCards;
    }
    
    /**
     * Decrypt encrypted field.
     * 
     * @param accountCard The Account Card to decrypt.
     * @return Account Card with decrypted field.
     */
    //@Transactional(readOnly=true)
    private AccountCard decryptResponse(AccountCard accountCard) {
	// decrypt base64
	AccountCard accCard = new AccountCard();

	List<AccountInfo> ls = new ArrayList<>();
	if (accountCard.getAccounts() != null) {
	    for (AccountInfo a : accountCard.getAccounts()) {
		AccountInfo acc = new AccountInfo();
		acc.setAccountBalance(a.getAccountBalance());
		acc.setAccountCard(a.getAccountCard());
		acc.setAccountName(a.getAccountName());
		acc.setAccountNo(CryptoUtil.decryptBase64(a.getAccountNo()));
		acc.setAccountStatus(a.getAccountStatus());
		acc.setAccountType(a.getAccountType());
		acc.setCif(a.getCif());
		acc.setCreateDate(a.getCreateDate());
		acc.setId(a.getId());
		acc.setMainAccount(a.isMainAccount());
		acc.setProduct(a.getProduct());
		acc.setStatus(a.getStatus());
		ls.add(acc);
	    }
	    accCard.setAccounts(ls);
	}
	accCard.setBirthDate(accountCard.getBirthDate());
	accCard.setCardStatus(accountCard.getCardStatus());
	accCard.setCif(accountCard.getCif());
	accCard.setId(accountCard.getId());
	accCard.setRegisteredCard(CryptoUtil.decryptBase64(accountCard.getRegisteredCard()));
	accCard.setRegisteredOn(accountCard.getRegisteredOn());
	accCard.setUser(accountCard.getUser());
	accCard.setValidMonth(accountCard.getValidMonth());
	accCard.setValidYear(accountCard.getValidYear());

	return accCard;
    }
    
    /**
     * Filter Account Verification
     * <p> 
     * Filter data account from tibco before save to database. The filters are:
     * <ol>
     * <li>Filter saving and giro only</i>
     * <li>Filter by card number (only include accinfo in xlink db)</i>
     * <li>Filter by cifStatus !closed</i>
     * </ol>
     * </p>
     * @param tibcoAccountInfo All of account info from tibco.
     * @param products All of products from db mbanking.
     * @return Map filtered account info to save and cif status.
     */
    public Map<String, Object> filterAccountVerification(List<GetInquiryCIFResType.Accounts> tibcoAccountInfo,
	    List<DebitCardInfo> xlinkData, List<Integer> pdidsDb, List<Integer> blackListPdid) {
	List<String> savingAccNo = new ArrayList<>(); // all saving account number to check status
	List<String> xlinkAccNo = xlinkData.stream().map(DebitCardInfo::getAccountNumber)
		.collect(Collectors.toList());
	
	// holder accno & cif status
	Map<String,Integer> accCifStatus = new HashMap<>();
	
	// list accountInfo to be response
	List<GetInquiryCIFResType.Accounts> accInfoResp = new ArrayList();
	// filtered accinfo (can be activated only)
	List<GetInquiryCIFResType.Accounts> filteredAccInfo = new ArrayList();
	for (GetInquiryCIFResType.Accounts accounts : tibcoAccountInfo) {
	    // Filter Saving and Giro only
	    if (BkpmConstants.CODE_TYPE_SAVING.equals(String.valueOf(accounts.getAcctype()))
		    || BkpmConstants.CODE_TYPE_GIRO.equals(String.valueOf(accounts.getAcctype()))) {
		String tibcoAccountNo = String.valueOf(accounts.getAccnumber());
		// padding to 10 with 0, because account number in db is 10 digit in length and
		// left padded with 0.
		tibcoAccountNo = StringUtils.leftPad(tibcoAccountNo, BkpmConstants.BUKOPIN_ACCNO_LENGTH,
			BkpmConstants.BUKOPIN_ACCNO_PADDING);
		// Filter by Card Number. Check is tibcoAccno exist in current card
		if (xlinkAccNo.contains(tibcoAccountNo)) {
		    // add saving account number to list, to get account balance
		    if (BkpmConstants.CODE_TYPE_SAVING.equals(String.valueOf(accounts.getAcctype()))) {
			savingAccNo.add(tibcoAccountNo);
		    } else if (BkpmConstants.CODE_TYPE_GIRO.equals(String.valueOf(accounts.getAcctype()))) {
			// Check status giro
			if(accounts.getStatus()==AccountStatusEnum.ACTIVE.getValue() ||
				accounts.getStatus() == AccountStatusEnum.PASSIVE.getValue()) {
			    // account status to save in accinfo table
			    accCifStatus.put(tibcoAccountNo, Integer.valueOf(accounts.getStatus()));

			    // Make sure account's pdid is exist in table PRODUCT
			    if (pdidsDb.contains(accounts.getProductid())
				    // Exclude accounts from tibco with blackListPdid
				    && !blackListPdid.contains(accounts.getProductid())) {

				// filtered accs info
				filteredAccInfo.add(accounts);
			    }
			    
			    // add response list
			    accInfoResp.add(accounts);
			}
		    }
		    
		}
	    }
	}
	
	// Filter by cif status
	if(!savingAccNo.isEmpty()) {
	    List<GetAccountBalanceResType.Transaction> transactions = accountBalanceService.callAccBalanceTibco(savingAccNo, 
		    new BigInteger(BkpmConstants.CODE_TYPE_SAVING));
	    
	    for(GetAccountBalanceResType.Transaction trx: transactions) {
		String accountNoTrx = StringUtils.leftPad(String.valueOf(trx.getAccNo()),
			BkpmConstants.BUKOPIN_ACCNO_LENGTH, BkpmConstants.BUKOPIN_ACCNO_PADDING);
		// account status to save in accinfo table
		accCifStatus.put(accountNoTrx, Integer.valueOf(trx.getCifStatus()));
		
		// only add active and passive account info
		if (CIFStatusEnum.ACTIVE.getValue() == Integer.valueOf(trx.getCifStatus())
			|| CIFStatusEnum.PASSIVE.getValue() == Integer.valueOf(trx.getCifStatus())) {
		    
		    // get account by account no
		    GetInquiryCIFResType.Accounts accounts = tibcoAccountInfo.stream().filter(acc -> {
			String accountNoTibco = StringUtils.leftPad(String.valueOf(acc.getAccnumber()),
				BkpmConstants.BUKOPIN_ACCNO_LENGTH, BkpmConstants.BUKOPIN_ACCNO_PADDING);
			return accountNoTrx.equals(accountNoTibco);
		    }).findFirst().orElse(null);
		    
		    if(accounts!=null) {
			// Make sure account's pdid is exist in table PRODUCT
			if (pdidsDb.contains(accounts.getProductid())
				// Exclude accounts from tibco with blackListPdid
				&& !blackListPdid.contains(accounts.getProductid())) {

			    // filtered accs info
			    filteredAccInfo.add(accounts);
			}
			accInfoResp.add(accounts);
		    }
		}
	    }
	}
	
	Map<String, Object> mapResult = new HashMap<>();
	mapResult.put("ACC_INFO", filteredAccInfo);
	mapResult.put("ACC_INFO_RESP", accInfoResp);
	mapResult.put("CIF_STATUS", accCifStatus);
	
	return mapResult;
    }

    /* Overrides: */
}
