package com.follett.fsc.mobile.circdesk.feature.inventory.view;

import android.support.v7.widget.RecyclerView;

import com.follett.fsc.mobile.circdesk.databinding.RowNewInventoryBinding;

public class NewInventoryViewHolder extends RecyclerView.ViewHolder {

    public RowNewInventoryBinding rowNewInventoryBinding;

    public NewInventoryViewHolder(RowNewInventoryBinding rowNewInventoryBinding) {
        super(rowNewInventoryBinding.getRoot());

        this.rowNewInventoryBinding = rowNewInventoryBinding;
    }
}
