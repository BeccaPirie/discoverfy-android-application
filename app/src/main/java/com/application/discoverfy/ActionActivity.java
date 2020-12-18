package com.application.discoverfy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import static com.application.discoverfy.Adapters.RecommendAdapter.EXTRA_ARTIST;
import static com.application.discoverfy.Adapters.RecommendAdapter.EXTRA_SONG_TITLE;

public class ActionActivity extends AppCompatActivity implements View.OnClickListener{

    // tag
    private static final String tag = "ActionActivity";

    // variables for the song name and artist
    private String songName;
    private String artist;

    // shared preferences
    private SharedPreferences sharedPreferences;

    // on create method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(tag, "is in onCreate" );
        setContentView(R.layout.activity_action);

        // shared preferences
        sharedPreferences = this.getSharedPreferences(getString(R.string.spotify), MODE_PRIVATE);

        // variables to store the song title, the artist name and the album name
        TextView songTitle = findViewById(R.id.tv_song_title);
        TextView artistName = findViewById(R.id.tv_artist_name);

        // intent with song title and artists
        Intent displaySelected = getIntent();

        // set song title
        songName = displaySelected.getStringExtra(EXTRA_SONG_TITLE);
        songTitle.setText(songName);

        // set artist name
        artist = displaySelected.getStringExtra(EXTRA_ARTIST);
        artistName.setText(artist);

        // button to listen to the song on spotify
        Button btn_listen = findViewById(R.id.btn_listen_on_spotify);
        btn_listen.setOnClickListener(this);

        // button to share the recommendation by SMS
        Button btnSave = findViewById(R.id.btn_share_recommendation);
        btnSave.setOnClickListener(this);

        // button to go back to the main page
        Button btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(this);
    }

    // on click method
    @Override
    public void onClick(View v) {

        // if listen on Spotify button is clicked
        if(v.getId() == R.id.btn_listen_on_spotify) {
            // check if Spotify is installed
            PackageManager pm = getPackageManager();
            boolean isSpotifyInstalled;
            try {
                pm.getPackageInfo("com.spotify.music", 0);
                isSpotifyInstalled = true;
            } catch (PackageManager.NameNotFoundException e) {
                isSpotifyInstalled = false;
            }
            listenOnSpotify(isSpotifyInstalled);
        }

        // if share recommendation button is clicked
        if(v.getId() == R.id.btn_share_recommendation) {
            // message to send
            String message = getString(R.string.message, songName, artist,
                    sharedPreferences.getString("web_link", ""));
            shareRecommendation(message);
        }

        // if back button is clicked
        if (v.getId() == R.id.btn_back) {
            Intent back = new Intent(ActionActivity.this, MainActivity.class);
            startActivity(back);
        }
    }

    // listen on Spotify method
    private void listenOnSpotify(boolean isSpotifyInstalled) {
        // if Spotify is installed, open track in Spotify
        Uri uri;
        Intent spotify = new Intent(Intent.ACTION_VIEW);
        if (isSpotifyInstalled) {
            uri = Uri.parse(sharedPreferences.getString("uri", ""));
            spotify.setData(uri);
            spotify.putExtra(Intent.EXTRA_REFERRER, Uri.parse
                    ("android-app://" + getApplicationContext().getPackageName()));
        // otherwise, open web link
        } else {
            uri = Uri.parse(sharedPreferences.getString("web_link", ""));
            spotify.setData(uri);
        }
        startActivity(spotify);
    }

    // share recommendation method
    private void shareRecommendation(String message) {
        // intent to send message
        Intent share = new Intent(Intent.ACTION_SEND);
        // text mime type
        share.setType("text/plain");
        // add the message
        share.putExtra("sms_body", message);
        // start activity
        if(share.resolveActivity(getPackageManager()) != null) {
            startActivity(share);
        }
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