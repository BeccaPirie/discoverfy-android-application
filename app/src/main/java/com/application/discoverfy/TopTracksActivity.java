package com.application.discoverfy;

import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.application.discoverfy.Adapters.TopTracksAdapter;
import com.application.discoverfy.Connectors.TopTracksProcessor;
import com.application.discoverfy.Data.SongRepository;
import com.application.discoverfy.Models.TopTracks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.application.discoverfy.LoginActivity.AUTH_TOKEN;

public class TopTracksActivity extends AppCompatActivity implements View.OnClickListener {

    // adapter
    private TopTracksAdapter topAdapter;

    // time range to provide data for
    private String timeRange = "short_term";

    // on create method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_tracks);

        // short term button
        Button btnShortTerm = findViewById(R.id.btn_short_term);
        btnShortTerm.setOnClickListener(this);

        // medium term button
        Button btnMedTerm = findViewById(R.id.btn_medium_term);
        btnMedTerm.setOnClickListener(this);

        // long term button
        Button btnLongTerm = findViewById(R.id.btn_long_term);
        btnLongTerm.setOnClickListener(this);

        // create list
        List<TopTracks> topTracksList = new ArrayList<TopTracks>();
        // get the recycler view
        RecyclerView topRv = findViewById(R.id.rv_top_tracks);
        // set fixed size
        topRv.setHasFixedSize(true);
        // get adapter
        topAdapter = new TopTracksAdapter(topTracksList);
        // set adapter
        topRv.setAdapter(topAdapter);
        // set layout manager
        topRv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        // download top tracks
        downloadTopTracks();
    }

    // download top tracks
    private void downloadTopTracks() {

        // get songs from database
        List<TopTracks> cachedTopTracks = SongRepository.getRepository(getApplicationContext()).getAllTracks();
        if(cachedTopTracks.size() > 0) {
            topAdapter.setTopTracks(cachedTopTracks);
            topAdapter.notifyDataSetChanged();
        }

        // endpoint
        Uri baseUri = Uri.parse("https://api.spotify.com/v1/me/top/tracks");
        Uri.Builder builder = baseUri.buildUpon();
        builder.appendQueryParameter("time_range", timeRange);
        Uri uri = builder.build();
        String ENDPOINT = uri.toString();

        // shared preferences
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.spotify), MODE_PRIVATE);

        // new json object request
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, ENDPOINT, null, response -> {
            TopTracksProcessor processor = new TopTracksProcessor();
            List<TopTracks> topTracks = processor.processTopTracks(response);
            SongRepository.getRepository(getApplicationContext()).storeTopTracks(topTracks);
            if(topTracks.size()>0){
                topAdapter.setTopTracks(topTracks);
                topAdapter.notifyDataSetChanged();
            } else {
                Toast.makeText(getApplicationContext(), getString(R.string.error_message), Toast.LENGTH_LONG).show();
            }
        }, error -> {
            Toast.makeText(getApplicationContext(), getString(R.string.error_message), Toast.LENGTH_LONG).show();
        }) {
            // getHeaders method
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
        // build queue and make the request
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(jsonObjectRequest);
    }

    // on click method
    @Override
    public void onClick(View v) {
        // if short term button is clicked
        if(v.getId() == R.id.btn_short_term) {
            // set time range to medium term and download tracks
            timeRange = "short_term";
            downloadTopTracks();
        }
        // if medium term button is clicked
        if(v.getId() == R.id.btn_medium_term) {
            // set time range to short term and download tracks
            timeRange = "medium_term";
            downloadTopTracks();
        }
        // if long term button is clicked
        if(v.getId() == R.id.btn_long_term) {
            // set time range to long term and download tracks
            timeRange = "long_term";
            downloadTopTracks();
        }

    }
}