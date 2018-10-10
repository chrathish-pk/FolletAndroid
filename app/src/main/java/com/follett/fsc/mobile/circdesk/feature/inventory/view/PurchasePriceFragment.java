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
import com.follett.fsc.mobile.circdesk.databinding.FragmentPurchasePriceBinding;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.PurchasePriceItem;
import com.follett.fsc.mobile.circdesk.feature.inventory.viewmodel.PurchasePriceViewModel;
import com.follett.fsc.mobile.circdesk.feature.loginsetup.view.SetupActivity;

import java.util.List;

public class PurchasePriceFragment extends BaseFragment<FragmentPurchasePriceBinding, PurchasePriceViewModel> implements View.OnClickListener, ItemClickListener {

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

        fragmentPurchasePriceBinding.recyclerviewPriceItem.setLayoutManager(new LinearLayoutManager(getActivity()));
        mActivity.baseBinding.backBtn.setOnClickListener(this);
        if (AppRemoteRepository.getInstance().getString(AppSharedPreferences.KEY_SELECTED_PRICE_LIMITER_VALUE).isEmpty()) {
            fragmentPurchasePriceBinding.purchasePriceEntry.setText("0");
        } else {
            fragmentPurchasePriceBinding.purchasePriceEntry.setText(AppRemoteRepository.getInstance().getString(AppSharedPreferences.KEY_SELECTED_PRICE_LIMITER_VALUE));
        }
        purchasePriceViewModel.setPriceItemData();
        purchasePriceViewModel.priceItemListMutableLiveData.observeForever(new Observer<List<PurchasePriceItem>>() {
            @Override
            public void onChanged(@Nullable List<PurchasePriceItem> purchasePriceItems) {

                PurchasePriceAdapter purchasePriceAdapter = new PurchasePriceAdapter(getActivity(), purchasePriceItems, PurchasePriceFragment.this);
                fragmentPurchasePriceBinding.recyclerviewPriceItem.setAdapter(purchasePriceAdapter);

            }
        });

        mActivity.baseBinding.backBtn.setOnClickListener(this);
    }


   /* @Override
    public void onClick(View v) {
        if (v.getId() == R.id.backBtn) {
            AppSharedPreferences.getInstance().setString(AppSharedPreferences.KEY_PURCHASEPRICE_VALUE, fragmentPurchasePriceBinding.purchasePriceEntry.getText().toString());
            if (getActivity() != null) {
                ((SetupActivity) getActivity()).selectedData.postValue(true);
            }
            mActivity.onBackPressed();
        }
    }*/

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.backBtn) {
            if (!fragmentPurchasePriceBinding.purchasePriceEntry.getText().toString().trim().isEmpty())
                AppSharedPreferences.getInstance().setString(AppSharedPreferences.KEY_SELECTED_PRICE_LIMITER_VALUE, fragmentPurchasePriceBinding.purchasePriceEntry.getText().toString().trim());

            if (getActivity() != null) {
                ((SetupActivity) getActivity()).selectedData.postValue(true);
            }

            mActivity.onBackPressed();

        }
    }

    @Override
    public void onItemClick(View view, int position) {
        //do nothing
    }
}
