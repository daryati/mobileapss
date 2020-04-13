/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.common.model.payload;

import java.io.Serializable;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Search Criteria Request Model
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Oct 24, 2019
 * @since 1.0.Alpha1-SNAPSHOT
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Criteria implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3453843674888250568L;
	
	/* Constants: */

	/* Attributes: */
	/**
	 * Page number
	 */
	private int page;
    
    /**
     * Total Data Per Page
     */
    private int dataPerPage;
    
    /**
     * Total All Data
     */
    private int totalData;
    
    /**
     * Total Pages 
     */
    private int totalPages;
    
    /**
     * Sorting Data <fieldName, ASC/DESC>
     */
    private Map<String,String> sort;
    
    /**
     * Data Filter <fieldName, searchObject>
     */
    private Map<String,Object> search;

	/* Constructors: */

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
	 * Gets <code>dataPerPage</code>.
	 * @return The <code>dataPerPage</code>.
	 */
	public int getDataPerPage() {
		return dataPerPage;
	}

	/**
	 * Sets <code>dataPerPage</code>.
	 * @param dataPerPage The <code>dataPerPage</code> to set.
	 */
	public void setDataPerPage(int dataPerPage) {
		this.dataPerPage = dataPerPage;
	}

	/**
	 * Gets <code>totalData</code>.
	 * @return The <code>totalData</code>.
	 */
	public int getTotalData() {
		return totalData;
	}

	/**
	 * Sets <code>totalData</code>.
	 * @param totalData The <code>totalData</code> to set.
	 */
	public void setTotalData(int totalData) {
		this.totalData = totalData;
	}

	/**
	 * Gets <code>totalPages</code>.
	 * @return The <code>totalPages</code>.
	 */
	public int getTotalPages() {
		return totalPages;
	}

	/**
	 * Sets <code>totalPages</code>.
	 * @param totalPages The <code>totalPages</code> to set.
	 */
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	/**
	 * Gets <code>sort</code>.
	 * @return The <code>sort</code>.
	 */
	public Map<String, String> getSort() {
		return sort;
	}

	/**
	 * Sets <code>sort</code>.
	 * @param sort The <code>sort</code> to set.
	 */
	public void setSort(Map<String, String> sort) {
		this.sort = sort;
	}

	/**
	 * Gets <code>search</code>.
	 * @return The <code>search</code>.
	 */
	public Map<String, Object> getSearch() {
		return search;
	}

	/**
	 * Sets <code>search</code>.
	 * @param search The <code>search</code> to set.
	 */
	public void setSearch(Map<String, Object> search) {
		this.search = search;
	}

	/**
	 * Gets <code>serialversionuid</code>.
	 * @return The <code>serialversionuid</code>.
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/* Functionalities: */

	/* Overrides: */
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Criteria [page=" + page + ", dataPerPage=" + dataPerPage + ", totalData=" + totalData + ", totalPages="
				+ totalPages + ", sort=" + sort + ", search=" + search + "]";
	}
}
