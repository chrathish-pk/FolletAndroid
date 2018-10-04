package com.follett.fsc.mobile.circdesk.feature.inventory.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.follett.fsc.mobile.circdesk.databinding.RowCheckouthandlingListBinding;

public class InventoryCheckoutHandlingViewHolder extends RecyclerView.ViewHolder {

    public RowCheckouthandlingListBinding rowCheckouthandlingListBinding;
    public InventoryCheckoutHandlingViewHolder(RowCheckouthandlingListBinding rowCheckouthandlingListBinding) {
        super(rowCheckouthandlingListBinding.getRoot());
        this.rowCheckouthandlingListBinding = rowCheckouthandlingListBinding;
    }
}
