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
import com.follett.fsc.mobile.circdesk.databinding.FragmentSubLocationBinding;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.SubLocation;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.SublocationList;
import com.follett.fsc.mobile.circdesk.feature.inventory.viewmodel.SubLocationViewModel;
import com.follett.fsc.mobile.circdesk.feature.loginsetup.view.SetupActivity;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;


public class SubLocationFragment extends BaseFragment<FragmentSubLocationBinding, SubLocationViewModel> implements ItemClickListener, View.OnClickListener {

    private FragmentSubLocationBinding fragmentSubLocationBinding;
    private SubLocationViewModel subLocationViewModel;
    private SubLocation subLocationData;

    public SubLocationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fragmentSubLocationBinding = getViewDataBinding();

        fragmentSubLocationBinding.recyclerviewSublocationlist.setLayoutManager(new LinearLayoutManager(getActivity()));
        subLocationViewModel.fetchSubLocationList();

        mActivity.baseBinding.backBtn.setOnClickListener(this);

        subLocationViewModel.subLocationListMutableLiveData.observeForever(new Observer<SubLocation>() {
            @Override
            public void onChanged(@Nullable SubLocation subLocation) {
                subLocationData = subLocation;
                String subLocationItem = AppRemoteRepository.getInstance().getString(AppSharedPreferences.KEY_SELECTED_SUB_LOCATION);
                if(!subLocationItem.isEmpty()) {
                    String[] includeItemArr = subLocationItem.split(",");
                    for (int i = 0; i < includeItemArr.length; i++) {
                        for (int j = 0; j < subLocationData.getSublocationList().size(); j++) {
                            if (subLocationData.getSublocationList().get(j).getSublocationName().equalsIgnoreCase(includeItemArr[i])) {
                                subLocationData.getSublocationList().get(j).setSelected(true);
                                break;
                            }
                        }
                    }
                }

                SubLocationListAdapter subLocationListAdapter = new SubLocationListAdapter(getActivity(), subLocation, SubLocationFragment.this);
                fragmentSubLocationBinding.recyclerviewSublocationlist.setAdapter(subLocationListAdapter);
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_sub_location;
    }

    @Override
    public SubLocationViewModel getViewModel() {
        subLocationViewModel = new SubLocationViewModel(getBaseActivity().getApplication());
        return subLocationViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public void onItemClick(View view, int position) {

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.backBtn) {
            String selectedSubLocation = null;
            List<Integer> subLocationIDList = new ArrayList<>();
            for (SublocationList subLocationList : subLocationData.getSublocationList()) {
                if (subLocationList.isSelected()) {
                    if (selectedSubLocation == null) {
                        selectedSubLocation = subLocationList.getSublocationName();
                    } else {
                        selectedSubLocation = selectedSubLocation + "," + subLocationList.getSublocationName();
                    }
                    subLocationIDList.add(subLocationList.getSublocationID());
                }
            }
            AppSharedPreferences.getInstance().setString(AppSharedPreferences.KEY_SELECTED_SUB_LOCATION, selectedSubLocation);

            String subLocationJSONString = new Gson().toJson(subLocationIDList);
            AppRemoteRepository.getInstance().setString(AppSharedPreferences.KEY_SELECTED_SUB_LOCATION_JSON, subLocationJSONString);

            if (getActivity() != null) {
                ((SetupActivity) getActivity()).selectedData.postValue(true);
            }
            mActivity.onBackPressed();
        }
    }

}
