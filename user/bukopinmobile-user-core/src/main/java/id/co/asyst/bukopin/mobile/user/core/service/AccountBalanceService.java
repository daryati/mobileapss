package id.co.asyst.bukopin.mobile.user.core.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.Holder;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.co.asyst.bukopin.mobile.common.core.util.BkpmUtil;
import id.co.asyst.bukopin.mobile.common.model.BkpmConstants;
import id.co.asyst.bukopin.mobile.user.core.service.soap.PortType;
import id.co.asyst.bukopin.mobile.user.core.util.AuthUtil;
import id.co.asyst.bukopin.mobile.user.model.AccountBalanceByAccNoReq;
import id.co.asyst.bukopin.mobile.user.model.AccountBalanceReq;
import id.co.asyst.bukopin.mobile.user.model.AccountBalanceRes;
import id.co.asyst.bukopin.mobile.user.model.AccountBalanceTransactionDetailRes;
import id.co.asyst.bukopin.mobile.user.model.AccountTypeEnum;
import id.co.asyst.bukopin.mobile.user.model.CIFStatusEnum;
import id.co.asyst.bukopin.mobile.user.model.InquiryTransactionDetailRes;
import id.co.asyst.bukopin.mobile.user.model.InquiryTransactionReq;
import id.co.asyst.bukopin.mobile.user.model.InquiryTransactionRes;
import id.co.asyst.bukopin.mobile.user.model.TotalBalanceRes;
import id.co.asyst.bukopin.mobile.user.model.entity.AccountCard;
import id.co.asyst.bukopin.mobile.user.model.entity.AccountInfo;
import id.co.asyst.bukopin.mobile.user.model.entity.Product;
import id.co.asyst.bukopin.mobile.user.model.soap.account.Fault;
import id.co.asyst.bukopin.mobile.user.model.soap.account.GetAccountBalanceReqType;
import id.co.asyst.bukopin.mobile.user.model.soap.account.GetAccountBalanceReqType.Account;
import id.co.asyst.bukopin.mobile.user.model.soap.account.GetAccountBalanceResType;
import id.co.asyst.bukopin.mobile.user.model.soap.account.GetAccountBalanceResType.Transaction;
import id.co.asyst.bukopin.mobile.user.model.soap.account.HeaderRQ;
import id.co.asyst.bukopin.mobile.user.model.soap.account.HeaderRQ.Credentials;
import id.co.asyst.bukopin.mobile.user.model.soap.account.HeaderRS;

/**
 * Service Implementation for managing Account Balance.
 * 
 * @author Kartika Dwi Handini
 * @version $Revision$, (Created on Nov 01, 2019)
 * @since 1.0.Alpha1
 */
@Service
@Transactional
public class AccountBalanceService {

    /**
     * Logger.
     */
    private final Logger log = LoggerFactory.getLogger(AccountBalanceService.class);

    /**
     * Port Type (SOAP Manager).
     */
    @Autowired
    private PortType portType;

    @Autowired
    private InquiryTransactionService inquiryService;

    @Autowired
    private AccountCardService accountCardService;

    @Autowired
    private AccountInfoUserService accountInfoUserService;

    @Autowired
    private ProductService productService;

    public AccountBalanceRes getAccountBalance(AccountBalanceReq accountBalanceReq) {
	// get transaction ID
	String txId = BkpmUtil.generateTrxId();

	log.debug("get account balance " + txId);
	AccountBalanceRes accBalanceResult = new AccountBalanceRes();

	// set request
	HeaderRQ headerRQ = new HeaderRQ();
	headerRQ.setClientTxnID(txId);

	Credentials credential = new Credentials();
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

	GetAccountBalanceReqType getAccountBalanceRQ = new GetAccountBalanceReqType();
	getAccountBalanceRQ.setAccType(accountBalanceReq.getAccountType());

	// get list of account number
	//AccountCard accCard = accountCardService.findByUsername(accountBalanceReq.getUsername());
	List<AccountCard> listAccCard = accountCardService.findListByUsername(accountBalanceReq.getUsername());

	Account acc = new Account();
	List<AccountInfo> accInfoFilter = new ArrayList<>();
	for (int i = 0; i < listAccCard.size(); i++) {
	    for (AccountInfo info : listAccCard.get(i).getAccounts()) {
		log.debug("acc no " + info.getAccountNo());
		if (accountBalanceReq.getAccountType().equals(BigInteger.valueOf(info.getAccountType().getValue()))) {
		    acc.getAccNo().add(info.getAccountNo());
		    accInfoFilter.add(info);
		}

	    }
	}
	getAccountBalanceRQ.setAccount(acc);
	
	if(acc.getAccNo().size()>0) {
		
		Holder<HeaderRS> headerRS = new Holder<HeaderRS>();
		Holder<GetAccountBalanceResType> getAccountBalanceRS = new Holder<GetAccountBalanceResType>();

		try {
		    portType.getAccountBalance(headerRQ, getAccountBalanceRQ, headerRS, getAccountBalanceRS);

		    if ("Sukses".equalsIgnoreCase(getAccountBalanceRS.value.getResponse().getDesc())
			    || "success".equalsIgnoreCase(getAccountBalanceRS.value.getResponse().getDesc())) {
			List<AccountBalanceTransactionDetailRes> accountList = new ArrayList<>();
			for (Transaction tx : getAccountBalanceRS.value.getTransaction()) {
			    AccountBalanceTransactionDetailRes detail = new AccountBalanceTransactionDetailRes();
			    
			    //set account no
			    String accountNo = tx.getAccNo();
			    if(accountNo.length() < BkpmConstants.BUKOPIN_ACCNO_LENGTH) {
				    // padding to 10 with 0, because account number in db is 10 digit in length and left padded with 0.
				    accountNo = StringUtils.leftPad(accountNo, BkpmConstants.BUKOPIN_ACCNO_LENGTH, 
					    BkpmConstants.BUKOPIN_ACCNO_PADDING);
				}
			    detail.setAccountNumber(accountNo);

			    //set status
			    if (null != tx.getAccStatus()) {
				detail.setAccountStatus(CIFStatusEnum.getEnum(Integer.valueOf(tx.getAccStatus())));
			    }

			    detail.setAccountType(tx.getAccType());
			    detail.setAvailableBalance(tx.getAvailableBalance());
			    detail.setBranch(tx.getBranch());

			    detail.setCifNumber(tx.getCifNumber());

			    if (null != tx.getCifStatus()) {
				detail.setCifStatus(CIFStatusEnum.getEnum(Integer.valueOf(tx.getCifStatus())));
			    }

			    detail.setCurrency(tx.getCurrency());
			    detail.setEffectiveBalance(tx.getEffectiveBalance());
			    detail.setHoldAmount(tx.getHoldAmt());
			    detail.setLocation(tx.getLokasi());

			    // use this if want name in camel case
			    /*
			     * String name[] = tx.getName().split("\\s+"); String nameStr=""; for(int a=0;
			     * a<name.length;a++) { nameStr =
			     * nameStr+name[a].substring(0,1)+name[a].substring(1,name[a].length()).
			     * toLowerCase()+" "; }
			     */

			    detail.setName(tx.getName());
			    detail.setPlafon(tx.getPlafon());

			    // get product desc
			    Product prod = productService.findByPdId(Integer.valueOf(tx.getProductID()));
			    detail.setProductID(prod.getProductName());

			    // get last 3 transaction
			    List<InquiryTransactionDetailRes> txDetail = getLast3Transaction(accountBalanceReq.getAccountType(),
				    tx.getAccNo());
			    if (null == txDetail) {
				txDetail = new ArrayList<>();
			    }

			    detail.setTransactionDetails(txDetail);
			    accountList.add(detail);
			}
			// order by cif status ascending (active - passive)
			accountList.sort(Comparator.comparing(AccountBalanceTransactionDetailRes::getCifStatus));

			TotalBalanceRes totalBalance = new TotalBalanceRes();
			totalBalance.setTotalAvailableBalance(
				getAccountBalanceRS.value.getTotalBalance().getTotalAvailableBalance());
			totalBalance.setTotalEffectiveBalance(
				getAccountBalanceRS.value.getTotalBalance().getTotalEffectiveBalance());

			// ini knp hasilny 0?
			// getAccountBalanceRS.value.getResponse().getResCode()
			accBalanceResult.setTotalBalance(totalBalance);
			accBalanceResult.setTransactionDetails(accountList);
			accBalanceResult.setResponseCode("000");
			accBalanceResult.setResponseMessage(getAccountBalanceRS.value.getResponse().getDesc());

		    } else {
			/*
			 * accBalanceResult.setResponseCode(getAccountBalanceRS.value.getResponse().
			 * getResCode());
			 * accBalanceResult.setResponseMessage(getAccountBalanceRS.value.getResponse().
			 * getDesc()); log.error( "error get response in account balance :" +
			 * getAccountBalanceRS.value.getResponse().getDesc());
			 */
		    	log.debug("HASIL "+getAccountBalanceRS.value.getResponse().getResCode());
			if ("221".equalsIgnoreCase(getAccountBalanceRS.value.getResponse().getResCode())
				|| "224".equalsIgnoreCase(getAccountBalanceRS.value.getResponse().getResCode())) {
			    List<AccountBalanceTransactionDetailRes> accountList = new ArrayList<>();
			    for (AccountInfo info : accInfoFilter) {			    	
				AccountBalanceTransactionDetailRes detail = new AccountBalanceTransactionDetailRes();
				log.debug("acc number " + info.getAccountNo());
				detail.setAccountNumber(info.getAccountNo());
				detail.setName(info.getAccountName());

				if (null != info.getAccountStatus()) {
				    detail.setAccountStatus(CIFStatusEnum.getEnum(info.getAccountStatus().getValue()));
				}

				detail.setAccountType(BigInteger.valueOf(info.getAccountType().getValue()));
				detail.setAvailableBalance(new BigDecimal(0.0));
				detail.setEffectiveBalance(new BigDecimal(0.0));
				detail.setProductID(info.getProduct().getProductName());

				if ("221".equalsIgnoreCase(getAccountBalanceRS.value.getResponse().getResCode())) {
				    // if account number is passive (2)
				    detail.setCifStatus(CIFStatusEnum.getEnum(2));
				} else if ("224".equalsIgnoreCase(getAccountBalanceRS.value.getResponse().getResCode())) {
				    // if account number is closed (9)
				    detail.setCifStatus(CIFStatusEnum.getEnum(9));
				} else {
				    detail.setCifStatus(CIFStatusEnum.getEnum(1));
				}

				List<InquiryTransactionDetailRes> txDetail = new ArrayList<>();
				detail.setTransactionDetails(txDetail);
				accountList.add(detail);
			    }
			    // order by cif status ascending (active - passive)
			    accountList.sort(Comparator.comparing(AccountBalanceTransactionDetailRes::getCifStatus));
			    
			    TotalBalanceRes totalBalance = new TotalBalanceRes();
			    totalBalance.setTotalAvailableBalance(new BigDecimal("0.0"));
			    totalBalance.setTotalEffectiveBalance(new BigDecimal("0.0"));
			    accBalanceResult.setTotalBalance(totalBalance);

			    accBalanceResult.setTransactionDetails(accountList);
			} else {
				log.debug("Response Code "+getAccountBalanceRS.value.getResponse().getResCode());
			    accBalanceResult.setTransactionDetails(new ArrayList<>());

			    TotalBalanceRes totalBalance = new TotalBalanceRes();
			    totalBalance.setTotalAvailableBalance(new BigDecimal("0.0"));
			    totalBalance.setTotalEffectiveBalance(new BigDecimal("0.0"));
			    accBalanceResult.setTotalBalance(totalBalance);
			}

			accBalanceResult.setResponseCode(getAccountBalanceRS.value.getResponse().getResCode());
			accBalanceResult.setResponseMessage(getAccountBalanceRS.value.getResponse().getDesc());

		    }

		} catch (Fault e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
		
		
	} else {
	    accBalanceResult.setTransactionDetails(new ArrayList<>());

	    TotalBalanceRes totalBalance = new TotalBalanceRes();
	    totalBalance.setTotalAvailableBalance(new BigDecimal("0.0"));
	    totalBalance.setTotalEffectiveBalance(new BigDecimal("0.0"));
	    accBalanceResult.setTotalBalance(totalBalance);
	}
	
	
	return accBalanceResult;
    }

    public List<InquiryTransactionDetailRes> getLast3Transaction(BigInteger accountType, String accountNumber) {
	List<InquiryTransactionDetailRes> result = new ArrayList<>();

	// get Transaction
	InquiryTransactionReq inqReq = new InquiryTransactionReq();
	inqReq.setAccountNumber(accountNumber);
	inqReq.setAccountType(accountType);
	inqReq.setTxLine(new BigInteger("3"));

	InquiryTransactionRes inqRes = inquiryService.getInquiry(inqReq);
	if (null != inqRes) {
	    result = inqRes.getTransactiondetails();
	}

	return result;
    }
    /*
     * Collections.sort(myList, new Comparator<MyObject>() { public int
     * compare(MyObject o1, MyObject o2) { if (o1.getDateTime() == null ||
     * o2.getDateTime() == null) return 0; return
     * o1.getDateTime().compareTo(o2.getDateTime()); } });
     */

    public AccountBalanceRes getAccountBalanceByAccountNum(AccountBalanceByAccNoReq accountBalanceByAccNoReq) {
	// get transaction ID
	String txId = BkpmUtil.generateTrxId();

	log.debug("get account balance " + txId);
	AccountBalanceRes accBalanceResult = new AccountBalanceRes();

	// set request
	HeaderRQ headerRQ = new HeaderRQ();
	headerRQ.setClientTxnID(txId);

	Credentials credential = new Credentials();
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

	GetAccountBalanceReqType getAccountBalanceRQ = new GetAccountBalanceReqType();
	getAccountBalanceRQ.setAccType(accountBalanceByAccNoReq.getAccountType());

	Account acc = new Account();
	acc.getAccNo().add(accountBalanceByAccNoReq.getAccountNumber());

	getAccountBalanceRQ.setAccount(acc);

	Holder<HeaderRS> headerRS = new Holder<HeaderRS>();
	Holder<GetAccountBalanceResType> getAccountBalanceRS = new Holder<GetAccountBalanceResType>();

	try {
	    portType.getAccountBalance(headerRQ, getAccountBalanceRQ, headerRS, getAccountBalanceRS);

	    if ("Sukses".equalsIgnoreCase(getAccountBalanceRS.value.getResponse().getDesc())
		    || "success".equalsIgnoreCase(getAccountBalanceRS.value.getResponse().getDesc())) {
		List<AccountBalanceTransactionDetailRes> accountList = new ArrayList<>();
		for (Transaction tx : getAccountBalanceRS.value.getTransaction()) {
		    AccountBalanceTransactionDetailRes detail = new AccountBalanceTransactionDetailRes();
		    detail.setAccountNumber(tx.getAccNo());

		    if (null != tx.getAccStatus()) {
			detail.setAccountStatus(CIFStatusEnum.getEnum(Integer.valueOf(tx.getAccStatus())));
		    }

		    detail.setAccountType(tx.getAccType());
		    detail.setAvailableBalance(tx.getAvailableBalance());
		    detail.setBranch(tx.getBranch());
		    ;
		    detail.setCifNumber(tx.getCifNumber());

		    if (null != tx.getCifStatus()) {
			detail.setCifStatus(CIFStatusEnum.getEnum(Integer.valueOf(tx.getCifStatus())));
		    }

		    detail.setCurrency(tx.getCurrency());
		    detail.setEffectiveBalance(tx.getEffectiveBalance());
		    detail.setHoldAmount(tx.getHoldAmt());
		    detail.setLocation(tx.getLokasi());

		    // use this if want name in camel case
		    /*
		     * String name[] = tx.getName().split("\\s+"); String nameStr=""; for(int a=0;
		     * a<name.length;a++) { nameStr =
		     * nameStr+name[a].substring(0,1)+name[a].substring(1,name[a].length()).
		     * toLowerCase()+" "; }
		     */
		    detail.setName(tx.getName());
		    detail.setPlafon(tx.getPlafon());

		    // get product desc
		    Product prod = productService.findByPdId(Integer.valueOf(tx.getProductID()));
		    detail.setProductID(prod.getProductName());

		    // get last 3 transaction
		    List<InquiryTransactionDetailRes> txDetail = getLast3Transaction(
			    accountBalanceByAccNoReq.getAccountType(), tx.getAccNo());
		    if (null == txDetail) {
			txDetail = new ArrayList<>();
		    }

		    detail.setTransactionDetails(txDetail);
		    accountList.add(detail);
		}

		TotalBalanceRes totalBalance = new TotalBalanceRes();
		totalBalance.setTotalAvailableBalance(
			getAccountBalanceRS.value.getTotalBalance().getTotalAvailableBalance());
		totalBalance.setTotalEffectiveBalance(
			getAccountBalanceRS.value.getTotalBalance().getTotalEffectiveBalance());

		accBalanceResult.setTotalBalance(totalBalance);
		accBalanceResult.setTransactionDetails(accountList);

		accBalanceResult.setResponseCode("000");
		accBalanceResult.setResponseMessage(getAccountBalanceRS.value.getResponse().getDesc());

	    } else {

		if ("221".equals(getAccountBalanceRS.value.getResponse().getResCode())
			|| "224".equals(getAccountBalanceRS.value.getResponse().getResCode())) {
		    List<AccountBalanceTransactionDetailRes> accountList = new ArrayList<>();
		    AccountInfo info = accountInfoUserService
			    .findByAccountNo(accountBalanceByAccNoReq.getAccountNumber());
		    AccountBalanceTransactionDetailRes detail = new AccountBalanceTransactionDetailRes();
		    log.debug("acc no " + info.getAccountNo());
		    detail.setAccountNumber(info.getAccountNo());
		    detail.setName(info.getAccountName());

		    if (null != info.getAccountStatus()) {
			detail.setAccountStatus(CIFStatusEnum.getEnum(info.getAccountStatus().getValue()));
		    }

		    detail.setAccountType(BigInteger.valueOf(info.getAccountType().getValue()));
		    detail.setAvailableBalance(new BigDecimal(0.0));
		    detail.setEffectiveBalance(new BigDecimal(0.0));
		    detail.setProductID(info.getProduct().getProductName());

		    if ("221".equals(getAccountBalanceRS.value.getResponse().getResCode())) {
			// if account number is passive (2)
			detail.setCifStatus(CIFStatusEnum.getEnum(2));
		    } else if ("224".equals(getAccountBalanceRS.value.getResponse().getResCode())) {
			// if account number is closed (9)
			detail.setCifStatus(CIFStatusEnum.getEnum(9));
		    } else {
			detail.setCifStatus(CIFStatusEnum.getEnum(1));
		    }

		    List<InquiryTransactionDetailRes> txDetail = new ArrayList<>();
		    detail.setTransactionDetails(txDetail);
		    accountList.add(detail);

		    TotalBalanceRes totalBalance = new TotalBalanceRes();
		    totalBalance.setTotalAvailableBalance(new BigDecimal("0.0"));
		    totalBalance.setTotalEffectiveBalance(new BigDecimal("0.0"));
		    accBalanceResult.setTotalBalance(totalBalance);

		    accBalanceResult.setTransactionDetails(accountList);
		} else {
		    accBalanceResult.setTransactionDetails(new ArrayList<>());

		    TotalBalanceRes totalBalance = new TotalBalanceRes();
		    totalBalance.setTotalAvailableBalance(new BigDecimal("0.0"));
		    totalBalance.setTotalEffectiveBalance(new BigDecimal("0.0"));
		    accBalanceResult.setTotalBalance(totalBalance);
		}

		accBalanceResult.setResponseCode(getAccountBalanceRS.value.getResponse().getResCode());
		accBalanceResult.setResponseMessage(getAccountBalanceRS.value.getResponse().getDesc());
		log.error(
			"error get response in account balance :" + getAccountBalanceRS.value.getResponse().getDesc());
	    }

	} catch (Fault e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	return accBalanceResult;
    }
}
