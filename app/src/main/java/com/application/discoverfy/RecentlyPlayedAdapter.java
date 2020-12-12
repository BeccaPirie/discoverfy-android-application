package com.application.discoverfy;

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

import com.application.discoverfy.Models.RecentSongs;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

// class for the Adapter for the RecentlyPlayedActivity RecyclerView
public class RecentlyPlayedAdapter extends RecyclerView.Adapter<RecentlyPlayedAdapter.RecentlyPlayedViewHolder> {

    // variable to store the array list of items
    private static List<RecentSongs> recentSongs;

    // ViewHolder
    public static class RecentlyPlayedViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final Context context;
        public TextView title;
        public TextView artist;
        public Button recommend;

        // constructor class
        public RecentlyPlayedViewHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();

            // assign TextViews and button
            title = itemView.findViewById(R.id.tv_rp_title);
            artist = itemView.findViewById(R.id.tv_rp_artist);
            recommend = itemView.findViewById(R.id.btn_discover);

            // set on click listener for the button
            recommend.setOnClickListener(this);
        }

        // on click method
        @Override
        public void onClick(View v) {
            // get adapter position
            int position = getAdapterPosition();
            //RecentlyPlayedListItem current = recentSongs.get(position);
             RecentSongs current = recentSongs.get(position);

            // save id and name to shared preferences
            SharedPreferences.Editor editor = context.getSharedPreferences(context.getString(R.string.shared_pref_file), MODE_PRIVATE).edit();
            //editor.putString("recent_song", current.getSongName());
            editor.putString("recent_id", current.getId());
            editor.putString("recent_song", current.getName());
            editor.putString("recent_artists", current.getArtists());
            editor.apply();

            // open RecommendActivity
            final Intent recommend = new Intent(context, RecommendActivity.class);
            context.startActivity(recommend);
        }
    }

    /*
    // put the data from the array list into the adapter
    public RecentlyPlayedAdapter(ArrayList<RecentlyPlayedListItem> recentSongs) {
        RecentlyPlayedAdapter.recentSongs = recentSongs;
    }

     */

    // put the data from the array list into the adapter
    public RecentlyPlayedAdapter(List<RecentSongs> recentSongs) {
        RecentlyPlayedAdapter.recentSongs = recentSongs;
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
        RecentSongs current = recentSongs.get(position);

        // get the data from the current item in the array list and pass it to the View
        holder.title.setText(current.getName());
        holder.artist.setText(current.getArtists());

    }

    // return the number of items in the array list
    @Override
    public int getItemCount() {
        return recentSongs.size();
    }

    public void setRecentSongs(List<RecentSongs> songs) {
        recentSongs = songs;
    }
}
