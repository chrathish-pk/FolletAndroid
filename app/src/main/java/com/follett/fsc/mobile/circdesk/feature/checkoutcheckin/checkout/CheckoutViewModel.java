/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.checkoutcheckin.checkout;

import com.follett.fsc.mobile.circdesk.app.base.BaseViewModel;
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

import java.util.HashMap;
import java.util.Map;

import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_CONTEXT_NAME;
import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_SITE_SHORT_NAME;

public class CheckoutViewModel extends BaseViewModel implements NetworkInterface {

    private Application mApplication;
    private UpdateUIListener updateUIListener;
    public final MutableLiveData<CheckoutResult> checkoutResultMutableLiveData = new MutableLiveData<>();
    public final MutableLiveData<ScanPatron> scanPatronMutableLiveData = new MutableLiveData<>();


    public CheckoutViewModel(@NonNull Application application, UpdateUIListener updateUIListener) {
        super(application);
        this.mApplication = application;
        this.updateUIListener = updateUIListener;
    }

    public void getScanPatron(String patronBarcodeID) {
        setIsLoding(true);
        AppUtils.getInstance().showProgressDialog(mApplication, null, null, false);
        Map<String, String> map = new HashMap<>();
        map.put("Accept", "application/json");
        map.put("Cookie", "JSESSIONID=" + AppSharedPreferences.getInstance().getString(AppSharedPreferences.KEY_SESSION_ID));
        map.put("text/xml", "gzip");

        AppRemoteRepository.getInstance().getScanPatron(map, this,AppSharedPreferences.getInstance()
                .getString(KEY_CONTEXT_NAME), AppSharedPreferences.getInstance()
                .getString(KEY_SITE_SHORT_NAME), patronBarcodeID);
    }

    public void getCheckoutResult(String patronID, String barcode, String collectionType, boolean overrideBlocks ) {
        setIsLoding(true);
        AppUtils.getInstance().showProgressDialog(mApplication, null, null, false);

        Map<String, String> map = new HashMap<>();
        map.put("Accept", "application/json");
        map.put("Cookie", "JSESSIONID=" + AppSharedPreferences.getInstance().getString(AppSharedPreferences.KEY_SESSION_ID));
        map.put("text/xml", "gzip");
        AppRemoteRepository.getInstance().getCheckoutResult(map, this,AppSharedPreferences.getInstance()
                .getString(KEY_CONTEXT_NAME), AppSharedPreferences.getInstance()
                .getString(KEY_SITE_SHORT_NAME), patronID, barcode, collectionType,overrideBlocks);
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
    public void onCallFailed(Throwable throwable) {
        setIsLoding(false);
        FollettLog.d("Exception", throwable.getMessage());
    }


}
