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
import com.follett.fsc.mobile.circdesk.data.remote.repository.AppRemoteRepository;
import com.follett.fsc.mobile.circdesk.databinding.FragmentInventoryCheckoutHandlingBinding;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.CheckoutHandling;
import com.follett.fsc.mobile.circdesk.feature.inventory.viewmodel.InventoryCheckoutHandlingViewModel;
import com.follett.fsc.mobile.circdesk.feature.loginsetup.view.SetupActivity;
import com.follett.fsc.mobile.circdesk.utils.AppUtils;

import java.util.List;

import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_SELECTED_CHECKOUT_HANDLING;
import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_SELECTED_INCLUDE_ITEMS;


public class InventoryCheckoutHandlingFragment extends BaseFragment<FragmentInventoryCheckoutHandlingBinding, InventoryCheckoutHandlingViewModel> implements ItemClickListener, View.OnClickListener {

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
        return inventoryCheckoutHandlingViewModel;
    }

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

                String checkoutHandlingItem;

                if(AppRemoteRepository.getInstance().getString(KEY_SELECTED_CHECKOUT_HANDLING).isEmpty()) {
                    checkoutHandlingItem = getString(R.string.checkInItemsInCirculation);
                }else{
                    checkoutHandlingItem = AppRemoteRepository.getInstance().getString(KEY_SELECTED_CHECKOUT_HANDLING);
                }
                String[] checkoutHandlingItemArr = checkoutHandlingItem.split(",");
                for (int i = 0; i < checkoutHandlingItemArr.length; i++) {
                    for (int j = 0; j < checkoutHandlingList.size(); j++) {
                        if (checkoutHandlingList.get(j).getCheckoutHandlingName().equalsIgnoreCase(checkoutHandlingItemArr[i])) {
                            checkoutHandlingList.get(j).setSelected(true);
                            break;
                        }
                    }
                }
                InventoryCheckoutHandlingAdapter inventoryCheckoutHandlingAdapter = new InventoryCheckoutHandlingAdapter(getActivity(), checkoutHandlings, InventoryCheckoutHandlingFragment.this);
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
            String selectedCheckoutHandling = null;
            for (CheckoutHandling checkoutHandling : checkoutHandlingList) {
                if (checkoutHandling.isSelected()) {
                    if (checkoutHandling.getCheckoutHandlingID() == 0) {
                        AppSharedPreferences.getInstance().setBoolean(AppSharedPreferences.KEY_IS_CHECKOUT_HANDLING_UNACCOUNTED_SELECTED, true);
                    } else {
                        AppSharedPreferences.getInstance().setBoolean(AppSharedPreferences.KEY_IS_CHECKOUT_HANDLING_ITEMS_IN_CIRCULATION_SELECTED, true);
                    }
                    if (selectedCheckoutHandling == null) {
                        selectedCheckoutHandling = checkoutHandling.getCheckoutHandlingName();
                    } else {
                        selectedCheckoutHandling = selectedCheckoutHandling + "," + checkoutHandling.getCheckoutHandlingName();
                    }
                } else {
                    if (checkoutHandling.getCheckoutHandlingID() == 0) {
                        AppSharedPreferences.getInstance().setBoolean(AppSharedPreferences.KEY_IS_CHECKOUT_HANDLING_UNACCOUNTED_SELECTED, false);
                    } else {
                        AppSharedPreferences.getInstance().setBoolean(AppSharedPreferences.KEY_IS_CHECKOUT_HANDLING_ITEMS_IN_CIRCULATION_SELECTED, false);
                    }
                }
            }
            AppSharedPreferences.getInstance().setString(KEY_SELECTED_CHECKOUT_HANDLING, selectedCheckoutHandling);


            if (AppRemoteRepository.getInstance().getString(AppSharedPreferences.KEY_SELECTED_CHECKOUT_HANDLING).isEmpty())
            {
                AppUtils.getInstance()
                        .showShortToastMessages(mActivity, getString(R.string.errorMsg));
            } else {
                if (getActivity() != null) {
                    ((SetupActivity) getActivity()).selectedData.postValue(true);
                }
                mActivity.onBackPressed();
            }


        }
    }
}
