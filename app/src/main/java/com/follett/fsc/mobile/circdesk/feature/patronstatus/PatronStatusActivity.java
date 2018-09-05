/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */
package com.follett.fsc.mobile.circdesk.feature.patronstatus;

import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.app.base.BaseActivity;
import com.follett.fsc.mobile.circdesk.databinding.ActivityCommonBinding;
import com.follett.fsc.mobile.circdesk.feature.loginsetup.NavigationListener;
import com.follett.fsc.mobile.circdesk.feature.loginsetup.SchoolListFragment;
import com.follett.fsc.mobile.circdesk.feature.patronstatus.model.PatronList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;

public class PatronStatusActivity extends BaseActivity<PatronStatusViewModel> implements NavigationListener {
    
    private PatronStatusFragment mPatronStatusFragment;
    
    private PatronListFragment mPatronListFragment;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        putContentView(R.layout.activity_common);
        inItView();
    }
    
    private void inItView() {
        setTitleBar(getString(R.string.patron_status_label));
        mPatronStatusFragment = PatronStatusFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.commonContainer, mPatronStatusFragment, "PatronStatusFragment")
                .commit();
        
    }
    
    @Override
    public void onNavigation(int position) {
    }
    
    @Override
    public void setToolBarTitle(String titleText) {
    
    }
    
    @Override
    public void onNavigation(Object model, int position) {
        
        if (position == 0 && model != null) {
            navigateToPatronList((ArrayList<PatronList>)model, true);
        } else if (position == 1 && model != null) {
            popFragmentFromBackStack(mPatronListFragment);
            mPatronStatusFragment.requestPatronId((PatronList) model);
        }
    }
    
    private void popFragmentFromBackStack(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction trans = manager.beginTransaction();
        trans.remove(fragment);
        trans.commit();
        manager.popBackStack();
    }
    
    
    private void navigateToPatronList(ArrayList<PatronList> patronList, boolean isAddToBackStack) {
    
        mPatronListFragment = PatronListFragment.newInstance(patronList);
        setToolBarTitle(getString(R.string.selectPatron));
        
        final FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction()
                .add(R.id.base_container, mPatronListFragment);
        if (isAddToBackStack) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();
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

