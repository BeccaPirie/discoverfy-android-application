package com.application.discoverfy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecommendAdapter extends RecyclerView.Adapter<RecommendAdapter.RecommendViewHolder> {

    private ArrayList<RecommendListItem> recommend;

    public static class RecommendViewHolder extends RecyclerView.ViewHolder {
        public TextView textView4;
        public TextView textView5;
        public TextView textView6;

        public RecommendViewHolder(@NonNull View itemView) {
            super(itemView);
            textView4 = itemView.findViewById(R.id.tv_r_number);
            textView5 = itemView.findViewById(R.id.tv_r_song_title);
            textView6 = itemView.findViewById(R.id.tv_r_artist);
        }
    }

    public RecommendAdapter(ArrayList<RecommendListItem> recommendListItems) {
        recommend = recommendListItems;
    }

    @NonNull
    @Override
    public RecommendViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_recommend_list_item, parent, false);
        RecommendAdapter.RecommendViewHolder rViewHolder = new RecommendAdapter.RecommendViewHolder(view);
        return rViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecommendViewHolder holder, int position) {
        RecommendListItem current = recommend.get(position);

        holder.textView4.setText(current.getListNumberRecommend());
        holder.textView5.setText(current.getSongTitleRecommend());
        holder.textView6.setText(current.getArtistNameRecommend());
    }

    @Override
    public int getItemCount() {
        return recommend.size();
    }




}

