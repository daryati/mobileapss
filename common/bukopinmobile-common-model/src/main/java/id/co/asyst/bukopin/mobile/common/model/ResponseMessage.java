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

/**
 * Response Message
 * 
 * @author Gibran Haq
 * @version $Revision$, Nov 27, 2019
 * @since 1.0.Alpha1
 */
public enum ResponseMessage {
    /**
     * TODO: Code Review: Document Me.
     */
    SUCCESS("000"),
    /**
     * TODO: Code Review: Document Me.
     */
    ERROR("900"),
    /**
     * TODO: Code Review: Document Me.
     */
    INVALID_INPUT("101"),
    DUPLICATE_DATA("102"),
    DATA_REQUIRED("103"),
    DATA_NOT_FOUND("104"),
    DATA_NOT_MATCH("105"),
    CARD_EXPIRED("106"),
    NO_ACCOUNT_NUMBER_AVAILABLE("107"),
    INPUT_ALREADY_ADDED("108"),
    DATA_OVER_LIMIT("109"),
    PLN_SUBSCRIBER_SUSPENDED("110"),
    INVALID_AMOUNT("111"),
    PHONE_NUMBER_EXPIRED("112"),
    CUST_BLOCKED("113"),
    INVALID_TRANSACTION("114"),
    
    PERMISSION_NOT_ALLOWED("201"),
    REQUEST_TIME_OUT("202"),
    NO_RESPONSE("203"),
    POSTING_FAILED("204"),
    SESSION_TIME_OUT("205"),
    USER_NOT_REGISTERED("206"),
    REGISTER_USER_FAILED("207"),
    ERROR_RESET_PASSWORD("208"),
    ERROR_SEND_OTP("209"),
    ERROR_FORGOT_PASSWORD("210"),
    ERROR_UNLOCK_USER("211"),
    ERROR_UPDATE_STATUS_USER("212"),
    ACTIVATION_USER_FAILED("213"),
    USER_IS_LOCKED("214"),
    ERROR_UPDATE_USER("215"),
    ERROR_DOUBLE_LOGIN("216"),
    ERROR_CUT_OFF_PLN("217"),
    ERROR_ADVICE_FAILED("218"),
    ERROR_SYSTEM_FAILURE("219"),
    INVALID_MIN_LIMIT_AMOUNT("220"),
    INVALID_MAX_LIMIT_AMOUNT("221"),
    TRANSACTION_PROCESSING("222"),
    
    // Business Process
    AMOUNT_NOT_ENOUGH("301"),
    LIMIT_TRANSFER_DAY("302"),
    ERROR_BILL_ALREADY_PAID("303"),
    ERROR_BILL_NOT_AVAILABLE("304"),
    ERROR_INACTIVE_BANK_ACCOUNT("305"),
    ERROR_WAIT_A_MOMENT("306"),
    ERROR_CUSTOMER_NAME_NOT_SAME("307"),
    ERROR_EXCEED_BILL_LIMIT("308"),
    ERROR_DIFFERENT_BILL("309"),
    ERROR_VOUCHER_OUT_OF_STOCK("310"),
    ERROR_INVALID_INTERVAL("311"),
    ERROR_BALANCE_LESS_MINIMUM("312"),
    ERROR_ACCOUNT_ALREADY_SAVED("313"),
    ERROR_INVALID_WOKEE_ACCOUNT("314"),
    ERROR_MUST_BE_MAIN_ACCOUNT("315"),
    ERROR_WOKEE_ACCOUNT_CLOSED("316"),
    
    // External Error
    ERROR_EXTERNAL("600"),
    ERROR_CENTAGATE("601"),
    ERROR_TIBCO("602"),
    ERROR_ARANET("603"),
    
    INTERNAL_SERVER_ERROR("500");
    
    private String code;
    
    
    // Constructor
    /**
     * @param code
     */
    private ResponseMessage(String code) {
	this.code = code;
    }
    
    /**
     * Gets <code>code</code>.
     * @return The <code>code</code>.
     */
    public String getCode() {
        return code;
    }
    /**
     * Sets <code>code</code>.
     * @param code The <code>code</code> to set.
     */
    public void setCode(String code) {
        this.code = code;
    }
    
    

}
