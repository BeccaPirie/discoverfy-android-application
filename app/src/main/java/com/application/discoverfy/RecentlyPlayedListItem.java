package com.application.discoverfy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

// class for custom object which contains the users recently played songs
public class RecentlyPlayedListItem extends AppCompatActivity {

    // variables used to store the list item number
    private String listNumber;

    // constructor for the list item
    public RecentlyPlayedListItem(String listNumber) {
        this.listNumber = listNumber;
    }

    // get the list item number
    public String getListNumber() {
        return listNumber;
    }

    /*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recently_played_list_item);
    } */
}