/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.inventory.viewmodel;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.view.View;

import com.follett.fsc.mobile.circdesk.app.CTAButtonListener;
import com.follett.fsc.mobile.circdesk.app.ItemClickListener;
import com.follett.fsc.mobile.circdesk.app.base.BaseViewModel;
import com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences;
import com.follett.fsc.mobile.circdesk.data.remote.api.NetworkInterface;
import com.follett.fsc.mobile.circdesk.data.remote.repository.AppRemoteRepository;
import com.follett.fsc.mobile.circdesk.feature.checkoutcheckin.UpdateUIListener;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.InProgressInventoryResults;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.InventoryDetails;
import com.follett.fsc.mobile.circdesk.utils.AppUtils;
import com.follett.fsc.mobile.circdesk.utils.FollettLog;

import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_IS_LIBRARY_SELECTED;
import static com.follett.fsc.mobile.circdesk.data.remote.apicommon.FollettApiConstants.INPROGRESS_INVENTORY_REQUEST_CODE;
import static com.follett.fsc.mobile.circdesk.data.remote.apicommon.FollettApiConstants.INVENTORY_DETAILS_REQUEST_CODE;

public class InventoryViewModel extends BaseViewModel<CTAButtonListener> implements NetworkInterface {

    private ItemClickListener itemClickListener;
    public MutableLiveData<InProgressInventoryResults> inventoryListMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<InventoryDetails> inventoryDetailsMutableLiveData = new MutableLiveData<>();
    private UpdateUIListener updateUIListener;
    private Application mApplication;

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
                .getInt(AppSharedPreferences.KEY_SELECTED_INVENTORY_PARTIAL_ID));
    }


    @Override
    public void onCallCompleted(Object model) {
        try {
            if (model instanceof InProgressInventoryResults) {
                inventoryListMutableLiveData.postValue((InProgressInventoryResults) model);
                InProgressInventoryResults inProgressInventoryResults = (InProgressInventoryResults) model;
                updateUIListener.updateUI(inProgressInventoryResults);
                AppSharedPreferences.getInstance().setInt(AppSharedPreferences.KEY_SELECTED_INVENTORY_PARTIAL_ID, inProgressInventoryResults.getInventoryList().get(0).getPartialID());

                getInventoryDetails();
            }
            if (model instanceof InventoryDetails) {
                inventoryDetailsMutableLiveData.postValue((InventoryDetails) model);
                InventoryDetails inventoryDetails = (InventoryDetails) model;
                updateUIListener.updateUI(inventoryDetails);
            }
        } catch (Exception e) {
            FollettLog.d("Exception", e.getMessage());
        }
        setIsLoding(false);
    }

    public void onCallFailed(Throwable throwable, String errorMessage) {
        setErrorMessage(errorMessage);
        setIsLoding(false);
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
        }
    }
}
