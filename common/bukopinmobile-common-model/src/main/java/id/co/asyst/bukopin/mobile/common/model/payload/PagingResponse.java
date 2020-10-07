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
 * Response with Pagination
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Sep 17, 2020
 * @since 1.4.Alpha1
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PagingResponse extends CommonResponse implements Serializable{

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 2314937121722877704L;
    
    /* Constants: */

    /* Attributes: */
    /**
     * Pagination Info
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
	return "PagingResponse [paging=" + paging + "]";
    }
}
