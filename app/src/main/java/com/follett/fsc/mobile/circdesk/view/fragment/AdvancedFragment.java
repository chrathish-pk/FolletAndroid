/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.view.fragment;

import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.databinding.AdvancedBinding;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AdvancedFragment extends Fragment {
    
    private AdvancedBinding mAdvancedBinding;
    
    public static AdvancedFragment newInstance() {
        Bundle args = new Bundle();
        AdvancedFragment fragment = new AdvancedFragment();
        fragment.setArguments(args);
        return fragment;
    }
    
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mAdvancedBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_advanced_layout, container, false);
        return mAdvancedBinding.getRoot();
    }
    
}
