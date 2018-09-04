/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */
package com.follett.fsc.mobile.circdesk.feature.patronstatus;

import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.app.base.BaseActivity;
import com.follett.fsc.mobile.circdesk.databinding.ActivityCommonBinding;

import android.os.Bundle;

public class PatronStatusActivity extends BaseActivity<PatronStatusViewModel> {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        putContentView(R.layout.activity_common);
        inItView();
    }
    
    private void inItView() {
        setTitleBar(getString(R.string.patron_status_label));
        getSupportFragmentManager().beginTransaction()
                .add(R.id.commonContainer, PatronStatusFragment.newInstance(), "PatronStatusFragment")
                .commit();
        
    }
//    @Override
//    public void onNavigation(int position) {
//        if (position == 0) { // Navigate to School list
//            navigateToSchoolList(true);
//        } else if (position == 1) { // Navigate to Login
//            navigateToLogin(true);
//        } else if (position == 2) { // Navigate to Home Screen
//            navigateToHome();
//        }
//    }
}

