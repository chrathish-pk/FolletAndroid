/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */
package com.follett.fsc.mobile.circdesk.feature.patronstatus;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.databinding.PatronFineItemBinding;
import com.follett.fsc.mobile.circdesk.feature.loginsetup.NavigationListener;
import com.follett.fsc.mobile.circdesk.feature.patronstatus.model.Fine;

import java.util.List;

public class PatronFineListAdapter extends RecyclerView.Adapter<PatronFineListAdapter.PatronFineListViewHolder> {
    
    private List<Fine> mFineList;
    
    private NavigationListener mNavigationListener;

    @NonNull
    @Override
    public PatronFineListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PatronFineItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.patron_fine_item, parent, false);
        return new PatronFineListViewHolder(binding);
    }
    
    public PatronFineListAdapter(Context context, List<Fine> fines, NavigationListener navigationListener) {
        this.mFineList = fines;
        mNavigationListener = navigationListener;
    }
    
    @Override
    public int getItemCount() {
        if (mFineList != null) {
            return mFineList.size();
        }
        return 0;
    }
    
    @Override
    public void onBindViewHolder(PatronFineListViewHolder holder, int position) {
        Fine fine = mFineList.get(position);
        holder.binding.setFineItem(fine);
    }
    
    public class PatronFineListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private PatronFineItemBinding binding;
        
        public PatronFineListViewHolder(PatronFineItemBinding listItemBinding) {
            super(listItemBinding.getRoot());
            binding = listItemBinding;
        }
        
        @Override
        public void onClick(View view) {
            /*int position = getAdapterPosition();
            mNavigationListener.onNavigation(mFineList.get(position), 1);*/
        }
    }
}
