package com.follett.fsc.mobile.circdesk.feature.itemstatus;

import android.arch.lifecycle.Observer;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.app.base.BaseFragment;
import com.follett.fsc.mobile.circdesk.data.remote.repository.AppRemoteRepository;
import com.follett.fsc.mobile.circdesk.databinding.FragmentItemStatusBinding;
import com.follett.fsc.mobile.circdesk.feature.iteminfo.TitleInfoActivity;
import com.follett.fsc.mobile.circdesk.feature.loginsetup.NavigationListener;
import com.follett.fsc.mobile.circdesk.utils.AppUtils;
import com.follett.fsc.mobile.circdesk.utils.FollettLog;

public class ItemStatusFragment extends BaseFragment<FragmentItemStatusBinding,ItemStatusViewModel> implements View.OnClickListener,UpdateItemUIListener {

    private ItemDetails itemDetailsinfo;
    private FragmentItemStatusBinding fragmentItemStatusBinding;
    private ItemStatusViewModel itemStatusViewModel;
    private NavigationListener mNavigationListener;


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
            mNavigationListener = (NavigationListener) context;
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
        itemStatusViewModel = new ItemStatusViewModel(getBaseActivity().getApplication(), new AppRemoteRepository(), this);
        return itemStatusViewModel;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentItemStatusBinding = getViewDataBinding();
        initView();

    }

    private void initView() {

        //baseBinding.backBtn.setOnClickListener(this);
        fragmentItemStatusBinding.itemStatusPatronGoBtn.setOnClickListener(this);
        fragmentItemStatusBinding.itemStatusCheckedoutInfoBtn.setOnClickListener(this);
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

                    if (itemDetailsinfo != null && itemDetailsinfo.getSuccess()) {
                        fragmentItemStatusBinding.itemErrorMsgLayout.setVisibility(View.GONE);
                        if (itemDetailsinfo != null) {
                            fragmentItemStatusBinding.setItemDetailsViewModel(itemDetailsinfo);
                        }
                    }
                    else if (itemDetailsinfo != null && !itemDetailsinfo.getSuccess()) {
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
                itemStatusViewModel.getScanItem(fragmentItemStatusBinding.itemStatusPatronEntry.getText().toString().trim());
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

        }

    }



    @Override
    public void updateUI(final Object itemDetails) {



    }
}
