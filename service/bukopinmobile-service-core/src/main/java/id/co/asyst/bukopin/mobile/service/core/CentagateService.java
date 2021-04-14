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

import id.co.asyst.bukopin.mobile.service.model.payload.CentagateCommonResponse;
import id.co.asyst.bukopin.mobile.service.model.payload.ChallengeQuestionRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.CtgDeleteUser;
import id.co.asyst.bukopin.mobile.service.model.payload.LoginCentagateRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.LogoutCentagateRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.QuestionAuthRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.RegistrationCentagateRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.UnlockUserRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.UpdatePasswordCentagateRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.UpdateUserProfileRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.UpdateUserStatusCentagateRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.ResetPasswordCentagateRequest;
import id.co.asyst.bukopin.mobile.service.model.payload.ResetQARequest;
import id.co.asyst.bukopin.mobile.service.model.payload.SaveQARequest;
import id.co.asyst.foundation.service.connector.annotations.ServiceUrl;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Centagate Service Connector
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Oct 28, 2019
 * @since 1.0.Alpha1-SNAPSHOT
 */
@ServiceUrl("https://10.0.138.30:443/") // dev
//@ServiceUrl("https://10.0.16.33:443/") // prod
public interface CentagateService {

    @POST("/CentagateWS/webresources/auth/authBasic")
    Call<CentagateCommonResponse> login(@Body LoginCentagateRequest request);

    @PUT("/CentagateWS/webresources/user/unlockUser/{userAdmin}")
    Call<CentagateCommonResponse> unlockUser(@Path(value = "userAdmin") String userAdmin,
	    @Body UnlockUserRequest request);

    @PUT("/CentagateWS/webresources/password/resetbyusername/{username}")
    Call<CentagateCommonResponse> resetPassword(@Body ResetPasswordCentagateRequest request,
	    @Path("username") String username);

    @POST("/CentagateWS/webresources/auth/kba/getQuestions")
    Call<CentagateCommonResponse> requestQuestion(@Body ChallengeQuestionRequest request);

    @PUT("/CentagateWS/webresources/security/saveUserQuestions/{userAdmin}")
    Call<CentagateCommonResponse> savePin(@Body SaveQARequest request);

    @POST("/CentagateWS/webresources/auth/authQna")
    Call<CentagateCommonResponse> authPin(@Body QuestionAuthRequest request);

    @PUT("/CentagateWS/webresources/user/status/update/{userAdmin}")
    Call<CentagateCommonResponse> updateUserStatus(@Path(value = "userAdmin") String userAdmin,
	    @Body UpdateUserStatusCentagateRequest request);

    @PUT("/CentagateWS/webresources/user/registerUserActivate/{username}")
    Call<CentagateCommonResponse> registration(@Path(value = "username") String username,
	    @Body RegistrationCentagateRequest request);

    @PUT("/CentagateWS/webresources/user/updatebyusername/{username}")
    Call<CentagateCommonResponse> updateUserProfile(@Path(value = "username") String username,
	    @Body UpdateUserProfileRequest request);
    
    @PUT("/CentagateWS/webresources/user/unbindAndDelete/{username}")
    Call<CentagateCommonResponse> deleteUser(@Path(value = "username") String username, @Body CtgDeleteUser request);

    @PUT("/CentagateWS/webresources/security/resetQaBPI/{username}")
    Call<CentagateCommonResponse> resetQA(@Path(value = "username") String username, @Body ResetQARequest request);

    @PUT("/CentagateWS/webresources/password/updatesimple/{username}")
    Call<CentagateCommonResponse> updatePasswordCentagate(@Path(value = "username") String username,
	    @Body UpdatePasswordCentagateRequest request);
    
    @POST("/CentagateWS/webresources/auth/logout")
    Call<CentagateCommonResponse> logout(@Body LogoutCentagateRequest request);

}
