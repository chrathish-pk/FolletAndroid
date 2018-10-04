package com.follett.fsc.mobile.circdesk.feature.inventory.view;

import android.support.v7.widget.RecyclerView;

import com.follett.fsc.mobile.circdesk.databinding.RowLocationListBinding;
import android.view.View;

class SubLocationListViewHolder extends RecyclerView.ViewHolder {

    public RowLocationListBinding rowLocationListBinding;

    public SubLocationListViewHolder(RowLocationListBinding rowLocationListBinding) {
        super(rowLocationListBinding.getRoot());
        this.rowLocationListBinding = rowLocationListBinding;
    }
}
