/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.viewmodel;

import com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences;
import com.follett.fsc.mobile.circdesk.data.model.SiteResults;
import com.follett.fsc.mobile.circdesk.data.remote.api.NetworkInterface;
import com.follett.fsc.mobile.circdesk.data.remote.repository.AppRemoteRepository;
import com.follett.fsc.mobile.circdesk.interfaces.SchoolListNavigator;
import com.follett.fsc.mobile.circdesk.utils.FollettLog;
import com.follett.fsc.mobile.circdesk.view.base.BaseViewModel;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;

import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_CONTEXT_NAME;

public class SchoolListViewModel extends BaseViewModel<SchoolListNavigator> implements NetworkInterface {
    
    public final MutableLiveData<SiteResults> siteResult = new MutableLiveData<>();
    
    private AppRemoteRepository mAppRemoteRepository;
    
    private Application mApplication;
    
    public SchoolListViewModel(Application application, AppRemoteRepository appRemoteRepository) {
        super(application);
        mApplication = application;
        mAppRemoteRepository = appRemoteRepository;
        fetchSchoolList();
    }
    
    public void fetchSchoolList() {
        setIsLoding(true);
        mAppRemoteRepository.getSchoolList(this, AppSharedPreferences.getInstance(mApplication)
                .getString(KEY_CONTEXT_NAME));
    }
    
    private void dismissProgrssBar() {
        setIsLoding(false);
    }
    
    @Override
    public void onCallCompleted(Object model) {
        dismissProgrssBar();
        try {
            siteResult.postValue((SiteResults) model);
        } catch (Exception e) {
            FollettLog.d("Exception", e.getMessage());
        }
    }
    
    @Override
    public void onCallFailed(Throwable throwable) {
        dismissProgrssBar();
    }
}
