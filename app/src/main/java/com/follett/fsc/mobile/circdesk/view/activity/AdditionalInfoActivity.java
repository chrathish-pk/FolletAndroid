/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.view.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.follett.fsc.mobile.circdesk.data.model.AdditionalInfo.TitleDetails;
import com.follett.fsc.mobile.circdesk.data.remote.repository.AppRemoteRepository;
import com.follett.fsc.mobile.circdesk.databinding.ActivityTitleDetailsBinding;

import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.interfaces.AdditionalInfoListener;
import com.follett.fsc.mobile.circdesk.utils.FollettLog;
import com.follett.fsc.mobile.circdesk.view.base.BaseActivity;
import com.follett.fsc.mobile.circdesk.viewmodel.AdditionalInfoViewModel;

public class AdditionalInfoActivity extends BaseActivity<AdditionalInfoViewModel> implements View.OnClickListener,AdditionalInfoListener {

    ActivityTitleDetailsBinding activityTitleDetailsBinding;
    TitleDetails additionalInfoDetails;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityTitleDetailsBinding = putContentView(R.layout.activity_title_details);
        setTitleBar(getString(R.string.titleDetails));
        setBackBtnVisible();
        baseBinding.backBtn.setOnClickListener(this);
        AdditionalInfoViewModel additionalInfoViewModel = new AdditionalInfoViewModel(this.getApplication(), new AppRemoteRepository(),this);
        additionalInfoViewModel.getTitleDetails();

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backBtn:
                finish();
                break;
            case R.id.additional_info_btn:
                Intent moreDetailsIntent = new Intent(this, AdditionalMoreInfoActivity.class);
                moreDetailsIntent.putExtra("titleMoreDetails", additionalInfoDetails);
                startActivity(moreDetailsIntent);
                break;
            default:
                break;
        }
    }

    @Override
    public void updateTitleDetails(final TitleDetails titleDetails) {

        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                if (titleDetails != null) {
                    additionalInfoDetails = titleDetails;
                    FollettLog.i("TAG","Title"+titleDetails.getTitle());

                    activityTitleDetailsBinding.itemTitleName.setText(titleDetails.getTitle());
                    activityTitleDetailsBinding.itemAuthor.setText(titleDetails.getResponsibility());
                    activityTitleDetailsBinding.itemInfo.setText("Call#"+titleDetails.getCallNumber());
                    activityTitleDetailsBinding.itemRatingBar.setRating(Float.parseFloat(titleDetails.getReviewInfoRecord().getReviewAverage()));
                    if(titleDetails.getStatus().equals("1"))
                    {
                        activityTitleDetailsBinding.itemStatus.setText("Status : IN");
                    }
                    else
                    {
                        activityTitleDetailsBinding.itemStatus.setText("Status : OUT");
                    }
                    activityTitleDetailsBinding.itemDescription.setText(titleDetails.getSummaryList().getSummary());
                    activityTitleDetailsBinding.itemAvailability.setText(titleDetails.getAvailableLocal()+" of "+titleDetails.getTotalLocal()+" Available");
                    activityTitleDetailsBinding.additionalInfoBtn.setOnClickListener(AdditionalInfoActivity.this);
                    Glide.with(AdditionalInfoActivity.this)
                            .load(AppRemoteRepository.BASE_URL+titleDetails.getContentImageLink())
                            .into(activityTitleDetailsBinding.itemImg);
                }
            }
        });
    }
}
