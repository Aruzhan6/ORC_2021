package com.example.meirlen.orc.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.meirlen.orc.App;
import com.example.meirlen.orc.R;
import com.example.meirlen.orc.basket.BasketManager;
import com.example.meirlen.orc.helper.Constans;
import com.example.meirlen.orc.helper.FakeUtiill;
import com.example.meirlen.orc.helper.ImageLoader;
import com.example.meirlen.orc.helper.SessionManager;
import com.example.meirlen.orc.interfaces.FavouriteMethodCaller;
import com.example.meirlen.orc.interfaces.UpdateProductListener;
import com.example.meirlen.orc.model.CardResponse;
import com.example.meirlen.orc.model.Character;
import com.example.meirlen.orc.model.DetailResponse;
import com.example.meirlen.orc.model.Field;
import com.example.meirlen.orc.model.Producer;
import com.example.meirlen.orc.model.Product;
import com.example.meirlen.orc.model.ProductImage;
import com.example.meirlen.orc.model.SearchValue;
import com.example.meirlen.orc.model.filter.FieldValues;
import com.example.meirlen.orc.presenter.ProductPresenter;
import com.example.meirlen.orc.view.ProductView;
import com.example.meirlen.orc.view.activity.DetailActivity;
import com.example.meirlen.orc.view.activity.ProductListActivity;
import com.example.meirlen.orc.view.adapter.DiscountAdapter;
import com.example.meirlen.orc.view.adapter.SliderAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.tinkoff.scrollingpagerindicator.ScrollingPagerIndicator;

import static com.example.meirlen.orc.helper.Constans.FAV_CHANGE;
import static com.example.meirlen.orc.view.activity.DetailActivity.EXTRA_PRODUCT;


public class DetailFragment extends Fragment implements ProductView, FavouriteMethodCaller {

    View rootview;

    @BindView(R.id.slider_pager)
    ViewPager sliderImage;

    @BindView(R.id.txtDesc)
    TextView txtDesc;

    @BindView(R.id.txtName)
    TextView txtName;


    @BindView(R.id.linearLayoutItems)
    LinearLayout linearLayoutItems;
    @BindView(R.id.linearLayoutItems2)
    LinearLayout linearLayoutItems2;

    @BindView(R.id.mainFieldLayoutSearch)
    LinearLayout mainFieldLayout;

    @BindView(R.id.txtPrice)
    TextView txtPrice;

    @BindView(R.id.txtTitle)
    TextView txtTitle;

    @BindView(R.id.slide_indicator)
    ScrollingPagerIndicator tabLayout;


    @BindView(R.id.txt_count)
    TextView txtCount;

    @BindView(R.id.product_bt_buy)
    LinearLayout productBtBuy;

    @BindView(R.id.container_added)
    LinearLayout containerAdded;

    @Inject
    ProductPresenter presenter;

    @Inject
    SessionManager sessionManager;


    @Inject
    BasketManager basketManager;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;


    @Inject
    Gson gson;
    private Product product;

    Activity activity;

    private static final String TAG = "DetailFragment";

    private String decrement;

    public static DetailFragment newInstance() {
        return new DetailFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.detail_product, null);
        App.getInstance().createDetailComponent().inject(this);
        presenter.setView(this);
        ButterKnife.bind(this, rootview);

        String strObj = Objects.requireNonNull(getActivity()).getIntent().getStringExtra(EXTRA_PRODUCT);
        product = gson.fromJson(strObj, Product.class);


        //product = FakeUtiill.getFakeProduct();

        txtDesc.setText(product.getProductDescription());
        txtTitle.setText(product.getProductName());
        txtPrice.setText(String.format("%s%s", getString(R.string.tenge), String.valueOf(product.getProductPrice())));

        if(product.getFields_and_values()!=null){
            List<FieldValues> getFields_and_values = product.getFields_and_values();
            getFields_and_values.add(new FieldValues(loadValue(product.getProductWeight()), loadField("Вес")));
            getFields_and_values.add(new FieldValues(loadValue(product.getProductSize()), loadField("Размер")));
            if (product.getProducer() != null) {
                Producer producer = product.getProducer();
                getFields_and_values.add(new FieldValues(loadValue(producer.getProducerName()), loadField("Компания")));
                getFields_and_values.add(new FieldValues(loadValue(producer.getProducerAddress()), loadField("Адресс")));
                getFields_and_values.add(new FieldValues(loadValue(producer.getProducerTel1()), loadField("Телефон")));
                txtName.setText("Еще от компании " + product.getProducer().getProducerName());
            }

        }


        if (product.getFields_and_values() != null) {
            drawFieldValues(product.getFields_and_values());
        }


        if (product.getImages() != null) {
            if (product.getImages().size() > 0) {
                List<ProductImage> images = new ArrayList<>();
                images.addAll(product.getImages());
                images.addAll(product.getImages());
                SliderAdapter sliderAdapter = new SliderAdapter(
                        getContext(), images);
                sliderImage.setAdapter(sliderAdapter);
                tabLayout.setSelectedDotColor(getResources().getColor(R.color.colorAccent));
                tabLayout.attachToPager(sliderImage);
            }

        }


        initBottomLayout();
        initCount();
        presenter.getProductById(String.valueOf(product.getProductId()), sessionManager.getAccessToken());
        return rootview;
    }

    private SearchValue loadValue(String value) {
        SearchValue searchValue = new SearchValue();
        searchValue.setValue(value);
        return searchValue;
    }

    private Field loadField(String name) {
        Field field = new Field();
        field.setFieldType("STRING");
        field.setFieldName(name);
        return field;
    }

    public void drawFieldValues(List<FieldValues> searchFields) {
        mainFieldLayout.removeAllViews();
        for (FieldValues fieldValues : searchFields) {
            View child = null;
            TextView txtValue;
            TextView txtTitle;
            switch (fieldValues.getField().getFieldType()) {
                case "MULTIPLE_SELECT":
                    child = getLayoutInflater().inflate(R.layout.item_character, null);
                    break;
                case "STRING":
                    child = getLayoutInflater().inflate(R.layout.item_character, null);
                    break;
                case "BOOLEAN":
                    child = getLayoutInflater().inflate(R.layout.item_character, null);
                    break;
                case "RANGE_INT":
                    child = getLayoutInflater().inflate(R.layout.item_character, null);
                    break;
            }
            mainFieldLayout.addView(child);

            assert child != null;
            txtValue = (TextView) child.findViewById(R.id.txtValue);
            txtTitle = (TextView) child.findViewById(R.id.txtTitle);
            txtTitle.setText(fieldValues.getField().getFieldName());
            txtValue.setText(fieldValues.getValue().getValue());
        }

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            activity = (Activity) context;
        }
    }

    private void initBottomLayout() {
        if (product.getCartCount() == null || product.getCartCount().equals("0")) {
            containerAdded.setVisibility(View.GONE);
            productBtBuy.setVisibility(View.VISIBLE);
        } else {
            containerAdded.setVisibility(View.VISIBLE);
            productBtBuy.setVisibility(View.GONE);
        }
    }

    private void initCount() {
        txtCount.setText(product.getCartCount());
    }


    @Override
    public void getList(List<Product> products) {

    }

    @Override
    public void addCartResponse(CardResponse response) {

        basketManager.update(response, product);
        product.setCartCount(String.valueOf(response.getCartCount()));
        product.setCartId(String.valueOf(response.getCartId()));
        product.setCartProductId(response.getCartProductId());
        initCount();
        initBottomLayout();
        ((UpdateProductListener) activity).onUpdateProduct(response.getProduct());


    }

    @Override
    public void detailResponse(DetailResponse response) {
        if (response.getProduct().getProducersProductsArray().size() > 0 && response.getProduct().getProducersProductsArray() != null) {
            initRandomProducts(response.getProduct().getProducersProductsArray());
        }
        if (response.getProduct().getSimilarProductsArray().size() > 0 && response.getProduct().getSimilarProductsArray() != null) {
            initSimilarProducts(response.getProduct().getSimilarProductsArray());
        }
    }

    @Override
    public void showItemLoading(int r) {
        if (r == FAV_CHANGE) {
            ((UpdateProductListener) activity).showLoading();
        } else {
            progressBar.setVisibility(View.VISIBLE);
            txtCount.setVisibility(View.GONE);
        }
    }

    @Override
    public void hideItemLoading(int r) {
        if (r == FAV_CHANGE) {
            ((UpdateProductListener) activity).hideLoading();
        } else {
            progressBar.setVisibility(View.GONE);
            txtCount.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void markFavourite(Product response) {
        ((UpdateProductListener) activity).onUpdateProduct(response);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void loadingFailed(String message) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(Throwable throwable) {
        Toast.makeText(activity, throwable.getMessage(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();

    }

    @OnClick({R.id.ic_increase, R.id.product_bt_buy, R.id.moreProducts, R.id.ic_decrease})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ic_increase:
                onAddCard(String.valueOf(product.getProductId()), "0");
                break;
            case R.id.ic_decrease:
                onAddCard(String.valueOf(product.getProductId()), "1");
                break;
            case R.id.moreProducts:
                ProductListActivity.productProducerIntent(getContext(), product);
                break;
            case R.id.product_bt_buy:
                containerAdded.setVisibility(View.VISIBLE);
                productBtBuy.setVisibility(View.GONE);
                onAddCard(String.valueOf(product.getProductId()), "0");
                break;
        }
    }

    public void onAddCard(String id, String decrement) {
        this.decrement = decrement;
        presenter.addCart(sessionManager.getAccessToken(), id, decrement);

    }


    private void initRandomProducts(List<Product> products) {
        LayoutInflater inflater = (LayoutInflater) Objects.requireNonNull(getContext()).getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        for (int i = 0; i < products.size(); i++) {
            assert inflater != null;
            View child = inflater.inflate(R.layout.product_disc_item, null);
            Product product = products.get(i);
            ImageView imageView = child.findViewById(R.id.product_img);
            TextView listText = child.findViewById(R.id.product_name);
            TextView product_price = child.findViewById(R.id.product_price);
            product_price.setText(String.valueOf(product.getProductPrice()) + getContext().getString(R.string.tenge));
            listText.setText(product.getProductName());


            if (product.getImages() != null && product.getImages().size() > 0) {
                ImageLoader.getInstance().load(getContext(), Constans.BASE_IMAGE_URL + product.getImages().get(0).getImagePath(), imageView);
            }

            linearLayoutItems.addView(child);
            child.setOnClickListener(v -> {
                Intent intent = DetailActivity.detailProductIntent(getActivity(), product);
                startActivity(intent);

            });

        }

    }

    private void initSimilarProducts(List<Product> products) {
        LayoutInflater inflater = (LayoutInflater) Objects.requireNonNull(getContext()).getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        for (int i = 0; i < products.size(); i++) {
            assert inflater != null;
            View child = inflater.inflate(R.layout.product_disc_item, null);
            Product product = products.get(i);
            ImageView imageView = child.findViewById(R.id.product_img);
            TextView listText = child.findViewById(R.id.product_name);
            TextView product_price = child.findViewById(R.id.product_price);
            product_price.setText(String.valueOf(product.getProductPrice()) + getContext().getString(R.string.tenge));
            listText.setText(product.getProductName());


            if (product.getImages() != null && product.getImages().size() > 0) {
                ImageLoader.getInstance().load(getContext(), Constans.BASE_IMAGE_URL + product.getImages().get(0).getImagePath(), imageView);
            }

            linearLayoutItems2.addView(child);
            child.setOnClickListener(v -> {
                Intent intent = DetailActivity.detailProductIntent(getActivity(), product);
                startActivity(intent);

            });

        }

    }

    @Override
    public void markSpaceFavourie(int space_id) {
        presenter.markFavourite(sessionManager.getAccessToken(), space_id);

    }


}
