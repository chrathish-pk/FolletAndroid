package com.follett.fsc.mobile.circdesk.feature.inventory;

import android.support.v7.widget.RecyclerView;

import com.follett.fsc.mobile.circdesk.databinding.RowCommonChecklistBinding;

public class CirculationTypeListViewHolder extends RecyclerView.ViewHolder {
    public RowCommonChecklistBinding rowCommonChecklistBinding;

    public CirculationTypeListViewHolder(RowCommonChecklistBinding rowCommonChecklistBinding) {
        super(rowCommonChecklistBinding.getRoot());

        this.rowCommonChecklistBinding = rowCommonChecklistBinding;


    }
}
