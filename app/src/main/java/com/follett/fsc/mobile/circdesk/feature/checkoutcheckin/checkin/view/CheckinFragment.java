/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.checkoutcheckin.checkin.view;

import com.follett.fsc.mobile.circdesk.BR;
import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.app.base.BaseFragment;
import com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences;
import com.follett.fsc.mobile.circdesk.databinding.FragmentCheckinBinding;
import com.follett.fsc.mobile.circdesk.feature.checkoutcheckin.UpdateUIListener;
import com.follett.fsc.mobile.circdesk.feature.checkoutcheckin.checkin.viewmodel.CheckinViewModel;
import com.follett.fsc.mobile.circdesk.feature.checkoutcheckin.model.CheckinResult;
import com.follett.fsc.mobile.circdesk.feature.iteminfo.view.TitleInfoActivity;
import com.follett.fsc.mobile.circdesk.utils.AppUtils;
import com.follett.fsc.mobile.circdesk.utils.FollettLog;
import com.honeywell.aidc.BarcodeFailureEvent;
import com.honeywell.aidc.BarcodeReadEvent;
import com.honeywell.aidc.BarcodeReader;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CompoundButton;

public class CheckinFragment extends BaseFragment<FragmentCheckinBinding, CheckinViewModel> implements UpdateUIListener, View.OnClickListener, CompoundButton.OnCheckedChangeListener, BarcodeReader.BarcodeListener {

    private CheckinViewModel checkinViewModel;
    private FragmentCheckinBinding fragmentCheckinBinding;
    private CheckinResult checkinResult;
    private boolean isLibraryUse;
    private BarcodeReader mBarcodeReader;


    @Override
    public int getLayoutId() {
        return R.layout.fragment_checkin;
    }

    @Override
    public CheckinViewModel getViewModel() {
        if (getBaseApplication() == null) {
            return null;
        }
        checkinViewModel = new CheckinViewModel(getBaseApplication()
                , this);
        return checkinViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final Activity activity = getBaseActivity();
        if (activity == null) {
            return;
        }
        fragmentCheckinBinding = getViewDataBinding();

        if (AppSharedPreferences.getInstance().getBoolean(AppSharedPreferences.KEY_IS_LIBRARY_SELECTED))
            fragmentCheckinBinding.checkinEntryIncludeLayout.checkinLibRecordSwitch.setVisibility(View.VISIBLE);
        else
            fragmentCheckinBinding.checkinEntryIncludeLayout.checkinLibRecordSwitch.setVisibility(View.INVISIBLE);

        fragmentCheckinBinding.checkinEntryIncludeLayout.patronEntry.setHint(getString(R.string.enterBarcode));
        fragmentCheckinBinding.checkinEntryIncludeLayout.patronGoBtn.setOnClickListener(this);
        fragmentCheckinBinding.checkinDetailIncludeLayout.checkedoutInfoBtn.setOnClickListener(this);
        fragmentCheckinBinding.checkinEntryIncludeLayout.checkinLibRecordSwitch.setOnCheckedChangeListener(this);
        if (AppUtils.brandName(mActivity)) {
            mBarcodeReader.addBarcodeListener(this);
            fragmentCheckinBinding.checkinEntryIncludeLayout.scanButton.setOnClickListener(this);

        }

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBarcodeReader = mActivity.getBarcodeReader();
    }

    @Override
    public void updateUI(final Object value) {
        Activity activity = getBaseActivity();
        if (activity == null) {
            return;
        }

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (value instanceof CheckinResult) {
                        checkinResult = (CheckinResult) value;
                        if (checkinResult.getSuccess()) {
                            fragmentCheckinBinding.checkinPatronErrorMsg.setVisibility(View.GONE);
                            fragmentCheckinBinding.checkinDetailIncludeLayout.checkedoutDetailLayout.setVisibility(View.VISIBLE);
                            fragmentCheckinBinding.setCheckinResult(checkinResult);
                            fragmentCheckinBinding.checkinDetailIncludeLayout.checkedoutLabel.setText(getString(R.string.checkedin));
                        } else {
                            fragmentCheckinBinding.checkinPatronErrorMsg.setVisibility(View.VISIBLE);
                            fragmentCheckinBinding.checkinDetailIncludeLayout.checkedoutDetailLayout.setVisibility(View.GONE);
                            fragmentCheckinBinding.checkinPatronErrorMsg.setText(checkinResult.getMessages().get(0).getMessage());

                        }
                    }
                } catch (Exception e) {
                    FollettLog.e(getString(R.string.error), e.getMessage());
                }
            }
        });
    }

    public void bindCheckinResult() {

        try {
            if (AppSharedPreferences.getInstance().getBoolean(AppSharedPreferences.KEY_IS_LIBRARY_SELECTED))
                fragmentCheckinBinding.checkinEntryIncludeLayout.checkinLibRecordSwitch.setVisibility(View.VISIBLE);
            else
                fragmentCheckinBinding.checkinEntryIncludeLayout.checkinLibRecordSwitch.setVisibility(View.INVISIBLE);

            fragmentCheckinBinding.checkinDetailIncludeLayout.checkedoutDetailLayout.setVisibility(View.GONE);
            fragmentCheckinBinding.checkinPatronErrorMsg.setVisibility(View.GONE);
            fragmentCheckinBinding.checkinEntryIncludeLayout.patronEntry.setText("");
        } catch (Exception e) {
            FollettLog.e("error", e.getMessage());
        }
    }


    @Override
    public void onClick(View v) {

        Activity activity = getBaseActivity();
        if (activity == null) {
            return;
        }

        switch (v.getId()) {
            case R.id.patronGoBtn:
                  getCheckInItem();
                break;
            case R.id.scanButton:
                checkinViewModel.triggerSoftwareScanner(mBarcodeReader);
                break;
            case R.id.checkedoutInfoBtn:
                if (!isNetworkConnected()) {
                    AppUtils.getInstance()
                            .showNoInternetAlertDialog(activity);
                    return;
                }
                Intent titleIntent = new Intent(activity, TitleInfoActivity.class);
                titleIntent.putExtra("bibID", checkinResult.getInfo().getBibID().toString().trim());
                startActivity(titleIntent);
                break;
            default:
                break;
        }
    }

    private void getCheckInItem() {

        AppUtils.getInstance()
                .hideKeyBoard(mActivity, fragmentCheckinBinding.checkinEntryIncludeLayout.patronEntry);
        if (AppUtils.getInstance().isEditTextNotEmpty(fragmentCheckinBinding.checkinEntryIncludeLayout.patronEntry)) {
            if (!isNetworkConnected()) {
                AppUtils.getInstance()
                        .showNoInternetAlertDialog(mActivity);
                return;
            }
            int collectionType = AppSharedPreferences.getInstance().getBoolean(AppSharedPreferences.KEY_IS_LIBRARY_SELECTED) ? 0 : 4;
            checkinViewModel.getCheckinData(fragmentCheckinBinding.checkinEntryIncludeLayout.patronEntry.getText().toString().trim(), String.valueOf(collectionType), isLibraryUse);
        } else {
            AppUtils.getInstance()
                    .showShortToastMessages(mActivity, getString(R.string.errorPatronEntry));
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        isLibraryUse = isChecked;
    }

    @Override
    public void onBarcodeEvent(final BarcodeReadEvent event) {
        checkinViewModel.onBarcodeFailureEvent(mBarcodeReader);
        getBaseActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (event != null) {
                    //fragmentCheckinBinding.p.setVisibility(View.GONE);
                    String barcode = event.getBarcodeData();
                    fragmentCheckinBinding.checkinEntryIncludeLayout.patronEntry.setText(barcode);
                    getCheckInItem();
                }
            }
        });
    }

    @Override
    public void onFailureEvent(BarcodeFailureEvent barcodeFailureEvent) {
        checkinViewModel.onBarcodeFailureEvent(mBarcodeReader);
    }

    @Override
    public void onResume() {
        super.onResume();
        checkinViewModel.onResumeScanner(mBarcodeReader);
    }

    @Override
    public void onPause() {
        super.onPause();
        checkinViewModel.onPauseScanner(mBarcodeReader);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mBarcodeReader != null) {
            mBarcodeReader.removeBarcodeListener(this);
        }
    }
}
