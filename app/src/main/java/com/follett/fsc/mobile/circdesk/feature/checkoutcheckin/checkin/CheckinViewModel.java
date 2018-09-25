/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.checkoutcheckin.checkin;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;

import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.app.base.BaseViewModel;
import com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences;
import com.follett.fsc.mobile.circdesk.data.remote.api.NetworkInterface;
import com.follett.fsc.mobile.circdesk.data.remote.repository.AppRemoteRepository;
import com.follett.fsc.mobile.circdesk.feature.checkoutcheckin.UpdateUIListener;
import com.follett.fsc.mobile.circdesk.utils.FollettLog;

import java.util.HashMap;
import java.util.Map;

import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_CONTEXT_NAME;
import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_SITE_SHORT_NAME;

/**
 * Created by muthulakshmi on 04/09/18.
 */

public class CheckinViewModel extends BaseViewModel implements NetworkInterface {

    private Application mApplication;
    private UpdateUIListener updateUIListener;
    public MutableLiveData<CheckinResult> checkinResultMutableLiveData = new MutableLiveData<>();

    public CheckinViewModel(Application application, UpdateUIListener updateUIListener) {
        super(application);
        this.mApplication = application;
        this.updateUIListener = updateUIListener;
    }

    public void getCheckinData(String checkinBarcode, String collectionType, boolean isLibraryUse) {
        setIsLoding(true);

        Map<String, String> map = new HashMap<>();
        map.put("Accept", "application/json");
        map.put("Cookie", "JSESSIONID=" + AppSharedPreferences.getInstance().getString(AppSharedPreferences.KEY_SESSION_ID));
        map.put("text/xml", "gzip");

        AppRemoteRepository.getInstance().getCheckinResult(map, this,AppSharedPreferences.getInstance()
                        .getString(KEY_CONTEXT_NAME), AppSharedPreferences.getInstance()
                        .getString(KEY_SITE_SHORT_NAME),
                checkinBarcode, collectionType, isLibraryUse);

    }


    @Override
    public void onCallCompleted(Object model) {
        setIsLoding(false);

        try {
            if (model instanceof CheckinResult) {
                checkinResultMutableLiveData.postValue((CheckinResult) model);
                CheckinResult checkinResult = (CheckinResult) model;
                updateUIListener.updateUI(checkinResult);
            }
        } catch (Exception e) {
            FollettLog.e(mApplication.getResources().getString(R.string.error), e.getMessage());
        }

    }

    @Override
    public void onCallFailed(Throwable throwable) {
        setIsLoding(false);
        FollettLog.d("Exception", throwable.getMessage());
    }
}
