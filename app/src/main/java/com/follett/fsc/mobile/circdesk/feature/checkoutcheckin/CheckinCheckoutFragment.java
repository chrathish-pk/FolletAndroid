/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.checkoutcheckin;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;

import com.follett.fsc.mobile.circdesk.BR;
import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.app.base.BaseFragment;
import com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences;
import com.follett.fsc.mobile.circdesk.databinding.ActivityCheckinCheckoutBinding;
import com.follett.fsc.mobile.circdesk.feature.checkoutcheckin.checkin.view.CheckinFragment;
import com.follett.fsc.mobile.circdesk.feature.checkoutcheckin.checkout.view.CheckoutFragment;
import com.follett.fsc.mobile.circdesk.feature.loginsetup.model.Permissions;
import com.follett.fsc.mobile.circdesk.utils.AppUtils;
import com.follett.fsc.mobile.circdesk.utils.FollettLog;
import com.google.gson.Gson;

public class CheckinCheckoutFragment extends BaseFragment<ActivityCheckinCheckoutBinding, CheckinCheckoutViewModel> implements View.OnClickListener, ViewPager.OnPageChangeListener {

    private TabLayoutViewPagerAdapter adapter;
    private ActivityCheckinCheckoutBinding actvityCheckinCheckoutBinding;
    private CheckinCheckoutViewModel checkinCheckoutViewModel;

    @Override
    public int getLayoutId() {
        return R.layout.activity_checkin_checkout;
    }

    @Override
    public CheckinCheckoutViewModel getViewModel() {
        if (getBaseApplication() == null) {
            return null;
        }

        checkinCheckoutViewModel = new CheckinCheckoutViewModel(getBaseApplication());
        return checkinCheckoutViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        actvityCheckinCheckoutBinding = getViewDataBinding();

        setupViewPager(actvityCheckinCheckoutBinding.viewPager);
        actvityCheckinCheckoutBinding.tabLayout.setupWithViewPager(actvityCheckinCheckoutBinding.viewPager);
        actvityCheckinCheckoutBinding.viewPager.setOffscreenPageLimit(actvityCheckinCheckoutBinding.tabLayout.getTabCount());
        actvityCheckinCheckoutBinding.viewPager.addOnPageChangeListener(this);

        actvityCheckinCheckoutBinding.libraryResourceIncludeLayout.libraryBtn.setOnClickListener(this);
        actvityCheckinCheckoutBinding.libraryResourceIncludeLayout.resourceBtn.setOnClickListener(this);

        updateLibraryResourceBg();
    }

    private void updateLibraryResourceBg() {
        Activity activity = getBaseActivity();
        if (activity == null) {
            return;
        }
        AppUtils.getInstance().updateLibResBg(activity,actvityCheckinCheckoutBinding.libraryResourceIncludeLayout.libraryBtn, actvityCheckinCheckoutBinding.libraryResourceIncludeLayout.resourceBtn);
    }

    private void setupViewPager(ViewPager checkinCheckoutViewPager) {
        try {
            adapter = new TabLayoutViewPagerAdapter(this.getChildFragmentManager());
            adapter.addFragment(new CheckoutFragment(), getString(R.string.checkout));
            adapter.addFragment(new CheckinFragment(), getString(R.string.checkin));

            checkinCheckoutViewPager.setAdapter(adapter);
            checkinCheckoutViewPager.setCurrentItem(0);
        } catch (Exception e) {
            FollettLog.e(getString(R.string.error), e.getMessage());
        }
    }

    @Override
    public void onClick(View v) {

        Activity activity = getBaseActivity();
        if (activity == null) {
            return;
        }
        switch (v.getId()) {
            case R.id.libraryBtn:
                AppSharedPreferences.getInstance().setBoolean(AppSharedPreferences.KEY_IS_LIBRARY_SELECTED, true);
                AppUtils.getInstance().updateLibResBg(activity,actvityCheckinCheckoutBinding.libraryResourceIncludeLayout.libraryBtn, actvityCheckinCheckoutBinding.libraryResourceIncludeLayout.resourceBtn);

                if (adapter != null) {
                    Fragment fragment = adapter.getItem(actvityCheckinCheckoutBinding.viewPager.getCurrentItem());
                    if (fragment instanceof CheckoutFragment) {
                        ((CheckoutFragment) fragment).bindPatronResult();
                    } else if (fragment instanceof CheckinFragment) {
                        ((CheckinFragment) fragment).bindCheckinResult();
                    }
                }
                break;
            case R.id.resourceBtn:
                AppSharedPreferences.getInstance().setBoolean(AppSharedPreferences.KEY_IS_LIBRARY_SELECTED, false);
                AppUtils.getInstance().updateLibResBg(activity,actvityCheckinCheckoutBinding.libraryResourceIncludeLayout.libraryBtn, actvityCheckinCheckoutBinding.libraryResourceIncludeLayout.resourceBtn);

                if (adapter != null) {
                    Fragment fragment1 = adapter.getItem(actvityCheckinCheckoutBinding.viewPager.getCurrentItem());
                    if (fragment1 instanceof CheckoutFragment) {
                        ((CheckoutFragment) fragment1).bindPatronResult();
                    } else if (fragment1 instanceof CheckinFragment) {
                        ((CheckinFragment) fragment1).bindCheckinResult();
                    }
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!TextUtils.isEmpty(AppSharedPreferences.getInstance().getString(AppSharedPreferences.KEY_SELECTED_BARCODE))) {
            Fragment fragment = adapter.getItem(actvityCheckinCheckoutBinding.viewPager.getCurrentItem());
            if (fragment instanceof CheckoutFragment) {
                ((CheckoutFragment) fragment).getPatronID();
            }
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        //Do implementation
    }

    @Override
    public void onPageSelected(int position) {
        String permissionValue = AppSharedPreferences.getInstance().getString(AppSharedPreferences.KEY_PERMISSIONS);
        Permissions permissions = new Gson().fromJson(permissionValue, Permissions.class);
        if (position == 0) {
            showLibraryResource(Boolean.parseBoolean(permissions.getCanCheckoutLibrary()), Boolean.parseBoolean(permissions.getCanCheckoutAsset()));
        } else if (position == 1) {
            showLibraryResource(Boolean.parseBoolean(permissions.getCanCheckinLibrary()), Boolean.parseBoolean(permissions.getCanCheckinAsset()));
        }
    }

    private void showLibraryResource(boolean canShowLibrary, boolean canShowResource) {
        if (canShowLibrary)
            actvityCheckinCheckoutBinding.libraryResourceIncludeLayout.libraryBtn.setVisibility(View.VISIBLE);
        else
            actvityCheckinCheckoutBinding.libraryResourceIncludeLayout.libraryBtn.setVisibility(View.GONE);

        if (canShowResource)
            actvityCheckinCheckoutBinding.libraryResourceIncludeLayout.resourceBtn.setVisibility(View.VISIBLE);
        else
            actvityCheckinCheckoutBinding.libraryResourceIncludeLayout.resourceBtn.setVisibility(View.GONE);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        //Do implementation
    }
}
