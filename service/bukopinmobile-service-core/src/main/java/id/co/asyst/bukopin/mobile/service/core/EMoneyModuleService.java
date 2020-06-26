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

import id.co.asyst.bukopin.mobile.service.model.payload.emoney.EMoneyInquiryRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.emoney.EMoneyInquiryResponse;
import id.co.asyst.bukopin.mobile.service.model.payload.emoney.EMoneyPurchaseRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.emoney.EMoneyPurchaseResponse;
import id.co.asyst.bukopin.mobile.service.model.payload.emoney.LinkAjaInquiryRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.emoney.LinkAjaInquiryResponse;
import id.co.asyst.bukopin.mobile.service.model.payload.emoney.LinkAjaPurchaseResponse;
import id.co.asyst.bukopin.mobile.service.model.payload.emoney.LinkAjaPurchaseRequest;
import id.co.asyst.foundation.service.connector.annotations.ServiceUrl;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * EMoney Service Connector
 * 
 * @author Kartika Dwi H
 * @version $Revision$, Jan 21, 2020
 * @since 1.1.Alpha1-SNAPSHOT
 */
//@ServiceUrl("http://10.2.62.80:55557") // dev
@ServiceUrl("http://10.0.13.20:55557") // prod
public interface EMoneyModuleService {
    
    @POST("/")
    Call<EMoneyInquiryResponse> emoneyInquiry (@Body EMoneyInquiryRequest request);
    
    @POST("/")
    Call<EMoneyPurchaseResponse> emoneyPurchase (@Body EMoneyPurchaseRequest request);
    
    @POST("/")
    Call<LinkAjaInquiryResponse> linkAjaInquiry (@Body LinkAjaInquiryRequest request);
    
    @POST("/")
    Call<LinkAjaPurchaseResponse> linkAjaPurchase (@Body LinkAjaPurchaseRequest request);
}
