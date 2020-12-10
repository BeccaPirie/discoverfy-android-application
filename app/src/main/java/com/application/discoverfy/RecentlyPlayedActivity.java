package com.application.discoverfy;

import android.content.SharedPreferences;
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
import com.application.discoverfy.Connectors.RecentSongsService;
import com.application.discoverfy.Models.RecentSongs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.application.discoverfy.LoginActivity.SPOTIFY;

public class RecentlyPlayedActivity extends AppCompatActivity {

    // tag
    private static final String tag = "Discoverfy";

    // adapter
    private RecentlyPlayedAdapter recentAdapter;

    // on create method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(tag, "is in onCreate");
        setContentView(R.layout.activity_recently_played);

        // TextView to display the users Spotify user ID
        TextView displayUsername = findViewById(R.id.tv_username);

        // get the user ID from shared preferences and display in the TextView
        SharedPreferences sharedPreferences = this.getSharedPreferences(SPOTIFY, 0);
        displayUsername.setText(sharedPreferences.getString("user_id", "no user"));

        // placeholder data
        ArrayList<RecentlyPlayedListItem> recentlyPlayedListItems = new ArrayList<>();
        recentlyPlayedListItems.add(new RecentlyPlayedListItem("Wonder", "Shawn Mendes"));
        recentlyPlayedListItems.add(new RecentlyPlayedListItem("No Time To Die", "Billie Eilish"));
        recentlyPlayedListItems.add(new RecentlyPlayedListItem("What A Man Gotta Do", "Jonas Brothers"));
        recentlyPlayedListItems.add(new RecentlyPlayedListItem("Black And White", "Niall Horan"));
        recentlyPlayedListItems.add(new RecentlyPlayedListItem( "Before You Go", "Lewis Capaldi"));
        recentlyPlayedListItems.add(new RecentlyPlayedListItem( "Falling", "Harry Styles"));
        recentlyPlayedListItems.add(new RecentlyPlayedListItem("Wings", "Birdy"));
        recentlyPlayedListItems.add(new RecentlyPlayedListItem("Ghosts", "BANNERS"));
        recentlyPlayedListItems.add(new RecentlyPlayedListItem("Crash My Car", "COIN"));
        recentlyPlayedListItems.add(new RecentlyPlayedListItem("Angel", "FINNEAS"));
        recentlyPlayedListItems.add(new RecentlyPlayedListItem("New Me", "Ella Eyre"));
        recentlyPlayedListItems.add(new RecentlyPlayedListItem("The Joke", "Brandi Carlile"));
        recentlyPlayedListItems.add(new RecentlyPlayedListItem("My Kind of Love", "Emeli Sande"));
        recentlyPlayedListItems.add(new RecentlyPlayedListItem("feel something", "Bea Miller"));
        recentlyPlayedListItems.add(new RecentlyPlayedListItem("Hard Sometimes", "Ruel"));
        recentlyPlayedListItems.add(new RecentlyPlayedListItem("Fix You", "Coldplay"));
        recentlyPlayedListItems.add(new RecentlyPlayedListItem("New Map", "M83"));
        recentlyPlayedListItems.add(new RecentlyPlayedListItem("Stranger", "Vistas"));
        recentlyPlayedListItems.add(new RecentlyPlayedListItem("Tongue Tied", "Grouplove"));
        recentlyPlayedListItems.add(new RecentlyPlayedListItem("Teenage Blues", "Vistas"));

        // initialise the RecyclerView
        RecyclerView recentRecyclerView = findViewById(R.id.rv_recently_played);

        // recommendRecyclerView will not change in size
        recentRecyclerView.setHasFixedSize(true);

        // create a new Adapter and pass in the array list of recently played songs
        recentAdapter = new RecentlyPlayedAdapter(recentlyPlayedListItems);

        // set the LayoutManager
        RecyclerView.LayoutManager recentLayoutManager = new LinearLayoutManager(this);

        // pass the Adapter to the RecyclerView
        recentRecyclerView.setAdapter(recentAdapter);

        // pass the LayoutManager to the RecyclerView
        recentRecyclerView.setLayoutManager(recentLayoutManager);

        /* ***** API *****
        List<RecentSongs> recentlyPlayedSongs = new ArrayList<RecentSongs>();
        RecyclerView recentRecyclerView = findViewById(R.id.rv_recently_played);
        recentRecyclerView.setHasFixedSize(true);
        recentAdapter = new RecentlyPlayedAdapter(recentlyPlayedSongs);
        recentRecyclerView.setAdapter(recentAdapter);
        recentRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        downloadRecentSongs();
        */
    }


    private void downloadRecentSongs() {
        String ENDPOINT = "https://api.spotify.com/v1/me/player/recently-played";
        SharedPreferences sharedPreferences = getSharedPreferences(SPOTIFY, 0);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, ENDPOINT, null, response -> {
            RecentSongsService service = new RecentSongsService();
            List<RecentSongs> songs = service.processSongs(response);
            if(songs.size()>0) {
                //recentAdapter.setRecentSongs(songs);
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
            public Map<String, String> getHeaders() throws AuthFailureError {
                // set bearer token as a header
                Map<String, String> headers = new HashMap<>();
                String token = sharedPreferences.getString("token", "");
                String auth = "Bearer " + token;
                headers.put("Authorization", auth);
                return headers;
            }
        };

        // *****check internet connection*****
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