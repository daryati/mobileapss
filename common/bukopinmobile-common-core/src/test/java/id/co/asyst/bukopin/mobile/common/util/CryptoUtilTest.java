/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.common.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import ch.qos.logback.core.net.SyslogOutputStream;
import id.co.asyst.bukopin.mobile.common.core.util.CryptoUtil;

/**
 * Test Class for CryptoUtil
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Nov 23, 2019
 * @since 1.0.Alpha1-SNAPSHOT
 */
@SpringBootTest
public class CryptoUtilTest {
    /* Constants: */
    private static final String SECRET_KEY = "kopibuaya1asyst!";
    private static final String PLAIN_TEXT = "Encrypt me!";
    private static final String HMAC_ENCRYPTED_TEXT = "d8f18ea523c7eeba4ac494a31c7cf50dfab70c0935c279091adc9b1320d01484";
    private static final String BASE64_ENCRYPTED_TEXT = "RW5jcnlwdCBtZSE=";
    private static final String PASSWORD = "password00";
    private static final String ENCRYPTED_PWD = "2QAJUY1mE9chfyqRaDchTeM9Y8TpfHue+YVuhun6wuS4iWsPhL/g3blZ+TLUQ7Up";
    private static final String REF_CODE = "4217900020|4217900020|PLUCAH4TQB82|6017";
    private static final String REF_CODE_AES = "V5y1NtPgTsA/zb8QAFPcuY+DR48pccRFNc3jgH1gTHV3nXVeoB/M4+qJmhUhJzbc";
    private static final String AES_HEX_ENCRYPTED = "c872460c1c3008041556ae3e0070a7ff";
    

    /* Attributes: */

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */

    /* Getters & setters for transient attributes: */

    /* Functionalities: */

    /* Overrides: */
//    @Test
//    public void testEncryptHmacSHA256() {
//	try {
//	    String encoded = CryptoUtil.encryptHmacSHA256(SECRET_KEY, PLAIN_TEXT);
//	    assertEquals(HMAC_ENCRYPTED_TEXT, encoded);
//	} catch (Exception e) {
//	    // TODO Auto-generated catch block
//	    e.printStackTrace();
//	}
//    }
    
//    @Test
//    public void testEncryptBase64() {
//	String encrypted = CryptoUtil.encryptBase64(PLAIN_TEXT);
//	assertEquals(BASE64_ENCRYPTED_TEXT, encrypted);
//    }
    
//    @Test
//    public void testDecryptBase64() {
//	String plainText = CryptoUtil.decryptBase64("MDgxMjU2");
//	System.out.println(plainText);
//	assertEquals(PLAIN_TEXT, plainText);
//    }
    
//    @Test
//    public void testEncryptAES() {
//	System.out.println("Test encrypt AES...");
//	String encrypted = CryptoUtil.encryptAES(REF_CODE);
//	assertEquals(REF_CODE_AES, encrypted);
//    }
//    
//    @Test
//    public void testDecryptAES() {
//	System.out.println("Test decrypt AES...");
//	String decrypted = CryptoUtil.decryptAES(REF_CODE_AES);
//	assertEquals(REF_CODE, decrypted);
//    }
//    
//    @Test
//    public void testMatchAES() {
//	System.out.println("Test match AES...");
//	boolean isMatch = CryptoUtil.matchAES(REF_CODE, REF_CODE_AES);
//	
//	assertTrue(isMatch);
//    }
//    
//    @Test
//    public void testEncryptPassword() {
//	System.out.println("Test encrypt password...");
//	String encrypted = "";
//	try {
//	    encrypted = CryptoUtil.encryptPassword(PASSWORD);
//	} catch(Exception e) {
//	    e.printStackTrace();
//	}
//	String decrypted = CryptoUtil.decryptPassword(encrypted);
//	assertNotNull(decrypted);
//    }
//    
//    @Test
//    public void testDecryptPassword() {
//	System.out.println("Test decrypt password...");
//	String decrypted = "";
//	try {
//	    decrypted = CryptoUtil.decryptPassword(ENCRYPTED_PWD);
//	} catch(Exception e) {
//	    e.printStackTrace();
//	}
//	assertEquals(PASSWORD, decrypted);
//    }
//    
    @Test
    public void testEncryptAESHex() {
	System.out.println("Test encrypt AES in Hex...");
	String encrypted = CryptoUtil.encryptAESHex("andri1");
	System.out.println(encrypted);
//	assertEquals(AES_HEX_ENCRYPTED, encrypted);
    }
//
//    @Test
//    public void testDecryptAESHex() {
//	System.out.println("Test decrypt AES in Hex...");
//	String decrypted = CryptoUtil.decryptAESHex(AES_HEX_ENCRYPTED);
//	System.out.println(decrypted);
//	assertEquals(PLAIN_TEXT, decrypted);
//    }
}
