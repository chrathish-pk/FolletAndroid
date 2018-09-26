/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.loginsetup.view;

import com.follett.fsc.mobile.circdesk.BR;
import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.app.CTAButtonListener;
import com.follett.fsc.mobile.circdesk.app.base.BaseFragment;
import com.follett.fsc.mobile.circdesk.data.remote.apicommon.Status;
import com.follett.fsc.mobile.circdesk.databinding.FragmentSchoolListBinding;
import com.follett.fsc.mobile.circdesk.feature.loginsetup.model.SiteResults;
import com.follett.fsc.mobile.circdesk.feature.loginsetup.viewmodel.SchoolListViewModel;
import com.follett.fsc.mobile.circdesk.utils.AppUtils;
import com.follett.fsc.mobile.circdesk.utils.FollettLog;

import android.app.Activity;
import android.app.Application;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

public class SchoolListFragment extends BaseFragment<FragmentSchoolListBinding, SchoolListViewModel> implements CTAButtonListener, View.OnClickListener {
    
    private static final String TAG = LoginFragment.class.getSimpleName();
    
    private SchoolListViewModel mViewModel;
    
    private NavigationListener mNavigationListener;
    
    
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
            mNavigationListener = (NavigationListener) context;
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
        Application application = getBaseApplication();
        if (application == null) {
            return null;
        }
        mViewModel = new SchoolListViewModel(application);
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

        mActivity.baseBinding.backBtn.setOnClickListener(this);
        inItView(binding);
    }
    
    public void inItView(final FragmentSchoolListBinding lBinding) {
        
        if (getBaseActivity() == null) {
            return;
        }
        lBinding.setCtaListener(this);
        lBinding.schoolRecyclerview.setLayoutManager(new LinearLayoutManager(getBaseActivity()));
        
        mViewModel.siteResult.observe(this, new Observer<SiteResults>() {
            @Override
            public void onChanged(@Nullable SiteResults siteResults) {
                SchoolListAdapter adapter = new SchoolListAdapter(getBaseActivity(), siteResults.sites);
                lBinding.schoolRecyclerview.setAdapter(adapter);
            }
        });
        mViewModel.getStatus()
                .observe(this, new Observer<Status>() {
                    @Override
                    public void onChanged(@Nullable Status status) {
                        handleStatus(status);
                    }
                });
    }
    
    private void handleStatus(Status status) {
        if (Status.SUCCESS.equals(status)) {
            mNavigationListener.onNavigation(null, 2);
        }
    }
    
    @Override
    public void onDetach() {
        mNavigationListener.setToolBarTitle(getString(R.string.connect_your_school_label));
        super.onDetach();
    }
    
    @Override
    public void ctaButtonOnClick(View view) {
        AppUtils.getInstance().clearSchoolPref();
        Activity activity = getBaseActivity();
        if (activity != null) {
            activity.onBackPressed();
        }
    }

    @Override
    public void onClick(View v) {
        AppUtils.getInstance().clearSchoolPref();
        mActivity.onBackPressed();
    }

}

