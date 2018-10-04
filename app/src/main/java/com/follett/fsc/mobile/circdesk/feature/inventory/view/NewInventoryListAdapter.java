package com.follett.fsc.mobile.circdesk.feature.inventory.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.app.ItemClickListener;
import com.follett.fsc.mobile.circdesk.databinding.RowNewInventoryBinding;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.NewInventoryData;

import java.util.ArrayList;
import java.util.List;

public class NewInventoryListAdapter extends RecyclerView.Adapter<NewInventoryViewHolder> implements View.OnClickListener {

    private Context context;
    private List<NewInventoryData> newInventoryDataList = new ArrayList<>();
    private ItemClickListener itemClickListener;

    public NewInventoryListAdapter(Context context, List<NewInventoryData> newInventoryDataList, ItemClickListener itemClickListener) {
        this.context = context;
        this.newInventoryDataList = newInventoryDataList;
        this.itemClickListener = itemClickListener;
    }


    @NonNull
    @Override
    public NewInventoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowNewInventoryBinding rowNewInventoryBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.row_new_inventory, parent, false);
        return new NewInventoryViewHolder(rowNewInventoryBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull NewInventoryViewHolder holder, int position) {

        holder.rowNewInventoryBinding.setNewInventoryData(newInventoryDataList.get(position));
        holder.rowNewInventoryBinding.itemNewInventoryLayout.setTag(position);
        holder.rowNewInventoryBinding.itemNewInventoryLayout.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return newInventoryDataList.size();
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onItemClick(v, (Integer) v.getTag());
    }
}
