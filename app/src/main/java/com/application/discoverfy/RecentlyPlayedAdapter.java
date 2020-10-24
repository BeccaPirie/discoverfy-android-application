package com.application.discoverfy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

// class for the Adapter for the RecentlyPlayedActivity RecyclerView
public class RecentlyPlayedAdapter extends RecyclerView.Adapter<RecentlyPlayedAdapter.RecentlyPlayedViewHolder> {

    // variable to store the array list of items
    private ArrayList<RecentlyPlayedListItem> recentlyPlayed;

    // ViewHolder
    public static class RecentlyPlayedViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewOne;
        public TextView textViewTwo;
        public TextView textViewThree;

        // constructor class
        public RecentlyPlayedViewHolder(@NonNull View itemView) {
            super(itemView);
            // assign Views
            textViewOne = itemView.findViewById(R.id.tv_rp_number);
            textViewTwo = itemView.findViewById(R.id.tv_rp_title);
            textViewThree = itemView.findViewById(R.id.tv_rp_artist);
        }
    }

    // put the data from the array list into the adapter
    public RecentlyPlayedAdapter(ArrayList<RecentlyPlayedListItem> recentlyPlayedListItems) {
        recentlyPlayed = recentlyPlayedListItems;
    }

    // pass the layout of list item to the adapter
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

        // get the data from the current item in the array list and pass it to the Views
        holder.textViewOne.setText(current.getListNumber());
        holder.textViewTwo.setText(current.getSongTitle());
        holder.textViewThree.setText(current.getArtistName());
    }

    // return the number of items in the array list
    @Override
    public int getItemCount() {
        return recentlyPlayed.size();
    }
}
