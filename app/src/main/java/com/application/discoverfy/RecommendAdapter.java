package com.application.discoverfy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

// class for the Adapter for the RecommendActivity RecyclerView
public class RecommendAdapter extends RecyclerView.Adapter<RecommendAdapter.RecommendViewHolder> {

    // variable to store the array list of items
    private ArrayList<RecommendListItem> recommend;

    // ViewHolder
    public static class RecommendViewHolder extends RecyclerView.ViewHolder {
        // variables to store TextViews in the list item
        public TextView textView4;
        public TextView textView5;
        public TextView textView6;

        // ViewHolder constructor class
        public RecommendViewHolder(@NonNull View itemView) {
            super(itemView);
            // assign the Views
            textView4 = itemView.findViewById(R.id.tv_r_number);
            textView5 = itemView.findViewById(R.id.tv_r_song_title);
            textView6 = itemView.findViewById(R.id.tv_r_artist);
        }
    }

    // store the information from the array list in the adapter
    public RecommendAdapter(ArrayList<RecommendListItem> recommendListItems) {
        recommend = recommendListItems;
    }

    // pass the layout of the list item to the adapter
    @NonNull
    @Override
    public RecommendViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_recommend_list_item, parent, false);
        RecommendAdapter.RecommendViewHolder rViewHolder = new RecommendAdapter.RecommendViewHolder(view);
        return rViewHolder;
    }

    // assign values to the views
    @Override
    public void onBindViewHolder(@NonNull RecommendViewHolder holder, int position) {
        // get the current item in the list
        RecommendListItem current = recommend.get(position);

        // call the get methods to display the information from the current item in the array list
        holder.textView4.setText(current.getListNumberRecommend());
        holder.textView5.setText(current.getSongTitleRecommend());
        holder.textView6.setText(current.getArtistNameRecommend());
    }

    // return the number of items in the array list
    @Override
    public int getItemCount() {
        return recommend.size();
    }
}

