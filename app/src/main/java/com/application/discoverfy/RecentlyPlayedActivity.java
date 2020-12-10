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

    // tag
    private static final String tag = "Discoverfy";

    // variables that will store the RecyclerView, the Adapter and the LayoutManager
    private RecyclerView recentRecyclerView;
    private RecentlyPlayedAdapter recentAdapter;
    private RecyclerView.LayoutManager recentLayoutManager;

    // model, connector and list for recently played songs from API
    private RecentSongs song;
    private RecentSongsService recentSongService;
    private ArrayList<RecentSongs> recentlyPlayedSongs;

    // on create method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(tag, "is in onCreate" );
        setContentView(R.layout.activity_recently_played);

        // TextView to display the users Spotify user ID
        TextView displayUsername = findViewById(R.id.tv_username);

        // get the user ID from shared preferences and display in the TextView
        SharedPreferences sharedPreferences = this.getSharedPreferences(SPOTIFY, 0);
        displayUsername.setText(sharedPreferences.getString("user_id", "no user"));

        // create an array list to store the items in the RecyclerView
        // DATA WILL BE DOWNLOADED FROM WEB SERVICE
        
        // recentlyPlayedSongs = new ArrayList<RecentSongs>();

        // placeholder data
        ArrayList<RecentlyPlayedListItem> recentlyPlayedListItems = new ArrayList<>();
        recentlyPlayedListItems.add(new RecentlyPlayedListItem("1.", "Wonder", "Shawn Mendes"));
        recentlyPlayedListItems.add(new RecentlyPlayedListItem("2.", "No Time To Die", "Billie Eilish"));
        recentlyPlayedListItems.add(new RecentlyPlayedListItem("3.", "What A Man Gotta Do", "Jonas Brothers"));
        recentlyPlayedListItems.add(new RecentlyPlayedListItem("4.", "Black And White", "Niall Horan"));
        recentlyPlayedListItems.add(new RecentlyPlayedListItem("5.", "Before You Go", "Lewis Capaldi"));
        recentlyPlayedListItems.add(new RecentlyPlayedListItem("6.", "Falling", "Harry Styles"));
        recentlyPlayedListItems.add(new RecentlyPlayedListItem("7.", "Wings", "Birdy"));
        recentlyPlayedListItems.add(new RecentlyPlayedListItem("8.", "Ghosts", "BANNERS"));
        recentlyPlayedListItems.add(new RecentlyPlayedListItem("9.", "Crash My Car", "COIN"));
        recentlyPlayedListItems.add(new RecentlyPlayedListItem("10.", "Angel", "FINNEAS"));
        recentlyPlayedListItems.add(new RecentlyPlayedListItem("11.", "New Me", "Ella Eyre"));
        recentlyPlayedListItems.add(new RecentlyPlayedListItem("12.", "The Joke", "Brandi Carlile"));
        recentlyPlayedListItems.add(new RecentlyPlayedListItem("13.", "My Kind of Love", "Emeli Sande"));
        recentlyPlayedListItems.add(new RecentlyPlayedListItem("14.", "feel something", "Bea Miller"));
        recentlyPlayedListItems.add(new RecentlyPlayedListItem("15.", "Hard Sometimes", "Ruel"));
        recentlyPlayedListItems.add(new RecentlyPlayedListItem("16.", "Fix You", "Coldplay"));
        recentlyPlayedListItems.add(new RecentlyPlayedListItem("17.", "New Map", "M83"));
        recentlyPlayedListItems.add(new RecentlyPlayedListItem("18.", "Stranger", "Vistas"));
        recentlyPlayedListItems.add(new RecentlyPlayedListItem("19.", "Tongue Tied", "Grouplove"));
        recentlyPlayedListItems.add(new RecentlyPlayedListItem("20.", "Teenage Blues", "Vistas"));


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

    // on resume method
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(tag, "is in onResume");
    }

    // on start method
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(tag, "is in onStart");
    }

    // on stop method
    @Override
    protected void onStop() {
        super.onStop();
        Log.d(tag, "is in onStop");
    }

    // on destroy method
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(tag, "is in onDestroy");
    }

    // on pause method
    @Override
    protected void onPause() {
        super.onPause();
        Log.d(tag, "is in onPause");
    }
}