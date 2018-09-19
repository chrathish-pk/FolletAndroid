/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.inventory;

import android.support.v7.widget.RecyclerView;

import com.follett.fsc.mobile.circdesk.databinding.RowInventoryBinding;

/**
 * Created by muthulakshmi on 18/09/18.
 */

public class InventoryListViewHolder extends RecyclerView.ViewHolder {

    public RowInventoryBinding rowInventoryBinding;

    public InventoryListViewHolder(RowInventoryBinding rowInventoryBinding) {
        super(rowInventoryBinding.getRoot());
        this.rowInventoryBinding = rowInventoryBinding;

    }
}
