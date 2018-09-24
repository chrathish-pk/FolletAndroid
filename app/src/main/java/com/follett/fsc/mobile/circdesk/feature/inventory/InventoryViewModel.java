/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.inventory;

import com.follett.fsc.mobile.circdesk.app.CTAButtonListener;
import com.follett.fsc.mobile.circdesk.app.ItemClickListener;
import com.follett.fsc.mobile.circdesk.app.base.BaseViewModel;
import com.follett.fsc.mobile.circdesk.data.remote.api.NetworkInterface;
import com.follett.fsc.mobile.circdesk.data.remote.repository.AppRemoteRepository;
import com.follett.fsc.mobile.circdesk.feature.checkoutcheckin.UpdateUIListener;
import com.follett.fsc.mobile.circdesk.utils.AppUtils;
import com.follett.fsc.mobile.circdesk.utils.FollettLog;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.view.View;

public class InventoryViewModel extends BaseViewModel<CTAButtonListener> implements NetworkInterface {

    private ItemClickListener itemClickListener;
    public MutableLiveData<InProgressInventoryResults> inventoryListMutableLiveData = new MutableLiveData<>();
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

    public void getInProgressInventoryResults(String site, String contextName, int collectionType) {
        setIsLoding(true);
        mAppRemoteRepository.getInProgressInventoryResults(AppUtils.getHeader(mApplication),this, site, contextName, collectionType);
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

            }
        } catch (Exception e) {
            FollettLog.d("Exception", e.getMessage());
        }
        setIsLoding(false);
    }

    @Override
    public void onCallFailed(Throwable throwable) {

    }
}
