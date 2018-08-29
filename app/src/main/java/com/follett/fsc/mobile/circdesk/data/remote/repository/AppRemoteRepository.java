/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.data.remote.repository;

import com.follett.fsc.mobile.circdesk.data.model.LoginResults;
import com.follett.fsc.mobile.circdesk.data.model.ScanPatron;
import com.follett.fsc.mobile.circdesk.data.model.SiteResults;
import com.follett.fsc.mobile.circdesk.data.model.Version;
import com.follett.fsc.mobile.circdesk.data.model.checkout.CheckoutResult;
import com.follett.fsc.mobile.circdesk.data.remote.api.APIClient;
import com.follett.fsc.mobile.circdesk.data.remote.api.APIInterface;

import io.reactivex.Observable;

public class AppRemoteRepository implements AppRepository {

    private APIInterface apiService;

    public static final String BASE_URL = "https://devprodtest.follettdestiny.com";

    public AppRemoteRepository() {
        apiService = APIClient.getClient(BASE_URL)
                .create(APIInterface.class);
    }

    @Override
    public Observable<Version> getVersion() {
        return apiService.getVersion("dvpdt_devprodtest", "COGNITE", "CircDeskMobile", "Android_24_7.0_lge_lucye_LG-H870DS", "1_Android", "English");
    }

    @Override
    public Observable<SiteResults> getSchoolList() {
        return null;
    }

    @Override
    public Observable<LoginResults> getLoginResults(String userName, String password) {
        return apiService.getLoginResults("dvpdt_devprodtest", "FDPSA", "COGNITE", password, userName, "DestinyCirc", "Android_24_7.0_lge_lucye_LG-H870DS",
                "1_Android", "English");
    }


    @Override
    public Observable<ScanPatron> getScanPatron(String patronBarcodeID) {
        return apiService.getScanPatron("dvpdt_devprodtest", "FDPSA", "COGNITE",
                patronBarcodeID, "DestinyCirc", "Android_24_7.0_lge_lucye_LG-H870DS",
                "1_Android", "English");
    }

    @Override
    public Observable<CheckoutResult> getCheckoutResult(String patronID, String barcode) {
        return apiService.getCheckoutResult("dvpdt_devprodtest", "FDPSA",
                barcode, patronID, "0", "false");
    }


}
