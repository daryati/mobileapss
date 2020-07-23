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

import id.co.asyst.bukopin.mobile.service.model.payload.insurance.InsuranceInquiryRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.insurance.InsuranceInquiryResponse;
import id.co.asyst.bukopin.mobile.service.model.payload.insurance.InsurancePurchaseRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.insurance.InsurancePurchaseResponse;
import id.co.asyst.foundation.service.connector.annotations.ServiceUrl;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * EMoney Service Connector
 * 
 * @author Kartika Dwi H
 * @version $Revision$, Mar 16, 2020
 * @since 1.1.Alpha1-SNAPSHOT
 */
//@ServiceUrl("http://10.2.62.80:55557") // dev
@ServiceUrl("http://10.0.13.20:55559") // prod
public interface InsuranceModuleService {
    
    @POST("/")
    Call<InsuranceInquiryResponse> inquiryInsurance (@Body InsuranceInquiryRequest request);
    
    @POST("/")
    Call<InsurancePurchaseResponse> purchaseInsurance (@Body InsurancePurchaseRequest request);
    
}
