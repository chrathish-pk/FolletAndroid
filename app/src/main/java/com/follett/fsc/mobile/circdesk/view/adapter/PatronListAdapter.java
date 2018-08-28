package com.follett.fsc.mobile.circdesk.view.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.data.model.Patron;
import com.follett.fsc.mobile.circdesk.data.model.ScanPatron;
import com.follett.fsc.mobile.circdesk.data.remote.repository.AppRemoteRepository;
import com.follett.fsc.mobile.circdesk.databinding.RowPatronListBinding;
import com.follett.fsc.mobile.circdesk.interfaces.ItemClickListener;
import com.follett.fsc.mobile.circdesk.utils.FollettLog;
import com.follett.fsc.mobile.circdesk.view.viewholder.PatronListViewHolder;

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

        FollettLog.d("patronImg", AppRemoteRepository.BASE_URL + patron.getPatronPictureFileName());

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.inventory);

        Glide.with(context)
                .load(AppRemoteRepository.BASE_URL + "/" + patron.getPatronPictureFileName())
                .apply(options)
                .into(holder.rowPatronListBinding.patronImg);
    }

    @Override
    public int getItemCount() {
        return scanPatron.getPatronList().size();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.patronLayout) {
            itemClickListener.OnItemClick(v, (Integer) v.getTag());
        }
    }
}
