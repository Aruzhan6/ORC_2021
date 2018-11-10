package com.example.meirlen.orc.helper;


import android.content.ClipData;
import android.util.Log;

import com.example.meirlen.orc.AppDatabase;
import com.example.meirlen.orc.model.Category;
import com.example.meirlen.orc.model.Field;
import com.example.meirlen.orc.model.SearchValue;

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
            if (item.getChildren().size() > 0)
                mDisposable.add(updateChildCategories(item.getChildren())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(() -> {
                                    Log.d(TAG, "Дочерние категорий успешно добавлены" + " " + String.valueOf(item.getFields().size()));
                                    generateFields(item.getChildren());

                                },
                                throwable -> {
                                    Log.d(TAG, throwable.getMessage());
                                }));
        }

        for (int i = 0; i < categories.size(); i++) {
            for (int j = 0; j < categories.get(i).getChildren().size(); j++) {
                Category item = categories.get(i);
                if (item.getChildren().size() > 0) {
                    generateChildCategory(item.getChildren());
                }
            }

        }


    }

    private void generateFields(List<Category> categories) {
        if (dataBase == null)
            return;
        for (Category item : categories) {
            mDisposable.add(updateFields(item.getFields())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(() -> {
                                Log.d(TAG, "Fields  успешно добавлены");
                                generateValueFields(item.getFields());
                            },
                            throwable -> {
                                Log.d(TAG, throwable.getMessage());
                            }));

        }

    }

    private void generateValueFields(List<Field> fields) {
        if (dataBase == null)
            return;
        for (Field item : fields) {
            mDisposable.add(updateValueFields(item.getValues())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(() -> {
                                Log.d(TAG, "ValueFields  успешно добавлены");
                            },
                            throwable -> {
                                Log.d(TAG, throwable.getMessage());
                            }));

        }

    }

    private Completable updateChildCategories(final List<Category> childCategories) {
        return Completable.fromAction(() -> {
            dataBase.categoryDao().insert(childCategories);
        });
    }

    private Completable updateFields(final List<Field> fields) {
        return Completable.fromAction(() -> {
            dataBase.fieldDao().insert(fields);
        });
    }

    private Completable updateValueFields(final List<SearchValue> fields) {
        return Completable.fromAction(() -> {
            dataBase.fieldValueDao().insert(fields);
        });
    }
}
