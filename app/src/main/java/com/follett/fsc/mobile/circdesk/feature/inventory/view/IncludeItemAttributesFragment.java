package com.follett.fsc.mobile.circdesk.feature.inventory.view;


import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.app.ItemClickListener;
import com.follett.fsc.mobile.circdesk.app.base.BaseFragment;
import com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences;
import com.follett.fsc.mobile.circdesk.data.remote.repository.AppRemoteRepository;
import com.follett.fsc.mobile.circdesk.databinding.FragmentIncludeItemAttributesBinding;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.IncludeItem;
import com.follett.fsc.mobile.circdesk.feature.inventory.viewmodel.IncludeItemAttributesViewModel;
import com.follett.fsc.mobile.circdesk.feature.loginsetup.view.SetupActivity;
import com.follett.fsc.mobile.circdesk.utils.AppUtils;

import java.util.List;

import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_SELECTED_INCLUDE_ITEMS;

public class IncludeItemAttributesFragment extends BaseFragment<FragmentIncludeItemAttributesBinding, IncludeItemAttributesViewModel> implements ItemClickListener, View.OnClickListener {

    FragmentIncludeItemAttributesBinding fragmentIncludeItemAttributesBinding;
    IncludeItemAttributesViewModel includeItemAttributesViewModel;
    private List<IncludeItem> includeItemListData;

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
        includeItemAttributesViewModel.includeItemListMutableLiveData.observeForever(new Observer<List<IncludeItem>>() {
            @Override
            public void onChanged(@Nullable List<IncludeItem> includeItemList) {
                includeItemListData = includeItemList;
                String includeItem;

                if(AppRemoteRepository.getInstance().getString(KEY_SELECTED_INCLUDE_ITEMS).isEmpty()) {
                    includeItem = getString(R.string.barcoded);
                }else{
                    includeItem = AppRemoteRepository.getInstance().getString(KEY_SELECTED_INCLUDE_ITEMS);
                }
                    //includeItem = AppRemoteRepository.getInstance().getString(KEY_SELECTED_INCLUDE_ITEMS);
                    String[] includeItemArr = includeItem.split(",");
                    for (int i = 0; i < includeItemArr.length; i++) {
                        for (int j = 0; j < includeItemListData.size(); j++) {
                            if (includeItemListData.get(j).getIncludeItemName().equalsIgnoreCase(includeItemArr[i])) {
                                includeItemListData.get(j).setSelected(true);
                                break;
                            }
                        }
                    }

                IncludeItemAttributesAdapter includeItemAttributesAdapter = new IncludeItemAttributesAdapter(getActivity(), includeItemList, IncludeItemAttributesFragment.this);
                fragmentIncludeItemAttributesBinding.recyclerviewIncludeItem.setAdapter(includeItemAttributesAdapter);
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
            for (IncludeItem includeItem : includeItemListData) {
                if (includeItem.isSelected()) {
                    if (includeItem.getIncludeItemID() == 0) {
                        AppSharedPreferences.getInstance().setBoolean(AppSharedPreferences.KEY_IS_INCLUDE_BARCODED_SELECTED, true);
                    } else if (includeItem.getIncludeItemID() == 1) {
                        AppSharedPreferences.getInstance().setBoolean(AppSharedPreferences.KEY_IS_INCLUDE_UNBARCODED_SELECTED, true);
                    } else {
                        AppSharedPreferences.getInstance().setBoolean(AppSharedPreferences.KEY_IS_INCLUDE_CONSUMMABLE_SELECTED, true);
                    }
                    if (selectedSubLocation == null) {
                        selectedSubLocation = includeItem.getIncludeItemName();
                    } else {
                        selectedSubLocation = selectedSubLocation + "," + includeItem.getIncludeItemName();
                    }
                } else {


                    if (includeItem.getIncludeItemID() == 0) {
                        AppSharedPreferences.getInstance().setBoolean(AppSharedPreferences.KEY_IS_INCLUDE_BARCODED_SELECTED, false);
                    } else if (includeItem.getIncludeItemID() == 1) {
                        AppSharedPreferences.getInstance().setBoolean(AppSharedPreferences.KEY_IS_INCLUDE_UNBARCODED_SELECTED, false);
                    } else {
                        AppSharedPreferences.getInstance().setBoolean(AppSharedPreferences.KEY_IS_INCLUDE_CONSUMMABLE_SELECTED, false);
                    }
                }
            }
            AppSharedPreferences.getInstance().setString(AppSharedPreferences.KEY_SELECTED_INCLUDE_ITEMS, selectedSubLocation);

            if (AppRemoteRepository.getInstance().getString(AppSharedPreferences.KEY_SELECTED_INCLUDE_ITEMS).isEmpty())
                AppUtils.getInstance()
                        .showShortToastMessages(mActivity, getString(R.string.errorMsg));
            else {
                if (getActivity() != null) {
                    ((SetupActivity) getActivity()).selectedData.postValue(true);
                }
                mActivity.onBackPressed();
            }
        }
    }
}
