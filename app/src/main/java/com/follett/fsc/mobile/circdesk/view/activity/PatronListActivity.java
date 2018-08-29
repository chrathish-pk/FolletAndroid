package com.follett.fsc.mobile.circdesk.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences;
import com.follett.fsc.mobile.circdesk.data.model.ScanPatron;
import com.follett.fsc.mobile.circdesk.databinding.ActivityPatronListBinding;
import com.follett.fsc.mobile.circdesk.interfaces.ItemClickListener;
import com.follett.fsc.mobile.circdesk.view.adapter.PatronListAdapter;
import com.follett.fsc.mobile.circdesk.view.base.BaseActivity;
import com.follett.fsc.mobile.circdesk.viewmodel.CheckoutViewModel;

public class PatronListActivity extends BaseActivity<CheckoutViewModel> implements ItemClickListener {

    ActivityPatronListBinding activityPatronListBinding;
    private CheckoutViewModel checkoutViewModel;
    private PatronListAdapter patronListAdapter;
    private ScanPatron scanPatron;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityPatronListBinding = putContentView(R.layout.activity_patron_list);
        //checkoutViewModel = new CheckoutViewModel(getApplication(), new AppRemoteRepository());
        activityPatronListBinding.patronListView.setLayoutManager(new LinearLayoutManager(this));


        if (getIntent().getExtras() != null) {
            scanPatron = (ScanPatron) getIntent().getSerializableExtra("scanPatron");
        }

        setTitleBar(getString(R.string.selectPatron));

        patronListAdapter = new PatronListAdapter(this, scanPatron, this);
        activityPatronListBinding.patronListView.setAdapter(patronListAdapter);




       /* checkoutViewModel.getScanPatron().observe(this, new Observer<ScanPatron>() {
            @Override
            public void onChanged(@Nullable ScanPatron scanPatron) {
                patronListAdapter = new PatronListAdapter(this,checkoutViewModel);
                activityPatronListBinding.patronListView.setAdapter(patronListAdapter);
            }
        });*/


    }

    @Override
    public void OnItemClicked() {

    }

    @Override
    public void OnItemClick(View view, int position) {
        AppSharedPreferences.getInstance(this).setString(AppSharedPreferences.KEY_SELECTED_BARCODE, scanPatron.getPatronList().get(position).getBarcode());
        finish();
    }
}
