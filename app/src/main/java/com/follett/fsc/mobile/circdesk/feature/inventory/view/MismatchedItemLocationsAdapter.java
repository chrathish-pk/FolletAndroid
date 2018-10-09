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
import com.follett.fsc.mobile.circdesk.databinding.RowMismatcheditemlocListBinding;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.MismatchedItemLocation;

import java.util.ArrayList;

public class MismatchedItemLocationsAdapter extends RecyclerView.Adapter<MismatchedItemLocationsAdapter.MismatchedItemLocViewHolder>  {

    private Context context;
    private MismatchedItemLocation mismatchedItemLocation;
    private ItemClickListener itemClickListener;
    ArrayList<MismatchedItemLocation> mismatchedItemLocationsList;
    private int lastSelectedPosition = -1;


    public MismatchedItemLocationsAdapter(Context context, ArrayList<MismatchedItemLocation> mismatchedItemLocationsList, ItemClickListener itemClickListener) {
        this.context = context;
        this.itemClickListener = itemClickListener;
        this.mismatchedItemLocationsList = mismatchedItemLocationsList;
    }


    @NonNull
    @Override
    public MismatchedItemLocViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowMismatcheditemlocListBinding rowMismatcheditemlocListBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.row_mismatcheditemloc_list, parent, false);
        return new MismatchedItemLocationsAdapter.MismatchedItemLocViewHolder(rowMismatcheditemlocListBinding);    }

    @Override
    public void onBindViewHolder(@NonNull MismatchedItemLocationsAdapter.MismatchedItemLocViewHolder holder, int position) {
        holder.rowMismatcheditemlocListBinding.setMismatchLocationList(mismatchedItemLocationsList.get(position));

        holder.rowMismatcheditemlocListBinding.mismatchedItemLocCheckbox.setChecked(lastSelectedPosition == position);
        holder.rowMismatcheditemlocListBinding.mismatchedItemLocCheckbox.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mismatchedItemLocationsList != null ? mismatchedItemLocationsList.size() : 0 ;
    }

    public class MismatchedItemLocViewHolder extends RecyclerView.ViewHolder {

        public RowMismatcheditemlocListBinding rowMismatcheditemlocListBinding;

        public MismatchedItemLocViewHolder(RowMismatcheditemlocListBinding rowMismatcheditemlocListBinding) {
            super(rowMismatcheditemlocListBinding.getRoot());
            this.rowMismatcheditemlocListBinding = rowMismatcheditemlocListBinding;
            rowMismatcheditemlocListBinding.mismatchedItemLocCheckbox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    lastSelectedPosition = getAdapterPosition();
                    notifyDataSetChanged();
                    itemClickListener.onItemClick(v, (Integer)v.getTag());
                }
            });
        }
    }
}
