/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */
package com.follett.fsc.mobile.circdesk.feature.loginsetup;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences;
import com.follett.fsc.mobile.circdesk.databinding.SchoolListItemBinding;

import java.util.List;

import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_SITE_ID;
import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_SITE_NAME;
import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.KEY_SITE_SHORT_NAME;

public class SchoolListAdapter extends RecyclerView.Adapter<SchoolListAdapter.SchoolListViewHolder> {
    
    private List<SiteRecord> mSchoolList;
    
    private NavigationListener mNavigationListener;
    
    private Context mContext;
    
    @NonNull
    @Override
    public SchoolListAdapter.SchoolListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SchoolListItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.school_list_item, parent, false);
        return new SchoolListViewHolder(binding);
    }
    
    public SchoolListAdapter(Context context, List<SiteRecord> schoolList) {
        mContext = context;
        this.mSchoolList = schoolList;
        mNavigationListener = (NavigationListener) context;
    }
    
    @Override
    public int getItemCount() {
        if (mSchoolList != null) {
            return mSchoolList.size();
        }
        return 0;
    }
    
    @Override
    public void onBindViewHolder(SchoolListAdapter.SchoolListViewHolder holder, int position) {
        SiteRecord siteRecord = mSchoolList.get(position);
        holder.binding.setSchoolData(siteRecord);
    }
    
    public class SchoolListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private SchoolListItemBinding binding;
        
        public SchoolListViewHolder(SchoolListItemBinding listItemBinding) {
            super(listItemBinding.getRoot());
            binding = listItemBinding;
            binding.schoolname.setOnClickListener(this);
        }
        
        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            AppSharedPreferences.getInstance()
                    .setString(KEY_SITE_SHORT_NAME, mSchoolList.get(position)
                            .getSiteShortName());
            AppSharedPreferences.getInstance()
                    .setString(KEY_SITE_ID, mSchoolList.get(position)
                            .getSiteID());
            AppSharedPreferences.getInstance()
                    .setString(KEY_SITE_NAME, mSchoolList.get(position)
                            .getSiteName());
            mNavigationListener.onNavigation(null, 2);
        }
    }
}
