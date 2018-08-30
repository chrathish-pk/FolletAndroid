/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.viewmodel;

import com.follett.fsc.mobile.circdesk.data.model.SiteResults;
import com.follett.fsc.mobile.circdesk.data.remote.repository.AppRemoteRepository;
import com.follett.fsc.mobile.circdesk.interfaces.SchoolListNavigator;
import com.follett.fsc.mobile.circdesk.view.base.BaseViewModel;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class SchoolListViewModel extends BaseViewModel<SchoolListNavigator> {
    
    public final MutableLiveData<SiteResults> siteResult = new MutableLiveData<>();
    
    private AppRemoteRepository mAppRemoteRepository;
    
    public SchoolListViewModel(Application application, AppRemoteRepository appRemoteRepository) {
        super(application);
        mAppRemoteRepository = appRemoteRepository;
        fetchSchoolList();
    }
    
    public void fetchSchoolList() {
        setIsLoding(true);
        mAppRemoteRepository.getSchoolList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<SiteResults>() {
                    @Override
                    public void onNext(SiteResults siteResults) {
                        dismissProgrssBar();
                        siteResult.postValue(siteResults);
                    }
                    
                    @Override
                    public void onError(Throwable e) {
                        dismissProgrssBar();
                    }
                    
                    @Override
                    public void onComplete() {
                    }
                });
    }
    
    private void dismissProgrssBar() {
        setIsLoding(false);
    }
}
