package com.example.meirlen.orc;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.example.meirlen.orc.model.discount.Discount;
import com.example.meirlen.orc.view.fragment.CategoriesFragment;
import com.example.meirlen.orc.view.fragment.DiscountFragment;
import com.example.meirlen.orc.view.fragment.HistoryFragment;
import com.example.meirlen.orc.view.fragment.ProductFragment;
import com.example.meirlen.orc.view.fragment.ProfileFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements AHBottomNavigation.OnTabSelectedListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.bottom_navigation)
    AHBottomNavigation bottomNavigation;
    private FragmentTransaction transaction;
    private Fragment[] fragments = new Fragment[4];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        fragments[0] = DiscountFragment.newInstance();
        fragments[1] = CategoriesFragment.newInstance();
        fragments[2] = HistoryFragment.newInstance();
        fragments[3] = ProfileFragment.newInstance();
        addFragment(fragments);

        bottomNavigation.setOnTabSelectedListener(this);
        this.createNavItems();
    }


    private void createNavItems() {
        //CREATE ITEMS
        AHBottomNavigationItem menu1 = new AHBottomNavigationItem(getString(R.string.bottom_menu_1), R.drawable.ic_search);
        AHBottomNavigationItem menu2 = new AHBottomNavigationItem(getString(R.string.bottom_menu_2), R.drawable.ic_conversation);
        AHBottomNavigationItem menu3 = new AHBottomNavigationItem(getString(R.string.bottom_menu_3), R.drawable.ic_like);
        AHBottomNavigationItem menu4 = new AHBottomNavigationItem(getString(R.string.bottom_menu_4), R.drawable.ic_profile);

        //ADD THEM to bar
        bottomNavigation.addItem(menu1);
        bottomNavigation.addItem(menu2);
        bottomNavigation.addItem(menu3);
        bottomNavigation.addItem(menu4);

        //set properties
        bottomNavigation.setDefaultBackgroundColor(Color.parseColor("#FEFEFE"));
        // Change colors for AHBottomNavigation
        bottomNavigation.setAccentColor(Color.parseColor("#000000"));
        bottomNavigation.setInactiveColor(Color.parseColor("#CCCCCC"));

        //set current item
        bottomNavigation.setCurrentItem(0);

    }

    @Override
    public boolean onTabSelected(int position, boolean wasSelected) {
        //show fragment
        if (position == 0) {
            toolbar.setTitle(R.string.bottom_menu_1);
        } else if (position == 1) {
            toolbar.setTitle(R.string.bottom_menu_2);
        } else if (position == 2) {
            toolbar.setTitle(R.string.bottom_menu_3);
        } else if (position == 3) {
            toolbar.setTitle(R.string.bottom_menu_4);
        }
        replaceFragment(fragments[position]);
        return true;
    }

    private void replaceFragment(Fragment fragment) {
        //transaction.replace(R.id.tab_container, fragment, "").commit();
        for (Fragment f : fragments) {
            transaction = getSupportFragmentManager().beginTransaction();
            if (f.equals(fragment))
                transaction.show(f).commit();
            else
                transaction.hide(f).commit();
        }
    }
    private void addFragment(Fragment[] fragments) {

        for (Fragment fragment : fragments) {
            transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.frame_container, fragment, "").commit();
        }

    }


}
