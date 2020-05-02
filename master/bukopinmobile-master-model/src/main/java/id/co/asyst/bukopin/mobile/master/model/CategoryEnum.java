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
 * Catogory Enum
 * Id should be equals with id in table Category.
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Jan 23, 2020
 * @since 1.0.Alpha1-SNAPSHOT
 */
public enum CategoryEnum {
    PULSA(1),
    LISTRIK(2),
    KARTU_KREDIT(3),
    TELEPON_TV(4),
    E_MONEY(5),
    ASURANSI(6),
    SAMOLNAS(7);
    
    private long id;
    
    private CategoryEnum(long id) {
	this.id = id;
    }
    
    public long getId() {
	return id;
    }
}
