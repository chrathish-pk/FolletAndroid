/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.checkoutcheckin;

import com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences;
import com.follett.fsc.mobile.circdesk.data.remote.api.NetworkInterface;
import com.follett.fsc.mobile.circdesk.data.remote.repository.AppRemoteRepository;
import com.follett.fsc.mobile.circdesk.utils.FollettLog;
import com.follett.fsc.mobile.circdesk.app.base.BaseViewModel;

import android.app.Application;
import android.support.annotation.NonNull;
import android.text.TextUtils;

public class CheckoutViewModel extends BaseViewModel implements NetworkInterface {

    //this is the data that we will fetch asynchronously
//    public ScanPatron scanPatron;
//    public CheckoutResult checkoutResult;
    private Application mApplication;
    private UpdateUIListener updateUIListener;

    private AppRemoteRepository mAppRemoteRepository;

    public CheckoutViewModel(@NonNull Application application, AppRemoteRepository appRemoteRepository, UpdateUIListener updateUIListener) {
        super(application);
        this.mApplication = application;
        this.mAppRemoteRepository = appRemoteRepository;
        this.updateUIListener = updateUIListener;
    }

    //we will call this method to get the data
   /* public LiveData<ScanPatron> getScanPatronLiveData() {
        //if the list is null
        if (scanPatronMutableLiveData == null) {
            scanPatronMutableLiveData = new MutableLiveData<ScanPatron>();
            //we will load it asynchronously from server in this method
            getScanPatron();
        }

        //finally we will return the list
        return scanPatronMutableLiveData;
    }*/


    public void getScanPatron(String patronBarcodeID) {
        setIsLoding(true);
        mAppRemoteRepository.getScanPatron(this, patronBarcodeID);
    }

    public void getCheckoutResult(String patronID, String barcode) {
        setIsLoding(true);
        mAppRemoteRepository.getCheckoutResult(this, patronID, barcode);
    }

    @Override
    public void onCallCompleted(Object model) {
        setIsLoding(false);
        try {
            if (model instanceof ScanPatron) {
                ScanPatron scanPatron = (ScanPatron) model;
                String selectedPatronID = AppSharedPreferences.getInstance(mApplication)
                        .getString(AppSharedPreferences.KEY_SELECTED_BARCODE);
                if (!TextUtils.isEmpty(selectedPatronID)) {
                    AppSharedPreferences.getInstance(mApplication)
                            .setString(AppSharedPreferences.KEY_BARCODE, selectedPatronID);
                    AppSharedPreferences.getInstance(mApplication)
                            .setString(AppSharedPreferences.KEY_PATRON_ID, scanPatron.getPatronID());
                    AppSharedPreferences.getInstance(mApplication)
                            .setString(AppSharedPreferences.KEY_SELECTED_BARCODE, null);
                }
                updateUIListener.updateUI(scanPatron);
            } else if (model instanceof CheckoutResult) {
                CheckoutResult checkoutResult = (CheckoutResult) model;
                updateUIListener.updateUI(checkoutResult);
            }
        } catch (Exception e) {
            FollettLog.d("Exception", e.getMessage());
        }
    }

    @Override
    public void onCallFailed(Throwable throwable) {
        setIsLoding(false);
        FollettLog.d("Exception", throwable.getMessage());
    }

}
