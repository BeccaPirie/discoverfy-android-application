package com.application.discoverfy;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import static com.application.discoverfy.RecommendAdapter.EXTRA_ARTIST;
import static com.application.discoverfy.RecommendAdapter.EXTRA_SONG_TITLE;

public class ActionActivity extends AppCompatActivity implements View.OnClickListener{

    // tag
    private static final String tag = "ActionActivity";

    // variables for the song name and artist
    private String songName;
    private String artist;

    // on create method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(tag, "is in onCreate" );
        setContentView(R.layout.activity_action);

        // variables to store the song title, the artist name and the album name
        TextView songTitle = findViewById(R.id.tv_song_title);
        TextView artistName = findViewById(R.id.tv_artist_name);

        Intent displaySelected = getIntent();

        // set song title
        songName = displaySelected.getStringExtra(EXTRA_SONG_TITLE);
        songTitle.setText(songName);

        // set artist name
        artist = displaySelected.getStringExtra(EXTRA_ARTIST);
        artistName.setText(artist);

        // button to search the song on YouTube
        Button btnListen = findViewById(R.id.btn_listen_on_youtube);
        btnListen.setOnClickListener(this);

        // button to share the recommendation by SMS
        Button btnSave = findViewById(R.id.btn_share_recommendation);
        btnSave.setOnClickListener(this);
    }

    // on click method
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_listen_on_youtube) {
            // String to search, including current song name and artist
            String youTubeSearch = getString(R.string.youTube, songName, artist);

            // uri to search song on YouTube
            Uri baseUri = Uri.parse("https://www.youtube.com/results");
            Uri.Builder builder = baseUri.buildUpon();
            builder.appendQueryParameter("search_query", youTubeSearch);
            Uri dataUri = builder.build();

            listenOnYoutube(dataUri);
        }

        if(v.getId() == R.id.btn_share_recommendation) {
            // message to send
            String message = getString(R.string.message, songName, artist);

            shareRecommendation(message);
        }

    }

    // listen on YouTube method
    private void listenOnYoutube(Uri dataUri) {
        // intent so send data
        Intent youTube = new Intent(Intent.ACTION_VIEW);
        // set data
        youTube.setData(dataUri);
        // start activity
        if(youTube.resolveActivity(getPackageManager()) != null) {
            startActivity(youTube);
        }
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