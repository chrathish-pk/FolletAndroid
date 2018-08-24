/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.data.remote.api;


import com.follett.fsc.mobile.circdesk.data.model.LoginResults;
import com.follett.fsc.mobile.circdesk.data.model.ScanPatron;
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

    @GET("rest/v4/circulation/scanpatron")
    Observable<ScanPatron> getScanPatron(@Query("contextName") String contextName, @Query("site") String site, @Query("client") String client,
                                         @Query("barcode") String barcode, @Query("appID") String appID, @Query("device") String device,
                                         @Query("appVersion") String appVersion, @Query("appLanguage") String appLanguage);

    @GET("rest/v4/district/login")
    Observable<LoginResults> getLoginResult(@Query("contextName") String contextName, @Query("site") String site,
                                            @Query("client") String client, @Query("password") String password,
                                            @Query("userName") String userName,
                                            @Query("appID") String appID, @Query("device") String device,
                                            @Query("appVersion") String appVersion, @Query("appLanguage") String appLanguage);




}
