/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */
package com.follett.fsc.mobile.circdesk.feature.inventory.view;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.follett.fsc.mobile.circdesk.BR;
import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.app.ItemClickListener;
import com.follett.fsc.mobile.circdesk.app.base.BaseFragment;
import com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences;
import com.follett.fsc.mobile.circdesk.data.remote.repository.AppRemoteRepository;
import com.follett.fsc.mobile.circdesk.databinding.FragmentNewInventoryBinding;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.CallNumbers;
import com.follett.fsc.mobile.circdesk.feature.inventory.viewmodel.NewInventoryViewModel;

public class NewInventoryFragment extends BaseFragment<FragmentNewInventoryBinding, NewInventoryViewModel> implements ItemClickListener {
    private NewInventoryViewModel newInventoryViewModel;
    private FragmentNewInventoryBinding fragmentNewInventoryBinding;
    private CallNumbers callNumbers;

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

        if (AppRemoteRepository.getInstance().getBoolean(AppSharedPreferences.KEY_IS_LIBRARY_SELECTED)) {
            fragmentNewInventoryBinding.callNumberLayout.setVisibility(View.VISIBLE);
            fragmentNewInventoryBinding.circulationTypesLayout.setVisibility(View.VISIBLE);
            fragmentNewInventoryBinding.libExcludeItemsLayout.setVisibility(View.VISIBLE);
        } else {
            fragmentNewInventoryBinding.limitedToLayout.setVisibility(View.VISIBLE);
            fragmentNewInventoryBinding.resourceTypeLayout.setVisibility(View.VISIBLE);
            fragmentNewInventoryBinding.purchasePriceLayout.setVisibility(View.VISIBLE);
            fragmentNewInventoryBinding.includeItemsLayout.setVisibility(View.VISIBLE);
            fragmentNewInventoryBinding.checkoutHandlingLayout.setVisibility(View.VISIBLE);
            fragmentNewInventoryBinding.excludeItemsLayout.setVisibility(View.VISIBLE);
            fragmentNewInventoryBinding.mismatchedItemLocationLayout.setVisibility(View.VISIBLE);
        }


    }

    @Override
    public void onItemClick(View view, int position) {
        switch (view.getId()) {
            case R.id.circulationTypesLayout:
                mActivity.pushFragment(new CirculationTypeFragment(), R.id.loginContainer, "CirculationTypeFragment", true);
                break;
            case R.id.callNumberLayout:
                Bundle bundle = new Bundle();
                CallNumbersFragment callNumbersFragment = new CallNumbersFragment();
                bundle.putParcelable("callNumbers", callNumbers);
                callNumbersFragment.setArguments(bundle);
                mActivity.pushFragment(callNumbersFragment, R.id.loginContainer, "CallNumbersFragment", true);
                break;
            case R.id.libExcludeItemsLayout:
                mActivity.pushFragment(new SeenOnOrAfterFragment(), R.id.loginContainer, "SeenOnOrAfterFragment", true);
                break;
        }
    }
}
