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

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Paging Parameter for Pagination
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Sep 17, 2020
 * @since 1.5.Alpha1
 */
@JsonInclude(Include.NON_NULL)
public class Paging {
    /* Constants: */
    /**
     * Default record per page
     */
    public static final int DEFAULT_LIMIT = 10;
    /**
     * Default current page
     */
    public static final int DEFAULT_PAGE = 1;

    
    /* Attributes: */
    /**
     * Current page 
     */
    private int page;
    
    /**
     * Limit data per page
     */
    private int limit;
    
    /**
     * Total all of records
     */
    private long totalRecord;
    

    /* Constructors: */
    public Paging() {
        this.page = DEFAULT_PAGE;
        this.limit = DEFAULT_LIMIT;
    }

    public Paging(int limit) {
        this(DEFAULT_PAGE, limit);
    }

    public Paging(int page, int limit) {
        this.page = page;
        this.limit = limit;
    }
    
    public Paging(int page, int limit, long totalRecord) {
        this.page = page;
        this.limit = limit;
        this.totalRecord = totalRecord;
    }


    /* Getters & setters for attributes: */
    /**
     * Gets <code>page</code>.
     * @return The <code>page</code>.
     */
    public int getPage() {
        return page;
    }

    /**
     * Sets <code>page</code>.
     * @param page The <code>page</code> to set.
     */
    public void setPage(int page) {
        this.page = page;
    }

    /**
     * Gets <code>limit</code>.
     * @return The <code>limit</code>.
     */
    public int getLimit() {
        return limit;
    }

    /**
     * Sets <code>limit</code>.
     * @param limit The <code>limit</code> to set.
     */
    public void setLimit(int limit) {
        this.limit = limit;
    }

    /**
     * Gets <code>totalRecord</code>.
     * @return The <code>totalRecord</code>.
     */
    public long getTotalRecord() {
        return totalRecord;
    }

    /**
     * Sets <code>totalRecord</code>.
     * @param totalRecord The <code>totalRecord</code> to set.
     */
    public void setTotalRecord(long totalRecord) {
        this.totalRecord = totalRecord;
    }

    /* Functionalities: */

    /* Overrides: */
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "Paging [page=" + page + ", limit=" + limit + ", totalRecord=" + totalRecord + "]";
    }
}
