/*
 * Copyright (c) 2018 Follett. All rights reserved.
 */
package com.follett.fsc.mobile.circdesk.view.activity;

import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.databinding.ActivityLoginBinding;
import com.follett.fsc.mobile.circdesk.view.base.BaseActivity;
import com.follett.fsc.mobile.circdesk.viewmodel.LoginViewModel;

import android.os.Bundle;

public class LoginActivity extends BaseActivity<LoginViewModel> {
    
    ActivityLoginBinding loginBinding;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginBinding = putContentView(R.layout.activity_login);
        setTitleBar(getString(R.string.connect_your_school_label));
    }
}

