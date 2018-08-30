/*
 * Copyright (c) 2018 Follett. All rights reserved.
 */
package com.follett.fsc.mobile.circdesk.view.activity;

import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences;
import com.follett.fsc.mobile.circdesk.databinding.ActivityLoginBinding;
import com.follett.fsc.mobile.circdesk.interfaces.NavigationListener;
import com.follett.fsc.mobile.circdesk.utils.AppUtils;
import com.follett.fsc.mobile.circdesk.view.adapter.SiteViewPagerAdapter;
import com.follett.fsc.mobile.circdesk.view.base.BaseActivity;
import com.follett.fsc.mobile.circdesk.view.fragment.LoginFragment;
import com.follett.fsc.mobile.circdesk.view.fragment.SchoolListFragment;
import com.follett.fsc.mobile.circdesk.viewmodel.LoginViewModel;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;

import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_CONTEXT_NAME;
import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_SITE_SHORT_NAME;
import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.SESSION_ID;

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
        navigateToFragment();
    }
    
    
    @Override
    public void onNavigation(int position) {
        if (position == 0) { // Navigate to School list
            navigateToSchoolList(true);
        } else if (position == 1) { // Navigate to Login
            navigateToLogin(true);
        } else if (position == 2) { // Navigate to Home Screen
            navigateToHome();
        }
    }
    
    private void navigateToLogin(boolean isAddToBackStack) {
        mLoginFragment = LoginFragment.newInstance();
        setToolBarTitle(getString(R.string.get_started_label));
        final FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction()
                .add(R.id.loginContainer, LoginFragment.newInstance(), "LoginFragment");
        if (isAddToBackStack) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();
    }
    
    private void navigateToSchoolList(boolean isAddToBackStack) {
        
        mSchoolListFragment = SchoolListFragment.newInstance();
        setToolBarTitle(getString(R.string.select_school_label));
        
        final FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction()
                .add(R.id.loginContainer, mSchoolListFragment);
        if (isAddToBackStack) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();
    }
    
    private void navigateToHome() {
        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
    }
    
    @Override
    public void setToolBarTitle(String titleText) {
        setTitleBar(titleText);
    }
    
    private void navigateToFragment() {
        
        if (!TextUtils.isEmpty(AppSharedPreferences.getInstance(this)
                .getString(SESSION_ID))) {
            AppUtils.getInstance()
                    .hideKeyBoard(this, mLoginBinding.getRoot());
            navigateToHome();
        } else if (!TextUtils.isEmpty(AppSharedPreferences.getInstance(this)
                .getString(KEY_SITE_SHORT_NAME))) {
            AppUtils.getInstance()
                    .hideKeyBoard(this, mLoginBinding.getRoot());
            navigateToLogin(false);
        } else if (!TextUtils.isEmpty(AppSharedPreferences.getInstance(this)
                .getString(KEY_CONTEXT_NAME))) {
            AppUtils.getInstance()
                    .hideKeyBoard(this, mLoginBinding.getRoot());
            navigateToSchoolList(false);
        }
    }
    
}

