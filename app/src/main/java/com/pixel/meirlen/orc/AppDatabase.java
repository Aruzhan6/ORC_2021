package com.pixel.meirlen.orc;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.pixel.meirlen.orc.model.Category;
import com.pixel.meirlen.orc.model.Field;
import com.pixel.meirlen.orc.model.SearchValue;
import com.pixel.meirlen.orc.dao.CategoryDao;
import com.pixel.meirlen.orc.dao.FieldDao;
import com.pixel.meirlen.orc.dao.FieldValueDao;

@Database(entities = {Category.class,Field.class, SearchValue.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
   public abstract CategoryDao categoryDao();
   public abstract FieldDao fieldDao();
   public abstract FieldValueDao fieldValueDao();
}