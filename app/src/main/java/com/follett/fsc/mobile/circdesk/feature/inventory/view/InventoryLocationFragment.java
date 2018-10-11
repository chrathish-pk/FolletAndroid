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
import com.follett.fsc.mobile.circdesk.databinding.FragmentInventoryLocationBinding;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.Location;
import com.follett.fsc.mobile.circdesk.feature.inventory.viewmodel.InventoryLocationViewModel;
import com.follett.fsc.mobile.circdesk.feature.loginsetup.view.SetupActivity;

public class InventoryLocationFragment extends BaseFragment<FragmentInventoryLocationBinding, InventoryLocationViewModel> implements ItemClickListener,View.OnClickListener{

    private FragmentInventoryLocationBinding fragmentInventoryLocationBinding;
    private InventoryLocationViewModel inventoryLocationViewModel;
    private Location locationList;
    private String selectedLocationItem;


    @Override
    public int getLayoutId() {
        return R.layout.fragment_inventory_location;
    }

    @Override
    public InventoryLocationViewModel getViewModel() {

        inventoryLocationViewModel = new InventoryLocationViewModel(getBaseActivity().getApplication());
        return inventoryLocationViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fragmentInventoryLocationBinding = getViewDataBinding();
        fragmentInventoryLocationBinding.recyclerViewLocationList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mActivity.baseBinding.backBtn.setOnClickListener(this);
        inventoryLocationViewModel.fetchLocationList();
        inventoryLocationViewModel.locationListMutableLiveData.observeForever(new Observer<Location>() {
            @Override
            public void onChanged(@Nullable Location location) {
                locationList = location;
                InventoryLocationAdapter inventoryLocationAdapter = new InventoryLocationAdapter(getActivity(), location, InventoryLocationFragment.this);
                fragmentInventoryLocationBinding.recyclerViewLocationList.setAdapter(inventoryLocationAdapter);
            }
        });

    }


    @Override
    public void onItemClick(View view, int position) {

        selectedLocationItem = locationList.getLocationList().get(position).getName();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.backBtn) {
            AppSharedPreferences.getInstance().setString(AppSharedPreferences.KEY_SELECTED_LOCATION_ITEM, selectedLocationItem);
            if (getActivity() != null) {
                ((SetupActivity) getActivity()).selectedLocationLiveData.postValue(selectedLocationItem);
                ((SetupActivity) getActivity()).selectedData.postValue(true);
            }
            mActivity.onBackPressed();
        }
    }
}
