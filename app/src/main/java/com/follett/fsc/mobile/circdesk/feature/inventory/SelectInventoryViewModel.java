/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.inventory;

import com.follett.fsc.mobile.circdesk.app.ItemClickListener;
import com.follett.fsc.mobile.circdesk.app.base.BaseViewModel;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.view.View;

import java.util.List;

public class SelectInventoryViewModel extends BaseViewModel {
    public MutableLiveData<List<Inventory>> inventoryList = new MutableLiveData<>();
    private ItemClickListener itemClickListener;

    public SelectInventoryViewModel(@NonNull Application application, ItemClickListener itemClickListener) {
        super(application);
        this.itemClickListener = itemClickListener;
    }

    public void OnItemClick(View view) {
        itemClickListener.onItemClick(view, 0);
    }


}
