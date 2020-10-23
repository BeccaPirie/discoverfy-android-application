package com.application.discoverfy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecentlyPlayedAdapter extends RecyclerView.Adapter<RecentlyPlayedAdapter.RecentlyPlayedViewHolder> {

    private ArrayList<RecentlyPlayedListItem> recentlyPlayed;

    public static class RecentlyPlayedViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewOne;
        public TextView textViewTwo;
        public TextView textViewThree;

        public RecentlyPlayedViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewOne = itemView.findViewById(R.id.tv_rp_number);
            textViewTwo = itemView.findViewById(R.id.tv_rp_title);
            textViewThree = itemView.findViewById(R.id.tv_rp_artist);
        }
    }

    public RecentlyPlayedAdapter(ArrayList<RecentlyPlayedListItem> recentlyPlayedListItems) {
        recentlyPlayed = recentlyPlayedListItems;
    }

    @NonNull
    @Override
    public RecentlyPlayedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_recently_played_list_item, parent, false);
        RecentlyPlayedViewHolder rpViewHolder = new RecentlyPlayedViewHolder(view);
        return rpViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecentlyPlayedViewHolder holder, int position) {
        RecentlyPlayedListItem current = recentlyPlayed.get(position);

        holder.textViewOne.setText(current.getListNumber());
        holder.textViewTwo.setText(current.getSongTitle());
        holder.textViewThree.setText(current.getArtistName());
    }

    @Override
    public int getItemCount() {
        return recentlyPlayed.size();
    }
}
