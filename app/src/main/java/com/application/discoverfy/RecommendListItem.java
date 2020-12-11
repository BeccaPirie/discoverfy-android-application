package com.application.discoverfy;

import androidx.appcompat.app.AppCompatActivity;

// class for custom object which contains the recommendations
public class RecommendListItem extends AppCompatActivity {

    // variables used to store the song name and artist name
    private String songNameRecommend;
    private String artistNameRecommend;

    // constructor for the list item
    public RecommendListItem(String songNameRecommend, String artistNameRecommend) {
        this.songNameRecommend = songNameRecommend;
        this.artistNameRecommend = artistNameRecommend;
    }

    // get the song name
    public String getSongNameRecommend()  {
        return songNameRecommend;
    }

    // get the artists name
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