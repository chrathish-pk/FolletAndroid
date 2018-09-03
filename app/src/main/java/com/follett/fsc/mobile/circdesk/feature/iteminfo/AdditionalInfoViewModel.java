/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.iteminfo;

import android.app.Application;

import com.follett.fsc.mobile.circdesk.app.base.BaseViewModel;
import com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences;
import com.follett.fsc.mobile.circdesk.data.remote.api.NetworkInterface;
import com.follett.fsc.mobile.circdesk.data.remote.repository.AppRemoteRepository;
import com.follett.fsc.mobile.circdesk.feature.iteminfo.model.TitleDetails;
import com.follett.fsc.mobile.circdesk.utils.FollettLog;

import java.util.HashMap;
import java.util.Map;

public class AdditionalInfoViewModel extends BaseViewModel implements NetworkInterface {

    private AppRemoteRepository mAppRemoteRepository;
    private AdditionalInfoListener additionalInfoListener;
    private Application mApplication;

    public AdditionalInfoViewModel(Application application, AppRemoteRepository appRemoteRepository, AdditionalInfoListener additionalInfoListener) {
        super(application);
        mAppRemoteRepository = appRemoteRepository;
        this.additionalInfoListener = additionalInfoListener;
        this.mApplication = application;
    }

    public void getTitleDetails(String bibID) {
        Map<String, String> map = new HashMap<>();
        map.put("Accept", "application/json");
        map.put("Cookie", "JSESSIONID=" + AppSharedPreferences.getInstance(mApplication).getString(AppSharedPreferences.KEY_SESSION_ID));
        map.put("text/xml", "gzip");
        mAppRemoteRepository.getTitleDetails(map, this, bibID);
    }

    @Override
    public void onCallCompleted(Object model) {
        try {
            TitleDetails titleDetails = (TitleDetails) model;
            additionalInfoListener.updateTitleDetails(titleDetails);
        } catch (Exception e) {
            FollettLog.d("Exception", e.getMessage());
        }
    }

    @Override
    public void onCallFailed(Throwable throwable) {
        FollettLog.d("Exception", throwable.getMessage());
    }
}
