package com.application.discoverfy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

import static com.application.discoverfy.RecentlyPlayedAdapter.EXTRA_SONG;

public class RecommendActivity extends AppCompatActivity {

    private static final String tag = "Discoverfy";

    // variables that will store the RecyclerView, the Adapter and the LayoutManager
    private RecyclerView recommendRecyclerView;
    private RecyclerView.Adapter recommendAdapter;
    private RecyclerView.LayoutManager recommendLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(tag, "is in onCreate" );
        setContentView(R.layout.activity_recommend);

        // get the TextView that displays the song title in the heading and set text to the selected song
        Intent recommend = getIntent();
        String song = recommend.getStringExtra(EXTRA_SONG);
        TextView songTitle = (TextView) findViewById(R.id.tv_song_title_recommend);
        songTitle.setText(song);

        // create an array list to store the items in the RecyclerView
        // DATA WILL BE DOWNLOADED FROM WEB SERVICE
        ArrayList<RecommendListItem> recommendListItems = new ArrayList<>();
        recommendListItems.add(new RecommendListItem("1."));
        recommendListItems.add(new RecommendListItem("2."));
        recommendListItems.add(new RecommendListItem("3."));
        recommendListItems.add(new RecommendListItem("4."));
        recommendListItems.add(new RecommendListItem("5."));
        recommendListItems.add(new RecommendListItem("6."));
        recommendListItems.add(new RecommendListItem("7."));
        recommendListItems.add(new RecommendListItem("8."));
        recommendListItems.add(new RecommendListItem("9."));
        recommendListItems.add(new RecommendListItem("10."));
        recommendListItems.add(new RecommendListItem("11."));
        recommendListItems.add(new RecommendListItem("12."));
        recommendListItems.add(new RecommendListItem("13."));
        recommendListItems.add(new RecommendListItem("14."));
        recommendListItems.add(new RecommendListItem("15."));
        recommendListItems.add(new RecommendListItem("16."));
        recommendListItems.add(new RecommendListItem("17."));
        recommendListItems.add(new RecommendListItem("18."));
        recommendListItems.add(new RecommendListItem("19."));
        recommendListItems.add(new RecommendListItem("20."));

        // initialise the RecyclerView
        recommendRecyclerView = findViewById(R.id.rv_recommended_songs);
        // recommendRecyclerView will not change in size
        recommendRecyclerView.setHasFixedSize(true);
        // set the LayoutManager
        recommendLayoutManager = new LinearLayoutManager(this);
        // create a  Adapter and pass in the array list of recommendations
        recommendAdapter = new RecommendAdapter(recommendListItems);

        // pass the LayoutManager to the RecyclerView
        recommendRecyclerView.setLayoutManager(recommendLayoutManager);
        // pass the Adapter to the RecyclerView
        recommendRecyclerView.setAdapter(recommendAdapter);
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