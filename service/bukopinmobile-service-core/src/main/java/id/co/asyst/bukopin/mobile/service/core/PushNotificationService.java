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

import java.util.Map;

import id.co.asyst.foundation.service.connector.annotations.ServiceUrl;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * 
 * 
 * @author Gibran Haq
 * @version $Revision$, Jun 2, 2020
 * @since 1.2.Alpha1
 */
@ServiceUrl("https://fcm.googleapis.com/")
public interface PushNotificationService {

    @POST("fcm/send")
    Call<Object> pushNotif(@Header("Authorization") String auth, @Header("Content-Type") String contentType, @Body Object req);
}
