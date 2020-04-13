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

import id.co.asyst.bukopin.mobile.service.model.payload.pln.postpaid.PostpaidRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.pln.postpaid.PostpaidResponse;
import id.co.asyst.bukopin.mobile.service.model.payload.pln.prepaid.PrepaidAdviceRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.pln.prepaid.PrepaidAdviceResponse;
import id.co.asyst.bukopin.mobile.service.model.payload.pln.prepaid.PrepaidInquiryRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.pln.prepaid.PrepaidInquiryResponse;
import id.co.asyst.bukopin.mobile.service.model.payload.pln.prepaid.PrepaidPurchaseRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.pln.prepaid.PrepaidPurchaseResponse;
import id.co.asyst.foundation.service.connector.annotations.ServiceUrl;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * PLN Service Connector
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Dec 6, 2019
 * @since 1.0.Alpha1-SNAPSHOT
 */
@ServiceUrl("http://10.2.62.80:55557") // dev
//@ServiceUrl("http://10.0.13.20:55557") // prod
public interface PLNService {
    
    @POST("/")
    Call<PrepaidInquiryResponse> prepaidInquiry (@Body PrepaidInquiryRequest request);
    
    @POST("/")
    Call<PrepaidPurchaseResponse> prepaidPurchase (@Body PrepaidPurchaseRequest request);
    
    @POST("/")
    Call<PrepaidAdviceResponse> prepaidAdvice (@Body PrepaidAdviceRequest request);
    
    @POST("/")
    Call<PostpaidResponse> postpaid (@Body PostpaidRequest request);
}
