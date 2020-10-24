package com.application.discoverfy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

// class for custom object which contains the users recently played songs
public class RecentlyPlayedListItem extends AppCompatActivity {

    // variables used to store the list item number, song title and artist name
    private String listNumber;
    private String songTitle;
    private String artistName;

    // constructor for the list item
    public RecentlyPlayedListItem(String listNumber, String songTitle, String artistName) {
        this.listNumber = listNumber;
        this.songTitle = songTitle;
        this.artistName = artistName;
    }

    // get the list item number
    public String getListNumber() {
        return listNumber;
    }

    // get the song title
    public String getSongTitle() {
        return songTitle;
    }

    // get the artist name
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