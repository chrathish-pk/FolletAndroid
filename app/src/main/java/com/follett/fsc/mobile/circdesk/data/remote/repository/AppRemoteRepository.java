/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.data.remote.repository;

import com.follett.fsc.mobile.circdesk.data.model.LoginResults;
import com.follett.fsc.mobile.circdesk.data.model.SiteResults;
import com.follett.fsc.mobile.circdesk.data.model.Version;
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
    public Observable<LoginResults> getLoginResults() {
        return apiService.getLoginResults("dvpdt_devprodtest", "FDPSA", "COGNITE", "pk", "pk", "DestinyCirc", "Android_24_7.0_lge_lucye_LG-H870DS",
                "1_Android", "English");
    }


//    @Override
//    public Observable<SiteResults> getSchoolList() {
//        return apiService.getSchoolList("dvpdt_devprodtest", "COGNITE", "library%2Ctextbook%2Casset", AppConstants.APP_ID,
//                "Android_24_7.0_lge_lucye_LG-H870DS", "1_Android", AppConstants.APP_LANGUAGE);
//    }
}
