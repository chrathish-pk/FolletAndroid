package com.follett.fsc.mobile.circdesk.feature.itemstatus;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.follett.fsc.mobile.circdesk.R;
import com.follett.fsc.mobile.circdesk.app.base.BaseActivity;
import com.follett.fsc.mobile.circdesk.feature.loginsetup.NavigationListener;


public class ItemStatusActivity extends BaseActivity<ItemStatusViewModel> implements NavigationListener,View.OnClickListener{

     private ItemStatusFragment itemStatusFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        putContentView(R.layout.activity_item_status);
        initView();

    }

    private void initView() {
        setTitleBar(getString(R.string.item_status_title));
        setBackBtnVisible();
        baseBinding.backBtn.setOnClickListener(this);
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
        //setToolBarTitle
    }

    @Override
    public void onNavigation(Object model, int position) {
        //onNavigation
    }

    private void popFragmentFromBackStack(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction trans = manager.beginTransaction();
        trans.remove(fragment);
        trans.commit();
        manager.popBackStack();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.backBtn) {
            finish();
        }
    }
}
