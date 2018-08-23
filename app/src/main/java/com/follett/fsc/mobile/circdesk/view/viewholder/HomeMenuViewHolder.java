package com.follett.fsc.mobile.circdesk.view.viewholder;

import android.support.v7.widget.RecyclerView;

import com.follett.fsc.mobile.circdesk.databinding.RowHomeMenuBinding;

public class HomeMenuViewHolder extends RecyclerView.ViewHolder {

    public RowHomeMenuBinding rowHomeMenuBinding;

    public HomeMenuViewHolder(RowHomeMenuBinding rowHomeMenuBinding) {
        super(rowHomeMenuBinding.getRoot());

        this.rowHomeMenuBinding = rowHomeMenuBinding;


    }

}
