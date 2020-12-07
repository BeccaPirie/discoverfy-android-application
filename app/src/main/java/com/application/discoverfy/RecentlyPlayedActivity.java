package com.application.discoverfy;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.application.discoverfy.Connectors.RecentSongsService;
import com.application.discoverfy.Models.RecentSongs;

import java.util.ArrayList;

import static com.application.discoverfy.LoginActivity.SPOTIFY;

public class RecentlyPlayedActivity extends AppCompatActivity {

    private static final String tag = "Discoverfy";

    // variables that will store the RecyclerView, the Adapter and the LayoutManager
    private RecyclerView recentRecyclerView;
    private RecentlyPlayedAdapter recentAdapter;
    private RecyclerView.LayoutManager recentLayoutManager;
    private RecentSongs song;

    private RecentSongsService recentSongService;
    private ArrayList<RecentSongs> recentlyPlayedSongs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(tag, "is in onCreate" );
        setContentView(R.layout.activity_recently_played);

        TextView displayUsername = findViewById(R.id.tv_username);

        SharedPreferences sharedPreferences = this.getSharedPreferences(SPOTIFY, 0);
        displayUsername.setText(sharedPreferences.getString("user_id", "no user"));

        // create an array list to store the items in the RecyclerView
        // DATA WILL BE DOWNLOADED FROM WEB SERVICE
        
        //recentlyPlayedSongs = new ArrayList<RecentSongs>();

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

        // create a new Adapter and pass in the array list of recently played songs
        recentAdapter = new RecentlyPlayedAdapter(recentlyPlayedListItems);
        //recentAdapter = new RecentlyPlayedAdapter(    , getApplicationContext());

        // set the LayoutManager
        recentLayoutManager = new LinearLayoutManager(this);

        // recentLayoutManager = new LinearLayoutManager(getApplicationContext());
        // pass the LayoutManager to the RecyclerView
        recentRecyclerView.setLayoutManager(recentLayoutManager);

        // pass the Adapter to the RecyclerView
        recentRecyclerView.setAdapter(recentAdapter);

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