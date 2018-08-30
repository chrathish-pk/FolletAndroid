/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.data.remote.api;


import com.follett.fsc.mobile.circdesk.data.model.AdditionalInfo.TitleDetails;
import com.follett.fsc.mobile.circdesk.data.model.LoginResults;
import com.follett.fsc.mobile.circdesk.data.model.ScanPatron;
import com.follett.fsc.mobile.circdesk.data.model.SiteResults;
import com.follett.fsc.mobile.circdesk.data.model.Version;
import com.follett.fsc.mobile.circdesk.data.model.checkout.CheckoutResult;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface APIInterface {

    @Headers({
            "Accept: application/json",
            "text/xml: gzip"
    })
    @GET("/rest/version")
    @JsonAndXmlConverters.Xml
    Observable<Version> getVersion(@Query("contextName") String contextName, @Query("client") String client, @Query("appID") String appId, @Query("device")
            String device, @Query("appVersion") String appVersion, @Query("appLanguage") String appLanguage);

    @Headers({
            "Accept: application/json",
            "text/xml: gzip"
    })
    @GET("/rest/v4/district/sites")
    @JsonAndXmlConverters.Xml
    Observable<SiteResults> getSchoolList(@Query("contextName") String contextName, @Query("client") String client, @Query("productTypes") String productTypes,
                                    @Query("appID") String appID, @Query("device") String device, @Query("appVersion") String appVersion, @Query("appLanguage") String appLanguage);

//    @Headers({
//            "Accept: application/json",
//            "text/xml: gzip"
//    })
//    @GET("rest/v4/district/login")
//    @JsonAndXmlConverters.Xml
//    Observable<LoginResults> getLoginResultsDummy(@Query("contextName") String contextName, @Query("site") String site, @Query("client") String client, @Query
//            ("password") String password, @Query("userName") String userName, @Query("appID") String appID, @Query("device") String device, @Query
//                                                     ("appVersion") String appVersion, @Query("appLanguage") String appLanguage);

    @Headers({
            "Cookie: JSESSIONID=9A659y8TEgwy7Djw-2ad7oV1re7cTGSwhWyFeASg",
            "Accept: application/json",
            "text/xml: gzip"
    })
    @GET("rest/v4/circulation/scanpatron")
    @JsonAndXmlConverters.Xml
    Observable<ScanPatron> getScanPatron(@Query("contextName") String contextName, @Query("site") String site, @Query("client") String client,
                                         @Query("barcode") String barcode, @Query("appID") String appID, @Query("device") String device,
                                         @Query("appVersion") String appVersion, @Query("appLanguage") String appLanguage);

    @Headers({
            "Cookie: JSESSIONID=9A659y8TEgwy7Djw-2ad7oV1re7cTGSwhWyFeASg",
            "Accept: application/json",
            "text/xml: gzip"
    })
    @GET("rest/v4/circulation/checkout")
    Observable<CheckoutResult> getCheckoutResult(@Query("contextName") String contextName, @Query("site") String site,
                                                 @Query("barcode") String barcode, @Query("patronID") String patronID,
                                                 @Query("collectionType") String collectionType, @Query("overrideBlocks") String overrideBlocks);


    @GET("rest/v4/catalog/titledetail")
    @Headers({
            "Cookie: JSESSIONID=9A659y8TEgwy7Djw-2ad7oV1re7cTGSwhWyFeASg",
            "Accept: application/json",
            "text/xml: gzip"
    })
    Observable<TitleDetails> getTitleDetails(@Query("contextName") String contextName, @Query("site") String site, @Query("client") String client,
                                             @Query("bibID") String bibid, @Query("appID") String appID, @Query("device") String device, @Query("appVersion") String appVersion, @Query("appLanguage") String appLanguage);
    
    
    @Headers({"Accept: application/json", "text/xml: gzip"})
    @GET("rest/v4/district/login")
    @JsonAndXmlConverters.Xml
    Observable<LoginResults> getLoginResults(@Query("contextName") String contextName, @Query("site") String site, @Query("userName") String userName, @Query
            ("password") String password);
}
