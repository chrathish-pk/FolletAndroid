package com.follett.fsc.mobile.circdesk.view.fragment;

import com.follett.fsc.mobile.circdesk.BR;
import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.databinding.FragmentCheckinBinding;
import com.follett.fsc.mobile.circdesk.viewmodel.CheckoutViewModel;

public class CheckinFragment extends BaseFragment<FragmentCheckinBinding, CheckoutViewModel> {

    private CheckoutViewModel checkinViewModel;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_checkin;
    }

    @Override
    public CheckoutViewModel getViewModel() {
        //checkinViewModel = ViewModelProviders.of(this).get(CheckoutViewModel.class);
        return null;
    }

    @Override
    public int getBindingVariable() {
        return BR.checkoutViewModel;
    }
}
