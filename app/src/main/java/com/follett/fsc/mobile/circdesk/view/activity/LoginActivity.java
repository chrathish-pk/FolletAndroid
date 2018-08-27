/*
 * Copyright (c) 2018 Follett. All rights reserved.
 */
package com.follett.fsc.mobile.circdesk.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;

import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.databinding.ActivityLoginBinding;
import com.follett.fsc.mobile.circdesk.interfaces.NavigationListener;
import com.follett.fsc.mobile.circdesk.view.adapter.SiteViewPagerAdapter;
import com.follett.fsc.mobile.circdesk.view.base.BaseActivity;
import com.follett.fsc.mobile.circdesk.view.fragment.LoginFragment;
import com.follett.fsc.mobile.circdesk.view.fragment.SchoolListFragment;
import com.follett.fsc.mobile.circdesk.viewmodel.LoginViewModel;

public class LoginActivity extends BaseActivity<LoginViewModel> implements NavigationListener {

    private ActivityLoginBinding mLoginBinding;

    private SchoolListFragment mSchoolListFragment;

    private LoginFragment mLoginFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLoginBinding = putContentView(R.layout.activity_login);
        inItView();
    }

    private void inItView() {

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

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        SiteViewPagerAdapter adapter = new SiteViewPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        mLoginBinding.viewPager.setAdapter(adapter);
    }

    public void navigationToNextFragment() {

    }

    @Override
    public void onNavigation(int position) {
        if (position == 0) { // Navigate to School list
            mSchoolListFragment = SchoolListFragment.newInstance();
            setToolBarTitle(getString(R.string.select_school_label));
            getFragmentManager().beginTransaction()
                    .add(R.id.loginContainer, mSchoolListFragment)
                    .addToBackStack(null)
                    .commit();
        } else if (position == 1) { // Navigate to Login
            mLoginFragment = LoginFragment.newInstance();
            setToolBarTitle(getString(R.string.get_started_label));
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.loginContainer, LoginFragment.newInstance(), "LoginFragment")
                    .addToBackStack(null)
                    .commit();
        } else if (position == 2) { // Navigate to Home Screen
            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
        }
    }

    @Override
    public void setToolBarTitle(String titleText) {
        setTitleBar(titleText);
    }

}

