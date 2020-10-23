package com.application.discoverfy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class RecentlyPlayedListItem extends AppCompatActivity {

    private String listNumber;
    private String songTitle;
    private String artistName;

    public RecentlyPlayedListItem(String listNumber, String songTitle, String artistName) {
        this.listNumber = listNumber;
        this.songTitle = songTitle;
        this.artistName = artistName;
    }

    public String getListNumber() {
        return listNumber;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public String getArtistName() {
        return artistName;
    }
/*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recently_played_list_item);
    } */
}