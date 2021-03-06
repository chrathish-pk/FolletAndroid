/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.iteminfo.view;

import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.app.base.BaseActivity;
import com.follett.fsc.mobile.circdesk.app.base.ScannerViewModel;
import com.follett.fsc.mobile.circdesk.app.base.LegalActivity;
import com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences;
import com.follett.fsc.mobile.circdesk.databinding.ActivityTitleDetailsBinding;
import com.follett.fsc.mobile.circdesk.feature.iteminfo.model.TitleDetails;
import com.follett.fsc.mobile.circdesk.feature.iteminfo.viewmodel.AdditionalInfoViewModel;
import com.follett.fsc.mobile.circdesk.utils.AppUtils;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

public class TitleInfoActivity extends BaseActivity<ScannerViewModel> implements View.OnClickListener, AdditionalInfoListener {

    ActivityTitleDetailsBinding activityTitleDetailsBinding;
    TitleDetails additionalInfoDetails;
    AdditionalInfoViewModel additionalInfoViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityTitleDetailsBinding = putContentView(R.layout.activity_title_details);
        setTitleBar(getString(R.string.titleDetails));
        setBackBtnVisible();
        baseBinding.backBtn.setOnClickListener(this);
        activityTitleDetailsBinding.topLayout.setVisibility(View.GONE);
        activityTitleDetailsBinding.descriptionLinearLayout.setVisibility(View.GONE);
        activityTitleDetailsBinding.availablityLayout.setVisibility(View.GONE);
        additionalInfoViewModel = new AdditionalInfoViewModel(this.getApplication(), this);

        if (getIntent() != null) {
            String bibID = getIntent().getStringExtra("bibID");
            additionalInfoViewModel.getTitleDetails(bibID);
        }
        additionalInfoViewModel.mTitleDetails.observe(this, new Observer<TitleDetails>() {
            @Override
            public void onChanged(@Nullable TitleDetails titleDetails) {
                updateTitleDetails(titleDetails);
            }
        });
        additionalInfoViewModel.getErrorMessage()
                .observe(this, new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        AppUtils.getInstance()
                                .showAlertDialog(TitleInfoActivity.this, null, String.valueOf(o));
                    }
                });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backBtn:
                finish();
                break;
            case R.id.additional_info_btn:
                Intent moreDetailsIntent = new Intent(this, AdditionalInfoActivity.class);
                moreDetailsIntent.putExtra("titleMoreDetails",additionalInfoDetails);
                startActivity(moreDetailsIntent);

                break;
            case R.id.legalBtn:
                startActivity(new Intent(TitleInfoActivity.this, LegalActivity.class));
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
                    activityTitleDetailsBinding.topLayout.setVisibility(View.VISIBLE);
                    activityTitleDetailsBinding.descriptionLinearLayout.setVisibility(View.VISIBLE);
                    activityTitleDetailsBinding.availablityLayout.setVisibility(View.VISIBLE);
                    activityTitleDetailsBinding.additionalInfoBtn.setOnClickListener(TitleInfoActivity.this);
                    activityTitleDetailsBinding.progressBar.setVisibility(View.GONE);
                    String siteText = String.format(getApplicationContext().getResources().getString(R.string.site_info),
                            AppSharedPreferences.getInstance().getString(AppSharedPreferences.KEY_SITE_NAME));
                    activityTitleDetailsBinding.itemAvailabilitySite.setText(siteText);
                    activityTitleDetailsBinding.itemRatingBar.setRating(Float.parseFloat(titleDetails.getReviewInfoRecord().getReviewAverage()));
                    activityTitleDetailsBinding.setTitleDetailsViewModel(additionalInfoDetails);
                }
            }
        });
    }
}
