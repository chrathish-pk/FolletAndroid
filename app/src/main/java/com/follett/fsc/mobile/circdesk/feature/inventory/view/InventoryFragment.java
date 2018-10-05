/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.inventory.view;

import android.app.Activity;
import android.arch.lifecycle.Observer;
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
import com.follett.fsc.mobile.circdesk.feature.inventory.InventoryViewSelectionFragment;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.InProgressInventoryResults;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.InventoryDetails;
import com.follett.fsc.mobile.circdesk.feature.inventory.viewmodel.InventoryViewModel;
import com.follett.fsc.mobile.circdesk.feature.loginsetup.view.SetupActivity;
import com.follett.fsc.mobile.circdesk.utils.AppUtils;
import com.follett.fsc.mobile.circdesk.utils.FollettLog;

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
        inventoryViewModel.getInProgressInventoryResults();
    }

    private void initViews() {
        final Activity activity = getBaseActivity();
        if (activity == null) {
            return;
        }

        fragmentInventoryBinding.libraryResourceIncludeLayout.libraryBtn.setOnClickListener(this);
        fragmentInventoryBinding.libraryResourceIncludeLayout.resourceBtn.setOnClickListener(this);

        fragmentInventoryBinding.finalizeInventoryBtn.setOnClickListener(this);
        fragmentInventoryBinding.inventoryViewSelectionsBtn.setOnClickListener(this);

        fragmentInventoryBinding.patronEntryIncludeLayout.patronEntry.setHint(getString(R.string.enterBarcode));
        fragmentInventoryBinding.patronEntryIncludeLayout.checkinLibRecordSwitch.setVisibility(View.GONE);

        AppUtils.getInstance().updateLibResBg(mActivity,fragmentInventoryBinding.libraryResourceIncludeLayout.libraryBtn, fragmentInventoryBinding.libraryResourceIncludeLayout.resourceBtn);

        ((SetupActivity) getActivity()).selectedInventoryNameLiveData.observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String selectedInventory) {
                fragmentInventoryBinding.inventorySelection.setText(selectedInventory);
                inventoryViewModel.getInventoryDetails();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.libraryBtn:
                fragmentInventoryBinding.inventoryLocation.setVisibility(View.GONE);
                fragmentInventoryBinding.inventoryLocationBar.setVisibility(View.GONE);
                AppRemoteRepository.getInstance().setBoolean(KEY_IS_LIBRARY_SELECTED, true);
                AppUtils.getInstance().updateLibResBg(mActivity,fragmentInventoryBinding.libraryResourceIncludeLayout.libraryBtn, fragmentInventoryBinding.libraryResourceIncludeLayout.resourceBtn);
                inventoryViewModel.getInProgressInventoryResults();
                break;
            case R.id.resourceBtn:
                fragmentInventoryBinding.inventoryLocation.setVisibility(View.VISIBLE);
                fragmentInventoryBinding.inventoryLocationBar.setVisibility(View.VISIBLE);
                AppRemoteRepository.getInstance().setBoolean(KEY_IS_LIBRARY_SELECTED, false);
                AppUtils.getInstance().updateLibResBg(mActivity,fragmentInventoryBinding.libraryResourceIncludeLayout.libraryBtn, fragmentInventoryBinding.libraryResourceIncludeLayout.resourceBtn);

                inventoryViewModel.getInProgressInventoryResults();
                break;
            case R.id.finalizeInventoryBtn:
              /*  DialogFragment fragment = new FinalizePopupFragment();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                fragment.show(ft,"FinalizePopupFragment");
                //mActivity.pushFragment(fragment, R.id.loginContainer, "FinalizePopupFragment", true);
                break;*/
                break;
            case R.id.inventoryViewSelectionsBtn:
                mActivity.pushFragment(new InventoryViewSelectionFragment(), R.id.loginContainer, getString(R.string.inventorySelections), true, true);
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

                        if (!inProgressInventoryResults.getInventoryList().isEmpty()) {
                            fragmentInventoryBinding.inventorySelection.setVisibility(View.VISIBLE);
                            inProgressInventoryResults.getInventoryList().get(0).setSelected(true);
                            AppSharedPreferences.getInstance().setInt(AppSharedPreferences.KEY_SELECTED_INVENTORY_PARTIAL_ID, inProgressInventoryResults.getInventoryList().get(0).getPartialID());
                            if (inProgressInventoryResults.getInventoryList().get(0).getName().isEmpty())
                                fragmentInventoryBinding.inventorySelection.setText(inProgressInventoryResults.getInventoryList().get(0).getDateStarted());
                            else
                                fragmentInventoryBinding.inventorySelection.setText(inProgressInventoryResults.getInventoryList().get(0).getName() + getString(R.string.started) + inProgressInventoryResults.getInventoryList().get(0).getDateStarted());
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
        if (view.getId() == R.id.inventorySelection) {
            Bundle bundle = new Bundle();
            SelectInventoryFragment selectInventoryFragment = new SelectInventoryFragment();
            bundle.putParcelable("InProgressInventoryResult", inProgressInventoryResults);
            selectInventoryFragment.setArguments(bundle);
            mActivity.pushFragment(selectInventoryFragment, R.id.loginContainer, getString(R.string.selectInventory), true, true);
        } else if (view.getId() == R.id.inventoryLocation) {
            mActivity.pushFragment(new InventoryLocationFragment(), R.id.loginContainer, getString(R.string.inventoryLocation), true, true);
        }
    }
}
