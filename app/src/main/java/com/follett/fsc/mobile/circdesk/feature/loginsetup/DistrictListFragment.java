/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.loginsetup;

import com.follett.fsc.mobile.circdesk.BR;
import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.app.CTAButtonListener;
import com.follett.fsc.mobile.circdesk.app.base.BaseFragment;
import com.follett.fsc.mobile.circdesk.data.remote.repository.AppRemoteRepository;
import com.follett.fsc.mobile.circdesk.databinding.FragmentDistrictListBinding;
import com.follett.fsc.mobile.circdesk.feature.loginsetup.model.DistrictList;
import com.follett.fsc.mobile.circdesk.utils.FollettLog;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import static com.follett.fsc.mobile.circdesk.utils.AppConstants.DISTRICT_LIST_KEY;

public class DistrictListFragment extends BaseFragment<FragmentDistrictListBinding, DistrictListViewModel> implements CTAButtonListener {
    
    private static final String TAG = DistrictListFragment.class.getSimpleName();
    
    private DistrictListViewModel mViewModel;
    
    private NavigationListener navigationListener;
    
    
    public static DistrictListFragment newInstance(DistrictList districtList) {
        Bundle args = new Bundle();
        args.putParcelable(DISTRICT_LIST_KEY, districtList);
        DistrictListFragment fragment = new DistrictListFragment();
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
        return R.layout.fragment_district_list;
    }
    
    @Override
    public DistrictListViewModel getViewModel() {
        if (getBaseApplication() == null) {
            return null;
        }
        mViewModel = new DistrictListViewModel(getBaseApplication());
        return mViewModel;
    }
    
    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }
    
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FragmentDistrictListBinding binding = getViewDataBinding();
        inItView(binding);
    }
    
    public void inItView(final FragmentDistrictListBinding lBinding) {
        if (getBaseActivity() == null) {
            return;
        }
        
        lBinding.districtRecyclerview.setLayoutManager(new LinearLayoutManager(getBaseActivity()));
        lBinding.setCtaListener(this);
        final Bundle arguments = getArguments();
        if (null != arguments) {
            DistrictList districtList = arguments.getParcelable(DISTRICT_LIST_KEY);
            DistrictListAdapter adapter = new DistrictListAdapter(getBaseActivity(), districtList);
            lBinding.districtRecyclerview.setAdapter(adapter);
        }


//        lBinding.schoolRecyclerview.setLayoutManager(new LinearLayoutManager(getBaseActivity()));

//        mViewModel.siteResult.observe(this, new Observer<SiteResults>() {
//            @Override
//            public void onChanged(@Nullable SiteResults siteResults) {
//                SchoolListAdapter adapter = new SchoolListAdapter(getBaseActivity(), siteResults.sites);
//                lBinding.schoolRecyclerview.setAdapter(adapter);
//            }
//        });
    }
    
    @Override
    public void onDetach() {
        navigationListener.setToolBarTitle(getString(R.string.connect_your_school_label));
        super.onDetach();
    }
    
    @Override
    public void ctaButtonOnClick(View view) {
        mViewModel.clearDistrictPref();
        AppRemoteRepository.mInstance = null;
        Activity activity = getBaseActivity();
        if (activity != null) {
            mActivity.pushFragment(new SetupFragment(), R.id.loginContainer, "SetupFragment", false);
        }
    }
}

