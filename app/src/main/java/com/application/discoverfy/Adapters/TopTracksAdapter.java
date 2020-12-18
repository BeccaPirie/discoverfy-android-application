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

import com.application.discoverfy.Models.TopTracks;
import com.application.discoverfy.R;
import com.application.discoverfy.RecommendActivity;

import java.util.List;

public class TopTracksAdapter extends RecyclerView.Adapter<TopTracksAdapter.TopTracksViewHolder> {

    // list for items
    private static List<TopTracks> topTracks;

    // ViewHolder
    public static class TopTracksViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final Context context;
        public TextView topTitle;
        public TextView topArtists;
        public Button topButton;

        // constructor
        public TopTracksViewHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            topTitle = itemView.findViewById(R.id.tv_top_title);
            topArtists = itemView.findViewById(R.id.tv_top_artists);
            topButton = itemView.findViewById(R.id.btn_top_recommend);
            topButton.setOnClickListener(this);
        }

        // on click listener
        @Override
        public void onClick(View v) {
            // get the adapter position
            int position = getAdapterPosition();
            TopTracks current = topTracks.get(position);

            // save the song id and name in shared preferences
            SharedPreferences.Editor editor = context.getSharedPreferences(context.getString(R.string.spotify), Context.MODE_PRIVATE).edit();
            editor.putString("id", current.getTopId());
            editor.putString("song", current.getTopName());
            editor.apply();

            // open the RecommendActivity class
            final Intent findRecommendations = new Intent(context, RecommendActivity.class);
            context.startActivity(findRecommendations);
        }
    }

    // put the data from the list into the adapter
    public TopTracksAdapter(List<TopTracks> topTracks) {
        TopTracksAdapter.topTracks = topTracks;
    }

    // onCreateViewHolder method
    @NonNull
    @Override
    public TopTracksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        // pass the layout of the list items to the adapter
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_top_tracks_list_item, parent, false);
        TopTracksViewHolder topTracksViewHolder = new TopTracksViewHolder(view);
        return topTracksViewHolder;
    }

    // onBindViewHolder method
    @Override
    public void onBindViewHolder(@NonNull TopTracksViewHolder viewHolder, int position) {
        // assign values to the views
        TopTracks current = topTracks.get(position);
        viewHolder.topTitle.setText(current.getTopName());
        viewHolder.topArtists.setText(current.getTopArtists());
    }

    // get item count
    @Override
    public int getItemCount() {
        return topTracks.size();
    }

    // set top tracks
    public void setTopTracks(List<TopTracks> tracks) {
        topTracks = tracks;
    }
}
