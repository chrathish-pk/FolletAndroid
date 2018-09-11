/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.checkoutcheckin.checkout;

import android.support.v7.widget.RecyclerView;

import com.follett.fsc.mobile.circdesk.databinding.RowPatronListBinding;

public class PatronListViewHolder extends RecyclerView.ViewHolder {

    public RowPatronListBinding rowPatronListBinding;

    public PatronListViewHolder(RowPatronListBinding rowPatronListBinding) {
        super(rowPatronListBinding.getRoot());

        this.rowPatronListBinding = rowPatronListBinding;
    }
}
