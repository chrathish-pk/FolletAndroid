package com.follett.fsc.mobile.circdesk.feature.inventory.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.app.ItemClickListener;
import com.follett.fsc.mobile.circdesk.databinding.RowLocationListBinding;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.SubLocation;

public class SubLocationListAdapter extends RecyclerView.Adapter<SubLocationListViewHolder> implements View.OnClickListener {

    private Context context;
    private SubLocation subLocationList;
    private ItemClickListener itemClickListener;

    public SubLocationListAdapter(Context context, SubLocation subLocationList, ItemClickListener itemClickListener) {
        this.context = context;
        this.subLocationList = subLocationList;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public SubLocationListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowLocationListBinding rowLocationListBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.row_location_list, parent, false);
        return new SubLocationListViewHolder(rowLocationListBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull SubLocationListViewHolder holder, int position) {
        holder.rowLocationListBinding.setSubLocationList(subLocationList.getSublocationList().get(position));

        holder.rowLocationListBinding.locationitemCheckbox.setTag(position);
        holder.rowLocationListBinding.locationItemChecklistLayout.setTag(position);
        holder.rowLocationListBinding.locationitemChecklistName.setTag(position);
        holder.rowLocationListBinding.locationitemCheckbox.setOnClickListener(this);
        holder.rowLocationListBinding.locationItemChecklistLayout.setOnClickListener(this);
        holder.rowLocationListBinding.locationitemChecklistName.setOnClickListener(this);

    }

    @Override
    public int getItemCount() {
        return subLocationList != null ? subLocationList.getSublocationList().size() : 0;
    }

    @Override
    public void onClick(View v) {
        subLocationList.getSublocationList().get((Integer) v.getTag()).setSelected(((CheckBox)v).isChecked());
        notifyDataSetChanged();
    }
}
