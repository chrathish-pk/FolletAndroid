package com.follett.fsc.mobile.circdesk.feature.inventory.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.follett.fsc.mobile.circdesk.databinding.RowInventorylocationListBinding;

class InventoryLocationViewHolder extends RecyclerView.ViewHolder {

    public RowInventorylocationListBinding rowInventorylocationListBinding;

    public InventoryLocationViewHolder(RowInventorylocationListBinding rowInventorylocationListBinding) {
        super(rowInventorylocationListBinding.getRoot());
        this.rowInventorylocationListBinding = rowInventorylocationListBinding;
    }
}
