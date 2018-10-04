package com.follett.fsc.mobile.circdesk.feature.inventory.viewmodel;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.follett.fsc.mobile.circdesk.app.base.BaseViewModel;
import com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences;
import com.follett.fsc.mobile.circdesk.data.remote.api.NetworkInterface;
import com.follett.fsc.mobile.circdesk.data.remote.repository.AppRemoteRepository;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.Location;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.SubLocation;
import com.follett.fsc.mobile.circdesk.utils.AppUtils;

public class InventoryLocationViewModel extends BaseViewModel implements NetworkInterface {

    Application mApplication;
    public MutableLiveData<Location> locationListMutableLiveData = new MutableLiveData<>();

    public InventoryLocationViewModel(@NonNull Application application) {
        super(application);
    }

    public void fetchLocationList() {

        setIsLoding(true);
        AppRemoteRepository.getInstance().getLocationList(this, AppUtils.getInstance().getHeader(mApplication),
                AppRemoteRepository.getInstance().getString(AppSharedPreferences.KEY_SITE_SHORT_NAME),
                AppSharedPreferences.getInstance().getString(AppSharedPreferences.KEY_CONTEXT_NAME));

    }

    @Override
    public void onCallCompleted(Object model) {
        setIsLoding(false);
        locationListMutableLiveData.postValue((Location) model);
    }

    @Override
    public void onCallFailed(Throwable throwable, String errorMessage) {
        setIsLoding(false);
        setErrorMessage(errorMessage);
    }

    @Override
    public void onRefreshToken(int requestCode) {
        //do nothing
    }
}
