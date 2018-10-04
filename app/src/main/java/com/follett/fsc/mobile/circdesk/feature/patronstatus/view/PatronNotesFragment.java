/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.patronstatus.view;

import com.follett.fsc.mobile.circdesk.BR;
import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.app.base.BaseFragment;
import com.follett.fsc.mobile.circdesk.databinding.FragmentPatronNotesBinding;
import com.follett.fsc.mobile.circdesk.feature.loginsetup.view.NavigationListener;
import com.follett.fsc.mobile.circdesk.feature.patronstatus.model.Note;
import com.follett.fsc.mobile.circdesk.feature.patronstatus.viewmodel.PatronNotesViewModel;
import com.follett.fsc.mobile.circdesk.utils.FollettLog;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import java.util.ArrayList;

public class PatronNotesFragment extends BaseFragment<FragmentPatronNotesBinding, PatronNotesViewModel> implements View.OnClickListener {
    
    private static final String TAG = PatronNotesFragment.class.getSimpleName();
    
    private static final String PATRON_NOTES = "patronNote";
    
    private NavigationListener mNavigationListener;
    
    public static PatronNotesFragment newInstance(ArrayList<Note> notes) {
        Bundle args = new Bundle();
        PatronNotesFragment fragment = new PatronNotesFragment();
        args.putParcelableArrayList(PATRON_NOTES, notes);
        fragment.setArguments(args);
        return fragment;
    }
    
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mNavigationListener = (NavigationListener) context;
        } catch (ClassCastException ex) {
            FollettLog.e(TAG, "ClassCastException");
        }
    }
    
    @Override
    public int getLayoutId() {
        return R.layout.fragment_patron_notes;
    }
    
    @Override
    public PatronNotesViewModel getViewModel() {
        Application application = getBaseApplication();
        if (application == null) {
            return null;
        }
        return new PatronNotesViewModel(application);
    }
    
    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }
    
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        inItView(getViewDataBinding());
    }
    
    public void inItView(final FragmentPatronNotesBinding lBinding) {
        
        if (getBaseActivity() == null) {
            return;
        }
        
        Bundle bundle = getArguments();
        if (bundle != null) {
            final ArrayList<Note> notes = bundle.getParcelableArrayList(PATRON_NOTES);
            if (!notes.isEmpty()) {
                lBinding.notesRecyclerview.setLayoutManager(new LinearLayoutManager(getBaseActivity()));
                lBinding.notesRecyclerview.setAdapter(new PatronNotesAdapter(notes));
            }
        }
    }
    
    @Override
    public void onDetach() {
        mNavigationListener.setToolBarTitle(getString(R.string.patron_status_label));
        super.onDetach();
    }
    
    
    @Override
    public void onClick(View view) {
        // Do Nothing
    }
}

