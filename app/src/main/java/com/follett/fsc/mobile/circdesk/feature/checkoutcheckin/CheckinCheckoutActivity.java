/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.checkoutcheckin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;

import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.app.base.BaseActivity;
import com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences;
import com.follett.fsc.mobile.circdesk.databinding.ActivityCheckinCheckoutBinding;
import com.follett.fsc.mobile.circdesk.utils.FollettLog;

public class CheckinCheckoutActivity extends BaseActivity<CheckinCheckoutViewModel> implements View.OnClickListener {

    TabLayoutViewPagerAdapter adapter;
    ActivityCheckinCheckoutBinding actvityCheckinCheckoutBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actvityCheckinCheckoutBinding = putContentView(R.layout.activity_checkin_checkout);

        setTitleBar(getString(R.string.checkinChecoutTitle));
        setBackBtnVisible();
        baseBinding.backBtn.setOnClickListener(this);

        setupViewPager(actvityCheckinCheckoutBinding.viewPager);
        actvityCheckinCheckoutBinding.tabLayout.setupWithViewPager(actvityCheckinCheckoutBinding.viewPager);
        actvityCheckinCheckoutBinding.viewPager.setOffscreenPageLimit(actvityCheckinCheckoutBinding.tabLayout.getTabCount());
        actvityCheckinCheckoutBinding.viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(actvityCheckinCheckoutBinding.tabLayout));

        actvityCheckinCheckoutBinding.libraryResourceIncludeLayout.libraryBtn.setOnClickListener(this);
        actvityCheckinCheckoutBinding.libraryResourceIncludeLayout.resourceBtn.setOnClickListener(this);

        updateLibraryResourceBg();
    }

    private void updateLibraryResourceBg() {
        if (AppSharedPreferences.getInstance(this).getBoolean(AppSharedPreferences.KEY_IS_LIBRARY_SELECTED)) {
            actvityCheckinCheckoutBinding.libraryResourceIncludeLayout.libraryBtn.setBackgroundColor(getResources().getColor(R.color.blueLabel));
            actvityCheckinCheckoutBinding.libraryResourceIncludeLayout.libraryBtn.setTextColor(getResources().getColor(R.color.white));
            actvityCheckinCheckoutBinding.libraryResourceIncludeLayout.resourceBtn.setBackgroundColor(getResources().getColor(R.color.white));
            actvityCheckinCheckoutBinding.libraryResourceIncludeLayout.resourceBtn.setTextColor(getResources().getColor(R.color.blueLabel));
        } else {
            actvityCheckinCheckoutBinding.libraryResourceIncludeLayout.resourceBtn.setBackgroundColor(getResources().getColor(R.color.blueLabel));
            actvityCheckinCheckoutBinding.libraryResourceIncludeLayout.resourceBtn.setTextColor(getResources().getColor(R.color.white));
            actvityCheckinCheckoutBinding.libraryResourceIncludeLayout.libraryBtn.setBackgroundColor(getResources().getColor(R.color.white));
            actvityCheckinCheckoutBinding.libraryResourceIncludeLayout.libraryBtn.setTextColor(getResources().getColor(R.color.blueLabel));
        }
    }

    private void setupViewPager(ViewPager checkinCheckoutViewPager) {
        try {
            adapter = new TabLayoutViewPagerAdapter(this.getSupportFragmentManager());
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
        switch (v.getId()) {
            case R.id.backBtn:
                finish();
                break;
            case R.id.libraryBtn:
                actvityCheckinCheckoutBinding.libraryResourceIncludeLayout.libraryBtn.setBackgroundColor(getResources().getColor(R.color.blueLabel));
                actvityCheckinCheckoutBinding.libraryResourceIncludeLayout.libraryBtn.setTextColor(getResources().getColor(R.color.white));
                actvityCheckinCheckoutBinding.libraryResourceIncludeLayout.resourceBtn.setBackgroundColor(getResources().getColor(R.color.white));
                actvityCheckinCheckoutBinding.libraryResourceIncludeLayout.resourceBtn.setTextColor(getResources().getColor(R.color.blueLabel));
                AppSharedPreferences.getInstance(this).setBoolean(AppSharedPreferences.KEY_IS_LIBRARY_SELECTED, true);
                Fragment fragment = adapter.getItem(actvityCheckinCheckoutBinding.viewPager.getCurrentItem());
                ((CheckoutFragment) fragment).bindPatronResult();
                break;
            case R.id.resourceBtn:
                actvityCheckinCheckoutBinding.libraryResourceIncludeLayout.resourceBtn.setBackgroundColor(getResources().getColor(R.color.blueLabel));
                actvityCheckinCheckoutBinding.libraryResourceIncludeLayout.resourceBtn.setTextColor(getResources().getColor(R.color.white));
                actvityCheckinCheckoutBinding.libraryResourceIncludeLayout.libraryBtn.setBackgroundColor(getResources().getColor(R.color.white));
                actvityCheckinCheckoutBinding.libraryResourceIncludeLayout.libraryBtn.setTextColor(getResources().getColor(R.color.blueLabel));
                AppSharedPreferences.getInstance(this).setBoolean(AppSharedPreferences.KEY_IS_LIBRARY_SELECTED, false);
                Fragment fragment1 = adapter.getItem(actvityCheckinCheckoutBinding.viewPager.getCurrentItem());
                ((CheckoutFragment) fragment1).bindPatronResult();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!TextUtils.isEmpty(AppSharedPreferences.getInstance(this).getString(AppSharedPreferences.KEY_SELECTED_BARCODE))) {
            Fragment fragment = adapter.getItem(actvityCheckinCheckoutBinding.viewPager.getCurrentItem());
            ((CheckoutFragment) fragment).getPatronID();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
