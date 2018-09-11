/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.checkoutcheckin.checkout;

import android.app.Application;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.follett.fsc.mobile.circdesk.app.base.BaseViewModel;
import com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences;
import com.follett.fsc.mobile.circdesk.data.remote.api.NetworkInterface;
import com.follett.fsc.mobile.circdesk.data.remote.repository.AppRemoteRepository;
import com.follett.fsc.mobile.circdesk.feature.checkoutcheckin.UpdateUIListener;
import com.follett.fsc.mobile.circdesk.utils.AppUtils;
import com.follett.fsc.mobile.circdesk.utils.FollettLog;

import java.util.HashMap;
import java.util.Map;

public class CheckoutViewModel extends BaseViewModel implements NetworkInterface {

    private Application mApplication;
    private UpdateUIListener updateUIListener;
    private AppRemoteRepository mAppRemoteRepository;

    public CheckoutViewModel(@NonNull Application application, UpdateUIListener updateUIListener) {
        super(application);
        this.mApplication = application;
        this.updateUIListener = updateUIListener;
        this.mAppRemoteRepository = new AppRemoteRepository(AppSharedPreferences.getInstance(application));
    }

    public void getScanPatron(String patronBarcodeID) {
        //setIsLoding(true);
        AppUtils.getInstance().showProgressDialog(mApplication, null, null, false);
        Map<String, String> map = new HashMap<>();
        map.put("Accept", "application/json");
        map.put("Cookie", "JSESSIONID=" + AppSharedPreferences.getInstance(mApplication).getString(AppSharedPreferences.KEY_SESSION_ID));
        map.put("text/xml", "gzip");

        mAppRemoteRepository.getScanPatron(map, this, patronBarcodeID);
    }

    public void getCheckoutResult(String patronID, String barcode, String collectionType) {
        setIsLoding(true);
        AppUtils.getInstance().showProgressDialog(mApplication, null, null, false);

        Map<String, String> map = new HashMap<>();
        map.put("Accept", "application/json");
        map.put("Cookie", "JSESSIONID=" + AppSharedPreferences.getInstance(mApplication).getString(AppSharedPreferences.KEY_SESSION_ID));
        map.put("text/xml", "gzip");
        mAppRemoteRepository.getCheckoutResult(map, this, patronID, barcode, collectionType);
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
