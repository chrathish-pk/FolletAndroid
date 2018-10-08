/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.inventory.view;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.follett.fsc.mobile.circdesk.BR;
import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.app.ItemClickListener;
import com.follett.fsc.mobile.circdesk.app.base.BaseFragment;
import com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences;
import com.follett.fsc.mobile.circdesk.databinding.FragmentInventoryListBinding;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.InProgressInventoryResults;
import com.follett.fsc.mobile.circdesk.feature.inventory.viewmodel.SelectInventoryViewModel;
import com.follett.fsc.mobile.circdesk.feature.loginsetup.view.SetupActivity;

public class SelectInventoryFragment extends BaseFragment<FragmentInventoryListBinding, SelectInventoryViewModel> implements ItemClickListener, View.OnClickListener {

    private FragmentInventoryListBinding fragmentInventoryListBinding;
    private SelectInventoryViewModel selectInventoryViewModel;
    private SelectInventoryListAdapter selectInventoryListAdapter;
    private InProgressInventoryResults inProgressInventoryResults;

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
        selectInventoryViewModel = new SelectInventoryViewModel(application, this);
        return selectInventoryViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.newInventoryViewModel;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null)
            inProgressInventoryResults = getArguments().getParcelable("InProgressInventoryResult");

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final Activity activity = getBaseActivity();
        if (activity == null) {
            return;
        }

        fragmentInventoryListBinding = getViewDataBinding();


        fragmentInventoryListBinding.newInventoryBtn.setOnClickListener(this);
        fragmentInventoryListBinding.inventoryRecyclerView.setLayoutManager(new LinearLayoutManager(activity));

        if (inProgressInventoryResults != null && inProgressInventoryResults.getInventoryList() != null) {
            selectInventoryListAdapter = new SelectInventoryListAdapter(activity, inProgressInventoryResults.getInventoryList(), SelectInventoryFragment.this);
            fragmentInventoryListBinding.inventoryRecyclerView.setAdapter(selectInventoryListAdapter);
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        for (int i = 0; i < inProgressInventoryResults.getInventoryList().size(); i++) {
            if (i == position)
                inProgressInventoryResults.getInventoryList().get(i).setSelected(true);
            else
                inProgressInventoryResults.getInventoryList().get(i).setSelected(false);

        }
        AppSharedPreferences.getInstance().setInt(AppSharedPreferences.KEY_SELECTED_INVENTORY_PARTIAL_ID, inProgressInventoryResults.getInventoryList().get(position).getPartialID());
        if (inProgressInventoryResults.getInventoryList().get(position).getName().isEmpty())
            ((SetupActivity) getActivity()).selectedInventoryNameLiveData.postValue(inProgressInventoryResults.getInventoryList().get(position).getDateStarted());
        else
            ((SetupActivity) getActivity()).selectedInventoryNameLiveData.postValue(inProgressInventoryResults.getInventoryList().get(position).getName() + getString(R.string.started) + inProgressInventoryResults.getInventoryList().get(position).getDateStarted());
        selectInventoryListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.newInventoryBtn) {

            AppSharedPreferences.getInstance().removeValues(AppSharedPreferences.KEY_CALL_NUMBER_FROM);
            AppSharedPreferences.getInstance().removeValues(AppSharedPreferences.KEY_CALL_NUMBER_TO);
            AppSharedPreferences.getInstance().removeValues(AppSharedPreferences.KEY_CIRCULATION_TYPE_LIST);
            AppSharedPreferences.getInstance().removeValues(AppSharedPreferences.KEY_CIRCULATION_TYPE_LIST_JSON);
            AppSharedPreferences.getInstance().removeValues(AppSharedPreferences.KEY_SELECTED_SUB_LOCATION);
            AppSharedPreferences.getInstance().removeValues(AppSharedPreferences.KEY_SELECTED_SUB_LOCATION_JSON);
            AppSharedPreferences.getInstance().removeValues(AppSharedPreferences.KEY_SEEN_FORMAT_DATE);
            AppSharedPreferences.getInstance().removeValues(AppSharedPreferences.KEY_SEEN_DATE);
            AppSharedPreferences.getInstance().removeValues(AppSharedPreferences.KEY_INVENTORY_NAME);

            mActivity.pushFragment(new NewInventoryFragment(), R.id.loginContainer, getString(R.string.newInventoryTitle), true, true);
        }
    }
}
