/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.inventory.viewmodel;

import com.follett.fsc.mobile.circdesk.app.base.BaseViewModel;
import com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences;
import com.follett.fsc.mobile.circdesk.data.remote.api.NetworkInterface;
import com.follett.fsc.mobile.circdesk.data.remote.repository.AppRemoteRepository;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.CirculationTypeList;
import com.follett.fsc.mobile.circdesk.utils.AppUtils;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;


public class CirculationTypeViewModel extends BaseViewModel implements NetworkInterface {

    private Application mApplication;
    public MutableLiveData<CirculationTypeList> circulationTypeListMutableLiveData = new MutableLiveData<>();

    public CirculationTypeViewModel(Application application) {
        super(application);
        mApplication = application;
    }

    public void fetchCirculationTypeList() {
        setIsLoding(true);
        AppRemoteRepository.getInstance().getCirculationTypeList(this, AppUtils.getInstance().getHeader(mApplication),
                AppRemoteRepository.getInstance().getString(AppSharedPreferences.KEY_SITE_SHORT_NAME),
                AppSharedPreferences.getInstance().getString(AppSharedPreferences.KEY_CONTEXT_NAME));
    }

    @Override
    public void onCallCompleted(Object model) {
        setIsLoding(false);
        circulationTypeListMutableLiveData.postValue((CirculationTypeList) model);
    }

    public void onCallFailed(Throwable throwable, String errorMessage) {
        setIsLoding(false);
        setErrorMessage(errorMessage);
        }
    
    @Override
    public void onRefreshToken(int requestCode) {
    
    }
}
