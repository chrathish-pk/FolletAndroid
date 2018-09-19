/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.checkoutcheckin.checkout;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.app.GlideApp;
import com.follett.fsc.mobile.circdesk.app.ItemClickListener;
import com.follett.fsc.mobile.circdesk.data.remote.repository.AppRemoteRepository;
import com.follett.fsc.mobile.circdesk.databinding.RowPatronListBinding;
import com.follett.fsc.mobile.circdesk.utils.FollettLog;

import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.SERVER_URI_VALUE;

public class PatronListAdapter extends RecyclerView.Adapter<PatronListViewHolder> implements View.OnClickListener {
    private Context context;
    private ScanPatron scanPatron;
    private ItemClickListener itemClickListener;

    public PatronListAdapter(Context context, ScanPatron scanPatron, ItemClickListener itemClickListener) {
        this.context = context;
        this.scanPatron = scanPatron;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public PatronListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowPatronListBinding rowPatronListBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.row_patron_list, parent, false);
        return new PatronListViewHolder(rowPatronListBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull PatronListViewHolder holder, int position) {
        Patron patron = scanPatron.getPatronList().get(position);
        holder.rowPatronListBinding.patronName.setText(patron.getLastFirstMiddleName());
        holder.rowPatronListBinding.patronBarcode.setText(patron.getBarcode());
        holder.rowPatronListBinding.patronLayout.setTag(position);

        holder.rowPatronListBinding.patronLayout.setOnClickListener(this);

        FollettLog.d("patronImg", AppRemoteRepository.getInstance().getString(SERVER_URI_VALUE) + patron.getPatronPictureFileName());

        RequestOptions requestOptions = new RequestOptions()
                .fitCenter()
                .placeholder(R.drawable.avatar)
                .transforms(new CenterCrop(), new RoundedCorners(500));

        GlideApp.with(context)
                .load(AppRemoteRepository.getInstance().getString(SERVER_URI_VALUE) + patron.getPatronPictureFileName() + "?contextName=dvpdt_devprodtest")
                .apply(requestOptions)
                .into(holder.rowPatronListBinding.patronImg);
    }

    @Override
    public int getItemCount() {
        return scanPatron != null ? scanPatron.getPatronList().size() : 0;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.patronLayout) {
            itemClickListener.onItemClick(v, (Integer) v.getTag());
        }
    }
}
