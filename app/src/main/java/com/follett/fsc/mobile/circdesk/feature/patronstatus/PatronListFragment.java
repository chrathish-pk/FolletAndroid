/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.patronstatus;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.follett.fsc.mobile.circdesk.BR;
import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.app.base.BaseFragment;
import com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences;
import com.follett.fsc.mobile.circdesk.databinding.FragmentPatronListBinding;
import com.follett.fsc.mobile.circdesk.feature.loginsetup.NavigationListener;
import com.follett.fsc.mobile.circdesk.feature.patronstatus.model.PatronList;
import com.follett.fsc.mobile.circdesk.utils.FollettLog;

import java.util.ArrayList;
import java.util.List;

import static com.follett.fsc.mobile.circdesk.utils.AppConstants.PATRON_LIST_KEY;

public class PatronListFragment extends BaseFragment<FragmentPatronListBinding, PatronListViewModel> implements View.OnClickListener, NavigationListener {

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
        mActivity.baseBinding.backBtn.setOnClickListener(this);
        inItView(binding);
    }

    public void inItView(final FragmentPatronListBinding lBinding) {

        final Bundle arguments = getArguments();
        if (arguments != null) {
            List<PatronList> patronList = arguments.getParcelableArrayList(PATRON_LIST_KEY);
            lBinding.patronListRecyclerview.setLayoutManager(new LinearLayoutManager(getBaseActivity()));
            PatronListAdapter adapter = new PatronListAdapter(getBaseActivity(), patronList,this);
            lBinding.patronListRecyclerview.setAdapter(adapter);
        }
    }

    @Override
    public void onDetach() {
        navigationListener.setToolBarTitle(getActivity().getString(R.string.patron_status_label));
        super.onDetach();
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.backBtn) {
           mActivity.onBackPressed();
        }
    }

    @Override
    public void onNavigation(int position) {

    }

    @Override
    public void setToolBarTitle(String titleText) {

    }

    @Override
    public void onNavigation(Object model, int position) {
        if (position == 1 && model != null) {  // pop patron list
            AppSharedPreferences.getInstance().setString(AppSharedPreferences.KEY_SELECTED_BARCODE,((PatronList)model).getBarcode());
            mActivity.onBackPressed();
        }
    }
}

