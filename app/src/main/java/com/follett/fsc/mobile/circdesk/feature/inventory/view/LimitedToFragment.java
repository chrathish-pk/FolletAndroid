package com.follett.fsc.mobile.circdesk.feature.inventory.view;

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
import com.follett.fsc.mobile.circdesk.databinding.FragmentLimitedToBinding;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.LimitedToParentData;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.SublocationList;
import com.follett.fsc.mobile.circdesk.feature.inventory.viewmodel.LimitedToViewModel;
import com.follett.fsc.mobile.circdesk.feature.itemstatus.view.UpdateItemUIListener;
import com.follett.fsc.mobile.circdesk.feature.loginsetup.view.SetupActivity;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class LimitedToFragment extends BaseFragment<FragmentLimitedToBinding, LimitedToViewModel> implements ItemClickListener, UpdateItemUIListener, View.OnClickListener {

    private FragmentLimitedToBinding fragmentLimitedToBinding;
    private LimitedToViewModel limitedToViewModel;
    private LimitedToAdapter limitedToAdapter;
    private List<LimitedToParentData> limitedToParentDataList = new ArrayList<>();
    private int selectedParentPostion;

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

        mActivity.baseBinding.backBtn.setOnClickListener(this);
    }

    @Override
    public void onItemClick(View view, int position) {
        if (view.getId() == R.id.itemLimitedToRadioBtn) {
            selectedParentPostion = position;
            for (int i = 0; i < limitedToParentDataList.size(); i++) {
                if (i == position) {
                    if (i == 0) {
                        AppSharedPreferences.getInstance().setBoolean(AppSharedPreferences.KEY_IS_UNLIMITED_SELECTED, false);
                    } else {
                        AppSharedPreferences.getInstance().setBoolean(AppSharedPreferences.KEY_IS_UNLIMITED_SELECTED, true);
                    }
                    AppSharedPreferences.getInstance().setInt(AppSharedPreferences.KEY_SELECTED_LIMITED_TO_ID, limitedToParentDataList.get(i).getLimitedToParentID());
                    limitedToParentDataList.get(i).setSelected(true);
                } else
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

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.backBtn) {
            String selectedSubLocation = null;
            List<Integer> selectedLimitedValueIDList = new ArrayList<>();
            if (selectedParentPostion == 1) {
                for (SublocationList subLocationList : limitedToParentDataList.get(selectedParentPostion).getSubLocation().getSublocationList()) {
                    if (subLocationList.isSelected()) {
                        if (selectedSubLocation == null) {
                            selectedSubLocation = subLocationList.getSublocationName();
                        } else {
                            selectedSubLocation = selectedSubLocation + "," + subLocationList.getSublocationName();
                        }
                        selectedLimitedValueIDList.add(subLocationList.getSublocationID());
                    }
                }
            }
            AppSharedPreferences.getInstance().setString(AppSharedPreferences.KEY_SELECTED_LIMITED_TO_LIST, selectedSubLocation);

            String subLocationJSONString = new Gson().toJson(selectedLimitedValueIDList);
            AppRemoteRepository.getInstance().setString(AppSharedPreferences.KEY_SELECTED_LIMITED_TO_LIST_JSON, subLocationJSONString);

            if (getActivity() != null) {
                ((SetupActivity) getActivity()).selectedData.postValue(true);
            }
            mActivity.onBackPressed();
        }
    }
}
