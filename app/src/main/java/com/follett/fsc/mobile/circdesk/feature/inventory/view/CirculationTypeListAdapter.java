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
import com.follett.fsc.mobile.circdesk.databinding.RowCommonChecklistBinding;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.CirculationTypeList;

public class CirculationTypeListAdapter extends RecyclerView.Adapter<CirculationTypeListViewHolder> implements View.OnClickListener {

    private Context context;
    private CirculationTypeList circulationTypeList;
    private ItemClickListener itemClickListener;

    public CirculationTypeListAdapter(Context context, CirculationTypeList circulationTypeList, ItemClickListener itemClickListener) {
        this.context = context;
        this.circulationTypeList = circulationTypeList;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public CirculationTypeListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowCommonChecklistBinding rowCommonChecklistBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.row_common_checklist, parent, false);
        return new CirculationTypeListViewHolder(rowCommonChecklistBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CirculationTypeListViewHolder holder, int position) {

        holder.rowCommonChecklistBinding.setCircTypeList(circulationTypeList.getCircTypeList().get(position));
        holder.rowCommonChecklistBinding.itemChecklistLayout.setTag(position);
        holder.rowCommonChecklistBinding.itemCheckbox.setTag(position);
        holder.rowCommonChecklistBinding.itemChecklistName.setTag(position);
        holder.rowCommonChecklistBinding.itemChecklistLayout.setOnClickListener(this);
        holder.rowCommonChecklistBinding.itemCheckbox.setOnClickListener(this);
        holder.rowCommonChecklistBinding.itemChecklistName.setOnClickListener(this);

        circulationTypeList.getCircTypeList().get(position).setSelected(holder.rowCommonChecklistBinding.itemCheckbox.isChecked());


    }

    @Override
    public int getItemCount() {
        return circulationTypeList != null ? circulationTypeList.getCircTypeList().size() : 0;
    }

    @Override
    public void onClick(View v) {
        notifyDataSetChanged();
    }
}
