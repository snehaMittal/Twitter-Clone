package com.javahelps.twitter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Sneha on 31-10-2017.
 */
public class CustomFragmentAdapter extends FragmentPagerAdapter {
    public CustomFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if(position==0){
            return new HomeFragment();
        }else if(position==1){
            return new SearchFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(position==0){
            return "Home_Fragment";
        }else if(position==1) {
            return "Search";
        }
        return super.getPageTitle(position);
    }
}
