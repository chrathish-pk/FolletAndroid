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
import com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences;
import com.follett.fsc.mobile.circdesk.data.remote.repository.AppRemoteRepository;
import com.follett.fsc.mobile.circdesk.databinding.FragmentInventoryBinding;
import com.follett.fsc.mobile.circdesk.feature.checkoutcheckin.UpdateUIListener;
import com.follett.fsc.mobile.circdesk.utils.FollettLog;

import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_COLLECTION_TYPE;
import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_IS_LIBRARY_SELECTED;

public class InventoryFragment extends BaseFragment<FragmentInventoryBinding, InventoryViewModel> implements ItemClickListener, View.OnClickListener, UpdateUIListener {

    private InventoryViewModel inventoryViewModel;
    private FragmentInventoryBinding fragmentInventoryBinding;
    private InProgressInventoryResults inProgressInventoryResults;
    private InventoryDetails inventoryDetails;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_inventory;
    }

    @Override
    public InventoryViewModel getViewModel() {
        if (getBaseApplication() == null) {
            return null;
        }
        inventoryViewModel = new InventoryViewModel(getBaseApplication(), this, this);
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
        initViews();
        inventoryViewModel.getInProgressInventoryResults(AppSharedPreferences.getInstance()
                .getString(AppSharedPreferences.KEY_SITE_SHORT_NAME), AppSharedPreferences.getInstance()
                .getString(AppSharedPreferences.KEY_CONTEXT_NAME), AppRemoteRepository.getInstance().getBoolean(KEY_IS_LIBRARY_SELECTED) ? 0 : 4);

    }

    private void initViews() {
        mActivity.setTitleBar(getString(R.string.inventory));
        mActivity.setBackBtnVisible();
        mActivity.baseBinding.backBtn.setOnClickListener(this);
        fragmentInventoryBinding.libraryResourceIncludeLayout.libraryBtn.setOnClickListener(this);
        fragmentInventoryBinding.libraryResourceIncludeLayout.resourceBtn.setOnClickListener(this);

        fragmentInventoryBinding.finalizeInventoryBtn.setOnClickListener(this);

        fragmentInventoryBinding.patronEntryIncludeLayout.patronEntry.setHint(getString(R.string.enterBarcode));
        fragmentInventoryBinding.patronEntryIncludeLayout.checkinLibRecordSwitch.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backBtn:
                mActivity.setTitleBar(getString(R.string.home));
                mActivity.baseBinding.backBtn.setVisibility(View.GONE);
                mActivity.onBackPressed();
                break;
            case R.id.libraryBtn:
                fragmentInventoryBinding.inventoryLocation.setVisibility(View.GONE);
                fragmentInventoryBinding.inventoryLocationBar.setVisibility(View.GONE);
                fragmentInventoryBinding.libraryResourceIncludeLayout.libraryBtn.setBackgroundColor(mActivity.getResources().getColor(R.color.blueLabel));
                fragmentInventoryBinding.libraryResourceIncludeLayout.libraryBtn.setTextColor(mActivity.getResources().getColor(R.color.white));
                fragmentInventoryBinding.libraryResourceIncludeLayout.resourceBtn.setBackgroundColor(mActivity.getResources().getColor(R.color.white));
                fragmentInventoryBinding.libraryResourceIncludeLayout.resourceBtn.setTextColor(mActivity.getResources().getColor(R.color.blueLabel));
                AppRemoteRepository.getInstance().setBoolean(KEY_IS_LIBRARY_SELECTED, true);
                break;
            case R.id.resourceBtn:
                fragmentInventoryBinding.inventoryLocation.setVisibility(View.VISIBLE);
                fragmentInventoryBinding.inventoryLocationBar.setVisibility(View.VISIBLE);
                fragmentInventoryBinding.libraryResourceIncludeLayout.libraryBtn.setBackgroundColor(mActivity.getResources().getColor(R.color.white));
                fragmentInventoryBinding.libraryResourceIncludeLayout.libraryBtn.setTextColor(mActivity.getResources().getColor(R.color.blueLabel));
                fragmentInventoryBinding.libraryResourceIncludeLayout.resourceBtn.setBackgroundColor(mActivity.getResources().getColor(R.color.blueLabel));
                fragmentInventoryBinding.libraryResourceIncludeLayout.resourceBtn.setTextColor(mActivity.getResources().getColor(R.color.white));
                AppRemoteRepository.getInstance().setBoolean(KEY_IS_LIBRARY_SELECTED, false);
                break;
            case R.id.finalizeInventoryBtn:
              /*  DialogFragment fragment = new FinalizePopupFragment();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                fragment.show(ft,"FinalizePopupFragment");
                //mActivity.pushFragment(fragment, R.id.loginContainer, "FinalizePopupFragment", true);
                break;*/
                break;
            default:
                break;
        }
    }


    @Override
    public void updateUI(final Object value) {

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (value instanceof InProgressInventoryResults) {
                        inProgressInventoryResults = (InProgressInventoryResults) value;

                        if (inProgressInventoryResults.getInventoryList().size() > 0) {
                            fragmentInventoryBinding.inventorySelection.setVisibility(View.VISIBLE);
                            fragmentInventoryBinding.inventorySelection.setText(inProgressInventoryResults.getInventoryList().get(0).getName()
                                    + " - " + inProgressInventoryResults.getInventoryList().get(0).getDateStarted());
                        } else {
                            fragmentInventoryBinding.inventorySelection.setVisibility(View.GONE);

                        }
                    }
                    if (value instanceof InventoryDetails) {
                        inventoryDetails = (InventoryDetails) value;
                        fragmentInventoryBinding.inventoryCompletedStatus.setText("Current status: " + inventoryDetails.getCompletePercentage() + " Complete");
                    }
                } catch (Exception e) {
                    FollettLog.e(getString(R.string.error), e.getMessage());
                }
            }
        });
    }


    @Override
    public void onItemClick(View view, int position) {
        switch (view.getId()) {
            case R.id.inventorySelection:
                mActivity.pushFragment(new SelectInventoryFragment(), R.id.loginContainer, "SelectInventoryFragment", true);
                break;
            case R.id.inventoryLocation:
                mActivity.pushFragment(new InventoryLocationFragment(), R.id.loginContainer, "InventoryLocationFragment", true);
                break;

        }
    }
}
