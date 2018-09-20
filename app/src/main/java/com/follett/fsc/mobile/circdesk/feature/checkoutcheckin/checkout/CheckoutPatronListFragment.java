/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.checkoutcheckin.checkout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.app.ItemClickListener;
import com.follett.fsc.mobile.circdesk.app.base.BaseFragment;
import com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences;
import com.follett.fsc.mobile.circdesk.databinding.ActivityPatronListBinding;

public class CheckoutPatronListFragment extends BaseFragment<ActivityPatronListBinding, CheckoutViewModel> implements ItemClickListener {
    private ScanPatron scanPatron;

    private ActivityPatronListBinding activityPatronListBinding;

    public static CheckoutPatronListFragment getInstance(ScanPatron scanPatron){
        Bundle args = new Bundle();
        args.putParcelable("scanPatron", scanPatron);
        CheckoutPatronListFragment fragment = new CheckoutPatronListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_patron_list;
    }

    @Override
    public CheckoutViewModel getViewModel() {
        return null;
    }

    @Override
    public int getBindingVariable() {
        return 0;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        activityPatronListBinding = getViewDataBinding();

        activityPatronListBinding.patronListView.setLayoutManager(new LinearLayoutManager(getActivity()));


        if (getArguments()!= null) {
            scanPatron = getArguments().getParcelable("scanPatron");
        }

        mActivity.setTitleBar(getString(R.string.selectPatron));

        PatronListAdapter patronListAdapter = new PatronListAdapter(getActivity(), scanPatron, this);
        activityPatronListBinding.patronListView.setAdapter(patronListAdapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        AppSharedPreferences.getInstance().setString(AppSharedPreferences.KEY_SELECTED_BARCODE, scanPatron.getPatronList().get(position).getBarcode());
        mActivity.onBackPressed();
    }
}