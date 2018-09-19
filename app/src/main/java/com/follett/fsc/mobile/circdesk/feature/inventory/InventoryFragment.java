/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.inventory;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.follett.fsc.mobile.circdesk.BR;
import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.app.ItemClickListener;
import com.follett.fsc.mobile.circdesk.app.base.BaseFragment;
import com.follett.fsc.mobile.circdesk.databinding.FragmentInventoryBinding;

/**
 * Created by muthulakshmi on 11/09/18.
 */

public class InventoryFragment extends BaseFragment<FragmentInventoryBinding, InventoryViewModel> implements ItemClickListener {

    private InventoryViewModel inventoryViewModel;
    private FragmentInventoryBinding fragmentInventoryBinding;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_inventory;
    }

    @Override
    public InventoryViewModel getViewModel() {
        inventoryViewModel = new InventoryViewModel(getBaseActivity().getApplication(), this);
        return inventoryViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.inventoryViewModel;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fragmentInventoryBinding = getViewDataBinding();
        mActivity.setTitleBar(getString(R.string.inventory));

        fragmentInventoryBinding.patronEntryIncludeLayout.checkinLibRecordSwitch.setVisibility(View.GONE);

    }


    @Override
    public void onItemClick(View view, int position) {
        switch (view.getId()) {
            case R.id.inventorySelection:
                mActivity.pushFragment(new InventoryListFragment(), R.id.loginContainer, "InventoryListFragment", true);
        }
    }
}
