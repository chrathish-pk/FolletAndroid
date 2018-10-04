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
import com.follett.fsc.mobile.circdesk.databinding.FragmentInventoryLocationBinding;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.Location;
import com.follett.fsc.mobile.circdesk.feature.inventory.viewmodel.InventoryLocationViewModel;
import com.follett.fsc.mobile.circdesk.feature.inventory.viewmodel.SelectInventoryViewModel;

public class InventoryLocationFragment extends BaseFragment<FragmentInventoryLocationBinding, InventoryLocationViewModel> implements ItemClickListener {

    private FragmentInventoryLocationBinding fragmentInventoryLocationBinding;
    private InventoryLocationViewModel inventoryLocationViewModel;

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
        inventoryLocationViewModel.fetchLocationList();
        fragmentInventoryLocationBinding.scanLocationBtn.setText(getString(R.string.scanLocation));
        inventoryLocationViewModel.locationListMutableLiveData.observeForever(new Observer<Location>() {
            @Override
            public void onChanged(@Nullable Location location) {

                InventoryLocationAdapter inventoryLocationAdapter = new InventoryLocationAdapter(getActivity(), location, InventoryLocationFragment.this);
                fragmentInventoryLocationBinding.recyclerViewLocationList.setAdapter(inventoryLocationAdapter);
            }
        });

    }


    @Override
    public void onItemClick(View view, int position) {
        if (view.getId() == R.id.newInventoryBtn) {
            //mActivity.pushFragment(new InventoryLocationFragment(), R.id.loginContainer, "InventoryLocationFragment", true);
        }
    }
}
