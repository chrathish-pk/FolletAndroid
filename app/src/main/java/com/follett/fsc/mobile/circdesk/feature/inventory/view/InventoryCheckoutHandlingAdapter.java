package com.follett.fsc.mobile.circdesk.feature.inventory.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.app.ItemClickListener;
import com.follett.fsc.mobile.circdesk.databinding.RowCheckouthandlingListBinding;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.CheckoutHandling;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.IncludeItem;

import java.util.ArrayList;

public class InventoryCheckoutHandlingAdapter extends RecyclerView.Adapter<InventoryCheckoutHandlingViewHolder> {

    private Context context;
    private ItemClickListener itemClickListener;
    ArrayList<CheckoutHandling> checkoutHandlings;

    public InventoryCheckoutHandlingAdapter(Context context, ArrayList<CheckoutHandling> checkoutHandlings, ItemClickListener itemClickListener) {
        this.context = context;
        this.itemClickListener = itemClickListener;
        this.checkoutHandlings = checkoutHandlings;
    }

    @NonNull
    @Override
    public InventoryCheckoutHandlingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowCheckouthandlingListBinding rowCheckouthandlingListBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.row_checkouthandling_list, parent, false);
        return new InventoryCheckoutHandlingViewHolder(rowCheckouthandlingListBinding);    }

    @Override
    public void onBindViewHolder(@NonNull InventoryCheckoutHandlingViewHolder holder, int position) {
        holder.rowCheckouthandlingListBinding.setCheckoutHandlingList(checkoutHandlings.get(position));
    }

    @Override
    public int getItemCount() {
        return checkoutHandlings != null ? checkoutHandlings.size() : 0;
    }
}
