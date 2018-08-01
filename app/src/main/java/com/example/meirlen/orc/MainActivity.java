package com.example.meirlen.orc;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.example.meirlen.orc.basket.Publisher;
import com.example.meirlen.orc.helper.GlobalVariables;
import com.example.meirlen.orc.helper.ProductViewEnum;
import com.example.meirlen.orc.model.Product;
import com.example.meirlen.orc.view.activity.BasketActivity;
import com.example.meirlen.orc.view.activity.SearchActivity;
import com.example.meirlen.orc.view.fragment.CategoriesFragment;
import com.example.meirlen.orc.view.fragment.DiscountFragment;
import com.example.meirlen.orc.view.fragment.HistoryFragment;
import com.example.meirlen.orc.view.fragment.ProductFragment;
import com.example.meirlen.orc.view.fragment.ProfileFragment;

import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements AHBottomNavigation.OnTabSelectedListener, Observer {

    private static final String TAG = "MainActivity";


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.bottom_navigation)
    AHBottomNavigation bottomNavigation;
    @BindView(R.id.txt_trash_count)
    TextView txtTrashCount;
    private FragmentTransaction transaction;
    private Fragment[] fragments = new Fragment[5];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GlobalVariables.basketManager.register(this);
        ButterKnife.bind(this);
        fragments[0] = DiscountFragment.newInstance();
        fragments[1] = CategoriesFragment.newInstance();
        fragments[2] = HistoryFragment.newInstance();
        fragments[3] = ProductFragment.newInstance(ProductViewEnum.FAVOURITE);
        fragments[4] = ProfileFragment.newInstance();
        addFragment(fragments);

        bottomNavigation.setOnTabSelectedListener(this);
        this.createNavItems();
    }


    private void createNavItems() {
        //CREATE ITEMS
        AHBottomNavigationItem menu1 = new AHBottomNavigationItem(getString(R.string.bottom_menu_1), R.drawable.ic_search);
        AHBottomNavigationItem menu2 = new AHBottomNavigationItem(getString(R.string.bottom_menu_2), R.drawable.ic_conversation);
        AHBottomNavigationItem menu3 = new AHBottomNavigationItem(getString(R.string.bottom_menu_3), R.drawable.ic_search);
        AHBottomNavigationItem menu4 = new AHBottomNavigationItem(getString(R.string.bottom_menu_4), R.drawable.ic_like);
        AHBottomNavigationItem menu5 = new AHBottomNavigationItem(getString(R.string.bottom_menu_5), R.drawable.ic_profile);

        //ADD THEM to bar
        bottomNavigation.addItem(menu1);
        bottomNavigation.addItem(menu2);
        bottomNavigation.addItem(menu3);
        bottomNavigation.addItem(menu4);
        bottomNavigation.addItem(menu5);

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
        } else if (position == 4) {
            toolbar.setTitle(R.string.bottom_menu_5);
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

    @Override
    protected void onResume() {
        super.onResume();
        setTxtTrashCount();

    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Publisher) {
            setTxtTrashCount();
        }
    }

    private void setTxtTrashCount() {
        txtTrashCount.setText(String.valueOf(GlobalVariables.COUNT_CART));

    }

    @OnClick({R.id.ic_search, R.id.action_bar_bt_trash})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.ic_search:
                Intent intent1 = new Intent(this, SearchActivity.class);
                startActivity(intent1);
                break;
            case R.id.action_bar_bt_trash:
                Intent intent = new Intent(this, BasketActivity.class);
                startActivity(intent);
                break;
        }
    }


}
