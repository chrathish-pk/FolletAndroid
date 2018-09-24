/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.inventory;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.follett.fsc.mobile.circdesk.BR;
import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.app.ItemClickListener;
import com.follett.fsc.mobile.circdesk.app.base.BaseFragment;
import com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences;
import com.follett.fsc.mobile.circdesk.databinding.FragmentInventoryBinding;
import com.follett.fsc.mobile.circdesk.feature.checkoutcheckin.UpdateUIListener;
import com.follett.fsc.mobile.circdesk.utils.FollettLog;

import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_COLLECTION_TYPE;

/**
 * Created by muthulakshmi on 11/09/18.
 */

public class InventoryFragment extends BaseFragment<FragmentInventoryBinding, InventoryViewModel> implements ItemClickListener, View.OnClickListener, UpdateUIListener {

    private InventoryViewModel inventoryViewModel;
    private FragmentInventoryBinding fragmentInventoryBinding;
    private TextView libraryBtn;
    private TextView resourceBtn;
    private InProgressInventoryResults inProgressInventoryResults;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_inventory;
    }

    @Override
    public InventoryViewModel getViewModel() {
        inventoryViewModel = new InventoryViewModel(getBaseActivity().getApplication(), this, this);
        return inventoryViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.inventoryViewModel;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backBtn:
                mActivity.onBackPressed();
                break;
            case R.id.libraryBtn:
                fragmentInventoryBinding.inventoryLocation.setVisibility(View.GONE);
                fragmentInventoryBinding.inventoryLocationBar.setVisibility(View.GONE);
                libraryBtn.setBackgroundColor(mActivity.getResources().getColor(R.color.blueLabel));
                libraryBtn.setTextColor(mActivity.getResources().getColor(R.color.white));
                resourceBtn.setBackgroundColor(mActivity.getResources().getColor(R.color.white));
                resourceBtn.setTextColor(mActivity.getResources().getColor(R.color.blueLabel));
                AppSharedPreferences.getInstance().setInt(KEY_COLLECTION_TYPE, 0);

                break;
            case R.id.resourceBtn:
                fragmentInventoryBinding.inventoryLocation.setVisibility(View.VISIBLE);
                fragmentInventoryBinding.inventoryLocationBar.setVisibility(View.VISIBLE);
                libraryBtn.setBackgroundColor(mActivity.getResources().getColor(R.color.white));
                libraryBtn.setTextColor(mActivity.getResources().getColor(R.color.blueLabel));
                resourceBtn.setBackgroundColor(mActivity.getResources().getColor(R.color.blueLabel));
                resourceBtn.setTextColor(mActivity.getResources().getColor(R.color.white));
                AppSharedPreferences.getInstance().setInt(KEY_COLLECTION_TYPE, 4);

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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fragmentInventoryBinding = getViewDataBinding();

        inventoryViewModel.getInProgressInventoryResults(AppSharedPreferences.getInstance()
                .getString(AppSharedPreferences.KEY_SITE_SHORT_NAME), AppSharedPreferences.getInstance()
                .getString(AppSharedPreferences.KEY_CONTEXT_NAME), AppSharedPreferences.getInstance()
                .getInt(KEY_COLLECTION_TYPE));
        initViews();

    }

    private void initViews() {
        mActivity.setTitleBar(getString(R.string.inventory));
        mActivity.setBackBtnVisible();
        mActivity.baseBinding.backBtn.setOnClickListener(this);
        libraryBtn = (TextView) mActivity.findViewById(R.id.libraryBtn);
        libraryBtn.setOnClickListener(this);
        resourceBtn = (TextView) mActivity.findViewById(R.id.resourceBtn);
        resourceBtn.setOnClickListener(this);

        fragmentInventoryBinding.finalizeInventoryBtn.setOnClickListener(this);

        fragmentInventoryBinding.patronEntryIncludeLayout.patronEntry.setHint(getString(R.string.enterBarcode));
        fragmentInventoryBinding.patronEntryIncludeLayout.checkinLibRecordSwitch.setVisibility(View.GONE);
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
                                    +" - "+inProgressInventoryResults.getInventoryList().get(0).getDateStarted());
                        } else {
                            fragmentInventoryBinding.inventorySelection.setVisibility(View.GONE);

                        }
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
