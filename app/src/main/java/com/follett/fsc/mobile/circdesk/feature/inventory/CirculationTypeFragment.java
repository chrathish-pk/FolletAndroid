/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.inventory;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.follett.fsc.mobile.circdesk.BR;
import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.app.ItemClickListener;
import com.follett.fsc.mobile.circdesk.app.base.BaseFragment;
import com.follett.fsc.mobile.circdesk.databinding.FragmentCirculationtypeLayoutBinding;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.CirculationTypeList;
import com.follett.fsc.mobile.circdesk.feature.inventory.viewmodel.CirculationTypeViewModel;
import com.follett.fsc.mobile.circdesk.BR;
import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;


public class CirculationTypeFragment extends BaseFragment<FragmentCirculationtypeLayoutBinding, CirculationTypeViewModel> implements ItemClickListener {

    private FragmentCirculationtypeLayoutBinding recyclerviewLayoutBinding;
    private CirculationTypeViewModel circulationTypeViewModel;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_circulationtype_layout;
    }

    @Override
    public CirculationTypeViewModel getViewModel() {
        circulationTypeViewModel = new CirculationTypeViewModel(getBaseActivity().getApplication());
        return circulationTypeViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerviewLayoutBinding = getViewDataBinding();

        recyclerviewLayoutBinding.recyclerviewList.setLayoutManager(new LinearLayoutManager(getActivity()));

        circulationTypeViewModel.fetchCirculationTypeList();

        circulationTypeViewModel.circulationTypeListMutableLiveData.observeForever(new Observer<CirculationTypeList>() {
            @Override
            public void onChanged(@Nullable CirculationTypeList circulationTypeList) {
                CirculationTypeListAdapter circulationTypeListAdapter = new CirculationTypeListAdapter(getActivity(), circulationTypeList, CirculationTypeFragment.this);
                recyclerviewLayoutBinding.recyclerviewList.setAdapter(circulationTypeListAdapter);
            }
        });

    }

    @Override
    public void onItemClick(View view, int position) {

    }
}
