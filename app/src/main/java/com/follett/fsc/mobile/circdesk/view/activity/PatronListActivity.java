package com.follett.fsc.mobile.circdesk.view.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.databinding.ActivityPatronListBinding;
import com.follett.fsc.mobile.circdesk.view.adapter.PatronListAdapter;
import com.follett.fsc.mobile.circdesk.view.base.BaseActivity;
import com.follett.fsc.mobile.circdesk.viewmodel.CheckoutViewModel;

public class PatronListActivity extends BaseActivity<CheckoutViewModel> {

    ActivityPatronListBinding activityPatronListBinding;
    private CheckoutViewModel checkoutViewModel;
    private PatronListAdapter patronListAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityPatronListBinding = putContentView(R.layout.activity_patron_list);
        checkoutViewModel = ViewModelProviders.of(this).get(CheckoutViewModel.class);

        setTitleBar(getString(R.string.selectPatron));



       /* checkoutViewModel.getScanPatron().observe(this, new Observer<ScanPatron>() {
            @Override
            public void onChanged(@Nullable ScanPatron scanPatron) {
                patronListAdapter = new PatronListAdapter(this,checkoutViewModel);
                activityPatronListBinding.patronListView.setAdapter(patronListAdapter);
            }
        });*/


    }
}
