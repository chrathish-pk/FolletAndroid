/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.iteminfo.view;

import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.app.base.BaseActivity;
import com.follett.fsc.mobile.circdesk.app.base.ScannerViewModel;
import com.follett.fsc.mobile.circdesk.databinding.ActivityMoreDetailsBinding;
import com.follett.fsc.mobile.circdesk.feature.iteminfo.model.TitleDetails;
import com.follett.fsc.mobile.circdesk.feature.iteminfo.viewmodel.AdditionalInfoViewModel;

import android.os.Bundle;
import android.view.View;

public class AdditionalInfoActivity extends BaseActivity<ScannerViewModel> implements View.OnClickListener {

    ActivityMoreDetailsBinding activityMoreDetailsBinding;
    private TitleDetails titleDetails;
    AdditionalInfoViewModel additionalInfoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMoreDetailsBinding = putContentView(R.layout.activity_more_details);
        setTitleBar(getString(R.string.additional_info_title));
        setBackBtnVisible();
        baseBinding.backBtn.setOnClickListener(this);

        if (getIntent().getExtras() != null) {
            titleDetails = getIntent().getParcelableExtra("titleMoreDetails");
        }
        updateMoreDetailsUI(titleDetails);
    }

    private void updateMoreDetailsUI(TitleDetails titleDetails) {
        if (titleDetails != null) {
            activityMoreDetailsBinding.setTitleDetailsViewModel(titleDetails);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.backBtn) {
            finish();
        }

    }
}
