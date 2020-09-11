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
package id.co.asyst.bukopin.mobile.common.model;

/**
 * System Cut Off Enum
 * <p>
 * This enum is pair of Module Name and id of SYSTEM_CUT_OFF data in Database.
 * </p>
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, May 28, 2020
 * @since 1.4.Alpha1
 */
public enum SystemCutOffEnum {
    OVERBOOK(1L),
    FUND_TRANSFER(2L),
    PLN(3L),
    GOPAY(4L),
    OVO(5L),
    LINKAJA(6L),
    TELKOMSEL(7L),
    TRI(8L),
    XL(9L),
    SMARTFREN(10L),
    INDOSAT(11L),
    TELKOM(12L),
    TELKOM_INTERNET(13L),
    BPJS(14L),
    SAMOLNAS(15L);
    
    private long id;
    
    SystemCutOffEnum(long id) {
	this.id = id;
    }

    /**
     * Gets <code>id</code>.
     * @return The <code>id</code>.
     */
    public long getId() {
        return id;
    }

    /**
     * Sets <code>id</code>.
     * @param id The <code>id</code> to set.
     */
    public void setId(long id) {
        this.id = id;
    }

}
