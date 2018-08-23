package com.follett.fsc.mobile.circdesk.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.databinding.ActivityPatronListBinding;
import com.follett.fsc.mobile.circdesk.view.base.BaseActivity;
import com.follett.fsc.mobile.circdesk.viewmodel.PatronListViewModel;

public class PatronListActivity extends BaseActivity<PatronListViewModel> {

    ActivityPatronListBinding activityPatronListBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityPatronListBinding = putContentView(R.layout.activity_patron_list);

        setTitleBar(getString(R.string.selectPatron));


    }
}
