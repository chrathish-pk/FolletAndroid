/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.homescreen;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.app.ItemClickListener;
import com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences;
import com.follett.fsc.mobile.circdesk.databinding.RowHomeMenuBinding;
import com.follett.fsc.mobile.circdesk.feature.loginsetup.Permissions;
import com.google.gson.Gson;

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
    public void onBindViewHolder(@NonNull HomeMenuViewHolder holder, final int position) {

        String permissionValue = AppSharedPreferences.getInstance(context).getString(AppSharedPreferences.KEY_PERMISSIONS);
        Permissions permissions = new Gson().fromJson(permissionValue, Permissions.class);

        boolean canCheckoutShow = Boolean.parseBoolean(permissions.getCanCheckoutAsset()) || Boolean.parseBoolean(permissions.getCanCheckoutLibrary()) || Boolean.parseBoolean(permissions.getCanCheckoutTextbook());
        boolean canCheckinShow = Boolean.parseBoolean(permissions.getCanCheckinAsset()) || Boolean.parseBoolean(permissions.getCanCheckinLibrary()) || Boolean.parseBoolean(permissions.getCanCheckinTextbook());
        boolean canShowPatronStatus = Boolean.parseBoolean(permissions.getCanViewPatronStatus());
        boolean canShowItemStatus = Boolean.parseBoolean(permissions.getCanViewItemsOutAsset()) || Boolean.parseBoolean(permissions.getCanViewItemsOutLibrary()) || Boolean.parseBoolean(permissions.getCanViewItemsOutTextbook());

        if ((position == 0 && canCheckoutShow && canCheckinShow) || (position == 1 && canShowPatronStatus) || (position == 2 && canShowItemStatus) || position == 3) {
            holder.rowHomeMenuBinding.setMenuItem(homeViewModel.homeMenuItems.get(position));
            holder.rowHomeMenuBinding.itemMenuImg.setImageResource(homeViewModel.homeMenuItems.get(position).getMenuImg());
        }

        ItemClickListener itemClickListener = new ItemClickListener() {
            @Override
            public void onItemClicked() {
                homeViewModel.setOpenTaskEvent(homeViewModel.homeMenuItems.get(position).getMenuName());
            }

            @Override
            public void onItemClick(View view, int position) {
                //onItemClicked
            }
        };
        holder.rowHomeMenuBinding.setListener(itemClickListener);

    }

    @Override
    public int getItemCount() {
        return homeViewModel.homeMenuItems.size();
    }

}
