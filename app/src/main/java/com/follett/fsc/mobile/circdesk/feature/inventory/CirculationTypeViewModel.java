/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.inventory;

import android.app.Application;

import com.follett.fsc.mobile.circdesk.app.base.BaseViewModel;
import com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences;
import com.follett.fsc.mobile.circdesk.data.remote.api.NetworkInterface;
import com.follett.fsc.mobile.circdesk.data.remote.repository.AppRemoteRepository;

public class CirculationTypeViewModel extends BaseViewModel implements NetworkInterface {

    private AppRemoteRepository mAppRemoteRepository;
    private Application mApplication;

    public CirculationTypeViewModel(Application application) {
        super(application);
        mApplication = application;
        mAppRemoteRepository = new AppRemoteRepository();
        fetchCirculationTypeList();
    }

    public void fetchCirculationTypeList() {
        setIsLoding(true);
        mAppRemoteRepository.getCirculationTypeList(this);
    }

    @Override
    public void onCallCompleted(Object model) {

    }

    @Override
    public void onCallFailed(Throwable throwable) {

    }
}
