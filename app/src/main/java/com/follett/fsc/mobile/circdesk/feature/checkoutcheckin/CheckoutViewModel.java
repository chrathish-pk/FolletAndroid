/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.checkoutcheckin;

import android.app.Application;
import android.text.TextUtils;

import com.follett.fsc.mobile.circdesk.app.base.BaseViewModel;
import com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences;
import com.follett.fsc.mobile.circdesk.data.remote.api.NetworkInterface;
import com.follett.fsc.mobile.circdesk.data.remote.repository.AppRemoteRepository;
import com.follett.fsc.mobile.circdesk.utils.FollettLog;

import java.util.HashMap;
import java.util.Map;

import android.support.annotation.NonNull;

public class CheckoutViewModel extends BaseViewModel implements NetworkInterface {

    private Application mApplication;
    private UpdateUIListener updateUIListener;

    private AppRemoteRepository mAppRemoteRepository;

    public CheckoutViewModel(@NonNull Application application, AppRemoteRepository appRemoteRepository, UpdateUIListener updateUIListener) {
        super(application);
        this.mApplication = application;
        this.mAppRemoteRepository = appRemoteRepository;
        this.updateUIListener = updateUIListener;
    }

    public void getScanPatron(String patronBarcodeID) {
        setIsLoding(true);

        Map<String, String> map = new HashMap<>();
        map.put("Accept", "application/json");
        map.put("Cookie", "JSESSIONID=" + AppSharedPreferences.getInstance(mApplication).getString(AppSharedPreferences.KEY_SESSION_ID));
        map.put("text/xml", "gzip");

        mAppRemoteRepository.getScanPatron(map, this, patronBarcodeID);
    }

    public void getCheckoutResult(String patronID, String barcode) {
        setIsLoding(true);

        Map<String, String> map = new HashMap<>();
        map.put("Accept", "application/json");
        map.put("Cookie", "JSESSIONID=" + AppSharedPreferences.getInstance(mApplication).getString(AppSharedPreferences.KEY_SESSION_ID));
        map.put("text/xml", "gzip");
        mAppRemoteRepository.getCheckoutResult(map, this, patronID, barcode);
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
                            .setString(AppSharedPreferences.KEY_PATRON_ID, scanPatron.getPatronID());
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