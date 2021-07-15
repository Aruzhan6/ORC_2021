package com.pixel.meirlen.orc;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.pixel.meirlen.orc.basket.BasketManager;
import com.pixel.meirlen.orc.basket.Publisher;
import com.pixel.meirlen.orc.helper.ProductViewEnum;
import com.pixel.meirlen.orc.interfaces.UpdateProductList;
import com.pixel.meirlen.orc.model.filter.range.MaxValue;
import com.pixel.meirlen.orc.model.filter.range.MinValue;
import com.pixel.meirlen.orc.model.filter.range.RangeInt;
import com.pixel.meirlen.orc.model.filter.range.RangeValue;
import com.pixel.meirlen.orc.view.activity.BasketActivity;
import com.pixel.meirlen.orc.view.fragment.CategoriesFragment;
import com.pixel.meirlen.orc.view.fragment.HistoryFragment;
import com.pixel.meirlen.orc.view.fragment.ProductFragment;
import com.pixel.meirlen.orc.view.fragment.ProfileFragment;
import com.pixel.meirlen.orc.view.fragment.MainTabFragment;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.pixel.meirlen.orc.helper.Constans.RQ_BASKET;

public class MainActivity extends AppCompatActivity implements AHBottomNavigation.OnTabSelectedListener, Observer {

    private static final String TAG = "MainActivity";


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.bottom_navigation)
    AHBottomNavigation bottomNavigation;
    @BindView(R.id.txt_trash_count)
    TextView txtTrashCount;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.ic_logo)
    ImageView icLogo;
    @Inject
    BasketManager basketManager;

    private FragmentTransaction transaction;
    private Fragment[] fragments = new Fragment[5];
    private UpdateProductList listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        App.getInstance().createMainComponent().inject(this);

        basketManager.register(this);
        ButterKnife.bind(this);
        fragments[0] = MainTabFragment.newInstance();
        fragments[1] = CategoriesFragment.newInstance();
        fragments[2] = HistoryFragment.newInstance();
        ProductFragment fragment = ProductFragment.newInstance(ProductViewEnum.FAVOURITE);
        setListener(fragment);
        fragments[3] = fragment;
        fragments[4] = ProfileFragment.newInstance();
        addFragment(fragments);

        bottomNavigation.setOnTabSelectedListener(this);
        bottomNavigation.setAnimation(null);


        RangeInt rangeInt = new RangeInt();
        HashMap<String, RangeValue> paramFilter = new HashMap<>();
        RangeValue rangeValue = new RangeValue(new MinValue(45), new MaxValue(45));
        paramFilter.put("f45", rangeValue);
        rangeInt.setRangeInt(paramFilter);


        Gson gson = new Gson();
        String modelClass = gson.toJson(rangeInt);
        Log.d("jsonFilter", modelClass);
        this.createNavItems();


    }


    private void createNavItems() {
        //CREATE ITEMS
        AHBottomNavigationItem menu1 = new AHBottomNavigationItem("", R.drawable.ic_home);
        AHBottomNavigationItem menu2 = new AHBottomNavigationItem("", R.drawable.ic_search);
        AHBottomNavigationItem menu3 = new AHBottomNavigationItem("", R.drawable.ic_list);
        AHBottomNavigationItem menu4 = new AHBottomNavigationItem("", R.drawable.ic_like_menu);
        AHBottomNavigationItem menu5 = new AHBottomNavigationItem("", R.drawable.ic_profile);

        //ADD THEM to bar
        bottomNavigation.addItem(menu1);
        bottomNavigation.addItem(menu2);
        bottomNavigation.addItem(menu3);
        bottomNavigation.addItem(menu4);
        bottomNavigation.addItem(menu5);

        //set properties
        // bottomNavigation.setDefaultBackgroundColor(Color.parseColor("#FEFEFE"));
        // Change colors for AHBottomNavigation
        // bottomNavigation.setDefaultBackgroundResource(R.drawable.bottom_menu_selector);
        bottomNavigation.setBackgroundColor(Color.parseColor("#FFFFFF"));
        bottomNavigation.setAccentColor(Color.parseColor("#764ba2"));
        bottomNavigation.setInactiveColor(Color.parseColor("#9B9B9B"));


        //set current item
        bottomNavigation.setCurrentItem(2);


    }

    @Override
    public boolean onTabSelected(int position, boolean wasSelected) {
        //show fragment
        if (position == 0) {
            toolbarTitle.setText(R.string.bottom_menu_1);

        } else if (position == 1) {
            toolbarTitle.setText(R.string.bottom_menu_2);

        } else if (position == 2) {
            toolbarTitle.setText(R.string.bottom_menu_3);
        } else if (position == 3) {
            toolbarTitle.setText(R.string.bottom_menu_4);
        } else if (position == 4) {
            toolbarTitle.setText(R.string.bottom_menu_5);
        }

        if (position == 0) {
            toolbarTitle.setVisibility(View.GONE);
            icLogo.setVisibility(View.VISIBLE);
        } else {
            toolbarTitle.setVisibility(View.VISIBLE);
            icLogo.setVisibility(View.GONE);
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
        txtTrashCount.setText(String.valueOf(BasketManager.COUNT_CART));
    }

    @OnClick({R.id.action_bar_bt_trash})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.action_bar_bt_trash:
                startActivityForResult(BasketActivity.basketIntent(this, "update"), RQ_BASKET);
                break;
        }
    }

    public void setListener(UpdateProductList listener) {
        this.listener = listener;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RQ_BASKET) {
            listener.update();
        }
    }


}
