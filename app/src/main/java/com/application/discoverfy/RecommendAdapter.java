package com.application.discoverfy;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
        final Context context;
        // variables to store TextViews in the list item
        public TextView textView4;
        public TextView textView5;
        public TextView textView6;
        public Button button2;

        // ViewHolder constructor class
        public RecommendViewHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            // assign the Views
            textView4 = itemView.findViewById(R.id.tv_r_number);
            textView5 = itemView.findViewById(R.id.tv_r_song_title);
            textView6 = itemView.findViewById(R.id.tv_r_artist);
            button2 = itemView.findViewById(R.id.btn_options);

            // open RecommendActivity when item clicked
            button2.setOnClickListener((View v) -> {
                final Intent intent = new Intent(context, ActionActivity.class);
                context.startActivity(intent);
            });
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

        // call the get method to display the information from the current item in the array list
        holder.textView4.setText(current.getListNumberRecommend());
    }

    // return the number of items in the array list
    @Override
    public int getItemCount() {
        return recommend.size();
    }
}

