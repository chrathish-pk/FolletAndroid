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
import com.follett.fsc.mobile.circdesk.feature.patronstatus.model.PatronList;
import com.follett.fsc.mobile.circdesk.utils.FollettLog;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import static com.follett.fsc.mobile.circdesk.utils.AppConstants.PATRON_LIST_KEY;

public class PatronListFragment extends BaseFragment<FragmentPatronListBinding, PatronListViewModel> implements CTAButtonListener {
    
    private static final String TAG = PatronListFragment.class.getSimpleName();
    
    private NavigationListener navigationListener;
    
    
    public static PatronListFragment newInstance(List<PatronList> patronList) {
        Bundle args = new Bundle();
        args.putParcelableArrayList(PATRON_LIST_KEY, (ArrayList<? extends Parcelable>) patronList);
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
        return new PatronListViewModel(getBaseActivity().getApplication());
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
            List<PatronList> patronList = arguments.getParcelableArrayList(PATRON_LIST_KEY);
            lBinding.patronListRecyclerview.setLayoutManager(new LinearLayoutManager(getBaseActivity()));
            PatronListAdapter adapter = new PatronListAdapter(getBaseActivity(), patronList);
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

