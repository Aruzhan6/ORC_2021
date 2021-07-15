package com.pixel.meirlen.orc.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.TextView;
import android.widget.Toast;

import com.pixel.meirlen.orc.App;
import com.pixel.meirlen.orc.R;
import com.pixel.meirlen.orc.base.BaseFragmentManagerActivity;
import com.pixel.meirlen.orc.basket.BasketManager;
import com.pixel.meirlen.orc.basket.Publisher;
import com.pixel.meirlen.orc.helper.ProductViewEnum;
import com.pixel.meirlen.orc.interfaces.UpdateProductList;
import com.pixel.meirlen.orc.model.Category;
import com.pixel.meirlen.orc.model.Product;
import com.pixel.meirlen.orc.view.fragment.ProductFragment;

import java.util.Observable;
import java.util.Observer;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.pixel.meirlen.orc.helper.Constans.RQ_BASKET;


public class ProductListActivity extends BaseFragmentManagerActivity implements Observer {

    public static final String EXTRA_ID_CATEGORY = "extra:parent_id";
    public static final String EXTRA_NAME_CATEGORY = "extra:name";
    public static final String EXTRA_FILTER = "extra:filter";
    public static final String EXTRA_DISCOUNY_PRODUCTS = "extra:prod";
    public static final String EXTRA_PRODUCER_ID = "extra:producer";
    private static final String TAG = "ProductListActivity";
    @BindView(R.id.txt_trash_count)
    TextView txtTrashCount;

    private UpdateProductList listener;
    @Inject
    BasketManager basketManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        App.getInstance().createProductComponent().inject(this);
        ButterKnife.bind(this);
        basketManager.register(this);
    }

    @Override
    protected Fragment fragment() {
        ProductFragment fragment = null;
        if (getIntent().getStringExtra(EXTRA_DISCOUNY_PRODUCTS) != null) {
            fragment = ProductFragment.newInstance(ProductViewEnum.DISCOUNT);
        } else if (getIntent().getStringExtra(EXTRA_PRODUCER_ID) != null) {
            fragment = ProductFragment.newInstance(ProductViewEnum.PRODUCER);
        } else {
            fragment = ProductFragment.newInstance(ProductViewEnum.PUBLIC);
        }
        setListener(fragment);
        return fragment;
    }

    public void setListener(UpdateProductList listener) {
        this.listener = listener;
    }

    @Override
    protected String title() {
        return getIntent().getStringExtra(EXTRA_NAME_CATEGORY);
    }

    @OnClick(R.id.action_bar_bt_trash)
    public void onViewClicked() {
        startActivityForResult(BasketActivity.basketIntent(this, "update"), RQ_BASKET);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RQ_BASKET) {
            listener.update();
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

    public static void productListIntent(Context context, Category category) {
        Intent intent = new Intent(context, ProductListActivity.class);
        intent.putExtra(EXTRA_ID_CATEGORY, String.valueOf(category.getCategoryId()));
        intent.putExtra(EXTRA_NAME_CATEGORY, category.getCategoryName());
        context.startActivity(intent);

    }

    public static void productProducerIntent(Context context, Product product) {
        if(product.getProducer()!=null){
            Intent intent = new Intent(context, ProductListActivity.class);
            intent.putExtra(EXTRA_PRODUCER_ID, String.valueOf(product.getProducer().getProducerId()));
            intent.putExtra(EXTRA_NAME_CATEGORY, product.getProducer().getProducerName());
            context.startActivity(intent);
        }
        else {
            Toast.makeText(context, "Снова с бэка Producer=null приходит! При добавление в избранные", Toast.LENGTH_SHORT).show();
        }


    }
}
