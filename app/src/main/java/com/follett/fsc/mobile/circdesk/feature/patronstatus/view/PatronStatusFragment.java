/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.patronstatus.view;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.EditorInfo;

import com.follett.fsc.mobile.circdesk.BR;
import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.app.base.BaseFragment;
import com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences;
import com.follett.fsc.mobile.circdesk.databinding.FragmentPatronStatusBinding;
import com.follett.fsc.mobile.circdesk.feature.iteminfo.view.TitleInfoActivity;
import com.follett.fsc.mobile.circdesk.feature.itemstatus.view.UpdateItemUIListener;
import com.follett.fsc.mobile.circdesk.feature.loginsetup.model.Permissions;
import com.follett.fsc.mobile.circdesk.feature.loginsetup.view.NavigationListener;
import com.follett.fsc.mobile.circdesk.feature.patronstatus.model.CustomCheckoutItem;
import com.follett.fsc.mobile.circdesk.feature.patronstatus.model.PatronInfo;
import com.follett.fsc.mobile.circdesk.feature.patronstatus.model.PatronList;
import com.follett.fsc.mobile.circdesk.feature.patronstatus.viewmodel.PatronStatusViewModel;
import com.follett.fsc.mobile.circdesk.utils.AppUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class PatronStatusFragment extends BaseFragment<FragmentPatronStatusBinding, PatronStatusViewModel> implements NavigationListener, View.OnClickListener, UpdateItemUIListener {

    private PatronStatusViewModel mViewModel;

    private FragmentPatronStatusBinding mBinding;

    private PatronInfo mPatronInfo;

    private PatronListFragment mPatronListFragment;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_patron_status;
    }

    @Override
    public PatronStatusViewModel getViewModel() {
        mViewModel = new PatronStatusViewModel(getBaseApplication(), this);
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
        //mActivity.setTitleBar(getString(R.string.patron_status_label));
        //mActivity.setBackBtnVisible();
        //mActivity.baseBinding.backBtn.setOnClickListener(this);

        showItemCheckedoutView();
        inItView();
    }

    private void showItemCheckedoutView() {
        String permissionValue = AppSharedPreferences.getInstance().getString(AppSharedPreferences.KEY_PERMISSIONS);
        Permissions permissions = new Gson().fromJson(permissionValue, Permissions.class);
        if (permissions != null && Boolean.parseBoolean(permissions.getCanViewItemsOutAsset()) || permissions != null && Boolean.parseBoolean(permissions.getCanViewItemsOutLibrary()))
            mBinding.itemRelativeLayout.setVisibility(View.VISIBLE);
        else
            mBinding.itemRelativeLayout.setVisibility(View.GONE);
    }

    private void inItView() {
        if (getBaseActivity() == null) {
            return;
        }
        mBinding.patronEntryIncludeLayout.checkinLibRecordSwitch.setVisibility(View.GONE);
        mBinding.patronEntryIncludeLayout.patronEntry.setImeOptions(EditorInfo.IME_ACTION_DONE);
        mViewModel.mPatronInfo.observe(this, new Observer<PatronInfo>() {
            @Override
            public void onChanged(@Nullable PatronInfo patronInfo) {
                //do nothing
            }
        });

        if (!TextUtils.isEmpty(AppSharedPreferences.getInstance().getString(AppSharedPreferences.KEY_SELECTED_BARCODE))) {
            getPatronInfo(AppSharedPreferences.getInstance().getString(AppSharedPreferences.KEY_SELECTED_BARCODE));
        } else {
            if (!TextUtils.isEmpty(mBinding.patronEntryIncludeLayout.patronEntry.getText()))
                getPatronInfo(AppUtils.getInstance()
                        .getEditTextValue(mBinding.patronEntryIncludeLayout.patronEntry));
        }

        setListener();
    }

    @Override
    public void onClick(View v) {
        if (v == mBinding.patronEntryIncludeLayout.patronGoBtn) {
            mBinding.patronDetailLayout.setVisibility(View.GONE);
            getPatronInfo(AppUtils.getInstance()
                    .getEditTextValue(mBinding.patronEntryIncludeLayout.patronEntry));

        } else if (v == mBinding.closeBtn) {
            AppSharedPreferences.getInstance().setString(AppSharedPreferences.KEY_SELECTED_BARCODE, "");
            mBinding.patronDetailLayout.setVisibility(View.GONE);
        } else if (v == mBinding.itemRelativeLayout) {
            if (mPatronInfo != null && (!mPatronInfo.getCheckouts()
                    .isEmpty() || !mPatronInfo.getAssetCheckOuts()
                    .isEmpty())) {
                onNavigation(mPatronInfo, 2);
            }
        } else if (v == mBinding.holdRelativeLayout) {
            if (mPatronInfo != null && !mPatronInfo.getHolds()
                    .isEmpty()) {
                onNavigation(mPatronInfo, 3);
            }
        } else if (v == mBinding.fineRelativeLayout) {
            if (mPatronInfo != null && !mPatronInfo.getFines()
                    .isEmpty()) {
                onNavigation(mPatronInfo, 4);
            }
        } else if (v.getId() == R.id.backBtn) {
            mActivity.setTitleBar(getString(R.string.home));
            AppUtils.getInstance()
                    .hideKeyBoard(mActivity, mBinding.patronEntryIncludeLayout.patronEntry);
            mActivity.baseBinding.backBtn.setVisibility(View.GONE);
            mActivity.onBackPressed();
        }
    }

    private void getPatronInfo(String patronID) {
        if (getBaseActivity() == null) {
            return;
        }
        AppUtils.getInstance()
                .hideKeyBoard(getBaseActivity(), mBinding.patronEntryIncludeLayout.patronEntry);

        if (!isNetworkConnected()) {
            AppUtils.getInstance()
                    .showNoInternetAlertDialog(mActivity);
            return;
        }
        mViewModel.getPatronInfo(patronID);
    }

    private void updateUI(final PatronInfo patronInfo) {
        if (null != mActivity) {
            mActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (patronInfo != null && patronInfo.getSuccess()) {
                        if (patronInfo.getPatronList() != null) {
                            navigateToPatronListFragment(patronInfo.getPatronList());
                        } else {
                            mBinding.setPatronInfo(patronInfo);
                            rightArrowDisable(patronInfo);
                        }
                    } else if (patronInfo != null && !patronInfo.getSuccess()) {
                        String msg = getString(R.string.double_quote) + AppUtils.getInstance()
                                .getEditTextValue(mBinding.patronEntryIncludeLayout.patronEntry) + getString(R.string.double_quote);
                        mBinding.patronErrorMsg.setText(getString(R.string.patron_not_found, msg));
                    }
                }
            });
        }
    }

    private void rightArrowDisable(PatronInfo patronInfo) {

        if (patronInfo.getHolds().isEmpty()) {
            mBinding.titleHoldTextView.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        } else {
            mBinding.titleHoldTextView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.right_arrow, 0);
        }
        if(patronInfo.getCheckouts().isEmpty())
        {
            mBinding.itemTextView.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        } else {
            mBinding.itemTextView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.right_arrow, 0);
        }
        if(patronInfo.getFines().isEmpty())
        {
            mBinding.fineTextView.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        } else {
            mBinding.fineTextView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.right_arrow, 0);
        }
    }

    private void navigateToPatronListFragment(List<PatronList> patronList) {
        onNavigation(patronList, 0);
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
        // do nothing
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
            mActivity.popFragment("PatronListFrgment");
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
        Activity activity = getBaseActivity();
        if (activity != null && checkoutItem != null) {
            if (!isNetworkConnected()) {
                AppUtils.getInstance()
                        .showNoInternetAlertDialog(activity);
                return;
            }
            Intent titleIntent = new Intent(activity, TitleInfoActivity.class);
            titleIntent.putExtra("bibID", String.valueOf(checkoutItem.getId()));
            startActivity(titleIntent);
        }
    }

    private void navigateToPatronList(ArrayList<PatronList> patronList, boolean isAddToBackStack) {
        mPatronListFragment = PatronListFragment.newInstance(patronList);
       // setToolBarTitle(getString(R.string.selectPatron));
        mActivity.replaceFragment(mPatronListFragment, R.id.loginContainer, getString(R.string.patron_status_label), true,true);
    }

    private void navigateToPatronCheckout(PatronInfo patronInfo, boolean isAddToBackStack, String title) {
        PatronItemCheckoutFragment patronItemCheckoutFragment = PatronItemCheckoutFragment.newInstance(patronInfo, title);
        //mActivity.setBackBtnVisible();
        //setToolBarTitle(title);
        mActivity.pushFragment(patronItemCheckoutFragment, R.id.loginContainer, getString(R.string.item_checkout_label), true,true);
    }

    private void navigateToFineList(PatronInfo patronInfo, boolean isAddToBackStack, String title) {
        PatronFineListFragment patronFineListFragment = PatronFineListFragment.newInstance(patronInfo);
        //mActivity.setBackBtnVisible();
        //setToolBarTitle(title);
        mActivity.pushFragment(patronFineListFragment, R.id.loginContainer, getString(R.string.fine_label), true,true);
    }


    @Override
    public void updateUI(Object value) {
        mPatronInfo = (PatronInfo) value;
        updateUI(mPatronInfo);
    }

    }
