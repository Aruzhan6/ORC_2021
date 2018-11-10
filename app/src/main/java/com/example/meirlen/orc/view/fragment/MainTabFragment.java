package com.example.meirlen.orc.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.meirlen.orc.R;
import com.example.meirlen.orc.view.custom.CustomTabLayout;
import com.example.meirlen.orc.view.custom.NonSwipeableViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Bagdat on 12/1/2017.
 */

public class MainTabFragment extends Fragment implements ViewPager.OnPageChangeListener {
    private static final int ALL_ORDERS = 0;
    private static final int MY_ORDERS = 1;

    private View rootView;


    @BindView(R.id.tabs)
    CustomTabLayout tabLayout;
    @BindView(R.id.viewpager)
    NonSwipeableViewPager viewPager;


    public static MainTabFragment newInstance() {
        return new MainTabFragment();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_requests_tab, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(DiscountFragment.newInstance(), "Акции");
        adapter.addFragment( QrListFragment.newInstance(), "Компании");
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(this);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (position == 0) {
            // toolBarImageButton.setImageResource(R.drawable.ic_like_black);
        } else {
            // toolBarImageButton.setImageResource(R.drawable.ic_left_search);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    class ViewPagerAdapter extends FragmentPagerAdapter {
        private boolean isPagingEnabled = false;

        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }


    }
}
