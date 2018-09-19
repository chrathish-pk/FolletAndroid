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
    public final MutableLiveData<ItemDetails> itemDetailsInfo = new MutableLiveData<>();



    public ItemStatusViewModel(Application application) {
        super(application);
        this.mApplication = application;
    }


    public void getScanItem(String itemBarcodeID,String collectionType) {
        setIsLoding(true);
        Map<String, String> map = new HashMap<>();
        map.put("Accept", "application/json");
        map.put("Cookie", "JSESSIONID=" + AppSharedPreferences.getInstance().getString(AppSharedPreferences.KEY_SESSION_ID));
        map.put("text/xml", "gzip");
        AppRemoteRepository.getInstance().getItemStatus(map,this,AppSharedPreferences.getInstance()
                .getString(KEY_CONTEXT_NAME), AppSharedPreferences.getInstance()
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
        setIsLoding(false);
    }
}
