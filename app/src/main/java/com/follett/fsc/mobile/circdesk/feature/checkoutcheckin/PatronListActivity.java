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
import com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences;
import com.follett.fsc.mobile.circdesk.databinding.ActivityPatronListBinding;
import com.follett.fsc.mobile.circdesk.app.base.BaseActivity;

public class PatronListActivity extends BaseActivity<CheckoutViewModel> implements ItemClickListener {

    private ActivityPatronListBinding activityPatronListBinding;
    private ScanPatron scanPatron;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityPatronListBinding = putContentView(R.layout.activity_patron_list);
        activityPatronListBinding.patronListView.setLayoutManager(new LinearLayoutManager(this));


        if (getIntent().getExtras() != null) {
            scanPatron = (ScanPatron) getIntent().getParcelableExtra(getString(R.string.scanPatron));
        }

        setTitleBar(getString(R.string.selectPatron));

        PatronListAdapter patronListAdapter = new PatronListAdapter(this, scanPatron, this);
        activityPatronListBinding.patronListView.setAdapter(patronListAdapter);
    }
    
    @Override
    public void onItemClicked() {
    
    }
    
    @Override
    public void onItemClick(View view, int position) {
        AppSharedPreferences.getInstance(this).setString(AppSharedPreferences.KEY_SELECTED_BARCODE, scanPatron.getPatronList().get(position).getBarcode());
        finish();
    }
}
