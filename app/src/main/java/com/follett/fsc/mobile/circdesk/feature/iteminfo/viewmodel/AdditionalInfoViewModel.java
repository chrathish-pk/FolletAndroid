/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.iteminfo.viewmodel;

import com.follett.fsc.mobile.circdesk.app.base.BaseViewModel;
import com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences;
import com.follett.fsc.mobile.circdesk.data.remote.api.NetworkInterface;
import com.follett.fsc.mobile.circdesk.data.remote.repository.AppRemoteRepository;
import com.follett.fsc.mobile.circdesk.feature.iteminfo.model.TitleDetails;
import com.follett.fsc.mobile.circdesk.feature.iteminfo.view.AdditionalInfoListener;
import com.follett.fsc.mobile.circdesk.utils.FollettLog;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_CONTEXT_NAME;
import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_SITE_SHORT_NAME;

public class AdditionalInfoViewModel extends BaseViewModel implements NetworkInterface {

    AdditionalInfoListener additionalInfoListener;
    public final MutableLiveData<TitleDetails> mTitleDetails = new MutableLiveData<>();
    private Application mApplication;

    public AdditionalInfoViewModel(@NonNull Application application, AdditionalInfoListener additionalInfoListener) {
        super(application);
        this.additionalInfoListener = additionalInfoListener;
        mApplication = application;
    }

    public void getTitleDetails(String bibID) {
        Map<String, String> map = new HashMap<>();
        map.put("Accept", "application/json");
        map.put("Cookie", "JSESSIONID=" + AppSharedPreferences.getInstance().getString(AppSharedPreferences.KEY_SESSION_ID));
        map.put("text/xml", "gzip");
        AppRemoteRepository.getInstance().getTitleDetails(map, this,AppSharedPreferences.getInstance()
                .getString(KEY_CONTEXT_NAME), AppSharedPreferences.getInstance()
                .getString(KEY_SITE_SHORT_NAME), bibID);
    }

    @Override
    public void onCallCompleted(Object model) {
        try {
            if (model instanceof TitleDetails) {
                mTitleDetails.postValue((TitleDetails) model);
            }
        } catch (Exception e) {
            FollettLog.d("Exception", e.getMessage());
        }
    }

    @Override
    public void onCallFailed(Throwable throwable, String errorMessage) {
        FollettLog.d("Exception", throwable.getMessage());
        setErrorMessage(errorMessage);
    }
}