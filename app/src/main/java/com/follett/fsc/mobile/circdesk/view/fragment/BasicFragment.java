/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.view.fragment;

import com.follett.fsc.mobile.circdesk.BR;
import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.data.remote.repository.AppRemoteRepository;
import com.follett.fsc.mobile.circdesk.databinding.FragmentBasicLayoutBinding;
import com.follett.fsc.mobile.circdesk.interfaces.BasicNavigator;
import com.follett.fsc.mobile.circdesk.interfaces.NavigationListener;
import com.follett.fsc.mobile.circdesk.utils.AppUtils;
import com.follett.fsc.mobile.circdesk.view.base.BaseFragment;
import com.follett.fsc.mobile.circdesk.viewmodel.BasicViewModel;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

public class BasicFragment extends BaseFragment<FragmentBasicLayoutBinding, BasicViewModel> implements BasicNavigator {
    
    private FragmentBasicLayoutBinding mBasicLayoutBinding;
    
    private BasicViewModel mBasicViewModel;
    
    private AppRemoteRepository appRemoteRepository;
    
    private NavigationListener navigationListener;
    
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
        appRemoteRepository = new AppRemoteRepository();
        mBasicViewModel = new BasicViewModel(getBaseApplication(), appRemoteRepository);
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
    }
    
    @Override
    public void connectOnClick() {
        
        if (AppUtils.getInstance()
                .isEditTextNotEmpty(mBasicLayoutBinding.libraryEditText)) {
            savePreference();
        } else {
            AppUtils.getInstance()
                    .showShortToastMessages(getBaseActivity(), getString(R.string.url_empty_label));
        }
    }
    
    @Override
    public void asyncTaskResult(boolean result) {
        if (result) {
            mBasicViewModel.getVersion();
        }
    }
    
    @Override
    public void displayErrorDialog(final String message) {
        getBaseActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AppUtils.getInstance()
                        .showShortToastMessages(getBaseActivity(), message);
            }
        });
    }
    
    @Override
    public void navigationToNextFragment(int fragmentNumber) {
        navigationListener.onNavigation(0);
    }
    
    private void savePreference() {
        AppUtils.getInstance()
                .hideKeyBoard(getBaseActivity(), mBasicLayoutBinding.libraryEditText);
        if (!isNetworkConnected()) {
            AppUtils.getInstance()
                    .showNoInternetAlertDialog(getBaseActivity());
            return;
        }
        
        mBasicViewModel.savePreference(mBasicLayoutBinding.libraryEditText.getText()
                .toString()
                .trim(), null, null);
    }
    
    private void inItView(final FragmentBasicLayoutBinding basicLayoutBinding) {
        
        navigationListener = (NavigationListener) getBaseActivity();
        basicLayoutBinding.libraryEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                
                if (i == EditorInfo.IME_ACTION_DONE) {
                    connectOnClick();
                }
                return true;
            }
        });
        
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
