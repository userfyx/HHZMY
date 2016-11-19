package com.hhzmy.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by a on 2016/11/8.
 */
public class ThreeFragmentAdapter extends PagerAdapter {
    private List<View> viewlist;
    public ThreeFragmentAdapter(List<View> viewlist) {
        this.viewlist=viewlist;
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view=viewlist.get(position);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(viewlist.get(position));
    }

    @Override
    public int getCount() {
        return viewlist.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }
}
