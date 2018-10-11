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
import com.follett.fsc.mobile.circdesk.databinding.RowLocationListBinding;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.SubLocation;

public class SubLocationListAdapter extends RecyclerView.Adapter<SubLocationListViewHolder> {

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
    public void onBindViewHolder(@NonNull SubLocationListViewHolder holder, final int position) {
        holder.rowLocationListBinding.setSubLocationList(subLocationList.getSublocationList().get(position));

        holder.rowLocationListBinding.locationitemCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                subLocationList.getSublocationList().get(position).setSelected(isChecked);
                notifyDataSetChanged();
            }
        });
        //subLocationList.getSublocationList().get(position).setSelected(holder.rowLocationListBinding.locationitemCheckbox.isChecked());

    }

    @Override
    public int getItemCount() {
        return subLocationList != null ? subLocationList.getSublocationList().size() : 0;
    }

}
