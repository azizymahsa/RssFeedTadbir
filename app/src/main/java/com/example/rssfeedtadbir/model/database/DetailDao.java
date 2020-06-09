package com.example.rssfeedtadbir.model.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.rssfeedtadbir.model.data.JNewsDetail;

@Dao
public interface DetailDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insert(JNewsDetail movieDetail);

    @Query("SELECT *FROM JNewsDetail WHERE imdbID=:id")
    JNewsDetail getDetail(String id);
}
