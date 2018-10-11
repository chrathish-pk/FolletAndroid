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
import com.follett.fsc.mobile.circdesk.app.AlertDialogListener;
import com.follett.fsc.mobile.circdesk.app.ItemClickListener;
import com.follett.fsc.mobile.circdesk.app.base.BaseFragment;
import com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences;
import com.follett.fsc.mobile.circdesk.data.remote.repository.AppRemoteRepository;
import com.follett.fsc.mobile.circdesk.databinding.FragmentNewInventoryBinding;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.CreateInventoryResult;
import com.follett.fsc.mobile.circdesk.feature.inventory.viewmodel.NewInventoryViewModel;
import com.follett.fsc.mobile.circdesk.feature.loginsetup.view.SetupActivity;
import com.follett.fsc.mobile.circdesk.utils.AppUtils;

public class NewInventoryFragment extends BaseFragment<FragmentNewInventoryBinding, NewInventoryViewModel> implements ItemClickListener, AlertDialogListener {
    private NewInventoryViewModel newInventoryViewModel;
    private FragmentNewInventoryBinding fragmentNewInventoryBinding;
    private NewInventoryListAdapter newInventoryListAdapter;
    private boolean isLibrarySelected;

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

        isLibrarySelected = AppSharedPreferences.getInstance().getBoolean(AppSharedPreferences.KEY_IS_LIBRARY_SELECTED);
        if (isLibrarySelected)
            newInventoryListAdapter = new NewInventoryListAdapter(getActivity(), newInventoryViewModel.getNewInventoryDataForLibrary(), this);
        else
            newInventoryListAdapter = new NewInventoryListAdapter(getActivity(), newInventoryViewModel.getNewInventoryDataForResource(), this);

        fragmentNewInventoryBinding.inventoryRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        fragmentNewInventoryBinding.inventoryRecyclerView.setAdapter(newInventoryListAdapter);

        if (getActivity() != null) {
            ((SetupActivity) getActivity()).selectedData.observe(getActivity(), new Observer<Boolean>() {
                @Override
                public void onChanged(@Nullable Boolean aBoolean) {
                    if (isLibrarySelected)
                        newInventoryListAdapter = new NewInventoryListAdapter(getActivity(), newInventoryViewModel.getNewInventoryDataForLibrary(), NewInventoryFragment.this);
                    else
                        newInventoryListAdapter = new NewInventoryListAdapter(getActivity(), newInventoryViewModel.getNewInventoryDataForResource(), NewInventoryFragment.this);

                    fragmentNewInventoryBinding.inventoryRecyclerView.setAdapter(newInventoryListAdapter);
                }

            });
        }

        newInventoryViewModel.createInventoryResultMutableLiveData.observeForever(new Observer<CreateInventoryResult>() {
            @Override
            public void onChanged(@Nullable CreateInventoryResult createInventoryResult) {
                if (createInventoryResult != null) {
                    if (!createInventoryResult.getSuccess()) {
                        AppUtils.getInstance().showAlertDialog(getActivity(), "", createInventoryResult.getMessage().toString(), getString(R.string.ok), "", NewInventoryFragment.this, 0);
                    } else {
                        AppSharedPreferences.getInstance().setInt(AppSharedPreferences.KEY_CREATED_INVENTORY_PARTIAL_ID, createInventoryResult.getPartialID());
                        mActivity.popFragment(null);
                        mActivity.pushFragment(new InventoryFragment(), R.id.loginContainer, getString(R.string.inventory), true, true);
                    }

                }
            }
        });

    }

    @Override
    public void onItemClick(View view, int position) {
        switch (position) {
            case 0:
                if (isLibrarySelected) {
                    mActivity.pushFragment(new CallNumbersFragment(), R.id.loginContainer, getString(R.string.callNumbers), true, true);
                } else {
                    mActivity.pushFragment(new LimitedToFragment(), R.id.loginContainer, getString(R.string.inventoryLimitedTo), true, true);
                }
                break;
            case 1:
                if (isLibrarySelected) {
                    mActivity.pushFragment(new CirculationTypeFragment(), R.id.loginContainer, getString(R.string.circulationTypeLabel), true, true);
                }else {
                    mActivity.pushFragment(new ResourceTypeFragment(), R.id.loginContainer, getString(R.string.resourceTypes), true, true);
                }
                break;
            case 2:
                if (isLibrarySelected) {
                    mActivity.replaceFragment(new SubLocationFragment(), R.id.loginContainer, getString(R.string.subLocationLabel), true, true);
                } else {
                    mActivity.replaceFragment(new PurchasePriceFragment(), R.id.loginContainer, getString(R.string.purchasePrice), true, true);
                }
                break;
            case 3:
                if (isLibrarySelected) {
                    mActivity.replaceFragment(new SeenOnOrAfterFragment(), R.id.loginContainer, getString(R.string.seenOnOrAfter), true, true);
                } else {
                    mActivity.pushFragment(new IncludeItemAttributesFragment(), R.id.loginContainer, getString(R.string.includeItemAttributes), true, true);
                }
                break;
            case 4:
                mActivity.pushFragment(new InventoryCheckoutHandlingFragment(), R.id.loginContainer, getString(R.string.checkoutHandling), true, true);
                break;
            case 5:
                mActivity.pushFragment(new SeenOnOrAfterFragment(), R.id.loginContainer, getString(R.string.seenOnOrAfter), true, true);
                break;
            case 6:
                mActivity.pushFragment(new MismatchedItemLocationsFragment(), R.id.loginContainer, getString(R.string.mismatchedItemLocationLabel), true, true);
                break;
            case 100:
                AppRemoteRepository.getInstance().setString(AppSharedPreferences.KEY_INVENTORY_NAME, fragmentNewInventoryBinding.newInventoryName.getText().toString().trim());
                newInventoryViewModel.getCreatedInventory();
                break;

            case 101:
                AppUtils.getInstance().removeInventorySelectedData();
                fragmentNewInventoryBinding.newInventoryName.setText("");
                if (isLibrarySelected)
                    newInventoryListAdapter = new NewInventoryListAdapter(getActivity(), newInventoryViewModel.getNewInventoryDataForLibrary(), NewInventoryFragment.this);
                else
                    newInventoryListAdapter = new NewInventoryListAdapter(getActivity(), newInventoryViewModel.getNewInventoryDataForResource(), NewInventoryFragment.this);

                fragmentNewInventoryBinding.inventoryRecyclerView.setAdapter(newInventoryListAdapter);
                break;
        }
    }

    @Override
    public void onPositiveButtonClick(int statusCode) {

    }

    @Override
    public void onNegativeButtonClick(int statusCode) {

    }
}
