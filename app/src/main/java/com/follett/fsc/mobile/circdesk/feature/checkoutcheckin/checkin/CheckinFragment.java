/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.checkoutcheckin.checkin;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.follett.fsc.mobile.circdesk.BR;
import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.app.base.BaseFragment;
import com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences;
import com.follett.fsc.mobile.circdesk.data.remote.repository.AppRemoteRepository;
import com.follett.fsc.mobile.circdesk.databinding.FragmentCheckinBinding;
import com.follett.fsc.mobile.circdesk.feature.checkoutcheckin.UpdateUIListener;
import com.follett.fsc.mobile.circdesk.feature.iteminfo.TitleInfoActivity;
import com.follett.fsc.mobile.circdesk.utils.AppUtils;
import com.follett.fsc.mobile.circdesk.utils.FollettLog;

public class CheckinFragment extends BaseFragment<FragmentCheckinBinding, CheckinViewModel> implements UpdateUIListener, View.OnClickListener {

    private CheckinViewModel checkinViewModel;
    private FragmentCheckinBinding fragmentCheckinBinding;
    private CheckinResult checkinResult;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_checkin;
    }

    @Override
    public CheckinViewModel getViewModel() {
        checkinViewModel = new CheckinViewModel(getBaseActivity().getApplication(), new AppRemoteRepository(), this);
        return null;
    }

    @Override
    public int getBindingVariable() {
        return BR.checkoutViewModel;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fragmentCheckinBinding = getViewDataBinding();

        fragmentCheckinBinding.checkinEntryIncludeLayout.patronGoBtn.setOnClickListener(this);
        fragmentCheckinBinding.checkinDetailIncludeLayout.checkedoutInfoBtn.setOnClickListener(this);
    }

    @Override
    public void updateUI(final Object value) {

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (value != null && value instanceof CheckinResult) {
                        checkinResult = (CheckinResult) value;
                        if (checkinResult.getSuccess()) {
                            fragmentCheckinBinding.checkinPatronErrorMsg.setVisibility(View.GONE);
                            fragmentCheckinBinding.checkinDetailIncludeLayout.checkedoutDetailLayout.setVisibility(View.VISIBLE);
                            fragmentCheckinBinding.checkinDetailIncludeLayout.checkedoutLabel.setText(getString(R.string.checkedin));
                            fragmentCheckinBinding.checkinDetailIncludeLayout.checkedoutName.setText(checkinResult.getInfo().getTitle());
                            fragmentCheckinBinding.checkinDetailIncludeLayout.checkedoutType.setText(checkinResult.getInfo().getBarcode());
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
        if (fragmentCheckinBinding != null) {
            fragmentCheckinBinding.checkinDetailIncludeLayout.checkedoutDetailLayout.setVisibility(View.GONE);
            fragmentCheckinBinding.checkinPatronErrorMsg.setVisibility(View.GONE);
            fragmentCheckinBinding.checkinEntryIncludeLayout.patronEntry.setText("");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.patronGoBtn:
                AppUtils.getInstance()
                        .hideKeyBoard(getBaseActivity(), fragmentCheckinBinding.checkinEntryIncludeLayout.patronEntry);
                if (AppUtils.getInstance().isEditTextNotEmpty(fragmentCheckinBinding.checkinEntryIncludeLayout.patronEntry)) {
                    int collectionType = AppSharedPreferences.getInstance(getActivity()).getBoolean(AppSharedPreferences.KEY_IS_LIBRARY_SELECTED) ? 0 : 4;
                    checkinViewModel.getCheckinData(fragmentCheckinBinding.checkinEntryIncludeLayout.patronEntry.getText().toString().trim(), String.valueOf(collectionType));
                } else {
                    AppUtils.getInstance()
                            .showShortToastMessages(getBaseActivity(), getString(R.string.errorPatronEntry));
                }
                break;
            case R.id.checkedoutInfoBtn:
                Intent titleIntent = new Intent(getActivity(), TitleInfoActivity.class);
                titleIntent.putExtra("bibID", checkinResult.getInfo().getBibID().toString().trim());
                startActivity(titleIntent);
                break;
        }
    }
}
