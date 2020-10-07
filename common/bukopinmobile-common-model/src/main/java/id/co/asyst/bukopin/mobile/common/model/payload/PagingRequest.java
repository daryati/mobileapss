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
package id.co.asyst.bukopin.mobile.common.model.payload;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Request with Pagination
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Sep 17, 2020
 * @since 1.5.Alpha1
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PagingRequest<T> extends CommonRequest<T> implements Serializable{

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 7719182013002103047L;
    
    /* Constants: */

    /* Attributes: */
    /**
     * Pagination Parameter
     */
    private Paging paging;


    /* Constructors: */

    /* Getters & setters for attributes: */
    /**
     * Gets <code>paging</code>.
     * @return The <code>paging</code>.
     */
    public Paging getPaging() {
        return paging;
    }

    /**
     * Sets <code>paging</code>.
     * @param paging The <code>paging</code> to set.
     */
    public void setPaging(Paging paging) {
        this.paging = paging;
    }

    /* Functionalities: */

    /* Overrides: */
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "PagingRequest [paging=" + paging + "]";
    }
}
