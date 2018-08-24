package com.follett.fsc.mobile.circdesk.view.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.databinding.RowHomeMenuBinding;
import com.follett.fsc.mobile.circdesk.interfaces.ItemClickListener;
import com.follett.fsc.mobile.circdesk.view.viewholder.HomeMenuViewHolder;
import com.follett.fsc.mobile.circdesk.viewmodel.HomeViewModel;

public class HomeMenuAdapter extends RecyclerView.Adapter<HomeMenuViewHolder> {


    private Context context;
    private HomeViewModel homeViewModel;

    public HomeMenuAdapter(Context context, HomeViewModel homeViewModel) {
        this.context = context;
        this.homeViewModel = homeViewModel;
    }

    @NonNull
    @Override
    public HomeMenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowHomeMenuBinding rowHomeMenuBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.row_home_menu, parent, false);
        return new HomeMenuViewHolder(rowHomeMenuBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeMenuViewHolder holder, int position) {
        holder.rowHomeMenuBinding.setMenuItem(homeViewModel.homeMenuItems.get(position));
        holder.rowHomeMenuBinding.itemMenuImg.setImageResource(homeViewModel.homeMenuItems.get(position).getMenuImg());

        ItemClickListener itemClickListener = new ItemClickListener() {
            @Override
            public void OnItemClicked() {
                homeViewModel.getOpenTaskEvent().call();
            }

            @Override
            public void OnItemClick(View view, int position) {

            }
        };

        holder.rowHomeMenuBinding.setListener(itemClickListener);

    }

    @Override
    public int getItemCount() {
        return homeViewModel.homeMenuItems.size();
    }

}
