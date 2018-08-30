/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.view.fragment;

import com.follett.fsc.mobile.circdesk.BR;
import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences;
import com.follett.fsc.mobile.circdesk.data.remote.apicommon.Status;
import com.follett.fsc.mobile.circdesk.data.remote.repository.AppRemoteRepository;
import com.follett.fsc.mobile.circdesk.databinding.FragmentBasicLayoutBinding;
import com.follett.fsc.mobile.circdesk.interfaces.CTAButtonListener;
import com.follett.fsc.mobile.circdesk.interfaces.NavigationListener;
import com.follett.fsc.mobile.circdesk.utils.AppUtils;
import com.follett.fsc.mobile.circdesk.view.base.BaseFragment;
import com.follett.fsc.mobile.circdesk.viewmodel.BasicViewModel;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import static com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences.SERVER_URI_VALUE;

public class BasicFragment extends BaseFragment<FragmentBasicLayoutBinding, BasicViewModel> implements CTAButtonListener {
    
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
    public void ctaButtonOnClick() {
        
        if (AppUtils.getInstance()
                .isEditTextNotEmpty(mBasicLayoutBinding.libraryEditText)) {
            savePreference();
        } else {
            AppUtils.getInstance()
                    .showShortToastMessages(getBaseActivity(), getString(R.string.url_empty_label));
        }
    }
    
    
    public void displayErrorToast(final String message) {
        getBaseActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AppUtils.getInstance()
                        .showShortToastMessages(getBaseActivity(), message);
            }
        });
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
        basicLayoutBinding.setBasicListener(this);
        navigationListener = (NavigationListener) getBaseActivity();
        mBasicViewModel.setStoredSchoolUri(AppSharedPreferences.getInstance(getBaseActivity())
                .getString(SERVER_URI_VALUE));
        basicLayoutBinding.libraryEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                
                if (i == EditorInfo.IME_ACTION_DONE) {
                    ctaButtonOnClick();
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
        
        mBasicViewModel.getStatus()
                .observe(this, new Observer<Status>() {
                    @Override
                    public void onChanged(@Nullable Status status) {
                        handleStatus(status);
                    }
                });
    }
    
    private void handleStatus(Status status) {
        
        if (Status.SUCCESS.equals(status)) {
            navigationListener.onNavigation(0);
        } else if (Status.ERROR.equals(status)) {
            displayErrorToast(getString(R.string.ssl_error));
        } else if (Status.SCHOOL_NOT_SETUP_ERROR.equals(status)) {
            displayErrorToast(getString(R.string.error_sorry_school_not_setup));
        }
    }
    
}
