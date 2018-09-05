/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.patronstatus;

import com.follett.fsc.mobile.circdesk.app.CTAButtonListener;
import com.follett.fsc.mobile.circdesk.app.base.BaseViewModel;
import com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences;
import com.follett.fsc.mobile.circdesk.data.remote.api.NetworkInterface;
import com.follett.fsc.mobile.circdesk.data.remote.repository.AppRemoteRepository;
import com.follett.fsc.mobile.circdesk.feature.loginsetup.SiteResults;
import com.follett.fsc.mobile.circdesk.utils.FollettLog;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;

import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_CONTEXT_NAME;

public class PatronListViewModel extends BaseViewModel<CTAButtonListener>  {
    
    public final MutableLiveData<SiteResults> siteResult = new MutableLiveData<>();
    
    private AppRemoteRepository mAppRemoteRepository;
    
    private Application mApplication;
    
    public PatronListViewModel(Application application) {
        super(application);
        mApplication = application;
        mAppRemoteRepository = new AppRemoteRepository();
        fetchSchoolList();
    }
    
    public void fetchSchoolList() {
//        setIsLoding(true);
//        mAppRemoteRepository.getSchoolList(this, AppSharedPreferences.getInstance(mApplication)
//                .getString(KEY_CONTEXT_NAME));
    }
    
    private void dismissProgrssBar() {
        setIsLoding(false);
    }
    
   
}
