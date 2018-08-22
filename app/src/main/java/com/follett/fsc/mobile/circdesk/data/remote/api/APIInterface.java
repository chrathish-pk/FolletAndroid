/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.data.remote.api;


import com.follett.fsc.mobile.circdesk.data.model.Version;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {

//    @GET("/rest/v4/district/sites")
//    Call<SiteResults> getSchoolList(@Query("contextName") String contextName, @Query("client") String client, @Query("productTypes") String productTypes,
//            @Query("appID") String appID, @Query("device") String device, @Query("appVersion") String appVersion, @Query("appLanguage") String appLanguage);
//
    
    @GET("/rest/version")
    Observable<Version> getVersion(@Query("contextName") String contextName, @Query("client") String client, @Query("appID") String appId, @Query("device")
            String device, @Query("appVersion") String appVersion, @Query("appLanguage") String appLanguage);
    
    
}
