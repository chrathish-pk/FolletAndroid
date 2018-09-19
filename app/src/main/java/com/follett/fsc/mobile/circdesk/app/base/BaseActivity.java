/*
 * Copyright (c) 2018 Follett. All rights reserved.
 */
package com.follett.fsc.mobile.circdesk.app.base;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.TextView;

import com.follett.fsc.mobile.circdesk.BuildConfig;
import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.databinding.ActivityBaseBinding;


import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences;
import com.follett.fsc.mobile.circdesk.databinding.ActivityBaseBinding;
import com.follett.fsc.mobile.circdesk.feature.homescreen.HomeActivity;
import com.follett.fsc.mobile.circdesk.feature.inventory.InventoryActivity;
import com.follett.fsc.mobile.circdesk.feature.loginsetup.LoginActivity;

public class BaseActivity<V extends BaseViewModel> extends AppCompatActivity {

    public ActivityBaseBinding baseBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        baseBinding = DataBindingUtil.setContentView(this, R.layout.activity_base);

        if(!TextUtils.isEmpty(AppSharedPreferences.getInstance().getString(AppSharedPreferences.KEY_SESSION_ID))){
            baseBinding.toolBarIcon.setImageResource(R.drawable.baseline_account_circle);
        }else {
            baseBinding.toolBarIcon.setImageResource(R.drawable.info_icon);
        }
        baseBinding.navigationLayout.navInfoSubLayout.legalBtn.setOnClickListener(this);
        baseBinding.toolBarIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                baseBinding.drawerLayout.openDrawer(GravityCompat.END);
                if(!TextUtils.isEmpty(AppSharedPreferences.getInstance().getString(AppSharedPreferences.KEY_SESSION_ID))){
                    baseBinding.navigationLayout.navView.getHeaderView(0).setVisibility(View.VISIBLE);
                    baseBinding.navigationLayout.navInfoLoginView.setVisibility(View.VISIBLE);
                    baseBinding.navigationLayout.navInfoLayout.navInfoView.setVisibility(View.GONE);
                    baseBinding.navigationLayout.navToolBarIcon.setImageResource(R.drawable.baseline_account_circle);

                    String site_text = String.format(getApplicationContext().getResources().getString(R.string.site_info),
                            AppSharedPreferences.getInstance().getString(AppSharedPreferences.KEY_SITE_NAME));
                    String apiVersion = String.format(getApplicationContext().getResources().getString(R.string.copyright_info),
                            BuildConfig.VERSION_NAME, AppSharedPreferences.getInstance(getApplicationContext()).getString(AppSharedPreferences.FOLLETT_API_VERSION));
                    baseBinding.navigationLayout.navInfoSubLayout.navbarSubContent.setText(apiVersion);
                    baseBinding.navigationLayout.siteHeader.setText(site_text);
                    baseBinding.navigationLayout.logout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            AppSharedPreferences.getInstance().removeValues(AppSharedPreferences.KEY_PERMISSIONS);
                            AppSharedPreferences.getInstance().removeValues(AppSharedPreferences.KEY_SESSION_ID);

                            finish();
                            startActivity(new Intent(BaseActivity.this, LoginActivity.class));
                        }
                    });
                } else {
                    baseBinding.navigationLayout.navView.getHeaderView(0).setVisibility(View.GONE);
                    baseBinding.navigationLayout.navInfoLoginView.setVisibility(View.GONE);
                    baseBinding.navigationLayout.navInfoLayout.navInfoView.setVisibility(View.VISIBLE);
                    baseBinding.navigationLayout.navToolBarIcon.setImageResource(R.drawable.info_icon_dark);
                }
            }
        });

        /* Please enable this for navigating to data picker screen by tapping Follet logo as of now. Need to remove this later */

       /* baseBinding.logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              baseBinding = DataBindingUtil.setContentView(BaseActivity.this, R.layout.call_numbers_and_seen_on_after);
                EditText date1 = (EditText)findViewById(R.id.enterDate);
                date1.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
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

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @TargetApi(Build.VERSION_CODES.N)
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            DatePickerDialog dpd = new DatePickerDialog(getActivity());
            // Create a TextView programmatically.
            TextView tv = new TextView(getActivity());

            // Create a TextView programmatically
            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT, // Width of TextView
                    RelativeLayout.LayoutParams.WRAP_CONTENT); // Height of TextView
            tv.setLayoutParams(lp);
            tv.setPadding(10, 10, 10, 10);
            tv.setGravity(Gravity.LEFT);
            tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14);
            tv.setText("Seen On or After");
            tv.setTextColor(getResources().getColor(R.color.white));
            tv.setBackgroundColor(getResources().getColor(R.color.blueLabel));

            dpd.setMessage(tv.getText()); // Uncomment this line to activate it

            // Return the DatePickerDialog
            return dpd;
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the chosen date
        }
    }


    protected <T extends ViewDataBinding> T putContentView(@LayoutRes int resId) {
        return DataBindingUtil.inflate(getLayoutInflater(), resId, baseBinding.baseContainer, true);
    }

    public void setTitleBar(String titleBarText) {
        baseBinding.titleBar.setText(titleBarText);
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

    public void pushFragment(Fragment fragment, int container, String tag, boolean shouldAdd) {
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
        ft.replace(container, fragment);
        ft.commit();
    }

    public void popFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.remove(fragment);
        ft.commit();
    }
    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.legalBtn)
        {
            startActivity(new Intent(BaseActivity.this, LegalActivity.class));
        }

    }
}
