/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.view.adapter;

import com.follett.fsc.mobile.circdesk.view.fragment.AdvancedFragment;
import com.follett.fsc.mobile.circdesk.view.fragment.BasicFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class SiteViewPagerAdapter extends FragmentStatePagerAdapter {
    
    private int mFragmentCount;
    
    public SiteViewPagerAdapter(FragmentManager fm, int count) {
        super(fm);
        mFragmentCount = count;
    }
    
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return BasicFragment.newInstance();
            case 1:
                return AdvancedFragment.newInstance();
            default:
                return null;
            
        }
    }
    
    @Override
    public int getCount() {
        return mFragmentCount;
    }
}
