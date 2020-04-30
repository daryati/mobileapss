/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.common.core.util;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Random;
import java.util.UUID;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Common Utilities for Bukopin Mobile
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Oct 29, 2019
 * @since 1.0.Alpha1-SNAPSHOT
 */
public class BkpmUtil {
    /**
     * Logger.
     */
    private static final Logger log = LoggerFactory.getLogger(BkpmUtil.class);
    
    /* Constants: */

    /* Attributes: */

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */

    /* Getters & setters for transient attributes: */

    /* Functionalities: */
    /**
     * Convert Object To JSON format
     * 
     * @param T,
     *            The Object to convert.
     * @return The Object in JOSN format.
     */
    public static String convertToJson(Object T) {
	String json = "";
	if (T == null) {
	    json = null;
	} else {
	    ObjectMapper Obj = new ObjectMapper();
	    try {
		json = Obj.writeValueAsString(T);
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}

	return json;
    }
    
    /**
     * Converts JAXBElement Object to XML (unformatted).
     * 
     * @param source
     *            The source JAXBElement Object to convert.
     * @param type
     *            Classes to be bound.
     * @return XML result.
     * @throws JAXBException
     *             JAXB Exception.
     */
    public static String convertToXml(Object source, Class<?>... type) {
        String result="";
        StringWriter sw = new StringWriter();

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(type);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, false);
            marshaller.marshal(source, sw);

            result = sw.toString();
        } catch (JAXBException e) {
//            log.error("Error while convert Object to XML, caused by: "+e.getCause(), e);
        }

        return result;
    }

    /**
     * Make Field Readable
     * 
     * @param fieldName
     *            The field name without space. (e.g. firstName)
     * @return The readable field name. (e.g. First Name)
     */
    public static String convertReadableField(String fieldName) {
	String result = "";

	// split words
	result = fieldName.replaceAll("(.)([A-Z])", "$1 $2");
	// capitalize first char
	result = result.substring(0, 1).toUpperCase() + result.substring(1);

	return result;
    }

    /**
     * generated transaction ID
     * 
     */
    public static String generateTrxId() {
	// generated uuid
	String uuid = UUID.randomUUID().toString().replace("-", "");
	return uuid;
    }
    
    /**
     * Generate Trx Id
     * 
     * @param length The length of ID.
     * @return n char of generated ID.
     */
    public static String generateTrxId(int length) {
	// generated uuid
	String uuid = generateTrxId();
	uuid = uuid.substring(0,length);
	return uuid;
    }
    
    /**
     * Generator random alpha numeric
     * 
     * @param length of alpha numeric
     * @return string, new combination of alpha numeric
     */
    public static String generateAlphaNumeric(int length) {
	String charNumber = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
	StringBuilder salt = new StringBuilder();
	Random rnd = new Random();
	while (salt.length() < length) { // length of the random string.
	    int index = (int) (rnd.nextFloat() * charNumber.length());
	    salt.append(charNumber.charAt(index));
	}
	String aplhaNumber = salt.toString();
	return aplhaNumber;
    }

    /* Overrides: */
}
