/*
 * Copyright (c) 2018 Follett. All rights reserved.
 */
package com.follett.fsc.mobile.circdesk.viewmodel;


import com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences;
import com.follett.fsc.mobile.circdesk.data.model.LoginResults;
import com.follett.fsc.mobile.circdesk.data.remote.apicommon.Status;
import com.follett.fsc.mobile.circdesk.data.remote.repository.AppRemoteRepository;
import com.follett.fsc.mobile.circdesk.interfaces.CTAButtonListener;
import com.follett.fsc.mobile.circdesk.view.base.BaseViewModel;

import android.app.Application;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_SESSION_ID;

public class LoginViewModel extends BaseViewModel<CTAButtonListener> {
    
    private Application mApplication;
    
    private AppRemoteRepository mAppRemoteRepository;
    
    public LoginViewModel(Application application, AppRemoteRepository appRemoteRepository) {
        super(application);
        mApplication = application;
        mAppRemoteRepository = appRemoteRepository;
    }

    
    public void getLoginResults(String userName, String password) {
        setIsLoding(true);
        mAppRemoteRepository.getLoginResults(userName, password)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new Observer<LoginResults>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }
                    
                    @Override
                    public void onNext(LoginResults loginResults) { // KEY_SESSION_ID
                        cancelProgressBar();
                        if (loginResults.getSuccess() != null && loginResults
                                .getSuccess().equalsIgnoreCase("true")) {
                            AppSharedPreferences.getInstance(mApplication).setString(KEY_SESSION_ID,
                                    loginResults.getSessionID());
                            setStatus(Status.SUCCESS);
                        }
                    }
                    
                    @Override
                    public void onError(Throwable e) {
                        cancelProgressBar();
                    }
                    
                    @Override
                    public void onComplete() {
                        cancelProgressBar();
                    }
                });
    }
    
    private void cancelProgressBar() {
        setIsLoding(false);
    }
}
