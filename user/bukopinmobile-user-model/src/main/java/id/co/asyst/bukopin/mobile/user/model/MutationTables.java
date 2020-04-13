/*
 * $Id$
 * 
 * Copyright (c) 2020 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.user.model;

import java.util.List;

/**
 * 
 * 
 * @author Ihsan Firman
 * @version $Revision$, Jan 20, 2020
 * @since 2.0
 */
public class MutationTables {
    /* Constants: */

    /* Attributes: */
    private MutationTableHeader header;
    
    private List<AccountStatementFieldTable> fieldtable;


    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */

    /**
     * Gets <code>header</code>.
     * @return The <code>header</code>.
     */
    public MutationTableHeader getHeader() {
        return header;
    }

    /**
     * Sets <code>header</code>.
     * @param header The <code>header</code> to set.
     */
    public void setHeader(MutationTableHeader header) {
        this.header = header;
    }

    /**
     * Gets <code>fieldtable</code>.
     * @return The <code>fieldtable</code>.
     */
    public List<AccountStatementFieldTable> getFieldtable() {
        return fieldtable;
    }

    /**
     * Sets <code>fieldtable</code>.
     * @param fieldtable The <code>fieldtable</code> to set.
     */
    public void setFieldtable(List<AccountStatementFieldTable> fieldtable) {
        this.fieldtable = fieldtable;
    }



    /* Getters & setters for transient attributes: */

    /* Functionalities: */

    /* Overrides: */
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "MutationTables [header=" + header + ", fieldtable=" + fieldtable + "]";
    }
}
