/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.patronstatus;

import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.app.base.BaseViewModel;
import com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences;
import com.follett.fsc.mobile.circdesk.data.remote.api.NetworkInterface;
import com.follett.fsc.mobile.circdesk.data.remote.repository.AppRemoteRepository;
import com.follett.fsc.mobile.circdesk.feature.patronstatus.model.AssetCheckOut;
import com.follett.fsc.mobile.circdesk.feature.patronstatus.model.Checkout;
import com.follett.fsc.mobile.circdesk.feature.patronstatus.model.PatronInfo;
import com.follett.fsc.mobile.circdesk.utils.FollettLog;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.databinding.BindingAdapter;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_CONTEXT_NAME;
import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_SITE_SHORT_NAME;

public class PatronStatusViewModel extends BaseViewModel implements NetworkInterface {
    
    public final MutableLiveData<PatronInfo> mPatronInfo = new MutableLiveData<>();
    
    private Application mApplication;
    
    private AppRemoteRepository mAppRemoteRepository;
    
    public PatronStatusViewModel(@NonNull Application application) {
        super(application);
        this.mApplication = application;
        mAppRemoteRepository = new AppRemoteRepository(AppSharedPreferences.getInstance(getApplication()));
    }
    
    public void getPatronInfo(String typedText) {
        if (!TextUtils.isEmpty(typedText)) {
            setIsLoding(true);
    
            Map<String, String> headerMap = new HashMap<>();
            headerMap.put("Accept", "application/json");
            headerMap.put("Cookie", "JSESSIONID=" + AppSharedPreferences.getInstance(mApplication)
                    .getString(AppSharedPreferences.KEY_SESSION_ID));
            headerMap.put("text/xml", "gzip");
            
            mAppRemoteRepository.getPatronStatus(this, headerMap, AppSharedPreferences.getInstance(getApplication())
                    .getString(KEY_CONTEXT_NAME), AppSharedPreferences.getInstance(getApplication())
                    .getString(KEY_SITE_SHORT_NAME), typedText);
        } else {
            setErrorMessage(getApplication().getString(R.string.errorPatronEntry));
        }
    }
    
    
    @Override
    public void onCallCompleted(Object model) {
        setIsLoding(false);
        try {
            if (model instanceof PatronInfo) {
                mPatronInfo.postValue((PatronInfo) model);
            }
        } catch (Exception e) {
            FollettLog.d("Exception", e.getMessage());
        }
    }
    
    @Override
    public void onCallFailed(Throwable throwable) {
        setIsLoding(false);
        FollettLog.d("Exception", throwable.getMessage());
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
