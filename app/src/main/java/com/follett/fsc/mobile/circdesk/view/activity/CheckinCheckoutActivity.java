package com.follett.fsc.mobile.circdesk.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;

import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences;
import com.follett.fsc.mobile.circdesk.databinding.ActivityLoginBinding;
import com.follett.fsc.mobile.circdesk.view.adapter.TabLayoutViewPagerAdapter;
import com.follett.fsc.mobile.circdesk.view.base.BaseActivity;
import com.follett.fsc.mobile.circdesk.view.fragment.CheckinFragment;
import com.follett.fsc.mobile.circdesk.view.fragment.CheckoutFragment;
import com.follett.fsc.mobile.circdesk.viewmodel.CheckinCheckoutViewModel;

public class CheckinCheckoutActivity extends BaseActivity<CheckinCheckoutViewModel> implements View.OnClickListener {

    TabLayoutViewPagerAdapter adapter;
    ActivityLoginBinding activityLoginBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityLoginBinding = putContentView(R.layout.activity_login);

        setTitleBar(getString(R.string.checkinChecoutTitle));
        setBackBtnVisible();
        baseBinding.backBtn.setOnClickListener(this);

        setupViewPager(activityLoginBinding.viewPager);
        activityLoginBinding.tabLayout.setupWithViewPager(activityLoginBinding.viewPager);
        activityLoginBinding.viewPager.setOffscreenPageLimit(activityLoginBinding.tabLayout.getTabCount());
        activityLoginBinding.viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(activityLoginBinding.tabLayout));

    }

    private void setupViewPager(ViewPager checkinCheckoutViewPager) {
        try {
            adapter = new TabLayoutViewPagerAdapter(this.getSupportFragmentManager());
            adapter.addFragment(new CheckinFragment(), getString(R.string.checkin));
            adapter.addFragment(new CheckoutFragment(), getString(R.string.checkout));

            checkinCheckoutViewPager.setAdapter(adapter);
            checkinCheckoutViewPager.setCurrentItem(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backBtn:
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!TextUtils.isEmpty(AppSharedPreferences.getInstance(this).getString(AppSharedPreferences.KEY_SELECTED_BARCODE))) {
            Fragment fragment = adapter.getItem(1);
            ((CheckoutFragment) fragment).getPatronID();
        }
    }
}
