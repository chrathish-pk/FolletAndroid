/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.inventory;

import android.app.Application;
import android.view.View;

import com.follett.fsc.mobile.circdesk.app.CTAButtonListener;
import com.follett.fsc.mobile.circdesk.app.ItemClickListener;
import com.follett.fsc.mobile.circdesk.app.base.BaseViewModel;
import com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences;
import com.follett.fsc.mobile.circdesk.data.remote.api.NetworkInterface;
import com.follett.fsc.mobile.circdesk.data.remote.repository.AppRemoteRepository;

public class InventoryViewModel extends BaseViewModel<CTAButtonListener> implements NetworkInterface {

    private ItemClickListener itemClickListener;

    private AppRemoteRepository mAppRemoteRepository;

    public InventoryViewModel(Application application, ItemClickListener itemClickListener) {
        super(application);
        this.itemClickListener = itemClickListener;
        mAppRemoteRepository = new AppRemoteRepository();
    }

    public void getInProgressInventoryResults(String site, String contextName, int collectionType) {
        setIsLoding(true);
        mAppRemoteRepository.getInProgressInventoryResults(this, site, contextName, collectionType);
    }

    public void OnItemClick(View view) {
        itemClickListener.onItemClick(view, 0);
    }

    @Override
    public void onCallCompleted(Object model) {
        InProgressInventoryResults inProgressInventoryResults = (InProgressInventoryResults) model;
        setIsLoding(false);
    }

    @Override
    public void onCallFailed(Throwable throwable) {

    }
}
