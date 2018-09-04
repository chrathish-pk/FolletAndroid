/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */
package com.follett.fsc.mobile.circdesk.feature.loginsetup;


import com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences;
import com.follett.fsc.mobile.circdesk.data.remote.api.NetworkInterface;
import com.follett.fsc.mobile.circdesk.data.remote.apicommon.Status;
import com.follett.fsc.mobile.circdesk.data.remote.repository.AppRemoteRepository;
import com.follett.fsc.mobile.circdesk.app.CTAButtonListener;
import com.follett.fsc.mobile.circdesk.utils.FollettLog;
import com.follett.fsc.mobile.circdesk.app.base.BaseViewModel;

import android.app.Application;
import android.support.annotation.NonNull;

import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_SESSION_ID;

public class LoginViewModel extends BaseViewModel<CTAButtonListener> implements NetworkInterface {
    
    private Application mApplication;
    
    private AppRemoteRepository mAppRemoteRepository;
    
    public LoginViewModel(@NonNull Application application, AppRemoteRepository appRemoteRepository) {
        super(application);
        mApplication = application;
        mAppRemoteRepository = appRemoteRepository;
    }
    
    
    public void getLoginResults(String contextName, String site, String userName, String password) {
        setIsLoding(true);
        mAppRemoteRepository.getLoginResults(this, contextName, site, userName, password);
    }
    
    private void cancelProgressBar() {
        setIsLoding(false);
    }
    
    @Override
    public void onCallCompleted(Object model) {
        cancelProgressBar();
        try {
            LoginResults loginResults = (LoginResults) model;
            if (loginResults.getSuccess() != null && loginResults.getSuccess()
                    .equalsIgnoreCase("true")) {
                AppSharedPreferences.getInstance(mApplication)
                        .setString(KEY_SESSION_ID, loginResults.getSessionID());
                setStatus(Status.SUCCESS);
            }
        } catch (Exception e) {
            FollettLog.d("Exception", e.getMessage());
        }
    }
    
    @Override
    public void onCallFailed(Throwable throwable) {
        cancelProgressBar();
    }
}
