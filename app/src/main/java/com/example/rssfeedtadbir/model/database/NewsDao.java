package com.example.rssfeedtadbir.model.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.rssfeedtadbir.model.data.JNews;

import java.util.List;


@Dao
public interface NewsDao
{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> insert(List<JNews> venue);

    @Query("DELETE FROM JNews")
    void deleteAll();

    @Query("SELECT *FROM JNews")
    List<JNews> getAll();
}
