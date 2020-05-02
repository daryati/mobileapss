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

import id.co.asyst.bukopin.mobile.service.model.payload.cc.InquiryCreditCardTibcoReq;
import id.co.asyst.bukopin.mobile.service.model.payload.cc.InquiryCreditCardTibcoResponse;
import id.co.asyst.bukopin.mobile.service.model.payload.cc.PaymentCreditCardTibcoReq;
import id.co.asyst.bukopin.mobile.service.model.payload.cc.PaymentCreditCardTibcoResponse;
import id.co.asyst.foundation.service.connector.annotations.ServiceUrl;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * 
 * 
 * @author Ihsan Firman
 * @version $Revision$, Apr 9, 2020
 * @since 2.0
 */

@ServiceUrl("http://10.2.62.80:55557") // dev
//@ServiceUrl("http://10.0.13.20:55557") // prod
public interface PaymentModuleService {

    @POST("/")
    Call<InquiryCreditCardTibcoResponse> creditCardInquiryTibcoResponse(@Body InquiryCreditCardTibcoReq req);
    
    @POST("/")
    Call<PaymentCreditCardTibcoResponse> creditCardPaymentTibcoResponse(@Body PaymentCreditCardTibcoReq req);
}
