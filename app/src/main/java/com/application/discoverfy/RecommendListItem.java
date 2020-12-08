package com.application.discoverfy;

import androidx.appcompat.app.AppCompatActivity;

// class for custom object which contains the recommendations
public class RecommendListItem extends AppCompatActivity {

    // variables used to store the list item number
    private String listNumberRecommend;
    private String songNameRecommend;
    private String artistNameRecommend;

    // constructor for the list item
    public RecommendListItem(String listNumberRecommend, String songNameRecommend, String artistNameRecommend) {
        this.listNumberRecommend = listNumberRecommend;
        this.songNameRecommend = songNameRecommend;
        this.artistNameRecommend = artistNameRecommend;
    }

    // get the list item number
    public String getListNumberRecommend() {
        return listNumberRecommend;
    }

    public String getSongNameRecommend()  {
        return songNameRecommend;
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