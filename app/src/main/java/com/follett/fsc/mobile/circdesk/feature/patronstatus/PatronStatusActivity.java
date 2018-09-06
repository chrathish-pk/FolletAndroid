/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */
package com.follett.fsc.mobile.circdesk.feature.patronstatus;

import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.app.base.BaseActivity;
import com.follett.fsc.mobile.circdesk.feature.loginsetup.NavigationListener;
import com.follett.fsc.mobile.circdesk.feature.patronstatus.model.PatronInfo;
import com.follett.fsc.mobile.circdesk.feature.patronstatus.model.PatronList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import java.util.ArrayList;

public class PatronStatusActivity extends BaseActivity<PatronStatusViewModel> implements NavigationListener, View.OnClickListener {
    
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
        setBackBtnVisible();
        baseBinding.backBtn.setOnClickListener(this);
        mPatronStatusFragment = PatronStatusFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.commonContainer, mPatronStatusFragment, "PatronStatusFragment")
                .commit();
        
    }
    
    @Override
    public void onNavigation(int position) {
        // Do Nothing
    }
    
    @Override
    public void setToolBarTitle(String titleText) {
        setTitleBar(titleText);
    }
    
    @Override
    public void onNavigation(Object model, int position) {
        
        if (position == 0 && model != null) {  //Show patron list
            navigateToPatronList((ArrayList<PatronList>) model, true);
        } else if (position == 1 && model != null) {  // pop patron list
            popFragmentFromBackStack(mPatronListFragment);
            mPatronStatusFragment.requestPatronId((PatronList) model);
        } else if (position == 2 && model != null) {    // PatronItemCheckoutFragment checkout
            navigateToPatronCheckout((PatronInfo) model, true, getString(R.string.item_checkout_label));
        } else if (position == 3 && model != null) {    // PatronItemCheckoutFragment hold
            navigateToPatronCheckout((PatronInfo) model, true, getString(R.string.on_hold_label));
        } else if (position == 4 && model != null) {    // FineListFragment Fine
            navigateToFineList((PatronInfo) model, true, getString(R.string.fine_label));
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
    
    private void navigateToPatronCheckout(PatronInfo patronInfo, boolean isAddToBackStack, String title) {
    
        PatronItemCheckoutFragment patronItemCheckoutFragment = PatronItemCheckoutFragment.newInstance(patronInfo, title);
        setToolBarTitle(title);
        
        final FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction()
                .add(R.id.base_container, patronItemCheckoutFragment);
        if (isAddToBackStack) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();
    }
    
    private void navigateToFineList(PatronInfo patronInfo, boolean isAddToBackStack, String title) {
    
        PatronFineListFragment patronFineListFragment = PatronFineListFragment.newInstance(patronInfo);
        setToolBarTitle(title);
        
        final FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction()
                .add(R.id.base_container, patronFineListFragment);
        if (isAddToBackStack) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();
    }
    
    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.backBtn) {
            super.onBackPressed();
        }
    }
}

