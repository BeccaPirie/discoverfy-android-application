package com.application.discoverfy;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.application.discoverfy.Connectors.RecentSongsService;
import com.application.discoverfy.Models.RecentSongs;

import java.util.ArrayList;

import static com.application.discoverfy.LoginActivity.SPOTIFY;

public class TestActivity extends AppCompatActivity {

    TextView displayUser;
    TextView recentlyPlayedSong;
    private RecentSongsService recentSongsService;
    private ArrayList<RecentSongs> recentlyPlayedSongs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        recentSongsService = new RecentSongsService(getApplicationContext());

        recentlyPlayedSong = findViewById(R.id.textView2);
        displayUser = findViewById(R.id.textView3);

        SharedPreferences sharedPreferences = this.getSharedPreferences(SPOTIFY, 0);
        displayUser.setText(sharedPreferences.getString("user_id", "no user"));

        getTracks();

    }


    private void getTracks() {
        recentSongsService.getRecentlyPlayedSongs(() -> {
            recentlyPlayedSongs = recentSongsService.getSongs();
            Log.d("TEST", String.valueOf(recentlyPlayedSongs));
            updateSong();
        });
    }

    private void updateSong() {
        if (recentlyPlayedSongs.size() > 0) {
            recentlyPlayedSong.setText(recentlyPlayedSongs.get(0).getName());
            RecentSongs recentSong = recentlyPlayedSongs.get(0);
        }
    }

}