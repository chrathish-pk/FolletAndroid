/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.checkoutcheckin.checkout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;

import com.follett.fsc.mobile.circdesk.BR;
import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.app.AlertDialogListener;
import com.follett.fsc.mobile.circdesk.app.base.BaseFragment;
import com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences;
import com.follett.fsc.mobile.circdesk.databinding.FragmentCheckoutBinding;
import com.follett.fsc.mobile.circdesk.feature.checkoutcheckin.UpdateUIListener;
import com.follett.fsc.mobile.circdesk.feature.checkoutcheckin.model.CheckoutResult;
import com.follett.fsc.mobile.circdesk.feature.checkoutcheckin.model.ScanPatron;
import com.follett.fsc.mobile.circdesk.feature.iteminfo.TitleInfoActivity;
import com.follett.fsc.mobile.circdesk.utils.AppUtils;
import com.follett.fsc.mobile.circdesk.utils.FollettLog;

public class CheckoutFragment extends BaseFragment<FragmentCheckoutBinding, CheckoutViewModel> implements View.OnClickListener, UpdateUIListener, AlertDialogListener {

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
        if (getBaseApplication() == null) {
            return null;
        }
        checkoutViewModel = new CheckoutViewModel(getBaseApplication()
                , this);
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

        fragmentCheckoutBinding.patronEntryIncludeLayout.checkinLibRecordSwitch.setVisibility(View.GONE);


        if (!TextUtils.isEmpty(AppSharedPreferences.getInstance().getString(AppSharedPreferences.KEY_SELECTED_BARCODE))) {
            getPatronID();
        }

        /*checkoutViewModel.scanPatronMutableLiveData.observeForever(new Observer<ScanPatron>() {
            @Override
            public void onChanged(@Nullable ScanPatron scanPatronValue) {
                scanPatron = scanPatronValue;
                scanPatronUpdate(scanPatron);
            }
        });

        checkoutViewModel.checkoutResultMutableLiveData.observeForever(new Observer<CheckoutResult>() {
            @Override
            public void onChanged(@Nullable CheckoutResult checkoutResultValue) {
                checkoutResult = checkoutResultValue;
                if (checkoutResult.getSuccess()) {
                    FollettLog.e("sucess", "checkout value");
                    bindCheckoutResult(checkoutResult);
                } else {
                    FollettLog.e("failure", "checkout value");

                    updateCheckoutErrorMsg(checkoutResult);
                }
            }
        });*/
    }

    @Override
    public void onClick(View v) {
    
        Activity activity = getBaseActivity();
        if (activity == null) {
            return;
        }
        
        if (v.getId() == R.id.patronGoBtn) {
            AppUtils.getInstance()
                    .hideKeyBoard(activity, fragmentCheckoutBinding.patronEntryIncludeLayout.patronEntry);
            if (AppUtils.getInstance().isEditTextNotEmpty(fragmentCheckoutBinding.patronEntryIncludeLayout.patronEntry)) {
                String barcode = AppSharedPreferences.getInstance().getString(AppSharedPreferences.KEY_SELECTED_BARCODE);
                if (TextUtils.isEmpty(barcode)) {
                    checkoutViewModel.getScanPatron(fragmentCheckoutBinding.patronEntryIncludeLayout.patronEntry.getText().toString().trim());
                } else {
                    int collectionType = AppSharedPreferences.getInstance().getBoolean(AppSharedPreferences.KEY_IS_LIBRARY_SELECTED) ? 0 : 4;
                    checkoutViewModel.getCheckoutResult(AppSharedPreferences.getInstance().getString(AppSharedPreferences.KEY_PATRON_ID),
                            fragmentCheckoutBinding.patronEntryIncludeLayout.patronEntry.getText().toString().trim(), String.valueOf(collectionType), false);
                }
            } else {
                AppUtils.getInstance()
                        .showShortToastMessages(activity, getString(R.string.errorPatronEntry));
            }
        } else if (v.getId() == R.id.checkoutCloseBtn && fragmentCheckoutBinding.patronDetailIncludeLayout.patronDetailLayout.getVisibility() == View.VISIBLE) {
            AppSharedPreferences.getInstance().setString(AppSharedPreferences.KEY_PATRON_ID, null);
            AppSharedPreferences.getInstance().setString(AppSharedPreferences.KEY_SELECTED_BARCODE, null);
            fragmentCheckoutBinding.patronDetailIncludeLayout.patronDetailLayout.setVisibility(View.GONE);
            fragmentCheckoutBinding.checkoutDetailIncludeLayout.checkedoutDetailLayout.setVisibility(View.GONE);
            fragmentCheckoutBinding.checkoutPatronErrorMsg.setVisibility(View.GONE);
            fragmentCheckoutBinding.patronEntryIncludeLayout.patronEntry.setText("");
            fragmentCheckoutBinding.patronEntryIncludeLayout.patronEntry.setHint(R.string.findPatron);
        } else if (v.getId() == R.id.checkedoutInfoBtn) {
            Intent titleIntent = new Intent(activity, TitleInfoActivity.class);
            titleIntent.putExtra("bibID", checkoutResult.getInfo().getBibID());
            startActivity(titleIntent);

        }
    }

    public void getPatronID() {
        if (checkoutViewModel != null) {
            String selectedBarcode = AppSharedPreferences.getInstance().getString(AppSharedPreferences.KEY_SELECTED_BARCODE);
            if (TextUtils.isEmpty(selectedBarcode))
                checkoutViewModel.getScanPatron(fragmentCheckoutBinding.patronEntryIncludeLayout.patronEntry.getText().toString().trim());
            else
                checkoutViewModel.getScanPatron(selectedBarcode);
        }
    }


    @Override
    public void updateUI(Object result) {
        Activity activity = getBaseActivity();
        if (activity == null) {
            return;
        }
        final Object value = result;
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (value instanceof ScanPatron) {
                    scanPatron = (ScanPatron) value;
                    scanPatronUpdate(scanPatron);
                } else if (value instanceof CheckoutResult) {
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

    private void scanPatronUpdate(ScanPatron scanPatron) {
        if (scanPatron.getSuccess().equalsIgnoreCase("true")) {
            if (scanPatron.getPatronList() != null) {
                navigateToPatronListScreen(scanPatron);
            } else {
                AppSharedPreferences.getInstance().setString(AppSharedPreferences.KEY_SELECTED_BARCODE, scanPatron.getBarcode());
                AppSharedPreferences.getInstance().setString(AppSharedPreferences.KEY_PATRON_ID, scanPatron.getPatronID());
                bindPatronResult();
            }
        } else {
            AppSharedPreferences.getInstance().setString(AppSharedPreferences.KEY_SELECTED_BARCODE, null);
            updatePatronErrorMsg(scanPatron);
        }
    }


    private void updateCheckoutErrorMsg(CheckoutResult checkoutResult) {
        Activity activity = getBaseActivity();
        if (activity == null) {
            return;
        }

        if (checkoutResult != null) {
            fragmentCheckoutBinding.checkoutDetailIncludeLayout.checkedoutDetailLayout.setVisibility(View.GONE);
            if (!checkoutResult.getMessages().isEmpty()) {
                if (checkoutResult.getMessages().size() == 1) {
                    String errorMsg = checkoutResult.getMessages().get(0).getMessage();
                    fragmentCheckoutBinding.checkoutPatronErrorMsg.setVisibility(View.VISIBLE);
                    fragmentCheckoutBinding.checkoutPatronErrorMsg.setText(errorMsg);
                } else if (checkoutResult.getMessages().size() > 1) {
                    String errorMsg = checkoutResult.getMessages().get(0).getMessage() + "\n\n" + checkoutResult.getMessages().get(1).getMessage();
                    AppUtils.getInstance().showAlertDialog(activity, "Checkout Blocked", errorMsg, "allow", "cancel", this, 0);
                }
            } else {
                FollettLog.e(getString(R.string.error), "Empty Error Message from api result");
            }
        }

    }

    private void bindCheckoutResult(CheckoutResult checkoutResult) {
        try {
            if (fragmentCheckoutBinding.checkoutPatronErrorMsg.getVisibility() == View.VISIBLE)
                fragmentCheckoutBinding.checkoutPatronErrorMsg.setVisibility(View.GONE);
            fragmentCheckoutBinding.checkoutDetailIncludeLayout.checkedoutDetailLayout.setVisibility(View.VISIBLE);
            fragmentCheckoutBinding.checkoutDetailIncludeLayout.setCheckoutResult(checkoutResult);
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
            if (!TextUtils.isEmpty(AppSharedPreferences.getInstance().getString(AppSharedPreferences.KEY_SELECTED_BARCODE))) {
                fragmentCheckoutBinding.patronDetailIncludeLayout.patronDetailLayout.setVisibility(View.VISIBLE);
                fragmentCheckoutBinding.checkoutDetailIncludeLayout.checkedoutDetailLayout.setVisibility(View.GONE);
                fragmentCheckoutBinding.patronEntryIncludeLayout.patronEntry.setText("");
                fragmentCheckoutBinding.patronEntryIncludeLayout.patronEntry.setHint(R.string.enterBarcode);
                if (fragmentCheckoutBinding.checkoutPatronErrorMsg.getVisibility() == View.VISIBLE)
                    fragmentCheckoutBinding.checkoutPatronErrorMsg.setVisibility(View.GONE);

                scanPatron.setLibrarySelected(AppSharedPreferences.getInstance().getBoolean(AppSharedPreferences.KEY_IS_LIBRARY_SELECTED));

                fragmentCheckoutBinding.setScanPatron(scanPatron);
            } else {
                if (fragmentCheckoutBinding != null)
                    fragmentCheckoutBinding.patronDetailIncludeLayout.patronDetailLayout.setVisibility(View.GONE);
            }
        }
    }


    private void navigateToPatronListScreen(ScanPatron scanPatron) {
        CheckoutPatronListFragment checkoutPatronListFragment = CheckoutPatronListFragment.getInstance(scanPatron);
        mActivity.replaceFragment(checkoutPatronListFragment, R.id.loginContainer, "CheckoutPatronListFragment", true);
    }

    @Override
    public void onPositiveButtonClick(int statusCode) {
        if (statusCode == 0) {
            int collectionType = AppSharedPreferences.getInstance().getBoolean(AppSharedPreferences.KEY_IS_LIBRARY_SELECTED) ? 0 : 4;
            checkoutViewModel.getCheckoutResult(AppSharedPreferences.getInstance().getString(AppSharedPreferences.KEY_PATRON_ID),
                    fragmentCheckoutBinding.patronEntryIncludeLayout.patronEntry.getText().toString().trim(), String.valueOf(collectionType), true);
        }
    }

    @Override
    public void onNegativeButtonClick(int statusCode) {
        //Do Nothing
    }
}
