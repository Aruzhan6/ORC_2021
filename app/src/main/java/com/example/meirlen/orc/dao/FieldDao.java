package com.example.meirlen.orc.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.meirlen.orc.model.Field;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface FieldDao {

    @Query("SELECT * FROM field")
    Flowable<List<Field>> getAll();


    @Query("SELECT * FROM Field WHERE fieldCategoryId = :id")
    Flowable<List<Field>> getById(String id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Field> fields);

    @Update
    void update(Field category);

    @Delete
    void delete(Field category);

    @Query("DELETE FROM field")
    void deleteAll();

}