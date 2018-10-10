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
import com.follett.fsc.mobile.circdesk.databinding.RowIncludeitemListBinding;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.IncludeItem;

import java.util.List;

public class IncludeItemAttributesAdapter extends RecyclerView.Adapter<IncludeItemAttributesViewHolder> implements View.OnClickListener {

    private Context context;
    private IncludeItem includeItem;
    private ItemClickListener itemClickListener;
    private List<IncludeItem> includeItemsList;

    public IncludeItemAttributesAdapter(Context context, List<IncludeItem> includeItemsList, ItemClickListener itemClickListener) {
        this.context = context;
        this.includeItemsList = includeItemsList;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public IncludeItemAttributesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowIncludeitemListBinding rowIncludeitemListBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.row_includeitem_list, parent, false);
        return new IncludeItemAttributesViewHolder(rowIncludeitemListBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull IncludeItemAttributesViewHolder holder, int position) {
        holder.rowIncludeitemListBinding.setIncludeItemList(includeItemsList.get(position));

        holder.rowIncludeitemListBinding.locationItemChecklistLayout.setTag(position);
        holder.rowIncludeitemListBinding.includeitemCheckbox.setTag(position);
        holder.rowIncludeitemListBinding.includeitemName.setTag(position);
        holder.rowIncludeitemListBinding.locationItemChecklistLayout.setOnClickListener(this);
        holder.rowIncludeitemListBinding.includeitemCheckbox.setOnClickListener(this);
        holder.rowIncludeitemListBinding.includeitemName.setOnClickListener(this);
        includeItemsList.get(position).setSelected(holder.rowIncludeitemListBinding.includeitemCheckbox.isChecked());

    }

    @Override
    public int getItemCount() {
        return includeItemsList != null ? includeItemsList.size() : 0;
    }

    @Override
    public void onClick(View v) {
        notifyDataSetChanged();
    }
}
