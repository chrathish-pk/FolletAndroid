/*
 * Copyright (c) 2018 Follett. All rights reserved.
 */
package com.follett.fsc.mobile.circdesk.viewmodel;


import com.follett.fsc.mobile.circdesk.data.model.LoginResults;
import com.follett.fsc.mobile.circdesk.data.remote.repository.AppRemoteRepository;
import com.follett.fsc.mobile.circdesk.interfaces.LoginNavigator;
import com.follett.fsc.mobile.circdesk.view.base.BaseViewModel;

import android.app.Application;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginViewModel extends BaseViewModel<LoginNavigator> {

    private Application mApplication;

    private AppRemoteRepository mAppRemoteRepository;

    public LoginViewModel(Application application, AppRemoteRepository appRemoteRepository) {
        super(application);
        mApplication = application;
        mAppRemoteRepository = appRemoteRepository;
    }

    public void connectToServerOnClick() {
        getNavigator().loginOnClick();
    }

    public void getLoginResults(String userName, String password) {
        setIsLoding(true);
        mAppRemoteRepository.getLoginResults(userName, password)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new Observer<LoginResults>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        cancelProgressBar();
                    }

                    @Override
                    public void onNext(LoginResults value) {
                        cancelProgressBar();
                        getNavigator().navigationToNextFragment();
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
    
    private void cancelProgressBar () {
        setIsLoding(false);
    }
}
