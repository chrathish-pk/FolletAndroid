/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */
package com.follett.fsc.mobile.circdesk.feature.loginsetup;

import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences;
import com.follett.fsc.mobile.circdesk.databinding.DistrictListItemBinding;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_CONTEXT_NAME;
import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_DISTRICT_NAME;

public class DistrictListAdapter extends RecyclerView.Adapter<DistrictListAdapter.DistrictListViewHolder> {
    
    private DistrictList mDistrictList;
    
    private NavigationListener mNavigationListener;
    
    private Context mContext;
    
    @NonNull
    @Override
    public DistrictListAdapter.DistrictListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        DistrictListItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.district_list_item, parent, false);
        return new DistrictListViewHolder(binding);
    }
    
    public DistrictListAdapter(Context context, DistrictList districtList) {
        mContext = context;
        this.mDistrictList = districtList;
        mNavigationListener = (NavigationListener) context;
    }
    
    @Override
    public int getItemCount() {
        if (mDistrictList != null) {
            return mDistrictList.getDistricts()
                    .size();
        }
        return 0;
    }
    
    @Override
    public void onBindViewHolder(DistrictListAdapter.DistrictListViewHolder holder, int position) {
        holder.binding.setDistrictData(mDistrictList.getDistricts()
                .get(position));
    }
    
    public class DistrictListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private DistrictListItemBinding binding;
        
        public DistrictListViewHolder(DistrictListItemBinding listItemBinding) {
            super(listItemBinding.getRoot());
            binding = listItemBinding;
            binding.districtContainer.setOnClickListener(this);
        }
        
        @Override
        public void onClick(View view) {
            AppSharedPreferences.getInstance(mContext)
                    .setString(KEY_CONTEXT_NAME, mDistrictList.getDistricts()
                            .get(getAdapterPosition())
                            .getContextName());
            AppSharedPreferences.getInstance(mContext)
                    .setString(KEY_DISTRICT_NAME, mDistrictList.getDistricts()
                            .get(getAdapterPosition())
                            .getDistrictName());
            mNavigationListener.onNavigation(null, 1);
        }
    }
}
