package com.follett.fsc.mobile.circdesk.feature.inventory.view;

import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.app.ItemClickListener;
import com.follett.fsc.mobile.circdesk.data.remote.repository.AppRemoteRepository;
import com.follett.fsc.mobile.circdesk.databinding.RowInventorylocationListBinding;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.Location;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.LocationList;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.SCANNING_LOCATION_ID;

public class InventoryLocationAdapter extends RecyclerView.Adapter<InventoryLocationAdapter.InventoryLocationViewHolder> {

    private Context context;
    private Location locationItemList;
    private ItemClickListener itemClickListener;
    private int lastSelectedPosition = -1;

    public InventoryLocationAdapter(Context context, Location locationItemList, ItemClickListener itemClickListener) {
        this.context = context;
        this.locationItemList = locationItemList;
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

        holder.rowInventorylocationListBinding.setLocationList(locationItemList.getLocationList().get(position));

        final List<LocationList> locationList = InventoryLocationAdapter.this.locationItemList.getLocationList();
        holder.rowInventorylocationListBinding.radioButton.setChecked(lastSelectedPosition == position);
    }

    @Override
    public int getItemCount() {
        return locationItemList != null ? locationItemList.getLocationList().size() : 0;
    }

    public class InventoryLocationViewHolder extends RecyclerView.ViewHolder {

        public RowInventorylocationListBinding rowInventorylocationListBinding;

        public InventoryLocationViewHolder(final RowInventorylocationListBinding rowInventorylocationListBinding) {
            super(rowInventorylocationListBinding.getRoot());
            this.rowInventorylocationListBinding = rowInventorylocationListBinding;

            final List<LocationList> locationList = locationItemList.getLocationList();
            for (int i = 0; i < locationList.size(); i++) {
                if (locationList.get(i).getLocationID() == AppRemoteRepository.getInstance().getInt(SCANNING_LOCATION_ID)) {
                    lastSelectedPosition = i;
                }
            }

            this.rowInventorylocationListBinding.radioButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    lastSelectedPosition = getAdapterPosition();
                    notifyDataSetChanged();
                    AppRemoteRepository.getInstance()
                            .setInt(SCANNING_LOCATION_ID, locationList
                                    .get(lastSelectedPosition)
                                    .getLocationID());
                    itemClickListener.onItemClick(v, lastSelectedPosition);
                }
            });
        }

    }

}
