package com.follett.fsc.mobile.circdesk.feature.inventory.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.follett.fsc.mobile.circdesk.databinding.RowLocationListBinding;

import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.app.ItemClickListener;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.SubLocation;
import com.follett.fsc.mobile.circdesk.utils.FollettLog;

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
    public void onBindViewHolder(@NonNull SubLocationListViewHolder holder, int position) {
        FollettLog.i("TAG","Location Name"+subLocationList.getSubLocationList().get(position).getSublocationName());
        //holder.rowLocationListBinding.setSubLocationList(subLocationList.getSubLocationList().get(position));
        holder.rowLocationListBinding.locationitemChecklistName.setText(subLocationList.getSubLocationList().get(position).getSublocationName());

    }

    @Override
    public int getItemCount() {
        return subLocationList.getSubLocationList() != null ? subLocationList.getSubLocationList().size() : 0;
    }
}
