/*
 * Copyright (c) 2018 Follett. All rights reserved.
 */
package com.follett.fsc.mobile.circdesk.app.base;

import com.follett.fsc.mobile.circdesk.data.remote.apicommon.Status;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.databinding.ObservableBoolean;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;

import java.lang.ref.WeakReference;

public abstract class BaseViewModel<N> extends AndroidViewModel {

    private Application mApplication;
    
    private final ObservableBoolean mIsLoading = new ObservableBoolean(false);
    
    private MutableLiveData<String> mErrorMessage = new MutableLiveData<>();

    private final MutableLiveData<Status> mStatus = new MutableLiveData<>();

    private WeakReference<N> mNavigator;
    
    public BaseViewModel(@NonNull Application application) {
        super(application);
        mApplication = application;
    }
    
    public N getNavigator() {
        return mNavigator.get();
    }
    
    public void setNavigator(N navigator) {
        this.mNavigator = new WeakReference<>(navigator);
    }
    
    public ObservableBoolean getIsLoading() {
        return mIsLoading;
    }
    
    public void setIsLoding(boolean isLoading) {
        mIsLoading.set(isLoading);
    }
    
    public MutableLiveData<Status> getStatus() {
        return mStatus;
    }

    public void setStatus(Status status) {
        mStatus.postValue(status);
    }
    
    public MutableLiveData<String> getErrorMessage() {
        return mErrorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        mErrorMessage.postValue(errorMessage);
    }

    protected boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) mApplication.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        }
        return false;
    }
}
