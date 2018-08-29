package com.follett.fsc.mobile.circdesk.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;

import com.follett.fsc.mobile.circdesk.BR;
import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences;
import com.follett.fsc.mobile.circdesk.data.model.ScanPatron;
import com.follett.fsc.mobile.circdesk.data.model.checkout.CheckoutResult;
import com.follett.fsc.mobile.circdesk.data.remote.repository.AppRemoteRepository;
import com.follett.fsc.mobile.circdesk.databinding.FragmentCheckoutBinding;
import com.follett.fsc.mobile.circdesk.interfaces.UpdateUIListener;
import com.follett.fsc.mobile.circdesk.utils.AppUtils;
import com.follett.fsc.mobile.circdesk.utils.FollettLog;
import com.follett.fsc.mobile.circdesk.view.activity.PatronListActivity;
import com.follett.fsc.mobile.circdesk.view.custom.GlideApp;
import com.follett.fsc.mobile.circdesk.viewmodel.CheckoutViewModel;

public class CheckoutFragment extends BaseFragment<FragmentCheckoutBinding, CheckoutViewModel> implements View.OnClickListener, UpdateUIListener {

    private CheckoutViewModel checkoutViewModel;
    private FragmentCheckoutBinding fragmentCheckoutBinding;

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

        fragmentCheckoutBinding.patronGoBtn.setOnClickListener(this);
        fragmentCheckoutBinding.checkoutCloseBtn.setOnClickListener(this);
        fragmentCheckoutBinding.checkedoutInfoBtn.setOnClickListener(this);

        /*checkoutViewModel.getScanPatronLiveData().observe(this, new Observer<ScanPatron>() {
            @Override
            public void onChanged(@Nullable ScanPatron scanPatron) {

                if (scanPatron != null && scanPatron.getPatronList().size() != 0) {
                    startActivity(new Intent(getActivity(), PatronListActivity.class));
                }
            }
        });*/
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.patronGoBtn) {
            AppUtils.getInstance()
                    .hideKeyBoard(getBaseActivity(), fragmentCheckoutBinding.patronEntry);
            if (AppUtils.getInstance().isEditTextNotEmpty(fragmentCheckoutBinding.patronEntry)) {
                String barcode = AppSharedPreferences.getInstance(getActivity()).getString(AppSharedPreferences.KEY_BARCODE);
                if (TextUtils.isEmpty(barcode)) {
                    checkoutViewModel.getScanPatron(fragmentCheckoutBinding.patronEntry.getText().toString().trim());
                } else {
                    checkoutViewModel.getCheckoutResult(barcode, fragmentCheckoutBinding.patronEntry.getText().toString().trim());
                }
            } else {
                AppUtils.getInstance()
                        .showShortToastMessages(getBaseActivity(), getString(R.string.errorPatronEntry));
            }
        } else if (v.getId() == R.id.checkoutCloseBtn && fragmentCheckoutBinding.patronDetailLayout.getVisibility() == View.VISIBLE) {
            AppSharedPreferences.getInstance(getActivity()).setString(AppSharedPreferences.KEY_BARCODE, null);
            fragmentCheckoutBinding.patronDetailLayout.setVisibility(View.GONE);
        } else if (v.getId() == R.id.checkedoutInfoBtn) {

        }
    }

    public void getPatronID() {
        checkoutViewModel.getScanPatron(AppSharedPreferences.getInstance(getActivity()).getString(AppSharedPreferences.KEY_SELECTED_BARCODE));
    }

    @Override
    public void updateUI(Object result) {
        final Object value = result;
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ScanPatron scanPatron = null;
                CheckoutResult checkoutResult = null;

                if (value != null && value instanceof ScanPatron) {
                    scanPatron = (ScanPatron) value;
                    if (scanPatron.getSuccess().equalsIgnoreCase("true")) {
                        if (scanPatron.getPatronList() != null) {
                            navigateToPatronListScreen(scanPatron);
                        } else {
                            bindPatronResult(scanPatron);
                        }
                    } else {
                        AppSharedPreferences.getInstance(getActivity()).setString(AppSharedPreferences.KEY_BARCODE, null);
                        updatePatronErrorMsg(scanPatron);
                    }
                } else if (value != null && value instanceof CheckoutResult) {
                    checkoutResult = (CheckoutResult) value;
                    if (checkoutResult.getSuccess() == true) {
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
            fragmentCheckoutBinding.checkoutPatronErrorMsg.setText(checkoutResult.getMessages().get(1).getMessage());
        }

    }

    private void bindCheckoutResult(CheckoutResult checkoutResult) {
        try {
            fragmentCheckoutBinding.checkedoutDetailLayout.setVisibility(View.VISIBLE);
            fragmentCheckoutBinding.checkedoutName.setText(checkoutResult.getInfo().getTitle());
            fragmentCheckoutBinding.checkedoutDue.setText(checkoutResult.getInfo().getDueDate());
            fragmentCheckoutBinding.checkedoutType.setText(checkoutResult.getInfo().getBarcode());
        } catch (Exception e) {
            FollettLog.e("Error", e.getMessage());
        }
    }

    private void updatePatronErrorMsg(ScanPatron scanPatron) {
        try {
            if (fragmentCheckoutBinding.patronDetailLayout.getVisibility() == View.VISIBLE)
                fragmentCheckoutBinding.patronDetailLayout.setVisibility(View.GONE);
            if (scanPatron != null) {
                fragmentCheckoutBinding.checkoutPatronErrorMsg.setVisibility(View.VISIBLE);
                fragmentCheckoutBinding.checkoutPatronErrorMsg.setText(scanPatron.getMessages().get(0).getMessage());
            } else {
                FollettLog.e("Scan patron error", "no data found from api result");
            }
        } catch (Exception e) {
            FollettLog.e("Error", e.getMessage());
        }
    }

    private void bindPatronResult(ScanPatron scanPatron) {
        if (scanPatron != null) {
            fragmentCheckoutBinding.patronDetailLayout.setVisibility(View.VISIBLE);
            fragmentCheckoutBinding.checkoutPatronName.setText(scanPatron.getLastFirstMiddleName());
            fragmentCheckoutBinding.checkoutPatronID.setText(scanPatron.getPatronID());
            fragmentCheckoutBinding.checkoutPatronType.setText(scanPatron.getPatronType());
            fragmentCheckoutBinding.checkedOutCount.setText("Checked Out: " + scanPatron.getAssetCheckouts());
            fragmentCheckoutBinding.overdue.setText("Overdue: " + scanPatron.getAssetOverdues());

            GlideApp.with(this)
                    .load(scanPatron.getPatronPictureFileName())
                    .placeholder(R.drawable.inventory)
                    .into(fragmentCheckoutBinding.checkoutPatronImg);
        } else {
            fragmentCheckoutBinding.patronDetailLayout.setVisibility(View.GONE);
        }
    }

    private void navigateToPatronListScreen(ScanPatron scanPatron) {
        Intent patronListIntent = new Intent(getActivity(), PatronListActivity.class);
        patronListIntent.putExtra("scanPatron", scanPatron);
        startActivity(patronListIntent);
    }
}
