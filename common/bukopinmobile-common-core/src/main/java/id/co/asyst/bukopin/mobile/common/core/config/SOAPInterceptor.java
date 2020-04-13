/*
 * $Id$
 * 
 * Copyright (c) 2020 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.common.core.config;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * SOAP Client Interceptor to log request response SOAP ws.
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Apr 1, 2020
 * @since 1.1.Alpha1
 */
public class SOAPInterceptor implements SOAPHandler<SOAPMessageContext> {

    /**
     * Logger
     */
    private final Logger log = LoggerFactory.getLogger(SOAPInterceptor.class);
    
    /* Constants: */
    /**
     * Additional string in soap body log
     */
    private final static String CLOSING_SOAP_STRING = "-----------------";

    /* Attributes: */

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */

    /* Getters & setters for transient attributes: */

    /* Functionalities: */

    /* Overrides: */
    @Override
    public void close(MessageContext context) {
//	log.info("SOAP close()");
    }

    @Override
    public boolean handleFault(SOAPMessageContext context) {
//	log.info("SOAP handleFault()");
	return true;
    }

    @Override
    public boolean handleMessage(SOAPMessageContext context) {
//	log.info("SOAP handleMessage()");
	
	// extract message info
	Boolean isRequest = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
	String endpoint = (String) context.get(BindingProvider.ENDPOINT_ADDRESS_PROPERTY);
	String soapAction = (String) context.get(BindingProvider.SOAPACTION_URI_PROPERTY);
	SOAPMessage msg = context.getMessage();
	
	StringBuilder sb = new StringBuilder();
	ByteArrayOutputStream out = new ByteArrayOutputStream();
	
	if(isRequest) {
	    log.info("Request SOAP: ");
	    sb.append("\n---[HTTP Request - "+endpoint+"]---\n");
	    sb.append("SOAPAction: "+soapAction+"\n");
	    
	    // log soap content
	    try {
		msg.writeTo(out);
		sb.append(out.toString());
		out.flush();
		out.close();
		
		// additional string
		sb.append(CLOSING_SOAP_STRING);
	    } catch (SOAPException | IOException e) {
		log.error("failed to log SOAP request.");
	    }
	} else {
	    log.info("Response SOAP: ");
	    sb.append("\n---[HTTP Response - "+endpoint+"]---\n");
	    Map<String,List<String>> respHeaders = (Map<String, List<String>>) context.get(MessageContext.HTTP_RESPONSE_HEADERS);
	    respHeaders.entrySet().forEach(h -> {
		sb.append(h.getKey()+" : "+h.getValue()+"\n");
	    });

	    // log soap content
	    try {
		msg.writeTo(out);
		sb.append(out.toString());
		out.flush();
		out.close();
		
		// additional string
		sb.append(CLOSING_SOAP_STRING);
	    } catch (SOAPException | IOException e) {
		log.error("failed to log SOAP response.");
	    }
	}
	log.debug(sb.toString());
	
	return true;
    }

    @Override
    public Set<QName> getHeaders() {
//	log.info("SOAP getHeaders()");
	return null;
    }
}
