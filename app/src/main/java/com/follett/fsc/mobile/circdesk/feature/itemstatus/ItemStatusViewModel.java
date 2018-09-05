package com.follett.fsc.mobile.circdesk.feature.itemstatus;

import android.app.Application;
import android.util.Log;

import com.follett.fsc.mobile.circdesk.app.base.BaseViewModel;
import com.follett.fsc.mobile.circdesk.data.remote.api.NetworkInterface;
import com.follett.fsc.mobile.circdesk.data.remote.repository.AppRemoteRepository;
import com.follett.fsc.mobile.circdesk.feature.checkoutcheckin.ScanPatron;
import com.follett.fsc.mobile.circdesk.feature.checkoutcheckin.UpdateUIListener;

public class ItemStatusViewModel extends BaseViewModel implements NetworkInterface {

    private Application mApplication;
    private UpdateItemUIListener updateItemUIListener;

    private AppRemoteRepository mAppRemoteRepository;

    public ItemStatusViewModel(Application application, AppRemoteRepository appRemoteRepository, UpdateItemUIListener updateItemUIListener) {
        super(application);
        this.mApplication = application;
        this.mAppRemoteRepository = appRemoteRepository;
        this.updateItemUIListener = updateItemUIListener;
    }


    public void getScanItem(String itemBarcodeID) {

        mAppRemoteRepository.getItemStatus(this,itemBarcodeID);
    }

    @Override
    public void onCallCompleted(Object model) {
        ItemDetails itemDetails = (ItemDetails) model;
        Log.i("TAG","Item Details"+model);
        updateItemUIListener.updateUI(itemDetails);
    }

    @Override
    public void onCallFailed(Throwable throwable) {

    }
}
