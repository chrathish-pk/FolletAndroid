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
import com.follett.fsc.mobile.circdesk.databinding.FragmentNewInventoryBinding;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.CreateInventoryResult;
import com.follett.fsc.mobile.circdesk.feature.inventory.viewmodel.NewInventoryViewModel;
import com.follett.fsc.mobile.circdesk.utils.FollettLog;

public class NewInventoryFragment extends BaseFragment<FragmentNewInventoryBinding, NewInventoryViewModel> implements ItemClickListener {
    private NewInventoryViewModel newInventoryViewModel;
    private FragmentNewInventoryBinding fragmentNewInventoryBinding;
    private NewInventoryListAdapter newInventoryListAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_new_inventory;
    }

    @Override
    public NewInventoryViewModel getViewModel() {
        if (getBaseApplication() == null) {
            return null;
        }
        newInventoryViewModel = new NewInventoryViewModel(getBaseApplication(), this);
        return newInventoryViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.newInventoryViewModel;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fragmentNewInventoryBinding = getViewDataBinding();

        newInventoryViewModel.createInventoryResultMutableLiveData.observeForever(new Observer<CreateInventoryResult>() {
            @Override
            public void onChanged(@Nullable CreateInventoryResult createInventoryResult) {
                FollettLog.e("result for create inventory result", createInventoryResult.toString());
            }
        });

        fragmentNewInventoryBinding.inventoryRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        if (AppSharedPreferences.getInstance().getBoolean(AppSharedPreferences.KEY_IS_LIBRARY_SELECTED))
            newInventoryListAdapter = new NewInventoryListAdapter(getActivity(), newInventoryViewModel.getNewInventoryDataForLibrary(), this);
        else
            newInventoryListAdapter = new NewInventoryListAdapter(getActivity(), newInventoryViewModel.getNewInventoryDataForResource(), this);

        fragmentNewInventoryBinding.inventoryRecyclerView.setAdapter(newInventoryListAdapter);

    }

    @Override
    public void onItemClick(View view, int position) {
        switch (position) {
            case 0:
                mActivity.pushFragment(new CallNumbersFragment(), R.id.loginContainer, getString(R.string.callNumbers), true, true);
                break;
            case 1:
                mActivity.pushFragment(new CirculationTypeFragment(), R.id.loginContainer, getString(R.string.circulationTypeLabel), true, true);
                break;
            case 2:
                mActivity.replaceFragment(new SeenOnOrAfterFragment(), R.id.loginContainer, getString(R.string.seenOnOrAfter), true, true);
                break;
            /*case R.id.startInventoryBtn:
                AppRemoteRepository.getInstance().setString(AppSharedPreferences.KEY_INVENTORY_NAME, fragmentNewInventoryBinding.newInventoryName.getText().toString().trim());
                newInventoryViewModel.getCreatedInventory();
                break;*/
        }
    }
}
