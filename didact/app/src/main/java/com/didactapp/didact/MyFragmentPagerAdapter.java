package com.didactapp.didact;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by roman on 08/03/16.
 */
public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    List<Fragment> mlistFragments;

    public MyFragmentPagerAdapter(FragmentManager fm, List<Fragment> listFragments) {
        super(fm);
        mlistFragments=listFragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mlistFragments.get(position);
    }

    @Override
    public int getCount() {
        return mlistFragments.size();
    }
}
