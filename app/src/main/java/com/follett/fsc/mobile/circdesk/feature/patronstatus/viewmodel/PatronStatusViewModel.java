/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.patronstatus.viewmodel;

import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.app.base.ScannerViewModel;
import com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences;
import com.follett.fsc.mobile.circdesk.data.remote.api.NetworkInterface;
import com.follett.fsc.mobile.circdesk.data.remote.repository.AppRemoteRepository;
import com.follett.fsc.mobile.circdesk.feature.itemstatus.view.UpdateItemUIListener;
import com.follett.fsc.mobile.circdesk.feature.patronstatus.model.AssetCheckOut;
import com.follett.fsc.mobile.circdesk.feature.patronstatus.model.Checkout;
import com.follett.fsc.mobile.circdesk.feature.patronstatus.model.PatronInfo;
import com.follett.fsc.mobile.circdesk.utils.AppUtils;
import com.follett.fsc.mobile.circdesk.utils.FollettLog;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.databinding.BindingAdapter;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.widget.TextView;

import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_CONTEXT_NAME;
import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_SITE_SHORT_NAME;

public class PatronStatusViewModel extends ScannerViewModel implements NetworkInterface {
    
    public final MutableLiveData<PatronInfo> mPatronInfo = new MutableLiveData<>();
    
    private Application mApplication;
    
    private UpdateItemUIListener updateItemUIListener;
    
    private String mPatronId;

    public PatronStatusViewModel(@NonNull Application application, UpdateItemUIListener updateItemUIListener) {
        super(application);
        this.mApplication = application;
        this.updateItemUIListener=updateItemUIListener;
    }
    
    public void getPatronInfo(String patronId) {
        if (!TextUtils.isEmpty(patronId)) {
            setIsLoding(true);
            mPatronId = patronId;
    
            AppRemoteRepository.getInstance().getPatronStatus(this, AppUtils.getInstance().getHeader(mApplication), AppSharedPreferences.getInstance()
                    .getString(KEY_CONTEXT_NAME), AppSharedPreferences.getInstance()
                    .getString(KEY_SITE_SHORT_NAME), patronId);
        } else {
            setErrorMessage(getApplication().getString(R.string.errorPatronEntry));
        }
    }
    

    @Override
    public void onCallCompleted(Object model) {
        setIsLoding(false);
        try {
            if (model instanceof PatronInfo) {
                updateItemUIListener.updateUI(model);
                mPatronInfo.postValue((PatronInfo) model);

            }

        } catch (Exception e) {
            FollettLog.d("Exception", e.getMessage());
        }
    }
    
    @Override
    public void onCallFailed(Throwable throwable, String errorMessage) {
        setIsLoding(false);
        FollettLog.d("Exception", throwable.getMessage());
        setErrorMessage(errorMessage);
    }
    
    @Override
    public void onRefreshToken(int requestCode) {
        AppRemoteRepository.getInstance().getPatronStatus(this, AppUtils.getInstance().getHeader(mApplication), AppSharedPreferences.getInstance()
                .getString(KEY_CONTEXT_NAME), AppSharedPreferences.getInstance()
                .getString(KEY_SITE_SHORT_NAME), mPatronId);
    }
    
    @BindingAdapter(value = {"overDueCount"})
    public static void setOverdueCount(@NonNull TextView textView, PatronInfo patronInfo) {
        int overDueCount = 0;
        if (patronInfo != null) {
            for (AssetCheckOut assetCheckOut : patronInfo.getAssetCheckOuts()) {
                if (assetCheckOut.getOverDue()) {
                    overDueCount++;
                }
            }
            for (Checkout checkout : patronInfo.getCheckouts()) {
                if (checkout.getOverDue()) {
                    overDueCount++;
                }
            }
        }
        textView.setText(String.valueOf(overDueCount));
    }
}
