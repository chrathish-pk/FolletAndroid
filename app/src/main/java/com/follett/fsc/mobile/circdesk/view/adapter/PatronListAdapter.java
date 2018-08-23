package com.follett.fsc.mobile.circdesk.view.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.databinding.RowPatronListBinding;
import com.follett.fsc.mobile.circdesk.view.viewholder.PatronListViewHolder;
import com.follett.fsc.mobile.circdesk.viewmodel.CheckoutViewModel;

public class PatronListAdapter extends RecyclerView.Adapter<PatronListViewHolder> {

    private Context context;
    private CheckoutViewModel checkoutViewModel;

    public PatronListAdapter(Context context, CheckoutViewModel checkoutViewModel) {
        this.context = context;
        this.checkoutViewModel = checkoutViewModel;
    }

    @NonNull
    @Override
    public PatronListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowPatronListBinding rowPatronListBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.row_patron_list, parent, false);
        return new PatronListViewHolder(rowPatronListBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull PatronListViewHolder holder, int position) {
        //holder.rowPatronListBinding.setPatronItem(checkoutViewModel);
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
