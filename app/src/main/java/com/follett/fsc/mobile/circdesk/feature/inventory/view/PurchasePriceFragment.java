package com.follett.fsc.mobile.circdesk.feature.inventory.view;


import android.os.Bundle;
import android.support.annotation.Nullable;

import com.follett.fsc.mobile.circdesk.BR;
import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.app.base.BaseFragment;
import com.follett.fsc.mobile.circdesk.feature.inventory.viewmodel.PurchasePriceViewModel;
import com.follett.fsc.mobile.circdesk.databinding.FragmentPurchasePriceBinding;

public class PurchasePriceFragment extends BaseFragment<FragmentPurchasePriceBinding,PurchasePriceViewModel> {

   private FragmentPurchasePriceBinding fragmentPurchasePriceBinding;
   private PurchasePriceViewModel purchasePriceViewModel;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_purchase_price;
    }

    @Override
    public PurchasePriceViewModel getViewModel() {
        purchasePriceViewModel = new PurchasePriceViewModel(getBaseActivity().getApplication());
        return purchasePriceViewModel;
   }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fragmentPurchasePriceBinding = getViewDataBinding();


    }
}
