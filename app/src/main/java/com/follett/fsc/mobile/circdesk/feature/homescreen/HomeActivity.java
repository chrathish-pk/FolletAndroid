/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.homescreen;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.app.ItemClickListener;
import com.follett.fsc.mobile.circdesk.app.base.BaseActivity;
import com.follett.fsc.mobile.circdesk.databinding.ActivityHomeBinding;
import com.follett.fsc.mobile.circdesk.feature.checkoutcheckin.CheckinCheckoutActivity;
import com.follett.fsc.mobile.circdesk.feature.inventory.InventoryActivity;
import com.follett.fsc.mobile.circdesk.feature.inventory.InventoryFragment;
import com.follett.fsc.mobile.circdesk.feature.itemstatus.ItemStatusActivity;
import com.follett.fsc.mobile.circdesk.feature.itemstatus.ItemStatusFragment;
import com.follett.fsc.mobile.circdesk.feature.patronstatus.PatronStatusActivity;
import com.follett.fsc.mobile.circdesk.feature.patronstatus.PatronStatusFragment;
import com.follett.fsc.mobile.circdesk.utils.SpacesItemDecoration;


public class HomeActivity extends BaseActivity<HomeViewModel> implements ItemClickListener {

    private ActivityHomeBinding activityHomeBinding;
    private HomeViewModel homeViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityHomeBinding = putContentView(R.layout.activity_home);
        setTitleBar(getString(R.string.home));

        homeViewModel = new HomeViewModel(getApplication(),this);
        activityHomeBinding.setHomeViewModel(homeViewModel);

    }

    @Override
    public void onItemClicked() {
        //Do nothing
    }

    @Override
    public void onItemClick(View view, int position) {

        switch (view.getId()) {
            case R.id.menuCheckinCheckoutLayout:
                startActivity(new Intent(this, CheckinCheckoutActivity.class));
                break;
            case R.id.menuPatronStatusLayout:
                startActivity(new Intent(HomeActivity.this, PatronStatusActivity.class));
                break;
            case R.id.menuItemStatusLayout:
                startActivity(new Intent(HomeActivity.this, ItemStatusActivity.class));
                break;
            case R.id.menuInventoryLayout:
                startActivity(new Intent(HomeActivity.this, InventoryActivity.class));
                break;
        }
    }
}
