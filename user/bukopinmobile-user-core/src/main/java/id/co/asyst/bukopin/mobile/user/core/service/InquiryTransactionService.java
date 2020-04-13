package id.co.asyst.bukopin.mobile.user.core.service;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.Holder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.co.asyst.bukopin.mobile.common.core.util.BkpmUtil;
import id.co.asyst.bukopin.mobile.common.model.BkpmConstants;
import id.co.asyst.bukopin.mobile.user.core.service.soap.PortType;
import id.co.asyst.bukopin.mobile.user.core.util.AuthUtil;
import id.co.asyst.bukopin.mobile.user.model.InquiryTransactionReq;
import id.co.asyst.bukopin.mobile.user.model.InquiryTransactionRes;
import id.co.asyst.bukopin.mobile.user.model.CIFStatusEnum;
import id.co.asyst.bukopin.mobile.user.model.InquiryTransactionDetailRes;
import id.co.asyst.bukopin.mobile.user.model.TransactionSummaryRes;
import id.co.asyst.bukopin.mobile.user.model.entity.Product;
import id.co.asyst.bukopin.mobile.user.model.soap.account.Fault;
import id.co.asyst.bukopin.mobile.user.model.soap.account.GetInquiryTransactionReqType;
import id.co.asyst.bukopin.mobile.user.model.soap.account.GetInquiryTransactionResType;
import id.co.asyst.bukopin.mobile.user.model.soap.account.GetInquiryTransactionResType.AccInfo;
import id.co.asyst.bukopin.mobile.user.model.soap.account.GetInquiryTransactionResType.Transaction;
import id.co.asyst.bukopin.mobile.user.model.soap.account.HeaderRQ;
import id.co.asyst.bukopin.mobile.user.model.soap.account.HeaderRQ.Credentials;
import id.co.asyst.bukopin.mobile.user.model.soap.account.HeaderRS;
import id.co.asyst.bukopin.mobile.user.model.soap.account.ObjectFactory;

/**
 * Service Implementation for managing Inquiry Transaction.
 * 
 * @author Kartika Dwi Handini
 * @version $Revision$, (Created on Nov 01, 2019)
 * @since 1.0.Alpha1
 */
@Service
@Transactional
public class InquiryTransactionService {

    /**
     * Logger.
     */
    private final Logger log = LoggerFactory.getLogger(InquiryTransactionService.class);



    /**
     * Port Type (SOAP Manager).
     */
    @Autowired
    private PortType portType;
    
    @Autowired
    private ProductService productService;
    
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    
    
   // ObjectFactory objectFactory = new ObjectFactory();
    
    public InquiryTransactionRes getInquiry (InquiryTransactionReq inquiryTransactionReq) {
    	// get transaction ID 
    	String txId = BkpmUtil.generateTrxId();
    	
        log.debug("get inquiry transaction for "+txId);
        InquiryTransactionRes inqTranResult = new InquiryTransactionRes();
        
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
		
        
        GetInquiryTransactionReqType getInquiryTransactionRQ = new GetInquiryTransactionReqType();
        getInquiryTransactionRQ.setAccNo(inquiryTransactionReq.getAccountNumber());
        getInquiryTransactionRQ.setAccType(inquiryTransactionReq.getAccountType());
        
        if(null != inquiryTransactionReq.getDateFrom() && null != inquiryTransactionReq.getDateTo()) {
        	GetInquiryTransactionReqType.Date dt = new GetInquiryTransactionReqType.Date();
        	try {
    			GregorianCalendar gregCal = new GregorianCalendar();
    			
    			//from    			
				gregCal.setTime(inquiryTransactionReq.getDateFrom());
				XMLGregorianCalendar xmlGregCal;
    			xmlGregCal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregCal);
    			xmlGregCal.setTimezone(DatatypeConstants.FIELD_UNDEFINED);
    			dt.setDateFrom(xmlGregCal);
				
    			//to
    			gregCal.setTime(inquiryTransactionReq.getDateTo());
    			xmlGregCal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregCal);
    			xmlGregCal.setTimezone(DatatypeConstants.FIELD_UNDEFINED);
            	dt.setDateTo(xmlGregCal);
            	
    		} catch (DatatypeConfigurationException e1) {
    			e1.printStackTrace();
    		}
            getInquiryTransactionRQ.setDate(dt);
        } else {
        	getInquiryTransactionRQ.setNTrx(inquiryTransactionReq.getTxLine());
        }
        
        GetInquiryTransactionReqType.PagingInfo paging = new GetInquiryTransactionReqType.PagingInfo();
        paging.setStartIndex(new BigInteger("1"));
        paging.setItemsPerPage(new BigInteger("100"));
        getInquiryTransactionRQ.setPagingInfo(paging);
        
        Holder<HeaderRS> headerRS = new Holder<HeaderRS>(); 
        Holder<GetInquiryTransactionResType> getInquiryTransactionRS = new Holder<GetInquiryTransactionResType>();
        try {
        	log.debug("headerRQ "+headerRQ.getCredentials().getClientID());
        	log.debug("getInquiryTransactionRQ "+getInquiryTransactionRQ.getAccNo());
        	
        	
			portType.getInquiryTransaction
					(headerRQ, getInquiryTransactionRQ,headerRS,getInquiryTransactionRS);
			
			
			//log.debug("response "+response.toString());
			
			if("000".equalsIgnoreCase(getInquiryTransactionRS.value.getResponse().getResCode())) {
				log.debug("RESPONSE SUKSES");
				AccInfo accInfo = getInquiryTransactionRS.value.getAccInfo();
				inqTranResult.setAccountNumber(accInfo.getAccNo());
				if(null != accInfo.getAccStatus()) {
					inqTranResult.setAccountStatus(CIFStatusEnum.getEnum(Integer.valueOf(accInfo.getAccStatus())));
				}
				
				inqTranResult.setAccountType(accInfo.getAccType().toString());
				inqTranResult.setBranch(accInfo.getBranch());
				inqTranResult.setCifNumber(accInfo.getCifNumber().toString());
				
				if(null != accInfo.getCifStatus()) {
					inqTranResult.setCifStatus(CIFStatusEnum.getEnum(Integer.valueOf(accInfo.getCifStatus())));
				}
				
				inqTranResult.setCurrency(accInfo.getCurrency());
				inqTranResult.setLocation(accInfo.getLocation());
				
				//use this if want name in camel case
				/*String name[] = accInfo.getName().split("\\s+");
			    String nameStr="";
			    for(int a=0; a<name.length;a++) {
			    	nameStr = nameStr+name[a].substring(0,1)+name[a].substring(1,name[a].length()).toLowerCase()+" ";
			    }*/
				
				inqTranResult.setName(accInfo.getName());
				
				//get product desc from db 
			    Product prod = productService.findByPdId(Integer.valueOf(accInfo.getProductID()));  
				inqTranResult.setProductID(prod.getProductName());
				//inqTranResult.setProductID(ProductIdEnum.getEnum(Integer.valueOf(accInfo.getProductID())));
				
				TransactionSummaryRes txSummary= new TransactionSummaryRes();
				txSummary.setEndingBalance(accInfo.getTrxSummary().getEndingBalance());
				txSummary.setStartingBalance(accInfo.getTrxSummary().getStartingBalance());
				txSummary.setTotalCreditAmount(accInfo.getTrxSummary().getTotalCreditAmt());
				txSummary.setTotalDebitAmount(accInfo.getTrxSummary().getTotalDebitAmt());
				inqTranResult.setTransactionSummary(txSummary);
				
				List<Transaction> txs = getInquiryTransactionRS.value.getTransaction();
				List<InquiryTransactionDetailRes> txDetail = new ArrayList<InquiryTransactionDetailRes>();
				
				for(Transaction tx : txs) {
					InquiryTransactionDetailRes detail = new InquiryTransactionDetailRes();
					detail.setAmountCredit(tx.getAmtCredit());
					detail.setAmountDebit(tx.getAmtDebit());
					detail.setBalanceAmount(tx.getBalanceAmt());
					detail.setDescription(tx.getTrxDesc1()+" "+tx.getTrxDesc2()+" "+tx.getTrxDesc3());
					
					String ef = sdf.format(tx.getEffectiveDate().toGregorianCalendar().getTime());
					detail.setEffectiveDate(ef);
					
					String ps = sdf.format(tx.getPostingDate().toGregorianCalendar().getTime());
					detail.setPostingDate(ps);
					
					detail.setReffNumber(tx.getReffNumber());
					txDetail.add(detail);
				}		
				
				
				Collections.sort(txDetail, new Comparator<InquiryTransactionDetailRes>() {
					  @Override
					  public int compare(InquiryTransactionDetailRes u1, InquiryTransactionDetailRes u2) {
					    return u2.getEffectiveDate().compareTo(u1.getEffectiveDate());
					  }
					});
				
				inqTranResult.setTransactiondetails(txDetail);
				inqTranResult.setResponseCode(getInquiryTransactionRS.value.getResponse().getResCode());
				inqTranResult.setResponseMessage(getInquiryTransactionRS.value.getResponse().getDesc());
			} else {
				log.error("error get response in inquiry transaction :"+ getInquiryTransactionRS.value.getResponse().getDesc());
				inqTranResult.setResponseCode(getInquiryTransactionRS.value.getResponse().getResCode());
				inqTranResult.setResponseMessage(getInquiryTransactionRS.value.getResponse().getDesc());
				inqTranResult=null;
			}
			
			
			
			
		} catch (Fault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return inqTranResult;
    }
    
    
    
    
   
}
