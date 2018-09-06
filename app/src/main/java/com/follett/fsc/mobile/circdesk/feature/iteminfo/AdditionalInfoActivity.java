/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.iteminfo;

import android.os.Bundle;
import android.view.View;

import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.databinding.ActivityMoreDetailsBinding;
import com.follett.fsc.mobile.circdesk.app.base.BaseActivity;
import com.follett.fsc.mobile.circdesk.feature.iteminfo.model.TitleDetails;

public class AdditionalInfoActivity extends BaseActivity<AdditionalInfoViewModel> implements View.OnClickListener {

    ActivityMoreDetailsBinding activityMoreDetailsBinding;
    private TitleDetails titleDetails;
    AdditionalInfoViewModel additionalInfoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMoreDetailsBinding = putContentView(R.layout.activity_more_details);
        setTitleBar(getString(R.string.titleDetails));
        setBackBtnVisible();
        baseBinding.backBtn.setOnClickListener(this);
        if (getIntent().getExtras() != null) {
            titleDetails = (TitleDetails) getIntent().getSerializableExtra("titleMoreDetails");
        }
//        additionalInfoViewModel.mTitleDetails.observe(this, new Observer<TitleDetails>() {
//
//            @Override
//            public void onChanged(@Nullable TitleDetails titleDetails) {
//                updateMoreDetailsUI(titleDetails);
//            }
//        });
        updateMoreDetailsUI(titleDetails);
    }

    private void updateMoreDetailsUI(TitleDetails titleDetails) {
        if (titleDetails != null) {
            activityMoreDetailsBinding.setTitleDetailsViewModel(titleDetails);
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
}
