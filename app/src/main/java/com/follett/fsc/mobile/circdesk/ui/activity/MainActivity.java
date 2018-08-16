/*
 * Copyright (c) 2018 Follett. All rights reserved.
 */
package com.follett.fsc.mobile.circdesk.ui.activity;

import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.ui.base.BaseActivity;
import com.follett.fsc.mobile.circdesk.utils.AppSharedPreferences;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends BaseActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
