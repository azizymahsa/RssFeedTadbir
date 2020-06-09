package com.example.rssfeedtadbir.viewmodel;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.rssfeedtadbir.view.NewsRssFragment;
import com.example.rssfeedtadbir.view.FavoriteNewsFragment;
import com.example.rssfeedtadbir.view.adapter.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainViewModel {

    private FragmentStatePagerAdapter viewPagerAdapter;
    private TabLayout.ViewPagerOnTabSelectedListener viewPagerOnTabSelectedListener;
    private TabLayout.TabLayoutOnPageChangeListener tabLayoutOnPageChangeListener;

    public MainViewModel(FragmentManager fm,
                         TabLayout.ViewPagerOnTabSelectedListener viewPagerOnTabSelectedListener,
                         TabLayout.TabLayoutOnPageChangeListener tabLayoutOnPageChangeListener) {
        this.viewPagerOnTabSelectedListener = viewPagerOnTabSelectedListener;
        this.tabLayoutOnPageChangeListener = tabLayoutOnPageChangeListener;

        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new NewsRssFragment());
        fragments.add(new FavoriteNewsFragment());
        viewPagerAdapter = new ViewPagerAdapter(fm, fragments);
    }

    public FragmentStatePagerAdapter getViewPagerAdapter() {
        return viewPagerAdapter;
    }

    public TabLayout.ViewPagerOnTabSelectedListener getViewPagerOnTabSelectedListener() {
        return viewPagerOnTabSelectedListener;
    }

    public TabLayout.TabLayoutOnPageChangeListener getTabLayoutOnPageChangeListener() {
        return tabLayoutOnPageChangeListener;
    }
}
