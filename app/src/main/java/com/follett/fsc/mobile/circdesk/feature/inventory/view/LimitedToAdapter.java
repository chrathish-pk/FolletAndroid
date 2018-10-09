package com.follett.fsc.mobile.circdesk.feature.inventory.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.app.ItemClickListener;
import com.follett.fsc.mobile.circdesk.databinding.RowLimitedToBinding;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.LimitedToParentData;

import java.util.ArrayList;
import java.util.List;

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
    public void onBindViewHolder(@NonNull final LimitedToViewHolder holder, int position) {
        holder.rowLimitedToBinding.setLimitedToParentData(limitedToParentDataList.get(position));

        selectedPosition = position;

        holder.rowLimitedToBinding.itemLimitedToLayout.setTag(position);

        int noOfChild = 0;

        if (position == 1)
            noOfChild = holder.rowLimitedToBinding.getLimitedToParentData().getSubLocation().getSublocationList().size();

        if (noOfChild > 0) {
            for (int indexView = 0; indexView < noOfChild; indexView++) {
                TextView textView = new TextView(context);
                textView.setId(indexView);
                textView.setPadding(0, 20, 0, 20);
                textView.setGravity(Gravity.CENTER);
                textView.setBackground(ContextCompat.getDrawable(context, R.drawable.background_sub_module_text));
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                //textView.setOnClickListener(this);

                textView.setText(limitedToParentDataList.get(position).getSubLocation().getSublocationList().get(indexView).getSublocationName());

                holder.rowLimitedToBinding.itemLimitedToChildLayout.addView(textView, layoutParams);
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

        public RowLimitedToBinding rowLimitedToBinding;

        public LimitedToViewHolder(RowLimitedToBinding rowLimitedToBinding) {
            super(rowLimitedToBinding.getRoot());
            this.rowLimitedToBinding = rowLimitedToBinding;

            int childCount = 0;
            if (selectedPosition == 1) {
                childCount = limitedToParentDataList.get(selectedPosition).getSubLocation().getSublocationList().size();
            }
            if (childCount > 0) {
                for (int indexView = 0; indexView < childCount; indexView++) {
                    TextView textView = new TextView(context);
                    textView.setId(indexView);
                    textView.setPadding(20, 20, 20, 20);
                    textView.setBackground(ContextCompat.getDrawable(context, R.drawable.background_sub_module_text));
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    //textView.setOnClickListener(this);
                    rowLimitedToBinding.itemLimitedToChildLayout.addView(textView, layoutParams);
                }
            } else
                rowLimitedToBinding.itemLimitedToChildLayout.removeAllViews();


        }

    }

}
