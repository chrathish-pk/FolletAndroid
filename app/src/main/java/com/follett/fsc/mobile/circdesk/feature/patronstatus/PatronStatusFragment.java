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
import com.follett.fsc.mobile.circdesk.feature.patronstatus.model.AssetCheckOut;
import com.follett.fsc.mobile.circdesk.feature.patronstatus.model.Checkout;
import com.follett.fsc.mobile.circdesk.feature.patronstatus.model.PatronInfo;
import com.follett.fsc.mobile.circdesk.feature.patronstatus.model.PatronList;
import com.follett.fsc.mobile.circdesk.utils.AppUtils;
import com.follett.fsc.mobile.circdesk.utils.FollettLog;

import android.arch.lifecycle.Observer;
import android.content.Context;
import android.content.Intent;
import android.databinding.BindingAdapter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import java.util.List;

public class PatronStatusFragment extends BaseFragment<FragmentPatronStatusBinding, PatronStatusViewModel> implements View.OnClickListener {
    
    private static final String TAG = PatronStatusFragment.class.getSimpleName();
    
    private PatronStatusViewModel mViewModel;
    
    private FragmentPatronStatusBinding mBinding;
    
    private PatronInfo mPatronInfo;
    
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
        mBinding.itemRelativeLayout.setOnClickListener(this);
        mBinding.closeBtn.setOnClickListener(this);
        mBinding.holdRelativeLayout.setOnClickListener(this);
        mBinding.fineRelativeLayout.setOnClickListener(this);
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
            mBinding.patronDetailLayout.setVisibility(View.GONE);
           getPatronInfo(AppUtils.getInstance()
                   .getEditTextValue(mBinding.patronEntryIncludeLayout.patronEntry));
           
        } else if (v == mBinding.closeBtn) {
            mBinding.patronDetailLayout.setVisibility(View.GONE);
        } else if (v == mBinding.itemRelativeLayout) {
            if (mPatronInfo != null) {
                if (!mPatronInfo.getCheckouts().isEmpty() ||
                        !mPatronInfo.getAssetCheckOuts().isEmpty()) {
                    mNavigationListener.onNavigation(mPatronInfo, 2);
    
                }
            }
        } else if (v == mBinding.holdRelativeLayout) {
            if (mPatronInfo != null) {
                if (!mPatronInfo.getHolds().isEmpty()) {
                    mNavigationListener.onNavigation(mPatronInfo, 3);
                }
            }
        } else if (v == mBinding.fineRelativeLayout) {
            if (mPatronInfo != null) {
                if (!mPatronInfo.getFines().isEmpty()) {
                    mNavigationListener.onNavigation(mPatronInfo, 4);
                }
            }
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
    
    
    @BindingAdapter(value = {"overDueCount"})
    public static void setOverdueCount(@NonNull TextView textView, @NonNull PatronInfo patronInfo) {
        int overDueCount = 0;
        if (patronInfo != null) {
            for (AssetCheckOut assetCheckOut : patronInfo.getAssetCheckOuts()) {
                if (assetCheckOut.getOverDue()) {
                    overDueCount++;
                }
            }
            for (Checkout checkout : patronInfo.getCheckouts()) {
                if (checkout.getOverDue()) {
                    overDueCount++;
                }
            }
        }
       
        textView.setText(String.valueOf(overDueCount));
    }


}
