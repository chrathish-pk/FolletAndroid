/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.homescreen;

import android.support.v7.widget.RecyclerView;

import com.follett.fsc.mobile.circdesk.databinding.RowHomeMenuBinding;

public class HomeMenuViewHolder extends RecyclerView.ViewHolder {

    public RowHomeMenuBinding rowHomeMenuBinding;

    public HomeMenuViewHolder(RowHomeMenuBinding rowHomeMenuBinding) {
        super(rowHomeMenuBinding.getRoot());
        this.rowHomeMenuBinding = rowHomeMenuBinding;
    }
}
