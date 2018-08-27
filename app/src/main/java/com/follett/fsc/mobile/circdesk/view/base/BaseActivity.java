/*
 * Copyright (c) 2018 Follett. All rights reserved.
 */
package com.follett.fsc.mobile.circdesk.view.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.databinding.ActivityBaseBinding;

public abstract class BaseActivity<V extends BaseViewModel> extends AppCompatActivity {

    private TextView titleBarTextView;
    private ImageView backBtn;

    public ActivityBaseBinding baseBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        baseBinding = DataBindingUtil.setContentView(this, R.layout.activity_base);

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

}
