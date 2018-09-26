package com.follett.fsc.mobile.circdesk.feature.loginsetup.view;

import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.app.base.BaseActivity;
import com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences;
import com.follett.fsc.mobile.circdesk.data.remote.repository.AppRemoteRepository;
import com.follett.fsc.mobile.circdesk.feature.homescreen.view.HomeFragment;
import com.follett.fsc.mobile.circdesk.feature.loginsetup.model.DistrictList;
import com.follett.fsc.mobile.circdesk.feature.loginsetup.viewmodel.LoginViewModel;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;

public class SetupActivity extends BaseActivity<LoginViewModel> implements NavigationListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        putContentView(R.layout.activity_setup);
        if (!TextUtils.isEmpty(AppSharedPreferences.getInstance().getString(AppSharedPreferences.KEY_CONTEXT_NAME)) &&
                TextUtils.isEmpty(AppSharedPreferences.getInstance().getString(AppSharedPreferences.KEY_SESSION_ID)))
            pushFragment(new LoginFragment(), R.id.loginContainer, "SetupFragment", false);
        else {
            AppRemoteRepository.mInstance = null;
            pushFragment(new SetupFragment(), R.id.loginContainer, "SetupFragment", false);
        }

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
