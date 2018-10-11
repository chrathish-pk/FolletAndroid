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
import com.follett.fsc.mobile.circdesk.databinding.FragmentResourceTypeBinding;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.ResourceType;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.ResourceTypeList;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.ResourceTypeListData;
import com.follett.fsc.mobile.circdesk.feature.inventory.viewmodel.ResourceTypeViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ResourceTypeFragment extends BaseFragment<FragmentResourceTypeBinding, ResourceTypeViewModel> implements ItemClickListener {

    private ResourceTypeViewModel resourceTypeViewModel;
    private FragmentResourceTypeBinding fragmentResourceTypeBinding;
    private ResourceTypeListAdatper resourceTypeListAdatper;
    private int heighestLevel;
    private List<ResourceTypeListData> resourceTypeListDataList = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.fragment_resource_type;
    }

    @Override
    public ResourceTypeViewModel getViewModel() {
        resourceTypeViewModel = new ResourceTypeViewModel(getBaseActivity().getApplication());
        return resourceTypeViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.resourceTypeViewModel;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fragmentResourceTypeBinding = getViewDataBinding();

        resourceTypeViewModel.fetchResourceTypeList();

        fragmentResourceTypeBinding.resourceTypeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        resourceTypeViewModel.resourceTypeMutableLiveData.observe(this, new Observer<ResourceType>() {
            @Override
            public void onChanged(@Nullable ResourceType resourceType) {
                if (resourceType != null) {
                    ResourceTypeList resourceTypeList = Collections.max(resourceType.getResourceTypeList(), new ResourceCompare());
                    heighestLevel = resourceTypeList.getLevel();


                    for (int levelIndex = 1; levelIndex <= heighestLevel; levelIndex++) {
                        resourceTypeListDataList.add(new ResourceTypeListData(null, levelIndex));
                    }

                    for (int i = 0; i < resourceTypeListDataList.size(); i++) {
                        List<ResourceTypeList> resourceTypeLists = new ArrayList<>();
                        for (int j = 0; j < resourceType.getResourceTypeList().size(); j++) {
                            if (resourceTypeListDataList.get(i).getLevel() == resourceType.getResourceTypeList().get(j).getLevel()) {
                                resourceTypeLists.add(resourceType.getResourceTypeList().get(j));
                            }
                        }
                        resourceTypeListDataList.get(i).setResourceTypeListData(resourceTypeLists);

                    }

                    resourceTypeListAdatper = new ResourceTypeListAdatper(getActivity(), resourceTypeListDataList, ResourceTypeFragment.this, heighestLevel);
                    fragmentResourceTypeBinding.resourceTypeRecyclerView.setAdapter(resourceTypeListAdatper);
                }
            }
        });
    }

    @Override
    public void onItemClick(View view, int position) {
        if (view.getId() == R.id.itemLimitedToRadioBtn) {
            for (int i = 0; i < resourceTypeListDataList.get(0).getResourceTypeListData().size(); i++) {
                if (i == position) {
                    resourceTypeListDataList.get(0).getResourceTypeListData().get(i).setSelectable(true);
                } else
                    resourceTypeListDataList.get(0).getResourceTypeListData().get(i).setSelectable(false);
            }

            resourceTypeListAdatper.notifyDataSetChanged();
        }
    }

    class ResourceCompare implements Comparator<ResourceTypeList> {

        @Override
        public int compare(ResourceTypeList e1, ResourceTypeList e2) {
            return e1.getLevel().compareTo(e2.getLevel());
        }
    }
}
