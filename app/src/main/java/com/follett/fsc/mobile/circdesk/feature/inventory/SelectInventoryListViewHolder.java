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

public class SelectInventoryListViewHolder extends RecyclerView.ViewHolder {

    public RowInventoryBinding rowInventoryBinding;

    public SelectInventoryListViewHolder(RowInventoryBinding rowInventoryBinding) {
        super(rowInventoryBinding.getRoot());
        this.rowInventoryBinding = rowInventoryBinding;

    }
}
