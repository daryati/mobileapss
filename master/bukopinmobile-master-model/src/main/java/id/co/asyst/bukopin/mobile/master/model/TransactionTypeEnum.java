/*
 * $Id$
 * 
 * Copyright (c) 2020 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.master.model;

/**
 * Transaction Type Enum (each enum element has 1 table in db)
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Jan 24, 2020
 * @since 1.1.Alpha1
 */
public enum TransactionTypeEnum {
    PLNPRE,
    PLNPOST,
    EMONEY,
    TELCOPRE,
    TELCOPOST,
    INSURANCE
}
