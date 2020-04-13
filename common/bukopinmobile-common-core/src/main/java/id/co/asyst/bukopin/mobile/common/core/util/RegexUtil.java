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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Regex Util
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Nov 14, 2019
 * @since 1.0.Alpha1-SNAPSHOT
 */
public class RegexUtil {
    /* Constants: */
    public static final String NUMERIC_REGEX = "[0-9]+";
    public static final String PHONE_NUMBER_REGEX = "^(?:\\+62|0)[0-9]{10,15}$";
    public static final String EMAIL_REGEX = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
    
    // TODO password should be 6-10 chars, alphanumeric

    /* Attributes: */

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */

    /* Getters & setters for transient attributes: */

    /* Functionalities: */
    /**
     * Validate Email
     * 
     * @param plaintext The email in string.
     * @return true if valid, else false.
     */
    public static boolean validateEmail(String plaintext) {
	Pattern pattern = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);
	Matcher matcher = pattern.matcher(plaintext);
	return matcher.find();
    }
    
    /**
     * Validate Phone Number
     * 
     * @param plaintext The phone number in string.
     * @return true if valid, else false.
     */
    public static boolean validatePhoneNumber(String plaintext) {
	Pattern pattern = Pattern.compile(PHONE_NUMBER_REGEX);
	Matcher matcher = pattern.matcher(plaintext);
	return matcher.find();
    }

    /* Overrides: */
}
