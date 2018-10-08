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
import com.follett.fsc.mobile.circdesk.feature.inventory.model.CircTypeList;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.CirculationID;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.CirculationTypeList;
import com.follett.fsc.mobile.circdesk.feature.inventory.viewmodel.CirculationTypeViewModel;
import com.follett.fsc.mobile.circdesk.feature.loginsetup.view.SetupActivity;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;


public class CirculationTypeFragment extends BaseFragment<FragmentCirculationtypeLayoutBinding, CirculationTypeViewModel> implements ItemClickListener, View.OnClickListener {

    private FragmentCirculationtypeLayoutBinding recyclerviewLayoutBinding;
    private CirculationTypeViewModel circulationTypeViewModel;
    private CirculationTypeList circulationTypeListData;

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
                circulationTypeListData = circulationTypeList;
                CirculationTypeListAdapter circulationTypeListAdapter = new CirculationTypeListAdapter(getActivity(), circulationTypeList, CirculationTypeFragment.this);
                recyclerviewLayoutBinding.recyclerviewList.setAdapter(circulationTypeListAdapter);
            }
        });

    }

    @Override
    public void onItemClick(View view, int position) {
        //do something
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.backBtn) {
            String selectedCirculationTypes = null;
            List<CirculationID> circulationIDList = new ArrayList<>();
            for (CircTypeList circTypeList : circulationTypeListData.getCircTypeList()) {
                if (circTypeList.isSelected()) {
                    if (selectedCirculationTypes == null) {
                        selectedCirculationTypes = circTypeList.getCircTypeDescription();
                    } else {
                        selectedCirculationTypes = selectedCirculationTypes + "," + circTypeList.getCircTypeDescription();
                    }
                    circulationIDList.add(new CirculationID(circTypeList.getCircTypeID()));
                }
            }
            AppSharedPreferences.getInstance().setString(AppSharedPreferences.KEY_CIRCULATION_TYPE_LIST, selectedCirculationTypes);

            String circulationTypesJSONString = new Gson().toJson(circulationIDList);
            AppRemoteRepository.getInstance().setString(AppSharedPreferences.KEY_CIRCULATION_TYPE_LIST_JSON, circulationTypesJSONString);

            if (getActivity() != null) {
                ((SetupActivity) getActivity()).selectedData.postValue(true);
            }
            mActivity.onBackPressed();
        }

    }
}
