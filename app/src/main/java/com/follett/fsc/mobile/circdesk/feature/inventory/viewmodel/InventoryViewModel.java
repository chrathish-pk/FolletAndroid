/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.inventory.viewmodel;

import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.app.CTAButtonListener;
import com.follett.fsc.mobile.circdesk.app.ItemClickListener;
import com.follett.fsc.mobile.circdesk.app.base.BaseViewModel;
import com.follett.fsc.mobile.circdesk.app.base.ScannerViewModel;
import com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences;
import com.follett.fsc.mobile.circdesk.data.remote.api.NetworkInterface;
import com.follett.fsc.mobile.circdesk.data.remote.repository.AppRemoteRepository;
import com.follett.fsc.mobile.circdesk.feature.checkoutcheckin.UpdateUIListener;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.InProgressInventoryResults;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.Info;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.InventoryDetails;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.InventoryScan;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.Location;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.LocationList;
import com.follett.fsc.mobile.circdesk.utils.AppUtils;
import com.follett.fsc.mobile.circdesk.utils.FollettLog;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.text.TextUtils;
import android.view.View;

import java.util.List;

import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_CONTEXT_NAME;
import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_IS_COPY_ID;
import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_IS_LIBRARY_SELECTED;
import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_SELECTED_INVENTORY_PARTIAL_ID;
import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_SITE_SHORT_NAME;
import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.SCANNING_LOCATION_ID;
import static com.follett.fsc.mobile.circdesk.data.remote.apicommon.FollettApiConstants.INPROGRESS_INVENTORY_REQUEST_CODE;
import static com.follett.fsc.mobile.circdesk.data.remote.apicommon.FollettApiConstants.INVENTORY_DETAILS_REQUEST_CODE;
import static com.follett.fsc.mobile.circdesk.data.remote.apicommon.FollettApiConstants.INVENTORY_SCAN_REQUEST_CODE;

public class InventoryViewModel extends ScannerViewModel implements NetworkInterface {

    private ItemClickListener itemClickListener;
    public MutableLiveData<InProgressInventoryResults> inventoryListMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<InventoryDetails> inventoryDetailsMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<InventoryScan> inventoryScanMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<String> barCodeNotFound = new MutableLiveData<>();
    private UpdateUIListener updateUIListener;
    private Application mApplication;
    private String mEnteredTextValue;

    public InventoryViewModel(Application application, ItemClickListener itemClickListener, UpdateUIListener updateUIListener) {
        super(application);
        this.itemClickListener = itemClickListener;
        this.updateUIListener = updateUIListener;
        mApplication = application;
    }

    public void onItemClick(View view) {
        itemClickListener.onItemClick(view, 0);
    }


    public void getInProgressInventoryResults() {
        setIsLoding(true);
        AppRemoteRepository.getInstance().getInProgressInventoryResults(AppUtils.getInstance().getHeader(mApplication), this, AppSharedPreferences.getInstance()
                .getString(AppSharedPreferences.KEY_SITE_SHORT_NAME), AppSharedPreferences.getInstance()
                .getString(AppSharedPreferences.KEY_CONTEXT_NAME), (AppRemoteRepository.getInstance().getBoolean(KEY_IS_LIBRARY_SELECTED)) ? 0 : 4);
    }

    public void getInventoryDetails() {
        setIsLoding(true);
        AppRemoteRepository.getInstance().getInventoryDetails(AppUtils.getInstance().getHeader(mApplication), this, AppSharedPreferences.getInstance()
                .getString(AppSharedPreferences.KEY_SITE_SHORT_NAME), AppSharedPreferences.getInstance()
                .getString(AppSharedPreferences.KEY_CONTEXT_NAME), AppSharedPreferences.getInstance()
                .getInt(KEY_SELECTED_INVENTORY_PARTIAL_ID));
    }
    
    public void getLocationList() {
        AppRemoteRepository.getInstance().getLocationList(this, AppUtils.getInstance().getHeader(mApplication),
                AppRemoteRepository.getInstance().getString(AppSharedPreferences.KEY_SITE_SHORT_NAME),
                AppSharedPreferences.getInstance().getString(AppSharedPreferences.KEY_CONTEXT_NAME));
    }

    @Override
    public void onCallCompleted(Object model) {
        setIsLoding(false);
        
        if (model == null) {
            return;
        }
    
        try {
            if (model instanceof InProgressInventoryResults) {
                inventoryListMutableLiveData.postValue((InProgressInventoryResults) model);
                InProgressInventoryResults inProgressInventoryResults = (InProgressInventoryResults) model;
                updateUIListener.updateUI(inProgressInventoryResults);
                AppSharedPreferences.getInstance().setInt(KEY_SELECTED_INVENTORY_PARTIAL_ID, inProgressInventoryResults.getInventoryList().get(0).getPartialID());

                getInventoryDetails();
            } else if (model instanceof InventoryDetails) {
                inventoryDetailsMutableLiveData.postValue((InventoryDetails) model);
                InventoryDetails inventoryDetails = (InventoryDetails) model;
                updateUIListener.updateUI(inventoryDetails);
            } else if (model instanceof Location) {
                final Location location = (Location) model;
                final List<LocationList> locationList = location.getLocationList();
                if (!locationList.isEmpty()) {
                    AppRemoteRepository.getInstance()
                            .setInt(SCANNING_LOCATION_ID, locationList.get(0)
                                    .getLocationID());
                }
            } else if (model instanceof InventoryScan) {
                final InventoryScan inventoryScan = ((InventoryScan) model);
                inventoryScanMutableLiveData.postValue(inventoryScan);
                final Info info = inventoryScan.getInfo();
                if (info != null) {
                    AppRemoteRepository.getInstance()
                            .setInt(KEY_IS_COPY_ID, info.getCopyID());
                } else {
                    final List<Object> messages = inventoryScan.getMessages();
                    if (!messages.isEmpty()) {
                        barCodeNotFound.postValue(messages.get(0)
                                .toString());
                    }
                }
            }
        } catch (Exception e) {
            FollettLog.d("Exception", e.getMessage());
        }
        setIsLoding(false);
    }

    public void onCallFailed(Throwable throwable, String errorMessage) {
        setIsLoding(false);
        setErrorMessage(errorMessage);
    }

    @Override
    public void onRefreshToken(int requestCode) {
    
        switch (requestCode) {
            case INPROGRESS_INVENTORY_REQUEST_CODE:
                getInProgressInventoryResults();
                break;
            case INVENTORY_DETAILS_REQUEST_CODE:
                getInventoryDetails();
                break;
            case INVENTORY_SCAN_REQUEST_CODE:
                getInventoryScanResults();
                break;
        }
    }
    
    public void inventoryScan(String enteredTextValue) {
        if (TextUtils.isEmpty(enteredTextValue)) {
            setErrorMessage(mApplication.getString(R.string.inventory_error));
            return;
        }
        setIsLoding(true);
        mEnteredTextValue = enteredTextValue;
        getInventoryScanResults();
    }
    
    private void getInventoryScanResults() {
        AppRemoteRepository.getInstance()
                .getInventoryScan(AppUtils.getInstance()
                        .getHeader(mApplication), this, AppRemoteRepository.getInstance()
                        .getString(KEY_CONTEXT_NAME), AppRemoteRepository.getInstance()
                        .getString(KEY_SITE_SHORT_NAME), AppRemoteRepository.getInstance()
                        .getBoolean(KEY_IS_LIBRARY_SELECTED) ? 0 : 4, AppRemoteRepository.getInstance()
                        .getInt(KEY_SELECTED_INVENTORY_PARTIAL_ID), mEnteredTextValue, AppRemoteRepository.getInstance()
                        .getBoolean(KEY_IS_LIBRARY_SELECTED) ? 0 : AppRemoteRepository.getInstance()
                        .getInt(SCANNING_LOCATION_ID), AppRemoteRepository.getInstance()
                        .getInt(KEY_IS_COPY_ID), true);
    }
}
