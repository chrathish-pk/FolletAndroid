/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.patronstatus;

import com.follett.fsc.mobile.circdesk.BR;
import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.app.CTAButtonListener;
import com.follett.fsc.mobile.circdesk.app.base.BaseFragment;
import com.follett.fsc.mobile.circdesk.databinding.FragmentPatronListBinding;
import com.follett.fsc.mobile.circdesk.feature.loginsetup.NavigationListener;
import com.follett.fsc.mobile.circdesk.feature.patronstatus.model.CustomCheckoutItem;
import com.follett.fsc.mobile.circdesk.feature.patronstatus.model.PatronInfo;
import com.follett.fsc.mobile.circdesk.utils.FollettLog;

import android.arch.lifecycle.Observer;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import java.util.List;

import static com.follett.fsc.mobile.circdesk.utils.AppConstants.PATRON_INFO_KEY;
import static com.follett.fsc.mobile.circdesk.utils.AppConstants.PATRON_TITLE_KEY;

public class PatronItemCheckoutFragment extends BaseFragment<FragmentPatronListBinding, PatronListViewModel> implements CTAButtonListener {
    
    private static final String TAG = PatronItemCheckoutFragment.class.getSimpleName();
    
    private PatronListViewModel mViewModel;
    
    private NavigationListener mNavigationListener;
    
    
    public static PatronItemCheckoutFragment newInstance(PatronInfo patronInfo, String title) {
        Bundle args = new Bundle();
        args.putParcelable(PATRON_INFO_KEY, patronInfo);
        args.putString(PATRON_TITLE_KEY, title);
        PatronItemCheckoutFragment fragment = new PatronItemCheckoutFragment();
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
        return R.layout.fragment_patron_list;
    }
    
    @Override
    public PatronListViewModel getViewModel() {
        mViewModel = new PatronListViewModel(getBaseActivity().getApplication());
        return mViewModel;
    }
    
    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }
    
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FragmentPatronListBinding binding = getViewDataBinding();
        inItView(binding);
    }
    
    public void inItView(final FragmentPatronListBinding lBinding) {
        
        lBinding.patronListRecyclerview.setLayoutManager(new LinearLayoutManager(getBaseActivity()));
        mViewModel.createCheckoutModelData(getArguments());
        
        mViewModel.checkoutLiveData.observe(this, new Observer<List<CustomCheckoutItem>>() {
            @Override
            public void onChanged(@Nullable List<CustomCheckoutItem> customCheckoutItems) {
                PatronItemCheckoutAdapter adapter = new PatronItemCheckoutAdapter(getBaseActivity(), customCheckoutItems);
                lBinding.patronListRecyclerview.setAdapter(adapter);
            }
        });
    }
    
    @Override
    public void onDetach() {
        mNavigationListener.setToolBarTitle(getActivity().getString(R.string.patron_status_label));
        super.onDetach();
    }
    
    @Override
    public void ctaButtonOnClick(View view) {
        getBaseActivity().onBackPressed();
    }
}

