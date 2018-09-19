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
import com.follett.fsc.mobile.circdesk.databinding.FragmentInventoryListBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by muthulakshmi on 12/09/18.
 */

public class SelectInventoryFragment extends BaseFragment<FragmentInventoryListBinding, SelectInventoryViewModel> implements ItemClickListener, View.OnClickListener {

    private FragmentInventoryListBinding fragmentInventoryListBinding;
    private SelectInventoryViewModel selectInventoryViewModel;
    private List<Inventory> inventoryListData = new ArrayList<>();
    private SelectInventoryListAdapter selectInventoryListAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_inventory_list;
    }

    @Override
    public SelectInventoryViewModel getViewModel() {
        selectInventoryViewModel = new SelectInventoryViewModel(getBaseActivity().getApplication(),this);
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

        mActivity.setTitleBar(getString(R.string.selectInventory));

        fragmentInventoryListBinding.inventoryRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        fragmentInventoryListBinding.newInventoryBtn.setOnClickListener(this);

        inventoryListData.add(new Inventory("first", false));
        inventoryListData.add(new Inventory("Second", false));
        inventoryListData.add(new Inventory("Third", false));

        selectInventoryListAdapter = new SelectInventoryListAdapter(getActivity(), inventoryListData, SelectInventoryFragment.this);
        fragmentInventoryListBinding.inventoryRecyclerView.setAdapter(selectInventoryListAdapter);


        selectInventoryViewModel.inventoryList.observe(this, new Observer<List<Inventory>>() {
            @Override
            public void onChanged(@Nullable List<Inventory> inventoryList) {
                inventoryListData = inventoryList;

                selectInventoryListAdapter = new SelectInventoryListAdapter(getActivity(), inventoryListData, SelectInventoryFragment.this);
                fragmentInventoryListBinding.inventoryRecyclerView.setAdapter(selectInventoryListAdapter);

            }
        });
    }

    @Override
    public void onItemClick(View view, int position) {
        inventoryListData.get(position).setSelected(true);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.newInventoryBtn) {
            mActivity.pushFragment(new NewInventoryFragment(), R.id.loginContainer, "NewInventoryFragment", true);
        }
    }
}
