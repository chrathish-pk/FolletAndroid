/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.inventory;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.follett.fsc.mobile.circdesk.app.base.BaseViewModel;

import java.util.List;

/**
 * Created by muthulakshmi on 12/09/18.
 */

public class InventoryListViewModel extends BaseViewModel {
    public MutableLiveData<List<Inventory>> inventoryList = new MutableLiveData<>();

    public InventoryListViewModel(@NonNull Application application) {
        super(application);
    }


}
