package com.application.discoverfy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class RecentlyPlayedActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String tag = "Discoverfy";

    // variables that will store the RecyclerView, the Adapter and the LayoutManager
    private RecyclerView recentRecyclerView;
    private RecentlyPlayedAdapter recentAdapter;
    private RecyclerView.LayoutManager recentLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(tag, "is in onCreate" );
        setContentView(R.layout.activity_recently_played);

        // create an array list to store the items in the RecyclerView
        // DATA WILL BE DOWNLOADED FROM WEB SERVICE
        ArrayList<RecentlyPlayedListItem> recentlyPlayedListItems = new ArrayList<>();
        recentlyPlayedListItems.add(new RecentlyPlayedListItem("1."));
        recentlyPlayedListItems.add(new RecentlyPlayedListItem("2."));
        recentlyPlayedListItems.add(new RecentlyPlayedListItem("3."));
        recentlyPlayedListItems.add(new RecentlyPlayedListItem("4."));
        recentlyPlayedListItems.add(new RecentlyPlayedListItem("5."));
        recentlyPlayedListItems.add(new RecentlyPlayedListItem("6."));
        recentlyPlayedListItems.add(new RecentlyPlayedListItem("7."));
        recentlyPlayedListItems.add(new RecentlyPlayedListItem("8."));
        recentlyPlayedListItems.add(new RecentlyPlayedListItem("9."));
        recentlyPlayedListItems.add(new RecentlyPlayedListItem("10."));
        recentlyPlayedListItems.add(new RecentlyPlayedListItem("11."));
        recentlyPlayedListItems.add(new RecentlyPlayedListItem("12."));
        recentlyPlayedListItems.add(new RecentlyPlayedListItem("13."));
        recentlyPlayedListItems.add(new RecentlyPlayedListItem("14."));
        recentlyPlayedListItems.add(new RecentlyPlayedListItem("15."));
        recentlyPlayedListItems.add(new RecentlyPlayedListItem("16."));
        recentlyPlayedListItems.add(new RecentlyPlayedListItem("17."));
        recentlyPlayedListItems.add(new RecentlyPlayedListItem("18."));
        recentlyPlayedListItems.add(new RecentlyPlayedListItem("19."));
        recentlyPlayedListItems.add(new RecentlyPlayedListItem("20."));


        // initialise the RecyclerView
        recentRecyclerView = findViewById(R.id.rv_recently_played);
        // recommendRecyclerView will not change in size
        recentRecyclerView.setHasFixedSize(true);
        // set the LayoutManager
        recentLayoutManager = new LinearLayoutManager(this);
        // create a new Adapter and pass in the array list of recently played songs
        recentAdapter = new RecentlyPlayedAdapter(recentlyPlayedListItems);

        // pass the LayoutManager to the RecyclerView
        recentRecyclerView.setLayoutManager(recentLayoutManager);
        // pass the Adapter to the RecyclerView
        recentRecyclerView.setAdapter(recentAdapter);

        // button to view Favourite recommendation
        Button viewFavourite = findViewById(R.id.btn_view_favourite);
        viewFavourite.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        // will open the ActionActivity and display the information of the favourite song
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(tag, "is in onResume");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(tag, "is in onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(tag, "is in onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(tag, "is in onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(tag, "is in onPause");
    }
}