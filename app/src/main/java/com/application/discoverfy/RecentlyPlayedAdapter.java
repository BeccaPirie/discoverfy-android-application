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

// class for the Adapter for the RecentlyPlayedActivity RecyclerView
public class RecentlyPlayedAdapter extends RecyclerView.Adapter<RecentlyPlayedAdapter.RecentlyPlayedViewHolder> {

    // variable to store the array list of items
    private static ArrayList<RecentlyPlayedListItem> recentlyPlayed;
    //private static ArrayList<RecentSongs> recentSongs;

    // extra for displaying song title in RecommendActivity
    public static final String EXTRA_SONG = "com.application.discoverfy.SONG";

    // ViewHolder
    public static class RecentlyPlayedViewHolder extends RecyclerView.ViewHolder {
        private final Context context;
        public TextView textViewOne;
        public TextView textViewTwo;
        public TextView textViewThree;
        public Button buttonOne;

        // constructor class
        public RecentlyPlayedViewHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();

            // assign TextViews and button
            textViewOne = itemView.findViewById(R.id.tv_rp_number);
            textViewTwo = itemView.findViewById(R.id.tv_rp_title);
            textViewThree = itemView.findViewById(R.id.tv_rp_artist);
            buttonOne = itemView.findViewById(R.id.btn_discover);

            // open RecommendActivity when item clicked and pass the data from that item to the activity
            buttonOne.setOnClickListener((View v) -> {
                int position = getAdapterPosition();
                RecentlyPlayedListItem current = recentlyPlayed.get(position);
                // RecentSongs current = recentSongs.get(position);
                final Intent recommend = new Intent(context, RecommendActivity.class);
                recommend.putExtra(EXTRA_SONG, current.getSongName());
                //recommend.putExtra(EXTRA_SONG, current.getName());
                //recommend.putExtra(EXTRA_SONG, current.getArtists());
                context.startActivity(recommend);
            });
        }
    }

    // put the data from the array list into the adapter
    public RecentlyPlayedAdapter(ArrayList<RecentlyPlayedListItem> recentlyPlayedListItems) {
        recentlyPlayed = recentlyPlayedListItems;
    }

    // pass the layout of the list item to the adapter
    @NonNull
    @Override
    public RecentlyPlayedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_recently_played_list_item, parent, false);
        RecentlyPlayedViewHolder rpViewHolder = new RecentlyPlayedViewHolder(view);
        return rpViewHolder;
    }

    // assign values to the Views
    @Override
    public void onBindViewHolder(@NonNull RecentlyPlayedViewHolder holder, int position) {
        // get the current item in the list
        RecentlyPlayedListItem current = recentlyPlayed.get(position);
        //RecentSongs current = recentSongs.get(position);

        // get the data from the current item in the array list and pass it to the View
        holder.textViewOne.setText(current.getListNumber());
        holder.textViewTwo.setText(current.getSongName());
        holder.textViewThree.setText(current.getArtistName());

    }

    // return the number of items in the array list
    @Override
    public int getItemCount() {
        return recentlyPlayed.size();
    }

}
