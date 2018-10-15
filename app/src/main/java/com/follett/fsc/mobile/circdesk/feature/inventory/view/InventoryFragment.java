/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.inventory.view;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.RadioGroup;

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
import com.follett.fsc.mobile.circdesk.feature.inventory.model.InventoryScan;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.NewInventoryData;
import com.follett.fsc.mobile.circdesk.feature.inventory.viewmodel.InventoryViewModel;
import com.follett.fsc.mobile.circdesk.feature.loginsetup.view.SetupActivity;
import com.follett.fsc.mobile.circdesk.utils.AppUtils;
import com.follett.fsc.mobile.circdesk.utils.FollettLog;
import com.honeywell.aidc.BarcodeFailureEvent;
import com.honeywell.aidc.BarcodeReadEvent;
import com.honeywell.aidc.BarcodeReader;

import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_IS_LIBRARY_SELECTED;

public class InventoryFragment extends BaseFragment<FragmentInventoryBinding, InventoryViewModel> implements ItemClickListener, View.OnClickListener, UpdateUIListener, BarcodeReader.BarcodeListener, RadioGroup.OnCheckedChangeListener {

    private InventoryViewModel inventoryViewModel;
    private FragmentInventoryBinding fragmentInventoryBinding;
    private InProgressInventoryResults inProgressInventoryResults;
    private InventoryDetails inventoryDetails;
    private BarcodeReader mBarcodeReader;


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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentInventoryBinding = getViewDataBinding();
        initViews();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBarcodeReader = mActivity.getBarcodeReader();


    }

    private void initViews() {
        final Activity activity = getBaseActivity();
        if (activity == null) {
            return;
        }

        if (AppRemoteRepository.getInstance()
                .getBoolean(KEY_IS_LIBRARY_SELECTED)) {
            isInventoryLibrary(true);
        } else {
            isInventoryLibrary(false);
        }

        if (AppUtils.brandName(mActivity)) {
            mBarcodeReader.addBarcodeListener(this);
            fragmentInventoryBinding.patronEntryIncludeLayout.scanButton.setOnClickListener(this);

        }
        fragmentInventoryBinding.libraryResourceIncludeLayout.libraryBtn.setOnClickListener(this);
        fragmentInventoryBinding.libraryResourceIncludeLayout.resourceBtn.setOnClickListener(this);

        fragmentInventoryBinding.finalizeInventoryBtn.setOnClickListener(this);
        fragmentInventoryBinding.inventoryViewSelectionsBtn.setOnClickListener(this);

        fragmentInventoryBinding.patronEntryIncludeLayout.patronEntry.setHint(getString(R.string.enterBarcode));
        fragmentInventoryBinding.patronEntryIncludeLayout.checkinLibRecordSwitch.setVisibility(View.GONE);

        if (AppRemoteRepository.getInstance().getString(AppSharedPreferences.KEY_SELECTED_LOCATION_ITEM).isEmpty())
            fragmentInventoryBinding.inventoryLocation.setText(getString(R.string.defaultLocation));
        else
            fragmentInventoryBinding.inventoryLocation.setText(AppRemoteRepository.getInstance().getString(AppSharedPreferences.KEY_SELECTED_LOCATION_ITEM));

        AppUtils.getInstance().updateLibResBg(mActivity, fragmentInventoryBinding.libraryResourceIncludeLayout.libraryBtn, fragmentInventoryBinding.libraryResourceIncludeLayout.resourceBtn);

        ((SetupActivity) getActivity()).selectedInventoryNameLiveData.observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String selectedInventory) {
                fragmentInventoryBinding.inventorySelection.setText(selectedInventory);
                inventoryViewModel.getInventoryDetails();
            }
        });

        ((SetupActivity)getActivity()).selectedLocationLiveData.observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String selectedLocation) {

                if(selectedLocation.isEmpty())
                {
                    fragmentInventoryBinding.inventoryLocation.setText(AppSharedPreferences.getInstance().getString(AppSharedPreferences.KEY_SELECTED_LOCATION_ITEM));
                }
                else
                {
                    fragmentInventoryBinding.inventoryLocation.setText(selectedLocation);

                }
            }
        });

        fragmentInventoryBinding.patronEntryIncludeLayout.patronGoBtn.setOnClickListener(this);
        inventoryViewModel.inventoryScanMutableLiveData.observe(this, new Observer<InventoryScan>() {
            @Override
            public void onChanged(@Nullable InventoryScan inventoryScan) {
                fragmentInventoryBinding.setScan(inventoryScan);
            }
        });
        inventoryViewModel.barCodeNotFound.observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                AppUtils.getInstance()
                        .showAlertDialog(activity, getString(R.string.not_found_label), String.valueOf(s));
            }
        });

        fragmentInventoryBinding.barcodedBtn.setTextColor(ContextCompat.getColor(getActivity(), R.color.blueLabel));
        fragmentInventoryBinding.unBarcodedBtn.setTextColor(ContextCompat.getColor(getActivity(), R.color.black));
        fragmentInventoryBinding.barcodedUnbarcodedRadioGroup.setOnCheckedChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.libraryBtn:
                isInventoryLibrary(true);
                break;
            case R.id.resourceBtn:
                isInventoryLibrary(false);
                break;
            case R.id.finalizeInventoryBtn:
                DialogFragment fragment = new FinalizePopupFragment();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                fragment.show(ft, "FinalizePopupFragment");
                break;
            case R.id.inventoryViewSelectionsBtn:
                mActivity.pushFragment(new InventoryViewSelectionFragment(), R.id.loginContainer, getString(R.string.inventorySelections), true, true);
                break;
            case R.id.patronGoBtn:
                AppUtils.getInstance()
                        .hideKeyBoard(mActivity, fragmentInventoryBinding.patronEntryIncludeLayout.patronEntry);
                inventoryViewModel.inventoryScan(AppUtils.getInstance().getEditTextValue(fragmentInventoryBinding.patronEntryIncludeLayout.patronEntry));
                break;
            case R.id.scanButton:
                inventoryViewModel.triggerSoftwareScanner(mBarcodeReader);
                break;
            default:
                break;
        }
    }

    private void isInventoryLibrary(boolean isInventoryLibrary) {

        if (isInventoryLibrary) {
            fragmentInventoryBinding.inventoryLocation.setVisibility(View.GONE);
            fragmentInventoryBinding.inventoryLocationBar.setVisibility(View.GONE);
        } else {

            fragmentInventoryBinding.inventoryLocation.setVisibility(View.VISIBLE);
            fragmentInventoryBinding.inventoryLocationBar.setVisibility(View.VISIBLE);

        }
        AppUtils.getInstance().updateLibResBg(mActivity, fragmentInventoryBinding.libraryResourceIncludeLayout.libraryBtn, fragmentInventoryBinding.libraryResourceIncludeLayout.resourceBtn);

        fragmentInventoryBinding.patronEntryIncludeLayout.patronEntry.setText("");
        fragmentInventoryBinding.inventoryScan.setVisibility(View.GONE);
        AppRemoteRepository.getInstance()
                .setBoolean(KEY_IS_LIBRARY_SELECTED, isInventoryLibrary);
        AppUtils.getInstance()
                .updateLibResBg(mActivity, fragmentInventoryBinding.libraryResourceIncludeLayout.libraryBtn, fragmentInventoryBinding
                        .libraryResourceIncludeLayout.resourceBtn);
        inventoryViewModel.getInProgressInventoryResults();

        if (!isInventoryLibrary) {
            inventoryViewModel.getLocationList();
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

                            if (AppSharedPreferences.getInstance().getInt(AppSharedPreferences.KEY_CREATED_INVENTORY_PARTIAL_ID) == 0) {
                                AppSharedPreferences.getInstance().setInt(AppSharedPreferences.KEY_SELECTED_INVENTORY_PARTIAL_ID, inProgressInventoryResults.getInventoryList().get(0).getPartialID());

                                if (inProgressInventoryResults.getInventoryList().get(0).getName().isEmpty())
                                    fragmentInventoryBinding.inventorySelection.setText(inProgressInventoryResults.getInventoryList().get(0).getDateStarted());
                                else
                                    fragmentInventoryBinding.inventorySelection.setText(inProgressInventoryResults.getInventoryList().get(0).getName() + getString(R.string.started) + inProgressInventoryResults.getInventoryList().get(0).getDateStarted());
                            } else {
                                AppSharedPreferences.getInstance().setInt(AppSharedPreferences.KEY_SELECTED_INVENTORY_PARTIAL_ID, AppSharedPreferences.getInstance().getInt(AppSharedPreferences.KEY_CREATED_INVENTORY_PARTIAL_ID));

                                /*if (inProgressInventoryResults.getInventoryList().stream().filter(p -> p.getPartialID() == AppSharedPreferences.getInstance().getInt(AppSharedPreferences.KEY_CREATED_INVENTORY_PARTIAL_ID)))
                                    fragmentInventoryBinding.inventorySelection.setText(inProgressInventoryResults.getInventoryList().get(0).getDateStarted());
                                else
                                    fragmentInventoryBinding.inventorySelection.setText(inProgressInventoryResults.getInventoryList().get(0).getName() + getString(R.string.started) + inProgressInventoryResults.getInventoryList().get(0).getDateStarted());
*/
                            }
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


    @Override
    public void onBarcodeEvent(final BarcodeReadEvent event) {
        inventoryViewModel.onBarcodeFailureEvent(mBarcodeReader);
        getBaseActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (event != null) {
                    String barcode = event.getBarcodeData();
                    fragmentInventoryBinding.patronEntryIncludeLayout.patronEntry.setText(barcode);
                    inventoryViewModel.inventoryScan(AppUtils.getInstance().getEditTextValue(fragmentInventoryBinding.patronEntryIncludeLayout.patronEntry));
                }
            }
        });
    }

    @Override
    public void onFailureEvent(BarcodeFailureEvent barcodeFailureEvent) {
        inventoryViewModel.onBarcodeFailureEvent(mBarcodeReader);
    }

    @Override
    public void onResume() {
        super.onResume();
        inventoryViewModel.onResumeScanner(mBarcodeReader);
    }

    @Override
    public void onPause() {
        super.onPause();
        inventoryViewModel.onPauseScanner(mBarcodeReader);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mBarcodeReader != null) {
            mBarcodeReader.removeBarcodeListener(this);
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (checkedId == R.id.barcodedBtn) {
            if (getActivity() != null) {
                fragmentInventoryBinding.barcodedBtn.setTextColor(ContextCompat.getColor(getActivity(), R.color.blueLabel));
                fragmentInventoryBinding.unBarcodedBtn.setTextColor(ContextCompat.getColor(getActivity(), R.color.black));
            }
        } else if (checkedId == R.id.unBarcodedBtn) {
            if (getActivity() != null) {
                fragmentInventoryBinding.barcodedBtn.setTextColor(ContextCompat.getColor(getActivity(), R.color.black));
                fragmentInventoryBinding.unBarcodedBtn.setTextColor(ContextCompat.getColor(getActivity(), R.color.blueLabel));
            }
        }
    }
}
