package com.follett.fsc.mobile.circdesk.feature.itemstatus.viewmodel;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;

import com.follett.fsc.mobile.circdesk.app.base.BaseViewModel;
import com.follett.fsc.mobile.circdesk.app.base.ScannerViewModel;
import com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences;
import com.follett.fsc.mobile.circdesk.data.remote.api.NetworkInterface;
import com.follett.fsc.mobile.circdesk.data.remote.repository.AppRemoteRepository;
import com.follett.fsc.mobile.circdesk.feature.itemstatus.model.ItemDetails;
import com.follett.fsc.mobile.circdesk.utils.AppUtils;
import com.follett.fsc.mobile.circdesk.utils.FollettLog;

import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_CONTEXT_NAME;
import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_SITE_SHORT_NAME;

public class ItemStatusViewModel extends ScannerViewModel implements NetworkInterface {

    public final MutableLiveData<ItemDetails> itemDetailsInfo = new MutableLiveData<>();
    
    private Application mApplication;
    private String mItemBarcodeID;
    private String mCollectionType;


    public ItemStatusViewModel(Application application) {
        super(application);
        mApplication = application;
    }


    public void getScanItem(String itemBarcodeID,String collectionType) {
    
        mItemBarcodeID = itemBarcodeID;
        mCollectionType = collectionType;
        setIsLoding(true);
        AppRemoteRepository.getInstance().getItemStatus(AppUtils.getInstance().getHeader(mApplication),this,AppSharedPreferences.getInstance()
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
    public void onCallFailed(Throwable throwable, String errorMessage) {
        setIsLoding(false);
        setErrorMessage(errorMessage);
    }
    
    @Override
    public void onRefreshToken(int requestCode) {
        AppRemoteRepository.getInstance().getItemStatus(AppUtils.getInstance().getHeader(mApplication), this, AppSharedPreferences.getInstance()
                .getString(KEY_CONTEXT_NAME), AppSharedPreferences.getInstance()
                .getString(KEY_SITE_SHORT_NAME), mItemBarcodeID, mCollectionType);
    }
}
