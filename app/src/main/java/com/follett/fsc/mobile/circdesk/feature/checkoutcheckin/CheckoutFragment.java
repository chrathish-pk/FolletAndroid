/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.checkoutcheckin;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;

import com.follett.fsc.mobile.circdesk.BR;
import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.app.GlideApp;
import com.follett.fsc.mobile.circdesk.app.base.BaseFragment;
import com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences;
import com.follett.fsc.mobile.circdesk.data.remote.repository.AppRemoteRepository;
import com.follett.fsc.mobile.circdesk.databinding.FragmentCheckoutBinding;
import com.follett.fsc.mobile.circdesk.feature.iteminfo.TitleInfoActivity;
import com.follett.fsc.mobile.circdesk.utils.AppUtils;
import com.follett.fsc.mobile.circdesk.utils.FollettLog;

public class CheckoutFragment extends BaseFragment<FragmentCheckoutBinding, CheckoutViewModel> implements View.OnClickListener, UpdateUIListener {

    private CheckoutViewModel checkoutViewModel;
    private FragmentCheckoutBinding fragmentCheckoutBinding;
    private ScanPatron scanPatron = null;
    private CheckoutResult checkoutResult = null;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_checkout;
    }

    @Override
    public CheckoutViewModel getViewModel() {
        checkoutViewModel = new CheckoutViewModel(getBaseActivity().getApplication(), new AppRemoteRepository(), this);
        return checkoutViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.checkoutViewModel;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fragmentCheckoutBinding = getViewDataBinding();

        fragmentCheckoutBinding.patronEntryIncludeLayout.patronGoBtn.setOnClickListener(this);
        fragmentCheckoutBinding.patronDetailIncludeLayout.checkoutCloseBtn.setOnClickListener(this);
        fragmentCheckoutBinding.checkoutDetailIncludeLayout.checkedoutInfoBtn.setOnClickListener(this);


        if (!TextUtils.isEmpty(AppSharedPreferences.getInstance(getActivity()).getString(AppSharedPreferences.KEY_SELECTED_BARCODE))) {
            getPatronID();
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.patronGoBtn) {
            AppUtils.getInstance()
                    .hideKeyBoard(getBaseActivity(), fragmentCheckoutBinding.patronEntryIncludeLayout.patronEntry);
            if (AppUtils.getInstance().isEditTextNotEmpty(fragmentCheckoutBinding.patronEntryIncludeLayout.patronEntry)) {
                String barcode = AppSharedPreferences.getInstance(getActivity()).getString(AppSharedPreferences.KEY_SELECTED_BARCODE);
                if (TextUtils.isEmpty(barcode)) {
                    checkoutViewModel.getScanPatron(fragmentCheckoutBinding.patronEntryIncludeLayout.patronEntry.getText().toString().trim());
                } else {
                    checkoutViewModel.getCheckoutResult(AppSharedPreferences.getInstance(getActivity()).getString(AppSharedPreferences.KEY_PATRON_ID),
                            fragmentCheckoutBinding.patronEntryIncludeLayout.patronEntry.getText().toString().trim());
                }
            } else {
                AppUtils.getInstance()
                        .showShortToastMessages(getBaseActivity(), getString(R.string.errorPatronEntry));
            }
        } else if (v.getId() == R.id.checkoutCloseBtn && fragmentCheckoutBinding.patronDetailIncludeLayout.patronDetailLayout.getVisibility() == View.VISIBLE) {
//            AppSharedPreferences.getInstance(getActivity()).setString(AppSharedPreferences.KEY_BARCODE, null);
            AppSharedPreferences.getInstance(getActivity()).setString(AppSharedPreferences.KEY_PATRON_ID, null);
            AppSharedPreferences.getInstance(getActivity()).setString(AppSharedPreferences.KEY_SELECTED_BARCODE, null);
            fragmentCheckoutBinding.patronDetailIncludeLayout.patronDetailLayout.setVisibility(View.GONE);
            fragmentCheckoutBinding.checkoutDetailIncludeLayout.checkedoutDetailLayout.setVisibility(View.GONE);
            fragmentCheckoutBinding.checkoutPatronErrorMsg.setVisibility(View.GONE);
            fragmentCheckoutBinding.patronEntryIncludeLayout.patronEntry.setText("");
        } else if (v.getId() == R.id.checkedoutInfoBtn) {

            Intent titleIntent = new Intent(getActivity(), TitleInfoActivity.class);
            titleIntent.putExtra("bibID", checkoutResult.getInfo().getBibID());
            startActivity(titleIntent);

        }
    }

    public void getPatronID() {
        if (checkoutViewModel != null) {
            String selectedBarcode = AppSharedPreferences.getInstance(getActivity()).getString(AppSharedPreferences.KEY_SELECTED_BARCODE);
            if (TextUtils.isEmpty(selectedBarcode))
                checkoutViewModel.getScanPatron(fragmentCheckoutBinding.patronEntryIncludeLayout.patronEntry.getText().toString().trim());
            else
                checkoutViewModel.getScanPatron(selectedBarcode);
        }
    }


    @Override
    public void updateUI(Object result) {
        final Object value = result;
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (value != null && value instanceof ScanPatron) {
                    scanPatron = (ScanPatron) value;
                    if (scanPatron.getSuccess().equalsIgnoreCase("true")) {
                        if (scanPatron.getPatronList() != null) {
                            navigateToPatronListScreen(scanPatron);
                        } else {
                            bindPatronResult();
                        }
                    } else {
                        AppSharedPreferences.getInstance(getActivity()).setString(AppSharedPreferences.KEY_SELECTED_BARCODE, null);
                        updatePatronErrorMsg(scanPatron);
                    }
                } else if (value != null && value instanceof CheckoutResult) {
                    checkoutResult = (CheckoutResult) value;
                    if (checkoutResult.getSuccess()) {
                        bindCheckoutResult(checkoutResult);
                    } else {
                        updateCheckoutErrorMsg(checkoutResult);
                    }
                }
            }
        });

    }

    private void updateCheckoutErrorMsg(CheckoutResult checkoutResult) {

        if (checkoutResult != null) {
            fragmentCheckoutBinding.checkoutPatronErrorMsg.setVisibility(View.VISIBLE);
            if (checkoutResult.getMessages().size() > 1) {
                String errorMsg = checkoutResult.getMessages().get(0).getMessage() + "\n" + checkoutResult.getMessages().get(1).getMessage();
                fragmentCheckoutBinding.checkoutPatronErrorMsg.setText(errorMsg);
            } else {
                fragmentCheckoutBinding.checkoutPatronErrorMsg.setText(checkoutResult.getMessages().get(0).getMessage());
            }
        }

    }

    private void bindCheckoutResult(CheckoutResult checkoutResult) {
        try {
            if (fragmentCheckoutBinding.checkoutPatronErrorMsg.getVisibility() == View.VISIBLE)
                fragmentCheckoutBinding.checkoutPatronErrorMsg.setVisibility(View.GONE);
            fragmentCheckoutBinding.checkoutDetailIncludeLayout.checkedoutDetailLayout.setVisibility(View.VISIBLE);
            fragmentCheckoutBinding.checkoutDetailIncludeLayout.checkedoutName.setText(checkoutResult.getInfo().getTitle());
            fragmentCheckoutBinding.checkoutDetailIncludeLayout.checkedoutDue.setText(checkoutResult.getInfo().getDueDate());
            fragmentCheckoutBinding.checkoutDetailIncludeLayout.checkedoutType.setText(checkoutResult.getInfo().getBarcode());
        } catch (Exception e) {
            FollettLog.e(getString(R.string.error), e.getMessage());
        }
    }

    private void updatePatronErrorMsg(ScanPatron scanPatron) {
        try {
            if (fragmentCheckoutBinding.patronDetailIncludeLayout.patronDetailLayout.getVisibility() == View.VISIBLE)
                fragmentCheckoutBinding.patronDetailIncludeLayout.patronDetailLayout.setVisibility(View.GONE);
            if (scanPatron != null) {
                fragmentCheckoutBinding.checkoutPatronErrorMsg.setVisibility(View.VISIBLE);
                fragmentCheckoutBinding.checkoutPatronErrorMsg.setText(scanPatron.getMessages().get(0).getMessage());
            } else {
                FollettLog.e(getString(R.string.scanPatronError), getString(R.string.noDataFound));
            }
        } catch (Exception e) {
            FollettLog.e(getString(R.string.error), e.getMessage());
        }
    }

    public void bindPatronResult() {
        if (scanPatron != null) {
            fragmentCheckoutBinding.patronDetailIncludeLayout.patronDetailLayout.setVisibility(View.VISIBLE);
            fragmentCheckoutBinding.checkoutDetailIncludeLayout.checkedoutDetailLayout.setVisibility(View.GONE);
            fragmentCheckoutBinding.patronEntryIncludeLayout.patronEntry.setText("");
            if (fragmentCheckoutBinding.checkoutPatronErrorMsg.getVisibility() == View.VISIBLE)
                fragmentCheckoutBinding.checkoutPatronErrorMsg.setVisibility(View.GONE);
            if (AppSharedPreferences.getInstance(getActivity()).getBoolean(AppSharedPreferences.KEY_IS_LIBRARY_SELECTED)) {
                String checoutValue = getString(R.string.checkoutLabel) + scanPatron.getLibraryCheckouts();
                fragmentCheckoutBinding.patronDetailIncludeLayout.checkedOutCount.setText(checoutValue);
                String overdueValue = getString(R.string.overdueLabel) + scanPatron.getLibraryOverdues();
                fragmentCheckoutBinding.patronDetailIncludeLayout.overdue.setText(overdueValue);
            } else {
                String checoutValue = getString(R.string.checkoutLabel) + scanPatron.getAssetCheckouts();
                fragmentCheckoutBinding.patronDetailIncludeLayout.checkedOutCount.setText(checoutValue);
                String overdueValue = getString(R.string.overdueLabel) + scanPatron.getAssetOverdues();
                fragmentCheckoutBinding.patronDetailIncludeLayout.overdue.setText(overdueValue);
            }
            fragmentCheckoutBinding.patronDetailIncludeLayout.checkoutPatronName.setText(scanPatron.getLastFirstMiddleName());
            fragmentCheckoutBinding.patronDetailIncludeLayout.checkoutPatronID.setText(scanPatron.getPatronID());
            fragmentCheckoutBinding.patronDetailIncludeLayout.checkoutPatronType.setText(scanPatron.getPatronType());

            GlideApp.with(this)
                    .load(scanPatron.getPatronPictureFileName())
                    .placeholder(R.drawable.inventory)
                    .into(fragmentCheckoutBinding.patronDetailIncludeLayout.checkoutPatronImg);
        } else {
            fragmentCheckoutBinding.patronDetailIncludeLayout.patronDetailLayout.setVisibility(View.GONE);
        }
    }


    private void navigateToPatronListScreen(ScanPatron scanPatron) {
        Intent patronListIntent = new Intent(getActivity(), PatronListActivity.class);
        patronListIntent.putExtra(getString(R.string.scanPatron), scanPatron);
        startActivity(patronListIntent);
    }
}