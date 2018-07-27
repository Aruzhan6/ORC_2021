package com.example.meirlen.orc.helper;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.meirlen.orc.R;

public class ImageLoader {

    private static volatile ImageLoader imageLoader;

    private ImageLoader() {

    }

    public static void initInstance() {
        if (imageLoader == null) {
            synchronized (ImageLoader.class) {
                imageLoader = new ImageLoader();
            }
        }
    }

    public static ImageLoader getInstance() {
        return imageLoader;
    }


    /**
     * Загрузка фото с установкой фиксированных размеров
     *
     * @param widthPx  ширина в пикселях
     * @param heightPx высота в пикселях
     */
    public void load(Context context, String url, ImageView imageView, int widthPx, int heightPx) {
        Glide.with(context)
                .load(url)
                .apply(new RequestOptions()
                        .override(widthPx, heightPx)
                        .placeholder(R.drawable.placeholder))
                .into(imageView);
    }

    public void load(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .apply(new RequestOptions()
                        .placeholder(R.drawable.placeholder))
                .into(imageView);
    }

}
