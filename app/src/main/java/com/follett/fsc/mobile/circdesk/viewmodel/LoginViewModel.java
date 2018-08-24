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
    
    public void getLoginResults() {
        
        mAppRemoteRepository.getLoginResults()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new Observer<LoginResults>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    
                    }
                    
                    @Override
                    public void onNext(LoginResults value) {
                        getNavigator().navigationToNextFragment();
                    }
                    
                    @Override
                    public void onError(Throwable e) {
                    
                    }
                    
                    @Override
                    public void onComplete() {
                    
                    }
                });


//        mAppRemoteRepository.getVersion()
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io())
//                .subscribeWith(new DisposableObserver<Version>() {
//                    @Override
//                    public void onNext(Version value) {
//                        if (Integer.parseInt(value.getVersion()) < AppConstants.MIN_API_VERSION_SUPPORTED) {
//                            getNavigator().displayErrorDialog(AppConstants.SCHOOL_NOT_SETUP_ERROR);
//                        } else {
//                            getNavigator().navigationToNextFragment(0);
//                        }
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//                    }
//                });
    }
}
