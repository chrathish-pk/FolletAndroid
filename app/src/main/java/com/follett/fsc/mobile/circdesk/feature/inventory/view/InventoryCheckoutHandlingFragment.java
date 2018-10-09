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
import com.follett.fsc.mobile.circdesk.databinding.FragmentInventoryCheckoutHandlingBinding;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.CheckoutHandling;
import com.follett.fsc.mobile.circdesk.feature.inventory.viewmodel.InventoryCheckoutHandlingViewModel;
import com.follett.fsc.mobile.circdesk.feature.loginsetup.view.SetupActivity;

import java.util.List;


public class InventoryCheckoutHandlingFragment extends BaseFragment<FragmentInventoryCheckoutHandlingBinding,InventoryCheckoutHandlingViewModel> implements ItemClickListener, View.OnClickListener {

   private FragmentInventoryCheckoutHandlingBinding fragmentInventoryCheckoutHandlingBinding;
   private InventoryCheckoutHandlingViewModel inventoryCheckoutHandlingViewModel;
   private List<CheckoutHandling> checkoutHandlingList;

    public InventoryCheckoutHandlingFragment() {
        // Required empty public constructor
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_inventory_checkout_handling;
    }

    @Override
    public InventoryCheckoutHandlingViewModel getViewModel() {
        inventoryCheckoutHandlingViewModel = new InventoryCheckoutHandlingViewModel(getBaseActivity().getApplication());
        return inventoryCheckoutHandlingViewModel;    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fragmentInventoryCheckoutHandlingBinding = getViewDataBinding();
        fragmentInventoryCheckoutHandlingBinding.recyclerviewCheckouthandling.setLayoutManager(new LinearLayoutManager(getActivity()));
        inventoryCheckoutHandlingViewModel.setCheckoutHandlingData();
        inventoryCheckoutHandlingViewModel.checkoutHnadlingListMutableLiveData.observeForever(new Observer<List<CheckoutHandling>>() {
            @Override
            public void onChanged(@Nullable List<CheckoutHandling> checkoutHandlings) {
                checkoutHandlingList = checkoutHandlings;
                InventoryCheckoutHandlingAdapter inventoryCheckoutHandlingAdapter = new InventoryCheckoutHandlingAdapter(getActivity(),checkoutHandlings,InventoryCheckoutHandlingFragment.this);
                fragmentInventoryCheckoutHandlingBinding.recyclerviewCheckouthandling.setAdapter(inventoryCheckoutHandlingAdapter);
            }
        });
        mActivity.baseBinding.backBtn.setOnClickListener(this);

    }

    @Override
    public void onItemClick(View view, int position) {

        //do nothing
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.backBtn) {
            String selectedSubLocation = null;
            for (CheckoutHandling checkoutHandling : checkoutHandlingList) {
                if (checkoutHandling.isSelected()) {
                    if (selectedSubLocation == null) {
                        selectedSubLocation = checkoutHandling.getCheckoutHandlingName();
                    } else {
                        selectedSubLocation = selectedSubLocation + "," + checkoutHandling.getCheckoutHandlingName();
                    }
                }
            }
            AppSharedPreferences.getInstance().setString(AppSharedPreferences.KEY_SELECTED_CHECKOUT_HANDLING, selectedSubLocation);

            if (getActivity() != null) {
                ((SetupActivity) getActivity()).selectedData.postValue(true);
            }
            mActivity.onBackPressed();
        }
    }
}
