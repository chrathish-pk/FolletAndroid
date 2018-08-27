package com.follett.fsc.mobile.circdesk.view.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.data.model.Patron;
import com.follett.fsc.mobile.circdesk.data.model.ScanPatron;
import com.follett.fsc.mobile.circdesk.data.remote.repository.AppRemoteRepository;
import com.follett.fsc.mobile.circdesk.databinding.RowPatronListBinding;
import com.follett.fsc.mobile.circdesk.utils.FollettLog;
import com.follett.fsc.mobile.circdesk.view.viewholder.PatronListViewHolder;

public class PatronListAdapter extends RecyclerView.Adapter<PatronListViewHolder> {

    private Context context;
    private ScanPatron scanPatron;

    public PatronListAdapter(Context context, ScanPatron scanPatron) {
        this.context = context;
        this.scanPatron = scanPatron;
    }

    @NonNull
    @Override
    public PatronListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowPatronListBinding rowPatronListBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.row_patron_list, parent, false);
        return new PatronListViewHolder(rowPatronListBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull PatronListViewHolder holder, int position) {
//        holder.rowPatronListBinding.setPatronItem();
        Patron patron = scanPatron.getScanPatronResult().getPatronList().getPatron().get(position);
        holder.rowPatronListBinding.patronName.setText(patron.getLastFirstMiddleName());
        holder.rowPatronListBinding.patronBarcode.setText(patron.getBarcode());

        FollettLog.d("patronImg", AppRemoteRepository.BASE_URL + "/" + patron.getPatronPictureFileName());
        /*Glide.with(context)
                .load(AppRemoteRepository.BASE_URL + "/" + patron.getPatronPictureFileName())
                .into(holder.rowPatronListBinding.patronImg);*/
    }

    @Override
    public int getItemCount() {
        return scanPatron.getScanPatronResult().getPatronList().getPatron().size();
    }
}
