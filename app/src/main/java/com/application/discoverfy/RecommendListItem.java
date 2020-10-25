package com.application.discoverfy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

// class for custom object which contains the recommendations
public class RecommendListItem extends AppCompatActivity {

    // variables used to store the list item number
    private String listNumberRecommend;

    // constructor for the list item
    public RecommendListItem(String listNumberRecommend) {
        this.listNumberRecommend = listNumberRecommend;
    }

    // get the list item number
    public String getListNumberRecommend() {
        return listNumberRecommend;
    }

    /*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend_list_item);
    }
     */
}