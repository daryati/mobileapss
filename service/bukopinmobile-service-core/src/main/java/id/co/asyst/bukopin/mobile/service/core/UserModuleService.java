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
//@ServiceUrl("http://localhost:8080/") // non LB
//@ServiceUrl("http://10.2.62.221:8080/") // dev LB
@ServiceUrl("http://10.0.13.54:8080/") // prod LB
public interface UserModuleService {

    @GET("bukopinmobile-user/api/getAccountInfo/{username}/preHandle")
    Call<CommonResponse> getAccountInfo (@Path(value = "username") String username);
    
    @GET("bukopinmobile-user/account/findUserIdByUsername/{username}")
    Call<CommonResponse> getUserIdByUsername (@Path(value = "username") String username);
    
    @POST("bukopinmobile-user/api/getAccountBalanceByAccNo/preHandle")
    Call<CommonResponse> getAccountBalance (@Body CommonRequest<GetAccountBalanceRequest> accountBalanceReq);
    
    @POST("bukopinmobile-user/api/auth/PIN/verify")
    Call<CommonResponse> verifyPIN (@Body CommonRequest<GetVerifyPINRequest> verifyPinReq);
    
    @GET("bukopinmobile-user/account/findUserByUsername/{username}")
    Call<CommonResponse> getUserByUsername (@Path(value = "username") String username);
    
    @POST("bukopinmobile-user/account/verifyAccountOwner")
    Call<CommonResponse> verifyAccountOwner (@Body CommonRequest request);
    
    @POST("bukopinmobile-user/api/userToken/getByToken")
    Call<CommonResponse> findUserByToken(@Body Map<String,String> token);
    
    @POST("bukopinmobile-user/api/verifyTokenOwner")
    Call<CommonResponse> verifyTokenOwner(@Body Map<String,String> request);
    
    @POST("bukopinmobile-user/api/verifyPhoneOwner")
    Call<CommonResponse> verifyPhoneOwner(@Body CommonRequest request);
    
    @GET("bukopinmobile-user/account/findAccountInfoByAccountNo/{accountNo}")
    Call<CommonResponse> getAccountInfoByAccountNo (@Path(value = "accountNo") String accountNo);
    
    @GET("bukopinmobile-user/api/validateSession/{token}")
    Call<CommonResponse> validateLoginSession(@Path(value="token") String token);
        
    @GET("bukopinmobile-user/account/getAccountCard/{username}")
    Call<CommonResponse> getAccountCardByUsername(@Path(value = "username") String username);

}


