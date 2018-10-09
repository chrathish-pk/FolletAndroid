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
import com.follett.fsc.mobile.circdesk.databinding.FragmentMismatchedItemLocationsBinding;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.MismatchedItemLocation;
import com.follett.fsc.mobile.circdesk.feature.inventory.viewmodel.MismatchedItemLocationsViewModel;

import java.util.ArrayList;

public class MismatchedItemLocationsFragment extends BaseFragment<FragmentMismatchedItemLocationsBinding,MismatchedItemLocationsViewModel> implements ItemClickListener{

    private FragmentMismatchedItemLocationsBinding fragmentMismatchedItemLocationsBinding;
    private MismatchedItemLocationsViewModel mismatchedItemLocationsViewModel;
    ArrayList<MismatchedItemLocation> mismatchedItemLocations;

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
                mismatchedItemLocations = mismatchedItemLocations;
                MismatchedItemLocationsAdapter mismatchedItemLocationsAdapter = new MismatchedItemLocationsAdapter(getActivity(), mismatchedItemLocations, MismatchedItemLocationsFragment.this);
                fragmentMismatchedItemLocationsBinding.recyclerviewMismatcheditemlocation.setAdapter(mismatchedItemLocationsAdapter);

            }
        });
    }
    @Override
    public void onItemClick(View view, int position) {
        //do nothing

        //Toast.makeText(getContext(),"Status"+mismatchedItemLocations.get(position).getMismatchedItemLocationStatus(),Toast.LENGTH_LONG).show();
        Toast.makeText(getContext(),"Status"+mismatchedItemLocationsViewModel.mismatchedItemLocListMutableLiveData.getValue().get(position).getMismatchedItemLocationStatus(),Toast.LENGTH_LONG).show();


    }
}
