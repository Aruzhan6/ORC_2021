package com.example.meirlen.orc;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.meirlen.orc.rest.model.Category;
import com.example.meirlen.orc.room.CategoryDao;

@Database(entities = {Category.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
   public abstract CategoryDao categoryDao();
}