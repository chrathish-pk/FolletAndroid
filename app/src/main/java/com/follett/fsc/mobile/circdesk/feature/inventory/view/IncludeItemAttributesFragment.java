package com.follett.fsc.mobile.circdesk.feature.inventory.view;


import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.follett.fsc.mobile.circdesk.app.ItemClickListener;
import com.follett.fsc.mobile.circdesk.databinding.FragmentIncludeItemAttributesBinding;
import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.app.base.BaseFragment;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.IncludeItem;
import com.follett.fsc.mobile.circdesk.feature.inventory.viewmodel.IncludeItemAttributesViewModel;

import java.util.ArrayList;

public class IncludeItemAttributesFragment extends BaseFragment<FragmentIncludeItemAttributesBinding,IncludeItemAttributesViewModel> implements ItemClickListener
{

    FragmentIncludeItemAttributesBinding fragmentIncludeItemAttributesBinding;
    IncludeItemAttributesViewModel includeItemAttributesViewModel;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_include_item_attributes;
    }

    @Override
    public IncludeItemAttributesViewModel getViewModel() {
        includeItemAttributesViewModel = new IncludeItemAttributesViewModel(getBaseActivity().getApplication());
        return includeItemAttributesViewModel;
    }

    @Override
    public int getBindingVariable() {
        return 0;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fragmentIncludeItemAttributesBinding = getViewDataBinding();
        fragmentIncludeItemAttributesBinding.recyclerviewIncludeItem.setLayoutManager(new LinearLayoutManager(getActivity()));
        includeItemAttributesViewModel.setIncludeItemData();
        includeItemAttributesViewModel.includeItemListMutableLiveData.observeForever(new Observer<ArrayList<IncludeItem>>() {
            @Override
            public void onChanged(@Nullable ArrayList<IncludeItem> includeItems) {

                IncludeItemAttributesAdapter includeItemAttributesAdapter = new IncludeItemAttributesAdapter(getActivity(), includeItems, IncludeItemAttributesFragment.this);
                fragmentIncludeItemAttributesBinding.recyclerviewIncludeItem.setAdapter(includeItemAttributesAdapter);
            }
        });

    }

    @Override
    public void onItemClick(View view, int position) {
        //do nothing
    }
}
