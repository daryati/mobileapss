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
import id.co.asyst.foundation.service.connector.annotations.ServiceUrl;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * 
 * 
 * @author Abi Nanel
 * @version $Revision$, Dec 19, 2019
 * @since 1.0.Alpha1
 */
@ServiceUrl("http://localhost:8082/bukopinmobile-master/") // dev
//@ServiceUrl("http://10.0.13.61:8082/bukopinmobile-master/") // prod
public interface MasterModuleService {

//    @POST("destination/saveToDestination")
//    Call<CommonResponse> saveToFavourite (@Body Map<String, Object> request);
        
    @POST("destination/saveToDestinationCommon")
    Call<CommonResponse> saveToFavouriteCommon(@Body CommonRequest request);
    
    @GET("config/getConfigByName/{name}")
    Call<CommonResponse> findConfigByName(@Path(value = "name") String name);
    
    @POST("prefixTelco/findByPrefixNoAndType")
    Call<CommonResponse> findByPrefixNoAndType(@Body CommonRequest request);
    
    @POST("prefixTelco/findByPrefixNoAndType")
    Call<CommonResponse> findByPrefixNoAndType(@Header("Accept-Language") String language, @Body CommonRequest request);
    
    @POST("prefixTelco/findByPrefixIdAndInstitutionType")
    Call<CommonResponse> findByPrefixIdAndInstitutionType(@Body CommonRequest request);
    
    @POST("prefixTelco/findByPrefixIdAndInstitutionType")
    Call<CommonResponse> findByPrefixIdAndInstitutionType(@Header("Accept-Language") String language, @Body CommonRequest request);
    
    @GET("institution/findCodeByProvider/{provider}")
    Call<CommonResponse> findCodeByProvider(@Path(value = "provider") String provider);
    
    @GET("institution/findCodeByProvider/{provider}")
    Call<CommonResponse> findCodeByProvider(@Header("Accept-Language") String language, @Path(value = "provider") String provider);
    
    @GET("prefixTelco/findOneByPrefixNo/{custNo}")
    Call<CommonResponse> findOneByPrefixNo(@Path(value = "custNo") String custNo);
    
    @GET("prefixTelco/findOneByPrefixNo/{custNo}")
    Call<CommonResponse> findOneByPrefixNo(@Header("Accept-Language") String language, @Path(value = "custNo") String custNo);
    
    @GET("listInsurance/allInsurance")
    Call<CommonResponse> getListInsurance();
}
