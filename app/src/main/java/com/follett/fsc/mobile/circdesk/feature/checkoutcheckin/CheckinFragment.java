/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.checkoutcheckin;

import com.follett.fsc.mobile.circdesk.BR;
import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.app.base.BaseFragment;
import com.follett.fsc.mobile.circdesk.databinding.FragmentCheckinBinding;

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
