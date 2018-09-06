package com.follett.fsc.mobile.circdesk.feature.itemstatus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.app.base.BaseActivity;
import com.follett.fsc.mobile.circdesk.data.local.prefs.AppSharedPreferences;
import com.follett.fsc.mobile.circdesk.data.remote.repository.AppRemoteRepository;
import com.follett.fsc.mobile.circdesk.databinding.ActivityItemStatusBinding;
import com.follett.fsc.mobile.circdesk.feature.iteminfo.TitleInfoActivity;
import com.follett.fsc.mobile.circdesk.feature.loginsetup.NavigationListener;
import com.follett.fsc.mobile.circdesk.utils.AppUtils;

public class ItemStatusActivity extends BaseActivity<ItemStatusViewModel> implements NavigationListener{

    private ItemDetails itemDetailsinfo;
    private ActivityItemStatusBinding activityItemStatusBinding;
    private ItemStatusViewModel itemStatusViewModel;
    private ItemStatusFragment itemStatusFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        putContentView(R.layout.activity_item_status);
        initView();

    }

    private void initView() {
        setTitleBar(getString(R.string.item_status_title));
        itemStatusFragment = ItemStatusFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.itemStatusContainer, itemStatusFragment, "itemStatusFragment")
                .commit();
    }
    @Override
    public void onNavigation(int position) {

        popFragmentFromBackStack(itemStatusFragment);
    }

    @Override
    public void setToolBarTitle(String titleText) {

    }

     private void popFragmentFromBackStack(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction trans = manager.beginTransaction();
        trans.remove(fragment);
        trans.commit();
        manager.popBackStack();
    }
}
