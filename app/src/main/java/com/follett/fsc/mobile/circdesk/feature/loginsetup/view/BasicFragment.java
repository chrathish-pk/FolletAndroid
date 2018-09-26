/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.loginsetup.view;

import com.follett.fsc.mobile.circdesk.BR;
import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.app.CTAButtonListener;
import com.follett.fsc.mobile.circdesk.app.CustomAlert;
import com.follett.fsc.mobile.circdesk.app.base.BaseFragment;
import com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences;
import com.follett.fsc.mobile.circdesk.data.remote.apicommon.Status;
import com.follett.fsc.mobile.circdesk.databinding.FragmentBasicLayoutBinding;
import com.follett.fsc.mobile.circdesk.feature.loginsetup.model.DistrictList;
import com.follett.fsc.mobile.circdesk.feature.loginsetup.viewmodel.BasicViewModel;
import com.follett.fsc.mobile.circdesk.utils.AppUtils;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.content.DialogInterface;
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

    private static final String IS_BASIC_FRAGMENT = "isbasicfragment";

    private FragmentBasicLayoutBinding mBasicLayoutBinding;

    private BasicViewModel mBasicViewModel;

    private NavigationListener navigationListener;

    private boolean mIsBaseFragment;

    public static BasicFragment newInstance(boolean isBasicFragment) {
        Bundle args = new Bundle();
        args.putBoolean(IS_BASIC_FRAGMENT, isBasicFragment);
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
        mBasicViewModel = new BasicViewModel(getBaseApplication());
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
    public void ctaButtonOnClick(View view) {
        if (getBaseActivity() == null) {
            return;
        }
        if (!AppUtils.getInstance()
                .isEditTextNotEmpty(mBasicLayoutBinding.libraryEditText)) {
            AppUtils.getInstance()
                    .showShortToastMessages(getBaseActivity(), getString(R.string.url_empty_label));
        } else if (!AppUtils.getInstance()
                .isEditTextNotEmpty(mBasicLayoutBinding.portEditText) && !mIsBaseFragment) {
            AppUtils.getInstance()
                    .showShortToastMessages(getBaseActivity(), getString(R.string.port_empty_lable));
        } else if (!AppUtils.getInstance()
                .isEditTextNotEmpty(mBasicLayoutBinding.sslportEditText) && !mIsBaseFragment) {
            AppUtils.getInstance()
                    .showShortToastMessages(getBaseActivity(), getString(R.string.ssl_port_empty_lable));
        } else if (AppUtils.getInstance()
                .isEditTextNotEmpty(mBasicLayoutBinding.libraryEditText) && mIsBaseFragment) {
            savePreference(AppUtils.getInstance()
                    .getEditTextValue(mBasicLayoutBinding.libraryEditText), null, null);
        } else {
            savePreference(AppUtils.getInstance()
                    .getEditTextValue(mBasicLayoutBinding.libraryEditText), AppUtils.getInstance()
                    .getEditTextValue(mBasicLayoutBinding.portEditText), AppUtils.getInstance()
                    .getEditTextValue(mBasicLayoutBinding.sslportEditText));
        }
    }


    public void displayErrorToast(final String message) {
        final Activity activity = getBaseActivity();
        if (activity != null) {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    AppUtils.getInstance()
                            .showShortToastMessages(activity, message);
                }
            });
        }
    }


    private void savePreference(String libraryURI, String port, String sslPort) {
    
        Activity activity = getBaseActivity();
        if (activity != null) {
            AppUtils.getInstance()
                    .hideKeyBoard(activity, mBasicLayoutBinding.libraryEditText);
            if (!isNetworkConnected()) {
                AppUtils.getInstance()
                        .showNoInternetAlertDialog(activity);
                return;
            }
            mBasicViewModel.savePreference(libraryURI, port, sslPort);
        }
    }

    private void inItView(final FragmentBasicLayoutBinding basicLayoutBinding) {
        if (getBaseActivity() == null) {
            return;
        }
        
        final Bundle arguments = getArguments();
        if (null != arguments) {
            mIsBaseFragment = arguments.getBoolean(IS_BASIC_FRAGMENT);
            mBasicViewModel.setAdvancedTabView(mIsBaseFragment);
        }

        if (mIsBaseFragment) {
            basicLayoutBinding.libraryEditText.setImeOptions(EditorInfo.IME_ACTION_DONE);
        } else {
            basicLayoutBinding.libraryEditText.setImeOptions(EditorInfo.IME_ACTION_NEXT);
            basicLayoutBinding.portEditText.setImeOptions(EditorInfo.IME_ACTION_NEXT);
            basicLayoutBinding.sslportEditText.setImeOptions(EditorInfo.IME_ACTION_DONE);
        }
        basicLayoutBinding.libraryEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {

                if (i == EditorInfo.IME_ACTION_DONE) {
                    AppUtils.getInstance()
                            .hideKeyBoard(getBaseActivity(), textView);
                    ctaButtonOnClick(basicLayoutBinding.libraryEditText);
                } else if (i == EditorInfo.IME_ACTION_NEXT) {
                    basicLayoutBinding.portEditText.requestFocus();
                }
                return true;
            }
        });
        basicLayoutBinding.sslportEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_DONE) {
                    AppUtils.getInstance()
                            .hideKeyBoard(getBaseActivity(), textView);
                    ctaButtonOnClick(basicLayoutBinding.sslportEditText);
                }
                return true;
            }
        });

        basicLayoutBinding.libraryEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Do Nothing
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                onTextChangedInEditText();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Do Nothing
            }
        });

        basicLayoutBinding.portEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Do Nothing
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                onTextChangedInEditText();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Do Nothing
            }
        });

        basicLayoutBinding.sslportEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Do Nothing
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                onTextChangedInEditText();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Do Nothing
            }
        });
        basicLayoutBinding.setBasicListener(this);
        navigationListener = (NavigationListener) getActivity();
        mBasicViewModel.setStoredSchoolUri(AppSharedPreferences.getInstance()
                .getString(SERVER_URI_VALUE));
        mBasicViewModel.getStatus()
                .observe(this, new Observer<Status>() {
                    @Override
                    public void onChanged(@Nullable Status status) {
                        handleStatus(status);
                    }
                });
        mBasicViewModel.mDistrictList.observe(this, new Observer<DistrictList>() {
            @Override
            public void onChanged(@Nullable DistrictList districtList) {
                navigationListener.onNavigation(districtList, 0);
            }
        });
    }

    private void handleStatus(Status status) {

        if (Status.SUCCESS.equals(status)) {
            navigationListener.onNavigation(null, 1);
        } else if (Status.ERROR.equals(status)) {
            displayErrorToast(getString(R.string.ssl_error));
        } else if (Status.SCHOOL_NOT_SETUP_ERROR.equals(status)) {
            displayErrorToast(getString(R.string.error_sorry_school_not_setup));
        } else if (Status.NO_LIST_FOUND.equals(status)) {
            showAlert(getString(R.string.no_district));
        }
    }

    private void showAlert(String msg) {
        if (getBaseActivity() == null) {
            return;
        }

        DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == DialogInterface.BUTTON_POSITIVE) {
                    dialog.dismiss();
                }
            }
        };
        CustomAlert.showDialog(getBaseActivity(), null, msg, getString(R.string.ok), onClickListener, null, onClickListener);
    }

    public void onTextChangedInEditText() {
        if (AppUtils.getInstance()
                .isEditTextNotEmpty(mBasicLayoutBinding.libraryEditText) && AppUtils.getInstance()
                .isEditTextNotEmpty(mBasicLayoutBinding.portEditText) && AppUtils.getInstance()
                .isEditTextNotEmpty(mBasicLayoutBinding.sslportEditText) && !mIsBaseFragment) {
            mBasicLayoutBinding.connectTextview.setSelected(true);
        } else if (AppUtils.getInstance()
                .isEditTextNotEmpty(mBasicLayoutBinding.libraryEditText) && mIsBaseFragment) {
            mBasicLayoutBinding.connectTextview.setSelected(true);
        } else {
            mBasicLayoutBinding.connectTextview.setSelected(false);
        }
    }

}
