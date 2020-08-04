/*
 * $Id$
 * 
 * Copyright (c) 2020 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
/*
 * Copyright (c) 2020 Lufthansa Systems Indonesia, PT. All rights reserved.
 */
package id.co.asyst.bukopin.mobile.transfer.core.util;

import org.apache.commons.lang3.StringUtils;

import id.co.asyst.bukopin.mobile.common.model.BkpmConstants;

/**
 * Transfer Module util
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Aug 4, 2020
 * @since 1.4.Alpha1
 */
public class TransferUtil {
    /* Constants: */

    /* Attributes: */

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */

    /* Getters & setters for transient attributes: */

    /* Functionalities: */
    /**
     * isOverbook to check is transaction overbook or fund transfer.
     * @param destBankCode The destination bank code.
     * 
     * @return false if destination is not bukopin bank, else true.
     */
    public static boolean isOverbook(String destBankCode) {
	boolean isOverbook = true;
	
	if(StringUtils.isNotBlank(destBankCode) && 
		!BkpmConstants.BUKOPIN_BANK_CODE.equals(destBankCode)) {
	    isOverbook = false;
	}
	
	return isOverbook;
    }

    /* Overrides: */
}
