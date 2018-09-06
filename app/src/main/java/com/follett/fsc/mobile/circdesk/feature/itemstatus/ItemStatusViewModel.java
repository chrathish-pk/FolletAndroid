package com.follett.fsc.mobile.circdesk.feature.itemstatus;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;

import com.follett.fsc.mobile.circdesk.app.base.BaseViewModel;
import com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences;
import com.follett.fsc.mobile.circdesk.data.remote.api.NetworkInterface;
import com.follett.fsc.mobile.circdesk.data.remote.repository.AppRemoteRepository;
import com.follett.fsc.mobile.circdesk.utils.FollettLog;

import java.util.HashMap;
import java.util.Map;

public class ItemStatusViewModel extends BaseViewModel implements NetworkInterface {

    private Application mApplication;
    private UpdateItemUIListener updateItemUIListener;
    public final MutableLiveData<ItemDetails> itemDetailsInfo = new MutableLiveData<>();


    private AppRemoteRepository mAppRemoteRepository;

    public ItemStatusViewModel(Application application, AppRemoteRepository appRemoteRepository, UpdateItemUIListener updateItemUIListener) {
        super(application);
        this.mApplication = application;
        this.mAppRemoteRepository = appRemoteRepository;
        this.updateItemUIListener = updateItemUIListener;
    }


    public void getScanItem(String itemBarcodeID) {
        setIsLoding(true);
        Map<String, String> map = new HashMap<>();
        map.put("Accept", "application/json");
        map.put("Cookie", "JSESSIONID=" + AppSharedPreferences.getInstance(mApplication).getString(AppSharedPreferences.KEY_SESSION_ID));
        map.put("text/xml", "gzip");
        mAppRemoteRepository.getItemStatus(map,this, itemBarcodeID);
    }

    @Override
    public void onCallCompleted(Object model) {
       setIsLoding(false);
        try {
            if (model instanceof ItemDetails) {
                itemDetailsInfo.postValue((ItemDetails) model);
            }
        } catch (Exception e) {
            FollettLog.d("Exception", e.getMessage());
        }


    }

    @Override
    public void onCallFailed(Throwable throwable) {

    }
}
