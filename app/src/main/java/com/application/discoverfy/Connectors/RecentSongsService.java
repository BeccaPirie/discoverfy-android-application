package com.application.discoverfy.Connectors;

import com.application.discoverfy.Models.RecentSongs;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RecentSongsService {

    // process songs method
    public List<RecentSongs> processSongs(JSONObject response) {
        // list for songs
        List<RecentSongs> recentSongs = new ArrayList<RecentSongs>();

        try {
            // get items array
            JSONArray itemsArray = response.getJSONArray("items");
            for (int i = 0, j = itemsArray.length(); i<j; i++) {
                // get every track object in the array
                JSONObject trackObject = itemsArray.getJSONObject(i);
                trackObject.getJSONObject("track");
                // create new RecentSongs object and set id and name
                RecentSongs songs = new RecentSongs();
                songs.setId(trackObject.getString("id"));
                songs.setName(trackObject.getString("name"));

                // get artists array
                JSONArray artistsArray = trackObject.getJSONArray("artists");
                for (int k = 0, n = artistsArray.length(); k<n; k++) {
                    // get artists objects from the array
                    JSONObject artistObject = artistsArray.getJSONObject(k);
                    // set artists
                    songs.setArtists(artistObject.getString("name"));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return recentSongs;
    }
}

/*
        // convert response
        // parse result with Gson
        Gson gson = new Gson();
        JSONArray jsonArray = response.optJSONArray("items");
        for (int n = 0; n < jsonArray.length(); n++) {
            try {
                JSONObject object = jsonArray.getJSONObject(n);
                object = object.optJSONObject("track");
                // add result to object
                RecentSongs recentSong = gson.fromJson(object.toString(), RecentSongs.class);
                recentSongs.add(recentSong);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return recentSongs;
    }


}*/

    // *****REDUNDANT CODE*****
/*
    private final static String ENDPOINT = "https://api.spotify.com/v1/me/player/recently-played";
    private ArrayList<RecentSongs> recentSongs = new ArrayList<>();
    private SharedPreferences sharedPreferences;
    private RequestQueue requestQueue;

    // constructor class
    public RecentSongsService(Context context) {
        sharedPreferences = context.getSharedPreferences(SPOTIFY, 0);
        requestQueue = Volley.newRequestQueue(context);
    }

    // get the list of songs
    public ArrayList<RecentSongs> getSongs() {
        return recentSongs;
    }

    // generate request
    public ArrayList<RecentSongs> getRecentlyPlayedSongs(final VolleyCallBack callBack) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, ENDPOINT, null, response -> {

            // parse result with Gson
            Gson gson = new Gson();
                    JSONArray jsonArray = response.optJSONArray("items");
                    for (int n = 0; n < jsonArray.length(); n++) {
                        try {
                            JSONObject object = jsonArray.getJSONObject(n);
                            object = object.optJSONObject("track");
                            // add result to object
                            RecentSongs recentSong = gson.fromJson(object.toString(), RecentSongs.class);
                            recentSongs.add(recentSong);
                            Log.d("TEST", String.valueOf(recentSongs));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    callBack.onSuccess();
                }, error -> {
                    Log.d("TEST", "error");
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
        // make request
        requestQueue.add(jsonObjectRequest);
        return recentSongs;
    }

 */