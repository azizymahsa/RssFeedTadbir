package com.example.rssfeedtadbir.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rssfeedtadbir.R;
import com.example.rssfeedtadbir.databinding.NewsRssBinding;
import com.example.rssfeedtadbir.view.adapter.NewsAdapter;
import com.example.rssfeedtadbir.viewmodel.NewsViewModel;

public class NewsRssFragment extends Fragment {
    private RecyclerView list;

    private NewsViewModel mNewsViewModel;
    private NewsAdapter mAdapter = new NewsAdapter();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return DataBindingUtil.inflate(inflater, R.layout.news_rss, container, false).getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        NewsRssBinding binding = DataBindingUtil.getBinding(getView());
        binding.setViewModel(new NewsViewModel());
    }
}
