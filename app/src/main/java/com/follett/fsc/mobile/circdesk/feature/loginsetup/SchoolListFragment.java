/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.loginsetup;

import com.follett.fsc.mobile.circdesk.BR;
import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.app.CTAButtonListener;
import com.follett.fsc.mobile.circdesk.app.CustomAlert;
import com.follett.fsc.mobile.circdesk.app.base.BaseFragment;
import com.follett.fsc.mobile.circdesk.data.remote.apicommon.Status;
import com.follett.fsc.mobile.circdesk.data.remote.repository.AppRemoteRepository;
import com.follett.fsc.mobile.circdesk.databinding.FragmentSchoolListBinding;
import com.follett.fsc.mobile.circdesk.utils.FollettLog;

import android.arch.lifecycle.Observer;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

public class SchoolListFragment extends BaseFragment<FragmentSchoolListBinding, SchoolListViewModel> implements CTAButtonListener {
    
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
        mViewModel.getStatus()
                .observe(this, new Observer<Status>() {
                    @Override
                    public void onChanged(@Nullable Status status) {
                        handleStatus(status);
                    }
                });
        mViewModel.noSchoolFoundMsg.observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String msg) {
                showAlert(msg);
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
        mNavigationListener.setToolBarTitle(getActivity().getString(R.string.connect_your_school_label));
        super.onDetach();
    }
    
    @Override
    public void ctaButtonOnClick(View view) {
        mViewModel.clearSchoolPref();
        getBaseActivity().onBackPressed();
    }
    
    private void showAlert(String msg) {
        
        DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        dialog.dismiss();
                        break;
                    default:
                        break;
                }
                
            }
        };
        CustomAlert.showDialog(getBaseActivity(), null, msg, getString(R.string.ok), onClickListener, null, onClickListener);
        
    }
}

