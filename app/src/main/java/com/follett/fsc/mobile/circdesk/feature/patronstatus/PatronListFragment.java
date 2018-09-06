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
import com.follett.fsc.mobile.circdesk.data.remote.repository.AppRemoteRepository;
import com.follett.fsc.mobile.circdesk.databinding.FragmentPatronListBinding;
import com.follett.fsc.mobile.circdesk.databinding.FragmentSchoolListBinding;
import com.follett.fsc.mobile.circdesk.feature.loginsetup.LoginFragment;
import com.follett.fsc.mobile.circdesk.feature.loginsetup.NavigationListener;
import com.follett.fsc.mobile.circdesk.feature.loginsetup.SchoolListAdapter;
import com.follett.fsc.mobile.circdesk.feature.loginsetup.SchoolListViewModel;
import com.follett.fsc.mobile.circdesk.feature.loginsetup.SiteResults;
import com.follett.fsc.mobile.circdesk.feature.patronstatus.model.PatronList;
import com.follett.fsc.mobile.circdesk.utils.FollettLog;

import android.arch.lifecycle.Observer;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import static com.follett.fsc.mobile.circdesk.utils.AppConstants.PATRON_LIST_KEY;

public class PatronListFragment extends BaseFragment<FragmentPatronListBinding, PatronListViewModel> implements CTAButtonListener {
    
    private static final String TAG = PatronListFragment.class.getSimpleName();
    
    private PatronListViewModel mViewModel;
    
    private NavigationListener navigationListener;
    
    private List<PatronList> mPatronList;
    
    
    public static PatronListFragment newInstance(ArrayList<PatronList> patronList) {
        Bundle args = new Bundle();
        args.putParcelableArrayList(PATRON_LIST_KEY, patronList);
        PatronListFragment fragment = new PatronListFragment();
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
    
        final Bundle arguments = getArguments();
        if (arguments != null) {
            mPatronList = arguments.getParcelableArrayList(PATRON_LIST_KEY);
            lBinding.patronListRecyclerview.setLayoutManager(new LinearLayoutManager(getBaseActivity()));
            PatronListAdapter adapter = new PatronListAdapter(getBaseActivity(), mPatronList);
            lBinding.patronListRecyclerview.setAdapter(adapter);
        }
    }
    
    @Override
    public void onDetach() {
        navigationListener.setToolBarTitle(getActivity().getString(R.string.patron_status_label));
        super.onDetach();
    }
    
    @Override
    public void ctaButtonOnClick(View view) {
        getBaseActivity().onBackPressed();
    }
}

