package com.follett.fsc.mobile.circdesk.feature.inventory.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.app.ItemClickListener;
import com.follett.fsc.mobile.circdesk.databinding.RowInventorylocationListBinding;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.Location;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.SubLocation;

public class InventoryLocationAdapter extends RecyclerView.Adapter<InventoryLocationViewHolder> {

    private Context context;
    private Location locationList;
    private ItemClickListener itemClickListener;

    public InventoryLocationAdapter(Context context, Location locationList, ItemClickListener itemClickListener) {
        this.context = context;
        this.locationList = locationList;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public InventoryLocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        RowInventorylocationListBinding rowInventorylocationListBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.row_inventorylocation_list, parent, false);
        return new InventoryLocationViewHolder(rowInventorylocationListBinding);

    }

    @Override
    public void onBindViewHolder(@NonNull InventoryLocationViewHolder holder, int position) {

        holder.rowInventorylocationListBinding.setLocationList(locationList.getLocationList().get(position));
    }

    @Override
    public int getItemCount() {
        return locationList !=null ? locationList.getLocationList().size() : 0;
    }
}
