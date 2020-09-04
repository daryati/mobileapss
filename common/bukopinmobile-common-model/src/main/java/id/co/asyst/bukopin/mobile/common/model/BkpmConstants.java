/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.common.model;

import java.text.SimpleDateFormat;

/**
 * Constants for Bukopin Mobile
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Oct 23, 2019
 * @since 1.0.Alpha1-SNAPSHOT
 */
public class BkpmConstants {

    // TODO: Code Review, move configurations into Application Profile:

    // CENTAGATE
    public static final String HMAC_ALGORITHM = "HmacSHA256";
    // Code Response
    public static final String CODE_CTG_SUCCESS = "0";
    public static final String CODE_CTG_INVALID_CREDENTIAL = "23001";
    public static final String CODE_CTG_PASSWORD_CANNOT_SAME_WITH_USERNAME = "28011";
    public static final String CODE_CTG_PASSWORD_CANNOT_SAME = "28007";
    public static final String CODE_STG_USER_LOCKED = "22033";
    public static final String CODE_CTG_USER_NOT_FOUND = "22002";
    public static final String CODE_CTG_PASSWORD_NOT_ALLOWED_LAST = "28003";
    public static final String CODE_CTG_DUPLICATE_EMAIL = "22001";
    public static final String CODE_CTG_USER_LIMIT_REACH = "22013";
    public static final String CODE_CTG_DUPLICATE_USERNAME = "22025";
    public static final String CODE_CTG_INVALID_LICENSE_FILE = "27001";
    public static final String CODE_CTG_USER_NOT_LOCKED = "22016";
    // Fields
    public static final String FIELD_HMAC = "hmac";
    public static final String FIELD_SERIAL_UID = "serialVersionUID";

    // SOAP
    public static final String CODE_SOAP_SUCCESS = "000";

    // Account Card Type
    public static final String CODE_TYPE_SAVING = "10";
    public static final String CODE_TYPE_GIRO = "20";

    // AUTHENTICATION
    public static final String OTP_ALGORITHM = "SHA1PRNG";
    public static int OTP_LENGTH = 6;
    public static int OTP_SMS_VALID_TIME = 5; // in minutes
    public static final String pinId = "MA==";
    public static final String pinQuestion = "RU5URVIgUElO";

    // clientID and Transaction ID
    public static final String CLIENT_ID = "111111";
    public static final String MERCHANT_ID = "6017";
    public static final String FORWARDING_INSTITUTION_ID = "441";
    public static final String CURRENCY_CODE_RUPIAH = "360";
    public static final String BUKOPIN_BANK_CODE = "441";
    public static final String OVERBOOK = "OVERBOOK";
    public static final String FUND_TRANSFER = "FUND_TRANSFER";
    public static final String TRANSFER_CHANNEL_ID = "MOBILEBKP";

    // REGEX
    public static final String NUMERIC_REGEX = "[0-9]+";
    public static final String PHONE_NUMBER_REGEX = "^(?:\\+62|0)[1-9]{10,15}$";
    public static final String EMAIL_REGEX = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
    public static final String USERNAME_REGEX = "";

    // DATE FORMAT
    public static final SimpleDateFormat sdfDateTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    public static final SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
    public static final String FORMAT_TIME_WITH_ZONE = "yyyy-MM-dd hh:mm:ss Z";
    
    // CRYPTO
    public static final String ENV_ENCRYPT_PWD_KEY = "BKPM_ENCRYPTION_PASSWORD";
    public static final int SALT_LENGTH = 12;
    
    // CONNECTION UTIL
    public static final String HTTP_HEADER_CONTENT_JSON = "application/json";
    public static final String HTTP_HEADER_CONTENTTYPE = "Content-Type";
    // api key for overbook transfer
    public static final String API_KEY_HEADER = "Api-key";
    public static final String BKP_API_KEY_HEADER = "BKP-Apikey";
    public static final String API_KEY_VALUE = "77474043-b7a0-489f-81c5-387ae027f8aa";
    
    public static final String STATUS_SUCCESS = "SUCCESS";
    
    //HTTP CONSTANT
    
    //HTTP Request Method (GET).
    public static final String REQUEST_METHOD_GET = "GET";
    
    //HTTP Header (value: {@value} ).
    public static final String HTTP_HEADER_ACCEPT = "Accept";
    
    //HTTP Proxy Host Key (value: {@value} ).
    public static final String HTTP_PROXYHOST_KEY = "http.proxyHost";
    
    //HTTP Proxy Port Key (value: {@value} ).
    public static final String HTTP_PROXYPORT_KEY = "http.proxyPort";
    
    //HTTPS Proxy Host Key (value: {@value} ).
    public static final String HTTPS_PROXYHOST_KEY = "https.proxyHost";
    
    //HTTPS Proxy Port Key (value: {@value} ).
    public static final String HTTPS_PROXYPORT_KEY = "https.proxyPort";
    
    //HTTP Request Method (POST).
    public static final String REQUEST_METHOD_POST = "POST";
    
    public static final String STATUS = "status";
    
    public static final String HTTP_STATUS_OK = "200";
    public static final String HTTP_STATUS_CREATED = "201";
    public static final String HTTP_STATUS_ACCEPTED = "202";
    
    public static final String WHITE_SPACE = " ";
    
    /**
     * AES Secret Key to encrypt and decrypt
     */
    public final static String AES_SECRET_KEY = "Gi1oV68mklSFzq9W";
    
    /**
     * Bukopin Logger's key
     */
    public final static String MDC_KEY_LOG = "bkpm-logger";
    /**
     * Bukopin Request Duration Logger's key
     */
    public final static String MDC_KEY_LOG_DURATION = "bkpm-request-duration";
    
    /**
     * Http Header: Device Id/ Phone Identity
     */
    public final static String HTTP_HEADER_DEVICE_ID = "Phone-Identity";
    
    
    /* CONFIGURATION KEYS (NAME in table CONFIGURATION): */
    /**
     * name of session timeout in CONFIGURATION table
     */
    public static final String KEY_SESSION_TIME_OUT = "SESSION_TIME_OUT";
    /**
     * Reset Password Duration
     */
    public static final String KEY_RESET_PASSWORD_DURATION = "RESET_PASSWORD_DURATION";
    /**
     * Not activated accounts 
     */
    public static final String KEY_ACCOUNT_NOT_ACTIVATED = "NOT_ACTIVATED";
    /**
     * Non Transactional Product key 
     */
    public static final String KEY_NON_TRX_ACCOUNT = "NOT_FUND_SOURCE";
    
    
    /* OTHERS: */
    /**
     * Length of Account Number Bukopin
     */
    public static final int BUKOPIN_ACCNO_LENGTH = 10;
    public static final String BUKOPIN_ACCNO_PADDING = "0";
}
