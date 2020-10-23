package com.application.discoverfy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class RecommendListItem extends AppCompatActivity {

    private String listNumberRecommend;
    private String songTitleRecommend;
    private String artistNameRecommend;

    public RecommendListItem(String listNumberRecommend, String songTitleRecommend, String artistNameRecommend) {
        this.listNumberRecommend = listNumberRecommend;
        this.songTitleRecommend = songTitleRecommend;
        this.artistNameRecommend = artistNameRecommend;
    }

    public String getListNumberRecommend() {
        return listNumberRecommend;
    }

    public String getSongTitleRecommend() {
        return songTitleRecommend;
    }

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