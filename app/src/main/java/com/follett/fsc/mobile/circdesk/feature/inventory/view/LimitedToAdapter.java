package com.follett.fsc.mobile.circdesk.feature.inventory.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.app.ItemClickListener;
import com.follett.fsc.mobile.circdesk.databinding.RowLimitedToBinding;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.LimitedToParentData;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class LimitedToAdapter extends RecyclerView.Adapter<LimitedToAdapter.LimitedToViewHolder> implements View.OnClickListener {

    private Context context;
    private List<LimitedToParentData> limitedToParentDataList = new ArrayList<>();
    private ItemClickListener itemClickListener;
    private int selectedPosition;

    public LimitedToAdapter(Context context, List<LimitedToParentData> limitedToParentDataList, ItemClickListener itemClickListener) {
        this.context = context;
        this.limitedToParentDataList = limitedToParentDataList;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public LimitedToViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowLimitedToBinding rowLimitedToBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.row_limited_to, parent, false);
        return new LimitedToViewHolder(rowLimitedToBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull final LimitedToViewHolder holder, final int position) {
        holder.rowLimitedToBinding.setLimitedToParentData(limitedToParentDataList.get(position));

        holder.rowLimitedToBinding.itemLimitedToRadioBtn.setChecked(limitedToParentDataList.get(position).isSelected());
        selectedPosition = position;

        holder.rowLimitedToBinding.itemLimitedToLayout.setTag(position);

        int noOfChild = 0;

        if (position == 1)
            noOfChild = holder.rowLimitedToBinding.getLimitedToParentData().getSubLocation().getSublocationList().size();

        if (noOfChild > 0) {
            holder.rowLimitedToBinding.itemLimitedToChildLayout.removeAllViews();
            for (int indexView = 0; indexView < noOfChild; indexView++) {

                CheckBox checkBox = (CheckBox) ((LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE)).inflate(R.layout.right_checkbox, null);
                checkBox.setText(limitedToParentDataList.get(position).getSubLocation().getSublocationList().get(indexView).getSublocationName());
                checkBox.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

                final int finalIndexView = indexView;
                checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        limitedToParentDataList.get(position).getSubLocation().getSublocationList().get(finalIndexView).setSelected(isChecked);
                    }
                });

                checkBox.setBackground(ContextCompat.getDrawable(context, R.drawable.background_sub_module_text));
                holder.rowLimitedToBinding.itemLimitedToChildLayout.addView(checkBox, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            }
        } else
            holder.rowLimitedToBinding.itemLimitedToChildLayout.removeAllViews();

        holder.rowLimitedToBinding.itemLimitedToRadioBtn.setTag(position);
        holder.rowLimitedToBinding.itemLimitedToRadioBtn.setOnClickListener(this);


        if (limitedToParentDataList.get(position).isSelected()) {
            holder.rowLimitedToBinding.itemLimitedToChildLayout.setVisibility(View.VISIBLE);
        } else {
            holder.rowLimitedToBinding.itemLimitedToChildLayout.setVisibility(View.GONE);
        }


        limitedToParentDataList.get(selectedPosition).setSelected(holder.rowLimitedToBinding.itemLimitedToRadioBtn.isChecked());

    }

    @Override
    public int getItemCount() {
        return limitedToParentDataList.size();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.itemLimitedToRadioBtn) {
            itemClickListener.onItemClick(v, (Integer) v.getTag());
        }
    }


    class LimitedToViewHolder extends RecyclerView.ViewHolder {

        RowLimitedToBinding rowLimitedToBinding;

        public LimitedToViewHolder(RowLimitedToBinding rowLimitedToBinding) {
            super(rowLimitedToBinding.getRoot());
            this.rowLimitedToBinding = rowLimitedToBinding;

        }

    }

}
