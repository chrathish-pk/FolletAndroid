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
import com.follett.fsc.mobile.circdesk.databinding.FragmentInventoryListBinding;

/**
 * Created by muthulakshmi on 19/09/18.
 */

public class InventoryLocationFragment extends BaseFragment<FragmentInventoryListBinding, SelectInventoryViewModel> implements ItemClickListener {

    private FragmentInventoryListBinding fragmentInventoryListBinding;
    private SelectInventoryViewModel selectInventoryViewModel;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_inventory_list;
    }

    @Override
    public SelectInventoryViewModel getViewModel() {
        selectInventoryViewModel = new SelectInventoryViewModel(getBaseActivity().getApplication(), this);
        return selectInventoryViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.newInventoryViewModel;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fragmentInventoryListBinding = getViewDataBinding();

        mActivity.setTitleBar(getString(R.string.inventoryLocation));
        fragmentInventoryListBinding.newInventoryBtn.setText(getString(R.string.scanLocation));

    }


    @Override
    public void onItemClick(View view, int position) {
        if (view.getId() == R.id.newInventoryBtn) {
            //mActivity.pushFragment(new InventoryLocationFragment(), R.id.loginContainer, "InventoryLocationFragment", true);
        }
    }
}
