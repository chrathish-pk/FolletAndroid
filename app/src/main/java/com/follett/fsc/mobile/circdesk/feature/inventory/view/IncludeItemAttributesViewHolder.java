package com.follett.fsc.mobile.circdesk.feature.inventory.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.follett.fsc.mobile.circdesk.databinding.RowIncludeitemListBinding;

class IncludeItemAttributesViewHolder extends RecyclerView.ViewHolder{

    RowIncludeitemListBinding rowIncludeitemListBinding;

    public IncludeItemAttributesViewHolder(RowIncludeitemListBinding rowIncludeitemListBinding) {
        super(rowIncludeitemListBinding.getRoot());
        this.rowIncludeitemListBinding = rowIncludeitemListBinding;
    }
}
