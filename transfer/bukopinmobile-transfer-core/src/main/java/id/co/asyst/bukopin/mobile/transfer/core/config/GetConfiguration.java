package id.co.asyst.bukopin.mobile.transfer.core.config;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.co.asyst.bukopin.mobile.common.core.util.ConnectionUtils;

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
