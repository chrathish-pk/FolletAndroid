/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */
package com.follett.fsc.mobile.circdesk.feature.loginsetup;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.text.TextUtils;

import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.app.base.BaseActivity;
import com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences;
import com.follett.fsc.mobile.circdesk.databinding.ActivityLoginBinding;
import com.follett.fsc.mobile.circdesk.feature.homescreen.HomeFragment;
import com.follett.fsc.mobile.circdesk.utils.AppUtils;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;

import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.app.base.BaseActivity;
import com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences;
import com.follett.fsc.mobile.circdesk.databinding.ActivityLoginBinding;
import com.follett.fsc.mobile.circdesk.feature.homescreen.HomeActivity;
import com.follett.fsc.mobile.circdesk.utils.AppUtils;

import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_CONTEXT_NAME;
import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_SESSION_ID;
import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_SITE_SHORT_NAME;

public class LoginActivity extends BaseActivity<LoginViewModel> implements NavigationListener {
    private ActivityLoginBinding mLoginBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLoginBinding = putContentView(R.layout.activity_login);
        navigateToFragment();
    }

    private void loadBasicFragment() {
        setToolBarTitle(getString(R.string.connect_your_school_label));
        final TabLayout tabLayout = mLoginBinding.tabLayout;
        tabLayout.addTab(tabLayout.newTab()
                .setText(getString(R.string.basic_label)));
        tabLayout.addTab(tabLayout.newTab()
                .setText(getString(R.string.advanced_label)));

        mLoginBinding.viewPager.setOffscreenPageLimit(tabLayout.getTabCount());
        mLoginBinding.viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mLoginBinding.tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mLoginBinding.viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // Do Nothing
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // Do Nothing
            }
        });
        SiteViewPagerAdapter adapter = new SiteViewPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        mLoginBinding.viewPager.setAdapter(adapter);
    }

    @Override
    public void onNavigation(int position) {
        // Do Nothing
    }

    private void navigateToLogin(boolean isAddToBackStack) {
        setToolBarTitle(getString(R.string.get_started_label));
        pushFragment(new LoginFragment(), R.id.loginContainer, "LoginFragment", isAddToBackStack);

    }

    private void navigateToDistrictList(DistrictList districtList, boolean isAddToBackStack) {
        DistrictListFragment mDistrictListFragment = DistrictListFragment.newInstance(districtList);
        setToolBarTitle(getString(R.string.select_district_label));
        pushFragment(mDistrictListFragment, R.id.loginContainer, "DistrictListFragment", isAddToBackStack);

    }

    private void navigateToSchoolList(boolean isAddToBackStack) {
        setToolBarTitle(getString(R.string.select_school_label));
        pushFragment(new SchoolListFragment(), R.id.loginContainer, "SchoolListFragment", isAddToBackStack);
    }

    private void navigateToHome() {
        pushFragment(new HomeFragment(), R.id.loginContainer, "HomeFragment", false);
    }

    @Override
    public void setToolBarTitle(String titleText) {
        setTitleBar(titleText);
    }

    @Override
    public void onNavigation(Object model, int position) {
        if (model instanceof DistrictList && position == 0) {
            navigateToDistrictList((DistrictList) model, true);
        } else if (position == 1) { // Navigate to School list
            navigateToSchoolList(true);
        } else if (position == 2) { // Navigate to Login
            navigateToLogin(true);
        } else if (position == 3) { // Navigate to Home Screen
            navigateToHome();
        }
    }


    private void navigateToFragment() {
        if (!TextUtils.isEmpty(AppSharedPreferences.getInstance()
                .getString(KEY_SESSION_ID))) {
            AppUtils.getInstance()
                    .hideKeyBoard(this, mLoginBinding.getRoot());
            navigateToHome();
        } else if (!TextUtils.isEmpty(AppSharedPreferences.getInstance()
                .getString(KEY_SITE_SHORT_NAME))) {
            AppUtils.getInstance()
                    .hideKeyBoard(this, mLoginBinding.getRoot());
            navigateToLogin(false);
        } else if (!TextUtils.isEmpty(AppSharedPreferences.getInstance()
                .getString(KEY_CONTEXT_NAME))) {
            AppUtils.getInstance()
                    .hideKeyBoard(this, mLoginBinding.getRoot());
            navigateToSchoolList(false);
        } else {
            loadBasicFragment();
        }
    }
    
}

