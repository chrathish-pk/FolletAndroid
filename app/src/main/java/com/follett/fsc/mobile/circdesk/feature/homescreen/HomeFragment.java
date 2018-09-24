/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.homescreen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.follett.fsc.mobile.circdesk.BR;
import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.app.ItemClickListener;
import com.follett.fsc.mobile.circdesk.app.base.BaseFragment;
import com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences;
import com.follett.fsc.mobile.circdesk.databinding.ActivityHomeBinding;
import com.follett.fsc.mobile.circdesk.feature.checkoutcheckin.CheckinCheckoutFragment;
import com.follett.fsc.mobile.circdesk.feature.inventory.InventoryFragment;
import com.follett.fsc.mobile.circdesk.feature.itemstatus.ItemStatusFragment;
import com.follett.fsc.mobile.circdesk.feature.loginsetup.model.Permissions;
import com.follett.fsc.mobile.circdesk.feature.patronstatus.PatronStatusFragment;
import com.google.gson.Gson;

public class HomeFragment extends BaseFragment<ActivityHomeBinding, HomeViewModel> implements ItemClickListener {
    private HomeViewModel homeViewModel;
    private ActivityHomeBinding activityHomeBinding;

    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    public HomeViewModel getViewModel() {
    
        if (getBaseApplication() == null) {
            return null;
        }
        homeViewModel = new HomeViewModel(getBaseApplication(), this);
        return homeViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.homeViewModel;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        activityHomeBinding = getViewDataBinding();

        mActivity.setTitleBar(getString(R.string.home));
        mActivity.changeInfoIcon();

        //checkPermissionToShowMenu();

    }

    private void checkPermissionToShowMenu() {
        String permissionValue = AppSharedPreferences.getInstance().getString(AppSharedPreferences.KEY_PERMISSIONS);
        Permissions permissions = new Gson().fromJson(permissionValue, Permissions.class);

        boolean canCheckoutShow = Boolean.parseBoolean(permissions.getCanCheckoutAsset()) || Boolean.parseBoolean(permissions.getCanCheckoutLibrary()) || Boolean.parseBoolean(permissions.getCanCheckoutTextbook());
        boolean canCheckinShow = Boolean.parseBoolean(permissions.getCanCheckinAsset()) || Boolean.parseBoolean(permissions.getCanCheckinLibrary()) || Boolean.parseBoolean(permissions.getCanCheckinTextbook());
        boolean canShowPatronStatus = Boolean.parseBoolean(permissions.getCanViewPatronStatus());
        boolean canShowItemStatus = Boolean.parseBoolean(permissions.getCanViewItemsOutAsset()) || Boolean.parseBoolean(permissions.getCanViewItemsOutLibrary()) || Boolean.parseBoolean(permissions.getCanViewItemsOutTextbook());

        if (canCheckoutShow || canCheckinShow)
            activityHomeBinding.menuCheckinCheckoutLayout.setVisibility(View.VISIBLE);
        else
            activityHomeBinding.menuCheckinCheckoutLayout.setVisibility(View.GONE);

        if (canShowPatronStatus)
            activityHomeBinding.menuPatronStatusLayout.setVisibility(View.VISIBLE);
        else
            activityHomeBinding.menuPatronStatusLayout.setVisibility(View.GONE);

        if (canShowItemStatus)
            activityHomeBinding.menuItemStatusLayout.setVisibility(View.VISIBLE);
        else
            activityHomeBinding.menuItemStatusLayout.setVisibility(View.GONE);
    }

    @Override
    public void onItemClick(View view, int position) {
        switch (view.getId()) {
            case R.id.menuCheckinCheckoutLayout:
                mActivity.pushFragment(new CheckinCheckoutFragment(), R.id.loginContainer, "CheckinCheckoutFragment", true);
                break;
            case R.id.menuPatronStatusLayout:
                mActivity.pushFragment(new PatronStatusFragment(), R.id.loginContainer, "PatronStatusFragment", true);
                break;
            case R.id.menuItemStatusLayout:
                mActivity.pushFragment(new ItemStatusFragment(), R.id.loginContainer, "ItemStatusFragment", true);
                break;
            case R.id.menuInventoryLayout:
                mActivity.pushFragment(new InventoryFragment(), R.id.loginContainer, "InventoryFragment", true);
                break;
        }
    }
}
