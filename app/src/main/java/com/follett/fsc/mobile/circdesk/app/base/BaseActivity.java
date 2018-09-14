/*
 * Copyright (c) 2018 Follett. All rights reserved.
 */
package com.follett.fsc.mobile.circdesk.app.base;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;

import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.databinding.ActivityBaseBinding;


import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences;
import com.follett.fsc.mobile.circdesk.databinding.ActivityBaseBinding;

public abstract class BaseActivity<V extends BaseViewModel> extends AppCompatActivity {
    


    private TextView titleBarTextView;
    private ImageView backBtn;

    public ActivityBaseBinding baseBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        baseBinding = DataBindingUtil.setContentView(this, R.layout.activity_base);

        if(!TextUtils.isEmpty(AppSharedPreferences.getInstance(this).getString(AppSharedPreferences.KEY_SESSION_ID))){
            baseBinding.toolBarIcon.setImageResource(R.drawable.baseline_account_circle);
        }else {
            baseBinding.toolBarIcon.setImageResource(R.drawable.info_icon);
        }

        baseBinding.toolBarIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                baseBinding.drawerLayout.openDrawer(GravityCompat.END);
                if(!TextUtils.isEmpty(AppSharedPreferences.getInstance(getApplicationContext()).getString(AppSharedPreferences.KEY_SESSION_ID))){
                    baseBinding.navigationLayout.navView.getHeaderView(0).setVisibility(View.VISIBLE);
                    baseBinding.navigationLayout.navInfoLoginView.setVisibility(View.VISIBLE);
                    baseBinding.navigationLayout.navInfoLayout.navInfoView.setVisibility(View.GONE);
                    baseBinding.navigationLayout.navToolBarIcon.setImageResource(R.drawable.baseline_account_circle);

                    String site_text = String.format(getApplicationContext().getResources().getString(R.string.site_info),
                            AppSharedPreferences.getInstance(getApplicationContext()).getString(AppSharedPreferences.KEY_SITE_NAME));
                    baseBinding.navigationLayout.siteHeader.setText(site_text);
                    baseBinding.navigationLayout.logout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            AppSharedPreferences.getInstance(getApplicationContext()).removeAllSession();
                        }
                    });
                }else {
                    baseBinding.navigationLayout.navView.getHeaderView(0).setVisibility(View.GONE);
                    baseBinding.navigationLayout.navInfoLoginView.setVisibility(View.GONE);
                    baseBinding.navigationLayout.navInfoLayout.navInfoView.setVisibility(View.VISIBLE);
                    baseBinding.navigationLayout.navToolBarIcon.setImageResource(R.drawable.info_icon_dark);
                }
            }
        });

        setSupportActionBar(baseBinding.toolbar);
    }

    protected <T extends ViewDataBinding> T putContentView(@LayoutRes int resId) {
        return DataBindingUtil.inflate(getLayoutInflater(), resId, baseBinding.baseContainer, true);
    }

    protected void setTitleBar(String titleBarText) {
        baseBinding.titleBar.setText(titleBarText);
    }

    protected void setBackBtnVisible() {
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

}
