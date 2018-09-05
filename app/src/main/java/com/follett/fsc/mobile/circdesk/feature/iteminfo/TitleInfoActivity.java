/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.iteminfo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.bumptech.glide.Glide;
import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.app.base.BaseActivity;
import com.follett.fsc.mobile.circdesk.data.remote.repository.AppRemoteRepository;
import com.follett.fsc.mobile.circdesk.databinding.ActivityTitleDetailsBinding;
import com.follett.fsc.mobile.circdesk.feature.iteminfo.model.TitleDetails;
import com.follett.fsc.mobile.circdesk.utils.FollettLog;

public class TitleInfoActivity extends BaseActivity<AdditionalInfoViewModel> implements View.OnClickListener, AdditionalInfoListener {

    ActivityTitleDetailsBinding activityTitleDetailsBinding;
    TitleDetails additionalInfoDetails;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityTitleDetailsBinding = putContentView(R.layout.activity_title_details);
        setTitleBar(getString(R.string.titleDetails));
        setBackBtnVisible();
        baseBinding.backBtn.setOnClickListener(this);
        AdditionalInfoViewModel additionalInfoViewModel = new AdditionalInfoViewModel(this.getApplication(), new AppRemoteRepository(), this);

        if (getIntent() != null) {
            String bibID = getIntent().getStringExtra("bibID");
            additionalInfoViewModel.getTitleDetails(bibID);
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backBtn:
                finish();
                break;
            case R.id.additional_info_btn:
                Intent moreDetailsIntent = new Intent(this, AdditionalInfoActivity.class);
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
                    FollettLog.i("TAG", "Title" + titleDetails.getTitle());

                    activityTitleDetailsBinding.itemTitleName.setText(titleDetails.getTitle());
                    activityTitleDetailsBinding.itemAuthor.setText(titleDetails.getResponsibility());
                    activityTitleDetailsBinding.itemInfo.setText(getString(R.string.item_callno)+titleDetails.getCallNumber());
                    activityTitleDetailsBinding.itemRatingBar.setRating(Float.parseFloat(titleDetails.getReviewInfoRecord().getReviewAverage()));
                    if(titleDetails.getStatus().equals(getString(R.string.one)))
                    {
                        activityTitleDetailsBinding.itemStatus.setText(getString(R.string.item_status_in));
                    }
                    else
                    {
                        activityTitleDetailsBinding.itemStatus.setText(getString(R.string.item_status_out));
                    }
                    activityTitleDetailsBinding.itemDescription.setText(titleDetails.getSummaryList().get(0));
                    activityTitleDetailsBinding.itemAvailability.setText(titleDetails.getAvailableLocal()+" "+getString(R.string.of)+" "+titleDetails.getTotalLocal()+" "+getString(R.string.available_txt));
                    activityTitleDetailsBinding.itemDescription.setText(titleDetails.getSummaryList().get(0));
                    activityTitleDetailsBinding.itemAvailability.setText(titleDetails.getAvailableLocal() + " of " + titleDetails.getTotalLocal() + " Available");
                    activityTitleDetailsBinding.additionalInfoBtn.setOnClickListener(TitleInfoActivity.this);
                    Glide.with(TitleInfoActivity.this)
                            .load(AppRemoteRepository.BASE_URL + titleDetails.getContentImageLink())
                            .into(activityTitleDetailsBinding.itemImg);
                }
            }
        });
    }
}
