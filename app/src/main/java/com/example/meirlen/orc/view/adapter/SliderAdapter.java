package com.example.meirlen.orc.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.meirlen.orc.R;
import com.example.meirlen.orc.helper.Constans;
import com.example.meirlen.orc.helper.ImageLoader;
import com.example.meirlen.orc.model.Product;
import com.example.meirlen.orc.model.ProductImage;


import java.util.List;


public class SliderAdapter extends PagerAdapter {

    private List<ProductImage> images;
    private LayoutInflater inflater;
    private Context context;


    public SliderAdapter(Context context, List<ProductImage> images) {
        this.context = context;
        this.images = images;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        View imageLayout = inflater.inflate(R.layout.slider_item, view, false);

        assert imageLayout != null;

        ImageView imageView = imageLayout.findViewById(R.id.image_item_slider);
        if (images.size() > 0) {
            ImageLoader.getInstance().load(context, Constans.BASE_IMAGE_URL+images.get(position).getImagePath(), imageView);
        } else {
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setImageResource(R.drawable.placeholder);
        }


        view.addView(imageLayout, 0);
        return imageLayout;
    }

    @Override
    public int getCount() {
        return images.size() == 0 ? 1 : images.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }
}
