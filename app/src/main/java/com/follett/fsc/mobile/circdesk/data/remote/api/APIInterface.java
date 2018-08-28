/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.data.remote.api;


import com.follett.fsc.mobile.circdesk.data.model.LoginResults;
import com.follett.fsc.mobile.circdesk.data.model.ScanPatron;
import com.follett.fsc.mobile.circdesk.data.model.SiteResults;
import com.follett.fsc.mobile.circdesk.data.model.Version;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface APIInterface {

//    @GET("/rest/v4/district/sites")
//    Call<SiteResults> getSchoolList(@Query("contextName") String contextName, @Query("client") String client, @Query("productTypes") String productTypes,
//            @Query("appID") String appID, @Query("device") String device, @Query("appVersion") String appVersion, @Query("appLanguage") String appLanguage);
//

    @GET("/rest/version")
    @JsonAndXmlConverters.Xml
    Observable<Version> getVersion(@Query("contextName") String contextName, @Query("client") String client, @Query("appID") String appId, @Query("device")
            String device, @Query("appVersion") String appVersion, @Query("appLanguage") String appLanguage);

//    @GET("/rest/v4/district/sites")
//    Observable<SiteResults> getSchoolList(@Query("contextName") String contextName, @Query("client") String client, @Query("productTypes") String
//            productTypes, @Query("appID") String appID, @Query("device") String device, @Query("appVersion") String appVersion, @Query("appLanguage") String
//            appLanguage);

    @GET("/rest/v4/district/sites")
    @JsonAndXmlConverters.Xml
    Call<SiteResults> getSchoolList(@Query("contextName") String contextName, @Query("client") String client, @Query("productTypes") String productTypes,
                                    @Query("appID") String appID, @Query("device") String device, @Query("appVersion") String appVersion, @Query("appLanguage") String appLanguage);

    @GET("rest/v4/district/login")
    @JsonAndXmlConverters.Xml
    Observable<LoginResults> getLoginResults(@Query("contextName") String contextName, @Query("site") String site, @Query("client") String client, @Query
            ("password") String password, @Query("userName") String userName, @Query("appID") String appID, @Query("device") String device, @Query
                                                     ("appVersion") String appVersion, @Query("appLanguage") String appLanguage);

    @Headers({
            "Cookie: JSESSIONID=zsX-V2pbla8sCH4kTrpuSaqgWRAx_gOhRBq75gcX",
            "text/xml: gzip"
    })
    @GET("rest/v4/circulation/scanpatron")
    @JsonAndXmlConverters.Xml
    Observable<ScanPatron> getScanPatron(@Query("contextName") String contextName, @Query("site") String site, @Query("client") String client,
                                         @Query("barcode") String barcode, @Query("appID") String appID, @Query("device") String device,
                                         @Query("appVersion") String appVersion, @Query("appLanguage") String appLanguage);

}
