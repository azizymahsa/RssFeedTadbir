package com.example.rssfeedtadbir.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.rssfeedtadbir.R;
import com.example.rssfeedtadbir.databinding.FavoriteNewsBinding;
import com.example.rssfeedtadbir.viewmodel.FavoriteNewsViewModel;

public class FavoriteNewsFragment extends Fragment
{
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        return DataBindingUtil.inflate(inflater, R.layout.favorite_news, container, false).getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        FavoriteNewsBinding binding = DataBindingUtil.getBinding(getView());
        binding.setViewModel(new FavoriteNewsViewModel());
    }
}
