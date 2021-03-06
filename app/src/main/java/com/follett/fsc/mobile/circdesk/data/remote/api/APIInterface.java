/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.data.remote.api;


import com.follett.fsc.mobile.circdesk.feature.checkoutcheckin.model.CheckinResult;
import com.follett.fsc.mobile.circdesk.feature.checkoutcheckin.model.CheckoutResult;
import com.follett.fsc.mobile.circdesk.feature.checkoutcheckin.model.ScanPatron;
import com.follett.fsc.mobile.circdesk.feature.inventory.InventorySelectionCriteria;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.CirculationTypeList;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.CreateInventoryLibRequest;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.CreateInventoryResRequest;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.CreateInventoryResult;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.InProgressInventoryResults;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.InventoryDetails;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.InventoryScan;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.Location;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.SubLocation;
import com.follett.fsc.mobile.circdesk.feature.iteminfo.model.TitleDetails;
import com.follett.fsc.mobile.circdesk.feature.itemstatus.model.ItemDetails;
import com.follett.fsc.mobile.circdesk.feature.loginsetup.model.DistrictList;
import com.follett.fsc.mobile.circdesk.feature.loginsetup.model.LoginResults;
import com.follett.fsc.mobile.circdesk.feature.loginsetup.model.SiteResults;
import com.follett.fsc.mobile.circdesk.feature.loginsetup.model.Version;
import com.follett.fsc.mobile.circdesk.feature.patronstatus.model.PatronInfo;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIInterface {

    @Headers({"Accept: application/json", "text/xml: gzip"})
    @GET("/rest/version")
    @JsonAndXmlConverters.Xml
    Observable<Version> getVersion();


    @Headers({"Accept: application/json", "text/xml: gzip"})
    @GET("/rest/v4/district/list")
    @JsonAndXmlConverters.Xml
    Observable<DistrictList> getDistrictList();

    /* need to modify according to the api */
    @GET("/rest/v6/circulation/circtypelist")
    @JsonAndXmlConverters.Xml
    Observable<CirculationTypeList> getCirculationTypeList(@HeaderMap Map<String, String> headers, @Query("site") String site, @Query("contextName") String contextName);

    @GET("/rest/v6/circulation/sublocationlist")
    @JsonAndXmlConverters.Xml
    Observable<SubLocation> getSubLocationList(@HeaderMap Map<String, String> headers, @Query("site") String site, @Query("contextName") String contextName);

    @Headers({"Accept: application/json", "text/xml: gzip"})
    @GET("/rest/v6/circulation/locationlist")
    @JsonAndXmlConverters.Xml
    Observable<Location> getLocationList(@HeaderMap Map<String, String> headers, @Query("site") String site, @Query("contextName") String contextName);

    @Headers({"Accept: application/json", "text/xml: gzip"})
    @GET("/rest/v4/district/sites")
    @JsonAndXmlConverters.Xml
    Observable<SiteResults> getSchoolList(@Query("contextName") String contextName);

    @GET("rest/v4/circulation/copystatus")
    @JsonAndXmlConverters.Xml
    Observable<ItemDetails> getScanItem(@HeaderMap Map<String, String> headers, @Query("contextName") String contextName, @Query("site") String site, @Query("barcode") String barcode, @Query
            ("collectionType") String collectionType);


    @GET("rest/v4/circulation/scanpatron")
    Observable<ScanPatron> getScanPatron(@HeaderMap Map<String, String> headers, @Query("contextName") String contextName, @Query("site") String site, @Query("barcode") String barcode);

    @GET("rest/v4/circulation/checkout")
    Observable<CheckoutResult> getCheckoutResult(@HeaderMap Map<String, String> headers, @Query("contextName") String contextName, @Query("site") String
            site, @Query("barcode") String barcode, @Query("patronID") String patronID, @Query("collectionType") String collectionType, @Query
            ("overrideBlocks") String overrideBlocks);

    @GET("rest/v4/catalog/titledetail")
    Observable<TitleDetails> getTitleDetails(@HeaderMap Map<String, String> headers, @Query("contextName") String contextName, @Query("site") String site,
            @Query("bibID") String bibid);

    @Headers({"Accept: application/json", "text/xml: gzip"})
    @GET("rest/v4/district/login")
    @JsonAndXmlConverters.Xml
    Observable<LoginResults> getLoginResults(@Query("contextName") String contextName, @Query("site") String site, @Query("userName") String userName, @Query
            ("password") String password);

    @GET("rest/v6/circulation/inprogressinventories")
    Observable<InProgressInventoryResults> getInProgressInventoryResults(@HeaderMap Map<String, String> headers,
             @Query("site") String site, @Query("contextName") String contextName, @Query("collectionType") int collectionType);

    @GET("rest/v6/circulation/inventoryselectioncriteria")
    Observable<InventorySelectionCriteria> getSelectedInventoriesList(@HeaderMap Map<String, String> headers,
            @Query("site") String site, @Query("contextName") String contextName, @Query("partialID") int partialID );

    @GET("rest/v4/circulation/checkin")
    Observable<CheckinResult> getCheckinResult(@HeaderMap Map<String, String> headers, @Query("contextName") String contextName, @Query("site") String site,
            @Query("barcode") String barcode, @Query("collectionType") String collectionType, @Query("inLibraryUse") String inLibraryUse);


    @GET("rest/v4/circulation/patronstatus")
    Observable<PatronInfo> getPatronStatus(@HeaderMap Map<String, String> headers, @Query("contextName") String contextName, @Query("site") String site,
            @Query("barcode") String barcode);

    @GET("rest/v6/circulation/inventorydetails")
    Observable<InventoryDetails> getInventoryDetails(@HeaderMap Map<String, String> headers, @Query("site") String site, @Query("contextName") String contextName,
                                                     @Query("partialID") int partialID);

    @POST("rest/v6/circulation/createinventory")
    Observable<CreateInventoryResult> createLibInventory(@HeaderMap Map<String, String> headers, @Query("contextName") String contextName, @Query("site") String site, @Body CreateInventoryLibRequest createInventoryLibRequest);

    @POST("rest/v6/circulation/createinventory")
    Observable<CreateInventoryResult> createResInventory(@HeaderMap Map<String, String> headers, @Query("contextName") String contextName, @Query("site") String site, @Body CreateInventoryResRequest createInventoryResRequest);

    @GET("rest/v6/circulation/inventoryscan")
    Observable<InventoryScan> getInventoryScan(@HeaderMap Map<String, String> headers, @Query("contextName") String contextName, @Query("site") String site,
            @Query("collectionType") int collectionType, @Query("partialID") int partialID, @Query("barcode") String barcode, @Query("scanningLocationID")
            int scanningLocationID, @Query("lastCopyID") int lastCopyID, @Query("checkShelfOrder") boolean checkShelfOrder);

    /*@GET("/rest/v6/circulation/resourcetypelist")
    @JsonAndXmlConverters.Xml
    Observable<ResourceType> getResourceTypeList(@HeaderMap Map<String, String> headers, @Query("site") String site, @Query("contextName") String contextName);
*/
}
