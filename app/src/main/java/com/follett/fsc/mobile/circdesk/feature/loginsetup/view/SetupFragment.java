/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */
package com.follett.fsc.mobile.circdesk.feature.loginsetup.view;

import com.follett.fsc.mobile.circdesk.BR;
import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.app.base.BaseFragment;
import com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences;
import com.follett.fsc.mobile.circdesk.databinding.ActivityLoginBinding;
import com.follett.fsc.mobile.circdesk.feature.homescreen.view.HomeFragment;
import com.follett.fsc.mobile.circdesk.feature.loginsetup.viewmodel.LoginViewModel;
import com.follett.fsc.mobile.circdesk.utils.AppUtils;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.text.TextUtils;

import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_CONTEXT_NAME;
import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_SESSION_ID;
import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_SITE_SHORT_NAME;

public class SetupFragment extends BaseFragment<ActivityLoginBinding, LoginViewModel> {
    private ActivityLoginBinding mLoginBinding;
    LoginViewModel loginViewModel;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public LoginViewModel getViewModel() {
        loginViewModel = new LoginViewModel(getBaseActivity().getApplication());
        return loginViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.loginViewModel;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final Activity activity = getBaseActivity();
        if (activity == null) {
            return;
        }
        mLoginBinding = getViewDataBinding();
        navigateToFragment();
    }

    private void loadBasicFragment() {
        mActivity.setTitle(getString(R.string.connect_your_school_label));
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
        SiteViewPagerAdapter adapter = new SiteViewPagerAdapter(getChildFragmentManager(), tabLayout.getTabCount());
        mLoginBinding.viewPager.setAdapter(adapter);
    }

    private void navigateToFragment() {
        if (!TextUtils.isEmpty(AppSharedPreferences.getInstance()
                .getString(KEY_SESSION_ID))) {
            AppUtils.getInstance()
                    .hideKeyBoard(getActivity(), mLoginBinding.getRoot());
            navigateToHome();
        } else if (!TextUtils.isEmpty(AppSharedPreferences.getInstance()
                .getString(KEY_SITE_SHORT_NAME))) {
            AppUtils.getInstance()
                    .hideKeyBoard(getActivity(), mLoginBinding.getRoot());
            navigateToLogin(false);
        } else if (!TextUtils.isEmpty(AppSharedPreferences.getInstance()
                .getString(KEY_CONTEXT_NAME))) {
            AppUtils.getInstance()
                    .hideKeyBoard(getActivity(), mLoginBinding.getRoot());
            navigateToSchoolList(false);
        } else {
            loadBasicFragment();
        }
    }

    private void navigateToHome() {
        mLoginBinding.tabLayout.removeAllTabs();
        mActivity.pushFragment(new HomeFragment(), R.id.loginContainer, "HomeFragment", false);
    }

    private void navigateToLogin(boolean isAddToBackStack) {
        mActivity.setTitle(getString(R.string.get_started_label));
        mActivity.pushFragment(new LoginFragment(), R.id.loginContainer, "LoginFragment", isAddToBackStack);

    }

    private void navigateToSchoolList(boolean isAddToBackStack) {
        mActivity.setTitleBar(getString(R.string.select_school_label));
        mActivity.pushFragment(new SchoolListFragment(), R.id.loginContainer, "SchoolListFragment", isAddToBackStack);
    }

}

