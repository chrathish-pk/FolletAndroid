/*
 * Copyright (c) 2018 Follett. All rights reserved.
 */
package com.follett.fsc.mobile.circdesk.app.base;

import android.app.Application;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.honeywell.aidc.AidcManager;
import com.honeywell.aidc.BarcodeReader;


public class ScannerBaseActivity<V extends ScannerViewModel> extends BaseActivity {

    private static BarcodeReader mBarcodeReader;

    private AidcManager mManager;

    private ScannerViewModel mViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Application application = getApplication();
        if (application == null) {
            return;
        }
        inItializeScanner();
        mViewModel = new ScannerViewModel(application);
        mBarcodeReader = mViewModel.setPropertyForBarcodeReader(mBarcodeReader);
    }

    private void inItializeScanner() {
        AidcManager.create(this, new AidcManager.CreatedCallback() {

            @Override
            public void onCreated(AidcManager aidcManager) {
                mManager = aidcManager;
                mBarcodeReader = mManager.createBarcodeReader();
            }
        });
    }

    public BarcodeReader getBarcodeReader() {
        return mBarcodeReader;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mViewModel.onDestoryScanner(mBarcodeReader, mManager);
    }
}
