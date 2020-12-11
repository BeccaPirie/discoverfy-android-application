package com.application.discoverfy;

import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
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

        // if saved instance state is not null, save the song name and display it in the title when activity is reinitialised
        if (savedInstanceState != null) {
            songName = savedInstanceState.getString(SONG);
            songTitle.setText(songName);
        }

        // put selected song in title
        SharedPreferences sharedPreferences = this.getSharedPreferences("TEST", MODE_PRIVATE);
        songTitle.setText(sharedPreferences.getString("recent_song", "no song"));

        // create an array list to store the items in the RecyclerView
        ArrayList<RecommendListItem> recommendListItems = new ArrayList<>();
        recommendListItems.add(new RecommendListItem("Careless", "Ella Eyre"));
        recommendListItems.add(new RecommendListItem("Someone You Loved", "Lewis Capaldi"));
        recommendListItems.add(new RecommendListItem("Castle on the Hill", "Ed Sheeran"));
        recommendListItems.add(new RecommendListItem("Midnight Sky", "Miley Cyrus"));
        recommendListItems.add(new RecommendListItem("Save Myself", "Ashe"));
        recommendListItems.add(new RecommendListItem("All I Want", "Kodaline"));
        recommendListItems.add(new RecommendListItem("Dream", "Shawn Mendes"));
        recommendListItems.add(new RecommendListItem("All You're Dreaming Of", "Liam Gallagher"));
        recommendListItems.add(new RecommendListItem("Lonely", "Noah Cyrus"));
        recommendListItems.add(new RecommendListItem("hopeless", "Clinton Kane"));
        recommendListItems.add(new RecommendListItem("Electric Love", "BORNS"));
        recommendListItems.add(new RecommendListItem("Green Light", "Lorde"));
        recommendListItems.add(new RecommendListItem("Blinding Lights", "The Weeknd"));
        recommendListItems.add(new RecommendListItem( "Golden", "Harry Styles"));
        recommendListItems.add(new RecommendListItem("heart", "flor"));
        recommendListItems.add(new RecommendListItem("Take Me Back Home", "Coasts"));
        recommendListItems.add(new RecommendListItem("superstars", "Christian French"));
        recommendListItems.add(new RecommendListItem("come out and play", "Billie Eilish"));
        recommendListItems.add(new RecommendListItem("Waves", "Dean Lewis"));
        recommendListItems.add(new RecommendListItem("Youngblood", "5 Seconds of Summer"));

        // initialise the RecyclerView
        RecyclerView recommendRecyclerView = findViewById(R.id.rv_recommended_songs);

        // recommendRecyclerView will not change in size
        recommendRecyclerView.setHasFixedSize(true);

        // set the LayoutManager
        RecyclerView.LayoutManager recommendLayoutManager = new LinearLayoutManager(this);

        // create a  Adapter and pass in the array list of recommendations
        recommendAdapter = new RecommendAdapter(recommendListItems);

        // pass the LayoutManager to the RecyclerView
        recommendRecyclerView.setLayoutManager(recommendLayoutManager);

        // pass the Adapter to the RecyclerView
        recommendRecyclerView.setAdapter(recommendAdapter);

        /*
         ***** API *****
        List<Recommendations> songRecommendations = new ArrayList<Recommendations>();
        RecyclerView recommendRecyclerView = findViewById(R.id.rv_recommended_songs);
        recommendRecyclerView.setHasFixedSize(true);
        recommendAdapter = new RecommendAdapter(songRecommendations);
        recommendRecyclerView.setAdapter(recommendAdapter);
        recommendRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        downloadRecommendations();
        */
    }

    private void downloadRecommendations() {
        // shared preferences
        SharedPreferences sharedPreferences = getSharedPreferences("TEST", MODE_PRIVATE);
        String id = sharedPreferences.getString("recent_id", "");

        // endpoint
        Uri baseUri = Uri.parse("https://api.spotify.com/v1/recommendations");
        Uri.Builder builder = baseUri.buildUpon();
        builder.appendQueryParameter("seed_tracks", id);
        Uri uri = builder.build();
        String ENDPOINT = uri.toString();

        // new json object request
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, ENDPOINT, null, response -> {
            RecommendationsService service = new RecommendationsService();
            List<Recommendations> songs = service.processRecommendations(response);
            if(songs.size()>0) {
                //recommendAdapter.setRecommendations(songs);
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
                String token = sharedPreferences.getString("token", "");
                String auth = "Bearer " + token;
                headers.put("Authorization", auth);
                return headers;
            }
        };

        // build queue and make request
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(jsonObjectRequest);
    }

    // on save instance state method
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        // replace value for key
        outState.putString(SONG, songName);
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