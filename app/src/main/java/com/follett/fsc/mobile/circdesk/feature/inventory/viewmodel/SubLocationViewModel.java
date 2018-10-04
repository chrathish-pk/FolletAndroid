package com.follett.fsc.mobile.circdesk.feature.inventory.viewmodel;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.follett.fsc.mobile.circdesk.app.base.BaseViewModel;
import com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences;
import com.follett.fsc.mobile.circdesk.data.remote.api.NetworkInterface;
import com.follett.fsc.mobile.circdesk.data.remote.repository.AppRemoteRepository;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.SubLocation;
import com.follett.fsc.mobile.circdesk.utils.AppUtils;

public class SubLocationViewModel extends BaseViewModel implements NetworkInterface {
    private Application mApplication;
    public MutableLiveData<SubLocation> subLocationListMutableLiveData = new MutableLiveData<>();


    public SubLocationViewModel(@NonNull Application application) {
        super(application);
        mApplication = application;
    }

    public void fetchSubLocationList() {
        setIsLoding(true);
        AppRemoteRepository.getInstance().getSubLocationList(this, AppUtils.getInstance().getHeader(mApplication),
                AppRemoteRepository.getInstance().getString(AppSharedPreferences.KEY_SITE_SHORT_NAME),
                AppSharedPreferences.getInstance().getString(AppSharedPreferences.KEY_CONTEXT_NAME));
    }



    @Override
    public void onCallCompleted(Object model) {

        setIsLoding(false);
        subLocationListMutableLiveData.postValue((SubLocation) model);
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
