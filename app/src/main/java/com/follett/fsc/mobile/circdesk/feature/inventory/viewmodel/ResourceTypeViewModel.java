package com.follett.fsc.mobile.circdesk.feature.inventory.viewmodel;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.follett.fsc.mobile.circdesk.app.base.BaseViewModel;
import com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences;
import com.follett.fsc.mobile.circdesk.data.remote.api.NetworkInterface;
import com.follett.fsc.mobile.circdesk.data.remote.repository.AppRemoteRepository;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.ResourceType;
import com.follett.fsc.mobile.circdesk.utils.AppUtils;

public class ResourceTypeViewModel extends BaseViewModel implements NetworkInterface {

    private Application application;
    public MutableLiveData<ResourceType> resourceTypeMutableLiveData = new MutableLiveData<>();

    public ResourceTypeViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
    }

    public void fetchResourceTypeList() {
        setIsLoding(true);
        AppRemoteRepository.getInstance().getResourceTypeList(AppUtils.getInstance().getHeader(application), this,
                AppRemoteRepository.getInstance().getString(AppSharedPreferences.KEY_SITE_SHORT_NAME),
                AppSharedPreferences.getInstance().getString(AppSharedPreferences.KEY_CONTEXT_NAME));
    }

    @Override
    public void onCallCompleted(Object model) {
        setIsLoding(false);
        resourceTypeMutableLiveData.postValue((ResourceType) model);
    }

    @Override
    public void onCallFailed(Throwable throwable, String errorMessage) {
        setIsLoding(false);
        setErrorMessage(errorMessage);
    }

    @Override
    public void onRefreshToken(int requestCode) {

    }
}
