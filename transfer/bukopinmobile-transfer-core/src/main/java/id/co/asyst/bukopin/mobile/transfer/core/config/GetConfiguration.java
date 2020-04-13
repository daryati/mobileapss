package id.co.asyst.bukopin.mobile.transfer.core.config;

import java.io.IOException;
import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.Holder;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.co.asyst.bukopin.mobile.common.core.util.BkpmUtil;
import id.co.asyst.bukopin.mobile.common.core.util.ConnectionUtils;
import id.co.asyst.bukopin.mobile.common.core.util.JsonVariable;
import id.co.asyst.bukopin.mobile.common.model.BkpmConstants;
import id.co.asyst.bukopin.mobile.common.model.payload.Identity;
import id.co.asyst.bukopin.mobile.transfer.core.service.soap.PostingPT;
import id.co.asyst.bukopin.mobile.transfer.core.util.AuthUtil;
import id.co.asyst.bukopin.mobile.transfer.model.InquiryAccountReq;
import id.co.asyst.bukopin.mobile.transfer.model.InquiryAccountRes;
import id.co.asyst.bukopin.mobile.transfer.model.PostingElementRes;
import id.co.asyst.bukopin.mobile.transfer.model.PostingReq;
import id.co.asyst.bukopin.mobile.transfer.model.PostingRes;
import id.co.asyst.bukopin.mobile.transfer.model.PostingToReq;
import id.co.asyst.bukopin.mobile.transfer.model.entity.ReceiverInfo;
import id.co.asyst.bukopin.mobile.transfer.model.entity.UserTransfer;
import id.co.asyst.bukopin.mobile.transfer.model.entity.Bank;
import id.co.asyst.bukopin.mobile.transfer.model.entity.FundTransfer;
import id.co.asyst.bukopin.mobile.transfer.model.soap.posting.Fault;
import id.co.asyst.bukopin.mobile.transfer.model.soap.posting.HeaderRQ;
import id.co.asyst.bukopin.mobile.transfer.model.soap.posting.HeaderRQ.Credentials;
import id.co.asyst.bukopin.mobile.transfer.model.soap.posting.HeaderRS;
import id.co.asyst.bukopin.mobile.transfer.model.soap.posting.Posting;
import id.co.asyst.bukopin.mobile.transfer.model.soap.posting.iso8583.Elements;
import id.co.asyst.bukopin.mobile.transfer.model.soap.posting.iso8583.ISOMessages;
import id.co.asyst.bukopin.mobile.user.model.entity.User;

/**
 * Service Implementation for managing Account Balance.
 * 
 * @author Kartika Dwi Handini
 * @version $Revision$, (Created on Dec 07, 2019)
 * @since 1.0.Alpha1
 */

@Service
@Transactional
public class GetConfiguration {

    /**
     * Logger.
     */
    private final Logger log = LoggerFactory.getLogger(GetConfiguration.class);
    
    @Autowired
    private Environment env;

	public String getConfigValue (String name ) {   
		log.debug("get Configuration ..... ");
    	String result="";    	
    	String url = env.getProperty("url.masterdata.configuration")+"bukopinmobile-master/config/getConfigByName/"+name;
	     
    	log.debug("url Conf"+url);
	    Map<String, String> header = new HashMap<String, String>();
	    header = new HashMap<String, String>();
	    header.put("Content-Type", "application/json");
	    header.put("Accept", "application/json");
	    
	    String res;
		try {
			res = ConnectionUtils.urlAccess(url,header).toString();
			JSONParser parser = new JSONParser();
			if(res != null){
				JSONObject responseJson = (JSONObject) parser.parse(res);
				log.debug("response "+responseJson);
				
				String statusObj = (String) responseJson.get("message");
				if("success".equalsIgnoreCase(statusObj)) {
					JSONObject resultobj = (JSONObject) responseJson.get("result");
					result = (String) resultobj.get("value");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
        return result;
    }
}
