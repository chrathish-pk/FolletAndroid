package com.follett.fsc.mobile.circdesk.feature.inventory.view;

import android.arch.lifecycle.Observer;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.follett.fsc.mobile.circdesk.BR;
import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.app.ItemClickListener;
import com.follett.fsc.mobile.circdesk.app.base.BaseFragment;
import com.follett.fsc.mobile.circdesk.databinding.FragmentInventoryCheckoutHandlingBinding;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.CheckoutHandling;
import com.follett.fsc.mobile.circdesk.feature.inventory.viewmodel.IncludeItemAttributesViewModel;
import com.follett.fsc.mobile.circdesk.feature.inventory.viewmodel.InventoryCheckoutHandlingViewModel;

import java.util.ArrayList;


public class InventoryCheckoutHandlingFragment extends BaseFragment<FragmentInventoryCheckoutHandlingBinding,InventoryCheckoutHandlingViewModel> implements ItemClickListener {

   private FragmentInventoryCheckoutHandlingBinding fragmentInventoryCheckoutHandlingBinding;
   private InventoryCheckoutHandlingViewModel inventoryCheckoutHandlingViewModel;

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
        inventoryCheckoutHandlingViewModel.checkoutHnadlingListMutableLiveData.observeForever(new Observer<ArrayList<CheckoutHandling>>() {
            @Override
            public void onChanged(@Nullable ArrayList<CheckoutHandling> checkoutHandlings) {

                InventoryCheckoutHandlingAdapter inventoryCheckoutHandlingAdapter = new InventoryCheckoutHandlingAdapter(getActivity(),checkoutHandlings,InventoryCheckoutHandlingFragment.this);
                fragmentInventoryCheckoutHandlingBinding.recyclerviewCheckouthandling.setAdapter(inventoryCheckoutHandlingAdapter);
            }
        });
    }

    @Override
    public void onItemClick(View view, int position) {

        //do nothing
    }
}
