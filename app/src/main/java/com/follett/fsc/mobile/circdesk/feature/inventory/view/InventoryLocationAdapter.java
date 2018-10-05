package com.follett.fsc.mobile.circdesk.feature.inventory.view;

import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.app.ItemClickListener;
import com.follett.fsc.mobile.circdesk.data.remote.repository.AppRemoteRepository;
import com.follett.fsc.mobile.circdesk.databinding.RowInventorylocationListBinding;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.Location;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.SCANNING_LOCATION_ID;

public class InventoryLocationAdapter extends RecyclerView.Adapter<InventoryLocationAdapter.InventoryLocationViewHolder> {

    private Context context;
    private Location locationList;
    private ItemClickListener itemClickListener;
    private int lastSelectedPosition = -1;

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
        holder.rowInventorylocationListBinding.radioButton.setChecked(lastSelectedPosition == position);
    }

    @Override
    public int getItemCount() {
        return locationList !=null ? locationList.getLocationList().size() : 0;
    }
    
    public class InventoryLocationViewHolder extends RecyclerView.ViewHolder {
        
        public RowInventorylocationListBinding rowInventorylocationListBinding;
        
        public InventoryLocationViewHolder(RowInventorylocationListBinding rowInventorylocationListBinding) {
            super(rowInventorylocationListBinding.getRoot());
            this.rowInventorylocationListBinding = rowInventorylocationListBinding;
            
            this.rowInventorylocationListBinding.radioButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    lastSelectedPosition = getAdapterPosition();
                    notifyDataSetChanged();
                    AppRemoteRepository.getInstance()
                            .setInt(SCANNING_LOCATION_ID, locationList.getLocationList()
                                    .get(lastSelectedPosition)
                                    .getLocationID());
                }
            });
        }
    }
}
