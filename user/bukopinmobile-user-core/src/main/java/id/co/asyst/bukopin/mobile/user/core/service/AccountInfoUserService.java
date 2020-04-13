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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.co.asyst.bukopin.mobile.common.core.util.CryptoUtil;
import id.co.asyst.bukopin.mobile.user.core.repository.AccountInfoUserRepository;
import id.co.asyst.bukopin.mobile.user.model.AccountStatusEnum;
import id.co.asyst.bukopin.mobile.user.model.entity.AccountInfo;

/**
 * Service Implementation for managing <code>AccountInfo</code>.
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Nov 6, 2019
 * @since 1.0.Alpha1-SNAPSHOT
 */
@Service("accountInfoUserService")
public class AccountInfoUserService {
    /**
     * Logger.
     */
    private final Logger log = LoggerFactory.getLogger(AccountInfoUserService.class);
    
    /**
     * User PIN Repository
     */
    private final AccountInfoUserRepository accountInfoUserRepository;
    
    /**
     * Constructor
     * 
     * @param userPINRepository The userPINRepository Bean
     */
    public AccountInfoUserService(AccountInfoUserRepository repository) {
	this.accountInfoUserRepository = repository;
    }
    
    /* Functionalities: */
    /**
     * Save Account Info
     * 
     * @param accountInfo The AccountInfo to save
     * @return persisted AccountInfo
     */
    @Transactional
    public AccountInfo save(AccountInfo accountInfo) {
	accountInfo.setAccountNo(
		CryptoUtil.encryptBase64(accountInfo.getAccountNo()));
	accountInfo = accountInfoUserRepository.save(accountInfo);
	accountInfoUserRepository.flush();
	
	AccountInfo ai = decryptResponse(accountInfo);
	return ai;
    }
    
    /**
     * Finds Account Info By CIF
     * 
     * @param cif The CIF number
     * @return persisted AccountInfo
     */
    @Transactional(readOnly=true)
    public List<AccountInfo> findByCIF(String cif) {
	log.debug("Find Account Info with CIF : "+cif);
//	return accountInfoUserRepository.findByCif(cif);
	return decryptResponse(accountInfoUserRepository.findByCif(cif));
    }
    
    /**
     * Finds Account Info By Account Number
     * 
     * @param accountNo The Account Number
     * @return persisted AccountInfo
     */
    @Transactional(readOnly=true)
    public AccountInfo findByAccountNo(String accountNo) {
    	log.debug("Find Account Info with account No : "+accountNo);
    	return decryptResponse(accountInfoUserRepository.findAccountInfoByAccountNo(
    		CryptoUtil.encryptBase64(accountNo)));
    }
    
    /**
     * Finds Account Info By Active CIF
     * 
     * @param cif  The CIF number
     * @return persisted AccountInfo
     */
    @Transactional(readOnly=true)
    public List<AccountInfo> findByActiveCif(String cif){
	log.debug("Find Active Account Info with CIF : " + cif);
	AccountStatusEnum accountStatus = AccountStatusEnum.ACTIVE;
//	return accountInfoUserRepository.findActiveByCif(cif, accountStatus);
	return decryptResponse(accountInfoUserRepository.findActiveByCif(cif, accountStatus));
    }
    
    /**
     * Deletes Account Info By CIF
     * 
     * @param cif  The CIF number
     */
    @Transactional
    public void deleteByCIF(String cif) {
	log.debug("remove All Account Info with CIF : "+cif);
	accountInfoUserRepository.deleteByCif(cif);
    }
    
    /**
     * Deletes Account Info By Account Card Id
     * 
     * @param id The Account Card's Id
     */
    @Transactional
    public void deleteByAccountCardId(Long id) {
	log.debug("remove All Account Info with account Card ID : "+id);
	accountInfoUserRepository.deleteByAccountCardId(id);
    }
    
    /**
     * Save Multiple Account Info
     * 
     * @param listAccInfo List of Account Info to save.
     */
    @Transactional
    public void saveAll(List<AccountInfo> listAccInfo) {
	log.debug("Save All Account Info with Account Card : "+ listAccInfo.get(0).getAccountCard().getRegisteredCard());
	listAccInfo.forEach(ai -> ai.setAccountNo(
		CryptoUtil.encryptBase64(ai.getAccountNo())));
	accountInfoUserRepository.saveAll(listAccInfo);
	accountInfoUserRepository.flush();
    }
    
    /**
     * Decrypt encrypted field.
     * 
     * @param accountInfos List Account Card to decrypt.
     * @return List Account Card with decrypted fields.
     */
    private List<AccountInfo> decryptResponse(List<AccountInfo> accountInfos) {
	if(accountInfos==null) return null;
	List<AccountInfo> decrypted = new ArrayList<>();
	for(AccountInfo ai : accountInfos) {
	    decrypted.add(decryptResponse(ai));
	}
	
	return decrypted;
    }
    
    /**
     * Decrypt encrypted field.
     * 
     * @param accountInfo The Account Card to decrypt.
     * @return Account Card with decrypted field.
     */
    private AccountInfo decryptResponse(AccountInfo accountInfo) {
	// decrypt base64
	//accountInfo.setAccountNo(CryptoUtil.decryptBase64(accountInfo.getAccountNo()));
	if(accountInfo==null) return null;
    	AccountInfo acc = new AccountInfo();
    		acc.setAccountBalance(accountInfo.getAccountBalance());
    		acc.setAccountCard(accountInfo.getAccountCard());
    		acc.setAccountName(accountInfo.getAccountName());
    		acc.setAccountNo(CryptoUtil.decryptBase64(accountInfo.getAccountNo()));
    		acc.setAccountStatus(accountInfo.getAccountStatus());
    		acc.setAccountType(accountInfo.getAccountType());
    		acc.setCif(accountInfo.getCif());
    		acc.setCreateDate(accountInfo.getCreateDate());
    		acc.setId(accountInfo.getId());
    		acc.setMainAccount(accountInfo.isMainAccount());
    		acc.setProduct(accountInfo.getProduct());
    		acc.setStatus(accountInfo.getStatus());
    	
	return acc;
    }
    
    private AccountInfo decryptAesHexResponse(AccountInfo accountInfo) {
    	// decrypt base64
    	//accountInfo.setAccountNo(CryptoUtil.decryptBase64(accountInfo.getAccountNo()));
    	if(accountInfo==null) return null;
        	AccountInfo acc = new AccountInfo();
        		acc.setAccountBalance(accountInfo.getAccountBalance());
        		acc.setAccountCard(accountInfo.getAccountCard());
        		acc.setAccountName(accountInfo.getAccountName());
        		acc.setAccountNo(CryptoUtil.decryptAESHex(accountInfo.getAccountNo()));
        		acc.setAccountStatus(accountInfo.getAccountStatus());
        		acc.setAccountType(accountInfo.getAccountType());
        		acc.setCif(accountInfo.getCif());
        		acc.setCreateDate(accountInfo.getCreateDate());
        		acc.setId(accountInfo.getId());
        		acc.setMainAccount(accountInfo.isMainAccount());
        		acc.setProduct(accountInfo.getProduct());
        		acc.setStatus(accountInfo.getStatus());
        	
    	return acc;
        }

    /* Overrides: */
}
