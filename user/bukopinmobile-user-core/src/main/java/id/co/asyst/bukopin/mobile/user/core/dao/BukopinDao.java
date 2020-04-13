/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.user.core.dao;

import java.util.List;

import id.co.asyst.bukopin.mobile.user.model.entity.internal.DebitCardInfo;

/**
 * Internal Bukopin Dao Interface
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Nov 5, 2019
 * @since 1.0.Alpha1-SNAPSHOT
 */
public interface BukopinDao {
    /**
     * Get Card Information By Card Number
     * 
     * @param accountNumber The Card number
     * @return Debit Card Information. Empty object if no card exist.
     */
    public List<DebitCardInfo> getCardByCardNumber(String accountNumber);
}
