package com.application.discoverfy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // display the users Spotify display name
        TextView welcome = findViewById(R.id.tv_username);
        SharedPreferences sharedPreferences = this.getSharedPreferences(getString(R.string.spotify), MODE_PRIVATE);
        welcome.setText(sharedPreferences.getString("display_name", "No user"));

        // recently played tracks button
        Button recentTracks = findViewById(R.id.btn_recent_tracks);
        recentTracks.setOnClickListener(this);

        // top tracks button
        Button topTracks = findViewById(R.id.btn_top_tracks);
        topTracks.setOnClickListener(this);
    }

    // on click method
    @Override
    public void onClick(View v) {
        // if recently played tracks button is clicked
        if(v.getId() == R.id.btn_recent_tracks) {
            // open RecentlyPlayedActivity
            Intent recentTracks = new Intent(MainActivity.this,
                    RecentlyPlayedActivity.class);
            startActivity(recentTracks);
        }

        // if top tracks button is clicked
        if(v.getId() == R.id.btn_top_tracks) {
            // open TopTracksActivity
            Intent topTracks = new Intent(MainActivity.this,
                    TopTracksActivity.class);
            startActivity(topTracks);
        }
    }
}