/*
 * Copyright (c) 2018 Follett. All rights reserved.
 */
package com.follett.fsc.mobile.circdesk.app.base;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;

import com.follett.fsc.mobile.circdesk.BuildConfig;
import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences;
import com.follett.fsc.mobile.circdesk.databinding.ActivityBaseBinding;
import com.follett.fsc.mobile.circdesk.databinding.NavigationHeaderBinding;
import com.follett.fsc.mobile.circdesk.feature.loginsetup.view.SetupActivity;
import com.follett.fsc.mobile.circdesk.utils.AppUtils;



public class BaseActivity<V extends BaseViewModel> extends AppCompatActivity implements View.OnClickListener {

    public ActivityBaseBinding baseBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        baseBinding = DataBindingUtil.setContentView(this, R.layout.activity_base);
        if (!TextUtils.isEmpty(AppSharedPreferences.getInstance().getString(AppSharedPreferences.KEY_SESSION_ID))) {
            baseBinding.toolBarIcon.setImageResource(R.drawable.baseline_account_circle);
        } else {
            baseBinding.toolBarIcon.setImageResource(R.drawable.info_icon);
        }
        baseBinding.navigationLayout.navInfoSubLayout.legalBtn.setOnClickListener(this);
        baseBinding.backBtn.setOnClickListener(this);
        baseBinding.toolBarIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                baseBinding.drawerLayout.openDrawer(GravityCompat.END);
                View headerView;
                if (!TextUtils.isEmpty(AppSharedPreferences.getInstance().getString(AppSharedPreferences.KEY_SESSION_ID))) {
                    baseBinding.navigationLayout.navView.getHeaderView(0).setVisibility(View.VISIBLE);
                    baseBinding.navigationLayout.navInfoLoginView.setVisibility(View.VISIBLE);
                    baseBinding.navigationLayout.navInfoLayout.navInfoView.setVisibility(View.GONE);
                    baseBinding.navigationLayout.navToolBarIcon.setImageResource(R.drawable.baseline_account_circle);
                    String siteText = String.format(getApplicationContext().getResources().getString(R.string.site_info),
                            AppSharedPreferences.getInstance().getString(AppSharedPreferences.KEY_SITE_NAME));
                    String apiVersion = String.format(getApplicationContext().getResources().getString(R.string.copyright_info),
                            BuildConfig.VERSION_NAME, AppSharedPreferences.getInstance().getString(AppSharedPreferences.FOLLETT_API_VERSION));
                    String userName = String.format(getApplicationContext().getResources().getString(R.string.user_info), AppSharedPreferences.getInstance().getString(AppSharedPreferences.KEY_USERNAME));
                    baseBinding.navigationLayout.navInfoSubLayout.navbarSubContent.setText(apiVersion);
                    baseBinding.navigationLayout.siteHeader.setText(siteText);
                    headerView = baseBinding.navigationLayout.navView.getHeaderView(0);
                    NavigationHeaderBinding headerBinding = DataBindingUtil.bind(headerView);
                    headerBinding.siteInfo.setText(siteText);
                    headerBinding.userInfo.setText(userName);
                    baseBinding.navigationLayout.logout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            AppSharedPreferences.getInstance().removeValues(AppSharedPreferences.KEY_PERMISSIONS);
                            AppSharedPreferences.getInstance().removeValues(AppSharedPreferences.KEY_SESSION_ID);
                            finish();
                            startActivity(new Intent(BaseActivity.this, SetupActivity.class));
                        }
                    });
                } else {

                    baseBinding.navigationLayout.navView.getHeaderView(0).setVisibility(View.GONE);
                    baseBinding.navigationLayout.navInfoLoginView.setVisibility(View.GONE);
                    baseBinding.navigationLayout.navInfoLayout.navInfoView.setVisibility(View.VISIBLE);
                    baseBinding.navigationLayout.navToolBarIcon.setImageResource(R.drawable.info_icon_dark);
                    String apiVersion = String.format(getApplicationContext().getResources().getString(R.string.copyright_info),
                            BuildConfig.VERSION_NAME, AppSharedPreferences.getInstance().getString(AppSharedPreferences.FOLLETT_API_VERSION));
                    baseBinding.navigationLayout.navInfoLayout.navbarSubContent.setText(apiVersion);
                }
            }
        });

        baseBinding.navigationLayout.logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppSharedPreferences.getInstance().removeValues(AppSharedPreferences.KEY_PERMISSIONS);
                AppSharedPreferences.getInstance().removeValues(AppSharedPreferences.KEY_SESSION_ID);

                finish();
                startActivity(new Intent(BaseActivity.this, SetupActivity.class));
            }
        });

        /* Please enable this for navigating to data picker screen by tapping Follet logo as of now. Need to remove this later */

       /* baseBinding.logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              baseBinding = DataBindingUtil.setContentView(BaseActivity.this, R.layout.fragment_call_numbers_exclude_items);
                EditText date1 = (EditText)findViewById(R.id.enterDate);
                date1.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // Initialize a new date picker dialog fragment
                        DialogFragment dFragment = new DatePickerFragment();
                        // Show the date picker dialog fragment
                        dFragment.show(getFragmentManager(), "Date Picker");
                    }
                });
            }
        });*/

        setSupportActionBar(baseBinding.toolbar);
    }

    protected <T extends ViewDataBinding> T putContentView(@LayoutRes int resId) {
        return DataBindingUtil.inflate(getLayoutInflater(), resId, baseBinding.baseContainer, true);
    }

    public void setTitleBar(String titleBarText) {
        baseBinding.titleBar.setText(titleBarText);
    }

    public void changeInfoIcon() {
        baseBinding.toolBarIcon.setImageResource(R.drawable.baseline_account_circle);
    }

    public void setBackBtnVisible() {
        baseBinding.backBtn.setVisibility(View.VISIBLE);
    }

    protected boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        }
        return false;
    }

    public void pushFragment(Fragment fragment, int container, String tag, boolean shouldAdd, boolean showBackBtn) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (shouldAdd)
            ft.addToBackStack(tag);
        else {
            if (fm.getBackStackEntryCount() > 0) {
                for (int i = 0; i < fm.getBackStackEntryCount(); i++) {
                    fm.popBackStack();
                }
            }
            if (!fm.getFragments().isEmpty()) {
                for (Fragment fragment1 : fm.getFragments()) {
                    getSupportFragmentManager().beginTransaction().remove(fragment1).commit();
                }
            }
        }
        ft.add(container, fragment, tag);
        setTitleBar(tag);
        if (showBackBtn)
            setBackBtnVisible();
        ft.commit();
    }

    public void replaceFragment(Fragment fragment, int container, String tag, boolean shouldAdd, boolean showBackBtn) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (shouldAdd)
            ft.addToBackStack(tag);
        ft.replace(container, fragment, tag);
        setTitleBar(tag);
        if (showBackBtn)
            setBackBtnVisible();
        ft.commit();
    }

    public void popFragment(String fragmentTag) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        fm.popBackStackImmediate();
        ft.remove(fm.findFragmentByTag(fragmentTag));
        ft.commit();
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.legalBtn) {
            startActivity(new Intent(BaseActivity.this, LegalActivity.class));
        } else if (v.getId() == R.id.backBtn) {
            AppUtils.getInstance().hideKeyBoard(this, v);
            onBackPressed();
        }


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.getFragments().get(fm.getFragments().size() - 1);
        if (fm.getFragments().size() == 1 || fragment.getTag().contains(".")) {
            setTitleBar("Home");
            baseBinding.backBtn.setVisibility(View.GONE);
        } else {
            setTitleBar(fragment.getTag());
        }
    }
}
