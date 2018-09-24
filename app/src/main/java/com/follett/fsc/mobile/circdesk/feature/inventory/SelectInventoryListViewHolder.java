/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.inventory;

import com.follett.fsc.mobile.circdesk.databinding.RowInventoryBinding;

import android.support.v7.widget.RecyclerView;

public class SelectInventoryListViewHolder extends RecyclerView.ViewHolder {

    public RowInventoryBinding rowInventoryBinding;

    public SelectInventoryListViewHolder(RowInventoryBinding rowInventoryBinding) {
        super(rowInventoryBinding.getRoot());
        this.rowInventoryBinding = rowInventoryBinding;

    }
}
