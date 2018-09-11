/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */
package com.follett.fsc.mobile.circdesk.feature.patronstatus;

import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.databinding.PatronCheckoutItemBinding;
import com.follett.fsc.mobile.circdesk.feature.loginsetup.NavigationListener;
import com.follett.fsc.mobile.circdesk.feature.patronstatus.model.CustomCheckoutItem;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class PatronItemCheckoutAdapter extends RecyclerView.Adapter<PatronItemCheckoutAdapter.PatronItemCheckoutViewHolder> {
    
    private List<CustomCheckoutItem> mCheckoutItemList;
    
    private NavigationListener mNavigationListener;
    
    @NonNull
    @Override
    public PatronItemCheckoutViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PatronCheckoutItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.patron_checkout_item, parent, false);
        return new PatronItemCheckoutViewHolder(binding);
    }
    
    public PatronItemCheckoutAdapter(Context context, List<CustomCheckoutItem> checkoutItemList) {
        this.mCheckoutItemList = checkoutItemList;
        mNavigationListener = (NavigationListener) context;
    }
    
    @Override
    public int getItemCount() {
        if (mCheckoutItemList != null) {
            return mCheckoutItemList.size();
        }
        return 0;
    }
    
    @Override
    public void onBindViewHolder(PatronItemCheckoutViewHolder holder, int position) {
        CustomCheckoutItem checkoutItem = mCheckoutItemList.get(position);
        holder.binding.setCheckoutItem(checkoutItem);
    }
    
    public class PatronItemCheckoutViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private PatronCheckoutItemBinding binding;
        
        public PatronItemCheckoutViewHolder(PatronCheckoutItemBinding listItemBinding) {
            super(listItemBinding.getRoot());
            binding = listItemBinding;
            binding.itemCheckoutContainer.setOnClickListener(this);
        }
    
        @Override
        public void onClick(View view) {
            CustomCheckoutItem item = mCheckoutItemList.get(getAdapterPosition());
            if (item.isArrow()) {
                mNavigationListener.onNavigation(item, 5);
            }
        }
    }
}
