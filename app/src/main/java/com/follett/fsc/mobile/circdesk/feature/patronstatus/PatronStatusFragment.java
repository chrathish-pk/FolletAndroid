/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.patronstatus;

import android.arch.lifecycle.Observer;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.inputmethod.EditorInfo;

import com.follett.fsc.mobile.circdesk.BR;
import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.app.base.BaseFragment;
import com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences;
import com.follett.fsc.mobile.circdesk.databinding.FragmentPatronStatusBinding;
import com.follett.fsc.mobile.circdesk.feature.iteminfo.TitleInfoActivity;
import com.follett.fsc.mobile.circdesk.feature.loginsetup.NavigationListener;
import com.follett.fsc.mobile.circdesk.feature.loginsetup.Permissions;
import com.follett.fsc.mobile.circdesk.feature.patronstatus.model.CustomCheckoutItem;
import com.follett.fsc.mobile.circdesk.feature.patronstatus.model.PatronInfo;
import com.follett.fsc.mobile.circdesk.feature.patronstatus.model.PatronList;
import com.follett.fsc.mobile.circdesk.utils.AppUtils;
import com.follett.fsc.mobile.circdesk.utils.FollettLog;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class PatronStatusFragment extends BaseFragment<FragmentPatronStatusBinding, PatronStatusViewModel> implements NavigationListener, View.OnClickListener {

    private static final String TAG = PatronStatusFragment.class.getSimpleName();

    private PatronStatusViewModel mViewModel;

    private FragmentPatronStatusBinding mBinding;

    private PatronInfo mPatronInfo;

    private NavigationListener mNavigationListener;

    private PatronListFragment mPatronListFragment;

    public static PatronStatusFragment newInstance() {
        PatronStatusFragment fragment = new PatronStatusFragment();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mNavigationListener = (NavigationListener) context;
        } catch (ClassCastException ex) {
            FollettLog.e(TAG, "ClassCastException");
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_patron_status;
    }

    @Override
    public PatronStatusViewModel getViewModel() {
        mViewModel = new PatronStatusViewModel(getBaseApplication());
        return mViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBinding = getViewDataBinding();
        mActivity.setTitleBar(getString(R.string.patron_status_label));
        mActivity.setBackBtnVisible();

        showItemCheckedoutView();
        inItView();
    }

    private void showItemCheckedoutView() {
        String permissionValue = AppSharedPreferences.getInstance().getString(AppSharedPreferences.KEY_PERMISSIONS);
        Permissions permissions = new Gson().fromJson(permissionValue, Permissions.class);
        if (Boolean.parseBoolean(permissions.getCanViewItemsOutAsset()) || Boolean.parseBoolean(permissions.getCanViewItemsOutLibrary()))
            mBinding.itemRelativeLayout.setVisibility(View.VISIBLE);
        else
            mBinding.itemRelativeLayout.setVisibility(View.GONE);
    }

    private void inItView() {

        mBinding.patronEntryIncludeLayout.checkinLibRecordSwitch.setVisibility(View.GONE);
        mBinding.patronEntryIncludeLayout.patronEntry.setImeOptions(EditorInfo.IME_ACTION_DONE);
        mViewModel.getErrorMessage()
                .observe(this, new Observer() {
                    @Override
                    public void onChanged(@Nullable Object object) {
                        AppUtils.getInstance()
                                .showShortToastMessages(getBaseActivity(), (String) object);
                    }
                });
        mViewModel.mPatronInfo.observe(this, new Observer<PatronInfo>() {
            @Override
            public void onChanged(@Nullable PatronInfo patronInfo) {
                mPatronInfo = patronInfo;
                updateUI(mPatronInfo);
            }
        });
        setListener();
    }

    @Override
    public void onClick(View v) {
        if (v == mBinding.patronEntryIncludeLayout.patronGoBtn) {
            mBinding.patronDetailLayout.setVisibility(View.GONE);
            getPatronInfo(AppUtils.getInstance()
                    .getEditTextValue(mBinding.patronEntryIncludeLayout.patronEntry));

        } else if (v == mBinding.closeBtn) {
            mBinding.patronDetailLayout.setVisibility(View.GONE);
        } else if (v == mBinding.itemRelativeLayout) {
            if (mPatronInfo != null && (!mPatronInfo.getCheckouts()
                    .isEmpty() || !mPatronInfo.getAssetCheckOuts()
                    .isEmpty())) {
                mNavigationListener.onNavigation(mPatronInfo, 2);
            }
        } else if (v == mBinding.holdRelativeLayout) {
            if (mPatronInfo != null && !mPatronInfo.getHolds()
                    .isEmpty()) {
                mNavigationListener.onNavigation(mPatronInfo, 3);
            }
        } else if (v == mBinding.fineRelativeLayout) {
            if (mPatronInfo != null && !mPatronInfo.getFines()
                    .isEmpty()) {
                mNavigationListener.onNavigation(mPatronInfo, 4);
            }
        }
    }

    private void getPatronInfo(String patronID) {
        AppUtils.getInstance()
                .hideKeyBoard(getBaseActivity(), mBinding.patronEntryIncludeLayout.patronEntry);
        mViewModel.getPatronInfo(patronID);
    }

    private void updateUI(final PatronInfo patronInfo) {
        if (null != mActivity) {
            mActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (patronInfo != null && patronInfo.getSuccess()) {
                        mBinding.patronErrorMsg.setVisibility(View.GONE);
                        if (patronInfo.getPatronList() != null) {
                            navigateToPatronListFragment(patronInfo.getPatronList());
                        } else {
                            mBinding.setPatronInfo(patronInfo);
                        }
                    } else if (patronInfo != null && !patronInfo.getSuccess()) {
                        mBinding.patronErrorMsg.setVisibility(View.VISIBLE);
                        String msg = getString(R.string.double_quote) + AppUtils.getInstance()
                                .getEditTextValue(mBinding.patronEntryIncludeLayout.patronEntry) + getString(R.string.double_quote);
                        mBinding.patronErrorMsg.setText(getString(R.string.patron_not_found, msg));
                    }
                }
            });
        }
    }

    private void navigateToPatronListFragment(List<PatronList> patronList) {
        mNavigationListener.onNavigation(patronList, 0);
    }

    public void requestPatronId(PatronList patronItem) {
        getPatronInfo(patronItem.getBarcode());
    }

    private void setListener() {

        mBinding.patronEntryIncludeLayout.patronGoBtn.setOnClickListener(this);
        mBinding.itemRelativeLayout.setOnClickListener(this);
        mBinding.closeBtn.setOnClickListener(this);
        mBinding.holdRelativeLayout.setOnClickListener(this);
        mBinding.fineRelativeLayout.setOnClickListener(this);
    }

    @Override
    public void onNavigation(int position) {

    }

    @Override
    public void setToolBarTitle(String titleText) {
        mActivity.setTitleBar(titleText);

    }

    @Override
    public void onNavigation(Object model, int position) {
        if (position == 0 && model != null) {  //Show patron list
            navigateToPatronList((ArrayList<PatronList>) model, true);
        } else if (position == 1 && model != null) {  // pop patron list
            popFragmentFromBackStack(mPatronListFragment);
            requestPatronId((PatronList) model);
        } else if (position == 2 && model != null) {    // PatronItemCheckoutFragment checkout
            navigateToPatronCheckout((PatronInfo) model, true, getString(R.string.item_checkout_label));
        } else if (position == 3 && model != null) {    // PatronItemCheckoutFragment hold
            navigateToPatronCheckout((PatronInfo) model, true, getString(R.string.on_hold_label));
        } else if (position == 4 && model != null) {    // FineListFragment Fine
            navigateToFineList((PatronInfo) model, true, getString(R.string.fine_label));
        } else if (position == 5) {
            navigateToTitleDetail((CustomCheckoutItem) model);
        }
    }

    private void navigateToTitleDetail(CustomCheckoutItem checkoutItem) {
        if (checkoutItem != null) {
            Intent titleIntent = new Intent(getActivity(), TitleInfoActivity.class);
            titleIntent.putExtra("bibID", String.valueOf(checkoutItem.getId()));
            startActivity(titleIntent);
        }
    }

    private void popFragmentFromBackStack(Fragment fragment) {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction trans = manager.beginTransaction();
        trans.remove(fragment);
        trans.commit();
        manager.popBackStack();
    }


    private void navigateToPatronList(ArrayList<PatronList> patronList, boolean isAddToBackStack) {

        mPatronListFragment = PatronListFragment.newInstance(patronList);
        setToolBarTitle(getString(R.string.selectPatron));

        final FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction()
                .add(R.id.base_container, mPatronListFragment);
        if (isAddToBackStack) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();
    }

    private void navigateToPatronCheckout(PatronInfo patronInfo, boolean isAddToBackStack, String title) {

        PatronItemCheckoutFragment patronItemCheckoutFragment = PatronItemCheckoutFragment.newInstance(patronInfo, title);
        setToolBarTitle(title);

        final FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction()
                .add(R.id.base_container, patronItemCheckoutFragment);
        if (isAddToBackStack) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();
    }

    private void navigateToFineList(PatronInfo patronInfo, boolean isAddToBackStack, String title) {

        PatronFineListFragment patronFineListFragment = PatronFineListFragment.newInstance(patronInfo);
        setToolBarTitle(title);

        final FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction()
                .add(R.id.base_container, patronFineListFragment);
        if (isAddToBackStack) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();
    }
}
