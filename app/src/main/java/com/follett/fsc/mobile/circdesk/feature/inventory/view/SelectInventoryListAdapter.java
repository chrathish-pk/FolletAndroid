/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.inventory.view;

import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.app.ItemClickListener;
import com.follett.fsc.mobile.circdesk.databinding.RowInventoryBinding;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.Inventory;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class SelectInventoryListAdapter extends RecyclerView.Adapter<SelectInventoryListViewHolder> implements View.OnClickListener {

    private Context context;
    private List<Inventory> inventoryList = new ArrayList<>();
    private ItemClickListener itemClickListener;

    public SelectInventoryListAdapter(Context context, List<Inventory> inventoryList, ItemClickListener itemClickListener) {
        this.context = context;
        this.inventoryList = inventoryList;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public SelectInventoryListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowInventoryBinding rowInventoryBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.row_inventory, parent, false);
        return new SelectInventoryListViewHolder(rowInventoryBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectInventoryListViewHolder holder, int position) {
        holder.rowInventoryBinding.setInventory(inventoryList.get(position));

        holder.rowInventoryBinding.itemInventoryLayout.setTag(position);
        holder.rowInventoryBinding.itemInventoryLayout.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return inventoryList.size();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.itemInventoryLayout) {
            itemClickListener.onItemClick(v, (Integer) v.getTag());
        }
    }
}
