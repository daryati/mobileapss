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

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.co.asyst.bukopin.mobile.common.core.util.CryptoUtil;
import id.co.asyst.bukopin.mobile.user.core.repository.AccountCardRepository;
import id.co.asyst.bukopin.mobile.user.model.entity.AccountCard;
import id.co.asyst.bukopin.mobile.user.model.entity.AccountInfo;
import id.co.asyst.bukopin.mobile.user.model.entity.User;

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
    
    public void saveAll(List<AccountCard> listAccCard){
	log.debug("Save all Account card with username : {} "+ listAccCard.get(0).getUser().getUsername());
	for (int i = 0; i < listAccCard.size(); i++) {
	    listAccCard.get(i).setRegisteredCard(CryptoUtil.encryptBase64(
		    listAccCard.get(i).getRegisteredCard()));
	    if(null != listAccCard.get(i).getAccounts()) {
		listAccCard.get(i).getAccounts().forEach(ai -> {
		    String encrypted = CryptoUtil.encryptBase64(ai.getAccountNo());
		    ai.setAccountNo(encrypted);
		});
	    }
	}
	accountCardRepository.saveAll(listAccCard);
	accountCardRepository.flush();
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
     * Find Account Cards By Username
     * 
     * @param username The User's username
     * @return List of User's Account Cards.
     */
    @Transactional(readOnly=true)
    public List<AccountCard> findCardsByUsername(String username) {
	log.debug("Find Account Cards by username : {} " + username);
	List<AccountCard> accountCards = accountCardRepository.findByUsername(username);
	if(accountCards!=null && !accountCards.isEmpty()) {
	    accountCards = decryptResponse(accountCards);
	}
	
	return accountCards;
//	return accountCardRepository.findByUsername(username);
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

    /* Overrides: */
}
