package com.follett.fsc.mobile.circdesk.feature.itemstatus.view;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;

import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.BR;
import com.follett.fsc.mobile.circdesk.app.base.BaseFragment;
import com.follett.fsc.mobile.circdesk.app.base.ScannerViewModel;
import com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences;
import com.follett.fsc.mobile.circdesk.databinding.FragmentItemStatusBinding;
import com.follett.fsc.mobile.circdesk.feature.iteminfo.view.TitleInfoActivity;
import com.follett.fsc.mobile.circdesk.feature.itemstatus.model.ItemDetails;
import com.follett.fsc.mobile.circdesk.feature.itemstatus.viewmodel.ItemStatusViewModel;
import com.follett.fsc.mobile.circdesk.feature.loginsetup.view.NavigationListener;
import com.follett.fsc.mobile.circdesk.utils.AppUtils;
import com.follett.fsc.mobile.circdesk.utils.FollettLog;
import com.honeywell.aidc.BarcodeFailureEvent;
import com.honeywell.aidc.BarcodeReadEvent;
import com.honeywell.aidc.BarcodeReader;

import static com.follett.fsc.mobile.circdesk.utils.AppConstants.BARCODE_READER_NOT_AVAILABLE;

public class ItemStatusFragment extends BaseFragment<FragmentItemStatusBinding, ItemStatusViewModel> implements View.OnClickListener,BarcodeReader.BarcodeListener {

    private static final String TAG = ScannerViewModel.class.getSimpleName();
    private ItemDetails itemDetailsinfo;
    private FragmentItemStatusBinding fragmentItemStatusBinding;
    private ItemStatusViewModel itemStatusViewModel;
    private BarcodeReader mBarcodeReader;



    public ItemStatusFragment() {
        // Required empty public constructor
    }

    public static ItemStatusFragment newInstance() {
        Bundle args = new Bundle();
        ItemStatusFragment fragment = new ItemStatusFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBarcodeReader = mActivity.getBarcodeReader();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            NavigationListener mNavigationListener = (NavigationListener) context;
        } catch (ClassCastException ex) {
            FollettLog.e("TAG", "ClassCastException");
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_item_status;
    }

    @Override
    public ItemStatusViewModel getViewModel() {
        if (getBaseApplication() == null) {
            return null;
        }
        itemStatusViewModel = new ItemStatusViewModel(getBaseApplication());
        return itemStatusViewModel;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentItemStatusBinding = getViewDataBinding();
        initView();

    }

    private void initView() {
        final Activity activity = getBaseActivity();
        if (activity == null) {
            return;
        }
        String brandName = Build.BRAND;
        if(brandName.equalsIgnoreCase(getString(R.string.dev_brand_name)))
        {
            mBarcodeReader.addBarcodeListener(this);
            fragmentItemStatusBinding.itemScanBtn.setOnClickListener(this);

        }

        fragmentItemStatusBinding.itemStatusPatronGoBtn.setOnClickListener(this);
        fragmentItemStatusBinding.itemStatusCheckedoutInfoBtn.setOnClickListener(this);
        fragmentItemStatusBinding.libraryResourceIncludeLayout.libraryBtn.setOnClickListener(this);
        fragmentItemStatusBinding.libraryResourceIncludeLayout.resourceBtn.setOnClickListener(this);
        itemStatusViewModel.itemDetailsInfo.observe(this, new Observer<ItemDetails>() {
            @Override
            public void onChanged(@Nullable ItemDetails itemDetails) {

                updateItemStatusUI(itemDetails);
            }


        });
        updateLibraryResourceBg();
    }

    private void updateLibraryResourceBg() {
        if (AppSharedPreferences.getInstance().getBoolean(AppSharedPreferences.KEY_IS_LIBRARY_SELECTED)) {
            fragmentItemStatusBinding.libraryResourceIncludeLayout.libraryBtn.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.blueLabel));
            fragmentItemStatusBinding.libraryResourceIncludeLayout.libraryBtn.setTextColor(ContextCompat.getColor(getActivity(), R.color.white));
            fragmentItemStatusBinding.libraryResourceIncludeLayout.resourceBtn.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.white));
            fragmentItemStatusBinding.libraryResourceIncludeLayout.resourceBtn.setTextColor(ContextCompat.getColor(getActivity(), R.color.blueLabel));
        } else {
            fragmentItemStatusBinding.libraryResourceIncludeLayout.resourceBtn.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.blueLabel));
            fragmentItemStatusBinding.libraryResourceIncludeLayout.resourceBtn.setTextColor(ContextCompat.getColor(getActivity(), R.color.white));
            fragmentItemStatusBinding.libraryResourceIncludeLayout.libraryBtn.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.white));
            fragmentItemStatusBinding.libraryResourceIncludeLayout.libraryBtn.setTextColor(ContextCompat.getColor(getActivity(), R.color.blueLabel));
        }
    }

    private void updateItemStatusUI(final ItemDetails itemDetails) {
        Activity activity = getBaseActivity();
        if (activity == null) {
            return;
        }
        activity.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                if (itemDetails != null) {
                    itemDetailsinfo = itemDetails;
                    if (itemDetailsinfo.getSuccess()) {
                        fragmentItemStatusBinding.itemErrorMsgLayout.setVisibility(View.GONE);
                        titleInfoBtnShow(itemDetailsinfo);
                    } else if (!itemDetailsinfo.getSuccess()) {
                        fragmentItemStatusBinding.itemErrorMsgLayout.setVisibility(View.VISIBLE);
                        fragmentItemStatusBinding.itemStatusDetailLayout.setVisibility(View.GONE);
                        fragmentItemStatusBinding.itemStatusCheckoutDetailsLayout.setVisibility(View.GONE);
                        String itemErrorMsg = getString(R.string.double_quote) + AppUtils.getInstance()
                                .getEditTextValue(fragmentItemStatusBinding.itemStatusPatronEntry) + getString(R.string.double_quote);
                        if(AppSharedPreferences.getInstance().getBoolean(AppSharedPreferences.KEY_IS_LIBRARY_SELECTED)) {
                            fragmentItemStatusBinding.itemErrorMsg.setText(getString(R.string.item_not_found, itemErrorMsg));
                        }
                        else
                        {
                            fragmentItemStatusBinding.itemErrorMsg.setText(getString(R.string.copyitem_not_found, itemErrorMsg));

                        }
                    }

                }
            }
        });
    }


    private void titleInfoBtnShow(ItemDetails itemDetails) {
        fragmentItemStatusBinding.setItemDetailsViewModel(itemDetailsinfo);
        if (AppSharedPreferences.getInstance().getBoolean(AppSharedPreferences.KEY_IS_LIBRARY_SELECTED)) {
            if(!itemDetails.getCurrentCheckout().isEmpty()) {
                fragmentItemStatusBinding.itemStatusCheckedoutInfoBtn.setVisibility(View.VISIBLE);
            }
            else
            {
                fragmentItemStatusBinding.itemStatusCheckedoutInfoBtn.setVisibility(View.GONE);
            }
        }
        else
        {
            fragmentItemStatusBinding.itemStatusCheckedoutInfoBtn.setVisibility(View.GONE);

        }
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }


    @Override
    public void onClick(View v) {


        if (v.getId() == R.id.itemStatusPatronGoBtn) {
            getScanItem();

        }
        else if (v == fragmentItemStatusBinding.itemScanBtn) {
            itemStatusViewModel.triggerSoftwareScanner(mBarcodeReader);
        }else if (v.getId() == R.id.itemStatusCheckedoutInfoBtn) {
            if (!isNetworkConnected()) {
                AppUtils.getInstance()
                        .showNoInternetAlertDialog(mActivity);
                return;
            }
            Intent titleIntent = new Intent(mActivity, TitleInfoActivity.class);
            String bidID = Integer.toString(itemDetailsinfo.getBibID());
            titleIntent.putExtra("bibID", bidID);
            Log.i("TAG", "BIDID :" + bidID);
            startActivity(titleIntent);

        } else if (v.getId() == R.id.libraryBtn) {
            fragmentItemStatusBinding.libraryResourceIncludeLayout.libraryBtn.setBackgroundColor(ContextCompat.getColor(mActivity, R.color.blueLabel));
            fragmentItemStatusBinding.libraryResourceIncludeLayout.libraryBtn.setTextColor(ContextCompat.getColor(mActivity, R.color.white));
            fragmentItemStatusBinding.libraryResourceIncludeLayout.resourceBtn.setBackgroundColor(ContextCompat.getColor(mActivity, R.color.white));
            fragmentItemStatusBinding.libraryResourceIncludeLayout.resourceBtn.setTextColor(ContextCompat.getColor(mActivity, R.color.blueLabel));
            AppSharedPreferences.getInstance().setBoolean(AppSharedPreferences.KEY_IS_LIBRARY_SELECTED, true);
            disableItemStatusView();

        } else if (v.getId() == R.id.resourceBtn) {
            fragmentItemStatusBinding.libraryResourceIncludeLayout.resourceBtn.setBackgroundColor(ContextCompat.getColor(mActivity, R.color.blueLabel));
            fragmentItemStatusBinding.libraryResourceIncludeLayout.resourceBtn.setTextColor(ContextCompat.getColor(mActivity, R.color.white));
            fragmentItemStatusBinding.libraryResourceIncludeLayout.libraryBtn.setBackgroundColor(ContextCompat.getColor(mActivity, R.color.white));
            fragmentItemStatusBinding.libraryResourceIncludeLayout.libraryBtn.setTextColor(ContextCompat.getColor(mActivity, R.color.blueLabel));
            AppSharedPreferences.getInstance().setBoolean(AppSharedPreferences.KEY_IS_LIBRARY_SELECTED, false);
            disableItemStatusView();
        }
    }

    private void getScanItem() {
        if (getBaseActivity() == null) {
            return;
        }
        AppUtils.getInstance()
                .hideKeyBoard(mActivity, fragmentItemStatusBinding.itemStatusPatronEntry);
        if (AppUtils.getInstance().isEditTextNotEmpty(fragmentItemStatusBinding.itemStatusPatronEntry)) {
            if (!isNetworkConnected()) {
                AppUtils.getInstance()
                        .showNoInternetAlertDialog(mActivity);
                return;
            }
            int collectionType = AppSharedPreferences.getInstance().getBoolean(AppSharedPreferences.KEY_IS_LIBRARY_SELECTED) ? 0 : 4;
            itemStatusViewModel.getScanItem(fragmentItemStatusBinding.itemStatusPatronEntry.getText().toString().trim(), String.valueOf(collectionType));
        } else {
            AppUtils.getInstance()
                    .showShortToastMessages(mActivity, getString(R.string.errorPatronEntry));
        }
    }

    private void disableItemStatusView() {
        fragmentItemStatusBinding.itemStatusPatronEntry.setText("");
        fragmentItemStatusBinding.itemErrorMsgLayout.setVisibility(View.GONE);
        fragmentItemStatusBinding.itemStatusDetailLayout.setVisibility(View.GONE);
        fragmentItemStatusBinding.itemStatusCheckoutDetailsLayout.setVisibility(View.GONE);
    }


    @Override
    public void onBarcodeEvent(final BarcodeReadEvent event) {
        itemStatusViewModel.onBarcodeFailureEvent(mBarcodeReader);
        getBaseActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (event != null) {
                    String barcode = event.getBarcodeData();
                    fragmentItemStatusBinding.itemStatusPatronEntry.setText(barcode);
                    getScanItem();           }
            }
        });
    }

    @Override
    public void onFailureEvent(BarcodeFailureEvent barcodeFailureEvent) {
        itemStatusViewModel.onBarcodeFailureEvent(mBarcodeReader);

    }

    @Override
    public void onResume() {
        super.onResume();
        itemStatusViewModel.onResumeScanner(mBarcodeReader);
    }

    @Override
    public void onPause() {
        super.onPause();
        itemStatusViewModel.onPauseScanner(mBarcodeReader);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mBarcodeReader != null) {
            mBarcodeReader.removeBarcodeListener(this);
        }
    }
}
