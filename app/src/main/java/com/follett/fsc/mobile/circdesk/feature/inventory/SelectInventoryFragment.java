/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.inventory;

import android.app.Activity;
import android.app.Application;
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
        Application application = getBaseApplication();
        if (application == null) {
            return null;
        }
        selectInventoryViewModel = new SelectInventoryViewModel(application,this);
        return selectInventoryViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.newInventoryViewModel;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final Activity activity = getBaseActivity();
        if (activity == null) {
            return;
        }
        
        fragmentInventoryListBinding = getViewDataBinding();

        mActivity.setTitleBar(getString(R.string.selectInventory));

        fragmentInventoryListBinding.inventoryRecyclerView.setLayoutManager(new LinearLayoutManager(activity));

        fragmentInventoryListBinding.newInventoryBtn.setOnClickListener(this);

        inventoryListData.clear();
        inventoryListData.add(new Inventory("000-999 Started 02/02/2018", false));
        inventoryListData.add(new Inventory("1000-0199 Started 02/03/2018", false));
        inventoryListData.add(new Inventory("2000-2099 Started 02/05/2018", false));

        selectInventoryListAdapter = new SelectInventoryListAdapter(activity, inventoryListData, SelectInventoryFragment.this);
        fragmentInventoryListBinding.inventoryRecyclerView.setAdapter(selectInventoryListAdapter);


        selectInventoryViewModel.inventoryList.observe(this, new Observer<List<Inventory>>() {
            @Override
            public void onChanged(@Nullable List<Inventory> inventoryList) {
                inventoryListData = inventoryList;

                selectInventoryListAdapter = new SelectInventoryListAdapter(activity, inventoryListData, SelectInventoryFragment.this);
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
