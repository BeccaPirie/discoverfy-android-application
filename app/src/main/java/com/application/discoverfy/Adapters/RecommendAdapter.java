package com.application.discoverfy.Adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.application.discoverfy.ActionActivity;
import com.application.discoverfy.Models.Recommendations;
import com.application.discoverfy.R;

import java.util.List;

// class for the Adapter for the RecommendActivity RecyclerView
public class RecommendAdapter extends RecyclerView.Adapter<RecommendAdapter.RecommendViewHolder> {

    // variable to store the array list of items
    private static List<Recommendations> recommend;

    // extras for displaying song details in ActionActivity
    public static final String EXTRA_SONG_TITLE = "com.application.discoverfy.SONG_TITLE";
    public static final String EXTRA_ARTIST = "com.application.discoverfy.SONG_ARTIST";

    // ViewHolder
    public static class RecommendViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final Context context;
        // variables to store TextViews in the list item
        public TextView songTitle;
        public TextView artist;
        public Button options;

        // ViewHolder constructor class
        public RecommendViewHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            // assign the Views
            songTitle = itemView.findViewById(R.id.tv_r_song_title);
            artist = itemView.findViewById(R.id.tv_r_artist);
            options = itemView.findViewById(R.id.btn_options);
            options.setOnClickListener(this);
        }

        // on click method
        @Override
        public void onClick(View v) {
            // get adapter position
            int position = getAdapterPosition();
            Recommendations current = recommend.get(position);

            SharedPreferences.Editor editor = context.getSharedPreferences(
                    context.getString(R.string.spotify), Context.MODE_PRIVATE).edit();
            editor.putString("uri", current.getUri());
            editor.putString("web_link", current.getWebLink());
            editor.apply();

            // put song title and artist as extras and start ActionActivity
            final Intent displaySelected = new Intent(context, ActionActivity.class);
            displaySelected.putExtra(EXTRA_SONG_TITLE, current.getName());
            displaySelected.putExtra(EXTRA_ARTIST, current.getArtists());
            context.startActivity(displaySelected);
        }
    }

    // store the information from the list in the adapter
    public RecommendAdapter(List<Recommendations> recommendListItems) {
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
        Recommendations current = recommend.get(position);

        // call the get method to display the information from the current item in the array list
        holder.songTitle.setText(current.getName());
        holder.artist.setText(current.getArtists());
    }

    // return the number of items in the list
    @Override
    public int getItemCount() {
        return recommend.size();
    }

    // set the recommendations
    public void setRecommendations(List<Recommendations> songs) {
        this.recommend = songs;
    }


}

