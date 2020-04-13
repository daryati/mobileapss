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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.co.asyst.bukopin.mobile.common.core.exception.MiddlewareException;
import id.co.asyst.bukopin.mobile.common.core.util.BkpmUtil;
import id.co.asyst.bukopin.mobile.common.model.BkpmConstants;
import id.co.asyst.bukopin.mobile.master.core.service.soap.CBSExchangeRate;
import id.co.asyst.bukopin.mobile.master.core.service.soap.ExchangeRatePT;
import id.co.asyst.bukopin.mobile.master.model.entity.Currency;
import id.co.asyst.bukopin.mobile.master.model.payload.ExchangeRateDetailResponse;
import id.co.asyst.bukopin.mobile.master.model.payload.ExchangeRateResponse;
import id.co.asyst.bukopin.mobile.master.model.soap.exchange.rate.CurrencyType;
import id.co.asyst.bukopin.mobile.master.model.soap.exchange.rate.Fault;
import id.co.asyst.bukopin.mobile.master.model.soap.exchange.rate.HeaderRQ;
import id.co.asyst.bukopin.mobile.master.model.soap.exchange.rate.HeaderRQ.Credentials;
import id.co.asyst.bukopin.mobile.user.model.soap.account.GetAccountBalanceResType;
import id.co.asyst.bukopin.mobile.master.model.soap.exchange.rate.HeaderRS;
import id.co.asyst.bukopin.mobile.master.model.soap.exchange.rate.Reqexchangerate;
import id.co.asyst.bukopin.mobile.master.model.soap.exchange.rate.Resexchangerate;

/**
 * Service Implementation for managing exchange rate.
 * 
 * @author Kartika Dwi Handini
 * @version $Revision$, (Created on Feb 07, 2020)
 * @since 1.1.Alpha1
 */
@Service
public class ExchangeRateService {

    /**
     * Logger.
     */
    private final Logger log = LoggerFactory.getLogger(ExchangeRateService.class);

    /**
     * Port Type (SOAP Manager).
     */
    @Autowired
    private ExchangeRatePT exchangePortType;
    
    @Autowired
    private CurrencyService currencyService;
    
    /* Constructors: */
    public ExchangeRateService(ExchangeRatePT exchangeRatePT) {
	this.exchangePortType = exchangeRatePT;
    }
    
    public ExchangeRateService() {
	// do nothing
    }


    @SuppressWarnings("unused")
	public ExchangeRateResponse getExchangeRate(String currencyNo) {
	// get transaction ID
	String txId = BkpmUtil.generateTrxId();

	log.debug("get exchangerate code "+currencyNo+" tx id " + txId);
	ExchangeRateResponse exchangeRateRes = new ExchangeRateResponse();

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

	Reqexchangerate exchangeRateRQ = new Reqexchangerate();
	CurrencyType cur = new CurrencyType();
	cur.setCurrencycode(currencyNo);
	exchangeRateRQ.setCurrency(cur);

	Holder<HeaderRS> headerRS = new Holder<HeaderRS>();
	Holder<Resexchangerate> exchangeRateRS = new Holder<Resexchangerate>();

	try {
		exchangePortType.getExchangeRate(headerRQ, exchangeRateRQ, headerRS, exchangeRateRS);
	    if ("00".equalsIgnoreCase(exchangeRateRS.value.getResponsemessage().getResponsecode())) {
	    	//get currency data from cache
	    	Currency currency = currencyService.getCurrencyByCurrencyNoCache(currencyNo);	    	
	    	if(null != currency) {
	    		ExchangeRateDetailResponse tt = new ExchangeRateDetailResponse();
		    	tt.setCurrencyNo(currency.getCurrencyNo().toString());
		    	tt.setCode(currency.getCode());
		    	String ttBuy1 = exchangeRateRS.value.getExchangerate().getKursttbuy().substring(0, 5);
		    	String ttBuy2 = exchangeRateRS.value.getExchangerate().getKursttbuy().substring(5, 10);
		    	
		    	String ttSell1 = exchangeRateRS.value.getExchangerate().getKursttsell().substring(0, 5);
		    	String ttSell2 = exchangeRateRS.value.getExchangerate().getKursttsell().substring(5,10);
		    	
		    	Double ttbuy = Double.parseDouble(ttBuy1+"."+ttBuy2);
		    	Double ttsell = Double.parseDouble(ttSell1+"."+ttSell2);
		    	
		    	tt.setBuy(ttbuy.toString());
		    	tt.setSell(ttsell.toString());
		    	
		    	exchangeRateRes.setTT(tt);
	    		
		    	ExchangeRateDetailResponse bankNote = new ExchangeRateDetailResponse();
		    	bankNote.setCurrencyNo(currency.getCurrencyNo().toString());
		    	bankNote.setCode(currency.getCode());
		    	String bankNoteBuy1 = exchangeRateRS.value.getExchangerate().getKursbanknotebuy().substring(0, 5);
		    	String bankNoteBuy2 = exchangeRateRS.value.getExchangerate().getKursbanknotebuy().substring(5, 10);
		    	
		    	String bankNoteSell1 = exchangeRateRS.value.getExchangerate().getKursbanknotesell().substring(0, 5);
		    	String bankNoteSell2 = exchangeRateRS.value.getExchangerate().getKursbanknotesell().substring(5,10);
		    	
		    	Double bankNotebuy = Double.parseDouble(ttBuy1+"."+ttBuy2);
		    	Double bankNotesell = Double.parseDouble(ttSell1+"."+ttSell2);
		    	
		    	bankNote.setBuy(ttbuy.toString());
		    	bankNote.setSell(ttsell.toString());
	    		
		    	exchangeRateRes.setBankNote(bankNote);
	    	} else {
	    		log.error("currency not found in cache");
	    	}
	    } else {
	    	log.error("error get response in exchange rate :" + exchangeRateRS.value.getResponsemessage().getResponsecode());
	    	throw new MiddlewareException(exchangeRateRS.value.getResponsemessage().getResponsecode());
	      }
	   } catch (Fault e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	   }

		return exchangeRateRes;
    }

    
}
