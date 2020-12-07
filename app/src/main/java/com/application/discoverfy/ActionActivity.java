package com.application.discoverfy;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import static com.application.discoverfy.RecommendAdapter.EXTRA_ALBUM;
import static com.application.discoverfy.RecommendAdapter.EXTRA_ARTIST;
import static com.application.discoverfy.RecommendAdapter.EXTRA_SONG_TITLE;

public class ActionActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String tag = "Discoverfy";
    private String songName;
    private String artist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(tag, "is in onCreate" );
        setContentView(R.layout.activity_action);

        // variables to store the song title, the artist name and the album name
        TextView songTitle = findViewById(R.id.tv_song_title);
        TextView artistName = findViewById(R.id.tv_artist_name);
        TextView albumName = findViewById(R.id.tv_album_name);

        // DATA WILL BE DOWNLOADED FROM WEB SERVICE
        Intent displaySelected = getIntent();

        // set song title
        // getName() from RecentSongsService
        songName = displaySelected.getStringExtra(EXTRA_SONG_TITLE);
        songTitle.setText(songName);

        // set artist name
        //
        artist = displaySelected.getStringExtra(EXTRA_ARTIST);
        artistName.setText(artist);

        // set album name
        String album = displaySelected.getStringExtra(EXTRA_ALBUM);
        albumName.setText(album);

        // button to search the song on YouTube
        Button btnListen = findViewById(R.id.btn_listen_on_youtube);
        btnListen.setOnClickListener(this);

        // button to share the recommendation by SMS
        Button btnSave = findViewById(R.id.btn_share_recommendation);
        btnSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_listen_on_youtube) {
            // redirect the user to YouTube and search for the recommendation
            String youTubeSearch = getString(R.string.youTube, songName, artist);

            Uri baseUri = Uri.parse("https://www.youtube.com/results");
            Uri.Builder builder = baseUri.buildUpon();
            builder.appendQueryParameter("search_query", youTubeSearch);
            Uri dataUri = builder.build();

            listenOnYoutube(dataUri);
        }

        if(v.getId() == R.id.btn_share_recommendation) {
            // share the recommendation as a text message
            String message = getString(R.string.message, songName, artist);
            shareRecommendation(message);
        }

        if(v.getId() == R.id.btn_back_to_rp) {
            Intent back = new Intent(ActionActivity.this, RecentlyPlayedActivity.class);
            startActivity(back);
        }

    }

    private void listenOnYoutube(Uri dataUri) {
        Intent youTube = new Intent(Intent.ACTION_VIEW);
        youTube.setData(dataUri);
        if(youTube.resolveActivity(getPackageManager()) != null) {
            startActivity(youTube);
        }
    }

    private void shareRecommendation(String message) {
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("text/plain");
        share.putExtra("sms_body", message);
        if(share.resolveActivity(getPackageManager()) != null) {
            startActivity(share);
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