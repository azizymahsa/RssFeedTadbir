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
import com.example.rssfeedtadbir.databinding.Page1Binding;
import com.example.rssfeedtadbir.viewmodel.NewsRssViewModel;

public class NewsRssFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return DataBindingUtil.inflate(inflater, R.layout.page1, container, false).getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Page1Binding binding = DataBindingUtil.getBinding(getView());
        binding.setViewModel(new NewsRssViewModel());
    }
}
