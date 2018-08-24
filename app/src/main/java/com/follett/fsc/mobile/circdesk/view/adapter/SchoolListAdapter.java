/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */
package com.follett.fsc.mobile.circdesk.view.adapter;

import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.data.model.SiteRecord;
import com.follett.fsc.mobile.circdesk.interfaces.ItemClickListener;
import com.follett.fsc.mobile.circdesk.interfaces.NavigationListener;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class SchoolListAdapter extends RecyclerView.Adapter<SchoolListAdapter.SchoolListViewHolder> {

    Context context;
    List<SiteRecord> schoolList;
    private NavigationListener navigationListener;
    @NonNull
    @Override
    public SchoolListAdapter.SchoolListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.school_list_item,parent,false);
        return new SchoolListViewHolder(view);
    }

    public SchoolListAdapter(Context context, List<SiteRecord> schoolList) {
        this.context = context;
        this.schoolList = schoolList;
        navigationListener = (NavigationListener) context;
    }

    @Override
    public int getItemCount() {
        if(schoolList != null){
            return schoolList.size();
        }
        return 0;
    }

    @Override
    public void onBindViewHolder(SchoolListAdapter.SchoolListViewHolder holder, int position) {

        holder.tvSchoolName.setText(schoolList.get(position).getSiteName());
    }

    public class SchoolListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvSchoolName;
        public SchoolListViewHolder(View itemView) {
            super(itemView);
            tvSchoolName = (TextView)itemView.findViewById(R.id.schoolname);
            tvSchoolName.setOnClickListener(this);
        }
    
        @Override
        public void onClick(View view) {
            navigationListener.onNavigation(1);
        }
    }
}
