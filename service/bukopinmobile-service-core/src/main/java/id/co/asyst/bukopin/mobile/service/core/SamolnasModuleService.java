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

import id.co.asyst.bukopin.mobile.service.model.payload.samolnas.SamolnasInquiryTibcoRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.samolnas.SamolnasInquiryTibcoResponse;
import id.co.asyst.bukopin.mobile.service.model.payload.samolnas.SamolnasPaymentTibcoRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.samolnas.SamolnasPaymentTibcoResponse;
import id.co.asyst.foundation.service.connector.annotations.ServiceUrl;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * 
 * 
 * @author Gibran Haq
 * @version $Revision$, Apr 27, 2020
 * @since 1.2.Alpha1
 */
@ServiceUrl("http://10.2.62.80:55557") // dev
//@ServiceUrl("http://10.0.13.20:55559") // prod
public interface SamolnasModuleService {
	
	@POST("/")
	Call<SamolnasInquiryTibcoResponse> inquirySamolnas(@Body SamolnasInquiryTibcoRequest req);
	
	@POST("/")
	Call<SamolnasPaymentTibcoResponse> paymentSamolnas(@Body SamolnasPaymentTibcoRequest req);

}
