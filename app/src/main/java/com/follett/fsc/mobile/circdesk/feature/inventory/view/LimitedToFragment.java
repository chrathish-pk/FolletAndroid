package com.follett.fsc.mobile.circdesk.feature.inventory.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.follett.fsc.mobile.circdesk.BR;
import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.app.ItemClickListener;
import com.follett.fsc.mobile.circdesk.app.base.BaseFragment;
import com.follett.fsc.mobile.circdesk.databinding.FragmentLimitedToBinding;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.LimitedToParentData;
import com.follett.fsc.mobile.circdesk.feature.inventory.viewmodel.LimitedToViewModel;
import com.follett.fsc.mobile.circdesk.feature.itemstatus.view.UpdateItemUIListener;

import java.util.ArrayList;
import java.util.List;

public class LimitedToFragment extends BaseFragment<FragmentLimitedToBinding, LimitedToViewModel> implements ItemClickListener, UpdateItemUIListener {

    private FragmentLimitedToBinding fragmentLimitedToBinding;
    private LimitedToViewModel limitedToViewModel;
    private LimitedToAdapter limitedToAdapter;
    private List<LimitedToParentData> limitedToParentDataList = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.fragment_limited_to;
    }

    @Override
    public LimitedToViewModel getViewModel() {
        limitedToViewModel = new LimitedToViewModel(getBaseActivity().getApplication(), this);
        return limitedToViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.limitedToViewModel;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fragmentLimitedToBinding = getViewDataBinding();

        limitedToViewModel.fetchSubLocationList();

        fragmentLimitedToBinding.limitedToParentRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void onItemClick(View view, int position) {
        if (view.getId() == R.id.itemLimitedToRadioBtn) {
            for (int i = 0; i < limitedToParentDataList.size(); i++) {
                if (i == position)
                    limitedToParentDataList.get(i).setSelected(true);
                else
                    limitedToParentDataList.get(i).setSelected(false);
            }

            limitedToAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void updateUI(Object value) {
        limitedToParentDataList = limitedToViewModel.getLimitedToParentData();
        limitedToAdapter = new LimitedToAdapter(getActivity(), limitedToParentDataList, this);
        fragmentLimitedToBinding.limitedToParentRecyclerView.setAdapter(limitedToAdapter);
    }
}
