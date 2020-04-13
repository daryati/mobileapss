/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.user.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.co.asyst.bukopin.mobile.user.core.dao.BukopinDao;
import id.co.asyst.bukopin.mobile.user.model.entity.internal.DebitCardInfo;

/**
 * Service Implementation for Managing Internal Bukopin Data
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Nov 5, 2019
 * @since 1.0.Alpha1-SNAPSHOT
 */
@Service
public class BukopinService {
    /* Constants: */

    /* Attributes: */
    @Autowired
    private BukopinDao bukopinDao;

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */

    /* Getters & setters for transient attributes: */

    /* Functionalities: */
    /**
     * Get Card Information By Card Number
     * 
     * @param cardNumber The Card number
     * @return Debit Card Information. Empty object if no card exist.
     */
    public List<DebitCardInfo> getCardByCardNumber(String cardNumber) {
	return bukopinDao.getCardByCardNumber(cardNumber);
    }

    /* Overrides: */
}
