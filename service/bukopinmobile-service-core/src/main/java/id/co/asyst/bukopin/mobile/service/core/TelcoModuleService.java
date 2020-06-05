/*
 * $Id$
 * 
 * Copyright (c) 2020 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.service.core;

import id.co.asyst.bukopin.mobile.service.model.payload.telco.postpaid.TelcoPostpaidInquiryTibcoRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.telco.postpaid.TelcoPostpaidInquiryTibcoResponse;
import id.co.asyst.bukopin.mobile.service.model.payload.telco.postpaid.TelcoPostpaidPaymentTibcoRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.telco.postpaid.TelcoPostpaidPaymentTibcoResponse;
import id.co.asyst.bukopin.mobile.service.model.payload.telco.prepaid.PrepaidTelcoPurchaseRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.telco.prepaid.PrepaidTelcoPurchaseResponse;
import id.co.asyst.bukopin.mobile.service.model.payload.telco.telkomPSTN.speedy.TelkomPSTNSpeedyInquiryRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.telco.telkomPSTN.speedy.TelkomPSTNSpeedyInquiryTibcoResponse;
import id.co.asyst.bukopin.mobile.service.model.payload.telco.telkomPSTN.speedy.TelkomPSTNSpeedyPurchaseTibcoRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.telco.telkomPSTN.speedy.TelkomPSTNSpeedyPurchaseTibcoResponse;
import id.co.asyst.foundation.service.connector.annotations.ServiceUrl;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * 
 * 
 * @author Gibran Haq
 * @version $Revision$, Feb 13, 2020
 * @since 1.1.Alpha1-SNAPSHOT
 */
//@ServiceUrl("http://10.2.62.80:55557") // dev
@ServiceUrl("http://10.0.13.20:55557") // prod
public interface TelcoModuleService {
    
    @POST("/")
    Call<TelcoPostpaidInquiryTibcoResponse> inquiryTelcoPostpaid(@Body TelcoPostpaidInquiryTibcoRequest req);
    
    @POST("/")
    Call<TelcoPostpaidPaymentTibcoResponse> paymentTelcoPostpaid(@Body TelcoPostpaidPaymentTibcoRequest req);

    @POST("/")
    Call<TelkomPSTNSpeedyInquiryTibcoResponse> inquiryTelkomPSTNSpeedy(@Body TelkomPSTNSpeedyInquiryRequest req);
    
    @POST("/")
    Call<TelkomPSTNSpeedyPurchaseTibcoResponse> purchaseTelkomPSTNSpeedy(@Body TelkomPSTNSpeedyPurchaseTibcoRequest req);
    
    @POST("/")
    Call<PrepaidTelcoPurchaseResponse> purchasePrepaidTelco(@Body PrepaidTelcoPurchaseRequest req);
}
