/*
 * $Id$
 * 
 * Copyright (c) 2014 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */

package id.co.asyst.bukopin.mobile.common.core.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector;

/**
 * 
 * 
 * @author Irvan Ahadi
 * @version $Revision$, 15 Des 2014
 * @since 1.0.Alpha1
 */
public abstract class JsonUtil implements Serializable {

	protected static final Logger log = LogManager.getLogger(JsonUtil.class);
	
    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = -8816694622495796954L;

    /* Constants: */
    /**
     * XML Charset used for Document Builder.
     */
    private static final String xmlCharset = "UTF-8";
    /**
     * Empty Element Value.
     */
    public static final String EMPTY_ELEMENT_VALUE = "";
    /**
     * New Line.
     * 
     * @since 1.0.Alpha2
     */
    public static final String NEW_LINE = "\n\r";
    /**
     * Message Type (Inbound).
     * 
     * @since 1.0.Alpha2
     */
    public static final String MESSAGE_TYPE_INBOUND = "Inbound Message";
    /**
     * Message Type (Outbound).
     * 
     * @since 1.0.Alpha2
     */
    public static final String MESSAGE_TYPE_OUTBOUND = "Outbound Message";
    /**
     * Message Info (ID).
     * 
     * @since 1.0.Alpha2
     */
    public static final String MESSAGE_INFO_ID = "ID: ";
    /**
     * Message Info (Address).
     * 
     * @since 1.0.Alpha2
     */
    public static final String MESSAGE_INFO_ADDRESS = "Address: ";
    /**
     * Message Info (Remote IP Address).
     * 
     * @since 1.0.Alpha2
     */
    public static final String MESSAGE_INFO_REMOTE_IP = "Remote Host: ";
    /**
     * Message Info (Encoding).
     * 
     * @since 1.0.Alpha2
     */
    public static final String MESSAGE_INFO_ENCODING = "Encoding: ";
    /**
     * Message Info (Content Type).
     * 
     * @since 1.0.Alpha2
     */
    public static final String MESSAGE_INFO_CONTENTTYPE = "Content Type: ";
    /**
     * Message Info (Headers).
     * 
     * @since 1.0.Alpha2
     */
    public static final String MESSAGE_INFO_HEADERS = "Headers: ";
    /**
     * Message Info (Payload).
     * 
     * @since 1.0.Alpha2
     */
    public static final String MESSAGE_INFO_PAYLOAD = "Payload: ";
    /**
     * Message Error - General (value <code>{@value}</code>).
     * 
     * @since 1.0.Alpha2
     */
    public static final String MESSAGE_ERROR_GENERAL = "ERROR/INVALID REQUEST";
    /**
     * Message Error - Mode (value <code>{@value}</code>).
     * 
     * @since 1.0.Alpha2
     */
    public static final String MESSAGE_ERROR_MODE = "ERROR/INVALID REQUEST MODE";

    /* Constructors: x */

    /* Functionalities: */
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
    public static String convertObjectToXml(Object source, Class<?>... type)
            throws JAXBException {
        String result;
        StringWriter sw = new StringWriter();

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(type);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, false);
            marshaller.marshal(source, sw);

            result = sw.toString();
        } catch (JAXBException e) {
            throw e;
        }

        return result;
    }

    /**
     * Converts JAXBElement Object to XML (with format option).
     * 
     * @param source
     *            The source JAXBElement Object to convert.
     * @param formattedOutput
     *            Formatted Output flag (<code>true</code> for XML output with
     *            carriage return. <code>false</code> for XML output with
     *            carriage return).
     * @param type
     *            Classes to be bound.
     * @return XML result.
     * @throws JAXBException
     *             JAXB Exception.
     */
    public static String convertObjectToXml(Object source,
            boolean formattedOutput, Class<?>... type) throws JAXBException {
        String result;
        StringWriter sw = new StringWriter();

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(type);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
                    formattedOutput);
            marshaller.marshal(source, sw);

            result = sw.toString();
        } catch (JAXBException e) {
            throw e;
        }

        return result;
    }

    /**
     * Converts JAXBElement Object to XML (with format option).
     * 
     * @param source
     *            The source JAXBElement Object to convert.
     * @param formattedOutput
     *            Formatted Output flag (<code>true</code> for XML output with
     *            carriage return. <code>false</code> for XML output with
     *            carriage return).
     * @return XML result.
     * @throws JAXBException
     *             JAXB Exception.
     */
    @SuppressWarnings("deprecation")
	public static String convertObjectToJson(Object source,
            boolean formattedOutput) throws JAXBException {
        String result = "";
        StringWriter sw = new StringWriter();

        ObjectMapper mapper = new ObjectMapper();
        AnnotationIntrospector introspector = new JaxbAnnotationIntrospector();
        // make deserializer use JAXB annotations (only)
        mapper.getDeserializationConfig().withInsertedAnnotationIntrospector(
                introspector);
        // make serializer use JAXB annotations (only)
        mapper.getSerializationConfig().withInsertedAnnotationIntrospector(
                introspector);

        try {
            result = mapper.writeValueAsString(source);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(),e);
        }

        // AnnotationIntrospector primary = new JacksonAnnotationIntrospector();
        // AnnotationIntrospector secondary = new JaxbAnnotationIntrospector();
        // AnnotationIntrospector pair = new
        // AnnotationIntrospector.Pair(primaryIntrospector,
        // secondaryIntropsector);

        // try {
        // JAXBContext jaxbContext = JAXBContext.newInstance(type);
        // Marshaller marshaller = jaxbContext.createMarshaller();
        // marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
        // formattedOutput);
        // marshaller.marshal(source, sw);
        //
        // result = sw.toString();
        // } catch (JAXBException e) {
        // throw e;
        // }

        return result;
    }

    /**
     * Converts XML to JAXBElement Object.
     * 
     * @param source
     *            The XML source to convert.
     * @param type
     *            Classes to be bound.
     * @return XML result.
     * @throws JAXBException
     *             JAXB Exception.
     */
    public static Object convertXmlToObject(String source, Class<?>... type)
            throws JAXBException {

        Object result = null;

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(type);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            result = unmarshaller.unmarshal(new StreamSource(new StringReader(
                    source)));

        } catch (JAXBException e) {
            throw e;
        }

        return result;
    }

    /**
     * extractValue
     * 
     * @param xml
     * @param xPathExpression
     * @return
     */
    public static String extractValue(String xml, String xPathExpression) {
        String value = null;

        if (xml != null) {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
                    .newInstance();
            documentBuilderFactory.setIgnoringElementContentWhitespace(true);
            documentBuilderFactory.setNamespaceAware(true);

            try {
                DocumentBuilder documentBuilder = documentBuilderFactory
                        .newDocumentBuilder();

                byte[] byteArray = xml.getBytes(xmlCharset);
                InputStream inputStream = new ByteArrayInputStream(byteArray);
                Document document = documentBuilder.parse(inputStream);
                XPathFactory xPathFactory = XPathFactory.newInstance();
                XPath xPath = xPathFactory.newXPath();

                value = xPath.evaluate(xPathExpression, document,
                        XPathConstants.STRING).toString();

            } catch (ParserConfigurationException e) {
                log.error(e.getMessage(), e);
            } catch (UnsupportedEncodingException e) {
            	log.error(e.getMessage(), e);
            } catch (SAXException e) {
            	log.error(e.getMessage(), e);
            } catch (IOException e) {
            	log.error(e.getMessage(), e);
            } catch (XPathExpressionException e) {
            	log.error(e.getMessage(), e);
            }

        }
        return value;
    }

}

