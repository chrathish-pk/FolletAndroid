package com.follett.fsc.mobile.circdesk.feature.loginsetup;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.app.base.BaseActivity;
import com.follett.fsc.mobile.circdesk.databinding.ActivitySetupBinding;
import com.follett.fsc.mobile.circdesk.feature.homescreen.HomeFragment;

public class SetupActivity extends BaseActivity<LoginViewModel> implements NavigationListener {

    private ActivitySetupBinding activitySetupBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activitySetupBinding = putContentView(R.layout.activity_setup);

        pushFragment(new SetupFragment(), R.id.loginContainer, "SetupFragment", true);

    }

    @Override
    public void onNavigation(int position) {

    }

    @Override
    public void setToolBarTitle(String titleText) {
        setTitleBar(titleText);
    }

    @Override
    public void onNavigation(Object model, int position) {
        if (model instanceof DistrictList && position == 0) {
            navigateToDistrictList((DistrictList) model, true);
        } else if (position == 1) { // Navigate to School list
            navigateToSchoolList(true);
        } else if (position == 2) { // Navigate to Login
            navigateToLogin(true);
        } else if (position == 3) { // Navigate to Home Screen
            navigateToHome();
        }
    }

    private void navigateToDistrictList(DistrictList districtList, boolean isAddToBackStack) {
        DistrictListFragment mDistrictListFragment = DistrictListFragment.newInstance(districtList);
        setToolBarTitle(getString(R.string.select_district_label));
        pushFragment(mDistrictListFragment, R.id.loginContainer, "DistrictListFragment", isAddToBackStack);

    }

    private void navigateToLogin(boolean isAddToBackStack) {
        setToolBarTitle(getString(R.string.get_started_label));
        pushFragment(new LoginFragment(), R.id.loginContainer, "LoginFragment", isAddToBackStack);

    }


    private void navigateToSchoolList(boolean isAddToBackStack) {
        setToolBarTitle(getString(R.string.select_school_label));
        pushFragment(new SchoolListFragment(), R.id.loginContainer, "SchoolListFragment", isAddToBackStack);
    }

    private void navigateToHome() {
        //mLoginBinding.tabLayout.removeAllTabs();
        pushFragment(new HomeFragment(), R.id.loginContainer, "HomeFragment", false);
    }

}