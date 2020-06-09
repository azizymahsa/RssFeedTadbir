package com.example.rssfeedtadbir.model.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.rssfeedtadbir.model.data.JNews;
import com.example.rssfeedtadbir.model.data.JNewsDetail;


@Database(entities = {JNews.class, JNewsDetail.class}, version = 1, exportSchema = false)
public abstract class NewsDatabase extends RoomDatabase {

    public static NewsDatabase createInstance(Context context) {
        return Room.databaseBuilder(context.getApplicationContext(),
                NewsDatabase.class,
                "data")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }

    public abstract NewsDao getMovieDao();
    public abstract DetailDao getDetailDao();
}
