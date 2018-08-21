/*
 * Copyright (c) 2018 Follett. All rights reserved.
 */
package com.follett.fsc.mobile.circdesk.view.activity;
        
        import com.follett.fsc.mobile.circdesk.R;
        import com.follett.fsc.mobile.circdesk.databinding.ActivityLoginBinding;
        import com.follett.fsc.mobile.circdesk.view.adapter.SiteViewPagerAdapter;
        import com.follett.fsc.mobile.circdesk.view.base.BaseActivity;
        import com.follett.fsc.mobile.circdesk.viewmodel.LoginViewModel;
        
        import android.os.Bundle;
        import android.support.design.widget.TabLayout;

public class LoginActivity extends BaseActivity<LoginViewModel> {
    
    private ActivityLoginBinding loginBinding;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginBinding = putContentView(R.layout.activity_login);
        inItView();
    }
    
    private void inItView() {
        
        setTitleBar(getString(R.string.connect_your_school_label));
        final TabLayout tabLayout = loginBinding.tabLayout;
        tabLayout.addTab(tabLayout.newTab()
                .setText(getString(R.string.basic_label)));
        tabLayout.addTab(tabLayout.newTab()
                .setText(getString(R.string.advanced_label)));
        
        loginBinding.viewPager.setOffscreenPageLimit(tabLayout.getTabCount());
        loginBinding.viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(loginBinding.tabLayout));
        SiteViewPagerAdapter adapter = new SiteViewPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        loginBinding.viewPager.setAdapter(adapter);
    }
}

