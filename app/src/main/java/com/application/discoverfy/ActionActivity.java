package com.application.discoverfy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ActionActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action);

        // variables to store the song title, the artist name and the album name
        TextView songTitle = (TextView) findViewById(R.id.tv_song_title);
        TextView artistName = (TextView) findViewById(R.id.tv_artist_name);
        TextView albumName = (TextView) findViewById(R.id.tv_album_name);

        // set the text
        // DATA WILL BE DOWNLOADED FROM WEB SERVICE


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
}