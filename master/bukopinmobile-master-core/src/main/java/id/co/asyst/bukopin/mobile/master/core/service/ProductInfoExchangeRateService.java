package id.co.asyst.bukopin.mobile.master.core.service;

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

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.co.asyst.bukopin.mobile.common.core.util.BkpmUtil;
import id.co.asyst.bukopin.mobile.common.model.BkpmConstants;
import id.co.asyst.bukopin.mobile.master.core.service.soap.ProductInfoPT;
import id.co.asyst.bukopin.mobile.master.model.payload.DepositoDetailResponse;
import id.co.asyst.bukopin.mobile.master.model.payload.DepositoResponse;
import id.co.asyst.bukopin.mobile.master.model.payload.GiroResponse;
import id.co.asyst.bukopin.mobile.master.model.payload.RateDepositoResponse;
import id.co.asyst.bukopin.mobile.master.model.payload.SavingsDetailResponse;
import id.co.asyst.bukopin.mobile.master.model.payload.SavingsResponse;
import id.co.asyst.bukopin.mobile.master.model.soap.exchange.rate.Fault;
import id.co.asyst.bukopin.mobile.master.model.soap.exchange.rate.HeaderRQ;
import id.co.asyst.bukopin.mobile.master.model.soap.exchange.rate.HeaderRQ.Credentials;
import id.co.asyst.bukopin.mobile.master.model.soap.exchange.rate.HeaderRS;
import id.co.asyst.bukopin.mobile.master.model.soap.exchange.rate.ProductInformationType;
import id.co.asyst.bukopin.mobile.master.model.soap.exchange.rate.ProductdetailType;
import id.co.asyst.bukopin.mobile.master.model.soap.exchange.rate.ProductdetailType.Deposito;
import id.co.asyst.bukopin.mobile.master.model.soap.exchange.rate.ProductdetailType.Giro;
import id.co.asyst.bukopin.mobile.master.model.soap.exchange.rate.ProductdetailType.Tabungan;
import id.co.asyst.bukopin.mobile.master.model.soap.exchange.rate.Reqproductinformation;
import id.co.asyst.bukopin.mobile.master.model.soap.exchange.rate.Resproductinformation;

/**
 * Service Implementation for managing Account Balance.
 * 
 * @author Kartika Dwi Handini
 * @version $Revision$, (Created on Nov 01, 2019)
 * @since 1.0.Alpha1
 */
@Service
public class ProductInfoExchangeRateService {

    /**
     * Logger.
     */
    private final Logger log = LoggerFactory.getLogger(ProductInfoExchangeRateService.class);

    /**
     * Port Type (SOAP Manager).
     */
    @Autowired
    private ProductInfoPT portType;
    
    /* Constructors: */
    public ProductInfoExchangeRateService() {
	// do nothing
    }
    
    public ProductInfoExchangeRateService(ProductInfoPT productInfoPt) {
	this.portType = productInfoPt;
    }

    public List<SavingsDetailResponse> getSavings(Integer  productCode) {
    	List<SavingsDetailResponse> res = new ArrayList<>();
    	
    	// get transaction ID
    	String txId = BkpmUtil.generateTrxId();

    	log.debug("get Savings Product Info " + txId);

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

    	Reqproductinformation getProductInfoRQ = new Reqproductinformation();
    	
    	ProductInformationType type = new ProductInformationType();
    	type.setProductcode(productCode);
    	getProductInfoRQ.setProductInformation(type);
    	

    	Holder<HeaderRS> headerRS = new Holder<HeaderRS>();
    	Holder<Resproductinformation> productInfoRes = new Holder<Resproductinformation>();

    	try {
    	    portType.getProductInfo(headerRQ, getProductInfoRQ, headerRS, productInfoRes);

    	    ProductdetailType productDetails = productInfoRes.value.getProductdetail();
        	Tabungan tabungan= productDetails.getTabungan();
        	
        	//Tabungan 1
        	if(!"".equalsIgnoreCase(tabungan.getRateprecentage1())) {
        		SavingsDetailResponse tab1 = new SavingsDetailResponse();
        		tab1.setDescription(tabungan.getRatedescription1());
        		tab1.setInterest(new BigInteger(tabungan.getInterestrate1()));
        		tab1.setLimit(new BigInteger(tabungan.getLimitbalance1()));
        		tab1.setRate(new BigDecimal(tabungan.getRateprecentage1()));
        		res.add(tab1);        		
        	}
        	
        	//Tabungan 2
        	if(!"".equalsIgnoreCase(tabungan.getRateprecentage2())) {
        		SavingsDetailResponse tab2 = new SavingsDetailResponse();
        		tab2.setDescription(tabungan.getRatedescription2());
        		tab2.setInterest(new BigInteger(tabungan.getInterestrate2()));
        		tab2.setLimit(new BigInteger(tabungan.getLimitbalance2()));
        		tab2.setRate(new BigDecimal(tabungan.getRateprecentage2()));
        		res.add(tab2);        		
        	}
        	
        	//Tabungan 3
        	if(!"".equalsIgnoreCase(tabungan.getRateprecentage3())) {
        		SavingsDetailResponse tab3 = new SavingsDetailResponse();
        		tab3.setDescription(tabungan.getRatedescription3());
        		tab3.setInterest(new BigInteger(tabungan.getInterestrate3()));
        		tab3.setLimit(new BigInteger(tabungan.getLimitbalance3()));
        		tab3.setRate(new BigDecimal(tabungan.getRateprecentage3()));
        		res.add(tab3);        		
        	}
        	
        	//Tabungan 4
        	if(!"".equalsIgnoreCase(tabungan.getRateprecentage4())) {
        		SavingsDetailResponse tab4 = new SavingsDetailResponse();
        		tab4.setDescription(tabungan.getRatedescription4());
        		tab4.setInterest(new BigInteger(tabungan.getInterestrate4()));
        		tab4.setLimit(new BigInteger(tabungan.getLimitbalance4()));
        		tab4.setRate(new BigDecimal(tabungan.getRateprecentage4()));
        		res.add(tab4);        		
        	}
        	
        	//Tabungan 5
        	if(!"".equalsIgnoreCase(tabungan.getRateprecentage5())) {
        		SavingsDetailResponse tab5 = new SavingsDetailResponse();
        		tab5.setDescription(tabungan.getRatedescription5());
        		tab5.setInterest(new BigInteger(tabungan.getInterestrate5()));
        		tab5.setLimit(new BigInteger(tabungan.getLimitbalance5()));
        		tab5.setRate(new BigDecimal(tabungan.getRateprecentage5()));
        		res.add(tab5);        		
        	}
        	

    	} catch (Fault e) {
    	    // TODO Auto-generated catch block
    	    e.printStackTrace();
    	}
    	
	return res;
    }

    public List<DepositoDetailResponse> getDeposito(Integer  productCode) {
    	List<DepositoDetailResponse> res = new ArrayList<>();
    	
    	// get transaction ID
    	String txId = BkpmUtil.generateTrxId();

    	log.debug("get Deposito Product Info " + txId);

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

    	Reqproductinformation getProductInfoRQ = new Reqproductinformation();
    	
    	ProductInformationType type = new ProductInformationType();
    	type.setProductcode(productCode);
    	getProductInfoRQ.setProductInformation(type);
    	

    	Holder<HeaderRS> headerRS = new Holder<HeaderRS>();
    	Holder<Resproductinformation> productInfoRes = new Holder<Resproductinformation>();

    	try {
    		log.debug("headerRQ "+headerRQ.getCredentials().getClientID());
        	log.debug("getProductInfoRQ "+getProductInfoRQ.getProductInformation().getProductcode());
    	    portType.getProductInfo(headerRQ, getProductInfoRQ, headerRS, productInfoRes);

    	    ProductdetailType productDetails = productInfoRes.value.getProductdetail();
        	Deposito deposito = productDetails.getDeposito();
        	
        	//Deposito 1
        	if(!"".equalsIgnoreCase(deposito.getProductdescription1())) {
        		DepositoDetailResponse deposito1 = new DepositoDetailResponse();
        		List<RateDepositoResponse> rateList = new ArrayList<>();
            	deposito1.setProduct(deposito.getProductdescription1());
            	    	 	
            	//rate1
            	if(!"".equalsIgnoreCase(deposito.getRateprecentage11())) {
            		RateDepositoResponse rate1 = new RateDepositoResponse();
                	rate1.setRate(new BigDecimal(deposito.getRateprecentage11()));
                	rate1.setDescription(deposito.getRatedescription11());
                	rateList.add(rate1);
            	}    	
            	
            	//rate2
            	if(!"".equalsIgnoreCase(deposito.getRateprecentage12())) {
            		RateDepositoResponse rate2 = new RateDepositoResponse();
                	rate2.setRate(new BigDecimal(deposito.getRateprecentage12()));
                	rate2.setDescription(deposito.getRatedescription12());
                	rateList.add(rate2);
            	}    	
            	
            	//rate3
            	if(!"".equalsIgnoreCase(deposito.getRateprecentage13())) {
            		RateDepositoResponse rate3 = new RateDepositoResponse();
                	rate3.setRate(new BigDecimal(deposito.getRateprecentage13()));
                	rate3.setDescription(deposito.getRatedescription13());
                	rateList.add(rate3);
            	}    	
            	
            	//rate4
            	if(!"".equalsIgnoreCase(deposito.getRateprecentage14())) {
            		RateDepositoResponse rate4 = new RateDepositoResponse();
                	rate4.setRate(new BigDecimal(deposito.getRateprecentage14()));
                	rate4.setDescription(deposito.getRatedescription14());
                	rateList.add(rate4);
            	}    	
            	
            	//rate5
            	if(!"".equalsIgnoreCase(deposito.getRateprecentage15())) {
            		RateDepositoResponse rate5 = new RateDepositoResponse();
                	rate5.setRate(new BigDecimal(deposito.getRateprecentage15()));
                	rate5.setDescription(deposito.getRatedescription15());
                	rateList.add(rate5);
            	}    	
            	
            	deposito1.setRateList(rateList);
            	res.add(deposito1);
        	}
        	
        	//Deposito 2
        	if(!"".equalsIgnoreCase(deposito.getProductdescription2())) {    		
            	DepositoDetailResponse deposito2 = new DepositoDetailResponse();
            	deposito2.setProduct(deposito.getProductdescription2());
            	
            	List<RateDepositoResponse> rateList = new ArrayList<>();   	
            	//rate1
            	if(!"".equalsIgnoreCase(deposito.getRateprecentage21())) {
            		RateDepositoResponse rate1 = new RateDepositoResponse();
                	rate1.setRate(new BigDecimal(deposito.getRateprecentage21()));
                	rate1.setDescription(deposito.getRatedescription21());
                	rateList.add(rate1);
            	}    	
            	
            	//rate2
            	if(!"".equalsIgnoreCase(deposito.getRateprecentage22())) {
            		RateDepositoResponse rate2 = new RateDepositoResponse();
                	rate2.setRate(new BigDecimal(deposito.getRateprecentage22()));
                	rate2.setDescription(deposito.getRatedescription22());
                	rateList.add(rate2);
            	}    	
            	
            	//rate3
            	if(!"".equalsIgnoreCase(deposito.getRateprecentage23())) {
            		RateDepositoResponse rate3 = new RateDepositoResponse();
                	rate3.setRate(new BigDecimal (deposito.getRateprecentage23()));
                	rate3.setDescription(deposito.getRatedescription23());
                	rateList.add(rate3);
            	}    	
            	
            	//rate4
            	if(!"".equalsIgnoreCase(deposito.getRateprecentage24())) {
            		RateDepositoResponse rate4 = new RateDepositoResponse();
                	rate4.setRate(new BigDecimal (deposito.getRateprecentage24()));
                	rate4.setDescription(deposito.getRatedescription24());
                	rateList.add(rate4);
            	}   
            	
            	//rate5
            	if(!"".equalsIgnoreCase(deposito.getRateprecentage25())) {
            		RateDepositoResponse rate5 = new RateDepositoResponse();
                	rate5.setRate(new BigDecimal (deposito.getRateprecentage25()));
                	rate5.setDescription(deposito.getRatedescription25());
                	rateList.add(rate5);
            	}    	
            	
            	deposito2.setRateList(rateList);
            	res.add(deposito2);
        	}
        	
        	//deposito3
        	if(!"".equalsIgnoreCase(deposito.getProductdescription3())) {    		
            	DepositoDetailResponse deposito3 = new DepositoDetailResponse();
            	deposito3.setProduct(deposito.getProductdescription3());
            	
            	List<RateDepositoResponse> rateList = new ArrayList<>();	
            	//rate1
            	if(!"".equalsIgnoreCase(deposito.getRateprecentage31())) {
            		RateDepositoResponse rate1 = new RateDepositoResponse();
                	rate1.setRate(new BigDecimal(deposito.getRateprecentage31()));
                	rate1.setDescription(deposito.getRatedescription31());
                	rateList.add(rate1);
            	}    	
            	
            	//rate2
            	if(!"".equalsIgnoreCase(deposito.getRateprecentage32())) {
            		RateDepositoResponse rate2 = new RateDepositoResponse();
                	rate2.setRate(new BigDecimal (deposito.getRateprecentage32()));
                	rate2.setDescription(deposito.getRatedescription32());
                	rateList.add(rate2);
            	}    	
            	
            	//rate3
            	if(!"".equalsIgnoreCase(deposito.getRateprecentage33())) {
            		RateDepositoResponse rate3 = new RateDepositoResponse();
                	rate3.setRate(new BigDecimal(deposito.getRateprecentage33()));
                	rate3.setDescription(deposito.getRatedescription33());
                	rateList.add(rate3);
            	}    	
            	
            	//rate4
            	if(!"".equalsIgnoreCase(deposito.getRateprecentage34())) {
            		RateDepositoResponse rate4 = new RateDepositoResponse();
                	rate4.setRate(new BigDecimal(deposito.getRateprecentage34()));
                	rate4.setDescription(deposito.getRatedescription34());
                	rateList.add(rate4);
            	}   
            	
            	//rate5
            	if(!"".equalsIgnoreCase(deposito.getRateprecentage35())) {
            		RateDepositoResponse rate5 = new RateDepositoResponse();
                	rate5.setRate(new BigDecimal(deposito.getRateprecentage35()));
                	rate5.setDescription(deposito.getRatedescription35());
                	rateList.add(rate5);
            	}    	
            	
            	deposito3.setRateList(rateList);
            	res.add(deposito3);
        	}
        	

    	} catch (Fault e) {
    	    // TODO Auto-generated catch block
    	    e.printStackTrace();
    	}
    	
	return res;
    }
    
    public GiroResponse getGiro(Integer  productCode) {
    	GiroResponse res = new GiroResponse();
    	
    	// get transaction ID
    	String txId = BkpmUtil.generateTrxId();

    	log.debug("get Savings Product Info " + txId);

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

    	Reqproductinformation getProductInfoRQ = new Reqproductinformation();
    	
    	ProductInformationType type = new ProductInformationType();
    	type.setProductcode(productCode);
    	getProductInfoRQ.setProductInformation(type);
    	

    	Holder<HeaderRS> headerRS = new Holder<HeaderRS>();
    	Holder<Resproductinformation> productInfoRes = new Holder<Resproductinformation>();

    	try {
    	    portType.getProductInfo(headerRQ, getProductInfoRQ, headerRS, productInfoRes);

    	    ProductdetailType productDetails = productInfoRes.value.getProductdetail();
        	Giro giro= productDetails.getGiro();
        	res.setRate(new BigDecimal(giro.getRateprecentage1()));
        	res.setDescription(giro.getRatedescription1());

    	} catch (Fault e) {
    	    // TODO Auto-generated catch block
    	    e.printStackTrace();
    	}
    	
	return res;
    }
   
}
