package com.example.rssfeedtadbir.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.rssfeedtadbir.R;
import com.example.rssfeedtadbir.model.data.JNews;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;



public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsHolder> {

    private OnMovieClickListener mClickListener = null;
    private ArrayList<JNews> mItems = new ArrayList<>();

    @NonNull
    @Override
    public NewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_news, parent, false);
        return new NewsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsHolder holder, int position) {
        holder.bind(mItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void addItems(List<JNews> results) {
        int count = getItemCount();
        mItems.addAll(results);
        notifyItemRangeInserted(count, results.size());
    }

    public void setOnMovieClickListener(OnMovieClickListener clickListener) {
        mClickListener = clickListener;
    }
    public void clear() {
        mItems.clear();
        notifyDataSetChanged();
    }
    class NewsHolder extends RecyclerView.ViewHolder {

        private ImageView movieImgView;
        private TextView movieTextView;

        public NewsHolder(@NonNull View itemView) {
            super(itemView);
            movieTextView = itemView.findViewById(R.id.news_text);
            movieImgView = itemView.findViewById(R.id.news_img);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mClickListener != null) {
                        mClickListener.onMovieClick(mItems.get(getAdapterPosition()).getImdbID(),mItems.get(getAdapterPosition()).getPoster(),String.format(Locale.US, "%s (%s)", mItems.get(getAdapterPosition()).getTitle(), mItems.get(getAdapterPosition()).getYear()));
                    }
                }
            });
        }

        public void bind(JNews data) {
            String title = String.format(Locale.US, "%s (%s)", data.getTitle(), data.getYear());
            movieTextView.setText(title);
            movieImgView.setImageResource(0);
            Glide.with(itemView.getContext()).load(data.getPoster()).into(movieImgView);
        }
    }

    public interface OnMovieClickListener {
        public void onMovieClick(String imdbId, String img, String tittle);
    }
}
