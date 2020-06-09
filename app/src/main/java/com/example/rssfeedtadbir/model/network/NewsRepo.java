package com.example.rssfeedtadbir.model.network;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.rssfeedtadbir.model.data.JNews;
import com.example.rssfeedtadbir.model.data.JNewsDetail;
import com.example.rssfeedtadbir.model.data.JSearchResult;
import com.example.rssfeedtadbir.model.database.DetailDao;
import com.example.rssfeedtadbir.model.database.NewsDao;
import com.example.rssfeedtadbir.model.database.NewsDatabase;
import com.example.rssfeedtadbir.model.network.utils.ApiCallback;
import com.example.rssfeedtadbir.model.network.utils.Response;

import java.lang.ref.WeakReference;
import java.util.List;

import retrofit2.Call;


public class NewsRepo
{

    private static final String SEARCH_KEYWORD = "batman";

    public NewsRepo(Context context) {
        mNewsDao = NewsDatabase.createInstance(context).getMovieDao();
        mDetailDao = NewsDatabase.createInstance(context).getDetailDao();
        mWeakContext = new WeakReference<>(context);
    }

    private WeakReference<Context> mWeakContext;
    private NewsDao mNewsDao;
    private DetailDao mDetailDao;
    private OmdbApi mApi = ApiProvider.getInstance().getOmdbApi();

    private MutableLiveData<Response<List<JNews>>> searchObservable = new MutableLiveData<>();
    private MutableLiveData<Response<JNewsDetail>> movieDetailObservable = new MutableLiveData<>();

    public LiveData<Response<List<JNews>>> getSearchObservable() {
        return searchObservable;
    }

    public LiveData<Response<JNewsDetail>> getNewsDetailObservable() {
        return movieDetailObservable;
    }

    public void searchNews() {
        List<JNews> cachedNews = mNewsDao.getAll();
        if (cachedNews == null || cachedNews.isEmpty()) {
            fetchMoviesFromNetwork();
        } else {
            searchObservable.setValue(new Response.Builder<List<JNews>>().success(cachedNews));
            toast("Data received from database");
        }
    }

    public void getDetails(String id) {
       JNewsDetail cachedDetail = mDetailDao.getDetail(id);
        if (cachedDetail == null ) {
            fetchDetailsNewsFromNetwork(id);
        } else {
            movieDetailObservable.setValue(new Response.Builder<JNewsDetail>().success(cachedDetail));
            toast("Data received from database!!!");
        }
    }

    private void fetchDetailsNewsFromNetwork(String id) {
        Call<JNewsDetail> call = mApi.getNewsDetail(OmdbApi.API_KEY, id);
        movieDetailObservable.setValue(new Response.Builder<JNewsDetail>().loading());
        call.enqueue(new ApiCallback<JNewsDetail>() {
            @Override
            public void onSuccess(JNewsDetail data) {
                JNewsDetail movies = data;
                movieDetailObservable.setValue(new Response.Builder<JNewsDetail>().success(movies));
                mDetailDao.insert(movies);
                toast("Data received from network and cached!!!");
            }

            @Override
            public void onFailure(Throwable t) {
                movieDetailObservable.setValue(new Response.Builder<JNewsDetail>().error(t));
            }
        });
    }

    private void fetchMoviesFromNetwork() {
        Call<JSearchResult> call = mApi.searchNews(OmdbApi.API_KEY, SEARCH_KEYWORD);
        searchObservable.setValue(new Response.Builder<List<JNews>>().loading());
        call.enqueue(new ApiCallback<JSearchResult>() {
            @Override
            public void onSuccess(JSearchResult data) {
                List<JNews> movies = data.getSearch();
                searchObservable.setValue(new Response.Builder<List<JNews>>().success(movies));
                mNewsDao.insert(movies);
                toast("Data received from network and cached");
            }

            @Override
            public void onFailure(Throwable t) {
                searchObservable.setValue(new Response.Builder<List<JNews>>().error(t));
            }
        });
    }




    private void toast(String msg) {
        Context context = mWeakContext.get();
        if (context != null) {
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
        }
    }
}
