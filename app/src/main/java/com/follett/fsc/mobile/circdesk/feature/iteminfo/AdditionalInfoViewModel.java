/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.iteminfo;

import com.follett.fsc.mobile.circdesk.feature.iteminfo.model.TitleDetails;
import com.follett.fsc.mobile.circdesk.data.remote.api.NetworkInterface;
import com.follett.fsc.mobile.circdesk.data.remote.repository.AppRemoteRepository;
import com.follett.fsc.mobile.circdesk.utils.FollettLog;
import com.follett.fsc.mobile.circdesk.app.base.BaseViewModel;

import android.app.Application;

public class AdditionalInfoViewModel extends BaseViewModel implements NetworkInterface {

    private AppRemoteRepository mAppRemoteRepository;
    private AdditionalInfoListener additionalInfoListener;

    public AdditionalInfoViewModel(Application application, AppRemoteRepository appRemoteRepository, AdditionalInfoListener additionalInfoListener) {
        super(application);
        mAppRemoteRepository = appRemoteRepository;
        this.additionalInfoListener = additionalInfoListener;
    }

    public void getTitleDetails(String bibID) {
        mAppRemoteRepository.getTitleDetails(this,bibID);
    }

    @Override
    public void onCallCompleted(Object model) {
        try {
            TitleDetails titleDetails = (TitleDetails) model;
            additionalInfoListener.updateTitleDetails(titleDetails);
        } catch (Exception e) {
            FollettLog.d("Exception", e.getMessage());
        }
    }

    @Override
    public void onCallFailed(Throwable throwable) {
        FollettLog.d("Exception", throwable.getMessage());
    }
}
