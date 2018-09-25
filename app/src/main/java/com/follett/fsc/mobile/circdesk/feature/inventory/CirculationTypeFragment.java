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

import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.app.ItemClickListener;
import com.follett.fsc.mobile.circdesk.app.base.BaseFragment;
import com.follett.fsc.mobile.circdesk.databinding.RecyclerviewLayoutBinding;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.CirculationTypeList;

public class CirculationTypeFragment extends BaseFragment<RecyclerviewLayoutBinding, CirculationTypeViewModel> implements ItemClickListener {

    private RecyclerviewLayoutBinding recyclerviewLayoutBinding;
    private CirculationTypeViewModel circulationTypeViewModel;

    @Override
    public int getLayoutId() {
        return R.layout.recyclerview_layout;
    }

    @Override
    public CirculationTypeViewModel getViewModel() {
        circulationTypeViewModel = new CirculationTypeViewModel(getBaseActivity().getApplication());
        return circulationTypeViewModel;
    }

    @Override
    public int getBindingVariable() {
        return 0;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerviewLayoutBinding = getViewDataBinding();

        recyclerviewLayoutBinding.recyclerviewList.setLayoutManager(new LinearLayoutManager(getActivity()));

//        CirculationTypeListAdapter circulationTypeListAdapter = new CirculationTypeListAdapter(getActivity(),)

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
