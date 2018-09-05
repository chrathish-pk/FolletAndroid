package com.follett.fsc.mobile.circdesk.feature.itemstatus;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.app.base.BaseFragment;
import com.follett.fsc.mobile.circdesk.app.base.BaseViewModel;
import com.follett.fsc.mobile.circdesk.data.remote.repository.AppRemoteRepository;
import com.follett.fsc.mobile.circdesk.databinding.FragmentItemStatusBinding;
import com.follett.fsc.mobile.circdesk.feature.checkoutcheckin.CheckoutViewModel;

public class ItemStatusFragment extends BaseFragment<FragmentItemStatusBinding,ItemStatusViewModel> implements View.OnClickListener,UpdateItemUIListener{

    private ItemDetails itemDetailsinfo;
    private FragmentItemStatusBinding activityItemStatusBinding;
    private ItemStatusViewModel itemStatusViewModel;


    public ItemStatusFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_item_status, container, false);
    }


    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public ItemStatusViewModel getViewModel() {
//        itemStatusViewModel = new CheckoutViewModel(getBaseActivity().getApplication(), new AppRemoteRepository(), this);
//        return itemStatusViewModel;
        return null;
    }

    @Override
    public int getBindingVariable() {
        return 0;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }


    @Override
    public void onClick(View v) {

    }

    @Override
    public void updateUI(Object value) {

    }
}
