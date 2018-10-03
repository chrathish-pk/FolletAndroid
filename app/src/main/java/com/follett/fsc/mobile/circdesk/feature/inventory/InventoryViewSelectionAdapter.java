package com.follett.fsc.mobile.circdesk.feature.inventory;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.databinding.ViewSelectedInventoryItemBinding;

import java.util.List;

public class InventoryViewSelectionAdapter extends RecyclerView.Adapter<InventoryViewSelectionAdapter.InventoryViewSelectionViewHolder> {

    private List<SelectionCriteriaItemList> selectionCriteriaItemLists;
    private Context mContext;

    @NonNull
    @Override
    public InventoryViewSelectionAdapter.InventoryViewSelectionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewSelectedInventoryItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.view_selected_inventory_item, parent, false);
        return new InventoryViewSelectionViewHolder(binding);
    }

    public InventoryViewSelectionAdapter(Context context, List<SelectionCriteriaItemList> selectionCriteriaItemLists) {
        mContext = context;
        this.selectionCriteriaItemLists = selectionCriteriaItemLists;
    }

    @Override
    public int getItemCount() {
        if (selectionCriteriaItemLists != null) {
            return selectionCriteriaItemLists.size();
        }
        return 0;
    }

    @Override
    public void onBindViewHolder(InventoryViewSelectionAdapter.InventoryViewSelectionViewHolder holder, int position) {
        SelectionCriteriaItemList selectionCriteriaItemList = selectionCriteriaItemLists.get(position);
        holder.binding.setSelectedInventorieslData(selectionCriteriaItemList);
    }

    public class InventoryViewSelectionViewHolder extends RecyclerView.ViewHolder {
        private ViewSelectedInventoryItemBinding binding;

        public InventoryViewSelectionViewHolder(ViewSelectedInventoryItemBinding listItemBinding) {
            super(listItemBinding.getRoot());
            binding = listItemBinding;
        }
    }
}
