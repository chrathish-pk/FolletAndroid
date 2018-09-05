/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.patronstatus;

import com.follett.fsc.mobile.circdesk.BR;
import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.app.base.BaseFragment;
import com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences;
import com.follett.fsc.mobile.circdesk.databinding.FragmentPatronStatusBinding;
import com.follett.fsc.mobile.circdesk.feature.checkoutcheckin.CheckoutResult;
import com.follett.fsc.mobile.circdesk.feature.checkoutcheckin.PatronListActivity;
import com.follett.fsc.mobile.circdesk.feature.loginsetup.NavigationListener;
import com.follett.fsc.mobile.circdesk.feature.patronstatus.model.PatronInfo;
import com.follett.fsc.mobile.circdesk.feature.patronstatus.model.PatronList;
import com.follett.fsc.mobile.circdesk.utils.AppUtils;
import com.follett.fsc.mobile.circdesk.utils.FollettLog;

import android.arch.lifecycle.Observer;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.EditorInfo;

import java.util.List;

public class PatronStatusFragment extends BaseFragment<FragmentPatronStatusBinding, PatronStatusViewModel> implements View.OnClickListener {
    
    private static final String TAG = PatronStatusFragment.class.getSimpleName();
    
    private PatronStatusViewModel mViewModel;
    private FragmentPatronStatusBinding mBinding;
    private CheckoutResult checkoutResult = null;
    
    private NavigationListener mNavigationListener;
    
    public static PatronStatusFragment newInstance() {
        Bundle args = new Bundle();
        PatronStatusFragment fragment = new PatronStatusFragment();
        fragment.setArguments(args);
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
        inItView();
    }
    
    
    private void inItView() {
        mBinding.patronEntryIncludeLayout.patronGoBtn.setOnClickListener(this);
        mBinding.closeBtn.setOnClickListener(this);
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
                updateUI(patronInfo);
            }
        });

//        mViewModel.getPatronInfo().observe(this, new Observer<PatronInfo>() {
//            @Override
//            public void onChanged(@Nullable PatronInfo patronInfo) {
//
//            }
//        });
    }
    
    @Override
    public void onClick(View v) {
        if (v == mBinding.patronEntryIncludeLayout.patronGoBtn) {
           getPatronInfo(AppUtils.getInstance()
                   .getEditTextValue(mBinding.patronEntryIncludeLayout.patronEntry));
           
        } else if (v == mBinding.closeBtn) {
            mBinding.patronDetailLayout.setVisibility(View.GONE);
        }


//        else if (v.getId() == R.id.checkoutCloseBtn && mBinding.patronDetailIncludeLayout.patronDetailLayout.getVisibility() == View.VISIBLE) {
//            AppSharedPreferences.getInstance(getActivity()).setString(AppSharedPreferences.KEY_BARCODE, null);
//            AppSharedPreferences.getInstance(getActivity()).setString(AppSharedPreferences.KEY_PATRON_ID, null);
//            AppSharedPreferences.getInstance(getActivity()).setString(AppSharedPreferences.KEY_SELECTED_BARCODE, null);
//            mBinding.patronDetailIncludeLayout.patronDetailLayout.setVisibility(View.GONE);
//            mBinding.checkoutDetailIncludeLayout.checkedoutDetailLayout.setVisibility(View.GONE);
//            mBinding.checkoutPatronErrorMsg.setVisibility(View.GONE);
//            mBinding.patronEntryIncludeLayout.patronEntry.setText("");
//        } else if (v.getId() == R.id.checkedoutInfoBtn) {
//
//            Intent titleIntent = new Intent(getActivity(), TitleInfoActivity.class);
//            titleIntent.putExtra("bibID", checkoutResult.getInfo().getBibID());
//            startActivity(titleIntent);
//
//        }
    }
    
    private void getPatronInfo(String patronID) {
        AppUtils.getInstance()
                .hideKeyBoard(getBaseActivity(), mBinding.patronEntryIncludeLayout.patronEntry);
        mViewModel.getPatronInfo(patronID);
    }

//    public void getPatronID() {
//        if (mViewModel != null) {
//            String selectedBarcode = AppSharedPreferences.getInstance(getActivity())
//                    .getString(AppSharedPreferences.KEY_SELECTED_BARCODE);
//            if (TextUtils.isEmpty(selectedBarcode)) {
//                mViewModel.getScanPatron(mBinding.patronEntryIncludeLayout.patronEntry.getText()
//                        .toString()
//                        .trim());
//            } else { mViewModel.getScanPatron(selectedBarcode); }
//        }
//    }
    
    
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
    
//        getActivity().runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//
//
////                else if (patronInfo != null && patronInfo instanceof CheckoutResult) {
////                    checkoutResult = (CheckoutResult) patronInfo;
////                    if (checkoutResult.getSuccess()) {
////                        bindCheckoutResult(checkoutResult);
////                    } else {
////                        updateCheckoutErrorMsg(checkoutResult);
////                    }
////                }
//
//    }
//    }

//    private void updateCheckoutErrorMsg(CheckoutResult checkoutResult) {
//
//        if (checkoutResult != null) {
//            mBinding.checkoutPatronErrorMsg.setVisibility(View.VISIBLE);
//            if (checkoutResult.getMessages().size() > 1) {
//                String errorMsg = checkoutResult.getMessages().get(0).getMessage() + "\n" + checkoutResult.getMessages().get(1).getMessage();
//                mBinding.checkoutPatronErrorMsg.setText(errorMsg);
//            } else {
//                mBinding.checkoutPatronErrorMsg.setText(checkoutResult.getMessages().get(0).getMessage());
//            }
//        }
//
//    }

//    private void bindCheckoutResult(CheckoutResult checkoutResult) {
//        try {
//            if (mBinding.checkoutPatronErrorMsg.getVisibility() == View.VISIBLE)
//                mBinding.checkoutPatronErrorMsg.setVisibility(View.GONE);
//            mBinding.checkoutDetailIncludeLayout.checkedoutDetailLayout.setVisibility(View.VISIBLE);
//            mBinding.checkoutDetailIncludeLayout.checkedoutName.setText(checkoutResult.getInfo().getTitle());
//            mBinding.checkoutDetailIncludeLayout.checkedoutDue.setText(checkoutResult.getInfo().getDueDate());
//            mBinding.checkoutDetailIncludeLayout.checkedoutType.setText(checkoutResult.getInfo().getBarcode());
//        } catch (Exception e) {
//            FollettLog.e(getString(R.string.error), e.getMessage());
//        }
//    }

//    private void updatePatronErrorMsg(ScanPatron scanPatron) {
//        mBinding.checkoutPatronErrorMsg.setVisibility(View.VISIBLE);
//        mBinding.checkoutPatronErrorMsg.setText(scanPatron.getMessages().get(0).getMessage());
//    }

//    public void updatePatronView() {
//
//
//        if (scanPatron != null) {
//            mBinding.patronDetailIncludeLayout.patronDetailLayout.setVisibility(View.VISIBLE);
//            mBinding.patronEntryIncludeLayout.patronEntry.setText("");
//            if (mBinding.checkoutPatronErrorMsg.getVisibility() == View.VISIBLE)
//                mBinding.checkoutPatronErrorMsg.setVisibility(View.GONE);
//            if (AppSharedPreferences.getInstance(getActivity()).getBoolean(AppSharedPreferences.KEY_IS_LIBRARY_SELECTED)) {
//                String checoutValue = getString(R.string.checkoutLabel) + scanPatron.getLibraryCheckouts();
//                mBinding.patronDetailIncludeLayout.checkedOutCount.setText(checoutValue);
//                String overdueValue = getString(R.string.overdueLabel) + scanPatron.getLibraryOverdues();
//                mBinding.patronDetailIncludeLayout.overdue.setText(overdueValue);
//            } else {
//                String checoutValue = getString(R.string.checkoutLabel) + scanPatron.getAssetCheckouts();
//                mBinding.patronDetailIncludeLayout.checkedOutCount.setText(checoutValue);
//                String overdueValue = getString(R.string.overdueLabel) + scanPatron.getAssetOverdues();
//                mBinding.patronDetailIncludeLayout.overdue.setText(overdueValue);
//            }
//            mBinding.patronDetailIncludeLayout.checkoutPatronName.setText(scanPatron.getLastFirstMiddleName());
//            mBinding.patronDetailIncludeLayout.checkoutPatronID.setText(scanPatron.getPatronID());
//            mBinding.patronDetailIncludeLayout.checkoutPatronType.setText(scanPatron.getPatronType());
//
//            GlideApp.with(this)
//                    .load(scanPatron.getPatronPictureFileName())
//                    .placeholder(R.drawable.inventory)
//                    .into(mBinding.patronDetailIncludeLayout.checkoutPatronImg);
//        } else {
//            mBinding.patronDetailIncludeLayout.patronDetailLayout.setVisibility(View.GONE);
//        }
//    }



}
