/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.inventory.view;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.follett.fsc.mobile.circdesk.BR;
import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.app.ItemClickListener;
import com.follett.fsc.mobile.circdesk.app.base.BaseFragment;
import com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences;
import com.follett.fsc.mobile.circdesk.data.remote.repository.AppRemoteRepository;
import com.follett.fsc.mobile.circdesk.databinding.FragmentCirculationtypeLayoutBinding;
//import com.follett.fsc.mobile.circdesk.feature.inventory.model.CircTypeRecord;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.CirculationTypeList;
import com.follett.fsc.mobile.circdesk.feature.inventory.viewmodel.CirculationTypeViewModel;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;


public class CirculationTypeFragment extends BaseFragment<FragmentCirculationtypeLayoutBinding, CirculationTypeViewModel> implements ItemClickListener, View.OnClickListener {

    private FragmentCirculationtypeLayoutBinding recyclerviewLayoutBinding;
    private CirculationTypeViewModel circulationTypeViewModel;
//    private List<CircTypeRecord> circTypeRecordList = new ArrayList<>();

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

        mActivity.baseBinding.backBtn.setOnClickListener(this);
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
        circulationTypeViewModel.circulationTypeListMutableLiveData.getValue().getCircTypeList().get(position).setSelected(true);
//        circTypeRecordList.add(new CircTypeRecord(circulationTypeViewModel.circulationTypeListMutableLiveData.getValue().getCircTypeList().get(position).getCircTypeID()));
    }


    @Override
    public void onClick(View v) {
       /* String circulationTypesJSONString = new Gson().toJson(circTypeRecordList);
        AppRemoteRepository.getInstance().setString(AppSharedPreferences.KEY_CIRCULATION_TYPE_LIST, circulationTypesJSONString);*/
    }
}
