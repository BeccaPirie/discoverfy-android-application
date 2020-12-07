package com.application.discoverfy.Connectors;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.application.discoverfy.Models.RecentSongs;
import com.application.discoverfy.VolleyCallBack;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.application.discoverfy.LoginActivity.SPOTIFY;

public class RecentSongsService {

    private final static String ENDPOINT = "https://api.spotify.com/v1/me/player/recently-played";
    private ArrayList<RecentSongs> recentSongs = new ArrayList<>();
    private SharedPreferences sharedPreferences;
    private RequestQueue requestQueue;

    public RecentSongsService(Context context) {
        sharedPreferences = context.getSharedPreferences(SPOTIFY, 0);
        requestQueue = Volley.newRequestQueue(context);

    }

    public ArrayList<RecentSongs> getSongs() {
        return recentSongs;
    }

    public ArrayList<RecentSongs> getRecentlyPlayedSongs(final VolleyCallBack callBack) {
        Log.d("TEST", "getRecentlyPlayedSongs()");
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, ENDPOINT, null, response -> {
                    Gson gson = new Gson();
                    JSONArray jsonArray = response.optJSONArray("items");
                    for (int n = 0; n < jsonArray.length(); n++) {
                        try {
                            JSONObject object = jsonArray.getJSONObject(n);
                            object = object.optJSONObject("track");
                            RecentSongs recentSong = gson.fromJson(object.toString(), RecentSongs.class);
                            recentSongs.add(recentSong);
                            Log.d("TEST", String.valueOf(recentSongs));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    callBack.onSuccess();
                }, error -> {
                    // TODO: Handle error

                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                String token = sharedPreferences.getString("token", "");
                String auth = "Bearer " + token;
                headers.put("Authorization", auth);
                return headers;
            }
        };
        requestQueue.add(jsonObjectRequest);
        return recentSongs;
    }
}
