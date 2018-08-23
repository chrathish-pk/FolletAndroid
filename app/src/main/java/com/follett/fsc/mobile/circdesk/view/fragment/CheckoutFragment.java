package com.follett.fsc.mobile.circdesk.view.fragment;

import android.arch.lifecycle.ViewModelProviders;

import com.follett.fsc.mobile.circdesk.BR;
import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.databinding.FragmentCheckoutBinding;
import com.follett.fsc.mobile.circdesk.viewmodel.CheckoutViewModel;

public class CheckoutFragment extends BaseFragment<FragmentCheckoutBinding, CheckoutViewModel> {

    private CheckoutViewModel checkoutViewModel;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_checkout;
    }

    @Override
    public CheckoutViewModel getViewModel() {
        checkoutViewModel = ViewModelProviders.of(this).get(CheckoutViewModel.class);
        return checkoutViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.checkoutViewModel;
    }
}
