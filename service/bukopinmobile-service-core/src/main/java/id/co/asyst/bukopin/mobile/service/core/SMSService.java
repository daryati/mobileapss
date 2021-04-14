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

import id.co.asyst.bukopin.mobile.service.model.payload.SMSGatewayRequest;
import id.co.asyst.foundation.service.connector.annotations.ServiceUrl;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * SMS  Gateway Service Connector
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Oct 30, 2019
 * @since 1.0.Alpha1-SNAPSHOT
 */
@ServiceUrl("http://10.2.62.239:8080/") // dev
//@ServiceUrl("http://10.0.14.115:80/") // prod
public interface SMSService {
	
	@POST("/E-MobileSMSMTPushBKP/EMobileSMSMTPushBKP")
	Call<Object> sendSMS(@Body SMSGatewayRequest request);
	
}
