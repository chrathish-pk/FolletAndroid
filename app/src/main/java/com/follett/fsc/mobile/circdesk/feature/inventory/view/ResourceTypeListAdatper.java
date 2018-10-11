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
import android.widget.LinearLayout;

import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.app.ItemClickListener;
import com.follett.fsc.mobile.circdesk.databinding.RowResourceTypeBinding;
import com.follett.fsc.mobile.circdesk.feature.inventory.model.ResourceTypeListData;

import java.util.List;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class ResourceTypeListAdatper extends RecyclerView.Adapter<ResourceTypeListAdatper.ResourceTypeListViewHolder> {

    private Context context;
    private ItemClickListener itemClickListener;
    private List<ResourceTypeListData> resourceTypeListDataList;
    private int heightLevel;

    public ResourceTypeListAdatper(Context context, List<ResourceTypeListData> resourceTypeListDataList, ItemClickListener itemClickListener, int heightLevel) {
        this.context = context;
        this.resourceTypeListDataList = resourceTypeListDataList;
        this.itemClickListener = itemClickListener;
        this.heightLevel = heightLevel;
    }

    @NonNull
    @Override
    public ResourceTypeListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowResourceTypeBinding rowResourceTypeBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.row_resource_type, parent, false);
        return new ResourceTypeListAdatper.ResourceTypeListViewHolder(rowResourceTypeBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ResourceTypeListViewHolder holder, int position) {
        holder.rowResourceTypeBinding.setResourceType(resourceTypeListDataList.get(0).getResourceTypeListData().get(position));

        /*for (int i = 1; i < heightLevel; i++) {
            LinearLayout linearLayout = new LinearLayout(context);
            linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            linearLayout.setId(i);

            for (int j = 0; j < resourceTypeListDataList.get(i).getResourceTypeListData().size(); j++) {
                CheckBox checkBox = (CheckBox) ((LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE)).inflate(R.layout.right_checkbox, null);
                checkBox.setText(resourceTypeListDataList.get(i).getResourceTypeListData().get(j).getName());
                checkBox.setId(j);
                checkBox.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                checkBox.setBackground(ContextCompat.getDrawable(context, R.drawable.background_sub_module_text));
                linearLayout.addView(checkBox, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            }

            holder.rowResourceTypeBinding.itemResourceParentLayout.addView(linearLayout);
        }*/


        for (int j = 0; j < resourceTypeListDataList.get(1).getResourceTypeListData().size(); j++) {
            CheckBox checkBox = (CheckBox) ((LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE)).inflate(R.layout.right_checkbox, null);
            checkBox.setText(resourceTypeListDataList.get(1).getResourceTypeListData().get(j).getName());
            checkBox.setId(j);
            checkBox.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            checkBox.setBackground(ContextCompat.getDrawable(context, R.drawable.background_sub_module_text));
            holder.rowResourceTypeBinding.itemResourceChildLayout.addView(checkBox, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        }

        if (resourceTypeListDataList.get(0).getResourceTypeListData().get(position).getSelectable()) {
            holder.rowResourceTypeBinding.itemResourceChildLayout.setVisibility(View.VISIBLE);
        } else {
            holder.rowResourceTypeBinding.itemResourceChildLayout.setVisibility(View.GONE);
        }

        resourceTypeListDataList.get(0).getResourceTypeListData().get(position).setSelectable(holder.rowResourceTypeBinding.itemResourceRadioBtn.isChecked());

    }

    @Override
    public int getItemCount() {
        return resourceTypeListDataList.get(0).getResourceTypeListData().size();
    }


    class ResourceTypeListViewHolder extends RecyclerView.ViewHolder {

        RowResourceTypeBinding rowResourceTypeBinding;

        public ResourceTypeListViewHolder(RowResourceTypeBinding rowResourceTypeBinding) {
            super(rowResourceTypeBinding.getRoot());
            this.rowResourceTypeBinding = rowResourceTypeBinding;

        }

    }
}
