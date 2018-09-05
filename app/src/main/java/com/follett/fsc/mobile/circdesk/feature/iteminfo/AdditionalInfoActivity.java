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
        updateMoreDetailsUI(titleDetails);
    }

    private void updateMoreDetailsUI(TitleDetails titleDetails) {

        activityMoreDetailsBinding.itemTitleName.setText(titleDetails.getTitle());
        activityMoreDetailsBinding.itemAuthor.setText(titleDetails.getResponsibility());
        activityMoreDetailsBinding.itemTitleBy.setText(titleDetails.getAdditionalInfoRecord().getTitlesBy());
        activityMoreDetailsBinding.itemPublished.setText(titleDetails.getAdditionalInfoRecord().getPublisher());
        activityMoreDetailsBinding.itemEdited.setText(titleDetails.getAdditionalInfoRecord().getEdition());
        activityMoreDetailsBinding.itemFormat.setText(titleDetails.getAdditionalInfoRecord().getFormat());
        activityMoreDetailsBinding.itemISBN.setText(titleDetails.getAdditionalInfoRecord().getIsbnList().get(0));
        activityMoreDetailsBinding.itemTarget.setText(titleDetails.getAdditionalInfoRecord().getTargetAudienceList().get(0));
        activityMoreDetailsBinding.itemAccelerated.setText(getString(R.string.item_quiz_no)+titleDetails.getQuizInfoRecord().getQuizList().get(0).getQuizNumber()+getString(R.string.item_points)+titleDetails.getQuizInfoRecord().getQuizList().get(0).getPoints());
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
