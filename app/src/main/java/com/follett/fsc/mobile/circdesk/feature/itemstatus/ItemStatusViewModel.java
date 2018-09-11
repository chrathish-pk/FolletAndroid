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

import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_CONTEXT_NAME;
import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_SITE_SHORT_NAME;

public class ItemStatusViewModel extends BaseViewModel implements NetworkInterface {

    private Application mApplication;
    UpdateItemUIListener updateItemUIListener;
    public final MutableLiveData<ItemDetails> itemDetailsInfo = new MutableLiveData<>();


    private AppRemoteRepository mAppRemoteRepository;

    public ItemStatusViewModel(Application application, UpdateItemUIListener updateItemUIListener) {
        super(application);
        this.mApplication = application;
        this.updateItemUIListener = updateItemUIListener;
        this.mAppRemoteRepository = new AppRemoteRepository(AppSharedPreferences.getInstance(application));
    }


    public void getScanItem(String itemBarcodeID,String collectionType) {
        setIsLoding(true);
        Map<String, String> map = new HashMap<>();
        map.put("Accept", "application/json");
        map.put("Cookie", "JSESSIONID=" + AppSharedPreferences.getInstance(mApplication).getString(AppSharedPreferences.KEY_SESSION_ID));
        map.put("text/xml", "gzip");
        mAppRemoteRepository.getItemStatus(map,this,AppSharedPreferences.getInstance(getApplication())
                .getString(KEY_CONTEXT_NAME), AppSharedPreferences.getInstance(getApplication())
                .getString(KEY_SITE_SHORT_NAME), itemBarcodeID,collectionType);
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
        //onCallFailed
    }
}
