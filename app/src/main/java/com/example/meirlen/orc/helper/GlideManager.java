package com.example.meirlen.orc.helper;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.meirlen.orc.R;

public class GlideManager {

    private static volatile GlideManager glideManager;

    private GlideManager() {
        /**
            Гарантия то что этот обьект будет в одном экземпляре
         */
    }

    public static GlideManager instance() {

        if (glideManager == null) {
            synchronized (GlideManager.class) {
                return new GlideManager();
            }

        }
        return glideManager;

    }


    /**
     * Загрузка фото с установкой фиксированных размеров
     *
     * @param widthPx  ширина в пикселях
     * @param heightPx высота в пикселях
     */
    public void initAvatar(Context context, String url, ImageView imageView, int widthPx, int heightPx) {
        Glide.with(context)
                .load(url)
                .apply(new RequestOptions()
                        .override(widthPx, heightPx)
                        .placeholder(R.drawable.placeholder))
                .into(imageView);
    }


}
