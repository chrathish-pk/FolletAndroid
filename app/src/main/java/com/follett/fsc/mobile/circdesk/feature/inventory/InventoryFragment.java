/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.inventory;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.follett.fsc.mobile.circdesk.BR;
import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.app.base.BaseFragment;
import com.follett.fsc.mobile.circdesk.databinding.FragmentInventoryBinding;

/**
 * Created by muthulakshmi on 11/09/18.
 */

public class InventoryFragment extends BaseFragment<FragmentInventoryBinding, InventoryViewModel> {

    private InventoryViewModel inventoryViewModel;
    private FragmentInventoryBinding fragmentInventoryBinding;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_inventory;
    }

    @Override
    public InventoryViewModel getViewModel() {
        inventoryViewModel = new InventoryViewModel(getBaseActivity().getApplication());
        return inventoryViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.inventoryViewModel;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //fragmentInventoryBinding.patronEntryIncludeLayout.checkinLibRecordSwitch.setVisibility(View.GONE);

    }
}
