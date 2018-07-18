package com.example.meirlen.orc.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.meirlen.orc.model.SearchValue;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface FieldValueDao {

    @Query("SELECT * FROM SearchValue")
    Flowable<List<SearchValue>> getAll();


    @Query("SELECT * FROM SearchValue WHERE valueFieldId = :id")
    Flowable<List<SearchValue>> getById(String id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<SearchValue> SearchValues);

    @Update
    void update(SearchValue category);

    @Delete
    void delete(SearchValue category);

    @Query("DELETE FROM SearchValue")
    void deleteAll();

}