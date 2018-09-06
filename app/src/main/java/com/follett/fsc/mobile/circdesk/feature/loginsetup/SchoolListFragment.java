/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.loginsetup;

import com.follett.fsc.mobile.circdesk.BR;
import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.data.remote.repository.AppRemoteRepository;
import com.follett.fsc.mobile.circdesk.databinding.FragmentSchoolListBinding;
import com.follett.fsc.mobile.circdesk.app.CTAButtonListener;
import com.follett.fsc.mobile.circdesk.utils.FollettLog;
import com.follett.fsc.mobile.circdesk.app.base.BaseFragment;

import android.arch.lifecycle.Observer;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

public class SchoolListFragment extends BaseFragment<FragmentSchoolListBinding, SchoolListViewModel> implements CTAButtonListener {
    
    private static final String TAG = LoginFragment.class.getSimpleName();
    
    private SchoolListViewModel mViewModel;
    
    private NavigationListener navigationListener;
    
    
    public static SchoolListFragment newInstance() {
        Bundle args = new Bundle();
        SchoolListFragment fragment = new SchoolListFragment();
        fragment.setArguments(args);
        return fragment;
    }
    
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            navigationListener = (NavigationListener) context;
        } catch (ClassCastException ex) {
            FollettLog.e(TAG, "ClassCastException");
        }
    }
    
    @Override
    public int getLayoutId() {
        return R.layout.fragment_school_list;
    }
    
    @Override
    public SchoolListViewModel getViewModel() {
        mViewModel = new SchoolListViewModel(getBaseActivity().getApplication(), new AppRemoteRepository());
        return mViewModel;
    }
    
    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }
    
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FragmentSchoolListBinding binding = getViewDataBinding();
        inItView(binding);
    }
    
    public void inItView(final FragmentSchoolListBinding lBinding) {
    
        lBinding.setCtaListener(this);
        lBinding.schoolRecyclerview.setLayoutManager(new LinearLayoutManager(getBaseActivity()));
        
        mViewModel.siteResult.observe(this, new Observer<SiteResults>() {
            @Override
            public void onChanged(@Nullable SiteResults siteResults) {
                SchoolListAdapter adapter = new SchoolListAdapter(getBaseActivity(), siteResults.sites);
                lBinding.schoolRecyclerview.setAdapter(adapter);
            }
        });
    }
    
    @Override
    public void onDetach() {
        navigationListener.setToolBarTitle(getActivity().getString(R.string.connect_your_school_label));
        super.onDetach();
    }
    
    @Override
    public void ctaButtonOnClick(View view) {
        getBaseActivity().onBackPressed();
    }
}
