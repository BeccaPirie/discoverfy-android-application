package com.application.discoverfy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

// class for custom object which contains the recommendations
public class RecommendListItem extends AppCompatActivity {

    // variables used to store the list item number, song title and artist name
    private String listNumberRecommend;
    private String songTitleRecommend;
    private String artistNameRecommend;

    // constructor for the list item
    public RecommendListItem(String listNumberRecommend, String songTitleRecommend, String artistNameRecommend) {
        this.listNumberRecommend = listNumberRecommend;
        this.songTitleRecommend = songTitleRecommend;
        this.artistNameRecommend = artistNameRecommend;
    }

    // get the list item number
    public String getListNumberRecommend() {
        return listNumberRecommend;
    }

    // get the song title
    public String getSongTitleRecommend() {
        return songTitleRecommend;
    }

    // get the artist name
    public String getArtistNameRecommend() {
        return artistNameRecommend;
    }
    /*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend_list_item);
    }

     */
}