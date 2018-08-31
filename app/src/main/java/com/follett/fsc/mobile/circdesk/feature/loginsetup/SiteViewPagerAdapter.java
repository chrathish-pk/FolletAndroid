/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.feature.loginsetup;

import com.follett.fsc.mobile.circdesk.feature.loginsetup.BasicFragment;

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
                return BasicFragment.newInstance(true);
            case 1:
                return BasicFragment.newInstance(false);
            default:
                return null;
            
        }
    }
    
    @Override
    public int getCount() {
        return mFragmentCount;
    }
}
