/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.master.model.payload;

import java.util.List;
import org.json.JSONArray;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * 
 * @author Kartika Dwi H
 * @version $Revision$, Feb 07, 2020
 * @since 1.1.Alpha1
 */
public class SavingsResponse {
    /* Constants: */

    /* Attributes: */
    private List<SavingsDetailResponse> savingsList;

	public List<SavingsDetailResponse> getSavingsList() {
		return savingsList;
	}

	public void setSavingsList(List<SavingsDetailResponse> savingsList) {
		this.savingsList = savingsList;
	}

}
