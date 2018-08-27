/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.viewmodel;

import com.follett.fsc.mobile.circdesk.data.model.SiteRecord;
import com.follett.fsc.mobile.circdesk.data.model.SiteResults;
import com.follett.fsc.mobile.circdesk.data.remote.repository.AppRemoteRepository;
import com.follett.fsc.mobile.circdesk.interfaces.SchoolListNavigator;
import com.follett.fsc.mobile.circdesk.utils.AppUtils;
import com.follett.fsc.mobile.circdesk.view.base.BaseViewModel;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class SchoolListViewModel extends BaseViewModel<SchoolListNavigator> {
    
    public final ObservableList<SiteRecord> schoolArrayList = new ObservableArrayList<>();
    
    private final MutableLiveData<List<SiteRecord>> schoolMutableLiveData;
    
    
    private Application mApplication;
    
    private AppRemoteRepository mAppRemoteRepository;
    
    public SchoolListViewModel(Application application, AppRemoteRepository appRemoteRepository) {
        super(application);
        schoolMutableLiveData = new MutableLiveData<>();
        mApplication = application;
        mAppRemoteRepository = appRemoteRepository;
        fetSchoolList();
    }
    
    public void fetSchoolList() {
        mAppRemoteRepository.getSchoolList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<SiteResults>() {
                    @Override
                    public void onNext(SiteResults value) {
                        AppUtils.getInstance()
                                .showShortToastMessages(mApplication, value.toString());
                    }
                    
                    @Override
                    public void onError(Throwable e) {
                    
                    }
                    
                    @Override
                    public void onComplete() {
                    
                    }
                });


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
