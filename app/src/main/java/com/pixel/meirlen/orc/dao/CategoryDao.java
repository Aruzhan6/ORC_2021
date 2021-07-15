package com.pixel.meirlen.orc.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.pixel.meirlen.orc.model.Category;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface CategoryDao {

   @Query("SELECT * FROM category WHERE categoryParentId = 0")
   Flowable<List<Category>> getAll();


   @Query("SELECT * FROM Category WHERE categoryParentId = :id")
   Flowable<List<Category>>  getById(String id);

   @Insert(onConflict = OnConflictStrategy.REPLACE)
   void insert(List<Category> categories);
 
   @Update
   void update(Category category);
 
   @Delete
   void delete(Category category);

   @Query("DELETE FROM category")
   void deleteAll();
 
}