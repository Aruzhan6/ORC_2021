package com.example.meirlen.orc.helper;


import android.util.Log;

import com.example.meirlen.orc.AppDatabase;
import com.example.meirlen.orc.rest.model.Category;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;


public class DataGenerator {
    private static final String TAG = "DataGenerator";

    private static DataGenerator instance;
    private static AppDatabase dataBase;
    private final CompositeDisposable mDisposable = new CompositeDisposable();

    public static DataGenerator with(AppDatabase appDataBase) {

        if (dataBase == null)
            dataBase = appDataBase;

        if (instance == null)
            instance = new DataGenerator();

        return instance;
    }


    public void generateChildCategory(List<Category> categories) {
        if (dataBase == null)
            return;

        for (Category item : categories) {
            if(item.getChildren().size()>0)

                mDisposable.add(updateChildCategories(item.getChildren())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(() -> Log.d(TAG,"Дочерние категорий успешно добавлены"),
                                throwable -> {
                                    Log.d(TAG,throwable.getMessage());
                                }));

        }

    }



    private Completable updateChildCategories(final List<Category> childCategories) {
        return Completable.fromAction(() -> {
            dataBase.categoryDao().insert(childCategories);
        });
    }


}
