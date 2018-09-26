/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.inventory.viewmodel;

import com.follett.fsc.mobile.circdesk.app.CTAButtonListener;
import com.follett.fsc.mobile.circdesk.app.ItemClickListener;
import com.follett.fsc.mobile.circdesk.app.base.BaseViewModel;
import com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences;
import com.follett.fsc.mobile.circdesk.data.remote.api.NetworkInterface;
import com.follett.fsc.mobile.circdesk.data.remote.repository.AppRemoteRepository;
import com.follett.fsc.mobile.circdesk.feature.checkoutcheckin.UpdateUIListener;
import com.follett.fsc.mobile.circdesk.feature.inventory.InventoryDetails;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.InProgressInventoryResults;
import com.follett.fsc.mobile.circdesk.utils.FollettLog;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.view.View;

public class InventoryViewModel extends BaseViewModel<CTAButtonListener> implements NetworkInterface {

    private ItemClickListener itemClickListener;
    public MutableLiveData<InProgressInventoryResults> inventoryListMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<InventoryDetails> inventoryDetailsMutableLiveData = new MutableLiveData<>();
    private AppRemoteRepository mAppRemoteRepository;
    private UpdateUIListener updateUIListener;
    private Application mApplication;

    public InventoryViewModel(Application application, ItemClickListener itemClickListener, UpdateUIListener updateUIListener) {
        super(application);
        this.itemClickListener = itemClickListener;
        this.updateUIListener = updateUIListener;
        mAppRemoteRepository = new AppRemoteRepository();
        mApplication = application;
    }

    public void OnItemClick(View view) {
        itemClickListener.onItemClick(view, 0);
    }

    @Override
    public void onCallCompleted(Object model) {
        try {
            if (model instanceof InProgressInventoryResults) {
                inventoryListMutableLiveData.postValue((InProgressInventoryResults) model);
                InProgressInventoryResults inProgressInventoryResults = (InProgressInventoryResults) model;
                updateUIListener.updateUI(inProgressInventoryResults);
                AppSharedPreferences.getInstance().setInt(AppSharedPreferences.KEY_PARTIALID, inProgressInventoryResults.getInventoryList().get(0).getPartialID());

                getInventoryDetails(AppSharedPreferences.getInstance()
                        .getString(AppSharedPreferences.KEY_SITE_SHORT_NAME), AppSharedPreferences.getInstance()
                        .getString(AppSharedPreferences.KEY_CONTEXT_NAME), AppSharedPreferences.getInstance()
                        .getInt(AppSharedPreferences.KEY_PARTIALID));
            }if (model instanceof InventoryDetails) {
                inventoryDetailsMutableLiveData.postValue((InventoryDetails) model);
                InventoryDetails inventoryDetails = (InventoryDetails) model;
                updateUIListener.updateUI(inventoryDetails);            }
        } catch (Exception e) {
            FollettLog.d("Exception", e.getMessage());
        }
        setIsLoding(false);
    }

    public void getInProgressInventoryResults(String site, String contextName, int collectionType) {
        setIsLoding(true);
        Map<String, String> map = new HashMap<>();
        map.put("Accept", "application/json");
        map.put("Cookie", "JSESSIONID=" + "IU9FapmnTaXrV7DMcpDWwJoLepJUXwlL9A_rkf8Z"/*AppSharedPreferences.getInstance().getString(AppSharedPreferences.KEY_SESSION_ID)*/);
        map.put("text/xml", "gzip");

        mAppRemoteRepository.getInProgressInventoryResults(map,this, site, contextName, collectionType);
    }

    public void getInventoryDetails(String site, String contextName, int partialID) {
        setIsLoding(true);
        Map<String, String> map = new HashMap<>();
        map.put("Accept", "application/json");
        map.put("Cookie", "JSESSIONID=" + "IU9FapmnTaXrV7DMcpDWwJoLepJUXwlL9A_rkf8Z"/*AppSharedPreferences.getInstance().getString(AppSharedPreferences.KEY_SESSION_ID)*/);
        map.put("text/xml", "gzip");

        mAppRemoteRepository.getInventoryDetails(map,this, site, contextName, partialID);
    }


    public void onCallFailed(Throwable throwable, String errorMessage) {
        setErrorMessage(errorMessage);
    }
}
