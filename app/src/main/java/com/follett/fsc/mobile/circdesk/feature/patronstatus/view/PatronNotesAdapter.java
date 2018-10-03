/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */
package com.follett.fsc.mobile.circdesk.feature.patronstatus.view;

import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.databinding.PatronNotesItemBinding;
import com.follett.fsc.mobile.circdesk.feature.patronstatus.model.Note;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

public class PatronNotesAdapter extends RecyclerView.Adapter<PatronNotesAdapter.PatronNotesViewHolder> {
    
    private List<Note> mNotes;
    
    @NonNull
    @Override
    public PatronNotesAdapter.PatronNotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PatronNotesItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.patron_notes_item, parent, false);
        return new PatronNotesViewHolder(binding);
    }
    
    public PatronNotesAdapter(List<Note> notes) {
        mNotes = notes;
    }
    
    @Override
    public int getItemCount() {
        if (mNotes != null) {
            return mNotes.size();
        }
        return 0;
    }
    
    @Override
    public void onBindViewHolder(PatronNotesAdapter.PatronNotesViewHolder holder, int position) {
        holder.binding.setNotes(mNotes.get(position));
    }
    
    public class PatronNotesViewHolder extends RecyclerView.ViewHolder {
        private PatronNotesItemBinding binding;
        
        public PatronNotesViewHolder(PatronNotesItemBinding listItemBinding) {
            super(listItemBinding.getRoot());
            binding = listItemBinding;
        }
    }
}