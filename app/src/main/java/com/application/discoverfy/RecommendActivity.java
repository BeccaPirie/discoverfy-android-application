package com.application.discoverfy;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import static com.application.discoverfy.RecentlyPlayedAdapter.EXTRA_SONG;

public class RecommendActivity extends AppCompatActivity {

    // tag
    private static final String tag = "Discoverfy";

    // key for current song name
    private static final String SONG = "SongName";
    // value for current song name
    private String songName;

    // variables that will store the RecyclerView, the Adapter and the LayoutManager
    private RecyclerView recommendRecyclerView;
    private RecyclerView.Adapter recommendAdapter;
    private RecyclerView.LayoutManager recommendLayoutManager;

    // on create method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(tag, "is in onCreate" );
        setContentView(R.layout.activity_recommend);

        // TextView for the name of the song selected
        TextView songTitle = findViewById(R.id.tv_song_title_recommend);

        // if saved instance state is not null, save the song name and display it in the title when activity is reinitialised
        if (savedInstanceState != null) {
            songName = savedInstanceState.getString(SONG);
            songTitle.setText(songName);
        }

        // get the TextView that displays the song title in the heading and set text to the selected song
        Intent recommend = getIntent();
        String song = recommend.getStringExtra(EXTRA_SONG);
        songTitle.setText(song);


        // create an array list to store the items in the RecyclerView
        // DATA WILL BE DOWNLOADED FROM WEB SERVICE
        ArrayList<RecommendListItem> recommendListItems = new ArrayList<>();
        recommendListItems.add(new RecommendListItem("1.", "Careless", "Ella Eyre"));
        recommendListItems.add(new RecommendListItem("2.", "Someone You Loved", "Lewis Capaldi"));
        recommendListItems.add(new RecommendListItem("3.", "Castle on the Hill", "Ed Sheeran"));
        recommendListItems.add(new RecommendListItem("4.", "Midnight Sky", "Miley Cyrus"));
        recommendListItems.add(new RecommendListItem("5.", "Save Myself", "Ashe"));
        recommendListItems.add(new RecommendListItem("6.", "All I Want", "Kodaline"));
        recommendListItems.add(new RecommendListItem("7.", "Dream", "Shawn Mendes"));
        recommendListItems.add(new RecommendListItem("8.", "All You're Dreaming Of", "Liam Gallagher"));
        recommendListItems.add(new RecommendListItem("9.", "Lonely", "Noah Cyrus"));
        recommendListItems.add(new RecommendListItem("10.", "hopeless", "Clinton Kane"));
        recommendListItems.add(new RecommendListItem("11.", "Electric Love", "BORNS"));
        recommendListItems.add(new RecommendListItem("12.", "Green Light", "Lorde"));
        recommendListItems.add(new RecommendListItem("13.", "Blinding Lights", "The Weeknd"));
        recommendListItems.add(new RecommendListItem("14.", "Golden", "Harry Styles"));
        recommendListItems.add(new RecommendListItem("15.", "heart", "flor"));
        recommendListItems.add(new RecommendListItem("16.", "Take Me Back Home", "Coasts"));
        recommendListItems.add(new RecommendListItem("17.", "superstars", "Christian French"));
        recommendListItems.add(new RecommendListItem("18.", "come out and play", "Billie Eilish"));
        recommendListItems.add(new RecommendListItem("19.", "Waves", "Dean Lewis"));
        recommendListItems.add(new RecommendListItem("20.", "Youngblood", "5 Seconds of Summer"));

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

    // on save instance state method
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        // replace value for key
        outState.putString(SONG, songName);
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