package com.application.discoverfy;

import androidx.appcompat.app.AppCompatActivity;

// class for custom object which contains the users recently played songs
public class RecentlyPlayedListItem extends AppCompatActivity {

    // variables used to store the list item number, song name and artist name
    private String listNumber;
    private String songName;
    private String artistName;

    // constructor for the list item
    public RecentlyPlayedListItem(String listNumber, String songName, String artistName) {
        this.listNumber = listNumber;
        this.songName = songName;
        this.artistName = artistName;
    }

    // get the list item number
    public String getListNumber() {
        return listNumber;
    }

    // get the song name
    public String getSongName() {
        return songName;
    }

    // get the artists name
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