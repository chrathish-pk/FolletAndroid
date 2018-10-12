package com.follett.fsc.mobile.circdesk.feature.inventory.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.app.ItemClickListener;
import com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences;
import com.follett.fsc.mobile.circdesk.databinding.RowIncludeitemListBinding;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.IncludeItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class IncludeItemAttributesAdapter extends RecyclerView.Adapter<IncludeItemAttributesAdapter.IncludeItemAttributesViewHolder>  {

    private Context context;
    private IncludeItem includeItem;
    private ItemClickListener itemClickListener;
    private List<IncludeItem> includeItemsList;
    private int SelectedPosition;


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
    public void onBindViewHolder(@NonNull IncludeItemAttributesViewHolder holder, final int position) {
        holder.rowIncludeitemListBinding.setIncludeItemList(includeItemsList.get(position));

        holder.rowIncludeitemListBinding.includeitemCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                includeItemsList.get(position).setSelected(isChecked);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return includeItemsList != null ? includeItemsList.size() : 0;
    }

    class IncludeItemAttributesViewHolder extends RecyclerView.ViewHolder {

        RowIncludeitemListBinding rowIncludeitemListBinding;

        public IncludeItemAttributesViewHolder(RowIncludeitemListBinding rowIncludeitemListBinding) {
            super(rowIncludeitemListBinding.getRoot());
            this.rowIncludeitemListBinding = rowIncludeitemListBinding;
        }
    }
}
