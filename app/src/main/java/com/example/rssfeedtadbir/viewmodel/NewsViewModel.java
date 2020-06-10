package com.example.rssfeedtadbir.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.rssfeedtadbir.model.data.JNews;
import com.example.rssfeedtadbir.model.data.JNewsDetail;
import com.example.rssfeedtadbir.model.network.NewsRepo;
import com.example.rssfeedtadbir.model.network.utils.Response;

import java.util.List;

public class NewsViewModel extends AndroidViewModel
{

    private NewsRepo repo = new NewsRepo(getApplication());

    public NewsViewModel(@NonNull Application application) {
        super(application);
    }



    public LiveData<Response<List<JNews>>> getSearchObservable() {
        return repo.getSearchObservable();
    }

    public void searchNews() {
        repo.searchNews();
    }
    public void getNewsDetail(String id) {
        repo.getDetails(id);
    }

    public LiveData<Response<JNewsDetail>> getNewsDetailObservable() {
        return repo.getNewsDetailObservable();
    }


}
