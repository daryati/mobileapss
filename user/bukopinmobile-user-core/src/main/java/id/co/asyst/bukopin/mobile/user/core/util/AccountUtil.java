/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.user.core.util;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import id.co.asyst.bukopin.mobile.common.model.BkpmConstants;
import id.co.asyst.bukopin.mobile.user.model.AccountInfoStatusEnum;
import id.co.asyst.bukopin.mobile.user.model.AccountStatusEnum;
import id.co.asyst.bukopin.mobile.user.model.AccountTypeEnum;
import id.co.asyst.bukopin.mobile.user.model.CardStatusEnum;
import id.co.asyst.bukopin.mobile.user.model.entity.AccountCard;
import id.co.asyst.bukopin.mobile.user.model.entity.AccountInfo;
import id.co.asyst.bukopin.mobile.user.model.entity.Product;
import id.co.asyst.bukopin.mobile.user.model.entity.User;
import id.co.asyst.bukopin.mobile.user.model.entity.internal.DebitCardInfo;
import id.co.asyst.bukopin.mobile.user.model.payload.ActivateDebitCardRequest;
import id.co.asyst.bukopin.mobile.user.model.soap.cif.GetInquiryCIFReqType;
import id.co.asyst.bukopin.mobile.user.model.soap.cif.GetInquiryCIFResType;
import id.co.asyst.bukopin.mobile.user.model.soap.cif.HeaderRQ;

/**
 * 
 * 
 * @author Abi Nanel
 * @version $Revision$, Nov 11, 2019
 * @since 2.0
 */
public class AccountUtil {
    /* Constants: */
    private static Logger log = LoggerFactory.getLogger(AccountUtil.class);

    /* Attributes: */

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */

    /* Getters & setters for transient attributes: */

    /* Functionalities: */
    /**
     * Generate Header Request for Get Inquiry CIF
     * 
     * @return The Object request.
     * @throws DatatypeConfigurationException
     */
    public static HeaderRQ generateHeaderRQInquiryCIF() throws DatatypeConfigurationException {
	log.debug("generate header Inqury CIF : {} ");
	HeaderRQ headerRQ = new HeaderRQ();
	HeaderRQ.Credentials credentials = new HeaderRQ.Credentials();
	GregorianCalendar now = new GregorianCalendar();
	XMLGregorianCalendar xmlDateTime;

	now.setTime(new Date());
	xmlDateTime = DatatypeFactory.newInstance().newXMLGregorianCalendar(now);

	// TO DO : non hard code client ID and client TXN ID
	credentials.setClientID("1111222233334444");
	headerRQ.setReqDateTime(xmlDateTime);
	headerRQ.setClientTxnID("TXN-000-001");
	headerRQ.setIsLastTxn(false);
	headerRQ.setCredentials(credentials);

	return headerRQ;
    }

    /**
     * Generate Body Request for Get Inquiry CIF
     * 
     * @return The Object request.
     */
    public static GetInquiryCIFReqType generateBodyInquiryCIF(String cif) {
	log.debug("generate body Inqury CIF : {} ");
	GetInquiryCIFReqType CIFReqType = new GetInquiryCIFReqType();
	GetInquiryCIFReqType.Paginginfo pageInfo = new GetInquiryCIFReqType.Paginginfo();

	// TO DO : non hard code start index and items per page
	pageInfo.setStartindex(BigInteger.valueOf(1));
	pageInfo.setItemsperpage(BigInteger.valueOf(10));
	CIFReqType.setCifnumber(cif);
	CIFReqType.setPaginginfo(pageInfo);

	return CIFReqType;
    }

    /**
     * Generate Set Data Account Card to save
     * 
     * @param debitCard
     *            information debit card
     * @param inquiryCIFRS
     *            result from get Inquiry CIF
     * @param user
     *            information from user's
     * 
     * @return The Object request.
     */
    public static AccountCard setDataAccountCard(ActivateDebitCardRequest debitCard, GetInquiryCIFResType inquiryCIFRS,
	    User user) {
	log.debug("set Data Account Card : {} ");
	AccountCard accCard = new AccountCard();
	accCard.setBirthDate(inquiryCIFRS.getAccInfo().getBirthdate().toGregorianCalendar().getTime());
	accCard.setCardStatus(CardStatusEnum.getEnum(1));
	accCard.setCif(inquiryCIFRS.getAccInfo().getCifnumber());
	accCard.setRegisteredCard(debitCard.getRegisteredCard());
	accCard.setRegisteredOn(new Date());
	accCard.setValidMonth(debitCard.getValidMonth());
	accCard.setValidYear(debitCard.getValidYear());

	// set user info
	user.setCifNumber(inquiryCIFRS.getAccInfo().getCifnumber());
	user.setFirstName(inquiryCIFRS.getAccInfo().getFirstname());
	user.setMiddleName(inquiryCIFRS.getAccInfo().getMiddlename());
	user.setLastName(inquiryCIFRS.getAccInfo().getLastname());
	user.setMobilePhone(inquiryCIFRS.getAccInfo().getMobilephone());
	accCard.setUser(user);

	return accCard;
    }

    /**
     * Generate Set Data Account Info to save
     * 
     * @param inquiryCIFRS
     *            result from get Inquiry CIF
     * @param accCard
     *            information from Account Card
     * 
     * @return The List Object request.
     */
    public static List<AccountInfo> setDataAccountInfo(GetInquiryCIFResType inquiryCIFRS, AccountCard accCard,
	    List<DebitCardInfo> cards, List<Product> listProduct) {
	log.debug("Set List Data Account Info : {} ");
	List<AccountInfo> listAccountInfo = new ArrayList<>();

	for (DebitCardInfo card : cards) {
	    for (GetInquiryCIFResType.Accounts accounts : inquiryCIFRS.getAccounts()) {
		
		String tibcoAccNo = String.valueOf(accounts.getAccnumber());
		if(tibcoAccNo.length()==9) {
		    // padding to 10 with 0
		    tibcoAccNo = StringUtils.leftPad(tibcoAccNo, 10, "0");
		}
		
		if (!card.getAccountNumber().equals(tibcoAccNo)) {
		    continue;
		}

		if (BkpmConstants.CODE_TYPE_SAVING.equals(String.valueOf(accounts.getAcctype()))
			|| BkpmConstants.CODE_TYPE_GIRO.equals(String.valueOf(accounts.getAcctype()))) {
		    AccountInfo accInfo = new AccountInfo();
		    accInfo.setAccountName(accounts.getAccname());
		    accInfo.setAccountNo(card.getAccountNumber());
		    accInfo.setAccountStatus(AccountStatusEnum.getEnum(accounts.getStatus()));
		    accInfo.setAccountType(AccountTypeEnum.getEnum((accounts.getAcctype())));
		    accInfo.setCreateDate(new Date());
		    accInfo.setCif(inquiryCIFRS.getAccInfo().getCifnumber());
		    accInfo.setMainAccount(false);
		    accInfo.setStatus(AccountInfoStatusEnum.getEnum(1));
		    accInfo.setAccountCard(accCard);
		    for (Product product : listProduct) {
			if (product.getPdId() == accounts.getProductid()) {
			    accInfo.setProduct(product);
			    break;
			}
		    }

		    listAccountInfo.add(accInfo);
		}
	    }
	}

	return listAccountInfo;
    }
    
    /**
     * Generate Response Verification
     * 
     * @param inquiryCIFRS Result Get CIF from Tibco
     * @param cards Cards from DB Xlink
     * @param listProduct List all products from PRODUCT table
     * 
     * @return List of Account Info ordered by notActivated ASC
     */
    public static List<AccountInfo> generateResponseVerification(GetInquiryCIFResType inquiryCIFRS, 
	    List<DebitCardInfo> cards, List<Product> listProduct) {
	log.debug("Set response Verification...");
	List<AccountInfo> listAccountInfo = new ArrayList<>();
	
	for (DebitCardInfo card : cards) {
	    // Filter only account number tibco eq with account number xlink db
	    for (GetInquiryCIFResType.Accounts accounts : inquiryCIFRS.getAccounts()) {
		String tibcoAccNo = String.valueOf(accounts.getAccnumber());
		if (tibcoAccNo.length() == 9) {
		    // padding to 10 with 0
		    tibcoAccNo = StringUtils.leftPad(tibcoAccNo, 10, "0");
		}

		if (card.getAccountNumber().equals(tibcoAccNo)) {
		    // Filter only Saving and Giro
		    if (BkpmConstants.CODE_TYPE_SAVING.equals(String.valueOf(accounts.getAcctype()))
			    || BkpmConstants.CODE_TYPE_GIRO.equals(String.valueOf(accounts.getAcctype()))) {
			AccountInfo accInfo = new AccountInfo();
			accInfo.setAccountName(accounts.getAccname());
			accInfo.setAccountNo(card.getAccountNumber());
			accInfo.setAccountStatus(AccountStatusEnum.getEnum(accounts.getStatus()));
			accInfo.setAccountType(AccountTypeEnum.getEnum((accounts.getAcctype())));
			accInfo.setCreateDate(new Date());
			accInfo.setCif(inquiryCIFRS.getAccInfo().getCifnumber());
			accInfo.setMainAccount(false);
			accInfo.setStatus(AccountInfoStatusEnum.getEnum(1));
//			accInfo.setAccountCard(accCard);
			
			// Find product in PRODUCT table
			List<Product> products = listProduct.stream().distinct().filter(
				p -> accounts.getProductid()==p.getPdId()).collect(Collectors.toList());
			Product product = new Product();
			if(products==null || products.isEmpty()) {
			    // if product not exist in PRODUCT table, acc cannot be activated
			    product.setPdId(accounts.getProductid());
			    accInfo.setNotActivated(true);
			} else {
			    product = products.get(0);
			}
			accInfo.setProduct(product);

			listAccountInfo.add(accInfo);
		    }
		}
	    }
	}
	
	// List of Account Info ordered by notActivated ASC
	listAccountInfo.sort(Comparator.comparing(AccountInfo::isNotActivated));
	
	return listAccountInfo;
    }

    /* Overrides: */
}
