/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */
package com.follett.fsc.mobile.circdesk.feature.patronstatus;

import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.databinding.PatronListItemBinding;
import com.follett.fsc.mobile.circdesk.databinding.SchoolListItemBinding;
import com.follett.fsc.mobile.circdesk.feature.loginsetup.NavigationListener;
import com.follett.fsc.mobile.circdesk.feature.patronstatus.model.PatronList;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class PatronListAdapter extends RecyclerView.Adapter<PatronListAdapter.PatronListViewHolder> {
    
    private List<PatronList> mPatronList;
    
    private NavigationListener mNavigationListener;
    
    private Context mContext;
    
    @NonNull
    @Override
    public PatronListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PatronListItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.patron_list_item, parent, false);
        return new PatronListViewHolder(binding);
    }
    
    public PatronListAdapter(Context context, List<PatronList> patronLists) {
        mContext = context;
        this.mPatronList = patronLists;
        mNavigationListener = (NavigationListener) context;
    }
    
    @Override
    public int getItemCount() {
        if (mPatronList != null) {
            return mPatronList.size();
        }
        return 0;
    }
    
    @Override
    public void onBindViewHolder(PatronListViewHolder holder, int position) {
        PatronList patronItem = mPatronList.get(position);
        holder.binding.setPatronItem(patronItem);
    }
    
    public class PatronListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private PatronListItemBinding binding;
        
        public PatronListViewHolder(PatronListItemBinding listItemBinding) {
            super(listItemBinding.getRoot());
            binding = listItemBinding;
            binding.patronLayout.setOnClickListener(this);
        }
        
        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
//            AppSharedPreferences.getInstance(mContext)
//                    .setString(KEY_SITE_SHORT_NAME, mPatronList.get(position)
//                            .getSiteShortName());
//            AppSharedPreferences.getInstance(mContext)
//                    .setString(KEY_SITE_ID, mPatronList.get(position)
//                            .getSiteID());
//            AppSharedPreferences.getInstance(mContext)
//                    .setString(KEY_SITE_NAME, mPatronList.get(position)
//                            .getSiteName());
            mNavigationListener.onNavigation(mPatronList.get(position), 1);
        }
    }
}