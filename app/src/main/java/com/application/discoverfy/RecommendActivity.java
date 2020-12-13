package com.application.discoverfy;

import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.application.discoverfy.Connectors.RecommendationsService;
import com.application.discoverfy.Models.Recommendations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.application.discoverfy.LoginActivity.AUTH_TOKEN;

public class RecommendActivity extends AppCompatActivity {

    // tag
    private static final String tag = "RecommendActivity";

    // key for current song name
    private static final String SONG = "SongName";
    // value for current song name
    private String songName;

    // adapter
    private RecommendAdapter recommendAdapter;

    // on create method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(tag, "is in onCreate" );
        setContentView(R.layout.activity_recommend);

        // TextView for the name of the song selected
        TextView songTitle = findViewById(R.id.tv_song_title_recommend);

        // put selected song in title
        SharedPreferences sharedPreferences = this.getSharedPreferences(getString(R.string.shared_pref_file), MODE_PRIVATE);
        songTitle.setText(sharedPreferences.getString("recent_song", "no song"));

        // create new array list
        List<Recommendations> songRecommendations = new ArrayList<Recommendations>();
        // initialise the RecyclerView
        RecyclerView recommendRecyclerView = findViewById(R.id.rv_recommended_songs);
        // set fixed size to true
        recommendRecyclerView.setHasFixedSize(true);
        // pass the adapter the list of recommendations
        recommendAdapter = new RecommendAdapter(songRecommendations);
        // set the adapter
        recommendRecyclerView.setAdapter(recommendAdapter);
        // set the layout manager
        recommendRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        downloadRecommendations();
    }

    private void downloadRecommendations() {
        // save the selected recently played songs id in shared preferences
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.shared_pref_file), MODE_PRIVATE);
        String songId = sharedPreferences.getString("recent_id", "");

        // endpoint
        Uri baseUri = Uri.parse("https://api.spotify.com/v1/recommendations");
        Uri.Builder builder = baseUri.buildUpon();
        builder.appendQueryParameter("seed_tracks", songId);
        Uri uri = builder.build();
        String ENDPOINT = uri.toString();

        // shared preferences
        SharedPreferences sharedPreferences2 = getSharedPreferences(getString(R.string.spotify), 0);

        // new json object request
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, ENDPOINT, null, response -> {
            RecommendationsService service = new RecommendationsService();
            List<Recommendations> songs = service.processRecommendations(response);
            if(songs.size()>0) {
                recommendAdapter.setRecommendations(songs);
                recommendAdapter.notifyDataSetChanged();
            } else {
                Toast.makeText(getApplicationContext(), getString(R.string.error_message), Toast.LENGTH_LONG).show();
            }
        }, error -> {
            Toast.makeText(getApplicationContext(), getString(R.string.error_message), Toast.LENGTH_LONG).show();
            Log.d(tag, error.getLocalizedMessage());
        }) {
            // get headers method
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                // set bearer token as a header
                Map<String, String> headers = new HashMap<>();
                String token = sharedPreferences2.getString(AUTH_TOKEN, "");
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