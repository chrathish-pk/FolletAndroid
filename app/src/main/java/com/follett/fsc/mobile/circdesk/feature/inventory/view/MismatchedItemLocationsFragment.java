package com.follett.fsc.mobile.circdesk.feature.inventory.view;


import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.app.ItemClickListener;
import com.follett.fsc.mobile.circdesk.app.base.BaseFragment;
import com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences;
import com.follett.fsc.mobile.circdesk.databinding.FragmentMismatchedItemLocationsBinding;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.MismatchedItemLocation;
import com.follett.fsc.mobile.circdesk.feature.inventory.viewmodel.MismatchedItemLocationsViewModel;
import com.follett.fsc.mobile.circdesk.feature.loginsetup.view.SetupActivity;

import java.util.ArrayList;
import java.util.List;

public class MismatchedItemLocationsFragment extends BaseFragment<FragmentMismatchedItemLocationsBinding, MismatchedItemLocationsViewModel> implements ItemClickListener, View.OnClickListener {

    private FragmentMismatchedItemLocationsBinding fragmentMismatchedItemLocationsBinding;
    private MismatchedItemLocationsViewModel mismatchedItemLocationsViewModel;
    private List<MismatchedItemLocation> mismatchedItemLocationList;
    private String selectedMismatchedItem;

    public MismatchedItemLocationsFragment() {
        // Required empty public constructor
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_mismatched_item_locations;
    }

    @Override
    public MismatchedItemLocationsViewModel getViewModel() {
        mismatchedItemLocationsViewModel = new MismatchedItemLocationsViewModel(getBaseActivity().getApplication());
        return mismatchedItemLocationsViewModel;
    }

    @Override
    public int getBindingVariable() {
        return 0;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fragmentMismatchedItemLocationsBinding = getViewDataBinding();
        fragmentMismatchedItemLocationsBinding.recyclerviewMismatcheditemlocation.setLayoutManager(new LinearLayoutManager(getActivity()));
        mismatchedItemLocationsViewModel.setMismatchedItemLocationsData();

        mismatchedItemLocationsViewModel.mismatchedItemLocListMutableLiveData.observeForever(new Observer<ArrayList<MismatchedItemLocation>>() {
            @Override
            public void onChanged(@Nullable ArrayList<MismatchedItemLocation> mismatchedItemLocations) {
                mismatchedItemLocationList = mismatchedItemLocations;
                MismatchedItemLocationsAdapter mismatchedItemLocationsAdapter = new MismatchedItemLocationsAdapter(getActivity(), mismatchedItemLocations, MismatchedItemLocationsFragment.this);
                fragmentMismatchedItemLocationsBinding.recyclerviewMismatcheditemlocation.setAdapter(mismatchedItemLocationsAdapter);

            }
        });

        mActivity.baseBinding.backBtn.setOnClickListener(this);
    }

    @Override
    public void onItemClick(View view, int position) {
        selectedMismatchedItem = mismatchedItemLocationList.get(position).getMismatchedItemLocationName();

        //Toast.makeText(getContext(), "Status" + mismatchedItemLocationsViewModel.mismatchedItemLocListMutableLiveData.getValue().get(position).getMismatchedItemLocationStatus(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.backBtn) {
            AppSharedPreferences.getInstance().setString(AppSharedPreferences.KEY_SELECTED_MISMATCHED_ITEM, selectedMismatchedItem);
            if (getActivity() != null) {
                ((SetupActivity) getActivity()).selectedData.postValue(true);
            }
            mActivity.onBackPressed();
        }
    }
}
