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
import com.follett.fsc.mobile.circdesk.databinding.FragmentNewInventoryBinding;

public class NewInventoryFragment extends BaseFragment<FragmentNewInventoryBinding, NewInventoryViewModel> {
    private NewInventoryViewModel  newInventoryViewModel;
    private FragmentNewInventoryBinding fragmentNewInventoryBinding;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_new_inventory;
    }

    @Override
    public NewInventoryViewModel getViewModel() {
        if (getBaseApplication() == null) {
            return null;
        }
        newInventoryViewModel = new NewInventoryViewModel(getBaseApplication());
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



    }
}
