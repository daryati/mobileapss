/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.service.core;

import java.util.Map;

import id.co.asyst.bukopin.mobile.common.model.payload.CommonRequest;
import id.co.asyst.bukopin.mobile.common.model.payload.CommonResponse;
import id.co.asyst.bukopin.mobile.service.model.payload.GetAccountBalanceRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.pln.GetVerifyPINRequest;
import id.co.asyst.foundation.service.connector.annotations.ServiceUrl;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * 
 * 
 * @author Ihsan Firman
 * @version $Revision$, Nov 27, 2019
 * @since 2.0
 */
@ServiceUrl("http://localhost:8080/")
public interface NonFinancialService {

    @SuppressWarnings("rawtypes")
	@POST("bukopinmobile-user/nonFinancial/saveReport")
    Call<CommonResponse> saveReportNonFinancial (@Body CommonRequest request);
    
    
}


