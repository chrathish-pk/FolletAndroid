/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.patronstatus.view;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.follett.fsc.mobile.circdesk.BR;
import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.app.CTAButtonListener;
import com.follett.fsc.mobile.circdesk.app.base.BaseFragment;
import com.follett.fsc.mobile.circdesk.databinding.FragmentPatronListBinding;
import com.follett.fsc.mobile.circdesk.feature.loginsetup.view.NavigationListener;
import com.follett.fsc.mobile.circdesk.feature.patronstatus.model.PatronInfo;
import com.follett.fsc.mobile.circdesk.feature.patronstatus.viewmodel.PatronListViewModel;
import com.follett.fsc.mobile.circdesk.utils.FollettLog;

import static com.follett.fsc.mobile.circdesk.utils.AppConstants.PATRON_INFO_KEY;

public class PatronFineListFragment extends BaseFragment<FragmentPatronListBinding, PatronListViewModel> implements CTAButtonListener, NavigationListener, View.OnClickListener {
    
    private static final String TAG = PatronFineListFragment.class.getSimpleName();

    private NavigationListener navigationListener;

    public static PatronFineListFragment newInstance(PatronInfo patronInfo) {
        Bundle args = new Bundle();
        args.putParcelable(PATRON_INFO_KEY, patronInfo);
        PatronFineListFragment fragment = new PatronFineListFragment();
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
        Application application = getBaseApplication();
        if (application == null) {
            return null;
        }
        return new PatronListViewModel(application);
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
        if (getBaseActivity() == null) {
            return;
        }
        lBinding.patronListRecyclerview.setLayoutManager(new LinearLayoutManager(getBaseActivity()));
        final Bundle arguments = getArguments();
        if (arguments != null) {
            PatronInfo patronInfo = arguments.getParcelable(PATRON_INFO_KEY);
            PatronFineListAdapter adapter = new PatronFineListAdapter(getBaseActivity(), patronInfo.getFines(),this);
            lBinding.patronListRecyclerview.setAdapter(adapter);
        }
    }
    
    @Override
    public void ctaButtonOnClick(View view) {
        Activity activity = getBaseActivity();
        if (activity != null) {
            activity.onBackPressed();
        }
    }

    @Override
    public void onNavigation(int position) {
        //do nothing
    }

    @Override
    public void setToolBarTitle(String titleText) {
        //do nothing
    }

    @Override
    public void onNavigation(Object model, int position) {
        //do nothing
    }

    @Override
    public void onClick(View v) {
        mActivity.setBackBtnVisible();
        mActivity.onBackPressed();
    }
}

