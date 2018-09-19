/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */
package com.follett.fsc.mobile.circdesk.feature.loginsetup;


import android.app.Application;
import android.support.annotation.NonNull;

import com.follett.fsc.mobile.circdesk.app.CTAButtonListener;
import com.follett.fsc.mobile.circdesk.app.base.BaseViewModel;
import com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences;
import com.follett.fsc.mobile.circdesk.data.remote.api.NetworkInterface;
import com.follett.fsc.mobile.circdesk.data.remote.apicommon.Status;
import com.follett.fsc.mobile.circdesk.data.remote.repository.AppRemoteRepository;
import com.follett.fsc.mobile.circdesk.utils.FollettLog;
import com.google.gson.Gson;

import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_PERMISSIONS;
import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_SESSION_ID;

public class LoginViewModel extends BaseViewModel<CTAButtonListener> implements NetworkInterface {

    private Application mApplication;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        mApplication = application;
    }


    public void getLoginResults(String contextName, String site, String userName, String password) {
        setIsLoding(true);
        AppRemoteRepository.getInstance().getLoginResults(this, contextName, site, userName, password);
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
                AppSharedPreferences.getInstance()
                        .setString(KEY_SESSION_ID, loginResults.getSessionID());

                String permissionJSON = new Gson().toJson((loginResults.getPermissions()));
                AppSharedPreferences.getInstance().setString(KEY_PERMISSIONS, permissionJSON);

                setStatus(Status.SUCCESS);
            } else if(loginResults.getInvalidUsernameOrPassword()!=null && loginResults.getInvalidUsernameOrPassword()
                    .equalsIgnoreCase("true"))
            {
                setStatus(Status.ERROR);
            }
            else
            {
                setStatus(Status.ERROR);
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
