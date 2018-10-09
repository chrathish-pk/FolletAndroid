/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.checkoutcheckin.checkout.viewmodel;

import com.follett.fsc.mobile.circdesk.app.base.BaseViewModel;
import com.follett.fsc.mobile.circdesk.app.base.ScannerViewModel;
import com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences;
import com.follett.fsc.mobile.circdesk.data.remote.api.NetworkInterface;
import com.follett.fsc.mobile.circdesk.data.remote.repository.AppRemoteRepository;
import com.follett.fsc.mobile.circdesk.feature.checkoutcheckin.UpdateUIListener;
import com.follett.fsc.mobile.circdesk.feature.checkoutcheckin.model.CheckoutResult;
import com.follett.fsc.mobile.circdesk.feature.checkoutcheckin.model.ScanPatron;
import com.follett.fsc.mobile.circdesk.utils.AppUtils;
import com.follett.fsc.mobile.circdesk.utils.FollettLog;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_CONTEXT_NAME;
import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_SITE_SHORT_NAME;
import static com.follett.fsc.mobile.circdesk.data.remote.apicommon.FollettApiConstants.CHECK_OUT_REQUEST_CODE;
import static com.follett.fsc.mobile.circdesk.data.remote.apicommon.FollettApiConstants.SCAN_PATRON_REQUEST_CODE;

public class CheckoutViewModel extends ScannerViewModel implements NetworkInterface {

    private UpdateUIListener updateUIListener;
    public final MutableLiveData<CheckoutResult> checkoutResultMutableLiveData = new MutableLiveData<>();
    public final MutableLiveData<ScanPatron> scanPatronMutableLiveData = new MutableLiveData<>();
    private Application mApplication;
    private String mPatronBarcodeID;
    private String mPatronID;
    private String mBarcode;
    private String mCollectionType;
    private boolean mOverrideBlocks;


    public CheckoutViewModel(@NonNull Application application, UpdateUIListener updateUIListener) {
        super(application);
        this.updateUIListener = updateUIListener;
        mApplication = application;
    }
    public void getScanPatron(String patronBarcodeID) {
        setIsLoding(true);
        
        mPatronBarcodeID = patronBarcodeID;

        AppRemoteRepository.getInstance().getScanPatron(AppUtils.getInstance().getHeader(mApplication), this,AppSharedPreferences.getInstance()
                .getString(KEY_CONTEXT_NAME), AppSharedPreferences.getInstance()
                .getString(KEY_SITE_SHORT_NAME), patronBarcodeID);
    }

    public void getCheckoutResult(String patronID, String barcode, String collectionType, boolean overrideBlocks ) {
        setIsLoding(true);
        mPatronID = patronID;
        mBarcode = barcode;
        mCollectionType = collectionType;
        mOverrideBlocks = overrideBlocks;
        AppRemoteRepository.getInstance().getCheckoutResult(AppUtils.getInstance().getHeader(mApplication), this,
                AppSharedPreferences.getInstance()
                .getString(KEY_CONTEXT_NAME), AppSharedPreferences.getInstance()
                .getString(KEY_SITE_SHORT_NAME), patronID, barcode, collectionType, overrideBlocks);
    }

    @Override
    public void onCallCompleted(Object model) {
        setIsLoding(false);
        try {
            if (model instanceof ScanPatron) {
                ScanPatron scanPatron = (ScanPatron) model;
                String selectedPatronID = AppSharedPreferences.getInstance()
                        .getString(AppSharedPreferences.KEY_SELECTED_BARCODE);
                if (!TextUtils.isEmpty(selectedPatronID)) {
                    AppSharedPreferences.getInstance()
                            .setString(AppSharedPreferences.KEY_PATRON_ID, scanPatron.getPatronID());
                }
                scanPatronMutableLiveData.postValue(scanPatron);
                updateUIListener.updateUI(scanPatron);
            } else if (model instanceof CheckoutResult) {
                checkoutResultMutableLiveData.postValue((CheckoutResult)model);
                CheckoutResult checkoutResult = (CheckoutResult) model;
                updateUIListener.updateUI(checkoutResult);
            }
        } catch (Exception e) {
            FollettLog.d("Exception", e.getMessage());
        }
    }

    @Override
    public void onCallFailed(Throwable throwable, String errorMessage) {
        setIsLoding(false);
        FollettLog.d("Exception", throwable.getMessage());
        setErrorMessage(errorMessage);
    }
    
    @Override
    public void onRefreshToken(int requestCode) {
        if (requestCode == SCAN_PATRON_REQUEST_CODE) {
            AppRemoteRepository.getInstance().getScanPatron(AppUtils.getInstance().getHeader(mApplication), this,AppSharedPreferences.getInstance()
                    .getString(KEY_CONTEXT_NAME), AppSharedPreferences.getInstance()
                    .getString(KEY_SITE_SHORT_NAME), mPatronBarcodeID);
        } else if (requestCode == CHECK_OUT_REQUEST_CODE) {
            AppRemoteRepository.getInstance().getCheckoutResult(AppUtils.getInstance().getHeader(mApplication), this, AppSharedPreferences.getInstance()
                    .getString(KEY_CONTEXT_NAME), AppSharedPreferences.getInstance()
                    .getString(KEY_SITE_SHORT_NAME), mPatronID, mBarcode, mCollectionType, mOverrideBlocks);
        }
    }
}
