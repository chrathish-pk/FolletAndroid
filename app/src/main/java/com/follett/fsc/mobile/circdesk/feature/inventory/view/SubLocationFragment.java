package com.follett.fsc.mobile.circdesk.feature.inventory.view;

import android.arch.lifecycle.Observer;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.follett.fsc.mobile.circdesk.BR;
import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.app.ItemClickListener;
import com.follett.fsc.mobile.circdesk.app.base.BaseFragment;
import com.follett.fsc.mobile.circdesk.databinding.FragmentSubLocationBinding;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.SubLocation;
import com.follett.fsc.mobile.circdesk.feature.inventory.viewmodel.SubLocationViewModel;
import com.follett.fsc.mobile.circdesk.utils.FollettLog;


public class SubLocationFragment extends BaseFragment<FragmentSubLocationBinding,SubLocationViewModel> implements ItemClickListener {

    private FragmentSubLocationBinding fragmentSubLocationBinding;
    private SubLocationViewModel subLocationViewModel;

    public SubLocationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fragmentSubLocationBinding = getViewDataBinding();
        fragmentSubLocationBinding.recyclerviewSublocationlist.setLayoutManager(new LinearLayoutManager(getActivity()));
        subLocationViewModel.fetchSubLocationList();
        subLocationViewModel.subLocationListMutableLiveData.observeForever(new Observer<SubLocation>() {
            @Override
            public void onChanged(@Nullable SubLocation subLocation) {

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

        //do nothing
    }
}
