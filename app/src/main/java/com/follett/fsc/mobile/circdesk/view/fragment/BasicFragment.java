/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.view.fragment;

import com.follett.fsc.mobile.circdesk.BR;
import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.databinding.FragmentBasicLayoutBinding;
import com.follett.fsc.mobile.circdesk.interfaces.BasicNavigator;
import com.follett.fsc.mobile.circdesk.utils.AppUtils;
import com.follett.fsc.mobile.circdesk.view.base.BaseFragment;
import com.follett.fsc.mobile.circdesk.viewmodel.BasicViewModel;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

public class BasicFragment extends BaseFragment<FragmentBasicLayoutBinding, BasicViewModel> implements BasicNavigator {
    
    private FragmentBasicLayoutBinding mBasicLayoutBinding;
    
    private BasicViewModel mBasicViewModel;
    
    public static BasicFragment newInstance() {
        Bundle args = new Bundle();
        BasicFragment fragment = new BasicFragment();
        fragment.setArguments(args);
        return fragment;
    }
    
    @Override
    public int getLayoutId() {
        return R.layout.fragment_basic_layout;
    }
    
    @Override
    public BasicViewModel getViewModel() {
        mBasicViewModel = ViewModelProviders.of(this)
                .get(BasicViewModel.class);
        return mBasicViewModel;
    }
    
    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }
    
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBasicViewModel.setNavigator(this);
    }
    
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBasicLayoutBinding = getViewDataBinding();
        inItView(mBasicLayoutBinding);
        mBasicViewModel.setNavigator(this);
    }
    
    @Override
    public void connectOnClick() {
        
        if (AppUtils.getInstance()
                .isEditTextEmpty(mBasicLayoutBinding.libraryEditText)) {
            AppUtils.getInstance()
                    .hideKeyBoard(getBaseActivity(), mBasicLayoutBinding.libraryEditText);
        } else {
            AppUtils.getInstance()
                    .showShortToastMessages(getBaseActivity(), getString(R.string.url_empty_label));
        }
    }

    private void inItView(final FragmentBasicLayoutBinding basicLayoutBinding) {
        
        basicLayoutBinding.libraryEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            
            }
            
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                
                if (charSequence.length() != 0) {
                    basicLayoutBinding.connectTextview.setSelected(true);
                } else {
                    basicLayoutBinding.connectTextview.setSelected(false);
                }
            }
            
            @Override
            public void afterTextChanged(Editable editable) {
            
            }
        });
    }
    
    
}
