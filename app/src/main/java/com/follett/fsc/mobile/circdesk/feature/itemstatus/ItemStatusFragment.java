package com.follett.fsc.mobile.circdesk.feature.itemstatus;

import android.arch.lifecycle.Observer;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;

import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.app.base.BaseFragment;
import com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences;
import com.follett.fsc.mobile.circdesk.databinding.FragmentItemStatusBinding;
import com.follett.fsc.mobile.circdesk.feature.iteminfo.TitleInfoActivity;
import com.follett.fsc.mobile.circdesk.feature.loginsetup.NavigationListener;
import com.follett.fsc.mobile.circdesk.utils.AppUtils;
import com.follett.fsc.mobile.circdesk.utils.FollettLog;

public class ItemStatusFragment extends BaseFragment<FragmentItemStatusBinding, ItemStatusViewModel> implements View.OnClickListener {

    private ItemDetails itemDetailsinfo;
    private FragmentItemStatusBinding fragmentItemStatusBinding;
    private ItemStatusViewModel itemStatusViewModel;


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
        itemStatusViewModel = new ItemStatusViewModel(getBaseActivity().getApplication());
        return itemStatusViewModel;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentItemStatusBinding = getViewDataBinding();
        mActivity.setTitleBar(getString(R.string.item_status_title));
        initView();

    }

    private void initView() {

        fragmentItemStatusBinding.itemStatusPatronGoBtn.setOnClickListener(this);
        fragmentItemStatusBinding.itemStatusCheckedoutInfoBtn.setOnClickListener(this);
        fragmentItemStatusBinding.libraryResourceIncludeLayout.libraryBtn.setOnClickListener(this);
        fragmentItemStatusBinding.libraryResourceIncludeLayout.resourceBtn.setOnClickListener(this);
        itemStatusViewModel.getErrorMessage()
                .observe(this, new Observer() {
                    @Override
                    public void onChanged(@Nullable Object object) {
                        AppUtils.getInstance()
                                .showShortToastMessages(getBaseActivity(), (String) object);
                    }
                });
        itemStatusViewModel.itemDetailsInfo.observe(this, new Observer<ItemDetails>() {
            @Override
            public void onChanged(@Nullable ItemDetails itemDetails) {

                updateItemStatusUI(itemDetails);
            }


        });
    }

    private void updateItemStatusUI(final ItemDetails itemDetails) {

        getActivity().runOnUiThread(new Runnable() {

            @Override
            public void run() {
                if (itemDetails != null) {
                    itemDetailsinfo = itemDetails;

                    if (itemDetailsinfo.getSuccess()) {
                        fragmentItemStatusBinding.itemErrorMsgLayout.setVisibility(View.GONE);
                        fragmentItemStatusBinding.setItemDetailsViewModel(itemDetailsinfo);
                    } else if (!itemDetailsinfo.getSuccess()) {
                        fragmentItemStatusBinding.itemErrorMsgLayout.setVisibility(View.VISIBLE);
                        fragmentItemStatusBinding.itemStatusDetailLayout.setVisibility(View.GONE);
                        fragmentItemStatusBinding.itemStatusCheckoutDetailsLayout.setVisibility(View.GONE);
                        String itemErrorMsg = getString(R.string.double_quote) + AppUtils.getInstance()
                                .getEditTextValue(fragmentItemStatusBinding.itemStatusPatronEntry) + getString(R.string.double_quote);
                        fragmentItemStatusBinding.itemErrorMsg.setText(getString(R.string.item_not_found, itemErrorMsg));
                    }

                }
            }
        });
    }

    @Override
    public int getBindingVariable() {
        return 0;
    }


    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.itemStatusPatronGoBtn) {
            AppUtils.getInstance()
                    .hideKeyBoard(getActivity(), fragmentItemStatusBinding.itemStatusPatronEntry);
            if (AppUtils.getInstance().isEditTextNotEmpty(fragmentItemStatusBinding.itemStatusPatronEntry)) {
                int collectionType = AppSharedPreferences.getInstance().getBoolean(AppSharedPreferences.KEY_IS_LIBRARY_SELECTED) ? 0 : 4;
                itemStatusViewModel.getScanItem(fragmentItemStatusBinding.itemStatusPatronEntry.getText().toString().trim(), String.valueOf(collectionType));
            } else {
                AppUtils.getInstance()
                        .showShortToastMessages(getActivity(), getString(R.string.errorPatronEntry));
            }
        } else if (v.getId() == R.id.itemStatusCheckedoutInfoBtn) {

            Intent titleIntent = new Intent(getActivity(), TitleInfoActivity.class);
            String bidID = Integer.toString(itemDetailsinfo.getBibID());
            titleIntent.putExtra("bibID", bidID);
            Log.i("TAG", "BIDID :" + bidID);
            startActivity(titleIntent);

        } else if (v.getId() == R.id.libraryBtn) {
            fragmentItemStatusBinding.libraryResourceIncludeLayout.libraryBtn.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.blueLabel));
            fragmentItemStatusBinding.libraryResourceIncludeLayout.libraryBtn.setTextColor(ContextCompat.getColor(getActivity(), R.color.white));
            fragmentItemStatusBinding.libraryResourceIncludeLayout.resourceBtn.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.white));
            fragmentItemStatusBinding.libraryResourceIncludeLayout.resourceBtn.setTextColor(ContextCompat.getColor(getActivity(), R.color.blueLabel));
            AppSharedPreferences.getInstance().setBoolean(AppSharedPreferences.KEY_IS_LIBRARY_SELECTED, true);
            disableItemStatusView();

        } else if (v.getId() == R.id.resourceBtn) {
            fragmentItemStatusBinding.libraryResourceIncludeLayout.resourceBtn.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.blueLabel));
            fragmentItemStatusBinding.libraryResourceIncludeLayout.resourceBtn.setTextColor(ContextCompat.getColor(getActivity(), R.color.white));
            fragmentItemStatusBinding.libraryResourceIncludeLayout.libraryBtn.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.white));
            fragmentItemStatusBinding.libraryResourceIncludeLayout.libraryBtn.setTextColor(ContextCompat.getColor(getActivity(), R.color.blueLabel));
            AppSharedPreferences.getInstance().setBoolean(AppSharedPreferences.KEY_IS_LIBRARY_SELECTED, false);
            disableItemStatusView();
        }
    }

    private void disableItemStatusView() {
        fragmentItemStatusBinding.itemStatusPatronEntry.setText("");
        fragmentItemStatusBinding.itemErrorMsgLayout.setVisibility(View.GONE);
        fragmentItemStatusBinding.itemStatusDetailLayout.setVisibility(View.GONE);
        fragmentItemStatusBinding.itemStatusCheckoutDetailsLayout.setVisibility(View.GONE);
    }


}
