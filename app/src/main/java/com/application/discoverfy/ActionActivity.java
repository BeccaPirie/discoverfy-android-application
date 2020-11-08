package com.application.discoverfy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.application.discoverfy.RecommendAdapter.EXTRA_ALBUM;
import static com.application.discoverfy.RecommendAdapter.EXTRA_ARTIST;
import static com.application.discoverfy.RecommendAdapter.EXTRA_SONG_TITLE;

public class ActionActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String tag = "Discoverfy";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(tag, "is in onCreate" );
        setContentView(R.layout.activity_action);

        // variables to store the song title, the artist name and the album name
        TextView songTitle = (TextView) findViewById(R.id.tv_song_title);
        TextView artistName = (TextView) findViewById(R.id.tv_artist_name);
        TextView albumName = (TextView) findViewById(R.id.tv_album_name);

        // DATA WILL BE DOWNLOADED FROM WEB SERVICE
        Intent displaySelected = getIntent();

        // set song title
        String songName = displaySelected.getStringExtra(EXTRA_SONG_TITLE);
        songTitle.setText(songName);

        // set artist name
        String artist = displaySelected.getStringExtra(EXTRA_ARTIST);
        artistName.setText(artist);

        // set album name
        String album = displaySelected.getStringExtra(EXTRA_ALBUM);
        albumName.setText(album);

        // button to play the song on Spotify
        Button btnListen = findViewById(R.id.btn_listen_on_spotify);
        btnListen.setOnClickListener(this);

        // button to save the song on Spotify
        Button btnSave = findViewById(R.id.btn_save_to_library);
        btnSave.setOnClickListener(this);

        // button to add the song as the favourite
        Button btnFavourite = findViewById(R.id.btn_add_to_favourites);
        btnFavourite.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_listen_on_spotify) {
            // redirect the user to the Spotify app and play the current song
        }

        if(v.getId() == R.id.btn_save_to_library) {
            // save the song to the users Music Library on the Spotify App
        }

        if(v.getId() == R.id.btn_add_to_favourites) {
            // set the current song as the users favourite
        }
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