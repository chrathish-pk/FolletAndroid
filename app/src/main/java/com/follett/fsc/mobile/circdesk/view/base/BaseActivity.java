/*
 * Copyright (c) 2018 Follett. All rights reserved.
 */
package com.follett.fsc.mobile.circdesk.view.base;

import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.databinding.ActivityBaseBinding;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public abstract class BaseActivity<V extends BaseViewModel> extends AppCompatActivity {
    
    private TextView titleBarTextView;
    
    private ActivityBaseBinding baseBinding;
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        baseBinding = DataBindingUtil.setContentView(this, R.layout.activity_base);
        initToolBar();
    }
    
    protected <T extends ViewDataBinding> T putContentView(@LayoutRes int resId) {
        return DataBindingUtil.inflate(getLayoutInflater(), resId, baseBinding.baseContainer, true);
    }
    
    private void initToolBar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        titleBarTextView = findViewById(R.id.titleBar);
        setSupportActionBar(toolbar);
    }
    
    protected void setTitleBar(String titleBarText) {
        titleBarTextView.setText(titleBarText);
    }
}
