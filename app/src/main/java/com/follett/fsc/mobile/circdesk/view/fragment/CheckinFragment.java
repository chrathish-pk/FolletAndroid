package com.follett.fsc.mobile.circdesk.view.fragment;

import android.arch.lifecycle.ViewModelProviders;

import com.follett.fsc.mobile.circdesk.BR;
import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.databinding.FragmentCheckinBinding;
import com.follett.fsc.mobile.circdesk.viewmodel.CheckinViewModel;

public class CheckinFragment extends BaseFragment<FragmentCheckinBinding, CheckinViewModel> {

    private CheckinViewModel checkinViewModel;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_checkin;
    }

    @Override
    public CheckinViewModel getViewModel() {
        checkinViewModel = ViewModelProviders.of(this).get(CheckinViewModel.class);
        return checkinViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.checkinViewModel;
    }
}
