/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.homescreen.viewmodel;

import com.follett.fsc.mobile.circdesk.app.CTAButtonListener;
import com.follett.fsc.mobile.circdesk.app.ItemClickListener;
import com.follett.fsc.mobile.circdesk.app.base.BaseViewModel;

import android.app.Application;
import android.view.View;

public class HomeViewModel extends BaseViewModel<CTAButtonListener> {

    private ItemClickListener itemClickListener;

    public HomeViewModel(Application application, ItemClickListener itemClickListener) {
        super(application);
        this.itemClickListener = itemClickListener;
    }


    public void onMenuClick(View view) {
        itemClickListener.onItemClick(view, 0);
    }
}
