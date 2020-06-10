package com.example.rssfeedtadbir.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rssfeedtadbir.R;
import com.example.rssfeedtadbir.model.data.JNews;
import com.example.rssfeedtadbir.model.network.utils.ApiObserver;
import com.example.rssfeedtadbir.view.adapter.NewsAdapter;
import com.example.rssfeedtadbir.viewmodel.NewsViewModel;

import java.util.List;

public class NewsRssFragment extends Fragment {
    private RecyclerView list;

    private NewsViewModel mNewsViewModel;
    private NewsAdapter mAdapter = new NewsAdapter();
    private View rootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       // return DataBindingUtil.inflate(inflater, R.layout.news_rss, container, false).getRoot();
        rootView = inflater.inflate(R.layout.news_rss, container, false);
        initViews();
        initViewModel();

        if (savedInstanceState == null) search();

        return rootView;
    }

    private void initViews() {
        list = rootView.findViewById(R.id.news_list);
        list.setLayoutManager(new GridLayoutManager(getContext(), 2));
        list.setAdapter(mAdapter);
        mAdapter.setOnMovieClickListener(new NewsAdapter.OnMovieClickListener() {
            @Override
            public void onMovieClick(String imdbId, String img, String tittle) {
                Toast.makeText(getActivity(), "News Clicked ->" + imdbId, Toast.LENGTH_SHORT).show();
               /* Intent myIntent = new Intent(NewsRssActivity.this, FavoriteNewsFragment.class);
                myIntent.putExtra("id", imdbId);
               // NewsRssActivity.this.startActivity(myIntent);
                */
            }
        });
    }

    private void initViewModel() {
        mNewsViewModel = ViewModelProviders.of(this).get(NewsViewModel.class);
        mNewsViewModel.getSearchObservable()
                .observe(this, new ApiObserver<List<JNews>>() {
                    @Override
                    public void onLoading() {
                        //   mSwipeRefreshLayout.setRefreshing(true);
                    }

                    @Override
                    public void onSuccess(List<JNews> data) {
                        //   mSwipeRefreshLayout.setRefreshing(false);
                        mAdapter.addItems(data);
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        //   mSwipeRefreshLayout.setRefreshing(false);
                        Toast.makeText(getActivity(), "Failed to get news, try agian ...", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void search() {
        mNewsViewModel.searchNews();
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
       /* NewsRssBinding binding = DataBindingUtil.getBinding(getView());
        binding.setViewModel(new NewsViewModel());*/
    }
}
