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

import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.app.base.BaseActivity;
import com.follett.fsc.mobile.circdesk.databinding.ActivityHomeBinding;
import com.follett.fsc.mobile.circdesk.feature.checkoutcheckin.CheckinCheckoutActivity;
import com.follett.fsc.mobile.circdesk.feature.patronstatus.PatronStatusActivity;
import com.follett.fsc.mobile.circdesk.utils.SpacesItemDecoration;


public class HomeActivity extends BaseActivity<HomeViewModel> {

    private ActivityHomeBinding activityHomeBinding;
    private HomeMenuAdapter homeMenuAdapter;
    private HomeViewModel homeViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityHomeBinding = putContentView(R.layout.activity_home);

        setTitleBar(getString(R.string.home));

        homeViewModel = new HomeViewModel(getApplication());
        homeViewModel.loadHomeMenuItems(this);

        activityHomeBinding.menuRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.spacing);
        activityHomeBinding.menuRecyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));

        homeMenuAdapter = new HomeMenuAdapter(this, homeViewModel);
        activityHomeBinding.menuRecyclerView.setAdapter(homeMenuAdapter);

        homeViewModel.getOpenTaskEvent().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                if (s.equalsIgnoreCase(getString(R.string.checkInCheckout))) {
                    startActivity(new Intent(HomeActivity.this, CheckinCheckoutActivity.class));
                } else if (s.equalsIgnoreCase(getString(R.string.patronStatus))) {
                    startActivity(new Intent(HomeActivity.this, PatronStatusActivity.class));
                }
            }
        });
    }

}
