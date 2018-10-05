package com.follett.fsc.mobile.circdesk.feature.loginsetup.view;

import android.arch.lifecycle.MutableLiveData;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.app.base.BaseActivity;
import com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences;
import com.follett.fsc.mobile.circdesk.data.remote.repository.AppRemoteRepository;
import com.follett.fsc.mobile.circdesk.feature.homescreen.view.HomeFragment;
import com.follett.fsc.mobile.circdesk.feature.loginsetup.model.DistrictList;
import com.follett.fsc.mobile.circdesk.feature.loginsetup.viewmodel.LoginViewModel;

public class SetupActivity extends BaseActivity<LoginViewModel> implements NavigationListener {

    public MutableLiveData<String> selectedDateLiveData = new MutableLiveData<>();
    public MutableLiveData<String> selectedInventoryNameLiveData = new MutableLiveData<>();
    public MutableLiveData<Boolean> selectedData = new MutableLiveData<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        putContentView(R.layout.activity_setup);
        if (!TextUtils.isEmpty(AppSharedPreferences.getInstance().getString(AppSharedPreferences.KEY_CONTEXT_NAME)) &&
                TextUtils.isEmpty(AppSharedPreferences.getInstance().getString(AppSharedPreferences.KEY_SESSION_ID)))
            pushFragment(new LoginFragment(), R.id.loginContainer, getString(R.string.get_started_label), false, false);
        else {
            AppRemoteRepository.mInstance = null;
            pushFragment(new SetupFragment(), R.id.loginContainer, getString(R.string.connect_your_school_label), false, false);
        }

    }

    @Override
    public void onNavigation(int position) {
        //do nothing
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
        pushFragment(mDistrictListFragment, R.id.loginContainer, getString(R.string.select_district_label), isAddToBackStack, false);

    }

    private void navigateToLogin(boolean isAddToBackStack) {
        setToolBarTitle(getString(R.string.get_started_label));
        pushFragment(new LoginFragment(), R.id.loginContainer, getString(R.string.get_started_label), isAddToBackStack, false);

    }


    private void navigateToSchoolList(boolean isAddToBackStack) {
        setToolBarTitle(getString(R.string.select_school_label));
        pushFragment(new SchoolListFragment(), R.id.loginContainer, getString(R.string.select_school_label), isAddToBackStack, false);
    }

    private void navigateToHome() {
        pushFragment(new HomeFragment(), R.id.loginContainer, getString(R.string.home), false, false);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
