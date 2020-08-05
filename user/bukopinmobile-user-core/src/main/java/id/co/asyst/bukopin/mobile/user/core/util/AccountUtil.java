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
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import id.co.asyst.bukopin.mobile.common.core.util.BkpmUtil;
import id.co.asyst.bukopin.mobile.common.model.BkpmConstants;
import id.co.asyst.bukopin.mobile.user.core.config.GetConfiguration;
import id.co.asyst.bukopin.mobile.user.core.service.AccountBalanceService;
import id.co.asyst.bukopin.mobile.user.core.service.ProductService;
import id.co.asyst.bukopin.mobile.user.model.AccountBalanceReq;
import id.co.asyst.bukopin.mobile.user.model.AccountBalanceRes;
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
//    @Autowired
//    private AccountBalanceService accountBalanceService;

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
	
	// get transaction ID
	String txId = BkpmUtil.generateTrxId();

	now.setTime(new Date());
	xmlDateTime = DatatypeFactory.newInstance().newXMLGregorianCalendar(now);

	// TO DO : non hard code client ID and client TXN ID
	credentials.setClientID(txId);
	headerRQ.setReqDateTime(xmlDateTime);
	headerRQ.setClientTxnID(BkpmConstants.CLIENT_ID);
	headerRQ.setIsLastTxn(false);
	headerRQ.setCredentials(credentials);

	return headerRQ;
    }

    /**
     * Generate Body Request for Get Inquiry CIF
     * 
     * @return The Object request.
     */
    public static GetInquiryCIFReqType generateBodyInquiryCIF(String cif, int page) {
	log.debug("generate body Inqury CIF : {} ");
	GetInquiryCIFReqType CIFReqType = new GetInquiryCIFReqType();
	GetInquiryCIFReqType.Paginginfo pageInfo = new GetInquiryCIFReqType.Paginginfo();
	
	// item per page always 10 returns by tibco
	BigInteger itemPerPage = BigInteger.TEN;
	BigInteger startPage = page <= 0 ? BigInteger.ONE : BigInteger.valueOf(page);
	
	pageInfo.setStartindex(startPage);
	pageInfo.setItemsperpage(itemPerPage);
	CIFReqType.setCifnumber(cif);
	CIFReqType.setPaginginfo(pageInfo);

	return CIFReqType;
    }

    /**
     * Generate Set Data Account Card to save
     * 
     * @param debitCard
     *            information debit card
     * @param tibcoAccountCard
     *            Account Card result from get Inquiry CIF
     * @param user
     *            information from user's
     * 
     * @return The Object request.
     */
    public static AccountCard setDataAccountCard(ActivateDebitCardRequest debitCard, 
	    GetInquiryCIFResType.AccInfo tibcoAccountCard, User user) {
	log.debug("set Data Account Card : {} ");
	AccountCard accCard = new AccountCard();
	accCard.setBirthDate(tibcoAccountCard.getBirthdate().toGregorianCalendar().getTime());
	accCard.setCardStatus(CardStatusEnum.getEnum(1));
	accCard.setCif(tibcoAccountCard.getCifnumber());
	accCard.setRegisteredCard(debitCard.getRegisteredCard());
	accCard.setRegisteredOn(new Date());
	accCard.setValidMonth(debitCard.getValidMonth());
	accCard.setValidYear(debitCard.getValidYear());

	// set user info
	user.setCifNumber(tibcoAccountCard.getCifnumber());
	user.setFirstName(tibcoAccountCard.getFirstname());
	user.setMiddleName(tibcoAccountCard.getMiddlename());
	user.setLastName(tibcoAccountCard.getLastname());
	user.setMobilePhone(tibcoAccountCard.getMobilephone());
	accCard.setUser(user);

	return accCard;
    }

    /**
     * Generate Set Data Account Info to save
     * 
     * @param tibcoAccountInfoNew
     *            Account Card result from get Inquiry CIF
     * @param accCard
     *            information from Account Card
     * @param listProduct
     * 		  Products list (Gyro and Saving only) that could be activated
     * @param accCifStatus Map account number and cif status.
     * 
     * @return 
     * <ul>
     * 	<li><b>null</b> if no match between account card from Tibco and XLINK DB.</li>
     *  <li><b>Empty List</b> if all of Products cannot be activated.</li>
     * 	<li>else will return The List Object request.</b>
     * </ul>
     */
    public static List<AccountInfo> setDataAccountInfo(AccountCard accCard,
	    List<GetInquiryCIFResType.Accounts> tibcoAccountInfoNew, List<Product> listProduct, 
	    Map<String,Integer> accCifStatus) {
	log.debug("Set List Data Account Info : {} ");
	List<AccountInfo> listAccountInfo = new ArrayList<>();
	
	for (GetInquiryCIFResType.Accounts accounts : tibcoAccountInfoNew) {
	    // Find product in Can Activated Products list
	    Optional<Product> ps = listProduct.stream().filter(
		    p -> p.getPdId()==accounts.getProductid()).findFirst();
	    Product product = ps.get();
	    
	    // padding to 10 with 0
	    String tibcoAccNo = StringUtils.leftPad(String.valueOf(accounts.getAccnumber()), 
		    BkpmConstants.BUKOPIN_ACCNO_LENGTH, 
		    BkpmConstants.BUKOPIN_ACCNO_PADDING);
	    
	    AccountInfo accInfo = new AccountInfo();
	    accInfo.setAccountName(accounts.getAccname());
	    accInfo.setAccountNo(tibcoAccNo);
	    accInfo.setAccountStatus(AccountStatusEnum.getEnum(
		    accCifStatus.get(tibcoAccNo)));
	    accInfo.setAccountType(AccountTypeEnum.getEnum((accounts.getAcctype())));
	    accInfo.setCreateDate(new Date());
	    accInfo.setCif(accCard.getCif());
	    accInfo.setMainAccount(false);
	    accInfo.setStatus(AccountInfoStatusEnum.getEnum(1));
	    accInfo.setAccountCard(accCard);
	    accInfo.setProduct(product);

	    listAccountInfo.add(accInfo);
	}

	return listAccountInfo;
    }
    
    /**
     * Generate Response Verification
     * 
     * @param inquiryCIFRS Result Get CIF from Tibco
     * @param cards Cards from DB Xlink
     * @param listProduct List all products from PRODUCT table
     * @param blackListPdid Black list PDIDs
     * 
     * @return List of Account Info ordered by notActivated ASC
     */
    public static List<AccountInfo> generateResponseVerification(AccountCard accCard,
	    List<GetInquiryCIFResType.Accounts> tibcoAccountInfo, List<Product> productsDb, 
	    List<Integer> blackListPdid) {
	log.debug("Set response Verification...");
	List<AccountInfo> listResponse = new ArrayList<>();
	
	for (GetInquiryCIFResType.Accounts accounts : tibcoAccountInfo) {
	    // padding to 10 with 0
	    String tibcoAccNo = StringUtils.leftPad(String.valueOf(accounts.getAccnumber()), 
		    BkpmConstants.BUKOPIN_ACCNO_LENGTH, 
		    BkpmConstants.BUKOPIN_ACCNO_PADDING);
	    
	    AccountInfo accInfo = new AccountInfo();
	    accInfo.setAccountName(accounts.getAccname());
	    accInfo.setAccountNo(tibcoAccNo);
	    accInfo.setAccountStatus(AccountStatusEnum.getEnum(accounts.getStatus()));
	    accInfo.setAccountType(AccountTypeEnum.getEnum((accounts.getAcctype())));
	    accInfo.setCreateDate(new Date());
	    accInfo.setCif(accCard.getCif());
	    accInfo.setMainAccount(false);
	    accInfo.setStatus(AccountInfoStatusEnum.getEnum(1));
	    accInfo.setAccountCard(accCard);
	    
	    // set product
	    List<Product> products = productsDb.stream().distinct().filter(
			// Make sure account's pdid is exist in table PRODUCT
			p -> accounts.getProductid()==p.getPdId()
			// Exclude accounts from tibco with blackListPdid
			&& !blackListPdid.contains(accounts.getProductid()) )
			.collect(Collectors.toList());
		Product product = new Product();
		if(products==null || products.isEmpty()) {
		    // if product not exist in PRODUCT table or blacklisted, acc cannot be activated
		    product.setPdId(accounts.getProductid());
		    accInfo.setNotActivated(true);
		} else {
		    product = products.get(0);
		}
	    accInfo.setProduct(product);

	    listResponse.add(accInfo);
	}
	
	// List of Account Info ordered by notActivated ASC
	listResponse.sort(Comparator.comparing(AccountInfo::isNotActivated));
	
	return listResponse;
    }
    
    /* Overrides: */
}
