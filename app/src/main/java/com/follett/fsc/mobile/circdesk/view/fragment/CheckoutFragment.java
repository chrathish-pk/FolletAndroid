package com.follett.fsc.mobile.circdesk.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.follett.fsc.mobile.circdesk.BR;
import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences;
import com.follett.fsc.mobile.circdesk.data.model.ScanPatron;
import com.follett.fsc.mobile.circdesk.data.remote.repository.AppRemoteRepository;
import com.follett.fsc.mobile.circdesk.databinding.FragmentCheckoutBinding;
import com.follett.fsc.mobile.circdesk.interfaces.ChangeUIListener;
import com.follett.fsc.mobile.circdesk.utils.AppUtils;
import com.follett.fsc.mobile.circdesk.view.custom.GlideApp;
import com.follett.fsc.mobile.circdesk.viewmodel.CheckoutViewModel;

public class CheckoutFragment extends BaseFragment<FragmentCheckoutBinding, CheckoutViewModel> implements View.OnClickListener, ChangeUIListener {

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
                checkoutViewModel.getScanPatron(fragmentCheckoutBinding.patronEntry.getText().toString().trim());

                /*if (scanPatron != null && scanPatron.getPatronList().size() != 0) {
                    Intent patronListIntent = new Intent(getActivity(), PatronListActivity.class);
                    patronListIntent.putExtra("scanPatron", scanPatron);
                    startActivity(patronListIntent);
                }*/
            } else {
                AppUtils.getInstance()
                        .showShortToastMessages(getBaseActivity(), getString(R.string.errorPatronEntry));
            }
        } else if (v.getId() == R.id.checkoutCloseBtn) {
            if (fragmentCheckoutBinding.patronDetailLayout.getVisibility() == View.VISIBLE)
                fragmentCheckoutBinding.patronDetailLayout.setVisibility(View.GONE);
        }
    }

    public void getPatronID() {
        checkoutViewModel.getScanPatron(AppSharedPreferences.getInstance(getActivity()).getString(AppSharedPreferences.KEY_SELECTED_PATRON_ID));
    }

    @Override
    public void updatePatronUI(final ScanPatron scanPatron) {

        getActivity().runOnUiThread(new Runnable() {

            @Override
            public void run() {
                if (scanPatron != null) {
                    fragmentCheckoutBinding.patronDetailLayout.setVisibility(View.VISIBLE);
                    fragmentCheckoutBinding.checkoutPatronName.setText(scanPatron.getLastFirstMiddleName());
                    fragmentCheckoutBinding.checkoutPatronID.setText(scanPatron.getPatronID());
                    fragmentCheckoutBinding.checkoutPatronType.setText(scanPatron.getPatronType());
                    fragmentCheckoutBinding.checkedOutCount.setText("Checked Out: " + scanPatron.getAssetCheckouts());
                    fragmentCheckoutBinding.overdue.setText("Overdue: " + scanPatron.getAssetOverdues());

                    GlideApp.with(getActivity())
                            .load(scanPatron.getPatronPictureFileName())
                            .placeholder(R.drawable.inventory)
                            .into(fragmentCheckoutBinding.checkoutPatronImg);
                } else {
                    fragmentCheckoutBinding.patronDetailLayout.setVisibility(View.GONE);
                }
            }
        });


    }

    @Override
    public void updateCheckoutUI() {

    }

    @Override
    public void updateErrorPatronUI(final ScanPatron scanPatron) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (scanPatron != null) {
                    fragmentCheckoutBinding.checkoutPatronErrorMsg.setVisibility(View.VISIBLE);
                    fragmentCheckoutBinding.checkoutPatronErrorMsg.setText(scanPatron.getMessages().get(0).getMessage());
                }
            }
        });
    }
}
