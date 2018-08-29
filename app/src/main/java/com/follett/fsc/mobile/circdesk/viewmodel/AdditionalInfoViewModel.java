/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.viewmodel;

import android.app.Application;

import com.follett.fsc.mobile.circdesk.data.model.AdditionalInfo.TitleDetails;
import com.follett.fsc.mobile.circdesk.data.remote.repository.AppRemoteRepository;
import com.follett.fsc.mobile.circdesk.interfaces.AdditionalInfoListener;
import com.follett.fsc.mobile.circdesk.view.base.BaseViewModel;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class AdditionalInfoViewModel extends BaseViewModel {

    Application mApplication;
    private AppRemoteRepository mAppRemoteRepository;
    TitleDetails titleDetails;
    AdditionalInfoListener additionalInfoListener;

    public AdditionalInfoViewModel(Application application,AppRemoteRepository appRemoteRepository,AdditionalInfoListener additionalInfoListener){
        super(application);
        mApplication = application;
        mAppRemoteRepository = appRemoteRepository;
        this.additionalInfoListener = additionalInfoListener;
    }

    public TitleDetails getTitleDetails() {

        mAppRemoteRepository.getTitleDetails().subscribeWith(new Observer<TitleDetails>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(TitleDetails value) {
                try {

                    if (value != null) {
                        titleDetails = value;
                        additionalInfoListener.updateTitleDetails(titleDetails);
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(Throwable e) {
            e.printStackTrace();
            }

            @Override
            public void onComplete() {

            }
        });
        return null;
    }
}
