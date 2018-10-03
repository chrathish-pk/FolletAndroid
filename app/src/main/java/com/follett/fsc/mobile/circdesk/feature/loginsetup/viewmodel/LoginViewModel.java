/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */
package com.follett.fsc.mobile.circdesk.feature.loginsetup.viewmodel;


import android.app.Application;
import android.support.annotation.NonNull;

import com.follett.fsc.mobile.circdesk.app.CTAButtonListener;
import com.follett.fsc.mobile.circdesk.app.base.BaseViewModel;
import com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences;
import com.follett.fsc.mobile.circdesk.data.remote.api.NetworkInterface;
import com.follett.fsc.mobile.circdesk.data.remote.apicommon.Status;
import com.follett.fsc.mobile.circdesk.data.remote.repository.AppRemoteRepository;
import com.follett.fsc.mobile.circdesk.feature.loginsetup.model.LoginResults;
import com.follett.fsc.mobile.circdesk.utils.FollettLog;

import static com.follett.fsc.mobile.circdesk.data.remote.apicommon.FollettApiConstants.LOGIN_REQUEST_CODE;

public class LoginViewModel extends BaseViewModel<CTAButtonListener> implements NetworkInterface {
    
    public LoginViewModel(@NonNull Application application) {
        super(application);
    }


    public void getLoginResults(String contextName, String site, String userName, String password) {
        setIsLoding(true);
        AppRemoteRepository.getInstance().getLoginResults(false,this, contextName, site, userName, password, LOGIN_REQUEST_CODE);
    }

    private void cancelProgressBar() {
        setIsLoding(false);
    }

    @Override
    public void onCallCompleted(Object model) {
        cancelProgressBar();
        try {
            FollettLog.d("Login context value",AppSharedPreferences.getInstance().getString(AppSharedPreferences.KEY_CONTEXT_NAME));
            LoginResults loginResults = (LoginResults) model;
            if (loginResults.getSuccess() != null && loginResults.getSuccess()
                    .equalsIgnoreCase("true")) {
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
    public void onCallFailed(Throwable throwable, String errorMessage) {
        cancelProgressBar();
        setErrorMessage(errorMessage);
    }
    
    @Override
    public void onRefreshToken(int requestCode) {
        // Do Nothing
    }
}
