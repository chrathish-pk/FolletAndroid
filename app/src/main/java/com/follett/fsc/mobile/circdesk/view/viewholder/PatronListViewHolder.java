package com.follett.fsc.mobile.circdesk.view.viewholder;

import android.support.v7.widget.RecyclerView;

import com.follett.fsc.mobile.circdesk.databinding.RowPatronListBinding;

public class PatronListViewHolder extends RecyclerView.ViewHolder {

    public RowPatronListBinding rowPatronListBinding;

    public PatronListViewHolder(RowPatronListBinding rowPatronListBinding) {
        super(rowPatronListBinding.getRoot());

        this.rowPatronListBinding = rowPatronListBinding;
    }
}
