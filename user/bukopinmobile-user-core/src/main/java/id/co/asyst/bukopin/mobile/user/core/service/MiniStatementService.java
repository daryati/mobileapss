package id.co.asyst.bukopin.mobile.user.core.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.soap.DetailEntry;
import javax.xml.ws.Holder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.co.asyst.bukopin.mobile.common.core.util.BkpmUtil;
import id.co.asyst.bukopin.mobile.common.model.BkpmConstants;
import id.co.asyst.bukopin.mobile.user.core.service.soap.PortType;
import id.co.asyst.bukopin.mobile.user.core.service.soap.StatementPT;
import id.co.asyst.bukopin.mobile.user.core.util.AuthUtil;
import id.co.asyst.bukopin.mobile.user.model.AccountBalanceReq;
import id.co.asyst.bukopin.mobile.user.model.AccountBalanceRes;
import id.co.asyst.bukopin.mobile.user.model.AccountBalanceTransactionDetailRes;
import id.co.asyst.bukopin.mobile.user.model.AccountInfoRes;
import id.co.asyst.bukopin.mobile.user.model.MiniStatementReq;
import id.co.asyst.bukopin.mobile.user.model.MiniStatementRes;
import id.co.asyst.bukopin.mobile.user.model.MiniStatementTransactionDetailRes;
import id.co.asyst.bukopin.mobile.user.model.TotalBalanceRes;
import id.co.asyst.bukopin.mobile.user.model.soap.statement.Err;
import id.co.asyst.bukopin.mobile.user.model.soap.statement.ErrList;
import id.co.asyst.bukopin.mobile.user.model.soap.statement.Fault;
import id.co.asyst.bukopin.mobile.user.model.soap.statement.GetMiniStatementReqType;
import id.co.asyst.bukopin.mobile.user.model.soap.statement.GetMiniStatementResType;
import id.co.asyst.bukopin.mobile.user.model.soap.statement.GetMiniStatementResType.Transaction;
import id.co.asyst.bukopin.mobile.user.model.soap.statement.HeaderRQ;
import id.co.asyst.bukopin.mobile.user.model.soap.statement.HeaderRQ.Credentials;
import id.co.asyst.bukopin.mobile.user.model.soap.statement.HeaderRS;

/**
 * Service Implementation for managing Account Balance.
 * 
 * @author Kartika Dwi Handini
 * @version $Revision$, (Created on Nov 05, 2019)
 * @since 1.0.Alpha1
 */
@Service
@Transactional
public class MiniStatementService {

    /**
     * Logger.
     */
    private final Logger log = LoggerFactory.getLogger(MiniStatementService.class);


    /**
     * Port Type (SOAP Manager).
     */
    @Autowired
    private StatementPT portType;
    
    

	public MiniStatementRes getMiniStatement (MiniStatementReq miniStatementReq) { 
    	// get transaction ID 
    	String txId = BkpmUtil.generateTrxId();
    	
        log.debug("get mini statement "+txId);
        MiniStatementRes miniStatementResult = new MiniStatementRes();
        
      //set request
        HeaderRQ headerRQ = new HeaderRQ();
        headerRQ.setClientTxnID(txId);
        
        Credentials credential= new Credentials();
        credential.setClientID(BkpmConstants.CLIENT_ID);        
        headerRQ.setCredentials(credential);
		try {
			GregorianCalendar gregCal = new GregorianCalendar();
			gregCal.setTime(new Date());
			XMLGregorianCalendar xmlGregCal;
			xmlGregCal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregCal);
			xmlGregCal.setTimezone(DatatypeConstants.FIELD_UNDEFINED);
	        headerRQ.setReqDateTime(xmlGregCal);
		} catch (DatatypeConfigurationException e1) {
			e1.printStackTrace();
		}
        
		
		
        GetMiniStatementReqType getMiniStatementRQ = new GetMiniStatementReqType();
        getMiniStatementRQ.setAccNo(miniStatementReq.getAccountNumber());
        getMiniStatementRQ.setAccType(miniStatementReq.getAccountType().toString());
        
        Holder<HeaderRS> headerRS = new Holder<HeaderRS>();        
        Holder<GetMiniStatementResType> getMiniStatementRS = new Holder<GetMiniStatementResType>();
        
        try {
        	portType.getMiniStatement(
        			headerRQ, getMiniStatementRQ, headerRS, getMiniStatementRS);
			
        	if(!"".equalsIgnoreCase(getMiniStatementRS.value.getAccInfo().getAccNo()) && 
        			null != getMiniStatementRS.value.getAccInfo().getAccNo()) {
        		AccountInfoRes accInfo = new AccountInfoRes();
        		accInfo.setName(getMiniStatementRS.value.getAccInfo().getName());
        		accInfo.setLastBalance(getMiniStatementRS.value.getAccInfo().getLastBalance());
        		accInfo.setAccountNumber(getMiniStatementRS.value.getAccInfo().getAccNo());
        		miniStatementResult.setAccountInfo(accInfo);        		
        		
        		
        		List<MiniStatementTransactionDetailRes> transactionList = new ArrayList<>();
        		for(Transaction tx : getMiniStatementRS.value.getTransaction()) {
        			MiniStatementTransactionDetailRes detail = new MiniStatementTransactionDetailRes();
        			detail.setDescription(tx.getTrxDesc());
        			detail.setPosting(tx.getPosting());
        			detail.setTransactionAmount(tx.getTrxAmount());
        			detail.setTransactionDateTime(tx.getTrxDateTime().toGregorianCalendar().getTime());
        			detail.setTransactionId(tx.getTrxID());
        			transactionList.add(detail);
        		}
        		miniStatementResult.setTransactionDetails(transactionList);
        		
        		
        	} else {
        		log.error("error get mini Statement");
        	}
        	
			
		} catch (Fault e) {
			e.printStackTrace();
		}
        
        return miniStatementResult;
    }
    
    
    
/*    
    
    *//**
     * Saves a InquiryTransactionRepository.
     *
     * @param schedule The UserToken to save.
     * @return The persisted UserToken.
     *//*
    public AccountInfoReq save(AccountInfoReq userToken) {
        log.debug("Request to save InquiryTransactionReq : {}");
        return inquiryTransactionRepository.save(userToken);
    }

    *//**
     * Get all userTokens.
     *
     * @return The list of all UserTokens.
     *//*
    @Transactional(readOnly = true)
    public List<AccountInfoReq> findAll() {
        log.debug("Request to get all UserTokens");
        return inquiryTransactionRepository.findAll();
    }

    *//**
     * Get all schedules (pageable).
     *
     * @param pageable The pagination information.
     * @return The list of entities.
     *//*
    @Transactional(readOnly = true)
    public Page<AccountInfoReq> findAll(Pageable pageable) {
        log.debug("Request to get all UserTokens");
        return inquiryTransactionRepository.findAll(pageable);
    }

    *//**
     * Get one UserToken by id.
     *
     * @param id the id of the entity
     * @return the entity
     *//*
    @Transactional(readOnly = true)
    public Optional<AccountInfoReq> findOne(Long id) {
        log.debug("Request to get UserToken : {}", id);
        return inquiryTransactionRepository.findById(id);
    }

    *//**
     * Delete the UserToken by id.
     *
     * @param id the id of the entity
     *//*
    public void delete(Long id) {
        log.debug("Request to delete UserToken : {}", id);
        inquiryTransactionRepository.deleteById(id);
    }*/
}
