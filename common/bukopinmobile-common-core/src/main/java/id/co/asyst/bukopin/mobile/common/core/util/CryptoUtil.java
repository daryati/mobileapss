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

import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.codec.Hex;

import id.co.asyst.bukopin.mobile.common.model.BkpmConstants;

/**
 * Cryptography Util
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Nov 23, 2019
 * @since 1.0.Alpha1-SNAPSHOT
 */
public class CryptoUtil {
    /**
     * Logger
     */
    private final static Logger log = LoggerFactory.getLogger(CryptoUtil.class);
    
    /* Constants: */
    /**
     * Random String (in decrypted password) length
     */
    private final static int RANDOM_STRING_LENGTH = 10;

    /* Attributes: */

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */

    /* Getters & setters for transient attributes: */

    /* Functionalities: */
    /**
     * Encrypt Hmac SHA256
     * 
     * @param key
     *            The secret key.
     * @param plainText
     *            The text to encrypt.
     * @return The encrypt plainText.
     * @throws Exception
     */
    public static String encryptHmacSHA256(String key, String plainText) throws Exception {
	SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), BkpmConstants.HMAC_ALGORITHM);
	Mac sha256 = Mac.getInstance(BkpmConstants.HMAC_ALGORITHM);
	sha256.init(keySpec);

	String encrypt = new String(Hex.encode(
		sha256.doFinal(plainText.getBytes(StandardCharsets.UTF_8))));
	return encrypt;
    }

    /**
     * Get Hmac Encoded of an Object (model)
     * 
     * @param object
     *            The object to encode its attributes.
     * @param exclude
     *            The list of Object's attributes which will not encoded.
     * @param key
     *            The secret key to encode.
     * @return Hmac 256 value of Object attributes.
     * @throws Exception
     */
    public static String getHmacObject(Object object, List<String> exclude, String key) throws Exception {
	Field[] fields = object.getClass().getDeclaredFields();
	String str = "";
	for (Field f : fields) {
	    f.setAccessible(true); // make private fields accessible
	    if (!exclude.contains(f.getName())) {
		str += String.valueOf(f.get(object));
	    }
	}
	return encryptHmacSHA256(key, str);
    }

    /**
     * Hash Plain Text using SHA256
     * 
     * @param password
     *            Plain text to hash.
     * @return SHA-256 Hashed plain text.
     * @throws NoSuchAlgorithmException
     */
    public static String hashSHA256(String password) throws NoSuchAlgorithmException {
	MessageDigest digest = MessageDigest.getInstance("SHA-256");
	byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
	String sha256hex = new String(Hex.encode(hash));
	return sha256hex;
    }
    
    /**
     * Encrypt Plain Text using Base64
     * 
     * @param plainText The Plain Text to encrypt.
     * @return Encrypted text.
     */
    public static String encryptBase64(String plainText) {
	byte[] encrypted = Base64.getEncoder().encode(plainText.getBytes());
	return new String(encrypted, StandardCharsets.UTF_8);
    }
    
    /**
     * Decrypt Base64
     * 
     * @param encryptedText The encrypted text.
     * @return The original text.
     */
    public static String decryptBase64(String encryptedText) {
	byte[] decrypted = Base64.getDecoder().decode(encryptedText.getBytes());
	return new String(decrypted, StandardCharsets.UTF_8);
    }
    
    /**
     * Encrypt AES
     * 
     * @param plainText The plain text to encrypt.
     * @param keyText The secret key.
     * @return The encrypted text in base64.
     */
    public static String encryptAES(String plainText, String keyText) {
	System.out.println("Encrypting AES: "+plainText);
	byte[] aes = processAES(plainText.getBytes(StandardCharsets.UTF_8), keyText, true);
	String encrypted = Base64.getEncoder().encodeToString(aes);
	
	return encrypted;
    }
    
    /**
     * Encrypt AES
     * 
     * @param plainText The plain text to encrypt.
     * @return The encrypted text in base64.
     */
    public static String encryptAES(String plainText) {
	return encryptAES(plainText, BkpmConstants.AES_SECRET_KEY);
    }
    
    /**
     * Encrypt AES Hex
     * 
     * @param plainText The plain text to encrypt.
     * @param keyText The secret key.
     * @return The encrypted text in Hex.
     */
    public static String encryptAESHex(String plainText) {
	System.out.println("Encrypting AES Hex: "+plainText);
	byte[] aes = processAES(plainText.getBytes(StandardCharsets.UTF_8), BkpmConstants.AES_SECRET_KEY, true);
	String encrypted = new String(Hex.encode(aes));
	
	return encrypted;
    }
    
    /**
     * Decrypt AES
     * 
     * @param encryptedText The Base64 encrypted text.
     * @param keyText The secret key.
     * @return The plain decrypted text.
     */
    public static String decryptAES(String encryptedText, String keyText) {
	byte[] aes = Base64.getDecoder().decode(encryptedText.getBytes());
	byte[] text = processAES(aes, keyText, false);
	
	// trim cuts off any leading null characters
	return new String(text, StandardCharsets.UTF_8).trim();
    }
    
    /**
     * Decrypt AES
     * 
     * @param encryptedText The encrypted text.
     * @return The plain decrypted text.
     */
    public static String decryptAES(String encryptedText) {
	return decryptAES(encryptedText, BkpmConstants.AES_SECRET_KEY);
    }
    
    /**
     * Decrypt AES Hex
     * 
     * @param encryptedText The Hex encrypted text.
     * @param keyText The secret key.
     * @return The plain decrypted text.
     */
    public static String decryptAESHex(String encryptedText) {
	byte[] aes = Hex.decode(encryptedText);
	byte[] text = processAES(aes, BkpmConstants.AES_SECRET_KEY, false);
	
	// trim cuts off any leading null characters
	return new String(text, StandardCharsets.UTF_8).trim();
    }
    
    /**
     * Match AES
     * 
     * @param plainText The Plain Text to match 
     * @param encryptedText The AES Encrypted Text to match.
     * @return True, if plainText and decrypted of encryptedText is equals. Else return false.
     */
    public static boolean matchAES(String plainText, String encryptedText) {
	System.out.println("Matching AES: "+plainText+" "+encryptedText);
	String decrypted = decryptAES(encryptedText, BkpmConstants.AES_SECRET_KEY);
	return plainText.equals(decrypted);
    }
    
    /**
     * Process AES
     * 
     * @param input The bit of Plain Text to encrypt.
     * @param keyText The key of encryption.
     * @param isEncrypt True for encryption, false for decryption.
     * @return Array of bytes of encrypted.
     */
    private static byte[] processAES(byte[] input, String keyText, boolean isEncrypt) {
	BlockCipher engine = new AESEngine();
	BufferedBlockCipher cipher = new PaddedBufferedBlockCipher(engine);
	
	byte[] key = keyText.getBytes();
	cipher.init(isEncrypt, new KeyParameter(key));
	byte[] cipherText = new byte[cipher.getOutputSize(input.length)];
	int outputLen = cipher.processBytes(input, 0, input.length, cipherText, 0);
	
	try {
	    cipher.doFinal(cipherText, outputLen);
	} catch (CryptoException ce) {
	    ce.printStackTrace();
	}
	
	return cipherText;
    }
    
    /**
     * Decrypt Password
     * 
     * @param encryptedText The encrypted password.
     * @return Decrypted password.
     */
    public static String decryptPassword(String encryptedText) {
	String pwdAndKey = decryptPasswordAndKey(encryptedText);
	
	// Get password only (pwdAndKey = key10Char+password)
//	String password = pwdAndKey.replace(BkpmConstants.PWD_ENCRYPTION_KEY, "");
	String password = pwdAndKey.substring(RANDOM_STRING_LENGTH, pwdAndKey.length());
	
	return password;
    }
    
    /**
     * Decrypt Password and Key
     * <ol>
     * <li>Decrypt AES.</li>
     * <li>Extract Password. Get even characters from last character.</li>
     * </ol>
     * 
     * @param encryptedText The encrypted password.
     * @return Decrypted Password and Secret Key.
     */
    public static String decryptPasswordAndKey(String encryptedText) {
	// 1. Decrypt AES
	String decrypted = decryptAES(encryptedText);

	// 2. Extract Password. Get even characters from last character.
	StringBuilder sb = new StringBuilder();
	for(int i=decrypted.length()-1; i >= 0; i--) {
	    // Get even characters from last character.
	    if (i % 2 == 0) {
		sb.append(decrypted.charAt(i));
	    }
	}

	return sb.toString();
    }
    
    /**
     * Encrypt Password
     * 
     * @param password The Password to encrypt.
     * @return The AES encrypted password.
     */
    public static String encryptPassword(String password) {
	log.debug("encrypt: "+password);
	
	String randomKey = BkpmUtil.generateTrxId(RANDOM_STRING_LENGTH);
	
	// 1. Add secret key
	String toEncrypt = randomKey+password;
	
	// 2. Insert random characters between "toEncrypt"
	String randomString = BkpmUtil.generateTrxId(toEncrypt.length());
	StringBuilder sb = new StringBuilder();
	for(int i=toEncrypt.length()-1; i >= 0; i--) {
	    sb.append(toEncrypt.charAt(i));
	    if(i!=0) {
		sb.append(randomString.charAt(i));
	    }
	}
	// 3. Encrypt using AES	
	String encrypted = encryptAES(sb.toString());
	
	return encrypted;
    }
    
    /* Overrides: */
}
