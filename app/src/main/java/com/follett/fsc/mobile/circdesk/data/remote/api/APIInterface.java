/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.data.remote.api;


import com.follett.fsc.mobile.circdesk.feature.checkoutcheckin.CheckoutResult;
import com.follett.fsc.mobile.circdesk.feature.checkoutcheckin.ScanPatron;
import com.follett.fsc.mobile.circdesk.feature.iteminfo.model.TitleDetails;
import com.follett.fsc.mobile.circdesk.feature.loginsetup.LoginResults;
import com.follett.fsc.mobile.circdesk.feature.loginsetup.SiteResults;
import com.follett.fsc.mobile.circdesk.feature.loginsetup.Version;

import java.util.Map;
import com.follett.fsc.mobile.circdesk.feature.checkoutcheckin.CheckoutResult;
import com.follett.fsc.mobile.circdesk.feature.patronstatus.model.PatronInfo;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface APIInterface {

    @Headers({
            "Accept: application/json",
            "text/xml: gzip"
    })
    @GET("/rest/version")
    @JsonAndXmlConverters.Xml
    Observable<Version> getVersion();

    @Headers({
            "Accept: application/json",
            "text/xml: gzip"
    })
    @GET("/rest/v4/district/sites")
    @JsonAndXmlConverters.Xml
    Observable<SiteResults> getSchoolList(@Query("contextName") String contextName);

//    @Headers({
//            "Accept: application/json",
//            "text/xml: gzip"
//    })
//    @GET("rest/v4/district/login")
//    @JsonAndXmlConverters.Xml
//    Observable<LoginResults> getLoginResultsDummy(@Query("contextName") String contextName, @Query("site") String site, @Query("client") String client, @Query
//            ("password") String password, @Query("userName") String userName, @Query("appID") String appID, @Query("device") String device, @Query
//                                                     ("appVersion") String appVersion, @Query("appLanguage") String appLanguage);

    @GET("rest/v4/circulation/scanpatron")
    @JsonAndXmlConverters.Xml
    Observable<ScanPatron> getScanPatron(@HeaderMap Map<String, String> headers, @Query("contextName") String contextName, @Query("site") String site, @Query("client") String client,
                                         @Query("barcode") String barcode, @Query("appID") String appID, @Query("device") String device,
                                         @Query("appVersion") String appVersion, @Query("appLanguage") String appLanguage);

    @GET("rest/v4/circulation/checkout")
    Observable<CheckoutResult> getCheckoutResult(@HeaderMap Map<String, String> headers, @Query("contextName") String contextName, @Query("site") String site,
                                                 @Query("barcode") String barcode, @Query("patronID") String patronID,
                                                 @Query("collectionType") String collectionType, @Query("overrideBlocks") String overrideBlocks);


    Observable<TitleDetails> getTitleDetails(@HeaderMap Map<String, String> headers, @Query("contextName") String contextName, @Query("site") String site, @Query("client") String client,
                                             @Query("bibID") String bibid, @Query("appID") String appID, @Query("device") String device, @Query("appVersion") String appVersion, @Query("appLanguage") String appLanguage);


    @Headers({"Accept: application/json", "text/xml: gzip"})
    @GET("rest/v4/district/login")
    @JsonAndXmlConverters.Xml
    Observable<LoginResults> getLoginResults(@Query("contextName") String contextName, @Query("site") String site, @Query("userName") String userName, @Query
            ("password") String password);
    
    @Headers({"Cookie: jJg9v-BUa6Wp74RYvU9zJAKxLGR8LFeksVeuNv5R",
            "Accept: application/json",
            "text/xml: gzip"})
    @GET("rest/v4/circulation/patronstatus")
    Observable<PatronInfo> getPatronStatus(@Query("contextName") String contextName, @Query("site") String site, @Query("barcode") String barcode);
}
