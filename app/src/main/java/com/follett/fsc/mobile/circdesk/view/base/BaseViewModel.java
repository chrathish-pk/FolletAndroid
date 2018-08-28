/*
 * Copyright (c) 2018 Follett. All rights reserved.
 */
package com.follett.fsc.mobile.circdesk.view.base;

import com.follett.fsc.mobile.circdesk.data.remote.apicommon.Status;
import com.follett.fsc.mobile.circdesk.interfaces.SingleLiveEvent;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableBoolean;

import java.lang.ref.WeakReference;

public abstract class BaseViewModel<N> extends ViewModel {
    
    private final ObservableBoolean mIsLoading = new ObservableBoolean(false);
    
    private final SingleLiveEvent<Status> mStatus = new SingleLiveEvent<>();
    
    public BaseViewModel(Application application) {
        setIsLoding(false);
    }
    
    private WeakReference<N> mNavigator;
    
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
    
    public LiveData<Status> getStatus() {
        return mStatus;
    }
    
    public void setStatus(Status status) {
        mStatus.setValue(status);
    }
    
    
}
