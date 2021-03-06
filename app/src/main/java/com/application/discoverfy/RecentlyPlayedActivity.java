package com.application.discoverfy;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.application.discoverfy.Adapters.RecentlyPlayedAdapter;
import com.application.discoverfy.Connectors.RecentSongsProcessor;
import com.application.discoverfy.Data.SongRepository;
import com.application.discoverfy.Models.RecentSongs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.application.discoverfy.LoginActivity.AUTH_TOKEN;

public class RecentlyPlayedActivity extends AppCompatActivity {

    // tag
    private static final String tag = "RecentlyPlayedActivity";

    // adapter
    private RecentlyPlayedAdapter recentAdapter;

    // on create method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(tag, "is in onCreate");
        setContentView(R.layout.activity_recently_played);

        // create list
        List<RecentSongs> recentlyPlayedSongs = new ArrayList<RecentSongs>();
        // initialise the RecyclerView
        RecyclerView recentRecyclerView = findViewById(R.id.rv_recently_played);
        // RecyclerView will not change in size
        recentRecyclerView.setHasFixedSize(true);
        // create new adapter and pass it the list of recently played songs
        recentAdapter = new RecentlyPlayedAdapter(recentlyPlayedSongs);
        // set the adapter
        recentRecyclerView.setAdapter(recentAdapter);
        // set the layout manager
        recentRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        downloadRecentSongs();
    }

    private void downloadRecentSongs() {
        // get songs from database
        List<RecentSongs> cachedSongs = SongRepository.getRepository(getApplicationContext()).getAllSongs();
        if (cachedSongs.size()>0) {
            recentAdapter.setRecentSongs(cachedSongs);
            recentAdapter.notifyDataSetChanged();
            return;
        }

        // endpoint
        String ENDPOINT = "https://api.spotify.com/v1/me/player/recently-played";

        // shared preferences
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.spotify), 0);

        // build volley request
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, ENDPOINT, null, response -> {
            RecentSongsProcessor service = new RecentSongsProcessor();
            List<RecentSongs> songs = service.processSongs(response);
            // store songs from the database
            SongRepository.getRepository(getApplicationContext()).storeRecentSongs(songs);
            if(songs.size()>0) {
                // update the data in the adapter
                recentAdapter.setRecentSongs(songs);
                recentAdapter.notifyDataSetChanged();
            } else {
                Toast.makeText(getApplicationContext(), getString(R.string.error_message), Toast.LENGTH_LONG).show();
            }
            }, error -> {
                Toast.makeText(getApplicationContext(), getString(R.string.error_message), Toast.LENGTH_LONG).show();
                Log.d(tag, error.getLocalizedMessage());
            }) {
            // get headers method
            @Override
            public Map<String, String> getHeaders() {
                // set bearer token as a header
                Map<String, String> headers = new HashMap<>();
                String token = sharedPreferences.getString(AUTH_TOKEN, "");
                String auth = "Bearer " + token;
                headers.put("Authorization", auth);
                return headers;
            }
        };

        // build queue and make request
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(jsonObjectRequest);

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