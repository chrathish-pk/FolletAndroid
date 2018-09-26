/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.app.base;

import com.follett.fsc.mobile.circdesk.R;

import android.os.Bundle;
import android.view.View;

public class LegalActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        putContentView(R.layout.activity_legal);
        setTitleBar(getString(R.string.legalTitle));
        baseBinding.backBtn.setOnClickListener(this);
        setBackBtnVisible();
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

}
