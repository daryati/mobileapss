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
package id.co.asyst.bukopin.mobile.service.core;

import id.co.asyst.bukopin.mobile.common.model.payload.CommonRequest;
import id.co.asyst.bukopin.mobile.common.model.payload.CommonResponse;
import id.co.asyst.foundation.service.connector.annotations.ServiceUrl;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Transfer Module Service
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Jul 7, 2020
 * @since 1.4.Alpha1
 */
@ServiceUrl("http://localhost:8081/") // non LB
//@ServiceUrl("http://10.2.62.221:8081/") // dev LB
//@ServiceUrl("http://10.0.13.54:8081/") // prod LB
public interface TransferModuleService {
    
    @GET("bukopinmobile-transfer/limitPackage/getDefault")
    Call<CommonResponse> getDefaultLimit();
    
    @POST("bukopinmobile-transfer/limitPackage/verifydailylimit")
    Call<CommonResponse>verifydailylimit  (@Body CommonRequest request);
    
    @POST("bukopinmobile-transfer/limitPackage/prosesdailyLimit")
    Call<CommonResponse>prosesdailyLimit  (@Body Object request);

}
